package sample;

import javafx.beans.binding.When;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Main.inventory;

public class AddProductController {

    public ObservableList<Part> tempparts = FXCollections.observableArrayList();


    @FXML
    public TableView<Part> tableAddPart;
    @FXML
    public TableColumn<Part, Integer> partIDAddCol;
    @FXML
    public TableColumn<Part, String> partNameAddCol;
    @FXML
    public TableColumn<Part, Integer> invLevelAddCol;
    @FXML
    public TableColumn<Part, Double> costPerUnitAddCol;

    @FXML
    public TableView<Part> tableDeletePart;
    @FXML
    public TableColumn<Part, Integer> partIDDelCol;
    @FXML
    public TableColumn<Part, String> partNameDelCol;
    @FXML
    public TableColumn<Part, Integer> invLevelDelCol;
    @FXML
    public TableColumn<Part, Double> costPerUnitDelCol;

    @FXML
    private TextField name;
    @FXML
    private TextField inv;
    @FXML
    private TextField cost;
    @FXML
    private TextField max;
    @FXML
    private TextField min;

    @FXML
    private TextField searchPartField;
    @FXML
    private Label errorMessage;


    @FXML
    protected void handleAddProductAction(ActionEvent event) throws IOException {
        String writeName = name.getText();
        Integer writeInv = Integer.parseInt(inv.getText());
        Double writeCost = Double.parseDouble(cost.getText());
        Integer writeMax = Integer.parseInt(max.getText());
        Integer writeMin = Integer.parseInt(min.getText());


        Double partsPrice = 0.00;
        for (Part p : tempparts) {
            partsPrice += p.getPrice();
        }

        // PART 2 EXCEPTION HANDLING

        if (partsPrice > writeCost) {
            errorMessage.setText("Parts prices must not exceed Product price");
        } else {
            Product temp = new Product(tempparts, Main.idproductcount, writeName, writeCost, writeInv, writeMin, writeMax);
            inventory.addProduct(temp);

            System.out.println("Product Added");
            changeToMain(event);
        }

    }

    @FXML
    public void initialize() {

        FilteredList<Part> flPart = new FilteredList(inventory.getAllParts(), p -> true);//Pass the data to a filtered list
        tableAddPart.setItems(flPart);
        partIDAddCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameAddCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelAddCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        costPerUnitAddCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableDeletePart.setItems(tempparts);
        partIDDelCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameDelCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelDelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        costPerUnitDelCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // SEARCH
        searchPartField.setOnKeyReleased(keyEvent ->
        {
            flPart.setPredicate(p -> p.getName().toLowerCase().contains(searchPartField.getText().toLowerCase().trim()));
            //filter table by first name
        });

    }

    // Scene Changes
    public void changeToMain(ActionEvent event) throws IOException {

        // Sets up the scene

        Parent mainParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene mainScene = new Scene(mainParent);

        // Get Stage information

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Sets Scene

        window.setScene(mainScene);
        window.show();

    }

    public void handleAddPartToProduct(ActionEvent actionEvent) {
        Part selectedPart = tableAddPart.getSelectionModel().getSelectedItem();
        tempparts.addAll(selectedPart);
    }

    public void handleDeletePartFromProduct(ActionEvent actionEvent) {
        Part selectedPart = tableDeletePart.getSelectionModel().getSelectedItem();
        tempparts.remove(selectedPart);
    }
}
