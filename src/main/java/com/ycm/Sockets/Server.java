package com.ycm.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final int COREPOOL = 5;
    private static final int MAXPOOL = 100;
    private static final long IDLETIME = 5000;

    private static final int SPORT = 4444;

    private ServerSocket socket;
    private ThreadPoolExecutor pool;

    public Server() throws IOException{
        this.socket = new ServerSocket(SPORT);
    }

    private void run(){
        this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        while(true){
            try{
                Socket s = this.socket.accept();

                this.pool.execute(new ServerThread(this, s));
            }catch(Exception e){
                e.printStackTrace();
                break;
            }
        }
        this.pool.shutdown();
    }

    /**
     * Gets the server pool.
     *
     * @return the thread pool.
     *
     **/
    public ThreadPoolExecutor getPool(){return this.pool;}

    /**
     * Closes the server execution.
     *
     **/
    public void close() {
        try {
            this.socket.close();
            System.out.println("Server is closing ...");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method starts the server.
     *
     * @param args the arguments required (empty).
     *
     * @throws IOException if the execution fails.
     *
     **/
    public static void main(final String[] args) throws IOException {
        new Server().run();
    }

}

