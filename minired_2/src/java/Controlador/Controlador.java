/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import ModeloDAO.UsuarioDAO;
import config.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alma_
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String sign="signin.jsp";
    String principal="principal.jsp";
    String consultar="consultar.jsp";
    String registrar="registrar.jsp";
    String editar="editars.jsp";
    String index="index.jsp";
    String sesion="gestarsesion.jsp";
    String comprobarsesion="comprobar.jsp";
    Usuario us= new Usuario();
    UsuarioDAO usd= new UsuarioDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("consultar")){
            acceso=consultar;
        }
        else if(action.equalsIgnoreCase("registrar")){
            acceso=registrar;
        }else if(action.equalsIgnoreCase("Registrarme")){
            String nombre_usuario=request.getParameter("nombre_usuario").trim();
            String contraseña=request.getParameter("contraseña").trim();
            String nombre=request.getParameter("nombre").trim();
            String apellidos=request.getParameter("apellidos");
            String correo_electronico=request.getParameter("correo_electronico").trim();
            Pattern patternusuario=Pattern.compile("^[a-zA-Z0-9_]{4,16}$");
            Matcher matcherusuario=patternusuario.matcher(nombre_usuario);
            Pattern patternContraseña = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$");
            Matcher matcherContraseña = patternContraseña.matcher(contraseña);
            Pattern patternNombre = Pattern.compile("^[a-zA-Z]{1,20}$");
            Matcher matcherNombre = patternNombre.matcher(nombre);
            Pattern patternApellidos = Pattern.compile("^[a-zA-Z\\s]{1,50}$");
            Matcher matcherApellidos=patternApellidos.matcher(apellidos);
            Pattern patternCorreo=Pattern.compile("^.{1,30}@[\\w.]+[a-zA-Z]{3,10}$");
            Matcher matcherCorreo=patternCorreo.matcher(correo_electronico);
            UsuarioDAO usd=new UsuarioDAO();
            
            if (nombre.isEmpty()) {
                String mensaje_error="El nombre no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if (apellidos.isEmpty()) {
                String mensaje_error="Los apellidos no pueden estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if(nombre_usuario.isEmpty()){
                String mensaje_error="El nombre de usuario no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if(contraseña.isEmpty()){
                String mensaje_error="La contraseña no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if(correo_electronico.isEmpty()){
                String mensaje_error="El correo electrónico no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }else if (usd.existeNombreUsuario(nombre_usuario)) {
                String mensaje_error = "El nombre de usuario ya está en uso. Por favor, elige otro nombre.";
                request.setAttribute("mensaje_error", mensaje_error);
                acceso = sign;
            }
            else if (!matcherusuario.matches()) {
                String mensaje_error="El nombre de usuario debe tener entre 4 y 16 caracteres y sólo puede contener letras, números y guiones bajos.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if (!matcherContraseña.matches()) {
                String mensaje_error="La contraseña debe tener al menos 8 caracteres,máximo 20 y contener al menos una letra minúscula, una letra mayúscula y un número.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if (!matcherNombre.matches()) {
                String mensaje_error="El nombre solo lleva letras sin espacios en blanco y es de maximo 20 letras.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if (!matcherApellidos.matches()) {
                String mensaje_error="Los apellidos solo llevan letras y espacios en blanco y son de maximo 50 caracteres.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else if (!matcherCorreo.matches()) {
                String mensaje_error="Por favor, ingrese un correo electrónico válido de maximo 30 caracteres.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = sign;
            }
            else {
                // Todos los datos son válidos, guardar en la base de datos
                us.setNombre_usuario(nombre_usuario);
                us.setContraseña(contraseña);
                us.setNombre(nombre);
                us.setApellidos(apellidos);
                us.setCorreo_electronico(correo_electronico);
                usd.registrar(us);
                acceso=index;
            }
        }else if(action.equalsIgnoreCase("Actualizar")){
            String nombre=request.getParameter("nombre").trim();
            String apellidos=request.getParameter("apellidos");
            String nombre_usuario=request.getParameter("nombre_usuario").trim();
            String contraseña=request.getParameter("contraseña").trim();
            String correo_electronico=request.getParameter("correo_electronico").trim();
            Pattern patternusuario=Pattern.compile("^[a-zA-Z0-9_]{4,16}$");
            Matcher matcherusuario=patternusuario.matcher(nombre_usuario);
            Pattern patternContraseña = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$");
            Matcher matcherContraseña = patternContraseña.matcher(contraseña);
            Pattern patternCorreo=Pattern.compile("^.{1,30}@[\\w.]+[a-zA-Z]{3,10}$");
            Matcher matcherCorreo=patternCorreo.matcher(correo_electronico);
            
            
            
            
            
            if(nombre_usuario.isEmpty()){
                String mensaje_error="El nombre de usuario no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }else if(contraseña.isEmpty()){
                String mensaje_error="La contraseña no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }else if(correo_electronico.isEmpty()){
                String mensaje_error="El correo electrónico no puede estar en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }
            else if (!matcherusuario.matches()) {
                String mensaje_error="El nombre de usuario debe tener entre 4 y 16 caracteres y sólo puede contener letras, números y guiones bajos.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }
            else if (!matcherContraseña.matches()) {
                String mensaje_error="La contraseña debe tener al menos 8 caracteres,máximo 20 y contener al menos una letra minúscula, una letra mayúscula y un número.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }else if (!matcherCorreo.matches()) {
                String mensaje_error="Por favor, ingrese un correo electrónico válido de maximo 30 caracteres.";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }
            else{
                us.setNombre(nombre);
                us.setApellidos(apellidos);
                us.setNombre_usuario(nombre_usuario);
                us.setContraseña(contraseña);            
                us.setCorreo_electronico(correo_electronico);
                usd.editar(us);
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                acceso=index; 
            }

        }else if(action.equalsIgnoreCase("Editar cuenta")){
           String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
           request.setAttribute("nombresesion",nombreUsuario);
           acceso=editar;
        }else if(action.equalsIgnoreCase("Eliminar cuenta"))
        {
            String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
            usd.eliminar(nombreUsuario);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            acceso=index;
        }else if(action.equalsIgnoreCase("close"))
        {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            acceso=index;
        }else if(action.equalsIgnoreCase("comprobar")){
        String contraseña = request.getParameter("contraseña");
        String nombre_usuario=(String) request.getSession().getAttribute("nombre_usuario");
        Connection con;
        Statement st;
        ResultSet rs;
        Conexion cn= new Conexion();
        
        String sql = "SELECT * FROM usuario WHERE nombre_usuario='" + nombre_usuario + "' AND contraseña='" + contraseña + "'";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);

            if (rs.next()) {
                String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                Usuario usuario = usuarioDAO.obtenerPorNombreUsuario(nombreUsuario);

                request.setAttribute("usuario", usuario);
                acceso=sesion;
                
            } else {
                String errorMessage = "Contraseña incorrecta.";
                request.setAttribute("errorMessage", errorMessage);
                acceso=comprobarsesion;
            }
        }catch(Exception e)
                {
            
                }
        }
            
            
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
