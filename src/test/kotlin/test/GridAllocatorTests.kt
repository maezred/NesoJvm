package test

import cafe.neso.util.jvm.grid.allocation.GridAllocator
import org.junit.Test

/**
 * Created by moltendorf on 2017-05-04.
 */

class GridAllocatorTests {
  val allocator = GridAllocator()

  @Test fun testSequential() {
    var id = 1

    val test = { x: Int, z: Int->
      val toId = allocator.getAllocationAt(x*2048 - 64, z*2048 - 64).id
      val fromId = allocator[id].id

      assert(id == toId)
      assert(id++ == fromId)
    }

    test(0, 0)// Center

    test(0, -1)  // North
    test(1, -1) // North East
    test(1, 0)  // East
    test(1, 1)  // South East
    test(0, 1) // South
    test(-1, 1) // South West
    test(-1, 0) // West
    test(-1, -1)  // North West

    test(0, -2) // North
    test(1, -2)
    test(2, -2) // North East
    test(2, -1)
    test(2, 0) // East
    test(2, 1)
    test(2, 2) // South East
    test(1, 2)
    test(0, 2) // South
    test(-1, 2)
    test(-2, 2) // South West
    test(-2, 1)
    test(-2, 0) // West
    test(-2, -1)
    test(-2, -2) // North West
    test(-1, -2)

    test(0, -3)
    test(1, -3)
    test(2, -3)
    test(3, -3)
    test(3, -2)
    test(3, -1)
    test(3, 0)
    test(3, 1)
    test(3, 2)
    test(3, 3)
    test(2, 3)
    test(1, 3)
    test(0, 3)
    test(-1, 3)
    test(-2, 3)
    test(-3, 3)
    test(-3, 2)
    test(-3, 1)
    test(-3, 0)
    test(-3, -1)
    test(-3, -2)
    test(-3, -3)
    test(-2, -3)
    test(-1, -3)

    test(0, -4)
  }

  @Test fun testRandom() {
    val test = { x: Int, z: Int->
      val allocation = allocator.getAllocationAt(x*2048 - 64, z*2048 - 64)

      assert(allocation.id == allocator[allocation.id].id)
    }

    // Random checks.
    for (it in 1 .. 1024) {
      test((Math.random()*1000).toInt(), (Math.random()*1000).toInt())
    }
  }
}
