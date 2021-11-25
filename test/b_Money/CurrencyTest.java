package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("Should be equal to SEK", "SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals("Should be equal to 0.15", 0.15, SEK.getRate(), 0);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.2);
		assertEquals("Should be equal to 0.2", 0.2, SEK.getRate(), 0);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals("", 15, SEK.universalValue(100), 0);
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals("", 1333, SEK.valueInThisCurrency(1000, DKK), 0);
	}

}
