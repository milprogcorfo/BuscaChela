package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Precio implements ActionListener, WindowListener, MouseListener {

    vista.FrmPrecio frmPre;
    modelo.BDPrecio bd;
    JFrame callWindow;

    public Precio(vista.FrmPrecio frmPre, modelo.BDPrecio bd, JFrame callWindow) {
        this.frmPre = frmPre;
        this.bd = bd;
        this.callWindow = callWindow;

        frmPre.addWindowListener(this);

        frmPre.btnAdd1.addActionListener(this);
        frmPre.btnAdd2.addActionListener(this);
        frmPre.btnAgregar.addActionListener(this);
        frmPre.btnBusca.addActionListener(this);
        frmPre.btnEliminar.addActionListener(this);
        frmPre.btnModificar.addActionListener(this);
        frmPre.btnVolver.addActionListener(this);
                
        frmPre.btnModificar.setEnabled(false);

        frmPre.tblPrecio.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        boolean res;
        modelo.Precio aux = new modelo.Precio();

        switch (cmd) {
            case "CMD_ADD1":
                vista.FrmProveedor vp = new vista.FrmProveedor();
                modelo.BDProveedor mbp = new modelo.BDProveedor();
                controlador.Proveedor cp = new controlador.Proveedor(vp, mbp, frmPre);
                frmPre.setEnabled(false);
                vp.setVisible(true);
                break;
            case "CMD_ADD2":
                vista.FrmProducto vpr = new vista.FrmProducto();
                modelo.BDProducto mbpr = new modelo.BDProducto();
                controlador.Producto cpr = new controlador.Producto(vpr, mbpr, frmPre);
                frmPre.setEnabled(false);
                vpr.setVisible(true);
                break;
            case "CMD_SAVE":
                String mar = String.valueOf(frmPre.cbxMarca.getSelectedItem());
                String tip = String.valueOf(frmPre.cbxTipo.getSelectedItem());
                String form = String.valueOf(frmPre.cbxFormato.getSelectedItem());
                int med = Integer.parseInt(String.valueOf(frmPre.cbxMedida.getSelectedItem()));
                int producto = bd.buscarIdProducto(mar, tip, form, med);

                int local = bd.buscarIdLocal((modelo.Proveedor)(frmPre.cbxEmpresa.getSelectedItem()));
                
                aux.setPrecio(Double.parseDouble(frmPre.txtPrecio.getText()));
                aux.setIdProducto(producto);
                aux.setIdLocal(local);
                int resaux = bd.agregar(aux);
                if (resaux == 1) {
                    JOptionPane.showMessageDialog(null, "Oferta agregada", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Oferta no pudo ser agregada", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CMD_DEL":
                int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la Oferta?", "Mensaje de Sistema", JOptionPane.YES_NO_OPTION);
                if (op != 1) {
                    mar = String.valueOf(frmPre.cbxMarca.getSelectedItem());
                    tip = String.valueOf(frmPre.cbxTipo.getSelectedItem());
                    form = String.valueOf(frmPre.cbxFormato.getSelectedItem());
                    med = Integer.parseInt(String.valueOf(frmPre.cbxMedida.getSelectedItem()));
                    producto = bd.buscarIdProducto(mar, tip, form, med);

                    local = bd.buscarIdLocal((modelo.Proveedor)(frmPre.cbxEmpresa.getSelectedItem()));
                    res = bd.eliminar(producto, local);
                    if (res) {
                        JOptionPane.showMessageDialog(null, "Oferta eliminada", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Oferta no pudo ser eliminada", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "CMD_MOD":
                aux.setPrecio(Double.parseDouble(frmPre.txtPrecio.getText()));
                mar = String.valueOf(frmPre.cbxMarca.getSelectedItem());
                tip = String.valueOf(frmPre.cbxTipo.getSelectedItem());
                form = String.valueOf(frmPre.cbxFormato.getSelectedItem());
                med = Integer.parseInt(String.valueOf(frmPre.cbxMedida.getSelectedItem()));
                producto = bd.buscarIdProducto(mar, tip, form, med);

                local = bd.buscarIdLocal((modelo.Proveedor)(frmPre.cbxEmpresa.getSelectedItem()));
                res = bd.modificar(producto, local, aux);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Oferta modificada", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Oferta no pudo ser modificada", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CMD_FIND":
                ArrayList<modelo.Precio> pre;
                DefaultTableModel modelo = (DefaultTableModel) frmPre.tblPrecio.getModel();
                modelo.setRowCount(0);
                pre = bd.buscar();

                for (modelo.Precio precio : pre) {
                    Object[] fila = new Object[6];

                    fila[0] = precio.getProveedor();
                    fila[1] = precio.getProducto().getMarca();
                    fila[2] = precio.getProducto().getTipo();
                    fila[3] = precio.getProducto().getFormato();
                    fila[4] = precio.getProducto().getMedida();
                    fila[5] = precio.getPrecio();

                    modelo.addRow(fila);
                }
                break;
                case "CMD_BACK":
                callWindow.setEnabled(true);
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        callWindow.setEnabled(true);
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
        
        DefaultComboBoxModel<modelo.Proveedor> empresa = (DefaultComboBoxModel) frmPre.cbxEmpresa.getModel();

        for(int i=empresa.getSize()-1;i>1;i--){
            empresa.removeElementAt(i);
        }

        ArrayList<modelo.Proveedor> pro = new ArrayList<>();
        pro = bdPro.buscar();

        for (modelo.Proveedor proveedor : pro) {
            empresa.addElement(proveedor);
        }
        modelo.BDProducto bdPrd = new modelo.BDProducto();

        DefaultComboBoxModel<String> marca = (DefaultComboBoxModel) frmPre.cbxMarca.getModel();
        DefaultComboBoxModel<String> tipo = (DefaultComboBoxModel) frmPre.cbxTipo.getModel();
        DefaultComboBoxModel<String> formato = (DefaultComboBoxModel) frmPre.cbxFormato.getModel();
        DefaultComboBoxModel<String> medida = (DefaultComboBoxModel) frmPre.cbxMedida.getModel();
        for(int i=marca.getSize()-1;i>1;i--){
            marca.removeElementAt(i);
        }
        for(int i=tipo.getSize()-1;i>1;i--){
            tipo.removeElementAt(i);
        }
        for(int i=formato.getSize()-1;i>1;i--){
            formato.removeElementAt(i);
        }
        for(int i=medida.getSize()-1;i>1;i--){
            medida.removeElementAt(i);
        }
        ArrayList<modelo.Producto> prd = new ArrayList<>();
        prd = bdPrd.buscar();
        
        for (modelo.Producto producto : prd) {
            marca.addElement(producto.getMarca());
            tipo.addElement(producto.getTipo());
            formato.addElement(producto.getFormato());
            medida.addElement(String.valueOf(producto.getMedida()));
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = frmPre.tblPrecio.getSelectedRow();

        Object emp = frmPre.tblPrecio.getModel().getValueAt(row, 0);
        Object mar = frmPre.tblPrecio.getModel().getValueAt(row, 1);
        Object tip = frmPre.tblPrecio.getModel().getValueAt(row, 2);
        Object form = frmPre.tblPrecio.getModel().getValueAt(row, 3);
        Object med = String.valueOf(frmPre.tblPrecio.getModel().getValueAt(row, 4));
        String pre = String.valueOf(frmPre.tblPrecio.getModel().getValueAt(row, 5));
        
        frmPre.cbxEmpresa.setSelectedItem(emp);
        frmPre.cbxMarca.setSelectedItem(mar);
        frmPre.cbxTipo.setSelectedItem(tip);
        frmPre.cbxFormato.setSelectedItem(form);
        frmPre.cbxMedida.setSelectedItem(med);
        frmPre.txtPrecio.setText(pre);
        frmPre.btnModificar.setEnabled(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
