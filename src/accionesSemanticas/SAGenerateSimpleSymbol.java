
package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class SAGenerateSimpleSymbol implements SemanticAction{

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) { 
        return new Token(SymbolTable.getInstance().getID(String.valueOf(lastCharacter)), String.valueOf(lastCharacter), "");
    }
    
}
