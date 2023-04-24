<%-- 
    Document   : gestarsesion
    Created on : 22 abr 2023, 19:11:53
    Author     : gamae
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mi sesión</title>
        <link rel="stylesheet" href="Css/comprobar.css"/>
    </head>
    <header>
        <div class="header-right">
        </div>
    </header>
    <body>
        <form action="Controlador" method="get">
                <h1>Comprueba tu identidad</h1>
                <input type="hidden" name="accion" value="comprobar">
		<label for="contraseña">Ingresa tu contraseña:</label>
		<input type="password" id="contraseña" name="contraseña" required><br><br>
		<input type="submit" value="Comprobar">
	</form>
        <form action="principal.jsp">
            <button type="submit">Volver</button>
        </form>
        
        <% String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) { %>
           <script>alert('<%= errorMessage %>');</script>
        <% } %>
        
        
        
 
        <footer></footer>
    </body>
    
</html>
