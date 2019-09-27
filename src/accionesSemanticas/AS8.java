package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class AS8 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {/*
		//devolver cadena.
		Token token = new Token(SymbolTable.getID("cadena"), lexema.toString(),"cadena");
		SymbolTable.addSymbol(token);
		return token;*/ return null;
	}

}
