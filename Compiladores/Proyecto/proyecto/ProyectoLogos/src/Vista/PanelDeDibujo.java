package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JPanel;
import Configuracion.Configuracion;
import Configuracion.Linea;
/*
#Martinez Coronel Brayan Yosafat 
#Ramirez Olvera Guillermo
#Sanchez Mendez Edmundo Josue
#Proyecto: LOGOS
#18/01/2021
#3CM7
*/
public class PanelDeDibujo extends JPanel{
    
    Configuracion configuracion;
    
    public PanelDeDibujo(){
        configuracion = new Configuracion();  
    }
    
    public void setConfiguracion(Configuracion configuracion){
        this.configuracion = configuracion;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ArrayList<Linea> lineas = configuracion.getLineas();
        for(int i = 0; i < lineas.size(); i++){
            int x0 = (Propiedades.PANEL_DE_DIBUJO_ANCHO/2) + lineas.get(i).getX0();
            int y0 = (Propiedades.PANEL_DE_DIBUJO_LARGO/2) - lineas.get(i).getY0();
            int x1 = (Propiedades.PANEL_DE_DIBUJO_ANCHO/2) + lineas.get(i).getX1();
            int y1 = (Propiedades.PANEL_DE_DIBUJO_LARGO/2) - lineas.get(i).getY1();;
            g.setColor(lineas.get(i).getColor());
            g.drawLine(x0, y0, x1, y1);
        }
        g.setColor(Color.BLACK);
        Polygon triangulo = triangulo(configuracion.getX(), configuracion.getY(), configuracion.getAngulo());
        g.drawPolygon(triangulo);
        g.fillPolygon(triangulo);
    }

    public Polygon triangulo(double x, double y, int angulo){
        Polygon poligono = null;
        int xs[] = new int[3];
        int ys[] = new int[3];
        xs[0] = (Propiedades.PANEL_DE_DIBUJO_ANCHO/2) + (int) x;
        ys[0] = (Propiedades.PANEL_DE_DIBUJO_LARGO/2) - (int) y;
        xs[1] = (Propiedades.PANEL_DE_DIBUJO_ANCHO/2) + (int) (x - 10*Math.cos(Math.toRadians(angulo+20)));
        ys[1] = (Propiedades.PANEL_DE_DIBUJO_LARGO/2) - (int) (y - + 10*Math.sin(Math.toRadians(angulo+20)));
        xs[2] = (Propiedades.PANEL_DE_DIBUJO_ANCHO/2) + (int) (x - 10*Math.cos(Math.toRadians(angulo-20)));
        ys[2] = (Propiedades.PANEL_DE_DIBUJO_LARGO/2) - (int) (y - + 10*Math.sin(Math.toRadians(angulo-20)));
        poligono = new Polygon(xs,ys,3);
        return poligono;
    }
    
}
