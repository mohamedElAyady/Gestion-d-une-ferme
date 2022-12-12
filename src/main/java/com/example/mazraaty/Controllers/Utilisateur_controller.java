package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Utilisateur;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.mazraaty.Main.c;

public class Utilisateur_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id;
    ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();

    @FXML
    private Circle photo;

    @FXML
    private Label today;

    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private TableView<Utilisateur> table1;

    @FXML
    private TableColumn<Utilisateur, String> col_cin;

    @FXML
    private TableColumn<Utilisateur, String> col_nom;

    @FXML
    private TableColumn<Utilisateur, String> col_num;

    @FXML
    private TableColumn<Utilisateur, String> col_email;

    @FXML
    private TableColumn<Utilisateur, String> col_type;


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


    public void print(ActionEvent event) throws IOException, DocumentException, SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        LocalDateTime now = LocalDateTime.now();

        String hash_name =hashCode()+"-"+dtf.format(now);
        String file_name = "src/main/resources/PDF/Utilisateurs-"+hash_name+".pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();

        //add paragraph
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE, BaseColor.RED);
        String p = "Utilisateur Records : ";
        Paragraph para = new Paragraph(p,f);
        para.setAlignment(10);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("CIN"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("nom et prénom"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("téléphone"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Email"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Position"));
        table.addCell(c1);

        table.setHeaderRows(1);

        pst = c.prepareStatement("select * from utilisateur");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            table.addCell(rs.getString("ID"));
            table.addCell(rs.getString("Cin"));
            table.addCell(rs.getString("Nom"));
            table.addCell(rs.getString("Num"));
            table.addCell(rs.getString("Email"));
            table.addCell(rs.getString("Type"));
        }

        document.add(table);



        document.close();
    }

    public void close_btn(ActionEvent event) throws IOException {
        new Stage_controller().close_btn(event);
    }

    public void log_out(ActionEvent event) throws IOException {
        new Stage_controller().log_out(event);

    }

    @FXML
    void add_infos(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_utilisateur.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(utilisateurs);
    }

    @FXML
    public void table_search(ActionEvent event){

        ResultSet rs=null;
        try {
        if(!search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                utilisateurs.clear();
                pst = c.prepareStatement("select * from utilisateur WHERE Cin LIKE '%"+id+"%'");

                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setJC(rs.getString("Cin"));
                p.setNom(rs.getString(
                        "Nom"));
                p.setNum(rs.getString("Num"));
                p.setEmail(rs.getString("Email"));
                p.setType(rs.getString("Type"));
                utilisateurs.add(p);
            }
            table1.setItems(utilisateurs);
            col_cin.setCellValueFactory(f -> f.getValue().jcProperty());
            col_nom.setCellValueFactory(f -> f.getValue().nomProperty());
            col_num.setCellValueFactory(f -> f.getValue().numProperty());
            col_email.setCellValueFactory(f -> f.getValue().emailProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());

            modifier_btn.setDisable(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(Utilisateur_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        utilisateurs.clear();
        initialize();
        modifier_btn.setDisable(false);
    }

    @FXML
    public void update(ActionEvent event) throws IOException, SQLException {
        Stage primaryStage = new Stage();
        if (id==-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE !!");
            alert.setHeaderText("Choisire une item");
            alert.showAndWait();
        }else {

            //open new stage
            //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
            FXMLLoader root1 = new FXMLLoader(getClass().getResource("@../../../../mazraaty/update_utilisateur.fxml"));
            Scene scene1 = new Scene(root1.load());
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            Pass_utilisateur t = new Pass_utilisateur(id,utilisateurs);

            Update_utilisteur_controller controller = root1.getController();
            controller.get(id);

            primaryStage.setUserData(t);
            primaryStage.show();}

    }

    @FXML
    public void suprimer(ActionEvent event) throws IOException{
        if (id==-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE !!");
            alert.setHeaderText("Choisire une item");
            alert.showAndWait();
        }else {
            try {
            pst = c.prepareStatement("delete from utilisateur where ID=?");
            pst.setInt(1, id);
            pst.executeUpdate();
                refrech();
            //get the data from Production_Controller using getUserData() method

            //open the confirmation window
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Utilisateur infos");
            alert.setHeaderText("Utilisateur informations");
            alert.setContentText("Bien ajouter !");
            alert.showAndWait();



            } catch (SQLException ee) {
                System.out.println(ee.getMessage());
        }

           }

    }

    public void initialize() throws SQLException {
        id =-1;
        new Stage_controller().init_date(today);
        new Stage_controller().profil_img(photo);
        table();

    }


    public void refrech_table(ObservableList<Utilisateur> utilisateurs){
        try{
            utilisateurs.clear();
            pst = c.prepareStatement("select * from utilisateur");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setJC(rs.getString("Cin"));
                p.setNom(rs.getString("Nom"));
                p.setNum(rs.getString("Num"));
                p.setEmail(rs.getString("Email"));
                p.setType(rs.getString("Type"));
                utilisateurs.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {
            pst = c.prepareStatement("select ID,Cin,Nom,Num,Email,Type from utilisateur");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Utilisateur p = new Utilisateur();
                    p.setJC(rs.getString("Cin"));
                    p.setNom(rs.getString("Nom"));
                    p.setNum(rs.getString("Num"));
                    p.setEmail(rs.getString("Email"));
                    p.setKey(rs.getString("ID"));
                    p.setType(rs.getString("Type"));
                    utilisateurs.add(p);

                }
            }
            table1.setItems(utilisateurs);
            col_cin.setCellValueFactory(f -> f.getValue().jcProperty());
            col_nom.setCellValueFactory(f -> f.getValue().nomProperty());
            col_num.setCellValueFactory(f -> f.getValue().numProperty());
            col_email.setCellValueFactory(f -> f.getValue().emailProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());
        } catch (SQLException ex)
        {
            Logger.getLogger(Utilisateur_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table1.setRowFactory( tv -> {
            TableRow<Utilisateur> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table1.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table1.getItems().get(myIndex).getKey()));

                }
            });
            return myRow;
        });



    }



}


