//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
//#line 2 "grammar.y"
package Parser;

import analizadorLexico.LexerAnalyzer;
//#line 19 "Parser.java"

public class Parser {

    boolean yydebug;        //do I want debug output?
    int yynerrs;            //number of errors so far
    int yyerrflag;          //was there an error?
    int yychar;             //the current working character

    LexerAnalyzer lexer;

    public void setLex(LexerAnalyzer lex) {
        this.lexer = lex;
    }

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
    void debug(String msg) {
        if (yydebug) {
            System.out.println(msg);
        }
    }

//########## STATE STACK ##########
    final static int YYSTACKSIZE = 500;  //maximum stack size
    int statestk[] = new int[YYSTACKSIZE]; //state stack
    int stateptr;
    int stateptrmax;                     //highest index of stackptr
    int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################

    final void state_push(int state) {
        try {
            stateptr++;
            statestk[stateptr] = state;
        } catch (ArrayIndexOutOfBoundsException e) {
            int oldsize = statestk.length;
            int newsize = oldsize * 2;
            int[] newstack = new int[newsize];
            System.arraycopy(statestk, 0, newstack, 0, oldsize);
            statestk = newstack;
            statestk[stateptr] = state;
        }
    }

    final int state_pop() {
        return statestk[stateptr--];
    }

    final void state_drop(int cnt) {
        stateptr -= cnt;
    }

    final int state_peek(int relative) {
        return statestk[stateptr - relative];
    }
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################

    final boolean init_stacks() {
        stateptr = -1;
        val_init();
        return true;
    }
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################

    void dump_stacks(int count) {
        int i;
        System.out.println("=index==state====value=     s:" + stateptr + "  v:" + valptr);
        for (i = 0; i < count; i++) {
            System.out.println(" " + i + "    " + statestk[i] + "      " + valstk[i]);
        }
        System.out.println("======================");
    }

//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java
    String yytext;//user variable to return contextual strings
    ParserVal yyval; //used to return semantic vals from action routines
    ParserVal yylval;//the 'lval' (result) I got from yylex()
    ParserVal valstk[];
    int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################

    void val_init() {
        valstk = new ParserVal[YYSTACKSIZE];
        yyval = new ParserVal();
        yylval = new ParserVal();
        valptr = -1;
    }

    void val_push(ParserVal val) {
        if (valptr >= YYSTACKSIZE) {
            return;
        }
        valstk[++valptr] = val;
    }

    ParserVal val_pop() {
        if (valptr < 0) {
            return new ParserVal();
        }
        return valstk[valptr--];
    }

    void val_drop(int cnt) {
        int ptr;
        ptr = valptr - cnt;
        if (ptr < 0) {
            return;
        }
        valptr = ptr;
    }

    ParserVal val_peek(int relative) {
        int ptr;
        ptr = valptr - relative;
        if (ptr < 0) {
            return new ParserVal();
        }
        return valstk[ptr];
    }

