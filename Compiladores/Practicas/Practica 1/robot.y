//Martinez Coronel Brayan Yosafat
//Practica 1
//YACC basico
//Robot
//16/10/2020
//3CM7

%{
	#include <stdio.h>
	#include <stdlib.h>
	#include <math.h>

	void yyerror (char *s);
	int yylex ();
	void warning(char *s, char *t);
%}

%union {
	struct {int x, y;} sec;
	struct {int dx, dy;} instr;
}

%token<sec> COMIENZA
%token<instr> ESTE
%token<instr> NORTE
%token<instr> OESTE
%token<instr> SUR
%token<sec> TERMINA

%type<sec> sec
%type<instr> instr

//Gramatica con reglas gramaticales
%%
sec	: COMIENZA	{ $$.x = 0; $$.y = 0; }
	| sec instr	{ $$.x = $1.x + $2.dx; 
			  $$.y = $1.y + $2.dy; }
	| sec TERMINA	{ printf("Nuevas coordenadas: %d,%d\n", 
        		  $$.x, $$.y); exit(0);}
	;

instr	: ESTE		{ $$.dx =  1; $$.dy =  0; }
        | NORTE	{ $$.dy =  0; $$.dy =  1; }
        | OESTE	{ $$.dx = -1; $$.dy =  0; }
        | SUR		{ $$.dx =  0; $$.dy = -1; }
	;
%%

#include <stdio.h>
#include <ctype.h>

char *progname;
int lineno = 1;

void main (int argc, char *argv[]){
	progname=argv[0];
  	yyparse();
}

int yylex (){
  	int c;

  	while ((c = getchar()) == ' ' || c == '\t');
  	
 	if (c == EOF)                           
    		return 0;
    		
  	if (c == 'C')
	      return COMIENZA;
        else if (c == 'E')
	      return ESTE;
        else if (c == 'N')
	      return NORTE;
        else if (c == 'O')
	      return OESTE;
        else if (c == 'S')
	      return SUR;
    	else if (c == '\n')
		lineno++;
	
	return TERMINA;
}

void yyerror (char *s) {
	warning(s, (char *) 0);
}

void warning(char *s, char *t){
	fprintf (stderr, "%s: %s", progname, s);
	
	if(t)
		fprintf (stderr, " %s", t);
	
	fprintf (stderr, ". Cerca de la linea %d\n", lineno);
}