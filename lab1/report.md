% Лабораторная работа № 1 «Введение в функциональное
  программирование на языке Scala»
% 5 марта 2025 г.
% Александр Старовойтов, ИУ9-61Б

# Цель работы

Целью данной работы является ознакомление с программированием на языке Scala на
основе чистых функций.

# Индивидуальный вариант

Функция `mul: (List[Int], List[Int]) => List[Int]`, выполняющая умножение двух
целых чисел, каждое из которых представлено списком степеней своих простых
делителей.

# Реализация и тестирование

Лабораторная работа была выполнена в виде проекта с использованием sbt и тестами
(toolkit-test).

Реализация:

```scala
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
```

Тесты:

```scala
import lab1.Lab1

class MulSuite extends munit.FunSuite {
  test("If both of the arguments is Nil, mul should return Nil") {
    assert(Lab1.mul(Nil, Nil) == Nil)
    assert(Lab1.mul(Nil, List(1, 1)) == Nil)
    assert(Lab1.mul(List(1), Nil) == Nil)
  }

  test("mul of two numbers with same length gives sum of its positions") {
    assert(Lab1.mul(List(1), List(1)) == List(2))
    assert(Lab1.mul(List(1, 2, 3), List(1, 2, 3)) == List(2, 4, 6))
  }

  test("mul of numbers with different length complements smaller one with zeroes") {
    assert(Lab1.mul(List(1, 2), List(1, 2, 3)) == List(2, 4, 3))
  }
}
```

build.sbt:
```scala
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "com.bmstu-iu9"

lazy val lab1 = project
  .in(file("."))
  .settings(
    name := "Lab1",
    libraryDependencies += "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
  )
```

# Вывод

В рамках данной лабораторной работы, я познакомился с программированием на языке
Scala на основе чистых функций. В процессе реализации, я научился пользоваться
сопоставлением с образцом, и узнал, как в Scala устроена организация кода в
проект и его тестирование.
