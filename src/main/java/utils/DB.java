package utils;

import java.sql.SQLException;

public class DB {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SQLiteConn inMemoryDB = new SQLiteConn();

        inMemoryDB.init();
        inMemoryDB.createDB();
        inMemoryDB.writeDB();
        inMemoryDB.readDB();
        inMemoryDB.close();
    }
}
