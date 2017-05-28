/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author jk
 */
public class BBDD {
    public static void main(String[] args) {
        MarcoBBDD mimarco = new MarcoBBDD();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mimarco.setVisible(true);
    }
}

class MarcoBBDD extends JFrame{
    public MarcoBBDD(){
        setBounds(300,300,700,700);
        LaminaBBDD milamina = new LaminaBBDD();
        add(milamina);
    }
}

class LaminaBBDD extends JPanel{
    public LaminaBBDD(){
        setLayout(new BorderLayout());
        comboTables = new JComboBox();
        conectarBBDD();
        obtenerTablas();
        comboTables.addActionListener(new ActionListener(){   
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTabla = (String)comboTables.getSelectedItem();
                mostrarInfoTabla(nombreTabla);
            }
            
        });
        areaInformacion = new JTextArea();
        add(areaInformacion,BorderLayout.CENTER);
        add(comboTables, BorderLayout.NORTH);
        
        
    }
    public void conectarBBDD(){
        miconexion = null;
        String datos[] = new String[3];
        try{
            entrada = new FileReader("/home/jk/datos_config.txt");
            BufferedReader mibuffer = new BufferedReader(entrada);
            for(int i=0;i<=2;i++){
                datos[i]=mibuffer.readLine();
            }
            miconexion = DriverManager.getConnection(datos[0],datos[1],datos[2]);
            entrada.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "No se encontro el archivo");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void obtenerTablas(){
        ResultSet miResultSet = null;
        try{
            DatabaseMetaData datosBBDD = miconexion.getMetaData();
            miResultSet=datosBBDD.getTables(null,null,null,null);
            while(miResultSet.next()){
                comboTables.addItem(miResultSet.getString("TABLE_NAME"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void mostrarInfoTabla(String tabla){
        ArrayList<String> campos = new  ArrayList<String>();
        String consulta = "SELECT * FROM " + tabla;
        areaInformacion.setText("");
        try{
            Statement miStatement = miconexion.createStatement();
            ResultSet miResultSet = miStatement.executeQuery(consulta);
            ResultSetMetaData rsBBDD = miResultSet.getMetaData();
            for(int i=1;i<rsBBDD.getColumnCount();i++){
                campos.add(rsBBDD.getColumnLabel(i));
                System.out.println(rsBBDD.getColumnLabel(i));
            }
            while(miResultSet.next()){
                for(String nombreCampo:campos){
                    areaInformacion.append(miResultSet.getString(nombreCampo)+" ");
                }
                areaInformacion.append("\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private JComboBox comboTables;
    private JTextArea areaInformacion;
    private Connection miconexion;
    private FileReader entrada;
}