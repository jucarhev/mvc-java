/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion1;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author jk
 */
public class Aplicacion_Consulta {
    public static void main(String[] args) {
        JFrame mimarco = new MarcoAplicacion();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mimarco.setVisible(true);
    }
}

class MarcoAplicacion extends JFrame{
    public MarcoAplicacion(){
        setTitle("Consulta BBDD");
        setBounds(500,300,400,400);
        setLayout(new BorderLayout());
        JPanel menus = new JPanel();
        menus.setLayout(new FlowLayout());
        secciones = new JComboBox();
        secciones.setEditable(false);
        secciones.addItem("Todos");
        paises = new JComboBox();
        paises.setEditable(false);
        paises.addItem("Todos");
        resultado = new JTextArea(4,50);
        resultado.setEditable(false);
        add(resultado);
        menus.add(secciones);
        menus.add(paises);
        add(menus,BorderLayout.NORTH);
        add(resultado,BorderLayout.CENTER);
        JButton botonConsulta = new JButton("Consulta");
        botonConsulta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutaConsulta();
            }
        });
        add(botonConsulta,BorderLayout.SOUTH);
        
        
        try{
            miconexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root","lenov35");
            Statement sentencia = miconexion.createStatement();
            
            String consulta = "select distinct(status) from test";
            ResultSet rs = sentencia.executeQuery(consulta);
            while(rs.next()){
                secciones.addItem(rs.getString(1));
            }
            rs.close();
            
            consulta = "select distinct(pais) from test";
            rs = sentencia.executeQuery(consulta);
            while(rs.next()){
                paises.addItem(rs.getString(1));
            }
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void ejecutaConsulta(){
        ResultSet rs = null;
        resultado.setText("");
        try{
            String seccion = (String)secciones.getSelectedItem();
            String pais = (String)paises.getSelectedItem();
            if(!seccion.equals("Todos") && pais.equals("Todos")){
                enviaConsultaSeccion = miconexion.prepareStatement(consultaSeccion);
                enviaConsultaSeccion.setString(1, seccion);
                rs = enviaConsultaSeccion.executeQuery();
            }else if(seccion.equals("Todos") && !pais.equals("Todos")){
                enviaConsultaPais = miconexion.prepareStatement(consultaPais);
                enviaConsultaPais.setString(1, pais);
                rs = enviaConsultaPais.executeQuery();
            }else if(!seccion.equals("Todos") && !pais.equals("Todos")){
                enviaConsultaTodos = miconexion.prepareStatement(consultaTodos);
                enviaConsultaTodos.setString(1, seccion);
                enviaConsultaTodos.setString(2, pais);
                rs = enviaConsultaTodos.executeQuery();
            }
            
            while(rs.next()){
                resultado.append(rs.getString(1));
                resultado.append("  ");
                resultado.append(rs.getString(2));
                resultado.append("  ");
                resultado.append(rs.getString(3));
                resultado.append("  ");
                resultado.append(rs.getString(4));
                resultado.append("\n");
            }
            
        }catch(Exception e){}
    }
    
    private JComboBox secciones, paises;
    private JTextArea resultado;
    private PreparedStatement enviaConsultaSeccion,enviaConsultaPais,enviaConsultaTodos;
    private final String consultaSeccion ="select name,lastname,pais,status from test where status=?";
    private final String consultaPais ="select name,lastname,pais,status from test where pais=?";
    private final String consultaTodos ="select name,lastname,pais,status from test where status=? and pais=?";
    private Connection miconexion;
}