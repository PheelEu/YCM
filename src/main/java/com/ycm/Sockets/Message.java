package com.ycm.Sockets;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private  String functionName;
    private String args1;
    private String args2;
    private String args3;
    private String args4;
    private String args5;
    private String args6;

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     */
    public Message(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     * @param args1 the attribute needed by function.
     */
    public Message(String functionName, String args1) {
        this.functionName = functionName;
        this.args1 = args1;
    }

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     * @param args1 the first attribute needed by function.
     * @param args2 the second attribute needed by function.
     */
    public Message(String functionName, String args1, String args2) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
    }

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     * @param args1 the first attribute needed by function.
     * @param args2 the second attribute needed by function.
     * @param args3 the third attribute needed by function.
     */
    public Message(String functionName, String args1, String args2, String args3) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
    }

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     * @param args1 the first attribute needed by function.
     * @param args2 the second attribute needed by function.
     * @param args3 the third attribute needed by function.
     * @param args4 the fourth attribute needed by function.
     */
    public Message(String functionName, String args1, String args2, String args3, String args4) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
        this.args4 = args4;
    }

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     * @param args1 the first attribute needed by function.
     * @param args2 the second attribute needed by function.
     * @param args3 the third attribute needed by function.
     * @param args4 the fourth attribute needed by function.
     * @param args5 the fifth attribute needed by function.
     */
    public Message(String functionName, String args1, String args2, String args3, String args4, String args5) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
        this.args4 = args4;
        this.args5 = args5;
    }

    /**
     * Class constructor.
     *
     * @param functionName name of the SQL function that need to be passed.
     * @param args1 the first attribute needed by function.
     * @param args2 the second attribute needed by function.
     * @param args3 the third attribute needed by function.
     * @param args4 the fourth attribute needed by function.
     * @param args5 the fifth attribute needed by function.
     * @param args6 the sixth attribute needed by function.
     */
    public Message(String functionName, String args1, String args2, String args3, String args4, String args5, String args6) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
        this.args4 = args4;
        this.args5 = args5;
        this.args6 = args6;
    }

    /**
     * Gets the name of function.
     *
     * @return the name.
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Gets the first attribute.
     *
     * @return the attribute.
     */
    public String getArgs1() {
        return args1;
    }

    /**
     * Gets the second attribute.
     *
     * @return the attribute.
     */
    public String getArgs2() {
        return args2;
    }

    /**
     * Gets the third attribute.
     *
     * @return the attribute.
     */
    public String getArgs3() {
        return args3;
    }

    /**
     * Gets the fourth attribute.
     *
     * @return the attribute.
     */
    public String getArgs4() {
        return args4;
    }

    /**
     * Gets the fifth attribute.
     *
     * @return the attribute.
     */
    public String getArgs5() {
        return args5;
    }

    /**
     * Gets the sixth attribute.
     *
     * @return the attribute.
     */
    public String getArgs6() {
        return args6;
    }
}
