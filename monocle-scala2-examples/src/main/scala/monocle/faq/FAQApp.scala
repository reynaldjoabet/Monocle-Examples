package monocle
package faq

import monocle.Iso

object FAQApp extends App {

  val m = Map("one" -> 1, "two" -> 2)
  // root is a trick to help type inference. without it, we would get an error
  val root = Iso.id[Map[String, Int]]

  println(root.index("two").replace(0)(m))
  println(root.index("three").replace(3)(m))
  println(root.at("three").replace(Some(3))(m))
  println(root.at("two").replace(None)(m))
  println(root.at("two").replace(Some(0))(m))

}
