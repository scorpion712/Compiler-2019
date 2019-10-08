
package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;
import symbol_table.SymbolTable;

public class SAGenerateSimpleSymbol implements SemanticAction{

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) { 
        return new Token(SymbolTable.getInstance().getID(String.valueOf(lastCharacter)), String.valueOf(lastCharacter), "");
    }
    
}
