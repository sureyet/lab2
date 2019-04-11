# 开发日志
开发语言：Java
GUI工具包：Swing
开发环境：IntelliJ IDEA 2018.2.5 x64


## todo-list

- [x] 设计一种程序语言用来描述比特大战，该语言能描述已给的以及更多的策略

- [x] 实现这个语言的语法分析，定义相应的语法树

- [x] 用户输入若干策略，每个策略保存为一个文本文件，并展示其相应的语法树

- [ ] 模拟这些策略两两之间的N回合（例如，n=200）的比特大战，并以所有对战得分的总和为这些策略排序

- [ ] 完善图形界面的设计

  ------

  ### 第一版

  2019/4/10  张烁

  1.GUI用的是swing，文件在src\src\lab2_GUI.java, 但是swing的控件不熟悉，所以界面设计的不如意（比如，运行程序后的初始化窗口大小、以及用以输入策略的输入框等）

  2.文法规则在src\src\BitLanguage.txt文件里

  3.lan文件夹和tree文件夹下分别存放着用以语法分析和生成语法树的类

  4.用户在输入策略后，点击“保存策略”按钮后，会进行语法分析，若通过语法分析，则会将该策略以txt形式保存在根目录下strategy文件夹下。

  5.用户只有在输入策略，保存成功后，点击“生成语法树”按钮后才会显示其语法树。
