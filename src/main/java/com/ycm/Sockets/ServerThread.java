package com.ycm.Sockets;

import com.ycm.Sql.MemberSql;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;


public class ServerThread implements Runnable {

    private static final int MAX = 100;
    private static final long SLEEPTIME = 200;

    private Server server;
    private Socket socket;

    public ServerThread(final Server s, final Socket c) {
        this.server = s;
        this.socket = c;
    }

    @Override
    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;

        try {
            is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String id = String.valueOf(this.hashCode());

        Random r = new Random();

        try {
            Object i = is.readObject();

            if (i instanceof Request) {
                Request rq = (Request) i;

                Thread.sleep(SLEEPTIME);

                if (os == null) {
                    try {
                        os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Response rs = new Response(executeQuery(rq));

                try {
                    os.writeObject(rs);
                    os.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (rs.getObj() != null) {
                    this.socket.close();
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Function that returns an object as a response to posting the request.
     *
     * @param rq the request with message into.
     * @return response's object.
     * @throws SQLException if SQL query fails.
     **/
    public Object executeQuery(Request rq) throws SQLException {

        Object obj = null;

        if(rq.getMsg() instanceof Message) {
            Message m = (Message) rq.getMsg();

            switch(m.getFunctionName()) {
                /*
                case "addEmployee":
                    obj = AdminSQL.addEmployee(m.getArgs1(), m.getArgs2(), m.getArgs3(), m.getArgs4());
                    break;
                case "decrease":
                    obj =  sqlQuery.decrease(m.getArgs1(), m.getArgs2(), m.getArgs3(), m.getArgs4(), m.getArgs5(), Integer.parseInt(m.getArgs6()));
                    break;
                case "deleteNotify":
                    obj = NotifySQL.deleteNotify(Integer.parseInt(m.getArgs1()));
                    break;
                case "deleteOrder":
                    obj = OrderSQL.deleteOrder(Integer.parseInt(m.getArgs1()));
                    break;
                case "deliver":
                    obj = EmployeeSQL.deliver(Integer.parseInt(m.getArgs1()));
                    break;
                case "getNotifyListShopEmployee":
                    obj = NotifySQL.getNotifyListShopEmployee();
                    break;
                case "getNotifyListShopUser":
                    obj = NotifySQL.getNotifyListShopUser();
                    break;
                case "getNotifyListUser":
                    obj = NotifySQL.getNotifyListUser(m.getArgs1());
                    break;
                case "getNumOrdersToDo":
                    obj = OrderSQL.getNumOrdersToDo();
                    break;
                case "getNumNotifiesToDo":
                    obj = NotifySQL.getNumNotifiesToDo();
                    break;
                case "getNumNotifiesUser":
                    obj = NotifySQL.getNumNotifiesUser(m.getArgs1());
                    break;
                case "getNumReplacement":
                    obj = OrderSQL.getNumReplacement();
                    break;
                case "getOrderListEmployee":
                    obj = OrderSQL.getOrderListEmployee();
                    break;
                case "getOrderListUser":
                    obj = OrderSQL.getOrderListUser();
                    break;
                case "login":
                    obj = sqlQuery.login(m.getArgs1(), m.getArgs2());
                    break;
                case "logout":
                    obj = sqlQuery.logout(m.getArgs1(), m.getArgs2());
                    break;
                case "notifyUser":
                    obj = sqlQuery.notifyUser(m.getArgs1(), m.getArgs2(), m.getArgs3(), Integer.parseInt(m.getArgs3()), m.getArgs4());
                    break;
                    */
                case "register":
                    obj = MemberSql.register(m.getArgs1(), m.getArgs2(), m.getArgs3(), m.getArgs4(), m.getArgs5(), m.getArgs6());
                    break;
                    /*
                case "removeEmployee":
                    obj = AdminSQL.removeEmployee(m.getArgs1(), m.getArgs2());
                    break;
                case "replacement":
                    obj = EmployeeSQL.replacement(m.getArgs1(), m.getArgs2(), m.getArgs3(), Integer.parseInt(m.getArgs4()), Integer.parseInt(m.getArgs5()));
                    break;
                case "request":
                    obj = EmployeeSQL.request(m.getArgs1(), m.getArgs2(), m.getArgs3(), m.getArgs4(), Integer.parseInt(m.getArgs5()));
                    break;
                case "searchWines":
                    obj = WineSQL.searchWines(m.getArgs1(), m.getArgs2(), m.getArgs3());
                    break;
                case "searchWine":
                    obj = WineSQL.searchWine(m.getArgs1(), m.getArgs2(), m.getArgs3());
                    break;
                case "sendNotify":
                    obj = UserSQL.sendNotify(m.getArgs1(), m.getArgs2(), m.getArgs3(), m.getArgs4(), Integer.parseInt(m.getArgs5()));
                    break;
                case "showEmployees":
                    obj = AdminSQL.showEmployees();
                    break;
                case "showOrders":
                    obj = AdminSQL.showOrders();
                    break;
                case "showUsers":
                    obj = AdminSQL.showUsers();
                    break;
                case "showWines":
                    obj = AdminSQL.showWines();
                    break;
                case "updateNotify":
                    obj = NotifySQL.updateNotify(m.getArgs1(), m.getArgs2(), m.getArgs3(), Integer.parseInt(m.getArgs4()));
                    break;
                    */
                default:
                    obj = (Object) "No requested Query";
            }
        }
        return obj;
    }
}
