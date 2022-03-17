import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddNews extends JFrame {

    private final int WIDTH = 385;
    private final int HEIGHT = 500;
    private JTextField titleTF;
    private JTextArea textTA;
    private JCheckBox privateCB;
    private JPanel panel;

    public AddNews(){
        super();
        setTitle("Добавление новости");
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

    private void addWidgets() {
        panel.setLayout(null);

        Font fontFields = new Font("Arial", Font.PLAIN, 16);
        Font fontLBL = new Font("Arial", Font.PLAIN, 30);

        JLabel headLBL = new JLabel("Новая новость");
        headLBL.setBounds(0, 30, WIDTH, 50);
        headLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headLBL.setFont(fontLBL);
        panel.add(headLBL);

        // Login GUI Settings
        JLabel loginLBL = new JLabel("Заголовок:");
        loginLBL.setBounds(15, 120, 150, 25);
        loginLBL.setFont(fontFields);
        panel.add(loginLBL);

        titleTF = new JTextField();
        titleTF.setBounds(170, 120, 200, 25);
        titleTF.setFont(fontFields);
        panel.add(titleTF);

        JLabel textLBL = new JLabel("Текст:");
        textLBL.setBounds(15, 160, 150, 25);
        textLBL.setFont(fontFields);
        panel.add(textLBL);

        textTA = new JTextArea();
        textTA.setBounds(15, 190, 355, 200);
        textTA.setFont(fontFields);
        panel.add(textTA);

        privateCB = new JCheckBox("Только для меня");
        privateCB.setBounds(25, 400, 160, 25);
        privateCB.setFont(fontFields);
        panel.add(privateCB);

        // Button Settings

        JButton regBTN = new JButton("Добавить");
        regBTN.setBounds(200, 400, 145, 25);
        regBTN.setFont(fontFields);
        regBTN.addActionListener(e -> regClick(e));
        panel.add(regBTN);

        JButton backBTN = new JButton("Назад");
        backBTN.setBounds(120, 445, 145, 25);
        backBTN.setFont(fontFields);
        backBTN.addActionListener(e -> backClick(e));
        panel.add(backBTN);
    }

    private void regClick(ActionEvent e) {
        News news = validateFields();
        if (news == null) {
            JOptionPane.showMessageDialog(null, "Ошибка заполнения полей");
            return;
        }
        try {
            DBconnector.addNews(news);
            backClick(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при записи в БД!");
        }
    }

    private News validateFields(){
        String title = titleTF.getText();
        String text = textTA.getText();
        boolean cb = privateCB.isSelected();

        if (title.trim().isEmpty() || text.trim().isEmpty())
            return null;
        int user_id = 1;
        return new News(title, text, user_id, cb);
    }

    private void backClick(ActionEvent e) {
        dispose();
    }

}
