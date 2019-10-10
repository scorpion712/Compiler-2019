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
    4,    4,    4,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    7,    7,    8,    8,    9,    9,    3,    3,
    3,    3,   10,   10,   11,   11,   11,   11,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   17,   17,   13,   13,   13,   13,   13,
   14,   14,   14,   14,   14,   15,   15,   15,   15,   15,
   15,   15,   16,   16,   16,   16,   16,   16,   19,   19,
   19,   20,   20,   20,    6,    6,   21,   21,   21,   21,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    3,
    3,    4,    2,    6,    6,    6,    5,    5,    6,    6,
    4,    6,    1,    3,    1,    3,    1,    1,    3,    3,
    3,    3,    1,    2,    1,    1,    2,    1,    6,    1,
    6,    5,    5,    6,    4,    6,    6,    6,    5,    8,
    8,    7,    7,    8,    7,    8,    7,    8,    8,    7,
    8,    7,    8,    1,    1,    4,    4,    4,    4,    3,
    5,    4,    5,    5,    5,    5,    5,    4,    4,    3,
    5,    4,    3,    3,    3,    3,    3,    3,    3,    3,
    1,    3,    3,    1,    1,    1,    1,    1,    2,    4,
};
final static short yydefred[] = {                         0,
    0,   95,    0,   96,    0,    0,    3,    4,    7,    8,
    0,    0,    0,    0,    0,    0,    0,    0,   33,   35,
   36,    0,   38,   40,    0,    0,    0,    5,    6,    0,
    0,    0,    0,    0,    0,    0,   98,    0,    0,    0,
    0,    0,   94,    0,    0,    0,    0,    0,    0,    0,
    0,   10,    0,   30,   34,   37,   31,    0,   29,   11,
    0,   28,    0,   27,    0,   25,    0,    9,    0,    0,
    0,    0,    0,   99,    0,    0,    0,    0,    0,   65,
   64,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   80,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   24,    0,    0,    0,    0,   21,
    0,    0,   12,   67,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,    0,    0,    0,    0,    0,
    0,    0,    0,   92,   93,   78,    0,   79,    0,    0,
    0,    0,    0,   68,    0,   69,   66,    0,    0,    0,
   18,    0,    0,   17,   26,   77,    0,    0,  100,    0,
    0,    0,    0,    0,    0,   49,    0,    0,   43,    0,
   42,   81,   76,    0,    0,    0,    0,   15,   16,   19,
   20,   22,   14,   47,    0,   41,    0,   46,    0,    0,
   44,    0,   48,    0,    0,    0,   39,    0,    0,    0,
    0,    0,   52,    0,    0,   57,    0,    0,   62,    0,
   60,   55,   53,   54,   51,   56,   58,   59,   61,   63,
   50,
};
final static short yydgoto[] = {                          5,
    6,    7,   80,    9,   10,   11,   17,   65,   66,   18,
   81,   20,   21,   22,   23,   40,   82,   24,   41,   42,
   43,
};
final static short yysindex[] = {                      -160,
  323,    0,  329,    0,    0, -160,    0,    0,    0,    0,
 -197,  -33,  -40,  -24, -173,  -80,  -12,  -57,    0,    0,
    0, -232,    0,    0,  -34, -223,  292,    0,    0,  -29,
  -77,  -42,   11,   20,   11,  -30,    0, -205,  -32,  125,
  -20,   58,    0,  -14, -202, -188, -193,   11, -175,  -41,
  -83,    0, -168,    0,    0,    0,    0,  -33,    0,    0,
  -83,    0,  -73,    0,  -25,    0,   47,    0,   -6,   71,
   78,   85, -140,    0,   91,  305,  137,   36,  305,    0,
    0, -123,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,    0,   83,  105,  -10, -108, -105, -169,   12,
  305,  110,   29,  -18,    0,  -16,   86,  122,   -9,    0,
  130,  -83,    0,    0,  133,  305,  305,  103,  305,  -94,
  -19, -102, -118, -114,    0,   79,   79,   79,   79,   58,
   58,   79,   79,    0,    0,    0,  139,    0,  150,  305,
  305,  305,  305,    0,    0,    0,    0,  152,  154,  194,
    0,  197,  -49,    0,    0,    0,  -27,  -74,    0,  -71,
  305,    0,  -64,  -37,  305,    0,  -85,  305,    0,  305,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  305,    0,  305,    0,   14,  305,
    0,  305,    0,   23,  -19,  239,    0,   27,   31,   40,
   41,   43,    0,   53,   55,    0,   57,   24,    0, -218,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                       295,
    0,    0,    0,    0,    0,  321,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   -8,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    8,   15,    0,    0,    0,   48,    0,    0,    0,    0,
    0,   68,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -142,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  149,  161,  173,  190,   88,
  108,  204,  226,    0,    0,    0,    0,    0,  253,    0,
    0,    0,    0,    0,   18,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  274,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   66,   67,   76,   80,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  328,   97,    0,    0,    0,  330,   -3,  231,  346,
  134,    0,    0,    0,    0,  104,  386,    0,   -4,  117,
  126,
};
final static int YYTABLESIZE=596;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
   32,   53,   34,   38,   38,   34,   34,   23,   76,  183,
   51,   64,   38,   63,   13,   45,   68,   64,  112,  108,
   34,   64,   87,   56,   88,  112,   94,  112,   69,   60,
  139,   53,   48,  110,  112,   23,   87,  220,   88,   89,
  221,   90,   49,  100,   93,  103,   52,  104,  138,   50,
   23,   23,  114,   95,   87,   38,   88,  106,   30,  109,
   73,   61,   98,   34,   38,   96,   23,  111,   74,   31,
  144,   87,   99,   88,  148,   34,  149,   97,  126,  127,
  128,  129,   46,  153,  132,  133,  142,  147,   97,   97,
   97,  101,   97,   47,   97,    1,    8,  143,  105,   91,
    2,    3,   29,    4,   92,  113,   97,   97,   91,   97,
   91,  115,   91,   70,   70,   70,   70,   70,  116,   70,
   70,   87,   70,   88,   70,  117,   91,   91,   89,   91,
   89,  119,   89,  118,   19,  125,   19,   71,   72,  168,
  169,  136,   77,  170,  171,  137,   89,   89,   90,   89,
   90,   55,   90,  164,   13,  165,  166,   14,  140,    3,
   55,  141,   15,  161,   26,   79,   90,   90,  146,   90,
  195,   13,  196,  197,   14,   48,    3,  122,  150,   15,
  151,   26,  107,  185,  186,   49,  187,  188,  154,   86,
   62,  156,   50,  190,  191,  159,   62,  172,   12,   13,
   62,   85,   14,  130,  131,   54,  182,   15,  173,   26,
  178,   19,  179,   87,  102,   35,  134,  135,   12,   13,
  192,  193,   14,   75,   67,   36,   36,   15,   57,   26,
   88,  184,   37,   37,   36,   33,   78,   13,   33,   33,
   14,   37,    3,   44,   83,   15,  152,   26,   83,   84,
   85,   86,  180,   33,  162,  181,   32,   32,   32,   32,
   32,   32,   32,   23,   32,   32,   84,   32,   23,   23,
   13,   23,  203,   72,   23,   13,   13,   36,   13,   12,
   13,  206,  219,   14,   37,  211,   36,   70,   15,  212,
   26,   12,   13,   37,    1,   14,   33,   19,  213,  214,
   15,  215,   26,   97,   97,   97,   97,   97,   33,   97,
   97,  216,   97,  217,   97,  218,   97,   97,   97,   97,
    2,   73,   74,   91,   91,   91,   91,   91,  162,   91,
   91,   75,   91,   28,   91,   71,   91,   91,   91,   91,
   32,   19,  155,   89,   89,   89,   89,   89,   27,   89,
   89,    0,   89,    0,   89,    0,   89,   89,   89,   89,
    0,    0,    0,   90,   90,   90,   90,   90,    0,   90,
   90,    0,   90,    0,   90,    0,   90,   90,   90,   90,
   78,   13,    0,    0,   14,    0,    3,    0,    0,   15,
    0,   26,  121,   13,    0,    0,   14,    0,    3,    0,
    0,   15,    0,   26,   86,   86,    0,    0,   86,    0,
   86,    0,    0,   86,    0,   86,   85,   85,    0,    0,
   85,    0,   85,    0,    0,   85,    0,   85,   87,   87,
    0,    0,   87,    0,   87,    0,    0,   87,    0,   87,
    0,    0,    0,    0,    0,   88,   88,    0,    0,   88,
    0,   88,    0,    0,   88,    0,   88,    0,    0,   83,
   83,  120,  123,   83,  124,   83,    0,    0,   83,    0,
   83,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   84,   84,    0,    0,   84,  145,   84,    0,    0,
   84,    0,   84,    0,  208,   13,    0,  209,   14,    0,
    3,  157,  158,   15,  160,   26,  163,  167,   82,   82,
   82,   82,   82,    0,   82,   82,    0,   82,    0,   82,
    0,    0,    0,    0,    0,  174,  175,  176,  177,   33,
   33,   64,   64,   33,    0,    0,   33,    0,   33,    0,
   33,    0,    0,    0,    0,    0,  189,   58,   13,    0,
  194,   14,  198,  199,   59,  200,   15,    0,   26,    0,
   78,   13,    0,    0,   14,    0,    3,    0,    0,   15,
  201,   26,  202,    0,    0,  204,    0,  205,   12,   13,
  207,  210,   14,    0,   25,   13,    0,   15,   14,   16,
    0,    0,    0,   15,    0,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   44,   40,   45,   45,   40,   40,    0,   41,   59,
   91,   95,   45,   91,    0,   40,   59,   95,   44,   93,
   40,   95,   43,  256,   45,   44,   41,   44,   33,   59,
   41,   44,  256,   59,   44,   44,   43,  256,   45,   60,
  259,   62,  266,   48,   59,   50,   59,   51,   59,  273,
   59,   44,   59,  256,   43,   45,   45,   61,  256,   63,
   91,   91,  256,   40,   45,  268,   59,   93,  274,  267,
   59,   43,  266,   45,   93,   40,   93,  266,   83,   84,
   85,   86,  256,   93,   89,   90,  256,   59,   41,   42,
   43,  267,   45,  267,   47,  256,    0,  267,  267,   42,
  261,  262,    6,  264,   47,   59,   59,   60,   41,   62,
   43,   41,   45,  256,  257,  258,  259,  260,   41,  262,
  263,   43,  265,   45,  267,   41,   59,   60,   41,   62,
   43,   41,   45,  274,    1,  259,    3,   34,   35,  258,
  259,   59,   39,  258,  259,   41,   59,   60,   41,   62,
   43,   18,   45,  256,  257,  258,  259,  260,  267,  262,
   27,  267,  265,  258,  267,   41,   59,   60,   59,   62,
  256,  257,  258,  259,  260,  256,  262,   41,   93,  265,
   59,  267,  256,  258,  259,  266,  258,  259,   59,   41,
  274,   59,  273,  258,  259,   93,  274,   59,  256,  257,
  274,   41,  260,   87,   88,  263,  256,  265,   59,  267,
   59,   78,   59,   41,  256,  256,   91,   92,  256,  257,
  258,  259,  260,  256,  267,  267,  267,  265,  263,  267,
   41,  259,  274,  274,  267,  273,  256,  257,  273,  273,
  260,  274,  262,  268,   41,  265,  256,  267,  269,  270,
  271,  272,   59,  273,  121,   59,  256,  257,  258,  259,
  260,  261,  262,  256,  264,  265,   41,  267,  261,  262,
  256,  264,  259,  256,  267,  261,  262,  267,  264,  256,
  257,  259,  259,  260,  274,  259,  267,  268,  265,  259,
  267,  256,  257,  274,    0,  260,  273,  164,  259,  259,
  265,  259,  267,  256,  257,  258,  259,  260,  273,  262,
  263,  259,  265,  259,  267,  259,  269,  270,  271,  272,
    0,  256,  256,  256,  257,  258,  259,  260,  195,  262,
  263,  256,  265,    6,  267,  256,  269,  270,  271,  272,
   11,  208,  112,  256,  257,  258,  259,  260,    3,  262,
  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,  272,
   -1,   -1,   -1,  256,  257,  258,  259,  260,   -1,  262,
  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,  272,
  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,
   -1,  267,  256,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  256,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,  265,   -1,  267,  256,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  256,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,   -1,  260,
   -1,  262,   -1,   -1,  265,   -1,  267,   -1,   -1,  256,
  257,   76,   77,  260,   79,  262,   -1,   -1,  265,   -1,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  256,  257,   -1,   -1,  260,  101,  262,   -1,   -1,
  265,   -1,  267,   -1,  256,  257,   -1,  259,  260,   -1,
  262,  116,  117,  265,  119,  267,  121,  122,  256,  257,
  258,  259,  260,   -1,  262,  263,   -1,  265,   -1,  267,
   -1,   -1,   -1,   -1,   -1,  140,  141,  142,  143,  256,
  257,  258,  259,  260,   -1,   -1,  263,   -1,  265,   -1,
  267,   -1,   -1,   -1,   -1,   -1,  161,  256,  257,   -1,
  165,  260,  167,  168,  263,  170,  265,   -1,  267,   -1,
  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,
  185,  267,  187,   -1,   -1,  190,   -1,  192,  256,  257,
  195,  196,  260,   -1,  256,  257,   -1,  265,  260,  267,
   -1,   -1,   -1,  265,   -1,  267,
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
"index : NUMERIC_CONST",
"block_statements : BEGIN statement_block END",
"block_statements : error statement_block END",
"block_statements : BEGIN error END",
"block_statements : BEGIN statement_block error",
"statement_block : executional_statements",
"statement_block : statement_block executional_statements",
"executional_statements : if_statement",
"executional_statements : assign_statement",
"executional_statements : foreach_in_statement error",
"executional_statements : print_statement",
"if_statement : IF '(' condition ')' executional_block END_IF",
"if_statement : if_else_statement",
"if_statement : IF error condition ')' executional_block END_IF",
"if_statement : IF condition ')' executional_block END_IF",
"if_statement : IF '(' condition executional_block END_IF",
"if_statement : IF '(' condition error executional_block END_IF",
"if_statement : IF condition executional_block END_IF",
"if_statement : IF '(' error ')' executional_block END_IF",
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
"if_else_statement : IF '(' condition ')' executional_block error executional_block END_IF",
"if_else_statement : IF '(' condition ')' executional_block executional_block END_IF",
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
"foreach_in_statement : FOREACH error IN ID executional_block",
"foreach_in_statement : FOREACH ID error ID executional_block",
"foreach_in_statement : FOREACH ID IN error executional_block",
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

