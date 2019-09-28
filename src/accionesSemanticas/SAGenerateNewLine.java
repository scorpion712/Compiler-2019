package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class SAGenerateNewLine implements SemanticAction {

    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastChar) {
        fc.nextLine();  // \n
        return null;
    }

}
