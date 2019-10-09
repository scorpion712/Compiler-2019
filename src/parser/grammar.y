%{
	package parser;

	import lexer.*;
	import symbol_table.*;
%}

%token IF ELSE END_IF PRINT INT BEGIN END LONG FOREACH IN ID STRING_CONST GREATER_EQUAL LESS_EQUAL EQUAL DISTINCT ASSIGN NUMERIC_CONST 


%left '+' '-'
%left '*' '/'

%%

program	:		/* empty */ {System.out.println("EOF");}
		|		statements
		;

statements	:		declarative_statements  
			|		block_statements
			|		statements 	declarative_statements
			|		statements 	block_statements
			;

declarative_statements	:		variable_declaration_statement  
						|		colection_declaration_statement
						;

variable_declaration_statement	:		type var_list ';'	{System.out.println("Variable declared at line " + lexer.getLine() + ".");}   
								|		error var_list ';' {yyerror(ERROR_TYPE);}
								|		type error ';' {yyerror(ERROR_IDENTIFIER);}   
								|		type var_list ID  ';'{yyerror(ERROR_COMMA);}  
								|		type var_list {yyerror(ERROR_SEMICOLON);}  
								;

colection_declaration_statement :		type colection_list ';' {System.out.println("Colection declared at line " + lexer.getLine() + ".");}
								|		error colection_list ';' {yyerror(ERROR_TYPE);}
								|		type ';' {yyerror(ERROR_COLECTION_ID);}
								|		type colection_list ID ';' {yyerror(ERROR_COMMA);}
								|		type colection_list  {yyerror(ERROR_SEMICOLON);}
								;

colection_list :		ID '['index_list']'
				|		colection_list ',' ID '['index_list']'
				|		error '['index_list']' {yyerror(ERROR_COLECTION_ID);} 
				|		ID index_list error {yyerror(ERROR_SQUARE_BRACKET);}
				|		ID '['index_list error{yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
				|		ID index_list']' {yyerror(ERROR_SQUARE_BRACKET_OPEN);}
				|		ID '['']'  {yyerror(ERROR_INDEX);}
				;

var_list 	:		ID	 
				|		var_list ',' ID     
				;

index_list 	: 		index
			|		index_list ',' index 
			; 

index 	:		'_'
			|		NUMERIC_CONST  
			;  
			
block_statements 	:		BEGIN statement_block END 
					|		error statement_block END {yyerror(ERROR_BEGIN);}
					|		BEGIN error END {yyerror(ERROR_EXE_STATEMENT);}
					|		BEGIN statement_block error {yyerror(ERROR_END);}
					;

statement_block	:		executional_statements
				|		statement_block executional_statements
				;

executional_statements 	:		if_statement
						|		assign_statement
						|		foreach_in_statement
						|		print_statement
						;

if_statement 	:		IF '(' condition ')' executional_block END_IF {System.out.println("IF statement at line " + lexer.getLine() + ".");}
				|		if_else_statement
				|		IF condition ')' executional_block END_IF {yyerror(ERROR_BRACKET_OPEN);}
				|		IF '('condition executional_block END_IF {yyerror(ERROR_BRACKET_CLOSE);}
				|		IF condition executional_block END_IF {yyerror(ERROR_BRACKET);}
				|		IF '('error')' executional_block END_IF {yyerror(ERROR_CONDITION);}
				|		error '('condition')' executional_block END_IF {yyerror(ERROR_IF);}
				|		IF '('condition')' error END_IF  {yyerror(ERROR_EXE_STATEMENT);}
				|		IF '(' condition ')' executional_block error {yyerror(ERROR_END_IF);}  
				;	

if_else_statement	: 		IF '(' condition ')' executional_block ELSE executional_block END_IF {System.out.println("IF-ELSE statement at line " + lexer.getLine() + ".");}
					|		IF '(' condition ')' executional_block ELSE error END_IF {yyerror(ERROR_EXE_STATEMENT);}
					|		IF '('error')' executional_block ELSE executional_block END_IF {yyerror(ERROR_CONDITION);}
					|		IF '('condition')' error ELSE executional_block END_IF {yyerror(ERROR_EXE_STATEMENT);}
					|		IF '(' condition ')' executional_block ELSE executional_block error {yyerror(ERROR_END_IF);}
					;

executional_block 	:		executional_statements
					| 		block_statements
					;

assign_statement	:		ID ASSIGN expression ';' {System.out.println("Assign statement at line " + lexer.getLine() + ".");}
					|		error ASSIGN expression ';' {yyerror(ERROR_IDENTIFIER);}
					|		ID error expression ';' {yyerror(ERROR_ASSIGN);}
					|		ID ASSIGN error ';' {yyerror(ERROR_EXPRESSION);}
					|		ID ASSIGN expression {yyerror(ERROR_SEMICOLON);}
					;

foreach_in_statement	:		FOREACH ID IN ID executional_block {System.out.println("FOREACH statement at line " + lexer.getLine() + ".");}
						|		ID IN ID executional_block {yyerror(ERROR_FOR);}
						|		FOREACH error IN ID executional_block {yyerror(ERROR_IDENTIFIER);}
						|		FOREACH ID error ID executional_block {yyerror(ERROR_IN);}
						|		FOREACH ID IN error executional_block {yyerror(ERROR_COLECTION_ID);}
						|		FOREACH ID IN ID '['{yyerror(ERROR_EXE_STATEMENT);}		
						;

print_statement	:		PRINT '('STRING_CONST')' ';' {System.out.println("Print statement at line " + lexer.getLine() + ".");}
				|		error '('STRING_CONST')' ';' {yyerror(ERROR_PRINT);}
				|		PRINT STRING_CONST')' ';' {yyerror(ERROR_BRACKET_OPEN);}
				|		PRINT '('STRING_CONST ';' {yyerror(ERROR_BRACKET_CLOSE);}
				|		PRINT STRING_CONST ';' {yyerror(ERROR_BRACKET);}
				|		PRINT '('error')' ';' {yyerror(ERROR_STRING);}
				|		PRINT '('STRING_CONST')' {yyerror(ERROR_SEMICOLON);}
				;

condition 	:		expression '<' expression	 
			|		expression '>' expression 	 
			|		expression LESS_EQUAL expression 	 
			| 		expression GREATER_EQUAL expression	 
			| 		expression EQUAL expression			 
			|		expression DISTINCT expression	 
			;

expression	:		expression '+' term
			|		expression '-' term
			|		term
			;

term	:		term '*' factor
		|		term '/' factor
		| 		factor
		;

type	:		INT  
		| 		LONG   
		;

factor 	:		ID 
		|		NUMERIC_CONST 
		|		'-' NUMERIC_CONST
		|		ID'['NUMERIC_CONST']'
		;
 
 %%

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