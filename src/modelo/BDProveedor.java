package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDProveedor extends BD  {
   
    
    public int agregar(Proveedor p){
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO local (local, comuna, direccion, telefono, latitud, longitud, descripcion) VALUES (?,?,?,?,?,?,?)");

            st.setString(1, p.getEmpresa());
            st.setString(2, p.getComuna());
            st.setString(3, p.getDireccion());
            st.setString(4, p.getTelefono());
            st.setDouble(5, p.getLatitud());
            st.setDouble(6, p.getLongitud());
            st.setString(7, p.getDescripcion());
            
            int res = st.executeUpdate();
            st.close();
            return res;
        }catch(SQLException se){
            System.err.println("ERROR EXECUTE: " + se.getMessage());
        }
        return 0;
    }
    
    public ArrayList<Proveedor> buscar(){
        try {
            PreparedStatement st = con.prepareStatement("SELECT local, comuna, direccion, telefono, latitud, longitud, descripcion FROM local ");

            ResultSet rs = st.executeQuery();
            ArrayList<Proveedor> resultado = new ArrayList<>();
            while(rs.next()){
                Proveedor p = new Proveedor();
                p.setEmpresa(rs.getString("local"));
                p.setComuna(rs.getString("comuna"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                p.setLatitud(rs.getDouble("latitud"));
                p.setLongitud(rs.getDouble("longitud"));
                p.setDescripcion(rs.getString("descripcion"));
                resultado.add(p);
            }
            rs.close();
            st.close();
            return resultado;    
        } catch (SQLException se){
            System.err.println("ERROR SELECT " + se.getMessage());
        }
                
        return null;
    }
    
    public boolean eliminar(String empresa, String comuna, String direccion){
         try {
            PreparedStatement sentencia = con.prepareStatement("DELETE FROM local WHERE local=? AND comuna=? AND direccion=? ");
            sentencia.setString(1, empresa);
            sentencia.setString(2, comuna);
            sentencia.setString(3, direccion);
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
    
    public boolean modificar(String empresa, String comuna, String direccion, Proveedor p){
        try{
            PreparedStatement sentencia = con.prepareStatement("UPDATE local SET local=?, comuna=?, direccion=?, telefono=?, latitud=?, longitud=?, descripcion=? WHERE local=? AND comuna=? AND direccion=?");
            
            sentencia.setString(1, p.getEmpresa());
            sentencia.setString(2, p.getComuna());
            sentencia.setString(3, p.getDireccion());
            sentencia.setString(4, p.getTelefono());
            sentencia.setDouble(5, p.getLatitud());
            sentencia.setDouble(6, p.getLongitud());
            sentencia.setString(7, p.getDescripcion());
            sentencia.setString(8, empresa);
            sentencia.setString(9, comuna);
            sentencia.setString(10, direccion);
            
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
