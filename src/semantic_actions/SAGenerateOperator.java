package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;
import symbol_table.SymbolTable;

public class SAGenerateOperator implements SemanticAction {

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        fc.returnCharacter(lastCharacter); // return the last read character
        return new Token(SymbolTable.getInstance().getID(lexeme.toString()), lexeme.toString(), "");
    }

}