    final ParserVal dup_yyval(ParserVal val) {
        ParserVal dup = new ParserVal();
        dup.ival = val.ival;
        dup.dval = val.dval;
        dup.sval = val.sval;
        dup.obj = val.obj;
        return dup;
    }
//#### end semantic value section ####
    public final static short IF = 257;
    public final static short ELSE = 258;
    public final static short END_IF = 259;
    public final static short PRINT = 260;
    public final static short INT = 261;
    public final static short BEGIN = 262;
    public final static short END = 263;
    public final static short LONG = 264;
    public final static short FOR_EACH = 265;
    public final static short IN = 266;
    public final static short ID = 267;
    public final static short STRING_CONST = 268;
    public final static short GREATER_EQUAL = 269;
    public final static short LESS_EQUAL = 270;
    public final static short EQUAL = 271;
    public final static short DISTINCT = 272;
    public final static short ASSIGN = 273;
    public final static short NUMERIC_CONST = 274;
    public final static short GREATER_THAN = 275;
    public final static short LESS_THAN = 276;
    public final static short YYERRCODE = 256;
    final static short yylhs[] = {-1,
        0, 1, 1, 1, 1, 2, 2, 4, 5, 7,
        7, 8, 8, 9, 9, 9, 9, 3, 3, 3,
        3, 10, 10, 16, 15, 15, 17, 17, 11, 12,
        13, 14, 14, 14, 14, 14, 14, 18, 18, 18,
        19, 19, 19, 6, 6, 20, 20, 20,};
    final static short yylen[] = {2,
        1, 1, 2, 2, 4, 1, 1, 3, 6, 1,
        3, 1, 1, 1, 1, 3, 3, 1, 1, 1,
        1, 6, 1, 8, 1, 3, 1, 3, 3, 5,
        5, 3, 3, 3, 3, 3, 3, 3, 3, 1,
        3, 3, 1, 1, 1, 1, 1, 4,};
    final static short yydefred[] = {0,
        44, 0, 45, 0, 0, 2, 6, 7, 0, 0,
        0, 0, 0, 3, 18, 19, 20, 21, 23, 0,
        4, 0, 0, 0, 0, 0, 0, 0, 0, 8,
        0, 0, 47, 0, 0, 0, 43, 0, 0, 0,
        5, 0, 15, 0, 0, 11, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 25, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 41, 42, 31, 30, 9, 16,
        17, 48, 27, 0, 0, 22, 26, 0, 0, 28,
        24,};
    final static short yydgoto[] = {4,
        5, 6, 65, 7, 8, 9, 23, 44, 45, 15,
        16, 17, 18, 34, 66, 19, 84, 35, 36, 37,};
    final static short yysindex[] = {-212,
        0, -220, 0, 0, -207, 0, 0, 0, -241, -2,
        13, -226, -222, 0, 0, 0, 0, 0, 0, -220,
        0, -29, -34, -252, -209, -202, -252, -192, -91, 0,
        -195, -18, 0, 33, -43, -33, 0, 34, -191, -26,
        0, 0, 0, -15, 37, 0, -189, -244, -252, -252,
        -252, -252, -252, -252, -252, -252, -252, -252, 24, -244,
        27, -90, -6, -220, 0, -230, -26, -26, -26, -26,
        -26, -26, -33, -33, 0, 0, 0, 0, 0, 0,
        0, 0, 0, -56, -244, 0, 0, -220, -171, 0,
        0,};
    final static short yyrindex[] = {0,
        0, 0, 0, 0, 89, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, -32, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 1, 0, 0, 0, 20, 0, 0, 0, 77,
        0, -37, 0, 0, -3, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 50, 52, 53, 54,
        55, 56, 39, 58, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0,};
    final static short yygindex[] = {0,
        0, 95, 4, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, -49, 0, 0, -19, 11, 12,};
    final static int YYTABLESIZE = 341;
    static short yytable[];

    static {
        yytable();
    }

    static void yytable() {
        yytable = new short[]{55,
            46, 56, 88, 43, 81, 14, 14, 40, 57, 31,
            78, 10, 10, 58, 32, 11, 55, 64, 56, 40,
            12, 33, 13, 28, 30, 22, 10, 85, 86, 67,
            68, 69, 70, 71, 72, 89, 10, 24, 38, 11,
            26, 46, 46, 46, 12, 46, 13, 46, 1, 2,
            27, 3, 25, 1, 20, 12, 3, 39, 38, 46,
            40, 29, 40, 39, 40, 73, 74, 83, 75, 76,
            41, 46, 47, 48, 59, 60, 29, 61, 40, 38,
            62, 38, 77, 38, 63, 79, 82, 91, 1, 13,
            35, 90, 34, 36, 37, 33, 32, 38, 39, 21,
            39, 0, 39, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 39, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 29, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 42, 80, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 87, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 49, 50, 51, 52, 0,
            0, 53, 54, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 46, 46,
            0, 46, 46, 46, 46, 0, 0, 0, 0, 46,
            46, 46, 46, 0, 0, 46, 46, 40, 40, 0,
            40, 40, 40, 40, 0, 0, 0, 0, 40, 40,
            40, 40, 0, 0, 40, 40, 38, 38, 0, 38,
            38, 38, 38, 0, 0, 0, 0, 38, 38, 38,
            38, 0, 0, 38, 38, 39, 39, 0, 39, 39,
            39, 39, 0, 0, 0, 0, 39, 39, 39, 39,
            0, 0, 39, 39, 29, 29, 0, 29, 29, 29,
            29,};
    }
    static short yycheck[];

    static {
        yycheck();
    }

