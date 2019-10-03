%{
	// imports go here
%}

%token IF ELSE END_IF PRINT INT BEGIN END LONG FOR_EACH IN ID STRING_CONST GREATER_EQUAL LESS_EQUAL EQUAL DISTINCT ASSIGN NUMERIC_CONST GREATER_THAN LESS_THAN

%start program

%%

program	:	statements
		;

statements	:	declarative_statements
			|	BEGIN executional_statements
			|	statements 	declarative_statements
			|	statements 	BEGIN executional_statements END
			;

declarative_statements	:	variable_declaration_statement
						|	colection_declaration_statement
						;

variable_declaration_statement	:	type 	variable_list 	';'
								;

colection_declaration_statement :	type ID '[' initial_value_list ']' ';'
								;

variable_list 	:	ID
				|	variable_list ',' ID
				;

initial_value_list 	: 	NUMERIC_CONST
					|	value_list
					;

value_list 	:	NUMERIC_CONST
			|	'_'
			|	value_list ',' NUMERIC_CONST
			| 	value_list ',' '_'
			;

executional_statements 	:	if_statement
						|	assign_statement
						|	for_each_in_statemet
						|	print_statement
						;

if_statement 	:	IF '(' condition ')' block_statements END_IF
				|	if_else_statement
				;

if_else_statement	: 	IF '(' condition ')' block_statements ELSE block_statements END_IF
					;

block_statements 	:	executional_statements
					|	BEGIN explicit_block_statements	END
					;

explicit_block_statements	:	executional_statements
							| explicit_block_statements ';' executional_statements
							;

assign_statement	:	ID ASSIGN expression
					;

for_each_in_statement	:	FOR ID IN ID block_statements
						;

print_statement	:	PRINT '('STRING_CONST')' ';'
				;

condition 	:	expression LESS_THAN expression
			|	expression GREATER_THAN expression
			|	expression LESS_EQUAL expression
			| 	expression GREATER_EQUAL expression
			| 	expression EQUAL expression
			|	expression DISTINCT expression
			;

expression	:	expression '+' term
			|	expression '-' term
			|	term
			;

term	:	term '*' factor
		|	term '/' factor
		| 	factor
		;

type	:	INT
		| 	LONG
		;

factor 	:	ID
		|	NUMERIC_CONST
		|	ID '['NUMERIC_CONST']'
		;

type	:	INT
		|	LONG
		;