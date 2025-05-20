% Лабораторная работа № 4 «Case-классы и сопоставление с образцом в Scala»
% 12 мая 2025 г.
% Александр Старовойтов, ИУ9-61Б

# Цель работы

Целью данной работы является приобретение навыков разработки case-классов на
языке Scala для представления абстрактных синтаксических деревьев.

# Индивидуальный вариант

Абстрактный синтаксис арифметических выражений:

Expr → Expr + Expr | Expr - Expr | Expr * Expr | Expr / Expr
  | Expr ^ Expr | - Expr | NUMBER | VARNAME
Требуется написать функцию optimize : Expr => Expr выполняющую следующие оптимизации выражения:

- сложение и вычитание с нулём,
- умножение и деление с единицей и нулём,
- нулевая и единичная степень, единица в степени,
- вычисление значений подвыражений, не содержащих переменных (деление на нуль вычисляться не должно),

# Реализация

```scala
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
```

# Тестирование

```scala
import lab4._

class SimplifySuite extends munit.FunSuite {
  test("Create variable expr") {
    val a = new VarExpr("x");
  }

  test("Create number expr") {
    val a = new NumExpr(5);
  }

  test("Create binary expr") {
    val a = new PlusExpr(new NumExpr(2), new NumExpr(2))
  }

  test("Simplify addition to 0") {
    val a = new PlusExpr(new VarExpr("x"), new NumExpr(0))

    val got = Main.simplify(a)

    assert(got == new VarExpr("x"))
  }

  test("Simplify substraction from 0") {
    val a = new MinusExpr(new NumExpr(0), new VarExpr("x"))

    val got = Main.simplify(a)

    assert(got == new UnaryMinusExpr(new VarExpr("x")))
  }

  test("Simplify multiplication by 1") {
    val a = new MulExpr(new NumExpr(1), new VarExpr("x"))

    val got = Main.simplify(a)

    assert(got == new VarExpr("x"))
  }

  test("Simplify multiplication by 0") {
    val a = new MulExpr(new NumExpr(0), new VarExpr("x"))

    val got = Main.simplify(a)

    assert(got == new NumExpr(0))
  }

  test("Simplify expressions without variables") {
    val a = MulExpr(new NumExpr(2), new NumExpr(2))

    val got = Main.simplify(a)

    assert(got == new NumExpr(4))
  }
}
```

# Вывод

Приобрел навыки разработки case-классов на языке Scala для представления
абстрактных синтаксических деревьев.
