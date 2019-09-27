package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class AS5 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {/*
		// Verificar palabra reservada
		pf.returnCharacter(ultimo_caracter);
		if (SymbolTable.containsID(lexema.toString()))
			return new Token(SymbolTable.getID(lexema.toString()),lexema.toString(),"Palabra Reservada");
		System.out.println("Linea " + pf.getLine() + ": (AL) Palabra Reservada no v�lida");
		lexema.setLength(0);*/
		return null;
	}

}
