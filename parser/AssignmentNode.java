package assign4.parser;

import assign4.lexer.Token;
import assign4.visitor.ASTVisitor;

/**
 * @author Chase Dodge
 */
public class AssignmentNode extends Node{
    private IdentifierNode left = new IdentifierNode();
    private IdentifierNode idRight = new IdentifierNode();
    private BinaryNode binaryRight = new BinaryNode();
    private Token operator = new Token('=');

    public AssignmentNode() { }
    public AssignmentNode(IdentifierNode id, BinaryNode binary, Token operator) {
        this.set_left(id);
        this.set_rightBinary(binary);
        this.set_operator(operator);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public IdentifierNode get_left() {
        return left;
    }

    public void set_left(IdentifierNode left) {
        this.left = left;
    }

    public BinaryNode get_rightBinary() {
        return binaryRight;
    }

    public void set_rightBinary(BinaryNode right) {
        this.binaryRight = right;
    }

    public Token get_operator() {
        return operator;
    }

    public void set_operator(Token operator) {
        this.operator = operator;
    }

    public IdentifierNode get_idRight() {
        return idRight;
    }

    public void set_idRight(IdentifierNode idRight) {
        this.idRight = idRight;
    }
}
