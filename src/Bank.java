import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> allCustomers = new ArrayList<>();

    public void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);
    }

    public void removeCustomer(Customer customerToRemove){
        allCustomers.remove(customerToRemove);
    }

    public Customer findCustomerByPIN(int pin) {
        Customer searchResult = null;
        for (Customer customer : allCustomers) {
            if (customer.getPin() == pin){
                searchResult = customer;
            }
        }
        return searchResult;
    }

    public StringBuilder getAllCustomers(){
        StringBuilder customerStrBldr = new StringBuilder();
        for(Customer customer : allCustomers){
            customerStrBldr.append(customer.toString());
        }
        return customerStrBldr;
    }
}
