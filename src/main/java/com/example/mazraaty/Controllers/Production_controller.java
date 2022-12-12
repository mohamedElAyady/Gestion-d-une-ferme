package com.example.mazraaty.Controllers;

import com.example.mazraaty.Main;
import com.example.mazraaty.Models.Production;
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

public class Production_controller {

    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int id = -1;


    //changement la
    ObservableList<Production> productions = FXCollections.observableArrayList();

    @FXML
    private Label date;

    @FXML
    private Button modifier_btn;

    @FXML
    private Label pro_indivi;

    @FXML
    private Label prix_total;

    @FXML
    private TextField search_by_id;

    @FXML
    private DatePicker date_picker;

    //changement la
    @FXML
    private TableView<Production> table;

    @FXML
    private TableColumn<Production, String> col5;

    @FXML
    private TableColumn<Production, String> col3;

    @FXML
    private TableColumn<Production, String> col4;

    @FXML
    private TableColumn<Production, String> col1;

    @FXML
    private TableColumn<Production, String> col2;

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
        String file_name = "src/main/resources/PDF/Production-"+hash_name+".pdf";
        Document document = new Document();

        PdfWriter.getInstance(document,new FileOutputStream(file_name));
        document.open();

        //add paragraph
        Font f=new Font(Font.FontFamily.TIMES_ROMAN,25.0f,Font.UNDERLINE,BaseColor.RED);
        String p = "Production Records : ";
        Paragraph para = new Paragraph(p,f);
        para.setAlignment(10);
        document.add(para);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("Vache ID"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("litres"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prix/litre"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date d'enrg"));
        table.addCell(c1);

        table.setHeaderRows(1);

        //changement la
        pst = c.prepareStatement("select ID,date_enrg,ID_vache,litres,prix_litre,total from production");
        ResultSet rs = pst.executeQuery();
        {
            while (rs.next()) {
                table.addCell(rs.getString("ID_vache"));
                table.addCell(rs.getString("litres"));
                table.addCell(rs.getString("prix_litre"));
                table.addCell(rs.getString("total"));
                table.addCell(rs.getString("date_enrg"));
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
        Parent root1 = FXMLLoader.load(getClass().getResource("@../../../../mazraaty/add_milk_infos.fxml"));
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Ajouter");
        //manage maximize, minimize and close button
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //specify modality for the new window
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setUserData(productions);
    }

    @FXML
    public void table_search(ActionEvent event){


        ResultSet rs=null;

        try {
            if(date_picker.getValue() == null){
                String id = search_by_id.getText();

                //changement la
                productions.clear();

                //changement la
                pst = c.prepareStatement("select date_enrg,ID_vache,litres,prix_litre,total from production WHERE ID_vache = ? ");
                pst.setString(1, id);
                rs = pst.executeQuery();
            }else if(search_by_id.getText().isEmpty()){
                String  date =date_picker.getValue().toString();

                //un changement la
                productions.clear();

                //changement la
                pst = c.prepareStatement("select date_enrg,ID_vache,litres,prix_litre,total from production WHERE date_enrg = ? ");
                pst.setString(1, date);
                rs = pst.executeQuery();
            }
            else if (!date_picker.getValue().toString().isEmpty()&& !search_by_id.getText().isEmpty()){
                String id = search_by_id.getText();
                String  date =date_picker.getValue().toString();
                //changement la
                productions.clear();

                //changement la
                pst = c.prepareStatement("select date_enrg,ID_vache,litres,prix_litre,total from production WHERE ID_vache = ? AND  date_enrg = ? ");
                pst.setString(1, id);
                pst.setString(2, date);
                rs = pst.executeQuery();
            }
            long k = 0;
            float p_t = 0;
            while (rs.next()) {
                Production p = new Production();
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_enrg"));
                p.setLitres(rs.getString("litres"));
                p.setPrix(rs.getString("prix_litre"));
                p.setTotal(rs.getString("total"));

                //pour calculer la production du quotidien
                k += Long.parseLong(p.getLitres());
                p_t += Float.parseFloat(p.getTotal());
                //changement la
                productions.add(p);
            }
            //changement la
            table.setItems(productions);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().dateProperty());
            col3.setCellValueFactory(f -> f.getValue().litresProperty());
            col4.setCellValueFactory(f -> f.getValue().totalProperty());
            col2.setCellValueFactory(f -> f.getValue().PrixProperty());
            modifier_btn.setDisable(true);
            //print le somme
            pro_indivi.setText(String.valueOf(k)+" L");
            prix_total.setText(String.valueOf(p_t)+ " DH");
        } catch (SQLException ex)
        {
            Logger.getLogger(Production_controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void refrech() throws SQLException {
        //changement la
        productions.clear();
        initialize();
        pro_indivi.setText("-----");
        prix_total.setText("-----");
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
            FXMLLoader root1 = new FXMLLoader(getClass().getResource("@../../../../mazraaty/update_milk_infos.fxml"));

            Scene scene1 = new Scene(root1.load());
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Modifier");
            //manage maximize, minimize and close button
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //specify modality for the new window
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            //pass id to update controller
            Pass_production t = new Pass_production(id, productions);

            Update_milk_controller controller = root1.getController();
            controller.get(id);
            primaryStage.setUserData(t);
            primaryStage.show();
        }
    }

    public void initialize() throws SQLException {
        new Stage_controller().profil_img(photo);
        id = -1;
        pro_indivi.setText("-----");
        prix_total.setText("-----");
        table();
        new Stage_controller().init_date(date);
    }

    public void refrech_table(ObservableList<Production> productions){
        try{
            productions.clear();

            //changement la
            pst = c.prepareStatement("select * from production");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //changement la
                Production p = new Production();
                p.setKey(rs.getString("ID"));
                p.setId(rs.getString("ID_vache"));
                p.setDate(rs.getString("date_enrg"));
                p.setLitres(rs.getString("litres"));
                p.setPrix(rs.getString("prix_litre"));
                p.setTotal(rs.getString("total"));
                productions.add(p);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void table() throws SQLException {

        try {

            //changement la
            pst = c.prepareStatement("select ID,date_enrg,ID_vache,litres,prix_litre,total from production");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    //changement la
                    Production p = new Production();
                    p.setId(rs.getString("ID_vache"));
                    p.setDate(rs.getString("date_enrg"));
                    p.setLitres(rs.getString("litres"));
                    p.setPrix(rs.getString("prix_litre"));
                    p.setTotal(rs.getString("total"));
                    p.setKey(rs.getString("ID"));
                    //changement la
                    productions.add(p);

                }
            }
            //changement la
            table.setItems(productions);
            col1.setCellValueFactory(f -> f.getValue().idProperty());
            col5.setCellValueFactory(f -> f.getValue().dateProperty());
            col3.setCellValueFactory(f -> f.getValue().litresProperty());
            col4.setCellValueFactory(f -> f.getValue().totalProperty());
            col2.setCellValueFactory(f -> f.getValue().PrixProperty());
        } catch (SQLException ex)
        {
            Logger.getLogger(Production_controller.class.getName()).log(Level.SEVERE, null, ex);
        }



        table.setRowFactory( tv -> {
            TableRow<Production> myRow = new TableRow<>();
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



}

