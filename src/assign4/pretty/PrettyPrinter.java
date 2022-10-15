package assign4.pretty;

import assign4.lexer.Token;
import assign4.parser.*;
import assign4.visitor.*;

/**
 * The Pretty Printer class.
 * Used to print the abstract syntax tree built from the input.txt file cleanly to the console.
 * @author Chase Dodge
 */
public class PrettyPrinter extends ASTVisitor {
    private String _indent = "    ";
    private int _level = 0;

    public Parser parser = null;

    public PrettyPrinter() { }
    public PrettyPrinter(Parser parser) {
        this.parser = parser;
        visit(parser.cunit);
    }

    public void visit(CompilationUnit n) {
        n.block.accept(this);
    }
    public void visit(BlockStatementNode n) {
        print("{", true);

        for (AssignmentNode stmt : n.stmts) {
            _level++;
            stmt.accept(this);
            _level--;
        }

        print("}", true);
    }
    public void visit(AssignmentNode n) {
        n.get_left().accept(this);
        print(n.get_operator().toString(), false);

        // TODO: Need to check for id node or binary node
        n.get_rightBinary().accept(this);
    }
    public void visit(BinaryNode n) {
        n.get_left().accept(this);
        Token op = n.get_operator();
        print(op.toString(), false);
        n.get_right().accept(this);
        print(";", true);
    }
    public void visit(IdentifierNode n) {
        print(n.get_id(), false);
        // Leaf node
    }

    /**
     * Prints out a number of indents determined by level, an identifier and a character to the console.
     * @param identifier The value of an IdentifierNode.
     * @param includeNewLine A boolean value that indicates whether to include a new line.
     * @see IdentifierNode
     */
    private void print(String identifier, boolean includeNewLine) {
        System.out.print(/*_indent.repeat(_level) +*/ identifier + " " + (includeNewLine ? '\n' : ""));
    }
}
