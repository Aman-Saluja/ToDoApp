import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	Connection con = null;
	java.sql.PreparedStatement pst;

	public static Connection dbconnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connecting to GCP Database
			// Add your own connection here

			return conn;
		} catch (Exception e2) {
			System.out.println("THE FOLLOWING EXCEPTION OCCURED" + e2);
			return null;
		}
	}

}
