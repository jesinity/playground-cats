package it.jesinity.cats.book.chapter03

import cats.Functor
import cats.instances.function._
import cats.syntax.functor._
import cats.instances.option._
import cats.instances.list._

import scala.language.higherKinds
// for Functor

object FunctorCompositon {

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  def main(args: Array[String]): Unit = {

    // for map
    val func1 = (a: Int) => a + 1
    val func2 = (a: Int) => a * 2
    val func3 = (a: Int) => a + "!"
    val func4 = func1.map(func2).map(func3)
    println(func4(123))

    val math1 = doMath(Option(20))
    val math2 = doMath(List(1, 2, 3))

    println(math1)
    println(math2)
  }
}
