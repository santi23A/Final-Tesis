package SistemaPanificadora;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
public class PantallaClientes extends javax.swing.JFrame {
    public static final String url = "jdbc:mysql://localhost/panificadora";
    public static final String username = "root";
    public static final String password = "";
    public PantallaClientes() {
        initComponents();
        this.setSize(738, 530);
        this.setLocationRelativeTo(null);
        AutoCompleteDecorator.decorate(this.jComboBox1);
    }
    void bloqueravtn(){
        generarbtn.setEnabled(false);
        modifbtn.setEnabled(false);
       botonEliminar.setEnabled(false);
    }
    void activarbtneliminar(){
         botonEliminar.setEnabled(true);
    }
    void activarbtnagregar(){
          generarbtn.setEnabled(true);
    }
    void activarbtnmod(){
         modifbtn.setEnabled(true);
    }
    DefaultTableModel model;
    private Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    void cargarnombre() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            String SQL = "SELECT nombre FROM `cliente` WHERE statusbajacl is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
void botonRegreso(){  
    dispose();            
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        this.setVisible(false);      
        c.closeConexion();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        correocl = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        direccioncl = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        generarbtn = new rsbuttom.RSButtonMetro();
        modifbtn = new rsbuttom.RSButtonMetro();
        botonEliminar = new rsbuttom.RSButtonMetro();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 243, 239));
        setMaximumSize(new java.awt.Dimension(714, 530));
        setMinimumSize(new java.awt.Dimension(714, 530));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel11.setBackground(new java.awt.Color(230, 205, 141));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Administración de clientes");
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 330, 42));

        jButton5.setBackground(new java.awt.Color(230, 205, 141));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 66, 40));

        getContentPane().add(jPanel11);
        jPanel11.setBounds(0, 0, 710, 60);

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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(26, 71, 660, 150);

        jPanel2.setBackground(new java.awt.Color(244, 243, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 60, 710, 170);

        jPanel3.setBackground(new java.awt.Color(244, 243, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(244, 243, 239));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Administrar datos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel4.setText("DNI:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 40, 20));

        jLabel2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 30));

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel3.setText("Correo:");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));
        jPanel6.add(correocl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 150, 30));

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel5.setText("Dirección:");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, 30));
        jPanel6.add(direccioncl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 150, 30));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 240, -1));
        jPanel6.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 240, -1));
        jPanel6.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 240, -1));
        jPanel6.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 240, 10));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar o escribir" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox1KeyTyped(evt);
            }
        });
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 150, 30));

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel6.setText("Teléfono:");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 70, 20));
        jPanel6.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 240, 10));
        jPanel6.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 150, -1));
        jPanel6.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 150, -1));

        generarbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        generarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar.png"))); // NOI18N
        generarbtn.setText("Generar");
        generarbtn.setColorBorde(null);
        generarbtn.setColorHover(new java.awt.Color(230, 205, 141));
        generarbtn.setColorNormal(new java.awt.Color(244, 243, 239));
        generarbtn.setColorPressed(new java.awt.Color(244, 237, 210));
        generarbtn.setColorTextHover(new java.awt.Color(0, 0, 0));
        generarbtn.setColorTextNormal(new java.awt.Color(0, 0, 0));
        generarbtn.setColorTextPressed(new java.awt.Color(0, 0, 0));
        generarbtn.setDefaultCapable(false);
        generarbtn.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        generarbtn.setIconTextGap(25);
        generarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarbtnActionPerformed(evt);
            }
        });

        modifbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modifbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        modifbtn.setText("Modificar");
        modifbtn.setColorHover(new java.awt.Color(230, 205, 141));
        modifbtn.setColorNormal(new java.awt.Color(244, 243, 239));
        modifbtn.setColorPressed(new java.awt.Color(244, 237, 210));
        modifbtn.setColorTextNormal(new java.awt.Color(0, 0, 0));
        modifbtn.setColorTextPressed(new java.awt.Color(0, 0, 0));
        modifbtn.setDefaultCapable(false);
        modifbtn.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        modifbtn.setIconTextGap(25);
        modifbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifbtnActionPerformed(evt);
            }
        });

        botonEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.setColorHover(new java.awt.Color(230, 205, 141));
        botonEliminar.setColorNormal(new java.awt.Color(244, 243, 239));
        botonEliminar.setColorPressed(new java.awt.Color(244, 237, 210));
        botonEliminar.setColorTextNormal(new java.awt.Color(0, 0, 0));
        botonEliminar.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonEliminar.setDefaultCapable(false);
        botonEliminar.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        botonEliminar.setIconTextGap(25);
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generarbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(generarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 230, 710, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents
void cargar(String valor) {
        String Pedidos[] = new String[9];
        Connection conn = null;
        String[] titulos = {"","Nombre y Apellido", "Correo","Dirección","Teléfono","DNI"};
        model = new DefaultTableModel(null, titulos){
        @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4) {
                    return true;
                } else {
                    return false;
                }
            }
        };
            
        try {
            conn = DriverManager.getConnection(url, username, password);
            String SQL = "Select * from cliente WHERE  nombre LIKE '%" + valor + "%' and statusbajacl is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Pedidos[0] = rs.getString("id_cliente");
                Pedidos[1] = rs.getString("nombre");
                Pedidos[2] = rs.getString("correo");
                Pedidos[3] = rs.getString("direccion");
                Pedidos[4] = rs.getString("telefono");
                Pedidos[5] = rs.getString("dni");        
                model.addRow(Pedidos);
                jTable1.setModel(model);
  jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
  jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
       e.printStackTrace();
        }
        return con;
    }    private void Limpiar_Cajas() {
        jComboBox1.setSelectedItem(null);
        correocl.setText(null);
        direccioncl.setText(null);
       jTextField1.setText(null);
 jTextField2.setText(null);
    }
    private void Limpiar_Cajas2() {
        jComboBox1.setSelectedItem(null);
        correocl.setText(null);
        direccioncl.setText(null);
        jTextField1.setText(null);
        jTextField2.setText(null);
    }
