package AFD;

import java.util.HashMap;
import java.util.List;

public class AFD {
    //Un AFD se compone de 5 cosas, sin embargo, para simplificar sólo usamos sigma
    //así como su estado y un conjunto de estados aceptados.
    //Sigma representa el comportamiento del AFD, lo guardamos con un HashMap, que
    //funciona como un arreglo en donde en vez de usar números como índices, se usan
    //"llaves", en este caso Strings
    
    //Para entender esto, sería el equivalente del HashMap en este caso, sería:
    //HashMap<String, HashMap<String, String>> = sigma<estado, fila<entrada, nuevoEstado>>
    //HashMap<String, HashMap<String, String>> = arreglo<clave, arreglo<clave,objeto a guardar>> 
    private HashMap<String, HashMap<String, String>> sigma;
    private String estado;
    private List<String> aceptados;
    
    //Sigma no puede ser nula, ni puede estar vacío el conjunto de estados aceptados
    private AFD(HashMap<String, HashMap<String, String>> sigma, List<String> aceptados){
        this.sigma = sigma;
        estado = "q0";
        this.aceptados = aceptados;
    }
    
    //Para poder validar estas cosas hacemos el constructor privado y usamos este método
    public static AFD newInstance(HashMap<String, HashMap<String, String>> sigma, 
            List<String> aceptados){
        if (sigma == null || aceptados == null || aceptados.isEmpty()){
            return null;
        } else {
            return new AFD(sigma, aceptados);
        }
    }
    
    //Regresa al AFD al estado inicial, suponemos siempre es q0
    public void resetAFD(){
        estado = "q0";
    }
    
    //Obtiene el nuevo estado del AFD a partir de una entrada y el estado actual
    //HashMap<estado actual, fila<entrada, nuevo estado>>
    public void evaluarSigma(String entrada){
        if (estado != null){
            estado = sigma.get(estado).get(entrada);            
        } else {
            estado = null;
        }
    }
    
    //Evalua si el estado actual está dentro del conjunto de estados aceptados
    public boolean esCorrecta(){
        return aceptados.contains(estado);
    }
}
