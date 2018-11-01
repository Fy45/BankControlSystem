package BankControlSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClearSystem {
	protected String accNo;
	protected double balance;
	protected String accType;
	protected double funds;
	protected boolean isActive;
	
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public double getFunds() {
		return funds;
	}
	public void setFunds(double funds) {
		this.funds = funds;
	}
	
	public boolean getActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public ClearSystem(){
		
	}
	public void readFunds(){
		 File file = new File("funds.txt");
		 BufferedReader reader = null;
	     String temp = null;
	     int line=0;
	     try {
	        
	        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	        	        
	        while ((temp = reader.readLine()) != null) {
	            System.out.println("Line"+ line + ":" +temp);	           
	        	String[] split=temp.split(" ");
	        	System.out.println("split:"+split[0]);
	        	System.out.println("split:"+split[1]);
	        	this.setAccNo(split[0]);
            	this.setFunds(Double.valueOf(split[1]));
            	System.out.println("accNo:"+accNo);
            	System.out.println("funds:"+funds);
            	clearFunds(accNo,funds);
            	line++;
            	
            	
	        }
	        FileWriter fileWriter = new FileWriter("funds.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        bufferedWriter.append("");
	        bufferedWriter.close();
	        
	     }catch(Exception ex){
	    	 System.out.println("error1");	
	      }
	        
	      
	}
	public void clearFunds(String accNo, double funds){
		 File file = new File("account.txt");
		 BufferedReader reader = null;
	     String temp = null;
	     int line=0;
	     try {
	        
	        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	        StringBuffer bf = new StringBuffer();
	        	        
	        while ((temp = reader.readLine()) != null) {
	            System.out.println("Line"+ line + ":" +temp);	           
	        	
	        	 if(temp.contains(accNo)&&accNo.length()==6){
		            	String[] split=temp.split(" ");	        	
		            	System.out.println("split:"+split[0]);
		            	System.out.println("split:"+split[1]);
		            	System.out.println("split:"+split[2]);
		            	
		            	this.setAccNo(split[0]);
		            	this.setBalance(Double.valueOf(split[1]));
		            	this.setAccType(split[2]);
		            	this.setActive(Boolean.valueOf(split[3]));
		            	
		            }
	        	 	temp=temp.trim();          	
	            	if(temp.indexOf(accNo) == -1){ //Лђеп!r1.startsWith(special)	            	
						bf.append(temp).append("\r\n");										
					}
            										  	        
            	line++;
            	
            	
	        }
	        
	        reader.close();
	        FileWriter fileWriter = new FileWriter("account.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        balance=balance+funds;
	        String record=accNo+" "+balance+" "+this.getAccType()+" "+this.getActive();
	        bufferedWriter.write(bf.toString());
	        bufferedWriter.append(record);
	        bufferedWriter.close();
	        System.out.println("your balance is:"+balance);
	        System.out.println("your funds is:"+funds);
       
	     } catch (FileNotFoundException fnfe) {
	        // TODO Auto-generated catch block
	        fnfe.printStackTrace();
	     } catch (IOException ioe) {
	        // TODO Auto-generated catch block
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
	}

}
