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
 ***************************************************************************

 * CODE DIVISIONS:
 * 1. MAIN CLASS
 * 2. MAIN
 * 3. CONSTRUCTOR
 * 4. INIT
 * 5. ACTION LISTENERS
 * 6. METHODS
 * 7. INNER CLASSES
 */
package examj;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.undo.UndoManager;
import javax.swing.Timer;
import org.w3c.tidy.Tidy;

/////////////////////////////////////////////////////////////////////////////////////////// 1. MAIN CLASS ****
public class examj_gui {

    public static JFrame win;
    JFrame win_find, win_licence, win_error_log;
    public static JEditorPane txt_code;
    public static JTextArea txt_licence, txt_msg, txt_error_log;
    JButton btn_compile, btn_run, btn_find, btn_debug;
    JScrollPane code_scroll, msg_scroll, licence_scroll,
    error_log_scroll;
    JPanel panel_north, panel_center, panel_south, panel_licence,
    panel_wc;
    public static JTextField txt_file;
    JTextField txt_find;
    JLabel lb_file, lb_line, lb_find;
    public static JFileChooser fc = new JFileChooser(),
    dc = new JFileChooser(), wc = new JFileChooser();
    public static String strLine, version = "10.6", arguments = " ",
                                            class_name = " ", code_dir, comp_gcc, comp_gpp, comp_avr_gcc,comp_cs,
                                                         comp_java, comp_fpc, comp_gpc, comp_webb, comp_python, comp_shell,
                                                         comp_perl, comp_ruby, comp_lua, comp_clojure, comp_groovy, comp_scala, avrdudepath, m, device, speed,
                                                         font_size_x, font_size_e,programmer, mmcu, encpass1, encpass2, examiner, gdb,
                                                         jdb, get_pr_name, get_pr_surname, get_pr_id, get_pr_group, astyle,autosave_min,jre, sre, osName;
    JMenuBar menuBar;
    JMenu menu_file, menu_help, menu_program, menu_db, menu_tools;
    JMenuItem item_exit, item_open, item_compile, item_run, item_about,
    item_find, menuItem, item_save, item_licence;
    JMenuItem item_upload_db, item_donate, item_db_setup,
    item_add_user_db, item_db_delete, item_print, item_settings,
    item_backup, item_debug, item_terminal, item_load_code_db,
    item_error_log, item_rm_user_db, item_formatc, item_analyze;
    UndoManager undoManager;
    public static JComboBox cmp_compiler;
    public static UUID uuid, uuid_code;
    JSplitPane split_pane_hor, split_pane_ver;
    static Timer autosave;

    /////////////////////////////////////////////////////////////////////////////////////////// 2. MAIN ****
    public static void main(String[] args) {
        new examj_gui();
    }

    /////////////////////////////////////////////////////////////////////////////////////////// 3. CONSTRUCTOR ****
    public examj_gui() {
        init();
    }

