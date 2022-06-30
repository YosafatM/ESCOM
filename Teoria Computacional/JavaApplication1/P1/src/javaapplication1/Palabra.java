/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author alumno
 */
class Palabra {
    private Alfabeto pertenencia;
    private String contenido;
    
    private Palabra(Alfabeto al, String contenido){
        this.pertenencia = al;
        this.contenido = contenido;
    }
    
    public static Palabra newInstance(Alfabeto al, String contenido){
        if (al == null || contenido == null){
            return null;
        } else {
            return new Palabra(al, contenido);
        }
    }
    
    public int coincide(String s){
        int indice = contenido.indexOf(s);
        int contador = 0;
            
        while (indice >= 0) {
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
                Alfabeto.union(x.pertenencia, y.pertenencia), 
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
}
