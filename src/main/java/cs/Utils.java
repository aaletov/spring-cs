package cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/hospitaldb";
        String user = "postgres";
        String passwd = "312125";
        return DriverManager.getConnection(url, user, passwd);
    }
}
