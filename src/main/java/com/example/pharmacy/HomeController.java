package com.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ComboBox<?> addMedicines_status;

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
    private TextField customerFullName;

    @FXML
    private TextField customerPhoneNumber;

    @FXML
    private TextField customerTotal;

    @FXML
    private TextField customerPoints;

    @FXML
    private AnchorPane addMedicines_form;

    @FXML
    private TableView<customerData> customer_tableView;

    @FXML
    private TableColumn<customerData, String> customer_col_date;

    @FXML
    private TableColumn<customerData, String> customer_col_fullName;

    @FXML
    private TableColumn<customerData, String> customer_col_id;

    @FXML
    private TableColumn<customerData, String> customer_col_phoneNumber;

    @FXML
    private TableColumn<customerData, String> customer_col_points;

//    @FXML
//    private TableColumn<customerData, String> customer_col_total;

    @FXML
    private Button customer_btn;

    @FXML
    private TextField customer_search;

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

    private double x;
    private double y;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<customerData> customerListData() {
        String sql = "SELECT * FROM Customer";

        ObservableList<customerData> listData = FXCollections.observableArrayList();

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            customerData cusData;
            while (result.next()) {
                cusData = new customerData(
                        result.getInt("id"),
                        result.getString("fullName"),
                        result.getString("phoneNum"),
                        result.getDate("registrationDate"),
//                        result.getDouble("total"),
                        result.getInt("loyaltyPoints")
                );

                listData.add(cusData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<customerData> customerList;
    public void customerShowListData(){
        customerList = customerListData();

        customer_col_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customer_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        customer_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customer_col_date.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
//        customer_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customer_col_points.setCellValueFactory(new PropertyValueFactory<>("loyaltyPoints"));

        customer_tableView.setItems(customerList);
    }

    public void customerSelect(){
        customerData cusData = customer_tableView.getSelectionModel().getSelectedItem();
        int num = customer_tableView.getSelectionModel().getSelectedIndex();

        if( (num-1) < -1) return;

        customerFullName.setText(cusData.getFullName());
        customerPhoneNumber.setText(cusData.getPhoneNumber());
//        customerTotal.setText(String.valueOf(cusData.getTotal()));

//        int points = (int) (cusData.getTotal() / 100000);

        customerPoints.setText(String.valueOf(cusData.getLoyaltyPoints()));
    }

    public void customerAdd(){
        String sql = "INSERT INTO Customer (fullName,phoneNum,loyaltyPoints)"
                + " VALUES (?,?,?)";

        connect = database.connectDb();

        try{
            Alert alert;

            if(customerFullName.getText().isEmpty() || customerPhoneNumber.getText().isEmpty() || customerTotal.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fields");
                alert.showAndWait();
            } else {
                String checkData = "SELECT * FROM Customer WHERE phoneNum = '" + customerPhoneNumber.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer already exists");
                    alert.showAndWait();
                }else{
                    double total = Double.parseDouble(customerTotal.getText());
                    int loyaltyPoints = (int) (total / 100000);

                    prepare = connect.prepareStatement(sql);

                    prepare.setString(1, customerFullName.getText());
                    prepare.setString(2, customerPhoneNumber.getText());
//                    prepare.setDouble(3, Double.parseDouble(customerTotal.getText()));
                    prepare.setInt(3, loyaltyPoints);

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();

                    customerShowListData();
                    customerReset();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public void customerReset(){
        customerFullName.setText("");
        customerPhoneNumber.setText("");
        customerTotal.setText("");
        customerPoints.setText("");
    }

    public void customerUpdate(){
        String sql = "UPDATE Customer SET fullName = '" + customerFullName.getText() + "'"
                + ", phoneNum = '" + customerPhoneNumber.getText() + "'";
        connect = database.connectDb();

        try{
            Alert alert;

            if(customerFullName.getText().isEmpty() || customerPhoneNumber.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update this customer?");
                Optional<ButtonType> option= alert.showAndWait();

                if(option.get() == ButtonType.OK){
                    statement = connect.prepareStatement(sql);
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated!");
                    alert.showAndWait();

                    customerShowListData();
                    customerReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerDelete(){
        String sql = "DELETE FROM Customer WHERE phoneNum = '" + customerPhoneNumber.getText() + "'";
        connect = database.connectDb();

        try{
            Alert alert;

            if(customerFullName.getText().isEmpty() || customerPhoneNumber.getText().isEmpty() || customerPoints.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this customer?");
                Optional<ButtonType> option= alert.showAndWait();

                if(option.get() == ButtonType.OK){
                    statement = connect.prepareStatement(sql);
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();

                    customerShowListData();
                    customerReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerSearch(){
        FilteredList<customerData> filter = new FilteredList<>(customerList, e -> true);
        customer_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCustomerData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if(predicateCustomerData.getPhoneNumber().contains(searchKey)){
                    return true;
                }
                else if(predicateCustomerData.getFullName().toLowerCase().contains(searchKey)){
                    return true;
                }

                else if (String.valueOf(predicateCustomerData.getLoyaltyPoints()).contains(searchKey)) {
                    return true;
                }

                else if(predicateCustomerData.getRegistrationDate().toString().contains(searchKey)){
                    return true;
                } else {return false;}
            });
        });

        SortedList<customerData> sortedList = new SortedList<>(filter);

        sortedList.comparatorProperty().bind(customer_tableView.comparatorProperty());
        customer_tableView.setItems(sortedList);
    }

    @FXML
    void switchForm(@NotNull ActionEvent event) {
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

            customerShowListData();
//            customerSearch();
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
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void minimize(ActionEvent event) {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void displayUsername(){
        String user = getData.username;

        //the first letter is big, the others are small
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

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


                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        home_form.setVisible(true);
        addMedicines_form.setVisible(false);
        customer_form.setVisible(false);
        purchase_form.setVisible(false);

        customerShowListData();
    }
}
