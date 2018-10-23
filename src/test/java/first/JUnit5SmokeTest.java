package first;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SQLiteConn;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnit5SmokeTest {

    @DisplayName("Smoke check for in-memory DataBase content")
    @Test
    void justAnExample() throws SQLException, ClassNotFoundException {

        SQLiteConn db = new SQLiteConn();
        db.init();
        List<String> actual_res = db.executeSQL("SELECT * FROM Users");
        db.close();

        assertEquals(4, actual_res.size());
        assertAll("Users",
                () -> assertEquals(actual_res.get(0), "Mike"),
                () -> assertEquals(actual_res.get(1), "Brendan"),
                () -> assertEquals(actual_res.get(2), "Leila"),
                () -> assertEquals(actual_res.get(3), "Maria")
        );
    }

}
