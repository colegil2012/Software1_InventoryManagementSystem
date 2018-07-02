/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Cole
 */
public abstract class Part {
    
    private SimpleIntegerProperty partID;
    private SimpleStringProperty partName;
    private SimpleDoubleProperty partPPU;
    private SimpleIntegerProperty partInvLevel;
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;
    
    
    //Methods to return Data Model Property Items for TableView Population
    public SimpleIntegerProperty partIDProperty() {
        return partID;
    }
    
    public SimpleStringProperty partNameProperty() {
        return partName;
    }
    
    public SimpleDoubleProperty partPPUProperty() {
        return partPPU;
    }
    
    public SimpleIntegerProperty partInvLevelProperty() {
        return partInvLevel;
    }
    
    
    //Basic Setter/Getter Methods
    public void setPartID(int id) {
        this.partID = new SimpleIntegerProperty(id);
    }
    
    public int getPartID() {
        return partID.get();
    }
    
    public void setName(String mName) {
        this.partName = new SimpleStringProperty(mName);
    } 
    
    public String getName() {
        return partName.get();
    }
    
    public void setPrice(double mPrice) {
        this.partPPU = new SimpleDoubleProperty(mPrice);
    }
    
    public Double getPrice() {
        return partPPU.get();
    }
    
    public void setInStock(int stock) {
        this.partInvLevel = new SimpleIntegerProperty(stock);
    }
    
    public int getInStock() {
        return partInvLevel.get();
    }
    
    public void setMin(int mMin) {
        this.min = new SimpleIntegerProperty(mMin);
    }
    
    public int getMin() {
        return min.get();
    }
    
    public void setMax(int mMax) {
        this.max = new SimpleIntegerProperty(mMax);
    }
    
    public int getMax() {
        return max.get();
    }
    
    
}
