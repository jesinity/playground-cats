package it.jesinity.cats.playground.validated

import com.typesafe.config.{ConfigException, ConfigFactory}
import cats._
import cats.data.Validated.{Invalid, Valid}
import cats.data._
import cats.syntax.validated._
import it.jesinity.cats.playground.configuration.Getter

case class Employee(name: String, age: Int, department: String, role: String)

object ValidationTest {

  import it.jesinity.cats.playground.configuration._

  val configuration = ConfigFactory.load()

  import cats.syntax._
  import cats.syntax.apply._

  def attemptGet[T: Getter](key: String): ValidatedNel[ConfigException, T] =
    Validated
      .catchOnly[ConfigException](configuration.get[T](key))
      .toValidatedNel

  def main(args: Array[String]): Unit = {

    val vName: ValidatedNel[ConfigException, String]       = attemptGet[String]("key")
    val vAge: ValidatedNel[ConfigException, Int]           = attemptGet[Int]("age")
    val vDepartment: ValidatedNel[ConfigException, String] = attemptGet[String]("department")
    val vRole: ValidatedNel[ConfigException, String]       = attemptGet[String]("role")

    val user: ValidatedNel[ConfigException, Employee] = (vName, vAge, vDepartment, vRole).mapN(Employee.apply)

    user match {
      case Valid(u) =>
        println(s"got user $u")

      case Invalid(misssing) =>
        println(misssing)
        val allMissingField = misssing.toList.mkString("\n")
        println(s"missing fields:\n$allMissingField")
    }
  }
}
