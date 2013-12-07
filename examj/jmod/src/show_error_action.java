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
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class show_error_action {
    public static void show_dialog() {
        try {
            String strline;
            File file_err = new File("bin/error.log");
            JFrame win_error_log = new JFrame("Error Log");
            JTextArea txt_error_log = new JTextArea(15, 40);
            txt_error_log.setEditable(false);
            JScrollPane error_log_scroll = new JScrollPane(txt_error_log);
            Toolkit toolkit_licence = win_error_log.getToolkit();
            Dimension screenSize = toolkit_licence.getScreenSize();
            win_error_log.setBounds((screenSize.width - 450) / 2,
                                    (screenSize.height - 400) / 2, 600, 600);
            win_error_log.setLayout(new BorderLayout());
            win_error_log.add(error_log_scroll, BorderLayout.CENTER);
            txt_error_log.setLineWrap(true);
            txt_error_log.setWrapStyleWord(true);
            if (file_err.exists()) {
                FileInputStream fstream = new FileInputStream("bin/error.log");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(in));
                while ((strline = br.readLine()) != null) {
                    txt_error_log.append(strline + "\n");
                }
                in.close();
                win_error_log.pack();
                win_error_log.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(win_error_log,
                                              "error.log not found", "ALERT",
                                              JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            System.out.println(err);
            error_log.send_error(err.getMessage());
            examj_gui.txt_msg
            .setText("Error: See 'error.log' for more information");

        }
    }
}
