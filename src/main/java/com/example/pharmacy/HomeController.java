package com.example.pharmacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button addMedicines_addBtn;

    @FXML
    private ComboBox<?> addMedicines_category;

    @FXML
    private Button addMedicines_clearBtn;

    @FXML
    private TableColumn<?, ?> addMedicines_col_category;

    @FXML
    private TableColumn<?, ?> addMedicines_col_date;

    @FXML
    private TableColumn<?, ?> addMedicines_col_medicineID;

    @FXML
    private TableColumn<?, ?> addMedicines_col_price;

    @FXML
    private TableColumn<?, ?> addMedicines_col_productName;

    @FXML
    private TableColumn<?, ?> addMedicines_col_status;

    @FXML
    private Button addMedicines_deleteBtn;

    @FXML
    private AnchorPane addMedicines_form;

    @FXML
    private TextField addMedicines_medicineID;

    @FXML
    private TextField addMedicines_price;

    @FXML
    private TextField addMedicines_productName;

    @FXML
    private TextField addMedicines_search;

    @FXML
    private ComboBox<?> addMedicines_status;

    @FXML
    private TableView<?> addMedicines_tableView;

    @FXML
    private Button addMedicines_updateBtn;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button medicines_btn;

    @FXML
    private Button purchase_btn;

    @FXML
    private AnchorPane purchase_form;

    @FXML
    private Label username;

    public void displayUsername(){
        String user = getData.username;

        //the first letter is big, the others are small
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    private double x;
    private double y;
    @FXML
    void logout() throws IOException {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to log out?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();


                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                root.setOnMouseClicked(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased(event -> {
                    stage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchForm(ActionEvent event) {
        if(event.getSource() == medicines_btn){
            addMedicines_form.setVisible(true);
            home_form.setVisible(false);
            customer_form.setVisible(false);
            purchase_form.setVisible(false);

            medicines_btn.setStyle("-fx-background-color: #fff; -fx-text-fill: #C85F77;");
            customer_btn.setStyle("-fx-background-color: #333856;");
            purchase_btn.setStyle("-fx-background-color: #333856;");
        }

        if(event.getSource() == customer_btn){
            addMedicines_form.setVisible(false);
            home_form.setVisible(false);
            customer_form.setVisible(true);
            purchase_form.setVisible(false);

            customer_btn.setStyle("-fx-background-color: #fff; -fx-text-fill: #C85F77;");
            medicines_btn.setStyle("-fx-background-color: #333856;");
            purchase_btn.setStyle("-fx-background-color: #333856;");

        }

        if(event.getSource() == purchase_btn){
            addMedicines_form.setVisible(false);
            home_form.setVisible(false);
            customer_form.setVisible(false);
            purchase_form.setVisible(true);

            purchase_btn.setStyle("-fx-background-color: #fff; -fx-text-fill: #C85F77;");
            customer_btn.setStyle("-fx-background-color: #333856;");
            medicines_btn.setStyle("-fx-background-color: #333856;");
        }
    }

    @FXML
    void addMedicineAdd(ActionEvent event) {

    }

    @FXML
    void addMedicineDelete(ActionEvent event) {

    }

    @FXML
    void addMedicineListCategory(ActionEvent event) {

    }

    @FXML
    void addMedicineListStatus(ActionEvent event) {

    }

    @FXML
    void addMedicineReset(ActionEvent event) {

    }

    @FXML
    void addMedicineSearch(KeyEvent event) {

    }

    @FXML
    void addMedicineSelect(MouseEvent event) {

    }

    @FXML
    void addMedicineUpdate(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void minimize(ActionEvent event) {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        home_form.setVisible(true);
        addMedicines_form.setVisible(false);
        customer_form.setVisible(false);
        purchase_form.setVisible(false);
    }
}
