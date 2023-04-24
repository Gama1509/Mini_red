/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Interfaces.Crud;
import Modelo.Usuario;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alma_
 */
public class UsuarioDAO implements Crud{
    
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    Usuario us=new Usuario();
    Conexion cn= new Conexion();
    
   
    
    @Override
    public List consultar() {
   
        ArrayList<Usuario> list= new ArrayList<>();
        String sql="SELECT nombre_usuario FROM usuario;";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                Usuario us= new Usuario();
                us.setNombre_usuario(rs.getString("nombre_usuario"));
                us.setContraseña("contraseña");
                us.setNombre("nombre");
                us.setApellidos("apellidos");
                us.setCorreo_electronico("correo_electronico");
                list.add(us);
                System.out.println("nombre_usuario");
            }
            
            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
        
        
        return list;
    }

    @Override
    public Usuario list(String nombre_usuario) {
        
        String sql = "SELECT * FROM usuario WHERE nombre_usuario='" + nombre_usuario + "'";
        try {
            con=cn.getConnection();
            st=con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next()){
                us.setNombre_usuario(rs.getString("nombre_usuario"));
                us.setContraseña(rs.getString("contraseña"));
                us.setNombre(rs.getString("nombre"));
                us.setApellidos(rs.getString("apellidos"));
                us.setCorreo_electronico(rs.getString("correo_electronico"));
            }                       
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
            
        return us;
    }
    

    @Override
    public boolean registrar(Usuario us) {
        String sql="insert into usuario(nombre_usuario,contraseña,nombre,apellidos,correo_electronico)values('"+us.getNombre_usuario()+"','"+us.getContraseña()+"','"+us.getNombre()+"','"+us.getApellidos()+"','"+us.getCorreo_electronico()+"')";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        
        ps.executeUpdate();
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    
    }

    @Override
    public boolean editar(Usuario us) {
        
        String sql="update usuario set nombre_usuario='"+us.getNombre_usuario()+"', contraseña='"+us.getContraseña()+"',correo_electronico='"+us.getCorreo_electronico()+"' where nombre='"+us.getNombre()+"' AND apellidos = '"+us.getApellidos()+"';";
        
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
           
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    }

    @Override
    public boolean eliminar(String nombre_usuario) {
        String sql="delete from usuario where nombre_usuario='"+nombre_usuario+"'";
        
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
           
        }catch(Exception e){
            System.err.println("Error"+e);
        }
        return false;
    }
    @Override
    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
        try {
            con =cn.getConnection();
            String sql = "SELECT * FROM usuario WHERE nombre_usuario='"+nombreUsuario+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                us.setNombre_usuario(rs.getString("nombre_usuario"));
                us.setContraseña(rs.getString("contraseña"));
                us.setNombre(rs.getString("nombre"));
                us.setApellidos(rs.getString("apellidos"));
                us.setCorreo_electronico(rs.getString("correo_electronico"));
            }
        }catch(Exception e){
            System.err.println("Error"+e);
        }

        return us;
        }
    @Override
    public boolean existeNombreUsuario(String nombreUsuario) {
        boolean existe = false;
        try {
        con =cn.getConnection();
        String sql=("SELECT * FROM usuario WHERE nombre_usuario ='"+nombreUsuario+"'");
        ps=con.prepareStatement(sql);
        rs=ps.executeQuery();
        if (rs.next()) {
            existe = true;
        }
        else{
            existe=false;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return existe;
    }
    

}
