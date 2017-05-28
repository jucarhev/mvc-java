/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.*;
import java.sql.*;
import modelo.*;
import vista.*;

/**
 *
 * @author jk
 */
public class ControladorBotonEjecuta implements ActionListener {
    public ControladorBotonEjecuta(Marco_Aplicacion elmarco){
        this.elmarco = elmarco;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String seleccionSeccion = (String)elmarco.secciones.getSelectedItem();
        String seleccionPais = (String)elmarco.paises.getSelectedItem();
        resultadoConsulta=obj.filtraBBDD(seleccionSeccion, seleccionPais);
        try{
            elmarco.resultado.setText("");
            while(resultadoConsulta.next()){
                elmarco.resultado.append(resultadoConsulta.getString(1));
                elmarco.resultado.append(resultadoConsulta.getString(2));
                elmarco.resultado.append(resultadoConsulta.getString(3));
                elmarco.resultado.append(resultadoConsulta.getString(4));
                elmarco.resultado.append("\n");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    }
    
    
    private Marco_Aplicacion elmarco;
    private ResultSet resultadoConsulta;
    EjecutaConsultas obj = new EjecutaConsultas();
}
