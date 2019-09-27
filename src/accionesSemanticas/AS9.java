package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;

public class AS9 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {
		// Caracter invalido
		pf.returnCharacter(ultimo_caracter);
		System.out.println("Linea " + pf.getLine() + ": (AL) Se esperaba otro caracter luego de: " + lexema);
		lexema.setLength(0);
		return null;
	}

}
