package monocle
package optics

import monocle.Iso

object IsoLaws {

  def roundTripOneWay[S, A](i: Iso[S, A], s: S): Boolean   = i.reverseGet(i.get(s)) == s
  def roundTripOtherWay[S, A](i: Iso[S, A], a: A): Boolean = i.get(i.reverseGet(a)) == a

}
