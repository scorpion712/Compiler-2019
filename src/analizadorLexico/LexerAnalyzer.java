package analizadorLexico;

import accionesSemanticas.*;
import analizadorSintactico.*;

public class LexerAnalyzer {

    // Automatum states (Matrix transition) 
    static final int INITIAL_STATE = 0;
    static final int FINAL_STATE = 11;
    static final int INVALID_TOKEN = -1;

    private ReaderBuffer fontCode;
    private int[][] transitionMatrix = {
        /*E0*/{1, 2, 11, 11, 3, 4, 5, 6, 7, 8, 0, 0, 0, 0, 11},
        /*E1*/ {1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 1, 11, 11},
        /*E2*/ {11, 2, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
        /*E3*/ {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
        /*E4*/ {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
        /*E5*/ {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
        /*E6*/ {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
        /*E7*/ {7, 7, 7, 7, 7, 7, 7, 7, 11, 7, 7, 11, 7, 7, 11},
        /*E8*/ {11, 11, 9, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
        /*E9*/ {9, 9, 10, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 11},
        /*E10*/ {9, 9, 10, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 11},};

    private SemanticAction[][] semanticActionMatrix;

    public LexerAnalyzer(StringBuffer pf) {
        this.fontCode = new ReaderBuffer(pf);
        this.cargarAS();
    }

    private void cargarAS() {
        // Create semantic actions
        SemanticAction AS0 = new SAAddCharacter();
        SemanticAction AS1 = new SAGenerateID();
        SemanticAction AS2 = null;
        SemanticAction AS3 = new AS3();
        SemanticAction AS4 = new AS4();
        SemanticAction AS5 = new AS5();
        SemanticAction AS6 = new AS6();
        SemanticAction AS7 = new AS7();
        SemanticAction AS8 = new AS8();
        SemanticAction AS9 = new AS9();
        SemanticAction AS10 = new AS10();
        SemanticAction AS11 = new SANull();
        SemanticAction AS12 = new AS12();
        
        // Accion semantica nula.
        SemanticAction accion_semantica_nula = new SANull();  //Usada para evitar null pointer mientras se construyen las AS restantes

        // Initialize semactic action matrix
        semanticActionMatrix = new SemanticAction[11][15];

        // State 0 (initial)
        semanticActionMatrix[0][0] = AS0;
        semanticActionMatrix[0][1] = AS0;
        semanticActionMatrix[0][2] = AS0;
        semanticActionMatrix[0][3] = AS1;
        semanticActionMatrix[0][4] = AS6;
        semanticActionMatrix[0][5] = AS1;
        semanticActionMatrix[0][6] = AS1;
        semanticActionMatrix[0][7] = AS1;
        semanticActionMatrix[0][8] = AS1;
        semanticActionMatrix[0][9] = AS11;
        semanticActionMatrix[0][10] = AS11;
        semanticActionMatrix[0][11] = AS11;
        semanticActionMatrix[0][12] = AS10;
        semanticActionMatrix[0][13] = AS11;
        semanticActionMatrix[0][14] = AS11;

        // State 1
        semanticActionMatrix[1][0] = AS0;
        semanticActionMatrix[1][1] = AS0;
        semanticActionMatrix[1][2] = AS1;
        semanticActionMatrix[1][3] = AS1;
        semanticActionMatrix[1][4] = AS1;
        semanticActionMatrix[1][5] = AS1;
        semanticActionMatrix[1][6] = AS1;
        semanticActionMatrix[1][7] = AS1;
        semanticActionMatrix[1][8] = AS1;
        semanticActionMatrix[1][9] = AS1;
        semanticActionMatrix[1][10] = AS1;
        semanticActionMatrix[1][11] = AS1;
        semanticActionMatrix[1][12] = AS0;
        semanticActionMatrix[1][13] = AS1;
        semanticActionMatrix[1][14] = AS1;

        // State 2
        semanticActionMatrix[2][0] = accion_semantica_nula;
        semanticActionMatrix[2][1] = accion_semantica_nula;
        semanticActionMatrix[2][2] = accion_semantica_nula;
        semanticActionMatrix[2][3] = accion_semantica_nula;
        semanticActionMatrix[2][4] = accion_semantica_nula;
        semanticActionMatrix[2][5] = accion_semantica_nula;
        semanticActionMatrix[2][6] = accion_semantica_nula;
        semanticActionMatrix[2][7] = accion_semantica_nula;
        semanticActionMatrix[2][8] = accion_semantica_nula;
        semanticActionMatrix[2][9] = accion_semantica_nula;
        semanticActionMatrix[2][10] = accion_semantica_nula;
        semanticActionMatrix[2][11] = accion_semantica_nula;
        semanticActionMatrix[2][12] = accion_semantica_nula;
        semanticActionMatrix[2][13] = accion_semantica_nula;
        semanticActionMatrix[2][14] = accion_semantica_nula;

        // State 3
        semanticActionMatrix[3][0] = accion_semantica_nula;
        semanticActionMatrix[3][1] = accion_semantica_nula;
        semanticActionMatrix[3][2] = accion_semantica_nula;
        semanticActionMatrix[3][3] = accion_semantica_nula;
        semanticActionMatrix[3][4] = accion_semantica_nula;
        semanticActionMatrix[3][5] = accion_semantica_nula;
        semanticActionMatrix[3][6] = accion_semantica_nula;
        semanticActionMatrix[3][7] = accion_semantica_nula;
        semanticActionMatrix[3][8] = accion_semantica_nula;
        semanticActionMatrix[3][9] = accion_semantica_nula;
        semanticActionMatrix[3][10] = accion_semantica_nula;
        semanticActionMatrix[3][11] = accion_semantica_nula;
        semanticActionMatrix[3][12] = accion_semantica_nula;
        semanticActionMatrix[3][13] = accion_semantica_nula;
        semanticActionMatrix[3][14] = accion_semantica_nula;

        // State 4
        semanticActionMatrix[4][0] = accion_semantica_nula;
        semanticActionMatrix[4][1] = accion_semantica_nula;
        semanticActionMatrix[4][2] = accion_semantica_nula;
        semanticActionMatrix[4][3] = accion_semantica_nula;
        semanticActionMatrix[4][4] = accion_semantica_nula;
        semanticActionMatrix[4][5] = accion_semantica_nula;
        semanticActionMatrix[4][6] = accion_semantica_nula;
        semanticActionMatrix[4][7] = accion_semantica_nula;
        semanticActionMatrix[4][8] = accion_semantica_nula;
        semanticActionMatrix[4][9] = accion_semantica_nula;
        semanticActionMatrix[4][10] = accion_semantica_nula;
        semanticActionMatrix[4][11] = accion_semantica_nula;
        semanticActionMatrix[4][12] = accion_semantica_nula;
        semanticActionMatrix[4][13] = accion_semantica_nula;
        semanticActionMatrix[4][14] = accion_semantica_nula;

        // State 5
        semanticActionMatrix[5][0] = accion_semantica_nula;
        semanticActionMatrix[5][1] = accion_semantica_nula;
        semanticActionMatrix[5][2] = accion_semantica_nula;
        semanticActionMatrix[5][3] = accion_semantica_nula;
        semanticActionMatrix[5][4] = accion_semantica_nula;
        semanticActionMatrix[5][5] = accion_semantica_nula;
        semanticActionMatrix[5][6] = accion_semantica_nula;
        semanticActionMatrix[5][7] = accion_semantica_nula;
        semanticActionMatrix[5][8] = accion_semantica_nula;
        semanticActionMatrix[5][9] = accion_semantica_nula;
        semanticActionMatrix[5][10] = accion_semantica_nula;
        semanticActionMatrix[5][11] = accion_semantica_nula;
        semanticActionMatrix[5][12] = accion_semantica_nula;
        semanticActionMatrix[5][13] = accion_semantica_nula;
        semanticActionMatrix[5][14] = accion_semantica_nula;

        // State 6
        semanticActionMatrix[6][0] = accion_semantica_nula;
        semanticActionMatrix[6][1] = accion_semantica_nula;
        semanticActionMatrix[6][2] = accion_semantica_nula;
        semanticActionMatrix[6][3] = accion_semantica_nula;
        semanticActionMatrix[6][4] = accion_semantica_nula;
        semanticActionMatrix[6][5] = accion_semantica_nula;
        semanticActionMatrix[6][6] = accion_semantica_nula;
        semanticActionMatrix[6][7] = accion_semantica_nula;
        semanticActionMatrix[6][8] = accion_semantica_nula;
        semanticActionMatrix[6][9] = accion_semantica_nula;
        semanticActionMatrix[6][10] = accion_semantica_nula;
        semanticActionMatrix[6][11] = accion_semantica_nula;
        semanticActionMatrix[6][12] = accion_semantica_nula;
        semanticActionMatrix[6][13] = accion_semantica_nula;
        semanticActionMatrix[6][14] = accion_semantica_nula;

        // State 7
        semanticActionMatrix[7][0] = accion_semantica_nula;
        semanticActionMatrix[7][1] = accion_semantica_nula;
        semanticActionMatrix[7][2] = accion_semantica_nula;
        semanticActionMatrix[7][3] = accion_semantica_nula;
        semanticActionMatrix[7][4] = accion_semantica_nula;
        semanticActionMatrix[7][5] = accion_semantica_nula;
        semanticActionMatrix[7][6] = accion_semantica_nula;
        semanticActionMatrix[7][7] = accion_semantica_nula;
        semanticActionMatrix[7][8] = accion_semantica_nula;
        semanticActionMatrix[7][9] = accion_semantica_nula;
        semanticActionMatrix[7][10] = accion_semantica_nula;
        semanticActionMatrix[7][11] = accion_semantica_nula;
        semanticActionMatrix[7][12] = accion_semantica_nula;
        semanticActionMatrix[7][13] = accion_semantica_nula;
        semanticActionMatrix[7][14] = accion_semantica_nula;

        // State 8
        semanticActionMatrix[8][0] = accion_semantica_nula;
        semanticActionMatrix[8][1] = accion_semantica_nula;
        semanticActionMatrix[8][2] = accion_semantica_nula;
        semanticActionMatrix[8][3] = accion_semantica_nula;
        semanticActionMatrix[8][4] = accion_semantica_nula;
        semanticActionMatrix[8][5] = accion_semantica_nula;
        semanticActionMatrix[8][6] = accion_semantica_nula;
        semanticActionMatrix[8][7] = accion_semantica_nula;
        semanticActionMatrix[8][8] = accion_semantica_nula;
        semanticActionMatrix[8][9] = accion_semantica_nula;
        semanticActionMatrix[8][10] = accion_semantica_nula;
        semanticActionMatrix[8][11] = accion_semantica_nula;
        semanticActionMatrix[8][12] = accion_semantica_nula;
        semanticActionMatrix[8][13] = accion_semantica_nula;
        semanticActionMatrix[8][14] = accion_semantica_nula;

        // State 9
        semanticActionMatrix[9][0] = accion_semantica_nula;
        semanticActionMatrix[9][1] = accion_semantica_nula;
        semanticActionMatrix[9][2] = accion_semantica_nula;
        semanticActionMatrix[9][3] = accion_semantica_nula;
        semanticActionMatrix[9][4] = accion_semantica_nula;
        semanticActionMatrix[9][5] = accion_semantica_nula;
        semanticActionMatrix[9][6] = accion_semantica_nula;
        semanticActionMatrix[9][7] = accion_semantica_nula;
        semanticActionMatrix[9][8] = accion_semantica_nula;
        semanticActionMatrix[9][9] = accion_semantica_nula;
        semanticActionMatrix[9][10] = accion_semantica_nula;
        semanticActionMatrix[9][11] = accion_semantica_nula;
        semanticActionMatrix[9][12] = accion_semantica_nula;
        semanticActionMatrix[9][13] = accion_semantica_nula;
        semanticActionMatrix[9][14] = accion_semantica_nula;

        // State 10
        semanticActionMatrix[10][0] = accion_semantica_nula;
        semanticActionMatrix[10][1] = accion_semantica_nula;
        semanticActionMatrix[10][2] = accion_semantica_nula;
        semanticActionMatrix[10][3] = accion_semantica_nula;
        semanticActionMatrix[10][4] = accion_semantica_nula;
        semanticActionMatrix[10][5] = accion_semantica_nula;
        semanticActionMatrix[10][6] = accion_semantica_nula;
        semanticActionMatrix[10][7] = accion_semantica_nula;
        semanticActionMatrix[10][8] = accion_semantica_nula;
        semanticActionMatrix[10][9] = accion_semantica_nula;
        semanticActionMatrix[10][10] = accion_semantica_nula;
        semanticActionMatrix[10][11] = accion_semantica_nula;
        semanticActionMatrix[10][12] = accion_semantica_nula;
        semanticActionMatrix[10][13] = accion_semantica_nula;
        semanticActionMatrix[10][14] = accion_semantica_nula;
    }

    // Mapping symbols using ASCII code in semantic action matrix
    private int getSymbol(int character) {
        if ((character >= 65 && character <= 90) || (character >= 97 && character <= 122)) { // [a-z] [A-Z]
            return 0;
        } else if (character >= 48 && character <= 57) { // [0-9]
            return 1;
        } else if (character == 43) { // +
            return 2;
        } else if (character == 45 || character == 42 || character == 40 || character == 41 // - * ( )
                || character == 44 || character == 59 || character == 93 || character == 91) { // , ; [ ]
            return 3;
        } else if (character == 58) {   // :
            return 4;
        } else if (character == 62) {   // >
            return 5;
        } else if (character == 60) {   // <
            return 6;
        } else if (character == 61) { // =
            return 7;
        } else if (character == 37) { // %
            return 8;
        } else if (character == 47) { // / 
            return 9;
        } else if (character == 9 || character == 32) { // \t ' '  
            return 10;
        } else if (character == 10) { // \n
            return 11;
        } else if (character == 95) { // _ 
            return 12;
        } else if (character == 0) { // eof 
            return 14;
        } else {    // other
            return 13;
        }
    }

    public boolean notEOF() {
        return !fontCode.eof();
    }

    public int getNroLinea() {
        return fontCode.getLine();
    }

    public int yylex() {
        StringBuilder lexeme = new StringBuilder();

        int currentState = INITIAL_STATE;
        Token token = null;
        while (currentState != FINAL_STATE) {
            int currentCharacter = 0; // set null character
            if (notEOF()) {
                currentCharacter = fontCode.getCaracter(); // get the next character
            }
            // perform transition and execute the semantic action (if it has) 
            SemanticAction as = semanticActionMatrix[currentState][getSymbol(currentCharacter)];
            token = as.execute(fontCode, lexeme, (char) currentCharacter);
            currentState = transitionMatrix[currentState][getSymbol(currentCharacter)];
        }

        if (token != null) {
            Parser.yylval = new ParserVal(token.getLexeme());
            //System.out.println("Linea " + programaFuente.getNroLinea() + ": (AL) " + devolucion.imprimir());
            return token.getID();
        }

        return INVALID_TOKEN; // error
    }

    // Used only to try the lexer
    public Token getToken() {
        StringBuilder lexeme = new StringBuilder();

        int currentState = INITIAL_STATE;
        Token token = null;
        while (currentState != FINAL_STATE) {
            int currentCharacter = 0; // set null character
            if (notEOF()) {
                currentCharacter = fontCode.getCaracter(); // get the next character
            }
            // perform transition and execute the semantic action (if it has) 
            // System.err.println("Estado " + currentState + " caracter leido " + (char)currentCharacter + " ascii  " + currentCharacter);
            SemanticAction as = semanticActionMatrix[currentState][getSymbol(currentCharacter)];
            token = as.execute(fontCode, lexeme, (char) currentCharacter);
            currentState = transitionMatrix[currentState][getSymbol(currentCharacter)];
            // System.err.println("Siguiente estado: " + currentState);
        }
        if (token != null) {
            Parser.yylval = new ParserVal(token.getLexeme());
            //System.out.println("Linea " + programaFuente.getNroLinea() + ": (AL) " + devolucion.imprimir());
            return token;

        }

        return null; // error
    }

}
