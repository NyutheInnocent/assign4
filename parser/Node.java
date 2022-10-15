package assign4.parser;

import assign4.visitor.*;

public abstract class Node {
    public abstract void accept(ASTVisitor visitor);
}
