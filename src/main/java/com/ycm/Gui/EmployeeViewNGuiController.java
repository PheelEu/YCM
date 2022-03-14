package com.ycm.Gui;

import com.ycm.Classes.Notification;
import com.ycm.Classes.Payment;
import com.ycm.Classes.Race;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.EmployeeGui.*;
import static com.ycm.Gui.EmployeeGui.a;

public class EmployeeViewNGuiController implements Initializable {

    @FXML
    private TableColumn<Notification, Double> amountCol;

    @FXML
    private TableColumn<Notification, Integer> boatIDCol;

    @FXML
    private TableColumn<Notification, LocalDate> dateCol;

    @FXML
    private TableColumn<Notification, String> typeCol;

    @FXML
    private TableColumn<Notification, Integer> paymentIDCol;

    @FXML
    private TableColumn<Notification, String> usernameCol;

    @FXML
    private TableView<Notification> notificationTable;
    @FXML
    private static ObservableList<Notification> notificationsObservableList = FXCollections.observableArrayList();

    /**
     * This method it's called by a GUI event, when the 'send notification' button gets pressed
     * Enables the Employee who clicked it send a notification to a member to remind him of an oncoming payment
     *
     * @param event it's the triggered event
     **/
    @FXML
    void sendNotificationBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Notification n = notificationTable.getSelectionModel().getSelectedItem();
        if(n==null){
            a.setContentText("No notification has been selected");
            a.showAndWait();
        }
        else {
            Object notificationSent = new Client().run(new Request(new Message("notificationSent", String.valueOf(n.getPaymentID()))));
            if((boolean) notificationSent){
                a.setContentText("Notification sent");
            }
            else {
                a.setContentText("Notification not sent! \nA problem occurred");
            }
            a.showAndWait();
        }
        Object viewNotifications = new Client().run(new Request(new Message("viewNotifications")));
        if (!(viewNotifications == null)) {
            notifications = (ArrayList<Notification>) viewNotifications;
            if (!(notifications.isEmpty())) {
                notificationsObservableList.clear();
            }
            for (int i = 0; i < notifications.size(); i++) {
                if(notifications.get(i).isSent() == false) {
                    if (getToday().plusDays(11).isAfter(notifications.get(i).getExpiringDate())) {
                        notificationsObservableList.add(new Notification(notifications.get(i).getPaymentID(), notifications.get(i).getUsername(),
                                notifications.get(i).getExpiringDate(), notifications.get(i).getTypeofPayment(), notifications.get(i).getAmount(),
                                notifications.get(i).getBoatID(), notifications.get(i).isSent()));
                    }
                }
            }
        }
    }

    /**
     * This is the initialize method from the @class javafx.fxml.Initialize
     * @param location is the URL location
     * @param resources are the ResourceBundle resources
     * Here is used to set the notification table and the values inside each column for each notification
     **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources){
        Object viewNotifications = new Client().run(new Request(new Message("viewNotifications")));
        if (!(viewNotifications == null)) {
            notifications = (ArrayList<Notification>) viewNotifications;
            if (!(notifications.isEmpty())) {
                notificationsObservableList.clear();
            }
            for (int i = 0; i < notifications.size(); i++) {
                if(notifications.get(i).isSent() == false) {
                    if (getToday().plusDays(11).isAfter(notifications.get(i).getExpiringDate())) {
                        notificationsObservableList.add(new Notification(notifications.get(i).getPaymentID(), notifications.get(i).getUsername(),
                                notifications.get(i).getExpiringDate(), notifications.get(i).getTypeofPayment(), notifications.get(i).getAmount(),
                                notifications.get(i).getBoatID(), notifications.get(i).isSent()));
                    }
                }
            }
        }
        paymentIDCol.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("expiringDate"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("typeofPayment"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        boatIDCol.setCellValueFactory(new PropertyValueFactory<>("boatID"));
        notificationTable.setItems(notificationsObservableList);
    }
}
