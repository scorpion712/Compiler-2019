package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class AS4 implements SemanticAction {

	public Token execute(ReaderBuffer pf, StringBuilder lexema, char ultimo_caracter) {
            /*
		//Contaste entera larga, verificar limite y agregar.
		long cte = Long.parseLong(lexema.toString());
		if (cte > Math.pow(2, 31)) {
			System.out.println("Linea " + pf.getLine() + ": (AL) WARNING: Constante fuera del rango permitido. (integer = "+ (long) cte +")");
			cte = (long)Math.pow(2, 31);
			System.out.println("Linea " + pf.getLine() + ": (AL) WARNING: Se le asignara el mayor valor permitido. ("+ (long) cte +")");
		}
		Token token = new Token(SymbolTable.getID("cte_larga"), Long.toString(cte),"Constante entera larga");
		token.addAttribute("Tipo", "linteger");
		SymbolTable.addSymbol(token);
		Token t = SymbolTable.getSymbol(Long.toString(cte));
		if (t.getAttribute("contador") == null) {
			t.addAttribute("contador", "1");
		} else {
			int contador = Integer.parseInt(t.getAttribute("contador")) + 1 ;
			t.addAttribute("contador", String.valueOf(contador));
		}
		return token;*/
            return null;
	}

}
