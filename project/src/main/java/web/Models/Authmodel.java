package web.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authmodel {
	public static boolean authenticate(String email, String password) {
        String url = "jdbc:mysql://localhost:3306/app_java_ee";
        String user = "root";
        String pwd = "";
        String sql = "SELECT idUsers FROM users WHERE Email=? AND Password=?";
        
        try {
            // Charger le pilote JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établir la connexion à la base de données
            try (Connection con = DriverManager.getConnection(url, user, pwd);
                 PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, email);
                pst.setString(2, password);
                
                try (ResultSet rs = pst.executeQuery()) {
                    // Si une ligne est retournée, les informations d'identification sont valides
                    return rs.next();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return false;
        }
    }
}
