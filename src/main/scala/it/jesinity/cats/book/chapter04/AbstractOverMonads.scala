package it.jesinity.cats.book.chapter04

import cats.Monad
import cats.syntax.functor._ // for map
import cats.syntax.either._  // for map
import cats.syntax.flatMap._ // for flatMap
import scala.language.higherKinds

import cats.instances.option._ // for Monad
import cats.instances.list._
import cats.instances.either._
// for Monad

object AbstractOverMonads {

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x * x + y * y

  def main(args: Array[String]): Unit = {

    val a = sumSquare(Option(3), Option(4))
    val b = sumSquare(List(1, 2, 3), List(4, 5))
    val c = sumSquare(1.asRight[String], 3.asRight[String])
    val d = sumSquare("33".asLeft[Int], 3.asRight[String])

    println(a)
    println(b)
    println(c)
    println(d)

  }
}
