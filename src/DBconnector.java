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

    public static boolean autorization(String login, String password) throws SQLException {
        String query = "SELECT * FROM users " +
                "WHERE login = '" + login + "' AND " +
                "password = '" + password + "';";
        System.out.println(query);
        resSet = statmt.executeQuery(query);
        int i = 0;
        while(resSet.next()) {
            i++;
        }
        return i == 1;
    }

    public static ArrayList<News> getNews() throws SQLException {
        String query = "SELECT * FROM news;";
        System.out.println(query);
        resSet = statmt.executeQuery(query);
        int i = 0;
        ArrayList<News> list = new ArrayList<>();
        while(resSet.next())
            list.add(new News(resSet));
        return list;
        //        News[] ans = {
//                new News(1, "title1", "text1", 1, false, LocalDate.now()),
//                new News(2, "title2", "text2", 2, true, LocalDate.now()),
//                new News(3, "title3", "text3", 3, false, LocalDate.now()),
//                new News(4, "title4", "text4", 2, true, LocalDate.now()),
//                new News(5, "title5", "text5", 1, false, LocalDate.now())
//        };
    }

}
