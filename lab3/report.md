% Лабораторная работа № 3 «Обобщённые классы в Scala»
% 12 мая 2025 г.
% Александр Старовойтов, ИУ9-61Б

# Цель работы

Целью данной работы является приобретение навыков разработки обобщённых классов
на языке Scala с использованием неявных преобразований типов.

# Индивидуальный вариант

Класс LinEq[T], представляющий линейное неравенство вида $a_1x_1 + a2x2 +
\ldots{} + a_n x_n < b$ с операцией, проверяющей, удовлетворяет ли заданный
список значений переменных неравенству. Коэффициенты неравенства, а также
значения переменных имеют тип T, который либо является числовым, либо булевским.
Для булевского типа умножение — это конъюнкция, сложение — дизъюнкция, и порядок
задан как false < true.


# Реализация

```scala
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
```

# Тестирование

Тесты:

```scala
import lab3.LinearInequality

class LinearInequalitySuite extends munit.FunSuite {
  test("Solve integers false") {
    val a = new lab3.LinearInequality(List(1, 1, 1), 5)

    val got = a.solve(List(1, 1, 3))

    assert(got == false)
  }

  test("Solve integers true") {
    val a = new lab3.LinearInequality(List(1, 1, 1), 5)

    val got = a.solve(List(1, 1, 2))

    assert(got == true)
  }

  test("Solve booleans false") {
    val a = new lab3.LinearInequality(List(false, true), true)

    val got = a.solve(List(true, true))

    assert(got == false)
  }

  test("Solve booleans true") {
    val a = new lab3.LinearInequality(List(false, true), true)

    val got = a.solve(List(true, false))

    assert(got == true)
  }
}
```

# Вывод

Приобрел навыки разработки обобщённых классов на языке Scala с использованием
неявных преобразований типов.
