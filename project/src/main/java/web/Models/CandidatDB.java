package web.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatDB {
	
		private static final String URL = "jdbc:mysql://localhost:3306/app_java_ee";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

		private static final String INSERT_CANDIDATS_SQL = "INSERT INTO candidats" + "  (Name, Surname, Position) VALUES "
				+ " (?, ?, ?);";

		private static final String SELECT_USER_BY_ID = "select IdCandidat,Name,Surname,Position from candidats where IdCandidat =?";
		private static final String SELECT_ALL_USERS = "select * from candidats";
		private static final String DELETE_USERS_SQL = "delete from candidats where idCandidat = ?;";
		private static final String UPDATE_USERS_SQL = "update candidats set Name = ?,Surname= ?, Position =? where IdCandidat = ?;";

		public CandidatDB() {
		}

		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}

		public void insertCandidat(Candidat candidat) throws SQLException {
			System.out.println(INSERT_CANDIDATS_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CANDIDATS_SQL)) {
				preparedStatement.setString(1, candidat.getName());
				preparedStatement.setString(2, candidat.getSurname());
				preparedStatement.setString(3, candidat.getPosition());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		public Candidat selectCandidat(int IdCandidat) {
			Candidat candidat = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
				preparedStatement.setInt(1, IdCandidat);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String Name = rs.getString("Name");
					String Surname = rs.getString("Surname");
					String Position = rs.getString("Position");
					candidat = new Candidat(IdCandidat, Name, Surname, Position);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return candidat;
		}

		public List<Candidat> selectAllCandidats() {
		    List<Candidat> candidats = new ArrayList<>();
		    try (Connection connection = getConnection()) {
		        if (connection != null) {
		            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
		                System.out.println(preparedStatement);
		                try (ResultSet rs = preparedStatement.executeQuery()) {
		                    while (rs.next()) {
		                        int IdCandidat = rs.getInt("IdCandidat");
		                        String Name = rs.getString("Name");
		                        String Surname = rs.getString("Surname");
		                        String Position = rs.getString("Position");
		                        candidats.add(new Candidat(IdCandidat, Name, Surname, Position));
		                    }
		                }
		            }
		        }
		    } catch (SQLException e) {
		        printSQLException(e);
		    }
		    return candidats;
		}

		public boolean deleteCandidat(int IdCandidat) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
				statement.setInt(1, IdCandidat);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}

		public boolean updateCandidat(Candidat candidat) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				System.out.println("updated Candidat:"+statement);
				statement.setString(1, candidat.getName());
				statement.setString(2, candidat.getSurname());
				statement.setString(3, candidat.getPosition());
				statement.setInt(4, candidat.getIdCandidat());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}

		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
}
