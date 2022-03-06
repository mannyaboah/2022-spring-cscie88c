package org.cscie88c.week2

//-- Student
class Student(
    cid: Int,
    cfirstName: String,
    clastName: String,
    cemail: String,
    cgender: String,
    ccountry: String
  ) {
  var id: Int = cid
  var firstName: String = cfirstName
  var lastName: String = clastName
  var email: String = cemail
  var gender: String = cgender
  var country: String = ccountry

  val firstAndLast: String = s"${lastName}, ${firstName}"
}
object Student {

  val allStudents: List[Student] = List(
    "1,Emmy,Conrart,econrart0@gizmodo.com,Male,China",
    "2,Marin,Blasoni,mblasoni1@edublogs.org,Female,United States",
    "3,Jesse,Chismon,jchismon2@hostgator.com,Male,China",
    "4,Delmore,Scriver,dscriver3@boston.com,Male,United States",
    "5,Jocelyn,Blaxlande,jblaxlande4@europa.eu,Female,China"
  ).map(Student(_))

  def apply(csvInsert: String): Student = {
    val attribute = csvInsert.split(",")
    new Student(
      cid = attribute(0).toInt,
      cfirstName = attribute(1),
      clastName = attribute(2),
      cemail = attribute(3),
      cgender = attribute(4),
      ccountry = attribute(5)
    )
  }

  def studentNamesByCountry(country: String): List[String] =
    allStudents.filter(_.country == country).map(_.firstAndLast)

  def studentTotalsByCountry(country: String): Int =
    allStudents.filter(_.country == country).size

}
