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
    5,    5,    5,    5,    7,    7,    8,    8,    9,    9,
    3,    3,    3,    3,   10,   10,   11,   11,   11,   11,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   18,
   18,   18,   18,   18,   17,   17,   13,   13,   13,   13,
   13,   14,   14,   14,   14,   14,   15,   15,   15,   15,
   15,   15,   15,   16,   16,   16,   16,   16,   16,   19,
   19,   19,   20,   20,   20,    6,    6,   21,   21,   21,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    3,
    3,    4,    2,    6,    6,    6,    5,    3,    5,    5,
    5,    6,    7,    7,    1,    3,    1,    3,    1,    1,
    3,    3,    3,    3,    1,    2,    1,    1,    1,    1,
    6,    1,    5,    5,    4,    6,    6,    6,    6,    8,
    8,    8,    8,    8,    1,    1,    4,    4,    4,    4,
    3,    5,    4,    5,    5,    5,    5,    5,    4,    4,
    3,    4,    4,    3,    3,    3,    3,    3,    3,    3,
    3,    1,    3,    3,    1,    1,    1,    1,    1,    2,
};
final static short yydefred[] = {                         0,
    0,   86,    0,   87,    0,    0,    3,    4,    7,    8,
    0,    0,    0,    0,    0,    0,    0,    0,   35,   37,
   38,   39,   40,   42,    0,    0,    0,    5,    6,    0,
    0,    0,    0,    0,   88,   89,    0,    0,    0,    0,
    0,   85,    0,    0,    0,    0,    0,    0,    0,    0,
   10,    0,   32,   36,   33,    0,   31,   11,    0,   30,
    0,   29,    0,   27,    0,    9,    0,    0,    0,   90,
    0,    0,    0,    0,   56,   55,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   71,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   26,
    0,    0,    0,    0,    0,    0,   12,   58,    0,    0,
    0,    0,    0,    0,   45,    0,    0,    0,    0,    0,
    0,    0,    0,   83,   84,   69,   70,    0,   72,    0,
    0,    0,    0,   59,   63,   60,   57,    0,    0,    0,
   21,   19,    0,    0,   20,   28,   68,    0,    0,    0,
    0,   44,   43,   67,   64,   65,   66,   62,   15,   16,
   22,   14,    0,    0,   47,    0,   46,    0,   48,   49,
    0,   41,   24,   23,    0,    0,    0,    0,   52,   53,
   51,   54,   50,
};
final static short yydgoto[] = {                          5,
    6,    7,   75,    9,   10,   11,   17,   63,   64,   18,
   76,   20,   21,   22,   23,   39,   77,   24,   40,   41,
   42,
};
final static short yysindex[] = {                       -65,
  196,    0,  250,    0,    0,  -65,    0,    0,    0,    0,
 -218,  -34,  -40,  -24, -168,  -84,    6, -121,    0,    0,
    0,    0,    0,    0,  -38, -219,  -92,    0,    0,  -27,
  -82,  -14,    7,   56,    0,    0, -264,  -41,  109,  -20,
   51,    0,   -8,  -23, -252, -187,    7, -226,   88,  -87,
    0, -200,    0,    0,    0,  -34,    0,    0,  -87,    0,
  -73,    0,  -18,    0,  -11,    0,   13,   33,   59,    0,
   72,  121,   37,  214,    0,    0, -133,    7,    7,    7,
    7,    7,    7,    7,    7,    7,    7,    0,   78,   -6,
  101, -126,  -86, -160,   14,  214,  125,   17,  -15,    0,
  -13,   87,  126,  -32,  129,  -87,    0,    0,  131,  214,
  214,  226,  -64,  -59,    0,   82,   82,   82,   82,   51,
   51,   82,   82,    0,    0,    0,    0,  135,    0,  214,
  214,  214,  214,    0,    0,    0,    0,  148,  149,  153,
    0,    0,   22,  123,    0,    0,    0,  -45, -101,  -37,
  -89,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -39,  159,    0,  214,    0,  214,    0,    0,
  238,    0,    0,    0,  -30,  -12,  -19, -220,    0,    0,
    0,    0,    0,
};
final static short yyrindex[] = {                       231,
    0,    0,    0,    0,    0,  232,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   47,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   11,   15,    0,    0,    0,    0,    0,    0,    0,    0,
   49,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,   24,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -104,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  133,  145,  157,  169,   69,
   89,  183,  202,    0,    0,    0,    0,  -54,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  249,  115,    0,    0,    0,  245,   36,   41,  266,
   16,    0,    0,    0,    0,   86,  -28,    0,   38,   94,
   92,
};
final static int YYTABLESIZE=517;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
   34,   34,   34,   37,   37,   34,   50,   62,   61,   70,
   25,  106,   62,   92,   13,   44,   19,   91,   19,  103,
   34,   62,   82,   18,   83,  106,  142,   17,  106,   52,
  106,   58,   89,   54,  128,  182,   47,   30,  183,   84,
   96,   85,   54,  113,   66,  114,   48,  107,   31,   52,
   88,   37,  127,   49,   25,   82,   82,   83,   83,   82,
  143,   83,   62,   59,   51,  163,  100,  135,   93,   25,
   67,  108,  134,  109,  105,  137,   34,  138,   94,  139,
  162,  148,  149,  151,   95,   99,   98,   45,   19,   82,
   25,   82,   86,   82,  101,  132,  104,   87,   46,  110,
   37,  155,  156,  157,  158,   25,  133,   82,   82,   80,
   82,   80,  111,   80,    8,  116,  117,  118,  119,   69,
   29,  122,  123,   72,   82,  115,   83,   80,   80,   81,
   80,   81,   37,   81,   12,   13,  126,  175,   14,  176,
  130,   53,  178,   15,  144,   26,  146,   81,   81,   74,
   81,   61,   61,   61,   61,   61,  166,  167,   61,  129,
   61,  112,   61,   56,   13,   19,  170,   14,  171,  172,
   57,   47,   15,   77,   26,  120,  121,  124,  125,  140,
  131,   48,  102,  136,  141,   76,   60,  145,   49,  147,
    1,   60,   19,  154,  152,    2,    3,   78,    4,  153,
   60,   73,   73,   73,   73,   73,  159,  160,   73,   79,
   73,  161,   73,  165,   71,  164,  173,  174,   12,   13,
  168,  169,   14,   74,   55,   35,   35,   15,  179,   26,
    1,    2,   36,   36,   33,   33,   12,   13,   33,  181,
   14,   60,   75,   43,   90,   15,  180,   26,   78,   79,
   80,   81,   65,   33,   28,   32,   34,   34,   34,   34,
   34,   34,   34,   34,   34,   34,   25,   34,   27,    0,
   13,   25,   25,   35,   25,   13,   13,   25,   13,   18,
   36,    0,    0,   17,   18,   18,    0,   18,   17,   17,
    0,   17,   12,   13,    0,    0,   14,    0,    0,    0,
    0,   15,    0,   26,   82,   82,   82,   82,   82,   33,
   82,   82,    0,   82,    0,   82,    0,   82,   82,   82,
   82,    0,   35,   68,   80,   80,   80,   80,   80,   36,
   80,   80,    0,   80,    0,   80,    0,   80,   80,   80,
   80,    0,    0,   97,   81,   81,   81,   81,   81,    0,
   81,   81,    0,   81,   35,   81,    0,   81,   81,   81,
   81,   36,    0,    0,   73,   13,    0,    0,   14,    0,
    3,    0,    0,   15,    0,   26,   73,   13,    0,    0,
   14,    0,    3,    0,    0,   15,    0,   26,   77,   77,
    0,    0,   77,    0,   77,    0,    0,   77,    0,   77,
   76,   76,    0,    0,   76,    0,   76,    0,    0,   76,
    0,   76,   78,   78,    0,    0,   78,    0,   78,    0,
    0,   78,    0,   78,   79,   79,    0,    0,   79,    0,
   79,    0,    0,   79,    0,   79,    0,    0,   74,   74,
    0,    0,   74,    0,   74,    0,    0,   74,    0,   74,
    0,   12,   13,    0,    0,   14,    0,   75,   75,    0,
   15,   75,   16,   75,    0,    0,   75,    0,   75,   73,
   13,    0,    0,   14,    0,    3,    0,    0,   15,    0,
   26,  150,   13,    0,    0,   14,    0,    3,    0,    0,
   15,    0,   26,  177,   13,    0,    0,   14,    0,    3,
    0,    0,   15,    0,   26,   25,   13,    0,    0,   14,
    0,    0,    0,    0,   15,    0,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   40,   40,   45,   45,   40,   91,   95,   91,  274,
    0,   44,   95,  266,    0,   40,    1,   41,    3,   93,
   40,   95,   43,    0,   45,   44,   59,    0,   44,   44,
   44,   59,   41,   18,   41,  256,  256,  256,  259,   60,
  267,   62,   27,   72,   59,   74,  266,   59,  267,   44,
   59,   45,   59,  273,   44,   43,   43,   45,   45,   43,
   93,   45,   95,   91,   59,   44,  267,   96,  256,   59,
   33,   59,   59,   41,   93,   59,   40,   93,  266,   93,
   59,  110,  111,  112,   47,   50,   49,  256,   73,   41,
   44,   43,   42,   45,   59,  256,   61,   47,  267,   41,
   45,  130,  131,  132,  133,   59,  267,   59,   60,   41,
   62,   43,   41,   45,    0,   78,   79,   80,   81,   34,
    6,   84,   85,   38,   43,  259,   45,   59,   60,   41,
   62,   43,   45,   45,  256,  257,   59,  166,  260,  168,
  267,  263,  171,  265,  104,  267,  106,   59,   60,   41,
   62,  256,  257,  258,  259,  260,  258,  259,  263,   59,
  265,   41,  267,  256,  257,  150,  256,  260,  258,  259,
  263,  256,  265,   41,  267,   82,   83,   86,   87,   93,
  267,  266,  256,   59,   59,   41,  274,   59,  273,   59,
  256,  274,  177,   59,  259,  261,  262,   41,  264,  259,
  274,  256,  257,  258,  259,  260,   59,   59,  263,   41,
  265,   59,  267,  259,  256,   93,  256,   59,  256,  257,
  258,  259,  260,   41,  263,  267,  267,  265,  259,  267,
    0,    0,  274,  274,  273,  273,  256,  257,  273,  259,
  260,  274,   41,  268,  268,  265,  259,  267,  269,  270,
  271,  272,  267,  273,    6,   11,  256,  257,  258,  259,
  260,  261,  262,  263,  264,  265,  256,  267,    3,   -1,
  256,  261,  262,  267,  264,  261,  262,  267,  264,  256,
  274,   -1,   -1,  256,  261,  262,   -1,  264,  261,  262,
   -1,  264,  256,  257,   -1,   -1,  260,   -1,   -1,   -1,
   -1,  265,   -1,  267,  256,  257,  258,  259,  260,  273,
  262,  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,
  272,   -1,  267,  268,  256,  257,  258,  259,  260,  274,
  262,  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,
  272,   -1,   -1,  256,  256,  257,  258,  259,  260,   -1,
  262,  263,   -1,  265,  267,  267,   -1,  269,  270,  271,
  272,  274,   -1,   -1,  256,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,  265,   -1,  267,  256,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  256,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,
   -1,  267,  256,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  256,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,  265,   -1,  267,   -1,   -1,  256,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
   -1,  256,  257,   -1,   -1,  260,   -1,  256,  257,   -1,
  265,  260,  267,  262,   -1,   -1,  265,   -1,  267,  256,
  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,
  267,  256,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,
  265,   -1,  267,  256,  257,   -1,   -1,  260,   -1,  262,
   -1,   -1,  265,   -1,  267,  256,  257,   -1,   -1,  260,
   -1,   -1,   -1,   -1,  265,   -1,  267,
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
"colection_declaration_statement : type ID '[' index_list ']'",
"colection_declaration_statement : type ID index_list",
"colection_declaration_statement : type ID '[' index_list ';'",
"colection_declaration_statement : type ID index_list ']' ';'",
"colection_declaration_statement : type ID '[' ']' ';'",
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
"factor : NUMERIC_CONST",
"factor : '-' NUMERIC_CONST",
};

