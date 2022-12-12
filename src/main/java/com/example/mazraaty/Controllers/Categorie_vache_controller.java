package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.mazraaty.Main.c;

public class Categorie_vache_controller {
    private PreparedStatement pst;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane Content;

    @FXML
    private Label Mère_vache;

    @FXML
    private Label Petit_vache;

    @FXML
    private Label Taureau;

    @FXML
    private Label Vache_latière;

    @FXML
    private TextField recherche;


    public void tableaudeboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/tableau_de_board.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tableau de board");
        stage.show();
    }

    public void utilisateur(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/utilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Utilisateur");
        stage.show();
    }

    public void vaccine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vaccine.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Surveillance des vaccins");
        stage.show();
    }

    public void vente_vache(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vente_vache.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vente de vaches");
        stage.show();
    }

    public void categorie_vache(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/categorie_vache.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Categorie de vache");
        stage.show();
    }

    public void alimentation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/alimentation.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("L'alimentation");
        stage.show();
    }

    public void production(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/production.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Production");
        stage.show();
    }

    public void vente_lait(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vente_lait.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vente de lait");
        stage.show();
    }

    public void vache(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vache.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vache");
        stage.show();
    }

    public void settings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/settings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }

    public void search(ActionEvent event) throws IOException {
        System.out.println("this function is not working yet !!");
    }

    public void print(ActionEvent event) throws IOException {
        System.out.println("this function is not working yet !!");
    }

    public void close_btn(ActionEvent event) throws IOException {
        Stage stage1;
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.close();
    }

    public void log_out(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage log_stage = new Stage();
        Main log_in = new Main();
        stage.close();
        log_in.start(log_stage);

    }
    public void m(){
        int a = 0,b=0,d=0,e=0;
        try {
            pst = c.prepareStatement("select type from vaches");
            ResultSet rs = pst.executeQuery();
            {
        while (rs.next()) {
            Utilisateur m = new Utilisateur();
            if (rs.getString("type").equals("Mère vache")) {a++;}
            else if (rs.getString("type").equals("Petit vache")) {b++;}
            else if (rs.getString("type").equals("Vache latière")) {d++;}
            else if (rs.getString("type").equals("Taureau")) {e++;}

        }
                Mère_vache.setText(String.valueOf(a));
                Petit_vache.setText(String.valueOf(b));
                Taureau.setText(String.valueOf(e));
                Vache_latière.setText(String.valueOf(d));
            }
        }catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
    }
    @FXML
    void tableau1(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/vache_latière.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("tableau");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

    }
    @FXML
    void tableau2(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/petit_vache.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("tableau");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

    }
    @FXML
    void tableau3(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/taureau.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("tableau");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

    }
    @FXML
    void tableau4(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/mère_vache.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("tableau");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

    }


    public void initialize(){
        m();
    }

}
