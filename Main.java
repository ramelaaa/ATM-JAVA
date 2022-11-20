
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {

        // Store all user data
        ArrayList<Customer> customers =  new ArrayList<Customer>();

        // user sign-in information
        String currentCustomerUsername, currentCustomerPassword;
        // user balance
        double currentCustomerBalance = 0.00;

        // file to read from
        File file = new File("Accounts.txt");

        Scanner scanner = new Scanner(file);

        boolean userVerified = false;

        // stores all activies the user does during their session
        ArrayList<String> transactions = new ArrayList<String>();

        // Read from file and populate ArrayList
        String temp1,temp2;
        double temp3;
        while(scanner.hasNext()) {
            temp1 = scanner.next();
            temp2 = scanner.next();
            temp3 = scanner.nextDouble();

            Customer myCustomer = new Customer(temp1,temp2,temp3);
            customers.add(myCustomer);
        }
        scanner.close();


        // Obtain username and password
        Scanner userSession = new Scanner(System.in);
        System.out.print("Enter your username: ");
        currentCustomerUsername = userSession.next();
        System.out.print("Enter your password: ");
        currentCustomerPassword = userSession.next();

        // verify user
        for(Customer x: customers){
            if(x.search(currentCustomerUsername, currentCustomerPassword) == true){
                userVerified = true;
                currentCustomerBalance = x.getBalance();
            }
        }

        if(userVerified == false){
            System.out.println("User Credentials are invalid.");
            //exit program
        }else{
            String output = String.format("Welcome %s!", currentCustomerUsername);
            System.out.println(output);

            // store Menu chouce
            String response = "";
            do {
                // show menu and prompt
                DisplayMenu();
                System.out.print("Enter your choice: ");
                response = userSession.next();
                //display balance
                if(response.equals("V")){
                    String viewOutput = String.format("Your total balance is: %s.", currentCustomerBalance);
                    transactions.add(viewOutput);
                    System.out.println(viewOutput);
                }else if(response.equals("D")){ // ask for amount and increment variable
                    double amountToDeposit = 0;
                    System.out.println("Total amount to deposit: ");
                    try{
                        amountToDeposit = userSession.nextDouble();
                    }catch(InputMismatchException e){
                        System.out.println("Invalid Input");
                    }
                    if(amountToDeposit < 0){
                        System.out.println("Amount must be positive!!!");
                    }else{
                        currentCustomerBalance += amountToDeposit;
                        String depositOutput = String.format("You deposited %s!", amountToDeposit);
                        System.out.println(depositOutput);
                        transactions.add(depositOutput);
                    }
                }else if(response.equals("W")){ // ask for amount, verify greater than 0, decrement variable
                    double amountToWithdraw = 0;
                    System.out.println("Total Amount to withdraw: ");
                    try{
                        amountToWithdraw= userSession.nextDouble();
                    }catch(InputMismatchException e){
                        System.out.println("Invalid Input");
                    }
                    if(amountToWithdraw < 0){
                        System.out.println("Amount must be positive!!!");
                    }else if (amountToWithdraw > currentCustomerBalance){
                        System.out.println("You do not have enough funds!");
                    }
                    else{
                        currentCustomerBalance -= amountToWithdraw;
                        String depositOutput = String.format("You withdrew %s!", amountToWithdraw);
                        System.out.println(depositOutput);
                        transactions.add(depositOutput);
                    }
                }else if(response.equals("P")){ // print transctions array to a file, exit program
                    FileWriter fileWriter = new FileWriter("Receipt.txt");
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println(currentCustomerUsername);
                    for (String x: transactions){
                        printWriter.println(x);
                    }
                    printWriter.close();
                    response = "Q";
                    System.out.println("printing receipt...");
                    System.out.println("Goodbye!");
                }else if(response.equals("Q")){
                    //exit program
                    System.out.println("Goodbye!");
                }else{
                    System.out.println("Invalid Input. Try Again!");
                } 
            } while ( !response.equals("Q"));
            userSession.close();
        }
    }

    // Prints menu options to the user.
    public static void DisplayMenu(){
        System.out.println("Press V to view balance.");
        System.out.println("Press D to deposit money.");
        System.out.println("Press W to withdraw money.");
        System.out.println("Press P to print receipt.");
        System.out.println("Press Q to quit.");
    }

    public static class Customer{
        // Constructor
        Customer(String username, String password, double balance){
            this.username = username;
            this.password = password;
            this.balance = balance;
        }

        private String username;
        private String password;
        private double balance;

        // returns user's balace
        final public double getBalance(){
            return balance;
        }

        // Check if customer info matches
        public boolean search(String username, String password){
            return this.username.equals(username) && this.password.equals(password);
        }
    }  
}