/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Cole
 */
public class FXMLMainController implements Initializable {
    
    
    @FXML private TableView<Part> partTable;
    
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInvLevelColumn;
    @FXML private TableColumn<Part, Double> partPPUColumn;
    
    @FXML private TableView<Product> productTable;
    
    @FXML private TableColumn<Product, Integer> productIDColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInvLevelColumn;
    @FXML private TableColumn<Product, Double> productPPUColumn; 
    
    @FXML private TextField partSearchBar;
    @FXML private TextField productSearchBar;
    
    
    /*
        Button Handlers for the Parts section
    
    */
    
    //Search Button Handler
    //This search will be performed on the PartID column of the table
    @FXML private void onPartSearchBtnClick() {
        String search = partSearchBar.getText();
        
        if ( !(search.equals("") )) {
            partTable.setItems(searchParts(search));
            partTable.refresh();
        } else {
            partTable.setItems(getParts());
            partTable.refresh();
        }
    }
    
    @FXML private void onProductSearchBtnClick() {
        String search = productSearchBar.getText();
        
        if ( !(search.equals("") )) {
            productTable.setItems(searchProducts(search));
            productTable.refresh();
        } else {
            productTable.setItems(getProducts());
            productTable.refresh();
        }
    }
    
    
    //Modify Button handler
    @FXML private void onPartModBtnClick(ActionEvent event) throws IOException {
        
        if(partTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partTable.getSelectionModel().getSelectedItem();
            int selectedID = selectedPart.getPartID();
            
            Inventory.setSearchPartID(selectedID);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("modifyPartScreen.fxml"));

            Parent root = (Parent)fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            
            stage.show();
    } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Part Selected");
            alert.showAndWait(); 
            
        }
    }
    
    
    //Delete button handler
    @FXML private void onPartDelBtnClick(ActionEvent event) throws IOException {
        if(partTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partTable.getSelectionModel().getSelectedItem();
            int selectedID = selectedPart.getPartID();

            for(int i = 0; i < Inventory.parts.size(); i++) {
                if(selectedID == Inventory.parts.get(i).getPartID()) {
                    Inventory.parts.remove(Inventory.parts.get(i));
                }
            }

            partTable.setItems(getParts());
            partTable.refresh();
                
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Part Selected");
            alert.showAndWait();
        }
    }
    
    
   
    
   //Method for switching to the addPart Screen, assigned to partAddBtn in FXML
    @FXML private void onPartAddBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPartScreen.fxml"));
        Parent addPartParent = (Parent)fxmlLoader.load();
        
        
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);

        addPartStage.show();
    }
    
    /*
    
        Button Handlers for the Products Section
    
    */
    
    
    @FXML private void onProductAddBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addProductScreen.fxml"));
        Parent addPartParent = (Parent)fxmlLoader.load();
        
        
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);

        addPartStage.show();
        
    }
    
    //Modify Button handler
    @FXML private void onProductModBtnClick(ActionEvent event) throws IOException {
        
        if(productTable.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            int selectedID = selectedProduct.getProductID();
            
            Inventory.setSearchProductID(selectedID);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("modifyProductScreen.fxml"));

            Parent root = (Parent)fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            
            stage.show();
    } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Part Selected");
            alert.showAndWait(); 
            
        }
    }
    
    @FXML private void onProductDelBtnClick(ActionEvent event) throws IOException {
        if(productTable.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            int selectedID = selectedProduct.getProductID();

            for(int i = 0; i < Inventory.products.size(); i++) {
                if(selectedID == Inventory.products.get(i).getProductID()) {
                    Inventory.products.remove(Inventory.products.get(i));
                }
            }

            productTable.setItems(getProducts());
            productTable.refresh();
                
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("No Part Selected");
            alert.showAndWait();
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        //Link Columns to the correct properties in the data model. 
        
        //Part Objects
        partIDColumn.setCellValueFactory((CellDataFeatures<Part, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().partIDProperty()).asObject();
            return returnValue;
        });
        
        partNameColumn.setCellValueFactory((CellDataFeatures<Part, String> param) -> {
            ObservableValue<String> returnValue;
            returnValue = (param.getValue().partNameProperty());
            return returnValue;
        });
        
        partInvLevelColumn.setCellValueFactory((CellDataFeatures<Part, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().partInvLevelProperty()).asObject();
            return returnValue;
        });
        
        partPPUColumn.setCellValueFactory((CellDataFeatures<Part, Double> param) -> {
            ObservableValue<Double> returnValue;
            returnValue = (param.getValue().partPPUProperty()).asObject();
            return returnValue;
        });
        
        //Product Objects
        productIDColumn.setCellValueFactory((CellDataFeatures<Product, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().productIDProperty()).asObject();
            return returnValue;
        });
        
        productNameColumn.setCellValueFactory((CellDataFeatures<Product, String> param) -> {
            ObservableValue<String> returnValue;
            returnValue = (param.getValue().productNameProperty());
            return returnValue;
        });
        
        productInvLevelColumn.setCellValueFactory((CellDataFeatures<Product, Integer> param) -> {
            ObservableValue<Integer> returnValue;
            returnValue = (param.getValue().productInvLevelProperty()).asObject();
            return returnValue;
        });
        
        productPPUColumn.setCellValueFactory((CellDataFeatures<Product, Double> param) -> {
            ObservableValue<Double> returnValue;
            returnValue = (param.getValue().productPPUProperty()).asObject();
            return returnValue;
        });
        
        partTable.setItems(getParts());
       
        productTable.setItems(getProducts());
        
    }    
    
    public ObservableList<Part> getParts() {
        
        ObservableList<Part> parts = FXCollections.observableArrayList();
        
        for(int i = 0; i < Inventory.parts.size(); i++) {
            parts.add(Inventory.parts.get(i));
        }
        
        return parts;
        
    }
    
    public ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        
        for(int i = 0; i < Inventory.products.size(); i++) {
            products.add(Inventory.products.get(i));
        }
        
        return products;
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
    
    public ObservableList<Product> searchProducts(String s) {
        ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
        
        for(int i = 0; i < Inventory.products.size(); i++) {
            if(String.valueOf(Inventory.products.get(i).getProductID()).contains(s)) {
                searchedProducts.add(Inventory.products.get(i));
            }
        }
        
        return searchedProducts;
    }
    
    public void exit() {
        System.exit(0);
    }
    
   
    
    
    
}
    
