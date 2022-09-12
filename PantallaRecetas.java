package SistemaPanificadora;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Santi
 */
public class PantallaRecetas extends javax.swing.JFrame {

    /**
     * Creates new form PantallaRecetas
     */
    String Prod[] = new String[5];
    private Object nombre;
  
    public PantallaRecetas() {
        initComponents();
        AutoCompleteDecorator.decorate(this.jComboBox6);
        AutoCompleteDecorator.decorate(this.jComboBox7);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocationRelativeTo(null);
        NuevaReceta.setSize(590, 440);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons2/Polo2.png")));
    }
void bloquedodtr(){
    jComboBox6.setEnabled(false);
    jComboBox7.setEnabled(false);
    jSpinner2.setEnabled(false);
    rSButtonMetro4.setEnabled(false);
}
void activardodtr(){
    jComboBox6.setEnabled(true);
    jComboBox7.setEnabled(true);
    jSpinner2.setEnabled(true);
    rSButtonMetro4.setEnabled(true);
}
    void recetcombobox() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jSpinner2.setValue(0);
        tabledtr.setSelectionMode(0);
        jTable4.setEnabled(false);
        jTable4.setVisible(false);
    }
void carcarjcombobocrc(){
            Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

            String SQL = "SELECT id_receta,nombre FROM receta where statusbajarc is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                 jComboBox1.addItem(rs.getString("nombre"));

            }

        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
    }
    void cargarprod() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

            String SQL = "SELECT * FROM producto where statusbajaprod is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox2.addItem(rs.getString("descripcion"));

            }

        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
    }
    void cargar(String Valor) {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();

        String[] titulos = {"Codigo", "Ingrediente", "Cantidad", "Unidad medida"};
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
        String rcdt[] = new String[5];
        c.getConexion();
        try {

            String SQL = "SELECT `id_receta`,rc.nombre,`cantidad`,cum.nombre,`statusbajarc` FROM `receta` AS rc JOIN catalogo_unidad_medida cum ON rc.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE `statusbajarc` is null ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

               rcdt[0] = rs.getString("id_receta");
                rcdt[1] = rs.getString("rc.nombre");
                rcdt[2] = rs.getString("cantidad");
                rcdt[3] = rs.getString("cum.nombre");
                //rcdt[4] = rs.getString("id_producto");

                c.model.addRow(rcdt);

                recetavalor.setModel(c.model);
       recetavalor.getColumnModel().getColumn(0).setMaxWidth(0);
  recetavalor.getColumnModel().getColumn(0).setMinWidth(0);
                rectanomb.addItem(rs.getString("rc.nombre"));
            }

        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
    }
void catalogoUnidadMedida(){
     Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

            String SQL = "SELECT * FROM `catalogo_unidad_medida` ORDER BY `id_catalogo_unidad_medida` ASC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
             unimedtxt.addItem(rs.getString("nombre"));

            }

        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
}
    void verreceta() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String[] titulos = {"Codigo", "Nombre", "Rendimiento", "Unidad medida"};
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
        String rc[] = new String[4];
        try {
  
            String SQL = "SELECT id_receta,rc.nombre,cantidad,cum.nombre  AS unidad_medida FROM receta AS rc JOIN catalogo_unidad_medida cum ON rc.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE statusbajarc is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                rc[0] = rs.getString("id_receta");
                rc[1] = rs.getString("nombre");
                rc[2] = rs.getString("cantidad");
                rc[3] = rs.getString("unidad_medida");


                c.model.addRow(rc);
                tabledtr.setModel(c.model);
             
                tabledtr.getColumnModel().getColumn(0).setMaxWidth(0);
  tabledtr.getColumnModel().getColumn(0).setMinWidth(0);

            }

        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
    }
    void cargarDetalleReceta(String valordtr) {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();

        String[] titulos = {"Codigo", "Ingrediente", "Cantidad", "Unidad de medida", "Receta"};
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
        String dtr[] = new String[5];
        c.getConexion();
        try {

            String SQL = "select `id_detalle_receta`,`cantidad`,unidad_medida,`id_receta`,mp.`id_materia_prima`,`statusbajadtrc` from detalle_receta AS dtr JOIN materia_prima mp ON dtr.id_materia_prima =mp.id_materia_prima  where id_detalle_receta LIKE '%" + valordtr + "%' and statusbajadtrc is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                dtr[0] = rs.getString("id_detalle_receta");
                dtr[1] = rs.getString("descripcion");
                dtr[2] = rs.getString("cantidad");
                dtr[3] = rs.getString("unidad_medida");
                dtr[4] = rs.getString("id_receta");

                c.model.addRow(dtr);
                jTable4.setModel(c.model);
                jTable1.setModel(c.model);
  jTable4.removeColumn(   jTable4.getColumnModel().getColumn(0));
  jTable4.removeColumn(   jTable4.getColumnModel().getColumn(4));
  jTable1.removeColumn(   jTable1.getColumnModel().getColumn(0));
  jTable1.removeColumn(   jTable1.getColumnModel().getColumn(4));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        c.closeConexion();
    }
    void cargarDetalleenReceta(String valordtr) {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();

        String[] titulos = {"cod detalle receta", "Ingrediente", "cantidad", "Unidad medida", "receta"};
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
        String dtr[] = new String[5];
        c.getConexion();
        try {

            String SQL = "select * from detalle_receta AS dtr JOIN materia_prima mp ON dtr.id_materia_prima = mp.id_materia_prima  where id_detalle_receta LIKE '%" + valordtr + "%' and statusbajadtrc is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                dtr[0] = rs.getString("id_detalle_receta");
                dtr[1] = rs.getString("descripcion");
                dtr[2] = rs.getString("cantidad");
                dtr[3] = rs.getString("unidad_medida");
                dtr[4] = rs.getString("id_receta");

                c.model.addRow(dtr);
                jTable4.setModel(c.model);

            }
        } catch (Exception e) {
              e.printStackTrace();
        }
        c.closeConexion();
    }

    void carganombreMP() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
     String box[] = new String[2];
        try {

            String SQL = "SELECT mp.id_mp_catalogo AS \"matprima_catalogo\",mp.id_materia_prima,mpc.nombre,`statusbajamp`,mpc.id_mp_catalogo As \"catalogo_mp\" FROM `materia_prima` AS mp JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo WHERE statusbajamp is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
 int valor= rs.getInt("matprima_catalogo");
 int valor2= rs.getInt("catalogo_mp");

if(valor==valor2){

 box[1]= rs.getString("mpc.nombre");


}
           
               jComboBox6.addItem(box[1]);
               
     
                
              
       
              
            }

        } catch (Exception e) {
  e.printStackTrace();  e.printStackTrace();
        }
        c.closeConexion();
    }
    void cargaunidadmedidaMP() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

            String SQL = "SELECT DISTINCT cum.nombre,`statusbajamp` FROM `materia_prima` AS mp JOIN catalogo_unidad_medida cum ON mp.`id_catalogo_unidad_medida`=cum.id_catalogo_unidad_medida WHERE statusbajamp is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

            
                jComboBox7.addItem(rs.getString("cum.nombre"));
            }

        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
    }
