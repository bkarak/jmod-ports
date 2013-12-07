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
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class create_properties {
    public create_properties() {
        try {
            File new_prop = new File("bin/examj.properties");
            FileWriter out = new FileWriter(new_prop);
            out.write("comp_avr_gcc=avr-gcc\n");
            out.write("code_dir=bin/code/\n");
            out.write("comp_g++=g++\n");
            out.write("examiner=examj_user\n");
            out.write("d_port=465\n");
            out.write("fontsize_x=15\n");
            out.write("autosave_min=5\n");
            out.write("jre=java\n");
            out.write("d_email=user@gmail.com\n");
            out.write("avrdudepath=avrdude\n");
            out.write("comp_gpc=gpc\n");
            out.write("d_host=smtp.gmail.com\n");
            out.write("comp_perl=perl\n");
            out.write("comp_shell=bash\n");
            out.write("comp_cs=gmcs\n");
            out.write("host=localhost\n");
            out.write("jdb=jdb\n");
            out.write("astyle=astyle\n");
            out.write("comp_wb=firefox\n");
            out.write("m=m168\n");
            out.write("fontsize_e=15\n");
            out.write("comp_clojure=clojure\n");
            out.write("mmcu=atmega168\n");
            out.write("programmer=stk500v1\n");
            out.write("device=/dev/ttyUSB0\n");
            out.write("comp_ruby=ruby\n");
            out.write("comp_fpc=fpc\n");
            out.write("comp_lua=lua\n");
            out.write("comp_python=python\n");
            out.write("comp_java=javac\n");
            out.write("m_to=user@yahoo.com\n");
            out.write("gdb=gdb\n");
            out.write("baud=19200\n");
            out.write("dbase=examj\n");
            out.write("comp_gcc=gcc");
            out.write("comp_groovy=groovy");
            out.write("comp_scala=scalac");
            out.write("sre=scala");
            out.close();
            JOptionPane.showMessageDialog(examj_gui.win,
                                          "'examj.properties' has been successfully created!\nPlease restart ExamJ",
                                          "Info", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception exe) {
            System.out.println(exe);
            error_log.send_error(exe.getMessage());
            examj_gui.txt_msg.setText("Error: See 'error.log' for more information");
        }
    }
}
