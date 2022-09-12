package SistemaPanificadora;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.Objects;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
public class PantallaProveedor extends javax.swing.JFrame {
    Conectividad c = new Conectividad();
    Connection conn = c.getConexion();
    public PantallaProveedor() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons2/Polo2.png")));

    }
void bloqearbtn(){
    botonEliminar.setEnabled(false);
    generarbtn.setEnabled(false);
}
void activarbtneliminar(){
     botonEliminar.setEnabled(true);
}
void activarbtn(){  
    generarbtn.setEnabled(true);
}
    void buscarauto() {
        String NIT_DOC = nombreapellido.getText(); //guardas el valor de tu textfield en un String
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/panificadora", "root", ""); //conexion con tu bd
            PreparedStatement consulta = con.prepareStatement("Select * FROM proveedor where id_proveedor = ? "); //tu consulta
            consulta.setString(1, NIT_DOC); //envias el dato a consultar

            ResultSet resultado = consulta.executeQuery(); //obtienes un resultado

            if (resultado.next()) {  //en caso de que el id exista

            } else {  //en caso de que el id no exista

                JOptionPane.showMessageDialog(null, "El Nombre del proveedor no se encuentra registrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PantallaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargar(String valor) {
        String[] titulos = {"ID de proveedor", "Proveedor", "Cuit"};
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
        String OP[] = new String[3];
        try {
            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM `proveedor` WHERE statusbajaprov is null";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                OP[0] = rs.getString("id_proveedor");
                OP[1] = rs.getString("nombre");
                OP[2]= rs.getString("cuit");
                c.model.addRow(OP);
                jtProveedor.setModel(c.model);
  jtProveedor.getColumnModel().getColumn(0).setPreferredWidth(0);
  jtProveedor.getColumnModel().getColumn(0).setMaxWidth(0);
  jtProveedor.getColumnModel().getColumn(0).setMinWidth(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    private void Limpiar_Cajas() {
        nombreapellido.setText(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inicioSesion6 = new rsbuttom.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        nombreapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cuitTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        generarbtn = new rsbuttom.RSButtonMetro();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProveedor = new javax.swing.JTable();
        botonEliminar = new rsbuttom.RSButtonMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(477, 441));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setBackground(new java.awt.Color(230, 205, 141));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel3.setText("Administración de proveedor");

        inicioSesion6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion6.setText("Salir");
        inicioSesion6.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion6.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion6.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion6.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion6.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion6.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion6.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion6.setDefaultCapable(false);
        inicioSesion6.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion6.setIconTextGap(10);
        inicioSesion6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inicioSesion6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 70));

        jPanel1.setBackground(new java.awt.Color(244, 243, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(244, 243, 239));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Administrar datos Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 12))); // NOI18N
        jPanel6.setLayout(null);

        nombreapellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreapellidoMouseClicked(evt);
            }
        });
        nombreapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreapellidoActionPerformed(evt);
            }
        });
        nombreapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreapellidoKeyPressed(evt);
            }
        });
        jPanel6.add(nombreapellido);
        nombreapellido.setBounds(150, 30, 140, 28);

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel4.setText("Cuit:");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(100, 70, 40, 30);

        cuitTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuitTxtActionPerformed(evt);
            }
        });
        cuitTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cuitTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuitTxtKeyTyped(evt);
            }
        });
        jPanel6.add(cuitTxt);
        cuitTxt.setBounds(150, 70, 140, 30);

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel5.setText("Nombre y apellido:");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(10, 20, 160, 30);

        generarbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        generarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
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
        jPanel6.add(generarbtn);
        generarbtn.setBounds(270, 120, 170, 50);

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 450, 180));

        jLabel2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel2.setText("Nombre y apellido:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 470, 200));

        jPanel2.setBackground(new java.awt.Color(244, 243, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtProveedor);

        botonEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 470, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        this.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        c.closeConexion();
    }//GEN-LAST:event_jButton3ActionPerformed

    int comprobacionID() {
        int identificacion = 0;
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT * FROM proveedor where statusbajaprov is null ";
            Statement stmt = c.con.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {

                String nombreprov = c.rs.getString("nombre");
                if (nombreprov.equals(c.rs.getString("nombre"))) {
                    identificacion = c.rs.getInt("id_proveedor");

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        return identificacion;

    }
    private void generarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarbtnActionPerformed

    
       if(notificacionProveedorExistente()==false ){ 
 c.getConexion();
 String numero=cuitTxt.getText();
 isValidCUITCUIL(numero);   
 try {
   c.ps = c.con.prepareStatement("INSERT INTO proveedor (nombre,cuit) VALUES(?,?)");
 c.ps.setString(1, nombreapellido.getText());
  c.ps.setString(2,numero);
 int res = c.ps.executeUpdate();
if (res > 0 ) {
JOptionPane.showMessageDialog(null, "SE HAN AGREGADO CORRECTAMENTE LOS DATOS DEL PROVEEDOR", "PROVEEDOR - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                Limpiar_Cajas2();
                cargar("");
 bloqearbtn();
            }
           }
        catch (Exception e) {
            System.err.println(e);
 JOptionPane.showMessageDialog(null, "EL PROVEEDOR NO SE HA INGRESADO CORRECTAMENTE", "PROVEEDOR - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
        } else if(notificacionProveedorExistente()==true) {
                 JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR CORRECTAMENTE SU PROVEEDOR, PORQUE YA HAY UNO EXISTENTE CON ESE NOMBRE","ERROR NOMBRE", JOptionPane.ERROR_MESSAGE);
     Limpiar_Cajas2();

          }   
    }//GEN-LAST:event_generarbtnActionPerformed

boolean  notificacionProveedorExistente(){
   String x="";
   boolean resultado=false;
 String y=cuitTxt.getText();
      try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT (cuit) FROM proveedor WHERE statusbajaprov is null ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                      x = c.rs.getString("cuit");
if(x.equals(y.toString())){
          System.out.println(y);
   resultado=true; 

            return resultado;
            }

            } 
   conn.close();

        }

        catch (Exception e) {
            // System.err.println(e);
            e.printStackTrace();

    }
        return resultado;


  }  
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed

        Date fecha = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);
        int fila = jtProveedor.getSelectedRow();
        int valor = parseInt(jtProveedor.getValueAt(fila, 0).toString());
int respuesta=JOptionPane.showConfirmDialog(this,"¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);
        if(respuesta==JOptionPane.YES_OPTION){
        if (fila >= 0) {
            try {
                c.getConexion();
                c.ps = c.con.prepareStatement("UPDATE proveedor SET statusbajaprov=? WHERE id_proveedor=" + valor);
                c.ps.setString(1, fechaactual);
                int res = c.ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "EL PROVEEDOR FUE DADO DE BAJA", "PROVEEDOR - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    cargar("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "EL PROVEEDOR NO SE PUDO DAR DE BAJA", "PROVEEDOR - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }

        }

} else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void nombreapellidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreapellidoMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_nombreapellidoMouseClicked
    void buscartodo() {
        try {
            c.ps = c.con.prepareStatement("SELECT * FROM proveedor WHERE nombre= ?");
            c.ps.setString(1, nombreapellido.getText());
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                nombreapellido.setText(c.rs.getString("nombre"));
            } else {
                JOptionPane.showMessageDialog(null, "NO EXISTE UN PROVEEDOR CON ESE NOMBRE", "PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");
        }
    }
    private void nombreapellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreapellidoKeyPressed
   activarbtn();
        nombreapellido.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarauto(); //Método que tienes que crearte
                    buscartodo();
                }
            }
            public void keyReleased(KeyEvent e) {
            }
        });
    }//GEN-LAST:event_nombreapellidoKeyPressed

    private void nombreapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreapellidoActionPerformed

    private void jtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProveedorMouseClicked
