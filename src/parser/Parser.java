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
public final static short INT_CONST=274;
public final static short LONG_CONST=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    1,    1,    1,    2,
    2,    4,    4,    5,    5,    5,    5,    6,    6,    6,
    6,    6,    6,    6,    6,    6,    8,    8,    8,    8,
    9,    9,    9,   10,   10,    3,    3,    3,    3,    3,
    3,   11,   11,   12,   12,   12,   12,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   19,   19,   19,   19,   18,   18,   18,   14,
   14,   14,   14,   14,   15,   15,   15,   15,   15,   15,
   16,   16,   16,   16,   16,   16,   16,   17,   17,   17,
   17,   17,   17,   20,   20,   20,   21,   21,   21,    7,
    7,   22,   22,   22,   22,   22,   22,   22,
};
final static short yylen[] = {                            2,
    0,    1,    1,    3,    3,    2,    2,    1,    2,    1,
    2,    1,    1,    3,    3,    3,    2,    6,    6,    6,
    5,    5,    6,    6,    4,    6,    1,    3,    2,    2,
    1,    3,    2,    1,    1,    3,    3,    3,    3,    3,
    2,    1,    2,    1,    1,    1,    1,    7,    8,    6,
    5,    5,    6,    4,    6,    5,    6,    6,    5,    7,
    6,    6,    7,    5,    7,    6,    7,    7,    6,    6,
    7,    8,    2,    2,    1,    3,    1,    2,    1,    4,
    4,    4,    4,    3,    5,    4,    5,    5,    4,    5,
    5,    5,    4,    4,    3,    4,    4,    3,    3,    3,
    3,    3,    3,    3,    3,    1,    3,    3,    1,    1,
    1,    1,    1,    1,    2,    2,    4,    5,
};
final static short yydefred[] = {                         0,
    0,  110,    0,  111,    0,    2,    0,    0,   10,   12,
   13,    0,    0,    0,    0,   41,    0,    0,    0,    0,
    0,   42,   44,   45,   46,   47,    0,    0,    0,    0,
   11,    9,    7,    0,    0,    0,    0,    0,    0,    0,
  113,  114,    0,    0,    0,    0,    0,  109,    0,    0,
    0,    0,    0,    0,    0,    0,   30,   29,   15,    0,
    0,   37,   43,   38,    0,   36,    5,    4,   16,    0,
   35,    0,   34,    0,   31,   14,    0,    0,    0,    0,
    0,  115,  116,    0,    0,    0,    0,    0,    0,   77,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   95,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   28,    0,    0,    0,    0,   25,
    0,    0,   33,   81,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   78,    0,   54,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  107,  108,   93,
   94,    0,   96,   89,    0,    0,    0,    0,   82,   86,
   83,   80,    0,    0,    0,   22,    0,    0,   21,   32,
   92,    0,    0,  117,    0,    0,   56,    0,    0,    0,
    0,   59,    0,    0,   52,    0,   51,    0,    0,    0,
   64,   91,   90,   88,   85,   87,   19,   20,   23,   24,
   26,   18,   57,    0,   50,    0,  118,   55,    0,   66,
   53,    0,   58,    0,   70,    0,    0,   69,   62,   61,
   76,   67,   60,   65,   63,   68,   71,   48,    0,   72,
   49,
};
final static short yydgoto[] = {                          5,
    6,    7,   89,    9,   10,   11,   12,   20,   74,   75,
   21,   90,   23,   24,   25,   26,   45,   91,  139,   46,
   47,   48,
};
final static short yysindex[] = {                       -65,
  280,    0, -163,    0,    0,    0,  -65,  -55,    0,    0,
    0,   86,  -29,  -40,   -3,    0, -116,  -71, -237,  -16,
  337,    0,    0,    0,    0,    0,  -30, -225,  349,  -53,
    0,    0,    0,  -42,  -76,   17,  -21,  -43,  -21,  -52,
    0,    0, -120,  -38,  141,  124,   35,    0,  -25,   -1,
 -222, -198,  125, -208,   77,  -86,    0,    0,    0, -194,
  -29,    0,    0,    0,  -29,    0,    0,    0,    0,  -86,
    0,  -66,    0,  -26,    0,    0,   -7,   44,   47,   55,
  -22,    0,    0,   57,  361,  153,   46,  361,  -17,    0,
  -94,  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,  -21,
  -21,    0,   51,  -15,   65,  361, -161, -221, -151,   11,
  361,   74,   12,  -12,    0,   19,   42,   97,   18,    0,
  103,  -86,    0,    0,  107,  361,  361,   75, -103,  361,
  -46,   34,  309,  -44,  -37,    0,  373,    0,  -72,    8,
    8,    8,    8,   35,   35,    8,    8,    0,    0,    0,
    0,  130,    0,    0,  361,  361,  361,  361,    0,    0,
    0,    0,  145,  148,  151,    0,  161,  -51,    0,    0,
    0,   73,  113,    0,  133,  158,    0,  -59,    0,  170,
  -18,    0, -114,  -36,    0,  -31,    0,   41,   46,  361,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   53,    0,   63,    0,    0,   67,    0,
    0,   80,    0,   87,    0,  -47,  100,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -45,    0,
    0,
};
final static short yyrindex[] = {                       217,
    0,    0,    0,    0,    0,    0,  230,  256,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   31,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  310,
    0,    0,    0,    0,   21,   25,    0,    0,    0,   58,
    0,    0,    0,    0,    0,    0,   78,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    0,    0,   13,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -131,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  191,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  105,    0,    0,  165,
  178,  209,  238,   98,  118,  254,  267,    0,    0,    0,
    0,  295,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -84,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  114,  120,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,  108,  355,    0,    0,    0,  354,   -6,  -27,
  379,  383,    0,    0,    0,    0,   40,  351,  415,   54,
   96,  204,
};
final static int YYTABLESIZE=640;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         44,
   40,   43,   85,   33,   43,   68,   43,  202,   73,   38,
   38,  228,   39,  231,   72,  103,   69,  122,   73,   56,
   27,   38,  129,   43,   17,  152,  118,   60,   73,   57,
   53,  122,  120,  102,  156,   96,   50,   97,   81,  105,
   54,  136,   59,  151,  106,  157,  123,   55,   70,  114,
   96,  124,   97,   96,   96,   97,   97,  107,  111,   40,
   60,  122,  122,  116,   27,  119,  121,  108,   73,  159,
  162,   39,  115,   38,   27,   76,  100,   79,   80,   27,
  163,  101,   73,   86,  125,   38,  123,  126,  123,   27,
   77,  123,   27,   14,  170,  127,   15,  130,  112,  112,
  112,   17,  112,   28,  112,  155,  110,    8,  113,  150,
  168,  164,   73,   73,   30,  158,  112,  112,  106,  112,
  106,   43,  106,  153,   79,   79,   79,   79,   79,   19,
   79,   79,  161,   79,  165,   79,  106,  106,  104,  106,
  104,  215,  104,  137,  216,  140,  141,  142,  143,   51,
   52,  146,  147,   82,   83,  166,  104,  104,  105,  104,
  105,  169,  105,  137,  138,  171,   96,  174,   97,   43,
  175,   42,   42,   77,   77,   42,  105,  105,   42,  105,
   42,   88,   42,   98,   53,   99,  191,   71,  192,  117,
    1,  144,  145,  133,   54,    2,    3,   71,    4,  210,
   32,   55,   67,  197,  201,  101,  198,   71,  227,  199,
  230,  137,  177,  137,  185,   39,    1,   84,  100,  200,
  137,  187,  218,   40,   78,  207,   40,  219,   40,    3,
   41,   42,   64,   41,   42,   41,   42,   13,   14,  137,
  213,   15,   37,   37,   16,   40,   17,   71,   28,  102,
   58,  128,   41,   42,   37,    8,   40,   40,   40,   40,
   40,   71,   40,   40,   49,   40,  104,   40,   39,   39,
   39,   39,   39,  167,   39,   39,   27,   39,  103,   39,
   17,   27,   27,   58,   27,   17,   17,   27,   17,   87,
   14,   71,   71,   15,   98,    3,   16,   27,   17,  220,
   28,   13,   14,  148,  149,   15,   37,   99,   16,    6,
   17,  222,   28,  112,  112,  112,  112,  112,   37,  112,
  112,  223,  112,   19,  112,  224,  112,  112,  112,  112,
  137,  203,  112,  106,  106,  106,  106,  106,  225,  106,
  106,   34,  106,   40,  106,  226,  106,  106,  106,  106,
   41,   42,   35,  104,  104,  104,  104,  104,  229,  104,
  104,   31,  104,   75,  104,   36,  104,  104,  104,  104,
  137,  205,   74,  105,  105,  105,  105,  105,   73,  105,
  105,   29,  105,   22,  105,   22,  105,  105,  105,  105,
  109,   40,   92,   93,   94,   95,   87,   14,   41,   42,
   15,    0,    3,   63,    0,   17,    0,   28,  132,   14,
    0,   63,   15,    0,    3,  137,  208,   17,    0,   28,
  101,  101,    0,    0,  101,    0,  101,  137,  211,  101,
    0,  101,    0,  100,  100,  131,  134,  100,  135,  100,
    0,    0,  100,    0,  100,    0,   84,   84,   84,   84,
   84,    0,   84,   84,    0,   84,  154,   84,    0,    0,
    0,  160,    0,    0,  102,  102,    0,    0,  102,   22,
  102,    0,    0,  102,    0,  102,  172,  173,    0,    0,
  176,    0,  180,  183,    0,    0,    0,  190,    0,    0,
    0,    0,    0,  103,  103,    0,    0,  103,    0,  103,
    0,    0,  103,    0,  103,  193,  194,  195,  196,   98,
   98,    0,    0,   98,  179,   98,    0,    0,   98,    0,
   98,    0,   99,   99,    0,    0,   99,    0,   99,    0,
    0,   99,    0,   99,    0,   13,   14,    0,    0,   15,
  221,    0,   16,    0,   17,  178,   18,  184,  186,  188,
   97,   97,   97,   97,   97,    0,   97,   97,    0,   97,
    0,   97,    0,   22,  181,   14,  137,  182,   15,    0,
    3,   22,    0,   17,    0,   28,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  204,  206,    0,    0,
  209,    0,   61,   14,  212,  214,   15,  217,    0,   62,
    0,   17,    0,   28,   65,   14,    0,    0,   15,    0,
    0,   66,    0,   17,    0,   28,   87,   14,    0,    0,
   15,    0,    3,    0,    0,   17,    0,   28,  189,   14,
    0,    0,   15,    0,    3,    0,    0,   17,    0,   28,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   45,   41,   59,   45,   59,   45,   59,   95,   40,
   40,   59,    0,   59,   91,   41,   59,   44,   95,   91,
    0,   40,   45,   45,    0,   41,   93,   44,   95,  267,
  256,   44,   59,   59,  256,   43,   40,   45,   91,   41,
  266,   59,   59,   59,  267,  267,   74,  273,   91,   56,
   43,   59,   45,   43,   43,   45,   45,  256,  267,   59,
   44,   44,   44,   70,   44,   72,   93,  266,   95,   59,
   59,   59,  267,   40,   44,   59,   42,   38,   39,   59,
   93,   47,   95,   44,   41,   40,  114,   41,  116,   59,
   37,  119,  256,  257,  122,   41,  260,   41,   41,   42,
   43,  265,   45,  267,   47,  267,   53,    0,   55,   59,
   93,   93,   95,   95,    7,  267,   59,   60,   41,   62,
   43,   45,   45,   59,  256,  257,  258,  259,  260,   44,
  262,  263,   59,  265,   93,  267,   59,   60,   41,   62,
   43,  256,   45,  258,  259,   92,   93,   94,   95,  266,
  267,   98,   99,  274,  275,   59,   59,   60,   41,   62,
   43,   59,   45,  258,  259,   59,   43,   93,   45,   45,
  274,  256,  257,  258,  259,  260,   59,   60,  263,   62,
  265,   41,  267,   60,  256,   62,  259,  274,   59,  256,
  256,   96,   97,   41,  266,  261,  262,  274,  264,  259,
  256,  273,  256,   59,  256,   41,   59,  274,  256,   59,
  256,  258,  259,  258,  259,  256,    0,  256,   41,   59,
  258,  259,  259,  267,  268,   93,  267,  259,  267,    0,
  274,  275,  263,  274,  275,  274,  275,  256,  257,  258,
  259,  260,  273,  273,  263,  267,  265,  274,  267,   41,
  267,  274,  274,  275,  273,    0,  256,  257,  258,  259,
  260,  274,  262,  263,  268,  265,  268,  267,  256,  257,
  258,  259,  260,  256,  262,  263,  256,  265,   41,  267,
  256,  261,  262,  267,  264,  261,  262,  267,  264,  256,
  257,  274,  274,  260,   41,  262,  263,  267,  265,  259,
  267,  256,  257,  100,  101,  260,  273,   41,  263,    0,
  265,  259,  267,  256,  257,  258,  259,  260,  273,  262,
  263,  259,  265,   44,  267,  259,  269,  270,  271,  272,
  258,  259,  256,  256,  257,  258,  259,  260,  259,  262,
  263,  256,  265,  267,  267,  259,  269,  270,  271,  272,
  274,  275,  267,  256,  257,  258,  259,  260,  259,  262,
  263,    7,  265,  259,  267,   12,  269,  270,  271,  272,
  258,  259,  259,  256,  257,  258,  259,  260,  259,  262,
  263,    3,  265,    1,  267,    3,  269,  270,  271,  272,
  266,  267,  269,  270,  271,  272,  256,  257,  274,  275,
  260,   -1,  262,   21,   -1,  265,   -1,  267,  256,  257,
   -1,   29,  260,   -1,  262,  258,  259,  265,   -1,  267,
  256,  257,   -1,   -1,  260,   -1,  262,  258,  259,  265,
   -1,  267,   -1,  256,  257,   85,   86,  260,   88,  262,
   -1,   -1,  265,   -1,  267,   -1,  256,  257,  258,  259,
  260,   -1,  262,  263,   -1,  265,  106,  267,   -1,   -1,
   -1,  111,   -1,   -1,  256,  257,   -1,   -1,  260,   87,
  262,   -1,   -1,  265,   -1,  267,  126,  127,   -1,   -1,
  130,   -1,  132,  133,   -1,   -1,   -1,  137,   -1,   -1,
   -1,   -1,   -1,  256,  257,   -1,   -1,  260,   -1,  262,
   -1,   -1,  265,   -1,  267,  155,  156,  157,  158,  256,
  257,   -1,   -1,  260,  132,  262,   -1,   -1,  265,   -1,
  267,   -1,  256,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,   -1,  256,  257,   -1,   -1,  260,
  190,   -1,  263,   -1,  265,  131,  267,  133,  134,  135,
  256,  257,  258,  259,  260,   -1,  262,  263,   -1,  265,
   -1,  267,   -1,  181,  256,  257,  258,  259,  260,   -1,
  262,  189,   -1,  265,   -1,  267,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  172,  173,   -1,   -1,
  176,   -1,  256,  257,  180,  181,  260,  183,   -1,  263,
   -1,  265,   -1,  267,  256,  257,   -1,   -1,  260,   -1,
   -1,  263,   -1,  265,   -1,  267,  256,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  256,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=275;
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
"EQUAL","DISTINCT","ASSIGN","INT_CONST","LONG_CONST",
};
final static String yyrule[] = {
"$accept : program",
"program :",
"program : program_statements",
"program_statements : statements",
"program_statements : statements block_statements ';'",
"program_statements : statements block_statements error",
"program_statements : statements block_statements",
"program_statements : block_statements ';'",
"program_statements : block_statements",
"program_statements : block_statements error",
"statements : declarative_statements",
"statements : statements declarative_statements",
"declarative_statements : variable_declaration_statement",
"declarative_statements : colection_declaration_statement",
"variable_declaration_statement : type var_list ';'",
"variable_declaration_statement : error var_list ';'",
"variable_declaration_statement : type error ';'",
"variable_declaration_statement : type var_list",
"colection_declaration_statement : type ID '[' index_list ']' ';'",
"colection_declaration_statement : error ID '[' index_list ']' ';'",
"colection_declaration_statement : type error '[' index_list ']' ';'",
"colection_declaration_statement : type ID index_list ']' ';'",
"colection_declaration_statement : type ID '[' ']' ';'",
"colection_declaration_statement : type ID '[' error ']' ';'",
"colection_declaration_statement : type ID '[' index_list error ';'",
"colection_declaration_statement : type ID index_list ';'",
"colection_declaration_statement : type ID '[' index_list ']' error",
"var_list : ID",
"var_list : var_list ',' ID",
"var_list : var_list ID",
"var_list : ',' ID",
"index_list : index",
"index_list : index_list ',' index",
"index_list : index_list index",
"index : '_'",
"index : INT_CONST",
"block_statements : BEGIN statement_block END",
"block_statements : error statement_block END",
"block_statements : BEGIN error END",
"block_statements : BEGIN statement_block error",
"block_statements : error statement_block error",
"block_statements : error END",
"statement_block : executional_statements",
"statement_block : statement_block executional_statements",
"executional_statements : if_statement",
"executional_statements : assign_statement",
"executional_statements : foreach_in_statement",
"executional_statements : print_statement",
"if_statement : IF '(' condition ')' executional_block END_IF ';'",
"if_statement : IF '(' condition ')' executional_block else_statement END_IF ';'",
"if_statement : IF error condition ')' executional_block END_IF",
"if_statement : IF condition ')' executional_block END_IF",
"if_statement : IF '(' condition executional_block END_IF",
"if_statement : IF '(' condition error executional_block END_IF",
"if_statement : IF condition executional_block END_IF",
"if_statement : IF '(' error ')' executional_block END_IF",
"if_statement : IF '(' ')' executional_block END_IF",
"if_statement : error '(' condition ')' executional_block END_IF",
"if_statement : IF '(' condition ')' error END_IF",
"if_statement : IF '(' condition ')' END_IF",
"if_statement : IF error condition ')' executional_block else_statement END_IF",
"if_statement : IF condition ')' executional_block else_statement END_IF",
"if_statement : IF '(' condition executional_block else_statement END_IF",
"if_statement : IF '(' condition error executional_block else_statement END_IF",
"if_statement : IF condition executional_block else_statement END_IF",
"if_statement : IF '(' error ')' executional_block else_statement END_IF",
"if_statement : IF '(' ')' executional_block else_statement END_IF",
"if_statement : error '(' condition ')' executional_block else_statement END_IF",
"if_statement : IF '(' condition ')' error else_statement END_IF",
"if_statement : IF '(' condition ')' else_statement END_IF",
"if_statement : IF '(' condition ')' executional_block error",
"if_statement : IF '(' condition ')' executional_block END_IF error",
"if_statement : IF '(' condition ')' executional_block else_statement END_IF error",
"else_statement : ELSE executional_block",
"else_statement : ELSE error",
"else_statement : ELSE",
"else_statement : ELSE executional_block executional_block",
"executional_block : executional_statements",
"executional_block : block_statements ';'",
"executional_block : block_statements",
"assign_statement : ID ASSIGN expression ';'",
"assign_statement : error ASSIGN expression ';'",
"assign_statement : ID error expression ';'",
"assign_statement : ID ASSIGN error ';'",
"assign_statement : ID ASSIGN expression",
"foreach_in_statement : FOREACH ID IN ID executional_block",
"foreach_in_statement : ID IN ID executional_block",
"foreach_in_statement : ID error IN ID executional_block",
"foreach_in_statement : FOREACH ID IN error executional_block",
"foreach_in_statement : FOREACH IN ID executional_block",
"foreach_in_statement : FOREACH ID error ID executional_block",
"print_statement : PRINT '(' STRING_CONST ')' ';'",
"print_statement : error '(' STRING_CONST ')' ';'",
"print_statement : PRINT STRING_CONST ')' ';'",
"print_statement : PRINT '(' STRING_CONST ';'",
"print_statement : PRINT STRING_CONST ';'",
"print_statement : PRINT '(' ')' ';'",
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
"factor : INT_CONST",
"factor : LONG_CONST",
"factor : '-' INT_CONST",
"factor : '-' LONG_CONST",
"factor : ID '[' INT_CONST ']'",
"factor : ID '[' '-' INT_CONST ']'",
};

