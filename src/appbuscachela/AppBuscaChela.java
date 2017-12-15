package appbuscachela;

public class AppBuscaChela {

    public static void main(String[] args) {
        modelo.BD.conectar();

        vista.FrmMain v = new vista.FrmMain();
        modelo.BDMain m = new modelo.BDMain();
        controlador.Main c = new controlador.Main(v, m);
        v.setVisible(true);
    }

}
