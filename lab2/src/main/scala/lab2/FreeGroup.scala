package lab2

class FreeGroup private {
  private var _value = ""

  def value: String = _value

  def this(v: String) = {
    this()
    _value = normalize(v)
  }

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
}
