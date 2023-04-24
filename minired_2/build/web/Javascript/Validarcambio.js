/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
function ValidarCambio(){
    var nombre_usuario,contraseña,correo_electronico;
  nombre_usuario=document.getElementById("nombre_usuario").value.trim();
  contraseña=document.getElementById("contraseña").value.trim();
  correo_electronico=document.getElementById("correo_electronico").value.trim();
  const nombreUsuarioRegex =/^[a-zA-Z0-9_]{4,16}$/;
  
    if(nombre_usuario==="" || contraseña==="" || correo_electronico===""){
        alert("No puede dejar espacios en blanco");
        return false;
    
    
    } else if(!nombreUsuarioRegex.test(nombre_usuario)){
        alert("El nombre de usuario debe tener entre 4 y 16 caracteres y sólo puede contener letras, números y guiones bajos.");
        return false;
    
    }




}

