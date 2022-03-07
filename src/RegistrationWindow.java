import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class RegistrationWindow extends JFrame {

    private final int WIDTH = 385;
    private final int HEIGHT = 500;
    private JTextField loginTF, nameTF, ageTF, professionTF;
    private JPasswordField passwordTF, repeat_passwordTF;
    private JPanel panel;

    public RegistrationWindow(){
        super();
        setTitle("Регистрация");
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

        JLabel headLBL = new JLabel("РЕГИСТРАЦИЯ");
        headLBL.setBounds(0, 30, WIDTH, 50);
        headLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headLBL.setFont(fontLBL);
        panel.add(headLBL);

        // Login GUI Settings
        JLabel loginLBL = new JLabel("Логин:");
        loginLBL.setBounds(15, 120, 150, 25);
        loginLBL.setFont(fontFields);
        panel.add(loginLBL);

        loginTF = new JTextField();
        loginTF.setBounds(170, 120, 200, 25);
        loginTF.setFont(fontFields);
        panel.add(loginTF);

        // Password GUI Settings
        JLabel passwordLBL = new JLabel("Пароль:");
        passwordLBL.setBounds(15, 165, 150, 25);
        passwordLBL.setFont(fontFields);
        panel.add(passwordLBL);

        passwordTF = new JPasswordField();
        passwordTF.setBounds(170, 165, 200, 25);
        passwordTF.setFont(fontFields);
        panel.add(passwordTF);

        // Repeat Password GUI Settings
        JLabel rpasswordLBL = new JLabel("Повторите пароль:");
        rpasswordLBL.setBounds(15, 210, 150, 25);
        rpasswordLBL.setFont(fontFields);
        panel.add(rpasswordLBL);

        repeat_passwordTF = new JPasswordField();
        repeat_passwordTF.setBounds(170, 210, 200, 25);
        repeat_passwordTF.setFont(fontFields);
        panel.add(repeat_passwordTF);

        // Name GUI Settings
        JLabel nameLBL = new JLabel("ФИО:");
        nameLBL.setBounds(15, 255, 150, 25);
        nameLBL.setFont(fontFields);
        panel.add(nameLBL);

        nameTF = new JTextField();
        nameTF.setBounds(170, 255, 200, 25);
        nameTF.setFont(fontFields);
        panel.add(nameTF);

        // Age GUI Settings
        JLabel ageLBL = new JLabel("Возраст:");
        ageLBL.setBounds(15, 300, 150, 25);
        ageLBL.setFont(fontFields);
        panel.add(ageLBL);

        ageTF = new JTextField();
        ageTF.setBounds(170, 300, 200, 25);
        ageTF.setFont(fontFields);
        panel.add(ageTF);

        // Profession GUI Settings
        JLabel profLBL = new JLabel("Профессия:");
        profLBL.setBounds(15, 345, 150, 25);
        profLBL.setFont(fontFields);
        panel.add(profLBL);

        professionTF = new JTextField();
        professionTF.setBounds(170, 345, 200, 25);
        professionTF.setFont(fontFields);
        panel.add(professionTF);

        // Button Settings

        JButton regBTN = new JButton("Регистрация");
        regBTN.setBounds(120, 390, 145, 25);
        regBTN.setFont(fontFields);
        regBTN.addActionListener(e -> regClick(e));
        panel.add(regBTN);

        JButton backBTN = new JButton("Назад");
        backBTN.setBounds(120, 435, 145, 25);
        backBTN.setFont(fontFields);
        backBTN.addActionListener(e -> backClick(e));
        panel.add(backBTN);
    }

    private void regClick(ActionEvent e) {
        User user = validateFields();
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Ошибка заполнения полей");
            return;
        }
        try {
            DBconnector.addUser(user);
            backClick(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Такой пользователь уже существует");
        }
    }

    private User validateFields(){
        String login = loginTF.getText();
        String password = String.valueOf(passwordTF.getPassword());
        String repeat_password = String.valueOf(repeat_passwordTF.getPassword());
        String name = nameTF.getText();
        String age_text = ageTF.getText();
        String profession = professionTF.getText();
        if (login.trim().isEmpty() || name.trim().isEmpty()
                || age_text.trim().isEmpty() || profession.trim().isEmpty())
            return null;
        int age = 0;
        try {
            age = Integer.parseInt(age_text);
        }catch (Exception e){
            return null;
        }
        if (!(password.equals(password) && password.length() > 5))
            return null;
        return new User(login, password,name, profession, age);
    }

    private void backClick(ActionEvent e) {
        AutorizationWindow window = new AutorizationWindow();
        window.run();
        setVisible(false);
        dispose();
    }

}
