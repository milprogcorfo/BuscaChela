package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDPrecio extends BD {
    
    public int agregar(Precio p) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO oferta (precio, producto_idProducto, local_idLocal) VALUES (?,?,?)");
            st.setDouble(1, p.getPrecio());
            st.setInt(2, p.getIdProducto());
            st.setInt(3, p.getIdLocal());
            int res = st.executeUpdate();
            st.close();
            return res;
        } catch (SQLException se) {
            System.err.println("ERROR EXECUTE: " + se.getMessage());
        }
        return 0;
    }
    
    public int buscarIdLocal(modelo.Proveedor prov){
        try {
            PreparedStatement st = con.prepareStatement("SELECT idLocal FROM local WHERE direccion = ?");
            st.setString(1, prov.getDireccion());
            ResultSet rs = st.executeQuery();
            int resultado = 0;
            while(rs.next()){
                resultado = rs.getInt("idLocal");
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        
        return -1;
    }
    
    public int buscarIdProducto(String marca, String tipo, String formato, int medida){
        try {
            PreparedStatement st = con.prepareStatement("SELECT idProducto FROM producto WHERE marca=? AND tipo=? AND formato=? AND medida=?");
            st.setString(1, marca);
            st.setString(2, tipo);
            st.setString(3, formato);
            st.setInt(4, medida);
            ResultSet rs = st.executeQuery();
            int resultado =0;
            while(rs.next()){
                resultado = rs.getInt("idProducto");
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        
        return -1;
    }
    
    public ArrayList<Precio> buscar() {
        try {
            PreparedStatement st = con.prepareStatement("SELECT l.local, l.direccion, l.comuna, p.marca, p.formato, p.medida, p.tipo, o.precio FROM local l, producto p, oferta o WHERE p.idProducto= o.producto_idProducto AND l.idLocal=o.local_idLocal");

            ResultSet rs = st.executeQuery();
            ArrayList<Precio> resultado = new ArrayList<>();
            while (rs.next()) {
                Precio p = new Precio();
                p.getProveedor().setEmpresa(rs.getString("local"));
                p.getProveedor().setDireccion(rs.getString("direccion"));
                p.getProveedor().setComuna(rs.getString("comuna"));
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
    
    public boolean eliminar(int producto, int local){
         try {
            PreparedStatement sentencia = con.prepareStatement("DELETE FROM oferta WHERE producto_idProducto=? AND local_idLocal=? ");
            sentencia.setInt(1, producto);
            sentencia.setInt(2, local);
            int resultado = sentencia.executeUpdate();
            sentencia.close();
            if (resultado>0)
                return true;
            return false;
        } catch (SQLException se){
            System.err.println("ERROR SELECT " + se.getMessage());
        }                
        return false;
    }
    
    public boolean modificar(int producto, int local, Precio p){
        try{
            PreparedStatement sentencia = con.prepareStatement("UPDATE oferta SET precio=? WHERE producto_idProducto=? AND local_idLocal=?");
            
            sentencia.setDouble(1, p.getPrecio());
            sentencia.setInt(2, producto);
            sentencia.setInt(3, local);
             
            int resultado = sentencia.executeUpdate();            
            sentencia.close();
            if (resultado>0)
                return true;
            else
                return false;
        } catch(SQLException se){
            System.err.println("ERROR UPDATE " + se.getMessage());            
        }
        return false;
    }
}
