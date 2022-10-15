package assign4.parser;

import assign4.lexer.*;
import assign4.visitor.*;
import java.io.IOException;

public class Parser extends ASTVisitor {
    private Lexer _lexer = null;
    public CompilationUnit cunit;

    private Token lookahead = null;

    public Parser() {
        cunit = new CompilationUnit();
        move();
        visit(cunit);
    }

    public Parser(Lexer lexer) {
        this._lexer = lexer;
        cunit = new CompilationUnit();
        move();
        visit(cunit);
    }

    public void visit(CompilationUnit n) {
        n.block = new BlockStatementNode();
        n.block.accept(this);
    }

    public void visit(BlockStatementNode n) {

//        debugger_char_check('{'); // Debugging
        match('{');

        createStmt(n);

//        debugger_char_check(';'); // Debugging


//        debugger_char_check('}'); // Debugging
        match('}');
    }
    private void createStmt(BlockStatementNode n) {
        if (lookahead.tag == '}') {
            return;
        }
        n.stmts.add(new AssignmentNode());
        n.stmts.get(n.stmts.size() - 1).accept(this);
        if (lookahead.tag == ';') {
            match(';');
        }
        createStmt(n);
    }

    public void visit(AssignmentNode n) {
        n.set_left(new IdentifierNode());
        n.get_left().accept(this);

        move(); // Read operator and do nothing

        // TODO: Need to check for binary node or identifier
        if (lookahead.tag == ';') {
            n.set_idRight(new IdentifierNode());
            n.get_idRight().accept(this);
        } else {
            n.set_rightBinary(new BinaryNode());
            n.get_rightBinary().accept(this);
        }
    }

    public void visit(BinaryNode n) {
        n.set_left(new IdentifierNode());
        n.get_left().accept(this);

        n.set_operator(lookahead);
        move(); // Read operator and do nothing

        n.set_right(new IdentifierNode());
        n.get_right().accept(this);
    }

    public void visit(IdentifierNode n) {
        n.set_id(lookahead.toString());
        n.set_w(new Word(lookahead.tag, lookahead.toString()));
        match(Tag.ID);
    }

    /////////////////////
    // Utility Methods //
    /////////////////////
    void move() {
        try {
            lookahead = _lexer.scan();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    Token peek() {
        try {
            return _lexer.scan();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    void error(String s) {
        throw new Error("near line " + _lexer.line + ": " + s);
    }

    void match(int t) {
        try {
            if (lookahead.tag == t) {
                move();
            } else {
                throw new Error("Syntax Error");
            }
        } catch (Error ex) {
            ex.printStackTrace();
        }
    }

    ///////////////////////
    // Debugging Methods //
    ///////////////////////
    private void debugger_char_check(char c) {
        if (lookahead.tag == c) System.out.println("matched with '" + c + "' : " + lookahead.tag);
    }
}
