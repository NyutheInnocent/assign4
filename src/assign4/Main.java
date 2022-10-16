package assign4;

import assign4.parser.Parser;
import assign4.pretty.PrettyPrinter;
import assign4.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        PrettyPrinter prettyPrinter = new PrettyPrinter(parser);
    }
}
