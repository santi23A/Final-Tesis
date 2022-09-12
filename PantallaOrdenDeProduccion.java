package SistemaPanificadora;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
public class PantallaOrdenDeProduccion extends javax.swing.JFrame {
public Timer tiempo;
    public PantallaOrdenDeProduccion() {
        initComponents();
            } 
    void deshabilitarebtn() {
        rSButtonMetro3.setEnabled(false);
        jLabel7.setEnabled(false);
        jDateChooser2.setEnabled(false);
        rSButtonMetro1.setEnabled(false);
        rSButtonMetro5.setEnabled(false);
         jLabel1.setVisible(false);
    }
    void activarebtn() {
        rSButtonMetro3.setEnabled(true);
        jLabel7.setEnabled(true);
        jDateChooser2.setEnabled(true);
        rSButtonMetro5.setEnabled(true);
    }
    void activarinsertar() {
        rSButtonMetro1.setEnabled(true);
    }
    void cargarumedop() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        try {
            String SQL = "SELECT DISTINCT cum.nombre AS unidad_medida from orden_de_produccion AS op JOIN producto p ON op.id_producto=p.id_producto JOIN catalogo_unidad_medida cum on p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE `statusbajaordprod` is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jTextField1.setText(rs.getString("unidad_medida"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void cargarprod() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        try {

            String SQL = "SELECT nombre FROM producto AS p JOIN receta rc ON p.id_receta=rc.id_receta JOIN detalle_receta dtrc ON rc.id_receta=dtrc.id_detalle_receta where id_detalle_receta >0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox4.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void cargaropsindpp(String npp) {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Código", " Producto", "Cantidad", "Unidad de medida", "Fecha", "Origen", "Codigo del producto"};
        c.model = new DefaultTableModel(null, titulos){
          @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String opdspp[] = new String[7];
        try {
            String SQL = "Select CASE WHEN interno = 1 THEN \"Interno\" ELSE \"Externo\" END origen,id_orden_produccion,descripcion,cantidad,cum.nombre AS unidad_medida,fecha,statusbajaordprod,op.id_producto FROM orden_de_produccion AS op JOIN producto p ON op.id_producto=p.id_producto JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida  where statusbajaordprod is null AND estado_de_produccion = false";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                opdspp[0] = rs.getString("id_orden_produccion");
                opdspp[1] = rs.getString("descripcion");
                opdspp[2] = rs.getString("cantidad");
                opdspp[3] = rs.getString("unidad_medida");
                opdspp[4] = rs.getString("fecha");
                opdspp[5] = rs.getString("origen");
                opdspp[6] = rs.getString("op.id_producto");
                c.model.addRow(opdspp);
                ordprod.setModel(c.model);
                ordprod.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
                ordprod.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                ordprod.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
                ordprod.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        verNotificacionesOrdProd = new rsbuttom.RSButtonMetro();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordprod = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        producetxt = new javax.swing.JSpinner();
        rSButtonMetro1 = new rsbuttom.RSButtonMetro();
        rSButtonMetro3 = new rsbuttom.RSButtonMetro();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        rSButtonMetro4 = new rsbuttom.RSButtonMetro();
        rSButtonMetro5 = new rsbuttom.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(931, 532));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(null);

        jButton2.setBackground(new java.awt.Color(230, 205, 141));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);
        jButton2.setBounds(11, 12, 66, 53);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Administración - Orden de producción");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(270, 22, 390, 30);

        verNotificacionesOrdProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/ios7-bell-outline_icon-icons.com_50335.png"))); // NOI18N
        verNotificacionesOrdProd.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        verNotificacionesOrdProd.setColorHover(new java.awt.Color(230, 205, 141));
        verNotificacionesOrdProd.setColorNormal(new java.awt.Color(244, 243, 239));
        verNotificacionesOrdProd.setColorPressed(new java.awt.Color(244, 237, 210));
        verNotificacionesOrdProd.setColorTextHover(new java.awt.Color(0, 0, 0));
        verNotificacionesOrdProd.setColorTextNormal(new java.awt.Color(0, 0, 0));
        verNotificacionesOrdProd.setColorTextPressed(new java.awt.Color(0, 0, 0));
        verNotificacionesOrdProd.setDefaultCapable(false);
        verNotificacionesOrdProd.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        verNotificacionesOrdProd.setIconTextGap(10);
        verNotificacionesOrdProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verNotificacionesOrdProdMouseClicked(evt);
            }
        });
        verNotificacionesOrdProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verNotificacionesOrdProdActionPerformed(evt);
            }
        });
        jPanel4.add(verNotificacionesOrdProd);
        verNotificacionesOrdProd.setBounds(840, 20, 70, 30);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 931, 70);

        ordprod.setAutoCreateRowSorter(true);
        ordprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ordprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordprodMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ordprodMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(ordprod);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 88, 910, 162);

        jPanel13.setBackground(new java.awt.Color(244, 243, 239));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel4.setText("Cantidad:");
        jPanel13.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 30));
        jPanel13.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 310, 10));

        producetxt.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));
        jPanel13.add(producetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 160, 30));

        rSButtonMetro1.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro1.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        rSButtonMetro1.setText("Agregar");
        rSButtonMetro1.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro1.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro1.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro1ActionPerformed(evt);
            }
        });
        jPanel13.add(rSButtonMetro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 140, 50));

        rSButtonMetro3.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro3.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        rSButtonMetro3.setText("Eliminar");
        rSButtonMetro3.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro3.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro3.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });
        jPanel13.add(rSButtonMetro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 140, 50));

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel6.setText("Producto:");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 30));

        jComboBox4.setEditable(true);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel13.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 160, 30));

        jLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel7.setText("Fecha de entrega:");
        jPanel13.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 130, 20));
        jPanel13.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 310, 10));
        jPanel13.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 310, 10));
        jPanel13.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 310, 10));
        jPanel13.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 130, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel8.setText("Fecha de entrega:");
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, 30));
        jPanel13.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 310, 10));
        jPanel13.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 310, 10));

        rSButtonMetro4.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro4.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setText("Actualizar fecha de entrega");
        rSButtonMetro4.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro4.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro4.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro4ActionPerformed(evt);
            }
        });
        jPanel13.add(rSButtonMetro4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 210, -1));

        rSButtonMetro5.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro5.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/verificar.png"))); // NOI18N
        rSButtonMetro5.setText("Producir");
        rSButtonMetro5.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro5.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro5.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro5.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro5.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonMetro5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMetro5MousePressed(evt);
            }
        });
        rSButtonMetro5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro5ActionPerformed(evt);
            }
        });
        jPanel13.add(rSButtonMetro5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 190, 140, 50));

        jPanel1.setBackground(new java.awt.Color(244, 243, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Orden de producción interna", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(244, 243, 239));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel13.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 370, 210));

        jPanel2.setBackground(new java.awt.Color(244, 243, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Orden de producción externa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 350, 140));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel1.setText("Cargando...");
        jPanel13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 199, 140, 40));

        getContentPane().add(jPanel13);
        jPanel13.setBounds(0, 268, 931, 267);

        jPanel3.setBackground(new java.awt.Color(244, 243, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 70, 930, 200);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        this.setVisible(false);
        c.closeConexion();
    }//GEN-LAST:event_jButton2ActionPerformed
void eliminarOrdPro(){
 Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);
        int fila = ordprod.getSelectedRow();

        int valor = parseInt(ordprod.getValueAt(fila, 0).toString());

        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (fila >= 0) {
                c.getConexion();
                try {

                    c.ps = conn.prepareStatement("UPDATE orden_de_produccion SET statusbajaordprod=? WHERE id_orden_produccion=" + valor);

                    c.ps.setString(1, fechaactual);
                    int res = c.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "SE HA PODIDO DAR DE BAJA LA ORDEN DE PRODUCCIÓN", "ORDEN DE PRODUCCIÓN - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        cargaropsindpp("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO DAR DE BAJA LA ORDEN DE PRODUCCIÓN", "ORDEN DE PRODUCCIÓN - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    ex.printStackTrace();
                }
            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }


}
    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
