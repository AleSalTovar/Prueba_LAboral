/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
console.log("funciones_materia.js cargado correctamente");
document.addEventListener("DOMContentLoaded", function() {
    const id_materia = document.getElementById("id_materia");
    const nombre_materia = document.getElementById("nombre_materia");
    
    const mensaje_id_materia = document.getElementById("mensaje_id_materia");
    
    const boton_crear_materia = document.getElementById("crear_materia");
    
    id_materia.addEventListener("input", function () {
        const valor = id_materia.value;
        const regex = /^[0-9]{1,3}$/;

        if (regex.test(valor)) {
            mensaje_id_materia.style.display = "none";
        } else {
            mensaje_id_materia.style.display = "block";
        }
    });
    
    function validar_materia() {
        const id_valido_materia = /^[0-9]{1,3}$/.test(id_materia.value);
        const nombre_valido_materia = /^[A-Za-z\s]{1,30}$/.test(nombre_materia.value);

        boton_crear_materia.disabled = !(id_valido_materia && nombre_valido_materia);
    }
    
    id_materia.addEventListener("input", validar_materia);
    nombre_materia.addEventListener("input", validar_materia);
});
