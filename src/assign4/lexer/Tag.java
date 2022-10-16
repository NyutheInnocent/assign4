package assign4.lexer;

public class Tag {
    public final static int NUM = 256;
    public final static int ID = 257;
    public final static int TRUE = 258;
    public final static int FALSE = 259;
    public final static int ADD = '+';
    public final static int SUB = '-';
    public final static int MULTIPLE = '*';
    public final static int DIVIDE = '/';
    public final static int MODULUS = '%';
    public final static int OPERATOR = ADD | SUB | MULTIPLE | DIVIDE | MODULUS;
}