    /////////////////////////////////////////////////////////////////////////////////////////// 4. INIT ****
    void init() {
        osName = System.getProperty("os.name");
        jsyntaxpane.DefaultSyntaxKit.initKit();
        File prop_ex = new File("bin/examj.properties");
        if (prop_ex.exists()) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream("bin/examj.properties"));
                comp_gcc = prop.getProperty("comp_gcc");
                comp_gpp = prop.getProperty("comp_g++");
                comp_cs = prop.getProperty("comp_cs");
                comp_avr_gcc = prop.getProperty("comp_avr_gcc");
                comp_java = prop.getProperty("comp_java");
                comp_fpc = prop.getProperty("comp_fpc");
                comp_gpc = prop.getProperty("comp_gpc");
                comp_webb = prop.getProperty("comp_wb");
                comp_python = prop.getProperty("comp_python");
                comp_shell = prop.getProperty("comp_shell");
                comp_perl = prop.getProperty("comp_perl");
                comp_ruby = prop.getProperty("comp_ruby");
                comp_lua = prop.getProperty("comp_lua");
                comp_clojure = prop.getProperty("comp_clojure");
                comp_groovy = prop.getProperty("comp_groovy");
                comp_scala = prop.getProperty("comp_scala");
                avrdudepath = prop.getProperty("avrdudepath");
                m = prop.getProperty("m");
                device = prop.getProperty("device");
                speed = prop.getProperty("baud");
                programmer = prop.getProperty("programmer");
                mmcu = prop.getProperty("mmcu");
                font_size_x = prop.getProperty("fontsize_x");
                font_size_e = prop.getProperty("fontsize_e");
                code_dir = prop.getProperty("code_dir");
                examiner = prop.getProperty("examiner");
                gdb = prop.getProperty("gdb");
                jdb = prop.getProperty("jdb");
                astyle = prop.getProperty("astyle");
                jre = prop.getProperty("jre");
                sre = prop.getProperty("sre");
                autosave_min = prop.getProperty("autosave_min");
                File theDir = new File(code_dir);
                if (!theDir.exists()) {
                    FileOutputStream out = new FileOutputStream("bin/examj.properties");
                    prop.setProperty("code_dir", "bin/code/");
                    prop.store(out, "---ExamJ Program Properties---");
                    out.close();
                    code_dir = "bin/code/";
                }

            } catch (Exception exe) {
                exception_inp(exe);
            }
        } else {
            new create_properties();
            System.exit(0);
        }
        uuid = UUID.randomUUID();
        uuid_code = UUID.randomUUID();
        win = new JFrame("ExamJ IDE " + version);
        win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        btn_compile = new JButton("Compile");
        btn_run = new JButton("Run");
        btn_debug = new JButton("Debug");
        txt_code = new JEditorPane();
        txt_msg = new JTextArea(5, 50);
        txt_msg.setEditable(false);
        txt_msg.setForeground(Color.blue);
        txt_msg.setText("Current Directory: " + code_dir);
        panel_north = new JPanel();
        panel_center = new JPanel(new BorderLayout());
        panel_south = new JPanel(new BorderLayout());
        code_scroll = new JScrollPane(txt_code);
        msg_scroll = new JScrollPane(txt_msg);
        txt_file = new JTextField("", 12);
        lb_file = new JLabel("Filename: ");
        lb_line = new JLabel("Document Information... ");
        cmp_compiler = new JComboBox();
        cmp_compiler.addItem("C");
        cmp_compiler.addItem("C++");
        cmp_compiler.addItem("C#");
        cmp_compiler.addItem("AVR-gcc");
        cmp_compiler.addItem("Java");
        cmp_compiler.addItem("Python");
        cmp_compiler.addItem("Free Pascal");
        cmp_compiler.addItem("GNU Pascal");
        cmp_compiler.addItem("Shell script");
        cmp_compiler.addItem("Perl");
        cmp_compiler.addItem("Ruby");
        cmp_compiler.addItem("Lua");
        cmp_compiler.addItem("HTML");
        cmp_compiler.addItem("Clojure");
        cmp_compiler.addItem("Groovy");
        cmp_compiler.addItem("Scala");
        code_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        msg_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        win.setLayout(new BorderLayout());
        panel_north.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel_north.add(btn_compile);
        panel_north.add(btn_run);
        panel_north.add(btn_debug);
        panel_north.add(lb_file);
        panel_north.add(txt_file);
        panel_north.add(cmp_compiler);
        panel_center.add(lb_line, BorderLayout.SOUTH);
        panel_center.add(code_scroll, BorderLayout.CENTER);
        panel_south.add(msg_scroll, BorderLayout.CENTER);
        menuBar = new JMenuBar();
        menu_file = new JMenu("File");
        menu_program = new JMenu("Program");
        menu_db = new JMenu("DataBase");
        menu_tools = new JMenu("Tools");
        menu_help = new JMenu("Help");
        menuBar.add(menu_file);
        menuBar.add(menu_program);
        menuBar.add(menu_db);
        menuBar.add(menu_tools);
        menuBar.add(menu_help);
        item_open = new JMenuItem("Open...");
        item_save = new JMenuItem("Save");
        item_exit = new JMenuItem("Exit");
        item_upload_db = new JMenuItem("Send code");
        item_add_user_db = new JMenuItem("Add user");
        item_compile = new JMenuItem("Compile");
        item_run = new JMenuItem("Run");
        item_about = new JMenuItem("About");
        item_licence = new JMenuItem("Licence");
        item_find = new JMenuItem("Find");
        item_donate = new JMenuItem("Donate");
        item_db_setup = new JMenuItem("Setup");
        item_db_delete = new JMenuItem("Delete");
        item_print = new JMenuItem("Print");
        item_settings = new JMenuItem("Settings");
        item_backup = new JMenuItem("Backup 'code' directory");
        item_debug = new JMenuItem("Debug");
        item_terminal = new JMenuItem("Terminal");
        item_load_code_db = new JMenuItem("Load/Delete Code From Database");
        item_error_log = new JMenuItem("View error.log");
        item_rm_user_db = new JMenuItem("Remove user");
        item_formatc = new JMenuItem("Code formatter");
        item_analyze = new JMenuItem("Code analyzer");
        menu_file.add(item_open);
        menu_file.add(item_save);
        menu_file.add(item_print);
        menu_file.add(item_exit);
        menu_program.add(item_compile);
        menu_program.add(item_run);
        menu_program.add(item_debug);
        menu_program.add(item_find);
        menu_program.add(item_upload_db);
        menu_program.add(item_load_code_db);
        menu_help.add(item_about);
        menu_help.add(item_licence);
        menu_help.add(item_donate);
        menu_db.add(item_db_setup);
        menu_db.add(item_db_delete);
        menu_db.add(item_add_user_db);
        menu_db.add(item_rm_user_db);
        menu_tools.add(item_analyze);
        menu_tools.add(item_formatc);
        menu_tools.add(item_terminal);
        menu_tools.add(item_backup);
        menu_tools.add(item_error_log);
        menu_tools.add(item_settings);
        wc.setCurrentDirectory(new File(code_dir));
        panel_wc = new JPanel();
        panel_wc.setLayout(new BorderLayout());
        panel_wc.add(wc, BorderLayout.CENTER);
        panel_wc.setBorder(BorderFactory.createTitledBorder("File List"));
        split_pane_ver = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split_pane_ver.setBorder(null);
        split_pane_ver.setTopComponent(panel_center);
        split_pane_ver.setBottomComponent(panel_south);
        split_pane_ver.setOneTouchExpandable(true);
        split_pane_ver.setContinuousLayout(true);
        split_pane_ver.setDividerLocation(400);
        split_pane_hor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split_pane_hor.setBorder(null);
        split_pane_hor.setLeftComponent(panel_wc);
        split_pane_hor.setRightComponent(split_pane_ver);
        split_pane_hor.setOneTouchExpandable(true);
        split_pane_hor.setContinuousLayout(true);
        win.add(panel_north, BorderLayout.NORTH);
        win.add(split_pane_hor, BorderLayout.CENTER);
        panel_center.setBorder(BorderFactory.createTitledBorder("Source Code"));
        panel_south.setBorder(BorderFactory.createTitledBorder("Messages"));
        item_open.addActionListener(new open_action());
        item_save.addActionListener(new save_action());
        item_compile.addActionListener(new compile_action());
        item_run.addActionListener(new run_action());
        item_find.addActionListener(new find_action());
        item_debug.addActionListener(new debug_action());
        btn_compile.addActionListener(new compile_action());
        btn_run.addActionListener(new run_action());
        btn_debug.addActionListener(new debug_action());
        item_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                                 ActionEvent.ALT_MASK));
        item_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                 ActionEvent.ALT_MASK));
        item_print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                                  ActionEvent.ALT_MASK));
        item_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                 ActionEvent.ALT_MASK));
        item_compile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                                    ActionEvent.ALT_MASK));
        item_run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                                ActionEvent.ALT_MASK));
        item_analyze.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                                    ActionEvent.ALT_MASK));
        item_load_code_db.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
                                         ActionEvent.ALT_MASK));
        item_upload_db.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
                                      ActionEvent.ALT_MASK));
        item_find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                                 ActionEvent.ALT_MASK));
        item_debug.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                                  ActionEvent.ALT_MASK));
        win.setJMenuBar(menuBar);
        win.pack();
        BasicSplitPaneUI ui = (BasicSplitPaneUI) split_pane_hor.getUI();
        BasicSplitPaneDivider divider = ui.getDivider();
        JButton button = (JButton) divider.getComponent(0);
        button.doClick();
        cmp_compiler.setSelectedIndex(0);
        Toolkit myToolkit = win.getToolkit();
        Dimension screenSize = myToolkit.getScreenSize();
        win.setBounds((screenSize.width - 600) / 2,
                      (screenSize.height - 800) / 2, 800, 600);
        win.setExtendedState(win.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        fc.setFileFilter(new SCFilter());
        wc.setFileFilter(new SCFilter());
        win.setVisible(true);
        int autosave_interval = Integer.parseInt(autosave_min);
        int as_min = autosave_interval * 1000 * 60;
        autosave = new Timer(as_min, new autosave_evt());
        txt_code.setContentType("text/plain");


        /////////////////////////////////////////////////////////////////////////////////////////// 5. ACTION LISTENERS ****
        item_analyze.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    int more = JOptionPane.showConfirmDialog(null,
                               "Are you sure ?", "Code analyzer",
                               JOptionPane.YES_NO_OPTION);
                    if (more == JOptionPane.YES_OPTION) {
                        if (osName.contains("Windows")) {
                            if ("Java".equals(cmp_compiler.getSelectedItem())) {
                                File filechk = new File(code_dir + txt_file.getText() + ".java");
                                if (filechk.exists()) {
                                    save(0);
                                    Process a1 = Runtime.getRuntime().exec("bin/tools/pmd-4.2.5/bin/pmd.bat "
                                                                           + code_dir + txt_file.getText() + ".java" + " text basic,imports,unusedcode,strings,braces");
                                    process_out(a1);
                                } else {
                                    JOptionPane.showMessageDialog(win_error_log,"File not found, you must first save the source code!", "ALERT",
                                                                  JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(win_error_log,"Analyzer not found", "ALERT",
                                                              JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            if ("Java".equals(cmp_compiler.getSelectedItem())) {
                                File filechk = new File(code_dir + txt_file.getText() + ".java");
                                if (filechk.exists()) {
                                    save(0);
                                    Process a1 = Runtime.getRuntime().exec(comp_shell + " bin/tools/pmd-4.2.5/bin/pmd.sh "
                                                                           + code_dir + txt_file.getText() + ".java" + " text basic,imports,unusedcode,strings,braces");
                                    process_out(a1);
                                } else {
                                    JOptionPane.showMessageDialog(win_error_log,"File not found, you must first save the source code!", "ALERT",
                                                                  JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(win_error_log,"Analyzer not found", "ALERT",
                                                              JOptionPane.WARNING_MESSAGE);
                            }

                        }
                    }
                    if (more == JOptionPane.NO_OPTION) {
                        txt_msg
                        .setText("Code analyzing cancelled by user."
                                 + "\n");
                    }
                } catch (Exception exe) {
                    exception_inp(exe);
                }

            }
        });

        item_formatc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String fname = null;
                int more = JOptionPane.showConfirmDialog(null,
                           "Are you sure ?", "Code formatter",
                           JOptionPane.YES_NO_OPTION);
                if (more == JOptionPane.YES_OPTION) {

                    if ("Java".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir + txt_file.getText() + ".java";
                        format_open(fname, "java");
                    } else if ("C".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir + txt_file.getText() + ".c";
                        format_open(fname, "gnu");
                    } else if ("C++".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir + txt_file.getText() + ".cpp";
                        format_open(fname, "gnu");
                    } else if ("C#".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir + txt_file.getText() + ".cs";
                        format_open(fname, "gnu");
                    } else if ("AVR-gcc".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir + txt_file.getText()
                                + ".c";
                        format_open(fname, "gnu");
                    } else if ("Perl".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir + txt_file.getText()
                                + ".pl";
                        format_open(fname, "gnu");
                    } else if ("Python".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir
                                + txt_file.getText()
                                + ".py";
                        format_open(fname, "");
                    } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir
                                + txt_file.getText()
                                + ".rb";
                        format_open(fname, "");
                    } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir
                                + txt_file
                                .getText()
                                + ".sh";
                        format_open(fname, "");
                    } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
                        fname = code_dir
                                + txt_file
                                .getText()
                                + ".html";
                        format_open(fname, "");
                    } else {
                        JOptionPane
                        .showMessageDialog(
                            win,
                            "Code formatter not found",
                            "Code formatter",
                            JOptionPane.WARNING_MESSAGE);
                    }

                }

                if (more == JOptionPane.NO_OPTION) {
                    txt_msg
                    .setText("Code formatting cancelled by user."
                             + "\n");
                }

            }
        });

        win.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                on_exit();
            }

        });

        wc.addActionListener(new ActionListener() {
            public void openfile() {
                File file = wc.getSelectedFile();
                int dotPos = file.getName().lastIndexOf(".");
                String extension = "Error";
                if (dotPos != -1) {
                    extension = file.getName().substring(dotPos);
                }
                if (".c".equals(extension) || ".cpp".equals(extension)|| ".cs".equals(extension)
                        || ".java".equals(extension) || ".py".equals(extension)
                        || ".pas".equals(extension) || ".sh".equals(extension)
                        || ".pl".equals(extension) || ".rb".equals(extension)
                        || ".lua".equals(extension) || ".clj".equals(extension)
                        || ".groovy".equals(extension)|| ".scala".equals(extension)
                || ".html".equals(extension)|| ".bat".equals(extension)) {

                    String getfilename = file.getName().substring(0,
                                         file.getName().lastIndexOf("."));
                    txt_file.setText(getfilename);
                    main_open_file(file);
                    if (".c".equals(extension)) {
                        cmp_compiler.setSelectedIndex(0);
                    } else if (".cpp".equals(extension)) {
                        cmp_compiler.setSelectedIndex(1);
                    } else if (".cs".equals(extension)) {
                        cmp_compiler.setSelectedIndex(2);
                    } else if (".java".equals(extension)) {
                        cmp_compiler.setSelectedIndex(4);
                    } else if (".py".equals(extension)) {
                        cmp_compiler.setSelectedIndex(5);
                    } else if (".pas".equals(extension)) {
                        cmp_compiler.setSelectedIndex(6);
                    } else if (".sh".equals(extension)||".bat".equals(extension)) {
                        cmp_compiler.setSelectedIndex(8);
                    } else if (".pl".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(9);
                    } else if (".rb".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(10);
                    } else if (".lua".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(11);
                    } else if (".html".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(12);
                    } else if (".clj".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(13);
                    } else if (".groovy".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(14);
                    } else if (".scala".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(15);
                    }
                }

                else {
                    JOptionPane.showMessageDialog(win, "Not supported file!");
                }
            }

            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals(JFileChooser.APPROVE_SELECTION)) {

                    if (txt_file.getDocument().getLength() > 0) {
                        int more = JOptionPane.showConfirmDialog(null,
                                   "Save Before Open ?", "Save",
                                   JOptionPane.YES_NO_OPTION);
                        if (more == JOptionPane.YES_OPTION) {
                            save(1);
                            openfile();
                        }
                        if (more == JOptionPane.NO_OPTION) {
                            openfile();
                        }
                    } else {
                        openfile();
                    }

                } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
                    txt_msg.setText("File open cancelled by user." + "\n");
                }
                update_fc();
            }

        });

        txt_code.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent ev) {
                int position = 0;
                int column = 0;
                int chars = txt_code.getDocument().getLength();
                for (int i = 0; i < txt_code.getCaretPosition(); i++) {
                    if (txt_code.getText().charAt(i) == '\n') {
                        position++;
                        column = 0;
                    } else {
                        column++;
                    }

                }
                int cur_line = (int) (position + 1);
                lb_line.setText("Ln: " + cur_line + " Col: " + column + " Ch: "
                                + chars);
            }
        });

        item_licence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                win_licence = new JFrame("Licence");
                txt_licence = new JTextArea(15, 40);
                txt_licence.setEditable(false);
                licence_scroll = new JScrollPane(txt_licence);
                Toolkit toolkit_licence = win_licence.getToolkit();
                Dimension screenSize = toolkit_licence.getScreenSize();
                win_licence.setBounds((screenSize.width - 450) / 2,
                                      (screenSize.height - 400) / 2, 600, 600);
                win_licence.setLayout(new BorderLayout());
                win_licence.add(licence_scroll, BorderLayout.CENTER);
                txt_licence
                .setText("Copying Permission Statemen\n\nThis program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.\n\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. \n\nYou should have received a copy of the GNU General Public License along with this program; If not, see \n\nhttp://www.gnu.org/licenses/");
                txt_licence.setLineWrap(true);
                txt_licence.setWrapStyleWord(true);
                win_licence.pack();
                win_licence.setResizable(false);
                win_licence.setVisible(true);
            }
        });

        item_error_log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                show_error_action.show_dialog();
            }
        });

        item_about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JOptionPane
                .showMessageDialog(
                    win,
                    "ExamJ IDE "
                    + version
                    + "\n\nAuthor: \nGeorge Hadjikyriacou 2008-2010\n\nghadjikyriacou@yahoo.com",
                    "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        item_backup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                int second = now.get(Calendar.SECOND);
                int day = now.get(Calendar.DAY_OF_MONTH);
                int month = now.get(Calendar.MONTH) + 1;
                int year = now.get(Calendar.YEAR);
                dc.setSelectedFile(new File("examj_code_" + day + month + year
                                            + hour + minute + second + ".zip"));
                int returnVal = dc.showSaveDialog(win);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FolderZiper.zipFolder(code_dir, dc.getSelectedFile()
                                              .toString());
                    } catch (Exception exe) {
                        exception_inp(exe);
                    }
                }

            }
        });

        item_donate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Runtime
                    .getRuntime()
                    .exec(
                        comp_webb
                        + " https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=6860213");
                } catch (Exception exe) {
                    exception_inp(exe);
                }

            }
        });

        item_upload_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new code_send();
            }
        });

        item_db_setup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new db_setup();
            }
        });

        item_db_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new db_delete();
            }
        });

        item_settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new settings_gui();
            }
        });

        item_print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    txt_code.print();
                } catch (Exception exe) {
                    exception_inp(exe);
                }
            }
        });

        item_add_user_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new add_user();
            }
        });

        item_rm_user_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new remove_user();
            }
        });

        item_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                on_exit();
            }
        });

        item_load_code_db.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new load_code_db();
            }
        });

        item_terminal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd.exe /c start cmd ");
                    } else {

                        Runtime
                        .getRuntime()
                        .exec(
                            "xterm -font -*-fixed-medium-r-*-*-"
                            + font_size_x
                            + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                            + comp_shell);
                    }
                } catch (Exception exe) {
                    exception_inp(exe);
                }
            }
        });

        cmp_compiler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String temp_cp = txt_code.getText();
                if ("C".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/c","Compile","Run");
                } else if ("C++".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/cpp","Compile","Run");
                } else if ("C#".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/java","Compile","Run");
                } else if ("Java".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/java","Compile","Run");
                } else if ("Python".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/python","Save","Run");
                } else if ("Free Pascal".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/c","Compile","Run");
                } else if ("GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/c","Compile","Run");
                } else if ("AVR-gcc".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/c","Compile","Upload to I/O Board");
                } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/bash","Save","Run");
                } else if ("Perl".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/bash","Save","Run");
                } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/ruby","Save","Run");
                } else if ("Lua".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/lua","Save","Run");
                } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/xhtml","Save","Run");
                } else if ("Clojure".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/clojure","Save","Run");
                } else if ("Groovy".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/groovy","Save","Run");
                } else if ("Scala".equals(cmp_compiler.getSelectedItem())) {
                    dyn_but_cont("text/scala","Compile","Run");
                }
                txt_code.setText(temp_cp);
                temp_cp = null;
                txt_code.setCaretPosition(0);
                set_font();
            }
        });

    }

    /////////////////////////////////////////////////////////////////////////////////////////// 6. METHODS ****
    public void dyn_but_cont(String content, String set_c, String set_r) {
        txt_code.setContentType(content);
        btn_compile.setText(set_c);
        btn_run.setText(set_r);
        item_compile.setText(set_c);
        item_run.setText(set_r);
    }

    public void run_cmd(String lang, String file_ext) {
        try {
            arguments = (String) JOptionPane
                        .showInputDialog(
                            win,
                            "Value(s):",
                            "Arguments",
                            JOptionPane.INFORMATION_MESSAGE);
            Runtime
            .getRuntime()
            .exec(
                "cmd.exe /c start "
                + lang
                + " "
                + code_dir
                + txt_file.getText()
                + file_ext
                + " "
                + arguments);
        } catch (Exception exe) {
            exception_inp(exe);
        }
    }

    public void run_xterm(String lang, String file_ext) {
        try {
            arguments = (String) JOptionPane.showInputDialog(win,"Value(s):","Arguments",JOptionPane.INFORMATION_MESSAGE);
            Runtime.getRuntime()
            .exec(
                "xterm -font -*-fixed-medium-r-*-*-"
                + font_size_x
                + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                + lang
                + " "
                + code_dir
                + txt_file.getText()
                + file_ext + " "
                + arguments);
        } catch (Exception exe) {
            exception_inp(exe);
        }
    }

    public static void set_font() {
        Font code_font = new Font("Courier 10 Pitch", Font.PLAIN, Integer.parseInt(font_size_e));
        txt_code.setFont(code_font);
    }

    void exception_inp(Exception exe) {
        System.out.println(exe);
        error_log.send_error(exe.getMessage());
        examj_gui.txt_msg.setText("Error: See 'error.log' for more information");
    }
    void main_open_file(File file_name) {
        try {
            txt_code.setText("");
            FileInputStream fr = new FileInputStream(file_name);
            InputStreamReader isr = new InputStreamReader(fr, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append('\n');
            }
            reader.close();
            txt_code.setText(buffer.toString());
            txt_code.setCaretPosition(0);

        } catch (Exception exe) {
            exception_inp(exe);
        }
    }

    void format_open(String filen, String style) {
        File filechk = new File(filen);
        if (filechk.exists()) {
            try {
                if ("Java".equals(cmp_compiler.getSelectedItem())
                        || "C".equals(cmp_compiler.getSelectedItem())
                        || "C++".equals(cmp_compiler.getSelectedItem())
                        || "C#".equals(cmp_compiler.getSelectedItem())
                        || "AVR-gcc".equals(cmp_compiler.getSelectedItem())
                        || "Perl".equals(cmp_compiler.getSelectedItem())) {
                    Process f1 = Runtime.getRuntime().exec(
                                     astyle + " --suffix=~ --style=" + style + " "
                                     + filen);
                    process_out(f1);
                    main_open_file(filechk);
                } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
                    Process f1 = Runtime.getRuntime().exec(
                                     comp_ruby + " bin/tools/ruby_formatter.rb "
                                     + filen + " " + filen);
                    process_out(f1);
                    main_open_file(filechk);
                } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
                    Process f1 = Runtime.getRuntime().exec(
                                     comp_ruby + " bin/tools/bash_formatter.rb "
                                     + filen + " " + filen);
                    process_out(f1);
                    main_open_file(filechk);
                } else if ("Python".equals(cmp_compiler.getSelectedItem())) {
                    Process f1 = Runtime
                                 .getRuntime()
                                 .exec(
                                     comp_python
                                     + " bin/tools/python_formatter.py -c "
                                     + filen);
                    process_out(f1);
                    Process f2 = Runtime
                                 .getRuntime()
                                 .exec(
                                     comp_python
                                     + " bin/tools/python_formatter.py -r "
                                     + filen);
                    process_out(f1);
                    process_out(f2);
                    main_open_file(filechk);
                } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
                    String temp_code = txt_code.getText();
                    Tidy tidy = new Tidy();
                    tidy.setIndentContent(true);
                    File filehtml = new File(code_dir
                                             + "out.html");
                    tidy.parse(new FileInputStream(filen),
                               new FileOutputStream(filehtml));
                    main_open_file(filehtml);
                    try {
                        File filebak = new File(filen + "~");
                        Writer output = new BufferedWriter(
                            new FileWriter(filebak));
                        output.write(temp_code);
                        output.close();
                        Writer output2 = new BufferedWriter(
                            new FileWriter(filen));
                        output2.write(txt_code.getText());
                        output2.close();
                        filehtml.delete();
                    } catch (Exception exe) {
                        exception_inp(exe);
                    }
                    temp_code = null;
                }
            } catch (Exception exe) {
                exception_inp(exe);
            }

        } else {
            JOptionPane.showMessageDialog(win,
                                          "File not found, you must first save the source code!",
                                          "Code formatter", JOptionPane.WARNING_MESSAGE);

        }
        update_fc();
    }

    String class_selector() {
        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.add("Select a class...");
        File code_directory = new File(code_dir);
        File[] class_files = code_directory.listFiles();
        for (int i = 0; i < class_files.length; i++) {
            if (class_files[i].getName().endsWith(".class")) {
                int dotPos = class_files[i].getName().indexOf(".");
                possibilities
                .add(class_files[i].getName().substring(0, dotPos));
            }
        }

        class_name = (String) JOptionPane.showInputDialog(win, "Class Name:",
                     "Class", JOptionPane.QUESTION_MESSAGE, null, possibilities
                     .toArray(new String[possibilities.size()]), possibilities.get(0));
        return class_name;
    }

    void on_exit() {
        autosave.stop();
        if (txt_file.getDocument().getLength() > 0 || txt_code.getDocument().getLength() > 0) {
            int more = JOptionPane.showConfirmDialog(null,
                       "Save Before Exit ?", "Save", JOptionPane.YES_NO_OPTION);
            if (more == JOptionPane.YES_OPTION) {
                if (txt_file.getDocument().getLength() == 0) {
                    JOptionPane
                    .showMessageDialog(win, "Set Filename Please...");
                } else {
                    save(1);
                    System.exit(0);
                }
            }
            if (more == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    public static void save(int chk_exists) {
        autosave.start();
        txt_msg.setText("");
        String filename = null;
        Calendar save_time = Calendar.getInstance();

        if ("C".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText() + ".c";
        } else if ("C++".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText() + ".cpp";
        } else if ("C#".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText() + ".cs";
        } else if ("Java".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText() + ".java";
        } else if ("Python".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText() + ".py";
        } else if ("Free Pascal".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText() + ".pas";
        } else if ("GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText()
                       + ".pas";
        } else if ("AVR-gcc".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir + txt_file.getText()
                       + ".c";
        } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
            if (osName.contains("Windows")) {
                filename = code_dir+ txt_file.getText() + ".bat";
            } else {
                filename = code_dir+ txt_file.getText() + ".sh";
            }

        } else if ("Perl".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file.getText()
                       + ".pl";
        } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file.getText()
                       + ".rb";
        } else if ("Lua".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file
                       .getText()
                       + ".lua";
        } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file
                       .getText()
                       + ".html";
        } else if ("Clojure".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file
                       .getText()
                       + ".clj";
        } else if ("Groovy".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file
                       .getText()
                       + ".groovy";
        } else if ("Scala".equals(cmp_compiler.getSelectedItem())) {
            filename = code_dir
                       + txt_file
                       .getText()
                       + ".scala";
        }
        File filechk = new File(filename);
        if (filechk.exists() && chk_exists==1) {
            int more = JOptionPane.showConfirmDialog(null,
                       "File Already Exist. Replace?", "File Exist",
                       JOptionPane.YES_NO_OPTION);
            if (more == JOptionPane.YES_OPTION) {
                try {
                    Writer output = new BufferedWriter(new FileWriter(filename));
                    output.write(txt_code.getText());
                    output.close();
                    txt_msg.setForeground(Color.blue);
                    txt_msg.setText("File saved ... "+save_time.getTime());
                } catch (Exception exe) {
                    System.out.println(exe);
                    error_log.send_error(exe.getMessage());
                    examj_gui.txt_msg.setText("Error: See 'error.log' for more information");
                }
            }
            if (more == JOptionPane.NO_OPTION) {
                txt_msg.setText("");
                txt_msg.append("File saving cancelled by user....");
            }
        } else {
            try {
                Writer output = new BufferedWriter(new FileWriter(filename));
                output.write(txt_code.getText());
                output.close();
                txt_msg.setForeground(Color.blue);
                txt_msg.setText("File saved ... "+save_time.getTime());
            } catch (Exception exe) {
                System.out.println(exe);
                error_log.send_error(exe.getMessage());
                examj_gui.txt_msg.setText("Error: See 'error.log' for more information");
            }
        }
        update_fc();
    }

    public static void update_fc() {
        wc.setCurrentDirectory(new File(code_dir));
        fc.setCurrentDirectory(new File(code_dir));
        wc.updateUI();
        fc.updateUI();
    }

    void process_out(Process inp_proc) {
        String line = null, line2 = null;
        txt_msg.setText("");
        txt_msg.setForeground(Color.blue);
        try {
            InputStream stdout = inp_proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdout);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                txt_msg.append(line + "\n");
            }
            InputStream stderr = inp_proc.getErrorStream();
            InputStreamReader isr2 = new InputStreamReader(stderr);
            BufferedReader br2 = new BufferedReader(isr2);
            while ((line2 = br2.readLine()) != null) {
                txt_msg.append(line2 + "\n");
            }
        } catch (Exception exe) {
            exception_inp(exe);
        }
    }

    void compiler_error_h(Process inp_proc) {
        int count = 0;
        String line = null;
        txt_msg.setForeground(Color.red);
        try {
            InputStream stderr = inp_proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                txt_msg.append(line + "\n");
                count++;
            }
            if (count == 0) {
                txt_msg.setForeground(Color.blue);
                txt_msg.setText("No errors :)");
            }
        } catch (Exception exe) {
            exception_inp(exe);
        }
    }

    void compile() {
        int count = 0;
        String line = null;
        try {

            if ("C".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".c";
                Process c = Runtime.getRuntime().exec(
                                comp_gcc + " " + code_dir + filename + " -o "
                                + code_dir + txt_file.getText() + " -g");
                compiler_error_h(c);
            }

            else if ("C++".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".cpp";
                Process cc = Runtime.getRuntime().exec(
                                 comp_gpp + " " + code_dir + filename + " -o "
                                 + code_dir + txt_file.getText() + " -g");
                compiler_error_h(cc);

            } else if ("C#".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".cs";
                Process cc = Runtime.getRuntime().exec(comp_cs + " -debug " + code_dir + filename);
                compiler_error_h(cc);

            } else if ("Java".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".java";
                Process java = Runtime.getRuntime().exec(
                                   comp_java + " -g " + code_dir + filename);
                compiler_error_h(java);

            } else if ("Scala".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".scala";
                Process scala = Runtime.getRuntime().exec(
                                    comp_scala + " -d " + code_dir+" "+ code_dir + filename);
                compiler_error_h(scala);

            }

            else if ("Free Pascal".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".pas";
                Process fpc = Runtime.getRuntime().exec(
                                  comp_fpc + " " + code_dir + filename
                                  + " -g");
                try {
                    InputStream stderr = fpc.getInputStream();
                    InputStreamReader isr = new InputStreamReader(
                        stderr);
                    BufferedReader br = new BufferedReader(isr);

                    while ((line = br.readLine()) != null) {
                        txt_msg.append(line + "\n");
                        count++;
                    }

                } catch (Exception exe) {
                    exception_inp(exe);
                }

            }

            else if ("GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
                save(1);
                String filename = txt_file.getText() + ".pas";
                Process gnu_pas = Runtime.getRuntime().exec(
                                      comp_gpc + " " + code_dir + filename
                                      + " -o " + code_dir
                                      + txt_file.getText() + " -g");
                compiler_error_h(gnu_pas);

            }

            else if ("AVR-gcc".equals(cmp_compiler.getSelectedItem())) {
                save(1);

                String filename_c = txt_file.getText()
                                    + ".c";
                String filename_o = txt_file.getText()
                                    + ".out";
                String filename_hex = txt_file.getText()
                                      + ".hex";

                File file_c_chk = new File(code_dir
                                           + filename_c);
                File file_o_chk = new File(code_dir
                                           + filename_o);

                if (file_c_chk.exists()) {

                    Process avr_gcc_o = Runtime
                                        .getRuntime()
                                        .exec(
                                            comp_avr_gcc
                                            + " -g -Os -mmcu="
                                            + mmcu + " -o "
                                            + code_dir
                                            + filename_o
                                            + " "
                                            + code_dir
                                            + filename_c);
                    compiler_error_h(avr_gcc_o);
                }

                if (file_o_chk.exists()) {
                    Process avr_gcc_hex = Runtime
                                          .getRuntime().exec(
                                              "avr-objcopy -O ihex -R .eeprom "
                                              + code_dir
                                              + filename_o
                                              + " "
                                              + code_dir
                                              + filename_hex);
                    InputStream stderr = avr_gcc_hex
                                         .getErrorStream();
                    InputStreamReader isr = new InputStreamReader(
                        stderr);
                    BufferedReader br1 = new BufferedReader(
                        isr);
                    while ((line = br1.readLine()) != null) {
                        txt_msg.append(line + "\n");
                        count++;
                    }
                    if (count == 0) {
                        txt_msg.setText(".HEX created");
                    }
                }

                else {

                    examj_gui.txt_msg
                    .append("file .o not found.");
                }
            }

            else if ("Python".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("Perl".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("Lua".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("Clojure".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            } else if ("Groovy".equals(cmp_compiler.getSelectedItem())) {
                save(1);
            }
        } catch (Exception exe) {
            exception_inp(exe);
        }

        wc.updateUI();
    }

/////////////////////////////////////////////////////////////////////////////////////////// 7. INNER CLASSES ****

    class autosave_evt implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            save(0);
        }
    }

    class compile_action implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (txt_file.getDocument().getLength() == 0) {
                JOptionPane.showMessageDialog(win, "Set Filename Please...");
            } else {
                compile();
            }
        }
    }

    class run_action implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (txt_file.getDocument().getLength() == 0) {
                JOptionPane.showMessageDialog(win, "Set Filename Please...");
            } else {
                try {
                    if (osName.contains("Windows")) {
                        if ("C++".equals(cmp_compiler.getSelectedItem()) || "C".equals(cmp_compiler.getSelectedItem()) || "C#".equals(cmp_compiler.getSelectedItem())||"Free Pascal".equals(cmp_compiler.getSelectedItem())|| "GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
                            arguments = (String) JOptionPane.showInputDialog(
                                            win, "Value(s):", "Arguments",
                                            JOptionPane.INFORMATION_MESSAGE);
                            Runtime.getRuntime().exec(
                                "cmd.exe /c start " + code_dir
                                + txt_file.getText() + ".exe "
                                + arguments);
                        } else if ("Java".equals(cmp_compiler.getSelectedItem())) {
                            class_name = class_selector();
                            File class_exists = new File(code_dir
                                                         + class_name + ".class");
                            if (class_exists.exists()
                                    && class_name.length() > 0) {
                                arguments = (String) JOptionPane
                                            .showInputDialog(
                                                win,
                                                "Value(s):",
                                                "Arguments",
                                                JOptionPane.INFORMATION_MESSAGE);
                                Runtime.getRuntime().exec(
                                    "cmd.exe /c start "+jre+" -classpath "
                                    + code_dir + " "
                                    + class_name + " "
                                    + arguments);
                            } else {
                                JOptionPane.showMessageDialog(win,"Class Not Found", "ERROR",JOptionPane.ERROR_MESSAGE);
                            }
                        } else if ("Scala".equals(cmp_compiler.getSelectedItem())) {
                            class_name = class_selector();
                            File class_exists = new File(code_dir
                                                         + class_name + ".class");
                            if (class_exists.exists()
                                    && class_name.length() > 0) {
                                arguments = (String) JOptionPane
                                            .showInputDialog(
                                                win,
                                                "Value(s):",
                                                "Arguments",
                                                JOptionPane.INFORMATION_MESSAGE);
                                Runtime.getRuntime().exec(
                                    "cmd.exe /c start "+sre+" -classpath "
                                    + code_dir + " "
                                    + class_name + " "
                                    + arguments);
                            } else {
                                JOptionPane.showMessageDialog(win,"Class Not Found", "ERROR",JOptionPane.ERROR_MESSAGE);
                            }
                        } else if ("Python".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(comp_python, ".py");
                        } else if ("AVR-gcc".equals(cmp_compiler.getSelectedItem())) {
                            String line = null;
                            int count = 0;

                            Process avrdude = Runtime
                                              .getRuntime()
                                              .exec(
                                                  avrdudepath
                                                  + " -V -F -c "
                                                  + programmer
                                                  + " -p "
                                                  + m
                                                  + " -b "
                                                  + speed
                                                  + " -P "
                                                  + device
                                                  + " -U flash:w:"
                                                  + code_dir
                                                  + txt_file
                                                  .getText()
                                                  + ".hex");
                            InputStream stderr = avrdude
                                                 .getErrorStream();
                            InputStreamReader isr = new InputStreamReader(
                                stderr);
                            BufferedReader br2 = new BufferedReader(
                                isr);
                            while ((line = br2.readLine()) != null) {
                                txt_msg.append("\n" + line
                                               + "\n");
                                count++;
                            }
                            if (count == 0) {
                                txt_msg.setText("OK :)");
                            }
                        } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(" ", ".bat");
                        } else if ("Perl".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(comp_perl, ".pl");
                        } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(comp_ruby, ".rb");
                        } else if ("Lua".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(comp_lua, ".lua");
                        } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
                            Runtime.getRuntime().exec(comp_webb+ " "+ code_dir+ txt_file.getText()+ ".html");
                        } else if ("Clojure".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(comp_clojure, ".clj");
                        } else if ("Groovy".equals(cmp_compiler.getSelectedItem())) {
                            run_cmd(comp_groovy, ".groovy");
                        }
                    } else {

                        if ("C++".equals(cmp_compiler.getSelectedItem()) || "C".equals(cmp_compiler.getSelectedItem()) || "Free Pascal".equals(cmp_compiler.getSelectedItem())|| "GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
                            arguments = (String) JOptionPane.showInputDialog(
                                            win, "Value(s):", "Arguments",
                                            JOptionPane.INFORMATION_MESSAGE);
                            Runtime
                            .getRuntime()
                            .exec(
                                "xterm -font -*-fixed-medium-r-*-*-"
                                + font_size_x
                                + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                                + code_dir + "./"
                                + txt_file.getText() + " "
                                + arguments);
                        }

                        if ("C#".equals(cmp_compiler.getSelectedItem())) {
                            arguments = (String) JOptionPane.showInputDialog(
                                            win, "Value(s):", "Arguments",
                                            JOptionPane.INFORMATION_MESSAGE);
                            Runtime
                            .getRuntime()
                            .exec(
                                "xterm -font -*-fixed-medium-r-*-*-"
                                + font_size_x
                                + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                                + code_dir + "./"
                                + txt_file.getText() + ".exe "
                                + arguments);
                        }

                        else if ("Java".equals(cmp_compiler.getSelectedItem())) {

                            class_name = class_selector();

                            File class_exists = new File(code_dir
                                                         + class_name + ".class");
                            if (class_exists.exists()
                                    && class_name.length() > 0) {

                                arguments = (String) JOptionPane
                                            .showInputDialog(
                                                win,
                                                "Value(s):",
                                                "Arguments",
                                                JOptionPane.INFORMATION_MESSAGE);
                                Runtime
                                .getRuntime()
                                .exec(
                                    "xterm -font -*-fixed-medium-r-*-*-"
                                    + font_size_x
                                    + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "+jre+" -classpath "
                                    + code_dir + " "
                                    + class_name + " "
                                    + arguments);
                            } else {
                                JOptionPane.showMessageDialog(win,
                                                              "Class Not Found", "ERROR",
                                                              JOptionPane.ERROR_MESSAGE);
                            }
                        } else if ("Scala".equals(cmp_compiler.getSelectedItem())) {

                            class_name = class_selector();

                            File class_exists = new File(code_dir
                                                         + class_name + ".class");
                            if (class_exists.exists()
                                    && class_name.length() > 0) {

                                arguments = (String) JOptionPane
                                            .showInputDialog(
                                                win,
                                                "Value(s):",
                                                "Arguments",
                                                JOptionPane.INFORMATION_MESSAGE);
                                Runtime
                                .getRuntime()
                                .exec(
                                    "xterm -font -*-fixed-medium-r-*-*-"
                                    + font_size_x
                                    + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "+sre+" -classpath "
                                    + code_dir + " "
                                    + class_name + " "
                                    + arguments);
                            } else {
                                JOptionPane.showMessageDialog(win,
                                                              "Class Not Found", "ERROR",
                                                              JOptionPane.ERROR_MESSAGE);
                            }
                        } else if ("Python".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_python, ".py");
                        } else if ("Perl".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_perl, ".pl");
                        } else if ("Ruby".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_ruby, ".rb");
                        } else if ("Lua".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_lua, ".lua");
                        } else if ("Shell script".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_shell, ".sh");
                        } else if ("HTML".equals(cmp_compiler.getSelectedItem())) {
                            Runtime.getRuntime().exec(comp_webb+ " "+ code_dir+ txt_file.getText()+ ".html");
                        } else if ("Clojure".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_clojure, ".clj");
                        } else if ("Groovy".equals(cmp_compiler.getSelectedItem())) {
                            run_xterm(comp_groovy, ".groovy");
                        } else if ("AVR-gcc".equals(cmp_compiler.getSelectedItem())) {
                            String line = null;
                            int count = 0;

                            Process avrdude = Runtime
                                              .getRuntime()
                                              .exec(
                                                  avrdudepath
                                                  + " -V -F -c "
                                                  + programmer
                                                  + " -p "
                                                  + m
                                                  + " -b "
                                                  + speed
                                                  + " -P "
                                                  + device
                                                  + " -U flash:w:"
                                                  + code_dir
                                                  + txt_file
                                                  .getText()
                                                  + ".hex");
                            InputStream stderr = avrdude
                                                 .getErrorStream();
                            InputStreamReader isr = new InputStreamReader(
                                stderr);
                            BufferedReader br2 = new BufferedReader(
                                isr);
                            while ((line = br2
                                           .readLine()) != null) {
                                txt_msg
                                .append("\n"
                                        + line
                                        + "\n");
                                count++;
                            }
                            if (count == 0) {
                                txt_msg
                                .setText("OK :)");
                            }
                        }

                    }
                } catch (Exception exe) {
                    exception_inp(exe);
                }
            }
        }
    }

    class open_action implements ActionListener {
        public void open() {

            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream("bin/examj.properties"));
                code_dir = prop.getProperty("code_dir");
            } catch (Exception exe) {
                exception_inp(exe);
            }

            fc.setCurrentDirectory(new File(code_dir));
            int returnVal = fc.showOpenDialog(win);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                int dotPos = file.getName().lastIndexOf(".");
                String extension = "Error";
                if (dotPos != -1) {
                    extension = file.getName().substring(dotPos);
                }
                if (".c".equals(extension) || ".cpp".equals(extension)|| ".cs".equals(extension)
                        || ".java".equals(extension) || ".py".equals(extension)
                        || ".pas".equals(extension) || ".sh".equals(extension)
                        || ".pl".equals(extension) || ".rb".equals(extension)
                        || ".lua".equals(extension)|| ".html".equals(extension)
                        || ".clj".equals(extension) || ".groovy".equals(extension)
                        || ".bat".equals(extension)|| ".scala".equals(extension)) {

                    String getfilename = file.getName().substring(0,
                                         file.getName().lastIndexOf("."));
                    txt_file.setText(getfilename);
                    main_open_file(file);
                    if (".c".equals(extension)) {
                        cmp_compiler.setSelectedIndex(0);
                    } else if (".cpp".equals(extension)) {
                        cmp_compiler.setSelectedIndex(1);
                    } else if (".cs".equals(extension)) {
                        cmp_compiler.setSelectedIndex(2);
                    } else if (".java".equals(extension)) {
                        cmp_compiler.setSelectedIndex(4);
                    } else if (".py".equals(extension)) {
                        cmp_compiler.setSelectedIndex(5);
                    } else if (".pas".equals(extension)) {
                        cmp_compiler.setSelectedIndex(6);
                    } else if (".sh".equals(extension) || ".bat".equals(extension)) {
                        cmp_compiler.setSelectedIndex(8);
                    } else if (".pl".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(9);
                    } else if (".rb".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(10);
                    } else if (".lua".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(11);
                    } else if (".html".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(12);
                    } else if (".clj".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(13);
                    } else if (".groovy".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(14);
                    } else if (".scala".equals(extension)) {
                        cmp_compiler
                        .setSelectedIndex(15);
                    }
                } else {
                    JOptionPane.showMessageDialog(win, "Not supported file!");
                }
            } else {
                txt_msg.append("File opening cancelled by user." + "\n");
            }
        }

        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == item_open) {
                if (txt_file.getDocument().getLength() > 0 || txt_code.getDocument().getLength() > 0) {
                    int more = JOptionPane.showConfirmDialog(null,
                               "Save Before Open ?", "Save",
                               JOptionPane.YES_NO_OPTION);
                    if (more == JOptionPane.YES_OPTION) {
                        if (txt_file.getDocument().getLength() == 0) {
                            JOptionPane.showMessageDialog(win,
                                                          "Set Filename Please...");
                        } else {
                            save(1);
                            open();
                        }
                    }
                    if (more == JOptionPane.NO_OPTION) {
                        open();
                    }
                } else {
                    open();
                }
            }
            update_fc();
        }
    }

    class save_action implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (txt_file.getDocument().getLength() == 0) {
                JOptionPane.showMessageDialog(win, "Set Filename Please...");
            } else {
                save(1);
            }
        }
    }

    class find_action implements ActionListener {
        public void actionPerformed(ActionEvent evb) {
            if (txt_code.getDocument().getLength() > 0) {
                win_find = new JFrame("Find");
                lb_find = new JLabel("Enter Word:");
                txt_find = new JTextField("", 12);
                btn_find = new JButton("Find");
                win_find.setLayout(new FlowLayout());
                win_find.add(lb_find);
                win_find.add(txt_find);
                win_find.add(btn_find);
                Toolkit toolkit_find = win_find.getToolkit();
                Dimension screenSize = toolkit_find.getScreenSize();
                win_find.setBounds(600, 100, (screenSize.width) / 2,
                                   (screenSize.height) / 2);
                win_find.pack();
                win_find.setResizable(false);
                win_find.setVisible(true);
                txt_code.requestFocusInWindow();
                txt_code.setCaretPosition(0);
                btn_find.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        txt_code.requestFocusInWindow();
                        String find = txt_find.getText();
                        if (find != null) {
                            int index = txt_code.getText().indexOf(find,
                                                                   (int) txt_code.getCaretPosition());
                            txt_code.select(index, index + find.length());
                            if (txt_code.getText().lastIndexOf(index) == index) {
                                txt_code.setCaretPosition(0);
                            }
                        }
                    }
                });

            } else {
                JOptionPane.showMessageDialog(win, "No Code...");
            }

        }
    }

    class debug_action implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (osName.contains("Windows")) {
                try {

                    if ("C".equals(cmp_compiler.getSelectedItem())
                            || "C++".equals(cmp_compiler.getSelectedItem())
                            || "Free Pascal".equals(cmp_compiler.getSelectedItem())
                            || "GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
                        File file_db_win = new File(code_dir
                                                    + txt_file.getText() + ".exe");
                        if (file_db_win.exists() && txt_file.getDocument().getLength() > 0) {
                            arguments = (String) JOptionPane.showInputDialog(
                                            win, "Value(s):", "Arguments",
                                            JOptionPane.INFORMATION_MESSAGE);
                            if (arguments == null) {

                                Runtime.getRuntime().exec(
                                    "cmd.exe /c start " + gdb + " "
                                    + code_dir + txt_file.getText()
                                    + ".exe");
                            } else {
                                Runtime.getRuntime().exec(
                                    "cmd.exe /c start " + gdb + " "
                                    + code_dir + txt_file.getText()
                                    + ".exe" + arguments);

                            }
                        } else {
                            JOptionPane.showMessageDialog(win_error_log,
                                                          "Executable not found", "ALERT",
                                                          JOptionPane.WARNING_MESSAGE);

                        }
                    } else if ("Java".equals(cmp_compiler.getSelectedItem())) {
                        class_name = class_selector();
                        File class_exists = new File(code_dir + class_name
                                                     + ".class");
                        if (class_exists.exists()
                                && class_name.length() > 0) {
                            arguments = (String) JOptionPane
                                        .showInputDialog(win, "Value(s):",
                                                         "Arguments",
                                                         JOptionPane.INFORMATION_MESSAGE);
                            Runtime.getRuntime().exec(
                                "cmd.exe /c start " + jdb
                                + " -classpath " + code_dir
                                + " " + class_name + " "
                                + arguments);
                        } else {
                            JOptionPane.showMessageDialog(win,
                                                          "Class Not Found", "ERROR",
                                                          JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(win,
                                                      "No debugger found!");
                    }

                } catch (Exception exe) {
                    exception_inp(exe);
                }
            }

            else {
                try {
                    if ("C".equals(cmp_compiler.getSelectedItem())
                            || "C++".equals(cmp_compiler.getSelectedItem())
                            || "Free Pascal".equals(cmp_compiler.getSelectedItem())
                            || "GNU Pascal".equals(cmp_compiler.getSelectedItem())) {
                        File file_db = new File(code_dir + txt_file.getText());
                        if (file_db.exists() && txt_file.getDocument().getLength() > 0) {

                            arguments = (String) JOptionPane.showInputDialog(
                                            win, "Value(s):", "Arguments",
                                            JOptionPane.INFORMATION_MESSAGE);
                            if (arguments == null) {
                                Runtime
                                .getRuntime()
                                .exec(
                                    "xterm -font -*-fixed-medium-r-*-*-"
                                    + font_size_x
                                    + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                                    + gdb + " " + code_dir
                                    + txt_file.getText());
                            }

                            else {
                                Runtime
                                .getRuntime()
                                .exec(
                                    "xterm -font -*-fixed-medium-r-*-*-"
                                    + font_size_x
                                    + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                                    + gdb + " " + code_dir
                                    + txt_file.getText()
                                    + " " + arguments);
                            }
                        } else {

                            JOptionPane.showMessageDialog(win_error_log,
                                                          "Executable not found", "ALERT",
                                                          JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    else if ("Java".equals(cmp_compiler.getSelectedItem())) {
                        class_name = class_selector();
                        File class_exists = new File(code_dir + class_name
                                                     + ".class");
                        if (class_exists.exists()
                                && class_name.length() > 0) {

                            arguments = (String) JOptionPane
                                        .showInputDialog(win, "Value(s):",
                                                         "Arguments",
                                                         JOptionPane.INFORMATION_MESSAGE);
                            Runtime
                            .getRuntime()
                            .exec(
                                "xterm -font -*-fixed-medium-r-*-*-"
                                + font_size_x
                                + "-*-*-*-*-*-iso8859-* -geometry 80x25 -hold -e "
                                + jdb + " -classpath "
                                + code_dir + " "
                                + class_name + " "
                                + arguments);
                        } else {
                            JOptionPane.showMessageDialog(win,"Class Not Found", "ERROR",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(win,"No debugger found!");
                    }
                } catch (Exception exe) {
                    exception_inp(exe);
                }
            }
        }
    }
}
