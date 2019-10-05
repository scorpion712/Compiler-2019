package accionesSemanticas;

import analizadorLexico.ReaderBuffer;
import analizadorLexico.Token;
import globales.SymbolTable;

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
        /*
        
        The limits must be included or not ¿? 
        
         */
        Token token; 
        if (constant >= -Math.pow(2, MAX_BITS_INT) && constant <= Math.pow(2, MAX_BITS_INT) - 1) { // it's an integer
            //token = new Token(SymbolTable.getInstance().getID(NUMERIC_CONST), Long.toString(constant), NUMERIC_CONST);
            token = new Token(SymbolTable.getInstance().getID(INT), Long.toString(constant), NUMERIC_CONST);
        } else {            //  it's a long
            if (constant < -Math.pow(2, MAX_BITS_ULONG)) {  // overflow
                System.out.println("    Lexical Warning at line " + fc.getLine() + ": constant out of range. Min defined " + Math.pow(2, -MAX_BITS_ULONG) + ".");
                constant = (long) -Math.pow(2, MAX_BITS_ULONG);
            } else {
                if (constant > Math.pow(2, MAX_BITS_ULONG) - 1) {
                    System.out.println("    Lexical Warning at line " + fc.getLine() + ": constant out of range. Max defined " + (Math.pow(2, MAX_BITS_ULONG) - 1) + ".");
                    constant = (long) Math.pow(2, MAX_BITS_ULONG) - 1;
                }
            }
            //token = new Token(SymbolTable.getInstance().getID(LONG), Long.toString(constant), LONG);
            token = new Token(SymbolTable.getInstance().getID(LONG), Long.toString(constant), NUMERIC_CONST); // 
        }
        SymbolTable.getInstance().addSymbol(token);
        return token;
    }

}