void rcPrincipal_eliminatbtn(){
     Conectividad c = new Conectividad();
        Connection conn = c.getConexion();

        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);
        c.getConexion();

        if (respuesta == JOptionPane.YES_OPTION) {

            if ( ordenDeProdEnExistencia() == true) {
    EliminarRecetayDTR();

            }



           if(ordenDeProdEnExistencia() == false) {
                 System.out.println("ERROR");
                 String pedVinculados=cantPedVinculados();
             JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR CORRECTAMENTE SU RECETA, PORQUE HAY "+pedVinculados+ " PEDIDO/S DE ORDEN DE PRODUCCION VINCULADAS A ESA RECETA", "ERROR RECETA", JOptionPane.ERROR_MESSAGE);



        } 



        } 
        if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }
          
}
void btnHome(){
           Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
            this.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        c.closeConexion();
}
void nuevaRc_añadirbtn(){
    float produce = (float) producetxt.getValue();
    if(produce>0){
    Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int id=0;
        // orden de produccion insertar dato
        c.getConexion();
        try {
            String datotexto1 = (String) jComboBox2.getSelectedItem();
            //int id = (int) jComboBox2.getSelectedIndex();
            

            //  int datotexto2 = (int) jComboBox2.getSelectedIndex();
            int unidadmedida = (int) unimedtxt.getSelectedIndex();

            //Inserto datos del valor de la receta

            c.ps = conn.prepareStatement("INSERT INTO receta(nombre,cantidad,id_catalogo_unidad_medida) VALUES (?,?,?)");

            c.ps.setString(1, datotexto1);
            c.ps.setFloat(2, produce);
            c.ps.setInt(3, unidadmedida);

            int res = c.ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "SE PUDO AGREGAR LA RECETA CORRECTAMENTE", "RECETA - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                
                cargar("");
                verreceta();
                recetcombobox();
            } else {
                 JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR LA RECETA CORRECTAMENTE", "RECETA - ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
              e.printStackTrace();

        }
        try{

            c.ps = conn.prepareStatement("SELECT MAX(id_receta) FROM `receta` WHERE statusbajarc is null");

            c.rs =c.ps.executeQuery();
            while(c.rs.next()){
                id=c.rs.getInt(1);
            }
            System.out.println(""+id);
        }catch(Exception e){
             e.printStackTrace();
        }

        try {
            int unidadmedida = (int) unimedtxt.getSelectedIndex();
            String datotexto2 = (String) jComboBox2.getSelectedItem();
            int idprod = (int) jComboBox2.getSelectedIndex();
            // int produce = (int) producetxt.getValue();

            //  int datotexto2 = (int) jComboBox2.getSelectedIndex();
            String unimed = (String) unimedtxt.getSelectedItem();

            //Inserto datos del valor de la receta

            c.ps = conn.prepareStatement("INSERT INTO producto(descripcion,stock,id_catalogo_unidad_medida,id_receta) VALUES (?,?,?,?)");

            c.ps.setString(1, datotexto2);
            c.ps.setFloat(2,0);
            c.ps.setInt(3,unidadmedida);
            c.ps.setInt(4, id);

            c.ps.executeUpdate();
        } catch (Exception e) {
              e.printStackTrace();
        }

        c.closeConexion();
    }else{
          JOptionPane.showMessageDialog(null, "El rendimiento de la receta debe ser mayor a cero", "ERROR", JOptionPane.ERROR_MESSAGE);

    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NuevaReceta = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        inicioSesion5 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima15 = new rsbuttom.RSButtonMetro();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        rSButtonMetro4 = new rsbuttom.RSButtonMetro();
        jSpinner2 = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabledtr = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        producetxt = new javax.swing.JSpinner();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        unimedtxt = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        añadirrecbtn = new rsbuttom.RSButtonMetro();
        ModificarReceta = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        inicioSesion7 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima17 = new rsbuttom.RSButtonMetro();
        jPanel14 = new javax.swing.JPanel();
        MPbotonMateriaPrima2 = new rsbuttom.RSButtonMetro();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        buscarbtn = new rsbuttom.RSButtonMetro();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        nombrerec = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        unimedmodrec = new javax.swing.JComboBox<>();
        modifrecspinner = new javax.swing.JSpinner();
        rectanomb = new javax.swing.JComboBox<>();
        modifrecbtn1 = new rsbuttom.RSButtonMetro();
        VerDetalleReceta = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        inicioSesion6 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima16 = new rsbuttom.RSButtonMetro();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        inicioSesion4 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima14 = new rsbuttom.RSButtonMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        recetavalor = new javax.swing.JTable();
        Eliminarrc = new rsbuttom.RSButtonMetro();
        NuevaRecetaBoton1 = new rsbuttom.RSButtonMetro();
        ModificarBoton1 = new rsbuttom.RSButtonMetro();
        jLabel8 = new javax.swing.JLabel();
        aux = new javax.swing.JTextField();

        NuevaReceta.setMinimumSize(new java.awt.Dimension(1034, 530));
        NuevaReceta.setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(244, 243, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMaximumSize(new java.awt.Dimension(1030, 700));
        jPanel3.setMinimumSize(new java.awt.Dimension(1030, 700));

        jPanel4.setBackground(new java.awt.Color(230, 205, 141));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(230, 205, 141));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 66, 53));

        jLabel19.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel19.setText("Añadir receta");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 42));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Añadir detalle de receta");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, 42));

        jPanel17.setBackground(new java.awt.Color(230, 205, 141));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Añadir detalle de receta");
        jPanel17.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 220, 50));

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

        MPbotonMateriaPrima15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima15.setText("Home");
        MPbotonMateriaPrima15.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima15.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima15.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima15.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima15.setDefaultCapable(false);
        MPbotonMateriaPrima15.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima15.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima15.setIconTextGap(25);
        MPbotonMateriaPrima15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima15, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel17.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 200, -1));

        jTable4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable4);

        jPanel19.setBackground(new java.awt.Color(244, 243, 239));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos para detalle de receta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 0, 14))); // NOI18N
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel22.setText("Ingrese la materia prima:");
        jPanel19.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 40));
        jPanel19.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 310, 10));
        jPanel19.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 310, 10));

        jLabel25.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel25.setText("Ingrese la unidad de medida:");
        jPanel19.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 40));

        jLabel26.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel26.setText("Cantidad:");
        jPanel19.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 30));
        jPanel19.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 310, 10));

        jComboBox6.setEditable(true);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel19.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 140, -1));

        jComboBox7.setEditable(true);
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel19.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 140, 20));

        rSButtonMetro4.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro4.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        rSButtonMetro4.setText("Agregar");
        rSButtonMetro4.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro4.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro4.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro4.setIconTextGap(25);
        rSButtonMetro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro4ActionPerformed(evt);
            }
        });
        jPanel19.add(rSButtonMetro4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 150, 50));

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));
        jPanel19.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 140, -1));

        tabledtr.setAutoCreateRowSorter(true);
        tabledtr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabledtr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabledtrMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabledtr);

        jPanel9.setBackground(new java.awt.Color(244, 243, 239));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos para añadir receta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel4.setText("Nombre:");

        jComboBox2.setEditable(true);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar o Escribir" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel5.setText("¿Cuanto rinde?");

        producetxt.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));

        jLabel12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel12.setText("Ingrese la unidad de medida:");

        unimedtxt.setEditable(true);
        unimedtxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        unimedtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unimedtxtActionPerformed(evt);
            }
        });

        añadirrecbtn.setBackground(new java.awt.Color(244, 243, 239));
        añadirrecbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        añadirrecbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        añadirrecbtn.setText("Añadir");
        añadirrecbtn.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        añadirrecbtn.setColorHover(new java.awt.Color(230, 205, 141));
        añadirrecbtn.setColorNormal(new java.awt.Color(244, 243, 239));
        añadirrecbtn.setColorPressed(new java.awt.Color(244, 237, 210));
        añadirrecbtn.setColorTextNormal(new java.awt.Color(0, 0, 0));
        añadirrecbtn.setDefaultCapable(false);
        añadirrecbtn.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        añadirrecbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        añadirrecbtn.setIconTextGap(25);
        añadirrecbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirrecbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(producetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unimedtxt, 0, 140, Short.MAX_VALUE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(añadirrecbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(producetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unimedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(añadirrecbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NuevaRecetaLayout = new javax.swing.GroupLayout(NuevaReceta.getContentPane());
        NuevaReceta.getContentPane().setLayout(NuevaRecetaLayout);
        NuevaRecetaLayout.setHorizontalGroup(
            NuevaRecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        NuevaRecetaLayout.setVerticalGroup(
            NuevaRecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, Short.MAX_VALUE)
        );

        ModificarReceta.setUndecorated(true);

        jPanel7.setBackground(new java.awt.Color(244, 243, 239));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(230, 205, 141));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel10.setText("Modificación de receta");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, 42));

        jButton4.setBackground(new java.awt.Color(230, 205, 141));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 50));

        jPanel25.setBackground(new java.awt.Color(244, 243, 239));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inicioSesion7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion7.setText("Salir");
        inicioSesion7.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion7.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion7.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion7.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion7.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion7.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion7.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion7.setDefaultCapable(false);
        inicioSesion7.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion7.setIconTextGap(10);
        inicioSesion7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion7ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima17.setText("Home");
        MPbotonMateriaPrima17.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima17.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima17.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima17.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima17.setDefaultCapable(false);
        MPbotonMateriaPrima17.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima17.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima17.setIconTextGap(25);
        MPbotonMateriaPrima17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima17, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 80));

        jPanel14.setBackground(new java.awt.Color(244, 243, 239));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel14.setLayout(null);

        MPbotonMateriaPrima2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Boton-buscar.png"))); // NOI18N
        MPbotonMateriaPrima2.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima2.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima2.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima2.setDefaultCapable(false);
        MPbotonMateriaPrima2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima2.setIconTextGap(25);
        MPbotonMateriaPrima2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima2ActionPerformed(evt);
            }
        });
        jPanel14.add(MPbotonMateriaPrima2);
        MPbotonMateriaPrima2.setBounds(520, 180, 36, 35);
        jPanel14.add(jSeparator1);
        jSeparator1.setBounds(210, 230, 312, 2);

        jPanel7.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jPanel15.setBackground(new java.awt.Color(244, 243, 239));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ingrese una receta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 18))); // NOI18N
        jPanel15.setLayout(null);

        buscarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Boton-buscar.png"))); // NOI18N
        buscarbtn.setColorHover(new java.awt.Color(230, 205, 141));
        buscarbtn.setColorNormal(new java.awt.Color(244, 243, 239));
        buscarbtn.setColorPressed(new java.awt.Color(244, 237, 210));
        buscarbtn.setColorTextNormal(new java.awt.Color(0, 0, 0));
        buscarbtn.setColorTextPressed(new java.awt.Color(0, 0, 0));
        buscarbtn.setDefaultCapable(false);
        buscarbtn.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        buscarbtn.setIconTextGap(25);
        buscarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarbtnActionPerformed(evt);
            }
        });
        jPanel15.add(buscarbtn);
        buscarbtn.setBounds(410, 30, 50, 35);

        jLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel11.setText("Receta:");
        jPanel15.add(jLabel11);
        jLabel11.setBounds(10, 30, 60, 30);

        jLabel15.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel15.setText("Nombre de la receta:");
        jPanel15.add(jLabel15);
        jLabel15.setBounds(10, 80, 150, 30);
        jPanel15.add(jSeparator7);
        jSeparator7.setBounds(10, 170, 390, 10);
        jPanel15.add(nombrerec);
        nombrerec.setBounds(240, 80, 160, 30);

        jLabel16.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel16.setText("Cantidad:");
        jPanel15.add(jLabel16);
        jLabel16.setBounds(10, 130, 170, 30);

        jLabel17.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel17.setText("Unidad de medida:");
        jPanel15.add(jLabel17);
        jLabel17.setBounds(10, 180, 140, 30);
        jPanel15.add(jSeparator9);
        jSeparator9.setBounds(10, 70, 390, 10);
        jPanel15.add(jSeparator10);
        jSeparator10.setBounds(10, 120, 390, 10);

        unimedmodrec.setEditable(true);
        unimedmodrec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel15.add(unimedmodrec);
        unimedmodrec.setBounds(240, 180, 160, 30);
        jPanel15.add(modifrecspinner);
        modifrecspinner.setBounds(240, 130, 160, 30);

        rectanomb.setEditable(true);
        rectanomb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        rectanomb.setToolTipText("");
        jPanel15.add(rectanomb);
        rectanomb.setBounds(240, 30, 160, 30);

        modifrecbtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modifrecbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        modifrecbtn1.setText("Modificar");
        modifrecbtn1.setColorHover(new java.awt.Color(230, 205, 141));
        modifrecbtn1.setColorNormal(new java.awt.Color(244, 243, 239));
        modifrecbtn1.setColorPressed(new java.awt.Color(244, 237, 210));
        modifrecbtn1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        modifrecbtn1.setDefaultCapable(false);
        modifrecbtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        modifrecbtn1.setIconTextGap(25);
        modifrecbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifrecbtn1ActionPerformed(evt);
            }
        });
        jPanel15.add(modifrecbtn1);
        modifrecbtn1.setBounds(490, 200, 130, 55);

        jPanel7.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 660, 270));

        javax.swing.GroupLayout ModificarRecetaLayout = new javax.swing.GroupLayout(ModificarReceta.getContentPane());
        ModificarReceta.getContentPane().setLayout(ModificarRecetaLayout);
        ModificarRecetaLayout.setHorizontalGroup(
            ModificarRecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ModificarRecetaLayout.setVerticalGroup(
            ModificarRecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        VerDetalleReceta.setUndecorated(true);
        VerDetalleReceta.getContentPane().setLayout(null);

        jPanel11.setBackground(new java.awt.Color(230, 205, 141));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel20.setText("Visualización - Detalle de receta");
        jPanel11.add(jLabel20);
        jLabel20.setBounds(110, 20, 360, 40);

        jButton5.setBackground(new java.awt.Color(230, 205, 141));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton5);
        jButton5.setBounds(11, 12, 66, 57);

        jPanel24.setBackground(new java.awt.Color(244, 243, 239));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        MPbotonMateriaPrima16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima16.setText("Home");
        MPbotonMateriaPrima16.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima16.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima16.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima16.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima16.setDefaultCapable(false);
        MPbotonMateriaPrima16.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima16.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima16.setIconTextGap(25);
        MPbotonMateriaPrima16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima16, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.add(jPanel24);
        jPanel24.setBounds(490, 20, 201, 43);

        VerDetalleReceta.getContentPane().add(jPanel11);
        jPanel11.setBounds(1, 1, 700, 80);

        jPanel6.setBackground(new java.awt.Color(244, 243, 239));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 113, 30));

        jLabel14.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel14.setText("Nombre de la receta:");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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
        jScrollPane4.setViewportView(jTable1);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 680, 250));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/Print_icon-icons.com_55835.png"))); // NOI18N
        jButton3.setText("Imprimir Receta");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 160, 30));

        VerDetalleReceta.getContentPane().add(jPanel6);
        jPanel6.setBounds(0, 80, 700, 320);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(734, 505));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(244, 243, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(230, 205, 141));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 36)); // NOI18N
        jLabel1.setText("Recetas");

        jButton1.setBackground(new java.awt.Color(230, 205, 141));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima14, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 70));

        recetavalor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        recetavalor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        recetavalor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recetavalorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(recetavalor);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 630, 200));

        Eliminarrc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Eliminarrc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        Eliminarrc.setText("Eliminar receta");
        Eliminarrc.setColorHover(new java.awt.Color(230, 205, 141));
        Eliminarrc.setColorNormal(new java.awt.Color(244, 243, 239));
        Eliminarrc.setColorPressed(new java.awt.Color(244, 237, 210));
        Eliminarrc.setColorTextNormal(new java.awt.Color(0, 0, 0));
        Eliminarrc.setDefaultCapable(false);
        Eliminarrc.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        Eliminarrc.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        Eliminarrc.setIconTextGap(25);
        Eliminarrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarrcActionPerformed(evt);
            }
        });
        jPanel1.add(Eliminarrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 210, 50));

        NuevaRecetaBoton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NuevaRecetaBoton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/añadirReceta.png"))); // NOI18N
        NuevaRecetaBoton1.setText("Añadir una nueva receta");
        NuevaRecetaBoton1.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        NuevaRecetaBoton1.setColorHover(new java.awt.Color(230, 205, 141));
        NuevaRecetaBoton1.setColorNormal(new java.awt.Color(244, 243, 239));
        NuevaRecetaBoton1.setColorPressed(new java.awt.Color(244, 237, 210));
        NuevaRecetaBoton1.setColorTextHover(new java.awt.Color(0, 0, 0));
        NuevaRecetaBoton1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        NuevaRecetaBoton1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        NuevaRecetaBoton1.setDefaultCapable(false);
        NuevaRecetaBoton1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        NuevaRecetaBoton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        NuevaRecetaBoton1.setIconTextGap(25);
        NuevaRecetaBoton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevaRecetaBoton1ActionPerformed(evt);
            }
        });
        jPanel1.add(NuevaRecetaBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 250, 50));

        ModificarBoton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ModificarBoton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons2/ver.png"))); // NOI18N
        ModificarBoton1.setText("Visualizar detalle de receta");
        ModificarBoton1.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ModificarBoton1.setColorHover(new java.awt.Color(230, 205, 141));
        ModificarBoton1.setColorNormal(new java.awt.Color(244, 243, 239));
        ModificarBoton1.setColorPressed(new java.awt.Color(244, 237, 210));
        ModificarBoton1.setColorTextHover(new java.awt.Color(0, 0, 0));
        ModificarBoton1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        ModificarBoton1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        ModificarBoton1.setDefaultCapable(false);
        ModificarBoton1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        ModificarBoton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        ModificarBoton1.setIconTextGap(10);
        ModificarBoton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarBoton1ActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 241, 50));

        jLabel8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel8.setText("Ingrese el nombre de una receta:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, 30));

        aux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auxActionPerformed(evt);
            }
        });
        aux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                auxKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                auxKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                auxKeyTyped(evt);
            }
        });
        jPanel1.add(aux, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
btnHome();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void EliminarrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarrcActionPerformed
rcPrincipal_eliminatbtn();     
    }//GEN-LAST:event_EliminarrcActionPerformed
