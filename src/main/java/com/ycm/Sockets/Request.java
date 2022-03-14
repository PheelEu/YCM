package com.ycm.Sockets;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private Message msg;

    /**
     * Class constructor of the request.
     * @param msg is the message to be passed.
     *
     **/
    public Request(Message msg){this.msg = msg;}

    /**
     * Gets the message.
     * @return the message set.
     **/
    public Message getMsg(){return msg;}

}
