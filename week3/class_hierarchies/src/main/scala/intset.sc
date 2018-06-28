abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
}

class Empty extends IntSet {
  def contains(x: Int) = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
}

class NonEmpty(h: Int, l: IntSet, r: IntSet) extends(IntSet) {
 def contains(x: Int): Boolean = {
    if (x < h) l contains x
    else if (x > h) r contains x
    else true
 }

  def incl(x: Int): IntSet = {
    if (x < h) new NonEmpty(h, l incl x, r)
    else if (x > h) new NonEmpty(h, l, r incl x)
    else this
  }
  override def toString = "{" + l + h + r + "}"
}

val t = new NonEmpty(3, new Empty, new Empty)
val t2 = t incl 4