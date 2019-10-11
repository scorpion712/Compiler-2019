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
    0,    0,    1,    1,    1,    1,    2,    2,    4,    4,
    4,    4,    4,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    7,    7,    8,    8,    9,    9,    3,    3,
    3,    3,    3,   10,   10,   11,   11,   11,   11,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   17,   17,   13,   13,
   13,   13,   13,   14,   14,   14,   14,   15,   15,   15,
   15,   15,   15,   15,   16,   16,   16,   16,   16,   16,
   19,   19,   19,   20,   20,   20,    6,    6,   21,   21,
   21,   21,   21,   21,   21,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    3,
    3,    4,    2,    6,    6,    6,    5,    5,    6,    6,
    4,    6,    1,    3,    1,    3,    1,    1,    3,    3,
    3,    3,    3,    1,    2,    1,    1,    1,    1,    6,
    1,    6,    5,    5,    6,    4,    6,    5,    6,    6,
    5,    8,    8,    7,    7,    8,    7,    8,    7,    8,
    7,    7,    8,    8,    7,    8,    1,    1,    4,    4,
    4,    4,    3,    5,    4,    5,    5,    5,    5,    4,
    4,    3,    4,    4,    3,    3,    3,    3,    3,    3,
    3,    3,    1,    3,    3,    1,    1,    1,    1,    1,
    1,    2,    2,    4,    5,
};
final static short yydefred[] = {                         0,
    0,   97,    0,   98,    0,    0,    3,    4,    7,    8,
    0,    0,    0,    0,    0,    0,    0,    0,   34,   36,
   37,   38,   39,   41,    0,    0,    0,    5,    6,    0,
    0,    0,    0,    0,    0,    0,  100,  101,    0,    0,
    0,    0,    0,   96,    0,    0,    0,    0,    0,    0,
    0,   10,    0,    0,   30,   35,   31,    0,   29,   11,
    0,   28,    0,   27,    0,   25,    0,    9,    0,    0,
    0,    0,    0,  102,  103,    0,    0,    0,    0,    0,
   68,   67,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   82,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   24,    0,    0,    0,    0,   21,
    0,    0,   12,   70,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   46,    0,    0,    0,    0,
    0,    0,    0,    0,   94,   95,   80,   81,    0,   83,
    0,    0,    0,   71,   75,   72,   69,    0,    0,    0,
   18,    0,    0,   17,   26,   79,    0,    0,  104,    0,
    0,    0,   48,    0,    0,    0,    0,   51,    0,    0,
   44,    0,   43,   78,   77,   74,   76,   15,   16,   19,
   20,   22,   14,   49,    0,   42,  105,    0,   47,    0,
    0,   45,    0,   50,    0,    0,    0,   40,    0,    0,
    0,    0,    0,   54,    0,    0,   59,   61,    0,    0,
   65,    0,   62,   57,   55,   56,   53,   58,   60,   63,
   64,   66,   52,
};
final static short yydgoto[] = {                          5,
    6,    7,   81,    9,   10,   11,   17,   65,   66,   18,
   82,   20,   21,   22,   23,   41,   83,   24,   42,   43,
   44,
};
final static short yysindex[] = {                      -169,
 -137,    0,  372,    0,    0, -169,    0,    0,    0,    0,
 -222,  -32,  -40,  -30, -170,  -74,   -2,  322,    0,    0,
    0,    0,    0,    0,  -31, -226,  335,    0,    0,  -47,
  -80,  -11,  -27,  -24,  -27,    8,    0,    0, -236,  -38,
  166,  148,   21,    0,  -13,  -16, -159,  -21, -157,   56,
  -76,    0, -149,  -32,    0,    0,    0,  -32,    0,    0,
  -76,    0,  -79,    0,  -22,    0,   65,    0,    7,   97,
   98,   99,  -25,    0,    0,  100,  351,  178,   72,  351,
    0,    0, -117,  -27,  -27,  -27,  -27,  -27,  -27,  -27,
  -27,  -27,  -27,    0,   89,  -10,   96, -195, -109,   10,
  351,  101,   11,  -12,    0,   -8,   69,  109,  -42,    0,
  116,  -76,    0,    0,  119,  351,  351,   87,  -84,  351,
 -184,   48,  -86, -144, -142,    0,   19,   19,   19,   19,
   21,   21,   19,   19,    0,    0,    0,    0,  129,    0,
  351,  351,  351,    0,    0,    0,    0,  137,  138,  141,
    0,  143,  -55,    0,    0,    0,  -54, -126,    0,  118,
 -124,  351,    0,    0, -122,  -34,  351,    0,  190,  351,
    0,  351,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  351,    0,    0,  351,    0,  -50,
  351,    0,  351,    0,  -46,   36,  302,    0,  -44,  -39,
  -29,   38,   40,    0,   41,   43,    0,    0,   52,   60,
    0, -165,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yyrindex[] = {                       212,
    0,    0,    0,    0,    0,  217,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   14,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   23,   27,    0,    0,    0,   84,    0,    0,    0,    0,
    0,    0,  104,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,    0,    0,   13,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -154,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  214,  226,  240,  253,
  124,  144,  265,  277,    0,    0,    0,    0, -106,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  202,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  215,   29,    0,    0,    0,  271,   35,  174,  225,
  424,    0,    0,    0,    0,   25,  434,    0,   -7,   33,
   -3,
};
final static int YYTABLESIZE=639;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   33,  112,   77,  183,   39,   34,   39,   34,   34,   46,
   63,   60,   32,  108,   64,   64,   51,   39,   64,  119,
   39,  112,   23,   39,   97,   69,   13,   95,    8,   48,
  139,  112,   53,   30,   29,  112,  110,   74,   75,   49,
  100,   53,  103,   61,   31,   94,   50,   68,  138,   88,
  153,   89,   88,   88,   89,   89,   52,   23,   71,   72,
  141,   88,   92,   89,   78,  114,   23,   93,  144,  147,
  111,  142,   23,  162,  163,   34,  127,  128,  129,  130,
  148,   23,  133,  134,  149,  104,    1,   34,  135,  136,
  222,    2,    3,  223,    4,  106,   47,  109,   73,   34,
   39,   73,   73,   73,   73,   73,   98,   73,   73,  101,
   73,   34,   73,  170,  171,  172,  173,  105,   12,   13,
  131,  132,   14,  113,   99,   99,   99,   15,   99,   16,
   99,  185,  186,  188,  189,  191,  192,  115,  116,  117,
  120,  126,   99,   99,   93,   99,   93,  137,   93,   84,
   84,   84,   84,   84,  140,   84,   84,  143,   84,  146,
   84,  150,   93,   93,   91,   93,   91,  151,   91,  166,
   13,  167,  168,   14,  154,    3,  107,  156,   15,  159,
   26,   48,   91,   91,   92,   91,   92,  174,   92,  160,
   88,   49,   89,   62,   62,  178,  179,   62,   50,  180,
  182,  181,   92,   92,  184,   92,   80,   90,  204,   91,
  187,    1,  207,  152,  213,   35,    2,   76,  123,  214,
   28,   12,   13,  193,  194,   14,   36,   27,   36,  215,
   15,   57,   26,   37,   38,   37,   38,   45,   33,   36,
   33,   33,   36,   70,   99,   36,   37,   38,  118,   37,
   38,   96,   37,   38,   88,   67,   33,   33,   33,   33,
   33,   33,   33,   33,   33,   33,   87,   33,   32,   32,
   32,   32,   32,   32,   32,   32,   32,   32,   23,   32,
   89,   32,   13,   23,   23,  155,   23,   13,   13,   23,
   13,   79,   13,   90,  208,   14,  216,    3,  217,  218,
   15,  219,   26,   79,   13,   85,    0,   14,   33,    3,
  220,  102,   15,    0,   26,   12,   13,   86,  221,   14,
   33,    0,   36,    0,   15,    0,   26,   12,   13,   37,
   38,   14,   33,    0,    0,    0,   15,    0,   26,   99,
   99,   99,   99,   99,   33,   99,   99,    0,   99,    0,
   99,    0,   99,   99,   99,   99,    0,    0,    0,   93,
   93,   93,   93,   93,    0,   93,   93,    0,   93,    0,
   93,    0,   93,   93,   93,   93,    0,    0,    0,   91,
   91,   91,   91,   91,    0,   91,   91,    0,   91,    0,
   91,    0,   91,   91,   91,   91,    0,    0,    0,   92,
   92,   92,   92,   92,    0,   92,   92,    0,   92,    0,
   92,    0,   92,   92,   92,   92,   84,   85,   86,   87,
    0,   79,   13,    0,   19,   14,   19,    3,    0,    0,
   15,    0,   26,  122,   13,    0,    0,   14,    0,    3,
    0,   56,   15,    0,   26,  196,   13,  197,  198,   14,
   56,    3,    0,    0,   15,    0,   26,   34,   34,   67,
   67,   34,    0,    0,   34,    0,   34,    0,   34,   88,
   88,    0,    0,   88,    0,   88,    0,    0,   88,    0,
   88,   87,   87,    0,    0,   87,    0,   87,    0,    0,
   87,    0,   87,    0,    0,   89,   89,    0,    0,   89,
    0,   89,   19,    0,   89,    0,   89,    0,   90,   90,
  121,  124,   90,  125,   90,    0,    0,   90,    0,   90,
   85,   85,    0,    0,   85,    0,   85,    0,    0,   85,
    0,   85,   86,   86,  145,    0,   86,    0,   86,    0,
    0,   86,    0,   86,    0,  164,    0,    0,    0,  157,
  158,    0,    0,  161,    0,  165,  169,  210,   13,    0,
  211,   14,    0,    3,    0,    0,   15,    0,   26,    0,
    0,    0,    0,    0,  175,  176,  177,   54,   13,    0,
    0,   14,    0,    0,   55,    0,   15,    0,   26,   19,
   58,   13,    0,    0,   14,  190,    0,   59,    0,   15,
  195,   26,  199,  200,    0,  201,   79,   13,    0,    0,
   14,    0,    3,    0,    0,   15,    0,   26,  202,  164,
    0,  203,    0,    0,  205,    0,  206,   25,   13,  209,
  212,   14,    0,   19,    0,    0,   15,    0,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   44,   41,   59,   45,   40,   45,   40,   40,   40,
   91,   59,    0,   93,   95,   95,   91,   45,   95,   45,
   45,   44,    0,   45,   41,   33,    0,   41,    0,  256,
   41,   44,   44,  256,    6,   44,   59,  274,  275,  266,
   48,   44,   50,   91,  267,   59,  273,   59,   59,   43,
   93,   45,   43,   43,   45,   45,   59,   44,   34,   35,
  256,   43,   42,   45,   40,   59,   44,   47,   59,   59,
   93,  267,   59,  258,  259,   40,   84,   85,   86,   87,
   93,   59,   90,   91,   93,   51,  256,   40,   92,   93,
  256,  261,  262,  259,  264,   61,  267,   63,   91,   40,
   45,  256,  257,  258,  259,  260,  266,  262,  263,  267,
  265,   40,  267,  258,  259,  258,  259,  267,  256,  257,
   88,   89,  260,   59,   41,   42,   43,  265,   45,  267,
   47,  258,  259,  258,  259,  258,  259,   41,   41,   41,
   41,  259,   59,   60,   41,   62,   43,   59,   45,  256,
  257,  258,  259,  260,   59,  262,  263,  267,  265,   59,
  267,   93,   59,   60,   41,   62,   43,   59,   45,  256,
  257,  258,  259,  260,   59,  262,  256,   59,  265,   93,
  267,  256,   59,   60,   41,   62,   43,   59,   45,  274,
   43,  266,   45,  274,  274,   59,   59,  274,  273,   59,
  256,   59,   59,   60,  259,   62,   41,   60,  259,   62,
   93,    0,  259,  256,  259,  256,    0,  256,   41,  259,
    6,  256,  257,  258,  259,  260,  267,    3,  267,  259,
  265,  263,  267,  274,  275,  274,  275,  268,  273,  267,
  273,  273,  267,  268,  266,  267,  274,  275,  274,  274,
  275,  268,  274,  275,   41,  267,  256,  257,  258,  259,
  260,  261,  262,  263,  264,  265,   41,  267,  256,  257,
  258,  259,  260,  261,  262,  263,  264,  265,  256,  267,
   41,   11,  256,  261,  262,  112,  264,  261,  262,  267,
  264,  256,  257,   41,  259,  260,  259,  262,  259,  259,
  265,  259,  267,  256,  257,   41,   -1,  260,  273,  262,
  259,  256,  265,   -1,  267,  256,  257,   41,  259,  260,
  273,   -1,  267,   -1,  265,   -1,  267,  256,  257,  274,
  275,  260,  273,   -1,   -1,   -1,  265,   -1,  267,  256,
  257,  258,  259,  260,  273,  262,  263,   -1,  265,   -1,
  267,   -1,  269,  270,  271,  272,   -1,   -1,   -1,  256,
  257,  258,  259,  260,   -1,  262,  263,   -1,  265,   -1,
  267,   -1,  269,  270,  271,  272,   -1,   -1,   -1,  256,
  257,  258,  259,  260,   -1,  262,  263,   -1,  265,   -1,
  267,   -1,  269,  270,  271,  272,   -1,   -1,   -1,  256,
  257,  258,  259,  260,   -1,  262,  263,   -1,  265,   -1,
  267,   -1,  269,  270,  271,  272,  269,  270,  271,  272,
   -1,  256,  257,   -1,    1,  260,    3,  262,   -1,   -1,
  265,   -1,  267,  256,  257,   -1,   -1,  260,   -1,  262,
   -1,   18,  265,   -1,  267,  256,  257,  258,  259,  260,
   27,  262,   -1,   -1,  265,   -1,  267,  256,  257,  258,
  259,  260,   -1,   -1,  263,   -1,  265,   -1,  267,  256,
  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,
  267,  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,
  265,   -1,  267,   -1,   -1,  256,  257,   -1,   -1,  260,
   -1,  262,   79,   -1,  265,   -1,  267,   -1,  256,  257,
   77,   78,  260,   80,  262,   -1,   -1,  265,   -1,  267,
  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,
   -1,  267,  256,  257,  101,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,   -1,  122,   -1,   -1,   -1,  116,
  117,   -1,   -1,  120,   -1,  122,  123,  256,  257,   -1,
  259,  260,   -1,  262,   -1,   -1,  265,   -1,  267,   -1,
   -1,   -1,   -1,   -1,  141,  142,  143,  256,  257,   -1,
   -1,  260,   -1,   -1,  263,   -1,  265,   -1,  267,  166,
  256,  257,   -1,   -1,  260,  162,   -1,  263,   -1,  265,
  167,  267,  169,  170,   -1,  172,  256,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  185,  196,
   -1,  188,   -1,   -1,  191,   -1,  193,  256,  257,  196,
  197,  260,   -1,  210,   -1,   -1,  265,   -1,  267,
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
"colection_declaration_statement : type ID index_list ']' ';'",
"colection_declaration_statement : type ID '[' ']' ';'",
"colection_declaration_statement : type ID '[' error ']' ';'",
"colection_declaration_statement : type ID '[' index_list error ';'",
"colection_declaration_statement : type ID index_list ';'",
"colection_declaration_statement : type ID '[' index_list ']' error",
"var_list : ID",
"var_list : var_list ',' ID",
"index_list : index",
"index_list : index_list ',' index",
"index : '_'",
"index : INT_CONST",
"block_statements : BEGIN statement_block END",
"block_statements : error statement_block END",
"block_statements : BEGIN error END",
"block_statements : BEGIN statement_block error",
"block_statements : error statement_block error",
"statement_block : executional_statements",
"statement_block : statement_block executional_statements",
"executional_statements : if_statement",
"executional_statements : assign_statement",
"executional_statements : foreach_in_statement",
"executional_statements : print_statement",
"if_statement : IF '(' condition ')' executional_block END_IF",
"if_statement : if_else_statement",
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
"if_else_statement : IF '(' condition ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' error ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF condition ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF error condition ')' executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' condition executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' condition error executional_block ELSE executional_block END_IF",
"if_else_statement : IF '(' condition ')' ELSE executional_block END_IF",
"if_else_statement : IF '(' condition ')' error ELSE executional_block END_IF",
"if_else_statement : IF '(' condition ')' executional_block error END_IF",
"if_else_statement : IF '(' condition ')' executional_block executional_block END_IF",
"if_else_statement : IF '(' condition ')' executional_block error executional_block END_IF",
"if_else_statement : IF '(' condition ')' executional_block ELSE error END_IF",
"if_else_statement : IF '(' condition ')' executional_block ELSE END_IF",
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
"foreach_in_statement : ID error IN ID executional_block",
"foreach_in_statement : FOREACH ID IN error executional_block",
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

//#line 169 "grammar.y"

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
 	private static final String ERROR_EXE_BLOCK_STATEMENT = ": executional block statement was expected.";  
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
//#line 583 "Parser.java"
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
{System.out.println("Variable declared at line " + val_peek(0).end_line + ".");}
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
{System.out.println("Colection declared at line " + val_peek(0).end_line + ".");}
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
{yyerror(ERROR_SQUARE_BRACKET_OPEN);}
break;
case 18:
//#line 41 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 19:
//#line 42 "grammar.y"
{yyerror(ERROR_INDEX);}
break;
case 20:
//#line 43 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
break;
case 21:
//#line 44 "grammar.y"
{yyerror(ERROR_SQUARE_BRACKET);}
break;
case 22:
//#line 45 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 30:
//#line 61 "grammar.y"
{yyerror(ERROR_BEGIN);}
break;
case 31:
//#line 62 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 32:
//#line 63 "grammar.y"
{yyerror(ERROR_END);}
break;
case 33:
//#line 64 "grammar.y"
{yyerror(ERROR_BEGIN); yyerror(ERROR_END);}
break;
case 40:
//#line 77 "grammar.y"
{System.out.println("IF statement at line " + val_peek(5).end_line + ".");}
break;
case 42:
//#line 79 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(3).begin_line);}
break;
case 43:
//#line 80 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(2).begin_line);}
break;
case 44:
//#line 81 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(3).begin_line);}
break;
case 45:
//#line 82 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(4).begin_line);}
break;
case 46:
//#line 83 "grammar.y"
{yyerror(ERROR_BRACKET, val_peek(2).begin_line);}
break;
case 47:
//#line 84 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(5).begin_line);}
break;
case 48:
//#line 85 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(4).begin_line);}
break;
case 49:
//#line 86 "grammar.y"
{yyerror(ERROR_IF, val_peek(4).begin_line);}
break;
case 50:
//#line 87 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(2).begin_line);}
break;
case 51:
//#line 88 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(1).begin_line);}
break;
case 52:
//#line 93 "grammar.y"
{System.out.println("IF-ELSE statement at line " + val_peek(7).end_line + ".");}
break;
case 53:
//#line 94 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(6).begin_line);}
break;
case 54:
//#line 95 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(5).begin_line);}
break;
case 55:
//#line 96 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(4).begin_line);}
break;
case 56:
//#line 97 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(5).begin_line);}
break;
case 57:
//#line 98 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(5).begin_line);}
break;
case 58:
//#line 99 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(6).begin_line);}
break;
case 59:
//#line 100 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(3).begin_line);}
break;
case 60:
//#line 101 "grammar.y"
{yyerror(ERROR_EXE_BLOCK_STATEMENT, val_peek(4).begin_line);}
break;
case 61:
//#line 102 "grammar.y"
{yyerror(ERROR_BEGIN, val_peek(3).begin_line);}
break;
case 62:
//#line 103 "grammar.y"
{yyerror(ERROR_ELSE, val_peek(0).begin_line);}
break;
case 63:
//#line 104 "grammar.y"
{yyerror(ERROR_ELSE, val_peek(2).begin_line);}
break;
case 64:
//#line 105 "grammar.y"
{yyerror(ERROR_EXE_BLOCK_STATEMENT, val_peek(2).begin_line);}
break;
case 65:
//#line 106 "grammar.y"
{yyerror(ERROR_EXE_BLOCK_STATEMENT, val_peek(1).begin_line);}
break;
case 66:
//#line 107 "grammar.y"
{yyerror(ERROR_END_IF, val_peek(2).begin_line);}
break;
case 69:
//#line 114 "grammar.y"
{System.out.println("Assign statement at line " + val_peek(3).end_line + ".");}
break;
case 70:
//#line 115 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 71:
//#line 116 "grammar.y"
{yyerror(ERROR_ASSIGN);}
break;
case 72:
//#line 117 "grammar.y"
{yyerror(ERROR_EXPRESSION);}
break;
case 73:
//#line 118 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 74:
//#line 121 "grammar.y"
{System.out.println("FOREACH statement at line " + val_peek(4).end_line  + ".");}
break;
case 75:
//#line 122 "grammar.y"
{yyerror(ERROR_FOR, val_peek(3).begin_line);}
break;
case 76:
//#line 123 "grammar.y"
{yyerror(ERROR_FOR, val_peek(4).begin_line);}
break;
case 77:
//#line 124 "grammar.y"
{yyerror(ERROR_COLECTION_ID, val_peek(3).begin_line);}
break;
case 78:
//#line 128 "grammar.y"
{System.out.println("Print statement at line " + lexer.getLine() + ".");}
break;
case 79:
//#line 129 "grammar.y"
{yyerror(ERROR_PRINT);}
break;
case 80:
//#line 130 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 81:
//#line 131 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 82:
//#line 132 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 83:
//#line 133 "grammar.y"
{yyerror(ERROR_STRING);}
break;
case 84:
//#line 134 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 100:
//#line 160 "grammar.y"
{checkRange(yyval.sval, MAX_BITS_INT);}
break;
case 101:
//#line 161 "grammar.y"
{checkRange(yyval.sval, MAX_BITS_LONG);}
break;
case 102:
//#line 162 "grammar.y"
{checkRange("-"+val_peek(0).sval, MAX_BITS_INT); }
break;
case 103:
//#line 163 "grammar.y"
{checkRange("-"+val_peek(0).sval, MAX_BITS_LONG);}
break;
case 104:
//#line 164 "grammar.y"
{checkRange(val_peek(1).sval, MAX_BITS_INT);}
break;
case 105:
//#line 165 "grammar.y"
{checkRange(val_peek(2).sval, MAX_BITS_INT);}
break;
//#line 1000 "Parser.java"
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
