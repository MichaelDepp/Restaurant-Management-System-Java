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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RestaurantManagement {

    /**
     * @param args the command line arguments
     */
    ArrayList<Item> fmenu = new ArrayList<>(); //Creating an arraylist based on the Item Class
    ArrayList<Item> bmenu = new ArrayList<>();
    ArrayList<Item> smenu = new ArrayList<>();
    ArrayList<Receipt> allreceipt = new ArrayList<>(); //Creating an arraylist based on Receipt Class
    Scanner input = new Scanner(System.in);
    int count = 101; //Initializing count as 101. So the order number will start from 101

    public static void main(String[] args) throws IOException {

        clearScreen(); //To clear the cmd
        RestaurantManagement ass1 = new RestaurantManagement(); //a new instance for this main
        ass1.start(); //Calling function start

    }

    public void start() throws IOException {

        initData(); //Calling initData function to load the menu items to the program
        String staff = login(); //Calling login function, staff name will be initialize here
        String option = "";
        option = menu(); //Calling menu function, option will gets it value from the return value
        while (!option.trim().equalsIgnoreCase("Q")) { //This will loop until the user enter q for quit
            switch (option) {
                case "1":
                    clearScreen(); //clearing the cmd
                    PlaceOrder(staff); //calling PlaceOrder function and passing the staff variable
                    break;
                case "2":
                    clearScreen();
                    OrderStatus();
                    break;
                case "3":
                    clearScreen();
                    EditPrice();
                    break;
                case "4":
                    clearScreen();
                    SalesSummary();
                    break;
                default:
                    System.out.println("Invalid Input Son!");
                    break;
            }
            option = menu();
        }

        System.out.println("");
        System.out.println("Thanks for Using our Restaurant Management System!");
        System.out.println("");
    }

    public void PlaceOrder(String staff) {

        String choose1 = "";
        String OrderID;
        int success = 0;
        while (!choose1.trim().equalsIgnoreCase("M")) { //Will loop until user enter M
            System.out.println("******************* Placing New Order ******************");
            System.out.println("Select from Options or Enter ‘M’ for Main Menu");
            System.out.println("1. Dine In");
            System.out.println("2. Take Away");
            System.out.println("3. Grab Food/Food Panda");
            System.out.println("M. Go to main Menu");
            choose1 = input.next(); //Getting choice input
            switch (choose1) {

                case "1":
                    clearScreen(); //Clearing cmd
                    OrderID = "DI" + count; //Setting the order ID from the count and adding the DI word to indicate its a DINE IN Order
                    success = ordering(OrderID, choose1, staff); //Calling ordering function and passing the required values
                    break;
                case "2":
                    clearScreen();
                    OrderID = "TA" + count; //Setting the order ID from the count and adding the TA word to indicate its a TAKE AWAY Order
                    success = ordering(OrderID, choose1, staff);
                    break;
                case "3":
                    clearScreen();
                    OrderID = "GF" + count; //Setting the order ID from the count and adding the DI word to indicate its a GRAB FOOD/FOOD PANDA Order
                    success = ordering(OrderID, choose1, staff); //
                    break;
                case "M":
                    break;
            }
            if (success == 1) { //if the return s value which is the success is equal to 1, it indicates the success of the previous order
                count++; //So the count will be plus 1 so the next orderId will have a new number.
            }
        }
    }

    public void OrderStatus() {

        String choose2 = "";
        while (!choose2.trim().equalsIgnoreCase("M")) { //Will loop until user enter M
            System.out.println("******************* Order Status ******************");
            System.out.println("Select from Options or Enter ‘M’ for Main Menu");
            System.out.println("1. Check Order Status");
            System.out.println("M. Go to main Menu");
            choose2 = input.next();
            switch (choose2) {

                case "1":
                    String statusid;
                    int found = 404;
                    System.out.println("Enter Your Order ID: ");
                    statusid = input.next(); //The user have to enter his Order ID

                    for (int i = 0; i < allreceipt.size(); i++) { //This will loop the arraylist allreceipt which store all the orders to find the order ID

                        if (statusid.equalsIgnoreCase(allreceipt.get(i).getOrderId())) { 
                            found = i; //if it found the it will copy the index of the arraylist that the order Id is being stored
                        }
                    }

                    if (found != 404) { //if the ORDER ID found

                        //A new object will be created for the class Receipt and all the infos from the arraylist allreceipt will be passed into the object.
                        Receipt os = new Receipt((allreceipt.get(found).getOrderId()), (allreceipt.get(found).getStaff()), (allreceipt.get(found).getOrderTime()), (allreceipt.get(found).getOrderDate()), (allreceipt.get(found).getName()), (allreceipt.get(found).getQuantity()), (allreceipt.get(found).getPrice()), (allreceipt.get(found).getProfit()), (allreceipt.get(found).getSubtotal()), (allreceipt.get(found).getTaxtotal()), (allreceipt.get(found).getFtotal()));
                        os.Order(); //calling function Order in the class Receipt
                    } else {
                        System.out.println("Order ID didnt exist!"); //Error message if the order ID is wrong

                    }
                    break;
                case "M":
                    break;
            }
        }

    }

    public void EditPrice() throws IOException {

        String choose3 = "";
        clearScreen();
        while (!choose3.trim().equalsIgnoreCase("M")) { //Will loop until user enter M
            System.out.println("******************* Edit Price ******************");
            System.out.println("Select from Options or Enter ‘M’ for Main Menu");
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Side");
            System.out.println("M. Go to main Menu");
            Menu bo = new Menu(bmenu); //new Object is being created for the class Menu
            Menu fo = new Menu(fmenu);
            Menu so = new Menu(smenu);
            choose3 = input.next();
            String type = " ";
            switch (choose3) {

                case "1":
                    type = "food"; //Setting the item type based on the choice user enter
                    fo.displayItem(); //Displaying all the items under the arraylist 
                    fmenu = fo.editItem(); //setting the existing arraylist based on the return value by calling function editItem
                    File f = new File("food.txt"); //setting the file name as the  present txt file name that storing all the datas
                    if (f.exists()) {
                        f.delete(); //it will delete the txt file that present now
                    }
                    writeNew(fmenu, type); //Calling function writeNew and passing list type and arraylist
                    System.out.println("Price Edited Succesfully!");
                    break;
                case "2":
                    type = "drink";
                    bo.displayItem();
                    bmenu = bo.editItem();
                    File b = new File("drink.txt");
                    if (b.exists()) {
                        b.delete();
                    }
                    writeNew(bmenu, type);
                    System.out.println("Price Edited Succesfully!");
                    break;
                case "3":
                    type = "side";
                    so.displayItem();
                    smenu = so.editItem();
                    File s = new File("side.txt");
                    if (s.exists()) {
                        s.delete();
                    }
                    writeNew(smenu, type);
                    System.out.println("Price Edited Succesfully!");
                    break;
                case "M":
                    break;
            }
        }
    }

    public static void writeNew(ArrayList<Item> editlist, String type) throws IOException {
        File fout = new File(type + ".txt"); //f name depends on the type variable that being passed
        FileOutputStream fos = new FileOutputStream(fout); //creating new txt file

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < editlist.size(); i++) { //will loop until the arraylist size
            bw.write(editlist.get(i).getId()); //writing the line based on the arraylist
            bw.newLine(); //going the nextline
            bw.write(editlist.get(i).getName());
            bw.newLine();
            bw.write(Double.toString(editlist.get(i).getPrice()));
            bw.newLine();
            bw.write(Double.toString(editlist.get(i).getProfit()));
            bw.newLine();
        }

        bw.close(); //closing the file
    }

    public void SalesSummary() throws FileNotFoundException, IOException {

        clearScreen(); //clearing the cmd
        System.out.println("******************* Sales Summary ******************");
        System.out.println(" ");
        DecimalFormat df = new DecimalFormat("#.##"); //formatting the decimate digit value
        ArrayList<String> sumName = new ArrayList<>(); //creating new arraylist
        ArrayList<Integer> sumQty = new ArrayList<>();
        int index1 = 0, index2 = 0, index3 = 0, no1 = 0, no2 = 0, no3 = 0, print = 0;
        Double saletotal = 0.0, saleprofit = 0.0;
        for (int i = 0; i < fmenu.size(); i++) { //this will lopp through each arraylist and get the names of all items existing
            sumName.add(fmenu.get(i).getName());
            sumQty.add(0); //initially all items will be set to 0 quantity
        }
        for (int i = 0; i < bmenu.size(); i++) {
            sumName.add(bmenu.get(i).getName());
            sumQty.add(0);
        }
        for (int i = 0; i < smenu.size(); i++) {
            sumName.add(smenu.get(i).getName());
            sumQty.add(0);
        }

        for (int i = 0; i < allreceipt.size(); i++) { //Looping through the arraylist 
            for (int j = 0; j < allreceipt.get(i).getName().size(); j++) { //looping throught the items inside the arraylist of the arraylist.
                for (int z = 0; z < sumName.size(); z++) { //looping through the arraylist that stored all items
                    if (sumName.get(z).equals(allreceipt.get(i).getName().get(j))) { //This will find whichever item that has been ordered
                        int pre, post, calc;
                        pre = sumQty.get(z);
                        post = allreceipt.get(i).getQuantity().get(j);
                        //this will add the quantity fof the same item from different receipt, by adding the previous values to post value
                        calc = pre + post;
                        sumQty.set(z, calc);
                        pre = 0;
                        post = 0;
                        calc = 0;
                    }
                }
            }
        }

        for (int i = 0; i < sumQty.size(); i++) { //this will loop throught and found out which is the top 3 items with high number of orders
            if (sumQty.get(i) > no1) {
                no3 = no2;
                index3 = index2;
                no2 = no1;
                index2 = index1;
                no1 = sumQty.get(i);
                index1 = i;
            } else if (sumQty.get(i) > no2) {
                no3 = no2;
                index3 = index2;
                no2 = sumQty.get(i);
                index2 = i;
            } else if (sumQty.get(i) > no3) {
                no3 = sumQty.get(i);
                index3 = i;
            }
        }

        for (int i = 0; i < allreceipt.size(); i++) { //will loop through the allreceipt arraylist and get the total sales

            saletotal = saletotal + allreceipt.get(i).getFtotal();
        }

        for (int i = 0; i < allreceipt.size(); i++) { //will loop throught the allreceipt arraylist and get the total profit

            saleprofit = saleprofit + allreceipt.get(i).getProfit();
        }

        for (int i = 0; i < sumQty.size(); i++) { //this is to check whether the has been any sales or no
            if (sumQty.get(i) != 0) {
                print = 1; //if there is a sale it will be set to 1
            }
        }

        if (print == 1) { //if there is sales only this function will be executed

            String notepad, email, sales;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //formating the day 
            Date today = new Date();
            sales = formatter.format(today);
            System.out.println("Sales Summary Of " + sales);
            System.out.println("\nTotal Revenue for today is : RM" + df.format(saletotal));
            System.out.println("\nTotal NetProfit for today is : Rm" + df.format(saleprofit));
            System.out.println("\nTop 3 Selling item fo the day: ");
            System.out.println("\nFirst Place: " + sumName.get(index1) + " " + "(total " + no1 + " orders)");
            System.out.println("Second Place: " + sumName.get(index2) + " " + "(total " + no2 + " orders)");
            System.out.println("Third Place: " + sumName.get(index3) + " " + "(total " + no3 + " orders)");
            System.out.println(" ");

            System.out.println("Do you want to save this info in a notepad ? (y/n): "); //Prompt asking whether user want to print the info in a notepad
            notepad = input.next();
            if ("y".equalsIgnoreCase(notepad)) { //if he put yes
                File fout = new File("Revenue-" + sales + ".txt"); //a new file will be created using the today's date
                FileOutputStream fos = new FileOutputStream(fout);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); //Writing inside the txt file
                bw.write("******************* Restaurant X ******************");
                bw.newLine();
                bw.write("Sales Summary Of " + sales);
                bw.newLine();
                bw.newLine();
                bw.write("Total Revenue for today is : RM" + df.format(saletotal));
                bw.newLine();
                bw.newLine();
                bw.write("Total NetProfit for today is : Rm" + df.format(saleprofit));
                bw.newLine();
                bw.newLine();
                bw.write("Top 3 Selling item fo the day: ");
                bw.newLine();
                bw.newLine();
                bw.write("First Place: " + sumName.get(index1) + " " + "(total " + no1 + " orders)");
                bw.newLine();
                bw.write("Second Place: " + sumName.get(index2) + " " + "(total " + no2 + " orders)");
                bw.newLine();
                bw.write("Third Place: " + sumName.get(index3) + " " + "(total " + no3 + " orders)");

                bw.close(); //Closing the txt file
                System.out.println("\nSaved In Notepad! ");

                System.out.println("\nDo you want to send this notepad to an email address ? (y/n): "); //Asking user whether he want to email the info 
                email = input.next();
                if ("y".equalsIgnoreCase(email)) { //if user enter y

                    String emailid;
                    System.out.println("Enter the email address: "); //it will ask the email id from the user
                    emailid = input.next();
                    Email mail = new Email(emailid, sales); //calling class Email and passing the emailid and today's date
                    mail.sendmail(); //calling function sendmail inside the class Email
                } else {
                    System.out.println("Noted! ");
                }
            }

        } else {
            System.out.println("No Sales Yet!");
        }

        System.out.print("Enter Anything to continue...");
        input.next();
    }

    public int ordering(String order, String type, String staff) {

        String Di = "";
        int s = 0;
        Menu ordermenu = new Menu(); //Creating a new object for the class Menu
        ArrayList<Order> forder = new ArrayList<>(); //Creating an arraylist based on OrderClass
        ArrayList<Order> border = new ArrayList<>();
        ArrayList<Order> sorder = new ArrayList<>();
        while (!Di.trim().equalsIgnoreCase("B")) { //Will loop until user enters B

            String title; //Setting The Title 
            if ("1".equals(type)) { //It will find out from the variable we passed. We passed the choice variable
                title = "Dine In";
            } else if ("2".equals(type)) {
                title = "Take Away";
            } else if ("3".equals(type)) {
                title = "Grab Food/Food Panda";
            } else {
                title = "";
            }
            System.out.println("******************* " + title + " ******************"); //It will print the title name
            ordermenu.displayMenu(); //calling displayMenu function inside Menu Class
            Menu bo = new Menu(bmenu); //Creating an object for the Menu and passing the arraylist of beverage arraylist
            Menu fo = new Menu(fmenu); //Creating an object for the Menu and passing the arraylist of food arraylist
            Menu so = new Menu(smenu); //Creating an object for the Menu and passing the arraylist of side arraylist
            Di = input.next();

            switch (Di) {

                case "1": //When food is selected
                    fo.displayItem(); //Calling displayItem function from Menu class
                    forder.addAll(fo.chooseItem()); //Calling chooseItem function and the return value will be added to forder arraylist
                    System.out.print("Enter Anything to continue...");
                    input.next(); //This is like getch funtion
                    clearScreen(); //To clear the cmd
                    break;
                case "2": //When beverage is selected
                    bo.displayItem();
                    border.addAll(bo.chooseItem());
                    System.out.print("Enter Anything to continue...");
                    input.next();
                    clearScreen();
                    break;
                case "3": //When side is selected
                    so.displayItem();
                    sorder.addAll(so.chooseItem());
                    System.out.print("Enter Anything to continue...");
                    input.next();
                    clearScreen();
                    break;
                case "4": //When cart is selected
                    int check = 1;
                    if (forder.size() == 0) { //this line will check whether any items have been added or no
                        if (border.size() == 0) {
                            if (sorder.size() == 0) {
                                check = 0; //If there is no any item has been added, the check will be set to 0
                            }
                        }
                    }

                    if (check != 0) { //if the check is not 0, which means there is item been added
                        Cart dn = new Cart(forder, border, sorder); //creating a object for the class Cart
                        Receipt rc = new Receipt(); //creating an object for the class Receipt
                        rc = (Receipt) (dn.displayCart(order, staff)); //Calling function displayCart inside Cart and copying the value to rc object
                        System.out.println("\n-------------------------------------------------");
                        System.out.println("Confirm Order & Pay ? (y/n)"); //Asking user whether he want to pay or not
                        String pay = input.next();
                        s = dn.confirmOrder(pay); //Calling the payment function and this will return variable s indicating the success of the payment
                        if ("y".equalsIgnoreCase(pay)) {
                            allreceipt.add(rc); //The receipt of this order will be added to allreceipt class, which will be use to track the order later
                        }
                    }
                    
                    else { //if there is no any item in any of the arraylist
                        System.out.println("You havent order anything!");
                    }
                    System.out.print("Enter Anything to continue...");
                    input.next();
                    Di = "B"; //will set Di to B to break the loop
                    clearScreen(); //to clear cmd
                    break;
                case "B":
                    break;
            }
        }

        return (s); //program will return s which indicates success of the order
    }

    public static void clearScreen() { //to clear the text that already displayed in the cmd

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public String menu() {

        clearScreen(); //clearing cmd
        //This entire function will print the menu
        System.out.println("******************* Welcome To Restaurant X ******************");
        System.out.println("Select any Option you want to perform. Enter ‘Q’ to quit the program ");
        System.out.println("1. Place Order");
        System.out.println("2. Order Status");
        System.out.println("3. Edit Price");
        System.out.println("4. Sales Summary");
        System.out.println("");

        return input.next(); //It will return the option that has been key-in
    }

    public String login() {

        String [] worker = new String[3]; //An array created with 3 users
        worker[0] = "Michael";
        worker[1] = "Thanesh";
        worker[2] = "Mubarak";
        
        String [] pwd = new String[3]; //Password has been hard coded into the array
        pwd[0] = "mic123";
        pwd[1] = "tha123";
        pwd[2] = "mub123";
        
        String uname = "", pass;
        int temp = 0; //for loop
        System.out.println("******************* Login ******************");
        System.out.println("");
        while (temp == 0) {
            int invalidname = 0;
            int invalidpass = 0;
            System.out.println("Enter Username: ");
            uname = input.next(); //Getting username
            System.out.println("Enter Password: ");
            pass = input.next(); //Getting password
            for (int i = 0; i < worker.length; i++) //Will loop according to the array size
            {
                if(uname.equalsIgnoreCase(worker[i])) //if a username matched
                {
                    if (pass.equals(pwd[i])) //it will check whether the username index and password index is matching or no
                    {                        //this technique use the real world algorithm
                        System.out.println("Login Succesful!");
                        System.out.println("Welcome " + worker[i]);
                        uname = worker[i]; //username will be copied to this variable
                        temp = 1;
                        invalidname = 0;
                        System.out.println("Enter anything to continue...");
                        input.next();
                    }
                    else {
                        invalidpass = 1;
                    }
                }
                
                else
                {
                    invalidname++;
                }
            }
            
            if (invalidname == worker.length) { //Error message for wrong username & password
                if (invalidpass == 0) {
                    System.out.println("Wrong Username!");
                }
            }
            
            if (invalidpass == 1) //this is for the user who put correct username but wrong password
            {
                System.out.println("Password Mismatch!");
            }
            
        }

        return (uname); //returning to uname to the staff variable
    }

    public void initData() throws FileNotFoundException, IOException {

        fmenu.clear(); //clearing the arraylist first
        bmenu.clear();
        smenu.clear();

        String filename1 = "food.txt"; //Setting the filename to our txt file name
        try (BufferedReader br = new BufferedReader(new FileReader(filename1))) { //opening the text file
            while (br.ready()) { //while there is a new line this will loop

                String id = br.readLine(); // the id copied the line from the text file
                String name = br.readLine(); 
                Double price = Double.parseDouble(br.readLine()); //Copying the string into a double data
                Double profit = Double.parseDouble(br.readLine());
                Item fm = new Item(id, name, price, profit); //a new instance being created for the class item with the above datas
                fmenu.add(fm); //that object being added to this same data type arraylist
            }
        }

        String filename2 = "drink.txt";
        try (BufferedReader dr = new BufferedReader(new FileReader(filename2))) {
            while (dr.ready()) {

                String id = dr.readLine();
                String name = dr.readLine();
                Double price = Double.parseDouble(dr.readLine());
                Double profit = Double.parseDouble(dr.readLine());
                Item dm = new Item(id, name, price, profit);
                bmenu.add(dm);
            }
        }

        String filename3 = "side.txt";
        try (BufferedReader sr = new BufferedReader(new FileReader(filename3))) {
            while (sr.ready()) {

                String id = sr.readLine();
                String name = sr.readLine();
                Double price = Double.parseDouble(sr.readLine());
                Double profit = Double.parseDouble(sr.readLine());
                Item sm = new Item(id, name, price, profit);
                smenu.add(sm);
            }
        }
    }
}