eliminarOrdPro();    
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed
    void agregarOrdProd(){
          float produce = (float) producetxt.getValue();
          if(produce>0){
     String dia = Integer.toString(jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser1.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(jDateChooser1.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes + "-" + dia);
        int selectprod = (int) jComboBox4.getSelectedIndex();
        Integer id = null;
      
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        try {
            c.getConexion();
            c.ps = conn.prepareStatement("INSERT INTO orden_de_produccion(id_producto,cantidad,fecha,interno,estado_de_produccion) VALUES (?,?,?,?,?)");
            c.ps.setInt(1, selectprod);
            c.ps.setFloat(2, produce);
            c.ps.setString(3, fecha);
            c.ps.setInt(4, 1);
            c.ps.setBoolean(5, false);
            int res = c.ps.executeUpdate();
            cargaropsindpp("");
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "SE PUDO GENERAR LA ORDEN DE PRODUCCIÓN (INTERNA) CORRECTAMENTE", "ORDEN DE PRODUCCIÓN - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                jComboBox4.setSelectedIndex(0);
                producetxt.setValue(0);
            } else {
                 JOptionPane.showMessageDialog(null, "LA ORDEN DE PRODUCCIÓN (INTERNA) NO PUDO SER GENERADA", "ORDEN DE PRODUCCIÓN - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
          }else{
                JOptionPane.showMessageDialog(null, "No se puede generar una orden de producción con cantidad cero", "ERROR", JOptionPane.ERROR_MESSAGE);

          }
    }      
    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
agregarOrdProd();       
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed
    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
    
        activarinsertar();
        cargarumedop();
    }//GEN-LAST:event_jComboBox4ItemStateChanged
    private void ordprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordprodMouseClicked
        activarebtn();     
    }//GEN-LAST:event_ordprodMouseClicked
    void actualizarFechaEntrega(){
    String dia = Integer.toString(jDateChooser2.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser2.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(jDateChooser2.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes + "-" + dia);
        int fila = ordprod.getSelectedRow();

        int valor = parseInt(ordprod.getValueAt(fila, 0).toString());

        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTÁ SEGURO EN QUE DESEA ACTUALIZAR LA FECHA DE ENTREGA?", "MODIFICAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (fila >= 0) {
                c.getConexion();
                try {

                    c.ps = conn.prepareStatement("UPDATE orden_de_produccion SET fecha=? WHERE id_orden_produccion=" + valor);

                    c.ps.setString(1, fecha);
                    int res = c.ps.executeUpdate();
                    cargaropsindpp("");
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "SE ACTUALIZÓ LA FECHA DE ENTREGA DE LA ORDEN DE PRODUCCIÓN (EXTERNA)", "ORDEN DE PRODUCCIÓN - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR LA FECHA DE LA ORDEN DE PRODUCCIÓN (EXTERNA)", "ORDEN DE PRODUCCIÓN - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }

    
    
    }
    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
actualizarFechaEntrega();
        
    }//GEN-LAST:event_rSButtonMetro4ActionPerformed
    private void verNotificacionesOrdProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verNotificacionesOrdProdMouseClicked
        // TODO add your handling code here:
        PantallaOrdenDeProduccion n = new PantallaOrdenDeProduccion();
        n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesOrdProdMouseClicked
    private void verNotificacionesOrdProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verNotificacionesOrdProdActionPerformed
    PantallaOrdenDeProduccion n = new PantallaOrdenDeProduccion();
n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesOrdProdActionPerformed
    private void rSButtonMetro5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro5ActionPerformed
 jLabel1.setVisible(true);
  try {  
     if (puedoProducir() == true) { 
         descuentoySeteodeStock();      
     } else {
            JOptionPane.showMessageDialog(null, "NO SE PUEDE GENERAR LA ORDEN DE PRODUCCION PORQUE HAY FALTANTE DE STOCK DE INGREDIENTE/S PARA PRODUCIR LA RECETA SOLICITADA", "ERROR PEDIDO", JOptionPane.ERROR_MESSAGE);
       jLabel1.setVisible(false);
     }
        } catch (Exception ex) {
            Logger.getLogger(PantallaOrdenDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rSButtonMetro5ActionPerformed
    ArrayList<Float> resultadoResta() {
        ArrayList<Float> listaResultadoDescuentoStock = new ArrayList<>();
        for (int i = 0; i < listaConsumoPorIngredienteDeReceta().size(); i++) {
            listaResultadoDescuentoStock.add((listaStockIngredientesDeMp().get(i)) - (listaConsumoPorIngredienteDeReceta().get(i)));
        }
        return listaResultadoDescuentoStock;
    }
    void descuentoySeteodeStock() {
        int codigo_receta = 0;
        int codigo_MateriaPrima = 0;
        int codigo_mp_en_DetalleReceta = 0;
        float stock_mp = 0;
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        int fila = ordprod.getSelectedRow();
        int codigoProducto = parseInt(ordprod.getValueAt(fila, 6).toString());
        float cantidadProducto = parseInt(ordprod.getValueAt(fila, 2).toString());
        try {
            String SQL = "SELECT dtrc.id_receta,id_producto,mp.id_materia_prima,dtrc.id_materia_prima,mp.stock FROM receta AS rc JOIN producto p ON rc.id_receta=p.id_receta JOIN detalle_receta dtrc ON rc.id_receta=dtrc.id_receta JOIN materia_prima mp ON dtrc.id_materia_prima=mp.id_materia_prima where dtrc.id_receta=" + codigoProducto;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                codigo_receta = rs.getInt("dtrc.id_receta");//obtengo id receta
                codigo_MateriaPrima = rs.getInt("mp.id_materia_prima");//obtengo id materia prima
                codigo_mp_en_DetalleReceta = rs.getInt("dtrc.id_materia_prima");//obtengo id_mp_detalle receta
                stock_mp = rs.getFloat("mp.stock");//obtengo stock del producto seleccionado
                   }                           
                  for (int i = 0; i < ListaIdMateriaPrimaDetalleReceta().size(); i++) {
                       if (codigo_receta == codigoProducto) {
                    if (codigo_mp_en_DetalleReceta == codigo_MateriaPrima) {
                            c.ps = conn.prepareStatement("UPDATE materia_prima AS mp INNER JOIN detalle_receta AS dtr ON mp.id_materia_prima= dtr.id_materia_prima SET mp.stock=?  WHERE mp.id_materia_prima=? and id_receta=" + codigoProducto);
                            c.ps.setFloat(1, (resultadoResta().get(i))); 
                            c.ps.setInt(2, ListaIdMateriaPrimaDetalleReceta().get(i));                     
                            c.ps.executeUpdate();
                        }
                    }
                }
            float varprod = 0;
            try {
                String SQL1 = "SELECT stock from producto where id_producto=" + codigoProducto;
                Statement stmtt = conn.createStatement();
                ResultSet rss = stmtt.executeQuery(SQL1);
                while (rss.next()) {
                    varprod = rss.getFloat("stock");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            varprod = cantidadProducto + varprod;
            c.ps = conn.prepareStatement("UPDATE producto set stock=? WHERE id_receta=" + codigoProducto);
            c.ps.setFloat(1, varprod);
            c.ps.executeUpdate();
            c.ps = conn.prepareStatement("UPDATE orden_de_produccion set estado_de_produccion=? WHERE id_producto=" + codigoProducto);
            c.ps.setBoolean(1, true);
            c.ps.executeUpdate();
cargaropsindpp(""); 
            jLabel1.setVisible(false);
            JOptionPane.showMessageDialog(null, "SE GENERÓ CORRECTAMENTE LA ORDEN DE PRODUCCIÓN", "ORDEN DE PRODUCCIÓN", JOptionPane.INFORMATION_MESSAGE);             
        } catch (Exception e) {
            e.printStackTrace();
              jLabel1.setVisible(false);
        }
        c.closeConexion();
    }
    ArrayList<Integer> ListaIdMateriaPrima() {
        int fila = ordprod.getSelectedRow();
        int checkProducto = parseInt(ordprod.getValueAt(fila, 5).toString());
        ArrayList<Integer> listaIDsMateriaPrima = new ArrayList<Integer>();
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {
            String SQL = ("SELECT id_materia_prima from materia_prima");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                listaIDsMateriaPrima.add(rs.getInt("id_materia_prima"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
        return listaIDsMateriaPrima;
    }
    ArrayList<Integer> ListaIdMateriaPrimaDetalleReceta() {
        int fila = ordprod.getSelectedRow();
        int checkProducto = parseInt(ordprod.getValueAt(fila, 6).toString());
        ArrayList<Integer> listaIDsMateriaPrima = new ArrayList<Integer>();
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {
            String SQL = ("select id_materia_prima from detalle_receta where id_receta=" + checkProducto);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {   
                listaIDsMateriaPrima.add(rs.getInt("id_materia_prima"));}     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaIDsMateriaPrima;
    }
    public boolean puedoProducir() {

      
        int fila = ordprod.getSelectedRow();
        int checkProducto = parseInt(ordprod.getValueAt(fila,6).toString());

        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        boolean valor = true;
        try {

            String SQL = "SELECT mp.id_materia_prima,mp.stock as 'stock ingrediente' FROM materia_prima mp join detalle_receta dtr on mp.id_materia_prima=dtr.id_materia_prima WHERE `statusbajamp` is null and statusbajadtrc is null and id_receta=" + checkProducto;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            int i = 0;
            while (rs.next()) {
                float stockIngrediente = rs.getFloat("stock ingrediente");
 

                float resultadoResta = resultadoResta().get(i);

                if (resultadoResta < 1) {
                    resultadoResta = 0;
                }
              
                if (stockIngrediente < resultadoResta || stockIngrediente == 0.0) {
                    valor = false;
                    return valor;
                }

                i++;

            }

        } catch (Exception e) {
            e.printStackTrace();
              jLabel1.setVisible(false);
        }
        c.closeConexion();
        return valor;

    }
    float rendimientoReceta() {
        float rendimiento = 0;
        int fila = ordprod.getSelectedRow();
int checkProducto = parseInt(ordprod.getValueAt(fila, 6).toString());
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {
            String SQL = ("SELECT cantidad FROM receta where id_receta=" + checkProducto);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                rendimiento = rs.getFloat("receta.cantidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
        return rendimiento;
    }
    ArrayList<Float> listaConsumoPorIngredienteDeReceta() {
        ArrayList<Float> listaConsumoPorIngredienteDeReceta = new ArrayList<Float>();
        int fila = ordprod.getSelectedRow();
        float PedidoCant = parseInt(ordprod.getValueAt(fila, 2).toString());
        for (int l = 0; l < rendimientoPorIngredienteReceta().size(); l++) {
            listaConsumoPorIngredienteDeReceta.add((PedidoCant * rendimientoPorIngredienteReceta().get(l) / rendimientoReceta()));
        }
        return listaConsumoPorIngredienteDeReceta;
    }
    ArrayList<Float> listaStockIngredientesDeMp() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        int fila = ordprod.getSelectedRow();
        ArrayList<Float> listaStockIngredientesDeReceta = new ArrayList<Float>();
        ArrayList<Integer> listaStockIngredientesDeRecetaid = new ArrayList<Integer>();
        int checkProducto = parseInt(ordprod.getValueAt(fila, 6).toString());
        c.getConexion();
        try {
            String SQL = "SELECT mp.stock as 'stock ingrediente' FROM materia_prima mp join detalle_receta dtr on mp.id_materia_prima=dtr.id_materia_prima WHERE `statusbajamp` is null and statusbajadtrc is null and id_receta=" + checkProducto;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                float stockIngredReceta = rs.getFloat("stock ingrediente");
                 listaStockIngredientesDeReceta.add(stockIngredReceta);       
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
        return listaStockIngredientesDeReceta;
    }
    ArrayList<Float> rendimientoPorIngredienteReceta() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        int fila = ordprod.getSelectedRow();
        ArrayList<Float> listaMatPrimaRendimiento = new ArrayList<>();
        int checkProducto = parseInt(ordprod.getValueAt(fila, 6).toString());
        c.getConexion();
        try {
            String SQL = ("SELECT detalle_receta.cantidad 'rendimiento_ingred' FROM detalle_receta JOIN receta on detalle_receta.id_receta= receta.id_receta JOIN materia_prima mp on detalle_receta.id_materia_prima=mp.id_materia_prima JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo WHERE statusbajarc is null and detalle_receta.statusbajadtrc is null and receta.id_receta=" + checkProducto);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                float rendimientosPorMatPrima = rs.getFloat("rendimiento_ingred");
                listaMatPrimaRendimiento.add(rendimientosPorMatPrima);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();

        return (listaMatPrimaRendimiento);

    }
    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed

    }//GEN-LAST:event_jComboBox4ActionPerformed
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed
    private void rSButtonMetro5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMetro5MouseClicked

    }//GEN-LAST:event_rSButtonMetro5MouseClicked
    private void ordprodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordprodMouseEntered
    }//GEN-LAST:event_ordprodMouseEntered
    private void rSButtonMetro5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMetro5MousePressed
 jLabel1.setVisible(true);       
    }//GEN-LAST:event_rSButtonMetro5MousePressed
    public ArrayList<Integer> corroborarMatPrimas() {
        int fila = ordprod.getSelectedRow();
        ArrayList<Integer> listaMatPrimProducto = new ArrayList<>();
        int checkProducto = parseInt(ordprod.getValueAt(fila, 6).toString());
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {
            String SQL = ("SELECT receta.nombre,detalle_receta.cantidad AS \"cantidadIngrediente\" FROM detalle_receta JOIN receta on detalle_receta.id_receta= receta.id_receta WHERE statusbajarc is null and detalle_receta.statusbajadtrc is null and receta.id_receta=" + checkProducto);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

                int maTeriasPrimas = rs.getInt("cantidadIngrediente");
                listaMatPrimProducto.add(maTeriasPrimas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
        return (listaMatPrimProducto);
    }
    public boolean testeoStockNotif() {
   boolean o = false;
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT id_mp_catalogo,stock,stock_min from materia_prima   ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                String valor = c.rs.getString("id_mp_catalogo");
                if ((c.rs.getInt("stock")) < c.rs.getInt("stock_min")) {
                    o = true;
                    return o;
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
    String numeroNotificacion() {
        String x = "";
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT COUNT(stock) as 'stock' from materia_prima where stock<stock_min and statusbajamp is null ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                x = c.rs.getString("stock");
            }
            conn.close();
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    void notificacionLabel() {
        if (testeoStockNotif() == true) {
            verNotificacionesOrdProd.setText(numeroNotificacion());

        }
        if (testeoStockNotif() == false) {
            verNotificacionesOrdProd.setText("0");
        }
    }
    void botonNotificaciones() {
        try {
            while (testeoStockNotif() == true) {
                dispose();
                AlertaStock v = new AlertaStock();
                v.cargar("");
                v.setVisible(true);
                break;

            }

            if (testeoStockNotif() == false) {
                JOptionPane.showMessageDialog(null, "NO HAY NOTIFICACIONES");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaOrdenDeProduccion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaOrdenDeProduccion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaOrdenDeProduccion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaOrdenDeProduccion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaOrdenDeProduccion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable ordprod;
    private javax.swing.JSpinner producetxt;
    private rsbuttom.RSButtonMetro rSButtonMetro1;
    private rsbuttom.RSButtonMetro rSButtonMetro3;
    private rsbuttom.RSButtonMetro rSButtonMetro4;
    private rsbuttom.RSButtonMetro rSButtonMetro5;
    private rsbuttom.RSButtonMetro verNotificacionesOrdProd;
    // End of variables declaration//GEN-END:variables
}