boolean ordenDeProdEnExistencia(){
     boolean var=true;
        int fila = recetavalor.getSelectedRow();
        int valor = parseInt(recetavalor.getValueAt(fila, 0).toString());
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

            String SQL = "SELECT p.id_producto,p.descripcion,ordp.id_orden_produccion from orden_de_produccion as ordp JOIN producto p on  ordp.id_producto=p.id_producto where `statusbajaordprod` is null and p.id_producto ="+valor;
           
           
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

                int ordp = rs.getInt("id_orden_produccion");
                    String ordrpString= String.valueOf(rs.getInt("id_orden_produccion"));
                
               
           
                if (ordp > 0) {
               //  System.out.println("soy falso no deberia poder eliminar");
                 
                 var=false;

             break; 
                } 
                
                     
                else if(ordp == 0 || ordrpString.equals("") || ordrpString.isEmpty()){
              //  System.out.println("soy verdadero  deberia poder eliminar");
                    var = true;
             
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
        return var;
 
    }
    
    
    String cantPedVinculados(){
    String  cantPedidos ="";
     int fila = recetavalor.getSelectedRow();
        int valor = parseInt(recetavalor.getValueAt(fila, 0).toString());
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

            String SQL = ("SELECT p.id_producto,p.descripcion,count(ordp.id_orden_produccion)'cantidad Pedidos Vinculados' from orden_de_produccion as ordp JOIN producto p on ordp.id_producto=p.id_producto where `statusbajaordprod` is null and p.id_producto =" + valor);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

           cantPedidos = Integer.toString(rs.getInt("cantidad Pedidos Vinculados"));
               
                
                
                return cantPedidos;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
        return cantPedidos;

    }
    void EliminarRecetayDTR(){
    Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);

        int fila = recetavalor.getSelectedRow();

        int valor = parseInt(recetavalor.getValueAt(fila, 0).toString());

         if (fila >= 0) {

                    try {

                        c.ps = conn.prepareStatement("UPDATE receta SET statusbajarc=? WHERE id_receta=" + valor);
                        c.ps.setString(1, fechaactual);
                        int res = c.ps.executeUpdate();
                        cargar("");
                        if (res > 0) {
                            JOptionPane.showMessageDialog(null, "LA RECETA FUE DADA DE BAJA");

                        }

                    } catch (SQLException ex) {
                         JOptionPane.showConfirmDialog(this, "LA RECETA NO PUDO SER DADA DE BAJA", "RECETAS-ELIMINAR", JOptionPane.WARNING_MESSAGE);
                    }

                }
                try {

                    c.ps = conn.prepareStatement("UPDATE detalle_receta SET statusbajadtrc=? WHERE id_receta=" + valor);
                    c.ps.setString(1, fechaactual);
                    int res = c.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "EL DETALLE DE RECETA FUE DADO DE BAJA");
                        cargar("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "EL DETALLE DE RECETA NO PUDO SER DADO DE BAJA");
                }
                try {

                    c.ps = conn.prepareStatement("UPDATE producto SET statusbajaprod=? WHERE id_receta=" + valor);
                    c.ps.setString(1, fechaactual);
                    int res = c.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "EL DETALLE DE RECETA FUE DADO DE BAJA");
                        cargar("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "EL DETALLE DE RECETA NO PUDO SER DADO DE BAJA");
                }

    }
