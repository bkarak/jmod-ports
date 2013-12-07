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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import java.awt.Color;

public class settings_gui {

    JFrame win_settings;
    JLabel lb_host, lb_dbase, lb_mmcu, lb_m, lb_device, lb_baud,
    lb_programmer, lb_font,lb_fontsize_xterm, lb_fontsize_editor,lb_d_email, lb_d_host, lb_d_port,
    lb_d_pass, lb_m_to, lb_gcc, lb_gpp, lb_avr_gcc, lb_java, lb_python,
    lb_fpc, lb_gpc, lb_shell, lb_perl, lb_ruby, lb_lua, lb_clojure, lb_web_browser,
    lb_avrdudepath, lb_code_dir, lb_examiner, lb_gdb, lb_jdb,
    lb_pr_name, lb_pr_surname, lb_pr_id, lb_pr_group, lb_astyle, lb_cs, lb_jre, lb_groovy,lb_scala,lb_sre;
    JTextField txt_host, txt_dbase, txt_mmcu, txt_m, txt_device,
    txt_baud, txt_programmer, txt_fontsize_xterm, txt_fontsize_editor,txt_d_email, txt_d_host,
    txt_d_port, txt_m_to, txt_gcc, txt_gpp, txt_avr_gcc, txt_java,txt_sre,
    txt_python, txt_fpc, txt_gpc, txt_shell, txt_perl, txt_ruby,
    txt_examiner, txt_lua, txt_web_browser, txt_avrdudepath,
    txt_code_dir, txt_gdb, txt_jdb, txt_pr_name, txt_pr_surname, txt_groovy,
    txt_pr_id, txt_pr_group, txt_astyle, txt_cs, txt_jre, txt_clojure, txt_scala;
    JButton btn_save, btn_defaults, btn_close;
    JPanel panel_1, panel_2, panel_3, panel_4, panel_5, panel_6,
    panel_7, panel_8, panel_9, panel_10, panel_11, panel_12, panel_13,
    panel_14, panel_15, panel_16, panel_17, panel_18, panel_19,
    panel_20, panel_21, panel_22, panel_23, panel_24, panel_25,
    panel_26, panel_27, panel_28, panel_29, panel_30,
    panel_31, panel_32, panel_33, panel_34, panel_35,
    panel_36, panel_37, panel_38,  panel_db, panel_avr, panel_avrdude,
    panel_font, panel_email, panel_paths_1, panel_paths_2, panel_details,
    panel_39, panel_40, panel_41, panel_42, panel_43, panel_44, panel_45;
    JPasswordField txt_d_pass;
    StandardPBEStringEncryptor encryptor;
    JTabbedPane tabbedPane;
    public static String code_dir;

    public settings_gui() {
        settings_init();
    }

