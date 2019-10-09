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
	package parser;

	import lexer.*;
	import symbol_table.*;
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
public final static short FOREACH=265;
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
    4,    4,    4,    5,    5,    5,    5,    5,    8,    8,
    8,    8,    8,    8,    8,    7,    7,    9,    9,   10,
   10,    3,    3,    3,    3,   11,   11,   12,   12,   12,
   12,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   19,   19,   19,   19,   19,   18,   18,   14,   14,   14,
   14,   14,   15,   15,   15,   15,   15,   15,   16,   16,
   16,   16,   16,   16,   16,   17,   17,   17,   17,   17,
   17,   20,   20,   20,   21,   21,   21,    6,    6,   22,
   22,   22,   22,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    3,
    3,    4,    2,    3,    3,    2,    4,    2,    4,    6,
    4,    3,    4,    3,    3,    1,    3,    1,    3,    1,
    1,    3,    3,    3,    3,    1,    2,    1,    1,    1,
    1,    6,    1,    5,    5,    4,    6,    6,    6,    6,
    8,    8,    8,    8,    8,    1,    1,    4,    4,    4,
    4,    3,    5,    4,    5,    5,    5,    5,    5,    5,
    4,    4,    3,    5,    4,    3,    3,    3,    3,    3,
    3,    3,    3,    1,    3,    3,    1,    1,    1,    1,
    1,    2,    4,
};
final static short yydefred[] = {                         0,
    0,   88,    0,   89,    0,    0,    3,    4,    7,    8,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   36,
   38,   39,   40,   41,   43,    0,    0,    0,    5,    6,
    0,    0,   16,    0,    0,    0,    0,    0,    0,   91,
    0,    0,    0,    0,    0,   87,    0,    0,    0,    0,
    0,    0,    0,   31,    0,   30,    0,   28,   10,    0,
   15,    0,    0,   33,   37,   34,    0,   32,   11,    0,
    9,    0,   14,    0,    0,    0,    0,    0,   92,    0,
    0,    0,    0,   57,   56,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   73,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   25,    0,   22,
   24,    0,   27,    0,   12,   17,   59,   21,    0,    0,
    0,    0,    0,    0,    0,   46,    0,    0,    0,    0,
    0,    0,    0,    0,   85,   86,   71,    0,   72,    0,
    0,    0,    0,    0,   60,   64,   61,   58,   23,   19,
   29,    0,   70,    0,   93,    0,    0,    0,   45,   44,
   74,   69,   65,   66,   67,   68,   63,    0,   48,    0,
   47,    0,   49,   50,    0,   42,   20,    0,    0,    0,
    0,   53,   54,   52,   55,   51,
};
final static short yydgoto[] = {                          5,
    6,    7,   84,    9,   10,   11,   17,   18,   57,   58,
   19,   85,   21,   22,   23,   24,   43,   86,   25,   44,
   45,   46,
};
final static short yysindex[] = {                       118,
 -105,    0,  274,    0,    0,  118,    0,    0,    0,    0,
  -49,  -38,  -40,  -24, -159,  -78,   -8,   18, -131,    0,
    0,    0,    0,    0,    0,  -34, -209,  -54,    0,    0,
  -15,  -77,    0,  -22,  -14,    7,  -88,   46,  -57,    0,
 -235,  -41,  116,  -20,   63,    0,  -13, -208, -207, -183,
    7, -201,   78,    0,  -66,    0,  -32,    0,    0, -161,
    0, -156,  -31,    0,    0,    0,  -31,    0,    0,   68,
    0,   91,    0,   20,  -25,   96,  106, -118,    0,  122,
  128,   27,  238,    0,    0,  -91,    7,    7,    7,    7,
    7,    7,    7,    7,    7,    7,    0,  121,  144,   -9,
  -76,  -75,  -84,   26,  238,  135,   29,    0,   -1,    0,
    0,  -88,    0,  107,    0,    0,    0,    0,  141,  238,
  108,  238,  250,  -60,  -55,    0,   88,   88,   88,   88,
   63,   63,   88,   88,    0,    0,    0,  153,    0,  155,
  238,  238,  238,  -83,    0,    0,    0,    0,    0,    0,
    0,  -88,    0,  -43,    0,  -69,  -37, -169,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -18,    0,  238,
    0,  238,    0,    0,  262,    0,    0,  -12,   -3,  -19,
 -110,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                       225,
    0,    0,    0,    0,    0,  232,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   11,    0,   15,   24,    0,    0,    0,   39,    0,
    0,    0,    0,    0,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  214,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  140,  152,  164,  176,
   79,   99,  190,  202,    0,    0,    0,    0,    0,  226,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  263,   35,    0,    0,    0,  259,  271,  -17,  177,
  287,   30,    0,    0,    0,    0,   65,  -27,    0,   77,
   62,   80,
};
final static int YYTABLESIZE=541;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
   35,   38,   38,   41,   41,   38,   56,  166,   38,   33,
   26,  112,   55,   55,   13,   48,   56,   56,  112,   75,
   38,   60,   91,   18,   92,  112,  108,   98,   56,   62,
   20,  140,   20,   78,    8,   60,   71,  109,   79,   93,
   30,   94,  112,   69,   73,   97,   51,   99,   65,  139,
   59,   41,   37,  124,   26,  125,   52,   65,  101,  100,
  111,   62,   91,   53,   92,  105,   38,  118,   91,   26,
   92,   91,  102,   92,  177,   37,   61,  146,  117,   90,
   90,   90,  103,   90,  145,   90,  174,  148,  175,  176,
   41,  150,  154,   26,  156,  158,   49,   90,   90,   84,
   90,   84,   77,   84,   95,  113,   81,   50,   26,   96,
  114,   20,   74,  163,  164,  165,  167,   84,   84,   82,
   84,   82,   41,   82,   63,   13,  115,  104,   14,  107,
   91,   64,   92,   15,  168,   27,  119,   82,   82,   83,
   82,   83,  178,   83,  179,  185,  120,  181,  186,  116,
   12,   13,  131,  132,   14,  121,   83,   83,   83,   15,
   83,   16,  122,  127,  128,  129,  130,  126,  123,  133,
  134,  143,   82,   13,  135,  136,   14,   51,    3,  137,
   79,   15,  144,   27,  138,   54,   20,   52,  170,  171,
  141,  142,   78,  147,   53,   54,   54,  152,  159,  153,
  155,   67,   13,  160,   80,   14,   31,   54,   68,   20,
   15,  161,   27,  162,   80,  169,   81,   32,   63,   13,
  172,  173,   14,  110,    1,   39,   39,   15,   66,   27,
   76,    2,   40,   40,   36,   36,   63,   13,   36,  184,
   14,   36,   77,   47,   70,   15,  182,   27,   87,   88,
   89,   90,   72,   36,  149,  183,   35,   35,   35,   35,
   35,   35,   35,   35,   35,   35,   26,   35,   29,   34,
   13,   26,   26,   39,   26,   13,   13,   26,   13,   18,
   40,   35,   63,   13,   18,   18,   14,   18,  151,   28,
    0,   15,    0,   27,   90,   90,   90,   90,   90,   36,
   90,   90,    0,   90,    0,   90,    0,   90,   90,   90,
   90,    0,   39,   76,   84,   84,   84,   84,   84,   40,
   84,   84,    0,   84,    0,   84,    0,   84,   84,   84,
   84,    0,    0,  106,   82,   82,   82,   82,   82,    0,
   82,   82,    0,   82,   39,   82,    0,   82,   82,   82,
   82,   40,    0,    0,   83,   83,   83,   83,   83,    0,
   83,   83,    0,   83,    0,   83,    0,   83,   83,   83,
   83,   82,   13,    1,    0,   14,    0,    3,    2,    3,
   15,    4,   27,   82,   13,    0,    0,   14,    0,    3,
    0,    0,   15,    0,   27,   79,   79,    0,    0,   79,
    0,   79,    0,    0,   79,    0,   79,   78,   78,    0,
    0,   78,    0,   78,    0,    0,   78,    0,   78,   80,
   80,    0,    0,   80,    0,   80,    0,    0,   80,    0,
   80,   81,   81,    0,    0,   81,    0,   81,    0,    0,
   81,    0,   81,    0,    0,   76,   76,    0,    0,   76,
    0,   76,    0,    0,   76,    0,   76,   77,   77,    0,
    0,   77,    0,   77,    0,    0,   77,    0,   77,   62,
   62,   62,   62,   62,    0,    0,   62,    0,   62,    0,
   62,   75,   75,   75,   75,   75,    0,    0,   75,    0,
   75,    0,   75,   82,   13,    0,    0,   14,    0,    3,
    0,    0,   15,    0,   27,  157,   13,    0,    0,   14,
    0,    3,    0,    0,   15,    0,   27,  180,   13,    0,
    0,   14,    0,    3,    0,    0,   15,    0,   27,   26,
   13,    0,    0,   14,    0,    0,    0,    0,   15,    0,
   27,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   40,   40,   45,   45,   40,   95,   91,   40,   59,
    0,   44,   91,   91,    0,   40,   95,   95,   44,   37,
   40,   44,   43,    0,   45,   44,   93,   41,   95,   44,
    1,   41,    3,   91,    0,   44,   59,   55,  274,   60,
    6,   62,   44,   59,   59,   59,  256,  256,   19,   59,
   59,   45,   91,   81,   44,   83,  266,   28,  266,  268,
   93,   44,   43,  273,   45,  267,   40,   93,   43,   59,
   45,   43,  256,   45,   93,   91,   59,  105,   59,   41,
   42,   43,  266,   45,   59,   47,  256,   59,  258,  259,
   45,   93,  120,   44,  122,  123,  256,   59,   60,   41,
   62,   43,   38,   45,   42,  267,   42,  267,   59,   47,
  267,   82,   36,  141,  142,  143,  144,   59,   60,   41,
   62,   43,   45,   45,  256,  257,   59,   51,  260,   53,
   43,  263,   45,  265,  152,  267,   41,   59,   60,   41,
   62,   43,  170,   45,  172,  256,   41,  175,  259,   59,
  256,  257,   91,   92,  260,  274,   41,   59,   60,  265,
   62,  267,   41,   87,   88,   89,   90,  259,   41,   93,
   94,  256,  256,  257,   95,   96,  260,  256,  262,   59,
   41,  265,  267,  267,   41,  274,  157,  266,  258,  259,
  267,  267,   41,   59,  273,  274,  274,   91,  259,   59,
   93,  256,  257,  259,   41,  260,  256,  274,  263,  180,
  265,   59,  267,   59,  256,  259,   41,  267,  256,  257,
  258,  259,  260,  256,    0,  267,  267,  265,  263,  267,
   41,    0,  274,  274,  273,  273,  256,  257,  273,  259,
  260,  273,   41,  268,  267,  265,  259,  267,  269,  270,
  271,  272,  267,  273,  256,  259,  256,  257,  258,  259,
  260,  261,  262,  263,  264,  265,  256,  267,    6,   11,
  256,  261,  262,  267,  264,  261,  262,  267,  264,  256,
  274,   11,  256,  257,  261,  262,  260,  264,  112,    3,
   -1,  265,   -1,  267,  256,  257,  258,  259,  260,  273,
  262,  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,
  272,   -1,  267,  268,  256,  257,  258,  259,  260,  274,
  262,  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,
  272,   -1,   -1,  256,  256,  257,  258,  259,  260,   -1,
  262,  263,   -1,  265,  267,  267,   -1,  269,  270,  271,
  272,  274,   -1,   -1,  256,  257,  258,  259,  260,   -1,
  262,  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,
  272,  256,  257,  256,   -1,  260,   -1,  262,  261,  262,
  265,  264,  267,  256,  257,   -1,   -1,  260,   -1,  262,
   -1,   -1,  265,   -1,  267,  256,  257,   -1,   -1,  260,
   -1,  262,   -1,   -1,  265,   -1,  267,  256,  257,   -1,
   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,  256,
  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,
  267,  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,
  265,   -1,  267,   -1,   -1,  256,  257,   -1,   -1,  260,
   -1,  262,   -1,   -1,  265,   -1,  267,  256,  257,   -1,
   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,  256,
  257,  258,  259,  260,   -1,   -1,  263,   -1,  265,   -1,
  267,  256,  257,  258,  259,  260,   -1,   -1,  263,   -1,
  265,   -1,  267,  256,  257,   -1,   -1,  260,   -1,  262,
   -1,   -1,  265,   -1,  267,  256,  257,   -1,   -1,  260,
   -1,  262,   -1,   -1,  265,   -1,  267,  256,  257,   -1,
   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,  256,
  257,   -1,   -1,  260,   -1,   -1,   -1,   -1,  265,   -1,
  267,
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
"END","LONG","FOREACH","IN","ID","STRING_CONST","GREATER_EQUAL","LESS_EQUAL",
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
"colection_declaration_statement : type colection_list ';'",
"colection_declaration_statement : error colection_list ';'",
"colection_declaration_statement : type ';'",
"colection_declaration_statement : type colection_list ID ';'",
"colection_declaration_statement : type colection_list",
"colection_list : ID '[' index_list ']'",
"colection_list : colection_list ',' ID '[' index_list ']'",
"colection_list : error '[' index_list ']'",
"colection_list : ID index_list error",
"colection_list : ID '[' index_list error",
"colection_list : ID index_list ']'",
"colection_list : ID '[' ']'",
"var_list : ID",
"var_list : var_list ',' ID",
"index_list : index",
"index_list : index_list ',' index",
"index : '_'",
"index : NUMERIC_CONST",
"block_statements : BEGIN statement_block END",
"block_statements : error statement_block END",
"block_statements : BEGIN error END",
"block_statements : BEGIN statement_block error",
"statement_block : executional_statements",
"statement_block : statement_block executional_statements",
"executional_statements : if_statement",
"executional_statements : assign_statement",
"executional_statements : foreach_in_statement",
"executional_statements : print_statement",
"if_statement : IF '(' condition ')' executional_block END_IF",
"if_statement : if_else_statement",
"if_statement : IF condition ')' executional_block END_IF",
"if_statement : IF '(' condition executional_block END_IF",
"if_statement : IF condition executional_block END_IF",
"if_statement : IF '(' error ')' executional_block END_IF",
"if_statement : error '(' condition ')' executional_block END_IF",
"if_statement : IF '(' condition ')' error END_IF",
"if_statement : IF '(' condition ')' executional_block error",
"if_else_statement : IF '(' condition ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' condition ')' executional_block ELSE error END_IF",
"if_else_statement : IF '(' error ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' condition ')' error ELSE executional_block END_IF",
"if_else_statement : IF '(' condition ')' executional_block ELSE executional_block error",
"executional_block : executional_statements",
"executional_block : block_statements",
"assign_statement : ID ASSIGN expression ';'",
"assign_statement : error ASSIGN expression ';'",
"assign_statement : ID error expression ';'",
"assign_statement : ID ASSIGN error ';'",
"assign_statement : ID ASSIGN expression",
"foreach_in_statement : FOREACH ID IN ID executional_block",
"foreach_in_statement : ID IN ID executional_block",
"foreach_in_statement : FOREACH error IN ID executional_block",
"foreach_in_statement : FOREACH ID error ID executional_block",
"foreach_in_statement : FOREACH ID IN error executional_block",
"foreach_in_statement : FOREACH ID IN ID '['",
"print_statement : PRINT '(' STRING_CONST ')' ';'",
"print_statement : error '(' STRING_CONST ')' ';'",
"print_statement : PRINT STRING_CONST ')' ';'",
"print_statement : PRINT '(' STRING_CONST ';'",
"print_statement : PRINT STRING_CONST ';'",
"print_statement : PRINT '(' error ')' ';'",
"print_statement : PRINT '(' STRING_CONST ')'",
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
"factor : ID '[' NUMERIC_CONST ']'",
};

