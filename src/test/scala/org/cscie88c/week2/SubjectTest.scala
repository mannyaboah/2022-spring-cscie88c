package org.cscie88c.week2

import org.scalatest.wordspec.AnyWordSpec

// write unit tests for Subject below

class SubjectTest extends AnyWordSpec {
  "Subject Constructor and Functions" when {
    val testData: List[Subject] = List(
      "1,Physical Education,true",
      "2,Chemistry,false"
    ).map(Subject(_))

    "Executed" should {
      "Create two subjects with respective attributes" in {
        val subject1 = "Physical Education"
        val subject2 = "Chemistry"

        assert(testData.size == 2)
        assert(testData(0).name == subject1 && testData(1).name == subject2)
      }
    }
  }
}
