/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaPanificadora;

import SistemaPanificadora.pantallaInicio;
import static SistemaPanificadora.pantallaInicio.jLabel10;
import static SistemaPanificadora.pantallaInicio.jMenu6;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JFrame {
void pantallaInicio(){
     pantallaInicio inicio = new pantallaInicio();
        inicio.cargarproducto();
     inicio.setLocationRelativeTo(null);
     
   
}
void InicioSesionbtn(){
    
        String U = txtUsuario.getText();
       String P = Password.getText();
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion(); 
        if (U.equals("") || P.equals("")) {
            JOptionPane.showMessageDialog(this, "Uno o mas campos estan vacios. Favor de llenarlos.");
        } else {   
            c.getConexion();
            try {
 c.ps = c.con.prepareStatement("select nombre_usuario, password from usuarios where nombre_usuario='" + U
                        + "' and password ='" + P + "'");
   c.rs = c.ps.executeQuery();
                if (c.rs.next()) {
                    pantallaInicio();
                   pantallaInicio home = new pantallaInicio();
                    home.setVisible(true);
                    home.setLocationRelativeTo(null);
                    this.setVisible(false);
                    seteoUsuarioActivo();
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Vuelve a intentar de nuevo.");
                }
            } catch (SQLException e) {
                System.err.print(e.toString());
                JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\n por Favor comunicarse con el administrador.");
            }
        }
}
    public InicioSesion() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(608, 531);
        this.setResizable(false);
        this.setTitle("Inicio Sesión");
        
    }
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SistemaPanificadora.pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaPanificadora.pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaPanificadora.pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaPanificadora.pantallaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SistemaPanificadora.InicioSesion().setVisible(true);

            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        inicioSesion = new rsbuttom.RSButtonMetro();
        inicioSesion1 = new rsbuttom.RSButtonMetro();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 243, 239));
        setMaximizedBounds(new java.awt.Rectangle(610, 570, 570, 570));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(244, 243, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Roboto Thin", 1, 24)); // NOI18N
        jLabel3.setText("Iniciar sesión");

        jLabel4.setFont(new java.awt.Font("Roboto Thin", 0, 18)); // NOI18N
        jLabel4.setText("Usuario:");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Thin", 0, 18)); // NOI18N
        jLabel5.setText("Contraseña:");

        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PasswordKeyTyped(evt);
            }
        });

        inicioSesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/Entrar.png"))); // NOI18N
        inicioSesion.setText("Ingresar");
        inicioSesion.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion.setDefaultCapable(false);
        inicioSesion.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion.setIconTextGap(10);
        inicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesionActionPerformed(evt);
            }
        });

        inicioSesion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion1.setText("Salir");
        inicioSesion1.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion1.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion1.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion1.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion1.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion1.setDefaultCapable(false);
        inicioSesion1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion1.setIconTextGap(10);
        inicioSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion1ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/round-account-button-with-user-inside_icon-icons.com_72596.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(txtUsuario)
                                .addComponent(Password, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 31, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inicioSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inicioSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 0, 310, 530));

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(244, 243, 239));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/Polo2.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesionActionPerformed

InicioSesionbtn();
    }//GEN-LAST:event_inicioSesionActionPerformed

        public void seteoUsuarioActivo() {
        if (jLabel10.getText().equals("admin")) {
            jMenu6.setEnabled(true);
        }
        else{
        jMenu6.setEnabled(false);
        }

    }

      public String seteoSesionNombre(){
      String U = txtUsuario.getText();
        return U;
      }
  
    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed

    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void PasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordKeyTyped

    }//GEN-LAST:event_PasswordKeyTyped

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed

    }//GEN-LAST:event_PasswordActionPerformed

    private void inicioSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion1ActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_inicioSesion1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private rsbuttom.RSButtonMetro inicioSesion;
    private rsbuttom.RSButtonMetro inicioSesion1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
