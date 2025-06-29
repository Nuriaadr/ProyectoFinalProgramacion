/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Views.Arbitros;

import Controladores.ArbitroDAO;
import Entitys.Arbitro;
import javax.swing.JOptionPane;

/**
 *
 * @author nuria
 */
public class CrearArbitro extends javax.swing.JDialog {

    /**
     * Creates new form CrearArbitro
     */
    public CrearArbitro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombreCrear = new javax.swing.JTextField();
        CrearArbitro = new javax.swing.JButton();
        Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre del arbitro a crear: ");

        nombreCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreCrearActionPerformed(evt);
            }
        });

        CrearArbitro.setText("Crear");
        CrearArbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearArbitroActionPerformed(evt);
            }
        });

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombreCrear))
                        .addGap(0, 170, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CrearArbitro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Salir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrearArbitro)
                    .addComponent(Salir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreCrearActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_nombreCrearActionPerformed

    private void CrearArbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearArbitroActionPerformed
        // TODO add your handling code here:
        String nombre = nombreCrear.getText().trim();
        if (!nombre.isEmpty()) {
            ArbitroDAO arbitroDAO = new ArbitroDAO();
            Arbitro nuevoArbitro = new Arbitro();
            nuevoArbitro.setNombre(nombre);

            arbitroDAO.crear(nuevoArbitro);
            JOptionPane.showMessageDialog(null, "Árbitro creado correctamente.");
            dispose(); //Cierra la ventana
        } else {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
        }
    }//GEN-LAST:event_CrearArbitroActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_SalirActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrearArbitro;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nombreCrear;
    // End of variables declaration//GEN-END:variables
}