//#line 162 "grammar.y"

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
 	private static final String ERROR_FOR = ": for expected.";
 	private static final String ERROR_IN = ": in expected."; 
 	private static final String ERROR_EXE_STATEMENT = ": executional statement was expected.";  
 	private static final String ERROR_EXE_BLOCK_STATEMENT = ": executional block statement was expected.";  
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
    public void checkRange(String constant) {
    	// TO DO
    }
//#line 554 "Parser.java"
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
case 37:
//#line 72 "grammar.y"
{yyerror("Foreach error");}
break;
case 39:
//#line 76 "grammar.y"
{System.out.println("IF statement at line " + val_peek(5).end_line + ".");}
break;
case 41:
//#line 78 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(3).begin_line);}
break;
case 42:
//#line 79 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(2).begin_line);}
break;
case 43:
//#line 80 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(3).begin_line);}
break;
case 44:
//#line 81 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(4).begin_line);}
break;
case 45:
//#line 82 "grammar.y"
{yyerror(ERROR_BRACKET, val_peek(2).begin_line);}
break;
case 46:
//#line 83 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(4).begin_line);}
break;
case 47:
//#line 84 "grammar.y"
{yyerror(ERROR_IF, val_peek(4).begin_line);}
break;
case 48:
//#line 85 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(2).begin_line);}
break;
case 49:
//#line 86 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(1).begin_line);}
break;
case 50:
//#line 89 "grammar.y"
{System.out.println("IF-ELSE statement at line " + val_peek(7).end_line + ".");}
break;
case 51:
//#line 90 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(6).begin_line);}
break;
case 52:
//#line 91 "grammar.y"
{yyerror(ERROR_CONDITION, val_peek(5).begin_line);}
break;
case 53:
//#line 92 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(4).begin_line);}
break;
case 54:
//#line 93 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN, val_peek(5).begin_line);}
break;
case 55:
//#line 94 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(5).begin_line);}
break;
case 56:
//#line 95 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE, val_peek(6).begin_line);}
break;
case 57:
//#line 96 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT, val_peek(3).begin_line);}
break;
case 58:
//#line 97 "grammar.y"
{yyerror(ERROR_EXE_BLOCK_STATEMENT, val_peek(4).begin_line);}
break;
case 59:
//#line 98 "grammar.y"
{yyerror(ERROR_BEGIN, val_peek(3).begin_line);}
break;
case 60:
//#line 99 "grammar.y"
{yyerror(ERROR_ELSE, val_peek(1).begin_line);}
break;
case 61:
//#line 100 "grammar.y"
{yyerror(ERROR_EXE_BLOCK_STATEMENT, val_peek(2).begin_line);}
break;
case 62:
//#line 101 "grammar.y"
{yyerror(ERROR_EXE_BLOCK_STATEMENT, val_peek(1).begin_line);}
break;
case 63:
//#line 102 "grammar.y"
{yyerror(ERROR_END_IF, val_peek(1).begin_line);}
break;
case 66:
//#line 109 "grammar.y"
{System.out.println("Assign statement at line " + lexer.getLine() + ".");}
break;
case 67:
//#line 110 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 68:
//#line 111 "grammar.y"
{yyerror(ERROR_ASSIGN);}
break;
case 69:
//#line 112 "grammar.y"
{yyerror(ERROR_EXPRESSION);}
break;
case 70:
//#line 113 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 71:
//#line 116 "grammar.y"
{System.out.println("FOREACH statement at line " + lexer.getLine() + ".");}
break;
case 72:
//#line 117 "grammar.y"
{yyerror(ERROR_FOR);}
break;
case 73:
//#line 118 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 74:
//#line 119 "grammar.y"
{yyerror(ERROR_IN);}
break;
case 75:
//#line 120 "grammar.y"
{yyerror(ERROR_COLECTION_ID);}
break;
case 76:
//#line 124 "grammar.y"
{System.out.println("Print statement at line " + lexer.getLine() + ".");}
break;
case 77:
//#line 125 "grammar.y"
{yyerror(ERROR_PRINT);}
break;
case 78:
//#line 126 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 79:
//#line 127 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 80:
//#line 128 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 81:
//#line 129 "grammar.y"
{yyerror(ERROR_STRING);}
break;
case 82:
//#line 130 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
//#line 943 "Parser.java"
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
