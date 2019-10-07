package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class SASymbolWarning implements SemanticAction{

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) { 
        System.out.println("    Lexical Warning at line " + fc.getLine() + ": character not identified."); 
        fc.addWarning();
        return null;
    }
    
}
