# RestaurantManagement
Restaurant Management System is my Object Oriented Programming assignment. This Entire project was code in Java. The user can place order, track order, edit menu, and  check the sales summary. I used suitable data structures and classes to complete this program.

This Restaurant Management system is fully build in Java for my Object Oriented Programming Assignment.

1. There are total 7 java files in the ".RestaurantManagement/src/restaurantmanagement" path.

2. I have attached the mail.jar javax mail package which I downloaded from Internet.

3. Once you open this project in your IDE, you need to add the "mail.jar" file in your libraries folder.

4. Then open the file "Email.java". At line 23 & 24, you need to add you need to add your email Id & email password. This feature can make the program to send the sales summary to the any email from your program.

5. For gmail users, this feature wont work unless you go to your google account/Security/Less secure app access and on the permission for "Allow Less Secure Apps". 

6. If you didnt setup this except this feauture all the other feature will work normally.

7. I also have included 3 txt.files which are "food.txt" , "drink.txt", & "side.txt". Each of this text files contain the restaurants menu information (NAME, ID, PRICE, PROFIT).

8. Once you run the program it will ask you to enter the username and password. There are 3 users for this system. You can use any of the below usernames and password.
	
	i) username - Michael, password - mic123
	ii) username - Thanesh, password - tha123
	iii) username - Mubarak, password - mub123

9. Once the password is matched you can use the program. You will be seeing for main functions of the program 
	
	1. Place Order // You can place your order. Each items informations will be retrived from the txt files. Every order will have a unique order ID // Payment will be done in the cart.
	2. Order Status // You can track your order status by entering your "Order ID". If the order time and current time is below 2 minits it will show "order received", if it is between 2 - 4 minits it will show "Cooking". If it is more than 4 minit it will show as "Delivered". It purely work using time difference.
	3. Edit Price // You can select the grouped items and edit it price and profit. Once you edit. The new price and profit for the item will be updated in the program as well as in the txt file.
	4. Sales Summary // Here You can see the total sales summary since you run the program. It will calculate total sales and profit. It will also list down the top 3 selling item for the day. The program also will offer you to save all the information in a notepad and you also can send the notepad to an email id you want.

10. You can enter Q to quit the program when you are in the main screen.

11. I have also provided the program in a executable .bat file. You can find it inside the "../dist" path. You can just run the .bat file to see how the full program works in cmd.
