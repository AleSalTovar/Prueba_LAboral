<%-- 
    Document   : materias
    Created on : 4/04/2025, 11:23:00 a. m.
    Author     : alejo
--%>

<%@page import="estructura.MateriaTD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeramitad.jsp"%>

<% 
    String mesaje = (String)request.getAttribute("mesaje");
    MateriaTD materiatd = (MateriaTD)request.getAttribute("estudiantetd");
%>
<div class="container">
    <h1>Manejo Matería</h1>
    
    <form class="user" action="SvMaterias" method="POST">
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="id_materia"
                    placeholder="Id Materia" name="id_materia"
                <%
                    if (materiatd != null){
                            %> value="<%=materiatd.getId_materia()%>"
                <%    
                    }
                %>
                >
                <small id="mensaje_id_materia" style="color: red; display: none;">
                    Identificación incorrecto: solo números, máximo 3 números.
                </small>
            </div>
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" id="consultar_materia"
                        value="consultar_materia" name="accion">
                    Consultar Materia
                </button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="nombre_materia"
                    placeholder="Nombre materia" name="nombre_materia"
                <%
                    if (materiatd != null){
                            %> value="<%=materiatd.getNombre_materia()%>"
                <%    
                    }
                %>
                >
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block" type="submit" id="crear_materia" disabled
                        value="crear_materia" name="accion">
                    Crear Materia
                </button>
            </div>
            <div class="col-sm-6">
                <button class="btn btn-primary btn-user btn-block"
                        value="eliminar_materia" name="accion">
                    Eliminar Materia
                </button>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <button class="btn btn-primary btn-user btn-block"
                        value="actualizar_materia" name="accion">
                    Actualizar Materia
                </button>
            </div>
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
        document.getElementById("id_materia").value = "";
        document.getElementById("nombre_materia").value = "";
        
        const mesaje = document.getElementById("mesaje");
        if (mesaje) {
            mesaje.textContent = "";
        }
    }
    </script>
    </form>
    </div>
</div>

<%@include file="components/bodysegundamitad.jsp"%>
