package com.ycm.Sockets;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private Message msg;

    public Request(Message msg){this.msg = msg;}

    public Message getMsg(){return msg;}

}
