package practica4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ConjuntoCociente{
    private static List<List<String>> clases;
    private static Set<String> entradas;
    private static boolean hayCambios;

    public static void simplificar(AFD afd){
        entradas = afd.getSigma().get(afd.getInicial()).keySet();
        hayCambios = true;
        
        //Comienza el proceso de simplificado
        primeraIteración(afd);
        
        List<List<String>> auxiliar;
        
        while (hayCambios){
            hayCambios = false;
            auxiliar = new ArrayList<>();
            
            for (List<String> clase: clases){
                if (clase.size() > 1){
                    int [][] valores = new int[clase.size()][entradas.size()];
                    
                    //Este bucle obtiene el índice de la clase a la que pertenece
                    for (int i = 0; i < valores.length; i++){
                        int o = 0;

                        for (String entrada: entradas){
                            valores[i][o] = clasificarSalida(afd.evaluarSigma(clase.get(i), entrada));
                            o++;
                        }
                    }
                    
                    //Esta lista nos indica: índice, índice, sonIguales
                    List<Tupla> mapeo = new ArrayList<>();
                    
                    //Estos for hacen pares con las salidas
                    for (int i = 0; i < valores.length; i++){
                        for (int o = i + 1; o < valores.length; o++){
                            //Comparamos que los valores sean iguales, en ese caso,
                            //sigue perteneciendo a la misma clase
                            boolean esIgual = true;
                            
                            for (int u = 0; u < entradas.size(); u++){
                                if (valores[i][u] != valores[o][u]){
                                    esIgual = false;
                                    break;
                                }
                            }
                            
                            //Si esIgual se agrega al inicio, sino, al final
                            if (esIgual){
                                mapeo.add(0, new Tupla(i, o, esIgual));
                            } else {
                                mapeo.add(new Tupla(i, o, esIgual));
                            }
                        }
                    }
                    
                    //Hay dos casos en donde no hay cambios, entonces no cambia la clase:
                    //  Todos son iguales (el último esIgual = true)
                    //  Todos son diferentes (el primero esIgual = false)
                    if (mapeo.size() > 1) {
                        if (!mapeo.get(0).esIgual() || mapeo.get(mapeo.size()-1).esIgual()){
                            auxiliar.add(clase);
                            continue;
                        }
                    }
                    
                    //Revisamos si se crea una nueva clase
                    int i = 0;
                    List<String> nuevaClase = new ArrayList<>();
                    
                    //Agregamos los elementos que están en las parejas que son iguales
                    while (i != mapeo.size() && mapeo.get(i).esIgual()){
                        String estado = clase.get(mapeo.get(i).getI1());
                        if (!nuevaClase.contains(estado)) nuevaClase.add(estado);
                        
                        estado = clase.get(mapeo.get(i).getI2());
                        if (!nuevaClase.contains(estado)) nuevaClase.add(estado);
                        
                        i++;
                    }
                    
                    //Si la nueva clase tiene el mismo tamaño que la original, no hay cambios
                    if (nuevaClase.size() == clase.size()){
                        auxiliar.add(clase);
                        continue;
                    }
                    
                    if (nuevaClase.isEmpty()){
                        for (String s : clase){
                            List<String> l = new ArrayList<>();
                            l.add(s);
                            auxiliar.add(l);
                        }
                        
                        continue;
                    }
                    
                    List<String> segundaClase = new ArrayList<>();
                    
                    //Le agregamos el resto de los elementos de la clase
                    for (String s : clase){
                        if (!nuevaClase.contains(s)) segundaClase.add(s);
                    }
                    
                    if (!nuevaClase.isEmpty()) auxiliar.add(nuevaClase);
                    if (!segundaClase.isEmpty()) auxiliar.add(segundaClase);
                    
                } else {
                    auxiliar.add(clase);
                }
            }
            
            //Al acabar la iteración, si la lista auxiliar no tiene el mismo tamaño
            //que la original, entonces, hubo cambios
            if (auxiliar.size() != clases.size()){
                hayCambios = true;
            }
            
            clases = auxiliar;
        }
        
        AFD nuevo = crearNuevoAFD(afd);
        
        imprimirAceptados(nuevo);
        imprimirSigma(nuevo);
        System.out.println("\nInicial: " + nuevo.getInicial());
    }
    
    private static void imprimirSigma(AFD nuevo){
        System.out.print("  ");
        for (String e : entradas){
            System.out.print("  " + e + " ");
        }
        
        for (String key : nuevo.getSigma().keySet()){
            System.out.print("\n" + key);
            
            for (String e : entradas){
                System.out.print("  " + nuevo.evaluarSigma(key, e));
            }
        }
        
        System.out.println("");
    }
    
    private static void imprimirAceptados(AFD nuevo){
        System.out.print("\nAceptados:");
        
        for (String a : nuevo.getAceptados()){
            System.out.print(" " + a);
        }
        
        System.out.println("\n");
    }
    
    private static void primeraIteración(AFD afd){
        clases = new ArrayList<>();
        clases.add(afd.getAceptados()); //Es la clase C0

        List<String> noAceptados = new ArrayList<>();

        for (String s: afd.getSigma().keySet()){
            if (!clases.get(0).contains(s)){
                noAceptados.add(s);
            }
        }

        clases.add(noAceptados);
    }
    
    private static int clasificarSalida(String estado){
        for (int i = 0; i < clases.size(); i++){
            if (clases.get(i).contains(estado)){
                return i;
            }
        }
        
        return -1;
    }
    
    private static AFD crearNuevoAFD(AFD afd){
        HashMap<String, HashMap<String, String>> sigma = new HashMap<>();
        
        //El afd requiere 3 cosas: sigma, estado inicial, aceptados
        //Hacemos sigma
        for (int i = 0; i < clases.size(); i++){
            List<String> c = clases.get(i);
            HashMap<String, String> fila = new HashMap<>();
            
            for (String e : entradas){
                int o = 0;
                int clase = clasificarSalida(afd.evaluarSigma(c.get(o), e));
                
                while (o < c.size() && clase == i){
                    clase = clasificarSalida(afd.evaluarSigma(c.get(o), e));
                    o++;
                }
                
                fila.put(e, "C" + clase);
            }
            
            sigma.put("C" + i, fila);
        }
        
        //Buscamos el estado inicial
        List<String> aceptados = new ArrayList<>();
        
        for (String e : afd.getAceptados()){
            int clase = clasificarSalida(e);
            String estado = "C" + clase;
            
            if (!aceptados.contains(estado))
                aceptados.add(estado);
        }
        
        //Buscamos el inicial
        int clase = clasificarSalida(afd.getInicial());
        
        return AFD.newInstance(sigma, aceptados, "C" + clase);
    }
}

class Tupla{
    private final int i1, i2;
    private final boolean esIgual;

    public Tupla(int i1, int i2, boolean esIgual) {
        this.i1 = i1;
        this.i2 = i2;
        this.esIgual = esIgual;
    }

    public int getI1() {
        return i1;
    }

    public int getI2() {
        return i2;
    }

    public boolean esIgual() {
        return esIgual;
    }
}