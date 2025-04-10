<%-- 
    Document   : consulta
    Created on : 4/04/2025, 3:45:37 p. m.
    Author     : alejo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeramitad.jsp"%>

<%
    ArrayList tabla = new ArrayList();
    tabla = (ArrayList)request.getAttribute("tabla");
    String mesaje = (String)request.getAttribute("mesaje");
%>
<div class="container">
    <h1>Consulta</h1>
    
    <form class="user" action="SvConsultaEstudiantes" method="POST">
        <div class="form-group">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="id_materia"
                    placeholder="Id materia" name="id_materia">
            </div>
        </div>
            <div class="form-group">
                <h2>Lista de Estudiantes</h2>
                <table class="table table-bordered table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Identificación</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  
                            if(tabla != null){
                            for(int i = 0; i<tabla.size(); i++) {
                            String[] linea = (String[])tabla.get(i);
                        %>
                            <tr>
                                <td><%= i+1 %></td>
                                <td><%= linea[0] %></td>
                                <td><%= linea[1] %></td>
                            </tr>
                        <%
                            }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        <div class="form-group">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" type="submit">
                    Consultar Estudiantes
                </button>
            </div>
        </div>
        <hr>
        <% 
            if(mesaje != null){
        %>
        <div class="col-sm-6 mb-3">
            <p><%=mesaje%></p>
        </div>
        <%    }
            %>
    </form>
</div>

<%@include file="components/bodysegundamitad.jsp"%>