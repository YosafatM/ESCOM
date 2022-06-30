/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class JavaApplication1 {
    private Alfabeto Sigma1, Sigma2;
    private Palabra x1, x2;
    private Scanner leer;
    int respuesta;
    
    public JavaApplication1(){
        Sigma1 = null;
        Sigma2 = null;
        x1 = null;
        x2 = null;
        leer = new Scanner(System.in);
        respuesta = 0;
        String respuestaS = "";
        
        while (true) {
            imprimirMenu("principal");
            
            try {
                respuesta = leer.nextInt();
            } catch (Exception e){
                System.out.println("No es el formato indicado");
                break;
            }
        
            switch (respuesta){
                case 0:
                    System.exit(0);
                break;                
                
                case 1:
                    for (int c = 1; c <= 2; c++){
                        imprimirMenu("alfabeto");

                        try {
                            respuesta = leer.nextInt();
                        } catch (Exception e){
                            System.out.println("No es el formato indicado");
                        }

                        if (respuesta == 1){
                            System.out.println("Defina Sigma " + c);
                            
                            if (c == 1){
                                Sigma1 = alfabetoPorCaracter();
                            } else {
                                Sigma2 = alfabetoPorCaracter();
                            }
                        }
                        else if (respuesta == 2){
                            System.out.println("Defina Sigma " + c);
                            
                            if (c == 1){
                                Sigma1 = alfabetoPorRango();
                            } else {
                                Sigma2 = alfabetoPorRango();
                            }
                        }
                    }
                break;
                
                case 2:
                    if (Sigma1 != null)
                        Sigma1.imprimirSimbolos();
                    else 
                        System.out.println("\nERROR! Defina el alfabeto"); 
                    
                    if (Sigma2 != null)
                        Sigma2.imprimirSimbolos();
                    else 
                        System.out.println("\nERROR! Defina el alfabeto");                    
                break;
                
                case 3:
                    do{
                        System.out.println("\nIngrese una palabra valida (x1) a partir de Sigma 1");
                        respuestaS = leer.next();
                        x1 = Sigma1.crearPalabra(respuestaS);
                    } while(x1 == null);
                    
                    do{
                        System.out.println("\nIngrese una palabra valida (x2) a partir de Sigma 1");
                        respuestaS = leer.next();
                        x2 = Sigma1.crearPalabra(respuestaS);
                    } while(x2 == null);  
                break;
                
                case 4:
                    System.out.println("\nIngrese la potencia para (x1x2)^n");
                    
                    try {
                        if (x1 == null || x2 == null){
                            System.out.println("Defina antes las palabras");
                            break;
                        }
                        
                        int potencia = leer.nextInt();
                        System.out.println(Palabra.multiplicar(x1, x2).aLaPotencia(potencia));
                    } catch (Exception e){
                        break;
                    }
                break;
                    
                case 5:
                    System.out.println("\nIngrese el simbolo a concurrir con x1");
                    respuestaS = leer.next();
                    
                    if (!Sigma1.tieneA(respuestaS)){
                        System.out.println("El elemento no pertece a Sigma 1");
                        break;
                    }
                    
                    if (x1 == null) {
                        System.out.println("Defina antes x1");
                        break;
                    }
                    
                    if (respuestaS.equals("")){
                        System.out.println("\n" + respuestaS + " esta " + 
                            2 + " veces en x1");
                    } else {
                        System.out.println("\n" + respuestaS + " esta " + 
                            x1.coincide(respuestaS) + " veces en x1");
                    }
                break;
                
                case 6:
                    System.out.println("\nIngrese la cadena para saber si es un palindromo");
                    System.out.println("La cadena " +
                            (Palabra.esPalindromo(leer.next()) ? "" : "NO")
                            + " es palindromo");
                break;
                
                case 9:
                    System.out.println("\nNo hay sistema");
                break;
                
                case 8:
                    for (int i = 0; i < 3; i++){
                        if ((int)(Math.random() * 2.9) == 1){
                            System.out.println(Sigma1.palabraAleatoria().toString());
                        } else {
                            System.out.println(Sigma2.palabraAleatoria().toString());
                        }
                    }
                break;
                
                case 7: 
                    System.out.println("jajaja hola");
                break;
            }
        }
    }
    
    private Alfabeto alfabetoPorCaracter(){
        Alfabeto a = new Alfabeto();
        String respuestaS;
        boolean buli = true;

        while(buli || a.obtnerLongitud() < 3){
            System.out.println("\nIngresar símbolo del alfabeto");
            a.agregarSimbolo(leer.next());

            if(a.obtnerLongitud() >= 3){
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
    
    public static void main(String[] args) {
        new JavaApplication1();
    }
    
    private static void imprimirMenu(String estado){
        switch (estado){
            case "principal":
                System.out.println("\nBienvenido al sistema");
                System.out.println("Ingrese la opción que desee");
                System.out.println("1) Definir alfabeto");
                System.out.println("2) Imprimir simbolos");
                System.out.println("3) Crear palabra");
                System.out.println("4) Potencia de (x1x2)^n");
                System.out.println("5) Concurrencia de simbolos");
                System.out.println("6) Palindromo");
                System.out.println("7) Sufijos y prefijos");
                System.out.println("8) Generar palabra aleatoria");
                System.out.println("9) Potencia de un alfabeto");
                System.out.println("0) Salir");
            break;
            
            case "alfabeto":
                System.out.println("\n¿Cómo quieres crear el alfabeto?");
                System.out.println("1) Simbolos");
                System.out.println("2) Rango");
            break;
        }
    }
}
