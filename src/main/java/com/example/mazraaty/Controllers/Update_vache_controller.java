package com.example.mazraaty.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.mazraaty.Main.c;

public class Update_vache_controller {
    public PreparedStatement pst;

    //Infos input
    @FXML
    public TextField ID;
    @FXML
    private TextField type;
    @FXML
    private DatePicker date;
    @FXML
    private TextField status;
    @FXML
    private Label display;

    //close btn
    @FXML
    void close_btn(ActionEvent event) throws IOException {
        Stage stage1 ;
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.close();
    }

    @FXML
    void update(ActionEvent event) throws IOException, SQLException {
        //get the data from Production_Controller using getUserData() method
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        TEST2_vache t = (TEST2_vache) stage.getUserData();
        int id = t.id;

            //int id = Integer.parseInt(stage.getUserData().toString());
            // System.out.println(id);


            String ID, type, status;
            LocalDate date;
            ID = this.ID.getText();
            type = this.type.getText();
            status = this.status.getText();
            date = this.date.getValue();

        if (ID.isEmpty() || type.isEmpty() || status.isEmpty()  || date == null ){
            display.setText("vérifier vos informations !");
        }else {
            pst = c.prepareStatement("update vaches set ID_vache = ?,statut  = ? ,date_naiss= ? ,type = ? where ID_vache = ? ");
            pst.setString(1, ID);
            pst.setString(2, status);
            pst.setString(3, date.toString());
            pst.setString(4, type);

            pst.setInt(5, id);
            pst.executeUpdate();
            //get the data from Production_Controller using getUserData() method
            new Vache_controller().refrech_table(t.vaches);

            //open the confirmation window
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vache infos");
            alert.setHeaderText("Vache informations");
            alert.setContentText("Bien Modifier !");
            close_btn(event);
            alert.showAndWait();

        }

    }


    public void initialize() {


    }

}
