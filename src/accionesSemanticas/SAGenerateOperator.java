package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class SAGenerateOperator implements SemanticAction {

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        fc.returnCharacter(lastCharacter); // return the last read character
        return new Token(SymbolTable.getInstance().getID(lexeme.toString()), lexeme.toString(), "");
    }

}
