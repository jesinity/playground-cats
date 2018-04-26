package it.jesinity.cats.book.chapter04

import cats.data.State
import State._

object StateMonad {

  def main(args: Array[String]): Unit = {

    val a = State[Int, String] { state =>
      (state, s"The state is $state")
    }

    // Get the state and the result:
    val (state, result) = a.run(10).value
    // state: Int = 10
    // result: String = The state is 10
    // Get the state, ignore the result:
    val state2 = a.runS(10).value
    // state: Int = 10
    // Get the result, ignore the state:
    val result2 = a.runA(10).value
    // result: String = The state is 10

    val step1 = State[Int, String] { num =>
      val ans = num + 1
      (ans, s"Result of step1: $ans")
    }

    val step2 = State[Int, String] { num =>
      val ans = num * 2
      (ans, s"Result of step2: $ans")
    }

    val both = for {
      a <- step1
      b <- step2
    } yield (a, b)

    val (state3, result3) = both.run(20).value

    println(state3)
    println(result3)

    val program: State[Int, (Int, Int, Int)] = for {
      a <- get[Int]
      _ <- set[Int](a + 1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c)

    println(program.run(30).value)

  }

}
