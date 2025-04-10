/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloEstudiante;
import modelo.ModeloPais;
import estructura.EstudianteTD;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author alejo
 */
@WebServlet(name = "SvEstudiante", urlPatterns = {"/SvEstudiante"})
public class SvEstudiante extends HttpServlet {

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
        
        ModeloEstudiante estudiante = new ModeloEstudiante();
        ModeloPais paismodelo = new ModeloPais();
        
        EstudianteTD estudiantetd = new EstudianteTD();
        
        String accion = request.getParameter("accion");
        
        String dominio = "";
        
        boolean email_encontrado = false;
        
        Long id_estudiante = Long.parseLong(request.getParameter("id_estudiante"));

        String nombre_estudiante = request.getParameter("nombre_estudiante");

        String apellido_estudiante = request.getParameter("apellido_estudiante");

        int pais = Integer.parseInt(request.getParameter("pais"));
        
        switch(accion){
            case "crear_estudiante":
                
                paismodelo.setWhere("WHERE id_pais="+pais);
                if(paismodelo.consultarRegistro()){
                    if(paismodelo.siguienteRegistro()){
                        dominio = paismodelo.obtenerRegistro().getDominio_pais();
                    }
                }

                StringTokenizer nombre_crear = new StringTokenizer(nombre_estudiante);
                StringTokenizer apellido_crear = new StringTokenizer(apellido_estudiante);
                String nombre_guardado = nombre_crear.nextToken();
                String apellido_guardado = apellido_crear.nextToken();
                String crear_email = nombre_guardado + "." + apellido_guardado + "@fasttrack.com." + dominio;
                
                while(!email_encontrado){
                   
                   estudiante.setWhere("WHERE email='"+crear_email+"' AND id_estudiante<>" + id_estudiante);
                   if(estudiante.consultarRegistro()){
                       if(estudiante.siguienteRegistro()){
                           int cont = 2;
                           String[] partes_correo = crear_email.split("@");
                           String[] partes_login = partes_correo[0].split("\\.");
                           try{
                               cont = Integer.parseInt(partes_login[partes_login.length-1]);
                               cont++;
                           }catch(Exception e){
                               cont = 2;
                           }
                           crear_email = nombre_guardado + "." + apellido_guardado + "." + cont + "@fasttrack.com." + dominio;
                       }else{
                           email_encontrado = true;
                       }
                   }
                }

                String email = crear_email.toLowerCase();
                
                estudiantetd.setId_estudiante(id_estudiante);
                estudiantetd.setNombre_estudiante(nombre_estudiante);
                estudiantetd.setApellido_estudiante(apellido_estudiante);
                estudiantetd.setEmail(email);
                estudiantetd.setPais(pais);
                
                if(estudiante.insertarRegistro(estudiantetd)){
                    request.setAttribute("mesaje", "Estudiante Guardado");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                    dispatcher.forward(request, response); 
                }else{
                    request.setAttribute("estudiantetd", estudiantetd);
                    request.setAttribute("mesaje", estudiante.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "actualizar_estudiante":
                
                paismodelo.setWhere("WHERE id_pais="+pais);
                if(paismodelo.consultarRegistro()){
                    if(paismodelo.siguienteRegistro()){
                        dominio = paismodelo.obtenerRegistro().getDominio_pais();
                    }
                }

                StringTokenizer nombre_actualizar = new StringTokenizer(nombre_estudiante);
                StringTokenizer apellido_actualizar = new StringTokenizer(apellido_estudiante);
                String nombre_actualizado = nombre_actualizar.nextToken();
                String apellido_actualizado = apellido_actualizar.nextToken();
                String actualizar_email = nombre_actualizado + "." + apellido_actualizado + "@fasttrack.com." + dominio;
                
                while(!email_encontrado){
                   
                   estudiante.setWhere("WHERE email='"+actualizar_email+"' AND id_estudiante<>" + id_estudiante);
                   if(estudiante.consultarRegistro()){
                       if(estudiante.siguienteRegistro()){
                           int cont = 2;
                           String[] partes_correo = actualizar_email.split("@");
                           String[] partes_login = partes_correo[0].split("\\.");
                           try{
                               cont = Integer.parseInt(partes_login[partes_login.length-1]);
                               cont++;
                           }catch(Exception e){
                               cont = 2;
                           }
                           actualizar_email = nombre_actualizado + "." + apellido_actualizado + "." + cont + "@fasttrack.com." + dominio;
                       }else{
                           email_encontrado = true;
                       }
                   }
                }

                String email_actualizado = actualizar_email.toLowerCase();
                
                estudiantetd.setNombre_estudiante(nombre_estudiante);
                estudiantetd.setApellido_estudiante(apellido_estudiante);
                estudiantetd.setEmail(email_actualizado);
                estudiantetd.setPais(pais);
                
                estudiante.setWhere("WHERE id_estudiante="+id_estudiante);
                if(estudiante.actualizarRegistro(estudiantetd)){
                    request.setAttribute("mesaje", "Estudiante Actualizado");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("estudiantetd", estudiantetd);
                    request.setAttribute("mesaje", estudiante.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "eliminar_estudiante":
                estudiante.setWhere("WHERE id_estudiante="+id_estudiante);
                if(estudiante.eliminarRegistro()){
                    request.setAttribute("mesaje", "Estudiante Eliminado");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                    dispatcher.forward(request, response);
                }else{
                    request.setAttribute("estudiantetd", null);
                    request.setAttribute("mesaje", estudiante.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "consultar_estudiante":
                estudiante.setWhere("WHERE id_estudiante="+id_estudiante);
                if(estudiante.consultarRegistro()){
                    if(estudiante.siguienteRegistro()){
                        request.setAttribute("estudiantetd", estudiante.obtenerRegistro());
                        request.setAttribute("mesaje", "");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                        dispatcher.forward(request, response);
                    }else{
                        request.setAttribute("estudiantetd", null);
                        request.setAttribute("mesaje", "");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
                        dispatcher.forward(request, response);
                    }
                           
                }else{
                    request.setAttribute("estudiantetd", null);
                    request.setAttribute("mesaje", estudiante.getError());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("estudiantes.jsp");
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
