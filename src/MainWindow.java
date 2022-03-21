import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainWindow extends JFrame implements addNewsCallback{

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private JPanel panel;
    private User currentUser;
    JTable table;
    TableModel model;

    public MainWindow(User user){
        super();
        this.currentUser = user;
        setTitle("Главное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addWidgets();
        getContentPane().add(panel);
        pack();
        setLocation(300, 200);
    }

    public void run(){
        setVisible(true);
    }

    private String[][] convertNewsToStrings(ArrayList<News> mas){
        int n = mas.size();
        String ans[][] = new String[n][4];
        for (int i = 0; i < n; i++){
            ans[i][0] = mas.get(i).getId() + "";
            ans[i][1] = mas.get(i).getTitle();
            ans[i][2] = mas.get(i).getText();
            ans[i][3] = (mas.get(i).isPrivat() ? "да" : "нет");
        }
        return ans;
    }

    private void addWidgets() {
        panel.setLayout(null);

        String data[][] = new String[0][];
        try {
            data = convertNewsToStrings(DBconnector.getNews(currentUser));
            String columns[] = {"id","Заголовок","Текст новости", "Скрытая"};
            model = new DefaultTableModel(data, columns);
            //model.addTableModelListener(this);
            table = new JTable(model);
            JScrollPane tableWithScroll = new JScrollPane(table);
            tableWithScroll.setBounds(5, 80, 790, 515);
            panel.add(tableWithScroll);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel nameLBL = new JLabel(currentUser.getName());
        nameLBL.setBounds(15, 25, 150, 25);
        panel.add(nameLBL);

        JButton addBTN = new JButton("Добавить новость");
        addBTN.setBounds(300, 25, 200, 25);
        addBTN.addActionListener(e -> showAddNews());
        panel.add(addBTN);

        JButton logoutBTN = new JButton("Выйти");
        logoutBTN.setBounds(520, 25, 100, 25);
        logoutBTN.addActionListener(e -> logout());
        panel.add(logoutBTN);
    }

    private void logout() {
        AutorizationWindow window = new AutorizationWindow();
        window.run();
        setVisible(false);
        dispose();
    }

    private void showAddNews(){
        AddNews window = new AddNews(currentUser);
        window.registerCallBack(this);
        window.run();
    }

    @Override
    public void news_added() {
        System.out.println("Новость добавлена");
    }
}
