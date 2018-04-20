package it.jesinity.cats.book.chapter02

import cats.Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.semigroup._ // for |+|
import cats.instances.map._
import cats.instances.int._
import cats.instances.option._ // for Monoid
import cats.instances.tuple._

/**
  * an example on how semigroups can be combined.
  */
object CombiningSemigroupsExample {

  def main(args: Array[String]): Unit = {

    "Scala" |+| " with " |+| "Cats"
    Option(1) |+| Option(2)

    val map1 = Map("a" -> 1, "b" -> 2)
    val map2 = Map("b" -> 3, "d" -> 4)
    map1 |+| map2

    val tuple1 = ("hello", 123)
    val tuple2 = ("world", 321)
    tuple1 |+| tuple2

  }

}
