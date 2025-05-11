package lab3

class LinearInequality[T](val a: List[T], b: T) (implicit num: LinearInequalityOps[T] = null) {
  def solve(v: List[T]): Boolean = {
    val lhs = v.zip(a).foldLeft(num.zero())((accum, elem) => (accum, elem) match {
                                                case (accum, (x, y)) => num.plus(accum, num.times(x, y))
                                              })
    num.lt(lhs, b)
  }
}

trait LinearInequalityOps[T] {
  def plus(a: T, b: T): T
  def times(a: T, b: T): T
  def lt(a: T, b: T): Boolean
  def zero(): T
}

object LinearInequalityOps {
  implicit def numeric_ops[T](implicit num: Numeric[T]): LinearInequalityOps[T] =
    new LinearInequalityOps[T] {
      def plus(a: T, b: T): T = num.plus(a, b)
      def times(a: T, b: T): T = num.times(a, b)
      def lt(a: T, b: T): Boolean = num.lt(a, b)
      def zero(): T = num.fromInt(0)
    }

  implicit def boolean_ops: LinearInequalityOps[Boolean] =
    new LinearInequalityOps[Boolean] {
      def plus(a: Boolean, b: Boolean): Boolean = a || b
      def times(a: Boolean, b: Boolean): Boolean = a && b
      def lt(a: Boolean, b: Boolean): Boolean = !a && b
      def zero(): Boolean = false
    }
}
