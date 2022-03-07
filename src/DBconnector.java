import java.sql.*;

public class DBconnector {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void conn() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:db/users.db");
        statmt = conn.createStatement();
        System.out.println("Connection to db is OK!");
    }

    public static void createTables() throws ClassNotFoundException, SQLException {
        createUserTable();
    }

    private static void createUserTable() throws ClassNotFoundException, SQLException {
        statmt.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                "    id         INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                            NOT NULL\n" +
                "                            UNIQUE,\n" +
                "    login      VARCHAR (25) UNIQUE\n" +
                "                            NOT NULL,\n" +
                "    password   VARCHAR (25) NOT NULL,\n" +
                "    name       VARCHAR (50) NOT NULL,\n" +
                "    age        INTEGER,\n" +
                "    profession VARCHAR (30) DEFAULT No_Profession\n" +
                ");");

        System.out.println("Таблица создана или уже существует.");
    }

    public static void addUser(User user) throws SQLException {
        statmt.execute(user.getInsertQuery());
    }

    public static boolean autorization(String login, String password) throws SQLException {
        String query = "SELECT * FROM users " +
                "WHERE 'login' = '" + login + "' AND " +
                "'password' = '" + password + "';";
        System.out.println(query);
            resSet = statmt.executeQuery(query);
            int i = 0;
            while(resSet.next()) {
                i++;
            }
            return i == 1;
    }


}
