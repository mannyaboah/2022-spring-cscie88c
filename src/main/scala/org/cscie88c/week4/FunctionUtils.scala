package org.cscie88c.week4

object FunctionUtils {

  // complete the implementation of the higher order functions below

  def add5(x: Int) = x + 5

  def applyNtimes(n: Int)(x: Int)(f: Int => Int): Int =
    if (n == 1) f(x) else f(x) + applyNtimes(n - 1)(x)(f)

  def applyNtimesExp(n: Int)(x: Int)(f: Int => Int): Int =
    if (n == 1) f(x) else if (n <= 0) 1 else f(x) * applyNtimes(n - 1)(x)(f)

  def applyNtimesFold(n: Int)(x: Int)(f: Int => Int): Int =
    List.fill(n)(f(x)).foldLeft(0)(_ + _)

  def applyNtimesExpFold(n: Int)(x: Int)(f: Int => Int): Int =
    List.fill(n)(f(x)).foldLeft(1)(_ * _)

  def myPositivePower(x: Int, n: Int): Int =
    applyNtimesExp(n)(x)((x: Int) => x + 0)

  def myPositivePowerFold(x: Int, n: Int): Int =
    applyNtimesExpFold(n)(x)((x: Int) => x)

  def deferredExecutor(
      name: String
    )(
      f: Int => Int
    ): Int => Int = { (x: Int) =>
    println(s"running on deferred executor ${name} with value ${x}")
    f(x)
  }
}
