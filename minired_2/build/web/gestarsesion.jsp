<%-- 
    Document   : gestarsesion
    Created on : 22 abr 2023, 19:45:51
    Author     : gamae
--%>


<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mi sesión</title>
        <link rel="stylesheet" href="Css/gestor.css"/>
    </head>
    <body>
        <div class="column-left"></div>
        
        <%Usuario usuario = (Usuario) request.getAttribute("usuario");%>
        <label>Nombre</label><input type="text" readonly value="<%= usuario.getNombre() %>"><br>
        <label>Apellidos</label><input type="text" readonly value="<%= usuario.getApellidos() %>"><br>
        <label>Nombre de usuario</label>
        <input type="text" readonly value="<%= usuario.getNombre_usuario() %>"><br>
        <label>Contraseña</label>
        <input type="text" readonly value="<%= usuario.getContraseña() %>"><br>
        <label>Correo electronico</label>
        <input type="text" readonly value="<%= usuario.getCorreo_electronico() %>"><br>
        <div class="botones">
        <form action="Controlador">
            <input type="submit" name="accion" value="Editar cuenta">
        </form>
        <form action="Controlador">
            <input type="submit" name="accion" value="Eliminar cuenta">
        </form>
        </div>    
        <form action="principal.jsp">
            <button type="submit">Volver</button>
        </form>
        <h4>Si eliminas tu cuenta se cerrara tu sesion</h4>
        <div class="column-right"></div>
        
    </body>
    <% String errorMessage = (String) request.getAttribute("mensaje_error");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
</html>
