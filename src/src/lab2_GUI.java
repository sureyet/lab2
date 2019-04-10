import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import lan.BitLanguage;
import lan.ParseException;
import lan.test;
import tree.BitLanguageTree;

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
    private JPanel Jpanel11;
    private JPanel Jpanel12;
    private JPanel Jpanel121;
    private JPanel Jpanel122;
    private JTextArea textArea3;

    //这里声明全局的文件名和路径，用以保存策略以及生成语法树
    String filename = null;  //策略同对应语法树文件的名字
    String filepath = null;    //策略文件的路径
    String filepath2 = null;    //语法树文件的位置

    public lab2_GUI() {
        保存策略Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //先将展示语法树的模块清空
                textArea3.setText("");
                //先将策略保存为文本，再验证该策略是否符合文法规则，若不符合弹出消息框，删除保存的策略文本
                filename = JOptionPane.showInputDialog("请输入策略的名字：");
                //在运行目录下创建文件夹
                filepath = System.getProperty("user.dir")+"\\strategy";
                File dir = new File(filepath);
                if(!dir.exists()){
                    dir.mkdir();
                }
                filepath += "\\"+ filename +".txt";

                File f = new File(filepath);
                if (!f.exists()) {
                    System.out.println("creat " + f.toString());
                    try {
                        f.createNewFile();
                    } catch (IOException e1) {
                        System.out.println("创建失败");
                    }
                }

                //将策略保存为文本文件
                try {
                    //策略内容
                    String content = textArea1.getText();
                    String []contents = content.split("\n");

                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                    for(String lineContent:contents){
                        bw.write(lineContent);
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                } catch (FileNotFoundException e1) {
                    System.out.println("写入文件时发生错误");
                } catch (IOException e1) {
                    System.out.println("写入文件时发生错误");
                }

                //接下来验证策略是否符合文法规则
                String []filepaths ={filepath};
                try {
                    BitLanguage.main(filepaths);
                    JOptionPane.showMessageDialog(null, "保存成功");
                } catch (ParseException e1) {    //语法分析发现错误
                    f.delete();     //发现错误后删除掉原先保存的文档
                    JOptionPane.showMessageDialog(null, "输入的策略格式不符合文法规则");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //顺便生成保存语法树的文本
                filepath2 = System.getProperty("user.dir")+"\\syntaxTree";
                File dir2 = new File(filepath2);
                if(!dir2.exists()){
                    dir2.mkdir();
                }
                filepath2 += "\\"+ filename +".txt";

                File f2 = new File(filepath2);
                if (!f2.exists()) {
                    System.out.println("creat " + f2.toString());
                    try {
                        f2.createNewFile();
                    } catch (IOException e1) {
                        System.out.println("创建失败");
                    }
                }

            }
        });
        生成语法树Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String []filepaths ={filepath,filepath2};
                try {
                    BitLanguageTree.main(filepaths);
                } catch (tree.ParseException e1) {
                    System.out.println("生成语法树失败");
                } catch (IOException e1) {
                    System.out.println("生成语法树失败");
                }
                //找到保存语法树的文件并将其展示出来
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath2));
                    String string = null;
                    string = bufferedReader.readLine();
                    while (string != null){
                        textArea3.append(string+"\r\n");
                        string = bufferedReader.readLine();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("lab2_GUI");
        frame.setContentPane(new lab2_GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
