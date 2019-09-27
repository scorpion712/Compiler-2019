package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class AS6 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {/*
		// Retornar el operador
		lexema.append(ultimo_caracter);
		return new Token(SymbolTable.getID(lexema.toString()),lexema.toString(), null);*/return null;
	}

}
