package BankControlSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Account {
	protected int accNo;
	protected int pin;
	protected String accType;
	protected Customer customer;
	protected double balance;
	protected double funds;
	protected double overDraftLimit;
	protected boolean isActive;
	protected boolean noticeNeeded;
	
	public Account(int accNo, int pin){
		this.accNo = accNo;
		this.pin = pin;
	}
	public Account(String accType){
		this.accType=accType;
		this.balance = 0.0;
		
	}
	public Account(){
		
	}	
	
	
	
	
	public boolean getActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public double getFunds() {
		return funds;
	}
	public void setFunds(double funds) {
		this.funds = funds;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getOverDraftLimit() {
		return overDraftLimit;
	}
	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public boolean isNoticeNeeded() {
		return noticeNeeded;
	}
	public void setNoticeNeeded(boolean noticeNeeded) {
		this.noticeNeeded = noticeNeeded;
	}
	public int getAccNo() {
		return accNo;
	}

	public int getPin() {
		return pin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getBalance() {
		return balance;
	}


	
	
	private void generatePin(){
		Random r = new Random();
		pin = (100000+r.nextInt(900000));
	}	
	private void generateAccNo(){
		Random r = new Random();
		accNo = (100000+r.nextInt(900000));
	}
	public void newAcc(){
	     try {	    	 	        	    
			 FileWriter fileWriter = new FileWriter("accpw.txt",true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		     generatePin();
		     generateAccNo();
		     String accPw=this.getAccNo()+" "+this.getPin();
		     bufferedWriter.append(accPw).append("\r\n");	
		     bufferedWriter.close();
		     /*add to account.txt accNo money accType*/
		     FileWriter fw = new FileWriter("account.txt",true);
			 BufferedWriter bw = new BufferedWriter(fw);
		     String accInfo=this.getAccNo()+" 0.0 "+this.getAccType()+" "+true;
		     bw.append(accInfo).append("\r\n");	
		     bw.close();		     
		     
		     System.out.println("acc:"+this.getAccNo());
		     System.out.println("pw:"+this.getPin());
			
		}catch (IOException ioe) {
	        // TODO Auto-generated catch block
	        ioe.printStackTrace();
	     }	 
	}
	
	public boolean loginAcc(String taccNo, String tpin){
		 File file = new File("accpw.txt");
		 BufferedReader reader = null;
	     String temp = null;
	     boolean flag=false;
	     //int line=0;
	     System.out.println("your input accNo: "+taccNo);
	     System.out.println("your input password:"+tpin);
	     try {
	        
	        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	       // StringBuffer bf = new StringBuffer();
	        	        
	        while ((temp = reader.readLine()) != null) {
	        	//System.out.println("Line"+ line + ":" +temp);
	            if(temp.contains(taccNo)){
	            	String[] split=temp.split(" ");
	            	//accNo=Integer.parseInt(split[0]);
	            	//customerList.add(new Account(accNo,split[1]));   	
	            	if(tpin.equals(split[1]))
	            		flag=true;	            		            			            			            	
	            	else{
	            		System.out.println("password is wrong");
	            		
	            	}
	            	break;	
	            }
	            	                       	           	              
	            //line ++ ;
	        }
	        
	        reader.close();	 
        
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
	return flag;	
	}
	
	
	public void payCash(String accNo, double tcashAmount){		 
		 int flag=0;		 

		 File file = new File("account.txt");
		 BufferedReader reader = null;
	     String temp = null;
	     try {
	        
	        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	        StringBuffer bf = new StringBuffer();
	        	        
	        while ((temp = reader.readLine()) != null) {          
	            
	            if(temp.contains(accNo)&&accNo.length()==6){
	            	String[] split=temp.split(" ");	            	 	
	            	this.setBalance(Double.valueOf(split[1]));	            	
	            	this.setAccType(split[2]);
	            	this.setActive(Boolean.valueOf(split[3]));
	            	
	            	if(this.getActive()==true)
	            		flag=1;//can continue
	            	else
	            		flag=2;
	            }	            
	            temp=temp.trim();
	            if(temp.indexOf(accNo) == -1){ //或者!r1.startsWith(special)	            	
					bf.append(temp).append("\r\n");										
				}	            	           	              
	            
	        }
	        
	        reader.close();	 
	       	       	        
	        if(flag==1){
	        	
		        FileWriter fileWriter = new FileWriter("account.txt");
		        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		        balance=balance+tcashAmount;
		        String record=accNo+" "+balance+" "+this.getAccType()+" "+this.getActive();
		        bufferedWriter.write(bf.toString());
		        bufferedWriter.append(record);
		        bufferedWriter.close();
		        System.out.println("your balance is:"+balance);
		        System.out.println("your funds is:"+funds);
	        }
	        else if(flag==2){
	        	System.out.println("account is suspend");
	        }
	        else{
	        	System.out.println("sorry no this account!in flag");
	        }
	        
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
	  
	 public void payCheck(String accNo,double checkAmount){
		 File file = new File("funds.txt");
		 BufferedReader reader = null;
	     String temp = null;
		 try{
			 reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		        StringBuffer bf = new StringBuffer();
		        	        
		        while ((temp = reader.readLine()) != null) {
		            //System.out.println("Line"+ line + ":" +temp);	           
		            
		            if(temp.contains(accNo)&&accNo.length()==6){
		            	String[] split=temp.split(" ");
		            	this.setFunds(Double.valueOf(split[1]));	           
		            }	            
		            temp=temp.trim();
		            if(temp.indexOf(accNo) == -1){ //或者!r1.startsWith(special)	            	
						bf.append(temp).append("\r\n");										
					}	            	           	              		       
		        }
		        
		        reader.close();
		        
		        FileWriter fileWriter = new FileWriter("funds.txt");
	        	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        	funds=funds+checkAmount;
	        	String record=accNo+" "+funds;
	        	bufferedWriter.write(bf.toString());
	        	bufferedWriter.append(record).append("\r\n");			        
	        	bufferedWriter.close();
		 
		 }catch(Exception ex){
			 System.out.println("no 'funds' file");
		 }
		 	
	 }
	 
	 public void withdraw(String accNo,double cashOut,boolean noticeNeeded){
		  System.out.println(" You want to withdraw: "+cashOut);
		  int line=1;
		  boolean flag=false;
		  
			 File file = new File("account.txt");
			 BufferedReader reader = null;
		     String temp = null;
		     try {
		        
		        reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		        StringBuffer bf = new StringBuffer();
		        	        
		        while ((temp = reader.readLine()) != null) {	           
		            
		            if(temp.contains(accNo)&&accNo.length()==6){
		            	String[] split=temp.split(" ");		            	 	
		            	balance=Double.valueOf(split[1]);		            	
		            	this.setAccType(split[2]);
		            	this.setActive(Boolean.valueOf(split[3]));
		            	if(split[2].equals("current")){
		            		this.setOverDraftLimit(5000);
		            		
		            		flag=true;
		            	}
		            	else{
		            		this.setAccType(split[2]);
		            		
		            		flag=false;
		            	}
		            	
		            }
		          
		            temp=temp.trim();
		            if(temp.indexOf(accNo) == -1){ //或者!r1.startsWith(special)	            	
						bf.append(temp).append("\r\n");										
					}	            	           	              
		            line ++ ;
		        }
		        
		        reader.close();	 
		       	/*flag==true it is a current account. there is overdraftlimit*/       	        
		        if(flag){
		        	
		        	if(this.getActive()==true){
		        		if(balance-cashOut>=-this.getOverDraftLimit()){
			        		FileWriter fileWriter = new FileWriter("account.txt");
					        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					        balance=balance-cashOut;
					        
					        String record=accNo+" "+balance+" "+this.getAccType()+" "+this.getActive();
					        bufferedWriter.write(bf.toString());
					        bufferedWriter.append(record);
					        bufferedWriter.close();
					        System.out.println("your balance is:"+balance);
			        	}
			        	else{
			        		this.setActive(false);
			        		FileWriter fileWriter = new FileWriter("account.txt");
					        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);					        
					        
					        String record=accNo+" "+balance+" "+this.getAccType()+" "+this.getActive();
					        bufferedWriter.write(bf.toString());
					        bufferedWriter.append(record);
					        bufferedWriter.close();
					        System.out.println("your balance is:"+balance);
			        		System.out.println("unsuccessfully "+accNo+" has a overdraft limit: "+-this.getOverDraftLimit()+" a letter is sent to you");
			        	}
		        	}
		        	else{
		        		System.out.println("sorry your account is suspend");		        	}
		        	
		        	
			        
		        }
		        /*flag==false it is saver and junior account. there is no overdraftlimit*/
		        else{
		        	
		        	if(this.getActive()==true){
		        		if(this.getAccType().equals("saver")&&noticeNeeded==true){
			        		if(balance-cashOut>=0){
				        		FileWriter fileWriter = new FileWriter("account.txt");
						        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						        balance=balance-cashOut;				        
						        String record=accNo+" "+balance+" "+this.getAccType()+" "+this.getActive();
						        bufferedWriter.write(bf.toString());
						        bufferedWriter.append(record);
						        bufferedWriter.close();
						        System.out.println("your money is:"+balance);
				  			    
				  		  }
				  		  else{
				  			  System.out.println("No enough money!!");
				  		  }
			        	}
			        	if(this.getAccType().equals("saver")&&noticeNeeded==false){
			        		System.out.println("You are saver acc, if you want to withdraw, you need notice first");
			        	}
			        	else{
			        		if(this.getAccType().equals("junior")){
				        		if(balance-cashOut>=0){
					        		FileWriter fileWriter = new FileWriter("account.txt");
							        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
							        balance=balance-cashOut;				        
							        String record=accNo+" "+balance+" "+this.getAccType()+" "+this.getActive();
							        bufferedWriter.write(bf.toString());
							        bufferedWriter.append(record);
							        bufferedWriter.close();
							        System.out.println("your balance is:"+balance);
					  			    
					  		  }
					  		  else{
					  			  System.out.println("No enough money!!");
					  		  }
				        	}
			        	}
		        	}
		        	else{
		        		System.out.println("sorry your account is suspend");
		        	}
		        	
		        	
		        	
		        }
		        
		               
		        
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
	 public boolean closeAccPw(String accNo){
		 File file = new File("accpw.txt");		 
	     String temp = null;
	     boolean flag=false;
	     try{
	    	 BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	    	 StringBuffer bf = new StringBuffer();	        
		        while ((temp = reader.readLine()) != null) {		            
		            if(temp.contains(accNo)&&accNo.length()==6){
		            	String[] split=temp.split(" ");		            	 	
		            	flag=true;
		            	
		            }
		            temp=temp.trim();
		            if(temp.indexOf(accNo) == -1){ //或者!r1.startsWith(special)	            	
						bf.append(temp).append("\r\n");										
					}
		            
		        }
		        reader.close();
		        
		 if(flag){
			 FileWriter fileWriter = new FileWriter("accpw.txt");
		     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		     
		     bufferedWriter.write(bf.toString());
		     bufferedWriter.close();
		     System.out.println(accNo+" is closed and deleted from the accpassword list. ");
		     }
	        
	     }catch(Exception ex){
	    	 flag=false;
	     }
	     
		return flag;
	 }
	 
	 public boolean closeAcc(String accNo){
		 File file = new File("account.txt");		 
	     String temp = null;
	     boolean flag=true;
	     try{
	    	 BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	    	 StringBuffer bf = new StringBuffer();	        
		        while ((temp = reader.readLine()) != null) {		            
		            if(temp.contains(accNo)&&accNo.length()==6){
		            	String[] split=temp.split(" ");		            	 	
		            	balance=Double.valueOf(split[1]);		            	
		            		         	
		            	if(balance==0){		            		
		            		flag=true;		            		
		            	}
		            	else{		            				            		
		            		flag=false;
		            	}
		            	
		            }
		            temp=temp.trim();
		            if(temp.indexOf(accNo) == -1){ //或者!r1.startsWith(special)	            	
						bf.append(temp).append("\r\n");										
					}
		            
		        }
		        reader.close();
		        
		 if(flag){
			 FileWriter fileWriter = new FileWriter("account.txt");
		     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		        		  
		     bufferedWriter.write(bf.toString());
		     bufferedWriter.close();
		     System.out.println(accNo+" is closed and deleted from the account list. ");
		     
		    
		 }
		        
		        
		        
	     }catch(Exception ex){
	    	 flag=false;
	     }
	     
	   
	     
	     
		return flag;
	 }
	 
	 public void reInstate(String accNo){
		 File file = new File("account.txt");		 
	     String temp = null;
	     boolean flag=false;
	     try{
	    	 BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	    	 StringBuffer bf = new StringBuffer();	        
		        while ((temp = reader.readLine()) != null) {		            
		            if(temp.contains(accNo)&&accNo.length()==6){
		            	String[] split=temp.split(" ");		            	 	
		            	this.setBalance(Double.valueOf(split[1]));
		            	this.setAccType(split[2]);
		            	flag=true;	            	
		            	
		            }
		            temp=temp.trim();
		            if(temp.indexOf(accNo) == -1){ //或者!r1.startsWith(special)	            	
						bf.append(temp).append("\r\n");										
					}
		            
		        }
		        reader.close();
		        
		 if(flag){
			 FileWriter fileWriter = new FileWriter("account.txt");
		     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		     String record=accNo+" "+balance+" "+this.getAccType()+" "+true;
		     bufferedWriter.write(bf.toString());
		     bufferedWriter.append(record);
		     bufferedWriter.close();
		     System.out.println(accNo+" is active. ");
		     
		    
		 }
		        
		        
		        
	     }catch(Exception ex){
	    	 flag=false;
	     }
	   
	 }
	 
}
