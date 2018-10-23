package first;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.SQLiteConn;

import java.sql.SQLException;

public class BaseTest {

    protected static SQLiteConn db;

    @BeforeAll
    private static void setup() {
        db = new SQLiteConn();
        try {
            db.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    private static void tearDown() {
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
