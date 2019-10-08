package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;
import symbol_table.SymbolTable;

public class SAGenerateAssignment implements SemanticAction {

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        lexeme.append(lastCharacter);
        return new Token(SymbolTable.getInstance().getID(lexeme.toString()), lexeme.toString(), "");
    }

}
