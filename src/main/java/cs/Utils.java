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
    public void createCSTables(Connection connection) throws SQLException {
        System.out.println("Creating tables");
        String createDiagnosisTableQuery = "CREATE TABLE DIAGNOSIS (" +
                "ID integer primary key," +
                "NAME varchar(50)" +
                ");";
        this.executeUpdate(connection, createDiagnosisTableQuery);
        String createWardsTableQuery = "CREATE TABLE WARDS (" +
                "ID integer primary key," +
                "NAME varchar(50)," +
                "MAX_COUNT integer" +
                ");";
        this.executeUpdate(connection, createWardsTableQuery);
        String createPeopleTableQuery = "CREATE TABLE PEOPLE (" +
                "ID integer primary key," +
                "FIRST_NAME varchar(20)," +
                "LAST_NAME varchar(20)," +
                "PATHER_NAME varchar(20)," +
                "DIAGNOSIS_ID integer references DIAGNOSIS(ID)," +
                "WARD_ID integer references WARDS(ID)" +
                ");";
        this.executeUpdate(connection, createPeopleTableQuery);
    }
    public void dropCSTables(Connection connection) throws SQLException {
        System.out.println("Dropping tables");
        String dropPeopleTableQuery = "drop table PEOPLE;";
        this.executeUpdate(connection, dropPeopleTableQuery);
        String dropDiagnosisTableQuery = "drop table DIAGNOSIS;";
        this.executeUpdate(connection, dropDiagnosisTableQuery);
        String dropWardsTableQuery = "drop table WARDS;";
        this.executeUpdate(connection, dropWardsTableQuery);
    }
}
