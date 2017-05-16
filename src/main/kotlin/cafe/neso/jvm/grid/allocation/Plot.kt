package cafe.neso.jvm.grid.allocation

/**
 * Created by moltendorf on 2017-05-04.
 */

data class Plot internal constructor(val lowerX: Int, val lowerZ: Int, val allocation: Allocation) {
  val size = allocation.allocator.plotSize

  // - 1 as it's "through" not "to"
  val upperX = lowerX + size - 1
  val upperZ = lowerZ + size - 1

  val northernZ = lowerZ
  val easternX = upperX
  val southernZ = upperZ
  val westernX = lowerX
}
