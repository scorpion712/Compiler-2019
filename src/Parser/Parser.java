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

	import analizadorLexico.*;
	import globales.*;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
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
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short ELSE=258;
public final static short END_IF=259;
public final static short PRINT=260;
public final static short INT=261;
public final static short BEGIN=262;
public final static short END=263;
public final static short LONG=264;
public final static short FOR_EACH=265;
public final static short IN=266;
public final static short ID=267;
public final static short STRING_CONST=268;
public final static short GREATER_EQUAL=269;
public final static short LESS_EQUAL=270;
public final static short EQUAL=271;
public final static short DISTINCT=272;
public final static short ASSIGN=273;
public final static short NUMERIC_CONST=274;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    2,    2,    4,    4,
    4,    4,    4,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    7,    7,    8,    8,    9,    9,
    3,   10,   10,   11,   11,   11,   11,   12,   12,   18,
   17,   17,   13,   14,   15,   16,   16,   16,   16,   16,
   16,   19,   19,   19,   20,   20,   20,    6,    6,   21,
   21,   21,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    3,
    3,    4,    2,    6,    6,    6,    5,    3,    5,    5,
    4,    6,    7,    7,    1,    3,    1,    3,    1,    1,
    3,    1,    2,    1,    1,    1,    1,    6,    1,    8,
    1,    1,    4,    5,    5,    3,    3,    3,    3,    3,
    3,    3,    3,    1,    3,    3,    1,    1,    1,    1,
    1,    2,
};
final static short yydefred[] = {                         0,
    0,   58,    0,   59,    0,    0,    3,    4,    7,    8,
    0,    0,    0,    0,    0,    0,    0,    0,   32,   34,
   35,   36,   37,   39,    5,    6,    0,    0,    0,    0,
   10,    0,    0,    0,    0,    0,   31,   33,   11,    0,
   30,    0,   29,    0,   27,    0,    9,    0,   26,   60,
   61,    0,    0,    0,    0,   57,    0,    0,    0,    0,
    0,   21,    0,    0,    0,   12,    0,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,    0,    0,   19,    0,    0,   20,   28,   15,
   42,   41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   55,   56,   45,   44,   16,   22,   14,    0,    0,
    0,   38,   24,   23,    0,   40,
};
final static short yydgoto[] = {                          5,
    6,    7,   91,    9,   10,   11,   13,   44,   45,   18,
   92,   20,   21,   22,   23,   53,   93,   24,   54,   55,
   56,
};
final static short yysindex[] = {                      -154,
 -251,    0, -161,    0,    0, -154,    0,    0,    0,    0,
 -201,  -35,   -6,   42,   44, -179, -175, -174,    0,    0,
    0,    0,    0,    0,    0,    0,  -49,  -83,  -20,  -92,
    0, -173,  -45, -165, -157,  -45,    0,    0,    0,  -92,
    0,  -60,    0,  -25,    0,   56,    0,  -14,    0,    0,
    0, -169,   75,  -16,   12,    0,   76, -149,   -2,   -8,
   26,    0,  -22,   61,  -92,    0,   62,    0, -170,  -45,
  -45,  -45,  -45,  -45,  -45,  -45,  -45,  -45,  -45,   63,
 -170,    0,   64,   65,    0,    3,   32,    0,    0,    0,
    0,    0, -158,   24,   24,   24,   24,   12,   12,   24,
   24,    0,    0,    0,    0,    0,    0,    0, -130,   68,
 -170,    0,    0,    0, -131,    0,
};
final static short yyrindex[] = {                       129,
    0,    0,    0,    0,    0,  130,    0,    0,    0,    0,
    0,    6,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    5,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   14,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -39,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   18,    0,    0,    0,    0,
    0,    0,    0,   90,   91,   92,   93,  -34,  -28,   94,
   95,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  131,   52,    0,    0,    0,  127,   21,    7,    0,
   46,    0,    0,    0,    0,    0,  -33,    0,    4,   37,
   35,
};
final static int YYTABLESIZE=282;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         52,
   25,   54,   43,   54,   13,   54,   52,   42,   52,   39,
   52,   43,   53,   18,   53,   12,   53,   17,   65,   54,
   54,   65,   54,   32,   52,   52,   74,   52,   75,   65,
   53,   53,   62,   53,   43,   65,   85,   32,   47,   59,
   74,   40,   75,   76,   25,   77,  109,  105,   19,   25,
   48,    8,   31,   78,   27,   30,   82,   26,   79,   25,
   60,  108,   63,   38,   25,   28,   74,   64,   75,   87,
   86,   89,   43,   94,   95,   96,   97,  115,   67,  100,
  101,   33,   14,   34,   83,   15,   14,   35,   37,   15,
   16,    3,   17,   49,   16,   14,   17,   36,   15,  111,
  112,    1,   57,   16,   68,   17,    2,    3,   58,    4,
   98,   99,  102,  103,   66,   69,   80,   81,   84,   88,
   90,  104,  106,  107,  110,  113,  114,  116,    1,    2,
   49,   48,   50,   51,   46,   47,   25,   29,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   41,    0,    0,    0,    0,    0,    0,    0,    0,
   41,    0,    0,    0,    0,   61,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   41,    0,    0,    0,    0,    0,    0,
    0,   50,    0,    0,    0,    0,    0,    0,   51,   54,
   54,   54,   54,    0,   52,   52,   52,   52,    0,    0,
   53,   53,   53,   53,    0,    0,   46,    0,    0,    0,
    0,   41,   70,   71,   72,   73,   25,    0,    0,    0,
   13,   25,   25,    0,   25,   13,   13,   25,   13,   18,
    0,    0,    0,   17,   18,   18,    0,   18,   17,   17,
    0,   17,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,   41,   95,   43,    0,   45,   41,   91,   43,   59,
   45,   95,   41,    0,   43,  267,   45,    0,   44,   59,
   60,   44,   62,   44,   59,   60,   43,   62,   45,   44,
   59,   60,   93,   62,   95,   44,   59,   44,   59,   36,
   43,   91,   45,   60,   44,   62,   44,   81,    3,   44,
   30,    0,   59,   42,  256,   91,   59,    6,   47,   59,
   40,   59,   42,   18,   59,  267,   43,   93,   45,   63,
   93,   65,   95,   70,   71,   72,   73,  111,   93,   76,
   77,   40,  257,   40,   93,  260,  257,  267,  263,  260,
  265,  262,  267,  267,  265,  257,  267,  273,  260,  258,
  259,  256,  268,  265,  274,  267,  261,  262,  266,  264,
   74,   75,   78,   79,   59,   41,   41,  267,   93,   59,
   59,   59,   59,   59,   93,  256,   59,  259,    0,    0,
   41,   41,   41,   41,   41,   41,    6,   11,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  274,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  274,   -1,   -1,   -1,   -1,  256,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  274,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  267,   -1,   -1,   -1,   -1,   -1,   -1,  274,  269,
  270,  271,  272,   -1,  269,  270,  271,  272,   -1,   -1,
  269,  270,  271,  272,   -1,   -1,  267,   -1,   -1,   -1,
   -1,  274,  269,  270,  271,  272,  256,   -1,   -1,   -1,
  256,  261,  262,   -1,  264,  261,  262,  267,  264,  256,
   -1,   -1,   -1,  256,  261,  262,   -1,  264,  261,  262,
   -1,  264,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=274;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'",null,"'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,"'_'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"IF","ELSE","END_IF","PRINT","INT","BEGIN",
