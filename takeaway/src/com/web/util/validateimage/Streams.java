package com.web.util.validateimage;

import java.io.*;

public class Streams
{
    /**
     * �ر�������
     * @param in ������
     */
    public static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ioex) {
                // ignore
            }
        }
    }
 
    /**
     * �ر������
     * @param out �����
     */
    public static void close(OutputStream out) {
        if (out != null) {
            try {
                out.flush();
            } catch (IOException ioex) {
                // ignore
            }
            try {
                out.close();
            } catch (IOException ioex) {
                // ignore
            }
        }
    }
}