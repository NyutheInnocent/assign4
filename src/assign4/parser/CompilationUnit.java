package assign4.parser;

import assign4.visitor.*;

/**
 * @author Chase Dodge
 */
public class CompilationUnit extends Node {
    public BlockStatementNode block;

    CompilationUnit() {
        this.block = new BlockStatementNode();
    }
    CompilationUnit(BlockStatementNode block) {
        this.block = block;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
