/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


/**
 *
 * @author Cole
 */
public class FXMLaddPartController { 
    
    
    @FXML TextField partIDText;
    @FXML TextField partNameText;
    @FXML TextField partInvText;
    @FXML TextField partPriceText;
    @FXML TextField partMinText;
    @FXML TextField partMaxText;
    @FXML TextField dynamicText;
    @FXML ToggleGroup partType;
    @FXML RadioButton InHouseRB;
    @FXML RadioButton OutsourcedRB;
    @FXML Label dynamicLabel;
    @FXML Button partSaveBtn;
    @FXML Button partCancelBtn;
        
    @FXML private void outsourcedDynLabelChange() {
        dynamicLabel.setText("Company Name:");
    }
    
    @FXML private void inHouseDynLabelChange() {
        dynamicLabel.setText("Machine ID:");
    }
    
    @FXML private void partSaveBtnHandler(ActionEvent event) throws IOException {
        
        String partName = "";
        int partInvLevel = -1;
        Double partPrice = -1.0;
        int partMin = -1;
        int partMax = -1;
        String companyName = "";
        int machineID = -1;
        
        //Create alert template for any errors in data type entry
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        
        //Set the selected Radio Button for future use
        RadioButton selected = (RadioButton) partType.getSelectedToggle();
        String toggleValue = selected.getText();
        
        partName = partNameText.getText();
        
        try {
            String partInvLevelString = partInvText.getText();
            partInvLevel = Integer.parseInt(partInvLevelString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for Inventory Level Field. Expected Integer." + e);
            alert.showAndWait();
        }
        
        try {
            String partPriceString = partPriceText.getText();
            partPrice = Double.parseDouble(partPriceString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for Price Field. Expected Double/Float." + e);
            alert.showAndWait();
        }
        
        try {
            String partMinString = partMinText.getText();
            partMin = Integer.parseInt(partMinString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for min field. Expected Integer." + e);
            alert.showAndWait();
        }
        
        try {
            String partMaxString = partMaxText.getText();
            partMax = Integer.parseInt(partMaxString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for min field. Expected Integer." + e);
            alert.showAndWait();
        }
        
        
        if(toggleValue.equals("InHouse")) {
            try {
                String machineIDString = dynamicText.getText();
                machineID = Integer.parseInt(machineIDString);
            } catch (NumberFormatException e) {
                alert.setContentText("Improper Data Type used for machine ID field. Expected Integer." + e);
                alert.showAndWait();
        }
        } else {
            companyName = dynamicText.getText();
        }
        
        
        if(toggleValue.equals("InHouse") && partName != "" && partPrice != -1.0 && partInvLevel != -1 && partMin != -1 && partMax != -1 && machineID != -1) {
            Part newPart = new InHouse(partName, partPrice, partInvLevel, partMin, partMax, machineID);
            Inventory.parts.add(newPart);
            
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            
        
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            
            stage.show();
            
        } else if (toggleValue.equals("Outsourced") && partName != "" && partPrice != -1.0 && partInvLevel != -1 && partMin != -1 && partMax != -1 && companyName != ""){
            Part newPart = new Outsourced(partName, partPrice, partInvLevel, partMin, partMax, companyName);
            Inventory.parts.add(newPart);
           
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = (Parent)fxmlLoader.load();
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            
            stage.show();
            
        } else {
            alert.setContentText("Not all fields were completed");
            alert.showAndWait();
        }
        
    }
    
    @FXML private void partCancelBtnHandler(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = (Parent)fxmlLoader.load();
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            
            stage.show();
    }
}    
    