"END","LONG","FOR_EACH","IN","ID","STRING_CONST","GREATER_EQUAL","LESS_EQUAL",
"EQUAL","DISTINCT","ASSIGN","NUMERIC_CONST",
};
final static String yyrule[] = {
"$accept : program",
"program :",
"program : statements",
"statements : declarative_statements",
"statements : block_statements",
"statements : statements declarative_statements",
"statements : statements block_statements",
"declarative_statements : variable_declaration_statement",
"declarative_statements : colection_declaration_statement",
"variable_declaration_statement : type var_list ';'",
"variable_declaration_statement : error var_list ';'",
"variable_declaration_statement : type error ';'",
"variable_declaration_statement : type var_list ID ';'",
"variable_declaration_statement : type var_list",
"colection_declaration_statement : type ID '[' index_list ']' ';'",
"colection_declaration_statement : error ID '[' index_list ']' ';'",
"colection_declaration_statement : type error '[' index_list ']' ';'",
"colection_declaration_statement : type ID '[' index_list ']'",
"colection_declaration_statement : type ID index_list",
"colection_declaration_statement : type ID '[' index_list ';'",
"colection_declaration_statement : type ID index_list ']' ';'",
"colection_declaration_statement : type ID '[' ']'",
"colection_declaration_statement : type ID '[' error ']' ';'",
"colection_declaration_statement : type ID '[' index_list index ']' ';'",
"colection_declaration_statement : type ID '[' index_list ']' ',' error",
"var_list : ID",
"var_list : var_list ',' ID",
"index_list : index",
"index_list : index_list ',' index",
"index : '_'",
"index : NUMERIC_CONST",
"block_statements : BEGIN statement_block END",
"statement_block : executional_statements",
"statement_block : statement_block executional_statements",
"executional_statements : if_statement",
"executional_statements : assign_statement",
"executional_statements : for_each_in_statement",
"executional_statements : print_statement",
"if_statement : IF '(' condition ')' executional_block END_IF",
"if_statement : if_else_statement",
"if_else_statement : IF '(' condition ')' executional_block ELSE executional_block END_IF",
"executional_block : executional_statements",
"executional_block : block_statements",
"assign_statement : ID ASSIGN expression ';'",
"for_each_in_statement : FOR_EACH ID IN ID executional_block",
"print_statement : PRINT '(' STRING_CONST ')' ';'",
"condition : expression '<' expression",
"condition : expression '>' expression",
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
"factor : '-' NUMERIC_CONST",
};

