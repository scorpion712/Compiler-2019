package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class SACommentWarning implements SemanticAction {

    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastChar) {
        System.out.println("    Lexical Warning at line " + fc.getLine() + ": to close comment was expected."); 
        fc.addWarning();
        return null;
    }

}
