def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(_) => Nil
  case y :: ys => y :: init(ys)
}

init(List(1, 2, 3))

def removeAt[T](n: Int, xs: List[T]): List[T] = {
 xs match {
   case Nil => xs
   case h :: t =>
     if (n == 0) t
     else h :: removeAt(n - 1, t)
 }
}


removeAt(1, List(1, 2, 3))
removeAt(2, List(1, 2, 3))
removeAt(3, List(1, 2, 3))
removeAt(4, List(1, 2, 3))
def flatten(xs: List[Any]): List[Any] = {
  xs match {
    case Nil => Nil
    case x :: t => x match  {
      case i :: tail => i :: flatten(tail) ::: flatten(t)
      case i => i :: flatten(t)
    }
  }
}

flatten(List(1, List(2, 3)))

import week5._
mergesort.msort(List(1, 2, -4, 9, 4))



def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (s, rest) = xs span (_ == x)
    s :: pack(rest)
}
pack(List("a", "a", "a", "b", "c", "c", "a")) map (l => (l.head, l.length))
