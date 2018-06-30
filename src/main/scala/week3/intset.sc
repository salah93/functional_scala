abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int) = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
  override def toString = ""
}

class NonEmpty(val h: Int, val l: IntSet, val r: IntSet) extends(IntSet) {
  def contains(x: Int): Boolean = {
    if (x < h) l contains x
    else if (x > h) r contains x
    else true
  }

  def union(other: IntSet): IntSet = (
    (l union r) union other) incl h

  def incl(x: Int): IntSet = {
    if (x < h) new NonEmpty(h, l incl x, r)
    else if (x > h) new NonEmpty(h, l, r incl x)
    else this
  }
  override def toString = "{" + l + h + r + "}"
}


val t = new NonEmpty(3, Empty, Empty)
val t2 = t incl 4
val t4 = new NonEmpty(2, Empty, Empty)
t union t4