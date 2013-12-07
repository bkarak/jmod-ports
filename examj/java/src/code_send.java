/***************************************************************************
 *   Copyright (C) 2008, George Hadjikyriacou                              *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 3 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program; if not, write to the                         *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 ***************************************************************************/

package examj;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class code_send {

    public static JFrame win_upload;
    JLabel lb_name, lb_surname, lb_id, lb_group;
    public static JTextField txt_name, txt_surname, txt_id, txt_group;
    JButton btn_upload_db, btn_send_mail;
    JPanel panel_up_1, panel_up_2, panel_up_3, panel_up_4, panel_up_5;
    static Connection connection = null;
    static Statement myStatement;

    public code_send() {
        upload_code();
    }

    public void upload_code() {
        win_upload = new JFrame("Send Code");
        Toolkit toolkit_upload = win_upload.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_upload.setBounds((screenSize.width - 300) / 2,
                             (screenSize.height - 300) / 2, 300, 300);
        lb_name = new JLabel("Name:");
        lb_surname = new JLabel("Surname:");
        lb_id = new JLabel("ID:");
        lb_group = new JLabel("Group:");
        lb_name.setPreferredSize(new Dimension(150, 16));
        lb_surname.setPreferredSize(new Dimension(150, 16));
        lb_id.setPreferredSize(new Dimension(150, 16));
        lb_group.setPreferredSize(new Dimension(150, 16));
        txt_name = new JTextField(examj_gui.get_pr_name, 15);
        txt_surname = new JTextField(examj_gui.get_pr_surname, 15);
        txt_id = new JTextField(examj_gui.get_pr_id, 15);
        txt_group = new JTextField(examj_gui.get_pr_group, 15);
        btn_upload_db = new JButton("Upload to DB");
        btn_send_mail = new JButton("Send to E-mail");
        panel_up_1 = new JPanel();
        panel_up_2 = new JPanel();
        panel_up_3 = new JPanel();
        panel_up_4 = new JPanel();
        panel_up_5 = new JPanel();
        panel_up_1.add(lb_name);
        panel_up_1.add(txt_name);
        panel_up_2.add(lb_surname);
        panel_up_2.add(txt_surname);
        panel_up_3.add(lb_id);
        panel_up_3.add(txt_id);
        panel_up_4.add(lb_group);
        panel_up_4.add(txt_group);
        panel_up_5.add(btn_upload_db);
        panel_up_5.add(btn_send_mail);
        win_upload.setLayout(new GridLayout(5, 1));
        win_upload.add(panel_up_1);
        win_upload.add(panel_up_2);
        win_upload.add(panel_up_3);
        win_upload.add(panel_up_4);
        win_upload.add(panel_up_5);
        win_upload.pack();
        win_upload.setResizable(false);
        win_upload.setVisible(true);

        btn_upload_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String prj_name = examj_gui.txt_file.getText();
                String lang = (String) examj_gui.cmp_compiler.getSelectedItem();
                ////////////////////////////////////////////////////////////////
                String str_code_1 = examj_gui.txt_code.getText();
                String str_code_2 = str_code_1.replaceAll("\\\\", "\\\\\\\\");
                String str_code = str_code_2.replaceAll("'", "''");
                ////////////////////////////////////////////////////////////////
                String name = txt_name.getText();
                String surname = txt_surname.getText();
                String id = txt_id.getText();
                String group = txt_group.getText();
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                int second = now.get(Calendar.SECOND);
                int month = now.get(Calendar.MONTH) + 1;
                int day = now.get(Calendar.DAY_OF_MONTH);
                int year = now.get(Calendar.YEAR);
                String date = year + "-" + month + "-" + day;
                String time = hour + ":" + minute + ":" + second;

                try {
                    String host, username, password, dbase;
                    Properties prop = new Properties();
                    prop.load(new FileInputStream("bin/examj.properties"));
                    host = prop.getProperty("host");
                    username = "examj_user";
                    password = "examj_user";
                    dbase = prop.getProperty("dbase");
                    Class.forName("org.gjt.mm.mysql.Driver");
                    String url = "jdbc:mysql://" + host + "/" + dbase;
                    connection = DriverManager.getConnection(url, username,
                                 password);
                    myStatement = connection.createStatement();
                } catch (Exception exe1) {
                    System.out.println(exe1);
                    error_log.send_error(exe1.getMessage());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                }
                if (txt_name.getText().length() < 1
                        || txt_surname.getText().length() < 1
                        || txt_id.getText().length() < 1
                        || txt_group.getText().length() < 1
                || examj_gui.txt_file.getText().length() < 1) {
                    JOptionPane.showMessageDialog(win_upload,
                                                  "All fields required!");
                } else {
                    try {
                        Properties propex = new Properties();
                        propex.load(new FileInputStream("bin/examj.properties"));
                        String examiner = propex.getProperty("examiner");
                        String myQuery = "INSERT IGNORE INTO code_uploaded (`date`,`time`,`name`,`surname`,`id`,`group`,`prj_name`,`lang`,`code`,`examiner`,`uuid`) values('"
                                         + date
                                         + "','"
                                         + time
                                         + "','"
                                         + name
                                         + "','"
                                         + surname
                                         + "','"
                                         + id
                                         + "','"
                                         + group
                                         + "','"
                                         + prj_name
                                         + "','"
                                         + lang
                                         + "','"
                                         + str_code
                                         + "','" + examiner + "','" + examj_gui.uuid_code + "'); ";
                        int check_insert = myStatement.executeUpdate(myQuery);
                        if (check_insert == 1) {
                            JOptionPane.showMessageDialog(win_upload,
                                                          "Code have been successfully uploaded!");
                            win_upload.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(win_upload,
                                                          "Upload Failed!");
                        }
                        connection.close();
                    } catch (Exception exe2) {
                        System.out.println(exe2);
                        error_log.send_error(exe2.getMessage());
                        examj_gui.txt_msg
                        .setText("Error: See 'error.log' for more information");
                    }
                }
            }
        });

        btn_send_mail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txt_name.getText().length() < 1
                        || txt_surname.getText().length() < 1
                        || txt_id.getText().length() < 1
                || txt_group.getText().length() < 1) {
                    JOptionPane.showMessageDialog(win_upload,
                                                  "All fields required!");
                } else {
                    new send_mail();
                }
            }
        });
    }
}
