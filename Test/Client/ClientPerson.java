package Client;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.Admin;
import common.Customer;
import common.Person;
import common.PersonFactory;
import common.Product;
import common.ProductManager;

public class ClientPerson {
    public static void main(String[] args) throws Exception {
        try {
			Scanner sc=new Scanner(System.in);
			System.out.println("Welcome to online Shopping store!");
			PersonFactory remoteFactory = (PersonFactory) Naming.lookup("//in-csci-rrpc03.cs.iupui.edu:2233/PersonFactory");
			ProductManager remoteProduct = (ProductManager) Naming.lookup("//in-csci-rrpc03.cs.iupui.edu:2233/ProductManager");
			do {
				System.out.println("Are you Customer or Admin:");
				String n=sc.nextLine();
				List<Product> productList = remoteProduct.browseProducts();
				Admin.loadData();
				if(n.equalsIgnoreCase("customer")) {
					Person cust = remoteFactory.createPerson("customer");
					System.out.println("1.Login 2.Register");
					String i=sc.nextLine();
					System.out.println("Enter Username:");
					String uname=sc.nextLine();
					System.out.println("Enter Password:");
					String pass=sc.nextLine();
					if(i.equalsIgnoreCase("1")) {
						if(cust.login(uname,pass)) {
							System.out.println("Login Successful");
						}
						else {
							System.out.println("Login unsuccessful");
							System.exit(0);
						}
						List<Product> cart = new ArrayList<>();
						do {
							System.out.println("Select 1.Add to cart \n2.Remove from cart \n3.View Items in cart \n4.Purchase Items in cart \n5View Products \n6.Logout");
					        int input2=sc.nextInt();
					        switch(input2) {
					        case 1:System.out.println("Products:");
					        		for (Product product : productList) {
					        		   System.out.println(product.getType() + " - " + product.getDescription() + " - $" + product.getPrice() + " - " + product.getQuantity() + " in stock");
					        	   }
					   	           Product productToAdd=new Product();
					   	           // Add a product to the cart
					   	           System.out.println("Enter a product name to add to your cart (or enter 'done' to finish shopping):");
					   	           int c=0;
					   	           while (true) {
					   	        	   String item = sc.nextLine();
					   	        	   if (item.equals("done")) {
					   	        		   System.out.println("Total cost:"+remoteProduct.purchaseCart(cart));
					   	        		   break;
					   	        	   }
					   	        	   productToAdd = remoteProduct.getProductByName(item);
					   	        	   if (productToAdd != null) {
					   	        		   remoteProduct.addToCart(productToAdd, cart);
					   	        		   cart.add(productToAdd);
					   	        		   System.out.println("Product Added!");
					   	        	   }
					   	        	   else if(c!=0 && productToAdd==null){
					   	        		   System.out.println("Product not Available! Check correct name");
					   	        	   }
					   	        	   c++;
					   	           }
					   	           break;
					        case 2:System.out.println("Enter a product name to remove from the cart (or enter 'done' to finish shopping):");
					        int count=0;		
					        while(true) {
					               String itemToRemove = sc.nextLine();
					               if (itemToRemove.equals("done")) {
				   	        		   System.out.println("Total cost:"+remoteProduct.purchaseCart(cart));
				   	        		   break;
				   	        	   }
					               Product productToRemove=remoteProduct.getProductByName(itemToRemove);
					               for (Product product : cart) {
					            	   if (product.getDescription().equals(itemToRemove)) {
					                   productToRemove = product;
					                   break;
					            	   }
					               }
					               if (productToRemove != null) {
					            	   remoteProduct.removeFromCart(productToRemove, cart);
					            	   cart.remove(productToRemove);
					            	   System.out.println("Product removed from cart.");
					               }
					               else if(count!=0 && productToRemove==null){
					                System.out.println("Product not found in cart.");
					               }
					               count++;
					            }
					            break;
					        case 3:System.out.println("Products in your cart:");
					        	   if (cart.isEmpty()) {
					        	   System.out.println("Your cart is empty.");
					        	   } 
					        	   else {
					        		   System.out.println("Items in your cart:");
					        		   for (Product product : cart) {
					        			   System.out.println(product.getDescription()+"-"+product.getPrice());
					        		   }
					        	   }
					        	   break;
					        case 4:if (cart.isEmpty()) {
					        	   System.out.println("Your cart is empty.");
					        	   } 
					        	   else {
					        		   System.out.println("Purchase Successful with total cost:"+remoteProduct.purchaseCart(cart));
									 cart.clear();
					        	   }
					        		break;
					        case 5: System.out.println("Products:");
				        	   		for (Product product : productList) {
				        	   			System.out.println(product.getType() + " - " + product.getDescription() + " - $" + product.getPrice() + " - " + product.getQuantity() + " in stock");
				        	   		}
				        	   		break;
					        case 6: System.out.println("Logged out!");
									System.exit(0);
					        default:System.out.println("Invalid Input");
					        }
						}while(true);
					}
					else if(i.equalsIgnoreCase("2")) {
						if(cust.register(uname,pass)) {
							System.out.println("Registration Successful");
						}
						else {
							System.out.println("Registration unsuccessful");
							System.exit(0);
						}
					}
					else {
						System.out.println("Invalid Input");
						System.exit(0);
					}
					
				}
				else if(n.equalsIgnoreCase("admin")) { 
					Person admin = remoteFactory.createPerson("admin");
					System.out.println("Enter Username:");
					String uname=sc.nextLine();
					System.out.println("Enter Password:");
					String pass=sc.nextLine();
					if(admin.login(uname,pass)) {
							System.out.println("Login Successful");
						}
						else {
							System.out.println("Login unsuccessful");
							System.exit(0);
						}

					List<Product> Product = new ArrayList<>();
					do {
						System.out.println("Select 1.view products \n2.Add product \n3.Remove product \n4.Update product \n5.Add admin \n6.Add customer \n7.Remove customer \n8.Logout");
				        int input2=sc.nextInt();
				        switch(input2) {
				        case 1:System.out.println("Products:");
				        	   for (Product product : productList) {
				        		   System.out.println(product.getType() + " - " + product.getDescription() + " - $" + product.getPrice() + " - " + product.getQuantity() + " in stock");
				        	   }
				   	           break;
				        case 2:sc.nextLine();
				        	System.out.println("Product Type:");
				        	String type=sc.nextLine();
				        	System.out.println("Product Description:");
				        	String des=sc.nextLine();
				        	System.out.println("Product price:");
				        	Double price=sc.nextDouble();
				        	System.out.println("Product quantity:");
				        	int qnt=sc.nextInt();
				        	Product p=new Product(type,des,price,qnt);
				        	remoteProduct.addProduct(p);
				        	productList.add(p);
				        	System.out.println("Product Added");
				        	List<Product> products = remoteProduct.browseProducts();
				            System.out.println("List of products:");
				            for (Product product : products) {
				                System.out.println(product.getDescription() + " - " + product.getPrice());
				            }
				            break;
				        case 3:sc.nextLine();
				        	System.out.println("Enter the product name to remove:");
				        	String productName = sc.nextLine();
				        	boolean removed=false;
				            for(Product p1: productList) {
				                if(p1.getDescription().equalsIgnoreCase(productName)){
				                    removed = productList.remove(p1);
				                    break;
				                }
				            }
				            if (removed) {
				                System.out.println("Product is removed" );
				            } else {
				                System.out.println("Product not found in database");
				            }
				        break;
				        case 4:
				            System.out.println("Enter the product name to update:");
				            sc.nextLine();
				            String productNameToUpdate = sc.nextLine();
				            Product productToUpdate = remoteProduct.getProductByName(productNameToUpdate);
				            if (productToUpdate != null) {
				                System.out.println("Enter the new price:");
				                double newPrice = sc.nextDouble();
				                sc.nextLine(); // consume the newline character
				                System.out.println("Enter the new type:");
				                String newType = sc.nextLine();
				                System.out.println("Enter the new description:");
				                String newDescription = sc.nextLine();
				                System.out.println("Enter the new quantity:");
				                int newQuantity = sc.nextInt();
				                sc.nextLine(); // consume the newline character
				                remoteProduct.updateProduct(productToUpdate, newPrice, newType, newDescription, newQuantity);
				                boolean updated=false;
				              
				                for(Product p1: productList) {
					                if(p1.getDescription().equalsIgnoreCase(productNameToUpdate)){
					                    p1.setDescription(newDescription);
					                    p1.setPrice(newPrice);
					                    p1.setQuantity(newQuantity);
					                    p1.setType(newType);
					                    updated=true;
					                    break;
					                }
					            }
				                if (updated) {
					                System.out.println("Product is updated" );
					            } else {
					                System.out.println("Product not found in database");
					            }
				            }
				            break;
				        case 5: sc.nextLine();
				        	Admin a = new Admin();
				        System.out.println("Enter Admin uname:");
				        String uname1=sc.nextLine();
				        System.out.println("Enter Admin password:");
				        String pass1=sc.nextLine();
				        Admin add=new Admin(uname1,pass1);
				        a.addAdmin(add);
				        System.out.println("Admin added");
				        		break;
				        case 6:sc.nextLine();
				        System.out.println("Enter Customer uname:");
				        String uname2=sc.nextLine();
				        System.out.println("Enter customer password:");
				        String pass2=sc.nextLine();
				        Customer c = new Customer(uname2,pass2);
				        Admin a2=new Admin();
				        a2.addCustomer(c);
				        System.out.println("Customer added");break;
				        case 7: sc.nextLine(); 
				        	System.out.println("Enter Customer uname:");
				        String uname3=sc.nextLine(); 
				        Admin a3=new Admin();
				        a3.removeCustomer(uname3);
				        System.out.println("Customer Removed");
				        		break;
				        
				        case 8: System.out.println("Logged out!");
								System.exit(0);
				        default:System.out.println("Invalid Input");
						}
						}while(true);
					}
				else
				{System.out.println("Invalid Input");}
			}while(true);
        }
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}

    }
}