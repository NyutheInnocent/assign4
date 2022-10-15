package assign4.lexer;

/**
 * @author Chase Dodge
 */
public class Word extends Token {
    public final String lexeme;
    public Word(int t, String s) {
        super(t); lexeme = s;
    }
    public String toString() {
        return lexeme;
    }
}
