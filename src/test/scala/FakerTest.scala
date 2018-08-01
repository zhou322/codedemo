import org.scalatest.{MustMatchers, WordSpec}

class FakerTest extends WordSpec with MustMatchers {
  private def withService(f: Faker ⇒ Unit): Unit = {
    f(new Faker)
  }

  "letterify" should {
    "replace ? by any lower case char" in {
      withService { f ⇒
        f.letterify("???") must fullyMatch regex """[a-z][a-z][a-z]"""
      }
    }

    "replace ? any lower case char at any postion" in {
      withService { f ⇒
        f.letterify("Test?") must fullyMatch regex """Test[a-z]"""
      }
    }

    "return empty string if input is null" in {
      withService { f ⇒
        f.letterify(null) must be("")
      }
    }
  }

  "numerify" should {
    "replace # by any number from 0 to 9" in {
      withService { f ⇒
        f.numerify("###") must fullyMatch regex """[0-9][0-9][0-9]"""
      }
    }

    "replace # by any number from 0 to 9 at any position" in {
      withService { f ⇒
        f.numerify("Test#") must fullyMatch regex """Test[0-9]"""
      }
    }

    "return empty string if input is null" in {
      withService { f ⇒
        f.numerify(null) must be("")
      }
    }
  }


  "bothify" should {
    "replace ? and # and same time" in {
      withService { f ⇒
        f.bothify("Test?#") must fullyMatch regex """Test[a-z][0-9]"""
      }
    }
  }

  "letterify Randomness" should {
    "randomness" in {
      withService { f ⇒
        val r = testHelper[String](3)(f.letterify, "???")
        r.length must be(3)
      }
    }
  }

  "numerify Randomness" should {
    "randomness" in {
      withService { f ⇒
        val r = testHelper[String](3)(f.numerify, "###")
        r.length must be(3)
      }
    }
  }

  "botify randomness" should {
    "randomness" in {
      withService { f ⇒
        val r = testHelper[String](3)(f.bothify, "##??")
        r.length must be(3)
      }
    }
  }

  private def testHelper[T](c: Int)(f: T ⇒ T, s: T): List[T] = {
    (1 to c).map(_ ⇒ f(s)).toList
  }
}
