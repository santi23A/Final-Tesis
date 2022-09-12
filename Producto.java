package SistemaPanificadora;
import SistemaPanificadora.pantallaInicio;
import java.awt.Dimension;
import java.awt.Toolkit;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Producto extends javax.swing.JFrame {
    String PR[] = new String[6];
    private int filas;
    public Producto() {
        initComponents();
        bloqueobotonesprod();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        DefaultTableModel model;
        int filas = 0;
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons2/Polo2.png")));
    }
   void bloqueobotonesprod(){
        modificarordpr.setEnabled(false);
        eliminarordpr1.setEnabled(false);
        cantprod.setEnabled(false);
    }
   void activarbotonesprod(){
        modificarordpr.setEnabled(true);
        eliminarordpr1.setEnabled(true);
        cantprod.setEnabled(true);
    }
    void cargar(String Valor) {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Codigo", "Producto", "Stock", "Unidad de medida"};
        c.model = new DefaultTableModel(null, titulos){  
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }};
        String PR[] = new String[4];
        try {
            String SQL = "SELECT `id_producto`,`descripcion`,`stock`,cum.nombre AS unidad_medida,`id_receta`,`statusbajaprod` FROM `producto` AS p JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where statusbajaprod is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                PR[0] = rs.getString("id_producto");
                PR[1] = rs.getString("Descripcion");
                PR[2] = rs.getString("Stock");
                PR[3] = rs.getString("unidad_medida");
                c.model.addRow(PR);
                jTable1.setModel(c.model);
    jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
    jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    void DescontarProd() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Cod producto", "Descripcion", "Stock", "Unidad medida"};
        String PR[] = new String[4];
        try {
            String SQL = "SELECT `id_producto`,`descripcion`,`stock`,cum.id_catalogo_unidad_medida AS unidad_medida,`id_receta`,`statusbajaprod` FROM `producto` AS p JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                PR[0] = c.rs.getString("id_producto");
                PR[1] = c.rs.getString("Descripcion");
                PR[2] = c.rs.getString("Stock");//STOCK DISPONIBLE
                PR[3] = c.rs.getString("unidad_medida");
                c.model.addRow(PR);
                jTable1.setModel(c.model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    void botonModificar(){
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = jTable1.getSelectedRow();
        int check = parseInt(jTable1.getValueAt(fila, 0).toString());
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?", "CONFIRMACION CAMBIOS", JOptionPane.YES_NO_OPTION, HEIGHT);
        if (respuesta == JOptionPane.YES_OPTION) {
            Connection con = null;
            c.getConexion();
            try {
                int contadorpedido = cantprod.getValue();
                c.ps = conn.prepareStatement("UPDATE producto SET Stock=? WHERE id_producto=?");
                c.ps.setInt(1, contadorpedido);
                c.ps.setInt(2, check);
                int res = c.ps.executeUpdate();
                bloqueobotonesprod();
                cargar("");
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "LOS DATOS DEL PRODUCTO SE MODIFICARON CORRECTAMENTE", "PRODUCTO - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO MODIFICAR LOS DATOS DEL PRODUCTO", "PRODUCTO - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            c.closeConexion();
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }
    }
    void botonEliminar(){
         Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);
        int fila = jTable1.getSelectedRow();
        int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);
        if (respuesta == JOptionPane.YES_OPTION) {
            if (fila >= 0) {
                c.getConexion();
                try {
                    c.ps = conn.prepareStatement("UPDATE producto SET statusbajaprod=? WHERE id_producto=" + valor);
                    c.ps.setString(1, fechaactual);
                    int res = c.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "producto dado de baja");
                        cargar("");
                         bloqueobotonesprod();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "El producto no se ha podido dar de baja");
                }
            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }
    }
    void seleccion_en_Tabla(){
        activarbotonesprod();
        int fila = jTable1.getSelectedRow();
        int check = parseInt(jTable1.getValueAt(fila, 0).toString());
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int contadorpedido = cantprod.getValue();
        try {
            c.getConexion();
            c.ps = conn.prepareStatement("SELECT * FROM producto WHERE id_producto=" + check);
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                cantprod.setValue(c.rs.getInt("Stock"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un producto con ese nombre");
            }
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");
        }
        c.closeConexion();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        cantprod = new com.toedter.components.JSpinField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        modificarordpr = new rsbuttom.RSButtonMetro();
        eliminarordpr1 = new rsbuttom.RSButtonMetro();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Producto");
        setMaximumSize(new java.awt.Dimension(758, 519));
        setMinimumSize(new java.awt.Dimension(758, 519));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(244, 243, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 720, 147));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 730, 10));

        jLabel9.setFont(new java.awt.Font("Roboto Thin", 1, 18)); // NOI18N
        jLabel9.setText("Productos:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 290, 238, 0));

        jPanel2.setBackground(new java.awt.Color(244, 243, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Generar un producto\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Thin", 1, 14))); // NOI18N
        jPanel2.setLayout(null);
        jPanel2.add(cantprod);
        cantprod.setBounds(230, 30, 150, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Cantidad del producto:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 30, 180, 30);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("KG");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(390, 30, 37, 30);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 460, 100));
        jPanel2.getAccessibleContext().setAccessibleName("Generar un Producto");

        jPanel5.setBackground(new java.awt.Color(244, 243, 239));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        modificarordpr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        modificarordpr.setText("Modificar");
        modificarordpr.setColorHover(new java.awt.Color(230, 205, 141));
        modificarordpr.setColorNormal(new java.awt.Color(244, 243, 239));
        modificarordpr.setColorPressed(new java.awt.Color(244, 237, 210));
        modificarordpr.setColorTextNormal(new java.awt.Color(0, 0, 0));
        modificarordpr.setDefaultCapable(false);
        modificarordpr.setFont(new java.awt.Font("Roboto Thin", 1, 14)); // NOI18N
        modificarordpr.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        modificarordpr.setIconTextGap(25);
        modificarordpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarordprActionPerformed(evt);
            }
        });

        eliminarordpr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        eliminarordpr1.setText("Eliminar");
        eliminarordpr1.setColorHover(new java.awt.Color(230, 205, 141));
        eliminarordpr1.setColorNormal(new java.awt.Color(244, 243, 239));
        eliminarordpr1.setColorPressed(new java.awt.Color(244, 237, 210));
        eliminarordpr1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        eliminarordpr1.setDefaultCapable(false);
        eliminarordpr1.setFont(new java.awt.Font("Roboto Thin", 1, 14)); // NOI18N
        eliminarordpr1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        eliminarordpr1.setIconTextGap(25);
        eliminarordpr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarordpr1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modificarordpr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarordpr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modificarordpr, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(eliminarordpr1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 170, 210));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setBackground(new java.awt.Color(230, 205, 141));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Thin", 1, 24)); // NOI18N
        jLabel3.setText("Producto");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263)
                .addComponent(jLabel3)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        dispose();
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        this.setVisible(false);
        c.closeConexion();
    }//GEN-LAST:event_jButton2ActionPerformed
    String traerNombreRec() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String nombreRceta = null;
        try {
            String SQL = "SELECT nombre from receta where statusbajarc is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            nombreRceta = (rs.getString("nombre"));
           conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return nombreRceta;
    }
    void alertaProducto() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        try {
            Producto op = new Producto();
            PantallaRecetas rec = new PantallaRecetas();
            String SQL = "SELECT descripcion from producto where statusbajaprod is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
               if (!(rs.getString("descripcion")).equals(traerNombreRec())) {
                    JOptionPane.showMessageDialog(null, "Debe crear una receta antes de crear un producto");
                    dispose();
                    op.setVisible(false);
                    rec.setVisible(true);
                }
            }
            conn.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private void modificarordprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarordprActionPerformed
botonModificar();        
    }//GEN-LAST:event_modificarordprActionPerformed
    private void eliminarordpr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarordpr1ActionPerformed
botonEliminar();       
    }//GEN-LAST:event_eliminarordpr1ActionPerformed
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      seleccion_en_Tabla();
    }//GEN-LAST:event_jTable1MouseClicked
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Producto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.components.JSpinField cantprod;
    private rsbuttom.RSButtonMetro eliminarordpr1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private rsbuttom.RSButtonMetro modificarordpr;
    // End of variables declaration//GEN-END:variables

}
