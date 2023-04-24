/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function ValidarComentario(){
    var comentario;
    comentario=document.getElementById("comentario").value;
    const comentarioRegex=/^[^\n]*$/;
    if(comentario==="")
    {
        alert("El comentario no puede quedarse vacio");
        return false;
    }
    else if(!comentarioRegex.test(comentario)){
        alert("El comentario no puede llevar saltos de linea");
        return false;
    }
}