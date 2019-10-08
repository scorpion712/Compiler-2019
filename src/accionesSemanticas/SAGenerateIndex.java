package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

public class SAGenerateIndex implements SemanticAction {

    private final String INDEX = "indice";
    
    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        // Generate '_' token (can be use as colection index)
        return new Token(SymbolTable.getInstance().getID("_"), lexeme.toString(), INDEX);
    }
    
}
