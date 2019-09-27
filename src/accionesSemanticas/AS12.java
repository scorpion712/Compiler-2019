package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class AS12 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {
		//descartar cadena
		lexema.setLength(0);
		return null;
	}

}
