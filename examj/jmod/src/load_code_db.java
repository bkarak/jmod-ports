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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class load_code_db {

    JFrame win_db_load_code;
    JLabel lb_user_user, lb_user_usr, lb_user_pass, lb_date, lb_id,
    lb_project;
    JTextField txt_user_usr;
    JPasswordField txt_user_pass;
    JButton btn_load_code, btn_connect, btn_delete_code;
    JComboBox cb_user, cb_date, cb_id, cb_project;
    JPanel panel_load_code_0, panel_load_code_1, panel_load_code_2,
    panel_load_code_3, panel_load_code_4, panel_load_code_5,
    panel_load_code_6;
    static Connection connection = null;
    static Statement myStatement;
    public StandardPBEStringEncryptor encryptor;

    public load_code_db() {
        load_code();
    }

    public int user_exists() {
        JPasswordField pwd = new JPasswordField(10);
        int rowCount = 0;
        int action = JOptionPane.showConfirmDialog(null, pwd,
                     "Enter Examiner Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0) {
            System.out.println("No password entered");
        } else {
            String ex_pass = new String(pwd.getPassword());
            try {
				CountUsers cu = new CountUsers((String) cb_user.getSelectedItem(), ex_pass);
                Statement stmt = cu.getStatement(connection);
                ResultSet rs5 = stmt.executeQuery();
                rs5.next();
                rowCount = rs5.getInt(1);
                rs5.close();
                stmt.close();
            } catch (Exception exe5) {
                System.out.println(exe5);
                error_log.send_error(exe5.getMessage());
                examj_gui.txt_msg
                .setText("Error: See 'error.log' for more information");
                JOptionPane.showMessageDialog(win_db_load_code,
                                              "Error loading data", "ERROR",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }
        return rowCount;
    }

    public void load_code() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(examj_gui.uuid.toString());
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        win_db_load_code = new JFrame("Load/Delete Code From Database");
        Toolkit toolkit_upload = win_db_load_code.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_db_load_code.setBounds((screenSize.width - 440) / 2,
                                   (screenSize.height - 300) / 2, 440, 300);
        lb_user_usr = new JLabel("MySQL username:");
        lb_user_pass = new JLabel("MySQL password:");
        lb_user_user = new JLabel("Examiner username:");
        lb_date = new JLabel("Date:");
        lb_id = new JLabel("ID:");
        lb_project = new JLabel("Project:");
        cb_date = new JComboBox();
        cb_id = new JComboBox();
        cb_project = new JComboBox();
        cb_user = new JComboBox();
        lb_user_user.setPreferredSize(new Dimension(150, 16));
        lb_user_usr.setPreferredSize(new Dimension(150, 16));
        lb_user_pass.setPreferredSize(new Dimension(150, 16));
        lb_date.setPreferredSize(new Dimension(150, 16));
        lb_id.setPreferredSize(new Dimension(150, 16));
        lb_project.setPreferredSize(new Dimension(150, 16));
        txt_user_usr = new JTextField("root", 15);
        txt_user_pass = new JPasswordField(15);
        txt_user_pass.setEchoChar('*');
        txt_user_pass.setText(encryptor.decrypt(examj_gui.encpass1));
        btn_load_code = new JButton("Load");
        btn_connect = new JButton("Connect");
        btn_delete_code = new JButton("Delete");
        btn_load_code.setEnabled(false);
        btn_delete_code.setEnabled(false);
        btn_connect.setEnabled(true);
        panel_load_code_0 = new JPanel();
        panel_load_code_1 = new JPanel();
        panel_load_code_2 = new JPanel();
        panel_load_code_3 = new JPanel();
        panel_load_code_4 = new JPanel();
        panel_load_code_5 = new JPanel();
        panel_load_code_6 = new JPanel();
        panel_load_code_0.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_load_code_1.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_load_code_2.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_load_code_3.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_load_code_4.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_load_code_5.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_load_code_0.add(lb_user_user);
        panel_load_code_0.add(cb_user);
        panel_load_code_1.add(lb_user_usr);
        panel_load_code_1.add(txt_user_usr);
        panel_load_code_2.add(lb_user_pass);
        panel_load_code_2.add(txt_user_pass);
        panel_load_code_3.add(lb_date);
        panel_load_code_3.add(cb_date);
        panel_load_code_4.add(lb_id);
        panel_load_code_4.add(cb_id);
        panel_load_code_5.add(lb_project);
        panel_load_code_5.add(cb_project);
        panel_load_code_6.add(btn_connect);
        panel_load_code_6.add(btn_load_code);
        panel_load_code_6.add(btn_delete_code);
        win_db_load_code.setLayout(new GridLayout(7, 1));
        win_db_load_code.add(panel_load_code_1);
        win_db_load_code.add(panel_load_code_2);
        win_db_load_code.add(panel_load_code_0);
        win_db_load_code.add(panel_load_code_3);
        win_db_load_code.add(panel_load_code_4);
        win_db_load_code.add(panel_load_code_5);
        win_db_load_code.add(panel_load_code_6);
        win_db_load_code.setVisible(true);

        btn_connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Properties prop = new Properties();
                    prop.load(new FileInputStream("bin/examj.properties"));
                    String host = prop.getProperty("host");
                    String username = txt_user_usr.getText();
                    char[] password = txt_user_pass.getPassword();
                    examj_gui.encpass1 = encryptor.encrypt(String
                                                           .valueOf(password));

                    Class.forName("org.gjt.mm.mysql.Driver");
                    String url = "jdbc:mysql://" + host + "/mysql";
                    connection = DriverManager.getConnection(url, username,
                                 encryptor.decrypt(examj_gui.encpass1));
					SelectUsernamesQuery suq = new SelectUsernamesQuery();
                    Statement stmt = suq.getStatement(connection);
                    cb_user.removeAllItems();
                    ResultSet rs0 = stmt.executeQuery();
                    while (rs0.next()) {
                        cb_user.addItem(rs0.getString("username"));
                    }
                    btn_load_code.setEnabled(true);
                    btn_delete_code.setEnabled(true);
                    btn_connect.setEnabled(false);
                    rs0.close();
                    stmt.close();
                } catch (Exception exe1) {
                    System.out.println(exe1);
                    error_log.send_error(exe1.toString());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                    JOptionPane.showMessageDialog(win_db_load_code,
                                                  "Error connecting to database", "ERROR",
                                                  JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cb_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb_date.removeAllItems();
                try {                    
					DateExaminerQuery deq = new DateExaminerQuery((String) cb_user.getSelectedItem());
					Statement stmt = deq.getStatement(connection);
                    ResultSet rs1 = stmt.executeQuery();
                    while (rs1.next()) {
                        cb_date.addItem(rs1.getString("date"));
                    }
                    rs1.close();
                    stmt.close();
                } catch (Exception exe0) {
                    System.out.println(exe0);
                    error_log.send_error(exe0.toString());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                }

            }
        });

        cb_date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb_id.removeAllItems();
                try {
					CodeUploadedQuery cuq = new CodeUploadedQuery((String) cb_date.getSelectedItem(), (String) cb_user.getSelectedItem());
                    Statement stmt = cuq.getStatement(connection);
                    ResultSet rs2 = stmt.executeQuery();
                    while (rs2.next()) {
                        cb_id.addItem(rs2.getString("id"));
                    }
                    rs2.close();
                    stmt.close();
                } catch (Exception exe2) {
                    System.out.println(exe2);
                    error_log.send_error(exe2.toString());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                }
            }
        });

        cb_id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cb_project.removeAllItems();
                try {
					CodeUploadedQueryPrjName cuqpn = new CodeUploadedQueryPrjName((String) cb_date.getSelectedItem(), 
																				  (String) cb_id.getSelectedItem(),
																				  (String) cb_user.getSelectedItem());
                    Statement stmt = cuqpn.getStatement(connection);
                    ResultSet rs3 = stmt.executeQuery();
                    while (rs3.next()) {
                        cb_project.addItem(rs3.getString("prj_name"));
                    }
                    rs3.close();
                    stmt.close();
                } catch (Exception exe3) {
                    System.out.println(exe3);
                    error_log.send_error(exe3.toString());
                    examj_gui.txt_msg
                    .setText("Error: See 'error.log' for more information");
                }
            }
        });

        btn_load_code.addActionListener(new ActionListener() {

            public void loadco() {
                examj_gui.txt_msg.setText("");
                int rowCount = user_exists();
                if (rowCount == 1) {
                    try {
						CodeUploadedQueryLang cuql = new CodeUploadedQueryLang((String) cb_date.getSelectedItem(),
																			   (String) cb_id.getSelectedItem(),
																			   (String) cb_project.getSelectedItem());
                        Statement stmt = cuql.getStatement(connection);
                        ResultSet rs4 = stmt.executeQuery();

                        while (rs4.next()) {
                            examj_gui.txt_code.setText(rs4.getString("code"));
                            String language = rs4.getString("lang");
                            if ("C".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(0);
                            } else if ("C++".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(1);
                            } else if ("C#".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(2);
                            } else if ("AVR-gcc".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(3);
                            } else if ("Java".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(4);
                            } else if ("Python".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(5);
                            } else if ("Free Pascal".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(6);
                            } else if ("GNU Pascal".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(7);
                            } else if (".sh".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(8);
                            } else if ("Perl".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(9);
                            } else if ("Ruby".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(10);
                            } else if ("Lua".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(11);
                            } else if ("HTML".equals(language)) {
                                examj_gui.cmp_compiler.setSelectedIndex(12);
                            }
                        }

                        examj_gui.txt_file.setText((String) cb_project.getSelectedItem());
                        stmt.close();
                        rs4.close();
                        connection.close();
                    } catch (Exception exe4) {
                        System.out.println(exe4);
                        error_log.send_error(exe4.toString());
                        examj_gui.txt_msg
                        .setText("Error: See 'error.log' for more information");
                        JOptionPane.showMessageDialog(win_db_load_code,
                                                      "Error loading data", "ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(win_db_load_code,
                                                  "Access denied!");
                }
                win_db_load_code.setVisible(false);
            }

            public void actionPerformed(ActionEvent e) {
                String file_len = examj_gui.txt_file.getText();
                String code_len = examj_gui.txt_code.getText();
                if (file_len.length() > 0 || code_len.length() > 0) {
                    int more = JOptionPane.showConfirmDialog(null,
                               "Save Before Load ?", "Save",
                               JOptionPane.YES_NO_OPTION);
                    if (more == JOptionPane.YES_OPTION) {
                        if (file_len.length() == 0) {
                            JOptionPane.showMessageDialog(win_db_load_code,
                                                          "Set Filename Please...");
                        } else {
                            examj_gui.save(1);
                            loadco();
                        }
                    }
                    if (more == JOptionPane.NO_OPTION) {
                        loadco();
                    }
                } else {
                    loadco();
                }
            }

        });

        btn_delete_code.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String delete_code = "";
                int rowCount = user_exists();
                if (rowCount == 1) {

                    ArrayList<String> possibilities = new ArrayList<String>();

                    possibilities.add("Delete this project only");
                    possibilities.add("Delete all projects of this ID");
                    possibilities.add("Delete all projects of this date");

                    String delete_project = (String) JOptionPane.showInputDialog(win_db_load_code, "Select:",
                                            "Delete Project (Warning)", JOptionPane.QUESTION_MESSAGE, null, possibilities.toArray(new String[possibilities.size()]), possibilities.get(0));
                    if (delete_project.length()>0) {
						SQLQuery<SQLConfiguration> sqlq = null;
                        if (delete_project == possibilities.get(0)) {
							sqlq = new DeleteCodeUploadedPrjName((String) cb_date.getSelectedItem(),
																 (String) cb_id.getSelectedItem(),
																 (String) cb_project.getSelectedItem());
                        }
                        if (delete_project == possibilities.get(1)) {
							sqlq = new DeleteCodeUploadedID((String) cb_date.getSelectedItem(), (String) cb_id.getSelectedItem());
                        }
                        if (delete_project == possibilities.get(2)) {
							sqlq = new DeleteCodeUploadedDate((String) cb_date.getSelectedItem());
                        }

                        try {
                            myStatement = sqlq.getStatement(connection);
                            int rm_check = myStatement.executeUpdate();
                            if (rm_check > 0) {
                                JOptionPane.showMessageDialog(win_db_load_code,
                                                              "Code have been successfully removed!");

                            } else {
                                JOptionPane.showMessageDialog(win_db_load_code,
                                                              "Code not found", "ALERT",
                                                              JOptionPane.WARNING_MESSAGE);
                            }

                            cb_user.setSelectedIndex(0);
                        } catch (Exception exe4) {
                            System.out.println(exe4);
                            error_log.send_error(exe4.toString());
                            examj_gui.txt_msg
                            .setText("Error: See 'error.log' for more information");
                            JOptionPane.showMessageDialog(win_db_load_code,
                                                          "Error loading data", "ERROR",
                                                          JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("Project have not been deleted");
                    }

                } else {
                    JOptionPane.showMessageDialog(win_db_load_code,
                                                  "Access denied!");
                }
            }
        });
    }
}
