import org.scalatest.{MustMatchers, WordSpec}

class UrlDecoratorServiceTest extends WordSpec with MustMatchers {
  private def withService(f: UrlDecoratorService => Unit): Unit = {
    f(new UrlDecoratorService)
  }

  "Service" should {
    "append utm to string url" in {
      withService { s =>
        s.processUrl("http://dummy-url") must be("http://dummy-url?add-utm")
        s.processUrl("http://dummy-url?123") must be("http://dummy-url?123&add-utm")
      }
    }
  }

  "Service" should {
    "append utm to int" in {
      withService { s =>
        s.processUrl(123) must be("123?add-utm")
      }
    }
  }
}
