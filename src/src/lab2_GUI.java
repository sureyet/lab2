import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

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
    private JTextArea textArea2;

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
                    System.out.println("生成语法树失败1");
                } catch (IOException e1) {
                    System.out.println("生成语法树失败2");
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
        模拟对抗Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath3 = null;
                filepath3 = System.getProperty("user.dir")+"\\strategy";
                File file3 = new File(filepath3); //策略文件存放的路径
                File lists[] = file3.listFiles();

                //开一张索引表和一张映射表，建立由策略名到得分总和的映射
                String index2stg [] = new String[lists.length];
                HashMap<String,Integer> hmap = new HashMap<>();
                for(int i=0;i<lists.length;i++){
                    index2stg[i] = lists[i].getName();
                    hmap.put(lists[i].getName(),0);
                }

                //判断策略文件夹下策略文件的数量，若数量少于2，则无法进行比特大战，提示后退出
                if(lists.length<2)
                    JOptionPane.showMessageDialog(null, "策略数量少于2，无法进行比特大战");
                else{
                    int N =Integer.parseInt(JOptionPane.showInputDialog("请输入回合数："));
                    for(int i=0;i<lists.length;i++)
                        for(int j=i+1;j< lists.length;j++){
                            String filepath4 = filepath3+"\\"+lists[i].getName();
                            String filepath5 = filepath3+"\\"+lists[j].getName();
                            try {
                                BitLanguage.oppose(N,filepath4,filepath5,hmap);
                                } catch (ParseException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                    String temp = null;
                    for(int i=0;i<lists.length-1;i++){//冒泡趟数
                        for(int j=0;j<lists.length-i-1;j++){
                            if(hmap.get(index2stg[j+1])> hmap.get(index2stg[j])){
                                temp = index2stg[j];
                                index2stg[j] = index2stg[j+1];
                                index2stg[j+1] = temp;
                            }
                        }
                    }
                    //输出测试
                    System.out.println("============");
                    for(int i=0;i<lists.length;i++){
                        System.out.println(index2stg[i]+":"+ hmap.get(index2stg[i]));
                    }

                    File f4 = new File(System.getProperty("user.dir")+"\\oppose.txt");
                    if(!f4.exists()) {
                        try {
                            f4.createNewFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    try {
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(f4));
                        for(int i=0;i<lists.length;i++){
                           bw2.write(index2stg[i]+":   "+ hmap.get(index2stg[i]));
                           bw2.newLine();
                           bw2.flush();
                        }
                        bw2.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    //找到保存语法树的文件并将其展示出来
                    try {
                        textArea2.append("策略名：  得分总和"+"\r\n");
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\oppose.txt"));
                        String string = null;
                        string = bufferedReader.readLine();
                        while (string != null){
                            textArea2.append(string+"\r\n");
                            string = bufferedReader.readLine();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
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
