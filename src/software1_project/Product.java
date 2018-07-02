/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Cole
 */
public class Product {
    
    private ArrayList<Part> associatedParts = new ArrayList<>();
    private SimpleIntegerProperty productID;
    private SimpleStringProperty productName;
    private SimpleDoubleProperty productPPU;
    private SimpleIntegerProperty productInvLevel;
    private SimpleIntegerProperty productMin;
    private SimpleIntegerProperty productMax;
    
    public Product(int ID, String name, Double price, int inStock, int min, int max) {
        
        this.setProductID(ID);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setMin(min);
        this.setMax(max);
        
    }
    
    public Product(String name, Double price, int inStock, int min, int max) {
        this.setProductID(Inventory.productCount);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setMin(min);
        this.setMax(max);
        
        Inventory.productCount++;
    }
    
    //Methods to return Data Model Property Items for TableView Population
    public SimpleIntegerProperty productIDProperty() {
        return productID;
    }
    
    public SimpleStringProperty productNameProperty() {
        return productName;
    }
    
    public SimpleDoubleProperty productPPUProperty() {
        return productPPU;
    }
    
    public SimpleIntegerProperty productInvLevelProperty() {
        return productInvLevel;
    }
    
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public ArrayList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public boolean removeAssociatedPart(int index) {
        boolean success;
        associatedParts.remove(index);
        
        if (associatedParts.get(index) != null) {
            success = false;
        } else {
            success = true;
        }
        
        return success;    
    }
    
    public void clearAssociatedPart() {
        associatedParts.clear();
    }
    
    public Part lookupAssociatedPart(int index) {
        Part selectedPart;
        
        selectedPart = associatedParts.get(index);
        return selectedPart;
    }
    
    public void setProductID(int ID) {
        this.productID = new SimpleIntegerProperty(ID);
    } 
    
    public int getProductID() {
        return productID.get();
    }
    
    public void setName(String mName) {
        this.productName = new SimpleStringProperty(mName);
    }
    
    public String getName() {
        return productName.get();
    }
    
    public void setPrice(Double mPrice) {
        this.productPPU = new SimpleDoubleProperty(mPrice);
    } 
    
    public double getPrice() {
        return productPPU.get();
    }
    
    public void setInStock(int mInStock) {
        this.productInvLevel = new SimpleIntegerProperty(mInStock);
    }
    
    public int getInStock() {
        return productInvLevel.get();
    }
    
    public void setMin(int mMin) {
        this.productMin = new SimpleIntegerProperty(mMin);
    }
    
    public int getMin() {
        return productMin.get();
    }
    
    public void setMax(int mMax) {
        this.productMax = new SimpleIntegerProperty(mMax);
    }
    
    public int getMax() {
        return productMax.get();
    }
}
