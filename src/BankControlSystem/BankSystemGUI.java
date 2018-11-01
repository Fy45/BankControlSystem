package BankControlSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class BankSystemGUI extends JFrame{
	JPanel homePanel = new JPanel(new BorderLayout());
	JPanel registerPanel = new JPanel(new BorderLayout());	
	JPanel loginPanel = new JPanel(new BorderLayout());
	JPanel depositPanel = new JPanel(new GridLayout(3,1));
	JPanel loginConfirmPanel=new JPanel(new BorderLayout());
	JPanel pC,pCC,pCN;
	int homeTime = 0;
	int registerTime = 0;
	int loginTime = 0;
	int depositTime = 0;
	
	public BankSystemGUI(){
		setTitle("BANKING SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);		
		home(homeTime);
	}
	
	public static void main(String[] args){
				
		BankSystemGUI newGame = new BankSystemGUI();						
		
	}
	
	
	/*method for the GUI of the home page of the bank*/
	void home(int i) {		
		
		System.out.print("Enter home page method!\n");
		
		JPanel action = new JPanel();
		JPanel prompt = new JPanel();
		JButton register = new JButton("To Register");
		JButton login = new JButton("To Login");
		JButton deposit = new JButton("To Deposit");
		JButton clear=new JButton("Clear Funds");
		
		if(i == 0){
			//Add myPanel to myFrame
			
			prompt.add(new JLabel("Welcom to our bank!"));
			homePanel.add(BorderLayout.NORTH,prompt);	
			
			action.add(register);
			action.add(login);
			action.add(deposit);
			action.add(clear);
			homePanel.add(BorderLayout.CENTER,action);			
			
			add(homePanel);	
			homeTime++;
		}
		
		register.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						homePanel.setVisible(false);
						remove(homePanel);
						register(registerTime);
						
					}					
				}
				
		);
		login.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						homePanel.setVisible(false);
						remove(homePanel);
						
						login(loginTime);
					}					
				}
				
		);
		
		deposit.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						homePanel.setVisible(false);
						remove(homePanel);
						deposit(depositTime);
					}					
				}
				
		);
		clear.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						ClearSystem cs=new ClearSystem();
						cs.readFunds();						
						System.out.println("clear successfully");						
					}					
				}
				
		);
			
	}
	
	/*For register page*/
	void register(int i){
		
		System.out.print("Enter register page method!\n");
		
		final JPanel content = new JPanel();
		JPanel action = new JPanel();
		JPanel radioButton = new JPanel();
		JPanel begin = new JPanel();
			
			
		final JTextField name = new JTextField(35);
		final JTextField addr = new JTextField(35);
		final JTextField dateOfBirth = new JTextField(35);

		final JRadioButton saver = new JRadioButton("Saver Account",true);
		final JRadioButton junior = new JRadioButton("Junior Account",true);
		final JRadioButton current = new JRadioButton("Current Account",true);
		ButtonGroup accType = new ButtonGroup();
		accType.add(saver);
		accType.add(junior);
		accType.add(current);
		
		JButton register = new JButton("Register");
		JButton back = new JButton("Back");
		

		
		
		if(i == 0){
			begin.add(new JLabel("Please Input Your Basic Information\n"));
			registerPanel.add(BorderLayout.NORTH,begin);
			
			content.add(new JLabel("Name:            "));
			content.add(name);
			content.add(new JLabel("Address:       "));
			content.add(addr);
			content.add(new JLabel("Date of birth: "));
			content.add(dateOfBirth);						
			content.add(new JLabel("Please Choose the Type of Account to Be Opened"));
			
			radioButton.add(saver);
			radioButton.add(junior);
			radioButton.add(current);
			content.add(radioButton);
		
			action.add(register);
			action.add(back);
						
			registerPanel.add(BorderLayout.CENTER,content);
			registerPanel.add(BorderLayout.SOUTH,action);				
						
			registerTime++;
		}
		
		add(registerPanel);
		registerPanel.setVisible(true);
		
		
		back.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						registerPanel.setVisible(false);
						remove(registerPanel);
						add(homePanel);
						homePanel.setVisible(true);
						home(homeTime);
					}					
				}
				
		);
		register.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String type;						
						if(saver.isSelected()){
							type="saver";					
							Customer c=new Customer(name.getText(),addr.getText(),dateOfBirth.getText(),type);					
							switch(c.customerReg()){
							case 0:JOptionPane.showMessageDialog(null, "Bad Faith", "Failed",JOptionPane.ERROR_MESSAGE);break;
							case 1:JOptionPane.showMessageDialog(null, "Successful Registration");break;
							case 2:JOptionPane.showMessageDialog(null, "You have registered "+type+" count", "Failed",JOptionPane.ERROR_MESSAGE);break;
							case 3:JOptionPane.showMessageDialog(null, "Welcome,Successful Registration");break;
							default: JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);	
							}
							
						}
						if(junior.isSelected()){
							type="junior";							
							String[] split=dateOfBirth.getText().split(" ");
							int dateYear=Integer.parseInt(split[0]);
							if(dateYear>=1999){
								Customer c=new Customer(name.getText(),addr.getText(),dateOfBirth.getText(),type);
								switch(c.customerReg()){
								case 0:JOptionPane.showMessageDialog(null, "Bad Faith", "Failed",JOptionPane.ERROR_MESSAGE);break;
								case 1:JOptionPane.showMessageDialog(null, "Successful Registration");break;
								case 2:JOptionPane.showMessageDialog(null, "You have registered "+type+" count", "Failed",JOptionPane.ERROR_MESSAGE);break;
								case 3:JOptionPane.showMessageDialog(null, "Welcome,Successful Registration");break;
								default: JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);	
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Age is not satified","ERROR",JOptionPane.ERROR_MESSAGE);
							}
							
						}
						if(current.isSelected()){
							type="current";
							Customer c=new Customer(name.getText(),addr.getText(),dateOfBirth.getText(),type);
							switch(c.customerReg()){
							case 0:JOptionPane.showMessageDialog(null, "Bad Faith", "Failed",JOptionPane.ERROR_MESSAGE);break;
							case 1:JOptionPane.showMessageDialog(null, "Successful Registration");break;
							case 2:JOptionPane.showMessageDialog(null, "You have registered "+type+" count", "Failed",JOptionPane.ERROR_MESSAGE);break;
							case 3:JOptionPane.showMessageDialog(null, "Welcome,Successful Registration");break;
							default: JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);	
							}
						}
						
						
						
						
						
					}
				});
	}
	
	
	/*For login page*/
	void login(int i){
		
		System.out.print("Enter login page method!\n");
		
		final JPanel beginPrompt = new JPanel();
		final JPanel content = new JPanel();
		JPanel action = new JPanel();		
		JLabel prompt = new JLabel("Please Input Your Information\n");
		JLabel promptAccNum = new JLabel("Account number:");
		JLabel promptPwd = new JLabel("Password:            ");
		final JLabel showInfo=new JLabel();
		final JTextField accNo = new JTextField(30);
		final JTextField pw = new JTextField(30);
		//accNo.setText("");
		//pw.setText("");		
		JButton loginConfirm = new JButton("Login");
		JButton back = new JButton("Back");
					
		
		if(i == 0){
			
			beginPrompt.add(prompt);
			loginPanel.add(BorderLayout.NORTH,beginPrompt);
			
			content.add(promptAccNum);
			content.add(accNo);
			content.add(promptPwd);
			content.add(pw);
			content.add(showInfo);
			loginPanel.add(BorderLayout.CENTER,content);
			
			action.add(loginConfirm);	
			action.add(back);
			content.add(action);
			loginPanel.add(BorderLayout.SOUTH,action);
			
			loginTime++;
		}		
		add(loginPanel);
		loginPanel.setVisible(true);
		
		loginConfirm.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
					  try{	
						  if(accNo.getText().length()==6&&pw.getText().length()==6){
							  Account a=new Account(Integer.parseInt(accNo.getText()),Integer.parseInt(pw.getText()));						
							  if(a.loginAcc(accNo.getText(),pw.getText())){								 
								  loginPanel.setVisible(false);
								  remove(loginPanel);
								  loginConfirm(accNo.getText());
							
							  }
							  else{
								  showInfo.setText("You haven't registered");
								  System.out.println("you havent't registered");
							  }					
						  }
						  else{
							  showInfo.setText("Error! accNo or password is wrong!");
						  }
					  }catch(Exception ex){
						  showInfo.setText("Error! you need to input your accNo and password");
					  }
					}				 
				}					
		);
		back.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						
					    loginPanel.setVisible(false);						
						remove(loginPanel);
						
						add(homePanel);
						homePanel.setVisible(true);
						home(homeTime);
					}					
				}
				
		);
			
	}
		/* for login successful page*/
	    void loginConfirm(final String accNo){	    	
	    	loginConfirmPanel.setVisible(false);
	    	remove(loginConfirmPanel);
	    	final JPanel loginConfirmPanel=new JPanel(new BorderLayout());
	    	JLabel showInfo=new JLabel();
	    	showInfo.setText(" Welcom: "+accNo+"    Login successful. ");
			JPanel pN =new JPanel();			
			pC =new JPanel(new BorderLayout());
		    JPanel pS =new JPanel();
		    pCN=new JPanel();
		    pCC=new JPanel();
			final JButton withdraw=new JButton("Withdraw");
			final JButton suspend=new JButton("Suspend");
			final JButton reInstate=new JButton("Re-instated");
			final JButton close=new JButton("Close Acc");
			JButton back=new JButton("Back to home");
			
			pCN.add(withdraw);
			pCN.add(close);			
			pCN.add(reInstate);						
			pN.add(showInfo);						
			pC.add(pCN,BorderLayout.NORTH);
			pC.add(pCC,BorderLayout.CENTER);
			pS.add(back);
			loginConfirmPanel.add(pN,BorderLayout.NORTH);
			loginConfirmPanel.add(pC,BorderLayout.CENTER);
			loginConfirmPanel.add(pS,BorderLayout.SOUTH);
					
			add(loginConfirmPanel);
			loginConfirmPanel.setVisible(true);
				
			
			withdraw.addActionListener(
					new ActionListener(){								
						public void actionPerformed(ActionEvent e) {
							
							pCC.setVisible(false);
							//loginConfirmPanel.remove(pC);
							pC.remove(pCC);
							pCC=new JPanel();							
							final JTextField cashOut=new JTextField(15);
							JButton get=new JButton("Get");
							final JCheckBox notice=new JCheckBox("Notice");
							final JLabel showInfo=new JLabel();
							pCC.add(BorderLayout.NORTH,new JLabel("******************************Welcome to Withdraw Money*******************************"));
							pCC.add(new JLabel(" Money you want to get out: "));
							pCC.add(cashOut);
							pCC.add(get);
							pCC.add(notice);							
							pCC.add(showInfo);
							//loginConfirmPanel.add(pC);
							pC.add(pCC,BorderLayout.CENTER);
							pCC.setVisible(true);
											
							get.addActionListener(
									new ActionListener(){								
										public void actionPerformed(ActionEvent e) {
											if(notice.isSelected()){
												try{
													double tcashOut=Integer.parseInt(cashOut.getText());
													Account acc=new Account();											
													acc.withdraw(accNo,tcashOut,true);
													showInfo.setText("Acc type:  "+acc.getAccType()+"      Balance:  "+acc.getBalance());
												}catch (Exception ex){												
													showInfo.setText("Error!!please input amount of money you want to get");
												}
											}
											else{
												try{
													double tcashOut=Integer.parseInt(cashOut.getText());
													Account acc=new Account();											
													acc.withdraw(accNo,tcashOut,false);
													showInfo.setText("Acc type:  "+acc.getAccType()+"      Balance:  "+acc.getBalance());
												}catch (Exception ex){												
													showInfo.setText("Error!!please input amount of money you want to get");
												}
											}
			
										}									            																															            
									 }																															
							);
												    
						}					
					}
					
			);
			
			
			reInstate.addActionListener(
					new ActionListener(){								
						public void actionPerformed(ActionEvent e) {						
							Account a =new Account();
							a.reInstate(accNo);
						}					
					}
					
			);
			close.addActionListener(
					new ActionListener(){								
						public void actionPerformed(ActionEvent e) {
							pCC.setVisible(false);
							loginConfirmPanel.remove(pCC);
							pCC=new JPanel();
							Account acc = new Account();
							if(acc.closeAcc(accNo)&&(acc.closeAccPw(accNo))){
								pCC.add(new JLabel("close successfully"));
							}
							else{
								pCC.add(new JLabel("close unsuccessfully"));
								JOptionPane.showMessageDialog(null, "Balance is not 0, you can not close it","ATTENTION",JOptionPane.ERROR_MESSAGE);
							}
							
							pC.add(pCC,BorderLayout.CENTER);
							pCC.setVisible(true);
						}					
					}
					
			);
			back.addActionListener(
					new ActionListener(){								
						public void actionPerformed(ActionEvent e) {
							
						    loginConfirmPanel.setVisible(false);						
							remove(loginConfirmPanel);							
							add(homePanel);
							homePanel.setVisible(true);
							home(homeTime);
						}					
					}
					
			);
			
	}
	
	
	/*For deposit page*/
	void deposit(int i){
		
		System.out.print("Enter deposit page method!\n");
		JPanel content = new JPanel();
		final JPanel money = new JPanel();
		final JPanel action = new JPanel();		
		
		JLabel promptAccNum = new JLabel("Please input the bank account number that you want to deposit money into:");
		JLabel promptDepositType = new JLabel("Please choose the type of deposit:");
		final JTextField accNum = new JTextField(40);
		
		final JLabel promptCash = new JLabel("Please input the amount of money to deposit:");
		final JTextField cashAmount = new JTextField(40);
		final JLabel promptCheque = new JLabel("Please input the cheque amount:");
		final JRadioButton depositType1 = new JRadioButton("Cash",true);
		final JRadioButton depositType2 = new JRadioButton("Cheque",true);
		ButtonGroup depositType = new ButtonGroup();
		depositType.add(depositType1);
		depositType.add(depositType2);
		final JPanel showInfo=new JPanel();
		final JLabel showMoney=new JLabel();
		final JButton confirm = new JButton("Confirm");
		final JButton back = new JButton("Back");
		
		
		if(i == 0){
			money.setVisible(false);
			//action.setVisible(false);						
			depositPanel.remove(money);
			//depositPanel.remove(action);
			
			showInfo.add(showMoney);
			
			content.add(promptAccNum);
			content.add(accNum);
			content.add(promptDepositType);
			content.add(depositType1);
			content.add(depositType2);
			content.add(showInfo);
			depositPanel.add(content);
					
			money.add(promptCash);
			money.add(cashAmount);
			
			depositPanel.add(money);
					
			action.add(confirm);	
			action.add(back);
			depositPanel.add(action);
						
			depositTime++;
		}
		
		add(depositPanel);
		depositPanel.setVisible(true);
		
		back.addActionListener(
				new ActionListener(){								
					public void actionPerformed(ActionEvent e) {
						loginPanel.setVisible(false);
						remove(depositPanel);
						add(homePanel);
						homePanel.setVisible(true);
						home(homeTime);
					}					
				}
				
		);
	
		depositType1.addActionListener(
				new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {						
												
						money.setVisible(false);
						money.remove(promptCheque);
						depositPanel.remove(money);												
						
						money.add(promptCash);
						money.add(cashAmount);				
						
						depositPanel.add(money);
						depositPanel.add(action);
						money.setVisible(true);													
					}
					
				}
				);
		
		depositType2.addActionListener(
				new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						
						money.setVisible(false);					
						depositPanel.remove(money);												
						money.remove(promptCash);
						money.add(promptCheque);
						money.add(cashAmount);			
						
						depositPanel.add(money);
						depositPanel.add(action);
						money.setVisible(true);			
						
					}
					
				}
				);
		confirm.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							
							double tcashAmount=Integer.parseInt(cashAmount.getText());
							Account acc=new Account();						
													
							if(depositType1.isSelected()){
								if(accNum.getText().length()==6){
									acc.payCash(accNum.getText(),tcashAmount);
									showMoney.setText(" Dear "+accNum.getText()+" Your balance is :"+acc.getBalance());
								}
								else{
									showMoney.setText("ERROR, Your accNo is not six numbers");
								}
							}
						
							else if(depositType2.isSelected()){
								if(accNum.getText().length()==6){
									
									acc.payCheck(accNum.getText(),tcashAmount);
									showMoney.setText(" Dear "+accNum.getText()+"Your funds is :"+acc.getFunds());
								}
								else{
									showMoney.setText("ERROR, Your accNo is not six numbers");
								}
							}
						
						}catch(Exception ex){
								System.out.println("please input your ACCOUTN number or MONEY");
						}
						
					}
				}
				);
		
	}
		

}
