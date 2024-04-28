public class Account {
    static int numberOfAccounts = 10000;
    private final int accountNumber;
    private double balance;

    public Account(double initialDeposit){
        numberOfAccounts++;
        this.accountNumber = numberOfAccounts;
        balance += initialDeposit;
    }

    public void depositFunds(double amount){
        balance += amount;
        System.out.printf("""
                
                Successfully deposited $%.2f.
                Your current balance is $%.2f.
                
                """, amount, balance);
    }

    public void withdrawFunds(double amount){
        if (balance < amount){
            System.out.println("\nInsufficient funds.");
        }else {
            balance -= amount;
            System.out.printf("""
                    
                    Successfully withdrew $%.2f.
                    Your current balance is $%.2f.
                    
                    """, amount, balance);
        }
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    @Override
    public String toString(){
        return String.format("""
                
                Account Number: %d
                Balance: $%.2f
                """,
                accountNumber, balance);
    }
}
