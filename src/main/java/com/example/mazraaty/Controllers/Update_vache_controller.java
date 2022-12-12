package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Vache;
import com.example.mazraaty.Models.Vente_vache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.mazraaty.Main.c;

public class Update_vache_controller {
    public PreparedStatement pst;
    //Infos input
    @FXML
    public TextField txt_field1;
    @FXML
    private TextField txt_field2;
    @FXML
    private DatePicker date_Picker;
    @FXML

    private Label display;
    @FXML
    private TextField recherche;
    @FXML
    private ComboBox<String> choisebox;
    String[] options={"Vache latière", "Petit vache","Taurea", "Mère vache" };
    @FXML
    private ComboBox<String> choisebox2;
    String[] options2={"non disponible", " disponible"};

    //close btn
    @FXML
    void close_btn(ActionEvent event) throws IOException {
        Stage stage1 ;
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.close();

    }

    public void get(int id) throws SQLException {

        //changement la
        pst = c.prepareStatement("select * from vaches WHERE ID_vache = ? ");
        pst.setString(1, String.valueOf(id));

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            //changement la
            Vache p = new Vache();
            p.setId(rs.getString("ID_vache"));
            p.setSexe(rs.getString("sexe"));
            p.setDate(rs.getString("date_naiss"));
            p.setType(rs.getString("type"));
            p.setStatus(rs.getString("statut"));


            p.setKey(rs.getString("ID_vache"));
            txt_field1.setText(p.getId());
            txt_field2.setText(p.getSexe());
            choisebox.setValue(p.getType());
            choisebox2.setValue(p.getStatus());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(p.getDate(), formatter);
            date_Picker.setValue(localDate);
        }
    }

    @FXML
    void update(ActionEvent event) throws IOException, SQLException {


        //get the data from Production_Controller using getUserData() method
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        TEST2_vache t = (TEST2_vache) stage.getUserData();
        int id = t.id;
        String ID_vache, sexe, type, status;
        LocalDate date_naiss;
        ID_vache = this.txt_field1.getText();
        sexe = this.txt_field2.getText();
        status = this.choisebox2.getValue();
        type = this.choisebox.getValue();
        date_naiss = this.date_Picker.getValue();

        if (ID_vache.isEmpty() || sexe.isEmpty() || type.isEmpty() || status.isEmpty() || date_naiss == null) {
            display.setText("vérifier vos informations !");
        } else {

            //changement la
            pst = c.prepareStatement("update vaches set ID_vache = ? ,sexe = ? ,date_naiss= ? ,type = ?,statut=? where ID_vache = ? ");
            pst.setString(1, ID_vache);
            pst.setString(2, sexe);
            pst.setString(3, date_naiss.toString());
            pst.setString(4, type);
            pst.setString(5, status);
            pst.setInt(6, id);
            pst.executeUpdate();

            //get the data from Production_Controller using getUserData() method
            new Vache_controller().refrech_table(t.vaches);

            //open the confirmation window
            //changement

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("vente infos");
                alert.setHeaderText("Bien Modifier !");
                close_btn(event);
                alert.showAndWait();

            }

        }




    public void initialize(){
        choisebox.getItems().addAll(options);
        choisebox2.getItems().addAll(options2);

    }


}
