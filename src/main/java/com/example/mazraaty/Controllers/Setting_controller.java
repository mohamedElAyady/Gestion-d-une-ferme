package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.mazraaty.Main.c;

public class Setting_controller {
    public PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
     StackPane stackpane;
    @FXML
    private Label adresse;

    @FXML
    private Label date;
    @FXML
    private Label username;

    @FXML
    private Label email;

    @FXML
    private Label nom;

    @FXML
    private Label password;

    @FXML
    private Circle photo;
    @FXML
    private Circle photo1;

    @FXML
    private Label prenom;

    @FXML
    private Label phone;

    @FXML
    private Label sexe;


    public void tableaudeboard(ActionEvent event) throws IOException {
        new Stage_controller().tableaudeboard(event);
    }

    public void utilisateur(ActionEvent event) throws IOException {
        new Stage_controller().utilisateur(event);
    }

    public void vaccine(ActionEvent event) throws IOException {
        new Stage_controller().vaccine(event);
    }

    public void vente_vache(ActionEvent event) throws IOException {
        new Stage_controller().vente_vache(event);
    }

    public void categorie_vache(ActionEvent event) throws IOException {
        new Stage_controller().categorie_vache(event);
    }

    public void alimentation(ActionEvent event) throws IOException {
        new Stage_controller().alimentation(event);
    }

    public void production(ActionEvent event) throws IOException {
        new Stage_controller().production(event);
    }

    public void vente_lait(ActionEvent event) throws IOException {
       new Stage_controller().vente_lait(event);
    }

    public void vache(ActionEvent event) throws IOException {
        new Stage_controller().vache(event);
    }

    public void settings(ActionEvent event) throws IOException {
        new Stage_controller().settings(event);
    }

    public void search(ActionEvent event) throws IOException {
        new Stage_controller().search(event);
    }

    public void print(ActionEvent event) throws IOException {
        System.out.println("this function is not working yet !!");
    }

    public void close_btn(ActionEvent event) throws IOException {
        new Stage_controller().close_btn(event);
    }

    public void log_out(ActionEvent event) throws IOException {
       new Stage_controller().log_out(event);

    }


    public void modifier() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("@../../../../mazraaty/Setting1.fxml"));
        Node node = (Node)fxmlLoader.load();
        stackpane.getChildren().addAll(node);

    }

    public void ajouter() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("@../../../../mazraaty/Setting2.fxml"));
        Node node = (Node)fxmlLoader.load();
        stackpane.getChildren().addAll(node);

    }

    public void initialize() throws SQLException {
        new Stage_controller().profil_img(photo);
        new Stage_controller().profil_img(photo1);
        pst = c.prepareStatement("SELECT * FROM admin ORDER BY ID DESC LIMIT 1");
        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            nom.setText(rs.getString(3));
            prenom.setText(rs.getString(2));
            username.setText(rs.getString(9));
            password.setText(rs.getString(4));
            email.setText(rs.getString(5));
            sexe.setText(rs.getString(6));
            adresse.setText(rs.getString(8));
            date.setText(rs.getString(7));
            phone.setText(rs.getString(10));
            String s = "C:\\java\\Workspace\\MASTER_GIT\\mazraaty\\src\\main\\resources\\admin_photos\\"+rs.getString(11);
            Image im = new Image(s,false);
            photo.setFill(new ImagePattern(im));

        }
    }





    }