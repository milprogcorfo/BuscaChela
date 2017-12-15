package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class Main implements ActionListener, WindowListener{

    vista.FrmMain frmMain;
    modelo.BDMain bd;

    public Main(vista.FrmMain frmMain, modelo.BDMain bd) {
        this.frmMain = frmMain;
        this.bd = bd;
        
        frmMain.addWindowListener(this);
        
        frmMain.mnuOferta.addActionListener(this);
        frmMain.mnuProveedor.addActionListener(this);
        frmMain.mnuProducto.addActionListener(this);
        frmMain.btnBuscaE.addActionListener(this);
        frmMain.btnBuscaPro.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "CMD_ADDO":
                vista.FrmPrecio v = new vista.FrmPrecio();
                modelo.BDPrecio m = new modelo.BDPrecio();
                controlador.Precio c = new controlador.Precio(v, m, frmMain);
                frmMain.setEnabled(false);
                v.setVisible(true);
                break;
            case "CMD_ADDE":
                vista.FrmProveedor vp = new vista.FrmProveedor();
                modelo.BDProveedor mbp = new modelo.BDProveedor();
                controlador.Proveedor cp = new controlador.Proveedor(vp, mbp, frmMain);
                frmMain.setEnabled(false);
                vp.setVisible(true);
                break;
            case "CMD_ADDP":
                vista.FrmProducto vpr = new vista.FrmProducto();
                modelo.BDProducto mbpr = new modelo.BDProducto();
                controlador.Producto cpr = new controlador.Producto(vpr, mbpr, frmMain);
                frmMain.setEnabled(false);
                vpr.setVisible(true);
                break;
            case "CMD_EMP":
                ArrayList<modelo.Precio> pre;
                DefaultTableModel modelo = (DefaultTableModel) frmMain.tblMain.getModel();
                modelo.setRowCount(0);
                String local = String.valueOf(frmMain.cbxEmpresa.getSelectedItem());
                pre = bd.buscarLocal(local);

                for (modelo.Precio precio : pre) {
                    Object[] fila = new Object[7];

                    fila[0] = precio.getProveedor().getEmpresa();
                    fila[1] = precio.getProveedor().getDireccion();
                    fila[2] = precio.getProducto().getMarca();
                    fila[3] = precio.getProducto().getTipo();
                    fila[4] = precio.getProducto().getFormato();
                    fila[5] = precio.getProducto().getMedida();
                    fila[6] = precio.getPrecio();

                    modelo.addRow(fila);
                }
                break;
            case "CMD_PRO":
                ArrayList<modelo.Precio> pro;
                DefaultTableModel modela = (DefaultTableModel) frmMain.tblMain.getModel();
                modela.setRowCount(0);
                String marca = String.valueOf(frmMain.cbxMarca.getSelectedItem());
                pro = bd.buscarMarca(marca);

                for (modelo.Precio precio : pro) {
                    Object[] fila = new Object[7];

                    fila[0] = precio.getProveedor().getEmpresa();
                    fila[1] = precio.getProveedor().getDireccion();
                    fila[2] = precio.getProducto().getMarca();
                    fila[3] = precio.getProducto().getTipo();
                    fila[4] = precio.getProducto().getFormato();
                    fila[5] = precio.getProducto().getMedida();
                    fila[6] = precio.getPrecio();

                    modela.addRow(fila);
                }
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
        modelo.BDProveedor bdPro = new modelo.BDProveedor();
        
        DefaultComboBoxModel<String> empresa = (DefaultComboBoxModel)frmMain.cbxEmpresa.getModel();
        for(int i=empresa.getSize()-1;i>1;i--){
            empresa.removeElementAt(i);
        }
        ArrayList<modelo.Proveedor> pro = new ArrayList<>();
        pro = bdPro.buscar();
        for(modelo.Proveedor proveedor:pro){
            empresa.addElement(proveedor.getEmpresa());
        }
        
        modelo.BDProducto bdPrd = new modelo.BDProducto();
        
        DefaultComboBoxModel<String> marca = (DefaultComboBoxModel)frmMain.cbxMarca.getModel();
        for(int i=marca.getSize()-1;i>1;i--){
            marca.removeElementAt(i);
        }
        ArrayList<modelo.Producto> prd = new ArrayList<>();
        prd = bdPrd.buscar(); 
        for(modelo.Producto producto:prd){
            marca.addElement(producto.getMarca());
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