boolean ingredienteEnExistencia() {
       boolean var = false;
         int fila = recetavalor.getSelectedRow();
         int valor = parseInt(recetavalor.getValueAt(fila, 0).toString());
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {

             String SQL = ("SELECT receta.nombre,COUNT(detalle_receta.id_materia_prima)as 'cantidadMatPrimas' FROM detalle_receta JOIN receta on detalle_receta.id_receta= receta.id_receta WHERE statusbajarc is null and detalle_receta.statusbajadtrc is null  and receta.id_receta="+ valor); 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

               int maTeriasPrimas=rs.getInt("cantidadMatPrimas");
                 String materiasPrimas= String.valueOf(rs.getInt("cantidadMatPrimas"));
               if( maTeriasPrimas==0 || materiasPrimas.equals("")){
               var=true;


               }
               else if(maTeriasPrimas!=0 || !materiasPrimas.equals("")){
                     JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR CORRECTAMENTE SU RECETA, PORQUE HAY UNA O VARIAS MATERIAS PRIMAS VINCULADAS","ERROR RECETA", JOptionPane.ERROR_MESSAGE);
                    }break;
            }


        } catch (Exception e) {
  e.printStackTrace();
        }
        c.closeConexion();
        return var;

  }
//-------------------------------------------------------verificacion de ingrediente en existencia----------------------------------------------

  

    



    private void MPbotonMateriaPrima2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPbotonMateriaPrima2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ModificarReceta.setVisible(false);
        PantallaRecetas recetahome = new PantallaRecetas();
        recetahome.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void auxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auxActionPerformed
        // TODO add your handling code here:
        buscarReceta();

    }//GEN-LAST:event_auxActionPerformed

    private void ModificarBoton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBoton1ActionPerformed
        VerDetalleReceta.setSize(705, 400);
