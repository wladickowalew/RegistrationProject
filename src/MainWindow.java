import javax.swing.*;
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
    }

    public void run(){
        setVisible(true);
    }

    private void addWidgets() {
        panel.setLayout(null);

        Font fontLBL = new Font("Arial", Font.PLAIN, 30);

        JLabel headLBL = new JLabel("Здесь будет главное окно!!!");
        headLBL.setBounds(0, 275, WIDTH, 50);
        headLBL.setHorizontalAlignment(SwingConstants.CENTER);
        headLBL.setFont(fontLBL);
        panel.add(headLBL);

    }

}
