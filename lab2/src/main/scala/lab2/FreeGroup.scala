package lab2

class FreeGroup private {
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

  def inverse() : FreeGroup = {
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
