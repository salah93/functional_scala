class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must not be zero!")

  def this(x: Int) = this(x, 1)
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  def numer = x

  def denom = y

  private var _iscool = false
  //setter
  def iscool_=(cool: Boolean) = _iscool = cool
  // getter
  def iscool = _iscool
  def +(that: Rational) = {
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom)
  }

  def -(that: Rational) = this + -that


  def unary_- = new Rational(-numer, denom)

  override def toString: String = {
    val g = gcd(x, y)
    this.numer / g + "/" + this.denom / g
  }
}
val x = new Rational(1, 3)
x.numer
val y = new Rational(5,7)
val z = new Rational(3, 2)
x.+(y)
-x
y - x
x - y - z
new Rational(9)
x.iscool
x.iscool = true
x.iscool