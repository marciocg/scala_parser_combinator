package marciocg

import scala.util.parsing.combinator._

object ParserExpr extends ArithParser {
    def mainexp(args: Array[String]) = {
        println("input: " + args(0))
        println(parseAll(expr, args(0)))
    }
}