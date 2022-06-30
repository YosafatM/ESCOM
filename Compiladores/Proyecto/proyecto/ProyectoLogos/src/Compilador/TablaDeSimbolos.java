package Compilador;

import java.util.ArrayList;
import java.util.Vector;
/*
#Martinez Coronel Brayan Yosafat 
#Ramirez Olvera Guillermo
#Sanchez Mendez Edmundo Josue
#Proyecto: LOGOS
#18/01/2021
#3CM7
*/
public class TablaDeSimbolos {

    ArrayList<Par> simbolos;
    
    public TablaDeSimbolos(){
        simbolos = new ArrayList<Par>();
    }
    
    public Object encontrar(String nombre){
        for(int i = 0; i < simbolos.size(); i++)
            if(nombre.equals(simbolos.get(i).getNombre()))
                return simbolos.get(i).getObjeto();
        return null;
    }
    
    public boolean insertar(String nombre, Object objeto){
        Par par = new Par(nombre, objeto);
        for(int i = 0; i < simbolos.size(); i++)
            if(nombre.equals(simbolos.get(i).getNombre())){
                simbolos.get(i).setObjeto(objeto);
                return true;
            }
        simbolos.add(par);
        return false;
    }
    
    public void imprimir(){
        for(int i = 0; i < simbolos.size(); i++){
            System.out.println(simbolos.get(i).getNombre() + simbolos.get(i).getObjeto().toString());
        }
    }

}