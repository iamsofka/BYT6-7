package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("Should be equal to 10000", 10000,SEK100.getAmount(), 0);
	}

	@Test
	public void testGetCurrency() {
		assertEquals("Should be equal to SEK", "SEK", SEK100.getCurrency().getName());
	}

	@Test
	public void testToString() {
		assertEquals("Should be equal to '100.0 SEK'", "100.0 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals("Should be equal to 1500", 1500, SEK100.universalValue(), 0);
	}

	@Test
	public void testEqualsMoney() {
		assertTrue("Should be equal", SEK100.equals(SEK100));
	}

	@Test
	public void testAdd() {
		assertEquals("Should be equal to 10000", 10000, SEK0.add(SEK100).getAmount(), 0);
	}

	@Test
	public void testSub() {
		assertEquals("Should be equal to -10000", -10000, SEK0.sub(SEK100).getAmount(), 0);
	}

	@Test
	public void testIsZero() {
		assertTrue("Should be true", SEK0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals("Should be -10000", -10000, SEK100.negate().getAmount(), 0);
	}

	@Test
	public void testCompareTo() {
		assertEquals("Should be equal", 0, SEK100.compareTo(SEK100));
		assertEquals("Should be 1, because SEK200 is bigger than SEK100", 1, SEK200.compareTo(SEK100));
		assertEquals("Should be -1, because SEK0 is smaller than SEK100", -1, SEK0.compareTo(SEK100));
	}
}
