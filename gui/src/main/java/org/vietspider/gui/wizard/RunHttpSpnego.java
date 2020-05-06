package org.vietspider.gui.wizard;

/** 
 * Author : Nhu Dinh Thuan
 *          nhudinhthuan@gmail.com
 * Jan 3, 2012  
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

public class RunHttpSpnego {

    static final String kuser = "username"; // your account name
    static final String kpass = "password"; // your password for the account

    static class MyAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            // I haven't checked getRequestingScheme() here, since for NTLM
            // and Negotiate, the usrname and password are all the same.
            System.err.println("Feeding username and password for " + getRequestingScheme());
            return (new PasswordAuthentication(kuser, kpass.toCharArray()));
        }
    }

    public static void main(String[] args) throws Exception {
        Authenticator.setDefault(new MyAuthenticator());
        URL url = new URL("http://nik.vn");
        InputStream ins = url.openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        String str;
        while((str = reader.readLine()) != null)
            System.out.println(str);
    }
}
