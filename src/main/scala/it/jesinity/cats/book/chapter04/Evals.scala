package it.jesinity.cats.book.chapter04

import cats.Eval

object Evals {

  def factorial(n: BigInt): Eval[BigInt] =
    if (n == 1) {
      Eval.now(n)
    } else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }

  def main(args: Array[String]): Unit = {

    val greeting = Eval.always { println("Step 1"); "Hello" }.map { str =>
      println("Step 2"); s"$str world"
    }

    greeting.value

    val ans = for {
      a <- Eval.now { println("Calculating A"); 40 }
      b <- Eval.always { println("Calculating B"); 2 }
    } yield {
      println("Adding A and B")
      a + b
    }

    println(ans.value)
    println(ans.value)

    println(factorial(50000).value)

  }


  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

  def foldRight2[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) =>
      b.map(fn(a, _))
    }.value
}
