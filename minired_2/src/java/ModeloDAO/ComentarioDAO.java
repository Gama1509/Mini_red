/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Modelo.Comentario;
import Modelo.Usuario;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author gamae
 */
public class ComentarioDAO {
    Comentario cm=new Comentario();
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    Usuario us=new Usuario();
    Conexion cn= new Conexion();
    
    public List<Comentario> consultar(String nombreUsuario) {
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        String sql = "SELECT destinatario, comentario, fecha FROM comentario WHERE autor = '"+nombreUsuario+"';";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Comentario comentario= new Comentario();
                comentario.setDestinatario(rs.getString("destinatario"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setFecha(rs.getString("fecha"));
                listaComentarios.add(comentario);
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return listaComentarios;
    }
    
    public List<Comentario> filtrarPorFecha(String nombreUsuario, String fecha) {
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        String sql;
        if (fecha == null || fecha.isEmpty()) {
            sql = "SELECT destinatario, comentario, fecha FROM comentario WHERE autor = '" + nombreUsuario + "';";
        } else {
            sql = "SELECT destinatario, comentario, fecha FROM comentario WHERE autor = '" + nombreUsuario + "' AND fecha = '" + fecha + "';";
        }
        try {
            con = cn.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setDestinatario(rs.getString("destinatario"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setFecha(rs.getString("fecha"));
                listaComentarios.add(comentario);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return listaComentarios;
    }
    public void eliminar(String comentario) {
    String sql = "DELETE FROM comentario WHERE comentario ='" + comentario + "';";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.executeUpdate();
    } catch (Exception e) {
        System.err.println("Error: " + e);
    }
    }
    public void actualizar(String comentarioAntiguo, String comentarioNuevo) 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "UPDATE comentario SET comentario='" +comentarioNuevo+ "' WHERE comentario='" + comentarioAntiguo + "'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }


    
    public boolean registrar(Comentario cm) {
        String sql="insert into comentario(autor,destinatario,comentario,fecha)values('"+cm.getAutor()+"','"+cm.getDestinatario()+"','"+cm.getComentario()+"','"+cm.getFecha()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    
    }
    
}