    public void settings_init() {
        win_settings = new JFrame("Settings");
        tabbedPane = new JTabbedPane();
        Toolkit toolkit_upload = win_settings.getToolkit();
        Dimension screenSize = toolkit_upload.getScreenSize();
        win_settings.setBounds((screenSize.width - 700) / 2,(screenSize.height - 520) / 2, 700, 520);
        lb_host = new JLabel("Hostname:");
        lb_dbase = new JLabel("Database:");
        lb_mmcu = new JLabel("Microcontroller:");
        lb_avrdudepath = new JLabel("Avrdude:");
        lb_m = new JLabel("Microcontroller:");
        lb_device = new JLabel("Device:");
        lb_baud = new JLabel("Baud:");
        lb_programmer = new JLabel("Programmer:");
        lb_font = new JLabel("<HTML><B>Font Size</B></HTML>");
        lb_fontsize_xterm = new JLabel("Xterm:");
        lb_fontsize_editor = new JLabel("Editor:");
        lb_d_email = new JLabel("From:");
        lb_d_host = new JLabel("SMTP Server:");
        lb_d_port = new JLabel("Port:");
        lb_d_pass = new JLabel("Password:");
        lb_m_to = new JLabel("To:");
        lb_gcc = new JLabel("C:");
        lb_gpp = new JLabel("C++:");
        lb_cs = new JLabel("Mono C#:");
        lb_avr_gcc = new JLabel("avr-gcc:");
        lb_java = new JLabel("Java (JDK):");
        lb_python = new JLabel("Python:");
        lb_fpc = new JLabel("Free pascal:");
        lb_gpc = new JLabel("GNU pascal:");
        lb_shell = new JLabel("Shell script:");
        lb_perl = new JLabel("Perl:");
        lb_ruby = new JLabel("Ruby:");
        lb_lua = new JLabel("Lua:");
        lb_web_browser = new JLabel("Web browser:");
        lb_code_dir = new JLabel("Code directory:");
        lb_examiner = new JLabel("Examiner's username:");
        lb_gdb = new JLabel("GDB debugger:");
        lb_jdb = new JLabel("JDB debugger:");
        lb_pr_name = new JLabel("Programmer's name:");
        lb_pr_surname = new JLabel("Programmer's surname:");
        lb_pr_id = new JLabel("Programmer's ID:");
        lb_pr_group = new JLabel("Programmer's group:");
        lb_astyle = new JLabel("Astyle:");
        lb_jre = new JLabel("Java (JRE):");
        lb_clojure = new JLabel("Clojure:");
        lb_groovy = new JLabel("Groovy:");
        lb_scala = new JLabel("Scala:");
        lb_sre = new JLabel("Scala (runtime):");
        lb_sre.setPreferredSize(new Dimension(200, 16));
        lb_scala.setPreferredSize(new Dimension(200, 16));
        lb_groovy.setPreferredSize(new Dimension(200, 16));
        lb_fontsize_editor.setPreferredSize(new Dimension(200, 16));
        lb_clojure.setPreferredSize(new Dimension(200, 16));
        lb_jre.setPreferredSize(new Dimension(200, 16));
        lb_cs.setPreferredSize(new Dimension(200, 16));
        lb_astyle.setPreferredSize(new Dimension(200, 16));
        lb_examiner.setPreferredSize(new Dimension(200, 16));
        lb_pr_name.setPreferredSize(new Dimension(200, 16));
        lb_pr_surname.setPreferredSize(new Dimension(200, 16));
        lb_pr_id.setPreferredSize(new Dimension(200, 16));
        lb_pr_group.setPreferredSize(new Dimension(200, 16));
        lb_code_dir.setPreferredSize(new Dimension(200, 16));
        lb_avrdudepath.setPreferredSize(new Dimension(200, 16));
        lb_gcc.setPreferredSize(new Dimension(200, 16));
        lb_gpp.setPreferredSize(new Dimension(200, 16));
        lb_avr_gcc.setPreferredSize(new Dimension(200, 16));
        lb_java.setPreferredSize(new Dimension(200, 16));
        lb_python.setPreferredSize(new Dimension(200, 16));
        lb_fpc.setPreferredSize(new Dimension(200, 16));
        lb_gpc.setPreferredSize(new Dimension(200, 16));
        lb_shell.setPreferredSize(new Dimension(200, 16));
        lb_perl.setPreferredSize(new Dimension(200, 16));
        lb_ruby.setPreferredSize(new Dimension(200, 16));
        lb_lua.setPreferredSize(new Dimension(200, 16));
        lb_web_browser.setPreferredSize(new Dimension(200, 16));
        lb_host.setPreferredSize(new Dimension(200, 16));
        lb_dbase.setPreferredSize(new Dimension(200, 16));
        lb_mmcu.setPreferredSize(new Dimension(200, 16));
        lb_m.setPreferredSize(new Dimension(200, 16));
        lb_device.setPreferredSize(new Dimension(200, 16));
        lb_baud.setPreferredSize(new Dimension(200, 16));
        lb_programmer.setPreferredSize(new Dimension(200, 16));
        lb_fontsize_xterm.setPreferredSize(new Dimension(200, 16));
        lb_d_email.setPreferredSize(new Dimension(200, 16));
        lb_d_host.setPreferredSize(new Dimension(200, 16));
        lb_d_port.setPreferredSize(new Dimension(200, 16));
        lb_d_pass.setPreferredSize(new Dimension(200, 16));
        lb_m_to.setPreferredSize(new Dimension(200, 16));
        lb_gdb.setPreferredSize(new Dimension(200, 16));
        lb_jdb.setPreferredSize(new Dimension(200, 16));
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("bin/examj.properties"));
            String host = prop.getProperty("host");
            String dbase = prop.getProperty("dbase");
            String mmcu = prop.getProperty("mmcu");
            String m = prop.getProperty("m");
            String device = prop.getProperty("device");
            String baud = prop.getProperty("baud");
            String programmer = prop.getProperty("programmer");
            String font_size_x = prop.getProperty("fontsize_x");
            String font_size_e = prop.getProperty("fontsize_e");
            String d_email = prop.getProperty("d_email");
            String d_host = prop.getProperty("d_host");
            String d_port = prop.getProperty("d_port");
            String m_to = prop.getProperty("m_to");
            String comp_gcc = prop.getProperty("comp_gcc");
            String comp_gpp = prop.getProperty("comp_g++");
            String comp_cs = prop.getProperty("comp_cs");
            String comp_avr_gcc = prop.getProperty("comp_avr_gcc");
            String comp_java = prop.getProperty("comp_java");
            String comp_python = prop.getProperty("comp_python");
            String comp_fpc = prop.getProperty("comp_fpc");
            String comp_gpc = prop.getProperty("comp_gpc");
            String comp_shell = prop.getProperty("comp_shell");
            String comp_perl = prop.getProperty("comp_perl");
            String comp_ruby = prop.getProperty("comp_ruby");
            String comp_lua = prop.getProperty("comp_lua");
            String comp_wb = prop.getProperty("comp_wb");
            String avrdudepath = prop.getProperty("avrdudepath");
            code_dir = prop.getProperty("code_dir");
            String examiner = prop.getProperty("examiner");
            String gdb = prop.getProperty("gdb");
            String jdb = prop.getProperty("jdb");
            String astyle = prop.getProperty("astyle");
            String jre = prop.getProperty("jre");
            String comp_groovy = prop.getProperty("comp_groovy");
            String comp_clojure = prop.getProperty("comp_clojure");
            String comp_scala = prop.getProperty("comp_scala");
            String sre = prop.getProperty("sre");
            txt_code_dir = new JTextField(code_dir, 30);
            txt_host = new JTextField(host, 30);
            txt_dbase = new JTextField(dbase, 30);
            txt_mmcu = new JTextField(mmcu, 30);
            txt_avrdudepath = new JTextField(avrdudepath, 30);
            txt_m = new JTextField(m, 30);
            txt_device = new JTextField(device, 30);
            txt_baud = new JTextField(baud, 30);
            txt_programmer = new JTextField(programmer, 30);
            txt_fontsize_xterm = new JTextField(font_size_x, 30);
            txt_fontsize_editor = new JTextField(font_size_e, 30);
            txt_d_email = new JTextField(d_email, 30);
            txt_d_host = new JTextField(d_host, 30);
            txt_d_port = new JTextField(d_port, 30);
            txt_d_pass = new JPasswordField(30);
            txt_d_pass.setEchoChar('*');
            txt_m_to = new JTextField(m_to, 30);
            txt_gcc = new JTextField(comp_gcc, 30);
            txt_gpp = new JTextField(comp_gpp, 30);
            txt_cs = new JTextField(comp_cs, 30);
            txt_avr_gcc = new JTextField(comp_avr_gcc, 30);
            txt_java = new JTextField(comp_java, 30);
            txt_python = new JTextField(comp_python, 30);
            txt_fpc = new JTextField(comp_fpc, 30);
            txt_gpc = new JTextField(comp_gpc, 30);
            txt_shell = new JTextField(comp_shell, 30);
            txt_perl = new JTextField(comp_perl, 30);
            txt_ruby = new JTextField(comp_ruby, 30);
            txt_lua = new JTextField(comp_lua, 30);
            txt_web_browser = new JTextField(comp_wb, 30);
            txt_avrdudepath = new JTextField(avrdudepath, 30);
            txt_examiner = new JTextField(examiner, 30);
            txt_examiner = new JTextField(examiner, 30);
            txt_gdb = new JTextField(gdb, 30);
            txt_jdb = new JTextField(jdb, 30);
            txt_astyle = new JTextField(astyle, 30);
            txt_jre = new JTextField(jre, 30);
            txt_clojure = new JTextField(comp_clojure, 30);
            txt_groovy = new JTextField(comp_groovy, 30);
            txt_scala = new JTextField(comp_scala, 30);
            txt_sre = new JTextField(sre, 30);

