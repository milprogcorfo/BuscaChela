package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static modelo.BD.con;

public class BDMain extends BD {
    
    public ArrayList<Precio> buscarLocal(String local){
        try {
            PreparedStatement st = con.prepareStatement("SELECT l.local, l.direccion, p.marca, p.formato, p.medida, p.tipo, o.precio FROM local l, producto p, oferta o WHERE l.local = ? AND p.idProducto = o.producto_idProducto AND l.idLocal = o.local_idLocal");
            st.setString(1, local);
            ResultSet rs = st.executeQuery();
            ArrayList<Precio> resultado = new ArrayList<>();
            while (rs.next()) {
                Precio p = new Precio();
                p.getProveedor().setEmpresa(rs.getString("local"));
                p.getProveedor().setDireccion(rs.getString("direccion"));
                p.getProducto().setMarca(rs.getString("marca"));
                p.getProducto().setFormato(rs.getString("formato"));
                p.getProducto().setMedida(rs.getInt("medida"));
                p.getProducto().setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getInt("precio"));
                resultado.add(p);
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return null;
    }
    
    public ArrayList<Precio> buscarMarca(String marca){
        try {
            PreparedStatement st = con.prepareStatement("SELECT l.local, l.direccion, p.marca, p.formato, p.medida, p.tipo, o.precio FROM local l, producto p, oferta o WHERE p.marca = ? AND p.idProducto = o.producto_idProducto AND l.idLocal = o.local_idLocal");
            st.setString(1, marca);
            ResultSet rs = st.executeQuery();
            ArrayList<Precio> resultado = new ArrayList<>();
            while (rs.next()) {
                Precio p = new Precio();
                p.getProveedor().setEmpresa(rs.getString("local"));
                p.getProveedor().setDireccion(rs.getString("direccion"));
                p.getProducto().setMarca(rs.getString("marca"));
                p.getProducto().setFormato(rs.getString("formato"));
                p.getProducto().setMedida(rs.getInt("medida"));
                p.getProducto().setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getInt("precio"));
                resultado.add(p);
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return null;
    } 
}
