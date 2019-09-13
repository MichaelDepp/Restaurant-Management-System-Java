/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmanagement;

/**
 *
 * @author Michael Depp
 */
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receipt {

    String orderId;
    String staff;
    String orderTime;
    String orderDate;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> qty = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
    Double profit;
    Double stotal;
    Double taxtotal;
    Double ftotal;
    Scanner input = new Scanner(System.in);

    public Receipt(String Oid, String staff, String orderTime, String orderDate, ArrayList<String> name, ArrayList<Integer> qty, ArrayList<Double> price, Double profit, Double stotal, Double taxtotal, Double ftotal) {

        this.orderId = Oid;
        this.staff = staff;
        this.orderTime = orderTime;
        this.orderDate = orderDate;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.profit = profit;
        this.stotal = stotal;
        this.taxtotal = taxtotal;
        this.ftotal = ftotal;

    }

    public Receipt() {

    }

    public String getOrderId() {
        return orderId;
    }

    public String getStaff() {
        return staff;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<Integer> getQuantity() {
        return qty;
    }

    public ArrayList<Double> getPrice() {
        return price;
    }

    public Double getProfit() {
        return profit;
    }

    public Double getSubtotal() {
        return stotal;
    }

    public Double getTaxtotal() {
        return taxtotal;
    }

    public Double getFtotal() {
        return ftotal;
    }

    public String timecalc() {

        String curr, status;
        int diff;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); //formatting the time by removing other infos like day month and year, cause we need time only
        Date datey = new Date(); //getting the time
        curr = formatter.format(datey); //the time being copied to curr
        Date date1 = null;
        try {
            date1 = formatter.parse(orderTime); //getting the time that passed during the order time
        } catch (ParseException ex) {
            Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex); //exception for parsing
        }
        Date date2 = null;
        try {
            date2 = formatter.parse(curr); //date 2 is the current time
        } catch (ParseException ex) {
            Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        long difference = date2.getTime() - date1.getTime(); //Order time and current time being minused
        diff = (int) (long) (difference / 1000); //the difference is set to seconds

        if (diff < 120) { //if the order time is less than 2 minutes / 120 seconds
            status = "Order Received"; //it will set to Order Received
        } else if (diff >= 120 && diff < 240) { //If it is more than 2 minits and less than 4 minits
            status = "Cooking"; //it will set to Cooking
        } else {
            status = "Delivered"; //If it is more than that it will set to Delivered
        }

        return (status); //returning the status
    }

    public void Order() {

        DecimalFormat df = new DecimalFormat("#.##"); //To format the double values into 2 digit place.
        System.out.println(" ");
        System.out.println("Order ID: " + orderId + "                         " + orderTime);
        System.out.println("Staff Name: " + staff + "                    " + orderDate);
        int count = 1;
        System.out.printf("%2s %20s %5s %10s", "No", "NAME", "QTY", "PRICE");
        System.out.println();
        for (int i = 0; i < name.size(); i++) { //It will loop through the arraylist and print all the items
            System.out.println("-------------------------------------------------");
            System.out.format("%2d %20s %5s %10s", count, (name.get(i)), (qty.get(i)), df.format((price.get(i)) * (qty.get(i))));
            System.out.println();
            count++;
        }
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nSUBTOTAL                       " + df.format(stotal));
        System.out.println("GOVT TAX(%6)                   " + df.format(taxtotal));
        System.out.println("Total                          " + df.format(ftotal));
        System.out.println("\n-------------------------------------------------");
        System.out.println("Order Status: " + timecalc()); //Calling funcion timecalc
        System.out.println(" ");

    }

}
