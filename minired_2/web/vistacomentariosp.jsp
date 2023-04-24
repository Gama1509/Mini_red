<%-- 
    Document   : vistacomentarios
    Created on : 21 abr 2023, 16:30:42
    Author     : gamae
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Comentario"%>
<%@page import="ModeloDAO.ComentarioDAO"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comentarios</title>
<link rel="stylesheet" href="Css/Vistascomentarios.css"/>
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
<body><div class="Hechos">
    <h1>Comentarios hechos por <%=request.getParameter("nombre_usuario")%></h1>
    </div>
    <form action="Controladorcomentarios" method="get">
        <input type="hidden" name="accion" value="filtrarComentarios">
        <input type="hidden" name="nombre_usuario" value="<%= request.getParameter("nombre_usuario") %>">
        
        <div class="Fechas">
        <input type="date" name="fecha" id="fecha">
        <button type="submit" name="accion" value="filtrarComentarios">Filtrar</button>
        </div>
    </form>

    <br>
    <div class="Tabla">
    <table>
        <tr>
            <th>Destinatario</th>
            <th>Comentario</th>
            <th>Fecha</th>
            <th colspan="2">Acciones</th>
        </tr>
        <%
            String nombreUsuario = request.getParameter("nombre_usuario");
            ComentarioDAO dao = new ComentarioDAO();
            List<Comentario> comentarios;
            if(request.getAttribute("comentariosFiltrados") != null) {
                comentarios = (List<Comentario>)request.getAttribute("comentariosFiltrados");
            } else {
                comentarios = dao.consultar(nombreUsuario);
            }
            for (Comentario comentario : comentarios) {
        %>
        <tr>
            <td><%=comentario.getDestinatario()%></td>
            <td><%=comentario.getComentario()%></td>
            <td><%=comentario.getFecha()%></td>
            <td>
                <a href="Controladorcomentarios?accion=eliminar&comentario=<%=comentario.getComentario()%>&nombre_usuario=<%=nombreUsuario%>">Eliminar</a>
                <a href="Controladorcomentarios?accion=editarc&comentario=<%=comentario.getComentario()%>&nombre_usuario=<%=nombreUsuario%>">Editar</a>
            </td>
        </tr>
        <% } %>
    </table>
    </div>
    <div class="volver">
    <form action="principal.jsp">
        <button type="submit">Volver</button>
    </form>
    </div>
    <% String errorMessage = (String) request.getAttribute("mensaje_error");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
        <footer></footer>
</body>
</html>
