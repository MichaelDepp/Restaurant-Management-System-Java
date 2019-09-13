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
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Cart {

    private ArrayList<String> name = new ArrayList<>(); //setting the arraylist as private
    private ArrayList<Integer> qty = new ArrayList<>();
    private ArrayList<Double> price = new ArrayList<>();
    private ArrayList<Double> profit = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public Cart(ArrayList<Order> fcart, ArrayList<Order> bcart, ArrayList<Order> scart) { //arraylist items being passed

        ArrayList<String> iname = new ArrayList<>();
        ArrayList<Integer> iqty = new ArrayList<>();
        ArrayList<Double> iprice = new ArrayList<>();
        ArrayList<Double> iprofit = new ArrayList<>();

        for (int i = 0; i < fcart.size(); i++) { //each datas inside the arraylist is being separated into another arraylist of the same data type
            iname.addAll(fcart.get(i).getOName());
            iqty.addAll(fcart.get(i).getQuantity());
            iprice.addAll(fcart.get(i).getPrice());
            iprofit.addAll(fcart.get(i).getProfit());
        }

        for (int i = 0; i < bcart.size(); i++) {
            iname.addAll(bcart.get(i).getOName());
            iqty.addAll(bcart.get(i).getQuantity());
            iprice.addAll(bcart.get(i).getPrice());
            iprofit.addAll(bcart.get(i).getProfit());
        }

        for (int i = 0; i < scart.size(); i++) {
            iname.addAll(scart.get(i).getOName());
            iqty.addAll(scart.get(i).getQuantity());
            iprice.addAll(scart.get(i).getPrice());
            iprofit.addAll(scart.get(i).getProfit());
        }

        this.name = iname;
        this.qty = iqty;
        this.price = iprice;
        this.profit = iprofit;

    }

    public Cart() {

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

    public ArrayList<Double> getProfit() {
        return profit;
    }

    double getSubtotal() { //calculate subtotal

        double subtotal = 0.0;
        for (int i = 0; i < name.size(); i++) {
            double amt;
            amt = price.get(i) * qty.get(i);
            subtotal = subtotal + amt;
        }

        return (subtotal);
    }

    double getGross() { //calculate gross

        double gross = 0.0;
        for (int i = 0; i < name.size(); i++) {
            double grs;
            grs = profit.get(i) * qty.get(i);
            gross = gross + grs;
        }

        return (gross);
    }

    public String getTime() { //get the current time

        String jam;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date datey = new Date();
        jam = formatter.format(datey);

        return (jam);
    }

    public String getDate() { //get the today's time'

        String hari;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        hari = formatter.format(today);

        return (hari);
    }

    public Object displayCart(String id, String staff) { 

        DecimalFormat df = new DecimalFormat("#.##"); //this is to format to decimal value of the price
        String orderTime = getTime(); //getting time
        String orderDate = getDate(); //getting date
        System.out.println("Order ID: " + id + "                         " + orderTime);
        System.out.println("Staff Name: " + staff + "                    " + orderDate);
        int count = 1;
        System.out.printf("%2s %20s %5s %10s", "No", "NAME", "QTY", "PRICE"); //formatting of the items in proper order
        System.out.println();
        for (int i = 0; i < name.size(); i++) { //All the items inside the cart will be looped and displayed
            System.out.println("-------------------------------------------------");
            System.out.format("%2d %20s %5s %10s", count, name.get(i), qty.get(i), df.format((price.get(i) * qty.get(i))));
            System.out.println();
            count++;
        }
        double stotal = getSubtotal(); //calling getSubtotal funtion
        double taxtotal = (getSubtotal() * 0.06); //adding the tax to subtotal
        double ftotal = stotal + taxtotal; //Final total is the sum of subtotal and taxttotal
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nSUBTOTAL                       " + df.format(stotal));
        System.out.println("GOVT TAX(%6)                   " + df.format(taxtotal));
        System.out.println("Total                          " + df.format(ftotal));
        //all the items is now being passed to create an object dummy for the class Receipt
        Receipt dummy = new Receipt(id, staff, orderTime, orderDate, name, qty, price, getGross(), stotal, taxtotal, ftotal); 

        return (dummy); //the object dummy is being return to the main

    }

    public double payment() { //Paying

        double cashin, balance;
        System.out.println("Cash In :"); 
        cashin = input.nextDouble(); //get money from user
        while (cashin < (getSubtotal() + (getSubtotal() * 0.06))) { //this will loop until the user enter enought cash
            System.out.println("Not enough cash. Enter again!");
            System.out.println("Cash In :");
            cashin = input.nextDouble();
        }
        balance = cashin - (getSubtotal() + (getSubtotal() * 0.06)); //will display the balance that should be return to the customer

        return (balance); //returnning the balance
    }

    public int confirmOrder(String pay) { //passing the option that user chose to pay or not

        DecimalFormat df = new DecimalFormat("#.##"); 
        int s;
        if ("y".equalsIgnoreCase(pay)) { //if he choose yes to pay, this happen

            System.out.println("Balance : " + df.format(payment())); //The program will display the balance 
            s = 1; //if s = 1 it indicate the transaction is success

        } else {
            name.clear(); //else all the item in cart will be cleared 
            qty.clear();
            price.clear();
            profit.clear();
            s = 0; //s will be set to 0, so the entire order is not counted. This will also wont make the Order ID to increase

        }

        return (s); //returning s which indicates the success of the order
    }

}
