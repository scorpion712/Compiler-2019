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

variable_declaration_statement	:		type var_list ';'	{System.out.println("variable declaration ok. Must print which variables were declared.");}   
								|		type error ';' {System.out.println("Error. ID or ID list not declared.");}
								|		error var_list {System.out.println("Error. Type is not defined.");}
								;

colection_declaration_statement :		type ID '['initial_value_list']' ';'  
								;

var_list 	:		ID	 
				|		error ';' {System.out.println("Error: Identifier not found.");}
				|		var_list ',' ID  
				;

initial_value_list 	: 		 NUMERIC_CONST 	 
					|		 index_list 
					;

index_list 	: 		index_list ',' index
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
		|		ID '['NUMERIC_CONST']' 
		;
 
 %%

	public Parser(LexerAnalyzer lexer) {
        this.lexer = lexer;
        //yydebug=true; // uncomment this line to show debug en console
	}
    private LexerAnalyzer lexer;

    private int yylex() {
        
        Token token = this.lexer.getToken(); 
        if (token != null) { 
            this.yylval = new ParserVal(token.getLexeme());
            return token.getID();
        } 
	return 0; 
    } 
    
    private void yyerror(String stack_underflow_aborting) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("!!! Error: " +stack_underflow_aborting);
    }
