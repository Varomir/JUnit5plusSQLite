package utils;

import java.sql.SQLException;

public class DB {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SQLiteConn.init();
        SQLiteConn.createDB();
        SQLiteConn.writeDB();
        SQLiteConn.readDB();
        SQLiteConn.close();
    }
}
