/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yosaf
 */
public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Enumeration enum1 = request.getParameterNames();
            
            while(enum1.hasMoreElements()) {
                String nombre = (String) enum1.nextElement();

                String valores[] = request.getParameterValues(nombre);
                if (valores != null) {
                    for (int i = 0; i < valores.length; i++) {
                        out.println(nombre + " (" + i + "): " + valores[i]);
                        out.println("<br/>");
                    }
                }
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }
}
