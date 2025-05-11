import lab3.LinearInequality

class FreeGroupSuite extends munit.FunSuite {
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
