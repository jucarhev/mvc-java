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
public class Conexion {
    public Conexion(){}
    
    public Connection dameConexion(){
        try{
            miconexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root","lenov35");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return miconexion;
    }
    private Connection miconexion;
}
