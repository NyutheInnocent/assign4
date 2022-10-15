package assign4.parser;

import assign4.visitor.*;

import java.util.ArrayList;

/**
 * @author Chase Dodge
 */
public class BlockStatementNode extends Node{
//    public IdentifierNode identifier;
    public ArrayList<AssignmentNode> stmts;

    public BlockStatementNode() {
        this.stmts = new ArrayList<>();
    }
    public BlockStatementNode(ArrayList<AssignmentNode> stmts) {
        this.stmts = stmts;
    }

    @Override
    public void accept(ASTVisitor visitor) {visitor.visit(this);}
}
