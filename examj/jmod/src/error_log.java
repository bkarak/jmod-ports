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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;
import java.awt.Color;

public class error_log {
    public static void send_error(String err) {
        examj_gui.txt_msg.setForeground(Color.red);
        Calendar now = Calendar.getInstance();
        try {
            FileWriter fstream = new FileWriter("bin/error.log", true);
            BufferedWriter output = new BufferedWriter(fstream);
            output.write(now.getTime() + " > \n" + err);
            output.write("\n\n");
            output.close();
            show_error_action.show_dialog();
        } catch (Exception exe1) {
            System.out.println(exe1);
            error_log.send_error(exe1.getMessage());
            examj_gui.txt_msg.setText("Error: See 'error.log' for more information");
        }
    }
}
