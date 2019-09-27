package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public interface SemanticAction {
	
	public Token execute(ReaderBuffer fc, StringBuilder lexema, char ultimo_caracter);
}