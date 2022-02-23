import cs.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static cs.Utils.getNewConnection;
import static cs.Utils.executeUpdate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDB {
    private static Connection connection;

    @Before
    public void init() throws SQLException {
        connection = getNewConnection();
    }

    @Test
    public void createAndDrop() throws SQLException {
        Utils.createCSTables(connection);
        Utils.dropCSTables(connection);
    }

    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try(Connection connection = getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }
}
