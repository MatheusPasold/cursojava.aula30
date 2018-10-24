package aula30;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";
	private static final String URL = "jdbc:postgresql://localhost:5432/curso";
	private static Connection connection;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("N�o foi poss�vel carregar o driver do banco de dados");
		}
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel criar uma conex�o com o banco de dados");
				throw e;
			}
		}
		return connection;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println("OIIIII, vc � lindo");
		Connection con = getConnection();
		if (con != null) {
			System.out.println("Teste Conex�o realizado com sucesso");
			con.close();
		} else {
			System.out.println("Deu pau![:(]");
		}
	}
}