package recfun


object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println(countChange(4, List(1, 2)))
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c -1, r - 1) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def loop(acc: Int, l: List[Char]): Boolean = {
      if (acc < 0)  false
      else {
        l match {
          case Nil => true
          case h :: Nil => h match {
            case '(' => false
            case ')' => acc - 1 == 0
            case _ => acc == 0
          }
          case h:: t => h match {
            case '(' => loop( acc + 1, t)
            case ')' => loop(acc - 1, t)
            case _ =>  loop(acc, t)
          }
        }
      }
    }
    loop(0, chars)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.nonEmpty && money > 0) {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
    else if(money == 0) 1
    else 0
  }
}
