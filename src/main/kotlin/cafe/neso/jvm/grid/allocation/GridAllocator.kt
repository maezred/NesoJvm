package cafe.neso.jvm.grid.allocation

import cafe.neso.core.extension.Bool
import java.lang.Math.floor
import kotlin.coroutines.experimental.buildSequence

/**
 * Created by moltendorf on 2017-05-04.
 */

class GridAllocator(val plotSize: Int = 128, val allocationSize: Int = 2048, val odd: Bool = true, val offset: Int = -64) {
  val squares = if (odd) {
    buildSequence {
      yield(Square(0, 1, 1, 1, 0))
      yieldAll(generateSequence(Square(1, 2, 9, 2, 2)) { Square(it.id + 1, it.max + 1, it.max + (it.moves + 2)*4, it.radius + 1, it.moves + 2) })
    }
  } else {
    generateSequence(Square(0, 1, 4, 1, 1)) {
      Square(it.id + 1, it.max + 1, it.max + (it.moves + 2)*4, it.radius + 1, it.moves + 2)
    }
  }

  // todo: this can definitely be optimized
  operator fun get(id: Int): Allocation {
    assert(id > 0)

    if (id == 1) {
      return Allocation(0, 0, this)
    }

    val square = squares.first { it.max >= id }
    var local = id - square.min

    // Top-left
    var turn = square.max - square.min - square.id + 1

    if (local >= turn) {
      local -= turn

      return Allocation(local - square.id, -square.id, this)
    }

    // Left
    turn -= square.moves

    if (local >= turn) {
      local -= turn

      return Allocation(-square.id, -local + square.id, this)
    }

    // Bottom
    turn -= square.moves

    if (local >= turn) {
      local -= turn

      return Allocation(-local + square.id, square.id, this)
    }

    // Right
    turn -= square.moves

    if (local >= turn) {
      local -= turn

      return Allocation(square.id, local - square.id, this)
    }

    // Top-right
    return Allocation(local, -square.id, this)
  }

  fun getAllocationAt(x: Int, z: Int): Allocation {
    return Allocation(
      gridX = floor((x - offset).toDouble()/allocationSize).toInt(),
      gridZ = floor((z - offset).toDouble()/allocationSize).toInt(),
      allocator = this
    )
  }

  data class Square(val id: Int, val min: Int, val max: Int, val radius: Int, val moves: Int)
}
