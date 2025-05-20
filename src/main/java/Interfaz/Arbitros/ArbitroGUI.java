/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz.Arbitros;

import DAO.ArbitroDAO;
import Entitys.Arbitro;
import java.awt.BorderLayout;
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
public class ArbitroGUI extends JFrame {

    private ArbitroDAO dao = new ArbitroDAO();

    public ArbitroGUI() {
        setTitle("Gestión de Árbitros");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Crear
        JPanel panelCrear = new JPanel();
        panelCrear.setLayout(new GridLayout(3, 2));
        JTextField nombreCrear = new JTextField();
        JButton btnCrear = new JButton("Crear Árbitro");
        panelCrear.add(new JLabel("Nombre:"));
        panelCrear.add(nombreCrear);
        panelCrear.add(new JLabel(""));
        panelCrear.add(btnCrear);
        btnCrear.addActionListener(e -> {
            String nombre = nombreCrear.getText();
            Arbitro a = new Arbitro();
            a.setNombre(nombre);
            dao.crear(a);
            JOptionPane.showMessageDialog(this, "Árbitro creado.");
            nombreCrear.setText("");
        });

        // Leer (por ID)
        JPanel panelLeer = new JPanel();
        panelLeer.setLayout(new GridLayout(3, 2));
        JTextField idLeer = new JTextField();
        JTextArea resultadoLeer = new JTextArea();
        resultadoLeer.setEditable(false);
        JButton btnLeer = new JButton("Buscar Árbitro");
        panelLeer.add(new JLabel("ID:"));
        panelLeer.add(idLeer);
        panelLeer.add(btnLeer);
        panelLeer.add(new JScrollPane(resultadoLeer));
        btnLeer.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idLeer.getText());
                Arbitro a = dao.leer(id);
                if (a != null) {
                    resultadoLeer.setText("ID: " + a.getId() + "\nNombre: " + a.getNombre());
                } else {
                    resultadoLeer.setText("No encontrado.");
                }
            } catch (NumberFormatException ex) {
                resultadoLeer.setText("ID inválido.");
            }
        });

        // Actualizar
        JPanel panelActualizar = new JPanel();
        panelActualizar.setLayout(new GridLayout(4, 2));
        JTextField idActualizar = new JTextField();
        JTextField nombreActualizar = new JTextField();
        JButton btnActualizar = new JButton("Actualizar Árbitro");
        panelActualizar.add(new JLabel("ID:"));
        panelActualizar.add(idActualizar);
        panelActualizar.add(new JLabel("Nuevo nombre:"));
        panelActualizar.add(nombreActualizar);
        panelActualizar.add(new JLabel(""));
        panelActualizar.add(btnActualizar);
        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idActualizar.getText());
                Arbitro a = dao.leer(id);
                if (a != null) {
                    a.setNombre(nombreActualizar.getText());
                    dao.actualizar(a);
                    JOptionPane.showMessageDialog(this, "Árbitro actualizado.");
                } else {
                    JOptionPane.showMessageDialog(this, "No encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        // Eliminar
        JPanel panelEliminar = new JPanel();
        panelEliminar.setLayout(new GridLayout(2, 2));
        JTextField idEliminar = new JTextField();
        JButton btnEliminar = new JButton("Eliminar Árbitro");
        panelEliminar.add(new JLabel("ID:"));
        panelEliminar.add(idEliminar);
        panelEliminar.add(new JLabel(""));
        panelEliminar.add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idEliminar.getText());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(this, "Árbitro eliminado.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        // Listar todos
        JPanel panelListar = new JPanel();
        panelListar.setLayout(new BorderLayout());
        JTextArea areaListar = new JTextArea();
        areaListar.setEditable(false);
        JButton btnListar = new JButton("Listar Árbitros");
        panelListar.add(btnListar, BorderLayout.NORTH);
        panelListar.add(new JScrollPane(areaListar), BorderLayout.CENTER);
        btnListar.addActionListener(e -> {
            List<Arbitro> lista = dao.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (Arbitro a : lista) {
                sb.append("ID: ").append(a.getId())
                        .append(" | Nombre: ").append(a.getNombre())
                        .append("\n");
            }
            areaListar.setText(sb.toString());
        });

        // Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));

        // Añadir pestañas
        tabs.addTab("Crear", panelCrear);
        tabs.addTab("Leer", panelLeer);
        tabs.addTab("Actualizar", panelActualizar);
        tabs.addTab("Eliminar", panelEliminar);
        tabs.addTab("Listar", panelListar);

        add(tabs, BorderLayout.CENTER);
        add(btnSalir, BorderLayout.SOUTH);

        setVisible(true);
    }

}
