package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.itextpdf.text.*;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.mazraaty.Main.c;

public class Setting_controller {
    public PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label today;

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

    public void print(ActionEvent event) throws IOException, DocumentException, SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        LocalDateTime now = LocalDateTime.now();

        String hash_name =hashCode()+"-"+dtf.format(now);
        String file_name = "src/main/resources/PDF/Admin_infos-"+hash_name+".pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();

        //add paragraph
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE, BaseColor.RED);
        String p = "Admin informations : ";
        Paragraph para = new Paragraph(p,f);
        para.setAlignment(10);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        pst = c.prepareStatement("SELECT * FROM admin ORDER BY ID DESC LIMIT 1");
        ResultSet rs = pst.executeQuery();

        f = new Font(Font.FontFamily.TIMES_ROMAN, 15.0f, Font.BOLD, BaseColor.BLACK);
        while (rs.next()) {
            //add name
            p = "prénom : " ;
            para = new Paragraph(p, f);
            para.add(rs.getString(2));
            para.add("                                     ");
            para.add("nom : ");
            para.add(rs.getString(3));
            document.add(para);
            document.add(new Paragraph(" "));
            //add username and password
            p = "username : " ;
            para = new Paragraph(p, f);
            para.add(rs.getString(9));
            para.add("                                     ");
            para.add("password : ");
            para.add(rs.getString(4));
            document.add(para);
            document.add(new Paragraph(" "));
            //add email and telephone
            p = "Email : " ;
            para = new Paragraph(p, f);
            para.add(rs.getString(5));
            para.add("                           ");
            para.add("N° téléphone : ");
            para.add(rs.getString(10));
            document.add(para);
            document.add(new Paragraph(" "));
            //add SEXE and DATE
            p = "sexe : " ;
            para = new Paragraph(p, f);
            para.add(rs.getString(6));
            para.add("                           ");
            para.add("data de naissance : ");
            para.add(rs.getString(7));
            document.add(para);
            document.add(new Paragraph(" "));
            //aDD adresse
            p = "adresse : " ;
            para = new Paragraph(p, f);
            para.add(rs.getString(8));
            document.add(para);
            document.add(new Paragraph(" "));

        }
        document.close();

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
        new Stage_controller().init_date(today);
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