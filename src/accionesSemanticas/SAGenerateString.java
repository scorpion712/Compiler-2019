package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

/* 
    Semantic action to recognize a one line string.
    A string of a line begins and ends with % (delimiters). 
    The semantic action discards the start and end delimiters and put the string in the Symbol Table.
*/
public class SAGenerateString implements SemanticAction {
    
    // this constant may have the same string acording to STRING_CONST values in identifiers map from SymbolTable.class
    private static final String STRING_CONST = "string";
    
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {  
        Token token = new Token(SymbolTable.getInstance().getID(STRING_CONST), lexeme.substring(1), STRING_CONST);
        SymbolTable.getInstance().addSymbol(token);

        return token;
    }

}
