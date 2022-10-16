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
        System.out.println("{");

        for (AssignmentNode stmt : n.stmts) {
            _level++;
            stmt.accept(this);
            _level--;
        }

        System.out.println("}");
    }
    public void visit(AssignmentNode n) {
        System.out.print(_indent.repeat(_level));
        n.get_left().accept(this);
        print(n.get_operator().toString());

        // TODO: Need to check for id node or binary node
        if (n.get_right_id().get_id() != null) {
            n.get_right_id().accept(this);
            System.out.println(';');
        }
        else if (n.get_right_binary() != null) {
            n.get_right_binary().accept(this);
        }
    }

    boolean rightHasBeenRead = false;
    public void visit(BinaryNode n) {
        n.get_left().accept(this);
        Token op = n.get_operator();
        print(op.toString());

        if (!rightHasBeenRead && n.get_right_binary() != null) {
            rightHasBeenRead = true;
            n.get_right_binary().accept(this);
            return;
        }

        n.get_right_id().accept(this);

        System.out.println(";");
    }
    public void visit(IdentifierNode n) {
        print(n.get_id());
        // Leaf node
    }

    /**
     * Prints out a number of indents determined by level, an identifier and a character to the console.
     * @param identifier The value of an IdentifierNode.
     * @see IdentifierNode
     */
    private void print(String identifier) {
        System.out.print(identifier + " ");
    }
}
