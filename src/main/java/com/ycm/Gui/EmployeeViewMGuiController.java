package com.ycm.Gui;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Member;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.ycm.Gui.EmployeeGui.*;

public class EmployeeViewMGuiController implements Initializable {

    @FXML
    private TableColumn<Member, String> addressCol;

    @FXML
    private TableColumn<Member, String> fcCol;

    @FXML
    private TableColumn<Member, Boolean> loggedCol;

    @FXML
    private TableColumn<Member, String> nameCol;

    @FXML
    private TableColumn<Member, String> surnameCol;

    @FXML
    private TableColumn<Member, String> usernameCol;

    @FXML
    private TableView<Member> memberTable;

    @FXML
    static ObservableList<Member> membersObservableList = FXCollections.observableArrayList();

    @FXML
    void removeMemberBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Member member = memberTable.getSelectionModel().getSelectedItem();
        if(member==null){
            a.setContentText("No member has been selected");
            a.showAndWait();
        }
        else {
            Object removeMember = new Client().run(new Request(new Message( "removeMember", member.getUsername())));
            if((boolean) removeMember){
                Object removeNotification = new Client().run(new Request(new Message("removeNotification", member.getUsername())));
                Object boats = new Client().run(new Request(new Message("memberBoats", member.getUsername())));
                if (!(boats == null)) {
                    Object removeBoat = new Client().run(new Request(new Message("removeAllBoat", member.getUsername())));
                    Object removeAllBoatsNotification = new Client().run(new Request(new Message("removeAllBoatNotification", member.getUsername())));
                    if(!(boolean) removeBoat) {
                        a.setContentText("Boat not removed! \nAn Error Occurred.");
                    }
                    if(!(boolean) removeAllBoatsNotification) {
                        a.setContentText("Boat notifications not removed! \nAn Error Occurred.");
                    }
                }
                if(!(boolean) removeNotification){
                    a.setContentText("Member notifications not removed! \nAn Error Occurred.");
                }
                a.setContentText("Member removed correctly");
            }
            else{
                a.setContentText("Member not removed! \nAn Error Occurred.");
            }
            memberTable.getItems().clear();
            Object members = new Client().run(new Request(new Message("members")));
            if (!(members == null)) {
                membersInfo = (ArrayList<Member>) members;
                if (!(membersObservableList.isEmpty())) {
                    membersObservableList.clear();
                }
                for (int i = 0; i < membersInfo.size(); i++) {
                    membersObservableList.add(new Member(membersInfo.get(i).getUsername(), "", membersInfo.get(i).getName(),
                            membersInfo.get(i).getSurname(),membersInfo.get(i).getAddress(), membersInfo.get(i).getFC(), membersInfo.get(i).isLogged()));
                }
            }
            a.showAndWait();
        }
    }

    /**
     * This is the initialize method from the @class javafx.fxml.Initializable
     * @param location is the URL location
     * @param resources are the ResourceBundle resources
     * Here is used to set the products table, the values inside each column, the brand box brands and to get the products info
     **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources){
        Object members = new Client().run(new Request(new Message("members")));
        if (!(members == null)) {
            membersInfo = (ArrayList<Member>) members;
            if (!(membersObservableList.isEmpty())) {
                membersObservableList.clear();
            }
            for (int i = 0; i < membersInfo.size(); i++) {
                membersObservableList.add(new Member(membersInfo.get(i).getUsername(), "", membersInfo.get(i).getName(),
                        membersInfo.get(i).getSurname(),membersInfo.get(i).getAddress(), membersInfo.get(i).getFC(), membersInfo.get(i).isLogged()));
            }
        }
        usernameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("surname"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        fcCol.setCellValueFactory(new PropertyValueFactory<Member, String>("FC"));
        loggedCol.setCellValueFactory(new PropertyValueFactory<Member, Boolean>("logged"));
        memberTable.setItems(membersObservableList);
    }
}
