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
}
