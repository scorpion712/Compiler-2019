package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class AS3 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {
            /*
		//Contaste entera, verificar limite y agregar.
		long cte = Long.parseLong(lexema.toString());
		if (cte > Math.pow(2, 15)) {
			System.out.println("Linea " + pf.getLine() + ": (AL) WARNING: Constante fuera del rango permitido. (integer = "+ (int) cte +")");
			cte = (long)Math.pow(2, 15);
			System.out.println("Linea " + pf.getLine() + ": (AL) WARNING: Se le asignara el mayor valor permitido. ("+ (int) cte +")");
		}
		Token token = new Token(SymbolTable.getID("cte"), Long.toString(cte),"Constante entera");
		token.addAttribute("Tipo", "integer");
		SymbolTable.addSymbol(token);
		Token t = SymbolTable.getSymbol(Long.toString(cte));
		if (t.getAttribute("contador") == null) {
			t.addAttribute("contador", "1");
		} else {
			int contador = Integer.parseInt(t.getAttribute("contador")) + 1 ;
			t.addAttribute("contador", String.valueOf(contador));
		}
		return token;
                */
            return null;
	}

}
