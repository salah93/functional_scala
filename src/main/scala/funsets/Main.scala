package funsets

object Main extends App {
  import FunSets._
  import math.abs
  println(contains(singletonSet(1), 1))
  val even: Set = (x: Int) => x % 2 == 0
  val s = map(even, x => x + 1)
  forall(s, x => abs(x) % 2 == 1)
}