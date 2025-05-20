/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz.Jugadores;

import DAO.JugadorDAO;
import DAO.PuebloDAO;
import Entitys.Jugador;
import Entitys.Pueblo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author nuria
 */
public class JugadorGUI extends JFrame {

    private JugadorDAO jugadorDAO = new JugadorDAO();

    public JugadorGUI() {
        setTitle("Gestión de Jugadores");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Crear Jugador
        JPanel crearPanel = new JPanel(new GridLayout(5, 2));
        JTextField nombreCrear = new JTextField();
        JTextField edadCrear = new JTextField();
        JButton crearBtn = new JButton("Crear Jugador");
        crearPanel.add(new JLabel("Nombre:"));
        crearPanel.add(nombreCrear);
        crearPanel.add(new JLabel("Edad:"));
        crearPanel.add(edadCrear);

        PuebloDAO puebloDAO = new PuebloDAO();
        List<Pueblo> pueblos = puebloDAO.listarTodos();
        JComboBox<Pueblo> comboPueblosCrear = new JComboBox<>(pueblos.toArray(new Pueblo[0]));
        crearPanel.add(new JLabel("Pueblo:"));
        crearPanel.add(comboPueblosCrear);
        
        crearPanel.add(new JLabel(""));
        crearPanel.add(crearBtn);
        crearBtn.addActionListener(e -> {
            String nombre = nombreCrear.getText();
            int edad = Integer.parseInt(edadCrear.getText());
            Pueblo puebloSeleccionado = (Pueblo) comboPueblosCrear.getSelectedItem();

            Jugador jugador = new Jugador();
            jugador.setNombre(nombre);
            jugador.setEdad(edad);
            jugador.setPueblo(puebloSeleccionado);
            jugadorDAO.crear(jugador);

            JOptionPane.showMessageDialog(this, "Jugador creado");
            nombreCrear.setText("");
            edadCrear.setText("");
        });

        // Leer Jugador
        JPanel leerPanel = new JPanel(new GridLayout(4, 2));
        JTextField idLeer = new JTextField();
        JTextArea infoLeer = new JTextArea(5, 20);
        infoLeer.setEditable(false);
        JButton leerBtn = new JButton("Leer Jugador");
        leerPanel.add(new JLabel("ID del Jugador:"));
        leerPanel.add(idLeer);
        leerPanel.add(new JLabel(""));
        leerPanel.add(leerBtn);
        leerPanel.add(new JLabel("Resultado:"));
        leerPanel.add(new JScrollPane(infoLeer));

        leerBtn.addActionListener(e -> {
            int id = Integer.parseInt(idLeer.getText());
            Jugador j = jugadorDAO.leer(id);
            if (j != null) {
                infoLeer.setText("ID: " + j.getId() + "\nNombre: " + j.getNombre() + "\nEdad: " + j.getEdad());
            } else {
                infoLeer.setText("Jugador no encontrado.");
            }
        });

        // Actualizar Jugador
        JPanel actualizarPanel = new JPanel(new GridLayout(5, 2));
        JTextField idActualizar = new JTextField();
        JTextField nombreActualizar = new JTextField();
        JTextField edadActualizar = new JTextField();
        JButton actualizarBtn = new JButton("Actualizar Jugador");
        actualizarPanel.add(new JLabel("ID del Jugador:"));
        actualizarPanel.add(idActualizar);
        actualizarPanel.add(new JLabel("Nuevo nombre:"));
        actualizarPanel.add(nombreActualizar);
        actualizarPanel.add(new JLabel("Nueva edad:"));
        actualizarPanel.add(edadActualizar);
        JComboBox<Pueblo> comboPueblosActualizar = new JComboBox<>(pueblos.toArray(new Pueblo[0]));
        actualizarPanel.add(new JLabel("Nuevo pueblo:"));
        actualizarPanel.add(comboPueblosActualizar);
        actualizarPanel.add(new JLabel(""));
        actualizarPanel.add(actualizarBtn);
        actualizarBtn.addActionListener(e -> {
            int id = Integer.parseInt(idActualizar.getText());
            Jugador j = jugadorDAO.leer(id);
            if (j != null) {
                j.setNombre(nombreActualizar.getText());
                j.setEdad(Integer.parseInt(edadActualizar.getText()));
                j.setPueblo((Pueblo) comboPueblosActualizar.getSelectedItem());
                jugadorDAO.actualizar(j);
                JOptionPane.showMessageDialog(this, "Jugador actualizado");
            } else {
                JOptionPane.showMessageDialog(this, "Jugador no encontrado");
            }
        });
        // Eliminar Jugador
        JPanel eliminarPanel = new JPanel(new GridLayout(3, 2));
        JTextField idEliminar = new JTextField();
        JButton eliminarBtn = new JButton("Eliminar Jugador");
        eliminarPanel.add(new JLabel("ID del Jugador:"));
        eliminarPanel.add(idEliminar);
        eliminarPanel.add(new JLabel(""));
        eliminarPanel.add(eliminarBtn);

        eliminarBtn.addActionListener(e -> {
            int id = Integer.parseInt(idEliminar.getText());
            jugadorDAO.eliminar(id);
            JOptionPane.showMessageDialog(this, "Jugador eliminado (si existía)");
        });

        // Listar Jugadores
        JPanel listarPanel = new JPanel(new BorderLayout());
        JButton listarBtn = new JButton("Listar Todos los Jugadores");
        JTextArea listarArea = new JTextArea();
        listarArea.setEditable(false);
        listarPanel.add(listarBtn, BorderLayout.NORTH);
        listarPanel.add(new JScrollPane(listarArea), BorderLayout.CENTER);

        listarBtn.addActionListener(e -> {
            List<Jugador> jugadores = jugadorDAO.listarTodos();
            listarArea.setText("");
            for (Jugador j : jugadores) {
                listarArea.append("ID: " + j.getId() + ", Nombre: " + j.getNombre() + ", Edad: " + j.getEdad() + "\n");
            }
        });

        // Añadir pestañas
        tabbedPane.addTab("Crear", crearPanel);
        tabbedPane.addTab("Leer", leerPanel);
        tabbedPane.addTab("Actualizar", actualizarPanel);
        tabbedPane.addTab("Eliminar", eliminarPanel);
        tabbedPane.addTab("Listar", listarPanel);

        // Botón de salir
        JButton salirBtn = new JButton("Salir");
        salirBtn.addActionListener(e -> dispose());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(salirBtn, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

}
