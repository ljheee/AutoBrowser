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
 
        // ������ť
        start = new JButton("��ʼ");
        end = new JButton("����");
        quit = new JLabel("<html>shi_sky���ر�����</html");
        pause = new JLabel(
                "<html>�������������win_xp_32ϵͳ��ie8�������JDK1.6,���������������ܳ��ֳ������в�������</html>");
 
        // ������ص��ı���
        url = new TextField("http://www.baidu.com");
        time = new TextField("3");
        max = new TextField("200");
        iP = new TextArea();
        iP.setColumns(30);
        iP.setRows(50);
 
        // ������ص�Label��ǩ
        urlname = new JLabel("������ַ���磺http://www.baidu.com��");
        timename = new JLabel("���ʼ��ʱ�䣨�룩");
        maxname = new JLabel("���ʴ�����0~99999���ڣ�");
        ipName = new JLabel("���������Ϣ��");
        // ��ʼ����������������
        panel1 = new JPanel(new GridLayout(2, 1));// ����һ��
        panel11 = new JPanel(new GridLayout(2, 1));
        panel2 = new JPanel(new GridLayout(2, 2));
        panel3 = new JPanel(new GridLayout(1, 4));
 
        // ����ش��ڶ�����ӵ�������ȥ
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
 
        // ����¼��ıO 
        start.addActionListener(this);
        end.addActionListener(this);
 
        f.setLayout(new BorderLayout());
        f.add(panel22, BorderLayout.CENTER);
 
        // ��ʼ��JFrame����
 
        f.setLocation(500, 100);
        f.setSize(500, 500);
        f.setBackground(Color.darkGray);
        f.setResizable(false);
        f.setVisible(true);
 
    }
 
    // �¼���Ӧ��
    public void actionPerformed(ActionEvent arg0) {
 
        int t = (Integer.valueOf(time.getText()).intValue());// ��ȡtextԭ���е�int���͵�ֵ�����ʼ��ʱ�䣩
        int m = (Integer.valueOf(max.getText()).intValue());// ��ȡtextԭ���е�int���͵�ֵ�����ʴ�����
        int go = 1; // ����
        int times = t * 1000;// ����ת��Ϊ����
 
        if (arg0.getSource() == start) {
 
            if (url.getText() != null && time.getText() != null
                    && max.getText() != null) {
 
                String url2 = null;
                String url1 = url.getText();// ��ȡtext�ڵ�url
                // �Ի�ȡ������ַ����Э��������������ʹ���http��ftp��https��file��mailto��Э�飩
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
                }// �����Ե�ַ�Ĵ���
 
                while (true) {
                    // ��ϵͳĬ�������
                    Desktop d = Desktop.getDesktop();
 
                    try {
                        String u = url2.toString(); // ��ȡ�����ĵ�ַ
                        // Runtime.getRuntime().exec("cmd /c start"+u);
                        d.browse(new URI(u));// ָ��Ҫ�򿪵���վ��ַ
                        System.out.println("url3:" + url2);
 
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
 
                    System.out.println("���ʴ�����" + go);
                    iP.append("���ʴ�����" + go + "\n");// �����ǰ��
                    go++;// �������ۼӼ�¼���ʴ���
 
                    try {
                        Thread.sleep(times);// �߳���Ϣ
 
                        String[] cmd = { "tskill iexplore", "tskill chrome",
                                "tskill sogouexplorer", "tskill The world",
                                "tskill Firefox", "tskill opera",
                                "tskill 360SE", "tskill Safari",
                                "tskill Maxthon" };
                        Process p;
 
                        // String command = "tskill iexplore";// ��DOS���������ַ�
                        // " ntsd -c q -p ";
                        // ѭ��ִ��DOS����
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
 
                        // ��ʼ��DOS����
                        // ����������
                        // �жϷ��ʴ����Ƿ񵽴����ô���������ﵽ���������
 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
                    if (go > m) {
                        iP.append("����ִ�н���������");
                        System.out.println("����ִ�н���������");
                        break;
                    }
 
                }
 
            } else {
                iP.append("�Բ���������Ĳ�������ȷ����");
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
