package com.ljheee.browser;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
 
import javax.swing.*;
 
public class Boxx extends Thread implements ActionListener, WindowListener {
 
    JFrame f;
 
    JPanel panel1;
    JPanel panel11;
 
    JPanel panel2;
    JPanel panel22;
 
    JPanel panel3;
 
    TextField url;
    TextField time;
    TextField max;
 
    TextArea iP;
 
    JLabel urlname;
    JLabel timename;
    JLabel maxname;
    JLabel ipName;
    JLabel quit;
    JLabel pause;
 
    JButton start;
    JButton end;
 
    public void run() {
        f = new JFrame("AutoBrowser");
 
        // 创建按钮
        start = new JButton("开始");
        end = new JButton("结束");
        quit = new JLabel("<html>shi_sky，特别制作</html");
        pause = new JLabel(
                "<html>软件开发环境：win_xp_32系统，ie8浏览器，JDK1.6,对于其他环境可能出现程序运行不正常！</html>");
 
        // 创建相关的文本域
        url = new TextField("http://www.baidu.com");
        time = new TextField("3");
        max = new TextField("200");
        iP = new TextArea();
        iP.setColumns(30);
        iP.setRows(50);
 
        // 创建相关的Label标签
        urlname = new JLabel("访问网址（如：http://www.baidu.com）");
        timename = new JLabel("访问间隔时间（秒）");
        maxname = new JLabel("访问次数（0~99999次内）");
        ipName = new JLabel("输出访问信息：");
        // 初始化容器和容器属性
        panel1 = new JPanel(new GridLayout(2, 1));// 两行一列
        panel11 = new JPanel(new GridLayout(2, 1));
        panel2 = new JPanel(new GridLayout(2, 2));
        panel3 = new JPanel(new GridLayout(1, 4));
 
        // 把相关窗口对象添加到容器中去
        panel1.add(urlname, BorderLayout.CENTER);
        panel1.add(url, BorderLayout.CENTER);
 
        panel11.add(ipName);
        panel11.add(iP);
 
        panel2.add(timename);
        panel2.add(maxname);
        panel2.add(time);
        panel2.add(max);
 
        panel3.add(pause);
        panel3.add(start);
        panel3.add(end);
        panel3.add(quit);
 
        panel22 = new JPanel(new GridLayout(4, 1));
 
        panel22.add(panel1);
        panel22.add(panel2);
        panel22.add(panel11);
        panel22.add(panel3);
 
        // 添加事件的監聽
        start.addActionListener(this);
        end.addActionListener(this);
 
        f.setLayout(new BorderLayout());
        f.add(panel22, BorderLayout.CENTER);
 
        // 初始化JFrame窗口
 
        f.setLocation(500, 100);
        f.setSize(500, 500);
        f.setBackground(Color.darkGray);
        f.setResizable(false);
        f.setVisible(true);
 
    }
 
    // 事件响应类
    public void actionPerformed(ActionEvent arg0) {
 
        int t = (Integer.valueOf(time.getText()).intValue());// 获取text原件中的int类型的值（访问间隔时间）
        int m = (Integer.valueOf(max.getText()).intValue());// 获取text原件中的int类型的值（访问次数）
        int go = 1; // 计数
        int times = t * 1000;// 由秒转化为毫秒
 
        if (arg0.getSource() == start) {
 
            if (url.getText() != null && time.getText() != null
                    && max.getText() != null) {
 
                String url2 = null;
                String url1 = url.getText();// 获取text内的url
                // 对获取到的网址进行协议或者其他的类型处理（http、ftp、https、file、mailto等协议）
                if ((url1.substring(0, 7)).equalsIgnoreCase("http://")
                        || (url1.substring(0, 6)).equalsIgnoreCase("ftp://")
                        || (url1.substring(0, 8)).equalsIgnoreCase("https://")
                        || (url1.substring(0, 7)).equalsIgnoreCase("file://")
                        || (url1.substring(0, 7)).equalsIgnoreCase("mailto://")) {
 
                    url2 = url1;
                    System.out.println("url1:" + url2);
                } else {
                    url2 = "http://" + url1;
                    System.out.println("url2:" + url2);
                }// 结束对地址的处理
 
                while (true) {
                    // 打开系统默认浏览器
                    Desktop d = Desktop.getDesktop();
 
                    try {
                        String u = url2.toString(); // 获取处理后的地址
                        // Runtime.getRuntime().exec("cmd /c start"+u);
                        d.browse(new URI(u));// 指定要打开的网站地址
                        System.out.println("url3:" + url2);
 
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
 
                    System.out.println("访问次数：" + go);
                    iP.append("访问次数：" + go + "\n");// 输出到前端
                    go++;// 计数器累加记录访问次数
 
                    try {
                        Thread.sleep(times);// 线程休息
 
                        String[] cmd = { "tskill iexplore", "tskill chrome",
                                "tskill sogouexplorer", "tskill The world",
                                "tskill Firefox", "tskill opera",
                                "tskill 360SE", "tskill Safari",
                                "tskill Maxthon" };
                        Process p;
 
                        // String command = "tskill iexplore";// 想DOS传入命令字符
                        // " ntsd -c q -p ";
                        // 循环执行DOS命令
                        for (int i = 0; i < cmd.length; i++) {
 
                            p = Runtime.getRuntime().exec("cmd /c" + cmd[i]);
 
                            InputStream in = p.getInputStream();
                            BufferedReader inr = new BufferedReader(
                                    new InputStreamReader(in, "utf-8"));
                            String line = null;
                            while ((line = inr.readLine()) != null) {
                                System.out.println(line);
                            }
 
                            p.waitFor();
                            System.out.println("DOS:" + i);
                        }
 
                        // 初始化DOS方法
                        // 加载命令行
                        // 判断反问次数是否到达设置次数，如果达到则结束程序
 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
                    if (go > m) {
                        iP.append("程序执行结束！！！");
                        System.out.println("程序执行结束！！！");
                        break;
                    }
 
                }
 
            } else {
                iP.append("对不起！你输入的参数不正确！！");
            }
        }
 
        if (arg0.getSource() == end) {
            System.exit(0);
        }
 
    }
 
    public void windowActivated(WindowEvent arg0) {
    }
 
    public void windowClosed(WindowEvent arg0) {
        System.exit(0);
    }
 
    public void windowClosing(WindowEvent arg0) {
        System.exit(0);
    }
 
    public void windowDeactivated(WindowEvent arg0) {
 
    }
 
    public void windowDeiconified(WindowEvent arg0) {
 
    }
 
    public void windowIconified(WindowEvent arg0) {
 
    }
 
    public void windowOpened(WindowEvent arg0) {
 
    }
 
    public static void main(String[] args) {
        Boxx b = new Boxx();
        b.run();
    }
 
}
