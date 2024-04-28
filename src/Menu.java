import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    Bank cardinalBank = new Bank();

    public void runMenu(){
        try{
            boolean inMenu = true;
            while(inMenu){
                System.out.print("""
                
                *** *** CARDINAL BANK *** ***
                
                1) Access Account
                2) Open New Account
                3) Close All Accounts
                4) Exit Program
                
                Please make a selection:\s""");
                int choice = Integer.parseInt(input.nextLine());

                switch (choice){
                    case 1:
                        accessAccount();
                        break;

                    case 2:
                        openNewAccount();
                        break;

                    case 3:
                        closeAllAccounts();
                        break;

                    case 4:
                        System.out.println("\nThank you for using Cardinal Bank, Chirp Chirp!");
                        inMenu = false;
                        break;

                    default:
                        System.out.println("\nInvalid selection, please try again.");
                        break;
                }
            }

        } catch (Exception e){
            System.out.print("Oops! Something went wrong. Restarting...\n");
            runMenu();
        }
    }

    private void accessAccount() {
        Customer customer;
        System.out.print("\nPlease enter PIN: ");
        int pin = Integer.parseInt(input.nextLine());

        if (cardinalBank.findCustomerByPIN(pin) != null) {
            customer = cardinalBank.findCustomerByPIN(pin);
        } else {
            System.out.println("Invalid PIN.");
            return;
        }

        //PIN valid, show all accounts
        System.out.printf("""
                    
                    Hello, %s.
                    Your accounts are listed below.
                    """, customer.getFullName());
        System.out.println(customer.getAllAccounts());

        System.out.print("Number of the account to access: ");
        int accountNum = Integer.parseInt(input.nextLine());
        Account currAccount = customer.getAccount(accountNum);

        if (currAccount == null) {
            System.out.println("\nInvalid Account Number.");
            //Automatically returns to menu

        } else {
            boolean isInAccount = true;
            while (isInAccount) {
                System.out.printf("""
                                
                                *** *** CARDINAL BANK *** ***
                                                
                                Hello, %s.
                                Account Number: %d
                                                
                                1) Make a deposit
                                2) Make a withdrawal
                                3) See account balance
                                4) Close Account
                                5) Exit
                                
                                Please make a selection:\s""",
                        customer.getFullName(),
                        currAccount.getAccountNumber());

                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        currAccount.depositFunds(Integer.parseInt(input.nextLine()));
                        break;

                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        currAccount.withdrawFunds(Integer.parseInt(input.nextLine()));
                        break;

                    case 3:
                        System.out.printf("\nCurrent balance: $%.2f\n", currAccount.getBalance());
                        break;

                    case 4:
                        System.out.print("Are you sure? (Enter 'y' to confirm): ");
                        if (input.nextLine().equalsIgnoreCase("y")) {
                            customer.closeAccount(currAccount);
                            System.out.println("\nAccount successfully closed.");
                            isInAccount = false;
                        } else {
                            System.out.println("\nAccount was not closed.");
                        }
                        break;

                    case 5:
                        isInAccount = false;
                        break;
                }
            }
        }
    }

    private void openNewAccount() {
        Customer customer = null;

        System.out.print("\nAre you a new customer? (y/n): ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            customer = createNewCustomer();

        } else if (answer.equalsIgnoreCase("n")) {
            System.out.print("\nWelcome back.\nPlease enter your PIN: ");
            int pin = Integer.parseInt(input.nextLine());

            if (cardinalBank.findCustomerByPIN(pin) == null) {
                System.out.println("\nInvalid PIN.");
                return;

            } else {
                customer = cardinalBank.findCustomerByPIN(pin);
            }

        } else { //Invalid Entry
            System.out.println("Please only answer with 'y' or 'n'.");
            openNewAccount();
        }

        if(customer != null){ //Customer found or created, now create new account
            System.out.println("\n*** *** New Account Setup *** ***");

            System.out.print("\nPlease enter an initial deposit: ");
            double deposit = Double.parseDouble(input.nextLine());
            Account newAccount = new Account(deposit);
            customer.addAccount(newAccount);
            System.out.printf("""
                            
                            New Account Opened: %d
                            Initial deposit: $%.2f
                            
                            """,
                    newAccount.getAccountNumber(), deposit);
        }
    }

    private Customer createNewCustomer(){
        System.out.println("\n*** *** Customer Profile Setup *** ***");

        System.out.print("\nEnter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Setup your PIN: ");
        int pin = Integer.parseInt(input.nextLine());

        Customer newCustomer = new Customer(firstName, lastName, pin);
        cardinalBank.addCustomer(newCustomer);
        System.out.println("\nCustomer profile complete. Welcome!");

        return newCustomer;
    }

    private void closeAllAccounts(){
        Customer customer = null;
        System.out.print("Please enter your PIN: ");
        int pin = Integer.parseInt(input.nextLine());
        customer = cardinalBank.findCustomerByPIN(pin);

        if (customer == null){
            System.out.println("\nInvalid PIN.");
            //Automatically returns to menu

        }else {
            System.out.print("Are you sure? (Enter 'y' to confirm): ");
            if (input.nextLine().equalsIgnoreCase("y")) {
                cardinalBank.removeCustomer(customer);
                System.out.println("\nCustomer removed from bank registry.");
            }else {
                System.out.println("\nNo accounts were closed.");
            }
        }
    }
}
