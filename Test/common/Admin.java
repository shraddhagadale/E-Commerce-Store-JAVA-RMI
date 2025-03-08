package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Admin extends UnicastRemoteObject implements Person {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    public static List<Person> customers=new CopyOnWriteArrayList<>();;
    public static List<Person> admins=new CopyOnWriteArrayList<>();

    public Admin() throws RemoteException {
        this.username = "";
        this.password = "";
        Person P1 =new Customer("customer1","Pass@1234");
        Person P2 =new Customer("customer2","Pass@9090");
        Person P3 =new Customer("customer3","Pass@8080");
        customers.add(P1);
        customers.add(P2);
        customers.add(P3);
        
        Person a1 =new Admin("admin1","Pass@1234");
        Person a2 =new Admin("admin2","Pass@9090");
        Person a3 =new Admin("admin3","Pass@8080");
        admins.add(a1);
        admins.add(a2);
        admins.add(a3);
    }
    
    public Admin(String username,String password)throws RemoteException{
    	this.username=username;
    	this.password=password;
    }
    
    public static void loadData() throws RemoteException{
    	Person P1 =new Customer("customer1","Pass@1234");
        Person P2 =new Customer("customer2","Pass@9090");
        Person P3 =new Customer("customer3","Pass@8080");
        customers.add(P1);
        customers.add(P2);
        customers.add(P3);
        
        Person a1 =new Admin("admin1","Pass@1234");
        Person a2 =new Admin("admin2","Pass@9090");
        Person a3 =new Admin("admin3","Pass@8080");
        admins.add(a1);
        admins.add(a2);
        admins.add(a3);
    }

    public String getUsername() throws RemoteException {
        return username;
    }

    public void setUsername(String username) throws RemoteException {
        this.username = username;
    }

    public String getPassword() throws RemoteException {
        return password;
    }

    public void setPassword(String password) throws RemoteException {
        this.password = password;
    }

    public void addCustomer(Customer customer) throws RemoteException {
        customers.add(customer);
    }

    public void removeCustomer(String uname) throws RemoteException {
        customers.remove(uname);
    }

    public void addAdmin(Admin admin) throws RemoteException {
        admins.add(admin);
    }

    public List<Person> getCustomers() throws RemoteException {
        return customers;
    }

    public List<Person> getAdmins() throws RemoteException {
        return admins;
    }

	@Override
	public boolean login(String uname, String pass) throws RemoteException {
		for (Person person : admins) {
            if (person.getUsername().equals(uname) && person.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
		
	}

	@Override
	public boolean register(String uname, String pass) throws RemoteException {
		int count=0;
		for (Person person : admins) {
            if (person.getUsername().equals(uname) && person.getPassword().equals(pass)) {
                count =1;
            	break;
            }
        }
		if(count!=1) {
			Person cust1 = new Admin(uname, pass);
			this.admins.add(cust1);
			return true;
		}
        return false;
	}
	
	@Override
	public String toString() {
	    return "Admin [username=" + username + ", password=" + password + "]";
	}

	
}