class UrlDecoratorService extends UrlDecoratorImpl {
  def processUrl(url: String): String = appendUtm[String](url)
  def processUrl(url: Int): String = appendUtm[Int](url)
}
