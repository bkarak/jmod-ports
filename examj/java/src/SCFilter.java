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
import javax.swing.*;
import javax.swing.filechooser.*;

public class SCFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.c) || extension.equals(Utils.cpp)|| extension.equals(Utils.cs)
                    || extension.equals(Utils.html)
                    || extension.equals(Utils.java)
                    || extension.equals(Utils.lua)
                    || extension.equals(Utils.pas)
                    || extension.equals(Utils.pl)
                    || extension.equals(Utils.py)
                    || extension.equals(Utils.rb)
                    || extension.equals(Utils.sh)
                    || extension.equals(Utils.clj)
                    || extension.equals(Utils.bat)
                    || extension.equals(Utils.groovy)
                    || extension.equals(Utils.scala)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Source Code Files";
    }
}
