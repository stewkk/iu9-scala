package lab1

object Lab1 {
  def main(args: Array[String]): Unit = {
    println("Hello")
  }

  def mul: (List[Int], List[Int]) => List[Int] = {
    def mulNonZero: (List[Int], List[Int]) => List[Int] = {
      case (Nil, Nil) => Nil
      case (l :: lhs, Nil) => l :: mulNonZero(lhs, Nil)
      case (Nil, r :: rhs) => r :: mulNonZero(Nil, rhs)
      case (l :: lhs, r :: rhs) => l+r :: mulNonZero(lhs, rhs)
    }

    {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (lhs, rhs) => mulNonZero(lhs, rhs)
    }
  }
}
