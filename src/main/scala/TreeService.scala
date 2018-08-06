sealed trait Tree[A]
case class Leaf[A](value: A) extends Tree[A]
case class Node[A](value: A, left: Tree[A], right:Tree[A]) extends Tree[A]

object TreeService extends App {
  def hight[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) =>
        println(s"Return height 1")
        1
      case Node(_, left, right) => 1 + Math.max(hight(left), hight(right))
    }
  }

  val d = Node[Int](1, Leaf[Int](1), Leaf[Int](1))
  val data = Node[Int](1, Leaf[Int](1), d)

  println(s"${hight(data)}")
}

