package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;

public class SASymbolWarning implements SemanticAction{

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) { 
        System.out.println("    Lexical Warning at line " + fc.getLine() + ": character not identified."); 
        fc.addWarning();
        return null;
    }
    
}
