package Configuracion;

import java.awt.Color;
import java.util.ArrayList;
/*
#Martinez Coronel Brayan Yosafat 
#Ramirez Olvera Guillermo
#Sanchez Mendez Edmundo Josue
#Proyecto: LOGOS
#18/01/2021
#3CM7
*/
public class Configuracion {

    ArrayList<Linea> lineas;
    double x;
    double y;
    int angulo;
    Color color;
    
    public Configuracion(){
        x = 0.0;
        y = 0.0;
        lineas = new ArrayList<Linea>(); 
    }
    
    public void agregarLinea(Linea linea){
        lineas.add(linea);
    }
    
    public void setPosicion(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void limpiar(){
        lineas.clear();
    }

    public ArrayList<Linea> getLineas() {
        return lineas;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    } 

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString(){
        String valor = "";
        for(int i = 0; i < lineas.size(); i++)
            valor += lineas.get(i)+", ";
        valor += "x:"+x+" y:"+y+" angulo: "+angulo;
        return valor;
    }
    
}
