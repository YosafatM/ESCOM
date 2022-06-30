%{
	import java.lang.Math;
	import java.io.*;
	import java.util.StringTokenizer;
	import Configuracion.Configuracion;
%}


%token IF
%token ELSE
%token WHILE
%token FOR
%token COMP
%token DIFERENTES
%token MAY
%token MEN
%token MAYI
%token MENI
%token FNCT
%token NUMBER
%token VAR
%token AND
%token OR
%token FUNC
%token RETURN
%token PARAMETRO
%token PROC
%right '='
%left '+' '-'
%left '*' '/'
%left ';'
%left COMP
%left DIFERENTES
%left MAY
%left MAYI
%left MEN
%left MENI
%left '!'
%left AND
%left OR
%right RETURN
%%

	list:   
		| list'\n'
	  	| list linea '\n'
		;

	linea: exp ';' {$$ = $1;}
		|stmt {$$ = $1;}
		|linea exp ';' {$$ = $1;}
		|linea stmt {$$ = $1;}
		;
		
	exp:  VAR {				
				$$ = new ParserVal(maquina.agregarOperacion("varPush_Eval")); 
				maquina.agregar($1.sval);
			}
		|'-' exp {
				$$ = new ParserVal(maquina.agregarOperacion("negativo"));
			}
		|NUMBER {
				$$ = new ParserVal(maquina.agregarOperacion("constPush"));
				maquina.agregar($1.dval);
			}
		| VAR '=' exp {
				$$ = new ParserVal($3.ival);
				maquina.agregarOperacion("varPush");
		        maquina.agregar($1.sval);
		        maquina.agregarOperacion("asignar");
		        maquina.agregarOperacion("varPush_Eval"); 
				maquina.agregar($1.sval);
			}
		| exp '*' exp {
				$$ = new ParserVal($1.ival);
				maquina.agregarOperacion("MUL");
			}
                | exp '/' exp {
				$$ = new ParserVal($1.ival);
				maquina.agregarOperacion("DIV");
			}
		| exp '+' exp {
				$$ = new ParserVal($1.ival);
				maquina.agregarOperacion("SUM");
			}
		| exp '-' exp {
				$$ = new ParserVal($1.ival);
				maquina.agregarOperacion("RES");
			}
		|'(' exp ')' {
				$$ = new ParserVal($2.ival);
			}
		| exp COMP exp {
				 maquina.agregarOperacion("EQ");
				 $$ = $1;
			}
		| exp DIFERENTES exp {
				 maquina.agregarOperacion("NE");
				 $$ = $1;
			}
		| exp MEN exp {
				 maquina.agregarOperacion("LE");
				 $$ = $1;
			}
		| exp MENI exp {
				 maquina.agregarOperacion("LQ");
				 $$ = $1;
			}
		| exp MAY exp {
				 maquina.agregarOperacion("GR");
				 $$ = $1;
			}
		| exp MAYI exp {
				 maquina.agregarOperacion("GE");
				 $$ = $1;
			}
		| exp AND exp {
				maquina.agregarOperacion("AND");
				 $$ = $1;
			}
		| exp OR exp {
				maquina.agregarOperacion("OR");
				 $$ = $1;
			}
		| '!' exp {
				maquina.agregarOperacion("NOT");
				$$ = $2;
			}
		| RETURN exp { $$ = $2; maquina.agregarOperacion("_return"); } 
		
		| PARAMETRO { $$ = new ParserVal(maquina.agregarOperacion("push_parametro")); maquina.agregar((int)$1.ival); }
		
		|nombreProc '(' arglist ')' { $$ = new ParserVal(maquina.agregarOperacionEn("invocar",($1.ival))); maquina.agregar(null); } //instrucciones tiene la estructura necesaria para la lista de argumentos
		;
		
	arglist: 
		|exp {$$ = $1; maquina.agregar("Limite");}
		|arglist ',' exp {$$ = $1; maquina.agregar("Limite");}
		;
	
	nop: {$$ = new ParserVal(maquina.agregarOperacion("nop"));}
		;
	
	stmt:if '(' exp stop ')' '{' linea stop '}' ELSE '{' linea stop'}' {
				$$ = $1;
				maquina.agregar($7.ival, $1.ival + 1);
				maquina.agregar($12.ival, $1.ival + 2);
				maquina.agregar(maquina.numeroDeElementos() - 1, $1.ival + 3);
			}
		| if '(' exp stop ')' '{' linea stop '}' nop stop{
				$$ = $1;
				maquina.agregar($7.ival, $1.ival + 1);
				maquina.agregar($10.ival, $1.ival + 2);
				maquina.agregar(maquina.numeroDeElementos() - 1, $1.ival + 3);
			}
		| while '(' exp stop ')' '{' linea stop '}' stop{
				$$ = $1;
				maquina.agregar($7.ival, $1.ival + 1);
				maquina.agregar($10.ival, $1.ival + 2);
			}
		| for '(' instrucciones stop ';' exp stop ';' instrucciones stop ')' '{' linea stop '}' stop{
				$$ = $1;
				maquina.agregar($6.ival, $1.ival + 1);
				maquina.agregar($9.ival, $1.ival + 2);
				maquina.agregar($13.ival, $1.ival + 3);
				maquina.agregar($16.ival, $1.ival + 4);
			}
		| funcion nombreProc '(' ')' '{' linea null '}'
		| procedimiento nombreProc '(' ')' '{' linea null '}'
		| instruccion '[' arglist ']' ';' { 
				$$ = new ParserVal($1.ival);
				maquina.agregar(null);
			}
		;
	instruccion: FNCT {
			$$ = new ParserVal(maquina.agregar((Funcion)($1.obj)));
			}
		;

	procedimiento: PROC { maquina.agregarOperacion("declaracion"); }
		;
	funcion: FUNC { maquina.agregarOperacion("declaracion"); }
		;
		
	nombreProc: VAR {$$ = new ParserVal(maquina.agregar($1.sval));}
		;
		
	null: {maquina.agregar(null);}
		;
		
	stop: {$$ = new ParserVal(maquina.agregarOperacion("stop"));} 
		;

	if: IF {
			$$ = new ParserVal(maquina.agregarOperacion("IF_ELSE"));
	        maquina.agregarOperacion("stop");//then
	        maquina.agregarOperacion("stop");//else
	        maquina.agregarOperacion("stop");//siguiente comando
		}
		;

	while: WHILE {
			$$ = new ParserVal(maquina.agregarOperacion("WHILE"));
	        maquina.agregarOperacion("stop");//cuerpo
	        maquina.agregarOperacion("stop");//final
		}
		;

	for : FOR {
			$$ = new ParserVal(maquina.agregarOperacion("FOR"));
	        maquina.agregarOperacion("stop");//condicion
	        maquina.agregarOperacion("stop");//instrucción final
	        maquina.agregarOperacion("stop");//cuerpo
	        maquina.agregarOperacion("stop");//final
		}		

	instrucciones: { $$ = new ParserVal(maquina.agregarOperacion("nop"));}
		|exp {$$ = $1;}
		|instrucciones ',' exp {$$ = $1;}
		;



