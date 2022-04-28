package org.cscie88c.week12

import scala.util.Random

object DataGenerator {

  val rnd = new Random()

  def intStream(min: Int, max: Int): LazyList[Int] =
    LazyList.iterate(rnd.nextInt(max - min) + min)(_ =>
      rnd.nextInt(max - min) + min
    )

  val customerIdStream = intStream(1000, 9999).map(i => s"CS${i}")

  val dayStream = intStream(1, 31)

  val monthStream = intStream(1, 12)

  val yearStream = intStream(12, 15)

  val amountStream = intStream(40, 120)

  val dateStream = dayStream.zip(monthStream).zip(yearStream).map {
    case ((d, m), y) => s"${d}-${m}-${y}"
  }

  val customerTransactionStream: LazyList[CustomerTransaction] =
    customerIdStream.zip(dateStream).zip(amountStream).map {
      case ((c, d), a) => CustomerTransaction(c, d, a)
    }

}
