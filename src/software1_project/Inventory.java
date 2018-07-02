/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software1_project;

import java.util.ArrayList;

/**
 *
 * @author Cole
 */
public class Inventory {
    
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Part> parts = new ArrayList<>();
    
    public static int partCount = 1;
    public static int productCount = 1;
    
    
    //Used to help search array list to populate modPart Screen
    public static int searchPartID = 0;
    public static int searchProductID = 0;
    
    
    public static void setSearchPartID(int i) {
        searchPartID = i;
    }
    
    public static void setSearchProductID(int i) {
        searchProductID = i;
    }
    
    
    //Methods for altering parts array list
    public void addPart(Part mPart) {
        parts.add(mPart);
    }
    
    public boolean deletePart(int i) {
        
        boolean success;
        parts.remove(i);
        
        if (parts.get(i) != null) {
            success = false;
        } else {
            success = true;
        }
        
        return success; 
    }
    
    public void updatePart(int i, Part p) {
        parts.set(i, p);
    }
    
    public Part lookupPart(int i) {
        Part p = parts.get(i);
        return p;
    }
    
    public Part getSinglePart(int index) {
        return parts.get(index);
    }
    
    public int getPartListLength() {
        return parts.size();
    }
    
    public ArrayList<Part> getParts() {
        return parts;
    }
    
   
    //Methods for altering products array list
    
    public void addProduct(Product mProduct) {
        products.add(mProduct);
    }
    
    public boolean deleteProduct(int i) {
        
        boolean success;
        products.remove(i);
        
        if (products.get(i) != null) {
            success = false;
        } else {
            success = true;
        }
        
        return success; 
    }
    
    public void updateProduct(int i, Product p) {
        products.set(i, p);
    }
    
    public Product lookupProduct(int i) {
        Product p = products.get(i);
        return p;
    }
    
    public Product getSingleProduct(int index) {
        return products.get(index);
    }
    
    public int getProductListLength() {
        return products.size();
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }
    
}
