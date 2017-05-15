package cafe.neso.util.jvm.grid.allocation

/**
 * Created by moltendorf on 2017-05-04.
 */

data class Allocation internal constructor(val gridX: Int, val gridZ: Int, val allocator: GridAllocator) {
  val size = allocator.allocationSize

  // - 1 as it's "through" not "to"
  val lowerX = gridX*allocator.allocationSize + allocator.offset
  val lowerZ = gridZ*allocator.allocationSize + allocator.offset
  val upperX = lowerX + size - 1
  val upperZ = lowerZ + size - 1

  val northernZ = lowerZ
  val easternX = upperX
  val southernZ = upperZ
  val westernX = lowerX

  val radius = maxOf(Math.abs(gridX), Math.abs(gridZ))
  val diameter: Int
  val layer: Int

  init {

    if (allocator.odd) {
      diameter = radius*2 + 1
      layer = radius
    } else {
      diameter = radius*2
      layer = maxOf(if (gridX > 0) gridX - 1 else gridX, if (gridZ > 0) gridZ - 1 else gridZ)
    }
  }

  val id: Int by lazy {
    if (layer == 0) {
      return@lazy 1
    }

    var id = 2 + sequence(radius - 1)

    id += if (gridZ == -layer) {
      if (gridX >= 0) {
        gridX
      } else {
        diameter*4 + gridX - 4
      }
    } else {
      if (gridX == radius) {
        diameter + gridZ - 1
      } else if (gridZ == radius) {
        diameter*2 - gridX - 2
      } else {
        diameter*3 - gridZ - 3
      }
    }

    id
  }

  companion object {
    private var cache = arrayListOf(0)

    private fun sequence(index: Int): Int {
      assert(index >= 0)

      if (index >= cache.size) {
        cache.add(index*8 + sequence(index - 1))
      }

      return cache[index]
    }
  }
}
