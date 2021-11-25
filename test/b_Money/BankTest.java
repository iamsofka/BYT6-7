package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		SweBank.openAccount("Bob");
		SweBank.deposit("Bob", new Money(10000, SEK));
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("Should be equal to SweBank", "SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("Should be SEK", "SEK", SweBank.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("testaccount");
		boolean exist = false;
		try {
			SweBank.openAccount("testaccount");
		} catch (AccountExistsException e) {
			exist = true;
		}
		assertTrue("account with 'testaccount' name should exist in the SweBank", exist);
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		assertEquals("Should be equal to 200.00 SEK (account already had 100.00)", 20000, SweBank.getBalance("Ulrika"), 0);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Ulrika", new Money(10000, SEK));
		assertEquals("Should be equal to 0.00 SEK", 0, SweBank.getBalance("Ulrika"), 0);
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals("Should be equal to 100.00 SEK", 10000, SweBank.getBalance("Ulrika"), 0);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Ulrika", SweBank, "Bob", new Money(10000, SEK));
		assertEquals("Should be equal to 0 after the transfer", 0, SweBank.getBalance("Ulrika"), 0);
		assertEquals("Should be equal to 20000 after the transfer", 20000, SweBank.getBalance("Bob"), 0);
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Ulrika", "testTimedPayment_PAYMENT", 1, 1, new Money(1000, SEK), SweBank, "Bob");
		SweBank.tick();
		SweBank.tick();
		assertEquals("Should be equal to 9000 after the timed payment", 9000, SweBank.getBalance("Ulrika"), 0);
		assertEquals("Should be equal to 11000 after the timed payment", 11000, SweBank.getBalance("Bob"), 0);
	}
}
