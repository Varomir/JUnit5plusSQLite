package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteConn {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void init() throws ClassNotFoundException, SQLException {
//        String relative_resources_path = "src/test/resources";
//        createFileOrRetrieve(relative_resources_path);
        String test_res = new File("src/test/resources").getAbsoluteFile().toString();
        String os_separator = FileSystems.getDefault().getSeparator();
        String db_name = "example.db";
        String db_absolute_path = test_res + os_separator + db_name;
//        final Path dir_path = Paths.get(db_full_path);
//        em.out.println(dir_path.toString());
        checkDirExists(test_res);

        Class.forName("org.sqlite.JDBC");
        String sqlite_conf = "jdbc:sqlite:" + db_absolute_path;

        connection = DriverManager.getConnection(sqlite_conf);
        statement = connection.createStatement();
        System.out.println("> DataBase connection open");
    }

    public static void createDB() throws SQLException {
        statement.execute("CREATE TABLE if not exists 'Users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", 'name' text, 'phone' INT);");
        System.out.println("Table created ow already exist.");

    }

    public static void writeDB() throws SQLException {
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Mike', 12543); ");
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Brendan', 321789); ");
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Leila', 456123); ");
        statement.execute("INSERT INTO 'Users' ('name', 'phone') VALUES ('Maria', 546312); ");
        System.out.println("Table filled");
    }

    public static List<String> executeSQL(String sql_statement) throws SQLException {
        resultSet = statement.executeQuery(sql_statement);
        List<String> column_name = new ArrayList<>();
        while (resultSet.next()) {
            column_name.add(resultSet.getString("name"));
        }
        System.out.println(">> Execute user provided custom SQL `" + sql_statement + "`");
        return column_name;
    }

    public static void readDB() throws SQLException {
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

    public static void close() throws SQLException {
        if (null != statement) {
            statement.close();
        }
        if (null != resultSet) {
            resultSet.close();
        }
        if (null != connection) {
            connection.close();
        }
        System.out.println("> All DataBase connections are closed");
    }

    private static void checkDirExists(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
    }

}
