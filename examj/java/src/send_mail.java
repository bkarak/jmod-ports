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
import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class send_mail {
    JFrame win_mail;
    String src_code = examj_gui.txt_code.getText();
    String src_title = examj_gui.txt_file.getText();
    String prlanguage = examj_gui.cmp_compiler.getSelectedItem().toString();
    String mname = code_send.txt_name.getText();
    String msurname = code_send.txt_surname.getText();
    String mid = code_send.txt_id.getText();
    String mgroup = code_send.txt_group.getText();
    public String d_email, d_password, decpass;
    StandardPBEStringEncryptor encryptor;

    public send_mail() {
        win_mail = new JFrame();
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("bin/examj.properties"));

            d_email = props.getProperty("d_email");
            encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(examj_gui.uuid.toString());
            encryptor.setAlgorithm("PBEWithMD5AndDES");
            decpass = encryptor.decrypt(examj_gui.encpass2);
            String d_host = props.getProperty("d_host");
            String d_port = props.getProperty("d_port");
            String m_to = props.getProperty("m_to");
            String m_subject = mname + " " + msurname + " " + mid + " "
                               + mgroup;
            String m_text = "Title: " + src_title + (char) 10
                            + " Programming Language: " + prlanguage + (char) 10
                            + (char) 10 + src_code + (char) 10 + (char) 10
                            + "Client: ExamJ " + examj_gui.version;
            props.put("mail.smtp.user", d_email);
            props.put("mail.smtp.host", d_host);
            props.put("mail.smtp.port", d_port);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", d_port);
            props.put("mail.smtp.socketFactory.class",
                      "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(m_to));
            Transport.send(msg);
            JOptionPane.showMessageDialog(win_mail,
                                          "Email have been successfully sent!");
            code_send.win_upload.setVisible(false);
        } catch (Exception exe1) {
            System.out.println(exe1);
            error_log.send_error(exe1.getMessage());
            examj_gui.txt_msg
            .setText("Error: See 'error.log' for more information");
            JOptionPane.showMessageDialog(win_mail, "Error sending mail",
                                          "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, decpass);
        }
    }
}