//#line 156 "grammar.y"

 	// Constants declared to print error messages
 	private static final String ERROR_IDENTIFIER = ": identifier expected."; 
 	private static final String ERROR_COLECTION_ID = ": colection identifier expected."; 
 	private static final String ERROR_INDEX = ": colection index expected.";
 	private static final String ERROR_TYPE = ": type expected.";
 	private static final String ERROR_SEMICOLON = ": ';' expected.";
 	private static final String ERROR_COMMA = ": ',' expected."; 
 	private static final String ERROR_SQUARE_BRACKET = " '['']' expected.";
 	private static final String ERROR_SQUARE_BRACKET_OPEN = " '[' expected.";
 	private static final String ERROR_SQUARE_BRACKET_CLOSE = " ']' expected.";
 	private static final String ERROR_BRACKET_OPEN = ": '(' expected.";
 	private static final String ERROR_BRACKET_CLOSE = ": ')' expected.";
 	private static final String ERROR_BRACKET = ": '('')' expected.";
 	private static final String ERROR_BEGIN = ": begin expected."; 
 	private static final String ERROR_END = ": end expected.";
 	private static final String ERROR_IF = ": if expected."; 
 	private static final String ERROR_END_IF = ": end_if expected.";
 	private static final String ERROR_FOR = ": for expected.";
 	private static final String ERROR_IN = ": in expected.";
 	private static final String ERROR_COLECTION = ": error in colection declaration statement."; 
 	private static final String ERROR_EXE_STATEMENT = ": executional statement was expected."; 
 	private static final String ERROR_STATEMENT = ": executional or declarative statement was expected."; 
 	private static final String ERROR_CONDITION = ": condition expected."; 
 	private static final String ERROR_PRINT = ": print expected."; 
 	private static final String ERROR_STRING = ": string expected."; 
 	private static final String ERROR_ASSIGN = ": ':=' expected."; 
 	private static final String ERROR_EXPRESSION = ": expression expected."; 

 	private int error_counter;

    private LexerAnalyzer lexer;


	public Parser(LexerAnalyzer lexer) {
        this.lexer = lexer;
        error_counter = 0;
        //yydebug=true; // uncomment this line to show debug en console
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
//#line 516 "Parser.java"
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
{yyerror(ERROR_COLECTION_ID);}
break;
case 17:
//#line 40 "grammar.y"
{yyerror(ERROR_COMMA);}
break;
case 18:
//#line 41 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 21:
//#line 46 "grammar.y"
{yyerror(ERROR_COLECTION_ID);}
break;
case 22:
//#line 47 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET);}
break;
case 23:
//#line 48 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
break;
case 24:
//#line 49 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_OPEN);}
break;
case 25:
//#line 50 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 33:
//#line 66 "grammar.y"
{yyerror(ERROR_BEGIN);}
break;
case 34:
//#line 67 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 35:
//#line 68 "grammar.y"
{yyerror(ERROR_END);}
break;
case 42:
//#line 81 "grammar.y"
{System.out.println("IF statement at line " + lexer.getLine() + ".");}
break;
case 44:
//#line 83 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 45:
//#line 84 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 46:
//#line 85 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 47:
//#line 86 "grammar.y"
{yyerror(ERROR_CONDITION);}
break;
case 48:
//#line 87 "grammar.y"
{yyerror(ERROR_IF);}
break;
case 49:
//#line 88 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 50:
//#line 89 "grammar.y"
{yyerror(ERROR_END_IF);}
break;
case 51:
//#line 92 "grammar.y"
{System.out.println("IF-ELSE statement at line " + lexer.getLine() + ".");}
break;
case 52:
//#line 93 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 53:
//#line 94 "grammar.y"
{yyerror(ERROR_CONDITION);}
break;
case 54:
//#line 95 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 55:
//#line 96 "grammar.y"
{yyerror(ERROR_END_IF);}
break;
case 58:
//#line 103 "grammar.y"
{System.out.println("Assign statement at line " + lexer.getLine() + ".");}
break;
case 59:
//#line 104 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 60:
//#line 105 "grammar.y"
{yyerror(ERROR_ASSIGN);}
break;
case 61:
//#line 106 "grammar.y"
{yyerror(ERROR_EXPRESSION);}
break;
case 62:
//#line 107 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 63:
//#line 110 "grammar.y"
{System.out.println("FOREACH statement at line " + lexer.getLine() + ".");}
break;
case 64:
//#line 111 "grammar.y"
{yyerror(ERROR_FOR);}
break;
case 65:
//#line 112 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 66:
//#line 113 "grammar.y"
{yyerror(ERROR_IN);}
break;
case 67:
//#line 114 "grammar.y"
{yyerror(ERROR_COLECTION_ID);}
break;
case 68:
//#line 115 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 69:
//#line 118 "grammar.y"
{System.out.println("Print statement at line " + lexer.getLine() + ".");}
break;
case 70:
//#line 119 "grammar.y"
{yyerror(ERROR_PRINT);}
break;
case 71:
//#line 120 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 72:
//#line 121 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 73:
//#line 122 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 74:
//#line 123 "grammar.y"
{yyerror(ERROR_STRING);}
break;
case 75:
//#line 124 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
//#line 865 "Parser.java"
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
