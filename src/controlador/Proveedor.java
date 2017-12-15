package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Proveedor implements ActionListener, WindowListener, MouseListener, KeyListener{
    
    vista.FrmProveedor frmPro;
    modelo.BDProveedor bd;
    JFrame callWindow;
    
    public Proveedor(vista.FrmProveedor frmPro, modelo.BDProveedor bd, JFrame callWindow){
        this.frmPro = frmPro;
        this.bd = bd;
        this.callWindow = callWindow;
        
        frmPro.addWindowListener(this);
        
        frmPro.txtEmpresa.addKeyListener(this);
        frmPro.cbxComuna.addKeyListener(this);
        frmPro.txtDireccion.addKeyListener(this);
        
        frmPro.btnBuscar.addActionListener(this);
        frmPro.btnEliminar.addActionListener(this);
        frmPro.btnGuardar.addActionListener(this);
        frmPro.btnModificar.addActionListener(this);
        frmPro.btnVolver.addActionListener(this);
        frmPro.btnLimpiar.addActionListener(this);
        
        frmPro.btnModificar.setEnabled(false);
        
        frmPro.tblProveedor.addMouseListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        boolean res;
        modelo.Proveedor aux = new modelo.Proveedor();
                
        switch (cmd) {
            case "CMD_ADD":
                aux.setEmpresa(frmPro.txtEmpresa.getText());
                aux.setComuna(String.valueOf(frmPro.cbxComuna.getSelectedItem()));
                aux.setDireccion(frmPro.txtDireccion.getText());
                aux.setTelefono(frmPro.txtTelefono.getText());
                aux.setLatitud(Double.parseDouble(frmPro.txtLatitud.getText()));
                aux.setLongitud(Double.parseDouble(frmPro.txtLongitud.getText()));
                aux.setDescripcion(frmPro.txaDescripcion.getText());
                int resaux = bd.agregar(aux);
                if(resaux==1){
                    JOptionPane.showMessageDialog(null, "Proveedor agregado", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                    this.frmPro.txtEmpresa.setText("");
                    this.frmPro.cbxComuna.setSelectedIndex(0);
                    this.frmPro.txtDireccion.setText("");
                    this.frmPro.txtTelefono.setText("");
                    this.frmPro.txtLatitud.setText("");
                    this.frmPro.txtLongitud.setText("");
                    this.frmPro.txaDescripcion.setText("");
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Proveedor no pudo ser agregado", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CMD_DEL":
                int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el proveedor?", "Mensaje de Sistema", JOptionPane.YES_NO_OPTION);
                if (op!=1){
                    String emp = frmPro.txtEmpresa.getText();
                    String com = String.valueOf(frmPro.cbxComuna.getSelectedItem());
                    String dir = frmPro.txtDireccion.getText();
                    res = bd.eliminar(emp, com, dir);
                    if(res){
                        JOptionPane.showMessageDialog(null, "Proveedor eliminado", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Proveedor no pudo ser eliminado", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "CMD_MOD":
                String emp = frmPro.txtEmpresa.getText();
                String com = String.valueOf(frmPro.cbxComuna.getSelectedItem());
                String dir = frmPro.txtDireccion.getText();
                String tel = frmPro.txtTelefono.getText();
                String lat = String.valueOf(frmPro.txtLatitud.getText());
                String lon = String.valueOf(frmPro.txtLongitud.getText());
                String des = frmPro.txaDescripcion.getText();
                vista.FrmModProveedor mod = new vista.FrmModProveedor();
                controlador.ModProveedor modP = new controlador.ModProveedor(mod, frmPro, bd, emp, com, dir, tel, lat, lon, des);
                frmPro.setEnabled(false);
                mod.setVisible(true);
                break;
            case "CMD_FIND":
                ArrayList<modelo.Proveedor> pro;
                DefaultTableModel modelo = (DefaultTableModel)frmPro.tblProveedor.getModel();
                modelo.setRowCount(0);
                pro = bd.buscar();
                
                for(modelo.Proveedor proveedor:pro){
                    Object[] fila = new Object[7];
                    
                    fila[0] = proveedor.getEmpresa();
                    fila[1] = proveedor.getComuna();
                    fila[2] = proveedor.getDireccion();
                    fila[3] = proveedor.getTelefono();
                    fila[4] = proveedor.getLatitud();
                    fila[5] = proveedor.getLongitud();
                    fila[6] = proveedor.getDescripcion();
                    
                    modelo.addRow(fila);
                }
                break;
            case "CMD_BACK":
                callWindow.setEnabled(true);
                break;
            case "CMD_CLEAR":
                this.frmPro.txtEmpresa.setText("");
                this.frmPro.cbxComuna.setSelectedIndex(0);
                this.frmPro.txtDireccion.setText("");
                this.frmPro.txtTelefono.setText("");
                this.frmPro.txtLatitud.setText("");
                this.frmPro.txtLongitud.setText("");
                this.frmPro.txaDescripcion.setText("");
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
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = frmPro.tblProveedor.getSelectedRow();
        
        String emp = (String)frmPro.tblProveedor.getModel().getValueAt(row, 0);
        String com = (String)frmPro.tblProveedor.getModel().getValueAt(row, 1);
        String dir = (String)frmPro.tblProveedor.getModel().getValueAt(row, 2);
        String tel = (String)frmPro.tblProveedor.getModel().getValueAt(row, 3);
        String lat = String.valueOf(frmPro.tblProveedor.getModel().getValueAt(row, 4));
        String lon = String.valueOf(frmPro.tblProveedor.getModel().getValueAt(row, 5));
        String des = (String)frmPro.tblProveedor.getModel().getValueAt(row, 6);
        frmPro.txtEmpresa.setText(emp);
        frmPro.cbxComuna.setSelectedItem(com);
        frmPro.txtDireccion.setText(dir);
        frmPro.txtTelefono.setText(tel);
        frmPro.txtLatitud.setText(lat);
        frmPro.txtLongitud.setText(lon);
        frmPro.txaDescripcion.setText(des);
        frmPro.btnModificar.setEnabled(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        frmPro.btnModificar.setEnabled(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
