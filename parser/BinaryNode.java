package assign4.parser;

import assign4.lexer.Tag;
import assign4.lexer.Token;
import assign4.visitor.ASTVisitor;

public class BinaryNode extends Node {
    private IdentifierNode left;
    private IdentifierNode right;
    private Token operator;

    BinaryNode() { }
    BinaryNode(IdentifierNode left, IdentifierNode right, Token operator) {
        this.set_left(left);
        this.set_right(right);
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

    public IdentifierNode get_right() {
        return right;
    }

    public void set_right(IdentifierNode right) {
        this.right = right;
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
