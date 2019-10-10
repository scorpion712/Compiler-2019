package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;
import symbol_table.SymbolTable;

public class SAGenerateIndex implements SemanticAction {

    private final String INDEX = "indice"; 
    
    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        // Generate '_' token (can be use as colection index)
        return new Token(SymbolTable.getInstance().getID("_"), lexeme.toString(), INDEX);
    }
    
}
