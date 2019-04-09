import javax.swing.*;

public class lab2_GUI {
    private JTextArea textArea1;
    private JButton 保存策略Button;
    private JButton 生成语法树Button;
    private JButton 模拟对抗Button;
    private JPanel rootPanel;
    private JPanel Jpanel1;
    private JPanel Jpanel2;
    private JPanel Jpanel3;
    private JLabel heading;
    private JLabel label1;
    private JPanel Jpanel11;
    private JPanel Jpanel12;
    private JPanel Jpanel121;
    private JPanel Jpanel122;
    private JTextArea textArea2;
    private JLabel label2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("lab2_GUI");
        frame.setContentPane(new lab2_GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