    static void yycheck() {
        yycheck = new short[]{43,
            0, 45, 59, 95, 95, 2, 44, 27, 42, 44,
            60, 44, 257, 47, 267, 260, 43, 262, 45, 0,
            265, 274, 267, 20, 59, 267, 59, 258, 259, 49,
            50, 51, 52, 53, 54, 85, 257, 40, 0, 260,
            267, 41, 42, 43, 265, 45, 267, 47, 261, 262,
            273, 264, 40, 261, 262, 93, 264, 0, 268, 59,
            41, 91, 43, 266, 45, 55, 56, 64, 57, 58,
            263, 267, 91, 41, 41, 267, 0, 93, 59, 41,
            44, 43, 59, 45, 274, 59, 93, 259, 0, 93,
            41, 88, 41, 41, 41, 41, 41, 59, 41, 5,
            43, -1, 45, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, 59, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 59, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, 274, 274, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, 263, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 269, 270, 271, 272, -1,
            -1, 275, 276, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, 258, 259,
            -1, 261, 262, 263, 264, -1, -1, -1, -1, 269,
            270, 271, 272, -1, -1, 275, 276, 258, 259, -1,
            261, 262, 263, 264, -1, -1, -1, -1, 269, 270,
            271, 272, -1, -1, 275, 276, 258, 259, -1, 261,
            262, 263, 264, -1, -1, -1, -1, 269, 270, 271,
            272, -1, -1, 275, 276, 258, 259, -1, 261, 262,
            263, 264, -1, -1, -1, -1, 269, 270, 271, 272,
            -1, -1, 275, 276, 258, 259, -1, 261, 262, 263,
            264,};
    }
    final static short YYFINAL = 4;
    final static short YYMAXTOKEN = 276;
    final static String yyname[] = {
        "end-of-file", null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, "'('", "')'", "'*'", "'+'", "','",
        "'-'", null, "'/'", null, null, null, null, null, null, null, null, null, null, null, "';'",
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        "'['", null, "']'", null, "'_'", null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, "IF", "ELSE", "END_IF", "PRINT", "INT", "BEGIN",
        "END", "LONG", "FOR_EACH", "IN", "ID", "STRING_CONST", "GREATER_EQUAL", "LESS_EQUAL",
        "EQUAL", "DISTINCT", "ASSIGN", "NUMERIC_CONST", "GREATER_THAN", "LESS_THAN",};
    final static String yyrule[] = {
        "$accept : program",
        "program : statements",
        "statements : declarative_statements",
        "statements : BEGIN executional_statements",
        "statements : statements declarative_statements",
        "statements : statements BEGIN executional_statements END",
        "declarative_statements : variable_declaration_statement",
        "declarative_statements : colection_declaration_statement",
        "variable_declaration_statement : type variable_list ';'",
        "colection_declaration_statement : type ID '[' initial_value_list ']' ';'",
        "variable_list : ID",
        "variable_list : variable_list ',' ID",
        "initial_value_list : NUMERIC_CONST",
        "initial_value_list : value_list",
        "value_list : NUMERIC_CONST",
        "value_list : '_'",
        "value_list : value_list ',' NUMERIC_CONST",
        "value_list : value_list ',' '_'",
        "executional_statements : if_statement",
        "executional_statements : assign_statement",
        "executional_statements : for_each_in_statement",
        "executional_statements : print_statement",
        "if_statement : IF '(' condition ')' block_statements END_IF",
        "if_statement : if_else_statement",
        "if_else_statement : IF '(' condition ')' block_statements ELSE block_statements END_IF",
        "block_statements : executional_statements",
        "block_statements : BEGIN explicit_block_statements END",
        "explicit_block_statements : executional_statements",
        "explicit_block_statements : explicit_block_statements ';' executional_statements",
        "assign_statement : ID ASSIGN expression",
        "for_each_in_statement : FOR_EACH ID IN ID block_statements",
        "print_statement : PRINT '(' STRING_CONST ')' ';'",
        "condition : expression LESS_THAN expression",
        "condition : expression GREATER_THAN expression",
        "condition : expression LESS_EQUAL expression",
        "condition : expression GREATER_EQUAL expression",
        "condition : expression EQUAL expression",
        "condition : expression DISTINCT expression",
        "expression : expression '+' term",
        "expression : expression '-' term",
        "expression : term",
        "term : term '*' factor",
        "term : term '/' factor",
        "term : factor",
        "type : INT",
        "type : LONG",
        "factor : ID",
        "factor : NUMERIC_CONST",
        "factor : ID '[' NUMERIC_CONST ']'",};

//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
    void yylexdebug(int state, int ch) {
        String s = null;
        if (ch < 0) {
            ch = 0;
        }
        if (ch <= YYMAXTOKEN) //check index bounds
        {
            s = yyname[ch];    //now get it
        }
        if (s == null) {
            s = "illegal-symbol";
        }
        debug("state " + state + ", reading " + ch + " (" + s + ")");
    }

//The following are now global, to aid in error reporting
    int yyn;       //next next thing to do
    int yym;       //
    int yystate;   //current parsing state from state table
    String yys;    //current token string

//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
    public int yyparse() {
        boolean doaction;
        init_stacks();
        yynerrs = 0;
        yyerrflag = 0;
        yychar = -1;          //impossible char forces a read
        yystate = 0;            //initial state
        state_push(yystate);  //save it
        val_push(yylval);     //save empty value
        while (true) //until parsing is done, either correctly, or w/error
        {
            doaction = true;
            if (yydebug) {
                debug("loop");
            }
            //#### NEXT ACTION (from reduction table)
            for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
                if (yydebug) {
                    debug("yyn:" + yyn + "  state:" + yystate + "  yychar:" + yychar);
                }
                if (yychar < 0) //we want a char?
                {
                    yychar = yylex();  //get next token
                    if (yydebug) {
                        debug(" next yychar:" + yychar);
                    }
                    //#### ERROR CHECK ####
                    if (yychar < 0) //it it didn't work/error
                    {
                        yychar = 0;      //change it to default string (no -1!)
                        if (yydebug) {
                            yylexdebug(yystate, yychar);
                        }
                    }
                }//yychar<0
                yyn = yysindex[yystate];  //get amount to shift by (shift index)
                if ((yyn != 0) && (yyn += yychar) >= 0
                        && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {
                    if (yydebug) {
                        debug("state " + yystate + ", shifting to state " + yytable[yyn]);
                    }
                    //#### NEXT STATE ####
                    yystate = yytable[yyn];//we are in a new state
                    state_push(yystate);   //save it
                    val_push(yylval);      //push our lval as the input for next rule
                    yychar = -1;           //since we have 'eaten' a token, say we need another
                    if (yyerrflag > 0) //have we recovered an error?
                    {
                        --yyerrflag;        //give ourselves credit
                    }
                    doaction = false;        //but don't process yet
                    break;   //quit the yyn=0 loop
                }

                yyn = yyrindex[yystate];  //reduce
                if ((yyn != 0) && (yyn += yychar) >= 0
                        && yyn <= YYTABLESIZE && yycheck[yyn] == yychar) {   //we reduced!
                    if (yydebug) {
                        debug("reduce");
                    }
                    yyn = yytable[yyn];
                    doaction = true; //get ready to execute
                    break;         //drop down to actions
                } else //ERROR RECOVERY
                {
                    if (yyerrflag == 0) {
                        yyerror("syntax error");
                        yynerrs++;
                    }
                    if (yyerrflag < 3) //low error count?
                    {
                        yyerrflag = 3;
                        while (true) //do until break
                        {
                            if (stateptr < 0) //check for under & overflow here
                            {
                                yyerror("stack underflow. aborting...");  //note lower case 's'
                                return 1;
                            }
                            yyn = yysindex[state_peek(0)];
                            if ((yyn != 0) && (yyn += YYERRCODE) >= 0
                                    && yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE) {
                                if (yydebug) {
                                    debug("state " + state_peek(0) + ", error recovery shifting to state " + yytable[yyn] + " ");
                                }
                                yystate = yytable[yyn];
                                state_push(yystate);
                                val_push(yylval);
                                doaction = false;
                                break;
                            } else {
                                if (yydebug) {
                                    debug("error recovery discarding state " + state_peek(0) + " ");
                                }
                                if (stateptr < 0) //check for under & overflow here
                                {
                                    yyerror("Stack underflow. aborting...");  //capital 'S'
                                    return 1;
                                }
                                state_pop();
                                val_pop();
                            }
                        }
                    } else //discard this token
                    {
                        if (yychar == 0) {
                            return 1; //yyabort
                        }
                        if (yydebug) {
                            yys = null;
                            if (yychar <= YYMAXTOKEN) {
                                yys = yyname[yychar];
                            }
                            if (yys == null) {
                                yys = "illegal-symbol";
                            }
                            debug("state " + yystate + ", error recovery discards token " + yychar + " (" + yys + ")");
                        }
                        yychar = -1;  //read another
                    }
                }//end error recovery
            }//yyn=0 loop
            if (!doaction) //any reason not to proceed?
            {
                continue;      //skip action
            }
            yym = yylen[yyn];          //get count of terminals on rhs
            if (yydebug) {
                debug("state " + yystate + ", reducing " + yym + " by rule " + yyn + " (" + yyrule[yyn] + ")");
            }
            if (yym > 0) //if count of rhs not 'nil'
            {
                yyval = val_peek(yym - 1); //get current semantic value
            }
            yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
            switch (yyn) {
//########## USER-SUPPLIED ACTIONS ##########
                case 1: //#line 11 "grammar.y"
                {
                    System.out.println("statements");
                }
                break;
                case 8: //#line 24 "grammar.y"
                {
                    System.out.println("variable declaration " + val_peek(3));
                }
                break;
                case 9: //#line 27 "grammar.y"
                {
                    System.out.println("colection declaration " + val_peek(6) + val_peek(5));
                }
                break;
                case 10: //#line 30 "grammar.y"
                {
                    System.out.println("variable id: " + val_peek(1));
                }
                break;
                case 12: //#line 34 "grammar.y"
                {
                    System.out.println("Numeric const : " + val_peek(1));
                }
                break;
                case 32: //#line 74 "grammar.y"
                {
                    System.out.println("Expresion " + val_peek(3) + " < " + val_peek(1));
                }
                break;
                case 33: //#line 75 "grammar.y"
                {
                    System.out.println("Expresion " + val_peek(3) + " > " + val_peek(1));
                }
                break;
                case 34: //#line 76 "grammar.y"
                {
                    System.out.println("Expresion " + val_peek(3) + " <= " + val_peek(1));
                }
                break;
                case 35: //#line 77 "grammar.y"
                {
                    System.out.println("Expresion " + val_peek(3) + " <= " + val_peek(1));
                }
                break;
                case 36: //#line 78 "grammar.y"
                {
                    System.out.println("Expresion " + val_peek(3) + " == " + val_peek(1));
                }
                break;
                case 37: //#line 79 "grammar.y"
                {
                    System.out.println("Expresion " + val_peek(3) + " <> " + val_peek(1));
                }
                break;
//#line 517 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
            }//switch
            //#### Now let's reduce... ####
            if (yydebug) {
                debug("reduce");
            }
            state_drop(yym);             //we just reduced yylen states
            yystate = state_peek(0);     //get new state
            val_drop(yym);               //corresponding value drop
            yym = yylhs[yyn];            //select next TERMINAL(on lhs)
            if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
            {
                if (yydebug) {
                    debug("After reduction, shifting from state 0 to state " + YYFINAL + "");
                }
                yystate = YYFINAL;         //explicitly say we're done
                state_push(YYFINAL);       //and save it
                val_push(yyval);           //also save the semantic value of parsing
                if (yychar < 0) //we want another character?
                {
                    yychar = yylex();        //get next character
                    if (yychar < 0) {
                        yychar = 0;  //clean, if necessary
                    }
                    if (yydebug) {
                        yylexdebug(yystate, yychar);
                    }
                }
                if (yychar == 0) //Good exit (if lex returns 0 ;-)
                {
                    break;                 //quit the loop--all DONE
                }
            }//if yystate
            else //else not done yet
            {                         //get next state and push, for next yydefred[]
                yyn = yygindex[yym];      //find out where to go
                if ((yyn != 0) && (yyn += yystate) >= 0
                        && yyn <= YYTABLESIZE && yycheck[yyn] == yystate) {
                    yystate = yytable[yyn]; //get new state
                } else {
                    yystate = yydgoto[yym]; //else go to new defred
                }
                if (yydebug) {
                    debug("after reduction, shifting from state " + state_peek(0) + " to state " + yystate + "");
                }
                state_push(yystate);     //going again, so push state & val...
                val_push(yyval);         //for next action
            }
        }//main loop
        return 0;//yyaccept!!
    }
//## end of method parse() ######################################

//## run() --- for Thread #######################################
    /**
     * A default run method, used for operating this parser object in the
     * background. It is intended for extending Thread or implementing Runnable.
     * Turn off with -Jnorun .
     */
    public void run() {
        yyparse();
    }
//## end of method run() ########################################

//## Constructors ###############################################
    /**
     * Default constructor. Turn off with -Jnoconstruct .
     *
     */
    public Parser() {
        //nothing to do
    }

    /**
     * Create a parser, setting the debug to true or false.
     *
     * @param debugMe true for debugging, false for no debug.
     */
    public Parser(boolean debugMe) {
        yydebug = debugMe;
    }
//###############################################################

    private int yylex() {
        int yylex = lexer.yylex();
        System.out.println("yylex en parser : " + yylex);
        return yylex;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void yyerror(String syntax_error) {
        System.out.println("Syntax_Error en parser " + syntax_error);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
//################### END OF CLASS ##############################
