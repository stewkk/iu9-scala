% Лабораторная работа № 2 «Введение в объетно-ориентированное
  программирование на языке Scala»
% 16 апреля 2025 г.
% Александр Старовойтов, ИУ9-61Б

# Цель работы
Целью данной работы является изучение базовых объектно-ориентированных
возможностей языка Scala.

# Индивидуальный вариант

Элемент свободной группы с двумя образующими. Представляет собой либо пустое
слово (единица группы), либо конечное слово, составленное из четырёх символов a,
ã, b, b̃ таким образом, что в нём a не появляется рядом с ã, а b не появляется
рядом с b̃. Операция сложения двух слов определяется как их конкатенация с
последующим сокращением пар aã, ãa, bb̃ и b̃b. Операция взятия обратного элемента
— как переворачивание слова с одновременной заменой a на ã, ã — на a, b — на b̃ и
b̃ — на b.

# Реализация и тестирование

`build.sbt`:
```scala
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "com.bmstu-iu9"

lazy val lab2 = project
  .in(file("."))
  .settings(
    name := "Lab2",
    libraryDependencies += "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test,
    scalacOptions += "-deprecation"
  )
```

```scala
package lab2


class FreeGroup private {
  // TODO: добавить первичный конструктор и заменить на неизменяемое значение
  private var _value = ""

  def this(v: String) = {
    this()
    _value = normalize(v)
  }

  def value = _value

  private def normalize(str: String): String = {
    val res = str
                 .replaceAll("̃ãa", "")
                 .replaceAll("aã", "")
                 .replaceAll("bb̃", "")
                 .replaceAll("b̃b", "")
    return res match {
      case _ if str == res => res
      case _ => normalize(res)
    }
  }

  override def toString: String =
    s"$value"

  def +(other: FreeGroup) = new FreeGroup(value+other.value)

  def unary_- : FreeGroup = {
    def inverseRec(prefix: String, suffix: String): String = {
      suffix.splitAt(2) match {
        case ("", rest) => prefix
        case ("a", rest) => inverseRec(prefix+"̃a", rest)
        case ("b", rest) => inverseRec(prefix+"̃b", rest)
        case ("ã", rest) => inverseRec(prefix+"a", rest)
        case ("b̃", rest) => inverseRec(prefix+"b", rest)
        case ("ab", rest) => inverseRec(prefix+"̃a", "b"+rest)
        case ("aa", rest) => inverseRec(prefix+"̃a", "a"+rest)
        case ("ba", rest) => inverseRec(prefix+"̃b", "a"+rest)
        case ("bb", rest) => inverseRec(prefix+"̃b", "b"+rest)
      }
    }

    new FreeGroup(inverseRec("", value).reverse)
  }
}
```

## Тестирование

```scala
import lab2.FreeGroup

class User

class FreeGroupSuite extends munit.FunSuite {
  test("Can get string value") {
    assert(new lab2.FreeGroup("ãb").value == "ãb")
    assert(new lab2.FreeGroup("ãb").toString == "ãb")
  }

  test("Constructor normalizes value") {
    assert(new lab2.FreeGroup("̃ãa").value == "")
    assert(new lab2.FreeGroup("aaãã").value == "")
  }

  test("Add concatenates and normalizes") {
    val lhs = new lab2.FreeGroup("aba")
    val rhs = new lab2.FreeGroup("ãb")

    val got = lhs + rhs

    assert(got.value == "abb")
  }

  test("Inverse inverses chars and reverses value") {
    val got = -(new lab2.FreeGroup("abãb̃"))

    assert(got.value == new lab2.FreeGroup("bab̃ã").value)
  }
}
```

# Вывод

Изучил базовые объектно-ориентированные возможности языка Scala.

