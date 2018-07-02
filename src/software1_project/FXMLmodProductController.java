/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Cole
 */
public class FXMLmodProductController implements Initializable {

    @FXML TextField productIDText, productNameText, productInvText, productPriceText, productMinText, productMaxText, searchBarText;
    @FXML private TableView<Part> partTable;
    
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    
    @FXML private TableView<Part> associatedPartTable;
    
    @FXML private TableColumn<Part, Integer> associatedPartIDColumn;
    @FXML private TableColumn<Part, String> associatedPartNameColumn;
    @FXML private TableColumn<Part, Integer> associatedPartInvColumn;
    @FXML private TableColumn<Part, Double> associatedPartPriceColumn;
    
    ArrayList<Part> associatedPartList = new ArrayList<>();
    
    
    
    @FXML private void onPartDelBtnClick(ActionEvent event) throws IOException {
        if(associatedPartTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = associatedPartTable.getSelectionModel().getSelectedItem();
            int selectedID = selectedPart.getPartID();

            for(int i = 0; i < associatedPartList.size(); i++) {
                if(selectedID == associatedPartList.get(i).getPartID()) {
                    associatedPartList.remove(associatedPartList.get(i));
                }
            }

            associatedPartTable.setItems(getAssociatedParts());
            associatedPartTable.refresh();
                
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Part Selected");
            alert.showAndWait();
        }
    }
    
    @FXML private void onPartSearchBtnClick() {
        String search = searchBarText.getText();
        
        if ( !(search.equals("") )) {
            partTable.setItems(searchParts(search));
            partTable.refresh();
        } else {
            partTable.setItems(getAllParts());
            partTable.refresh();
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i < Inventory.products.size(); i++) {
            if(Inventory.searchProductID == Inventory.products.get(i).getProductID()) {
                Product selectedProduct = Inventory.products.get(i);
                
                productIDText.setText(Integer.toString(selectedProduct.getProductID()));
                productNameText.setText(selectedProduct.getName());
                productInvText.setText(Integer.toString(selectedProduct.getInStock()));
                productPriceText.setText(Double.toString(selectedProduct.getPrice()));
                productMinText.setText(Integer.toString(selectedProduct.getMin()));
                productMaxText.setText(Integer.toString(selectedProduct.getMax()));
                associatedPartList = selectedProduct.getAssociatedParts();
                
                
        //Part Objects
        partIDColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().partIDProperty()).asObject();
            return returnValue;
        });
        
        partNameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, String> param) -> {
            ObservableValue<String> returnValue;
            returnValue = (param.getValue().partNameProperty());
            return returnValue;
        });
        
        partInvColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().partInvLevelProperty()).asObject();
            return returnValue;
        });
        
        partPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, Double> param) -> {
            ObservableValue<Double> returnValue;
            returnValue = (param.getValue().partPPUProperty()).asObject();
            return returnValue;
        });
        
        //Associated Part Objects
        associatedPartIDColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().partIDProperty()).asObject();
            return returnValue;
        });
        
        associatedPartNameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, String> param) -> {
            ObservableValue<String> returnValue;
            returnValue = (param.getValue().partNameProperty());
            return returnValue;
        });
        
        associatedPartInvColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().partInvLevelProperty()).asObject();
            return returnValue;
        });
        
        associatedPartPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Part, Double> param) -> {
            ObservableValue<Double> returnValue;
            returnValue = (param.getValue().partPPUProperty()).asObject();
            return returnValue;
        });
        
        partTable.setItems(getAllParts());
        associatedPartTable.setItems(getAssociatedParts());
       }
     }
    }
    
    public ObservableList<Part> getAllParts() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        
        for(int i = 0; i < Inventory.parts.size(); i++) {
            parts.add(Inventory.parts.get(i));
        }
        
        return parts;
    }
    
    public ObservableList<Part> getAssociatedParts() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        
        for(int i = 0; i < associatedPartList.size(); i++) {
            parts.add(associatedPartList.get(i));
        }
        return parts;
    }
    
    @FXML private void onPartAddBtnClick(ActionEvent event) throws IOException {
        if(partTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partTable.getSelectionModel().getSelectedItem();
            int selectedID = selectedPart.getPartID();

            for(int i = 0; i < Inventory.parts.size(); i++) {
                if(selectedID == Inventory.parts.get(i).getPartID()) {
                    associatedPartList.add(Inventory.parts.get(i));
                }
            }
           
            associatedPartTable.setItems(getAssociatedParts());
            associatedPartTable.refresh();
                
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Part Selected");
            alert.showAndWait();
        }
    }
    
    //Button Handler to save Product to Inventory
    @FXML private void productSaveBtnHandler(ActionEvent event) throws IOException {
        int productID = Integer.parseInt(productIDText.getText());
        String productName = "";
        int productInvLevel = -1;
        Double productPrice = -1.0;
        int productMin = -1;
        int productMax = -1;
        ArrayList<Part> parts = new ArrayList<>();
        
        
        //Create alert template for any errors in data type entry
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        
        productName = productNameText.getText();
        
        try {
            String productInvLevelString = productInvText.getText();
            productInvLevel = Integer.parseInt(productInvLevelString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for Inventory Level Field. Expected Integer." + e);
            alert.showAndWait();
        }
        
        try {
            String productPriceString = productPriceText.getText();
            productPrice = Double.parseDouble(productPriceString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for Price Field. Expected Double/Float." + e);
            alert.showAndWait();
        }
        
        try {
            String productMinString = productMinText.getText();
            productMin = Integer.parseInt(productMinString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for min field. Expected Integer." + e);
            alert.showAndWait();
        }
        
        try {
            String productMaxString = productMaxText.getText();
            productMax = Integer.parseInt(productMaxString);
        } catch (NumberFormatException e) {
            alert.setContentText("Improper Data Type used for min field. Expected Integer." + e);
            alert.showAndWait();
        }
        
        for(int i = 0; i < associatedPartList.size(); i++) {
            parts.add(associatedPartList.get(i));
        }
        
        if(productName != "" && productPrice != -1.0 && productInvLevel != -1 && productMin != -1 && productMax != 0) {
            
            int index = 0;
            
            for (int i = 0; i < Inventory.products.size(); i++) {
                if(productID == Inventory.products.get(i).getProductID()) {
                    index = i;
                }
            }
            
            Product newProduct = new Product(productID, productName, productPrice, productInvLevel, productMin, productMax);
            
            //Need to get which items match the PartID in the column, add those from the static Inventory class
            
            List<Integer> columnData = new ArrayList<>();
                 
            for(Part part : associatedPartTable.getItems()) {
                columnData.add(associatedPartIDColumn.getCellObservableValue(part).getValue());
            }
            
            for(int i = 0; i < columnData.size(); i++) {
                for(int f = 0; f < Inventory.parts.size(); f++) {
                    if(columnData.get(i) == Inventory.parts.get(f).getPartID()) {
                        newProduct.addAssociatedPart(Inventory.parts.get(f));
                    }
                }
            }
            
            
            Inventory.products.set(index, newProduct);
            
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
    
    @FXML private void productCancelBtnHandler(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = (Parent)fxmlLoader.load();
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            
            stage.show();
    }
    
    public ObservableList<Part> searchParts(String s) {
        ObservableList<Part> searchedParts = FXCollections.observableArrayList();
        
        for(int i = 0; i < Inventory.parts.size(); i++) {
            if(String.valueOf(Inventory.parts.get(i).getPartID()).contains(s)) {
                searchedParts.add(Inventory.parts.get(i));
            }
        }
        
        return searchedParts;
    }
    
    
}
