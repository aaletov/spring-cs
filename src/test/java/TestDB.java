import cs.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDB {
    private static Connection connection;
    private static Utils utils;

    @Before
    public void init() throws SQLException {
        utils = new Utils();
        connection = utils.getNewConnection();
    }

    @Test
    public void createAndDrop() throws SQLException {
        utils.createCSTables(connection);
        utils.dropCSTables(connection);
    }

    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try(Connection connection = utils.getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }
}
