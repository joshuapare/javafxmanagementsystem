package sample;


import javafx.beans.binding.When;
import javafx.beans.value.ObservableValue;
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

public class ModifyPartController {

    String machineId = "Machine ID";
    String companyName = "Company Name";
    int idx;
    //Add Part

    @FXML
    private TextField id;
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

    public void setFields(Part selectedPart){
        
        idx = inventory.getAllParts().indexOf(selectedPart);
        id.setText(Integer.toString(selectedPart.getId()));
        name.setText(selectedPart.getName());
        inv.setText(Integer.toString(selectedPart.getStock()));
        cost.setText(Double.toString(selectedPart.getPrice()));
        max.setText(Integer.toString(selectedPart.getMax()));
        min.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart.getClass() == Outsourced.class){
            rbOutsourced.setSelected(true);
            String tempval = ((Outsourced) selectedPart).getCompanyName();
            companyNameMachineIdText.setText(tempval);
        } else if (selectedPart.getClass() == InHouse.class){
            rbInHouse.setSelected(true);
            companyNameMachineIdText.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

    }


    @FXML
    protected void handleUpdatePartAction(ActionEvent event) throws IOException {
        String writeName = name.getText();
        Integer writeInv = Integer.parseInt(inv.getText());
        Double writeCost = Double.parseDouble(cost.getText());
        Integer writeMax = Integer.parseInt(max.getText());
        Integer writeMin = Integer.parseInt(min.getText());
        String writeCNT = companyNameMachineIdText.getText();
        Boolean OS = rbOutsourced.isSelected();
        Boolean IH = rbInHouse.isSelected();



        if(OS){
            Part temp = new Outsourced(Main.idpartcount, writeName, writeCost, writeInv, writeMin, writeMax, writeCNT);
            inventory.updatePart(idx, temp);
        }
        else if(IH){
            Integer machineID = Integer.parseInt(writeCNT);
            InHouse temp = new InHouse(Main.idpartcount, writeName, writeCost, writeInv, writeMin, writeMax, machineID);
            inventory.updatePart(idx, temp);
        }
        System.out.println("Item Updated");
        changeToMain(event);

    }

    @FXML
    public void initialize() {

        //Bindings

        switchLabel.textProperty().bind((ObservableValue<? extends String>) new When(rbInHouse.selectedProperty()).then(machineId).otherwise(companyName));
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
}
