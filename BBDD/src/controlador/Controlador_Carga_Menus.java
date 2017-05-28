/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.*;

import modelo.*;
import vista.*;
/**
 *
 * @author jk
 */
public class Controlador_Carga_Menus extends WindowAdapter{
    public Controlador_Carga_Menus(Marco_Aplicacion elmarco){
        this.elmarco = elmarco;
    }
    public void windowOpened(WindowEvent e){
        obj.ejecutaConsultas();
        try{
            while(obj.rs.next()){
                elmarco.secciones.addItem(obj.rs.getString(1));
            }
            while(obj.rs2.next()){
                elmarco.paises.addItem(obj.rs2.getString(1));
            }
        }catch(Exception e2){}
    }
    CargaMenus obj = new CargaMenus();
    private Marco_Aplicacion elmarco;
    
}