activarbtneliminar();
    }//GEN-LAST:event_jtProveedorMouseClicked
public static boolean isValidCUITCUIL(String cuit){  
    if (cuit.length() != 13) return false;{
	boolean rv = false;
	int resultado = 0;
	String cuit_nro = cuit.replace("-", "");
	String codes = "6789456789";
	int verificador = Character.getNumericValue(cuit_nro.charAt(cuit_nro.length() - 1));
	int x = 0;	
	while (x < 10)
	{
		int digitoValidador = Integer.parseInt(codes.substring(x, x+1));
		int digito = Integer.parseInt(cuit_nro.substring(x, x+1));
		int digitoValidacion = digitoValidador * digito;
		resultado += digitoValidacion;
		x++;
	}
	resultado = resultado % 11;
	rv = (resultado == verificador);
	return rv;  
}
}
    private void cuitTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuitTxtKeyTyped
isValidCUITCUIL(cuitTxt.getText());
    }//GEN-LAST:event_cuitTxtKeyTyped

    private void cuitTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuitTxtActionPerformed

    }//GEN-LAST:event_cuitTxtActionPerformed

    private void cuitTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuitTxtKeyPressed

    }//GEN-LAST:event_cuitTxtKeyPressed

    private void inicioSesion6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion6ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_inicioSesion6ActionPerformed

    private void Limpiar_Cajas2() {
        nombreapellido.setText(null);
        cuitTxt.setText(null);
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
            java.util.logging.Logger.getLogger(PantallaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaProveedor().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro botonEliminar;
    private javax.swing.JTextField cuitTxt;
    private rsbuttom.RSButtonMetro generarbtn;
    private rsbuttom.RSButtonMetro inicioSesion6;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtProveedor;
    private javax.swing.JTextField nombreapellido;
    // End of variables declaration//GEN-END:variables
}
