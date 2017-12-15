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

public class Producto implements ActionListener, WindowListener, MouseListener, KeyListener {

    vista.FrmProducto frmPro;
    modelo.BDProducto bd;
    JFrame callWindow;

    public Producto(vista.FrmProducto frmPro, modelo.BDProducto bd, JFrame callWindow) {
        this.frmPro = frmPro;
        this.bd = bd;
        this.callWindow = callWindow;

        frmPro.addWindowListener(this);

        frmPro.txtMarca.addKeyListener(this);
        frmPro.txtFormato.addKeyListener(this);
        frmPro.txtMedida.addKeyListener(this);
        frmPro.txtTipo.addKeyListener(this);

        frmPro.btnBuscar.addActionListener(this);
        frmPro.btnEliminar.addActionListener(this);
        frmPro.btnGuardar.addActionListener(this);
        frmPro.btnMod.addActionListener(this);
        frmPro.btnVolver.addActionListener(this);
        frmPro.btnLimpiar.addActionListener(this);


        frmPro.btnMod.setEnabled(false);

        frmPro.tblCerveza.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        boolean res;
        modelo.Producto aux = new modelo.Producto();
        switch (cmd) {
            case "CMD_ADD":
                aux.setMarca(frmPro.txtMarca.getText());
                aux.setMedida(Integer.parseInt(frmPro.txtMedida.getText()));
                aux.setFormato(frmPro.txtFormato.getText());
                aux.setTipo(frmPro.txtTipo.getText());
                int resaux = bd.agregar(aux);
                if (resaux == 1) {
                    JOptionPane.showMessageDialog(null, "Producto agregado", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                    this.frmPro.txtFormato.setText("");
                    this.frmPro.txtMarca.setText("");
                    this.frmPro.txtMedida.setText("");
                    this.frmPro.txtTipo.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no pudo ser agregado", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CMD_FIND":
                ArrayList<modelo.Producto> pro;
                DefaultTableModel modelo = (DefaultTableModel) frmPro.tblCerveza.getModel();
                modelo.setRowCount(0);
                pro = bd.buscar();

                for (modelo.Producto producto : pro) {
                    Object[] fila = new Object[4];

                    fila[0] = producto.getMarca();
                    fila[1] = producto.getTipo();
                    fila[2] = producto.getFormato();
                    fila[3] = producto.getMedida();
                    modelo.addRow(fila);
                }
                break;
            case "CMD_DEL":
                int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el producto?", "Mensaje de Sistema", JOptionPane.YES_NO_OPTION);
                if (op != 1) {
                    String mar = frmPro.txtMarca.getText();
                    String form = frmPro.txtFormato.getText();
                    int med = Integer.parseInt(frmPro.txtMedida.getText());
                    String tip = frmPro.txtTipo.getText();
                    res = bd.eliminar(mar, form, med, tip);
                    if (res) {
                        JOptionPane.showMessageDialog(null, "Producto eliminado", "Mensaje de Sistema", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no pudo ser eliminado", "Mensaje de Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "CMD_MOD":
                String mar = frmPro.txtMarca.getText();
                String form = frmPro.txtFormato.getText();
                int med = Integer.parseInt(frmPro.txtMedida.getText());
                String tip = frmPro.txtTipo.getText();
                vista.FrmModProducto mod = new vista.FrmModProducto();
                controlador.ModProducto modP = new controlador.ModProducto(mod, frmPro, bd, mar, form, med, tip);
                frmPro.setEnabled(false);
                mod.setVisible(true);
                break;
            case "CMD_BACK":
                callWindow.setEnabled(true);
                break;
            case "CMD_CLEAR":
                this.frmPro.txtFormato.setText("");
                this.frmPro.txtMarca.setText("");
                this.frmPro.txtMedida.setText("");
                this.frmPro.txtTipo.setText("");
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
        int row = frmPro.tblCerveza.getSelectedRow();

        String mar = (String) frmPro.tblCerveza.getModel().getValueAt(row, 0);
        String tip = (String) frmPro.tblCerveza.getModel().getValueAt(row, 1);
        String form = (String) frmPro.tblCerveza.getModel().getValueAt(row, 2);
        String med = String.valueOf(frmPro.tblCerveza.getModel().getValueAt(row, 3));

        frmPro.txtMarca.setText(mar);
        frmPro.txtTipo.setText(tip);
        frmPro.txtFormato.setText(form);
        frmPro.txtMedida.setText(med);
        frmPro.btnMod.setEnabled(true);

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

    @Override
    public void keyTyped(KeyEvent e) {
        frmPro.btnMod.setEnabled(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
