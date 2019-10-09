package lexer;

public class ReaderBuffer {

    private StringBuffer buffer;
    private int line;
    private int warning_counter;

    public ReaderBuffer(StringBuffer sb) {
        this.buffer = new StringBuffer(sb);
        this.line = 1;
        this.warning_counter = 0;
    }

    public int getWarning() {
        return warning_counter;
    }
    
    public void addWarning() {
        warning_counter++; 
    }
    
    public boolean eof() { 
        return buffer.length() == 0;
    }

    public int getCaracter() {
        int next = -1;
        if (!this.eof()) {
            next = buffer.charAt(0);
            buffer.deleteCharAt(0);
            if (next == 13) { //  \n
                next = buffer.charAt(0);
                buffer.deleteCharAt(0);
            }
        }
        return next;
    }

    public void returnCharacter(int caracter) {
        if (caracter != 0) {
            buffer.insert(0, (char) caracter);
        }
    }

    public void nextLine() {
        line++;
    }

    public int getLine() {
        return line;
    }


}
