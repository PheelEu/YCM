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
    private String args7;
    private String args8;

    /**
     * Class constructor of the message.
     * @param functionName is the name of the SQL function that needs to be passed.
     *
     **/
    public Message(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Class constructor with one argument.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the attribute needed by the function.
     *
     **/
    public Message(String functionName, String args1) {
        this.functionName = functionName;
        this.args1 = args1;
    }

    /**
     * Class constructor with two arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     **/
    public Message(String functionName, String args1, String args2) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
    }

    /**
     * Class constructor with three arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     * @param args3 is the third attribute needed by the function.
     */
    public Message(String functionName, String args1, String args2, String args3) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
    }

    /**
     * Class constructor with four arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the first attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     * @param args3 is the third attribute needed by the function.
     * @param args4 is the fourth attribute needed by the function.
     */
    public Message(String functionName, String args1, String args2, String args3, String args4) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
        this.args4 = args4;
    }

    /**
     * Class constructor with four arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the first attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     * @param args3 is the third attribute needed by the function.
     * @param args4 is the fourth attribute needed by the function.
     * @param args5 is the fifth attribute needed by the function.
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
     * Class constructor with four arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the first attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     * @param args3 is the third attribute needed by the function.
     * @param args4 is the fourth attribute needed by the function.
     * @param args5 is the fifth attribute needed by the function.
     * @param args6 is the sixth attribute needed by the function.
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
     * Class constructor with four arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the first attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     * @param args3 is the third attribute needed by the function.
     * @param args4 is the fourth attribute needed by the function.
     * @param args5 is the fifth attribute needed by the function.
     * @param args6 is the sixth attribute needed by the function.
     * @param args7 is the seventh attribute needed by the function.
     */
    public Message(String functionName, String args1, String args2, String args3, String args4, String args5, String args6, String args7) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
        this.args4 = args4;
        this.args5 = args5;
        this.args6 = args6;
        this.args7 = args7;
    }

    /**
     * Class constructor with four arguments.
     *
     * @param functionName is name of the SQL function that needs to be passed.
     * @param args1 is the first attribute needed by the function.
     * @param args2 is the second attribute needed by the function.
     * @param args3 is the third attribute needed by the function.
     * @param args4 is the fourth attribute needed by the function.
     * @param args5 is the fifth attribute needed by the function.
     * @param args6 is the sixth attribute needed by the function.
     * @param args7 is the seventh attribute needed by the function.
     */
    public Message(String functionName, String args1, String args2, String args3, String args4, String args5, String args6, String args7, String args8) {
        this.functionName = functionName;
        this.args1 = args1;
        this.args2 = args2;
        this.args3 = args3;
        this.args4 = args4;
        this.args5 = args5;
        this.args6 = args6;
        this.args7 = args7;
        this.args8 = args8;
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

    /**
     * Gets the seventh attribute.
     *
     * @return the attribute.
     */
    public String getArgs7() {
        return args7;
    }

    /**
     * Gets the eighth attribute.
     *
     * @return the attribute.
     */
    public String getArgs8() {
        return args8;
    }
}