void botonEliminar(){
     Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);
        int fila = jTable1.getSelectedRow();
        int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
  int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);
        if (respuesta == JOptionPane.YES_OPTION) {
        if (fila >= 0) {
            try {
                con = getConexion();
                ps = con.prepareStatement("UPDATE cliente SET statusbajacl=? WHERE id_cliente=" + valor);
                ps.setString(1, fechaactual);
                int res = ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "EL CLIENTE FUE DADO DE BAJA", "CLIENTE - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    cargar("");
                     bloqueravtn();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "NO SE PUDO DAR DE BAJA EL CLIENTE", "CLIENTE - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        }
} else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS", "CLIENTE - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
}
}
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
       botonEliminar();
    }//GEN-LAST:event_botonEliminarActionPerformed
void botonModificar(){
    Integer  telefono= Integer.parseInt(jTextField1.getText());
        Integer  valordni= Integer.parseInt(jTextField2.getText());
               int fila = jTable1.getSelectedRow();
         int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
        Connection con = null;
        String valor2 = (String) jComboBox1.getSelectedItem();
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?", "confirm", JOptionPane.YES_NO_OPTION, HEIGHT);
        System.out.println(valor2);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                con = getConexion();
                ps = con.prepareStatement("UPDATE cliente SET correo=?,direccion=?,telefono=?,nombre=?,dni=? where id_cliente=?");
                ps.setString(1, correocl.getText());
                ps.setString(2, direccioncl.getText());
                ps.setInt(3, telefono);
                ps.setString(4, valor2);
                ps.setInt(5,valordni);
                ps.setInt(6, valor);
                int res = ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "LOS DATOS DEL CLIENTE FUERON MODIFICADOS CORRECTAMENTE", "CLIENTE - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
 bloqueravtn();
 cargar("");
                } else {
                    JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE PUDO MODIFICAR CORRECTAMENTE", "CLIENTE - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
                con.close();
            } catch (Exception e) {
           e.printStackTrace();

            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS", "CLIENTE - MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
}
    private void modifbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifbtnActionPerformed
botonModificar();
    }//GEN-LAST:event_modifbtnActionPerformed
void botonGenrar(){
int telefono =Integer.parseInt(jTextField1.getText()) ;
int valordni =Integer.parseInt(jTextField2.getText());
           while(notificacionClienteExistente()==false ){ 
        String[] myStrings = new String[]{"Select nombre from cliente"};
        JList list = new JList(myStrings);
 String valor1 = (String) jComboBox1.getSelectedItem();
        try {
            con = getConexion();
            ps = con.prepareStatement("INSERT INTO cliente(nombre,dni,correo,direccion,telefono) VALUES (?,?,?,?,?)");
            ps.setString(1, valor1);
            ps.setInt(2,valordni);
            ps.setString(3, correocl.getText());
            ps.setString(4, direccioncl.getText());
            ps.setInt(5,telefono);         
            int res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "EL CLIENTE HA SIDO AGREGADO CORRECTAMENTE", "CLIENTES", JOptionPane.INFORMATION_MESSAGE);
                Limpiar_Cajas2();
                cargar("");
                 bloqueravtn();
            } else {
                JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE HA GENERADO CORRECTAMENTE");
                Limpiar_Cajas2();
            }
            con.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
           }
            if(notificacionClienteExistente()==true) {
                 JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR CORRECTAMENTE SU ClIENTE, PORQUE YA HAY UNO CON ESE DOCUMENTO","ERROR DOCUMENTO", JOptionPane.ERROR_MESSAGE);
           Limpiar_Cajas2();
            }
}
    private void generarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarbtnActionPerformed
botonGenrar();
    }//GEN-LAST:event_generarbtnActionPerformed
    boolean  notificacionClienteExistente(){
Integer texto =Integer.parseInt(jTextField2.getText());
   int x=0; 
   boolean resultado=false;
      try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT (dni) FROM cliente WHERE statusbajacl is null ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
         x=c.rs.getInt("dni");
            if(x==texto){
            resultado=true; 
            return resultado;
            }                  
        }
      } 
        catch (Exception e) {
            e.printStackTrace();
    }
        return resultado;
  } 
    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        activarbtnagregar();
    }//GEN-LAST:event_jComboBox1KeyPressed
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        activarbtneliminar();
        activarbtnmod();
             int fila = jTable1.getSelectedRow();
         int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
        Connection con = null;
        try {
            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM cliente WHERE  id_cliente= ?");
            ps.setInt(1, valor);
            rs = ps.executeQuery();
            if (rs.next()) {
                jComboBox1.setSelectedItem(rs.getString("nombre"));
                correocl.setText(rs.getString("correo"));
                direccioncl.setText(rs.getString("direccion"));
              jTextField1.setText(rs.getString("telefono"));
              jTextField2.setText(rs.getString("dni"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe un cliente con ese nombre");
            }
        } catch (Exception e) {
         e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void jComboBox1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyTyped
    }//GEN-LAST:event_jComboBox1KeyTyped
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
         activarbtnagregar();
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
botonRegreso();
    }//GEN-LAST:event_jButton5ActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaClientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro botonEliminar;
    private javax.swing.JTextField correocl;
    private javax.swing.JTextField direccioncl;
    private rsbuttom.RSButtonMetro generarbtn;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private rsbuttom.RSButtonMetro modifbtn;
    // End of variables declaration//GEN-END:variables
}
