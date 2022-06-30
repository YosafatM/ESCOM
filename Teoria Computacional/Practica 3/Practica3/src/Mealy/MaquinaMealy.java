/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mealy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MaquinaMealy {
    //Esta lista está ordenada, es importante que lo esté
    public static List<String> nombres = Arrays.asList("aaron","benjamin","caleb",
            "daniel","david","eduardo","gabriel","ian","isaac","juan","kevin",
            "luis","mark","miguel","paul","roberto","sebastian","steven","tomas",
            "tyler","uriel","ursula");
    
    private NodoMealy inicial;
    private int indice = -1;
    private NodoMealy actual;
    
    //No se pueden crear Máquinas de Mealy si no es por el otro método
    private MaquinaMealy(){}
    
    public static MaquinaMealy obtenerDiagrama(){
        MaquinaMealy m = new MaquinaMealy();
        HashMap<String, VerticeMealy> mapa = new HashMap<>();
        
        //Para las palabras que empiezan con letra única se hace esto
        for (int o = 0; o < nombres.size(); o++){
            //Para estos casos no se cumple que sean únicos
            //Estos casos son palabras que comienzan con d, i, m, s, u
            switch(o){
                case 3: case 4: case 7: case 8: case 12: case 13: case 16: case 17:
                case 19: case 20: case 21:
                    continue;
            }
            
            //Empezamos al revés, así que no tiene a dónde ir con el HashMap
            NodoMealy iterador = new NodoMealy(new HashMap<>());

            for (int i = nombres.get(o).length() - 1; i > 0; i--){
                VerticeMealy v = new VerticeMealy(0, iterador);
                HashMap<String, VerticeMealy> h = new HashMap<>();

                h.put("" + nombres.get(o).charAt(i), v);
                iterador = new NodoMealy(h);
            }
            
            //Se agrega el nodo al mapa del nodo inicial
            //El costo que tiene es el propio índice de donde se encuentra la palabra
            VerticeMealy verticeInicial = new VerticeMealy(o, iterador);
            //En realidad, esta es la iteración que falta en el for de arriba
            mapa.put("" + nombres.get(o).charAt(0), verticeInicial);
        }
        
        //Ahora se hacen los casos especiales
        //Letra d, i, m, s, u
        envolver(mapa, 3, 4);
        envolver(mapa, 7, 8);
        envolver(mapa, 12, 13);
        envolver(mapa, 16, 17);
        envolver(mapa, 19, 20);
        
        m.inicial = new NodoMealy(mapa);
        m.actual = m.inicial;
        
        return m;
    }
    
    //Esto hace casi lo mismo que el for de arriba, sólo que para dos palabras
    private static void envolver(HashMap<String, VerticeMealy> mapa, int indicePrincipal,
            int indiceSecundario){
        NodoMealy iterador = new NodoMealy(new HashMap<>());

        for (int i = nombres.get(indicePrincipal).length() - 1; i > 2; i--){
            VerticeMealy v = new VerticeMealy(0, iterador);
            HashMap<String, VerticeMealy> h = new HashMap<>();

            h.put("" + nombres.get(indicePrincipal).charAt(i), v);
            iterador = new NodoMealy(h);
        }
        
        VerticeMealy helperV = new VerticeMealy(0, iterador);
        HashMap<String, VerticeMealy> helperH = new HashMap<>();
        helperH.put("" + nombres.get(indicePrincipal).charAt(2), helperV);
        
        iterador = new NodoMealy(new HashMap<>());
        
        for (int i = nombres.get(indiceSecundario).length() - 1; i > 2; i--){
            VerticeMealy v = new VerticeMealy(0, iterador);
            HashMap<String, VerticeMealy> h = new HashMap<>();

            h.put("" + nombres.get(indiceSecundario).charAt(i), v);
            iterador = new NodoMealy(h);
        }
        
        helperV = new VerticeMealy(1, iterador);
        helperH.put("" + nombres.get(indiceSecundario).charAt(2), helperV);
        iterador = new NodoMealy(helperH);
        
        helperV = new VerticeMealy(0, iterador);
        helperH.put("" + nombres.get(indicePrincipal).charAt(1), helperV);
        iterador = new NodoMealy(helperH);
        
        helperV = new VerticeMealy(3, iterador);
        mapa.put("" + nombres.get(indicePrincipal).charAt(0), helperV);
    }
    
    public void evaluar(String entrada){
        if (actual == null) return;
        
        VerticeMealy v = actual.getVertice(entrada);
        
        if (v == null) return;
        
        if (indice == -1) indice = 0;
        
        indice += v.getCosto();
        actual = v.getDestino();
    }
    
    public void reiniciar(){
        actual = inicial;
        indice = -1;
    }
    
    public String getPrediccion(){
        return indice == -1 ? "Sin resultados" : nombres.get(indice);
    }
}
