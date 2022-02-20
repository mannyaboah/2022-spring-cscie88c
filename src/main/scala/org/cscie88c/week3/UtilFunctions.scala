package org.cscie88c.week3

object UtilFunctions {

  def mult2(x: Int, y: Int): Int = x * y

  def pythTest(
      x: Int,
      y: Int,
      z: Int
    ): Boolean = Math.pow(x, 2) + Math.pow(y, 2) == Math.pow(z, 2)

  val pythTriplesUpto100: List[(Int, Int, Int)] = for {
    x <- (1 to 100).toList
    y <- (1 to 100).toList
    z <- (1 to 100).toList
    if pythTest(x, y, z)
  } yield (x, y, z)

}
