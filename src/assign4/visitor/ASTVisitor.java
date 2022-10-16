package assign4.visitor;

import assign4.parser.*;

/**
 * @author Chase Dodge
 */
public class ASTVisitor {
    public void visit(CompilationUnit n) {
        n.block.accept(this);
    }
    public void visit(BlockStatementNode n) {
        for (AssignmentNode stmt : n.stmts) {
            stmt.accept(this);
        }
    }
    public void visit(AssignmentNode n) {
        n.get_left().accept(this);
        n.get_right_binary().accept(this);
    }
    public void visit(BinaryNode n) {
        n.get_left().accept(this);
        n.get_right_id().accept(this);
    }
    public void visit(IdentifierNode n) {
        // Leaf node
    }
}
