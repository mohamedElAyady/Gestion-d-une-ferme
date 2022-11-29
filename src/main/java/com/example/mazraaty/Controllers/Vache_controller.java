package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Vache;

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

public class Vache_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id;
    ObservableList<Vache> vaches = FXCollections.observableArrayList();

    @FXML
    private Label date;

    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_picker;

    @FXML
    private TableView<Vache> table;

    @FXML
    private TableColumn<Vache, String> col_date;

    @FXML
    private TableColumn<Vache, String> col_type;

    @FXML
    private TableColumn<Vache, String> col_status;

    @FXML
    private TableColumn<Vache, String> id_col;

    @FXML
    private Circle photo;



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
        String file_name = "src/main/resources/PDF/Vache-"+hash_name+".pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();
        //add image
        Image img = Image.getInstance("src/main/resources/images/logo.png");
        document.add(img);

        //add paragraph
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE, BaseColor.RED);
        String p = "Vache Records : ";
        Paragraph para = new Paragraph(p,f);
        para.setAlignment(10);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Vache ID"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Type"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Statut"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date d'enrg"));
        table.addCell(c1);

        table.setHeaderRows(1);

        //changement la
        pst = c.prepareStatement("select * from vaches");
        ResultSet rs = pst.executeQuery();
        {
            while (rs.next()) {
                table.addCell(rs.getString("ID_vache"));
                table.addCell(rs.getString("type"));
                table.addCell(rs.getString("statut"));
                table.addCell(rs.getString("date_naiss"));
            }
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
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_vache_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(vaches);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;

        try {
            if(date_picker.getValue() == null){
                String id = search_by_id.getText();
                vaches.clear();
                pst = c.prepareStatement("select date_naiss,ID_vache,type,statut from vaches WHERE ID_vache = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }else if(search_by_id.getText().isEmpty()){
                String  date =date_picker.getValue().toString();
                vaches.clear();
                pst = c.prepareStatement("select date_naiss,ID_vache,type,statut from vaches WHERE date_naiss = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            }
            else if (!date_picker.getValue().toString().isEmpty()&& !search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                String  date =date_picker.getValue().toString();
                vaches.clear();
                pst = c.prepareStatement("select date_naiss,ID_vache,type,statut from vaches WHERE ID_vache = ? AND  date_naiss = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Vache p = new Vache();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_naiss"));
                p.setType(rs.getString("type"));
                p.setStatus(rs.getString("statut"));

                vaches.add(p);
            }
            table.setItems(vaches);
            id_col.setCellValueFactory(f -> f.getValue().idProperty());
            col_date.setCellValueFactory(f -> f.getValue().dateProperty());
            col_type.setCellValueFactory(f -> f.getValue().typeProperty());
            col_status.setCellValueFactory(f -> f.getValue().statusProperty());


            modifier_btn.setDisable(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(Vache_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        vaches.clear();
        initialize();
        modifier_btn.setDisable(false);
    }
    @FXML
   public void update(ActionEvent event) throws IOException{
        if (id==-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE !!");
            alert.setHeaderText("Choisire une item");
            alert.showAndWait();
        }else {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/update_vache_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Modifier");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        //pass id to update controller
            TEST2_vache t = new TEST2_vache(id,vaches);

        primaryStage.setUserData(t);
        primaryStage.show();}

    }

    public void initialize() throws SQLException {
        new Stage_controller().profil_img(photo);
        id =-1;
        table();
        new Stage_controller().init_date(date);
    }

    public void refrech_table(ObservableList<Vache> vaches){
        try{
            vaches.clear();
            pst = c.prepareStatement("select * from vaches");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Vache p = new Vache();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_naiss"));
                p.setType(rs.getString("type"));
                p.setStatus(rs.getString("statut"));

                vaches.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {
            pst = c.prepareStatement("select ID_vache,date_naiss,type,statut from vaches");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    Vache p = new Vache();
                    p.setId(rs.getString("ID_vache"));
                    p.setDate(rs.getString("date_naiss"));
                    p.setType(rs.getString("type"));
                    p.setStatus(rs.getString("statut"));

                    vaches.add(p);
                }
            }
            table.setItems(vaches);
            id_col.setCellValueFactory(f -> f.getValue().idProperty());
            col_date.setCellValueFactory(f -> f.getValue().dateProperty());
           col_type.setCellValueFactory(f -> f.getValue().typeProperty());
           col_status.setCellValueFactory(f -> f.getValue().statusProperty());

        } catch (SQLException ex)
        {
            Logger.getLogger(Vache_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setRowFactory( tv -> {
            TableRow<Vache> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

                }
            });
            return myRow;
        });



    }



}

