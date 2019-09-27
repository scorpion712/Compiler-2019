package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class SAAddCharacter implements SemanticAction {

	public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
		// append the character to lexeme
		lexeme.append(lastCharacter);
		return null;
	}

}
