package Compilador;


import java.util.ArrayList;
/*
#Martinez Coronel Brayan Yosafat 
#Ramirez Olvera Guillermo
#Sanchez Mendez Edmundo Josue
#Proyecto: LOGOS
#18/01/2021
#3CM7
*/
public class Marco {

    private ArrayList parametros;
    private int retorno;
    private String nombre;

    public Marco(){
        parametros = new ArrayList();
        retorno = 0;
        nombre = null;
    }
    
    public void agregarParametro(Object parametro){
        parametros.add(parametro);
    }
    
    public Object getParametro(int i){
        return parametros.get(i);
    }
    
    public void setParametros(ArrayList parametros) {
        this.parametros = parametros;
    }

    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
}
