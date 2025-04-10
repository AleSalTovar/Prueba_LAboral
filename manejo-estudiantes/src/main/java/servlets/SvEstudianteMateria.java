/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import estructura.EstudianteMateriaTD;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloEstudianteMateria;

/**
 *
 * @author alejo
 */
@WebServlet(name = "SvEstudianteMateria", urlPatterns = {"/SvEstudianteMateria"})
public class SvEstudianteMateria extends HttpServlet {

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
        
        ModeloEstudianteMateria estudiante_materia = new ModeloEstudianteMateria();
        
        EstudianteMateriaTD estudiante_materiatd = new EstudianteMateriaTD();
        
        String accion = request.getParameter("accion");
        
        Long id_estudiante = Long.parseLong(request.getParameter("id_estudiante_materia"));
        
        int id_materia = Integer.parseInt(request.getParameter("id_materia"));
        
        switch(accion){
            case "asignar_materia":
                
                estudiante_materiatd.setId_estudiante(id_estudiante);
                estudiante_materiatd.setId_materia(id_materia);
                if(estudiante_materia.insertarRegistro(estudiante_materiatd)){
                    request.setAttribute("mesaje", "Aignación realizada");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiante_materia.jsp");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("materiatd", estudiante_materiatd);
                    request.setAttribute("mesaje", estudiante_materia.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiante_materia.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "desasignar_materia":
                estudiante_materia.setWhere("WHERE id_estudiante="+id_estudiante+" AND id_materia="+id_materia);
                if(estudiante_materia.eliminarRegistro()){
                    request.setAttribute("mesaje", "Desasignación realizada");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiante_materia.jsp");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("materiatd", estudiante_materiatd);
                    request.setAttribute("mesaje", estudiante_materia.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiante_materia.jsp");
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
