package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;

public class SANull implements SemanticAction {

    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        // Null Semantic Action
        /*
        if (lastCharacter == 0) {  
            System.out.println("Fin de archivo ");
        }
        */
        return null;
    }

}
