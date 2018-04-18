package it.jesinity.cats.playground.either

import java.text.SimpleDateFormat
import java.util.Date

import cats.syntax.either._

object EitherExample {

  def main(args: Array[String]): Unit = {

    val a: Either[String, Int] = 3.asRight[String]
    val b: Either[String, Int] = 4.asRight[String]
    val c: Either[String, Int] = "ciccio".asLeft[Int]

    val result1 = for {
      x <- a
      y <- b
    } yield x * x + y * y
    println(result1)

    val result2 = for {
      x <- a
      y <- c
      z <- b
    } yield x * x + y * y + z * z
    println(result2)

    val d: Either[NumberFormatException, Int]    = Either.catchOnly[NumberFormatException]("200".toInt)
    val e: Either[IllegalArgumentException, Int] = Either.catchOnly[IllegalArgumentException](new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toInt)
    val f: Either[NumberFormatException, Int]    = Either.catchOnly[NumberFormatException](new SimpleDateFormat("yyyyMMdd").format(new Date()).toInt)

    val result3 = for {
      x <- d
      y <- e
      z <- f
    } yield x * x + y * y + z * z
    println(result3)
  }
}
