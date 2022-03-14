package com.ycm.Sockets;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Object obj;

    /**
     * Class constructor of the response.
     * @param obj is the object to be passed as a response to a request.
     **/
    public Response(final Object obj){this.obj = obj;}

    /**
     * Gets the object.
     * @return the object set.
     **/
    public Object getObj(){return obj;}
}
