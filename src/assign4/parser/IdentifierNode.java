package assign4.parser;

import assign4.lexer.*;
import assign4.visitor.*;

public class IdentifierNode extends Node {
    private String _id;
    private Word _w;
    private Num _n;

    public IdentifierNode() { }
    public IdentifierNode(Word w) {
        this.set_w(w);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Word get_w() {
        return _w;
    }

    public void set_w(Word _w) {
        this._w = _w;
    }

    public Num get_n() {
        return _n;
    }

    public void set_n(Num n) {
        this._n = n;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
