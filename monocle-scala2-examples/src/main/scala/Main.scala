import monocle.syntax.all._

object Main extends App {

  // Thanks to the syntax import, all case classes are now enriched with optics manipulators. With those, applying discount to a shelf is as simple as:
  case class Product(id: String, name: String, price: Double)

  case class Shelf(id: String, product: Product)

  case class Display(id: String, kind: String, shelves: List[Shelf])

  case class Alley(id: String, displays: List[Display])

  case class Shop(alleys: List[Alley])

  // The task sounds simple - we want to apply a 10% discount in the entire shop. The entire shop stays the same, only prices change. Do you imagine doing it the old fashion way? sounds like a lot of map and copy. Try it yourself as an exercise!
  val water: Product  = Product("1", "Bottle of water", 6.99)
  val milk: Product   = Product("2", "Milk 1l", 3.85)
  val cheese: Product = Product("3", "Cheese", 7.99)
  val ham: Product    = Product("4", "Ham", 9.99)

  val shop = Shop(
    alleys = List(
      Alley(
        id = "1",
        displays = List(
          Display("1", "Ambient", List(Shelf("1", water), Shelf("2", milk))),
          Display("2", "Chilled", List(Shelf("3", cheese), Shelf("4", ham)))
        )
      )
    )
  )

  val shelf = Shelf("1", water)

  shelf.focus(_.product.price).modify(_ * 0.9)

  // A word of explanation focus tells the optics how to navigate to the price. Then we apply the discount by calling modify.

  // Now that we know the basics, let’s apply the discount to the entire shop as defined above.

  val discounted =
    shop
      .focus(_.alleys)
      .each
      .refocus(_.displays)
      .each
      .refocus(_.shelves)
      .each
      .refocus(_.product.price)
      .modify(_ * 0.9)

  // val discounted = shop
  // .focus(_.alleys.each.displays.each.shelves.each.product.price)
  // .modify(_ * 0.9)
  // Now there’s a bit of repeating focus and each. They mean that we focus on a List type of field, and then instruct the optics to apply the upcoming combinators to each of items. This resembles the workings of map or forEach. Then once we reach out the shelf, the logic is the same as above
}
