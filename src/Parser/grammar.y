%{
	package Parser;

	import analizadorLexico.*;
	import globales.*;
%}

%token IF ELSE END_IF PRINT INT BEGIN END LONG FOR_EACH IN ID STRING_CONST GREATER_EQUAL LESS_EQUAL EQUAL DISTINCT ASSIGN NUMERIC_CONST 


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
								|		type var_list ID {yyerror(ERROR_COMMA);}  
								|		type var_list {yyerror(ERROR_SEMICOLON);}  
								;

colection_declaration_statement :		type ID '['index_list']'';' {System.out.println("Colection declared at line " + lexer.getLine() + ".");} 
								|		error ID '['index_list']' ';' {yyerror(ERROR_TYPE);}
								|		type '['index_list']' ';' {yyerror(ERROR_IDENTIFIER);}
								|		type ID '['index_list']' {yyerror(ERROR_SEMICOLON);}
								|		type ID '['index_list';' {yyerror(ERROR_SQUARE_BRACKET_CLOSE);}
								|		type ID index_list']'';' {yyerror(ERROR_SQUARE_BRACKET_OPEN);}
								|		type ID '['index_list']'',' {yyerror(ERROR_COLECTION);}
								;

var_list 	:		ID	 
				|		var_list ',' ID     
				;

index_list 	: 		index
			|		index_list ',' index 
			|		index_list index {yyerror(ERROR_COMMA);}
			; 

index 	:		'_'
			|		NUMERIC_CONST  
			;  
			
block_statements 	:		BEGIN statement_block	END
					;

statement_block	:		executional_statements
				|		statement_block executional_statements
				;

executional_statements 	:		if_statement
						|		assign_statement
						|		for_each_in_statement
						|		print_statement
						;

if_statement 	:		IF '(' condition ')' executional_block END_IF
				|		if_else_statement
				;	

if_else_statement	: 		IF '(' condition ')' executional_block ELSE executional_block END_IF
					;

executional_block 	:		executional_statements
					| 		block_statements
					;

assign_statement	:		ID ASSIGN expression ';'
					;

for_each_in_statement	:		FOR_EACH ID IN ID executional_block
						;

print_statement	:		PRINT '('STRING_CONST')' ';'
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
		;
 
 %%

 	// Constants declared to print error messages
 	private static final String ERROR_IDENTIFIER = ": identifier expected.";
 	private static final String ERROR_IDENTIFIER_LIST = ": identifier list expected.";
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
 	private static final String ERROR_NUMERIC_CONST = ": numeric const or '_' expected.";

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