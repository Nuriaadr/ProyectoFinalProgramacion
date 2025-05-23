/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views.Partidos;

import Controladores.PartidoDAO;
import Entitys.Arbitro;
import Entitys.Partido;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author nuria
 */
public class PartidoGUI extends JFrame {

    private PartidoDAO partidoDAO = new PartidoDAO();

    public PartidoGUI() {
        setTitle("Gestión de Partidos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Panel CREAR
        JPanel crearPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField fechaField = new JTextField("dd/MM/yyyy");
        JTextField lugarField = new JTextField();
        JTextField arbitroField = new JTextField(); // Aquí solo un String para simplificar (podrías usar JComboBox con arbitros)
        JButton crearBtn = new JButton("Crear");

        crearPanel.add(new JLabel("Fecha (dd/MM/yyyy):"));
        crearPanel.add(fechaField);
        crearPanel.add(new JLabel("Lugar:"));
        crearPanel.add(lugarField);
        crearPanel.add(new JLabel("Arbitro (ID o nombre):"));
        crearPanel.add(arbitroField);
        crearPanel.add(new JLabel());
        crearPanel.add(crearBtn);

        crearBtn.addActionListener(e -> {
            String fechaStr = fechaField.getText().trim();
            String lugar = lugarField.getText().trim();
            String arbitroStr = arbitroField.getText().trim();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (fechaStr.isEmpty() || lugar.isEmpty() || arbitroStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Rellena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Parsear fecha
                java.util.Date fechaUtil = sdf.parse(fechaStr);
                java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

                // Aquí deberías buscar el arbitro real desde la base de datos, pero simplifico con null
                Arbitro arbitro = null; // O buscar arbitroDAO.leer(id) si lo tienes implementado

                Partido partido = new Partido();
                partido.setFecha(fechaSql);
                partido.setLugar(lugar);
                partido.setArbitro(arbitro);

                partidoDAO.crear(partido);
                JOptionPane.showMessageDialog(this, "Partido creado.");
                fechaField.setText("");
                lugarField.setText("");
                arbitroField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear partido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel LEER
        JPanel leerPanel = new JPanel(new BorderLayout());
        JTextField leerId = new JTextField();
        JTextArea leerResultado = new JTextArea();
        JButton leerBtn = new JButton("Leer");

        JPanel topLeer = new JPanel(new GridLayout(1, 2));
        topLeer.add(new JLabel("ID Partido:"));
        topLeer.add(leerId);
        leerPanel.add(topLeer, BorderLayout.NORTH);
        leerPanel.add(new JScrollPane(leerResultado), BorderLayout.CENTER);
        leerPanel.add(leerBtn, BorderLayout.SOUTH);

        leerBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(leerId.getText().trim());
                Partido p = partidoDAO.leer(id);
                leerResultado.setText(p != null ? p.toString() : "No encontrado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel LISTAR
        JPanel listarPanel = new JPanel(new BorderLayout());
        JTextArea listarArea = new JTextArea();
        JButton listarBtn = new JButton("Listar Partidos");

        listarPanel.add(new JScrollPane(listarArea), BorderLayout.CENTER);
        listarPanel.add(listarBtn, BorderLayout.SOUTH);

        listarBtn.addActionListener(e -> {
            List<Partido> lista = partidoDAO.listarTodos();
            listarArea.setText("");
            for (Partido p : lista) {
                listarArea.append(p + "\n");
            }
        });

        // Panel ELIMINAR
        JPanel eliminarPanel = new JPanel(new BorderLayout());
        JTextField eliminarId = new JTextField();
        JButton eliminarBtn = new JButton("Eliminar");

        eliminarPanel.add(new JLabel("ID a eliminar:"), BorderLayout.NORTH);
        eliminarPanel.add(eliminarId, BorderLayout.CENTER);
        eliminarPanel.add(eliminarBtn, BorderLayout.SOUTH);

        eliminarBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(eliminarId.getText().trim());
                partidoDAO.eliminar(id);
                JOptionPane.showMessageDialog(this, "Partido eliminado.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabs.add("Crear", crearPanel);
        tabs.add("Leer", leerPanel);
        tabs.add("Listar", listarPanel);
        tabs.add("Eliminar", eliminarPanel);

        add(tabs);
        setVisible(true);
    }
}
