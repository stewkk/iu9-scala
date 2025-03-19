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
}
