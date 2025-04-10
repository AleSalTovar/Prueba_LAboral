/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utilidades.XMLReader;

/**
 *
 * @author alejo
 */
@WebServlet(name = "SvConsultaMaterias", urlPatterns = {"/SvConsultaMaterias"})
public class SvConsultaMaterias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long id_estudiante = Long.parseLong(request.getParameter("id_estudiante"));
        
        ArrayList tabla = new ArrayList();

        try {
            local.serverwebservice.WebService adicion = new local.serverwebservice.WebService();
            local.serverwebservice.ServerWebService port = adicion.getServerWebServicePort();

            //String res = port.materiasestudiante(a);
            String res = port.materiasestudiante(String.valueOf(id_estudiante));

            Document doc = XMLReader.convertStringToDocument(res);

            NodeList nodeList = doc.getElementsByTagName("materia");
            for (int i = 0; i < nodeList.getLength(); i++) {
                String[] linea = new String[2];
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element employee = (Element) node;
                    linea[0] = XMLReader.printComponent(employee, "codigo");
                    linea[1] = XMLReader.printComponent(employee, "nombre");
                    tabla.add(linea);
                }
            }
        
        if(tabla.size() != 0){
            request.setAttribute("tabla", tabla);
            RequestDispatcher dispatcher = request.getRequestDispatcher("consulta_materia.jsp");
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("tabla", null);
            request.setAttribute("mesaje", "no existe el estudiante");
            RequestDispatcher dispatcher = request.getRequestDispatcher("consulta_materia.jsp");
            dispatcher.forward(request, response);
        }
            
        } catch (Exception E) {

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
