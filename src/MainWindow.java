import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private JPanel panel;

    public MainWindow(){
        super();
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

    private String[][] convertNewsToStrings(News[] mas){
        int n = mas.length;
        String ans[][] = new String[n][4];
        for (int i = 0; i < n; i++){
            ans[i][0] = mas[i].getId() + "";
            ans[i][1] = mas[i].getTitle();
            ans[i][2] = mas[i].getText();
            ans[i][3] = (mas[i].isPrivat() ? "да" : "нет");
        }
        return ans;
    }

    private void addWidgets() {
        panel.setLayout(null);

        String data[][] = convertNewsToStrings(DBconnector.getNews());
        String columns[] = {"id","Заголовок","Текст новости", "Скрытая"};
        TableModel model = new DefaultTableModel(data, columns);
        //model.addTableModelListener(this);
        JTable table = new JTable(model);
        JScrollPane tableWithScroll = new JScrollPane(table);
        tableWithScroll.setBounds(5, 5, 740, 500);
        panel.add(tableWithScroll);

    }

}
