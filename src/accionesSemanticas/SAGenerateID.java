package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class SAGenerateID implements SemanticAction {

    private static final int MAX_LENGTH = 25;
    private static final String KEY_WORD = "Palabra reservada";
    private static final String IDENTIFIER = "Identificador";

    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        fc.returnCharacter(lastCharacter);  // discard the last character read
        if (lexeme.length() > MAX_LENGTH) {      // truncate and show warning
            lexeme = new StringBuilder(lexeme.substring(0, MAX_LENGTH));
            System.out.println("    Lexical Warning at line " + fc.getLine() + ": identifier exceeds " + MAX_LENGTH + " characters.");
            fc.addWarning();
            // System.out.println("Linea " + fc.getNroLinea() + ": (AL) WARNING: Identificador truncado");
        }
        // Generate token and add it to symbol table
        if (SymbolTable.getInstance().containsID(lexeme.toString())) { // its a key word
            return new Token(SymbolTable.getInstance().getID(lexeme.toString()), lexeme.toString(), KEY_WORD);
        }
        Token token = new Token(SymbolTable.getInstance().getID("id"), lexeme.toString(), IDENTIFIER);
        SymbolTable.getInstance().addSymbol(token);

        return token;
    }

}
