package it.jesinity.cats.book.chapter03

object FunctionFunctor {

  import cats.instances.function._ // for Functor
  import cats.syntax.functor._

  val func1: Int => Double =
    (x: Int) => x.toDouble

  val func2: Double => Double =
    (y: Double) => y * 2

  def main(args: Array[String]): Unit = {

    val result: Double = (func1 map func2)(1)
    // composition using map
    // res7: Double = 2.0
    val resul2: Double = (func1 andThen func2)(1) // composition using andThen
    // res8: Double = 2.0
    val result3 = func2(func1(1))

  }
}
