import math.abs

val tolerance = 0.001
def isCloseEnough(x: Double, y: Double) = abs((x - y) / x) / x < tolerance
def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
}

def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
fixedPoint(x => 1 + x / 2)(1)
def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1)
sqrt(4)

