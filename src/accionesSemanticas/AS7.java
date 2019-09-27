package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class AS7 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {/*
		// reotrna operador y devuelve el ultimo caracter.
		pf.returnCharacter(ultimo_caracter);
		return new Token(SymbolTable.getID(lexema.toString()),lexema.toString(),null);*/return null;
	}

}
