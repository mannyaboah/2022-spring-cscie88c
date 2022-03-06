package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class ListUtilsTest extends StandardTest {
  "ListUtils" when {
    "calling ones" should {
      "return the correct value 😄" in {
        val testData = List(1.0, 1.0, 1.0, 1.0, 1.0);
        val res = ListUtils.ones(5)

        testData shouldBe res
      }

    }

    "calling zeros" should {
      "return the correct value 😄" in {
        val testData = List(0.0, 0.0, 0.0, 0.0, 0.0);
        val res = ListUtils.zeros(5)

        testData shouldBe res
      }
    }

    "Calculating character counts in string" should {

      "Check that Hello world has accurate character count 😄" in {
        val hello = "Hello world"
        val map = ListUtils.charCounts(hello)

        map('H') shouldBe 1
        map('e') shouldBe 1
        map('l') shouldBe 3
        map('w') shouldBe 1
        map('o') shouldBe 2
        map('r') shouldBe 1
        map('d') shouldBe 1
      }

      "Check that map contains all characters of the alphabet 😄" in {
        val testList = ('a' to 'z').toList
        val testString = "The quick brown fox jumps over the lazy dog"
        val map = ListUtils.charCounts(testString)

        testList.forall(c => map(c) > 0) shouldBe true

      }

      "Return the top N characters in a List 😄" in {
        val testString = "See me coming"
        val res = ListUtils.topN(3)(ListUtils.charCounts(testString))

        res('e') shouldBe 3
        res('n') shouldBe 1
        res('m') shouldBe 2
        res.size shouldBe 3
      }
    }
  }

}