            txt_pr_name = new JTextField(examj_gui.get_pr_name, 30);
            txt_pr_surname = new JTextField(examj_gui.get_pr_surname, 30);
            txt_pr_id = new JTextField(examj_gui.get_pr_id, 30);
            txt_pr_group = new JTextField(examj_gui.get_pr_group, 30);

        } catch (Exception exe1) {
            System.out.println(exe1);
            error_log.send_error(exe1.toString());
            examj_gui.txt_msg
            .setText("Error: See 'error.log' for more information");
        }

        btn_save = new JButton("Save");
        btn_defaults = new JButton("Defaults");
        btn_close = new JButton("Close");
        panel_1 = new JPanel();
        panel_2 = new JPanel();
        panel_3 = new JPanel();
        panel_4 = new JPanel();
        panel_5 = new JPanel();
        panel_6 = new JPanel();
        panel_7 = new JPanel();
        panel_8 = new JPanel();
        panel_9 = new JPanel();
        panel_10 = new JPanel();
        panel_11 = new JPanel();
        panel_12 = new JPanel();
        panel_13 = new JPanel();
        panel_14 = new JPanel();
        panel_15 = new JPanel();
        panel_16 = new JPanel();
        panel_17 = new JPanel();
        panel_18 = new JPanel();
        panel_19 = new JPanel();
        panel_20 = new JPanel();
        panel_21 = new JPanel();
        panel_22 = new JPanel();
        panel_23 = new JPanel();
        panel_24 = new JPanel();
        panel_25 = new JPanel();
        panel_26 = new JPanel();
        panel_27 = new JPanel();
        panel_28 = new JPanel();
        panel_29 = new JPanel();
        panel_30 = new JPanel();
        panel_31 = new JPanel();
        panel_32 = new JPanel();
        panel_db = new JPanel();
        panel_avr = new JPanel();
        panel_avrdude = new JPanel();
        panel_font = new JPanel();
        panel_email = new JPanel();
        panel_paths_1 = new JPanel();
        panel_paths_2 = new JPanel();
        panel_details = new JPanel();
        panel_33 = new JPanel();
        panel_34 = new JPanel();
        panel_35 = new JPanel();
        panel_36 = new JPanel();
        panel_37 = new JPanel();
        panel_38 = new JPanel();
        panel_39 = new JPanel();
        panel_40 = new JPanel();
        panel_41 = new JPanel();
        panel_42 = new JPanel();
        panel_43 = new JPanel();
        panel_44 = new JPanel();
        panel_45 = new JPanel();
        panel_16.add(lb_gcc);
        panel_16.add(txt_gcc);
        panel_17.add(lb_gpp);
        panel_17.add(txt_gpp);
        panel_18.add(lb_avr_gcc);
        panel_18.add(txt_avr_gcc);
        panel_19.add(lb_java);
        panel_19.add(txt_java);
        panel_20.add(lb_python);
        panel_20.add(txt_python);
        panel_21.add(lb_fpc);
        panel_21.add(txt_fpc);
        panel_22.add(lb_gpc);
        panel_22.add(txt_gpc);
        panel_23.add(lb_shell);
        panel_23.add(txt_shell);
        panel_24.add(lb_perl);
        panel_24.add(txt_perl);
        panel_25.add(lb_ruby);
        panel_25.add(txt_ruby);
        panel_26.add(lb_lua);
        panel_26.add(txt_lua);
        panel_27.add(lb_web_browser);
        panel_27.add(txt_web_browser);
        panel_10.add(lb_d_email);
        panel_10.add(txt_d_email);
        panel_11.add(lb_d_host);
        panel_11.add(txt_d_host);
        panel_12.add(lb_d_port);
        panel_12.add(txt_d_port);
        panel_13.add(lb_d_pass);
        panel_13.add(txt_d_pass);
        panel_14.add(lb_m_to);
        panel_14.add(txt_m_to);
        panel_1.add(lb_host);
        panel_1.add(txt_host);
        panel_2.add(lb_dbase);
        panel_2.add(txt_dbase);
        panel_3.add(lb_mmcu);
        panel_3.add(txt_mmcu);
        panel_4.add(lb_m);
        panel_4.add(txt_m);
        panel_5.add(lb_device);
        panel_5.add(txt_device);
        panel_6.add(lb_baud);
        panel_6.add(txt_baud);
        panel_7.add(lb_programmer);
        panel_7.add(txt_programmer);
        panel_8.add(lb_fontsize_xterm);
        panel_8.add(txt_fontsize_xterm);
        panel_41.add(lb_fontsize_editor);
        panel_41.add(txt_fontsize_editor);
        panel_42.add(lb_font);
        panel_28.add(lb_avrdudepath);
        panel_28.add(txt_avrdudepath);
        panel_29.add(lb_code_dir);
        panel_29.add(txt_code_dir);
        panel_30.add(lb_examiner);
        panel_30.add(txt_examiner);
        panel_31.add(lb_gdb);
        panel_31.add(txt_gdb);
        panel_32.add(lb_jdb);
        panel_32.add(txt_jdb);
        panel_33.add(lb_pr_name);
        panel_33.add(txt_pr_name);
        panel_34.add(lb_pr_surname);
        panel_34.add(txt_pr_surname);
        panel_35.add(lb_pr_id);
        panel_35.add(txt_pr_id);
        panel_36.add(lb_pr_group);
        panel_36.add(txt_pr_group);
        panel_37.add(lb_astyle);
        panel_37.add(txt_astyle);
        panel_38.add(lb_cs);
        panel_38.add(txt_cs);
        panel_39.add(lb_jre);
        panel_39.add(txt_jre);
        panel_40.add(lb_clojure);
        panel_40.add(txt_clojure);
        panel_43.add(lb_groovy);
        panel_43.add(txt_groovy);
        panel_44.add(lb_scala);
        panel_44.add(txt_scala);
        panel_45.add(lb_sre);
        panel_45.add(txt_sre);
        panel_9.add(btn_save);
        panel_9.add(btn_defaults);
        panel_9.add(btn_close);
        win_settings.setLayout(new BorderLayout());
        panel_db.setLayout(new GridLayout(15, 1));
        panel_db.add(panel_1);
        panel_db.add(panel_2);
        panel_email.setLayout(new GridLayout(15, 1));
        panel_email.add(panel_10);
        panel_email.add(panel_11);
        panel_email.add(panel_12);
        panel_email.add(panel_13);
        panel_email.add(panel_14);
        panel_avr.setLayout(new GridLayout(15, 1));
        panel_avr.add(panel_3);
        panel_avrdude.setLayout(new GridLayout(15, 1));
        panel_avrdude.add(panel_4);
        panel_avrdude.add(panel_5);
        panel_avrdude.add(panel_6);
        panel_avrdude.add(panel_7);
        panel_font.setLayout(new GridLayout(15, 1));
        panel_font.add(panel_42);
        panel_font.add(panel_8);
        panel_font.add(panel_41);
        panel_paths_1.setLayout(new GridLayout(15, 1));
        panel_paths_1.add(panel_29);
        panel_paths_1.add(panel_16);
        panel_paths_1.add(panel_17);
        panel_paths_1.add(panel_38);
        panel_paths_1.add(panel_18);
        panel_paths_1.add(panel_28);
        panel_paths_1.add(panel_19);
        panel_paths_1.add(panel_20);
        panel_paths_1.add(panel_21);
        panel_paths_1.add(panel_22);
        panel_paths_1.add(panel_23);
        panel_paths_1.add(panel_24);
        panel_paths_1.add(panel_25);
        panel_paths_1.add(panel_26);
        panel_paths_1.add(panel_40);
        panel_paths_2.setLayout(new GridLayout(15, 1));
        panel_paths_2.add(panel_45);
        panel_paths_2.add(panel_44);
        panel_paths_2.add(panel_43);
        panel_paths_2.add(panel_39);
        panel_paths_2.add(panel_27);
        panel_paths_2.add(panel_31);
        panel_paths_2.add(panel_32);
        panel_paths_2.add(panel_37);
        panel_details.setLayout(new GridLayout(15, 1));
        panel_details.add(panel_30);
        panel_details.add(panel_33);
        panel_details.add(panel_34);
        panel_details.add(panel_35);
        panel_details.add(panel_36);
        tabbedPane.addTab("Font", panel_font);
        tabbedPane.addTab("E-Mail", panel_email);
        tabbedPane.addTab("DataBase", panel_db);
        tabbedPane.addTab("avr-gcc", panel_avr);
        tabbedPane.addTab("avrdude", panel_avrdude);
        tabbedPane.addTab("Paths 1/2", panel_paths_1);
        tabbedPane.addTab("Paths 2/2", panel_paths_2);
        tabbedPane.addTab("User Details", panel_details);

        win_settings.add(tabbedPane, BorderLayout.CENTER);
        win_settings.add(panel_9, BorderLayout.SOUTH);
        win_settings.setResizable(false);
        win_settings.setVisible(true);

        btn_close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                win_settings.setVisible(false);
            }
        });

        btn_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int more = JOptionPane.showConfirmDialog(null,
                           "Are you sure ?", "Save", JOptionPane.YES_NO_OPTION);
                if (more == JOptionPane.YES_OPTION) {
                    try {
                        Properties set_prop = new Properties();
                        FileOutputStream out = new FileOutputStream(
                            "bin/examj.properties");
                        set_prop.setProperty("host", txt_host.getText());
                        set_prop.setProperty("dbase", txt_dbase.getText());
                        set_prop.setProperty("mmcu", txt_mmcu.getText());
                        set_prop.setProperty("m", txt_m.getText());
                        set_prop.setProperty("device", txt_device.getText());
                        set_prop.setProperty("baud", txt_baud.getText());
                        set_prop.setProperty("programmer", txt_programmer.getText());
                        set_prop.setProperty("fontsize_x", txt_fontsize_xterm.getText());
                        set_prop.setProperty("fontsize_e", txt_fontsize_editor.getText());
                        set_prop.setProperty("d_email", txt_d_email.getText());
                        set_prop.setProperty("d_host", txt_d_host.getText());
                        set_prop.setProperty("d_port", txt_d_port.getText());
                        set_prop.setProperty("m_to", txt_m_to.getText());
                        char[] password = txt_d_pass.getPassword();
                        encryptor = new org.jasypt.encryption.pbe.StandardPBEStringEncryptor();
                        String uuidpas = examj_gui.uuid.toString();
                        encryptor.setPassword(uuidpas);
                        encryptor.setAlgorithm("PBEWithMD5AndDES");
                        examj_gui.encpass2 = encryptor.encrypt(String.valueOf(password));
                        set_prop.setProperty("comp_gcc", txt_gcc.getText());
                        set_prop.setProperty("comp_g++", txt_gpp.getText());
                        set_prop.setProperty("comp_cs", txt_cs.getText());
                        set_prop.setProperty("comp_avr_gcc", txt_avr_gcc.getText());
                        set_prop.setProperty("comp_java", txt_java.getText());
                        set_prop.setProperty("comp_python", txt_python.getText());
                        set_prop.setProperty("comp_fpc", txt_fpc.getText());
                        set_prop.setProperty("comp_gpc", txt_gpc.getText());
                        set_prop.setProperty("comp_shell", txt_shell.getText());
                        set_prop.setProperty("comp_perl", txt_perl.getText());
                        set_prop.setProperty("comp_ruby", txt_ruby.getText());
                        set_prop.setProperty("comp_lua", txt_lua.getText());
                        set_prop.setProperty("comp_wb", txt_web_browser.getText());
                        set_prop.setProperty("avrdudepath", txt_avrdudepath.getText());
                        set_prop.setProperty("examiner", txt_examiner.getText());
                        set_prop.setProperty("gdb", txt_gdb.getText());
                        set_prop.setProperty("jdb", txt_jdb.getText());
                        set_prop.setProperty("astyle", txt_astyle.getText());
                        set_prop.setProperty("jre", txt_jre.getText());
                        set_prop.setProperty("comp_clojure", txt_clojure.getText());
                        set_prop.setProperty("comp_groovy", txt_groovy.getText());
                        set_prop.setProperty("comp_scala", txt_scala.getText());
                        set_prop.setProperty("sre", txt_sre.getText());
                        set_prop.setProperty("autosave_min", examj_gui.autosave_min);
                        examj_gui.get_pr_name = txt_pr_name.getText();
                        examj_gui.get_pr_surname = txt_pr_surname.getText();
                        examj_gui.get_pr_id = txt_pr_id.getText();
                        examj_gui.get_pr_group = txt_pr_group.getText();
                        String path = txt_code_dir.getText();

                        if (!path.endsWith("/")) {
                            path = path + "/";
                        }
                        if (examj_gui.osName.contains("Windows")) {
                            path = path.replaceAll("/", "\\\\\\\\");
                        } else {

                        }

                        txt_code_dir.setText(path);
                        File theDir = new File(path);
                        if (!theDir.exists()) {
                            System.out.println("creating directory: " + path);
                            if (theDir.mkdir()) {
                                set_prop.setProperty("code_dir", path);
                                examj_gui.code_dir = path;
                                txt_code_dir.setText(path);

                            } else {
                                set_prop.setProperty("code_dir", code_dir);
                                examj_gui.code_dir = code_dir;
                                txt_code_dir.setText(code_dir);
                                JOptionPane.showMessageDialog(win_settings,
                                                              "Permission denied!", "ERROR",
                                                              JOptionPane.ERROR_MESSAGE);

                            }

                        } else {
                            set_prop.setProperty("code_dir", path);
                            examj_gui.code_dir = path;
                            txt_code_dir.setText(path);
                        }

                        set_prop.store(out, "---ExamJ Program Properties---");
                        out.close();
                        examj_gui.update_fc();
                        JOptionPane.showMessageDialog(win_settings,
                                                      "Settings have been successfully changed!",
                                                      "Info", JOptionPane.INFORMATION_MESSAGE);
                        examj_gui.txt_msg.setForeground(Color.blue);
                        examj_gui.txt_msg.setText("Current Directory: "+ examj_gui.code_dir);
                        examj_gui.comp_gcc = txt_gcc.getText();
                        examj_gui.comp_gpp = txt_gpp.getText();
                        examj_gui.comp_cs = txt_cs.getText();
                        examj_gui.comp_avr_gcc = txt_avr_gcc.getText();
                        examj_gui.comp_java = txt_java.getText();
                        examj_gui.comp_fpc = txt_fpc.getText();
                        examj_gui.comp_gpc = txt_gpc.getText();
                        examj_gui.comp_webb = txt_web_browser.getText();
                        examj_gui.comp_python = txt_python.getText();
                        examj_gui.comp_shell = txt_shell.getText();
                        examj_gui.comp_perl = txt_perl.getText();
                        examj_gui.comp_ruby = txt_ruby.getText();
                        examj_gui.comp_lua = txt_lua.getText();
                        examj_gui.avrdudepath = txt_avrdudepath.getText();
                        examj_gui.m = txt_m.getText();
                        examj_gui.device = txt_device.getText();
                        examj_gui.speed = txt_baud.getText();
                        examj_gui.font_size_x = txt_fontsize_xterm.getText();
                        examj_gui.font_size_e = txt_fontsize_editor.getText();
                        examj_gui.programmer = txt_programmer.getText();
                        examj_gui.mmcu = txt_mmcu.getText();
                        examj_gui.gdb = txt_gdb.getText();
                        examj_gui.jdb = txt_jdb.getText();
                        examj_gui.astyle = txt_astyle.getText();
                        examj_gui.jre = txt_jre.getText();
                        examj_gui.comp_clojure = txt_clojure.getText();
                        examj_gui.comp_groovy = txt_groovy.getText();
                        examj_gui.comp_scala = txt_scala.getText();
                        examj_gui.sre = txt_sre.getText();
                        examj_gui.set_font();
                        win_settings.setVisible(false);
                    } catch (Exception exe2) {
                        System.out.println(exe2);
                        error_log.send_error(exe2.getMessage());
                        examj_gui.txt_msg.setText("Error: See 'error.log' for more information");
                    }
                }
                if (more == JOptionPane.NO_OPTION) {
                    System.out.println("Settings have not been saved");
                }
            }
        });

        btn_defaults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sel = tabbedPane.getSelectedIndex();
                if (sel == 2) {
                    txt_host.setText("localhost");
                    txt_dbase.setText("examj");
                } else if (sel == 3) {
                    txt_mmcu.setText("atmega168");
                } else if (sel == 4) {
                    txt_m.setText("m168");
                    txt_device.setText("/dev/ttyUSB0");
                    txt_baud.setText("19200");
                    txt_programmer.setText("stk500v1");
                } else if (sel == 0) {
                    txt_fontsize_xterm.setText("15");
                    txt_fontsize_editor.setText("15");
                } else if (sel == 1) {
                    txt_d_email.setText("user@gmail.com");
                    txt_d_host.setText("smtp.gmail.com");
                    txt_d_port.setText("465");
                    txt_m_to.setText("user@yahoo.com");
                } else if (sel == 5) {
                    txt_code_dir.setText("bin/code/");
                    txt_gcc.setText("gcc");
                    txt_gpp.setText("g++");
                    txt_cs.setText("gmcs");
                    txt_avr_gcc.setText("avr-gcc");
                    txt_avrdudepath.setText("avrdude");
                    txt_java.setText("javac");
                    txt_python.setText("python");
                    txt_fpc.setText("fpc");
                    txt_gpc.setText("gpc");
                    txt_shell.setText("bash");
                    txt_perl.setText("perl");
                    txt_ruby.setText("ruby");
                    txt_lua.setText("lua");
                    txt_clojure.setText("clojure");

                } else if (sel == 6) {
                    txt_sre.setText("scala");
                    txt_scala.setText("scalac");
                    txt_groovy.setText("groovy");
                    txt_jre.setText("java");
                    txt_astyle.setText("astyle");
                    txt_web_browser.setText("firefox");
                    txt_gdb.setText("gdb");
                    txt_jdb.setText("jdb");
                } else if (sel == 7) {
                    txt_examiner.setText("examj_user");
                }
            }
        });
    }
}
