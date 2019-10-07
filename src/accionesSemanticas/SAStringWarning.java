package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class SAStringWarning implements SemanticAction {

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        fc.returnCharacter(lastCharacter);
        System.out.println("    Lexical Warning at line " + fc.getLine() + ": string delimitter not closed.");
        fc.addWarning();
        return null;
    }

}
