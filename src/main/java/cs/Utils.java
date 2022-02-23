package cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/hospitaldb";
        String user = "postgres";
        String passwd = "312125";
        return DriverManager.getConnection(url, user, passwd);
    }

    public static int executeUpdate(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        // Для Insert, Update, Delete
        int result = statement.executeUpdate(query);
        return result;
    }
    public static void createCSTables(Connection connection) throws SQLException {
        String createDiagnosisTableQuery = "CREATE TABLE DIAGNOSIS (" +
                "ID integer primary key," +
                "NAME varchar(50)" +
                ");";
        Utils.executeUpdate(connection, createDiagnosisTableQuery);
        String createWardsTableQuery = "CREATE TABLE WARDS (" +
                "ID integer primary key," +
                "NAME varchar(50)," +
                "MAX_COUNT integer" +
                ");";
        Utils.executeUpdate(connection, createWardsTableQuery);
        String createPeopleTableQuery = "CREATE TABLE PEOPLE (" +
                "ID integer primary key," +
                "FIRST_NAME varchar(20)," +
                "LAST_NAME varchar(20)," +
                "PATHER_NAME varchar(20)," +
                "DIAGNOSIS_ID integer references DIAGNOSIS(ID)," +
                "WARD_ID integer references WARDS(ID)" +
                ");";
        Utils.executeUpdate(connection, createPeopleTableQuery);
    }
    public static void dropCSTables(Connection connection) throws SQLException {
        String dropPeopleTableQuery = "drop table PEOPLE;";
        Utils.executeUpdate(connection, dropPeopleTableQuery);
        String dropDiagnosisTableQuery = "drop table DIAGNOSIS;";
        Utils.executeUpdate(connection, dropDiagnosisTableQuery);
        String dropWardsTableQuery = "drop table WARDS;";
        Utils.executeUpdate(connection, dropWardsTableQuery);
    }
}
