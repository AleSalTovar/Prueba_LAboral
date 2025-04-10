/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import estructura.MateriaTD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloMateria;

/**
 *
 * @author alejo
 */
@WebServlet(name = "SvMaterias", urlPatterns = {"/SvMaterias"})
public class SvMaterias extends HttpServlet {

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
        
        ModeloMateria materia = new ModeloMateria();
        
        MateriaTD materiatd = new MateriaTD();
        
        int id_materia = Integer.parseInt(request.getParameter("id_materia"));

        String nombre_materia = request.getParameter("nombre_materia");
                
        String accion = request.getParameter("accion");
        
        switch(accion){
            case "crear_materia":

                materiatd.setId_materia(id_materia);
                materiatd.setNombre_materia(nombre_materia);
                if(materia.insertarRegistro(materiatd)){
                    request.setAttribute("mesaje", "Materia Guardado");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                    //response.sendRedirect("estudiantes.jsp?msj='ddf'");  
                }else{
                    request.setAttribute("materiatd", materiatd);
                    request.setAttribute("mesaje", materia.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "actualizar_materia":
                
                materiatd.setNombre_materia(nombre_materia);
                
                materia.setWhere("WHERE id_materia="+id_materia);
                if(materia.actualizarRegistro(materiatd)){
                    request.setAttribute("mesaje", "Materia actualizada");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("materiatd", materiatd);
                    request.setAttribute("mesaje", materia.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "eliminar_materia":
                
                materia.setWhere("WHERE id_materia="+id_materia);
                if(materia.eliminarRegistro()){
                    request.setAttribute("mesaje", "Materia eliminada");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("materiatd", materiatd);
                    request.setAttribute("mesaje", materia.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "consultar_materia":
                materia.setWhere("WHERE id_materia="+id_materia);
                if(materia.consultarRegistro()){
                    if(materia.siguienteRegistro()){
                        request.setAttribute("estudiantetd", materia.obtenerRegistro());
                        request.setAttribute("mesaje", "");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                        dispatcher.forward(request, response);
                    }else{
                        request.setAttribute("estudiantetd", null);
                        request.setAttribute("mesaje", "");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                        dispatcher.forward(request, response);
                    }
                           
                }else{
                    request.setAttribute("estudiantetd", null);
                    request.setAttribute("mesaje", materia.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("materias.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
