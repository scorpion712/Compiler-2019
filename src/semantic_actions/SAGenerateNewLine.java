package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;

public class SAGenerateNewLine implements SemanticAction {

    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastChar) {
        fc.nextLine();  // \n
        return null;
    }

}
