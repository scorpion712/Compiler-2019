package globales;

import java.util.Hashtable;
import java.util.Set;

import analizadorLexico.Token;
import java.util.Map;

public final class SymbolTable {

    private Map<String, Integer> identifiers  ;
    private Map<String, Token> symbols;
    
    // Languaje reserved words. Used to identify tokens
    private static final int IF = 257;
    private static final int ELSE = 258;
    private static final int END_IF = 259;
    private static final int PRINT = 260;
    private static final int INT = 261;
    private static final int BEGIN = 262;
    private static final int END = 263;
    private static final int LONG = 264;
    private static final int FOR_EACH = 265;
    private static final int IN = 266;

    // Token constants that not have an ASCII representation 
    // Note: they have to be mapped with Parser.
    private static final int ID = 267;
    private static final int STRING_CONST = 268;
    private static final int GREATER_EQUAL = 269; // >= 
    private static final int LESS_EQUAL = 270; // <=
    private static final int EQUAL = 271;  // ==
    private static final int DISTINCT = 272; // <>
    private static final int ASSIGN = 273; // :=  
    private static final int NUMERIC_CONST = 274; 
    
    // Usefull identifiers names
    private static final String GREATER_EQUAL_ID = ">="; 
    private static final String LESS_EQUAL_ID = "<="; 
    private static final String EQUAL_ID = "==";  
    private static final String DISTINCT_ID = "<>";
    private static final String ASSIGN_ID = ":=";  
    private static final String GREATER_THAN = ">";
    private static final String LESS_THAN = "<";
    
    
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
        identifiers.put("(", 40);
        identifiers.put(")", 41);
        identifiers.put("*", 42);
        identifiers.put("+", 43);
        identifiers.put(",", 44);
        identifiers.put("-", 45);
        identifiers.put("/", 47); 
        identifiers.put(";", 59);
        identifiers.put(LESS_THAN, 60);
        identifiers.put("=", 61);
        identifiers.put(GREATER_THAN, 62);
        identifiers.put("[", 91); 
        identifiers.put("]", 93);
        identifiers.put("if", IF);
        identifiers.put("else", ELSE);
        identifiers.put("end_if", END_IF);
        identifiers.put("print", PRINT);
        identifiers.put("int", INT);    // numeric const
        identifiers.put("begin", BEGIN);
        identifiers.put("end", END);
        identifiers.put("long", LONG);
        identifiers.put("for_each", FOR_EACH);
        identifiers.put("in", IN);
        identifiers.put("id", ID);
        identifiers.put("string", STRING_CONST);
        identifiers.put(GREATER_EQUAL_ID, GREATER_EQUAL);
        identifiers.put(LESS_EQUAL_ID, LESS_EQUAL);
        identifiers.put(EQUAL_ID, EQUAL);
        identifiers.put(DISTINCT_ID, DISTINCT);
        identifiers.put(ASSIGN_ID, ASSIGN);
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
