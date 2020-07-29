package sample;

import javafx.beans.binding.When;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Main.inventory;

public class AddPartController {

    String machineId = "Machine ID";
    String companyName = "Company Name";
    String machineIDText = "Mach ID";
    String compNameText = "Comp Name";
    //Add Part

    @FXML
    private Label switchLabel;
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
    private TextField companyNameMachineIdText;
    @FXML
    private RadioButton rbOutsourced;
    @FXML
    private RadioButton rbInHouse;
    @FXML
    private Label errorMessage;


    @FXML
    protected void handleSubmitPartAction(ActionEvent event) throws IOException {
        String writeName = name.getText();
        Integer writeInv = Integer.parseInt(inv.getText());
        Double writeCost = Double.parseDouble(cost.getText());
        Integer writeMax = Integer.parseInt(max.getText());
        Integer writeMin = Integer.parseInt(min.getText());
        String writeCNT = companyNameMachineIdText.getText();
        boolean OS = rbOutsourced.isSelected();
        boolean IH = rbInHouse.isSelected();

        if(OS && (writeInv <= writeMax) && (writeInv >= writeMin)){
            Part temp = new Outsourced(Main.idpartcount, writeName, writeCost, writeInv, writeMin, writeMax, writeCNT);
            submissionPart(temp, event);
        }
        else if(IH && (writeInv <= writeMax) && (writeInv >= writeMin)){
            int machineID = Integer.parseInt(writeCNT);
            InHouse temp = new InHouse(Main.idpartcount, writeName, writeCost, writeInv, writeMin, writeMax, machineID);
            submissionPart(temp, event);
        }
        else if ((writeInv > writeMax) || (writeInv < writeMin)){
            errorMessage.setText("Inventory must be between Min and Max");
        }

    }

    @FXML
    public void initialize() {

        rbInHouse.setSelected(true);

        //Bindings and Listeners

        switchLabel.textProperty().bind(new When(rbInHouse.selectedProperty()).then(machineId).otherwise(companyName));
        rbInHouse.selectedProperty().addListener((obs, wasPreviouslySelected, isSelected) -> {
            if (isSelected) {
                companyNameMachineIdText.textProperty().setValue(machineIDText);
            } else {
                companyNameMachineIdText.textProperty().setValue(compNameText);
            }
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

    //Function Reuses

    public void submissionPart(Part temp, ActionEvent event) throws IOException {
        inventory.addPart(temp);
        System.out.println("Item Added");
        changeToMain(event);
    }
}
