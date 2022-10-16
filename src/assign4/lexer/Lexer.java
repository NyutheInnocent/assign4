package assign4.lexer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.Hashtable;

/**
 * @author Chase Dodge
 */
public class Lexer {
    public int line = 1;
    private char peek = ' ';
    private char next = ' ';
    private int readLimit = 2;

    //    private BufferedInputStream bin;
    private PushbackInputStream pin;

    private Hashtable<String, Word> words = new Hashtable<>();

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
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(peek);
                readChar();
            } while (Character.isLetterOrDigit(peek));

            String s = sb.toString();
            Word w = words.get(s);

            if (w != null) {
                return w;
            }
            w = new Word(Tag.ID, s);
            words.put(s,w);
            return w;
        }

        Token t = new Token(peek);
        peek = ' ';
        return t;
    }

    public Token get_next() throws IOException {
        return new Token(next);
    }

    public void setupIOStream() {
        try {
            FileInputStream in = new FileInputStream("src/assign4/input.txt");
//            bin = new BufferedInputStream(in);
            pin = new PushbackInputStream(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    void readChar() throws IOException {
        // bin.mark(readLimit);
        peek = (char) pin.read();
        int temp = pin.read();
        if (temp == -1) {
            return;
        }
        next = (char) temp;
        pin.unread(temp);
    }
}

