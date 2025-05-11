package lab4

abstract class Expr

case class VarExpr(v: String) extends Expr
case class NumExpr(v: Integer) extends Expr
case class PlusExpr(lhs: Expr, rhs: Expr) extends Expr
case class MinusExpr(lhs: Expr, rhs: Expr) extends Expr
case class MulExpr(lhs: Expr, rhs: Expr) extends Expr
case class DivExpr(lhs: Expr, rhs: Expr) extends Expr
case class ExpExpr(lhs: Expr, rhs: Expr) extends Expr
case class UnaryMinusExpr(expr: Expr) extends Expr

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello")
  }
}
