package BankControlSystem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;



public class Customer {
	private String name;
	private String address;
	private String dateOfBirth;
	/*private String birthYear;
	private String birthMonth;
	private String birthDay;*/
	private boolean creditStatus=true;
	private String accType;
	
	//private ArrayList<Customer> customer = new ArrayList<Customer>();
	
	public Customer(String name, String address, String dateOfBirth,String accType){
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.accType=accType;
		creditStatus = true;
	}
	

	
	
	

	public String getName() {
		return name;
	}





	public String getAddress() {
		return address;
	}





	public String getDateOfBirth() {
		return dateOfBirth;
	}





	public boolean isCreditStatus() {
		return creditStatus;
	}





	public void setName(String name) {
		this.name = name;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}





	public void setCreditStatus(boolean creditStatus) {
		this.creditStatus = creditStatus;
	}



	public String getAccType() {
		return accType;
	}



	public void setAccType(String accType) {
		this.accType = accType;
	}






	public int customerReg(){		
		int line=1;
		int flag = 0;
		
		File file = new File("customer.txt");
		BufferedReader reader = null;
	    String temp = null;
	    try {
		        
		        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		       // StringBuffer bf = new StringBuffer();
		        System.out.println("read");	        
		        while ((temp = reader.readLine()) != null) {
		            System.out.println("Line"+ line + ":" +temp);
		        	
		        	System.out.println(this.getName());
		            if(temp.contains(this.getName())){
		            	flag=1;
		            	String[] split=temp.split(" ");		          		            		            	
		            	this.setCreditStatus(Boolean.valueOf(split[5]));
		            	System.out.println(split[5]);
	            	
		            	if(this.isCreditStatus()== false){
		        			System.out.println("bad credit history!");
		        		    flag=0;//bad credit history
		        		    break;
		            	}		            			            		            		                
		                if(this.getAccType().equals(split[6])){
		                	System.out.println("sorry, You have already registered.");
		                	flag=2;//have registered
		                	break;
		                }
		                		                
		            }else{
		            	flag=3;//not exist
		            	
		            }
		            line++;
		           		            			            
		        }
		        
		        reader.close();	 
		       	       	        
		        if(flag==3){
		        	System.out.println("welcome, new customer");
			        FileWriter fileWriter = new FileWriter("customer.txt",true);
			        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			        String info = this.getName()+" "+this.getAddress()+" "+this.getDateOfBirth()+" "+this.isCreditStatus()+" "+this.getAccType();        
			        bufferedWriter.append(info).append("\r\n");			        
			        bufferedWriter.close();
			        Account acc = new Account(this.accType);
			        acc.newAcc();
			        
		        }
		        if(flag==0){
		        	System.out.println("sorry, You have bad credit history, cannot register");
		        }
		        if(flag==2){
		        	System.out.println("you have already registered");
		        }
		        if(flag==1){
		        	System.out.println("you can register this type of account");		        	
			        FileWriter fileWriter = new FileWriter("customer.txt",true);
			        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			        String info = this.getName()+" "+this.getAddress()+" "+this.getDateOfBirth()+" "+this.isCreditStatus()+" "+this.getAccType();        
			        bufferedWriter.append(info).append("\r\n");			        
			        bufferedWriter.close();			        
			        Account acc = new Account(this.accType);
			        acc.newAcc();
		        }
		        
		               
		        
		     }catch (FileNotFoundException fnfe) {
		        //TODO Auto-generated catch block
		        fnfe.printStackTrace();
		     }catch (IOException ioe) {
		        //TODO Auto-generated catch block
		        ioe.printStackTrace();
		     }finally{
		        if(reader != null){
		            try {
		                reader.close();
		            } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		     }
		return flag;
	   
		
	}

	


	
}