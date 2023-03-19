package Junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import methods.Money;
import methods.MoneyBag;

public class MoneyBagTest {
	
	private MoneyBag moneyBag;
	private Money m50ILS;
	private Money m200USD;

	@Before
	public void setUp() throws Exception {
		m50ILS = new Money(50, "ILS");
		m200USD = new Money(200, "USD");
	}

	// MoneyBag "moneyBag" contains: 50 ILS and 200 USD
	
	@Test
	public void testAddMoney_AddToExistMoneyType() {
		// 1
		// checking functionality: adding the same type of money to MoneyBag
		// input data: object Money amount 15, type ILS
		// expected result: 65 ILS and 200 USD
		moneyBag = new MoneyBag(m50ILS, m200USD);
		MoneyBag result = (MoneyBag) moneyBag.addMoney(new Money(15, "ILS"));
		MoneyBag expected = new MoneyBag(new Money(65, "ILS") , m200USD);
		assertEquals(result, expected);
	}
	
	@Test
	public void testAddMoney_SubMoney() {
		// 2
		// checking functionality: subtracting the same type of money from MoneyBag
		// input data: object Money amount -20, type USD
		// expected result: 50 ILS and 180 USD
		moneyBag = new MoneyBag(m50ILS, m200USD);
		MoneyBag result = (MoneyBag) moneyBag.addMoney(new Money(-20, "USD"));
		MoneyBag expected = new MoneyBag(m50ILS, new Money(180, "USD"));
		assertEquals(result, expected);
	}
	
	@Test
	public void testAddMoney_AddNewMoneyType() {
		// 3
		// checking functionality: adding a new type of money to MoneyBag
		// input data: object Money amount 150, type CHF
		// expected result: 50 ILS, 200 USD and 150 CHF
		moneyBag = new MoneyBag(m50ILS, m200USD);
		MoneyBag result = (MoneyBag) moneyBag.addMoney(new Money(150, "CHF"));
		MoneyBag expected = new MoneyBag(new Money[] {m50ILS, m200USD, new Money(150, "CHF")});
		assertEquals(result, expected);
	}
	
	@Test
	public void testAddMoney_AddNullMoney() {
		// 4
	    // checking functionality: adding null to MoneyBag
	    // input data: moneyBag with 50 ILS and 200 USD
	    // expected: throws NullPointerException
		moneyBag = new MoneyBag(m50ILS, m200USD);
		boolean isExceptionThrown = false;
		try 
		{
			moneyBag.addMoney(null);
		} 
		catch (NullPointerException e) 
		{
			isExceptionThrown = true;
		}
		assertTrue(isExceptionThrown);
	}

	@Test
	public void testContains_SameAmountSameCurrency() {
		// 1
		// checking functionality: checking if the moneyBag contains 50 ILS. the right amount and type of money
		// input data: moneyBag with 50 ILS, 200 USD
		// expected result: true
		moneyBag = new MoneyBag(m50ILS, m200USD);
		assertTrue(moneyBag.contains(m50ILS));
	}
	
	@Test
	public void testContains_DiffAmountSameCurrency() {
		// 2
		// checking functionality: checking if the moneyBag contains 100 USD. wrong amount of money with the same type
		// input data: moneyBag with 50 ILS, 200 USD
		// expected result: false
		moneyBag = new MoneyBag(m50ILS, m200USD);
		assertFalse(moneyBag.contains(new Money(100, "USD")));
	}
	
	@Test
	public void testContains_SameAmountDiffCurrency() {
		// 3
		// checking functionality: checking if the moneyBag contains different type of money with amount that exist in the moneyBag
		// input data: moneyBag with 50 ILS, 200 USD and check for 50 CHF
		// expected result: NullPointerException
		moneyBag = new MoneyBag(m50ILS, m200USD);
		try {
			assertFalse(moneyBag.contains(new Money(50, "CHF")));	
		}
		catch (NullPointerException e) {}
	}
	
	@Test
	public void testContains_Null() {
		// 4
		// checking functionality: checking if MoneyBag contains null
		// input data: moneyBag with 50 ILS, 200 USD and check for NULL
		// expected result: throws NullPointerException
		moneyBag = new MoneyBag(m50ILS, m200USD);
		try {
			assertFalse(((MoneyBag) moneyBag).contains(null));
		} 
		catch (NullPointerException e) 
		{
			assertTrue(true);
		}	
	}

}
