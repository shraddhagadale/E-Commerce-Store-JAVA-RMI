package common;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Person extends Remote{
	
    public boolean login(String uname, String pass) throws RemoteException;
    
    public boolean register(String uname, String pass) throws RemoteException;

	public void setUsername(String string) throws RemoteException;

	public void setPassword(String string) throws RemoteException;

	public String getUsername() throws RemoteException;
	
	public String getPassword() throws RemoteException;
}
