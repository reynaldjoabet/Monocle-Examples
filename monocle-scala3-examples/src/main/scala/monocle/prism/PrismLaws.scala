package monocle
package prism

import monocle.Prism

object PrismLaws {

  def partialRoundTripOneWay[S, A](p: Prism[S, A], s: S): Boolean = p.getOption(s) match {
    case None    => true
    case Some(a) => p.reverseGet(a) == s
  }

  def partialRoundTripOtherWay[S, A](p: Prism[S, A], a: A): Boolean =
    p.getOption(p.reverseGet(a)).contains(a)

}
