/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Comentario;
import ModeloDAO.ComentarioDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gamae
 */
@WebServlet(name = "Controladorcomentarios", urlPatterns = {"/Controladorcomentarios"})
public class Controladorcomentarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controladorcomentarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controladorcomentarios at " + request.getContextPath() + "</h1>");
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
        String editar="comentar.jsp";
        String verc="vistacomentarios.jsp";
        String acceso="";
        String verca="vistacomentariosp.jsp";
        String principal="principal.jsp";
        String action=request.getParameter("accion");
        String editarc="editarc.jsp";
        Comentario cm=new Comentario();
        ComentarioDAO cmd=new ComentarioDAO();
        if(action.equalsIgnoreCase("mcomentar")){
           if(request.getParameter("nombre_usuario").isEmpty()){
                String mensaje_error="Ocurrio un error :(";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso=principal;
           }
           else{
             request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
            acceso=editar;  
           }
           
        }else if(action.equalsIgnoreCase("Subir comentario")){
            String destinatario=request.getParameter("nombre_usuario");
            String autor=(String) request.getSession().getAttribute("nombre_usuario");
            String comentario = request.getParameter("comentario");
            LocalDate fechaActual = LocalDate.now();
            String fecha = fechaActual.toString();
            Pattern patternComentario=Pattern.compile("^[^\\n]*$");
            Matcher matcherComentario=patternComentario.matcher(comentario);
            if(destinatario.isEmpty()){
                String mensaje_error="Ocurrio un error :(";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso=principal;
            }
            else if (comentario.isEmpty()){
                String mensaje_error="El comentario no puede quedarse en blanco";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso = principal;
            }
            else if (!matcherComentario.matches()){
                String mensaje_error="El comentario no puede llevar saltos de linea";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso=principal;
            }
            else{
                cm.setDestinatario(destinatario);
                cm.setAutor(autor);
                cm.setComentario(comentario);
                cm.setFecha(fecha);
                cmd.registrar(cm);
                acceso="principal.jsp";
            }
            
            
            
            
        }else if(action.equalsIgnoreCase("gvc"))
        {
            String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
            String nombreParametro = request.getParameter("nombre_usuario");
            if(nombreParametro.isEmpty()){
                String mensaje_error="Ocurrio un error :(";
                request.setAttribute("mensaje_error",mensaje_error);
                acceso=principal;
            }
            else if (nombreUsuario != null && nombreUsuario.equals(nombreParametro)) {
                request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                acceso =verca;
            } else {
                request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                acceso = verc;
            }

        }else if (action.equalsIgnoreCase("filtrarComentarios")) {
        String nombreUsuario = request.getParameter("nombre_usuario");
        String fecha = request.getParameter("fecha");
        if (nombreUsuario.isEmpty()){
            String mensaje_error="Ocurrio un error :(";
            request.setAttribute("mensaje_error",mensaje_error);
            acceso=principal;
        }
        else{
            List<Comentario> comentarios;
            if (fecha != null && !fecha.isEmpty()) {
                comentarios = cmd.filtrarPorFecha(nombreUsuario, fecha);
            } else {
                comentarios = cmd.consultar(nombreUsuario);
            }
            request.setAttribute("comentariosFiltrados", comentarios);
            acceso = verc;
        }
    }else if (action.equalsIgnoreCase("eliminar")) {
        String comentario = request.getParameter("comentario");
        String nombre_usuario=request.getParameter("nombre_usuario");
        String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
        
        if (comentario.isEmpty()){
            String mensaje_error="Ocurrio un error :(";
            request.setAttribute("mensaje_error",mensaje_error);
            acceso=principal;
        }
        else{
            ComentarioDAO dao = new ComentarioDAO();
            dao.eliminar(comentario);
            if (nombreUsuario != null && nombreUsuario.equals(nombre_usuario)) {
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso =verca;
                } else {
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso = verc;
                }
        }

    }else if (action.equalsIgnoreCase("editarc")) {
        // Enviar el comentario a la vista de edición
        String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
        if(request.getParameter("nombre_usuario").isEmpty()){   
            String mensaje_error="Ocurrio un error :(";
            request.setAttribute("mensaje_error",mensaje_error);
            acceso=principal;
        }
        else if (request.getParameter("comentario").isEmpty()){
            if (nombreUsuario != null && nombreUsuario.equals(request.getParameter("nombre_usuario"))) {
                    String mensaje_error="El comentario no puede quedarse en blanco";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso =verca;
                } else {
                    String mensaje_error="El comentario no puede quedarse en blanco";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso = verc;
                }
        }
        
        else{
            request.setAttribute("comentario",request.getParameter("comentario"));
            request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
            acceso=editarc;
        }
        

    }else if(action.equalsIgnoreCase("guardarcambios")){
        String comentarioAntiguo = request.getParameter("comentarioAntiguo");
        String comentarioNuevo = request.getParameter("comentarioNuevo");
        String nombreUsuario = (String) request.getSession().getAttribute("nombre_usuario");
        String nombre_usuario=request.getParameter("nombre_usuario");
        Pattern patternComentario=Pattern.compile("^[^\\n]*$");
        Matcher matcherComentario=patternComentario.matcher(comentarioNuevo);
        if (comentarioAntiguo.isEmpty()){
                if (nombreUsuario != null && nombreUsuario.equals(nombre_usuario)) {
                    String mensaje_error="El comentario no puede quedarse en blanco";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso =verca;
                } else {
                    String mensaje_error="El comentario no puede quedarse en blanco";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso = verc;
                }
            }        
            
        else if(comentarioNuevo.isEmpty()){
            if (nombreUsuario != null && nombreUsuario.equals(nombre_usuario)) {
                    String mensaje_error="El comentario no puede quedarse en blanco";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso =verca;
                } else {
                    String mensaje_error="El comentario no puede quedarse en blanco";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso = verc;
                }
        }
        else if(!matcherComentario.matches()){
            if (nombreUsuario != null && nombreUsuario.equals(nombre_usuario)) {
                    String mensaje_error="El comentario no puede llevar saltos de linea";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso =verca;
                } else {
                    String mensaje_error="El comentario no puede llevar saltos de linea";
                    request.setAttribute("mensaje_error",mensaje_error);
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso = verc;
                }
        }
        else if(nombre_usuario.isEmpty()){
            String mensaje_error="Ocurrio un error :(";
            request.setAttribute("mensaje_error",mensaje_error);
            acceso=principal;
        }
        else{
            ComentarioDAO dao = new ComentarioDAO();
            dao.actualizar(comentarioAntiguo, comentarioNuevo);
            if (nombreUsuario != null && nombreUsuario.equals(nombre_usuario)) {
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso =verca;
                } else {
                    request.setAttribute("nombre_usuario",request.getParameter("nombre_usuario"));
                    acceso = verc;
                }
        }
        
        
        
        
    }
    else{
        acceso=principal;
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
