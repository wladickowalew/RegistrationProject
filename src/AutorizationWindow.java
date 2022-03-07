import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AutorizationWindow extends JFrame {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private JTextField loginTF;
    private JPasswordField passwordTF;
    private JPanel panel;

    public AutorizationWindow(){
        super();
        setTitle("Авторизация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addWidgets();
        getContentPane().add(panel);
        pack();
    }

    public void run(){
        setVisible(true);
    }

    private void addWidgets() {
        panel.setLayout(null);

        Font fontFields = new Font("Arial", Font.PLAIN, 16);
        Font fontLBL = new Font("Arial", Font.PLAIN, 30);

        JLabel headLBL = new JLabel("АВТОРИЗАЦИЯ");
        headLBL.setBounds(0, 30, WIDTH, 50);
        headLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headLBL.setFont(fontLBL);
        panel.add(headLBL);

        // Login GUI Settings
        JLabel loginLBL = new JLabel("Логин:");
        loginLBL.setBounds(15, 120, 60, 25);
        loginLBL.setFont(fontFields);
        panel.add(loginLBL);

        loginTF = new JTextField();
        loginTF.setBounds(80, 120, 200, 25);
        loginTF.setFont(fontFields);
        panel.add(loginTF);

        // Password GUI Settings
        JLabel passwordLBL = new JLabel("Пароль:");
        passwordLBL.setBounds(15, 165, 60, 25);
        passwordLBL.setFont(fontFields);
        panel.add(passwordLBL);

        passwordTF = new JPasswordField();
        passwordTF.setBounds(80, 165, 200, 25);
        passwordTF.setFont(fontFields);
        panel.add(passwordTF);

        // Button Settings
        JButton enterBTN = new JButton("Войти");
        enterBTN.setBounds(80, 210, 140, 25);
        enterBTN.setFont(fontFields);
        enterBTN.addActionListener(e -> enterClick(e));
        panel.add(enterBTN);

        JButton regBTN = new JButton("Регистрация");
        regBTN.setBounds(80, 250, 140, 25);
        regBTN.setFont(fontFields);
        regBTN.addActionListener(e -> regClick(e));
        panel.add(regBTN);
    }

    private void regClick(ActionEvent e) {
        System.out.println("Reg Click!");
    }

    private void enterClick(ActionEvent e) {
        System.out.println("Enter Click!");
    }

}
