import scala.util.Random

final class Faker {
  private val chars: List[Char] =  ('a' to 'z').toList

  def letterify(s: String): String = {
    s match {
      case ss: String ⇒ ss.replaceAll("[?]", chars(Random.nextInt(25)).toString)
      case null ⇒ "" // this is proper way to do it. it is only for testing
    }

  }

  def numerify(s: String): String = {
    s match {
      case ss: String ⇒ ss.replaceAll("[#]", Random.nextInt(9).toString)
      case null ⇒ "" // this is proper way to do it. it is only for testing
    }
  }

  def bothify(s: String): String = {
    letterify(numerify(s))
  }
}
