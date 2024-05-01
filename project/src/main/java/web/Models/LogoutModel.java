package web.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogoutModel {
	private static final String URL = "jdbc:mysql://localhost:3306/app_java_ee";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void addUser(String name, String surname, String date, String email, String password) throws SQLException {
    	
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (Name, Surname, Date, Email, Password) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, date);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.executeUpdate();
        }
    }
}
