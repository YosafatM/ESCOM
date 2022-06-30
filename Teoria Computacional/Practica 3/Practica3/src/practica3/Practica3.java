package practica3;

import AFD.AFD;
import AFD.AFDPorClase;
import Mealy.MaquinaMealy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Practica3 {

    public static void main(String[] args) {
        AFDPorClase a;
        AFD b, c;
        MaquinaMealy d;
        
        Scanner s = new Scanner(System.in);
        String palabra;
        
        a = AFDPorClase.newInstance(AFD1(), Arrays.asList("q2", "q4", "q6"), 
                Arrays.asList("numero", "signo", "exponente", "punto"), clasesAFD1());
        b = AFD.newInstance(AFD2(), Arrays.asList("q0", "q3"));
        c = AFD.newInstance(AFD3(), Arrays.asList("qf"));
        d = MaquinaMealy.obtenerDiagrama();
        
        while (true){
            System.out.println("\nSeleccione una opcion:");
            System.out.println("0) Salir");
            System.out.println("1) AFD de numeros reales");
            System.out.println("2) AFD de 0 pares sin 1 juntos");
            System.out.println("3) AFD de letras juntas");
            System.out.println("4) Predicción de autocompletado");
            
            switch (s.nextInt()){
                case 1:
                    System.out.println("Ingrese la palabra a evaluar");
                    palabra = s.next();
                    a.resetAFD();
                    
                    for (int i = 0; i < palabra.length(); i++){
                        a.evaluarSigma(a.clasificarEntrada("" + palabra.charAt(i)));
                    }
                    
                    if (a.esCorrecta()){
                        System.out.println("Aceptada");
                    } else {
                        System.out.println("NO aceptada");
                    }
                    break;
                    
                case 2:
                    System.out.println("Ingrese la palabra a evaluar");
                    palabra = s.next();
                    b.resetAFD();
                    
                    for (int i = 0; i < palabra.length(); i++){
                        b.evaluarSigma("" + palabra.charAt(i));
                    }
                    
                    if (b.esCorrecta()){
                        System.out.println("Aceptada");
                    } else {
                        System.out.println("NO aceptada");
                    }
                    break;
                    
                case 3:
                    System.out.println("Ingrese la palabra a evaluar");
                    palabra = s.next();
                    c.resetAFD();
                    
                    for (int i = 0; i < palabra.length(); i++){
                        c.evaluarSigma("" + palabra.charAt(i));
                    }
                    
                    if (c.esCorrecta()){
                        System.out.println("Aceptada");
                    } else {
                        System.out.println("NO aceptada");
                    }
                    break;
                    
                case 4:
                    System.out.println("Ingrese un texto para predecirlo, estas son las posibles predicciones");
                    System.out.println(MaquinaMealy.nombres);
                    palabra = s.next().toLowerCase();
                    d.reiniciar();
                    
                    for (int i = 0; i < palabra.length(); i++){
                        d.evaluar("" + palabra.charAt(i));
                    }
                    
                    System.out.println(d.getPrediccion());
                    break;
                    
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    
    //Hacemos las tablas de sigma, se puede ver que el HashMap fila, representa
    //una fila de la tabla de sigma, y el HashMap que se retorna es sigma en su
    //totalidad
    
    private static List<List<String>> clasesAFD1(){
        List<List<String>> clases = new ArrayList<>();
        List<String> clase = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        clases.add(clase);
        
        clase = Arrays.asList("+", "-");
        clases.add(clase);
        
        clase = Arrays.asList("E", "e");
        clases.add(clase);
        
        clase = Arrays.asList(".");
        clases.add(clase);
        
        return clases;
    }
    
    private static HashMap<String, HashMap<String, String>> AFD1(){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        
        HashMap<String, String> fila = new HashMap<>();
        fila.put("numero", "q2");
        fila.put("signo", "q1");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("q0", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q2");
        fila.put("signo", "qx");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("q1", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q2");
        fila.put("signo", "q1");
        fila.put("exponente", "q5");
        fila.put("punto", "q3");
        sigma.put("q2", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q4");
        fila.put("signo", "qx");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("q3", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q4");
        fila.put("signo", "q1");
        fila.put("exponente", "q5");
        fila.put("punto", "qx");
        sigma.put("q4", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q6");
        fila.put("signo", "q7");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("q5", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q6");
        fila.put("signo", "q1");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("q6", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "q6");
        fila.put("signo", "qx");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("q7", fila);
        
        fila = new HashMap<>();
        fila.put("numero", "qx");
        fila.put("signo", "qx");
        fila.put("exponente", "qx");
        fila.put("punto", "qx");
        sigma.put("qx", fila);
        
        return sigma;
    }
    
    private static HashMap<String, HashMap<String, String>> AFD2(){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        
        HashMap<String, String> fila = new HashMap<>();
        fila.put("0", "q1");
        fila.put("1", "q3");
        sigma.put("q0", fila);
        
        fila = new HashMap<>();
        fila.put("0", "q0");
        fila.put("1", "q2");
        sigma.put("q1", fila);
        
        fila = new HashMap<>();
        fila.put("0", "q0");
        fila.put("1", "qx");
        sigma.put("q2", fila);
        
        fila = new HashMap<>();
        fila.put("0", "q1");
        fila.put("1", "qx");
        sigma.put("q3", fila);
        
        fila = new HashMap<>();
        fila.put("0", "qx");
        fila.put("1", "qx");
        sigma.put("qx", fila);
        
        return sigma;
    }
    
    private static HashMap<String, HashMap<String, String>> AFD3(){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        
        HashMap<String, String> fila = new HashMap<>();
        fila.put("a", "q1");
        fila.put("b", "q2");
        fila.put("c", "q3");
        sigma.put("q0", fila);
        
        fila = new HashMap<>();
        fila.put("a", "q4");
        fila.put("b", "qb");
        fila.put("c", "qc");
        sigma.put("q1", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qa");
        fila.put("b", "q5");
        fila.put("c", "qc");
        sigma.put("q2", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qa");
        fila.put("b", "qb");
        fila.put("c", "q6");
        sigma.put("q3", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qx");
        fila.put("b", "qf");
        fila.put("c", "qf");
        sigma.put("q4", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qf");
        fila.put("b", "qx");
        fila.put("c", "qf");
        sigma.put("q5", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qf");
        fila.put("b", "qf");
        fila.put("c", "qx");
        sigma.put("q6", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qf");
        fila.put("b", "qx");
        fila.put("c", "qx");
        sigma.put("qa", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qx");
        fila.put("b", "qf");
        fila.put("c", "qx");
        sigma.put("qb", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qx");
        fila.put("b", "qx");
        fila.put("c", "qf");
        sigma.put("qc", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qx");
        fila.put("b", "qx");
        fila.put("c", "qx");
        sigma.put("qf", fila);
        
        fila = new HashMap<>();
        fila.put("a", "qx");
        fila.put("b", "qx");
        fila.put("c", "qx");
        sigma.put("qx", fila);
        
        return sigma;
    }
}
