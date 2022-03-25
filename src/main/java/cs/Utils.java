package cs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class Utils {

    @Value("${spring.datasource.url}")
    private String appUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    public Connection getNewConnection() throws SQLException {
        String url = appUrl;
        String user = userName;
        String passwd = password;
        return DriverManager.getConnection(url, user, passwd);
    }

    public int executeUpdate(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

}
