package practica4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Practica4 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        AFD auto1, auto2, auto3;
        auto1 = AFD.newInstance(AFD1(), Arrays.asList("C","D","E"), "A");
        auto2 = AFD.newInstance(AFD2(), Arrays.asList("E"), "A");
        auto3 = AFD.newInstance(AFD3(), Arrays.asList("Q", "R"), "P");
        
        ConjuntoCociente.simplificar(auto3);
    }
    
    //Usamos el diseño que hicimos desde la otra práctica 
    private static HashMap<String, HashMap<String, String>> AFD1(){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        HashMap<String, String> fila = new HashMap<>();
        fila.put("0", "B");
        fila.put("1", "C");
        sigma.put("A", fila);
        
        fila = new HashMap<>();
        fila.put("0", "A");
        fila.put("1", "D");
        sigma.put("B", fila);
        
        fila = new HashMap<>();
        fila.put("0", "E");
        fila.put("1", "F");
        sigma.put("C", fila);
        
        fila = new HashMap<>();
        fila.put("0", "E");
        fila.put("1", "F");
        sigma.put("D", fila);
        
        fila = new HashMap<>();
        fila.put("0", "E");
        fila.put("1", "F");
        sigma.put("E", fila);
        
        fila = new HashMap<>();
        fila.put("0", "F");
        fila.put("1", "F");
        sigma.put("F", fila);   
        
        return sigma;
    }
    
    private static HashMap<String, HashMap<String, String>> AFD2(){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        
        HashMap<String, String> fila = new HashMap<>();
        fila.put("0", "B");
        fila.put("1", "C");
        sigma.put("A", fila);
        
        fila = new HashMap<>();
        fila.put("0", "B");
        fila.put("1", "D");
        sigma.put("B", fila);
        
        fila = new HashMap<>();
        fila.put("0", "B");
        fila.put("1", "C");
        sigma.put("C", fila);
        
        fila = new HashMap<>();
        fila.put("0", "B");
        fila.put("1", "E");
        sigma.put("D", fila);
        
        fila = new HashMap<>();
        fila.put("0", "B");
        fila.put("1", "C");
        sigma.put("E", fila); 
        
        return sigma;
    }
    
    private static HashMap<String, HashMap<String, String>> AFD3(){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        
        HashMap<String, String> fila = new HashMap<>();
        fila.put("a", "Q");
        fila.put("b", "P");
        sigma.put("P", fila);
        
        fila = new HashMap<>();
        fila.put("a", "R");
        fila.put("b", "S");
        sigma.put("Q", fila);
        
        fila = new HashMap<>();
        fila.put("a", "Q");
        fila.put("b", "T");
        sigma.put("R", fila);
        
        fila = new HashMap<>();
        fila.put("a", "T");
        fila.put("b", "U");
        sigma.put("S", fila);
        
        fila = new HashMap<>();
        fila.put("a", "S");
        fila.put("b", "U");
        sigma.put("T", fila);
        
        fila = new HashMap<>();
        fila.put("a", "Q");
        fila.put("b", "U");
        sigma.put("U", fila);
        
        return sigma;
    }
}
