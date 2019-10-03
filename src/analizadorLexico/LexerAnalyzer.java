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
        /*E7*/ {7, 7, 7, 7, 7, 7, 7, 7, 11, 7, 7, 0, 7, 7, 11},
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
        SemanticAction AS2 = new SAGenerateConst();
        SemanticAction AS3 = new SAGenerateAssignment();
        SemanticAction AS4 = new SAGenerateOperator();
        SemanticAction AS5 = new SAGenerateGreaterEqual();
        SemanticAction AS6 = new SAGenerateDistinct();
        SemanticAction AS7 = new SAGenerateLessEqual();
        SemanticAction AS8 = new SAGenerateEqual();
        SemanticAction AS9 = new SAGenerateString();
        SemanticAction AS10 = new SAGenerateNewLine();
        SemanticAction AS11 = new SACommentWarning();
        SemanticAction AS12 = new SAGenerateSimpleSymbol();
        SemanticAction AS13 = new SASymbolWarning(); 

        SemanticAction AS14 = new SAStringWarning();
        
        // Initialize semactic action matrix
        semanticActionMatrix = new SemanticAction[11][15];

        // State 0 (initial)
        semanticActionMatrix[0][0] = AS0;
        semanticActionMatrix[0][1] = AS0;
        semanticActionMatrix[0][2] = AS12;
        semanticActionMatrix[0][3] = AS12;
        semanticActionMatrix[0][4] = AS0;
        semanticActionMatrix[0][5] = AS0;
        semanticActionMatrix[0][6] = AS0;
        semanticActionMatrix[0][7] = AS0;
        semanticActionMatrix[0][8] = AS0;
        semanticActionMatrix[0][9] = AS0;
        semanticActionMatrix[0][10] = new SANull();
        semanticActionMatrix[0][11] = AS10;
        semanticActionMatrix[0][12] = AS13;
        semanticActionMatrix[0][13] = AS13;
        semanticActionMatrix[0][14] = new SANull(); // EOF

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
        semanticActionMatrix[2][0] = AS2;
        semanticActionMatrix[2][1] = AS0;
        semanticActionMatrix[2][2] = AS2;
        semanticActionMatrix[2][3] = AS2;
        semanticActionMatrix[2][4] = AS2;
        semanticActionMatrix[2][5] = AS2;
        semanticActionMatrix[2][6] = AS2;
        semanticActionMatrix[2][7] = AS2;
        semanticActionMatrix[2][8] = AS2;
        semanticActionMatrix[2][9] = AS2;
        semanticActionMatrix[2][10] = AS2;
        semanticActionMatrix[2][11] = AS2;
        semanticActionMatrix[2][12] = AS2;
        semanticActionMatrix[2][13] = AS2;
        semanticActionMatrix[2][14] = AS2;

        // State 3
        semanticActionMatrix[3][0] = AS13;
        semanticActionMatrix[3][1] = AS13;
        semanticActionMatrix[3][2] = AS13;
        semanticActionMatrix[3][3] = AS13;
        semanticActionMatrix[3][4] = AS13;
        semanticActionMatrix[3][5] = AS13;
        semanticActionMatrix[3][6] = AS13;
        semanticActionMatrix[3][7] = AS3;
        semanticActionMatrix[3][8] = AS13;
        semanticActionMatrix[3][9] = AS13;
        semanticActionMatrix[3][10] = AS13;
        semanticActionMatrix[3][11] = AS13;
        semanticActionMatrix[3][12] = AS13;
        semanticActionMatrix[3][13] = AS13;
        semanticActionMatrix[3][14] = AS13;

        // State 4
        semanticActionMatrix[4][0] = AS4;
        semanticActionMatrix[4][1] = AS4;
        semanticActionMatrix[4][2] = AS4;
        semanticActionMatrix[4][3] = AS4;
        semanticActionMatrix[4][4] = AS4;
        semanticActionMatrix[4][5] = AS4;
        semanticActionMatrix[4][6] = AS4;
        semanticActionMatrix[4][7] = AS5;
        semanticActionMatrix[4][8] = AS4;
        semanticActionMatrix[4][9] = AS4;
        semanticActionMatrix[4][10] = AS4;
        semanticActionMatrix[4][11] = AS4;
        semanticActionMatrix[4][12] = AS4;
        semanticActionMatrix[4][13] = AS4;
        semanticActionMatrix[4][14] = AS4;

        // State 5
        semanticActionMatrix[5][0] = AS4;
        semanticActionMatrix[5][1] = AS4;
        semanticActionMatrix[5][2] = AS4;
        semanticActionMatrix[5][3] = AS4;
        semanticActionMatrix[5][4] = AS4;
        semanticActionMatrix[5][5] = AS6;
        semanticActionMatrix[5][6] = AS4;
        semanticActionMatrix[5][7] = AS7;
        semanticActionMatrix[5][8] = AS4;
        semanticActionMatrix[5][9] = AS4;
        semanticActionMatrix[5][10] = AS4;
        semanticActionMatrix[5][11] = AS4;
        semanticActionMatrix[5][12] = AS4;
        semanticActionMatrix[5][13] = AS4;
        semanticActionMatrix[5][14] = AS4;

        // State 6
        semanticActionMatrix[6][0] = AS4;
        semanticActionMatrix[6][1] = AS4;
        semanticActionMatrix[6][2] = AS4;
        semanticActionMatrix[6][3] = AS4;
        semanticActionMatrix[6][4] = AS4;
        semanticActionMatrix[6][5] = AS6;
        semanticActionMatrix[6][6] = AS4;
        semanticActionMatrix[6][7] = AS8;
        semanticActionMatrix[6][8] = AS4;
        semanticActionMatrix[6][9] = AS4;
        semanticActionMatrix[6][10] = AS4;
        semanticActionMatrix[6][11] = AS4;
        semanticActionMatrix[6][12] = AS4;
        semanticActionMatrix[6][13] = AS4;
        semanticActionMatrix[6][14] = AS4;

        // State 7
        semanticActionMatrix[7][0] = AS0;
        semanticActionMatrix[7][1] = AS0;
        semanticActionMatrix[7][2] = AS0;
        semanticActionMatrix[7][3] = AS0;
        semanticActionMatrix[7][4] = AS0;
        semanticActionMatrix[7][5] = AS0;
        semanticActionMatrix[7][6] = AS0;
        semanticActionMatrix[7][7] = AS0;
        semanticActionMatrix[7][8] = AS9;
        semanticActionMatrix[7][9] = AS0;
        semanticActionMatrix[7][10] = AS0;
        semanticActionMatrix[7][11] = AS14; // \n deberia lanzar warning cadena no valida por falta de cierre
        semanticActionMatrix[7][12] = AS0; 
        semanticActionMatrix[7][13] = AS0;
        semanticActionMatrix[7][14] = AS14; // EOF debería lanzar warning por cadena no cerrada

        // State 8
        semanticActionMatrix[8][0] = AS4;
        semanticActionMatrix[8][1] = AS4;
        semanticActionMatrix[8][2] = AS0;
        semanticActionMatrix[8][3] = AS4;
        semanticActionMatrix[8][4] = AS4;
        semanticActionMatrix[8][5] = AS4;
        semanticActionMatrix[8][6] = AS4;
        semanticActionMatrix[8][7] = AS4;
        semanticActionMatrix[8][8] = AS4;
        semanticActionMatrix[8][9] = AS4;
        semanticActionMatrix[8][10] = AS4;
        semanticActionMatrix[8][11] = AS4;
        semanticActionMatrix[8][12] = AS4;
        semanticActionMatrix[8][13] = AS4;
        semanticActionMatrix[8][14] = AS4;

        // State 9
        semanticActionMatrix[9][0] = AS0;
        semanticActionMatrix[9][1] = AS0;
        semanticActionMatrix[9][2] = AS0;
        semanticActionMatrix[9][3] = AS0;
        semanticActionMatrix[9][4] = AS0;
        semanticActionMatrix[9][5] = AS0;
        semanticActionMatrix[9][6] = AS0;
        semanticActionMatrix[9][7] = AS0;
        semanticActionMatrix[9][8] = AS0;
        semanticActionMatrix[9][9] = AS0;
        semanticActionMatrix[9][10] = AS0;
        semanticActionMatrix[9][11] = AS10;
        semanticActionMatrix[9][12] = AS0;
        semanticActionMatrix[9][13] = AS0;
        semanticActionMatrix[9][14] = AS11;

        // State 10
        semanticActionMatrix[10][0] = AS0;
        semanticActionMatrix[10][1] = AS0;
        semanticActionMatrix[10][2] = AS0;
        semanticActionMatrix[10][3] = AS0;
        semanticActionMatrix[10][4] = AS0;
        semanticActionMatrix[10][5] = AS0;
        semanticActionMatrix[10][6] = AS0;
        semanticActionMatrix[10][7] = AS0;
        semanticActionMatrix[10][8] = AS0;
        semanticActionMatrix[10][9] = AS0;
        semanticActionMatrix[10][10] = AS0;
        semanticActionMatrix[10][11] = AS10;
        semanticActionMatrix[10][12] = AS0;
        semanticActionMatrix[10][13] = AS0;
        semanticActionMatrix[10][14] = AS11;
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
            int currentCharacter = 0; // set null (eof) character
            if (notEOF()) {
                currentCharacter = fontCode.getCaracter(); // get the next character
            }
            // perform transition and execute the semantic action 
            SemanticAction as = semanticActionMatrix[currentState][getSymbol(currentCharacter)]; 
            token = as.execute(fontCode, lexeme, (char) currentCharacter);
            currentState = transitionMatrix[currentState][getSymbol(currentCharacter)]; 
        } 

        return token; // or error
    }

}
