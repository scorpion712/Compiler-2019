package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class SAGenerateNotEqual implements SemanticAction {

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        lexeme.append(lastCharacter);
        return new Token(SymbolTable.getInstance().getID(lexeme.toString()), lexeme.toString(), "");
    }

}
