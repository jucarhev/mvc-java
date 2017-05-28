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
public class EjecutaConsultas {
    public EjecutaConsultas(){
        miconexion = new Conexion();
    }
            
    public ResultSet filtraBBDD(String seccion,String pais){
        //pruebas = "";
        Connection conecta =  miconexion.dameConexion();
        rs = null;
        try{
            if(!seccion.equals("Todos") && pais.equals("Todos")){
                //pruebas = "Seccion";
                enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
                enviaConsultaSeccion.setString(1, seccion);
                rs = enviaConsultaSeccion.executeQuery();
                
            }else if(seccion.equals("Todos") && !pais.equals("Todos")){
                //pruebas = "pais";
                enviaConsultaPais = conecta.prepareStatement(consultaPais);
                enviaConsultaPais.setString(1, pais);
                rs = enviaConsultaPais.executeQuery();
            }else{
                //pruebas = "ambos";
                enviaConsulta = conecta.prepareStatement(consulta);
                enviaConsulta.setString(1, seccion);
                enviaConsulta.setString(2, pais);
                rs = enviaConsulta.executeQuery();
            }
        }catch(Exception e){}
        //return pruebas;
        return rs;
    }
    private String pruebas;
    
    private Conexion miconexion;
    private ResultSet rs;
    private PreparedStatement enviaConsultaSeccion,enviaConsultaPais,enviaConsulta;
    private final String consultaSeccion = "SELECT seccion,nombre,precio,pais FROM productos WHERE seccion=?";
    private final String consultaPais = "SELECT seccion,nombre,precio,fecha,importado,pais FROM productos WHERE pais=?";
    private final String consulta = "SELECT seccion,nombre,precio,fecha,importado,pais FROM productos WHERE seccion=? and pais=?";
}