package week4

trait Expr {
  def eval: Int = {
    this match {
      case Number(n) => n
      case Sum(x, y) => x.eval + y.eval
      case Prod(x, y) => x.eval * y.eval
      //case Var(x) => x
    }
  }
  def show: String = this match {
    case Number(n) => n.toString
    case Sum(e1, e2) =>
      s"${e1.show} + " + (e2 match {
        case Prod(x, y) => s"(${e2.show})"
        case _ => s"${e2.show}"
      })
    case Prod(e1, e2) => s"${e1.show} * ${e2.show}"
  }
}
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Var(x: String) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
