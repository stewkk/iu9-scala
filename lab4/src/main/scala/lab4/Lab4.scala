package lab4

import scala.math.pow

abstract class Expr

case class VarExpr(v: String) extends Expr
case class NumExpr(v: Int) extends Expr
case class PlusExpr(lhs: Expr, rhs: Expr) extends Expr
case class MinusExpr(lhs: Expr, rhs: Expr) extends Expr
case class MulExpr(lhs: Expr, rhs: Expr) extends Expr
case class DivExpr(lhs: Expr, rhs: Expr) extends Expr
case class ExpExpr(lhs: Expr, rhs: Expr) extends Expr
case class UnaryMinusExpr(expr: Expr) extends Expr

object Main {
  def simplify(expr: Expr): Expr = {
    val simplified = expr match {
      case e @ VarExpr(_) => e
      case e @ NumExpr(_) => e
      case e @ PlusExpr(lhs, rhs) => PlusExpr(simplify(lhs), simplify(rhs))
      case e @ MinusExpr(lhs, rhs) => MinusExpr(simplify(lhs), simplify(rhs))
      case e @ MulExpr(lhs, rhs) => MulExpr(simplify(lhs), simplify(rhs))
      case e @ DivExpr(lhs, rhs) => DivExpr(simplify(lhs), simplify(rhs))
      case e @ ExpExpr(lhs, rhs) => ExpExpr(simplify(lhs), simplify(rhs))
      case e @ UnaryMinusExpr(expr) => UnaryMinusExpr(simplify(expr))
    };
    simplified match {
      case DivExpr(_ @ NumExpr(1), other) => other
      case DivExpr(other, _ @ NumExpr(1)) => other
      case DivExpr(zero @ NumExpr(0), _) => zero
      case PlusExpr(_ @ NumExpr(lhs), _ @ NumExpr(rhs)) => NumExpr(lhs+rhs)
      case MinusExpr(_ @ NumExpr(lhs), _ @ NumExpr(rhs)) => NumExpr(lhs-rhs)
      case MulExpr(_ @ NumExpr(lhs), _ @ NumExpr(rhs)) => NumExpr(lhs*rhs)
      case DivExpr(_ @ NumExpr(lhs), _ @ NumExpr(rhs)) => NumExpr(lhs/rhs)
      case ExpExpr(_ @ NumExpr(lhs), _ @ NumExpr(rhs)) => NumExpr(pow(lhs.toDouble, rhs.toDouble).toInt)
      case UnaryMinusExpr(_ @ NumExpr(op)) => NumExpr(-op)
      case PlusExpr(other, _ @ NumExpr(0)) => other
      case PlusExpr(_ @ NumExpr(0), other) => other
      case MinusExpr(other, _ @ NumExpr(0)) => other
      case MinusExpr(_ @ NumExpr(0), _ @ UnaryMinusExpr(other)) => other
      case MinusExpr(_ @ NumExpr(0), other) => simplify(UnaryMinusExpr(other))
      case MulExpr(_ @ NumExpr(1), other) => other
      case MulExpr(other, _ @ NumExpr(1)) => other
      case MulExpr(_, zero @ NumExpr(0)) => zero
      case MulExpr(zero @ NumExpr(0), _) => zero
      case ExpExpr(_, _ @ NumExpr(0)) => NumExpr(1)
      case ExpExpr(other, _ @ NumExpr(1)) => other
      case ExpExpr(_ @ NumExpr(1), _) => NumExpr(1)

      case other => other
    }
  }

  def main(args: Array[String]): Unit = {
    println("Hello")
  }
}
