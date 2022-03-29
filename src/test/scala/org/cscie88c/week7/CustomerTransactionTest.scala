package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import org.cscie88c.week7.CustomerTransaction._

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    "create a case class from csv string correctly 😀" in {

      val test = CustomerTransaction("id1,Johnny Test,100")

      test.getOrElse(None) shouldBe CustomerTransaction(
        "id1",
        "Johnny Test",
        100.0
      )
    }
    "load and clean raw CSV data file 😀" in {
      // add unit tests below
      val transactions = readFromCSVFile("data/dirty-retail-data-sample.csv")

      transactions.size shouldBe 5
    }
  }

}
