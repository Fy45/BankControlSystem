package BankControlSystem;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

public class CustomerTest extends TestCase{

	@Test
	public void test() {
		
		Customer c = new Customer("alice","bupt","1994 8 8","saver");
		assertEquals("",c.getName());
		assertEquals("bupt",c.getAddress());
		assertEquals("1994 8 8",c.getDateOfBirth());
		assertEquals("saver",c.getAccType());
	}

}
