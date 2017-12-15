package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDProducto extends BD {

    public int agregar(Producto p) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO producto (marca, formato, medida, tipo) VALUES (?,?,?,?)");
            st.setString(1, p.getMarca());
            st.setString(2, p.getFormato());
            st.setInt(3, p.getMedida());
            st.setString(4, p.getTipo());
            int res = st.executeUpdate();
            st.close();
            return res;
        } catch (SQLException se) {
            System.err.println("ERROR EXECUTE: " + se.getMessage());
        }
        return 0;
    }

    public ArrayList<Producto> buscar() {
        try {
            PreparedStatement st = con.prepareStatement("SELECT marca, formato, medida, tipo FROM producto ");

            ResultSet rs = st.executeQuery();
            ArrayList<Producto> resultado = new ArrayList<>();
            while (rs.next()) {
                Producto p = new Producto();
                p.setMarca(rs.getString("marca"));
                p.setFormato(rs.getString("formato"));
                p.setMedida(rs.getInt("medida"));
                p.setTipo(rs.getString("tipo"));
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

    public boolean eliminar(String marca, String formato, int medida, String tipo) {
        try {
            PreparedStatement sentencia = con.prepareStatement("DELETE FROM producto WHERE marca=? AND formato=? AND medida=? AND tipo=? ");
            sentencia.setString(1, marca);
            sentencia.setString(2, formato);
            sentencia.setInt(3, medida);
            sentencia.setString(4, tipo);
            int resultado = sentencia.executeUpdate();
            sentencia.close();
            if (resultado > 0) {
            return true;
            }
            return false;
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return false;
    }

    public boolean modificar(String marca, String formato, int medida, String tipo, Producto p) {
        try {
            PreparedStatement sentencia = con.prepareStatement("UPDATE producto SET marca=?, formato=?, medida=?, tipo=? WHERE marca=? AND formato=? AND medida=? AND tipo=?");

            sentencia.setString(1, p.getMarca());
            sentencia.setString(2, p.getFormato());
            sentencia.setInt(3, p.getMedida());
            sentencia.setString(4, p.getTipo());
            sentencia.setString(5, marca);
            sentencia.setString(6, formato);
            sentencia.setInt(7, medida);
            sentencia.setString(8, tipo);

            int resultado = sentencia.executeUpdate();
            sentencia.close();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException se) {
            System.err.println("ERROR UPDATE " + se.getMessage());
        }
        return false;
    }
}
