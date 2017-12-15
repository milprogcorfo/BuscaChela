package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
    static Connection con;
    
    public static boolean conectar(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/buscachela", "root", "");
            return true;
        } catch(SQLException se){
            System.err.println("ERROR CONNECTION: " + se.getMessage());
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se2){
                System.err.println("ERROR CLOSE CONN: " + se.getMessage());
            }
        }
        return false;
    }
}