//VerDetalleReceta.pack();
VerDetalleReceta.setLocationRelativeTo(null);
 //verreceta();
 carcarjcombobocrc();
        VerDetalleReceta.setVisible(true);
        this.setVisible(false);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_ModificarBoton1ActionPerformed

    private void NuevaRecetaBoton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevaRecetaBoton1ActionPerformed
  verreceta();
        cargarprod();
           bloquedodtr();
        dispose();
catalogoUnidadMedida();   
//NuevaReceta.setSize(1050, 580);
NuevaReceta.pack();
NuevaReceta.setVisible(true);
     
        
        NuevaReceta.setResizable(false);
        NuevaReceta.setLocationRelativeTo(null);

    }//GEN-LAST:event_NuevaRecetaBoton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        VerDetalleReceta.setVisible(false);
        PantallaRecetas home = new PantallaRecetas();
        home.setVisible(true);
         // home.setSize(734, 540);
        home.pack();
         this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void buscarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarbtnActionPerformed
 Conectividad c = new Conectividad();
Connection conn = c.getConexion();

        int nombrereceta = rectanomb.getSelectedIndex();
        nombrereceta = nombrereceta;
c.getConexion();
        try {
            
            c.ps = conn.prepareStatement("SELECT * FROM receta WHERE id_receta= ?");
            c.ps.setInt(1, nombrereceta);

            c.rs = c.ps.executeQuery();

            if (c.rs.next()) {
                nombrerec.setText(c.rs.getString("nombre"));
                modifrecspinner.setValue(c.rs.getInt("cantidad"));
                unimedmodrec.setSelectedItem(c.rs.getString("unidad_medida"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe una receta con ese nombre");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        c.closeConexion();
    }//GEN-LAST:event_buscarbtnActionPerformed

    private void modifrecbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifrecbtn1ActionPerformed
 Conectividad c = new Conectividad();
Connection conn = c.getConexion();

        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?", "CONFIRMACION CAMBIOS", JOptionPane.YES_NO_OPTION, HEIGHT);

        if (respuesta == JOptionPane.YES_OPTION) {

            Connection con = null;
            String unidadmedida = (String) unimedmodrec.getSelectedItem();

            String recetanom = (String) rectanomb.getSelectedItem();
c.getConexion();
            try {

               

                int contadorreceta = (int) modifrecspinner.getValue();

                c.ps = con.prepareStatement("UPDATE receta SET nombre=?,cantidad=?,unidad_medida=? WHERE nombre=?");

                c.ps.setString(1, nombrerec.getText());
                c.ps.setInt(2, contadorreceta);
                c.ps.setString(3, unidadmedida);
                c.ps.setString(4, recetanom);

                int res = c.ps.executeUpdate();
                cargar("");
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO MODIFICADOS CORRECTAMENTE");

                } else {
                    JOptionPane.showMessageDialog(null, "LA RECETA NO SE HA MODIFICADO CORRECTAMENTE");

                }

               

            } catch (Exception e) {
                System.err.println(e);

            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }
        c.closeConexion();
    }//GEN-LAST:event_modifrecbtn1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void auxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auxKeyReleased
        // TODO add your handling code here:
    
    }//GEN-LAST:event_auxKeyReleased
void buscarReceta() {
        String var=aux.getText();

        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();

        String[] titulos = {"Codigo", "Nombre", "Cantidad", "Unidad medida"};
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
        String rcdt[] = new String[4];
         c.getConexion();
        try {

          String SQL = "SELECT id_receta,rc.nombre,cantidad,cum.nombre AS unidad_medida FROM receta AS rc JOIN catalogo_unidad_medida cum ON rc.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE statusbajarc is null and rc.nombre LIKE '%" + var + "%'";
            Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                rcdt[0] = rs.getString("id_receta");
                rcdt[1] = rs.getString("rc.nombre");
                rcdt[2] = rs.getString("cantidad");
                rcdt[3] = rs.getString("unidad_medida");
                //rcdt[4] = rs.getString("id_producto");

                c.model.addRow(rcdt);

                recetavalor.setModel(c.model);

                  recetavalor.getColumnModel().getColumn(0).setMaxWidth(0);
  recetavalor.getColumnModel().getColumn(0).setMinWidth(0);

            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        c.closeConexion();

    }
    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        String filtro = (String) jComboBox1.getSelectedItem();
       Conectividad c = new Conectividad();
Connection conn = c.getConexion();

        String[] titulos = {"Codigo", "Ingrediente", "Cantidad", "Unidad medida", "Receta"};
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
        String filtrar[] = new String[5];
c.getConexion();
        try {
            int id_receta =0;
        String SQL1 = "select  id_receta,nombre from receta where statusbajarc is null AND nombre LIKE '%" + filtro+ "%' ";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(SQL1);
            while (rs1.next()) {
          id_receta=rs1.getInt("id_receta");

            }
            
            String SQL = "select dtr.id_detalle_receta,mpc.nombre,cantidad,cum.nombre,dtr.id_receta from detalle_receta AS dtr JOIN materia_prima mp ON dtr.id_materia_prima = mp.id_materia_prima JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida JOIN materia_prima_catalogo mpc on mp.id_mp_catalogo=mpc.id_mp_catalogo WHERE `statusbajamp` is null  AND id_receta LIKE '%" + id_receta+ "%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                filtrar[0] = rs.getString("id_detalle_receta");
                filtrar[1] = rs.getString("mpc.nombre");
                filtrar[2] = rs.getString("cantidad");
                filtrar[3] = rs.getString("cum.nombre");
                filtrar[4] = rs.getString("dtr.id_receta");

                c.model.addRow(filtrar);
                jTable1.setModel(c.model);
  jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
  jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
  jTable1.getColumnModel().getColumn(0).setMinWidth(0);
  jTable1.getColumnModel().getColumn(4).setMinWidth(0);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
c.closeConexion();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

   
 //--------------------------------------------------------------------------------------------------------------------------------------------------------
  void ActualizarTablaDetRec(){
     Conectividad c = new Conectividad();
Connection conn = c.getConexion();

            String[] titulos = {"cod detalle receta", "Ingrediente", "cantidad", "Unidad medida", "receta"};
       
        String PR[] = new String[5];
   int filtro = (int) jComboBox1.getSelectedIndex();
       
          c.getConexion();
        
try {   

           
            String SQL = "select * from detalle_receta AS dtr JOIN materia_prima mp ON dtr.id_materia_prima = mp.id_materia_prima  where id_receta =" +filtro;
            Statement stmt = conn.createStatement();
    c.rs = stmt.executeQuery(SQL);
         
    while (c.rs.next()) {
       
               
   
               
             //  model.addRow(filtrar);
                jTable1.setModel(c.model);
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        } 
 c.closeConexion();
    }
    
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------------  
    
    
    
    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:`

    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1KeyPressed

    private void auxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auxKeyTyped
        // TODO add your handling code here:
           
    }//GEN-LAST:event_auxKeyTyped

    private void auxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auxKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_auxKeyPressed

    private void inicioSesion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion4ActionPerformed
        System.exit(0);    // TODO add your handling code here:
    }//GEN-LAST:event_inicioSesion4ActionPerformed

    private void MPbotonMateriaPrima14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima14ActionPerformed
btnHome();
    }//GEN-LAST:event_MPbotonMateriaPrima14ActionPerformed

    
    boolean validarCampoNulo(){
    boolean x=false;
      float nroIngresado= Float.parseFloat(jSpinner2.getValue().toString());
        System.out.println(nroIngresado);
            
    if(nroIngresado==0){
    x=true;
   
    return x;
    }
  
    x=false;
        return x;
    }
    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
   // if(validarCampoNulo()==false){
       
         float cantidad =(Float.parseFloat(jSpinner2.getValue().toString()));

        int fila = tabledtr.getSelectedRow();

        int valor = parseInt(tabledtr.getValueAt(fila, 0).toString());

        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
       String unidadmedida =(String)jComboBox7.getSelectedItem();

        String selectmp =  (String) jComboBox6.getSelectedItem();

        c.getConexion();
           int id_materia_prima =0;
    int unidad_medida=0;  
        try {

       c.ps = conn.prepareStatement("SELECT id_catalogo_unidad_medida,nombre FROM catalogo_unidad_medida WHERE nombre=?");   
       c.ps.setString(1, unidadmedida);
     c.rs= c.ps.executeQuery();
          while (c.rs.next()) {
      unidad_medida=c.rs.getInt("id_catalogo_unidad_medida");
       }
       c.ps = conn.prepareStatement("select mp.id_materia_prima,mp.id_mp_catalogo,mpc.nombre from materia_prima_catalogo AS mpc JOIN materia_prima mp ON mpc.id_mp_catalogo=mp.id_mp_catalogo where mp.statusbajamp is null AND mpc.nombre=?");    
       c.ps.setString(1, selectmp);
       c.rs = c.ps.executeQuery();
         
         while (c.rs.next()) {
            id_materia_prima=c.rs.getInt("id_materia_prima");
          
         
            }
        
        
    
            c.ps = conn.prepareStatement("INSERT INTO detalle_receta(cantidad,id_catalogo_unidad_medida,id_receta,id_materia_prima) VALUES (?,?,?,?)");

            c.ps.setFloat(1, cantidad);
            c.ps.setInt(2, unidad_medida);
            c.ps.setInt(3, valor);
            c.ps.setInt(4, id_materia_prima);

            int res = c.ps.executeUpdate();
            if (res > 0) {
             
                JOptionPane.showMessageDialog(null, "EL DETALLE DE RECETA HA SIDO AGREGADO CORRECTAMENTE", "RECETA-DETALLE RECETA", JOptionPane.INFORMATION_MESSAGE);
                bloquedodtr();
                recetcombobox();
            } else {
                JOptionPane.showConfirmDialog(null, "EL DETALLE DE RECETA NO HA PODIDO SER AGREGADO CORRECTAMENTE", "RECETA-DETALLE RECETA", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e);
   c.closeConexion();
    }
      
        
 /*else if(validarCampoNulo()==true){
JOptionPane.showMessageDialog(null, "EL DETALLE DE RECETA NO HA PODIDO SER AGREGADO DEBIDO AL INGRESO DE UN  VALOR NULO", "RECETA-DETALLE RECETA", JOptionPane.ERROR_MESSAGE);
     }   */  


    }//GEN-LAST:event_rSButtonMetro4ActionPerformed

    private void tabledtrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabledtrMouseClicked
        // TODO add your handling code here:
         activardodtr();
       
 Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = tabledtr.getSelectedRow();
        int filtroa = parseInt(tabledtr.getValueAt(fila, 0).toString());
        int comparar = 0;
        try {

            String SQL1 = "SELECT id_receta FROM `detalle_receta` where id_receta=" + filtroa;
            Statement stmt1 = conn.createStatement();
            ResultSet rss = stmt1.executeQuery(SQL1);
            while (rss.next()) {
                comparar = rss.getInt("id_receta");

            }
            if (comparar > 0) {




       
        jTable4.setEnabled(true);
        jTable4.setVisible(true);
       
       
       

        String[] titulos = {"Codigo", "Ingrediente", "Cantidad", "Unidad medida", "Receta"};
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
        String filtrar[] = new String[5];
        c.getConexion();
        try {

            String SQL = "select `id_detalle_receta`,mpc.nombre,cantidad,cum.nombre,id_receta from detalle_receta AS dtr JOIN materia_prima mp ON dtr.id_materia_prima = mp.id_materia_prima JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo WHERE `statusbajadtrc` is null AND id_receta =" + filtroa;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                filtrar[0] = rs.getString("id_detalle_receta");
                filtrar[1] = rs.getString("mpc.nombre");
                filtrar[2] = rs.getString("cantidad");
                filtrar[3] = rs.getString("cum.nombre");
                filtrar[4] = rs.getString("id_receta");

                c.model.addRow(filtrar);
                jTable4.setModel(c.model);
                jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable4.getColumnModel().getColumn(0).setMinWidth(0);
                jTable4.getColumnModel().getColumn(4).setMaxWidth(0);
                jTable4.getColumnModel().getColumn(4).setMinWidth(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
         } else {
                jTable4.setVisible(false);
            }
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
            e.printStackTrace();
        }
        c.closeConexion();
    }//GEN-LAST:event_tabledtrMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        NuevaReceta.setVisible(false);
        PantallaRecetas recetashome = new PantallaRecetas();
        recetashome.setVisible(true);
        recetashome.pack();
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inicioSesion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion5ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_inicioSesion5ActionPerformed

    private void MPbotonMateriaPrima15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPbotonMateriaPrima15ActionPerformed

    private void inicioSesion6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion6ActionPerformed
        System.exit(0);    // TODO add your handling code here:
    }//GEN-LAST:event_inicioSesion6ActionPerformed

    private void MPbotonMateriaPrima16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima16ActionPerformed
        this.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima16ActionPerformed

    private void recetavalorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recetavalorMouseClicked
        // TODO add your handling code here:
        Eliminarrc.setEnabled(true);
    }//GEN-LAST:event_recetavalorMouseClicked

    private void inicioSesion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioSesion7ActionPerformed

    private void MPbotonMateriaPrima17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPbotonMateriaPrima17ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
            c.getConexion();
               String codigo = (String) jComboBox1.getSelectedItem();
        try {
             
            int id_receta =0;
        String SQL = "select  id_receta,nombre from receta where statusbajarc is null AND nombre LIKE '%" + codigo+ "%' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
          id_receta=rs.getInt("id_receta");

            }
          
       
            JasperReport reporteReceta = null;
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
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void añadirrecbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirrecbtnActionPerformed
        nuevaRc_añadirbtn();
    }//GEN-LAST:event_añadirrecbtnActionPerformed

    private void unimedtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unimedtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unimedtxtActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(PantallaRecetas

.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaRecetas

.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaRecetas

.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaRecetas

.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaRecetas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static rsbuttom.RSButtonMetro Eliminarrc;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima14;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima15;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima16;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima17;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima2;
    private rsbuttom.RSButtonMetro ModificarBoton1;
    private javax.swing.JFrame ModificarReceta;
    private javax.swing.JFrame NuevaReceta;
    public static rsbuttom.RSButtonMetro NuevaRecetaBoton1;
    private javax.swing.JFrame VerDetalleReceta;
    private javax.swing.JTextField aux;
    private rsbuttom.RSButtonMetro añadirrecbtn;
    private rsbuttom.RSButtonMetro buscarbtn;
    private rsbuttom.RSButtonMetro inicioSesion4;
    private rsbuttom.RSButtonMetro inicioSesion5;
    private rsbuttom.RSButtonMetro inicioSesion6;
    private rsbuttom.RSButtonMetro inicioSesion7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private rsbuttom.RSButtonMetro modifrecbtn1;
    private javax.swing.JSpinner modifrecspinner;
    private javax.swing.JTextField nombrerec;
    private javax.swing.JSpinner producetxt;
    private rsbuttom.RSButtonMetro rSButtonMetro4;
    private javax.swing.JTable recetavalor;
    private javax.swing.JComboBox<String> rectanomb;
    private javax.swing.JTable tabledtr;
    private javax.swing.JComboBox<String> unimedmodrec;
    private javax.swing.JComboBox<String> unimedtxt;
    // End of variables declaration//GEN-END:variables

    

}
