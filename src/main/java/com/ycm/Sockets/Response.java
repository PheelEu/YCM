package com.ycm.Sockets;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Object obj;

    public Response(final Object obj){this.obj = obj;}

    public Object getObj(){return obj;}
}
