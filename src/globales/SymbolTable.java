package globales;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import analizadorLexico.Token;
import java.util.Map;

public final class SymbolTable {

    private Map<String, Integer> identifiers  ;
    private Map<String, Token> symbols;
    
    // Languaje reserved words. Used to identify tokens
    static final int IF = 257;
    static final int ELSE = 258;
    static final int END_IF = 259;
    static final int PRINT = 260;
    static final int INT = 261;
    static final int BEGIN = 262;
    static final int END = 263;
    static final int LONG = 264;
    static final int FOR_EACH = 265;
    static final int IN = 266;

    // Token constants that not have an ASCII representation
    static final int ID = 300;
    static final int STRING_CONST = 301;
    static final int GREATER_EQUAL = 302; // >= 
    static final int LESS_EQUAL = 303; // <=
    static final int EQUAL = 304;  // ==
    static final int NOT_EQUAL = 305; // <>
    static final int ASIGNATION = 306; // := 
    static final int NUMERIC_CONST = 307;

    private static SymbolTable symbolTable;
    
    public static SymbolTable getInstance() {
        if (symbolTable == null) {
            symbolTable = new SymbolTable();
        } 
        return symbolTable;
    }
    
    private SymbolTable() {
        identifiers = new Hashtable<String, Integer>();
        symbols = new Hashtable<String, Token>();
        initialize();
    }
    
    private void initialize() {
        /*
         son necesarios en la TS ?
        
        identificadores.put("(", 40);
        identificadores.put(")", 41);
        identificadores.put("*", 42);
        identificadores.put("+", 43);
        identificadores.put(",", 44);
        identificadores.put("-", 45);
        identificadores.put("/", 47);
        identificadores.put(":", 58);
        identificadores.put(";", 59);
        identificadores.put("<", 60);
        identificadores.put("=", 61);
        identificadores.put(">", 62);
        identificadores.put("{", 123);
        identificadores.put("}", 125);
        */
        identifiers.put("if", IF);
        identifiers.put("else", ELSE);
        identifiers.put("end_if", END_IF);
        identifiers.put("print", PRINT);
        identifiers.put("integer", INT);
        identifiers.put("begin", BEGIN);
        identifiers.put("end", END);
        identifiers.put("long", LONG);
        identifiers.put("for_each", FOR_EACH);
        identifiers.put("in", IN);
        /*
        identificadores.put("void", 265);
        identificadores.put("fun", 266);
        identificadores.put("return", 267);
        */
        identifiers.put("id", ID);
        identifiers.put("string", STRING_CONST);
        //identificadores.put("cte_larga", 271);
        identifiers.put(">=", GREATER_EQUAL);
        identifiers.put("<=", LESS_EQUAL);
        identifiers.put("==", EQUAL);
        identifiers.put("<>", NOT_EQUAL);
        identifiers.put(":=", ASIGNATION);
        identifiers.put("cte", NUMERIC_CONST);
    }

    public boolean containsID(String id) { 
        return identifiers.containsKey(id);
    }

    public int getID(String id) { 
        return identifiers.get(id);
    }

    public void addSymbol(Token t) {
        if (!symbols.containsKey(t.getLexeme())) {
            symbols.put(t.getLexeme(), t);
        }
    }

    public Token getSymbol(String lexema) {
        return symbols.get(lexema);
    }

    public Set<String> getLexemes() {
        return symbols.keySet();
    }

    public String print() {
        StringBuffer out = new StringBuffer();
        for (String s : symbols.keySet()) {
            Token t = getSymbol(s);
            out.append(t.toString() + "\n");
        }
        /*
        Show all the SymbolTable
        out.append("______________________________________________________________\nPalabras reservadas e identificadores:\n");
        for (String s: identifiers.keySet()) {
            out.append("identificador: " + s + " | id: " + identifiers.get(s) + "\n");
        }
        */
        return out.toString();
    }

    public boolean containsLexeme(String lexema) {
        return symbols.containsKey(lexema);
    }

    public void remove(String lexema) {
        symbols.remove(lexema);
    }

    public void modificarLexema(String viejo, String nuevo) {
        Token t = getSymbol(viejo);
        if (t != null) {
            t.setLexeme(nuevo);
            remove(viejo);
            addSymbol(t);
        }
    }

}
