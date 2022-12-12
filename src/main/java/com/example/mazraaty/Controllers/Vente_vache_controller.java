package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;

import com.example.mazraaty.Models.Vente_vache;
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

public class Vente_vache_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id = -1;


    //changement la
    ObservableList<Vente_vache> vente_vaches = FXCollections.observableArrayList();



    @FXML
    private Label date;

    @FXML
    private Button modifier_btn;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_vente;

    //changement la
    @FXML
    private TableView<Vente_vache> table;

    @FXML
    private TableColumn<Vente_vache, String> col5;

    @FXML
    private TableColumn<Vente_vache, String> col3;

    @FXML
    private TableColumn<Vente_vache, String> col4;

    @FXML
    private TableColumn<Vente_vache, String> col1;

    @FXML
    private TableColumn<Vente_vache, String> col2;

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
        String file_name = "src/main/resources/PDF/Vente_vaches-"+hash_name+".pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();

        //add paragraph
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE, BaseColor.RED);
        String p = "Cow salesr Records : ";
        Paragraph para = new Paragraph(p,f);
        para.setAlignment(10);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ID Vache"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prix de vente"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Client"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("télé_Client"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date_vente"));
        table.addCell(c1);

        table.setHeaderRows(1);

        //changement la
        pst = c.prepareStatement("SELECT * FROM vente_vaches");
        ResultSet rs = pst.executeQuery();
        {
            while (rs.next()) {
                table.addCell(rs.getString("ID"));
                table.addCell(rs.getString("ID_vache"));
                table.addCell(rs.getString("prix_vente"));
                table.addCell(rs.getString("name_client"));
                table.addCell(rs.getString("mobile"));
                table.addCell(rs.getString("date_vente"));
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
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_vente_vache_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(vente_vaches);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;

        try {
            if(date_vente.getValue() == null){
                String id = search_by_id.getText();

                //changement la
                vente_vaches.clear();

                //changement la
                pst = c.prepareStatement("select * from vente_vaches WHERE ID_vache = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }else if(search_by_id.getText().isEmpty()){
                String  date =date_vente.getValue().toString();

                //un changement la
                vente_vaches.clear();

                //changement la
                pst = c.prepareStatement("select * from vente_vaches WHERE date_vente = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            }
            else if (!date_vente.getValue().toString().isEmpty()&& !search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                String  date =date_vente.getValue().toString();
                //changement la
                vente_vaches.clear();

                //changement la
                pst = c.prepareStatement("select * from vente_vaches WHERE ID_vache = ? AND  date_vente = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Vente_vache p = new Vente_vache();
                p.setId(rs.getString("ID_vache"));
                p.setName_client(rs.getString("name_client"));
                p.setDate_vente(rs.getString("date_vente"));
                p.setMobile(rs.getString("mobile"));
                p.setPrix_vente(rs.getString("prix_vente"));

                vente_vaches.add(p);
                //pour calculer la production du quotidien
             //   k += Long.parseLong(p.getLitres());
             //   p_t += Float.parseFloat(p.getTotal());
                //changement la
              //  vente_laits.add(p);
            }
            //changement la
            table.setItems(vente_vaches);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().date_venteProperty());
            col3.setCellValueFactory(f -> f.getValue().mobileProperty());
            col4.setCellValueFactory(f -> f.getValue().name_clientProperty());
            col2.setCellValueFactory(f -> f.getValue().prix_venteProperty());

            modifier_btn.setDisable(true);
            //print le somme
           // pro_indivi.setText(String.valueOf(k)+" L");
          //  prix_total.setText(String.valueOf(p_t)+ " DH");
        } catch (SQLException ex)
        {
            Logger.getLogger(Vente_vache_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        //changement la
        vente_vaches.clear();
        initialize();
        modifier_btn.setDisable(false);

    }

    @FXML
    public void update(ActionEvent event) throws IOException, SQLException {
        Stage primaryStage = new Stage();
        //open new stage
        //Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        if (id == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error !");
            alert.setHeaderText("Sélectionnez un item !");
            alert.showAndWait();
        }else {
            //changement la
            FXMLLoader root1 =new FXMLLoader(getClass().getResource("@../../../../mazraaty/update_vente_vache_infos.fxml"));

            Scene scene1 = new Scene(root1.load());
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            pass_vente_vache t = new pass_vente_vache(id, vente_vaches);
            Update_vente_vache_controller controller = root1.getController();
            controller.get(id);
            primaryStage.setUserData(t);
            primaryStage.show();
        }
    }



    public void refrech_table(ObservableList<Vente_vache> vente_vaches){
        try{
            vente_vaches.clear();

            //changement la
            pst = c.prepareStatement("select * from vente_vaches");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //changement la
                Vente_vache p = new Vente_vache();
                p.setId(rs.getString("ID_vache"));
                p.setName_client(rs.getString("name_client"));
                p.setMobile(rs.getString("mobile"));
                p.setPrix_vente(rs.getString("prix_vente"));
                p.setDate_vente(rs.getString("date_vente"));
                p.setKey(rs.getString("ID"));
                //changement la
                vente_vaches.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {


        try {

            //changement la
            pst = c.prepareStatement("select * from vente_vaches");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    //changement la
                    Vente_vache p = new Vente_vache();
                    p.setId(rs.getString("ID_vache"));
                    p.setName_client(rs.getString("name_client"));
                    p.setMobile(rs.getString("mobile"));
                    p.setPrix_vente(rs.getString("prix_vente"));
                    p.setDate_vente(rs.getString("date_vente"));

                    p.setKey(rs.getString("ID"));
                    //changement la
                    vente_vaches.add(p);
                }
            }
            //changement la
            table.setItems(vente_vaches);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().date_venteProperty());

            col3.setCellValueFactory(f -> f.getValue().name_clientProperty());
            col4.setCellValueFactory(f -> f.getValue().mobileProperty());
            col2.setCellValueFactory(f -> f.getValue().prix_venteProperty());
        } catch (SQLException ex)
        {
            Logger.getLogger(Vente_vache_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setRowFactory( tv -> {
            TableRow<Vente_vache> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getKey()));

                }
            });
            return myRow;
        });



    }

    public void initialize() throws SQLException {
        id =-1;
        table();
        new Stage_controller().init_date(date);
    }



}

