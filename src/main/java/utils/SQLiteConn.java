package utils;

import java.io.File;
import java.nio.file.FileSystems;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteConn {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private final String DB_NAME = "example.db";
    private final String DB_RELATIVE_PATH = "src/test/resources";

    public void init() throws ClassNotFoundException, SQLException {

        String test_resources = new File(DB_RELATIVE_PATH).getAbsoluteFile().toString();
        checkDirExists(test_resources);
        String os_separator = FileSystems.getDefault().getSeparator();
        String db_absolute_path = test_resources + os_separator + DB_NAME;

        Class.forName("org.sqlite.JDBC");
        String sqlite_conf = "jdbc:sqlite:" + db_absolute_path;

        connection = DriverManager.getConnection(sqlite_conf);
        statement = connection.createStatement();
        System.out.println("> DataBase connection open");
    }

    public void createDB() throws SQLException {
        statement.execute("CREATE TABLE if not exists 'Users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", 'name' text, 'phone' INT);");
        System.out.println("Table created ow already exist.");

    }

    public void writeDB() throws SQLException {
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Mike', 12543); ");
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Brendan', 321789); ");
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Leila', 456123); ");
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Maria', 546312); ");
        System.out.println("Table filled");
    }

    public List<String> executeSQL(String sql_statement) throws SQLException {
        resultSet = statement.executeQuery(sql_statement);
        List<String> column_name = new ArrayList<>();
        while (resultSet.next()) {
            column_name.add(resultSet.getString("name"));
        }
        System.out.println(">> Execute user provided custom SQL `" + sql_statement + "`");
        return column_name;
    }

    public void readDB() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM Users");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            System.out.println("ID = " + id);
            System.out.println("name = " + name);
            System.out.println("phone = " + phone + "\n");
        }
        System.out.println("Table selected");
    }

    public void close() throws SQLException {
        if (null != statement) statement.close();
        if (null != resultSet) resultSet.close();
        if (null != connection) connection.close();
        System.out.println("> All DataBase connections are closed");
    }

    private static void checkDirExists(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
    }

}
