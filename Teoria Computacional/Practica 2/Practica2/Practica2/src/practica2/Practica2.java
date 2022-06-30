package practica2;

import java.util.Scanner;

public class Practica2 {
    private Scanner leer;
    private Alfabeto Sigma;
    private Lenguaje Leng1, Leng2;
    
    public Practica2(){
        leer = new Scanner(System.in);
        Sigma = null;
        int longitud;
        int numP;
        int respuesta;
        String respuestaS;
        Palabra palab;
               
        
        System.out.println("\n Bienvenido al sistema");
        System.out.println("Para empezar, defina un alfabeto con una de las siguientes opciones");
        System.out.println("1) Por Símbolos");
        System.out.println("2) Por Rango");
        respuesta = leer.nextInt();

        switch(respuesta){
            case 1: 
                Sigma = alfabetoPorCaracter();
            break;
            case 2: 
                Sigma = alfabetoPorRango();
            break;
        }
        System.out.print("Alfabeto Sigma = ");
        Sigma.imprimirSimbolos();
        for (int i = 0; i < 2; i++) {
            System.out.println("\n Con base en Sigma, ingresa el numero de palabras con las que se va a crear el lenguaje "+(i+1));
            numP = leer.nextInt();
            System.out.println("Ingrese la longitud de las palabras ");
            longitud = leer.nextInt();
            if (i==0)
                Leng1 = Sigma.crearLenguaje(numP, longitud);
            else
                Leng2 = Sigma.crearLenguaje(numP, longitud);
        }
        System.out.println("Los lenguajes generados son: ");
        System.out.print("Lenguaje 1: ");
        Leng1.imprimirPalabras();
        System.out.println("");
        System.out.print("Lenguaje 2: ");
        Leng2.imprimirPalabras();
        System.out.println("");
        boolean cont = true;
        
        while(cont){
            boolean cont1 = true;
            boolean cont2 = true;
            while(cont1){            
                System.out.println("Desea agregar una palabra a algun lenguaje? (s/n)");
                respuestaS = leer.next();
                if(respuestaS.equals("s")){
                    do{
                        System.out.println("Escriba la palabra que va a agregar");
                        palab = Sigma.crearPalabra(leer.next());
                    } while (palab == null);
                    System.out.println("En qué lenguaje? (1/2)");
                    respuesta = leer.nextInt();
                    if (respuesta == 1){
                        Leng1.agregarPalabra(palab);
                        System.out.println("Palabra agregada");
                        System.out.print("Lenguaje 1: ");
                        Leng1.imprimirPalabras();
                    }
                    else{
                        Leng2.agregarPalabra(palab);
                        System.out.println("Palabra agregada");
                        System.out.print("Lenguaje 2: ");
                        Leng2.imprimirPalabras();
                    }
                }
                else cont1 = false;
            }
        
            while(cont2 = true){
                System.out.println("\nElija la opción deseada");
                System.out.println("1) Unión de Lenguajes");
                System.out.println("2) Diferencia");
                System.out.println("3) Concatenación");
                System.out.println("4) Potencia");
                System.out.println("5) CURP aleatoria");
                System.out.println("6) Verificar palabra con vocales en orden");
                System.out.println("7) salir");
                respuesta = leer.nextInt();
                switch (respuesta){
                    case 1: 
                        System.out.print("Lenguaje 1: ");
                        Leng1.imprimirPalabras();
                        System.out.print("\nLenguaje 2: ");
                        Leng2.imprimirPalabras();
                        System.out.println("\nUnión de los lenguajes: ");
                        Lenguaje.Union(Leng1, Leng2).imprimirPalabras();
                        System.out.println("");
                    break;
                    case 2:
                        System.out.print("Lenguaje 1: ");
                        Leng1.imprimirPalabras();
                        System.out.print("\nLenguaje 2: ");
                        Leng2.imprimirPalabras();
                        System.out.println("\nSeleccione el lenguaje del que va a obtener la diferencia");
                        respuesta = leer.nextInt();
                        if (respuesta==1) {
                            System.out.println("La diferencia es: ");
                            Leng1.Resta(Leng2).imprimirPalabras();
                            System.out.println("");                        
                        }
                        else {
                            System.out.println("La diferencia es: ");
                            Leng2.Resta(Leng1).imprimirPalabras();
                            System.out.println("");    
                        }
                    break;
                    case 3:
                        System.out.print("Lenguaje 1: ");
                        Leng1.imprimirPalabras();
                        System.out.print("\nLenguaje 2: ");
                        Leng2.imprimirPalabras();
                        System.out.println("\nSeleccione el primer argumento de la concatenacion");
                        respuesta = leer.nextInt();
                        if (respuesta==1) {
                            System.out.println("La concatenacion es: ");
                            Lenguaje.Concatenacion(Leng1, Leng2).imprimirPalabras();
                            System.out.println("");                        
                        }
                        else {
                            System.out.println("La concatenacion es: ");
                            Lenguaje.Concatenacion(Leng2, Leng1).imprimirPalabras();
                            System.out.println("");
                        }
                    break;
                    case 4:
                        
                    break;
                    case 5:
                        System.out.println(Palabra.crearCURP());
                    break;
                    case 6:
                        System.out.println("\nIngrese la cadena con vocales en orden alfabético a revisar:\n");
                        
                        try {
                            String cadena = leer.next();
                            
                            if (cadena.matches("([a-df-hj-np-tv-z]*a[b-hj-np-tv-z]"
                                    + "*e[b-df-np-tv-z]*i[b-df-hj-tv-z]*o[b-df-hj-np-z]"
                                    + "*u[b-df-hj-np-z]*)+")){
                                System.out.println("Cadena aceptada");
                            } else {
                                System.out.println("Cadena NO aceptada");
                            }
                        } catch (Exception e){
                            System.out.println("");
                        }
                        
                    break;
                    case 7:
                        System.exit(0);                        
                    break;
                }
            }
        }
        System.out.println("Ha salido");            
        
    }
        
    public static void main(String[] args) {
        new Practica2();
    }
    
    private Alfabeto alfabetoPorCaracter(){
        Alfabeto a = new Alfabeto();
        String respuestaS;
        boolean buli = true;

        while(buli || a.obtenerLongitud() < 3){
            System.out.println("\nIngresar símbolo del alfabeto");
            a.agregarSimbolo(leer.next());

            if(a.obtenerLongitud() >= 3){
                System.out.println("\nDesea agregar otro simbolo?");
                respuestaS = leer.next();   

                if (respuestaS.equalsIgnoreCase("no")||respuestaS.equalsIgnoreCase("nel"))
                    buli=false;                                
            }
        }
        return a;
    }
    
    private Alfabeto alfabetoPorRango(){
        Alfabeto a = new Alfabeto();
        String respuestaS;
        System.out.println("Ingrese el rango con el que definirá los símbolos ej: a-z");
        respuestaS = leer.next();
        for (int ascii = respuestaS.codePointAt(0); ascii <= respuestaS.codePointAt(2); ascii++){
            a.agregarSimbolo("" + (char)ascii);
        }

        return a;
    }
    
}
