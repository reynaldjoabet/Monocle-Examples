package monocle
package prism

import monocle.{Focus, Iso, Prism}
import monocle.macros.{GenIso, GenPrism}
import monocle.prism.Json.{JNull, JNum, JStr}

object PrismGenerationApp extends App {

  val rawJNum: Prism[Json, JNum] = GenPrism[Json, JNum]
  println(rawJNum.getOption(JNum(4.5)))
  println(rawJNum.getOption(JStr("Hello")))

  // val jNum: Prism[Json, Double] = GenPrism[Json, JNum].andThen(Focus[JNum](_.v))
  val jNull: Prism[Json, Unit] = GenPrism[Json, JNull.type]
    .andThen(Iso[JNull.type, Unit](_ => ())(_ => JNull))

}
