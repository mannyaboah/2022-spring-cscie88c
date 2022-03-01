package org.cscie88c.week4

object FunctionUtils {

  // complete the implementation of the higher order functions below

  def add5(x: Int) = x + 5

  def applyNtimes(n: Int)(x: Int)(f: Int => Int): Int =
    if (n == 1) f(x) else f(x) + applyNtimes(n - 1)(x)(f)

  def applyNtimesExp(n: Int)(x: Int)(f: Int => Int): Int =
    if (n == 1) f(x) else if (n <= 0) 1 else f(x) * applyNtimes(n - 1)(x)(f)

  // Questionable
  def applyNtimesFold(n: Int)(x: Int)(f: Int => Int): Int =
    List.fill(n)(x).foldLeft(0)(f(_) + f(_))

  def myPositivePower(x: Int, n: Int): Int =
    applyNtimesExp(n)(x)((x: Int) => x + 0)

  def deferredExecutor(
      name: String
    )(
      f: Int => Int
    ): Int => Int = { (x: Int) =>
    println(s"running on deferred executor ${name} with value ${x}")
    f(x)
  }
}
