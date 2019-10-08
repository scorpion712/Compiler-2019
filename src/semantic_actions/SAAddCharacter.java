package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;

public class SAAddCharacter implements SemanticAction {

	public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
		// append the character to lexeme
		lexeme.append(lastCharacter);
		return null;
	}

}
