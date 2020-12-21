import scala.annotation.tailrec
import scala.collection.Map
import scala.io.Source

object TwentyOne extends App {
  var array = Source.fromInputStream(this.getClass.getResourceAsStream("twentyone.txt")).getLines().toList

  val linePattern = "(.*) \\(contains (.*)\\)$".r

  @tailrec
  def process(lines: List[String], aller2Ing: Map[String, List[String]]): Map[String, List[String]] = {
    lines match {
      case line :: tail => {
        val linePattern(ingText, allergenes) = line
        val ingredients = ingText.split(" ")
        val map = allergenes.split(", ").map(al => (al ->aller2Ing.get(al)
          .fold(ingredients.toList)(l => l.intersect(ingredients)))).toMap
        process(tail, aller2Ing ++ map)
      }
      case _ => aller2Ing
    }
  }

  for ((k,v) <- process(array, Map())) println(s"$k -> $v")

}
