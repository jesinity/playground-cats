package it.jesinity.cats.book.chapter04

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._
// for Monad

/**
  * testing monadic operations on Monads
  */
object MonadsForOption {

  def main(args: Array[String]): Unit = {

    val opt1: Option[Int] = Monad[Option].pure(3)
    val opt2: Option[Int] = Monad[Option].flatMap(opt1)(a => Some(a + 2))
    val opt3: Option[Int] = Monad[Option].map(opt1)(a => a + 2)
    println(opt1)
    println(opt2)
    println(opt3)

    val list1 = Monad[List].pure(3)
    val list2 = Monad[List].flatMap(List(1, 2, 3))(a => List(a, a * 10))
    val list3 = Monad[List].map(list2)(a => a + 123)
    println(list1)
    println(list2)
    println(list3)

  }
}
