/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
/**
 *
 * @author jk
 */
public class CargaMenus {
    public CargaMenus(){
        miconexion = new Conexion();
        
    }
    public String ejecutaConsultas(){
        Productos miproducto = null;
        Connection accesoBBDD=miconexion.dameConexion();
        try{
            Statement secciones = accesoBBDD.createStatement();
            Statement paises = accesoBBDD.createStatement();
            rs = secciones.executeQuery("SELECT DISTINCTROW seccion from productos");
            rs2 = paises.executeQuery("SELECT DISTINCTROW pais from productos");
            miproducto = new Productos();
            miproducto.setSeccion(rs.getString(1));
            miproducto.setpOrigen(rs2.getString(1));
            rs.close();
            rs2.close();
            accesoBBDD.close();
        }catch(Exception e){
            
        }
        return miproducto.getSeccion();
        
    }
    public ResultSet ejecutaConsultas2(){
        Connection accesoBBDD=miconexion.dameConexion();
        try{
            Statement secciones = accesoBBDD.createStatement();
            return rs = secciones.executeQuery("SELECT DISTINCTROW seccion from productos");
        }catch(Exception e){}
        return null;
    }
    
    Conexion miconexion;
    public ResultSet rs,rs2;
}
