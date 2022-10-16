package assign4.parser;

import assign4.lexer.Token;
import assign4.visitor.ASTVisitor;

/**
 * @author Chase Dodge
 */
public class AssignmentNode extends Node{
    private IdentifierNode left = new IdentifierNode();
    private IdentifierNode right_id = new IdentifierNode();
    private BinaryNode right_binary = new BinaryNode();
    private Token operator = new Token('=');

    public AssignmentNode() { }
    public AssignmentNode(IdentifierNode id, BinaryNode binary, Token operator) {
        this.set_left(id);
        this.set_right_binary(binary);
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

    public BinaryNode get_right_binary() {
        return right_binary;
    }

    public void set_right_binary(BinaryNode right) {
        this.right_binary = right;
    }

    public Token get_operator() {
        return operator;
    }

    public void set_operator(Token operator) {
        this.operator = operator;
    }

    public IdentifierNode get_right_id() {
        return right_id;
    }

    public void set_right_id(IdentifierNode idRight) {
        this.right_id = idRight;
    }
}
