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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
public class PantallaPedidos extends javax.swing.JFrame {
    private Object nombre;
    public PantallaPedidos() {
        SpinnerNumberModel nm = new SpinnerNumberModel();
        initComponents();
        AutoCompleteDecorator.decorate(this.selectprod);
        AutoCompleteDecorator.decorate(this.jComboBox6);
        AutoCompleteDecorator.decorate(this.jComboBox7);
        AutoCompleteDecorator.decorate(this.jComboBox4);
        AutoCompleteDecorator.decorate(this.jComboBox5);
        AutoCompleteDecorator.decorate(this.jComboBox4);
        AutoCompleteDecorator.decorate(this.jComboBox8);
        AutoCompleteDecorator.decorate(this.jComboBox2);
        AutoCompleteDecorator.decorate(this.jComboBox14);
        AutoCompleteDecorator.decorate(this.jComboBox14);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocationRelativeTo(null);
        rSButtonMetro7.setEnabled(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons2/Polo2.png")));
        this.setSize(800, 290);
    }
void bloquemodcl(){
    jComboBox2.setEnabled(false);
        jSpinner1.setEnabled(false);
}
void activarmodcl(){
    jComboBox2.setEnabled(true);
        jSpinner1.setEnabled(true);
}
    void recetmodificarpedcliente() {
        jComboBox6.setSelectedIndex(0);
        jTable6.setVisible(false);
        jComboBox2.setSelectedItem(0);
        jSpinner1.setValue(0);
    }
    void activarmodificarpedcliente() {
        jTable6.setVisible(true);
    }
    void bloquearbtn() {
        generarpedido1.setEnabled(false);
        generarpedido3.setEnabled(false);
        jButton7.setEnabled(false);
        jCheckBox2.setEnabled(false);
        rSButtonMetro2.setEnabled(false);
    }
    void activarbtngenerarcltbl() {
        jCheckBox2.setEnabled(true);
        jButton7.setEnabled(true);
    }
    void activarbtngenerarcl() {
        rSButtonMetro2.setEnabled(true);
    }
    void activarbtngenerar() {
        generarpedido1.setEnabled(true);
    }
    void deshabilitarmodificarpedprov() {
        jComboBox5.setVisible(false);
        jSpinner3.setVisible(false);
    }
    void habilitarmodificarpedprov() {
        jComboBox5.setVisible(true);
        jSpinner3.setVisible(true);
    }
   void deshabilitartablapedprov() {
        jComboBox7.setEnabled(false);
        jSpinner2.setEnabled(false);
        jCheckBox1.setEnabled(false);
        generarpedido3.setEnabled(false);
        jTextField2.setEnabled(false);
    }
    void habilitartablapedprov() {
        jComboBox7.setEnabled(true);
        jSpinner2.setEnabled(true);
        jCheckBox1.setEnabled(true);
        generarpedido3.setEnabled(true);
        jTextField2.setEnabled(true);
    }
    void ocultartablapdc() {
        tablaPedClie.setVisible(false);
    }
    void habilitartablapdc() {
        tablaPedClie.setVisible(true);
    }
    void bloqueoopcionesdtr() {
        jComboBox8.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jSpinField3.setEnabled(false);
        jTextField1.setEnabled(false);
        rSButtonMetro7.setEnabled(false);
        jTable5.setVisible(false);
    }
    void activaropcionesdtr() {
        jComboBox8.setEnabled(true);
        jCheckBox3.setEnabled(true);
        jSpinField3.setEnabled(true);
        jTextField1.setEnabled(true);
        rSButtonMetro7.setEnabled(true);
        jTable5.setVisible(true);
    }
    void bloqueobotonelimienar() {
        rSButtonMetro6.setEnabled(false);
    }
    void activarbotonelimienar() {
        rSButtonMetro6.setEnabled(true);
    }
    void bloqueoordprod() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        int fila = jTable4.getSelectedRow();
        int valor = parseInt(jTable4.getValueAt(fila, 0).toString());
        try {      
            int comparar=0;
             String SQL1 = "select id_pedido_cliente FROM detalle_pedido where id_pedido_cliente="+valor;
            Statement stmt1 = conn.createStatement();
            ResultSet rss = stmt1.executeQuery(SQL1);
            while (rss.next()) {
               comparar = rss.getInt("id_pedido_cliente");
                System.out.println(""+comparar);
            }
            con.getConexion();
            String SQL = "SELECT pc.estado,dp.id_orden_produccion FROM detalle_pedido AS dp JOIN producto p ON dp.id_producto=p.id_producto JOIN pedido_cliente pc ON dp.id_pedido_cliente=pc.id_pedido_cliente where statusbajadt is null and pc.id_pedido_cliente="+comparar;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int resultado = rs.getInt("dp.id_orden_produccion");
                String status = rs.getString("pc.estado");
                if ((resultado==0 )&&status.equals("Pendiente")) {
                    jButton7.setEnabled(true);
                } else {
                    jButton7.setEnabled(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void bloqueopaneldtr() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String check[] = new String[1];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2){
         @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        int fila = jTable1.getSelectedRow();
        int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
        try {
            con.getConexion();
            String SQL = "SELECT control FROM pedido_a_proveedor WHERE  statusbajapedprov is null AND id_pedido_proveedor LIKE '%" + valor + "%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            int resultado = 0;
            while (rs.next()) {
                resultado = rs.getInt("control");
                if (resultado == 1) {
                    jCheckBox1.setSelected(true);
                    jComboBox7.setEnabled(false);
                    jSpinner2.setEnabled(false);
                    generarpedido3.setEnabled(false);
                    generarpedido3.setVisible(false);
                } else {
                    jCheckBox1.setSelected(false);
                    jComboBox7.setEnabled(true);
                    jSpinner2.setEnabled(true);
                    generarpedido3.setEnabled(true);
                    generarpedido3.setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    void bloqueopanelpdc() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String resultado = "";
        String comp = "Entregado";
        String comp2 = "Pendiente";
        String check[] = new String[1];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2);
        int fila = jTable4.getSelectedRow();
        int valor = parseInt(jTable4.getValueAt(fila, 0).toString());
        try {
            con.getConexion();
            String SQL = "SELECT * FROM pedido_cliente WHERE  statusbajapedcl is null AND id_pedido_cliente=" + valor;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                resultado = rs.getString("estado") + resultado;
                if (resultado.equals(comp)) {
                    jCheckBox2.setSelected(true);
                    jCheckBox2.setEnabled(false);
                    jSpinField3.setEnabled(false);
                    jComboBox8.setEnabled(false);
                    rSButtonMetro7.setEnabled(false);
                    jCheckBox3.setEnabled(false);
                } else if (resultado.equals(comp2)) {
                    jCheckBox2.setSelected(false);
                    jCheckBox2.setEnabled(true);
                    jSpinField3.setEnabled(true);
                    jComboBox8.setEnabled(true);
                    rSButtonMetro7.setEnabled(true);
                    jCheckBox3.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    void flitrojboxmodpedprov() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        c.getConexion();
        try {
            String SQL = "SELECT Distinct prov.nombre FROM detalle_pedido_proveedor AS dpp JOIN materia_prima mp ON dpp.id_materia_prima= mp.id_materia_prima JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida JOIN pedido_a_proveedor pap ON dpp.id_pedido_proveedor=pap.id_pedido_proveedor JOIN proveedor prov on pap.id_proveedor=prov.id_proveedor where statusbajadtpedprov is null AND pap.control=0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox4.addItem(rs.getString("prov.nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
    }
    void modificarpedprov() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String filtropedprov = (String) jComboBox4.getSelectedItem();
        String[] titulos = {"Código", "Ingrediente", "Cantidad", "Unidad de medida"};
        con.model = new DefaultTableModel(null, titulos){
         @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        
        };
        String filtropedpro[] = new String[5];
        con.getConexion();
        try {
int id_proveedor=0;
                 con.ps = conn.prepareStatement("SELECT id_proveedor,nombre FROM proveedor WHERE nombre=?");   
       con.ps.setString(1, filtropedprov);
     con.rs= con.ps.executeQuery();
          while (con.rs.next()) {
      id_proveedor=con.rs.getInt("id_proveedor");
       }
            String SQL = "SELECT dpp.id_detalle_pedido_proveedor,mpc.nombre AS descripcion,cantidad,cum.nombre AS unidad_medida,pap.control,prov.nombre FROM detalle_pedido_proveedor AS dpp JOIN materia_prima mp ON dpp.id_materia_prima= mp.id_materia_prima JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida JOIN pedido_a_proveedor pap ON dpp.id_pedido_proveedor=pap.id_pedido_proveedor JOIN proveedor prov on pap.id_proveedor=prov.id_proveedor where statusbajadtpedprov is null AND pap.control=0 and prov.id_proveedor=" + id_proveedor;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
               filtropedpro[0] = rs.getString("dpp.id_detalle_pedido_proveedor");
                filtropedpro[1] = rs.getString("descripcion");
                filtropedpro[2] = rs.getString("cantidad");
                filtropedpro[3] = rs.getString("unidad_medida");
                con.model.addRow(filtropedpro);
                jTable7.setModel(con.model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion();
    }
    void modificadtr() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String filtro = (String) jComboBox6.getSelectedItem();
        String[] titulos = {"Codigo", "Producto", "Cantidad", "Unidad de Medida"};
        con.model = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        
        
        };
        String filtrar[] = new String[4];
        con.getConexion();
        try {
            int idcliente=0;
  con.ps = conn.prepareStatement("SELECT id_cliente,nombre FROM cliente WHERE nombre=?");   
       con.ps.setString(1, filtro);
     con.rs= con.ps.executeQuery();
          while (con.rs.next()) {
      idcliente=con.rs.getInt("id_cliente");
       }
            String SQL = "SELECT dp.id_pedido_cliente,dp.id_detalle_pedido,descripcion,cantidad,statusbajadt,pd.estado,cum.nombre FROM detalle_pedido AS dp JOIN producto p ON dp.id_producto=p.id_producto JOIN pedido_cliente pd ON dp.id_pedido_cliente=pd.id_pedido_cliente JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where statusbajadt is null AND pd.estado='Pendiente'  AND pd.id_cliente="+idcliente;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                filtrar[0] = rs.getString("id_detalle_pedido");
                filtrar[1] = rs.getString("descripcion");
                filtrar[2] = rs.getString("cantidad");
                filtrar[3] = rs.getString("cum.nombre");
                con.model.addRow(filtrar);
                jTable6.setModel(con.model);
                jTable6.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable6.getColumnModel().getColumn(0).setMinWidth(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion();
    }
    void buscarpedidos() {
        String estado = (String) jComboBox9.getSelectedItem();
        if (estado.equals("Todos")) {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            c.getConexion();
            String filtro = (String) jComboBox1.getSelectedItem();
            String status = (String) jComboBox9.getSelectedItem();
            String Pedidos[] = new String[6];
            String[] titulos = {"Cliente", "Producto", "Cantidad", "Unidad de medida", "Estado", "Fecha"};
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
            try {
                int id_cliente = 0;
                String SQL = "select id_cliente,nombre from cliente where statusbajacl is null AND nombre LIKE '%" + filtro + "%' ";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    id_cliente = rs.getInt("id_cliente");
                }
                c.ps = conn.prepareStatement("SELECT pdc.id_pedido_cliente,dtp.cantidad,statusbajadt,pdc.estado,pdc.fecha,statusbajapedcl,cl.nombre,statusbajacl,p.descripcion,cum.nombre FROM pedido_cliente AS pdc JOIN detalle_pedido dtp ON pdc.id_pedido_cliente=dtp.id_pedido_cliente JOIN producto p ON dtp.id_producto=p.id_producto JOIN cliente cl ON pdc.id_cliente= cl.id_cliente JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where cl.id_cliente  LIKE '%" + id_cliente + "%' AND statusbajapedcl is null  ORDER BY pdc.fecha DESC");
                c.rs = c.ps.executeQuery();
                while (c.rs.next()) {
                    Pedidos[0] = c.rs.getString("pdc.id_pedido_cliente");
                    Pedidos[1] = c.rs.getString("p.descripcion");
                    Pedidos[2] = c.rs.getString("dtp.cantidad");
                    Pedidos[3] = c.rs.getString("cum.nombre");
                    Pedidos[4] = c.rs.getString("pdc.estado");
                    Pedidos[5] = c.rs.getString("fecha");
                    c.model.addRow(Pedidos);
                    tablaPedClie.setModel(c.model);
                    tablaPedClie.getColumnModel().getColumn(0).setMaxWidth(0);
                    tablaPedClie.getColumnModel().getColumn(0).setMinWidth(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.closeConexion();
        } else {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            c.getConexion();
            String filtro = (String) jComboBox1.getSelectedItem();
            String status = (String) jComboBox9.getSelectedItem();
            String Pedidos[] = new String[6];
            String[] titulos = {"Cliente", "Producto", "Cantidad", "Unidad de medida", "Estado", "Fecha"};
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
            try {
                int id_cliente = 0;
                String SQL = "select id_cliente,nombre from cliente where statusbajacl is null AND nombre LIKE '%" + filtro + "%' ";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    id_cliente = rs.getInt("id_cliente");
                }
                c.ps = conn.prepareStatement("SELECT pdc.id_pedido_cliente,dtp.cantidad,statusbajadt,pdc.estado,pdc.fecha,statusbajapedcl,cl.nombre,statusbajacl,p.descripcion,cum.nombre FROM pedido_cliente AS pdc JOIN detalle_pedido dtp ON pdc.id_pedido_cliente=dtp.id_pedido_cliente JOIN producto p ON dtp.id_producto=p.id_producto JOIN cliente cl ON pdc.id_cliente= cl.id_cliente JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where pdc.estado LIKE '%" + status + "%' AND cl.id_cliente  LIKE '%" + id_cliente + "%' AND statusbajapedcl is null ORDER BY pdc.fecha DESC");
                c.rs = c.ps.executeQuery();
                while (c.rs.next()) {
                    Pedidos[0] = c.rs.getString("pdc.id_pedido_cliente");
                    Pedidos[1] = c.rs.getString("p.descripcion");
                    Pedidos[2] = c.rs.getString("dtp.cantidad");
                    Pedidos[3] = c.rs.getString("cum.nombre");
                    Pedidos[4] = c.rs.getString("pdc.estado");
                    Pedidos[5] = c.rs.getString("fecha");
                    c.model.addRow(Pedidos);
                    tablaPedClie.setModel(c.model);
                    tablaPedClie.getColumnModel().getColumn(0).setMaxWidth(0);
                    tablaPedClie.getColumnModel().getColumn(0).setMinWidth(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.closeConexion();
        }
    }
    void resercombobox() {
        selectprod.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jTable3.setVisible(false);
        jComboBox14.setSelectedIndex(0);
        jSpinner2.setValue(0);
    }
    void cargarprovped() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String mp[] = new String[8];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2);
        try {
            con.getConexion();
            String SQL = "SELECT * FROM pedido_a_proveedor WHERE  statusbajapedprov is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {

            }
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
            e.printStackTrace();
        }
        con.closeConexion();
    }
    void cargadescripciondtr() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String mp[] = new String[8];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2);
        try {
            con.getConexion();
            String SQL = "select descripcion FROM pedido_cliente AS pdc JOIN detalle_pedido dtp ON pdc.id_pedido_cliente=dtp.id_pedido_cliente JOIN producto p ON dtp.id_producto=p.id_producto JOIN cliente cl ON pdc.id_cliente= cl.id_cliente where   statusbajapedcl is null ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox2.addItem(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion();
    }
    void cargacodigodtr() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String mp[] = new String[8];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2);
        try {
            con.getConexion();
            String SQL = "select DISTINCT(cl.nombre) FROM pedido_cliente AS pdc JOIN detalle_pedido dtp ON pdc.id_pedido_cliente=dtp.id_pedido_cliente JOIN producto p ON dtp.id_producto=p.id_producto JOIN cliente cl ON pdc.id_cliente= cl.id_cliente where statusbajapedcl is null and pdc.estado='Pendiente' ORDER BY pdc.id_cliente";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox6.addItem(rs.getString("cl.nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       con.closeConexion();
    }
    void cargarmp() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String mp[] = new String[8];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2);
        try {
            con.getConexion();
            String SQL = "SELECT DISTINCT mpc.nombre FROM `materia_prima` AS mp JOIN materia_prima_catalogo mpc on mp.id_mp_catalogo=mpc.id_mp_catalogo WHERE statusbajamp is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox7.addItem(rs.getString("mpc.nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    void cargarpedido() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        int filtro = (int) jComboBox1.getSelectedIndex();
        String Pedidos[] = new String[6];
        String[] titulos = {"Producto", "Cantidad", "Unidad Medida", "Estado", "Fecha"};
        con.model = new DefaultTableModel(null, titulos);
        try {
            String SQL = "select pdc.id_pedido_cliente,dtp.cantidad,statusbajadt,pdc.estado,pdc.fecha,statusbajapedcl,cl.nombre,statusbajacl,p.descripcion,cum.nombre FROM pedido_cliente AS pdc JOIN detalle_pedido dtp ON pdc.id_pedido_cliente=dtp.id_pedido_cliente JOIN producto p ON dtp.id_producto=p.id_producto JOIN cliente cl ON pdc.id_cliente= cl.id_cliente JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where  cl.id_cliente LIKE '%" + filtro + "%' AND statusbajapedcl is null GROUP BY pdc.fecha ORDER BY pdc.fecha DESC  ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Pedidos[0] = rs.getString("pdc.id_pedido_cliente");
                Pedidos[1] = rs.getString("p.descripcion");
                Pedidos[2] = rs.getString("dtp.cantidad");
                Pedidos[3] = rs.getString("cum.nombre");
                Pedidos[4] = rs.getString("pdc.estado");
                Pedidos[5] = rs.getString("fecha");
                con.model.addRow(Pedidos);
                tablaPedClie.setModel(con.model);
                tablaPedClie.getColumnModel().getColumn(0).setMaxWidth(0);
                tablaPedClie.getColumnModel().getColumn(0).setMinWidth(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion();
    }
    void cargar(String provalor) {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String pedidosProv[] = new String[4];
        String[] titulos2 = {"Codigo", "Proveedor", "Fecha",};
        con.model = new DefaultTableModel(null, titulos2){
         @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        
        };
        try {
            con.getConexion();
            String SQL = "select id_pedido_proveedor,nombre,fecha,pprov.id_proveedor,control from pedido_a_proveedor AS pprov JOIN proveedor p ON pprov.id_proveedor = p.id_proveedor where id_pedido_proveedor LIKE '%" + provalor + "%' and statusbajapedprov is null AND control=0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                pedidosProv[0] = rs.getString("id_pedido_proveedor");
                pedidosProv[1] = rs.getString("nombre");
                pedidosProv[2] = rs.getString("fecha");
                pedidosProv[3] = rs.getString("control");
                con.model.addRow(pedidosProv);
                jTable1.setModel(con.model);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                con.getConexion();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    void cargardetalleproveedor(String cdtp) {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        try {
            String detalleProv[] = new String[4];
            String[] titulos4 = {"Código", "Ingrediente ", "Cantidad", "Unidad de medida"};
            con.model = new DefaultTableModel(null, titulos4){
             @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
            };
            con.getConexion();
            String SQL = " SELECT id_detalle_pedido_proveedor,mpc.nombre,cantidad,id_pedido_proveedor,cum.nombre FROM detalle_pedido_proveedor AS dpp JOIN materia_prima mp ON dpp.id_materia_prima=mp.id_materia_prima JOIN materia_prima_catalogo mpc on mp.id_materia_prima=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida= cum.id_catalogo_unidad_medida where id_detalle_pedido_proveedor LIKE '%" + cdtp + "%' and statusbajadtpedprov is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                detalleProv[0] = rs.getString("id_detalle_pedido_proveedor");
                detalleProv[1] = rs.getString("mpc.nombre");
                detalleProv[2] = rs.getString("cantidad");
                detalleProv[3] = rs.getString("cum.nombre");
                con.model.addRow(detalleProv);
                jTable3.setModel(con.model);
                jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable3.getColumnModel().getColumn(0).setMinWidth(0);
                con.getConexion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion(); }
    void cargarproducto() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        try {
            con.getConexion();
            String SQL = "SELECT DISTINCT(descripcion) from producto where statusbajaProd is null ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                selectprod.addItem(rs.getString("descripcion"));
                jComboBox8.addItem(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }  con.closeConexion();}
    void cargarcliente() {
        int id = (int) jComboBox3.getSelectedIndex();
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        try {
            con.getConexion();
            String SQL = "SELECT nombre,id_cliente from cliente where statusbajacl is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox3.addItem(rs.getString("nombre"));  }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }  con.closeConexion(); }
    void cargarproveedor() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        try {
            con.getConexion();
            String SQL = "SELECT *from proveedor where statusbajaprov is null  ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox14.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
       con.closeConexion();
    }
    void cargapedidoClienteconDetalleReceta() {
       Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        try {
            String SQL = "SELECT DISTINCT(cl.nombre) FROM detalle_pedido AS dtp JOIN pedido_cliente pdc ON dtp.id_pedido_cliente=pdc.id_pedido_cliente JOIN cliente cl ON pdc.id_cliente=cl.id_cliente WHERE  statusbajapedcl is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("cl.nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    void pedidocl(String pdc) {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        String Pedidos[] = new String[4];
        String[] titulos = {"Codigo", "Cliente", "Estado", "Fecha"};
        con.model = new DefaultTableModel(null, titulos);
        try {
           String SQL = "SELECT  id_pedido_cliente,nombre, estado,fecha,statusbajapedcl FROM pedido_cliente AS pdc JOIN cliente cl ON pdc.id_cliente=cl.id_cliente where id_pedido_cliente LIKE '%" + pdc + "%'  AND statusbajapedcl is null AND estado='Pendiente' || estado='En Produccion'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Pedidos[0] = rs.getString("id_pedido_cliente");
                Pedidos[1] = rs.getString("nombre");
                Pedidos[2] = rs.getString("estado");
                Pedidos[3] = rs.getString("fecha");
                con.model.addRow(Pedidos);
                jTable4.setModel(con.model);
                jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable4.getColumnModel().getColumn(0).setMinWidth(0);
                con.getConexion();  }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    void lecturaegramos() {
        float Cantidades = 0;
        String Medida = "";
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = jTable4.getSelectedRow();
        int filtroa = parseInt(jTable4.getValueAt(fila, 0).toString());
        String Pedidos[] = new String[3];
        float resultado = 0;
        c.getConexion();
        try {
            c.ps = conn.prepareStatement("SELECT `id_detalle_pedido`,`id_pedido_cliente`,`descripcion`,`cantidad`,`statusbajadt`,cum.nombre FROM detalle_pedido AS dp JOIN producto p ON dp.id_producto=p.id_producto JOIN catalogo_unidad_medida cum ON p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where  statusbajadt is null AND id_pedido_cliente=" + filtroa);
            c.rs = c.ps.executeQuery();
            while (c.rs.next()) {
                Cantidades = (c.rs.getFloat("cantidad"));
                Medida = (c.rs.getString("cum.nombre"));
                if (Cantidades < 1.00) {
                    Pedidos[2] = Medida = "Gr";
                } else {
                    Pedidos[2] = Medida = "Kg";
                }
                Pedidos[0] = c.rs.getString("descripcion");
                Pedidos[1] = c.rs.getString("cantidad");
               Pedidos[2] = c.rs.getString(Medida);
                jTable5.setModel(c.model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
    }
    void clienteestado() {
        jComboBox9.addItem("Todos");
        jComboBox9.addItem("En producción");
        jComboBox9.addItem("Entregado");
        jComboBox9.addItem("Pendiente");
    }
    public void detallepdc(String dtpdc) {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        int fila = jTable4.getSelectedRow();
        int filtroa = parseInt(jTable4.getValueAt(fila, 0).toString());
        int comparar = 0;
        try {
            String SQL1 = "select id_detalle_pedido FROM detalle_pedido where id_pedido_cliente="+filtroa;
            Statement stmt1 = conn.createStatement();
            ResultSet rss = stmt1.executeQuery(SQL1);
            while (rss.next()) {
               comparar = rss.getInt("id_detalle_pedido");
            }
            if (comparar > 0) {
                try {
                    String Pedidos[] = new String[5];
                    String[] titulos = {"Codigo","Producto","Cantidad solicitada","Unidad de medida","Codigo op"};
                    con.model = new DefaultTableModel(null, titulos);
                    String SQL = "SELECT id_detalle_pedido,id_pedido_cliente,descripcion,cantidad,statusbajadt,cum.nombre,id_orden_produccion FROM detalle_pedido AS dp JOIN producto p ON dp.id_producto=p.id_producto JOIN catalogo_unidad_medida cum on p.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where id_detalle_pedido LIKE '%" + dtpdc + "%'  AND statusbajadt is null AND id_pedido_cliente=" + filtroa;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(SQL);
                    while (rs.next()) {
                        Pedidos[0] = rs.getString("id_detalle_pedido");
                        Pedidos[1] = rs.getString("descripcion");
                        Pedidos[2] = rs.getString("cantidad");
                        Pedidos[3] = rs.getString("cum.nombre");
                        Pedidos[4] = rs.getString("id_orden_produccion");
                        con.model.addRow(Pedidos);
                        jTable5.setModel(con.model);
                        jTable5.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
                        jTable5.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                        jTable5.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                        jTable5.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                jTable5.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pantallaProveedor = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        inicioSesion3 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima13 = new rsbuttom.RSButtonMetro();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        MPbotonMateriaPrima4 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima3 = new rsbuttom.RSButtonMetro();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        nuevoPedidoProveedor = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        inicioSesion2 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima9 = new rsbuttom.RSButtonMetro();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jComboBox7 = new javax.swing.JComboBox<>();
        jSpinner2 = new javax.swing.JSpinner();
        generarpedido3 = new rsbuttom.RSButtonMetro();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        generarpedido1 = new rsbuttom.RSButtonMetro();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        modificarPedidoProv = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        inicioSesion5 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima15 = new rsbuttom.RSButtonMetro();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        MPbotonMateriaPrima7 = new rsbuttom.RSButtonMetro();
        jLabel11 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        ModificarPedCLiente = new javax.swing.JFrame();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        inicioSesion8 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima11 = new rsbuttom.RSButtonMetro();
        jPanel19 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        modifrpedcliente2 = new rsbuttom.RSButtonMetro();
        NuevoPedClie = new javax.swing.JFrame();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        rSButtonMetro2 = new rsbuttom.RSButtonMetro();
        jLabel37 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        rSButtonMetro8 = new rsbuttom.RSButtonMetro();
        jPanel17 = new javax.swing.JPanel();
        selectprod = new javax.swing.JComboBox<>();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        rSButtonMetro7 = new rsbuttom.RSButtonMetro();
        jTextField1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        jSpinField3 = new javax.swing.JSpinner();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        inicioSesion7 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima10 = new rsbuttom.RSButtonMetro();
        PantallaClientes = new javax.swing.JFrame();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        inicioSesion6 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima16 = new rsbuttom.RSButtonMetro();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaPedClie = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        rSButtonMetro3 = new rsbuttom.RSButtonMetro();
        rSButtonMetro4 = new rsbuttom.RSButtonMetro();
        rSButtonMetro6 = new rsbuttom.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        inicioSesion4 = new rsbuttom.RSButtonMetro();
        MPbotonMateriaPrima14 = new rsbuttom.RSButtonMetro();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        MPbotonMateriaPrima12 = new rsbuttom.RSButtonMetro();
        jPanel15 = new javax.swing.JPanel();
        rSButtonMetro1 = new rsbuttom.RSButtonMetro();
        botonPedidosProveedor = new rsbuttom.RSButtonMetro();

        pantallaProveedor.setUndecorated(true);
        pantallaProveedor.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(230, 205, 141));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setBackground(new java.awt.Color(230, 205, 141));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel12.setText("Administración de pedidos - Proveedor");

        jPanel21.setBackground(new java.awt.Color(244, 243, 239));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inicioSesion3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion3.setText("Salir");
        inicioSesion3.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion3.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion3.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion3.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion3.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion3.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion3.setDefaultCapable(false);
        inicioSesion3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion3.setIconTextGap(10);
        inicioSesion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion3ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima13.setText("Home");
        MPbotonMateriaPrima13.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima13.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima13.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima13.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima13.setDefaultCapable(false);
        MPbotonMateriaPrima13.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima13.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima13.setIconTextGap(25);
        MPbotonMateriaPrima13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima13, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(43, 43, 43)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pantallaProveedor.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 60));

        jPanel5.setBackground(new java.awt.Color(244, 243, 239));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 770, 200));

        jPanel12.setBackground(new java.awt.Color(244, 243, 239));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        MPbotonMateriaPrima4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MPbotonMateriaPrima4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        MPbotonMateriaPrima4.setText("Modificar pedido");
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
        MPbotonMateriaPrima3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Ordenproduccion.png"))); // NOI18N
        MPbotonMateriaPrima3.setText("Generar pedido");
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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160)
                .addComponent(MPbotonMateriaPrima4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MPbotonMateriaPrima3, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(MPbotonMateriaPrima4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 610, 80));

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });
        jComboBox11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox11MouseClicked(evt);
            }
        });
        jPanel5.add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 150, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel4.setText("Seleccione un proveedor de la lista:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 20));

        pantallaProveedor.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 790, 340));

        nuevoPedidoProveedor.setMinimumSize(new java.awt.Dimension(1160, 150));
        nuevoPedidoProveedor.setUndecorated(true);

        jPanel7.setBackground(new java.awt.Color(244, 243, 239));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(230, 205, 141));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setBackground(new java.awt.Color(230, 205, 141));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel5.setText("Nuevo pedido - Proveedor");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(244, 243, 239));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inicioSesion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion2.setText("Salir");
        inicioSesion2.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion2.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion2.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion2.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion2.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion2.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion2.setDefaultCapable(false);
        inicioSesion2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion2.setIconTextGap(10);
        inicioSesion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion2ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima9.setText("Home");
        MPbotonMateriaPrima9.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima9.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima9.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima9.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima9.setDefaultCapable(false);
        MPbotonMateriaPrima9.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima9.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima9.setIconTextGap(25);
        MPbotonMateriaPrima9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(88, 88, 88))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(197, 197, 197)))
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel8);
        jPanel8.setBounds(0, 0, 1140, 70);

        jPanel14.setBackground(new java.awt.Color(244, 243, 239));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalle del pedido de un proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N
        jPanel14.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel6.setText("Seleccione un ingrediente:");
        jPanel14.add(jLabel6);
        jLabel6.setBounds(10, 30, 180, 30);

        jLabel16.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel16.setText("Ingrese la cantidad:");
        jPanel14.add(jLabel16);
        jLabel16.setBounds(10, 80, 150, 30);
        jPanel14.add(jSeparator4);
        jSeparator4.setBounds(20, 70, 440, 10);

        jComboBox7.setEditable(true);
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar o escribir" }));
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });
        jComboBox7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox7MouseClicked(evt);
            }
        });
        jPanel14.add(jComboBox7);
        jComboBox7.setBounds(190, 30, 160, 30);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));
        jPanel14.add(jSpinner2);
        jSpinner2.setBounds(160, 80, 60, 30);

        generarpedido3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        generarpedido3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        generarpedido3.setText("Generar detalle del pedido");
        generarpedido3.setColorHover(new java.awt.Color(230, 205, 141));
        generarpedido3.setColorNormal(new java.awt.Color(244, 243, 239));
        generarpedido3.setColorPressed(new java.awt.Color(244, 237, 210));
        generarpedido3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        generarpedido3.setDefaultCapable(false);
        generarpedido3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        generarpedido3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        generarpedido3.setIconTextGap(25);
        generarpedido3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generarpedido3MouseClicked(evt);
            }
        });
        generarpedido3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarpedido3ActionPerformed(evt);
            }
        });
        jPanel14.add(generarpedido3);
        generarpedido3.setBounds(310, 120, 240, 50);

        jCheckBox1.setBackground(new java.awt.Color(244, 243, 239));
        jCheckBox1.setText("Verificado");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel14.add(jCheckBox1);
        jCheckBox1.setBounds(240, 80, 79, 25);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(244, 243, 239));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel14.add(jTextField2);
        jTextField2.setBounds(330, 80, 30, 30);

        jPanel7.add(jPanel14);
        jPanel14.setBounds(560, 240, 580, 180);

        jPanel18.setBackground(new java.awt.Color(244, 243, 239));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nuevo pedido de un proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N
        jPanel18.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel15.setText("Seleccione el proveedor:");
        jPanel18.add(jLabel15);
        jLabel15.setBounds(30, 70, 180, 30);

        jComboBox14.setEditable(true);
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });
        jPanel18.add(jComboBox14);
        jComboBox14.setBounds(220, 70, 170, 30);

        generarpedido1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        generarpedido1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        generarpedido1.setText("Generar pedido");
        generarpedido1.setColorHover(new java.awt.Color(230, 205, 141));
        generarpedido1.setColorNormal(new java.awt.Color(244, 243, 239));
        generarpedido1.setColorPressed(new java.awt.Color(244, 237, 210));
        generarpedido1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        generarpedido1.setDefaultCapable(false);
        generarpedido1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        generarpedido1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        generarpedido1.setIconTextGap(25);
        generarpedido1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generarpedido1MouseClicked(evt);
            }
        });
        generarpedido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarpedido1ActionPerformed(evt);
            }
        });
        jPanel18.add(generarpedido1);
        generarpedido1.setBounds(330, 120, 190, 50);

        jPanel7.add(jPanel18);
        jPanel18.setBounds(10, 240, 540, 180);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jTable1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jTable1HierarchyChanged(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jPanel7.add(jScrollPane3);
        jScrollPane3.setBounds(10, 80, 540, 150);

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable3);

        jPanel7.add(jScrollPane5);
        jScrollPane5.setBounds(560, 80, 580, 150);

        nuevoPedidoProveedor.getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        modificarPedidoProv.setUndecorated(true);
        modificarPedidoProv.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(244, 243, 239));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(230, 205, 141));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setBackground(new java.awt.Color(230, 205, 141));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Modificación de pedidos - Proveedor");

        jPanel28.setBackground(new java.awt.Color(244, 243, 239));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima15, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 70));

        jComboBox4.setEditable(true);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar" }));
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
        jPanel6.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 140, 40));

        jLabel8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel8.setText("Código del pedido:");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 82, 170, 30));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable7);

        jPanel6.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 130, 650, 140));

        jPanel29.setBackground(new java.awt.Color(244, 243, 239));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Modificacion de un pedido"));

        jLabel10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel10.setText("Ingrediente:");

        MPbotonMateriaPrima7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MPbotonMateriaPrima7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        MPbotonMateriaPrima7.setText("Guardar cambios");
        MPbotonMateriaPrima7.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima7.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima7.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima7.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima7.setDefaultCapable(false);
        MPbotonMateriaPrima7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima7.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima7.setIconTextGap(10);
        MPbotonMateriaPrima7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MPbotonMateriaPrima7MouseClicked(evt);
            }
        });
        MPbotonMateriaPrima7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima7ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel11.setText("Cantidad:");

        jComboBox5.setEditable(true);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar" }));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                        .addComponent(MPbotonMateriaPrima7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MPbotonMateriaPrima7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 650, 130));

        modificarPedidoProv.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 450));

        ModificarPedCLiente.setMaximumSize(new java.awt.Dimension(696, 446));
        ModificarPedCLiente.setMinimumSize(new java.awt.Dimension(696, 446));
        ModificarPedCLiente.setUndecorated(true);

        jPanel10.setBackground(new java.awt.Color(244, 243, 239));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(null);

        jPanel16.setBackground(new java.awt.Color(230, 205, 141));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton9.setBackground(new java.awt.Color(230, 205, 141));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel19.setText("Modificación de pedidos - Clientes ");

        jPanel32.setBackground(new java.awt.Color(244, 243, 239));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inicioSesion8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salirX_2.png"))); // NOI18N
        inicioSesion8.setText("Salir");
        inicioSesion8.setColorBorde(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inicioSesion8.setColorHover(new java.awt.Color(230, 205, 141));
        inicioSesion8.setColorNormal(new java.awt.Color(244, 243, 239));
        inicioSesion8.setColorPressed(new java.awt.Color(244, 237, 210));
        inicioSesion8.setColorTextHover(new java.awt.Color(0, 0, 0));
        inicioSesion8.setColorTextNormal(new java.awt.Color(0, 0, 0));
        inicioSesion8.setColorTextPressed(new java.awt.Color(0, 0, 0));
        inicioSesion8.setDefaultCapable(false);
        inicioSesion8.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        inicioSesion8.setIconTextGap(10);
        inicioSesion8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesion8ActionPerformed(evt);
            }
        });

        MPbotonMateriaPrima11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima11.setText("Home");
        MPbotonMateriaPrima11.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima11.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima11.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima11.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima11.setDefaultCapable(false);
        MPbotonMateriaPrima11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima11.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima11.setIconTextGap(25);
        MPbotonMateriaPrima11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(23, 23, 23))))
        );

        jPanel10.add(jPanel16);
        jPanel16.setBounds(0, -3, 700, 80);

        jPanel19.setBackground(new java.awt.Color(244, 243, 239));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });
        jPanel19.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel21.setText("Seleccione un cliente de la lista:");
        jPanel19.add(jLabel21);
        jLabel21.setBounds(10, 30, 240, 30);

        jComboBox6.setEditable(true);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jComboBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox6MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBox6MousePressed(evt);
            }
        });
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel19.add(jComboBox6);
        jComboBox6.setBounds(260, 30, 150, 30);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable6);

        jPanel19.add(jScrollPane8);
        jScrollPane8.setBounds(10, 80, 620, 110);

        jComboBox2.setEditable(true);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jPanel19.add(jComboBox2);
        jComboBox2.setBounds(110, 220, 140, 30);
        jPanel19.add(jSpinner1);
        jSpinner1.setBounds(110, 270, 140, 30);

        jLabel7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel7.setText("Producto:");
        jPanel19.add(jLabel7);
        jLabel7.setBounds(20, 220, 80, 30);

        jLabel9.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel9.setText("Cantidad:");
        jPanel19.add(jLabel9);
        jLabel9.setBounds(30, 270, 80, 30);

        modifrpedcliente2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modifrpedcliente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        modifrpedcliente2.setText("Modificar");
        modifrpedcliente2.setColorHover(new java.awt.Color(230, 205, 141));
        modifrpedcliente2.setColorNormal(new java.awt.Color(244, 243, 239));
        modifrpedcliente2.setColorPressed(new java.awt.Color(244, 237, 210));
        modifrpedcliente2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        modifrpedcliente2.setColorTextPressed(new java.awt.Color(0, 0, 0));
        modifrpedcliente2.setDefaultCapable(false);
        modifrpedcliente2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        modifrpedcliente2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        modifrpedcliente2.setIconTextGap(25);
        modifrpedcliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifrpedcliente2ActionPerformed(evt);
            }
        });
        jPanel19.add(modifrpedcliente2);
        modifrpedcliente2.setBounds(480, 280, 150, 50);

        jPanel10.add(jPanel19);
        jPanel19.setBounds(10, 90, 660, 340);

        javax.swing.GroupLayout ModificarPedCLienteLayout = new javax.swing.GroupLayout(ModificarPedCLiente.getContentPane());
        ModificarPedCLiente.getContentPane().setLayout(ModificarPedCLienteLayout);
        ModificarPedCLienteLayout.setHorizontalGroup(
            ModificarPedCLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );
        ModificarPedCLienteLayout.setVerticalGroup(
            ModificarPedCLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        NuevoPedClie.setTitle("Nuevo Pedido Cliente");
        NuevoPedClie.setMinimumSize(new java.awt.Dimension(980, 572));
        NuevoPedClie.setUndecorated(true);
        NuevoPedClie.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(244, 243, 239));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel24.setBackground(new java.awt.Color(244, 243, 239));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ingrese los datos del nuevo pedido:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 18))); // NOI18N

        jComboBox3.setEditable(true);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        rSButtonMetro2.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        rSButtonMetro2.setText("Generar pedido");
        rSButtonMetro2.setColorBorde(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonMetro2.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro2.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro2.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro2.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel37.setText("Cliente:");

        jCheckBox2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jCheckBox2.setText("Marcar si el producto se encuentra entregado");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBox2MousePressed(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jButton7.setText("Generar orden de producción");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        rSButtonMetro8.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N
        rSButtonMetro8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/usuario_1.png"))); // NOI18N
        rSButtonMetro8.setText("Nuevo cliente");
        rSButtonMetro8.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro8.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro8.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro8.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonMetro8, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(rSButtonMetro2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addGap(14, 14, 14)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSButtonMetro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSButtonMetro8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(244, 243, 239));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ingrese el detalle de pedido:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 18))); // NOI18N

        selectprod.setEditable(true);
        selectprod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));

        jLabel28.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Cantidad:");

        jComboBox8.setEditable(true);
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });
        jComboBox8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox8MouseClicked(evt);
            }
        });
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        rSButtonMetro7.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro7.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CheckChico.png"))); // NOI18N
        rSButtonMetro7.setText("Generar detalle pedido");
        rSButtonMetro7.setAlignmentY(1.0F);
        rSButtonMetro7.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro7.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro7.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro7.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro7.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro7ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(244, 243, 239));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Unidad de medida:");

        jCheckBox3.setBackground(new java.awt.Color(244, 243, 239));
        jCheckBox3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jCheckBox3.setText("Unidad en GR");
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseClicked(evt);
            }
        });
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Producto:");

        jSpinField3.setModel(new javax.swing.SpinnerNumberModel(1.0f, null, null, 1.0f));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator26, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(selectprod, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addContainerGap())
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox8, 0, 140, Short.MAX_VALUE)
                            .addComponent(jSpinField3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jCheckBox3)
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonMetro7, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox3)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(rSButtonMetro7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(317, 317, 317)
                .addComponent(selectprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable4);

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
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        NuevoPedClie.getContentPane().add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 980, 490));

        jPanel11.setBackground(new java.awt.Color(230, 205, 141));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel17.setText("Nuevo pedido - Clientes");

        jButton5.setBackground(new java.awt.Color(230, 205, 141));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel31.setBackground(new java.awt.Color(244, 243, 239));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        MPbotonMateriaPrima10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/homeChico.png"))); // NOI18N
        MPbotonMateriaPrima10.setText("Home");
        MPbotonMateriaPrima10.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima10.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima10.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima10.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima10.setDefaultCapable(false);
        MPbotonMateriaPrima10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima10.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima10.setIconTextGap(25);
        MPbotonMateriaPrima10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        NuevoPedClie.getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        PantallaClientes.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        PantallaClientes.setTitle("Gestion  pedidos cliente");
        PantallaClientes.setMinimumSize(new java.awt.Dimension(775, 410));
        PantallaClientes.setUndecorated(true);
        PantallaClientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setBackground(new java.awt.Color(244, 243, 239));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel26.setBackground(new java.awt.Color(230, 205, 141));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton11.setBackground(new java.awt.Color(230, 205, 141));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Atras.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        jLabel31.setText("Administración de pedidos - Clientes");

        jPanel30.setBackground(new java.awt.Color(244, 243, 239));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addComponent(MPbotonMateriaPrima16, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioSesion6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(inicioSesion6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );

        jPanel27.setBackground(new java.awt.Color(244, 243, 239));

        tablaPedClie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPedClie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPedClieMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaPedClie);

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel3.setText("Cliente:");

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
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

        jLabel13.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel13.setText("Estado:");

        jButton6.setIcon(new javax.swing.ImageIcon("C:\\GestionPanificadora\\src\\icons2\\lupa chica.png")); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rSButtonMetro3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lista.png"))); // NOI18N
        rSButtonMetro3.setText("Generar un nuevo pedido");
        rSButtonMetro3.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro3.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro3.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro3.setColorTextHover(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });

        rSButtonMetro4.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/guardar_1.png"))); // NOI18N
        rSButtonMetro4.setText("Modificar pedido");
        rSButtonMetro4.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro4.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro4.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro4.setColorTextHover(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro4.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro4ActionPerformed(evt);
            }
        });

        rSButtonMetro6.setBackground(new java.awt.Color(244, 243, 239));
        rSButtonMetro6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSButtonMetro6.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
        rSButtonMetro6.setText("Eliminar");
        rSButtonMetro6.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro6.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro6.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro6.setColorTextHover(new java.awt.Color(0, 0, 0));
        rSButtonMetro6.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro6.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro6.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rSButtonMetro3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(rSButtonMetro6, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonMetro4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButtonMetro6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonMetro3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSButtonMetro4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PantallaClientes.getContentPane().add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 430));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Pedidos");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 320));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 320));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(230, 205, 141));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 36)); // NOI18N
        jLabel1.setText("Pedidos");

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
                .addComponent(inicioSesion4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MPbotonMateriaPrima14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 60));

        jPanel4.setBackground(new java.awt.Color(244, 243, 239));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMaximumSize(new java.awt.Dimension(800, 320));
        jPanel4.setMinimumSize(new java.awt.Dimension(800, 320));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 778, 20));

        MPbotonMateriaPrima12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MPbotonMateriaPrima12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        MPbotonMateriaPrima12.setText("Volver al home");
        MPbotonMateriaPrima12.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MPbotonMateriaPrima12.setColorHover(new java.awt.Color(230, 205, 141));
        MPbotonMateriaPrima12.setColorNormal(new java.awt.Color(244, 243, 239));
        MPbotonMateriaPrima12.setColorPressed(new java.awt.Color(244, 237, 210));
        MPbotonMateriaPrima12.setColorTextNormal(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima12.setColorTextPressed(new java.awt.Color(0, 0, 0));
        MPbotonMateriaPrima12.setDefaultCapable(false);
        MPbotonMateriaPrima12.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        MPbotonMateriaPrima12.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        MPbotonMateriaPrima12.setIconTextGap(25);
        MPbotonMateriaPrima12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPbotonMateriaPrima12ActionPerformed(evt);
            }
        });
        jPanel4.add(MPbotonMateriaPrima12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 210, 50));

        jPanel15.setBackground(new java.awt.Color(244, 243, 239));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Administracion de Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Light", 1, 14))); // NOI18N

        rSButtonMetro1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonMetro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/usuario_1.png"))); // NOI18N
        rSButtonMetro1.setText("Administrar pedidos de un cliente");
        rSButtonMetro1.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonMetro1.setColorHover(new java.awt.Color(230, 205, 141));
        rSButtonMetro1.setColorNormal(new java.awt.Color(244, 243, 239));
        rSButtonMetro1.setColorPressed(new java.awt.Color(244, 237, 210));
        rSButtonMetro1.setColorTextHover(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setColorTextNormal(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        rSButtonMetro1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        rSButtonMetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro1ActionPerformed(evt);
            }
        });

        botonPedidosProveedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonPedidosProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/camion.png"))); // NOI18N
        botonPedidosProveedor.setText("Administrar pedidos de un proveedor");
        botonPedidosProveedor.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonPedidosProveedor.setColorHover(new java.awt.Color(230, 205, 141));
        botonPedidosProveedor.setColorNormal(new java.awt.Color(244, 243, 239));
        botonPedidosProveedor.setColorPressed(new java.awt.Color(244, 237, 210));
        botonPedidosProveedor.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonPedidosProveedor.setColorTextNormal(new java.awt.Color(0, 0, 0));
        botonPedidosProveedor.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonPedidosProveedor.setDefaultCapable(false);
        botonPedidosProveedor.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        botonPedidosProveedor.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        botonPedidosProveedor.setIconTextGap(10);
        botonPedidosProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPedidosProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSButtonMetro1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(botonPedidosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonMetro1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPedidosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 110));
        jPanel15.getAccessibleContext().setAccessibleName("Administración de pedidos");

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 800, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void btnVolverPedClient() {
Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        jComboBox8.removeAllItems();
        NuevoPedClie.setVisible(false);
        PantallaClientes.setVisible(true);
        con.closeConexion();
    }
    private void botonPedidosProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPedidosProveedorActionPerformed
        dispose();
        deshabilitartablapedprov();
        detallepedidoprov();
        pantallaProveedor.setVisible(true);
        this.setVisible(false);
        pantallaProveedor.setSize(790, 400);
        pantallaProveedor.setResizable(false);
        pantallaProveedor.setLocationRelativeTo(null);
        cargar("");
        PantallaPedidos home = new PantallaPedidos();
        home.setVisible(false);
    }//GEN-LAST:event_botonPedidosProveedorActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    pantallaProveedor.setVisible(false);
        PantallaPedidos home = new PantallaPedidos();
        home.setVisible(true);
        this.setVisible(false);        
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        dispose();
        nuevoPedidoProveedor.setVisible(false);
        pantallaProveedor.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
        modificarPedidoProv.setVisible(false);
        pantallaProveedor.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
    void modificarPedProv() {
        int idmp = (int) jComboBox5.getSelectedIndex();
        int cant = (int) jSpinner3.getValue();
        int fila = jTable7.getSelectedRow();
        int id = parseInt(jTable7.getValueAt(fila, 0).toString());
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?", "CONFIRMACION CAMBIOS", JOptionPane.YES_NO_OPTION, HEIGHT);
        try {
            if (respuesta == JOptionPane.YES_OPTION) {
                con.ps = conn.prepareStatement("UPDATE detalle_pedido_proveedor SET cantidad=?  WHERE id_detalle_pedido_proveedor=?");
                con.ps.setInt(1, cant);//jspinner
                con.ps.setInt(2, id);//jspinner
                int res = con.ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO MODIFICADOS CORRECTAMENTE", "MODIFICAR PEDIDO-PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
                    modificarpedprov();
                } else {
                    JOptionPane.showMessageDialog(null, "LOS DATOS NO SE HAN SIDO MODIFICADOS", "MODIFICAR PEDIDO-PROVEEDOR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (respuesta == JOptionPane.NO_OPTION) {
               JOptionPane.showMessageDialog(null, "LOS DATOS NO SE HAN SIDO MODIFICADOS", "MODIFICAR PEDIDO-PROVEEDOR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        con.closeConexion();
    }
    private void MPbotonMateriaPrima7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima7ActionPerformed
        modificarPedProv();
    }//GEN-LAST:event_MPbotonMateriaPrima7ActionPerformed
    private void MPbotonMateriaPrima3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima3ActionPerformed
        cargarproveedor();
        cargarmp();
        pantallaProveedor.setVisible(false);
        nuevoPedidoProveedor.setVisible(true);
        nuevoPedidoProveedor.setSize(1160, 470);
        nuevoPedidoProveedor.setResizable(false);
        nuevoPedidoProveedor.setLocationRelativeTo(null);

    }//GEN-LAST:event_MPbotonMateriaPrima3ActionPerformed
    private void MPbotonMateriaPrima4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima4ActionPerformed
        deshabilitarmodificarpedprov();
        flitrojboxmodpedprov();
        pantallaProveedor.setVisible(false);
        modificarPedidoProv.setVisible(true);
        modificarPedidoProv.setSize(682, 450);
        modificarPedidoProv.setResizable(false);
        modificarPedidoProv.setLocationRelativeTo(null);
    }//GEN-LAST:event_MPbotonMateriaPrima4ActionPerformed
    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
        clienteestado();

        cargapedidoClienteconDetalleReceta();
        bloqueoopcionesdtr();
        dispose();
        PantallaClientes.setVisible(true);
        PantallaClientes.pack();
        PantallaClientes.setLocationRelativeTo(null);
        PantallaClientes.setResizable(false);
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        PantallaClientes.setVisible(false);

        PantallaPedidos home = new PantallaPedidos();
        home.setVisible(true);
        c.closeConexion();
    }//GEN-LAST:event_jButton11ActionPerformed
    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
        pedidocl("");
        cargarcliente();
        cargarproducto();
        bloquearbtn();
        PantallaClientes.setVisible(false);
        NuevoPedClie.setVisible(true);
        NuevoPedClie.setSize(980, 572);

        NuevoPedClie.setResizable(false);
        NuevoPedClie.setLocationRelativeTo(null);
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed
    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
        cargadescripciondtr();
        cargacodigodtr();
        bloquemodcl();
        PantallaClientes.setVisible(false);
        ModificarPedCLiente.setVisible(true);
        //ModificarPedCLiente.setSize(699, 590);
        ModificarPedCLiente.setSize(696, 446);
        ModificarPedCLiente.setLocationRelativeTo(null);
        ModificarPedCLiente.setResizable(false);
    }//GEN-LAST:event_rSButtonMetro4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        btnVolverPedClient();
    }//GEN-LAST:event_jButton5ActionPerformed
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ModificarPedCLiente.setVisible(false);
        PantallaClientes.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed
    private void MPbotonMateriaPrima12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima12ActionPerformed
        dispose();
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima12ActionPerformed
    void modificarPedClie() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?", "CONFIRMACION CAMBIOS", JOptionPane.YES_NO_OPTION, HEIGHT);
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = jTable6.getSelectedRow();
        int id = parseInt(jTable6.getValueAt(fila, 0).toString());
        try {
            if (respuesta == JOptionPane.YES_OPTION) {
                String valor = (String) jComboBox2.getSelectedItem();
                int valor2 = (int) jSpinner1.getValue();
int id_producto=0;
                 c.ps = conn.prepareStatement("SELECT id_producto FROM producto WHERE descripcion=?");   
       c.ps.setString(1, valor);
     c.rs= c.ps.executeQuery();
          while (c.rs.next()) {
      id_producto=c.rs.getInt("id_producto");
       }     
          c.ps = conn.prepareStatement("UPDATE detalle_pedido SET id_producto=?,cantidad=? where id_detalle_pedido=?");
               c.ps.setInt(1, id_producto);
                c.ps.setInt(2, valor2);
                c.ps.setInt(3, id);
                int res = c.ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO MODIFICADOS CORRECTAMENTE");
                    recetmodificarpedcliente();
                    modificadtr();
                } else {
                    JOptionPane.showMessageDialog(null, "LOS DATOS NO SE HAN MODIFICADO CORRECTAMENTE");
                }
            } else if (respuesta == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        c.closeConexion();
    }
    private void modifrpedcliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifrpedcliente2ActionPerformed
        modificarPedClie();
    }//GEN-LAST:event_modifrpedcliente2ActionPerformed
    void eliminarPedClie() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR?", "ELIMINAR ELEMENTO", JOptionPane.YES_NO_OPTION, HEIGHT);

        if (respuesta == JOptionPane.YES_OPTION) {

            Conectividad con = new Conectividad();
            Connection conn = con.getConexion();

            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaactual = sdf.format(fecha);

            int fila = tablaPedClie.getSelectedRow();

            int valor = parseInt(tablaPedClie.getValueAt(fila, 0).toString());
    

            con.getConexion();
            try {

                con.ps = conn.prepareStatement("UPDATE pedido_cliente SET statusbajapedcl=? WHERE id_pedido_cliente=" + valor);

                con.ps.setString(1, fechaactual);

                con.ps.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "El pedido no se ha podido dar de baja");
            }
            try {

                con.ps = conn.prepareStatement("UPDATE detalle_pedido SET statusbajadt=? WHERE id_pedido_cliente=" + valor);

                con.ps.setString(1, fechaactual);

                int res = con.ps.executeUpdate();
                cargarpedido();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "pedido dado de baja");
                    cargarpedido();
                    pedidocl("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "El pedido no se ha podido dar de baja");
            }

            con.closeConexion();

        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "SE HAN CANCELADO LOS CAMBIOS");
        }
    }
    private void rSButtonMetro6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro6ActionPerformed
        eliminarPedClie();
    }//GEN-LAST:event_rSButtonMetro6ActionPerformed
    private void rSButtonMetro8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro8ActionPerformed
        pantallaInicio in = new pantallaInicio();
        NuevoPedClie.setVisible(false);
        PantallaClientes cl = new PantallaClientes();
        in.pantallaCliente();
    }//GEN-LAST:event_rSButtonMetro8ActionPerformed
    private void MPbotonMateriaPrima9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima9ActionPerformed
        nuevoPedidoProveedor.setVisible(false);        // TODO add your handling code here:
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima9ActionPerformed
    void generarPedProv() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        String prov = (String) jComboBox14.getSelectedItem();
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaactual = sdf.format(fecha);
        try {
int id_proveedor=0;
                 con.ps = conn.prepareStatement("SELECT id_proveedor,nombre FROM proveedor WHERE nombre=?");   
       con.ps.setString(1, prov);
     con.rs= con.ps.executeQuery();
          while (con.rs.next()) {
      id_proveedor=con.rs.getInt("id_proveedor");
       }
            con.ps = conn.prepareStatement("INSERT INTO pedido_a_proveedor(fecha,id_proveedor,control) VALUES (?,?,?)");
            con.ps.setString(1, fechaactual);
            con.ps.setInt(2, id_proveedor);
            con.ps.setInt(3, 0);
            int res = con.ps.executeUpdate();
            if (res > 0) {
                 JOptionPane.showMessageDialog(null, "SE HA GENERADO EL PEDIDO PARA EL PROVEEDOR", "GENERAR-PEDIDO PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
                cargarprovped();
                cargar("");
                resercombobox();
                bloquearbtn();
            } else {
             JOptionPane.showMessageDialog(null, "NO SE PUDO GENERAR EL PEDIDO PARA EL PROVEEDOR", "GENERAR-PEDIDO PROVEEDOR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        con.closeConexion();
    }
    private void generarpedido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarpedido1ActionPerformed
        generarPedProv();
    }//GEN-LAST:event_generarpedido1ActionPerformed
    void generarDetallePedProv() {
        Scanner sc = new Scanner(System.in);
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        if (jCheckBox3.isSelected() == false) {
            float cantidades = (float) jSpinField3.getValue();
            if (cantidades > 0) {
                float engramos = cantidades / 1000;
                int fila = jTable1.getSelectedRow();
                int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
                try {
                    float cantidad = (float) jSpinner2.getValue();
                    String selectmp= (String) jComboBox7.getSelectedItem();
   
                 int idmp=0;
                    
            String SQL1 = "SELECT mp.id_materia_prima,mpc.nombre from materia_prima AS mp JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo where mpc.nombre LIKE '%" + selectmp + "%'";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(SQL1);
            while (rs1.next()) {
                idmp=rs1.getInt("mp.id_materia_prima");
          
            }
                    con.ps = conn.prepareStatement("INSERT INTO detalle_pedido_proveedor(cantidad,id_materia_prima,id_pedido_proveedor) VALUES (?,?,?)");
                    con.ps.setFloat(1, cantidad);
                    con.ps.setInt(2, idmp);
                    con.ps.setInt(3, valor);
                    int res = con.ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "DETALLE DEL PEDIDO PROVEEDOR SE GENERÓ CORRECTAMENTE", "DETALLE PEDIDO-PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
                        resercombobox();
                        bloquearbtn();
                        deshabilitartablapedprov();
                    }
                } catch (Exception e) {
                    //System.err.println(e);
                    e.printStackTrace();
JOptionPane.showMessageDialog(null, "LOS DATOS DEL PEDIDO PROVEEDOR NO SE HA GUARDADO CORRECTAMENTE", "DETALLE PEDIDO-PROVEEDOR", JOptionPane.ERROR_MESSAGE);
                    con.closeConexion();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO se puede generar stock cantidad cero en detalle pedido proveedor", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void generarpedido3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarpedido3ActionPerformed
        generarDetallePedProv();
    }//GEN-LAST:event_generarpedido3ActionPerformed
    void detallepedidoprov() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        String detalleProv[] = new String[3];
        String[] titulos4 = {"Ingrediente ", "Cantidad",};
        con.model = new DefaultTableModel(null, titulos4);
        try {
            String SQL = "SELECT DISTINCT pp.id_proveedor,nombre FROM `pedido_a_proveedor` AS pp JOIN detalle_pedido_proveedor dpp ON pp.id_pedido_proveedor=dpp.id_pedido_proveedor  JOIN proveedor prov on pp.id_proveedor=prov.id_proveedor where pp.statusbajapedprov is null and dpp.statusbajadtpedprov is null and control=0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                jComboBox11.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    void cargarCombolistadoProveedores() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        String Filtros = (String) jComboBox11.getSelectedItem();
        String detalleProv[] = new String[3];
        String[] titulos4 = {"Ingrediente ", "Cantidad", "Unidad de medida"};
        c.model = new DefaultTableModel(null, titulos4){
          @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        c.getConexion();
        try {
            int id_proveedor = 0;
            String SQL1 = "select  id_proveedor,nombre from proveedor where statusbajaprov is null AND nombre LIKE '%" + Filtros + "%' ";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(SQL1);
            while (rs1.next()) {
                id_proveedor = rs1.getInt("id_proveedor");
            }
            String SQL = "SELECT id_detalle_pedido_proveedor,mpc.nombre AS descripcion,cantidad,cum.nombre as unidad_medida FROM detalle_pedido_proveedor AS dpp JOIN materia_prima mp ON dpp.id_materia_prima=mp.id_materia_prima JOIN pedido_a_proveedor pap ON dpp.id_pedido_proveedor=pap.id_pedido_proveedor JOIN materia_prima_catalogo mpc on mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum on mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida JOIN proveedor p ON pap.id_proveedor=p.id_proveedor where statusbajadtpedprov is null  and control=0 and p.id_proveedor LIKE '%" + id_proveedor + "%' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                detalleProv[0] = rs.getString("descripcion");
                detalleProv[1] = rs.getString("cantidad");
                detalleProv[2] = rs.getString("unidad_medida");
                c.model.addRow(detalleProv);
                jTable2.setModel(c.model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox11ItemStateChanged
        cargarCombolistadoProveedores();
    }//GEN-LAST:event_jComboBox11ItemStateChanged
    private void generarpedido1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generarpedido1MouseClicked
        cargar("");
        nuevoPedidoProveedor.setVisible(false);
        nuevoPedidoProveedor.setVisible(true);
        nuevoPedidoProveedor.setSize(890, 590);
        nuevoPedidoProveedor.setResizable(false);
        nuevoPedidoProveedor.setLocationRelativeTo(null);
    }//GEN-LAST:event_generarpedido1MouseClicked
    private void generarpedido3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generarpedido3MouseClicked
     
    }//GEN-LAST:event_generarpedido3MouseClicked
    void detallePedidoProveedor() {
        int fila = jTable1.getSelectedRow();
        int filtroa = parseInt(jTable1.getValueAt(fila, 0).toString());
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        int comparar = 0;
        try {
            String SQL1 = "select dpp.id_detalle_pedido_proveedor from pedido_a_proveedor AS pap JOIN detalle_pedido_proveedor dpp ON pap.id_pedido_proveedor=dpp.id_pedido_proveedor WHERE pap.id_pedido_proveedor=" + filtroa;
            Statement stmt1 = conn.createStatement();
            ResultSet rss = stmt1.executeQuery(SQL1);
            while (rss.next()) {
                comparar = rss.getInt("dpp.id_detalle_pedido_proveedor");
            }
            if (comparar > 0) {
                jTable3.setVisible(true);
                String[] titulos = {"Código detalle pedido", "Descripción", "Cantidad", "Unidad de medida"};
                con.model = new DefaultTableModel(null, titulos){
                 @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 0 && columnas == 1 && columnas == 2 && columnas == 3 && columnas == 4 && columnas == 5 && columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
                
                };
                String filtrar[] = new String[4];
                con.getConexion();
                try {
                    String SQL = "SELECT `id_detalle_pedido_proveedor`,mpc.nombre,`cantidad`,cum.nombre FROM detalle_pedido_proveedor AS dpp JOIN materia_prima mp ON dpp.id_materia_prima=mp.id_materia_prima JOIN materia_prima_catalogo mpc on mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum on mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida WHERE `statusbajadtpedprov` is null AND id_pedido_proveedor =" + filtroa;
                    Statement stmt = conn.createStatement();
                    con.rs = stmt.executeQuery(SQL);
                    while (con.rs.next()) {
                        if (fila >= 0) {
                            filtrar[0] = con.rs.getString("id_detalle_pedido_proveedor");
                            filtrar[1] = con.rs.getString("mpc.nombre");
                            filtrar[2] = con.rs.getString("cantidad");
                            filtrar[3] = con.rs.getString("cum.nombre");
                            con.model.addRow(filtrar);
                            jTable3.setModel(con.model);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
                }
            } else {
                jTable3.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        int fila = jTable1.getSelectedRow();
        int valor = parseInt(jTable1.getValueAt(fila, 0).toString());
        if (fila == -1) {
            jTable3.setVisible(false);
        } else {
            cargardetalleproveedor("");
            habilitartablapedprov();
            detallePedidoProveedor();
            bloqueopaneldtr();
            generarpedido3.setEnabled(false);
        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void jTable1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jTable1HierarchyChanged
    }//GEN-LAST:event_jTable1HierarchyChanged
    boolean validacionStockProducto() {
        int fila = jTable4.getSelectedRow();
        int codigoProducto = parseInt(jTable4.getValueAt(fila, 0).toString());
        boolean resultado = false;
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "select p.id_producto,descripcion,stock,cantidad from producto as p JOIN detalle_pedido dtr ON p.id_producto=dtr.id_producto where statusbajaprod is null and dtr.id_pedido_cliente=" + codigoProducto;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                float stock = rs.getFloat("stock");
                float cantidadSolicitada = rs.getFloat("cantidad");
                if (stock < cantidadSolicitada) {
                    resultado = true;
                    return resultado;
                }
            }
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    void generarDetallePed() {
        bloqueoopcionesdtr();
        if (alertaIngresoDatoNulo() == true) {
            int fila = jTable4.getSelectedRow();
            int valor1 = parseInt(jTable4.getValueAt(fila, 0).toString());
            Conectividad con = new Conectividad();
            Connection conn = con.getConexion();
            if (jCheckBox3.isSelected() == true) {
                float cantidad = (float) jSpinField3.getValue();
                if (cantidad > 0) {
                    float engramos = cantidad / 1000;
                    try {
                        int id_producto = 0;
                        con.getConexion();
                        String provp = (String) jComboBox8.getSelectedItem();
                        String SQL = "SELECT id_producto, descripcion FROM producto where statusbajaprod is null AND descripcion LIKE '%" + provp + "%' ";
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(SQL);
                        while (rs.next()) {
                            id_producto = rs.getInt(("id_producto"));
                        }
                        con.ps = conn.prepareStatement("INSERT INTO detalle_pedido(id_producto,cantidad,id_pedido_cliente) VALUES (?,?,?)");
                        con.ps.setInt(1, id_producto);
                        con.ps.setFloat(2, engramos);
                        con.ps.setInt(3, valor1);
                        int res = con.ps.executeUpdate();
                        if (res > 0) {
                           JOptionPane.showMessageDialog(null, "EL DETALLE DEL PEDIDO SE GENERÓ CORRECTAMENTE", "GENERAR PEDIDO-CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "EL DETALLE DEL PEDIDO NO SE HA GENERADO  CORRECTAMENTE", "GENERAR PEDIDO-CLIENTE", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    con.closeConexion();
                } else {
                    JOptionPane.showMessageDialog(null, "NO se puede agregar cantidad cero en un detalle de pedido cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    con.getConexion();
                    float cantidad = (float) jSpinField3.getValue();
                    if (cantidad > 0) {
                        int id_producto = 0;
                        String provp = (String) jComboBox8.getSelectedItem();
                        String SQL = "SELECT id_producto, descripcion FROM producto where statusbajaprod is null AND descripcion LIKE '%" + provp + "%' ";
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(SQL);
                        while (rs.next()) {
                            id_producto = rs.getInt(("id_producto"));
                        }
                        con.ps = conn.prepareStatement("INSERT INTO detalle_pedido(id_producto,cantidad,id_pedido_cliente) VALUES (?,?,?)");
                        con.ps.setInt(1, id_producto);
                        con.ps.setFloat(2, cantidad);
                        con.ps.setInt(3, valor1);
                        int res = con.ps.executeUpdate();
                        jComboBox8.setSelectedItem("Seleccioanar");
                        jSpinField3.setValue(0);
                        bloqueoopcionesdtr();
                        if (res > 0) {
                             JOptionPane.showMessageDialog(null, "EL DETALLE DEL PEDIDO SE GENERÓ CORRECTAMENTE", "GENERAR PEDIDO-CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "EL DETALLE DEL PEDIDO NO SE HA GENERADO  CORRECTAMENTE", "GENERAR PEDIDO-CLIENTE", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE PUEDE AGREGAR CANTIDAD CERO EN UN DETALLE PEDIDO CLIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                con.closeConexion();
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO SE PUDO REALIZAR EL PEDIDO PORQUE LA CANTIDAD SOLICITADA ES NULA", "ERROR CANTIDAD", JOptionPane.ERROR_MESSAGE);
        }

    }
    private void rSButtonMetro7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro7ActionPerformed
        generarDetallePed();
    }//GEN-LAST:event_rSButtonMetro7ActionPerformed
    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int fila = jTable4.getSelectedRow();
        int filtroa = parseInt(jTable4.getValueAt(fila, 0).toString());
        if (fila == -1) {
            jTable5.setVisible(false);
        } else {
            jTable5.setVisible(true);
            activaropcionesdtr();
            activarbtngenerarcl();
            bloqueopanelpdc();
            detallepdc("");
        }
    }//GEN-LAST:event_jTable4MouseClicked
    void generarPedidoCl() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        con.getConexion();
        try {
            int id_cliente = 0;
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaactual = sdf.format(fecha);
            String cliente = (String) jComboBox3.getSelectedItem().toString();
            String SQL = "SELECT id_cliente,nombre FROM cliente where statusbajacl is null AND nombre LIKE'%" + cliente + "%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                id_cliente = rs.getInt("id_cliente");
            }
            String prod = (String) selectprod.getSelectedItem();
            con.ps = conn.prepareStatement("INSERT INTO pedido_cliente(fecha,estado,id_cliente) VALUES (?,?,?)");
            con.ps.setString(1, fechaactual);
            con.ps.setString(2, "Pendiente");
            con.ps.setInt(3, id_cliente);
            int res = con.ps.executeUpdate();
            cargarpedido();
            pedidocl("");
            if (res > 0) {
                bloquearbtn();
              JOptionPane.showMessageDialog(null, "EL PEDIDO SE GENERÓ CORRECTAMENTE", "GENERAR PEDIDO-CLIENTE", JOptionPane.INFORMATION_MESSAGE);
            } else {
               JOptionPane.showMessageDialog(null, "EL PEDIDO NO SE HA GENERADO  CORRECTAMENTE", "GENERAR PEDIDO-CLIENTE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.closeConexion();
    }
    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        generarPedidoCl();
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed
    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        activarmodificarpedcliente();
        modificadtr();
    }//GEN-LAST:event_jComboBox6ItemStateChanged
    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        activarmodcl();
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = jTable6.getSelectedRow();
        int valor1 = parseInt(jTable6.getValueAt(fila, 0).toString());
        c.getConexion();
        try {
            c.ps = conn.prepareStatement("SELECT * FROM detalle_pedido AS dp join producto p ON dp.id_producto=p.id_producto WHERE id_detalle_pedido=?");
            c.ps.setInt(1, valor1);
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                jComboBox2.setSelectedItem(c.rs.getString("descripcion"));
                jSpinner1.setValue(c.rs.getInt("cantidad"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un producto con ese nombre");
            }
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");
        }
        c.closeConexion();
    }//GEN-LAST:event_jTable6MouseClicked
    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        habilitarmodificarpedprov();
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = jTable7.getSelectedRow();
        int valor1 = parseInt(jTable7.getValueAt(fila, 0).toString());
        c.getConexion();
        try {
            c.ps = conn.prepareStatement("SELECT id_detalle_pedido_proveedor,mpc.nombre AS descripcion,cantidad,cum.nombre AS unidad_medida FROM detalle_pedido_proveedor AS dpp JOIN materia_prima mp ON dpp.id_materia_prima= mp.id_materia_prima JOIN materia_prima_catalogo mpc ON mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum ON mp.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where statusbajadtpedprov is null and id_detalle_pedido_proveedor=?");
            c.ps.setInt(1, valor1);
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                jComboBox5.setSelectedItem(c.rs.getString("descripcion"));
                jSpinner3.setValue(c.rs.getInt("cantidad"));
            } else {
                JOptionPane.showMessageDialog(null, "error al seleccionar el ingrediente");
            }
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");
        }
        c.closeConexion();
    }//GEN-LAST:event_jTable7MouseClicked
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
    }//GEN-LAST:event_jCheckBox1ItemStateChanged
    void verificarPedidoProv() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        int fila = jTable1.getSelectedRow();
        int check = parseInt(jTable1.getValueAt(fila, 0).toString());
        if (jCheckBox1.isSelected() == true) {
            try {
                con.getConexion();
                String SQL = "SELECT * from pedido_a_proveedor where id_pedido_proveedor= " + check;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {

                    try {
                        con.ps = conn.prepareStatement("UPDATE materia_prima a, detalle_pedido_proveedor b SET a.stock = b.cantidad+a.stock WHERE a.id_materia_prima = b.id_materia_prima AND b.id_pedido_proveedor= " + check);
                        con.ps.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        con.ps = conn.prepareStatement("UPDATE pedido_a_proveedor set control=1 where id_pedido_proveedor=" + check);
                        int res = con.ps.executeUpdate();
                        if (res > 0) {
                            JOptionPane.showMessageDialog(null, "SU VERIFICACION SE HA COMPLETADO CON EXITO", "VERIFICAR PEDIDO-PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
                            bloqueopaneldtr();
                            jComboBox7.setEnabled(false);
                            jSpinner2.setEnabled(false);
                            generarpedido3.setEnabled(false);
                            jCheckBox1.setEnabled(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            con.closeConexion();
        }
        nuevoPedidoProveedor.setVisible(false);

        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);

    }
    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        verificarPedidoProv();
    }//GEN-LAST:event_jCheckBox1MouseClicked
    boolean CheckeoStockProductoExistente() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        float pedidodelCLiente = 0;
        int codprod = 0;
        float StockInicial = 0;
        boolean stock = false;
        try {
            String SQL = "SELECT cantidad from detalle_pedido where id_producto=" + codprod;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                pedidodelCLiente = rs.getFloat("cantidad");//aca
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            String SQL = "SELECT stock from producto where id_producto=" + codprod;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                StockInicial = rs.getFloat("stock");//aca
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pedidodelCLiente > StockInicial) {
            stock = true;
        }
        return stock;
    }
    void entregarProducto() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int fila = jTable4.getSelectedRow();
        int filtrob = parseInt(jTable4.getValueAt(fila, 0).toString());
        int codprod = 0;
        float pedidodelCLiente = 0;
        try {
            try {
                String SQL = "SELECT id_producto from detalle_pedido where id_pedido_cliente=" + filtrob;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    codprod = rs.getInt("id_producto");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String SQL = "SELECT cantidad from detalle_pedido where id_producto=" + codprod;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    pedidodelCLiente = rs.getFloat("cantidad");//aca
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String SQL = "SELECT stock from producto where id_producto=" + codprod;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    float StockInicial = rs.getFloat("stock");
                    float descuento = StockInicial - pedidodelCLiente;
                    c.ps = conn.prepareStatement("UPDATE producto set stock=? where id_producto=" + codprod);
                    c.ps.setFloat(1, descuento);
                    int res = c.ps.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.ps = conn.prepareStatement("UPDATE pedido_cliente set estado=? where id_pedido_cliente=" + filtrob);
            c.ps.setString(1, "Entregado");
            int res = c.ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "EL PRODUCTO HA SIDO ENTREGADO", "GENERAR PEDIDO-CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                pedidocl("");
                jCheckBox2.setEnabled(false);
                jButton7.setEnabled(false);
                bloqueoopcionesdtr();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR NO PUEDES MARCAR EL PRODUCTO COMO ENTREGADO", "GENERAR PEDIDO-CLIENTE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    boolean alertaIngresoDatoNulo() {
        int fila = jTable4.getSelectedRow();
        int codigoProducto = parseInt(jTable4.getValueAt(fila, 0).toString());
        boolean resultado = true;
        float y = (float) jSpinField3.getValue();
        try {
            Conectividad c = new Conectividad();
            Connection conn = c.getConexion();
            String SQL = "select p.id_producto,descripcion,stock,cantidad from producto as p JOIN detalle_pedido dtr ON p.id_producto=dtr.id_producto where statusbajaprod is null and dtr.id_pedido_cliente=" + codigoProducto;
            Statement stmt = conn.createStatement();
            c.rs = stmt.executeQuery(SQL);
            while (c.rs.next()) {
                if (y == 0) {
                    resultado = false;
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        if (jCheckBox2.isSelected() == true && validacionStockProducto() == true) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO REALIZAR EL PEDIDO POR FALTANTE DE STOCK", "ERROR FALTANTE STOCK", JOptionPane.ERROR_MESSAGE);
            jCheckBox2.setSelected(false);
        }
        if (jCheckBox2.isSelected() == true && validacionStockProducto() == false) {
            entregarProducto();
            jCheckBox2.setSelected(true);
        }
    }//GEN-LAST:event_jCheckBox2MouseClicked
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
    }//GEN-LAST:event_jTextField2ActionPerformed
    private void jComboBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox7MouseClicked
generarpedido3.setEnabled(true);
    }//GEN-LAST:event_jComboBox7MouseClicked
    void cargarListadoIngredMP() {
              generarpedido3.setEnabled(true);
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String dato = (String) jComboBox7.getSelectedItem();
        String mp[] = new String[8];
        String[] titulos2 = {""};
        con.model = new DefaultTableModel(null, titulos2);
        try {
            con.getConexion();
            String SQL = "SELECT DISTINCT mpc.nombre,cum.nombre FROM `materia_prima` AS mp JOIN materia_prima_catalogo mpc on mp.id_mp_catalogo=mpc.id_mp_catalogo JOIN catalogo_unidad_medida cum on mp.`id_catalogo_unidad_medida`=cum.id_catalogo_unidad_medida WHERE statusbajamp is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String umed = rs.getString("cum.nombre");
                String desc = rs.getString("mpc.nombre");
                if (dato.equals(desc)) {
                    jTextField2.setText(umed);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged
      
        cargarListadoIngredMP();
  
    }//GEN-LAST:event_jComboBox7ItemStateChanged
    private void jComboBox8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox8MouseClicked
rSButtonMetro7.setEnabled(true);
    }//GEN-LAST:event_jComboBox8MouseClicked
    void busquedaProductoCombo() {
        Conectividad con = new Conectividad();
        Connection conn = con.getConexion();
        String combos = (String) jComboBox8.getSelectedItem();
        jComboBox8.getSelectedIndex();
        
        try {
            int id_producto = 0;
            String SQL1 = "select  id_producto,descripcion from producto where statusbajaprod is null AND descripcion LIKE '%" + combos + "%' ";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(SQL1);
            while (rs1.next()) {
                id_producto = rs1.getInt("id_producto");
            }
            con.getConexion();
            String SQL = "SELECT id_producto,cum.nombre AS unidad_medida from producto JOIN catalogo_unidad_medida cum ON producto.id_catalogo_unidad_medida=cum.id_catalogo_unidad_medida where statusbajaProd is null and id_producto LIKE '%" + id_producto + "%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String umed = rs.getString("unidad_medida");
                jTextField1.setText(umed);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar" + " " + e.getMessage());
        }
        con.closeConexion();
    }
    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged

        busquedaProductoCombo();
    }//GEN-LAST:event_jComboBox8ItemStateChanged
    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        bloqueoordprod();
    }//GEN-LAST:event_jTable5MouseClicked
    void generarOrdenDeProduccion() {
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        int id = 1;
        int valor = 0;
        float valor1 = 0;
        int fila = jTable5.getSelectedRow();
        int filtrob = parseInt(jTable5.getValueAt(fila,0).toString());
        c.getConexion();
        try {
            c.ps = conn.prepareStatement("SELECT * FROM detalle_pedido  WHERE  id_detalle_pedido="+filtrob);
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                valor = c.rs.getInt("id_producto");
                valor1 = c.rs.getFloat("cantidad");
            } else {
                System.out.println("no se encuentra el dato solicitado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            c.ps = conn.prepareStatement("INSERT INTO orden_de_produccion(id_producto,cantidad,interno,estado_de_produccion) VALUES (?,?,?,?) ");
            c.ps.setInt(1, valor);
            c.ps.setFloat(2, valor1);
            c.ps.setFloat(3, 0);
            c.ps.setBoolean(4, false);

            c.ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            c.ps = conn.prepareStatement("select max(id_orden_produccion)from orden_de_produccion");
            c.rs = c.ps.executeQuery();
            while (c.rs.next()) {
                id = c.rs.getInt(1);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            float cantidad = (float) jSpinner2.getValue();
            int selectmp = (int) jComboBox7.getSelectedIndex();

            c.ps = conn.prepareStatement("UPDATE detalle_pedido set id_orden_produccion=? where id_detalle_pedido=" + filtrob);
            c.ps.setInt(1, id);

            int res = c.ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int idpedcl=0;
 c.ps = conn.prepareStatement("SELECT id_pedido_cliente FROM detalle_pedido  WHERE  id_detalle_pedido="+filtrob);
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                idpedcl = c.rs.getInt("id_pedido_cliente");
               
            } else {
                System.out.println("no se encuentra el dato solicitado.");
            }
            float cantidad = (float) jSpinner2.getValue();
            int selectmp = (int) jComboBox7.getSelectedIndex();

            c.ps = conn.prepareStatement("UPDATE pedido_cliente AS pc INNER JOIN detalle_pedido dp ON pc.id_pedido_cliente = dp.id_detalle_pedido SET pc.estado=? where pc.id_pedido_cliente=" + idpedcl);
            c.ps.setString(1, "En producción");

            int res = c.ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "LA ORDEN DE PRODUCCIÓN EXTERNA SE GENERÓ CORRECTAMENTE");
                pedidocl("");

            } else {
                JOptionPane.showMessageDialog(null, "Error no puedes generar la orden de producción");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.closeConexion();
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        generarOrdenDeProduccion();
    }//GEN-LAST:event_jButton7ActionPerformed
    private void tablaPedClieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedClieMouseClicked
        activarbotonelimienar();
    }//GEN-LAST:event_tablaPedClieMouseClicked
    private void jComboBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MouseClicked
    }//GEN-LAST:event_jComboBox6MouseClicked
    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
    }//GEN-LAST:event_jComboBox4ActionPerformed
    private void jComboBox11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox11MouseClicked
    }//GEN-LAST:event_jComboBox11MouseClicked
    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        activarbtngenerar();
    }//GEN-LAST:event_jComboBox14ItemStateChanged
    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        activarbtngenerarcl();
    }//GEN-LAST:event_jComboBox3ItemStateChanged
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Conectividad c = new Conectividad();
        Connection conn = c.getConexion();
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
        this.setVisible(false);
        c.closeConexion();
    }//GEN-LAST:event_jButton1ActionPerformed
    private void inicioSesion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_inicioSesion2ActionPerformed
    private void inicioSesion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion3ActionPerformed
        System.exit(0);        
    }//GEN-LAST:event_inicioSesion3ActionPerformed
    private void MPbotonMateriaPrima13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima13ActionPerformed
        pantallaProveedor.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima13ActionPerformed
    private void inicioSesion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion4ActionPerformed
        System.exit(0);    
    }//GEN-LAST:event_inicioSesion4ActionPerformed
    private void MPbotonMateriaPrima14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima14ActionPerformed
        this.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima14ActionPerformed
    private void inicioSesion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion5ActionPerformed
        System.exit(0);      
    }//GEN-LAST:event_inicioSesion5ActionPerformed
    private void MPbotonMateriaPrima15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima15ActionPerformed
        modificarPedidoProv.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima15ActionPerformed
    private void inicioSesion6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion6ActionPerformed
        System.exit(0);  
    }//GEN-LAST:event_inicioSesion6ActionPerformed
    private void MPbotonMateriaPrima16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima16ActionPerformed
        PantallaClientes.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima16ActionPerformed
    private void inicioSesion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion7ActionPerformed
        System.exit(0);   
    }//GEN-LAST:event_inicioSesion7ActionPerformed
    private void MPbotonMateriaPrima10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima10ActionPerformed
        NuevoPedClie.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima10ActionPerformed
    private void inicioSesion8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesion8ActionPerformed
        System.exit(0); 
    }//GEN-LAST:event_inicioSesion8ActionPerformed
    private void MPbotonMateriaPrima11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima11ActionPerformed
        ModificarPedCLiente.setVisible(false);
        pantallaInicio home = new pantallaInicio();
        home.setVisible(true);
    }//GEN-LAST:event_MPbotonMateriaPrima11ActionPerformed
    private void MPbotonMateriaPrima7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MPbotonMateriaPrima7MouseClicked
    }//GEN-LAST:event_MPbotonMateriaPrima7MouseClicked
    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
    }//GEN-LAST:event_jCheckBox2ActionPerformed
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    private void jCheckBox2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MousePressed
    }//GEN-LAST:event_jCheckBox2MousePressed
    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed

    }//GEN-LAST:event_jComboBox6ActionPerformed
    private void jComboBox6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MousePressed
    }//GEN-LAST:event_jComboBox6MousePressed
    private void jComboBox6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MouseEntered
    }//GEN-LAST:event_jComboBox6MouseEntered
    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
    }//GEN-LAST:event_jPanel19MouseClicked
    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        modificarpedprov();
    }//GEN-LAST:event_jComboBox4ItemStateChanged
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    }//GEN-LAST:event_jButton6ActionPerformed
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
    }//GEN-LAST:event_jButton6MouseClicked
    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        buscarpedidos();
    }//GEN-LAST:event_jButton6MousePressed
    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        clienteestado();
    }//GEN-LAST:event_jComboBox1MouseClicked
    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
    }//GEN-LAST:event_jCheckBox3ActionPerformed
    private void jCheckBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseClicked
    }//GEN-LAST:event_jCheckBox3MouseClicked

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PantallaPedidos().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima10;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima11;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima12;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima13;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima14;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima15;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima16;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima3;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima4;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima7;
    private rsbuttom.RSButtonMetro MPbotonMateriaPrima9;
    private javax.swing.JFrame ModificarPedCLiente;
    private javax.swing.JFrame NuevoPedClie;
    private javax.swing.JFrame PantallaClientes;
    private rsbuttom.RSButtonMetro botonPedidosProveedor;
    private rsbuttom.RSButtonMetro generarpedido1;
    private rsbuttom.RSButtonMetro generarpedido3;
    private rsbuttom.RSButtonMetro inicioSesion2;
    private rsbuttom.RSButtonMetro inicioSesion3;
    private rsbuttom.RSButtonMetro inicioSesion4;
    private rsbuttom.RSButtonMetro inicioSesion5;
    private rsbuttom.RSButtonMetro inicioSesion6;
    private rsbuttom.RSButtonMetro inicioSesion7;
    private rsbuttom.RSButtonMetro inicioSesion8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSpinner jSpinField3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JFrame modificarPedidoProv;
    private rsbuttom.RSButtonMetro modifrpedcliente2;
    private javax.swing.JFrame nuevoPedidoProveedor;
    private javax.swing.JFrame pantallaProveedor;
    private rsbuttom.RSButtonMetro rSButtonMetro1;
    private rsbuttom.RSButtonMetro rSButtonMetro2;
    private rsbuttom.RSButtonMetro rSButtonMetro3;
    private rsbuttom.RSButtonMetro rSButtonMetro4;
    private rsbuttom.RSButtonMetro rSButtonMetro6;
    private rsbuttom.RSButtonMetro rSButtonMetro7;
    private rsbuttom.RSButtonMetro rSButtonMetro8;
    private javax.swing.JComboBox<String> selectprod;
    private javax.swing.JTable tablaPedClie;
    // End of variables declaration//GEN-END:variables
}
