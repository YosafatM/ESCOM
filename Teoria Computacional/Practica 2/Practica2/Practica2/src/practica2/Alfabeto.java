package practica2;

import java.util.ArrayList;
import java.util.List;

public class Alfabeto {
    private List<String> simbolos;
    
    public Alfabeto(){
       simbolos = new ArrayList<>();         
    }
    
    public Alfabeto(List<String> simbolos){
        this.simbolos = simbolos;
    }
    
    public void agregarSimbolo(String simb){
        if (simbolos.contains(simb))
            System.out.println("El simbolo ya existe");
        else{
            simbolos.add(simb);
            System.out.println(simb + " agregado");
        }
    }
    
    public int obtenerLongitud(){
        return simbolos.size();
    }
    
    public void imprimirSimbolos(){
        simbolos.forEach((s) -> {
            System.out.print("["+s+"]");
        });
    }
    
    public boolean tieneA(String x){
        return simbolos.contains(x);
    }
    
    Palabra crearPalabra(String palabraPrueba){
        if(palabraPrueba.equals(""))
            return Palabra.newInstance(this, "");
        
        boolean[] marcas = new boolean[palabraPrueba.length()];
        
        for (int i = 0; i < marcas.length; i++) {
            marcas[i] = false;
        }
        
        simbolos.forEach((s)-> {
            int indice = palabraPrueba.indexOf(s);
            
            while (indice >= 0){
                for (int o = 0; o < s.length(); o++){
                    marcas[indice + o] = true;
                }
                indice = palabraPrueba.indexOf(s, indice + 1);
            }
        });
        
        boolean valida = true;
        
        for (int i = 0; i < marcas.length; i++){
            if(valida != marcas [i]){
                valida = false;
                break;
            }
        }
        
        return valida ? Palabra.newInstance(this, palabraPrueba) : null;
    }
    
    public Palabra palabraAleatoria(int longitud){
        if (simbolos.isEmpty()) return Palabra.newInstance(this, "");
        
//        int longitud = (int) (Math.random() * 10
        StringBuilder constructor = new StringBuilder("");
        
        for (int i = 0; i < longitud; i++){
            constructor.append(simbolos.get((int) (Math.random() * (simbolos.size() - 0.1))));
        }
        
        return Palabra.newInstance(this, constructor.toString());
    }
    
    public static Alfabeto Union(Alfabeto a, Alfabeto b){
        if (a.equals(b)) return a;
        
        List<String> simbolos = new ArrayList<>();
        boolean esMenorA = a.simbolos.size() < b.simbolos.size();
        simbolos.addAll(esMenorA ? b.simbolos : a.simbolos);
        simbolos.removeAll(esMenorA ? a.simbolos : b.simbolos);
        simbolos.addAll(esMenorA ? a.simbolos : b.simbolos);
        return new Alfabeto(simbolos);
    }
    
    public Lenguaje crearLenguaje(int np, int l){
        List<Palabra> leng = new ArrayList<>();
        for (int i = 0; i < np; i++) {
            Palabra palabra = palabraAleatoria(l);
            boolean existe = false;
            
            for (Palabra p : leng){
                if (p.getContenido().equals(palabra.getContenido())){
                    existe = true;
                }
            }
            
            if (!existe){
                leng.add(palabra);
            } else {
                i -= 1;
            }
        }
        return Lenguaje.newInstance(this, leng);
         
    }
}
