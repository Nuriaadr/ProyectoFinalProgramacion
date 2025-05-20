/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz.Participaciones;

import DAO.JugadorDAO;
import DAO.ParticipacionDAO;
import DAO.PartidoDAO;
import Entitys.Jugador;
import Entitys.Participacion;
import Entitys.Partido;
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
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParticipacionGUI extends JFrame {

    private ParticipacionDAO participacionDAO = new ParticipacionDAO();
    private JugadorDAO jugadorDAO = new JugadorDAO();
    private PartidoDAO partidoDAO = new PartidoDAO();

    public ParticipacionGUI() {
        setTitle("Gestión de Participaciones");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Crear panel
        JPanel crearPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JComboBox<Jugador> comboJugadores = new JComboBox<>();
        JComboBox<Partido> comboPartidos = new JComboBox<>();
        JTextField golesField = new JTextField();
        JButton crearBtn = new JButton("Crear");

        // Cargar combos
        for (Jugador j : jugadorDAO.listarTodos()) {
            comboJugadores.addItem(j);
        }
        for (Partido p : partidoDAO.listarTodos()) {
            comboPartidos.addItem(p);
        }

        crearPanel.add(new JLabel("Jugador:"));
        crearPanel.add(comboJugadores);
        crearPanel.add(new JLabel("Partido:"));
        crearPanel.add(comboPartidos);
        crearPanel.add(new JLabel("Goles:"));
        crearPanel.add(golesField);
        crearPanel.add(new JLabel());
        crearPanel.add(crearBtn);

        crearBtn.addActionListener(e -> {
            try {
                Jugador jugador = (Jugador) comboJugadores.getSelectedItem();
                Partido partido = (Partido) comboPartidos.getSelectedItem();
                if (jugador == null || partido == null) {
                    JOptionPane.showMessageDialog(this, "Selecciona un jugador y un partido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int goles = Integer.parseInt(golesField.getText());
                if (goles < 0) {
                    JOptionPane.showMessageDialog(this, "Los goles no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                participacionDAO.crear(new Participacion(jugador, partido, goles));
                JOptionPane.showMessageDialog(this, "Participación creada con éxito.");
                golesField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un número válido en goles.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(this, "Error al crear participación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Leer panel
        JPanel leerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel leerInputPanel = new JPanel(new BorderLayout(5, 5));
        JTextField leerIdField = new JTextField();
        JButton leerBtn = new JButton("Leer");
        JTextArea leerResultado = new JTextArea();
        leerResultado.setEditable(false);
        leerResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));

        leerInputPanel.add(new JLabel("ID Participación:"), BorderLayout.WEST);
        leerInputPanel.add(leerIdField, BorderLayout.CENTER);
        leerInputPanel.add(leerBtn, BorderLayout.EAST);

        leerPanel.add(leerInputPanel, BorderLayout.NORTH);
        leerPanel.add(new JScrollPane(leerResultado), BorderLayout.CENTER);

        leerBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(leerIdField.getText());
                Participacion p = participacionDAO.leer(id);
                if (p != null) {
                    leerResultado.setText(p.toString());
                } else {
                    leerResultado.setText("Participación no encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al leer participación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listar panel
        JPanel listarPanel = new JPanel(new BorderLayout(10, 10));
        JTextArea listarArea = new JTextArea();
        listarArea.setEditable(false);
        listarArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JButton listarBtn = new JButton("Listar todas");

        listarPanel.add(new JScrollPane(listarArea), BorderLayout.CENTER);
        listarPanel.add(listarBtn, BorderLayout.SOUTH);

        listarBtn.addActionListener(e -> {
            try {
                List<Participacion> lista = participacionDAO.listarTodos();
                listarArea.setText("");
                if (lista.isEmpty()) {
                    listarArea.setText("No hay participaciones.");
                } else {
                    for (Participacion p : lista) {
                        listarArea.append(p.toString() + "\n");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al listar participaciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Eliminar panel
        JPanel eliminarPanel = new JPanel(new BorderLayout(10, 10));
        JTextField eliminarIdField = new JTextField();
        JButton eliminarBtn = new JButton("Eliminar");

        eliminarPanel.add(new JLabel("ID a eliminar:"), BorderLayout.NORTH);
        eliminarPanel.add(eliminarIdField, BorderLayout.CENTER);
        eliminarPanel.add(eliminarBtn, BorderLayout.SOUTH);

        eliminarBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(eliminarIdField.getText());
                Participacion p = participacionDAO.leer(id);
                if (p == null) {
                    JOptionPane.showMessageDialog(this, "No existe participación con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar la participación con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    participacionDAO.eliminar(id);
                    JOptionPane.showMessageDialog(this, "Participación eliminada.");
                    eliminarIdField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar participación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Añadimos las pestañas
        tabs.add("Crear", crearPanel);
        tabs.add("Leer", leerPanel);
        tabs.add("Listar", listarPanel);
        tabs.add("Eliminar", eliminarPanel);

        add(tabs);
        setVisible(true);
    }

}
