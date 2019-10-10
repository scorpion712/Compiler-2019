package semantic_actions;

import lexer.ReaderBuffer;
import lexer.Token;
import symbol_table.SymbolTable;

public class SAGenerateConst implements SemanticAction {

    // constants to define the numbers of bits to represent an integer const or a ulong const
    private static final int MAX_BITS_INT = 15;
    private static final int MAX_BITS_ULONG = 31;

    // this two constants may have the same string acording to INT and LONG values in identifiers map  from SymbolTable.class
    private static final String NUMERIC_CONST = "cte";
    private static final String LONG = "LONG";
    private static final String INT = "int"; 

    @Override
    public Token execute(ReaderBuffer fc, StringBuilder lexeme, char lastCharacter) {
        fc.returnCharacter(lastCharacter); // return last read character
        long constant = Long.parseLong(lexeme.toString());
        Token token; 
        if (constant >= -Math.pow(2, MAX_BITS_INT) && constant <= Math.pow(2, MAX_BITS_INT) - 1) { // it's an integer
            token = new Token(SymbolTable.getInstance().getID(NUMERIC_CONST), Long.toString(constant), INT);
        } else {            //  it's a long
            if (constant < -Math.pow(2, MAX_BITS_ULONG)) {  // overflow
                System.out.println("    Lexical Warning at line " + fc.getLine() + ": constant out of range. Min defined " + Math.pow(2, -MAX_BITS_ULONG) + ".");
                fc.addWarning();
                return null;
            } else {
                if (constant > Math.pow(2, MAX_BITS_ULONG) - 1) {
                    System.out.println("    Lexical Warning at line " + fc.getLine() + ": constant out of range. Max defined " + (Math.pow(2, MAX_BITS_ULONG) - 1) + ".");
                    fc.addWarning();
                    return null;
                }
            }
            token = new Token(SymbolTable.getInstance().getID(NUMERIC_CONST), Long.toString(constant), LONG); // 
        }
        SymbolTable.getInstance().addSymbol(token);
        return token;
    }

}
