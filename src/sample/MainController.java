package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.inventory;

public class MainController {

    // Main Part Table

    @FXML
    public TableView<Part> tablePart;
    @FXML
    public TableColumn<Part, Integer> partIDCol;
    @FXML
    public TableColumn<Part, String> partNameCol;
    @FXML
    public TableColumn<Part, Integer> invLevelCol;
    @FXML
    public TableColumn<Part, Double> costPerUnitCol;

    // Main Product Table

    @FXML
    public TableView<Product> tableProduct;
    @FXML
    public TableColumn<Product, Integer> productIDCol;
    @FXML
    public TableColumn<Product, String> productNameCol;
    @FXML
    public TableColumn<Product, Integer> productInvLevelCol;
    @FXML
    public TableColumn<Product, Double> productPricePerCol;

    @FXML
    public Button exit;

    @FXML
    public TextField searchPartField;

    @FXML
    public TextField searchProductField;

    // Handlers

    @FXML
    protected void handleDeletePart(ActionEvent event){
        boolean deleteconfirm;
        Part selectedPart = tablePart.getSelectionModel().getSelectedItem();
        deleteconfirm = inventory.deletePart(selectedPart);
        if (deleteconfirm){
            System.out.println("Item Deleted");
        }
    } // NOT FINISHED - SETUP BOOLEAN MESSAGE

    @FXML
    public void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {

        // SETUP PARTS TABLE
        FilteredList<Part> flPart = new FilteredList(inventory.getAllParts(), p -> true);//Pass the data to a filtered list
        tablePart.setItems(flPart);
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        costPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // SEARCH
        searchPartField.setOnKeyReleased(keyEvent ->
                {
                    flPart.setPredicate(p -> p.getName().toLowerCase().contains(searchPartField.getText().toLowerCase().trim()));
                    //filter table by first name
                });
        // SETUP PRODUCT TABLE

        FilteredList<Product> flProduct = new FilteredList(inventory.getAllProducts(), p -> true);//Pass the data to a filtered list

        tableProduct.setItems(flProduct);
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchProductField.setOnKeyReleased(keyEvent ->
        {
            flProduct.setPredicate(p -> p.getName().toLowerCase().contains(searchProductField.getText().toLowerCase().trim()));
            //filter table by first name
        });

    }

    // Scene Changes

    public void changeToAddPart(ActionEvent event) throws IOException {

        // Sets up the scene

        Parent addPartParent = FXMLLoader.load(getClass().getResource("addpart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        // Get Stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Sets Scene

        window.setScene(addPartScene);
        window.show();

    }
    public void changeToModifyPart(ActionEvent event) throws IOException {

        Part selectedPart = tablePart.getSelectionModel().getSelectedItem();

        // Sets up the scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifypart.fxml"));
        Parent modifyPartParent = loader.load();

        Scene modifyPartScene = new Scene(modifyPartParent);

        // Pass Selected Data

        ModifyPartController controller = loader.getController();
        controller.setFields(selectedPart);


        // Get Stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Sets Scene

        window.setScene(modifyPartScene);
        window.show();

    }

    public void changeToAddProduct(ActionEvent event) throws IOException {

        // Sets up the scene

        Parent addProductParent = FXMLLoader.load(getClass().getResource("addproduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);

        // Get Stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Sets Scene

        window.setScene(addProductScene);
        window.show();

    }
    public void changeToModifyProduct(ActionEvent event) throws IOException {

        Product selectedProduct = tableProduct.getSelectionModel().getSelectedItem();

        // Sets up the scene

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyproduct.fxml"));
        Parent modifyProductParent = loader.load();

        Scene modifyProductScene = new Scene(modifyProductParent);

        // Pass Selected Data

        ModifyProductController controller = loader.getController();
        controller.setFields(selectedProduct);

        // Get Stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Sets Scene

        window.setScene(modifyProductScene);
        window.show();

    }


}
