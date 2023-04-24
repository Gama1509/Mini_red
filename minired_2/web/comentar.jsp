<%-- 
    Document   : comentar
    Created on : 21 abr 2023, 11:41:29
    Author     : gamae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentar</title>
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
        <h1>Comentario a ${nombre_usuario}</h1>
        <form onsubmit="return ValidarComentario()" action="Controladorcomentarios" method="get">
            <input type=hidden name="nombre_usuario" value="${nombre_usuario}" />
            <textarea id="comentario" name="comentario" required maxlength="10000"></textarea>
            <br>
            <input type="submit" name="accion" value="Subir comentario" />
        </form>
        <form action="principal.jsp">
            <button type="submit">Volver</button>
        </form>
            <<footer></footer>
    </body>
</html>