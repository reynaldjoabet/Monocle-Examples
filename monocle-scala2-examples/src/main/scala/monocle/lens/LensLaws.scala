package monocle
package lens

import monocle.Lens

object LensLaws {

  def getReplace[S, A](l: Lens[S, A], s: S): Boolean =
    l.replace(l.get(s))(s) == s

  def replaceGet[S, A](l: Lens[S, A], s: S, a: A): Boolean =
    l.get(l.replace(a)(s)) == a

}
