package com.ycm.Sockets;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final int SPORT = 4444;
    private static final String SHOST = "localhost";

    /**
     * This runs the client code
     **/
    public Object run(Request rq) {
        Socket client = null;
        ObjectOutputStream os = null;
        ObjectInputStream is = null;

        try {
            client = new Socket(SHOST, SPORT);
        } catch(Exception e) {
            return e;
        }

        try {
            os = new ObjectOutputStream(client.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            os.writeObject(rq);
            os.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (is == null) {
            try {
                is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        Object o = null;
        try {
            o = is.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }

        Response rs = null;
        try {
            if (o instanceof Response) {
                rs = (Response) o;
                client.close();
                return rs.getObj();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
