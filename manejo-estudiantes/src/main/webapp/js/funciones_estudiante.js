/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    const id_estudiante = document.getElementById("id_estudiante");
    const nombre_estudiante = document.getElementById("nombre_estudiante");
    const apellido_estudiante = document.getElementById("apellido_estudiante");
    
    const mensaje_id_estudiante = document.getElementById("mensaje_id_estudiante");
    const mensaje_nombre_estudiante = document.getElementById("mensaje_nombre_estudiante");
    const mensaje_apellido_estudiante = document.getElementById("mensaje_apellido_estudiante");
    
    const select_pais = document.getElementById("pais");
    const boton_crear_estudiante = document.getElementById("crear_estudiante");
    
    id_estudiante.addEventListener("input", function () {
        const valor = id_estudiante.value;
        const regex = /^[0-9]{6,11}$/;

        if (regex.test(valor)) {
            mensaje_id_estudiante.style.display = "none";
        } else {
            mensaje_id_estudiante.style.display = "block";
        }
    });
    
    nombre_estudiante.addEventListener("input", function () {
        const valor = nombre_estudiante.value;
        const regex = /^[A-Z ]{3,30}$/;

        if (regex.test(valor)) {
            mensaje_nombre_estudiante.style.display = "none";
        } else {
            mensaje_nombre_estudiante.style.display = "block";
        }
    });
    
    apellido_estudiante.addEventListener("input", function () {
        const valor = apellido_estudiante.value;
        const regex = /^[A-Z ]{3,30}$/;

        if (regex.test(valor)) {
            mensaje_apellido_estudiante.style.display = "none";
        } else {
            mensaje_apellido_estudiante.style.display = "block";
        }
    });
    
    function validar_estudiante() {
        const id_valido = /^[0-9]{6,11}$/.test(id_estudiante.value);
        const nombre_valido = /^[A-Z\s]{3,30}$/.test(nombre_estudiante.value);
        const apellido_valido = /^[A-Z\s]{3,30}$/.test(apellido_estudiante.value);
        const pais_valido = select_pais.value !== "0";

        boton_crear_estudiante.disabled = !(id_valido && nombre_valido && apellido_valido && pais_valido);
    }
    
    id_estudiante.addEventListener("input", validar_estudiante);
    nombre_estudiante.addEventListener("input", validar_estudiante);
    apellido_estudiante.addEventListener("input", validar_estudiante);
    select_pais.addEventListener("change", validar_estudiante);
});

