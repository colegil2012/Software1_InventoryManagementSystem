/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Cole
 */
public class InHouse extends Part {
    
    protected SimpleIntegerProperty machineID;
    
    
    public InHouse(int ID, String mName, double mPrice, int mInStock, int mMin, int mMax, int mMachineID) {
        super.setPartID(ID);
        super.setName(mName);
        super.setPrice(mPrice);
        super.setInStock(mInStock);
        super.setMin(mMin);
        super.setMax(mMax);
        this.machineID = new SimpleIntegerProperty(mMachineID);
    }
    
    public InHouse(String mName, double mPrice, int mInStock, int mMin, int mMax, int mMachineID) {
        super.setPartID(Inventory.partCount);
        super.setName(mName);
        super.setPrice(mPrice);
        super.setInStock(mInStock);
        super.setMin(mMin);
        super.setMax(mMax);
        this.machineID = new SimpleIntegerProperty(mMachineID);
        Inventory.partCount ++;
    }
    
    public InHouse() {
        super.setPartID(0);
        super.setName("");
        super.setPrice(0.00);
        super.setInStock(0);
        super.setMin(0);
        super.setMax(0);
        this.machineID = new SimpleIntegerProperty(0);
    }
    
    
    public void setMachineID(int ID) {
        this.machineID = new SimpleIntegerProperty(ID);
    }
    
    public int getMachineID() {
        return machineID.get();
    }
}
