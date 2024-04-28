import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private int pin;
    private ArrayList<Account> openAccounts = new ArrayList<>();

    public Customer(String firstName, String lastName, int pin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
    }

    public void addAccount(Account newAccount){
        openAccounts.add(newAccount);
    }

    public int getPin(){
        return pin;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public Account getAccount(int accountNum){
        Account searchResult = null;
        for(Account account : openAccounts){
            if(account.getAccountNumber() == accountNum){
                searchResult = account;
            }
        }
        return searchResult;
    }

    public StringBuilder getAllAccounts(){
        StringBuilder accountStrBldr = new StringBuilder();
        for(Account account : openAccounts){
            accountStrBldr.append(account.toString());
        }
        return accountStrBldr;
    }

    public void closeAccount(Account account){
        openAccounts.remove(account);
    }

    @Override
    public String toString(){
        return String.format("""
                %s, %s
                PIN: %d
                Number of accounts: %d
                
                """, lastName, firstName,
                pin, openAccounts.size());
    }
}
