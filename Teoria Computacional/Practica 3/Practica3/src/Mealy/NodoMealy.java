/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mealy;

import java.util.HashMap;

public class NodoMealy {
    private HashMap<String, VerticeMealy> mapa;

    public NodoMealy(HashMap<String, VerticeMealy> mapa) {
        this.mapa = mapa;
    }
    
    public VerticeMealy getVertice(String entrada){
        return mapa.get(entrada);
    }
}
