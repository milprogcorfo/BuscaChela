package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModProveedor implements ActionListener, WindowListener {

    vista.FrmModProveedor mod;
    modelo.BDProveedor bd;
    JFrame callWindow;
    String empresa, comuna, direccion, telefono, latitud, longitud, descripcion;

    public ModProveedor(vista.FrmModProveedor mod, JFrame callWindow, modelo.BDProveedor bd, String empresa, String comuna, String direccion, String telefono, String latitud, String longitud, String descripcion) {
        this.mod = mod;
        this.bd = bd;
        this.callWindow = callWindow;
        this.empresa = empresa;
        this.comuna = comuna;
        this.direccion = direccion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        
        mod.txtEmpresa.setText(empresa);
        mod.cbxComuna.setSelectedItem(comuna);
        mod.txtDireccion.setText(direccion);
        mod.txtTelefono.setText(telefono);
        mod.txtLatitud.setText(latitud);
        mod.txtLongitud.setText(longitud);
        mod.txaDescripcion.setText(descripcion);
        
        mod.addWindowListener(this);

        mod.btnGuardar.addActionListener(this);
        mod.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        modelo.Proveedor aux = new modelo.Proveedor();
        switch (cmd) {
            case "CMD_ADD":
                aux.setEmpresa(mod.txtEmpresa.getText());
                aux.setComuna(String.valueOf(mod.cbxComuna.getSelectedItem()));
                aux.setDireccion(mod.txtDireccion.getText());
                aux.setTelefono(mod.txtTelefono.getText());
                aux.setLatitud(Double.parseDouble(mod.txtLatitud.getText()));
                aux.setLongitud(Double.parseDouble(mod.txtLongitud.getText()));
                aux.setDescripcion(mod.txaDescripcion.getText());
                
                boolean res = bd.modificar(empresa, comuna, direccion, aux);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Proveedor modificado", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Proveedor no pudo ser modificado", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
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
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
