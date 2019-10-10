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

variable_declaration_statement	:		type var_list ';'	{System.out.println("Variable declared at line " + $3.end_line + ".");}   
								|		error var_list ';' {yyerror(ERROR_TYPE);}
								|		type error ';' {yyerror(ERROR_IDENTIFIER);}   
								|		type var_list ID  ';'{yyerror(ERROR_COMMA);}  
								|		type var_list {yyerror(ERROR_SEMICOLON);}  
								;

colection_declaration_statement :		type ID '['index_list']'';' {System.out.println("Colection declared at line " + $6.end_line + ".");}
								|		error ID '['index_list']'';' {yyerror(ERROR_TYPE);}
								|		type error '['index_list']'';' {yyerror(ERROR_COLECTION_ID);}
								|		type ID index_list']'';' {yyerror(ERROR_SQUARE_BRACKET_OPEN);}
								|		type ID '['']'';' {yyerror(ERROR_INDEX);}
								|		type ID '['error']'';' {yyerror(ERROR_INDEX);}
								|		type ID '['index_list error ';' {yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
								|		type ID index_list ';' {yyerror(ERROR_SQUARE_BRACKET);}
								|		type ID '['index_list']' error {yyerror(ERROR_SEMICOLON);}
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
						|		foreach_in_statement error {yyerror("Foreach error");}
						|		print_statement
						;

if_statement 	:		IF '(' condition ')' executional_block END_IF {System.out.println("IF statement at line " + $1.end_line + ".");}
				|		if_else_statement
				|		IF error condition ')' executional_block END_IF {yyerror(ERROR_BRACKET_OPEN, $3.begin_line);}
				|		IF condition ')' executional_block END_IF {yyerror(ERROR_BRACKET_OPEN, $3.begin_line);}
				|		IF '('condition executional_block END_IF {yyerror(ERROR_BRACKET_CLOSE, $2.begin_line);}
				|		IF '('condition error executional_block END_IF {yyerror(ERROR_BRACKET_CLOSE, $2.begin_line);}
				|		IF condition executional_block END_IF {yyerror(ERROR_BRACKET, $2.begin_line);}
				|		IF '('error')' executional_block END_IF {yyerror(ERROR_CONDITION, $2.begin_line);}
				|		error '('condition')' executional_block END_IF {yyerror(ERROR_IF, $2.begin_line);}
				|		IF '('condition')' error END_IF  {yyerror(ERROR_EXE_STATEMENT, $4.begin_line);}
				|		IF '('condition')' END_IF  {yyerror(ERROR_EXE_STATEMENT, $4.begin_line);}    
				;	

if_else_statement	: 		IF '(' condition ')' executional_block ELSE executional_block END_IF {System.out.println("IF-ELSE statement at line " + $1.end_line + ".");}
					|		IF '('error')' executional_block ELSE executional_block END_IF {yyerror(ERROR_CONDITION, $2.begin_line);}
					|		IF '('')' executional_block ELSE executional_block END_IF {yyerror(ERROR_CONDITION, $2.begin_line);}
					|		IF condition ')' executional_block ELSE executional_block END_IF {yyerror(ERROR_BRACKET_OPEN, $3.begin_line);}
					|		IF error condition ')' executional_block ELSE executional_block END_IF {yyerror(ERROR_BRACKET_OPEN, $3.begin_line);}
					|		IF '(' condition executional_block ELSE executional_block END_IF {yyerror(ERROR_BRACKET_CLOSE, $2.begin_line);}
					|		IF '(' condition error executional_block ELSE executional_block END_IF {yyerror(ERROR_BRACKET_CLOSE, $2.begin_line);}
					|		IF '(' condition ')' ELSE executional_block END_IF {yyerror(ERROR_EXE_STATEMENT, $4.begin_line);}
					|		IF '(' condition ')' error ELSE executional_block END_IF {yyerror(ERROR_EXE_BLOCK_STATEMENT, $4.begin_line);} 
					|		IF '(' condition ')' executional_block error executional_block END_IF {yyerror(ERROR_BEGIN, $5.begin_line);} 
					|		IF '(' condition ')' executional_block executional_block END_IF {yyerror(ERROR_ELSE, $6.begin_line);} 
					|		IF '(' condition ')' executional_block ELSE error END_IF {yyerror(ERROR_EXE_BLOCK_STATEMENT, $6.begin_line);} 
					|		IF '(' condition ')' executional_block ELSE  END_IF {yyerror(ERROR_EXE_BLOCK_STATEMENT, $6.begin_line);} 
					|		IF '(' condition ')' executional_block ELSE executional_block error {yyerror(ERROR_END_IF, $7.begin_line);} 
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
					//	|		FOREACH ID IN  {yyerror(ERROR_EXE_STATEMENT);}		
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