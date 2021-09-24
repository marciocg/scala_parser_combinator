package marciocg
import java.io.FileReader
import scala.util.parsing.combinator._

class JSON extends JavaTokenParsers {
    // def value1: Parser[Any] = obj1 | arr1 | stringLiteral | floatingPointNumber | 
    //                         "null" | "true" | "false"
  
    // def obj1: Parser[Any] = "{"~repsep(member1, ",")~"}"

    // def arr1: Parser[Any] = "["~repsep(value1, ",")~"]"

    // def member1: Parser[Any] = stringLiteral~":"~value1

    def obj: Parser[Map[String, Any]] = 
        "{"~> repsep(member, ",") <~"}" ^^ (Map() ++ _)

    def arr: Parser[List[Any]] = 
        "["~> repsep(value, ",") <~"]"
    
    def member: Parser[(String, Any)] = 
        stringLiteral~":"~value ^^
        { case name~":"~value => (name, value) }

    def value: Parser[Any] = 
        obj |
        arr |
        stringLiteral |
        floatingPointNumber ^^ (_.toDouble) |
        "null" ^^ (x => null) |
        "true" ^^ (x => true) |
        "false" ^^ (x => false)

}


object ParserJSON extends JSON {
    def main(args: Array[String]) {
        val reader = new FileReader(args(0))
        println(parseAll(value, reader))
    }
}