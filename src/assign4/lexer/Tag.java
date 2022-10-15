package assign4.lexer;

public class Tag {
    public final static int NUM = 256;
    public final static int ID = 257;
    public final static int TRUE = 258;
    public final static int FALSE = 259;
    public final static int ADD = 260;
    public final static int SUB = 261;
    public final static int MULTIPLE = 262;
    public final static int DIVIDE = 263;
    public final static int MODULUS = 264;
    public final static int OPERATOR = ADD | SUB | MULTIPLE | DIVIDE | MODULUS;
}

