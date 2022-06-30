package Compilador;
/*
#Martinez Coronel Brayan Yosafat 
#Ramirez Olvera Guillermo
#Sanchez Mendez Edmundo Josue
#Proyecto: LOGOS
#18/01/2021
#3CM7
*/
public class Par {

    private String nombre;
    private Object objeto;

    public Par(String nombre, Object objeto){
        this.nombre = nombre;
        this.objeto = objeto;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
        
}
