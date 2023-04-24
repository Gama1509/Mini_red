<%-- 
    Document   : signin
    Created on : 21 abr 2023, 08:34:56
    Author     : gamae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
        <link rel="stylesheet" href="Css/signin.css"/>
        <script src="Javascript/validarsigin.js"></script>
    </head>
    <body>
       <div class="divarriba"> 
        <form onsubmit="return validarFormulario()" action="Controlador">
            <h1>Registrate</h1>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required pattern="[A-Za-z\s]{1,20}" title="El nombre solo puede contener letras mayúsculas y minúsculas y espacios en blanco." maxlength="20"><br>
            <label for="apellido">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos" required pattern="[a-zA-Z\s]{1,50}" title="Los apellidos solo llevan letras y espacios en blanco y son de maximo 50 caracteres." maxlength="50"><br>
            <label for="correo">Correo electrónico:</label>
            <input type="email" id="correo_electronico"name="correo_electronico" maxlength="44" required><br>
            <label for="usuario">Nombre de usuario:</label>
            <input type="text" id="nombre_usuario" name="nombre_usuario" required pattern="[a-zA-Z0-9_]{4,16}" title="El nombre de usuario debe tener entre 4 y 16 caracteres y sólo puede contener letras, números y guiones bajos." minlength="4" maxlength="16"><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="contraseña" name="contraseña" required pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$" title="La contraseña debe contener al menos 8 caracteres y maximo 20, una letra mayúscula, una letra minúscula, un número y un caracter especial." maxlength="20" minlength="8"><br>
            <input type="submit" name="accion" value="Registrarme">
            <p>¿Ya tienes una cuenta? <a href="index.jsp">Inicia sesión</a></p>
        </form>
       </div>
    <% String errorMessage = (String) request.getAttribute("mensaje_error");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
    </body>
</html>