//#line 153 "grammar.y"

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
 	private static final String ERROR_COLECTION = ": error in colection declaration statement."; 
 	private static final String ERROR_EXE_STATEMENT = ": executional statement was expected."; 
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
//#line 505 "Parser.java"
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
case 32:
//#line 63 "grammar.y"
{yyerror(ERROR_BEGIN);}
break;
case 33:
//#line 64 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 34:
//#line 65 "grammar.y"
{yyerror(ERROR_END);}
break;
case 41:
//#line 78 "grammar.y"
{System.out.println("IF statement at line " + lexer.getLine() + ".");}
break;
case 43:
//#line 80 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 44:
//#line 81 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 45:
//#line 82 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 46:
//#line 83 "grammar.y"
{yyerror(ERROR_CONDITION);}
break;
case 47:
//#line 84 "grammar.y"
{yyerror(ERROR_IF);}
break;
case 48:
//#line 85 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 49:
//#line 86 "grammar.y"
{yyerror(ERROR_END_IF);}
break;
case 50:
//#line 89 "grammar.y"
{System.out.println("IF-ELSE statement at line " + lexer.getLine() + ".");}
break;
case 51:
//#line 90 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 52:
//#line 91 "grammar.y"
{yyerror(ERROR_CONDITION);}
break;
case 53:
//#line 92 "grammar.y"
{yyerror(ERROR_EXE_STATEMENT);}
break;
case 54:
//#line 93 "grammar.y"
{yyerror(ERROR_END_IF);}
break;
case 57:
//#line 101 "grammar.y"
{System.out.println("Assign statement at line " + lexer.getLine() + ".");}
break;
case 58:
//#line 102 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 59:
//#line 103 "grammar.y"
{yyerror(ERROR_ASSIGN);}
break;
case 60:
//#line 104 "grammar.y"
{yyerror(ERROR_EXPRESSION);}
break;
case 61:
//#line 105 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
case 62:
//#line 108 "grammar.y"
{System.out.println("FOREACH statement at line " + lexer.getLine() + ".");}
break;
case 63:
//#line 109 "grammar.y"
{yyerror(ERROR_FOR);}
break;
case 64:
//#line 110 "grammar.y"
{yyerror(ERROR_IDENTIFIER);}
break;
case 65:
//#line 111 "grammar.y"
{yyerror(ERROR_IN);}
break;
case 66:
//#line 112 "grammar.y"
{yyerror(ERROR_COLECTION_ID);}
break;
case 67:
//#line 116 "grammar.y"
{System.out.println("Print statement at line " + lexer.getLine() + ".");}
break;
case 68:
//#line 117 "grammar.y"
{yyerror(ERROR_PRINT);}
break;
case 69:
//#line 118 "grammar.y"
{yyerror(ERROR_BRACKET_OPEN);}
break;
case 70:
//#line 119 "grammar.y"
{yyerror(ERROR_BRACKET_CLOSE);}
break;
case 71:
//#line 120 "grammar.y"
{yyerror(ERROR_BRACKET);}
break;
case 72:
//#line 121 "grammar.y"
{yyerror(ERROR_STRING);}
break;
case 73:
//#line 122 "grammar.y"
{yyerror(ERROR_SEMICOLON);}
break;
//#line 854 "Parser.java"
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
