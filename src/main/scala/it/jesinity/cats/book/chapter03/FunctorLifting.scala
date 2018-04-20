package it.jesinity.cats.book.chapter03

import scala.language.higherKinds
import cats.Functor
import cats.instances.list._
import cats.instances.option._ // for Functor

object FunctorLifting {

  def main(args: Array[String]): Unit = {

    val func = (x: Int) => x + 1
    // func: Int => Int = <function1>
    val liftedFunc: Option[Int] => Option[Int] = Functor[Option].lift(func)
    liftedFunc(Option(1))
  }
}
