package practica2;

import java.util.Arrays;
import java.util.List;

public class Palabra {
    private Alfabeto pertenencia;
    private String contenido;

    private Palabra(Alfabeto al, String contenido){
        this.pertenencia = al;
        this.contenido = contenido;
    }
    
    public static Palabra newInstance(Alfabeto al, String contenido){
        if(al == null || contenido == null)
            return null;
        else 
            return new Palabra(al, contenido);
    }
    
    public int coincide(String s){
        int indice = contenido.indexOf(s);
        int contador = 0;
        
        while (indice >= 0){
            contador++;
            indice = contenido.indexOf(s, indice + 1);
        }
        return contador;
    }
    
    @Override
    public String toString(){
        return contenido;
    }
    
    public String aLaPotencia(int n){
        if (n == 0) return "";
        
        StringBuilder nuevaPalabra = new StringBuilder(contenido);
        
        for (int i = 1; i < Math.abs(n); i++){
            nuevaPalabra.append(contenido);
        }
        
        return n > 0 ? nuevaPalabra.toString() : nuevaPalabra.reverse().toString();
    }
    
    public static Palabra multiplicar(Palabra x, Palabra y){
        return Palabra.newInstance(
                Alfabeto.Union(x.pertenencia, y.pertenencia), 
                x.contenido + y.contenido);
    }
    
    public static boolean esPalindromo(String x){
        if (x.equals("")) return true;
        if (x.length()==1) return false;
        
        boolean esPalindromo = true;
        
        for (int i = 0; i < x.length() / 2; i++){
            if (x.charAt(i) != x.charAt(x.length()-1-i)){
                esPalindromo = false;
            }
        }
        
        return esPalindromo;
    }
    
    public String getContenido(){
        return contenido;
    }
    
    public static String crearCURP(){
        StringBuilder sb = new StringBuilder();
        List<String> vocales = Arrays.asList("A", "E", "I", "O", "U");
        List<String> consonantes = Arrays.asList("B", "C", "D", "F", "G", "H", "J", "K", "L", "M",
                "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z");
        List<String> estado = Arrays.asList("AS", "BC", "BS", "CC", "CL", "CM", "CS", "CH", "DF", "DG",
                "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL",
                "QT", "QR", "SP", "SL", "SR", "TC", "TS", "TL", "VZ", "YN", "ZS", "NE");
        
        sb.append((char)(65 + Math.random() * 25.9));
        sb.append(vocales.get((int) (Math.random() * 4.9)));
        sb.append((char)(65 + Math.random() * 25.9));
        sb.append((char)(65 + Math.random() * 25.9));
        
        int añoP = (int) (48 + Math.random() * 9.9);
        int mesP1 = (int) (48 + Math.random() * 1.9);
        int mesP2 = mesP1 == 48 ? (char)(48 + Math.random() * 9.9) : (char)(48 + Math.random() * 1.9);
        int diaP1, diaP2;
        
        sb.append((char)añoP);
        sb.append((char)(int)(48 + Math.random() * 9.9));
        sb.append((char)mesP1);
        sb.append((char)mesP2);
        sb.append((char)(int)(48 + Math.random() * 1.9));
        sb.append((char)(int)(48 + Math.random() * 9.9));
        
        sb.append((int) (Math.random() * 1.9) == 0 ? "H" : "M");
        sb.append(estado.get((int) (Math.random() * 32.9)));
        

        sb.append(consonantes.get((int) (Math.random() * 20.9)));
        sb.append(consonantes.get((int) (Math.random() * 20.9)));
        sb.append(consonantes.get((int) (Math.random() * 20.9)));
        
        if (añoP > 49){
            sb.append((int) (Math.random() * 9.9));
        } else {
            sb.append((char)(int)(65 + Math.random() * 25.9));
        }
        
        sb.append((int)(Math.random() * 9.9));
        
        return sb.toString();
    }
}
