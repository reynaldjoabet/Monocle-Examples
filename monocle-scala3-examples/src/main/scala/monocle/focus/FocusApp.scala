package monocle
package focus

import java.time.YearMonth

import cats.syntax.all.*

import monocle.overview.{Address, User => OverviewUser}
import monocle.syntax.*
import monocle.Focus._

object FocusApp extends App {

  val anna = OverviewUser("Anna", Address(12, "high street"))
  println(anna.focus(_.name).replace("Bob"))
  println(anna.focus(_.address.streetNumber).modify(_ + 1))

  // Update an optional field of a case class (Scala 3 only)
  val anna2 = User("Anna", Some(Address(12, "high street")))
  val bob   = User("bob", None)

  val k: Option[Int] = Some(6)

  println(anna2.focus(_.address.some.streetNumber).modify(_ + 1))
  println(bob.focus(_.address.some.streetNumber).modify(_ + 1))

  // Update a single element inside a List (Scala 3 only)

  val anna3 = UserWithCard(
    "Anna",
    List(
      DebitCard("4568 5794 3109 3087", YearMonth.of(2022, 4), 361),
      DebitCard("5566 2337 3022 2470", YearMonth.of(2024, 8), 990)
    )
  )

  val bob2 = UserWithCard("Bob", List())

  println(anna3.focus(_.debitCards.index(0).expirationDate).replace(YearMonth.of(2026, 2)))
  println(
    bob2.focus(_.debitCards.index(1).as[DebitCard].expirationDate).replace(YearMonth.of(2026, 2))
  )

}
