%{
	package Parser;

	import analizadorLexico.*;
	import globales.*;
%}

%token IF ELSE END_IF PRINT INT BEGIN END LONG FOR_EACH IN ID STRING_CONST GREATER_EQUAL LESS_EQUAL EQUAL DISTINCT ASSIGN NUMERIC_CONST GREATER_THAN LESS_THAN


%left '+' '-'
%left '*' '/'

%%

program	:		/* empty */ {System.out.println("EOF");}
		|		statements {System.out.println("statements");}
		;

statements	:		declarative_statements {System.out.println("declarat");}
			|		block_statements
			|		statements 	declarative_statements
			|		statements 	block_statements
			;

declarative_statements	:		variable_declaration_statement {System.out.println("var decl state");}
						|		colection_declaration_statement
						;

variable_declaration_statement	:		type variable_list ';'{System.out.println("variable declaration");}
								|		error variable_list';' {System.out.println("Error. Variable type not declared.");}
								|		type error ';' {System.out.println("Error. ID or ID list not declared.");}
								;

colection_declaration_statement :		type ID '['initial_value_list']' ';' {System.out.println("colection declaration ");}
								;

variable_list 	:		ID	{System.out.println("ID aca");}
				|		error ';' {System.out.println("Error: No se encontro identificador.");}
				|		variable_list ',' ID {System.out.println("var list");}
				;

initial_value_list 	: 		 NUMERIC_CONST 	{System.out.println("Numeric const : ");}
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

condition 	:		expression LESS_THAN expression	 {System.out.println("Expresion " + $0);}
			|		expression GREATER_THAN expression 	{System.out.println("Expresion " + $0 + " > " + $2);}
			|		expression LESS_EQUAL expression 	{System.out.println("Expresion " + $0 + " <= " + $2);}
			| 		expression GREATER_EQUAL expression	{System.out.println("Expresion " + $0 + " <= " + $2);}
			| 		expression EQUAL expression			{System.out.println("Expresion " + $0 + " == " + $2);}
			|		expression DISTINCT expression		{System.out.println("Expresion " + $0 + " <> " + $2);}
			;

expression	:		expression '+' term
			|		expression '-' term
			|		term
			;

term	:		term '*' factor
		|		term '/' factor
		| 		factor
		;

type	:		INT {System.out.println("Soy tipo INT");}
		| 		LONG {System.out.println("LONG");}
		;

factor 	:		ID 
		|		NUMERIC_CONST
		|		ID '['NUMERIC_CONST']' 
		;
 