1 + 2
def sqrtIter ( guess : Double , x : Double ) : Double =
  if ( isGoodEnough ( guess , x )) guess
  else sqrtIter(improve(guess, x ), x )

def isGoodEnough(d: Double, d1: Double): Boolean = math.abs(d * d - d1) < 0.001

def improve(d: Double, d1: Double): Double = (d + d1 / d) / 2

def sqrt(x: Double) = sqrtIter(1, x)

sqrt(2)