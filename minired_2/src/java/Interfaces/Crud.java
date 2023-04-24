/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;
import Modelo.Usuario;
import java.util.List;
/**
 *
 * @author alma_
 */
public interface Crud {
   public List consultar();
   public Usuario list(String nombre_usuario);
   public boolean registrar(Usuario us);
   public boolean editar(Usuario us);
   public boolean eliminar(String nombre_usuario);
   public Usuario obtenerPorNombreUsuario(String nombreUsuario);
   public boolean existeNombreUsuario(String nombreUsuario);

}
