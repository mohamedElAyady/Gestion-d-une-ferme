package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import static com.example.mazraaty.Main.c;

public class Stage_controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void print(ActionEvent event) throws IOException, DocumentException {

        String file_name = "src/main/resources/PDF/test.pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();

        Paragraph para = new Paragraph("this is a test");
        document.add(para);
        document.close();




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


    @FXML
    private Circle photo;


    @FXML
    private ComboBox<String> choisebox;
    PreparedStatement pst;
    ObservableList<String> options = FXCollections.observableArrayList(
            "Option 1",
            "Option 2",
            "Option 3"
    );

    @FXML
    void pass() {
        choisebox.getItems().addAll(options);
        System.out.println(choisebox.getValue());
        try {

            //changement la
            pst = c.prepareStatement("select * from stock");
            ResultSet rs = pst.executeQuery();
            {


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void profil_img(Circle photo) throws SQLException {
        pst = c.prepareStatement("SELECT * FROM admin where ID = (select MAX(ID) from admin)");
        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            //change this path !!!!
            String s = "C:\\java\\Workspace\\MASTER_GIT\\mazraaty\\src\\main\\resources\\admin_photos"+rs.getString(11);
            Image im = new Image(s,false);
            photo.setFill(new ImagePattern(im));

        }
    }

    public void initialize() throws SQLException {
        profil_img(photo);
    }



}

