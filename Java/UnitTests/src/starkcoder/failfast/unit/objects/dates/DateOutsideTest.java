package starkcoder.failfast.unit.objects.dates;
/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2015 Keld Oelykke
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import starkcoder.failfast.FailFast;
import starkcoder.failfast.IFailFast;
import starkcoder.failfast.SFailFast;
import starkcoder.failfast.checks.Checker;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.Failer;
import starkcoder.failfast.fails.IFailer;
import starkcoder.failfast.templates.comparables.IComparableOutsideTest;

/**
 * Fail-fast unit test of {link:IObjectDateOutsideCheck} and {link:IObjectDateOutsideFail}.
 * 
 * @author Keld Oelykke
 */
public class DateOutsideTest implements IComparableOutsideTest<Date> {

	private IChecker checker;
	private IFailer failer;
	private String toString = null;
	
	@Override
	public String toString() {
		return this.toString;
	}

	@Rule
	public TestWatcher watcher = new TestWatcher() {
	   protected void starting(Description description) {
		   toString = description.getTestClass().getSimpleName() + "." + description.getMethodName();
	   }
	};

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// this would be in you application startup section
		ICallContractor callContractor = new CallContractor();
		IFailFast failFastOrNull = new FailFast(new Checker(callContractor), new Failer(callContractor), callContractor);
		SFailFast.setFailFastOrNull(failFastOrNull);
		this.checker = SFailFast.getChecker();
		this.failer = SFailFast.getFailer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// this would be in you application shutdown section
		SFailFast.setFailFastOrNull(null);
		this.checker = null;
		this.failer = null;
	}

	// 1st - caller checks
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableOutsideCheckerCallerIsNull() {
		Date valueA = new Date(122000);
		Date valueMin = new Date(123000);
		Date valueMax = new Date(123000);
		if(checker.isDateOutside(null, valueA, valueMin, valueMax))
		{
			failer.failDateOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableOutsideFailerCallerIsNull() {
		Date valueA = new Date(122000);
		Date valueMin = new Date(123000);
		Date valueMax = new Date(123000);
		if(checker.isDateOutside(this, valueA, valueMin, valueMax))
		{
			failer.failDateOutside(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideFailerCallerIsWrong() {
		Date valueA = new Date(122000);
		Date valueMin = new Date(123000);
		Date valueMax = new Date(123000);
		if(checker.isDateOutside(new String("Foo"), valueA, valueMin, valueMax))
		{
			failer.failDateOutside(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchCheckCheck() {
		Date valueA = new Date(122000);
		Date valueMin = new Date(123000);
		Date valueMax = new Date(123000);
		if(checker.isDateOutside(this, valueA, valueMin, valueMax))
		{
			checker.isDateOutside(this, valueA, valueMin, valueMax);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchFail() {
		failer.failDateOutside(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchWrongCheck() {
		Date valueA = new Date(124000);
		Date valueMin = new Date(125000);
		Date valueMax = new Date(124000);
		if(checker.isDateInside(this, valueA, valueMin, valueMax)) // wrong call
		{
			failer.failDateOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchWrongFail() {
		Date valueA = new Date(122000);
		Date valueMin = new Date(123000);
		Date valueMax = new Date(123000);
		if(checker.isDateOutside(this, valueA, valueMin, valueMax))
		{
			failer.failDateInside(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailNoMessage() {
		Date valueA = new Date(121000);
		Date valueMin = new Date(122000);
		Date valueMax = new Date(123000);
		try
		{
			if(checker.isDateOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDateOutside(this, "valueA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailMessage() {
		Date valueA = new Date(122000);
		Date valueMin = new Date(124000);
		Date valueMax = new Date(123000);
		try
		{
			if(checker.isDateOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDateOutside(this, "valueA", "Extra info goes here");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test
	public void testComparableOutsideNoFail() {
		Date valueA = new Date(123000);
		Date valueMin = new Date(123000);
		Date valueMax = new Date(124000);
		if(checker.isDateOutside(this, valueA, valueMin, valueMax))
		{
			failer.failDateOutside(this, "valueA");
		}
		assertTrue("Expected valueA & valueMin to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	
	// 4th - corner cases
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailComparableMin() {
		Date valueA = new Date(Long.MIN_VALUE);
		Date valueMin = new Date(Long.MIN_VALUE + 1);
		Date valueMax = new Date(Long.MAX_VALUE);
		try
		{
			if(checker.isDateOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDateOutside(this, "valueA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailComparableMax() {
		Date valueA = new Date(Long.MAX_VALUE);
		Date valueMin = new Date(Long.MIN_VALUE);
		Date valueMax = new Date(Long.MAX_VALUE - 1);
		try
		{
			if(checker.isDateOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDateOutside(this, "valueA", "Extra info goes here");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test(expected=FailFastException.class)
	public void testDateOutsideFailMinusZeroVsZero() {
		Date valueA = new Date(1);
		Date valueMin = new Date(0);
		Date valueMax = new Date(0);
		try
		{
			if(checker.isDateOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDateOutside(this, "valueA", "Extra info goes here");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
}
