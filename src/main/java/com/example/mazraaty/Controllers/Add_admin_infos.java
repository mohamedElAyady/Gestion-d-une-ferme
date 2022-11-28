package com.example.mazraaty.Controllers;

import com.example.mazraaty.Models.Production;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

import static com.example.mazraaty.Main.c;

public class Add_admin_infos {
    public PreparedStatement pst;

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
    private TextField phone;

    @FXML
    private ComboBox<String> sexe;

    @FXML
    private Circle photo;

    String img;
    Path from,to;
    @FXML
    void submit(ActionEvent event) {
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

        if (adresse.isEmpty() || nom.isEmpty() || prenom.isEmpty() || password.isEmpty()|| email.isEmpty() || img == null || username.isEmpty() || phone.isEmpty() || sexe.isEmpty() || date == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin infos");
            alert.setHeaderText("Verifier votre information");
            alert.showAndWait();
        }else {
            try {
                //changement la
                pst = c.prepareStatement("insert into admin(adresse,nom,prenom,password,email,username,phone,sexe,date,photo)values(?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, adresse);
                pst.setString(2, nom);
                pst.setString(3, prenom);
                pst.setString(4, password);
                pst.setString(5, email);
                pst.setString(6, username);
                pst.setString(7, phone);
                pst.setString(8, sexe);
                pst.setString(9, date.toString());
                pst.setString(10, img);
                pst.executeUpdate();
                Files.copy(from,to);
                //open the confirmation window
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                //changer cet valeurs
                alert.setTitle("Admin infos");
                alert.setHeaderText("Bien ajouter !");
                alert.showAndWait();
                back(event);

            } catch (SQLException | IOException ee) {
                ee.getMessage();
            }
        }



    }

    ObservableList<String> options = FXCollections.observableArrayList("homme", "femme");
    FileChooser fileChooser = new FileChooser();
    public void initialize() throws SQLException {
        Image im = new Image("C:\\Users\\HP\\Desktop\\Test\\mazraaty\\src\\main\\resources\\images\\admin.png",false);
        photo.setFill(new ImagePattern(im));
        sexe.getItems().addAll(options);
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
        to = (Path) Paths.get("C:\\Users\\HP\\Desktop\\Test\\mazraaty\\src\\main\\resources\\admin_photos\\"+img);
        //Files.copy(from,to);
        Image im = new Image(from.toString(),false);
        photo.setFill(new ImagePattern(im));


    }

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


}
