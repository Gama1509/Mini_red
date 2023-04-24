<%-- 
    Document   : editar
    Created on : 31 mar 2023, 19:14:00
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Usuario"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edita tu cuenta</title>
        <link rel="stylesheet" href="Css/eds.css"/>
        <script src="Javascript/Validarcambio.js"></script>
    </head>
    <body>
        <%
          UsuarioDAO usd=new UsuarioDAO();
          String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
          Usuario us=(Usuario)usd.list(nombreUsuario);
        
        
        %>
        <div class="column-left"></div>
        <h1>Modifica tus datos</h1>
        <form onsubmit="return ValidarCambio()" action="Controlador">
            <input type=hidden name="nombre" required pattern="[A-Za-z\s]{1,20}" title="El nombre solo puede contener letras mayúsculas y minúsculas y espacios en blanco." maxlength="20" id="nombre" value="<%=us.getNombre()%>"><br>
            <input type=hidden name="apellidos" id="apellidos" required pattern="[a-zA-Z\s]{1,50}" title="Los apellidos solo llevan letras y espacios en blanco y son de maximo 50 caracteres." maxlength="50" value="<%=us.getApellidos()%>"><br>
            Nombre de usuario <input type="text" name="nombre_usuario" required pattern="[a-zA-Z0-9_]{4,16}" title="El nombre de usuario debe tener entre 4 y 16 caracteres y sólo puede contener letras, números y guiones bajos." minlength="4" maxlength="16" id="nombre_usuario" value="<%=us.getNombre_usuario()%>"><br>
            Contraseña <input type="text" required pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$" title="La contraseña debe contener al menos 8 caracteres y maximo 20, una letra mayúscula, una letra minúscula, un número y un caracter especial." maxlength="20" minlength="8" id="contraseña" name="contraseña" value="<%=us.getContraseña()%>"><br>
            Correo electronico <input type="email" name="correo_electronico" id="correo_electronico" maxlength="44" required value="<%=us.getCorreo_electronico()%>"><br>
            
            <input type="submit" name="accion" value="Actualizar" >
            
        </form>
        <form action="principal.jsp">
            <button type="submit">Volver</button>
        </form>
            <h4>Una vez editada la cuenta se cerrara tu sesion</h4>
            <div class="column-right"></div>
    </body>
    <% String errorMessage = (String) request.getAttribute("mensaje_error");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
</html>
