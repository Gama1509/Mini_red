/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
function validarFormulario() {
  var nombre,apellidos,nombre_usuario,contraseña,correo_electronico;
  nombre=document.getElementById("nombre").value.trim();
  nombre_usuario=document.getElementById("nombre_usuario").value.trim();
  apellidos=document.getElementById("apellidos").value;
  contraseña=document.getElementById("contraseña").value();
  correo_electronico=document.getElementById("correo_electronico").value;
    const nombreUsuarioRegex =/^[a-zA-Z0-9_]{4,16}$/;
  const nombreRegex=/^[a-zA-Z]{1,20}$/;
    if(nombre==="" || apellidos==="" || nombre_usuario==="" || contraseña==="" || correo_electronico===""){
        alert("No puede dejar espacios en blanco");
        return false;
    }
    else if (!nombreRegex.test(nombre)) {
        alert("El nombre solo lleva letras sin espacios en blanco y es de maximo 20 letras.");
        return false;
    } else if(!nombreUsuarioRegex.test(nombre_usuario)){
        alert("El nombre de usuario debe tener entre 4 y 16 caracteres y sólo puede contener letras, números y guiones bajos.");
        return false;
    }
    

}


