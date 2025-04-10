<%-- 
    Document   : crear_estudiante
    Created on : 3/04/2025, 9:42:11 a. m.
    Author     : alejo
--%>

<%@page import="estructura.EstudianteTD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeramitad.jsp"%>
<%@page import="modelo.ModeloPais" %> 
<%@page import="estructura.PaisTD"%>

<% 
    String mesaje = (String)request.getAttribute("mesaje");
    EstudianteTD estudiantetd = (EstudianteTD)request.getAttribute("estudiantetd");
    
%>
<div class="container">
    <h1>Manejo Estudiante</h1>

    <form class="user" action="SvEstudiante" method="POST">
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="id_estudiante"
                    placeholder="Identificación" name="id_estudiante"
                    <%
                        if (estudiantetd != null){
                            %> value="<%=estudiantetd.getId_estudiante()%>"
                    <%    
                        }
                    %>
                >
                <small id="mensaje_id_estudiante" style="color: red; display: none;">
                    Identificación incorrecto: solo números, de seis a once números.
                </small>
            </div>
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" id="consultar_estudiante"
                        value="consultar_estudiante" name="accion">
                    Consultar Estudiante
                </button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="nombre_estudiante"
                    placeholder="Primer nombre" name="nombre_estudiante"
                    <%
                        if (estudiantetd != null){
                            %> value="<%=estudiantetd.getNombre_estudiante()%>"
                    <%    
                        }
                    %>
                >                    
                <small id="mensaje_nombre_estudiante" style="color: red; display: none;">
                    Nombre incorrecto: solo MAYÚSCULAS sin tildes, ñ ni caracteres especiales.
                </small>
            </div>
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="apellido_estudiante"
                    placeholder="Primer apellido" name="apellido_estudiante"
                    <%
                        if (estudiantetd != null){
                            %> value="<%=estudiantetd.getApellido_estudiante()%>"
                    <%    
                        }
                    %>
                >
                <small id="mensaje_apellido_estudiante" style="color: red; display: none;">
                    Apellido incorrecto: solo MAYÚSCULAS sin tildes, ñ ni caracteres especiales.
                </small>
            </div>
            <div class="col-sm-6 mb-3">
                <select class="form-select form-control-user" name="pais" id="pais">
                    <option value="0">País</option>
                    <% modelo.ModeloPais pais = new ModeloPais();
                       pais.setWhere("Order by nombre_pais");
                       if (pais.consultarRegistro()){
                        while(pais.siguienteRegistro()){
                            PaisTD registro = pais.obtenerRegistro();
                        %>
                        <option value=<%=registro.getId_pais()%>
                                <%if (estudiantetd != null && registro.getId_pais() == estudiantetd.getPais()){%> selected <%}%>><%=registro.getNombre_pais()%></option>
                    <%
                        }
                        }
                    %>
                </select>
            </div>
            <div class="col-sm-6 mb-3">
                <input class="form-control form-control-user" type="text" placeholder="Email" readonly name="email" id="email"
                    <%
                        if (estudiantetd != null){
                            %> value="<%=estudiantetd.getEmail()%>"
                    <%    
                        }
                    %>
                >
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" type="submit" id="crear_estudiante" disabled
                        value="crear_estudiante" name="accion">
                    Crear Estudiante
                </button>
            </div>
            <div class="col-sm-6">
                <button class="btn btn-primary btn-user btn-block" id="eliminar_estudiante"
                        value="eliminar_estudiante" name="accion">
                    Eliminar Estudiante
                </button>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" id="actualizar_estudiante"
                        value="actualizar_estudiante" name="accion">
                    Actualizar Estudiante
                </button>
            </div>
            <div class="col-sm-6">
                <button type="button" class="btn btn-primary btn-user btn-block" onclick="limpiarFormulario()">
                    Limpiar
                </button>
            </div>
        </div>
        <% 
            if(mesaje != null){
        %>
        <div class="col-sm-6 mb-3">
            <p id="mesaje"><%=mesaje%></p>
        </div>
        <%    }
            %>
        <hr>
    <script>
    function limpiarFormulario() {
        document.getElementById("id_estudiante").value = "";
        document.getElementById("nombre_estudiante").value = "";
        document.getElementById("apellido_estudiante").value = "";
        document.getElementById("pais").value = "0";
        document.getElementById("email").value = "";
        
        const mesaje = document.getElementById("mesaje");
        if (mesaje) {
            mesaje.textContent = "";
        }
    }
    </script>
    </form>
</div>

<%@include file="components/bodysegundamitad.jsp"%>