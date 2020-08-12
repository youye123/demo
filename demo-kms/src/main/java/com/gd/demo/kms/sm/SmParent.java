package com.gd.demo.kms.sm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


abstract public class SmParent {

    public final static String sm2Sk = "5646d029b2a1d56f2a5ce0e86cdd66bc1f3e13005ea696a53bf706781598809c";

    public final static String sm2Pk = "0425908025511d46b90fa56664483cb2e95b9572d4ada851d6d21963a9f59f20ea340bb65454ccc9cfab74d7c748a283a80fd168394ef635ba3e75227d3e521131";

    public static void main(String arg[]) {
        String[] cmds = new String[]{
                "gmssl sm2 -genzid -in sm2.pem -id Alice -noout"
        };
        for (String item : cmds) {
            sendRecvPromptCmd(item);
        }
    }

    public static void sendRecvPromptCmd(String cmd) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            InputStream eis = proc.getErrorStream();
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            InputStreamReader eisr = new InputStreamReader(eis);
            BufferedReader br = new BufferedReader(isr);
            BufferedReader ebr = new BufferedReader(eisr);
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("correct: " + line);
            }
            String eline = "";
            while ((eline = ebr.readLine()) != null) {
                System.out.println("error: " + eline);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
