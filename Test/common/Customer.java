package common;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Customer extends UnicastRemoteObject implements Person{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private List<Person> customers;

	public String getUsername() throws RemoteException{
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() throws RemoteException{
		return password;
	}

	public void setPassword(String password) throws RemoteException{
		this.password = password;
	}

	public Customer() throws RemoteException {
        this.username=" ";
        this.password=" ";
        this.customers = new ArrayList<Person>();
        // Add default values to customers list
        Person cust1 = new Customer("customer1","Pass@1234");
        Person cust2 = new Customer("customer2","Pass@9090");
        Person cust3 = new Customer("customer3","Pass@8080");
        this.customers.add(cust1);
        this.customers.add(cust2);
        this.customers.add(cust3);
        Admin.loadData();
    }

    public Customer(String username, String password)throws RemoteException {
		this.username=username;
		this.password=password;
	}


	@Override
	public boolean login(String uname, String pass) throws RemoteException {
		
		for (Person person : Admin.customers) {
            if (person.getUsername().equals(uname) && person.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
	}

	@Override
	
	public boolean register(String uname, String pass) throws RemoteException {
		int count=0;
		for (Person person : Admin.customers) {
            if (person.getUsername().equals(uname) && person.getPassword().equals(pass)) {
                count =1;
            	break;
            }
        }
		if(count!=1) {
			Person cust1 = new Customer(uname, pass);
			Admin.customers.add(cust1);
			return true;
		}
        return false;
	}
	@Override
	public String toString() {
	    return "Customer [username=" + username + ", password=" + password + "]";
	}

}
