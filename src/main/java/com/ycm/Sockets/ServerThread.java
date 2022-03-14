package com.ycm.Sockets;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Notification;
import com.ycm.Sql.*;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;


public class ServerThread implements Runnable {

    private static final int MAX = 100;
    private static final long SLEEPTIME = 200;

    private Server server;
    private Socket socket;

    /**
     * Class constructor of the server thread.
     * @param s is the server to be passed.
     *
     **/
    public ServerThread(final Server s, final Socket c) {
        this.server = s;
        this.socket = c;
    }

    /**
     * This runs the server thread code
     **/
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
                case "addBoat":
                    obj = MemberSql.AddBoat(Integer.parseInt(m.getArgs1()),m.getArgs2(), Double.parseDouble(m.getArgs3()),
                            Double.parseDouble(m.getArgs4()), m.getArgs5());
                    break;
                case "lastBoatID":
                    obj = MemberSql.lastBoatID();
                    break;
                case "removeBoat":
                    obj = MemberSql.removeBoat(Integer.parseInt(m.getArgs1()));
                    break;
                case "removeAllBoat":
                    obj = EmployeeSql.removeAllBoat(m.getArgs1());
                    break;
                case "expiryDate":
                    obj =  MemberSql.expiryDate(m.getArgs1(), m.getArgs2());
                    break;
                case "upcomingRaces":
                    obj = RaceSql.upcomingRaces();
                    break;
                case "memberBoats":
                    obj =  MemberSql.memberBoats(m.getArgs1());
                    break;
                case "boats":
                    obj =  EmployeeSql.boats();
                    break;
                case "addCompetitor":
                    obj = RaceSql.addCompetitor(m.getArgs1(), Integer.parseInt(m.getArgs2()), m.getArgs3());
                    break;
                case "checkCompetitors":
                    obj = RaceSql.checkCompetitors(Integer.parseInt(m.getArgs1()), m.getArgs2());
                    break;
                case "removeCompetitor":
                    obj = RaceSql.removeCompetitor(m.getArgs1());
                    break;
                case "addRace":
                    obj = RaceSql.addRace(m.getArgs1(), Double.parseDouble(m.getArgs2()), LocalDate.parse(m.getArgs3()));
                    break;
                case "removeRace":
                    obj = RaceSql.removeRace(m.getArgs1());
                    break;
                case "addNotification":
                    obj =  NotificationSql.addNotification(Integer.parseInt(m.getArgs1()),m.getArgs2(),
                            LocalDate.parse(m.getArgs3()), m.getArgs4(), Double.parseDouble(m.getArgs5()), Integer.parseInt(m.getArgs6()));
                    break;
                case "viewNotifications":
                    obj = NotificationSql.viewNotifications();
                    break;
                case "viewMemberNotifications":
                    obj = NotificationSql.viewMemberNotifications(m.getArgs1());
                    break;
                case "notificationSent":
                    obj = NotificationSql.notificationSent(Integer.parseInt(m.getArgs1()));
                    break;
                case "removeNotification":
                    obj =  NotificationSql.removeNotification(Integer.parseInt(m.getArgs1()));
                    break;
                case "removeAllNotification":
                    obj = NotificationSql.removeAllNotification(m.getArgs1());
                    break;

                case "removeBoatNotification":
                    obj = NotificationSql.removeBoatNotification(Integer.parseInt(m.getArgs1()));
                    break;
                case "removeAllBoatNotification":
                    obj = NotificationSql.removeAllBoatNotification(m.getArgs1());
                    break;
                case "addPayment":
                    obj = PaymentSql.addPayment(Integer.parseInt(m.getArgs1()), m.getArgs2(), LocalDate.parse(m.getArgs3()),
                            m.getArgs4(), m.getArgs5(), Double.parseDouble(m.getArgs6()), Boolean.parseBoolean(m.getArgs7()),
                            Integer.parseInt(m.getArgs8()));
                    break;
                case "viewPayments":
                    obj = PaymentSql.viewPayments();
                    break;
                case "lastPaymentID":
                    obj = PaymentSql.lastPaymentID();
                    break;
                case "deletePayment":
                    obj =  PaymentSql.deletePayment(m.getArgs1(), Double.parseDouble(m.getArgs2()));
                    break;
                case "login":
                    obj = QuerySql.login(m.getArgs1(), m.getArgs2());
                    break;
                case "logout":
                    obj = QuerySql.logout(m.getArgs1(), m.getArgs2());
                    break;
                case "deleteAccount":
                    obj = QuerySql.deleteAccount(m.getArgs1(), m.getArgs2());
                    break;
                case "checkUsername":
                    obj = MemberSql.checkUsername(m.getArgs1());
                    break;
                case "register":
                    obj = MemberSql.register(m.getArgs1(), m.getArgs2(), m.getArgs3(), m.getArgs4(), m.getArgs5(), m.getArgs6());
                    break;
                case "members":
                    obj = EmployeeSql.members();
                    break;
                case "removeMember":
                    obj = EmployeeSql.removeMember(m.getArgs1());
                    break;
                default:
                    obj = (Object) "Query not available";
            }
        }
        return obj;
    }
}
