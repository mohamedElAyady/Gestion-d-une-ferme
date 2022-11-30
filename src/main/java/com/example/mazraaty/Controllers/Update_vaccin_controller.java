package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import com.example.mazraaty.Models.Vaccine;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.mazraaty.Main.c;

public class Update_vaccin_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    private TextField txt_field1;
    @FXML
    private TextField txt_field2;
    @FXML
    private DatePicker date_picker;
    @FXML
    private TextField txt_field3;
    @FXML
    private DatePicker date_picker1;
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
        Pass_vaccine t = (Pass_vaccine) stage.getUserData();
        int id = t.id;
        //changement la
        String ID, vaccin, remarque;
        LocalDate date1,date2;

        ID = this.txt_field1.getText();
        vaccin = this.txt_field2.getText();
        remarque = this.txt_field3.getText();
        date1 = this.date_picker.getValue();
        date2 = this.date_picker1.getValue();

                if (ID.isEmpty() || vaccin.isEmpty() || date2==null || date1 == null ) {
                    display.setText("vérifier vos informations !");
                } else {

                    //changement la
                    pst = c.prepareStatement("update vaccine set ID_vache = ?,date_next_vacc = ? ,vaccine = ? ,remarque = ?,date_enrg = ? where ID = ? ");
                    pst.setString(1, ID);
                    pst.setString(2, date2.toString());
                    pst.setString(3, vaccin);
                    pst.setString(4, remarque);
                    pst.setString(5, date1.toString());
                    pst.setInt(6, id);
                    pst.executeUpdate();

                    //get the data from Production_Controller using getUserData() method
                    new Vaccine_controller().refrech_table(t.vaccines);

                    //open the confirmation window
                    //changement la
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vaccination infos");
                    alert.setHeaderText("Bien Modifier !");
                    close_btn(event);
                    alert.showAndWait();
                }

    }

    @FXML
    public void get(int id) throws SQLException {
        //changement la
        pst = c.prepareStatement("select * from vaccine WHERE ID = ? ");
        pst.setString(1, String.valueOf(id));

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Vaccine p = new Vaccine();
            p.setID_vache(rs.getString("ID_vache"));
            p.setDate_enrg(rs.getString("date_enrg"));
            p.setVaccine(rs.getString("vaccine"));
            p.setRemarque(rs.getString("remarque"));
            p.setdate_next_vacc(rs.getString("date_next_vacc"));
            txt_field1.setText(p.getID_vache());
            txt_field3.setText(p.getRemarque());
            txt_field2.setText(p.getVaccine());


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(p.getDate_enrg(), formatter);
            date_picker.setValue(localDate);
            localDate = LocalDate.parse(p.getdate_next_vacc(), formatter);
            date_picker1.setValue(localDate);
        }
    }





}
