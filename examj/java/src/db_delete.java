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

public class db_delete {

    JFrame win_db_delete;
    JLabel lb_root_usr, lb_root_pass;
    JTextField txt_root_usr;
    JPasswordField txt_root_pass;
    JButton btn_delete_db;
    JPanel panel_delete_db_1, panel_delete_db_2, panel_delete_db_3;
    static Connection connection = null;
    static Statement myStatement;

    public db_delete() {
        dbase_delete();
    }

    public void dbase_delete() {
        win_db_delete = new JFrame("Delete Database");
        Toolkit toolkit_upload = win_db_delete.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_db_delete.setBounds((screenSize.width - 300) / 2,
                                (screenSize.height - 300) / 2, 300, 300);
        lb_root_usr = new JLabel("MySQL username:");
        lb_root_pass = new JLabel("MySQL password:");
        lb_root_usr.setPreferredSize(new Dimension(150, 16));
        lb_root_pass.setPreferredSize(new Dimension(150, 16));
        txt_root_usr = new JTextField("root", 15);
        txt_root_pass = new JPasswordField(15);
        txt_root_pass.setEchoChar('*');
        btn_delete_db = new JButton("Apply");
        panel_delete_db_1 = new JPanel();
        panel_delete_db_2 = new JPanel();
        panel_delete_db_3 = new JPanel();
        panel_delete_db_1.add(lb_root_usr);
        panel_delete_db_1.add(txt_root_usr);
        panel_delete_db_2.add(lb_root_pass);
        panel_delete_db_2.add(txt_root_pass);
        panel_delete_db_3.add(btn_delete_db);
        win_db_delete.setLayout(new GridLayout(3, 1));
        win_db_delete.add(panel_delete_db_1);
        win_db_delete.add(panel_delete_db_2);
        win_db_delete.add(panel_delete_db_3);
        win_db_delete.pack();
        win_db_delete.setResizable(false);
        win_db_delete.setVisible(true);
        btn_delete_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int more = JOptionPane.showConfirmDialog(null,
                           "Are you sure ?", "Delete Database",
                           JOptionPane.YES_NO_OPTION);
                if (more == JOptionPane.YES_OPTION) {
                    try {
                        Properties prop = new Properties();
                        prop.load(new FileInputStream("bin/examj.properties"));
                        String host = prop.getProperty("host");
                        String username = txt_root_usr.getText();
                        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                        encryptor.setPassword(examj_gui.uuid.toString());
                        encryptor.setAlgorithm("PBEWithMD5AndDES");
                        String encpass = encryptor.encrypt(String
                                                           .valueOf(txt_root_pass.getPassword()));
                        Class.forName("org.gjt.mm.mysql.Driver");
                        String url = "jdbc:mysql://" + host + "/mysql";
                        connection = DriverManager.getConnection(url, username,
                                     encryptor.decrypt(encpass));
                        System.out.println("Connection : [OK]\n");
                        myStatement = connection.createStatement();
                        int a_check = myStatement.executeUpdate("USE mysql;");
                        int b_check = myStatement
                                      .executeUpdate("DELETE FROM user WHERE User='examj_user'");
                        int c_check = myStatement
                                      .executeUpdate("FLUSH PRIVILEGES;");
                        int d_check = myStatement
                                      .executeUpdate("DROP DATABASE examj;");
                        if (a_check == 0 && b_check == 1 && c_check == 0
                        && d_check == 2) {
                            JOptionPane.showMessageDialog(win_db_delete,
                                                          "Database have been successfully deleted!");
                            txt_root_pass.setText("");
                            win_db_delete.setVisible(false);
                        }
                        connection.close();
                    } catch (Exception exe1) {
                        System.out.println(exe1);
                        error_log.send_error(exe1.getMessage());
                        examj_gui.txt_msg
                        .setText("Error: See 'error.log' for more information");
                        JOptionPane.showMessageDialog(win_db_delete,
                                                      "Error deleting database", "ERROR",
                                                      JOptionPane.ERROR_MESSAGE);

                    }
                    ///////////////////////////////////////////////////////////////////
                }
                if (more == JOptionPane.NO_OPTION) {
                    System.out.println("Database have not been deleted");
                }
            }
        });

    }
}
