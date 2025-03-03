package lab1

object Lab1 {
  def main(args: Array[String]): Unit = {
    println("Hello")
  }

  def mul: (List[Int], List[Int]) => List[Int] = {
    case (Nil, Nil) => Nil
    case (l :: lhs, Nil) => l :: mul(lhs, Nil)
    case (Nil, r :: rhs) => r :: mul(Nil, rhs)
    case (l :: lhs, r :: rhs) => l+r :: mul(lhs, rhs)
  }
}
