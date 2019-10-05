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
public static ParserVal yyval; //used to return semantic vals from action routines
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
public final static short GREATER_THAN=275;
public final static short LESS_THAN=276;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    2,    2,    4,    5,
    7,    7,    7,    8,    8,    9,   10,   10,    3,   11,
   11,   12,   12,   12,   12,   13,   13,   19,   18,   18,
   14,   15,   16,   17,   17,   17,   17,   17,   17,   20,
   20,   20,   21,   21,   21,    6,    6,   22,   22,   22,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    6,
    1,    2,    3,    1,    1,    3,    1,    1,    3,    1,
    2,    1,    1,    1,    1,    6,    1,    8,    1,    1,
    4,    5,    5,    3,    3,    3,    3,    3,    3,    3,
    3,    1,    3,    3,    1,    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
   46,    0,   47,    0,    0,    3,    4,    7,    8,    0,
    0,    0,    0,    0,    0,   20,   22,   23,   24,   25,
   27,    5,    6,    0,    0,    0,    0,    0,    0,    0,
   19,   21,   12,    0,    9,    0,    0,   49,    0,    0,
    0,   45,    0,    0,    0,   14,    0,    0,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   31,    0,    0,    0,   30,   29,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   43,   44,
   33,   32,   10,   18,   17,   16,   50,    0,   26,    0,
   28,
};
final static short yydgoto[] = {                          4,
    5,    6,   68,    8,    9,   10,   26,   47,   48,   86,
   15,   69,   17,   18,   19,   20,   39,   70,   21,   40,
   41,   42,
};
final static short yysindex[] = {                      -191,
    0, -193,    0,    0, -191,    0,    0,    0,    0, -217,
  -26,   -6, -215, -220, -206,    0,    0,    0,    0,    0,
    0,    0,    0,   -3,  -29,  -31, -234, -199, -187, -234,
    0,    0,    0, -194,    0, -186,   -9,    0,   42,   -7,
  -27,    0,   43, -182,  -36,    0,   -5,   45,    0, -188,
 -202, -234, -234, -234, -234, -234, -234, -234, -234, -234,
 -234,   28, -202,    0,   31,  -92,   -2,    0,    0, -183,
   23,   23,   23,   23,   23,   23,  -27,  -27,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -202,    0, -167,
    0,
};
final static short yyrindex[] = {                        93,
    0,    0,    0,    0,   94,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -28,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -41,    0,    0,    0,
  -33,    0,    0,    0,    0,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,   56,   57,   58,   59,   60,  -24,  -16,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,   97,   32,    0,    0,    0,    0,    0,    0,    0,
    0,    9,    0,    0,    0,    0,    0,  -58,    0,   -8,
  -17,   17,
};
final static int YYTABLESIZE=269;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
   48,   48,   85,   48,   82,   48,   58,   42,   59,   42,
   16,   42,   36,   27,   60,   11,   40,   48,   40,   61,
   40,   45,   64,   32,   41,   42,   41,   35,   41,   90,
   11,    7,   37,   28,   40,   58,   23,   59,   24,   38,
   77,   78,   41,   71,   72,   73,   74,   75,   76,   25,
   11,   29,   30,   12,   11,   33,   31,   12,   13,    2,
   14,   34,   13,   11,   14,   58,   12,   59,   43,    1,
    2,   13,    3,   14,   88,   89,   79,   80,   44,   46,
   49,   50,   51,   62,   63,   67,   81,   65,   66,   83,
   87,   91,    1,    2,   15,   37,   36,   38,   39,   35,
   34,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   84,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   48,   48,   48,
   48,    0,    0,   48,   48,   42,   42,   42,   42,    0,
    0,   42,   42,    0,   40,   40,   40,   40,    0,    0,
   40,   40,   41,   41,   41,   41,    0,    0,   41,   41,
    0,   52,   53,   54,   55,    0,    0,   56,   57,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   42,   43,   95,   45,   63,   47,   43,   41,   45,   43,
    2,   45,   44,   40,   42,   44,   41,   59,   43,   47,
   45,   30,   59,   15,   41,   59,   43,   59,   45,   88,
   59,    0,  267,   40,   59,   43,    5,   45,  256,  274,
   58,   59,   59,   52,   53,   54,   55,   56,   57,  267,
  257,  267,  273,  260,  257,   59,  263,  260,  265,  262,
  267,   91,  265,  257,  267,   43,  260,   45,  268,  261,
  262,  265,  264,  267,  258,  259,   60,   61,  266,  274,
  267,   91,   41,   41,  267,  274,   59,   93,   44,   59,
   93,  259,    0,    0,   93,   41,   41,   41,   41,   41,
   41,    5,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  274,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  269,  270,  271,
  272,   -1,   -1,  275,  276,  269,  270,  271,  272,   -1,
   -1,  275,  276,   -1,  269,  270,  271,  272,   -1,   -1,
  275,  276,  269,  270,  271,  272,   -1,   -1,  275,  276,
   -1,  269,  270,  271,  272,   -1,   -1,  275,  276,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=276;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
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
"EQUAL","DISTINCT","ASSIGN","NUMERIC_CONST","GREATER_THAN","LESS_THAN",
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
"variable_declaration_statement : type variable_list ';'",
"colection_declaration_statement : type ID '[' initial_value_list ']' ';'",
"variable_list : ID",
"variable_list : error ';'",
"variable_list : variable_list ',' ID",
"initial_value_list : NUMERIC_CONST",
"initial_value_list : index_list",
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
"factor : ID '[' NUMERIC_CONST ']'",
};

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
case 2:
//#line 17 "grammar.y"
{System.out.println("statements");}
break;
case 3:
//#line 20 "grammar.y"
{System.out.println("declarat");}
break;
case 7:
//#line 26 "grammar.y"
{System.out.println("var decl state");}
break;
case 9:
//#line 30 "grammar.y"
{System.out.println("variable declaration");}
break;
case 10:
//#line 33 "grammar.y"
{System.out.println("colection declaration ");}
break;
case 11:
//#line 36 "grammar.y"
{System.out.println("ID aca");}
break;
case 12:
//#line 37 "grammar.y"
{System.out.println("Error: No se encontro identificador.");}
break;
case 13:
//#line 38 "grammar.y"
{System.out.println("var list");}
break;
case 14:
//#line 41 "grammar.y"
{System.out.println("Numeric const : ");}
break;
case 34:
//#line 85 "grammar.y"
{System.out.println("Expresion " + val_peek(3));}
break;
case 35:
//#line 86 "grammar.y"
{System.out.println("Expresion " + val_peek(3) + " > " + val_peek(1));}
break;
case 36:
//#line 87 "grammar.y"
{System.out.println("Expresion " + val_peek(3) + " <= " + val_peek(1));}
break;
case 37:
//#line 88 "grammar.y"
{System.out.println("Expresion " + val_peek(3) + " <= " + val_peek(1));}
break;
case 38:
//#line 89 "grammar.y"
{System.out.println("Expresion " + val_peek(3) + " == " + val_peek(1));}
break;
case 39:
//#line 90 "grammar.y"
{System.out.println("Expresion " + val_peek(3) + " <> " + val_peek(1));}
break;
case 46:
//#line 103 "grammar.y"
{System.out.println("Soy tipo INT");}
break;
case 47:
//#line 104 "grammar.y"
{System.out.println("LONG");}
break;
//#line 536 "Parser.java"
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
public Parser(LexerAnalyzer lexer) {
        this.al = lexer;
        //yydebug=true; // uncomment this line to show debug en console
}
    private LexerAnalyzer al;

    private int yylex() {
        
        Token token = this.al.getToken(); 
        
        if (token != null) {
            this.yylval = new ParserVal(token.getLexeme());
            return token.getID();
        } 
	return 0; 
    } 
    
    private void yyerror(String syntax_error) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("yyerror: " + syntax_error);
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
