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
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class add_user {

    JFrame win_add_user;
    JLabel lb_root_usr, lb_root_pass, lb_name, lb_surname, lb_email,
    lb_username, lb_password;
    JTextField txt_root_usr, txt_name, txt_surname, txt_email,
    txt_username;
    JPasswordField txt_root_pass, txt_usr_pass;
    JButton btn_add;
    JPanel panel_add_user_1, panel_add_user_2, panel_add_user_3,
    panel_add_user_4, panel_add_user_5, panel_add_user_6,
    panel_add_user_7, panel_add_user_8;
    static Connection connection = null;
    static Statement myStatement;

    public add_user() {
        add_user_db();
    }

    public void add_user_db() {
        win_add_user = new JFrame("Add User (Examiner)");
        Toolkit toolkit_upload = win_add_user.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_add_user.setBounds((screenSize.width - 300) / 2,
                               (screenSize.height - 300) / 2, 300, 300);
        lb_root_usr = new JLabel("MySQL username:");
        lb_root_pass = new JLabel("MySQL password:");
        lb_name = new JLabel("Name:");
        lb_surname = new JLabel("Surname:");
        lb_email = new JLabel("E-Mail:");
        lb_username = new JLabel("Username:");
        lb_password = new JLabel("Password:");
        lb_root_usr.setPreferredSize(new Dimension(150, 16));
        lb_root_pass.setPreferredSize(new Dimension(150, 16));
        lb_name.setPreferredSize(new Dimension(150, 16));
        lb_surname.setPreferredSize(new Dimension(150, 16));
        lb_email.setPreferredSize(new Dimension(150, 16));
        lb_username.setPreferredSize(new Dimension(150, 16));
        lb_password.setPreferredSize(new Dimension(150, 16));
        txt_root_usr = new JTextField("root", 15);
        txt_name = new JTextField("", 15);
        txt_surname = new JTextField("", 15);
        txt_email = new JTextField("", 15);
        txt_username = new JTextField("", 15);
        txt_root_pass = new JPasswordField(15);
        txt_root_pass.setEchoChar('*');
        txt_usr_pass = new JPasswordField(15);
        txt_usr_pass.setEchoChar('*');
        btn_add = new JButton("Add User");
        panel_add_user_1 = new JPanel();
        panel_add_user_2 = new JPanel();
        panel_add_user_3 = new JPanel();
        panel_add_user_4 = new JPanel();
        panel_add_user_5 = new JPanel();
        panel_add_user_6 = new JPanel();
        panel_add_user_7 = new JPanel();
        panel_add_user_8 = new JPanel();
        panel_add_user_1.add(lb_root_usr);
        panel_add_user_1.add(txt_root_usr);
        panel_add_user_2.add(lb_root_pass);
        panel_add_user_2.add(txt_root_pass);
        panel_add_user_3.add(lb_name);
        panel_add_user_3.add(txt_name);
        panel_add_user_4.add(lb_surname);
        panel_add_user_4.add(txt_surname);
        panel_add_user_5.add(lb_email);
        panel_add_user_5.add(txt_email);
        panel_add_user_6.add(lb_username);
        panel_add_user_6.add(txt_username);
        panel_add_user_7.add(lb_password);
        panel_add_user_7.add(txt_usr_pass);
        panel_add_user_8.add(btn_add);
        win_add_user.setLayout(new GridLayout(8, 1));
        win_add_user.add(panel_add_user_1);
        win_add_user.add(panel_add_user_2);
        win_add_user.add(panel_add_user_3);
        win_add_user.add(panel_add_user_4);
        win_add_user.add(panel_add_user_5);
        win_add_user.add(panel_add_user_6);
        win_add_user.add(panel_add_user_7);
        win_add_user.add(panel_add_user_8);
        win_add_user.pack();
        win_add_user.setResizable(false);
        win_add_user.setVisible(true);
        btn_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties prop = new Properties();
                    prop.load(new FileInputStream("bin/examj.properties"));
                    String host = prop.getProperty("host");
                    String username = txt_root_usr.getText();
                    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                    encryptor.setPassword(examj_gui.uuid.toString());
                    encryptor.setAlgorithm("PBEWithMD5AndDES");
                    String encpass = encryptor
                                     .encrypt(String.valueOf(txt_root_pass.getPassword()));
                    Class.forName("org.gjt.mm.mysql.Driver");
                    String url = "jdbc:mysql://" + host + "/mysql";
                    connection = DriverManager.getConnection(url, username,
                                 encryptor.decrypt(encpass));
                    myStatement = connection.createStatement();

                } catch (Exception exe1) {
                    System.out.println(exe1);
                    error_log.send_error(exe1.getMessage());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                }

                String name = txt_name.getText();
                String surname = txt_surname.getText();
                String email = txt_email.getText();
                String usrname = txt_username.getText();
                char[] usrpassword = txt_usr_pass.getPassword();
                if (name.length() > 0
                || String.valueOf(usrpassword).length() > 0) {
                    ////////////////////////////
                    try {
                        int add_usr = myStatement
                                      .executeUpdate("INSERT IGNORE INTO examj.users(name,surname,email,username,password) VALUES('"
                                                     + name
                                                     + "', '"
                                                     + surname
                                                     + "', '"
                                                     + email
                                                     + "', '"
                                                     + usrname
                                                     + "', md5('"
                                                     + String.valueOf(usrpassword) + "'));");

                        if (add_usr == 1) {
                            JOptionPane.showMessageDialog(win_add_user,
                                                          "User have been successfully Added!");
                            txt_root_pass.setText("");
                            win_add_user.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(win_add_user,
                                                          "User already exists!");
                            txt_root_pass.setText("");
                        }
                        connection.close();

                    } catch (Exception exe2) {
                        System.out.println(exe2);
                        error_log.send_error(exe2.getMessage());
                        examj_gui.txt_msg
                        .setText("Error: See 'error.log' for more information");
                        JOptionPane.showMessageDialog(win_add_user,
                                                      "Error adding user", "ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                    ////////////////////////////
                } else {
                    JOptionPane.showMessageDialog(win_add_user,
                                                  "Username/Password required!");
                }
            }
        });
    }

}
