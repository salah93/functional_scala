import week3._

object MyList {
  def apply[T](): MyList[T] = {
    Nil
  }
  def apply[T](n: T): MyList[T] = {
    new Cons(n, Nil)
  }
  def apply[T](n: T, o: T): MyList[T] = {
    new Cons(n, new Cons(o, Nil))
  }
}


MyList()
MyList(1)
MyList(1, 2)

val a: Array[NonEmpty] = Array(
  new NonEmpty(1, Empty, Empty))
// error
//val b: Array[IntSet] = a
//b(0) = Empty
//val s: NonEmpty = a(0)

