/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmanagement;

import java.util.ArrayList;

/**
 *
 * @author Michael Depp
 */
public class Order {
    
    private ArrayList<String> OName = new ArrayList<>();
    protected ArrayList<Integer> quantity = new ArrayList<>(); //setting protected not public cause being used in another class
    private ArrayList<Double> Oprice = new ArrayList<>();
    private ArrayList<Double> Oprofit = new ArrayList<>();
    

    public Order (ArrayList<String> OName, ArrayList<Integer> quantity, ArrayList<Double> Oprice, ArrayList<Double> Oprofit) {//double subtotal, double tax, double ftotal, String Otime) {

        this.OName = OName;
        this.quantity = quantity;
        this.Oprice = Oprice;
        this.Oprofit = Oprofit;
    }

    Order() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public ArrayList<String> getOName() {
        return OName;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }
    
    public ArrayList<Double> getPrice() {
        return Oprice;
    }
    
    public ArrayList<Double> getProfit() {
        return Oprofit;
    }

}
