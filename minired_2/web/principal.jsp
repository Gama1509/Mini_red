<%-- 
    Document   : principal
    Created on : 21 abr 2023, 11:04:02
    Author     : gamae
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuario"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios Registrados</title>
        <link rel="stylesheet" href="Css/menucss.css"/>
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

        <div style="text-align: center;">

       <table style="margin: 0 auto; margin-top: 7%">
            <thead>
                <tr>
                    <th>Nombre de usuario</th>
                    <th colspan="2">Acciones</th>
                </tr>
            </thead>
            <tbody>
            <% 
                UsuarioDAO dao = new UsuarioDAO();
                List<Usuario> listaUsuarios = dao.consultar();
                Iterator<Usuario> iter = listaUsuarios.iterator();
                while(iter.hasNext()){
                    Usuario usuario = iter.next();
            %>
                <tr>
                    <td><%= usuario.getNombre_usuario() %></td>
                    <td>
                        <a href="Controladorcomentarios?accion=mcomentar&nombre_usuario=<%=usuario.getNombre_usuario()%>">Comentar</a>
                    </td>
                    <td>
                        <a href="Controladorcomentarios?accion=gvc&nombre_usuario=<%=usuario.getNombre_usuario()%>">Ver comentarios</a>

                    </td>
                </tr>
                
            <%}%>
            </tbody>
        </table>
        </div>
    </body>
    <% String errorMessage = (String) request.getAttribute("mensaje_error");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
    <footer>
        
    </footer>
</html>