package AFD;

import java.util.HashMap;
import java.util.List;

public class AFDPorClase {
    /*A diferencia del otro AFD, en este vamos a usar clases, por ejemplo, la clase
    numero, que consta de una lista del 0 al 9, clase punto que consta de un punto...
    Al igual que en el anterior tiene un Hash, funciona de la misma manera, pero ahora
    en vez de entrada consideramos clase de la entrada (numero, punto, exponente)
    */
    private HashMap<String, HashMap<String, String>> sigma;
    private List<List<String>> clases;
    private String estado;
    private List<String> aceptados, nombresClase;
    
    private AFDPorClase(HashMap<String, HashMap<String, String>> sigma, List<String> aceptados,
            List<String> nombresClase, List<List<String>> clases){
        this.sigma = sigma;
        estado = "q0";
        this.aceptados = aceptados;
        this.clases = clases;
        this.nombresClase = nombresClase;
    }
    
    public static AFDPorClase newInstance(HashMap<String, HashMap<String, String>> sigma, 
            List<String> aceptados, List<String> nombresClase, List<List<String>> clases){
        if (sigma == null || aceptados == null || aceptados.isEmpty()){
            return null;
        } else {
            return new AFDPorClase(sigma, aceptados, nombresClase, clases);
        }
    }
    
    public void resetAFD(){
        estado = "q0";
    }
    
    public void evaluarSigma(String clase){
        if (estado != null){
            estado = sigma.get(estado).get(clase);            
        } else {
            estado = null;
        }
    }
    
    //Esto es lo único nuevo, ahora la entrada se clasifica, si llega un 1, por ejemplo,
    //regresaría el String "numero"
    public String clasificarEntrada(String entrada){
        int i;
        
        for (i = 0; i < clases.size(); i++){
            if (clases.get(i).contains(entrada)){
                break;
            }
            
            if (i == clases.size() - 1){
                return "Sin clasificar";
            }
        }
        
        return nombresClase.get(i);
    }
    
    public boolean esCorrecta(){
        return aceptados.contains(estado);
    }
}
