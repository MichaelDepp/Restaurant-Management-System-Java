/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michael Depp
 */
public class Menu {

    private ArrayList<Item> item = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    

    public Menu(ArrayList<Item> item) {
        this.item = item;
    }

    Menu() {
        
    }

    public ArrayList<Item> getItem()
    {
        return item;
    }
    
    public ArrayList<Item> editItem() {
        
        String chooseP = "y";
        int index;
        while (!"n".equalsIgnoreCase(chooseP)) { //Will loop until user enter n
            int found=0;
            double nprice, nprofit;
            System.out.println("Enter the item ID: "); //Prompt user to enter the item Id that they want to edit
            String cusId = input.next();
            for (int i = 0; i < item.size(); i++) { //Looping through the arraylist to find the Id that user entered

                if (cusId.equalsIgnoreCase(item.get(i).getId())) { //If found the item
                    index = i;
                    System.out.println(" "); 
                    System.out.println("You have selected " + item.get(index).getName()); //Displaying the item name
                    System.out.println("Current Price: " + item.get(index).getPrice() + "\nCurrent Profit:" + item.get(index).getProfit()); //Displaying price
                    System.out.println("Enter The New Price For This Item: ");
                    nprice = input.nextDouble(); //User will enter the price he wants
                    item.get(index).price = nprice; //It will be set into the arraylist
                    System.out.println("Enter The New Profit For This Item: "); //User will enter the profit he wants
                    nprofit = input.nextDouble();
                    item.get(index).profit = nprofit;  //It will be changed into the arraylist

                }
                else
                {
                    found++; //will add everytime the item didnt found
                }

            }
            
            if (found == item.size()) //if the found number and arraylist size same means the item didnt present in any of the list
            {
                System.out.println("Item didn't exist!");//so it will display item didnt exist
            }
            
            System.out.println("Do you want to edit price of another item from here ? (y/n)"); //Asking user whether he want to edit another item
            chooseP = input.next(); //if he didnt enter n the program will loop again

        }
        
        return (item); //returning the updated arraylist

    }
    
    public ArrayList<Order> chooseItem() { //Choosing Item function

        ArrayList<Order> ordertemp = new ArrayList<>(); //creating an arraylist based on Order class
        ArrayList<String> Cname = new ArrayList<>(); 
        ArrayList<Integer> Cqty = new ArrayList<>();
        ArrayList<Double> Cprice = new ArrayList<>();
        ArrayList<Double> Cprofit = new ArrayList<>();
        
        String chooseP = "y";
        int index;
        while (!"n".equalsIgnoreCase(chooseP)) { //will loop until chooseP have 'n' value
            int found=0;
            System.out.println("Enter the item ID: "); //User have to enter the item they want to buy
            String cusId = input.next();
            int qty;
            for (int i = 0; i < item.size(); i++) { //Once the user enter the ID it will loop through the arraylist to find the item

                if (cusId.equalsIgnoreCase(item.get(i).getId())) { //if the id matched
                    index = i;
                    System.out.println("How many " + item.get(index).getName() + " you want ? :");//The program will prompt use to enter quantity
                    qty = input.nextInt();
                    Cname.add(item.get(index).getName()); //Chosen item names will be added to cname arraylist
                    Cprice.add(item.get(index).getPrice()); //Chosen item price will be added to cname arraylist
                    Cprofit.add(item.get(index).getProfit()); //Chosen item quantity will be added to cname arraylist
                    Cqty.add(qty);

                }
                else
                {
                    found++;
                }

            }
            
            if (found == item.size())
            {
                System.out.println("Item didn't exist!"); //Error message if the item didnt exist
            }
            
            System.out.println("Do you want to add another item from here ? (y/n)"); //Prompt user whether he want to add another item
            chooseP = input.next(); //this chooseP variable will make the loop break if the user enter n

        }
        Order dummy = new Order(Cname, Cqty, Cprice, Cprofit); ///All the arraylist will be added to create an object for Order Class
        ordertemp.add(dummy); //The object will be added to the arraylist ordertemp.
        
        return (ordertemp); //ordertemp will be return to the main

    }
    
    public void displayMenu() { //Printing the Food type option

        System.out.println("Select from Options or Enter ‘B’ to go back.");
        System.out.println("1. Food");
        System.out.println("2. Beverage");
        System.out.println("3. Side");
        System.out.println("4. Cart");
        System.out.println("B. Go Back");
    }
    
    public void displayItem() { //Will display the items based on the arraylist that has been passed
        System.out.println("");
        int count = 1; //To count number of items
        System.out.printf("%2s %10s %15s %10s", "No", "ID", "NAME", "PRICE");
        System.out.println();
        for (int i = 0; i < item.size(); i++) { //Will loop and print item until it reaches the arraylist size
            System.out.format("%2d %10s %15s %10s", count, item.get(i).getId(), item.get(i).getName(), item.get(i).getPrice()); //formatting
            System.out.println();
            count++; //Will be added as there is more item
        }
    }

}
