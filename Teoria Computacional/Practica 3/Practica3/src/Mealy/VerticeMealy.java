/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mealy;

public class VerticeMealy {
    private int costo;
    private NodoMealy destino;

    public VerticeMealy(int costo, NodoMealy destino) {
        this.costo = costo;
        this.destino = destino;
    }

    public int getCosto() {
        return costo;
    }

    public NodoMealy getDestino() {
        return destino;
    }
}
