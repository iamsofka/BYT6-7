package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("testAddRemoveTimedPayment_PAYMENT", 1, 1, new Money(100, SEK), SweBank, "Alice");
		assertTrue("Should exist", testAccount.timedPaymentExists("testAddRemoveTimedPayment_PAYMENT"));
		testAccount.removeTimedPayment("testAddRemoveTimedPayment_PAYMENT");
		assertFalse("Should not exist", testAccount.timedPaymentExists("testAddRemoveTimedPayment_PAYMENT"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("testTimedPayment_PAYMENT", 1, 1, new Money(100, SEK), SweBank, "Alice");
		testAccount.tick();
		testAccount.tick();
		assertEquals("Should be '100000.00 SEK' - '1.00 SEK' (99999.00 SEK) because of the payment", 9999900, testAccount.getBalance().getAmount(), 0);

	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(10000000, SEK));
		assertEquals("Should be zero after the transfer", 0, testAccount.getBalance().getAmount(), 0);
	}
	
	@Test
	public void testGetBalance() {
		assertEquals("Should be equal to value set up in the @Before function", 10000000, testAccount.getBalance().getAmount(), 0);
	}
}
