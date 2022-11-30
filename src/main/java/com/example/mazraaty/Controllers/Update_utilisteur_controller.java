package com.example.mazraaty.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.mazraaty.Main.c;

public class Update_utilisteur_controller {
    public PreparedStatement pst;
    private boolean update;
    //Infos input

    @FXML
    private TextField JC;
    @FXML
    private TextField nom;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> choisebox;
    String[] options={"3aml", "patron"};

    @FXML
    private Label display;
    @FXML
    private TextField recherche;
    @FXML
    private AnchorPane ap;

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
        Pass_utilisateur t = (Pass_utilisateur) stage.getUserData();
        int id = t.id;

        //int id = Integer.parseInt(stage.getUserData().toString());
        // System.out.println(id);


        String JC,nom, num,email,type;
        type = choisebox.getValue();
        JC = this.JC.getText();
        nom = this.nom.getText();
        num = this.num.getText();
        email =this.email.getText();

        if (JC.isEmpty() || nom.isEmpty() || num.isEmpty() || email.isEmpty()   ){
            display.setText("vérifier vos informations !");
        }else {
            pst = c.prepareStatement("update utilisateur set Cin=?,Nom=?,Num=?,Email=?,Type=? where id = ?");
            pst.setString(1, JC);
            pst.setString(2, nom);
            pst.setString(3, num);
            pst.setString(4, email);
            pst.setString(5, type);
            pst.setInt(6, id);
            pst.executeUpdate();

            //get the data from Production_Controller using getUserData() method
            new Utilisateur_controller().refrech_table(t.utilisateurs);

            //open the confirmation window
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Utilisateur infos");
            alert.setHeaderText("Utilisateur informations");
            alert.setContentText("Bien Modifier !");
            close_btn(event);
            alert.showAndWait();

        }

    }


    public void initialize() {
        choisebox.getItems().addAll(options);

    }

}