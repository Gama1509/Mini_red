<%-- 
    Document   : editcomentario
    Created on : 22 abr 2023, 12:30:42
    Author     : gamae
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Modelo.Comentario"%>
<%@page import="ModeloDAO.ComentarioDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Comentario</title>
<link rel="stylesheet" href="Css/Comentar.css"/>
<script src="Javascript/Validarcomentario.js"></script>
</head>
<header>
        <div class="header-right">
            <a href="Contacto.jsp">Información de contacto</a>
            <span class="header-separator"></span>
          <a href="Controlador?accion=close">Cerrar sesión</a>
          <span class="header-separator"></span>
          <a href="comprobar.jsp">Gestionar cuenta</a>
        </div>
    </header>
<body>
    <h1>Editar Comentario</h1>
    <form onsubmit="return ValidarComentario()" action="Controladorcomentarios" method="get">
        <input type="hidden" name="accion" value="guardarcambios">
        <input type="hidden" name="nombre_usuario" value="<%= request.getParameter("nombre_usuario") %>">
        <input type="hidden" name="comentarioAntiguo" value="<%= request.getParameter("comentario") %>">
        <textarea name="comentarioNuevo" id="comentario" rows="5" cols="50" maxlength="10000" required><%= request.getParameter("comentario") %></textarea><br><br>
        <button type="submit">Guardar Cambios</button>
    </form>
    <form action="principal.jsp">
        <button type="submit">Volver</button>
    </form>
    <% String errorMessage = (String) request.getAttribute("mensaje_error");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
        <footer></footer>
</body>

</html>

