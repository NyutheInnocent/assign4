package assign4.parser;

import assign4.lexer.Tag;
import assign4.lexer.Token;
import assign4.visitor.ASTVisitor;

public class BinaryNode extends Node {
    private IdentifierNode left;
    private IdentifierNode rightId;
    private BinaryNode rightBinary;
    private Token operator;

    BinaryNode() { }
    BinaryNode(IdentifierNode left) {
        this.set_left(left);
    }
    BinaryNode(IdentifierNode left, IdentifierNode right, Token operator) {
        this.set_left(left);
        this.set_right_id(right);
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

    public IdentifierNode get_right_id() {
        return rightId;
    }

    public void set_right_id(IdentifierNode right) {
        this.rightId = right;
    }

    public BinaryNode get_right_binary() {
        return rightBinary;
    }

    public void set_right_binary(BinaryNode right) {
        this.rightBinary = right;
    }

    public Token get_operator() {
        return operator;
    }

    public void set_operator(Token operator) {
        if (operator.tag == Tag.ADD || operator.tag == Tag.SUB ||
                operator.tag == Tag.DIVIDE || operator.tag == Tag.MULTIPLE || operator.tag == Tag.MODULUS) {
            this.operator = operator;
        }
    }
}
