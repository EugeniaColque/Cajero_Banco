/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ventanas;

import cajero.Cuenta;
import cajero.Evento;
import cajero.GestorCuenta;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Daniel
 */
public class PanelOpciones extends javax.swing.JPanel {

    /**
     * Creates new form PanelOpciones
     */
    public PanelOpciones(GestorCuenta gestionador, JFrame panelPrincipal) {
        initComponents();
        panelPrincipal.getContentPane().removeAll();
        panelPrincipal.getContentPane().add(this, BorderLayout.CENTER);
        panelPrincipal.getContentPane().revalidate();
        panelPrincipal.getContentPane().repaint();
        panelPrincipal.pack();
        
        
        DefaultTableModel modeloTabla = (DefaultTableModel) jtRegistro.getModel();
        TableColumnModel modeloColumna = jtRegistro.getColumnModel();
        
        jcbSeleccionarCuenta.setModel(new DefaultComboBoxModel(gestionador.getCuentas()));
        jcbSeleccionarCuenta.setSelectedIndex(EstadoComboBox.getIndiceSeleccionado());
        Cuenta cuenta = (Cuenta) jcbSeleccionarCuenta.getSelectedItem();
        lblNombreTitular.setText(gestionador.titularCuenta());
        gestionador.establecerCuenta(cuenta.getNroCuenta());
        List<Evento> eventos = gestionador.getEventos();
        modeloColumna.getColumn(2).setHeaderValue("Monto"+ "("+gestionador.getDivisa()+")");
        modeloColumna.getColumn(3).setHeaderValue("Saldo"+ "("+gestionador.getDivisa()+")");
        actualizarTabla(eventos, modeloTabla);
        
        jcbSeleccionarCuenta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    modeloTabla.setRowCount(0);
                    Cuenta cuenta = (Cuenta) jcbSeleccionarCuenta.getSelectedItem();
                    gestionador.establecerCuenta(cuenta.getNroCuenta());
                    List<Evento> eventos = gestionador.getEventos(); 
                    modeloColumna.getColumn(2).setHeaderValue("Monto"+ "("+gestionador.getDivisa()+")");
                    modeloColumna.getColumn(3).setHeaderValue("Saldo"+ "("+gestionador.getDivisa()+")");
                    actualizarTabla(eventos,  modeloTabla);
                    
                }
            }

        });
       

        btnConsultarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelConsultarSaldo panelConsultarSaldo = new PanelConsultarSaldo(gestionador, PanelOpciones.this, modeloTabla, panelPrincipal);
            }

        });

        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelDivisaRetirar panelDepositarDivisa = new PanelDivisaRetirar(gestionador, PanelOpciones.this, modeloTabla, panelPrincipal);
            }

        });

        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelDivisaDepositar panelDepositarDivisa = new PanelDivisaDepositar(gestionador, PanelOpciones.this, modeloTabla, panelPrincipal);

            }

        });

        btnTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelDivisaTransferir panelDepositarDivisa = new PanelDivisaTransferir(gestionador, PanelOpciones.this, modeloTabla, panelPrincipal);
            }

        });

        btnCambiarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelCambioContrasena panelCambiarContrasena = new PanelCambioContrasena(gestionador, PanelOpciones.this, modeloTabla, panelPrincipal);
            }
        });

        btnCrearCuentaAdicional.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelCrearCuentaAdicional panelCrearCuentaAdicional = new PanelCrearCuentaAdicional(gestionador, PanelOpciones.this, modeloTabla, panelPrincipal);

            }

        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelSesionInicio principal = new PanelSesionInicio(gestionador, panelPrincipal);
                panelPrincipal.add(principal, BorderLayout.PAGE_START);
            }
        });

    }
    
    public void actualizarTabla(List<Evento> eventos, DefaultTableModel modeloTabla){
        
        for (int i = 0; i < eventos.size(); i++) {
                        Evento evento = eventos.get(i);
                        String[] filaEvento = new String[4];
                        filaEvento[0] = evento.getFecha();
                        filaEvento[1] = evento.getDescripcion();
                        filaEvento[2] = String.format("%.2f",Double.parseDouble(evento.getMonto()));
                        filaEvento[3] = String.format("%.2f",Double.parseDouble(evento.getSaldo()));
                        modeloTabla.addRow(filaEvento);
                    }
                    EstadoComboBox.setIndiceSeleccionado(jcbSeleccionarCuenta.getSelectedIndex());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnConsultarSaldo = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        btnDepositar = new javax.swing.JButton();
        btnTransferir = new javax.swing.JButton();
        btnCambiarContrasena = new javax.swing.JButton();
        btnCrearCuentaAdicional = new javax.swing.JButton();
        jcbSeleccionarCuenta = new javax.swing.JComboBox<>();
        lblSeleccionarCuenta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNombreTitular = new javax.swing.JLabel();
        lblTitular = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtRegistro = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(134, 190, 225));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png"))); // NOI18N

        btnConsultarSaldo.setBackground(new java.awt.Color(0, 174, 237));
        btnConsultarSaldo.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnConsultarSaldo.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultarSaldo.setText("ConsultarSaldo");

        btnRetirar.setBackground(new java.awt.Color(0, 174, 237));
        btnRetirar.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnRetirar.setForeground(new java.awt.Color(255, 255, 255));
        btnRetirar.setText("Retirar");

        btnDepositar.setBackground(new java.awt.Color(0, 174, 237));
        btnDepositar.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnDepositar.setForeground(new java.awt.Color(255, 255, 255));
        btnDepositar.setText("Depositar");

        btnTransferir.setBackground(new java.awt.Color(0, 174, 237));
        btnTransferir.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnTransferir.setForeground(new java.awt.Color(255, 255, 255));
        btnTransferir.setText("Transferir");

        btnCambiarContrasena.setBackground(new java.awt.Color(0, 174, 237));
        btnCambiarContrasena.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnCambiarContrasena.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarContrasena.setText("Cambiar Contrasena");

        btnCrearCuentaAdicional.setBackground(new java.awt.Color(0, 174, 237));
        btnCrearCuentaAdicional.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnCrearCuentaAdicional.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuentaAdicional.setText("Abrir Cuenta Adicional");

        jcbSeleccionarCuenta.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N

        lblSeleccionarCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSeleccionarCuenta.setForeground(new java.awt.Color(255, 255, 255));
        lblSeleccionarCuenta.setText("Selecciona una Cuenta");

        jPanel3.setBackground(new java.awt.Color(4, 86, 160));

        jLabel1.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Panel de Opciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(165, 165, 165))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lblNombreTitular.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        lblNombreTitular.setForeground(new java.awt.Color(255, 255, 255));

        lblTitular.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        lblTitular.setForeground(new java.awt.Color(51, 51, 51));
        lblTitular.setText("Titular");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSeleccionarCuenta, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbSeleccionarCuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRetirar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTransferir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCambiarContrasena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrearCuentaAdicional, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblTitular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(lblNombreTitular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(lblSeleccionarCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSeleccionarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTransferir)
                    .addComponent(btnConsultarSaldo))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRetirar)
                    .addComponent(btnCrearCuentaAdicional))
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDepositar)
                    .addComponent(btnCambiarContrasena))
                .addGap(36, 36, 36))
        );

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(134, 190, 225));

        jtRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Descripcion", "Monto", "Saldo"
            }
        ));
        jScrollPane2.setViewportView(jtRegistro);
        if (jtRegistro.getColumnModel().getColumnCount() > 0) {
            jtRegistro.getColumnModel().getColumn(1).setPreferredWidth(250);
        }

        jLabel2.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Historial de la Cuenta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarContrasena;
    private javax.swing.JButton btnConsultarSaldo;
    private javax.swing.JButton btnCrearCuentaAdicional;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTransferir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbSeleccionarCuenta;
    private javax.swing.JTable jtRegistro;
    private javax.swing.JLabel lblNombreTitular;
    private javax.swing.JLabel lblSeleccionarCuenta;
    private javax.swing.JLabel lblTitular;
    // End of variables declaration//GEN-END:variables
}
