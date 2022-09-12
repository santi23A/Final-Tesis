package SistemaPanificadora;
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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
public class PantallaMateriaPrima extends javax.swing.JFrame {
    public static final String url = "jdbc:mysql://localhost/panificadora";
    public static final String username = "root";
    public static final String password = "";
 String MP[] = new String[6];
    public PantallaMateriaPrima() {
        initComponents();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocationRelativeTo(null);
        AutoCompleteDecorator.decorate(this.selecingred);
        AutoCompleteDecorator.decorate(this.nombreMP);
        AutoCompleteDecorator.decorate(this.unidadmedMP);
        AutoCompleteDecorator.decorate(this.unimedbox);

    }
    void bloquearbtn() {
        guardarnuevoing.setEnabled(false);
        modificarmp.setEnabled(false);
        eliminarordpr1.setEnabled(false);
    }
    void activarbtnguardat() {
        guardarnuevoing.setEnabled(true);

    }
    void activarbtnmodificar() {
        modificarmp.setEnabled(true);

    }
    void cargar(String Valor) {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Código", "Ingrediente", "Stock", "Unidad de medida"};
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
        String MP[] = new String[4];
        String filtro = jTextField1.getText();
        try {
            c.getConexion();
            conn = DriverManager.getConnection(url, username, password);
            String SQL = "SELECT `id_materia_prima`,mpc.nombre,`stock`,cum.nombre,`stock_min`,statusbajamp FROM materia_prima AS mp JOIN materia_prima_catalogo mpc ON mp. id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE statusbajamp is null AND mpc.nombre LIKE '%" + filtro + "%' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                MP[0] = rs.getString("id_materia_prima");
                MP[1] = rs.getString("mpc.nombre");
                MP[2] = rs.getString("stock");
                MP[3] = rs.getString("cum.nombre");
                c.model.addRow(MP);
                jTable1.setModel(c.model);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            }
            SQL = "SELECT DISTINCT nombre from materia_prima_catalogo ";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                selecingred.addItem(rs.getString("nombre"));
                nombreMP.addItem(rs.getString("nombre"));
            }
            SQL = "SELECT DISTINCTROW nombre from catalogo_unidad_medida";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                unimedbox.addItem(rs.getString("nombre"));
                unidadmedMP.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
void Añadir_guardarbtn(){
      if (notificacionMateriaPrimaExistente() == true) {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
           
            int unidadmedida = (int) unimedbox.getSelectedIndex();
            int selectingres = (int) selecingred.getSelectedIndex();

            int id = (int) selecingred.getSelectedIndex();
            try {
                c.ps = conn.prepareStatement("INSERT INTO materia_prima (id_mp_catalogo,stock,id_catalogo_unidad_medida,stock_min) VALUES (?,?,?,?)");

                c.ps.setInt(1, selectingres);
                c.ps.setInt(2, 0);
                c.ps.setInt(3, unidadmedida);
                c.ps.setInt(4, 50);

                int res = c.ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "SE PUDO AGREGAR CORRECTAMENTE LA MATERIA PRIMA", "MATERIA PRIMA - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO AÑADIR CORRECTAMENTE EL NUEVO INGREDIENTE", "MATERIA PRIMA - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
                c.closeConexion();

            } catch (Exception e) {
                System.err.println(e);

            }

        } else if (notificacionMateriaPrimaExistente() == false) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR CORRECTAMENTE SU MATERIA PRIMA, PORQUE YA HAY UNO EXISTENTE CON ESE NOMBRE", "ERROR NOMBRE", JOptionPane.ERROR_MESSAGE);

        }
}
void Modificar_modificarbtn(){
     int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?", "CONFIRMACION CAMBIOS", JOptionPane.YES_NO_OPTION, HEIGHT);

        if (respuesta == JOptionPane.YES_OPTION) {

            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();

            try {

                String ingr = (String) nombreMP.getSelectedItem();
                int ingr2 = (int) nombreMP.getSelectedIndex();
                int undmed = (int) unidadmedMP.getSelectedIndex();
                int cantidad = (int) StockMP.getValue();

                c.ps = conn.prepareStatement("UPDATE materia_prima SET stock=?,id_catalogo_unidad_medida=? where id_mp_catalogo=?");
                c.ps.setInt(1, cantidad);
                c.ps.setInt(2, undmed);
                c.ps.setInt(3, ingr2);

                int res = c.ps.executeUpdate();
                if (res > 0) {
                     JOptionPane.showMessageDialog(null, "SE MODIFICÓ LA MATERIA PRIMA CORRECTAMNETE", "MATERIA PRIMA - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);

                } else {
                     JOptionPane.showMessageDialog(null, "NO SE PUDO MODIFICAR LA MATERIA PRIMA", "MATERIA PRIMA - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                System.err.println(e);

            }

        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS", "MATERIA PRIMA - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
}
void Modificar_jcomboboxfiltro(){
    activarbtnmodificar();
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int stockmp = StockMP.getValue();

        String nombremp = (String) nombreMP.getSelectedItem();
        String valor = (String) unidadmedMP.getSelectedItem();
        int id = (int) nombreMP.getSelectedIndex();

        c.getConexion();
        try {

            c.ps = conn.prepareStatement("SELECT mpc.nombre AS descripcion ,stock,cum.nombre as unidad_medida FROM materia_prima AS mp JOIN catalogo_unidad_medida cum ON mp.id_materia_prima=cum.id_catalogo_unidad_medida JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo WHERE id_materia_prima=" + id);
            c.rs = c.ps.executeQuery();
            // System.out.println(""+id);

            while (c.rs.next()) {
                unidadmedMP.setSelectedItem(c.rs.getString("unidad_medida"));
                StockMP.setValue(c.rs.getInt("stock"));

                c.closeConexion();
            }

        } catch (Exception e) {

        }
}
void mpPrincipal_btnatras(){
   Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
      dispose();
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        this.setVisible(false);
        c.closeConexion();
}
void mpAñadir_btnatras(){
            NuevoIngrediente.setVisible(false);
        PantallaMateriaPrima home = new PantallaMateriaPrima();
        home.setVisible(true);

}
void mpModificar_btnatras(){
        ModificarMateriaPrima.setVisible(false);
        PantallaMateriaPrima home = new PantallaMateriaPrima();
        home.setVisible(true);
        this.setVisible(false);    
}
void btnHome(){
       Conectividad c = new Conectividad();
            Connection conn = c.getConexion();

    this.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        c.closeConexion();
}
void btnEliminar(){
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
                try {
                    c.ps = conn.prepareStatement("UPDATE materia_prima SET statusbajamp=? WHERE id_materia_prima=" + valor);
                    c.ps.setString(1, fechaactual);
                    int res = c.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "LA MATERIA PRIMA FUE DADA DE BAJA", "MATERIA PRIMA - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        cargar("");
                        bloquearbtn();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO DAR DE BAJA LA MATERIA PRIMA", "MATERIA PRIMA - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS", "MATERIA PRIMA - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
}
void buscaringredeinte(){
    Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Ingrediente", "Stock", "Unidad de medida"};
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
        String MP[] = new String[3];
  
        try {
            String var =jTextField1.getText();
            c.getConexion();
            conn = DriverManager.getConnection(url, username, password);
            String SQL = "SELECT mpc.nombre,`stock`,cum.nombre FROM `materia_prima` AS mp JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE `statusbajamp` is null AND mpc.nombre LIKE '%" + var + "%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                MP[0] = rs.getString("mpc.nombre");
                MP[1] = rs.getString("stock");
                MP[2] = rs.getString("cum.nombre");
                c.model.addRow(MP);
                jTable1.setModel(c.model);
          
            }
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }  
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModificarMateriaPrima = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        inicioSesion5 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima14 = new rsbuttom.RSButtonMetro();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        unidadmedMP = new javax.swing.JComboBox<>();
        StockMP = new com.toedter.components.JSpinField();
        nombreMP = new javax.swing.JComboBox<>();
        modificarmp = new rsbuttom.RSButtonMetro();
        NuevoIngrediente = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        inicioSesion4 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima15 = new rsbuttom.RSButtonMetro();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        unimedbox = new javax.swing.JComboBox<>();
        selecingred = new javax.swing.JComboBox<>();
        guardarnuevoing = new rsbuttom.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        atras = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        eliminarordpr1 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima4 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima3 = new rsbuttom.RSButtonMetro();

        ModificarMateriaPrima.setFocusable(false);
        ModificarMateriaPrima.setUndecorated(true);
        ModificarMateriaPrima.getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(244, 243, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMaximumSize(new java.awt.Dimension(580, 540));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(230, 205, 141));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel2.setText("Modificar ingrediente");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 250, 50));

        jButton3.setBackground(new java.awt.Color(230, 205, 141));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 50));

        jPanel23.setBackground(new java.awt.Color(244, 243, 239));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inicioSesion5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion5.setText("Salir");
        inicioSesion5.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion5.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion5.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion5.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion5.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion5.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion5.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion5.setDefaultCapable(false);
        inicioSesion5.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion5.setIconTextGap(10);
        inicioSesion5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion5ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima14.setText("Home");
        MPbotonMateriaPrima14.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima14.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima14.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima14.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima14.setDefaultCapable(false);
        MPbotonMateriaPrima14.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima14.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima14.setIconTextGap(25);
        MPbotonMateriaPrima14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima14, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(MPbotonMateriaPrima14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 230, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 650, 70));

        jPanel9.setBackground(new java.awt.Color(244, 243, 239));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "A continuación, ingrese el ingrediente a modificar:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 18))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel8.setText("Nombre del ingrediente:");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 30));
        jPanel9.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 410, 10));
        jPanel9.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 410, 10));

        jLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel7.setText("Stock:");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 60, 30));

        jLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel11.setText("Unidad de medida:");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 30));
        jPanel9.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 410, 10));

        unidadmedMP.setEditable(true);
        unidadmedMP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel9.add(unidadmedMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 220, 30));
        jPanel9.add(StockMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 220, 30));

        nombreMP.setEditable(true);
        nombreMP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        nombreMP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nombreMPItemStateChanged(evt);
            }
        });
        nombreMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreMPActionPerformed(evt);
            }
        });
        jPanel9.add(nombreMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 220, 30));

        modificarmp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modificarmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        modificarmp.setText("Modificar");
        modificarmp.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        modificarmp.setColorHover(new java.awt.Color(230, 205, 141));
        modificarmp.setColorNormal(new java.awt.Color(244, 243, 239));
        modificarmp.setColorPressed(new java.awt.Color(244, 237, 210));
        modificarmp.setColorTextNormal(new java.awt.Color(0, 0, 0));
        modificarmp.setColorTextPressed(new java.awt.Color(0, 0, 0));
        modificarmp.setDefaultCapable(false);
        modificarmp.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        modificarmp.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        modificarmp.setIconTextGap(25);
        modificarmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarmpActionPerformed(evt);
            }
        });
        jPanel9.add(modificarmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 227, 60));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 510, 270));

        ModificarMateriaPrima.getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 5, 650, 360);

        NuevoIngrediente.setFocusable(false);
        NuevoIngrediente.setMinimumSize(new java.awt.Dimension(585, 409));
        NuevoIngrediente.setUndecorated(true);
        NuevoIngrediente.getContentPane().setLayout(null);

        jPanel6.setBackground(new java.awt.Color(244, 243, 239));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(230, 205, 141));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setBackground(new java.awt.Color(230, 205, 141));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel4.setText("Nuevo ingrediente");

        jPanel22.setBackground(new java.awt.Color(244, 243, 239));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inicioSesion4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion4.setText("Salir");
        inicioSesion4.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion4.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion4.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion4.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion4.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion4.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion4.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion4.setDefaultCapable(false);
        inicioSesion4.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion4.setIconTextGap(10);
        inicioSesion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion4ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima15.setText("Home");
        MPbotonMateriaPrima15.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima15.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima15.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima15.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima15.setDefaultCapable(false);
        MPbotonMateriaPrima15.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima15.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima15.setIconTextGap(10);
        MPbotonMateriaPrima15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima15, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, -1));

        jPanel8.setBackground(new java.awt.Color(244, 243, 239));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "A continuación, ingrese el nuevo ingrediente:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N
        jPanel8.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel10.setText("Nombre del ingrediente:");
        jPanel8.add(jLabel10);
        jLabel10.setBounds(20, 60, 170, 30);
        jPanel8.add(jSeparator6);
        jSeparator6.setBounds(20, 100, 400, 10);

        jLabel14.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel14.setText("Unidad de medida:");
        jPanel8.add(jLabel14);
        jLabel14.setBounds(20, 120, 130, 30);
        jPanel8.add(jSeparator8);
        jSeparator8.setBounds(20, 180, 400, 10);

        unimedbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        unimedbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unimedboxActionPerformed(evt);
            }
        });
        jPanel8.add(unimedbox);
        unimedbox.setBounds(210, 120, 210, 30);

        selecingred.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        selecingred.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selecingredItemStateChanged(evt);
            }
        });
        selecingred.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                selecingredKeyTyped(evt);
            }
        });
        jPanel8.add(selecingred);
        selecingred.setBounds(210, 60, 210, 30);

        guardarnuevoing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        guardarnuevoing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar.png"))); // NOI18N
        guardarnuevoing.setText("Guardar");
        guardarnuevoing.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        guardarnuevoing.setColorHover(new java.awt.Color(230, 205, 141));
        guardarnuevoing.setColorNormal(new java.awt.Color(244, 243, 239));
        guardarnuevoing.setColorPressed(new java.awt.Color(244, 237, 210));
        guardarnuevoing.setColorTextHover(new java.awt.Color(0, 0, 0));
        guardarnuevoing.setColorTextNormal(new java.awt.Color(0, 0, 0));
        guardarnuevoing.setColorTextPressed(new java.awt.Color(0, 0, 0));
        guardarnuevoing.setDefaultCapable(false);
        guardarnuevoing.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        guardarnuevoing.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        guardarnuevoing.setIconTextGap(10);
        guardarnuevoing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarnuevoingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guardarnuevoingMouseEntered(evt);
            }
        });
        guardarnuevoing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarnuevoingActionPerformed(evt);
            }
        });
        jPanel8.add(guardarnuevoing);
        guardarnuevoing.setBounds(240, 220, 210, 50);

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 470, 280));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 220, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 91, Short.MAX_VALUE))
        );

        NuevoIngrediente.getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 800, 500);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(244, 243, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(867, 562));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setBackground(new java.awt.Color(230, 205, 141));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel3.setText("Administración de materia prima");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(jLabel3)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 770, 70));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 128, 750, 156));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 122, 28));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel1.setText("Ingrese el nombre de la materia prima:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        jPanel1.add(atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(856, 145, -1, -1));

        jPanel10.setBackground(new java.awt.Color(244, 243, 239));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "¿Que desea hacer?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N

        eliminarordpr1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        eliminarordpr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        eliminarordpr1.setText("Eliminar");
        eliminarordpr1.setColorHover(new java.awt.Color(230, 205, 141));
        eliminarordpr1.setColorNormal(new java.awt.Color(244, 243, 239));
        eliminarordpr1.setColorPressed(new java.awt.Color(244, 237, 210));
        eliminarordpr1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        eliminarordpr1.setDefaultCapable(false);
        eliminarordpr1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        eliminarordpr1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        eliminarordpr1.setIconTextGap(25);
        eliminarordpr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarordpr1ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MPbotonMateriaPrima4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        MPbotonMateriaPrima4.setText("Modificar ingredientes");
        MPbotonMateriaPrima4.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima4.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima4.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima4.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima4.setColorTextPressed(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima4.setDefaultCapable(false);
        MPbotonMateriaPrima4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima4.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima4.setIconTextGap(25);
        MPbotonMateriaPrima4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima4ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MPbotonMateriaPrima3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/añadir.png"))); // NOI18N
        MPbotonMateriaPrima3.setText("Añadir ingrediente");
        MPbotonMateriaPrima3.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima3.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima3.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima3.setColorTextPressed(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima3.setDefaultCapable(false);
        MPbotonMateriaPrima3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima3.setIconTextGap(25);
        MPbotonMateriaPrima3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MPbotonMateriaPrima3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(eliminarordpr1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(MPbotonMateriaPrima4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eliminarordpr1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(MPbotonMateriaPrima3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(MPbotonMateriaPrima4, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 740, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 440));

        setBounds(0, 0, 772, 445);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
mpPrincipal_btnatras();      
    }//GEN-LAST:event_jButton2ActionPerformed

    void alertaStock() {
        pantallaInicio v = new pantallaInicio();
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();

            String SQL = "SELECT mpc.Nombre,stock,stock_min from materia_prima AS mp JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo where mp.statusbajamp is null";

            Statement stmt = conn.createStatement();

            c.rs = stmt.executeQuery(SQL);

            while (c.rs.next()) {
                String valor = c.rs.getString("mpc.nombre");
                if ((c.rs.getInt("stock")) < c.rs.getInt("stock_min")) {
                    JOptionPane.showMessageDialog(this, "Se detectó faltantes de Stock de Materias Primas  Por favor revise Materia Prima", "Alerta Stock", JOptionPane.WARNING_MESSAGE);
                    break;
                }

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void MPbotonMateriaPrima4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima4ActionPerformed
        dispose();
        ModificarMateriaPrima.setVisible(true);
        this.setVisible(false);
        ModificarMateriaPrima.setSize(650, 368);
        ModificarMateriaPrima.setLocationRelativeTo(null);

    }//GEN-LAST:event_MPbotonMateriaPrima4ActionPerformed

    private void MPbotonMateriaPrima3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima3ActionPerformed
        dispose();
        NuevoIngrediente.setVisible(true);
        this.setVisible(false);        // TODO add your handling code here: 
        NuevoIngrediente.setSize(585, 409);
        NuevoIngrediente.setLocationRelativeTo(null);
        NuevoIngrediente.setResizable(false);
    }//GEN-LAST:event_MPbotonMateriaPrima3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
buscaringredeinte();    
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        mpModificar_btnatras();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void modificarmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarmpActionPerformed
Modificar_modificarbtn();       
    }//GEN-LAST:event_modificarmpActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
mpAñadir_btnatras();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guardarnuevoingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarnuevoingActionPerformed
Añadir_guardarbtn(); 
    }//GEN-LAST:event_guardarnuevoingActionPerformed

    boolean notificacionMateriaPrimaExistente() {
        int x = 0;
        boolean resultado = true;
        int y = (int) selecingred.getSelectedIndex();
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT (id_mp_catalogo) FROM materia_prima where statusbajamp is null ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                x = c.rs.getInt("id_mp_catalogo");
                if (x == y) {
                    resultado = false;
                    return resultado;

                }
            }
            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return resultado;
    }
    private void eliminarordpr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarordpr1ActionPerformed
btnEliminar();
    }//GEN-LAST:event_eliminarordpr1ActionPerformed

    private void unimedboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unimedboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unimedboxActionPerformed

    private void guardarnuevoingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarnuevoingMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarnuevoingMouseClicked

    private void guardarnuevoingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarnuevoingMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_guardarnuevoingMouseEntered

    private void nombreMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreMPActionPerformed

    private void nombreMPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nombreMPItemStateChanged
  Modificar_jcomboboxfiltro();
    }//GEN-LAST:event_nombreMPItemStateChanged

    private void selecingredItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selecingredItemStateChanged
        // TODO add your handling code here:
        activarbtnguardat();
    }//GEN-LAST:event_selecingredItemStateChanged

    private void selecingredKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selecingredKeyTyped
        // TODO add your handling code here:
        activarbtnguardat();
    }//GEN-LAST:event_selecingredKeyTyped

    private void inicioSesion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion4ActionPerformed
        System.exit(0);    // TODO add your handling code here:
    }//GEN-LAST:event_inicioSesion4ActionPerformed

    private void MPbotonMateriaPrima15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima15ActionPerformed
     btnHome();
    }//GEN-LAST:event_MPbotonMateriaPrima15ActionPerformed

    private void inicioSesion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion5ActionPerformed
        System.exit(0);    // TODO add your handling code here:
    }//GEN-LAST:event_inicioSesion5ActionPerformed

    private void MPbotonMateriaPrima14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima14ActionPerformed
btnHome();        
    }//GEN-LAST:event_MPbotonMateriaPrima14ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        eliminarordpr1.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(PantallaMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaMateriaPrima().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima14;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima15;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima3;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima4;
    private javax.swing.JFrame ModificarMateriaPrima;
    private javax.swing.JFrame NuevoIngrediente;
    private com.toedter.components.JSpinField StockMP;
    private javax.swing.JLabel atras;
    private rsbuttom.RSButtonMetro eliminarordpr1;
    private rsbuttom.RSButtonMetro guardarnuevoing;
    private rsbuttom.RSButtonMetro inicioSesion4;
    private rsbuttom.RSButtonMetro inicioSesion5;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private rsbuttom.RSButtonMetro modificarmp;
    private javax.swing.JComboBox<String> nombreMP;
    private javax.swing.JComboBox<String> selecingred;
    private javax.swing.JComboBox<String> unidadmedMP;
    private javax.swing.JComboBox<String> unimedbox;
    // End of variables declaration//GEN-END:variables
}