%%



TablaDeSimbolos tablaDeSimbolos = new TablaDeSimbolos();
MaquinaDePila maquina = new MaquinaDePila(tablaDeSimbolos);
int i = 0;
int j = 0;
double[][] auxiliar;
Funcion funcionAux;
boolean huboError;

String ins;
StringTokenizer st;

void yyerror(String s){
	huboError = true;
	System.out.println("error:"+s);
        System.exit(0);
}

boolean newline;
int yylex(){
	String s;
	int tok = 0;
	Double d;
	if (!st.hasMoreTokens()){
		if (!newline){
			newline=true;
			return '\n'; //So we look like classic YACC example
		}
		else
			return 0;
	}
	s = st.nextToken();
	try{
		d = Double.valueOf(s);/*this may fail*/
		yylval = new ParserVal(d.doubleValue()); //SEE BELOW
		return NUMBER;
	}
	catch (Exception e){}
	if(esVariable(s)){
		if(s.equals("proc")){
			return PROC;
		}
		if(s.charAt(0) == '$'){
			yylval = new ParserVal((int)Integer.parseInt(s.substring(1)));
			return PARAMETRO;
		}
		if(s.equals("return")){
			return RETURN;
		}
		if(s.equals("func")){
			return FUNC;
		}
		if(s.equals("if")){
			return IF;
		}
		if(s.equals("else")){
			return ELSE;
		}
		if(s.equals("while")){
			return WHILE;
		}
		if(s.equals("for")){
			return FOR;
		}
		boolean esFuncion = false;
		Object objeto = tablaDeSimbolos.encontrar(s);
		if(objeto instanceof Funcion){
			funcionAux = (Funcion)objeto;
			yylval = new ParserVal(objeto);
			esFuncion = true;
			return FNCT;
		}
		if(!esFuncion){
			yylval = new ParserVal(s);
			return VAR;
		}
	}
	else{
            if(s.equals("==")){
                return COMP;
            }
            if(s.equals("!=")){
                    return DIFERENTES;
            }
            if(s.equals("<")){
                    return MEN;
            }
            if(s.equals("<=")){
                    return MENI;
            }
            if(s.equals(">")){
                    return MAY;
            }
            if(s.equals(">=")){
                    return MAYI;
            }
            if(s.equals("&&")){
                    return AND;
            }
            if(s.equals("||")){
                    return OR;
            }
            tok = s.charAt(0);
	}
	//System.out.println("Token: " + tok);
	return tok;
}

    String reservados[] = {">=", "&&", "||", "<=","==", "!=", "=", "{", "}", ",", "*","+", "-", "(", ")", "|", "[", "]", ";", "!", "<", ">","/"};
