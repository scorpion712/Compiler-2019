#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "grammar.y"
	package Parser;

	import analizadorLexico.*;
	import globales.*;
#line 11 "y.tab.c"
#define IF 257
#define ELSE 258
#define END_IF 259
#define PRINT 260
#define INT 261
#define BEGIN 262
#define END 263
#define LONG 264
#define FOR_EACH 265
#define IN 266
#define ID 267
#define STRING_CONST 268
#define GREATER_EQUAL 269
#define LESS_EQUAL 270
#define EQUAL 271
#define DISTINCT 272
#define ASSIGN 273
#define NUMERIC_CONST 274
#define GREATER_THAN 275
#define LESS_THAN 276
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    0,    1,    1,    1,    1,    2,    2,    4,    5,
    7,    7,    8,    8,    9,   10,   10,    3,   11,   11,
   12,   12,   12,   12,   13,   13,   19,   18,   18,   14,
   15,   16,   17,   17,   17,   17,   17,   17,   20,   20,
   20,   21,   21,   21,    6,    6,   22,   22,   22,
};
short yylen[] = {                                         2,
    0,    1,    1,    1,    2,    2,    1,    1,    3,    6,
    1,    3,    1,    1,    3,    1,    1,    3,    1,    2,
    1,    1,    1,    1,    6,    1,    8,    1,    1,    4,
    5,    5,    3,    3,    3,    3,    3,    3,    3,    3,
    1,    3,    3,    1,    1,    1,    1,    1,    4,
};
short yydefred[] = {                                      0,
   45,    0,   46,    0,    0,    3,    4,    7,    8,    0,
    0,    0,    0,    0,    0,   19,   21,   22,   23,   24,
   26,    5,    6,    0,    0,    0,    0,    0,    0,   18,
   20,    0,    9,    0,    0,   48,    0,    0,    0,   44,
    0,    0,    0,   13,    0,    0,   12,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   30,    0,    0,    0,   29,   28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   42,   43,   32,   31,
   10,   17,   16,   15,   49,    0,   25,    0,   27,
};
short yydgoto[] = {                                       4,
    5,    6,   66,    8,    9,   10,   25,   45,   46,   84,
   15,   67,   17,   18,   19,   20,   37,   68,   21,   38,
   39,   40,
};
short yysindex[] = {                                   -192,
    0, -194,    0,    0, -192,    0,    0,    0,    0, -253,
    2,   21, -233, -208, -207,    0,    0,    0,    0,    0,
    0,    0,    0,  -51,  -37, -254, -190, -189, -254,    0,
    0, -198,    0, -188,  -11,    0,   40,   -7,  -31,    0,
   41, -184,   -4,    0,   -9,   42,    0, -187, -203, -254,
 -254, -254, -254, -254, -254, -254, -254, -254, -254,   26,
 -203,    0,   29,  -92,   -3,    0,    0, -191,  -12,  -12,
  -12,  -12,  -12,  -12,  -31,  -31,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -203,    0, -170,    0,
};
short yyrindex[] = {                                     91,
    0,    0,    0,    0,   92,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -35,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -41,    0,    0,    0,  -33,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   52,   54,
   55,   56,   57,   58,  -24,  -16,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,   95,   32,    0,    0,    0,    0,    0,    0,    0,
    0,   13,    0,    0,    0,    0,    0,  -56,    0,   -6,
   -5,   16,
};
#define YYTABLESIZE 269
short yytable[] = {                                      47,
   47,   47,   83,   47,   80,   47,   34,   41,   11,   41,
   58,   41,   35,   24,   16,   59,   39,   47,   39,   36,
   39,   33,   43,   11,   40,   41,   40,   31,   40,   88,
   56,    7,   57,   28,   39,   56,   23,   57,   56,   32,
   57,   26,   40,   69,   70,   71,   72,   73,   74,   11,
   75,   76,   12,   11,   62,   30,   12,   13,    2,   14,
   27,   13,   11,   14,   29,   12,   86,   87,    1,    2,
   13,    3,   14,   77,   78,   44,   42,   41,   47,   48,
   49,   60,   61,   63,   79,   64,   65,   81,   89,   85,
    1,    2,   36,   14,   35,   37,   38,   34,   33,   22,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   82,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   47,   47,   47,
   47,    0,    0,   47,   47,   41,   41,   41,   41,    0,
    0,   41,   41,    0,   39,   39,   39,   39,    0,    0,
   39,   39,   40,   40,   40,   40,    0,    0,   40,   40,
    0,   50,   51,   52,   53,    0,    0,   54,   55,
};
short yycheck[] = {                                      41,
   42,   43,   95,   45,   61,   47,   44,   41,   44,   43,
   42,   45,  267,  267,    2,   47,   41,   59,   43,  274,
   45,   59,   29,   59,   41,   59,   43,   15,   45,   86,
   43,    0,   45,  267,   59,   43,    5,   45,   43,   91,
   45,   40,   59,   50,   51,   52,   53,   54,   55,  257,
   56,   57,  260,  257,   59,  263,  260,  265,  262,  267,
   40,  265,  257,  267,  273,  260,  258,  259,  261,  262,
  265,  264,  267,   58,   59,  274,  266,  268,  267,   91,
   41,   41,  267,   93,   59,   44,  274,   59,  259,   93,
    0,    0,   41,   93,   41,   41,   41,   41,   41,    5,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
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
#define YYFINAL 4
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 276
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,0,
"';'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'['",0,
"']'",0,"'_'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,"IF","ELSE","END_IF","PRINT","INT","BEGIN","END","LONG",
"FOR_EACH","IN","ID","STRING_CONST","GREATER_EQUAL","LESS_EQUAL","EQUAL",
"DISTINCT","ASSIGN","NUMERIC_CONST","GREATER_THAN","LESS_THAN",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 16 "grammar.y"
{System.out.println("EOF");}
break;
case 2:
#line 17 "grammar.y"
{System.out.println("statements");}
break;
case 3:
#line 20 "grammar.y"
{System.out.println("declarat");}
break;
case 7:
#line 26 "grammar.y"
{System.out.println("var decl state");}
break;
case 9:
#line 30 "grammar.y"
{System.out.println("variable declaration");}
break;
case 10:
#line 33 "grammar.y"
{System.out.println("colection declaration ");}
break;
case 11:
#line 36 "grammar.y"
{System.out.println("ID aca");}
break;
case 12:
#line 37 "grammar.y"
{System.out.println("var list");}
break;
case 13:
#line 40 "grammar.y"
{System.out.println("Numeric const : ");}
break;
case 33:
#line 84 "grammar.y"
{System.out.println("Expresion " + yyvsp[-3]);}
break;
case 34:
#line 85 "grammar.y"
{System.out.println("Expresion " + yyvsp[-3] + " > " + yyvsp[-1]);}
break;
case 35:
#line 86 "grammar.y"
{System.out.println("Expresion " + yyvsp[-3] + " <= " + yyvsp[-1]);}
break;
case 36:
#line 87 "grammar.y"
{System.out.println("Expresion " + yyvsp[-3] + " <= " + yyvsp[-1]);}
break;
case 37:
#line 88 "grammar.y"
{System.out.println("Expresion " + yyvsp[-3] + " == " + yyvsp[-1]);}
break;
case 38:
#line 89 "grammar.y"
{System.out.println("Expresion " + yyvsp[-3] + " <> " + yyvsp[-1]);}
break;
case 45:
#line 102 "grammar.y"
{System.out.println("Soy tipo INT");}
break;
case 46:
#line 103 "grammar.y"
{System.out.println("LONG");}
break;
#line 455 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
