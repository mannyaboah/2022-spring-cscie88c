package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTraitTest extends StandardTest {

  "plus" should {

    "add two MyInt values correctly 😄" in {
      // add your unit tests for MyInt below
      val testI = MyInt(8)
      val testII = MyInt(16)
      val testIII = MyInt(20)

      MyInt(5).plus(MyInt(3)) shouldBe testI
      MyInt(8).plus(MyInt(8)) shouldBe testII
      MyInt(10).plus(MyInt(10)) shouldBe testIII
    }

    "add two MyBool values correctly 😀" in {
      // add your unit tests for MyBool below
      MyBool(true).plus(MyBool(false)) shouldBe (MyBool(true))
      MyBool(true).plus(MyBool(true)) shouldBe (MyBool(true))
      MyBool(false).plus(MyBool(false)) shouldBe (MyBool(false))
    }
  }
}
