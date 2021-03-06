import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
        createNewsTable();
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

        System.out.println("Таблица users создана или уже существует.");
    }

    private static void createNewsTable() throws ClassNotFoundException, SQLException {
        statmt.execute("CREATE TABLE IF NOT EXISTS news (\n" +
                "    id        INTEGER       PRIMARY KEY AUTOINCREMENT\n" +
                "                            UNIQUE\n" +
                "                            NOT NULL,\n" +
                "    title     VARCHAR (100) NOT NULL,\n" +
                "    text      TEXT          NOT NULL,\n" +
                "    user_id   INTEGER       REFERENCES users (id),\n" +
                "    private   BOOLEAN       DEFAULT (0),\n" +
                "    date_time DATETIME      NOT NULL\n" +
                ");");

        System.out.println("Таблица news создана или уже существует.");
    }

    public static void addUser(User user) throws SQLException {
        statmt.execute(user.getInsertQuery());
    }

    public static void addNews(News news) throws SQLException {
        statmt.execute(news.getInsertQuery());
    }

    public static User autorization(String login, String password) throws SQLException {
        String query = "SELECT * FROM users " +
                "WHERE login = '" + login + "' AND " +
                "password = '" + password + "';";
        System.out.println(query);
        resSet = statmt.executeQuery(query);
        User user = new User(resSet);
        return user;
    }

    public static ArrayList<News> getNews(User user) throws SQLException {
        String query = "SELECT * FROM news\n" +
                "WHERE private = 0 OR user_id = " + user.getId();
        System.out.println(query);
        resSet = statmt.executeQuery(query);
        ArrayList<News> list = new ArrayList<>();
        while(resSet.next())
            list.add(new News(resSet));
        return list;
    }

}
