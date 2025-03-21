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
    val got = new lab2.FreeGroup("abãb̃").inverse()

    assert(got.value == new lab2.FreeGroup("bab̃ã").value)
  }
}
