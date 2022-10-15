package assign4.lexer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @author Chase Dodge
 */
public class Lexer {
    public int line = 1;
    private char peek = ' ';

    private FileInputStream in;
    private BufferedInputStream bin;

    private Hashtable words = new Hashtable();

    public Lexer() {
        reserve(new Word(Tag.TRUE,"true"));
        reserve(new Word(Tag.FALSE,"false"));

        setupIOStream();
    }
    void reserve(Word t) {words.put(t.lexeme, t); }

    public Token scan() throws IOException {
        // Removes space tab and newline
        for (; ; readChar()) {
            if (peek == ' ' || peek == '\t') continue;
            else if (peek == '\n') line += 1;
            else break;
        }
        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readChar();
            } while (Character.isDigit(peek));

            return new Num(v);
        }
        if (Character.isLetter(peek)) {
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(peek);
                readChar();
            } while (Character.isLetterOrDigit(peek));

            // TODO: Bug somewhere in here
            String s = sb.toString();
            Word w = (Word)words.get(s);

            if (w != null) {
                return w;
            }
            w = new Word(Tag.ID, s);
            words.put(s,w);
            return w;
        }
        if (peek == '+') {
            return new Token(Tag.ADD);
        }
        if (peek == '-') {
            return new Token(Tag.SUB);
        }
        if (peek == '*') {
            return new Token(Tag.MULTIPLE);
        }
        if (peek == '/') {
            return new Token(Tag.DIVIDE);
        }
        if (peek == '%') {
            return new Token(Tag.MODULUS);
        }
        Token t = new Token(peek);
        peek = ' ';
        return t;
    }

    public void setupIOStream() {
        try {
            in = new FileInputStream("src/assign4/input.txt");
            bin = new BufferedInputStream(in);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    void readChar() throws IOException {
        peek = (char)bin.read();
    }
}

