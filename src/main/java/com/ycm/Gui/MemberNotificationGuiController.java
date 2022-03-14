package com.ycm.Gui;

import com.ycm.Classes.Notification;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.ClubGui.getPopupStage;
import static com.ycm.Gui.MemberGui.*;
import static com.ycm.Gui.MemberGui.setPaid;
import static com.ycm.Gui.PaymentGui.setCost;
import static com.ycm.Gui.PaymentGui.setPaymentDate;

public class MemberNotificationGuiController implements Initializable{

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
    static ObservableList<Notification> notificationsObservableList = FXCollections.observableArrayList();

    /**
     * This method is called by a GUI event, when the 'payment' button gets pressed
     * Enables the member who clicked it to make a payment, if a notification (corresponding to a specific type of payment)
     * is selected.
     * If input is accepted the payment page gets opened.
     *
     * @param event it's the triggered event
     **/
    @FXML
    void paymentBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Notification n = notificationTable.getSelectionModel().getSelectedItem();
        if(n==null){
            a.setContentText("No notification has been selected");
            a.showAndWait();
        }
        else {
            setNotification(n);
            if(Objects.equals(n.getTypeofPayment(), "Boat storage payment")){
                try{
                    setBoatRegistered(true);
                    setType(1);
                    setPaymentDate(n.getExpiringDate());
                    setCost(n.getAmount());
                    setPaid(true);
                    setPopupScene(PaymentGui.paymentScene());
                    setPopupTitle("Boat storage payment");
                    getPopupStage().show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(Objects.equals(n.getTypeofPayment(), "Annual subscription payment")){
                try{
                    setType(2);
                    setPaid(true);
                    setRegistered(true);
                    setPaymentDate(n.getExpiringDate());
                    setPopupScene(PaymentGui.paymentScene());
                    setPopupTitle("Annual subscription payment");
                    getPopupStage().show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Object viewMemberNotifications = new Client().run(new Request(new Message("viewMemberNotifications", getMember().getUsername())));
        if (!(viewMemberNotifications == null)) {
            mNotifications = (ArrayList<Notification>) viewMemberNotifications;
            if (!(mNotifications.isEmpty())) {
                notificationsObservableList.clear();
            }
            for (int i = 0; i < mNotifications.size(); i++) {
                notificationsObservableList.add(new Notification(mNotifications.get(i).getPaymentID(), mNotifications.get(i).getUsername(),
                        mNotifications.get(i).getExpiringDate(), mNotifications.get(i).getTypeofPayment(), mNotifications.get(i).getAmount(),
                        mNotifications.get(i).getBoatID(), mNotifications.get(i).isSent()));
            }
        }
        notificationTable.getItems().clear();
    }


    /**
     * This is the initialize method from the @class javafx.fxml.Initialize
     * @param location is the URL location
     * @param resources are the ResourceBundle resources
     * Here is used to set the notifications table and all the values and information inside each column.
     **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources){
        notificationTable.getItems().clear();
        mNotifications.clear();
        Object viewMemberNotifications = new Client().run(new Request(new Message("viewMemberNotifications", getMember().getUsername())));
        if (!(viewMemberNotifications == null)) {
            mNotifications = (ArrayList<Notification>) viewMemberNotifications;
            if (!(mNotifications.isEmpty())) {
                notificationsObservableList.clear();
            }
            for (int i = 0; i < mNotifications.size(); i++) {
                notificationsObservableList.add(new Notification(mNotifications.get(i).getPaymentID(), mNotifications.get(i).getUsername(),
                        mNotifications.get(i).getExpiringDate(), mNotifications.get(i).getTypeofPayment(), mNotifications.get(i).getAmount(),
                        mNotifications.get(i).getBoatID(), mNotifications.get(i).isSent()));
            }
        }
        paymentIDCol.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("paymentID"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Notification, String>("username"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Notification, LocalDate>("expiringDate"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Notification, String>("typeofPayment"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Notification, Double>("amount"));
        boatIDCol.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("boatID"));

        notificationTable.setItems(notificationsObservableList);
    }
}
