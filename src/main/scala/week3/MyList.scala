package week3

trait MyList[T] {
  def head: T
  def tail: MyList[T]
  def isEmpty: Boolean
}

class Nil[T] extends MyList[T] {
  def head = throw new NoSuchElementException("Nil.head")
  def tail = throw new NoSuchElementException("Nil.tail")
  def isEmpty = true
  override def toString: String = "-|"
}

class Cons[T](val head: T, val tail: MyList[T]) extends MyList[T] {
  def isEmpty = false

  override def toString: String = head + " -> " + tail.toString
}
