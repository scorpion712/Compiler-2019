package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;

public interface SemanticAction {

    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter);
}