//#line 181 "grammar.y"

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
 	private static final String ERROR_ELSE = ": else expected."; 
 	private static final String ERROR_END_IF = ": end_if expected.";
 	private static final String ERROR_FOR = ": foreach expected.";
 	private static final String ERROR_IN = ": in expected."; 
 	private static final String ERROR_EXE_STATEMENT = ": executional statement was expected.";   
 	private static final String ERROR_CONDITION = ": condition expected."; 
 	private static final String ERROR_PRINT = ": print expected."; 
 	private static final String ERROR_STRING = ": string expected."; 
 	private static final String ERROR_ASSIGN = ": ':=' expected."; 
 	private static final String ERROR_EXPRESSION = ": expression expected."; 
 	private static final String ERROR_CONST = ": constant out of range.";

 	private int error_counter;

    private LexerAnalyzer lexer;


    // constants to define the numbers of bits to represent an integer const or a ulong const
    private static final int MAX_BITS_INT = 15;
    private static final int MAX_BITS_LONG = 31;

	public Parser(LexerAnalyzer lexer) {
        this.lexer = lexer;
        error_counter = 0;
        //yydebug=true; // uncomment this line to show debug en console
	}

    private int yylex() {
        
        Token token = this.lexer.getToken(); 
        if (token != null) { 
            this.yylval = new ParserVal(token.getLexeme()); 
            this.yylval.begin_line = lexer.getLine();
            this.yylval.end_line = lexer.getLine();
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

    private void yyerror(String parser_error, int line) {
    	if (!parser_error.equalsIgnoreCase("syntax error") ) { // ignore default parser error
    		System.out.println("    Syntax error near line " + line + parser_error);
    		error_counter++;
    	}
    }

    public int getError() {
    	return error_counter;
    }

    // Check if a constants is out of range
    public void checkRange(String myConst, int rangeBits) { 
        long constant = Long.parseLong(myConst); 
        if (constant < -Math.pow(2, rangeBits) || constant > Math.pow(2, rangeBits) - 1) {
            yyerror(ERROR_CONST, lexer.getLine());
        }
        if (constant < 0) {  
            Token t = SymbolTable.getInstance().getSymbol(myConst.substring(1));
            SymbolTable.getInstance().remove(myConst.substring(1));
            SymbolTable.getInstance().addSymbol(new Token(t.getID(), myConst, "negative " + t.getDescription()));
        }
    }
//#line 600 "Parser.java"
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
case 5:
//#line 22 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 6:
//#line 23 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 8:
//#line 25 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 9:
//#line 26 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 14:
//#line 37 "grammar.y"
{System.out.println("Variable declared at line " + val_peek(0).end_line + ".");}
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
//#line 43 "grammar.y"
{System.out.println("Colection declared at line " + val_peek(0).end_line + ".");}
break;
case 19:
//#line 44 "grammar.y"
{yyerror(ERROR_TYPE);}
break;
case 20:
//#line 45 "grammar.y"
{yyerror(ERROR_COLECTION_ID);}
break;
case 21:
//#line 46 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_OPEN);}
break;
case 22:
//#line 47 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 23:
//#line 48 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 24:
//#line 49 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
break;
case 25:
//#line 50 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET);}
break;
case 26:
//#line 51 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 29:
//#line 56 "grammar.y"
{yyerror(ERROR_COMMA, val_peek(1).begin_line);}
break;
case 30:
//#line 57 "grammar.y"
{yyerror(ERROR_IDENTIFIER, val_peek(1).begin_line);}
break;
case 33:
//#line 62 "grammar.y"
{yyerror(ERROR_COMMA, val_peek(1).begin_line);}
break;
case 37:
//#line 70 "grammar.y"
{yyerror(ERROR_BEGIN);}
break;
case 38:
//#line 71 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 39:
//#line 72 "grammar.y"
{yyerror(ERROR_END);}
break;
case 40:
//#line 73 "grammar.y"
{yyerror(ERROR_BEGIN); yyerror(ERROR_END);}
break;
case 41:
//#line 74 "grammar.y"
{yyerror(ERROR_BEGIN, val_peek(1).end_line);}
break;
case 48:
//#line 87 "grammar.y"
{System.out.println("IF statement at line " + val_peek(6).end_line + ".");}
break;
case 49:
//#line 88 "grammar.y"
{System.out.println("IF-ELSE statement at line " + val_peek(7).end_line + ".");}
break;
case 50:
//#line 89 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(3).begin_line);}
break;
case 51:
//#line 90 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(2).begin_line);}
break;
case 52:
//#line 91 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(3).begin_line);}
break;
case 53:
//#line 92 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(4).begin_line);}
break;
case 54:
//#line 93 "grammar.y"
{yyerror(ERROR_BRACKET, val_peek(2).begin_line);}
break;
case 55:
//#line 94 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(5).begin_line);}
break;
case 56:
//#line 95 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(4).begin_line);}
break;
case 57:
//#line 96 "grammar.y"
{yyerror(ERROR_IF, val_peek(4).begin_line);}
break;
case 58:
//#line 97 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(2).begin_line);}
break;
case 59:
//#line 98 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(1).begin_line);}
break;
case 60:
//#line 99 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(4).begin_line);}
break;
case 61:
//#line 100 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(3).begin_line);}
break;
case 62:
//#line 101 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(4).begin_line);}
break;
case 63:
//#line 102 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(5).begin_line);}
break;
case 64:
//#line 103 "grammar.y"
{yyerror(ERROR_BRACKET, val_peek(3).begin_line);}
break;
case 65:
//#line 104 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(6).begin_line);}
break;
case 66:
//#line 105 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(5).begin_line);}
break;
case 67:
//#line 106 "grammar.y"
{yyerror(ERROR_IF, val_peek(5).begin_line);}
break;
case 68:
//#line 107 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(3).begin_line);}
break;
case 69:
//#line 108 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(2).begin_line);}
break;
case 70:
//#line 109 "grammar.y"
{yyerror(ERROR_END_IF, val_peek(2).begin_line);}
break;
case 71:
//#line 110 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 72:
//#line 111 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 74:
//#line 115 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(1).begin_line);}
break;
case 75:
//#line 116 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(0).begin_line);}
break;
case 76:
//#line 117 "grammar.y"
{yyerror(ERROR_BEGIN, val_peek(3).begin_line);}
break;
case 79:
//#line 122 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 80:
//#line 125 "grammar.y"
{System.out.println("Assign statement at line " + val_peek(3).end_line + ".");}
break;
case 81:
//#line 126 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 82:
//#line 127 "grammar.y"
{yyerror(ERROR_ASSIGN);}
break;
case 83:
//#line 128 "grammar.y"
{yyerror(ERROR_EXPRESSION);}
break;
case 84:
//#line 129 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 85:
//#line 132 "grammar.y"
{System.out.println("FOREACH statement at line " + val_peek(4).end_line  + ".");}
break;
case 86:
//#line 133 "grammar.y"
{yyerror(ERROR_FOR, val_peek(3).begin_line);}
break;
case 87:
//#line 134 "grammar.y"
{yyerror(ERROR_FOR, val_peek(4).begin_line);}
break;
case 88:
//#line 135 "grammar.y"
{yyerror(ERROR_COLECTION_ID, val_peek(3).begin_line);}
break;
case 89:
//#line 136 "grammar.y"
{yyerror(ERROR_IDENTIFIER, val_peek(3).begin_line);}
break;
case 90:
//#line 137 "grammar.y"
{yyerror(ERROR_IN, val_peek(3).begin_line);}
break;
case 91:
//#line 140 "grammar.y"
{System.out.println("Print statement at line " + lexer.getLine() + ".");}
break;
case 92:
//#line 141 "grammar.y"
{yyerror(ERROR_PRINT);}
break;
case 93:
//#line 142 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 94:
//#line 143 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 95:
//#line 144 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 96:
//#line 145 "grammar.y"
{yyerror(ERROR_STRING);}
break;
case 97:
//#line 146 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 113:
//#line 172 "grammar.y"
{checkRange(yyval.sval, MAX_BITS_INT);}
break;
case 114:
//#line 173 "grammar.y"
{checkRange(yyval.sval, MAX_BITS_LONG);}
break;
case 115:
//#line 174 "grammar.y"
{checkRange("-"+val_peek(0).sval, MAX_BITS_INT); }
break;
case 116:
//#line 175 "grammar.y"
{checkRange("-"+val_peek(0).sval, MAX_BITS_LONG);}
break;
case 117:
//#line 176 "grammar.y"
{checkRange(val_peek(1).sval, MAX_BITS_INT);}
break;
case 118:
//#line 177 "grammar.y"
{checkRange(val_peek(2).sval, MAX_BITS_INT);}
break;
//#line 1065 "Parser.java"
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
