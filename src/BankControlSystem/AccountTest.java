package BankControlSystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	@Test
	public void test() {
		Account a = new Account(111111,111111);
		assertEquals(111111,a.getAccNo());
		assertEquals(111111,a.getPin());
		
	}

}
