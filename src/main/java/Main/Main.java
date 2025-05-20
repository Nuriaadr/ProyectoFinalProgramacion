/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Main;

import Entitys.Jugador;
import Interfaz.Panel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.swing.JFrame;

/**
 *
 * @author nuria
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mi aplicación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);  
        frame.setLocationRelativeTo(null); 

        Panel panel = new Panel(); 
        frame.add(panel);          

        frame.setVisible(true);    
    }

}
