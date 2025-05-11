package lab3

class LinearInequality[T](val a: List[T], b: T) (implicit num: Numeric[T] = null) {
  def solve(v: List[T]): Boolean = {
    val lhs = v.zip(a).foldLeft(num.fromInt(0))((accum, elem) => (accum, elem) match {
                                                case (accum, (x, y)) => num.plus(accum, num.times(x, y))
                                              })
    num.lt(lhs, b)
  }
}
