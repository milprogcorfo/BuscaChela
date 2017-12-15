package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModProducto implements ActionListener, WindowListener{
    vista.FrmModProducto mod;
    modelo.BDProducto bd;
    JFrame callWindow;
    String marca, formato, tipo;
    int medida;
    
    public ModProducto(vista.FrmModProducto mod, JFrame callWindow, modelo.BDProducto bd, String marca, String formato, int medida, String tipo){
        this.mod = mod;
        this.bd = bd;
        this.callWindow = callWindow;
        this.marca = marca;
        this.formato = formato;
        this.medida = medida;
        this.tipo = tipo;
        
        mod.txtMarca.setText(marca);
        mod.txtTipo.setText(tipo);
        mod.txtFormato.setText(formato);
        mod.txtMedida.setText(""+medida);
        
        mod.addWindowListener(this);
        
        mod.btnMod.addActionListener(this);
        mod.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        modelo.Producto aux = new modelo.Producto();
        switch(cmd){
            case "CMD_ADD":
                aux.setFormato(mod.txtFormato.getText());
                aux.setMarca(mod.txtMarca.getText());
                aux.setMedida(Integer.parseInt(mod.txtMedida.getText()));
                aux.setTipo(mod.txtTipo.getText());
                boolean res = bd.modificar(marca, formato, medida, tipo, aux);
                if(res){
                    JOptionPane.showMessageDialog(null, "Producto modificado", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Producto no pudo ser modificado", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
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
