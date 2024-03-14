package monocle
package traversal

import monocle.Traversal

object TraversalLaws{
  def modifyGetAll[S, A](t: Traversal[S, A], s: S, f: A => A): Boolean =
    t.getAll(t.modify(f)(s)) == t.getAll(s).map(f)

  def composeModify[S, A](t: Traversal[S, A], s: S, f: A => A, g: A => A): Boolean =
    t.modify(g)(t.modify(f)(s)) == t.modify(g compose f)(s)
}
