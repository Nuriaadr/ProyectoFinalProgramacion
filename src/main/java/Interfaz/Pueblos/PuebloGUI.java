/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz.Pueblos;

import DAO.PuebloDAO;
import Entitys.Pueblo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
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
public class PuebloGUI extends JFrame {

    private final PuebloDAO puebloDAO = new PuebloDAO();

    public PuebloGUI() {
        setTitle("Gestión de Pueblos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel Crear
        JPanel crearPanel = new JPanel(new GridLayout(4, 2));
        JTextField nombreCrear = new JTextField();
        JTextField localizacionCrear = new JTextField();
        JButton crearBtn = new JButton("Crear");

        crearPanel.add(new JLabel("Nombre:"));
        crearPanel.add(nombreCrear);
        crearPanel.add(new JLabel("Localización:"));
        crearPanel.add(localizacionCrear);
        crearPanel.add(new JLabel());
        crearPanel.add(crearBtn);

        crearBtn.addActionListener(e -> {
            String nombre = nombreCrear.getText();
            String localizacion = localizacionCrear.getText();

            Pueblo pueblo = new Pueblo();
            pueblo.setNombre(nombre);
            pueblo.setLocalizacion(localizacion);
            puebloDAO.crear(pueblo);

            JOptionPane.showMessageDialog(this, "Pueblo creado");
            nombreCrear.setText("");
            localizacionCrear.setText("");
        });

        // Panel Leer
        JPanel leerPanel = new JPanel(new BorderLayout());
        JPanel leerInputPanel = new JPanel(new FlowLayout());
        JTextField idLeer = new JTextField(10);
        JButton leerBtn = new JButton("Leer");
        JTextArea leerResultado = new JTextArea(5, 40);
        leerResultado.setEditable(false);

        leerInputPanel.add(new JLabel("ID:"));
        leerInputPanel.add(idLeer);
        leerInputPanel.add(leerBtn);
        leerPanel.add(leerInputPanel, BorderLayout.NORTH);
        leerPanel.add(new JScrollPane(leerResultado), BorderLayout.CENTER);

        leerBtn.addActionListener(e -> {
            int id = Integer.parseInt(idLeer.getText());
            Pueblo p = puebloDAO.leer(id);
            if (p != null) {
                leerResultado.setText("ID: " + p.getId() + "\nNombre: " + p.getNombre() + "\nLocalización: " + p.getLocalizacion());
            } else {
                leerResultado.setText("Pueblo no encontrado");
            }
        });

        // Panel Actualizar
        JPanel actualizarPanel = new JPanel(new GridLayout(4, 2));
        JTextField idActualizar = new JTextField();
        JTextField nombreActualizar = new JTextField();
        JTextField localizacionActualizar = new JTextField();
        JButton actualizarBtn = new JButton("Actualizar");

        actualizarPanel.add(new JLabel("ID:"));
        actualizarPanel.add(idActualizar);
        actualizarPanel.add(new JLabel("Nuevo nombre:"));
        actualizarPanel.add(nombreActualizar);
        actualizarPanel.add(new JLabel("Nueva localización:"));
        actualizarPanel.add(localizacionActualizar);
        actualizarPanel.add(new JLabel());
        actualizarPanel.add(actualizarBtn);

        actualizarBtn.addActionListener(e -> {
            int id = Integer.parseInt(idActualizar.getText());
            Pueblo p = puebloDAO.leer(id);
            if (p != null) {
                p.setNombre(nombreActualizar.getText());
                p.setLocalizacion(localizacionActualizar.getText());
                puebloDAO.actualizar(p);
                JOptionPane.showMessageDialog(this, "Pueblo actualizado");
            } else {
                JOptionPane.showMessageDialog(this, "Pueblo no encontrado");
            }
        });

        // Panel Eliminar
        JPanel eliminarPanel = new JPanel(new FlowLayout());
        JTextField idEliminar = new JTextField(10);
        JButton eliminarBtn = new JButton("Eliminar");

        eliminarPanel.add(new JLabel("ID:"));
        eliminarPanel.add(idEliminar);
        eliminarPanel.add(eliminarBtn);

        eliminarBtn.addActionListener(e -> {
            int id = Integer.parseInt(idEliminar.getText());
            puebloDAO.eliminar(id);
            JOptionPane.showMessageDialog(this, "Pueblo eliminado (si existía)");
        });

        // Panel Listar Todos
        JPanel listarPanel = new JPanel(new BorderLayout());
        JTextArea areaLista = new JTextArea();
        areaLista.setEditable(false);
        JButton listarBtn = new JButton("Listar todos");

        listarPanel.add(listarBtn, BorderLayout.NORTH);
        listarPanel.add(new JScrollPane(areaLista), BorderLayout.CENTER);

        listarBtn.addActionListener(e -> {
            List<Pueblo> lista = puebloDAO.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (Pueblo p : lista) {
                sb.append("ID: ").append(p.getId())
                        .append(" | Nombre: ").append(p.getNombre())
                        .append(" | Localización: ").append(p.getLocalizacion())
                        .append("\n");
            }
            areaLista.setText(sb.toString());
        });

        // Añadir pestañas
        tabbedPane.addTab("Crear", crearPanel);
        tabbedPane.addTab("Leer", leerPanel);
        tabbedPane.addTab("Actualizar", actualizarPanel);
        tabbedPane.addTab("Eliminar", eliminarPanel);
        tabbedPane.addTab("Listar Todos", listarPanel);

        add(tabbedPane);
    }

}
