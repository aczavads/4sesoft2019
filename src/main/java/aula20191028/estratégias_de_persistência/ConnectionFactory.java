package aula20191028.estratégias_de_persistência;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public static Connection createConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/4sesoft2019", "postgres","unicesumar");
			conn.setAutoCommit(false);
			return conn;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
