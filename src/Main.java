import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBconnector.conn();
            DBconnector.createTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AutorizationWindow window = new AutorizationWindow();
        window.run();
    }
}
