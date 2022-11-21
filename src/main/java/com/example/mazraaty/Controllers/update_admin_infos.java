package com.example.mazraaty.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.example.mazraaty.Main.c;

public class update_admin_infos {


    public PreparedStatement pst;

    FileChooser fileChooser = new FileChooser();

    String img;
    Path from,to;

    @FXML
    private TextField adresse;

    @FXML
    private DatePicker date;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField password;

    @FXML
    private TextField prenom;

    @FXML
    private TextField username;

    @FXML
    private ComboBox<String> sexe;

    @FXML
    private TextField phone;

    @FXML
    private Circle photo;

    @FXML
    void effacer(ActionEvent event) {
        nom.clear();
        prenom.clear();
        username.clear();
        password.clear();
        email.clear();
        sexe.setValue("sexe");
        adresse.clear();
        phone.clear();
        date.setValue(null);
        Image im = new Image("C:\\java\\Workspace\\PROJET_ICI\\src\\main\\resources\\images\\admin.png",false);
        photo.setFill(new ImagePattern(im));
    }

    @FXML
    void submit(ActionEvent event) throws SQLException, IOException {
        String adresse,nom,prenom,password,email,username,phone,sexe;
        LocalDate date;

        adresse = this.adresse.getText();
        nom = this.nom.getText();
        prenom = this.prenom.getText();
        password = this.password.getText();
        email = this.email.getText();
        username = this.username.getText();
        phone = this.phone.getText();
        sexe = this.sexe.getValue();
        date = this.date.getValue();

        //changement la
        pst = c.prepareStatement("UPDATE `admin` SET`prenom`=?,`nom`=?,`password`=?,`email`=?,sexe= ?,`date`=?" +
                ",`adresse`=?,`username`=?,`phone`=?,`photo`=? WHERE ID = (select MAX(ID) from admin)");
        pst.setString(1, prenom);
        pst.setString(2, nom);
        pst.setString(3, password);
        pst.setString(4,email);
        pst.setString(5, sexe);
        pst.setString(6, date.toString());
        pst.setString(7, adresse);
        pst.setString(8, username);
        pst.setString(9, phone);
        pst.setString(10, img);
        pst.executeUpdate();
        //copy the image from-->to
        Files.copy(from,to);
        //open the confirmation window
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //changer cet valeurs
        alert.setTitle("Admin infos");
        alert.setHeaderText("Bien modifier !");
        alert.showAndWait();
        new Stage_controller().settings(event);
    }

    ObservableList<String> options = FXCollections.observableArrayList("homme", "femme");

    public void initialize() throws SQLException {

        new Stage_controller().profil_img(photo);
        sexe.getItems().addAll(options);
        pst = c.prepareStatement("SELECT * FROM admin ORDER BY ID DESC LIMIT 1\n");
        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            nom.setText(rs.getString(3));
            prenom.setText(rs.getString(2));
            username.setText(rs.getString(9));
            password.setText(rs.getString(4));
            email.setText(rs.getString(5));
            sexe.setPromptText(rs.getString(6));
            sexe.setValue(rs.getString(6));
            adresse.setText(rs.getString(8));
            phone.setText(rs.getString(10));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(rs.getString(7), formatter);
            date.setValue(localDate);
            img = rs.getString(11);
        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        new Stage_controller().settings(event);
    }

    @FXML
    void add_photo(ActionEvent event) throws IOException, SQLException {
        Stage stage1 ;
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg","*.png", "*.jpeg", "*.svg")
        );
        File file = fileChooser.showOpenDialog(null);
        from = (Path) Paths.get(file.toURI());
        img = hashCode()+file.getName();
        to = (Path) Paths.get("C:\\java\\Workspace\\PROJET_ICI\\src\\main\\resources\\admin_photos\\"+img);
        Image im = new Image(from.toString(),false);
        photo.setFill(new ImagePattern(im));

    }
}
