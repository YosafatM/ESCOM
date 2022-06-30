package practica2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Lenguaje {
    private Alfabeto pertenencia;
    private List<Palabra> contenido;
    
    private Lenguaje(Alfabeto al, List<Palabra> contenido){
        this.pertenencia = al;
        this.contenido = contenido;
    }
    
    //Con este metodo no se puede crear un lenguaje si no pertenece a un alfabeto
    public static Lenguaje newInstance(Alfabeto al, List<Palabra> contenido){
        if (al == null || contenido == null){
            return null;
        } else {
            return new Lenguaje(al, contenido);
        }
    }
    
    public void imprimirPalabras(){
        contenido.forEach((s) -> {
            System.out.print("[" + s + "]");
        });
    }
    
    public void agregarPalabra(Palabra pal){
        if (contenido.contains(pal))
            System.out.println("La palabra ya existe");
        else
            contenido.add(pal);
    }

//    public static Lenguaje Union(Alfabeto Sigma, Lenguaje a, Lenguaje b){
//        if (a.equals(b))
//            return a;
//        
//        List<Palabra> contenido = new ArrayList<>();
//        boolean esMenorA = a.contenido.size() < b.contenido.size();
//        contenido.addAll(esMenorA ? b.contenido : a.contenido);
//        contenido.removeAll(esMenorA ? a.contenido : b.contenido);
//        contenido.addAll(esMenorA ? a.contenido : b.contenido);
//        return new Lenguaje(Sigma, contenido);
//    }
    
//    public static Lenguaje Union(Alfabeto Sigma, Lenguaje a, Lenguaje b){
//        if (a.equals(b))
//            return a;
//            
//        
//        List<Palabra> contenido = new ArrayList<>();
//        List<Palabra> aux = new ArrayList<>();
//        contenido.addAll(b.contenido);
//        contenido.addAll(a.contenido);
//        
//        return new Lenguaje(Sigma, aux);
//    }
    
//    public ArrayList<String> palabrasRepetidas(Lenguaje leng1, Lenguaje leng2){
//        ArrayList<String> aux = new ArrayList<>(); 
//        boolean esMayor1 = leng1.contenido.size() > leng2.contenido.size();
//        if (esMayor1){
//            for (int i = 0; i < leng1.contenido.size(); i++) 
//                for (int j = 0; j < leng2.contenido.size(); j++) 
//                    if (leng1.contenido.get(i).toString().equals(leng2.contenido.get(j).toString())) 
//                        aux.add(leng1.contenido.get(i).toString());
//        }
//        else{
//            for (int i = 0; i < leng2.contenido.size(); i++) 
//                for (int j = 0; j < leng1.contenido.size(); j++) 
//                    if (leng2.contenido.get(i).toString().equals(leng1.contenido.get(j).toString()))
//                        aux.add(leng2.contenido.get(i).toString());                
//                       
//        }
//        System.out.println("Elementos repetidos: "+aux.toString());
//        return aux;
//    }
    
    public static Lenguaje Union(Lenguaje leng1, Lenguaje leng2){
        List<Palabra> nuevoContenido = new ArrayList<>(leng2.contenido);

        leng1.contenido.forEach((l) -> {
            boolean pertenece = false;
            for (Palabra p: leng2.contenido){
                if (l.getContenido().equals(p.getContenido())){
                    pertenece = true;
                }
            }
            if (!pertenece) {
                nuevoContenido.add(l);
            }
        });
        
        return new Lenguaje(leng1.pertenencia, nuevoContenido);
        
        /*if (esMayor1){
        for (int i = 0; i < leng1.contenido.size(); i++)
        for (int j = 0; j < leng2.contenido.size(); j++)
        if (leng1.contenido.get(i).toString().equals(leng2.contenido.get(j).toString()))
        leng1.contenido.remove(i);
        leng1.contenido.addAll(leng2.contenido);
        return leng1;
        }
        else{
        for (int i = 0; i < leng2.contenido.size(); i++)
        for (int j = 0; j < leng1.contenido.size(); j++)
        if (leng2.contenido.get(i).toString().equals(leng1.contenido.get(j).toString()))
        leng2.contenido.remove(i);
        leng2.contenido.addAll(leng1.contenido);
        return leng2;
        }*/
    }
    
    public Lenguaje Resta(Lenguaje leng2){
        
        List<Palabra> nuevoContenido = new ArrayList<>(contenido);

        leng2.contenido.forEach((l) -> {
            for (Palabra p: contenido){
                if (l.getContenido().equals(p.getContenido())){
                    nuevoContenido.remove(p);
                }
            }
        });
        
        return new Lenguaje(pertenencia, nuevoContenido);
    }
    
    public static Lenguaje Concatenacion(Lenguaje leng1, Lenguaje leng2){
        List<Palabra> nuevoContenido = new ArrayList<>();

        leng1.contenido.forEach((l) -> {
            leng2.contenido.forEach((p) -> {
                nuevoContenido.add(Palabra.newInstance(leng1.pertenencia, l.getContenido() + p.getContenido()));
            });
        });
        
        return new Lenguaje(leng1.pertenencia, nuevoContenido);
    }
}