public String ajustarCadena(String cadena){
    String nueva = "";
    boolean encontrado = false;
    for(int i = 0; i < cadena.length() - 1; i++){
        encontrado = false;
        for(int j = 0; j < reservados.length; j++){
            if(cadena.substring(i, i + reservados[j].length()).equals(reservados[j])){
                i += reservados[j].length()-1;
                nueva += " " + reservados[j] + " ";
                encontrado = true;
                break;
            }
        }
        if(!encontrado)
            nueva += cadena.charAt(i);
    }
    nueva += cadena.charAt(cadena.length()-1);
    return nueva;
}

boolean esVariable(String s){
	boolean cumple = true;
	for(int i = 0; i < reservados.length; i++)
		if(s.equals(reservados[i]))
			cumple = false;
	return cumple;
}
public void insertarInstrucciones(){
	tablaDeSimbolos.insertar("TURN", new MaquinaDePila.Girar());
	tablaDeSimbolos.insertar("FORWARD", new MaquinaDePila.Avanzar());
	tablaDeSimbolos.insertar("COLOR", new MaquinaDePila.CambiarColor());
        tablaDeSimbolos.insertar("PenUP", new MaquinaDePila.SubirPincel());
        tablaDeSimbolos.insertar("PenDOWN", new MaquinaDePila.BajarPincel());

}


public Configuracion ejecutarCodigo(String codigo){
    st = new StringTokenizer(ajustarCadena(codigo));
    newline=false;
    yyparse();
    if(!huboError)
            maquina.ejecutar();
    return maquina.getConfiguracion();
} 

public boolean compilar(String codigo){
    st = new StringTokenizer(ajustarCadena(codigo));
    newline=false;
    yyparse();
    return !huboError;
}

public boolean ejecutarSiguiente(){
    return maquina.ejecutarSiguiente();
}

public Configuracion getConfiguracion(){
    return maquina.getConfiguracion();
}

public void limpiar(){
    tablaDeSimbolos = new TablaDeSimbolos();
    insertarInstrucciones();
    maquina = new MaquinaDePila(tablaDeSimbolos);
}

public Configuracion ejecutar(){
    maquina.ejecutar();
    return maquina.getConfiguracion();
}

void dotest() throws Exception{
	insertarInstrucciones();
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	while (true){
		huboError = false;
		try{
			ins = ajustarCadena(in.readLine());
		}
		catch (Exception e){}
		st = new StringTokenizer(ins);
		newline=false;
		//maquina = new MaquinaDePila(tablaDeSimbolos);
		yyparse();
		if(!huboError)
			maquina.ejecutar();
	}
}

public static void main(String args[]) throws Exception{
	Parser par = new Parser(false);
	par.dotest();
}
