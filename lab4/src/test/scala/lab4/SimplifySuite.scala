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
