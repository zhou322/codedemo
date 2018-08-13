
trait UrlDecorator[T] {
  def withUtm(url: T): String
}

trait UrlDecoratorImpl {
  private val utm = "add-utm"
  implicit object  stringUrlDecorator extends UrlDecorator[String] {
    override def withUtm(url: String): String = {
      if (url.contains("?")) {
        s"$url&$utm"
      } else {
        s"$url?$utm"
      }
    }
  }

  implicit object intUrlDecorator extends UrlDecorator[Int] {
    override def withUtm(url: Int): String = s"${url.toInt}?$utm"
  }

  protected def appendUtm[T](url: T)(implicit urlImpl: UrlDecorator[T]): String = urlImpl.withUtm(url)
}
