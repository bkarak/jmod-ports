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

public class db_setup {

    JFrame win_dbsetup;
    JLabel lb_root_usr, lb_root_pass;
    JTextField txt_root_usr;
    JPasswordField txt_root_pass;
    JButton btn_setup_db;
    JPanel panel_setup_db_1, panel_setup_db_2, panel_setup_db_3;
    static Connection connection = null;
    static Statement myStatement;

    public db_setup() {
        dbase_setup();
    }

    public void dbase_setup() {
        win_dbsetup = new JFrame("Database Setup");
        Toolkit toolkit_upload = win_dbsetup.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_dbsetup.setBounds((screenSize.width - 300) / 2,
                              (screenSize.height - 300) / 2, 300, 300);
        lb_root_usr = new JLabel("MySQL username:");
        lb_root_pass = new JLabel("MySQL password:");
        lb_root_usr.setPreferredSize(new Dimension(150, 16));
        lb_root_pass.setPreferredSize(new Dimension(150, 16));
        txt_root_usr = new JTextField("root", 15);
        txt_root_pass = new JPasswordField(15);
        txt_root_pass.setEchoChar('*');
        btn_setup_db = new JButton("Apply");
        panel_setup_db_1 = new JPanel();
        panel_setup_db_2 = new JPanel();
        panel_setup_db_3 = new JPanel();
        panel_setup_db_1.add(lb_root_usr);
        panel_setup_db_1.add(txt_root_usr);
        panel_setup_db_2.add(lb_root_pass);
        panel_setup_db_2.add(txt_root_pass);
        panel_setup_db_3.add(btn_setup_db);
        win_dbsetup.setLayout(new GridLayout(3, 1));
        win_dbsetup.add(panel_setup_db_1);
        win_dbsetup.add(panel_setup_db_2);
        win_dbsetup.add(panel_setup_db_3);
        win_dbsetup.pack();
        win_dbsetup.setResizable(false);
        win_dbsetup.setVisible(true);
        btn_setup_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                examj_gui.txt_msg.setText("");
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
                    int a_check = myStatement.executeUpdate("USE mysql;");
                    int b_check = myStatement
                                  .executeUpdate("GRANT INSERT ON examj.* TO 'examj_user'@'%' IDENTIFIED BY 'examj_user';");
                    int c_check = myStatement
                                  .executeUpdate("FLUSH PRIVILEGES;");
                    int d_check = myStatement
                                  .executeUpdate("CREATE DATABASE examj;");
                    int e_check = myStatement.executeUpdate("USE examj;");

                    String create_table_1 = "CREATE TABLE IF NOT EXISTS `code_uploaded`("
                                            + "`date` date NOT NULL default '0000-00-00',"
                                            + "`time` time NOT NULL default '00:00:00',"
                                            + "`name` varchar(50) NOT NULL,"
                                            + "`surname` varchar(50) NOT NULL,"
                                            + "`id` varchar(50) NOT NULL,"
                                            + "`group` varchar(50) NOT NULL,"
                                            + "`prj_name` varchar(50) NOT NULL,"
                                            + "`lang` varchar(50) NOT NULL,"
                                            + "`code` LONGTEXT NOT NULL,"
                                            + "`grade` FLOAT DEFAULT NULL,"
                                            + "`notes` LONGTEXT NOT NULL,"
                                            + "`examiner` varchar(50) NOT NULL,"
                                            + "`uuid` varchar(50) NOT NULL,"
                                            + "PRIMARY KEY(`id`,`prj_name`) ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";

                    String create_table_2 = "CREATE TABLE IF NOT EXISTS `users`("
                                            + "`name` varchar(50) NOT NULL,"
                                            + "`surname` varchar(50) NOT NULL,"
                                            + "`email` varchar(100) NOT NULL,"
                                            + "`username` varchar(50) NOT NULL,"
                                            + "`password` varchar(100) NOT NULL,"
                                            + "PRIMARY KEY(`username`) ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";

                    String insert_data_1 = "INSERT INTO users VALUES('examj_user','examj_user','examj_user','examj_user','3da531d62a9c3c0b4ca09c2e3c1e2a97')";

                    int f_check = myStatement.executeUpdate(create_table_1);
                    int g_check = myStatement.executeUpdate(create_table_2);
                    int h_check = myStatement.executeUpdate(insert_data_1);

                    if (a_check == 0 && b_check == 0 && c_check == 0
                            && d_check == 1 && e_check == 0 && f_check == 0
                    && g_check == 0 && h_check == 1) {
                        JOptionPane.showMessageDialog(win_dbsetup,
                                                      "Database setup completed!");
                        txt_root_pass.setText("");
                        win_dbsetup.setVisible(false);
                    }
                    connection.close();

                } catch (Exception exe1) {
                    System.out.println(exe1);
                    error_log.send_error(exe1.getMessage());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                    JOptionPane.showMessageDialog(win_dbsetup,
                                                  "Error setting up database", "ERROR",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