//#line 123 "grammar.y"

 	// Constants declared to print error messages
 	private static final String ERROR_IDENTIFIER = ": identifier expected."; 
 	private static final String ERROR_INDEX = ": colection index expected.";
 	private static final String ERROR_TYPE = ": type expected.";
 	private static final String ERROR_SEMICOLON = ": ';' expected.";
 	private static final String ERROR_COMMA = ": ',' expected."; 
 	private static final String ERROR_SQUARE_BRACKET = " '['']' expected.";
 	private static final String ERROR_SQUARE_BRACKET_OPEN = " '[' expected.";
 	private static final String ERROR_SQUARE_BRACKET_CLOSE = " ']' expected.";
 	private static final String ERROR_BRACKET_OPEN = ": '(' expected.";
 	private static final String ERROR_BRACKET = ": ')' expected.";
 	private static final String ERROR_BEGIN = ": begin expected."; // before a executive statement
 	private static final String ERROR_END = ": end expected.";
 	private static final String ERROR_END_IF = ": end_if expected.";
 	private static final String ERROR_FOR = ": for expected.";
 	private static final String ERROR_IN = ": in expected.";
 	private static final String ERROR_OPERATOR = ": math operator expected.";
 	private static final String ERROR_SYMBOL = ": symbol expected.";
 	private static final String ERROR_COLECTION = ": error in colection declaration statement."; 

 	private int error_counter;

    private LexerAnalyzer lexer;


	public Parser(LexerAnalyzer lexer) {
        this.lexer = lexer;
        error_counter = 0;
        yydebug=true; // uncomment this line to show debug en console
	}

    private int yylex() {
        
        Token token = this.lexer.getToken(); 
        if (token != null) { 
            this.yylval = new ParserVal(token.getLexeme());
            return token.getID();
        } 
	return 0; 
    } 
    
    private void yyerror(String parser_error) {
    	if (!parser_error.equalsIgnoreCase("syntax error") ) { // ignore default parser error
    		System.out.println("    Syntax error near line " + lexer.getLine() + parser_error);
    		error_counter++;
    	}
    }

    public int getError() {
    	return error_counter;
    }

    // Check if a constants is out of range
    public void checkRange(String constant) {
    	// TO DO
    }
//#line 398 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 16 "grammar.y"
{System.out.println("EOF");}
break;
case 9:
//#line 30 "grammar.y"
{System.out.println("Variable declared at line " + lexer.getLine() + ".");}
break;
case 10:
//#line 31 "grammar.y"
{yyerror(ERROR_TYPE);}
break;
case 11:
//#line 32 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 12:
//#line 33 "grammar.y"
{yyerror(ERROR_COMMA);}
break;
case 13:
//#line 34 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 14:
//#line 37 "grammar.y"
{System.out.println("Colection declared at line " + lexer.getLine() + ".");}
break;
case 15:
//#line 38 "grammar.y"
{yyerror(ERROR_TYPE);}
break;
case 16:
//#line 39 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 17:
//#line 40 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 18:
//#line 41 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET);}
break;
case 19:
//#line 42 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
break;
case 20:
//#line 43 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_OPEN);}
break;
case 21:
//#line 44 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 22:
//#line 45 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 23:
//#line 46 "grammar.y"
{yyerror(ERROR_COMMA);}
break;
case 24:
//#line 47 "grammar.y"
{yyerror(ERROR_COLECTION);}
break;
//#line 615 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
