<%-- 
    Document   : estudiante_materia
    Created on : 7/04/2025, 4:18:44 p. m.
    Author     : alejo
--%>

<%@page import="estructura.EstudianteMateriaTD"%>
<%@page import="modelo.ModeloMateria"%>
<%@page import="estructura.MateriaTD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeramitad.jsp"%>

<%  
    String mesaje = (String)request.getAttribute("mesaje");
    EstudianteMateriaTD estudiante_materia = (EstudianteMateriaTD)request.getAttribute("estudiante_materiatd");
%>
<div class="container">
    <h1>Asignación de materias</h1>
    
    <form class="user" action="SvEstudianteMateria" method="POST">
        <div class="form-group">
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="id_estudiante_materia"
                    placeholder="Id Estudiante" name="id_estudiante_materia">
            </div>
            <div class="col-sm-6 mb-3">
                <select class="form-select form-control-user" name="id_materia" id="id_materia">
                    <option value="0">Materias</option>
                    <% modelo.ModeloMateria materia = new ModeloMateria();
                       materia.setWhere("Order by nombre_materia");
                       if (materia.consultarRegistro()){
                        while(materia.siguienteRegistro()){
                            MateriaTD registro = materia.obtenerRegistro();
                        %>
                        <option value=<%=registro.getId_materia()%>>
                        <%=registro.getNombre_materia()%></option>
                    <%
                        }
                        }
                    %>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" type="submit" id="asignar_materia" 
                        value="asignar_materia" name="accion">
                    Asignar Materia
                </button>
            </div>
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" type="submit" id="desasignar_materia"
                        value="desasignar_materia" name="accion">
                    Desasignar Materia
                </button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-6">
                <button type="button" class="btn btn-primary btn-user btn-block" onclick="limpiarFormulario()">
                    Limpiar
                </button>
            </div>
        </div>
        <hr>
        <% 
            if(mesaje != null){
        %>
        <div class="col-sm-6 mb-3">
            <p id="mesaje"><%=mesaje%></p>
        </div>
        <%    }
            %>
    <script>
    function limpiarFormulario() {
        document.getElementById("id_estudiante_materia").value = "";
        document.getElementById("id_materia").value = "0";
        
        const mesaje = document.getElementById("mesaje");
        if (mesaje) {
            mesaje.textContent = "";
        }
    }
    </script>
    </form>
</div>

<%@include file="components/bodysegundamitad.jsp"%>
