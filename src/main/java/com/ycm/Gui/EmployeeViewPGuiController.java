package com.ycm.Gui;

import com.ycm.Classes.Payment;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.ycm.Gui.EmployeeGui.payments;


public class EmployeeViewPGuiController implements Initializable {

    @FXML
    private TableColumn<Payment, Double> aAmountCol;

    @FXML
    private TableColumn<Payment, LocalDate> aDateCol;

    @FXML
    private TableColumn<Payment, Integer> aIDCol;

    @FXML
    private TableColumn<Payment, String> aMethodCol;

    @FXML
    private TableColumn<Payment, Boolean> aPaidCol;

    @FXML
    private TableColumn<Payment, String> aUsernameCol;

    @FXML
    private TableColumn<Payment, Double> bAmountCol;

    @FXML
    private TableColumn<Payment, Integer> bBoatIDCol;

    @FXML
    private TableColumn<Payment, LocalDate> bDateCol;

    @FXML
    private TableColumn<Payment, Integer> bIDCol;

    @FXML
    private TableColumn<Payment, String> bMethodCol;

    @FXML
    private TableColumn<Payment, Boolean> bPaidCol;

    @FXML
    private TableColumn<Payment, String> bUsernameCol;


    @FXML
    private TableColumn<Payment, LocalDate> rDateCol;

    @FXML
    private TableColumn<Payment, Integer> rIDCol;

    @FXML
    private TableColumn<Payment, String> rMethodCol;

    @FXML
    private TableColumn<Payment, String> rUsernameCol;

    @FXML
    private TableColumn<Payment, Double> rAmountCol;

    @FXML
    private TableColumn<Payment, Integer> rBoatIDCol;

    @FXML
    private TableColumn<Payment, Boolean> rPaidCol;

    @FXML
    private TableView<Payment> annualSubTable;

    @FXML
    private TableView<Payment> boatStorageTable;

    @FXML
    private TableView<Payment> raceFeeTable;

    @FXML
    static ObservableList<Payment> aPaymentsObservableList = FXCollections.observableArrayList();

    @FXML
    static ObservableList<Payment> bPaymentsObservableList = FXCollections.observableArrayList();

    @FXML
    static ObservableList<Payment> rPaymentsObservableList = FXCollections.observableArrayList();

    /**
     * This is the initialize method from the @class javafx.fxml.Initializable
     * @param location is the URL location
     * @param resources are the ResourceBundle resources
     * Here is used to set the products table, the values inside each column, the brand box brands and to get the products info
     **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources){
        Object viewPayments = new Client().run(new Request(new Message("viewPayments")));
        if (!(viewPayments == null)) {
            payments = (ArrayList<Payment>) viewPayments;
            if (!(payments.isEmpty())) {
                aPaymentsObservableList.clear();
                bPaymentsObservableList.clear();
                rPaymentsObservableList.clear();
            }
            for (int i = 0; i < payments.size(); i++) {
                if(Objects.equals(payments.get(i).getTypeofPayment(), "Annual subscription payment")) {
                    aPaymentsObservableList.add(new Payment(payments.get(i).getID(), payments.get(i).getUsername(),
                        payments.get(i).getPaymentDate(),payments.get(i).getMethod(), payments.get(i).getAmount(), payments.get(i).isPaid()));
                }
                if(Objects.equals(payments.get(i).getTypeofPayment(), "Boat storage payment")) {
                    bPaymentsObservableList.add(new Payment(payments.get(i).getID(), payments.get(i).getUsername(),
                            payments.get(i).getPaymentDate(),payments.get(i).getMethod(), payments.get(i).getAmount(),
                            payments.get(i).isPaid(), payments.get(i).getInfo()));
                }
                if(Objects.equals(payments.get(i).getTypeofPayment(), "Race enrollment fee")) {
                    rPaymentsObservableList.add(new Payment(payments.get(i).getID(), payments.get(i).getUsername(),
                            payments.get(i).getPaymentDate(),payments.get(i).getMethod(), payments.get(i).getAmount(),
                            payments.get(i).isPaid(), payments.get(i).getInfo()));
                }
            }
        }

        aIDCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("ID"));
        aUsernameCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("username"));
        aDateCol.setCellValueFactory(new PropertyValueFactory<Payment, LocalDate>("paymentDate"));
        aMethodCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("method"));
        aAmountCol.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        aPaidCol.setCellValueFactory(new PropertyValueFactory<Payment, Boolean>("paid"));

        bIDCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("ID"));
        bUsernameCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("username"));
        bDateCol.setCellValueFactory(new PropertyValueFactory<Payment, LocalDate>("paymentDate"));
        bMethodCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("method"));
        bAmountCol.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        bPaidCol.setCellValueFactory(new PropertyValueFactory<Payment, Boolean>("paid"));
        bBoatIDCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("info"));

        rIDCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("ID"));
        rUsernameCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("username"));
        rDateCol.setCellValueFactory(new PropertyValueFactory<Payment, LocalDate>("paymentDate"));
        rMethodCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("method"));
        rAmountCol.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        rPaidCol.setCellValueFactory(new PropertyValueFactory<Payment, Boolean>("paid"));
        rBoatIDCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("info"));

        annualSubTable.setItems(aPaymentsObservableList);
        boatStorageTable.setItems(bPaymentsObservableList);
        raceFeeTable.setItems(rPaymentsObservableList);
    }
}
