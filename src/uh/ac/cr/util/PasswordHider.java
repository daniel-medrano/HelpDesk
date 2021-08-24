package uh.ac.cr.util;

import java.io.*;

class HiderThread implements Runnable {
    private boolean stop;

    public HiderThread() {}

    public void run () {
        stop = true;
        while (stop) {
            System.out.print("\010*");
            try {
                Thread.currentThread().sleep(1);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void stopHiding() {
        this.stop = false;
    }
}

public class PasswordHider {

    public static String readPassword () {
        HiderThread hiderThread = new HiderThread();
        Thread hider = new Thread(hiderThread);
        hider.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // stop hiding
        hiderThread.stopHiding();
        // return the password entered by the user
        return password;
    }
}
