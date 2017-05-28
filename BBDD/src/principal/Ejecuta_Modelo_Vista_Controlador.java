/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import javax.swing.*;
import vista.Marco_Aplicacion;

/**
 *
 * @author jk
 */
public class Ejecuta_Modelo_Vista_Controlador {
    public static void main(String[] args) {
        Marco_Aplicacion mimarco = new Marco_Aplicacion();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mimarco.setVisible(true);
    }
}
