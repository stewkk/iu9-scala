import lab1.Lab1

class MulSuite extends munit.FunSuite {
  test("If both of the arguments is Nil, mul should return Nil") {
    assert(Lab1.mul(Nil, Nil) == Nil)
  }

  test("mul of two numbers with same length gives sum of its positions") {
    assert(Lab1.mul(List(1), List(1)) == List(2))
    assert(Lab1.mul(List(1, 2, 3), List(1, 2, 3)) == List(2, 4, 6))
  }

  test("mul of numbers with different length complements smaller one with zeroes") {
    assert(Lab1.mul(List(1, 2), List(1, 2, 3)) == List(2, 4, 3))
    assert(Lab1.mul(Nil, List(1, 1)) == List(1, 1))
    assert(Lab1.mul(List(1), Nil) == List(1))
  }
}
