/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Cole
 */
public class Outsourced extends Part{
    
    private SimpleStringProperty companyName;
    
    public Outsourced(int ID, String mName, double mPrice, int mInStock, int mMin, int mMax, String mCompanyName) {
        super.setPartID(ID);
        super.setName(mName);
        super.setPrice(mPrice);
        super.setInStock(mInStock);
        super.setMin(mMin);
        super.setMax(mMax);
        this.companyName = new SimpleStringProperty(mCompanyName);  
    }
    
    public Outsourced(String mName, double mPrice, int mInStock, int mMin, int mMax, String mCompanyName) {
        super.setPartID(Inventory.partCount);
        super.setName(mName);
        super.setPrice(mPrice);
        super.setInStock(mInStock);
        super.setMin(mMin);
        super.setMax(mMax);
        this.companyName = new SimpleStringProperty(mCompanyName); 
        Inventory.partCount ++;
    }
    
    public Outsourced() {
        super.setPartID(0);
        super.setName("");
        super.setPrice(0.00);
        super.setInStock(0);
        super.setMin(0);
        super.setMax(0);
        this.companyName = new SimpleStringProperty("");
        
    }
    
    public void setCompanyName(String name) {
        this.companyName = new SimpleStringProperty(name);
    }
    
    public String getCompanyName() {
        return companyName.get();
    }
    
}
