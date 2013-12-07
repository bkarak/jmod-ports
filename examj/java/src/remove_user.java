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

public class remove_user {

    JFrame win_dbremove_user;
    JLabel lb_root_usr, lb_root_pass, lb_examiner;
    JTextField txt_root_usr, txt_examiner;
    JPasswordField txt_root_pass;
    JButton btn_remove_user_db;
    JPanel panel_remove_user_1, panel_remove_user_2,
    panel_remove_user_3, panel_remove_user_4;
    static Connection connection = null;
    static Statement myStatement;

    public remove_user() {
        dbase_remove_user();
    }

    public void dbase_remove_user() {
        win_dbremove_user = new JFrame("Remove User (Examiner)");
        Toolkit toolkit_upload = win_dbremove_user.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_dbremove_user.setBounds((screenSize.width - 300) / 2,
                                    (screenSize.height - 300) / 2, 300, 300);
        lb_root_usr = new JLabel("MySQL username:");
        lb_root_pass = new JLabel("MySQL password:");
        lb_examiner = new JLabel("Examiner username:");
        lb_root_usr.setPreferredSize(new Dimension(150, 16));
        lb_root_pass.setPreferredSize(new Dimension(150, 16));
        lb_examiner.setPreferredSize(new Dimension(150, 16));
        txt_root_usr = new JTextField("root", 15);
        txt_examiner = new JTextField("", 15);
        txt_root_pass = new JPasswordField(15);
        txt_root_pass.setEchoChar('*');
        btn_remove_user_db = new JButton("Apply");
        panel_remove_user_1 = new JPanel();
        panel_remove_user_2 = new JPanel();
        panel_remove_user_3 = new JPanel();
        panel_remove_user_4 = new JPanel();
        panel_remove_user_1.add(lb_root_usr);
        panel_remove_user_1.add(txt_root_usr);
        panel_remove_user_2.add(lb_root_pass);
        panel_remove_user_2.add(txt_root_pass);
        panel_remove_user_4.add(lb_examiner);
        panel_remove_user_4.add(txt_examiner);
        panel_remove_user_3.add(btn_remove_user_db);
        win_dbremove_user.setLayout(new GridLayout(4, 1));
        win_dbremove_user.add(panel_remove_user_1);
        win_dbremove_user.add(panel_remove_user_2);
        win_dbremove_user.add(panel_remove_user_4);
        win_dbremove_user.add(panel_remove_user_3);
        win_dbremove_user.pack();
        win_dbremove_user.setResizable(false);
        win_dbremove_user.setVisible(true);
        btn_remove_user_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                examj_gui.txt_msg.setText("");
                int more = JOptionPane.showConfirmDialog(null,
                           "Are you sure ?", "Remove User",
                           JOptionPane.YES_NO_OPTION);
                if (more == JOptionPane.YES_OPTION) {
                    ///////////////////////////////////////////////////////////////////
                    try {
                        Properties prop = new Properties();
                        prop.load(new FileInputStream("bin/examj.properties"));
                        String host = prop.getProperty("host");
                        String username = txt_root_usr.getText();
                        String examiner = txt_examiner.getText();
                        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                        encryptor.setPassword(examj_gui.uuid.toString());
                        encryptor.setAlgorithm("PBEWithMD5AndDES");
                        String encpass = encryptor.encrypt(String
                                                           .valueOf(txt_root_pass.getPassword()));
                        Class.forName("org.gjt.mm.mysql.Driver");
                        String url = "jdbc:mysql://" + host + "/mysql";
                        connection = DriverManager.getConnection(url, username,
                                     encryptor.decrypt(encpass));
                        myStatement = connection.createStatement();
                        String detele_user = "DELETE FROM examj.users WHERE username = '"
                                             + examiner + "'";
                        int rm_check = myStatement.executeUpdate(detele_user);
                        if (rm_check == 1) {
                            JOptionPane.showMessageDialog(win_dbremove_user,
                                                          "User have been successfully removed!");
                            txt_root_pass.setText("");
                            win_dbremove_user.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(win_dbremove_user,
                                                          "User not found", "ALERT",
                                                          JOptionPane.WARNING_MESSAGE);
                        }
                        connection.close();
                    } catch (Exception exe1) {
                        System.out.println(exe1);
                        error_log.send_error(exe1.getMessage());
                        examj_gui.txt_msg
                        .setText("Error: See 'error.log' for more information");
                        JOptionPane.showMessageDialog(win_dbremove_user,
                                                      "Error removing user", "ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                    ///////////////////////////////////////////////////////////////////
                }
                if (more == JOptionPane.NO_OPTION) {
                    System.out.println("User have not been removed");
                }
            }
        });

    }
}
