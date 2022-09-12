package SistemaPanificadora;
import static SistemaPanificadora.InicioSesion.txtUsuario;
import java.awt.Toolkit;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public class pantallaInicio extends javax.swing.JFrame implements Runnable {
    Conectividad c = new Conectividad();
    Connection conn = c.getConexion();
    InicioSesion is;
    String hora, minutos, segundos;
    Thread hilo;
    public Date hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
        return horaactual;
    }
    public void run() {
        Thread current = Thread.currentThread();
        while (current == hilo) {
            hora();
            labelHora.setText(hora + ":" + minutos + ":" + segundos);
        }
    }
    public pantallaInicio() {
        initComponents();
        String pdesripcion = "";
        String provfecha = "";
        String mpescripcion = "";
        String dtprovcantidad = "";
        cargarUsuarios("");
        hilo = new Thread(this);
        hilo.start();
        labelFecha.setText(fechaActual());
        notificacionLabel();
        setearNombreUsuario();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons2/Polo2.png")));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RecetaPDF = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        guardarordpr1 = new rsbuttom.RSButtonMetro();
        guardarordpr = new rsbuttom.RSButtonMetro();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        MayorCantPedProv = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        guardarordpr2 = new rsbuttom.RSButtonMetro();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        rSButtonMetro3 = new rsbuttom.RSButtonMetro();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        ProductoElaborado = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        verNotificacionesOrdProd = new rsbuttom.RSButtonMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        rSButtonMetro4 = new rsbuttom.RSButtonMetro();
        ProdMasSolicitadoCl = new javax.swing.JFrame();
        jPanel13 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        verNotificacionesOrdProd1 = new rsbuttom.RSButtonMetro();
        nomcl = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        AdminUsuarios = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        registrarUsuarioBtn = new rsbuttom.RSButtonMetro();
        jLabel11 = new javax.swing.JLabel();
        usuarioTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        credencialTxt = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnEliminarUsuario = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima12 = new rsbuttom.RSButtonMetro();
        content = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelFecha = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        verNotificaciones = new rsbuttom.RSButtonMetro();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        MPbotonMateriaPrima1 = new rsbuttom.RSButtonMetro();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        botonRecetas = new rsbuttom.RSButtonMetro();
        materiaPrima = new rsbuttom.RSButtonMetro();
        botonOrdenproduccion = new rsbuttom.RSButtonMetro();
        jSeparator6 = new javax.swing.JSeparator();
        botonAjustes = new rsbuttom.RSButtonMetro();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        botonPedidos1 = new rsbuttom.RSButtonMetro();
        jSeparator7 = new javax.swing.JSeparator();
        rSButtonMetro1 = new rsbuttom.RSButtonMetro();
        rSButtonMetro2 = new rsbuttom.RSButtonMetro();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        RecetaPDF.setMinimumSize(new java.awt.Dimension(797, 399));
        RecetaPDF.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(244, 243, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(797, 399));
        jPanel1.setMinimumSize(new java.awt.Dimension(797, 399));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel3.setText("Recetas:");

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Receta");

        guardarordpr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        guardarordpr1.setColorHover(new java.awt.Color(230, 205, 141));
        guardarordpr1.setColorNormal(new java.awt.Color(244, 243, 239));
        guardarordpr1.setColorPressed(new java.awt.Color(244, 237, 210));
        guardarordpr1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        guardarordpr1.setDefaultCapable(false);
        guardarordpr1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        guardarordpr1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        guardarordpr1.setIconTextGap(25);
        guardarordpr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarordpr1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardarordpr1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarordpr1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        guardarordpr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print-black-printer-tool-symbol_icon-icons.com_54467.png"))); // NOI18N
        guardarordpr.setText("Imprimir receta");
        guardarordpr.setColorHover(new java.awt.Color(230, 205, 141));
        guardarordpr.setColorNormal(new java.awt.Color(244, 243, 239));
        guardarordpr.setColorPressed(new java.awt.Color(244, 237, 210));
        guardarordpr.setColorTextNormal(new java.awt.Color(0, 0, 0));
        guardarordpr.setDefaultCapable(false);
        guardarordpr.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        guardarordpr.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        guardarordpr.setIconTextGap(25);
        guardarordpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarordprActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel20.setText("Rinde:");

        jLabel22.setText("Kg");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardarordpr, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardarordpr, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout RecetaPDFLayout = new javax.swing.GroupLayout(RecetaPDF.getContentPane());
        RecetaPDF.getContentPane().setLayout(RecetaPDFLayout);
        RecetaPDFLayout.setHorizontalGroup(
            RecetaPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RecetaPDFLayout.setVerticalGroup(
            RecetaPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MayorCantPedProv.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        MayorCantPedProv.setTitle("Reporte");
        MayorCantPedProv.setMinimumSize(new java.awt.Dimension(779, 420));
        MayorCantPedProv.setUndecorated(true);
        MayorCantPedProv.getContentPane().setLayout(null);

        jPanel9.setBackground(new java.awt.Color(244, 243, 239));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(230, 205, 141));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        guardarordpr2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        guardarordpr2.setColorHover(new java.awt.Color(230, 205, 141));
        guardarordpr2.setColorNormal(new java.awt.Color(244, 243, 239));
        guardarordpr2.setColorPressed(new java.awt.Color(244, 237, 210));
        guardarordpr2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        guardarordpr2.setDefaultCapable(false);
        guardarordpr2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        guardarordpr2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        guardarordpr2.setIconTextGap(25);
        guardarordpr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarordpr2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Pedidos realizados por un proveedor");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardarordpr2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardarordpr2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(25, 25, 25))
        );

        jPanel9.add(jPanel10);
        jPanel10.setBounds(0, 7, 770, 70);

        jButton1.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print-black-printer-tool-symbol_icon-icons.com_54467.png"))); // NOI18N
        jButton1.setText("Imprimir Pedidos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1);
        jButton1.setBounds(540, 320, 211, 73);

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
        jScrollPane1.setViewportView(jTable1);

        jPanel9.add(jScrollPane1);
        jScrollPane1.setBounds(10, 160, 689, 154);

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel5.setText("Desde:");
        jPanel9.add(jLabel5);
        jLabel5.setBounds(10, 130, 40, 20);

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setMaxSelectableDate(new java.util.Date(253370779309000L));
        jPanel9.add(jDateChooser1);
        jDateChooser1.setBounds(50, 130, 130, 22);

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel6.setText("Hasta:");
        jPanel9.add(jLabel6);
        jLabel6.setBounds(200, 130, 35, 20);

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jPanel9.add(jDateChooser2);
        jDateChooser2.setBounds(240, 130, 151, 22);

        rSButtonMetro3.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro3.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/Boton-buscar.png"))); // NOI18N
        rSButtonMetro3.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro3.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro3.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonMetro3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMetro3MousePressed(evt);
            }
        });
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });
        jPanel9.add(rSButtonMetro3);
        rSButtonMetro3.setBounds(400, 110, 40, 40);

        jLabel14.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel14.setText("Proveedor");
        jPanel9.add(jLabel14);
        jLabel14.setBounds(10, 90, 57, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel9.add(jComboBox2);
        jComboBox2.setBounds(80, 90, 158, 22);

        jLabel23.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel23.setText("jLabel23");
        jPanel9.add(jLabel23);
        jLabel23.setBounds(460, 122, 110, 30);

        MayorCantPedProv.getContentPane().add(jPanel9);
        jPanel9.setBounds(0, 0, 770, 410);

        ProductoElaborado.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProductoElaborado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProductoElaborado.setMinimumSize(new java.awt.Dimension(583, 402));
        ProductoElaborado.setUndecorated(true);
        ProductoElaborado.getContentPane().setLayout(null);

        jPanel11.setBackground(new java.awt.Color(244, 243, 239));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel12.setBackground(new java.awt.Color(230, 205, 141));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setLayout(null);

        jButton5.setBackground(new java.awt.Color(230, 205, 141));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton5);
        jButton5.setBounds(11, 12, 66, 53);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Productos mayormente generados.");
        jPanel12.add(jLabel18);
        jLabel18.setBounds(100, 10, 450, 42);

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
        jPanel12.add(verNotificacionesOrdProd);
        verNotificacionesOrdProd.setBounds(840, 20, 70, 30);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        rSButtonMetro4.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print-black-printer-tool-symbol_icon-icons.com_54467.png"))); // NOI18N
        rSButtonMetro4.setText("Imprimir Reporte");
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rSButtonMetro4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(rSButtonMetro4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        ProductoElaborado.getContentPane().add(jPanel11);
        jPanel11.setBounds(0, 0, 580, 400);

        ProdMasSolicitadoCl.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProdMasSolicitadoCl.setTitle("Pedido mas solicitado");
        ProdMasSolicitadoCl.setMinimumSize(new java.awt.Dimension(700, 410));
        ProdMasSolicitadoCl.setUndecorated(true);
        ProdMasSolicitadoCl.getContentPane().setLayout(null);

        jPanel13.setBackground(new java.awt.Color(244, 243, 239));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(null);

        jButton2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print-black-printer-tool-symbol_icon-icons.com_54467.png"))); // NOI18N
        jButton2.setText("Imprimir Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton2);
        jButton2.setBounds(440, 340, 220, 60);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Boton-buscar.png"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton4);
        jButton4.setBounds(500, 100, 40, 40);

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTable5);

        jPanel13.add(jScrollPane6);
        jScrollPane6.setBounds(20, 150, 640, 172);

        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        jDateChooser3.setMaxSelectableDate(new java.util.Date(253370779291000L));
        jPanel13.add(jDateChooser3);
        jDateChooser3.setBounds(110, 120, 120, 22);

        jLabel15.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel15.setText("Desde");
        jPanel13.add(jLabel15);
        jLabel15.setBounds(60, 120, 40, 22);

        jLabel16.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel16.setText("Hasta");
        jPanel13.add(jLabel16);
        jLabel16.setBounds(300, 120, 40, 22);

        jDateChooser4.setDateFormatString("yyyy-MM-dd");
        jPanel13.add(jDateChooser4);
        jDateChooser4.setBounds(358, 120, 130, 22);

        jPanel14.setBackground(new java.awt.Color(230, 205, 141));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setLayout(null);

        jButton6.setBackground(new java.awt.Color(230, 205, 141));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton6);
        jButton6.setBounds(11, 12, 66, 53);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Pedidos solicitados por un cliente");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel14.add(jLabel7);
        jLabel7.setBounds(200, 10, 330, 42);

        verNotificacionesOrdProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/ios7-bell-outline_icon-icons.com_50335.png"))); // NOI18N
        verNotificacionesOrdProd1.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        verNotificacionesOrdProd1.setColorHover(new java.awt.Color(230, 205, 141));
        verNotificacionesOrdProd1.setColorNormal(new java.awt.Color(244, 243, 239));
        verNotificacionesOrdProd1.setColorPressed(new java.awt.Color(244, 237, 210));
        verNotificacionesOrdProd1.setColorTextHover(new java.awt.Color(0, 0, 0));
        verNotificacionesOrdProd1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        verNotificacionesOrdProd1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        verNotificacionesOrdProd1.setDefaultCapable(false);
        verNotificacionesOrdProd1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        verNotificacionesOrdProd1.setIconTextGap(10);
        verNotificacionesOrdProd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verNotificacionesOrdProd1MouseClicked(evt);
            }
        });
        verNotificacionesOrdProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verNotificacionesOrdProd1ActionPerformed(evt);
            }
        });
        jPanel14.add(verNotificacionesOrdProd1);
        verNotificacionesOrdProd1.setBounds(840, 20, 70, 30);

        jPanel13.add(jPanel14);
        jPanel14.setBounds(0, 0, 700, 70);

        jPanel13.add(nomcl);
        nomcl.setBounds(110, 80, 120, 24);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel13.add(jComboBox4);
        jComboBox4.setBounds(360, 80, 130, 22);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Estado");
        jPanel13.add(jLabel8);
        jLabel8.setBounds(300, 80, 39, 20);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Cliente");
        jPanel13.add(jLabel19);
        jLabel19.setBounds(60, 80, 39, 20);

        jLabel24.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel24.setText("jLabel24");
        jPanel13.add(jLabel24);
        jLabel24.setBounds(560, 120, 110, 22);

        ProdMasSolicitadoCl.getContentPane().add(jPanel13);
        jPanel13.setBounds(0, 0, 700, 410);

        AdminUsuarios.setMinimumSize(new java.awt.Dimension(712, 502));
        AdminUsuarios.setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(244, 243, 239));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(230, 205, 141));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Roboto Light", 1, 36)); // NOI18N
        jLabel9.setText("Administración de usuarios");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(130, 14, 465, 66);

        jButton3.setBackground(new java.awt.Color(230, 205, 141));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);
        jButton3.setBounds(13, 14, 66, 53);

        jPanel5.add(jPanel6);
        jPanel6.setBounds(0, 0, 710, 90);

        listaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(listaUsuarios);

        jPanel5.add(jScrollPane5);
        jScrollPane5.setBounds(10, 110, 680, 130);

        jPanel7.setBackground(new java.awt.Color(244, 243, 239));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registrar un usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        registrarUsuarioBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registrarUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar.png"))); // NOI18N
        registrarUsuarioBtn.setText("Registrar");
        registrarUsuarioBtn.setColorBorde(null);
        registrarUsuarioBtn.setColorHover(new java.awt.Color(230, 205, 141));
        registrarUsuarioBtn.setColorNormal(new java.awt.Color(244, 243, 239));
        registrarUsuarioBtn.setColorPressed(new java.awt.Color(244, 237, 210));
        registrarUsuarioBtn.setColorTextHover(new java.awt.Color(0, 0, 0));
        registrarUsuarioBtn.setColorTextNormal(new java.awt.Color(0, 0, 0));
        registrarUsuarioBtn.setColorTextPressed(new java.awt.Color(0, 0, 0));
        registrarUsuarioBtn.setDefaultCapable(false);
        registrarUsuarioBtn.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        registrarUsuarioBtn.setIconTextGap(25);
        registrarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarUsuarioBtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel11.setText("Ingrese la credencial:");

        jLabel12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel12.setText("Ingrese la contraseña:");

        jLabel13.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel13.setText("Ingrese el usuario:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credencialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registrarUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credencialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registrarUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel7);
        jPanel7.setBounds(10, 250, 440, 240);

        jPanel8.setBackground(new java.awt.Color(244, 243, 239));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Otras opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        btnEliminarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        btnEliminarUsuario.setText("Eliminar usuario");
        btnEliminarUsuario.setColorHover(new java.awt.Color(230, 205, 141));
        btnEliminarUsuario.setColorNormal(new java.awt.Color(244, 243, 239));
        btnEliminarUsuario.setColorPressed(new java.awt.Color(244, 237, 210));
        btnEliminarUsuario.setColorTextNormal(new java.awt.Color(0, 0, 0));
        btnEliminarUsuario.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnEliminarUsuario.setDefaultCapable(false);
        btnEliminarUsuario.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnEliminarUsuario.setIconTextGap(15);
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MPbotonMateriaPrima12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        MPbotonMateriaPrima12.setText("      Volver al home");
        MPbotonMateriaPrima12.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MPbotonMateriaPrima12.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima12.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima12.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima12.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima12.setColorTextPressed(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima12.setDefaultCapable(false);
        MPbotonMateriaPrima12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima12.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima12.setIconTextGap(15);
        MPbotonMateriaPrima12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(MPbotonMateriaPrima12, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MPbotonMateriaPrima12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel8);
        jPanel8.setBounds(460, 250, 240, 240);

        javax.swing.GroupLayout AdminUsuariosLayout = new javax.swing.GroupLayout(AdminUsuarios.getContentPane());
        AdminUsuarios.getContentPane().setLayout(AdminUsuariosLayout);
        AdminUsuariosLayout.setHorizontalGroup(
            AdminUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        AdminUsuariosLayout.setVerticalGroup(
            AdminUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(230, 205, 141));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelFecha.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        labelFecha.setText("Fecha");

        labelHora.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        labelHora.setText("Hora");

        verNotificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/ios7-bell-outline_icon-icons.com_50335.png"))); // NOI18N
        verNotificaciones.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        verNotificaciones.setColorHover(new java.awt.Color(230, 205, 141));
        verNotificaciones.setColorNormal(new java.awt.Color(244, 243, 239));
        verNotificaciones.setColorPressed(new java.awt.Color(244, 237, 210));
        verNotificaciones.setColorTextHover(new java.awt.Color(0, 0, 0));
        verNotificaciones.setColorTextNormal(new java.awt.Color(0, 0, 0));
        verNotificaciones.setColorTextPressed(new java.awt.Color(0, 0, 0));
        verNotificaciones.setDefaultCapable(false);
        verNotificaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        verNotificaciones.setIconTextGap(10);
        verNotificaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verNotificacionesMouseClicked(evt);
            }
        });
        verNotificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verNotificacionesActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/3741756-bussiness-ecommerce-marketplace-onlinestore-store-user_108907.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 712, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(verNotificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelHora))
                    .addComponent(verNotificaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 70));

        jPanel3.setBackground(new java.awt.Color(244, 243, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MPbotonMateriaPrima1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Salir.png"))); // NOI18N
        MPbotonMateriaPrima1.setText("Salir");
        MPbotonMateriaPrima1.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima1.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima1.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima1.setDefaultCapable(false);
        MPbotonMateriaPrima1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima1.setIconTextGap(25);
        MPbotonMateriaPrima1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima1ActionPerformed(evt);
            }
        });
        jPanel3.add(MPbotonMateriaPrima1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 190, 50));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 190, 10));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 190, 20));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 190, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 190, 20));

        botonRecetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Pan.png"))); // NOI18N
        botonRecetas.setText("Recetas");
        botonRecetas.setColorHover(new java.awt.Color(230, 205, 141));
        botonRecetas.setColorNormal(new java.awt.Color(244, 243, 239));
        botonRecetas.setColorPressed(new java.awt.Color(244, 237, 210));
        botonRecetas.setColorTextNormal(new java.awt.Color(0, 0, 0));
        botonRecetas.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonRecetas.setDefaultCapable(false);
        botonRecetas.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        botonRecetas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        botonRecetas.setIconTextGap(25);
        botonRecetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRecetasActionPerformed(evt);
            }
        });
        jPanel3.add(botonRecetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 190, 50));

        materiaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/materia prima.png"))); // NOI18N
        materiaPrima.setText("Materia prima");
        materiaPrima.setColorHover(new java.awt.Color(230, 205, 141));
        materiaPrima.setColorNormal(new java.awt.Color(244, 243, 239));
        materiaPrima.setColorPressed(new java.awt.Color(244, 237, 210));
        materiaPrima.setColorTextNormal(new java.awt.Color(0, 0, 0));
        materiaPrima.setColorTextPressed(new java.awt.Color(0, 0, 0));
        materiaPrima.setDefaultCapable(false);
        materiaPrima.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        materiaPrima.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        materiaPrima.setIconTextGap(25);
        materiaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiaPrimaActionPerformed(evt);
            }
        });
        jPanel3.add(materiaPrima, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, 50));

        botonOrdenproduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lista.png"))); // NOI18N
        botonOrdenproduccion.setText("      Producto");
        botonOrdenproduccion.setColorHover(new java.awt.Color(230, 205, 141));
        botonOrdenproduccion.setColorNormal(new java.awt.Color(244, 243, 239));
        botonOrdenproduccion.setColorPressed(new java.awt.Color(244, 237, 210));
        botonOrdenproduccion.setColorTextNormal(new java.awt.Color(0, 0, 0));
        botonOrdenproduccion.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonOrdenproduccion.setDefaultCapable(false);
        botonOrdenproduccion.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        botonOrdenproduccion.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        botonOrdenproduccion.setIconTextGap(5);
        botonOrdenproduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOrdenproduccionActionPerformed(evt);
            }
        });
        jPanel3.add(botonOrdenproduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 190, 50));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 190, 20));

        botonAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camion.png"))); // NOI18N
        botonAjustes.setText("Proveedor");
        botonAjustes.setColorHover(new java.awt.Color(230, 205, 141));
        botonAjustes.setColorNormal(new java.awt.Color(244, 243, 239));
        botonAjustes.setColorPressed(new java.awt.Color(244, 237, 210));
        botonAjustes.setColorTextNormal(new java.awt.Color(0, 0, 0));
        botonAjustes.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonAjustes.setDefaultCapable(false);
        botonAjustes.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        botonAjustes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        botonAjustes.setIconTextGap(25);
        botonAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAjustesActionPerformed(evt);
            }
        });
        jPanel3.add(botonAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 190, 30));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, 10));

        jLabel2.setFont(new java.awt.Font("Roboto Thin", 1, 24)); // NOI18N
        jLabel2.setText("Bienvenido!");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 150, 30));

        botonPedidos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/control.png"))); // NOI18N
        botonPedidos1.setText("Pedidos");
        botonPedidos1.setColorBorde(null);
        botonPedidos1.setColorHover(new java.awt.Color(230, 205, 141));
        botonPedidos1.setColorNormal(new java.awt.Color(244, 243, 239));
        botonPedidos1.setColorPressed(new java.awt.Color(244, 237, 210));
        botonPedidos1.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonPedidos1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        botonPedidos1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonPedidos1.setDefaultCapable(false);
        botonPedidos1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        botonPedidos1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        botonPedidos1.setIconTextGap(25);
        botonPedidos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPedidos1ActionPerformed(evt);
            }
        });
        jPanel3.add(botonPedidos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, 60));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 190, 20));

        rSButtonMetro1.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro1.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/3741756-bussiness-ecommerce-marketplace-onlinestore-store-user_108907.png"))); // NOI18N
        rSButtonMetro1.setText("Cliente");
        rSButtonMetro1.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro1.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro1.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro1ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonMetro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 200, 40));

        rSButtonMetro2.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro2.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Ordenproduccion.png"))); // NOI18N
        rSButtonMetro2.setText("Orden de producción");
        rSButtonMetro2.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro2.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro2.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro2.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonMetro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 200, 50));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 190, 20));

        content.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 560));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/Polo.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        content.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 780, 560));

        getContentPane().add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuBar1.setBackground(new java.awt.Color(230, 205, 141));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jMenu1.setText("Archivo");

        jMenu3.setText("Generar reporte");

        jMenu4.setText("Pedido cliente");

        jMenuItem1.setText("Pedidos solicitados por un cliente");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenu3.add(jMenu4);

        jMenu5.setText("Receta");

        jMenuItem3.setText("Receta");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenu3.add(jMenu5);

        jMenu7.setText("Producto");

        jMenuItem5.setText("Productos mayormente generados");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenu3.add(jMenu7);

        jMenu8.setText("Pedido proveedor");

        jMenuItem6.setText("Pedidos realizados por un proveedor");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem6);

        jMenu3.add(jMenu8);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Información");

        jMenuItem2.setText("Acerca de:");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Usuarios");

        jMenuItem4.setText("Administración de usuarios");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void setearNombreUsuario() {
        jLabel10.setText(txtUsuario.getText());
    }
    public boolean testeoStockNotif() {
        boolean o = false;
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT id_mp_catalogo,stock,stock_min from materia_prima";
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
            String SQL = "SELECT COUNT(stock) as 'stock' from materia_prima where stock < stock_min and statusbajamp is null ";
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
    void reprodmassol() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Producto", "Cantidad", "Unidad de medida"};
        c.model = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String[] reppdc = new String[3];
        c.getConexion();
        try {
            String SQL = "SELECT estado_de_produccion,p.descripcion, sum(ordpr.cantidad) as cantidad,cum.nombre AS unidad_medida FROM producto AS p JOIN orden_de_produccion ordpr ON p.id_producto = ordpr.id_producto JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where estado_de_produccion=1 GROUP by p.id_producto ORDER BY cantidad DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                reppdc[0] = rs.getString("p.descripcion");
                reppdc[1] = rs.getString("cantidad");
                reppdc[2] = rs.getString("unidad_medida");
                c.model.addRow(reppdc);
                jTable2.setModel(c.model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        c.closeConexion();;
    }
    void ReporPedidoCliente() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();

        String[] titulos = {"Prodcuto", "Fecha", "Cantidad", "Unidad Medida"};
        c.model = new DefaultTableModel(null, titulos){
        @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String[] reppdc = new String[4];
        c.getConexion();
        try {

            String SQL = "SELECT prod.descripcion as 'producto',pc.fecha,SUM(dp.cantidad) as 'cantidad',cum.nombre as 'unidad_medida' FROM pedido_cliente AS pc JOIN cliente c ON pc.id_cliente = c.id_cliente JOIN detalle_pedido dp ON pc.id_pedido_cliente = dp.id_pedido_cliente JOIN producto prod ON dp.id_producto = prod.id_producto JOIN catalogo_unidad_medida cum ON prod.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where fecha BETWEEN '2022-05-05' AND '2022-06-05' AND statusbajapedcl is null GROUP BY(prod.descripcion) ORDER BY(dp.cantidad)DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                reppdc[0] = rs.getString("producto");
                reppdc[1] = rs.getString("fecha");
                reppdc[2] = rs.getString("cantidad");
                reppdc[3] = rs.getString("unidad_medida");
                c.model.addRow(reppdc);
                jTable2.setModel(c.model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        c.closeConexion();;
    }
    void notificacionLabel() {
        if (testeoStockNotif() == true) {
            verNotificaciones.setText(numeroNotificacion());
        }
        if (testeoStockNotif() == false) {
            verNotificaciones.setText("0");
        }
    }
    void cargarproducto() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        try {
            con.getConexion();
            String SQL = "SELECT DISTINCT nombre from receta where statusbajarc is null ";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    void pantallaCliente() {
        PantallaClientes cliente = new PantallaClientes();
        cliente.setSize(714, 530);
        cliente.setResizable(false);
        cliente.setVisible(true);
        this.setVisible(false);
        cliente.cargar("");
        cliente.bloqueravtn();
        cliente.cargarnombre();
    }
    void pantallaOrdenProduccion() {
        PantallaOrdenDeProduccion op = new PantallaOrdenDeProduccion();
        op.setSize(931, 532);
        op.setResizable(false);
        op.setVisible(true);
        op.setLocationRelativeTo(null);
        op.cargarprod();
        op.cargaropsindpp("");
        op.deshabilitarebtn();
        op.notificacionLabel();
        this.setVisible(false);
        PantallaMateriaPrima mp = new PantallaMateriaPrima();
        mp.alertaStock();
    }
    void pantallaProveedor() {
        PantallaProveedor pprov = new PantallaProveedor();
        pprov.setVisible(true);
        pprov.setSize(477, 441);
        pprov.setResizable(false);
        this.setVisible(false);
        pprov.cargar("");
        pprov.setLocationRelativeTo(null);
        pprov.comprobacionID();
        pprov.bloqearbtn();
    }
    void pantallaMp() {
        PantallaMateriaPrima MP = new PantallaMateriaPrima();
        MP.setVisible(true);
        MP.setSize(772, 445);
        MP.setResizable(false);
        this.setVisible(false);  
        MP.cargar("");
        MP.bloquearbtn();
    }
    void pantallaReceta() {
        PantallaRecetas rc = new PantallaRecetas();
        rc.setVisible(true);
        rc.setSize(734, 505);
        rc.setResizable(false);
        this.setVisible(false);
        rc.cargar("");
        rc.Eliminarrc.setEnabled(false);
        rc.carganombreMP();
        rc.cargaunidadmedidaMP();
    }
    void pantallaPedidos() {
        PantallaPedidos pd = new PantallaPedidos();
        pd.setVisible(true);
        pd.setSize(800, 290);//(IZQ,Abajo)
        pd.setResizable(false);
        this.setVisible(false);
    }
    void combobosReportcliente() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        try {
            con.getConexion();
            String SQL = "select DISTINCT cl.nombre FROM detalle_pedido AS dp JOIN pedido_cliente pdc ON dp.id_pedido_cliente=pdc.id_pedido_cliente JOIN cliente cl ON pdc.id_cliente=cl.id_cliente where dp.id_detalle_pedido > 0 GROUP BY cl.nombre; ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                nomcl.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();

    }
    void comboboxreportestatuscl() {
        jComboBox4.addItem("Todos");
        jComboBox4.addItem("Pendiente");
        jComboBox4.addItem("Entregado");
        jComboBox4.addItem("En producción");
    }
    private void MPbotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonSalirActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_MPbotonSalirActionPerformed
    private void MPbotonrecetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonrecetasActionPerformed
        PantallaRecetas rc = new PantallaRecetas();
        rc.setVisible(true);
        this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_MPbotonrecetasActionPerformed
    private void MPbotonMateriaPrima1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima1ActionPerformed
        jLabel10.setText("");
        this.setVisible(false);
        InicioSesion v = new InicioSesion();
        v.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima1ActionPerformed
    private void botonRecetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRecetasActionPerformed
        pantallaReceta();
    }//GEN-LAST:event_botonRecetasActionPerformed
    private void materiaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiaPrimaActionPerformed
        pantallaMp();
    }//GEN-LAST:event_materiaPrimaActionPerformed
    private void botonAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAjustesActionPerformed
        pantallaProveedor();
    }//GEN-LAST:event_botonAjustesActionPerformed
    private void botonOrdenproduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOrdenproduccionActionPerformed
        Producto op = new Producto();
        op.setVisible(true);
        op.setSize(758, 519);
        op.setResizable(false);
        this.setVisible(false);
        op.cargar("");
    }//GEN-LAST:event_botonOrdenproduccionActionPerformed
    private void botonPedidos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPedidos1ActionPerformed
        pantallaPedidos();
    }//GEN-LAST:event_botonPedidos1ActionPerformed
    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
        pantallaCliente();
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed
    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        pantallaOrdenProduccion();
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        pantallaInicio n = new pantallaInicio();
        n.setVisible(false);
        ProdMasSolicitadoCl.setVisible(true);
        ProdMasSolicitadoCl.setSize(700, 410);
        ProdMasSolicitadoCl.setLocationRelativeTo(null);
       jLabel24.setVisible(false);
        combobosReportcliente();
        comboboxreportestatuscl();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(null, "Grupo de desarrolo FSI desarrollado en el instituto Icop 4021 ubicado en la escuela incamulada calle San Martin 1540");
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        pantallaInicio n = new pantallaInicio();
        n.setVisible(false);
        dispose();
        ProductoElaborado.setVisible(true);
        ProductoElaborado.setLocationRelativeTo(null);
        ProductoElaborado.setSize(583, 402);
        reprodmassol();
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        cargarproducto();
        pantallaInicio n = new pantallaInicio();
        n.setVisible(false);
        dispose();
        RecetaPDF.setVisible(true);
        RecetaPDF.setSize(400, 230);
        RecetaPDF.setLocationRelativeTo(null);
        RecetaPDF.setResizable(false);

    }//GEN-LAST:event_jMenuItem3ActionPerformed
    void reporteReceta() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
            c.getConexion();
               String codigo = (String) jComboBox1.getSelectedItem();
        try {
            int id_receta =0;
        String SQL = "select id_receta,nombre from receta where statusbajarc is null AND nombre LIKE '%" + codigo+ "%' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
          id_receta=rs.getInt("id_receta");
            }
            JasperReport reporteReceta =null;
            String path = "C:\\GestionPanificadora\\src\\Reportes\\reporteReceta.jasper";
               Map parametroCodDep = new HashMap();
            parametroCodDep.put("id_receta",id_receta);
            reporteReceta = (JasperReport) JRLoader.loadObjectFromFile(path);        
 JasperPrint jprint = JasperFillManager.fillReport(reporteReceta,parametroCodDep,conn);
            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
  view.setVisible(true);       
        } catch (JRException ex) {
ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaRecetas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void guardarordprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarordprActionPerformed
        reporteReceta();
    }//GEN-LAST:event_guardarordprActionPerformed
    private void guardarordpr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarordpr1ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        pantallaInicio n = new pantallaInicio();
        RecetaPDF.setVisible(false);
        dispose();
        n.setVisible(true);
        n.setLocationRelativeTo(null);
        c.closeConexion();
    }//GEN-LAST:event_guardarordpr1ActionPerformed
    private void verNotificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verNotificacionesActionPerformed
        pantallaInicio n = new pantallaInicio();
        n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesActionPerformed
    private void verNotificacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verNotificacionesMouseClicked
        AlertaStock v = new AlertaStock();
        v.cargar("");
    }//GEN-LAST:event_verNotificacionesMouseClicked
    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
    }//GEN-LAST:event_jMenuItem1MouseClicked
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        pantallaInicio n = new pantallaInicio();
        n.setVisible(false);
        dispose();
        jLabel23.setVisible(false);
        MayorCantPedProv.setSize(771, 410);
        MayorCantPedProv.setLocationRelativeTo(null);
        MayorCantPedProv.setVisible(true);
        cargarProveedor();
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed
    void cargarProveedor() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        try {
            c.ps = conn.prepareStatement("select id_proveedor,nombre from proveedor where statusbajaprov is null ORDER BY id_proveedor ASC");
            c.rs = c.ps.executeQuery();
            while (c.rs.next()) {
                jComboBox2.addItem(c.rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void reporteCantidadPedidosProveedor() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String dia = Integer.toString(jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser1.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(jDateChooser1.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes + "-" + dia);
        String dia1 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes1 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.MONTH) + 1);
        String year1 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.YEAR));
        String fecha1 = (year1 + "-" + mes1 + "-" + dia1);
        c.getConexion();
        String[] titulos = {"fecha", "Ingrediente", "Cantidad", "Unidad de medida"};
        c.model = new DefaultTableModel(null, titulos){    
        @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }             
        };
        String reppedprov[] = new String[4];
        String proveedor = (String) jComboBox2.getSelectedItem();
        try {
            int id_proveedor=0;
                 c.ps = conn.prepareStatement("SELECT id_proveedor,nombre FROM proveedor WHERE nombre=?");   
       c.ps.setString(1, proveedor);
     c.rs= c.ps.executeQuery();
          while (c.rs.next()) {
      id_proveedor=c.rs.getInt("id_proveedor");
       }
            c.ps = conn.prepareStatement("SELECT prov.id_pedido_proveedor, p.nombre, prov.fecha, mpc.nombre, dtprov.cantidad,cum.nombre,prov.control FROM pedido_a_proveedor AS prov JOIN proveedor p ON prov.id_proveedor = p.id_proveedor JOIN detalle_pedido_proveedor dtprov ON prov.id_pedido_proveedor = dtprov.id_pedido_proveedor JOIN materia_prima mp ON dtprov.id_materia_prima = mp.id_materia_prima JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE fecha BETWEEN ?  AND ?  AND p.id_proveedor=?  AND p.statusbajaprov is null");
            c.ps.setString(1, fecha);
            c.ps.setString(2, fecha1);
            c.ps.setInt(3, id_proveedor);
            c.rs = c.ps.executeQuery();
            while (c.rs.next()) {
                reppedprov[0] = c.rs.getString("prov.fecha");
                reppedprov[1] = c.rs.getString("mpc.nombre");
                reppedprov[2] = c.rs.getString("dtprov.cantidad");
                reppedprov[3] = c.rs.getString("cum.nombre");
                c.model.addRow(reppedprov);
                jTable1.setModel(c.model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
    }
    private void rSButtonMetro3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMetro3MouseClicked
jLabel23.setVisible(true);
jLabel23.setText("Buscando...");
        reporteCantidadPedidosProveedor();
        jLabel23.setVisible(false);
    }//GEN-LAST:event_rSButtonMetro3MouseClicked
    void MayorCantidadPedidosProveedor() throws SQLException {
 Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String dia = Integer.toString(jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser1.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(jDateChooser1.getCalendar().get(Calendar.YEAR));
        String fecha1 = (year + "-" + mes + "-" + dia);
        String dia1 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes1 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.MONTH) + 1);
        String year1 = Integer.toString(jDateChooser2.getCalendar().get(Calendar.YEAR));
        String fecha2 = (year1 + "-" + mes1 + "-" + dia1);
        c.getConexion();
        String codigo = (String) jComboBox2.getSelectedItem();
        try {
            int id_proveedor=0;
                 c.ps = conn.prepareStatement("SELECT id_proveedor,nombre FROM proveedor WHERE nombre=?");   
       c.ps.setString(1, codigo);
     c.rs= c.ps.executeQuery();
          while (c.rs.next()) {
      id_proveedor=c.rs.getInt("id_proveedor");
       }
            JasperReport reporteReceta = null;
            String path = "C:\\GestionPanificadora\\src\\Reportes\\reportePedProv.jasper";
            Map parametroCodDep = new HashMap();
            parametroCodDep.put("fecha1", fecha1);
            parametroCodDep.put("fecha2", fecha2);
            parametroCodDep.put("id_proveedor", id_proveedor);
            reporteReceta = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporteReceta, parametroCodDep, conn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            MayorCantidadPedidosProveedor();
        } catch (SQLException ex) {
            Logger.getLogger(pantallaInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    void productoMasElaboradoReporte() {
        c.getConexion();
        try {
            JasperReport reporte = null;
            String path = "C:\\GestionPanificadora\\src\\Reportes\\reporteProdElaborado.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
        productoMasElaboradoReporte();
    }//GEN-LAST:event_rSButtonMetro4ActionPerformed
    void filtroReporteRecetas() {
        String filtro = (String) jComboBox1.getSelectedItem();
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Ingrediente", "Cantidad", "Unidad de medida"};
        c.model = new DefaultTableModel(null, titulos){
        @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String filtrar[] = new String[3];
        c.getConexion();
        try {
               int id_receta =0;
        String SQL1 = "select  id_receta,nombre from receta where statusbajarc is null AND nombre LIKE '%" + filtro+ "%' ";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(SQL1);
            while (rs1.next()) {
          id_receta=rs1.getInt("id_receta");

            }
            String SQL = "select rc.id_receta,rc.cantidad as cuanto_rinde,mpc.nombre,dtr.cantidad,cum.nombre from detalle_receta AS dtr JOIN materia_prima mp ON dtr.id_materia_prima = mp.id_materia_prima JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida JOIN receta rc on dtr.id_receta=rc.id_receta where rc.id_receta=" + id_receta;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                filtrar[0] = rs.getString("mpc.nombre");
                filtrar[1] = rs.getString("dtr.cantidad");
                filtrar[2] = rs.getString("cum.nombre");
                jLabel21.setText(rs.getString("cuanto_rinde"));
                c.model.addRow(filtrar);
                jTable3.setModel(c.model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        c.closeConexion();
    }
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        filtroReporteRecetas();
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    void reportePedidosClientes() {
      String estado=(String) jComboBox4.getSelectedItem();
      if(estado.equals("Todos")){
       String nombre_cliente = (String) nomcl.getSelectedItem();
        String dia = Integer.toString(jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser3.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(jDateChooser3.getCalendar().get(Calendar.YEAR));
        String fecha1 = (year + "-" + mes + "-" + dia);
        String dia1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.MONTH) + 1);
        String year1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.YEAR));
        String fecha2 = (year1 + "-" + mes1 + "-" + dia1);
        c.getConexion();
        try {
            System.out.println(fecha1);
            System.out.println(fecha2);
            JasperReport reportepedprod = null;
            String path = "C:\\GestionPanificadora\\src\\Reportes\\reportePedidoSolicitadoCl2.jasper";
            Map parametroCodDep = new HashMap();
            parametroCodDep.put("fecha1", fecha1);
            parametroCodDep.put("fecha2", fecha2);
            parametroCodDep.put("nombre_cliente", nombre_cliente);
            reportepedprod = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reportepedprod, parametroCodDep, conn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
      }else{
        String nombre_cliente = (String) nomcl.getSelectedItem();
        String dia = Integer.toString(jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(jDateChooser3.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(jDateChooser3.getCalendar().get(Calendar.YEAR));
        String fecha1 = (year + "-" + mes + "-" + dia);
        String dia1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.MONTH) + 1);
        String year1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.YEAR));
        String fecha2 = (year1 + "-" + mes1 + "-" + dia1);
        c.getConexion();
        try {
            System.out.println(fecha1);
            System.out.println(fecha2);
            JasperReport reportepedprod = null;
            String path = "C:\\GestionPanificadora\\src\\Reportes\\reportePedidoSolicitadoCl.jasper";
            Map parametroCodDep = new HashMap();
            parametroCodDep.put("fecha1", fecha1);
            parametroCodDep.put("fecha2", fecha2);
            parametroCodDep.put("estado", estado);
            parametroCodDep.put("nombre_cliente", nombre_cliente);
            reportepedprod = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reportepedprod, parametroCodDep, conn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
      }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reportePedidosClientes();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        dispose();
        AdminUsuarios.setVisible(true);
        AdminUsuarios.setSize(712, 502);
        AdminUsuarios.setLocationRelativeTo(null);
        AdminUsuarios.setResizable(false);

    }//GEN-LAST:event_jMenuItem4ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        AdminUsuarios.setVisible(false);
        pantallaInicio n = new pantallaInicio();
        n.setVisible(true);
        n.setLocationRelativeTo(null);
        c.closeConexion();
    }//GEN-LAST:event_jButton3ActionPerformed
    void registrarUsuarioSistema() {
        if (validacionUsuariosRepetidos() == false) {
            c.getConexion();
            int var = Integer.parseInt(credencialTxt.getText());
            String usuario =usuarioTxt.getText();
            String clave =passwordTxt.getText();
            try {
                c.ps = c.con.prepareStatement("INSERT INTO usuarios (nombre_usuario,password,credencial) VALUES(?,?,?)");
                c.ps.setString(1,usuario);
                c.ps.setString(2, clave);
                c.ps.setInt(3, var);
                int res = c.ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "EL USUARIO FUE INGRESADO CORRECTAMENTE", "USUARIO - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    cargarUsuarios("");
                }
            } catch (Exception e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "EL USUARIO NO SE HA INGRESADO CORRECTAMENTE", "USUARIO - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR CORRECTAMENTE SU USUARIO, PORQUE YA EXISTE UNO CON ESA CREDENCIAL", "ERROR DOCUMENTO", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void registrarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarUsuarioBtnActionPerformed
        registrarUsuarioSistema();
        vaciarCamposRegistroUsuario();
    }//GEN-LAST:event_registrarUsuarioBtnActionPerformed
    void vaciarCamposRegistroUsuario() {
        usuarioTxt.setText("");
        credencialTxt.setText("");
        passwordTxt.setText("");
    }
    boolean validacionUsuariosRepetidos() {
        System.out.println("Credencial Base Dato");
        boolean n = false;
        int x = 0;
        int v = Integer.parseInt(credencialTxt.getText().toString());
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT (credencial) FROM usuarios WHERE statusBajaUser is null ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                x = c.rs.getInt("credencial");
            }
            if (x == v) {
                n = true;
                return n;
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    String usuarioAdmin() {
        String credencialAdmin = "";
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "SELECT `credencial` FROM usuarios where nombre_usuario='admin' AND password = 'admin' AND credencial =333 and `statusBajaUser` is null ";
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                credencialAdmin = c.rs.getString("credencial");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credencialAdmin;
    }
    public void cargarUsuarios(String valor) {
        String[] titulos = {"ID usuario", "Nombre de usuario", "Credencial", "Contraseña"};
        c.model = new DefaultTableModel(null, titulos){
        @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String user[] = new String[4];
        try {
            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM usuarios WHERE statusBajaUser is null";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                user[0] = rs.getString("id_usuario");
                user[1] = rs.getString("nombre_usuario");
                user[2] = rs.getString("credencial");
                user[3] = rs.getString("password");
                c.model.addRow(user);
                listaUsuarios.setModel(c.model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    void eliminarUsuarioSistema() {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);
        int fila = listaUsuarios.getSelectedRow();
        int valor = parseInt(listaUsuarios.getValueAt(fila, 0).toString());
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);
        if (respuesta == JOptionPane.YES_OPTION) {
            if (fila >= 0) {
                try {
                    c.getConexion();
                    c.ps = c.con.prepareStatement("UPDATE usuarios SET statusBajaUser=? WHERE id_usuario=" + valor);
                    c.ps.setString(1, fechaactual);
                    int res = c.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "EL USUARIO FUE DADO DE BAJA", "USUARIO - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        cargarUsuarios("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO DAR DE BAJA EL USUARIO", "USUARIO - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS", "USUARIO - ELIMINACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        eliminarUsuarioSistema();
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed
    private void MPbotonMateriaPrima12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima12ActionPerformed
        dispose();
        AdminUsuarios.setVisible(false);
        pantallaInicio n = new pantallaInicio();
        n.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima12ActionPerformed
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    }//GEN-LAST:event_jComboBox2ActionPerformed
boolean mostrartablapedidoCliente(){
    try {
                c.ps = conn.prepareStatement("select id_pedido_cliente FROM detalle_pedido where id_detalle_pedido > 0");

                c.rs = c.ps.executeQuery();

                while (c.rs.next()) {
int id = c.rs.getInt("id_pedido_cliente");
if(id>0){
    System.out.println("soy mayor a cero "+id);
}
if(id==0  ||  id<0){
    System.out.println("soy menor a cero "+id);
}
      }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        return false;
}
    void buscarProductoMasSolicitadoEntreFechas() {
      jLabel24.setVisible(true);
      jLabel24.setText("Buscando...");
        mostrartablapedidoCliente();
        String selecestado = (String) jComboBox4.getSelectedItem();
        if (selecestado.equals("Todos")) {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            c.getConexion();
            String cliente = (String) nomcl.getSelectedItem();
            String estado = (String) jComboBox4.getSelectedItem();
            String dia = Integer.toString(jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(jDateChooser3.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(jDateChooser3.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes + "-" + dia);
            String dia1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mes1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.MONTH) + 1);
            String year1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.YEAR));
            String fecha1 = (year1 + "-" + mes1 + "-" + dia1);
            String[] titulos = {"Producto", "Fecha", "Cantidad", "Unidad de medida", "Estado"};
            c.model = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
            };
            String[] reppdc = new String[5];
            try {
                c.ps = conn.prepareStatement("SELECT dp.id_detalle_pedido,c.nombre ,pc.estado,prod.descripcion as 'producto',pc.fecha,SUM(dp.cantidad) as 'cantidad',cum.nombre AS 'unidad_medida' FROM pedido_cliente AS pc JOIN cliente c ON pc.id_cliente = c.id_cliente JOIN detalle_pedido dp ON pc.id_pedido_cliente = dp.id_pedido_cliente JOIN producto prod ON dp.id_producto = prod.id_producto JOIN catalogo_unidad_medida cum ON prod.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where pc.fecha BETWEEN ? AND ? AND statusbajapedcl is null AND c.nombre=? GROUP BY(prod.descripcion) ORDER BY(dp.cantidad)DESC");
                c.ps.setString(1, fecha);
                c.ps.setString(2, fecha1);
                c.ps.setString(3, cliente);
                c.rs = c.ps.executeQuery();
                while (c.rs.next()) {
                        reppdc[0] = c.rs.getString("producto");
                        reppdc[1] = c.rs.getString("fecha");
                        reppdc[2] = c.rs.getString("cantidad");
                        reppdc[3] = c.rs.getString("unidad_medida");
                        reppdc[4] = c.rs.getString("pc.estado");
                        c.model.addRow(reppdc);
                        jTable5.setModel(c.model);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            c.getConexion();
            String cliente = (String) nomcl.getSelectedItem();
            String estado = (String) jComboBox4.getSelectedItem();
            String dia = Integer.toString(jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(jDateChooser3.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(jDateChooser3.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes + "-" + dia);
            String dia1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mes1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.MONTH) + 1);
            String year1 = Integer.toString(jDateChooser4.getCalendar().get(Calendar.YEAR));
            String fecha1 = (year1 + "-" + mes1 + "-" + dia1);
            String[] titulos = {"Prodcuto", "Fecha", "Cantidad", "Unidad de Medida", "Estado"};
            c.model = new DefaultTableModel(null, titulos){
                @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
            };
            String[] reppdc = new String[5];
            try {
                c.ps = conn.prepareStatement("SELECT c.nombre ,pc.estado,prod.descripcion as 'producto',pc.fecha,SUM(dp.cantidad) as 'cantidad',cum.nombre AS 'unidad_medida' FROM pedido_cliente AS pc JOIN cliente c ON pc.id_cliente = c.id_cliente JOIN detalle_pedido dp ON pc.id_pedido_cliente = dp.id_pedido_cliente JOIN producto prod ON dp.id_producto = prod.id_producto JOIN catalogo_unidad_medida cum ON prod.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where pc.fecha BETWEEN ? AND ? AND statusbajapedcl is null AND pc.estado=? AND c.nombre=? GROUP BY(prod.descripcion) ORDER BY(dp.cantidad)DESC");
                c.ps.setString(1, fecha);
                c.ps.setString(2, fecha1);
                c.ps.setString(3, estado);
                c.ps.setString(4, cliente);
                c.rs = c.ps.executeQuery();
                while (c.rs.next()) {
                    reppdc[0] = c.rs.getString("producto");
                    reppdc[1] = c.rs.getString("fecha");
                    reppdc[2] = c.rs.getString("cantidad");
                    reppdc[3] = c.rs.getString("unidad_medida");
                    reppdc[4] = c.rs.getString("pc.estado");
                    c.model.addRow(reppdc);
                    jTable5.setModel(c.model);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.closeConexion();
        }
        jLabel24.setVisible(false);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    }//GEN-LAST:event_jButton4ActionPerformed
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        buscarProductoMasSolicitadoEntreFechas();
    }//GEN-LAST:event_jButton4MouseClicked
    private void guardarordpr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarordpr2ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        MayorCantPedProv.setVisible(false);
        dispose();
        pantallaInicio n = new pantallaInicio();
        n.setVisible(true);
        n.setLocationRelativeTo(null);
        c.closeConexion();
    }//GEN-LAST:event_guardarordpr2ActionPerformed
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        ProductoElaborado.setVisible(false);
        dispose();
        pantallaInicio n = new pantallaInicio();
        n.setVisible(true);
        n.setLocationRelativeTo(null);
        this.setVisible(false);
        c.closeConexion();
    }//GEN-LAST:event_jButton5ActionPerformed
    private void verNotificacionesOrdProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verNotificacionesOrdProdMouseClicked
        PantallaOrdenDeProduccion n = new PantallaOrdenDeProduccion();
        n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesOrdProdMouseClicked
    private void verNotificacionesOrdProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verNotificacionesOrdProdActionPerformed
        PantallaOrdenDeProduccion n = new PantallaOrdenDeProduccion();
        n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesOrdProdActionPerformed
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        ProdMasSolicitadoCl.setVisible(false);
        dispose();
        pantallaInicio n = new pantallaInicio();
        n.setVisible(true);
        n.setLocationRelativeTo(null);
        c.closeConexion();
    }//GEN-LAST:event_jButton6ActionPerformed
    private void verNotificacionesOrdProd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verNotificacionesOrdProd1MouseClicked
        PantallaOrdenDeProduccion n = new PantallaOrdenDeProduccion();
        n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesOrdProd1MouseClicked
    private void verNotificacionesOrdProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verNotificacionesOrdProd1ActionPerformed
        PantallaOrdenDeProduccion n = new PantallaOrdenDeProduccion();
        n.botonNotificaciones();
    }//GEN-LAST:event_verNotificacionesOrdProd1ActionPerformed
    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        filtroReporteRecetas();
        cargarproducto();
    }//GEN-LAST:event_jMenuItem3MouseClicked
    private void rSButtonMetro3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMetro3MousePressed
    }//GEN-LAST:event_rSButtonMetro3MousePressed
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
            // System.err.println(e);
            e.printStackTrace();
        }
    }
    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat(" EEEE dd MMMM YYYY");
        return formatoFecha.format(fecha);
    }
    public static String diaActual() {
        return null;
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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SistemaPanificadora.pantallaInicio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame AdminUsuarios;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima1;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima12;
    private javax.swing.JFrame MayorCantPedProv;
    private javax.swing.JFrame ProdMasSolicitadoCl;
    private javax.swing.JFrame ProductoElaborado;
    private javax.swing.JFrame RecetaPDF;
    private rsbuttom.RSButtonMetro botonAjustes;
    private rsbuttom.RSButtonMetro botonOrdenproduccion;
    private rsbuttom.RSButtonMetro botonPedidos1;
    private rsbuttom.RSButtonMetro botonRecetas;
    private rsbuttom.RSButtonMetro btnEliminarUsuario;
    private javax.swing.JPanel content;
    private javax.swing.JTextField credencialTxt;
    private rsbuttom.RSButtonMetro guardarordpr;
    private rsbuttom.RSButtonMetro guardarordpr1;
    private rsbuttom.RSButtonMetro guardarordpr2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    public static javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelHora;
    private javax.swing.JTable listaUsuarios;
    private rsbuttom.RSButtonMetro materiaPrima;
    private javax.swing.JComboBox<String> nomcl;
    private javax.swing.JPasswordField passwordTxt;
    private rsbuttom.RSButtonMetro rSButtonMetro1;
    private rsbuttom.RSButtonMetro rSButtonMetro2;
    private rsbuttom.RSButtonMetro rSButtonMetro3;
    private rsbuttom.RSButtonMetro rSButtonMetro4;
    private rsbuttom.RSButtonMetro registrarUsuarioBtn;
    private javax.swing.JTextField usuarioTxt;
    private rsbuttom.RSButtonMetro verNotificaciones;
    private rsbuttom.RSButtonMetro verNotificacionesOrdProd;
    private rsbuttom.RSButtonMetro verNotificacionesOrdProd1;
    // End of variables declaration//GEN-END:variables
}
