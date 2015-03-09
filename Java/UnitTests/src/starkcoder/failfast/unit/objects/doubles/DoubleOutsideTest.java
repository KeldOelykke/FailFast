package starkcoder.failfast.unit.objects.doubles;
/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Keld Ølykke
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
 * Fail-fast unit test of {link:IObjectDoubleOutsideCheck} and {link:IObjectDoubleOutsideFail}.
 * 
 * @author Keld Oelykke
 */
public class DoubleOutsideTest implements IComparableOutsideTest<Double> {

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
		double valueA = 0.122d;
		double valueMin = 0.123d;
		double valueMax = 0.123d;
		if(checker.isDoubleOutside(null, valueA, valueMin, valueMax))
		{
			failer.failDoubleOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableOutsideFailerCallerIsNull() {
		double valueA = 0.122d;
		double valueMin = 0.123d;
		double valueMax = 0.123d;
		if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
		{
			failer.failDoubleOutside(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideFailerCallerIsWrong() {
		double valueA = 0.122d;
		double valueMin = 0.123d;
		double valueMax = 0.123d;
		if(checker.isDoubleOutside(new String("Foo"), valueA, valueMin, valueMax))
		{
			failer.failDoubleOutside(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchCheckCheck() {
		double valueA = 0.122d;
		double valueMin = 0.123d;
		double valueMax = 0.123d;
		if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
		{
			checker.isDoubleOutside(this, valueA, valueMin, valueMax);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchFail() {
		failer.failDoubleOutside(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchWrongCheck() {
		double valueA = 0.124d;
		double valueMin = 0.1241d;
		double valueMax = 0.124d;
		if(checker.isDoubleInside(this, valueA, valueMin, valueMax)) // wrong call
		{
			failer.failDoubleOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchWrongFail() {
		double valueA = 0.122d;
		double valueMin = 0.123d;
		double valueMax = 0.123d;
		if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
		{
			failer.failDoubleInside(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailNoMessage() {
		double valueA = 0.121d;
		double valueMin = 0.122d;
		double valueMax = 0.123d;
		try
		{
			if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDoubleOutside(this, "valueA");
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
		double valueA = 0.122d;
		double valueMin = 0.124d;
		double valueMax = 0.123d;
		try
		{
			if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDoubleOutside(this, "valueA", "Extra info goes here");
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
		double valueA = 0.123d;
		double valueMin = 0.123d;
		double valueMax = 0.124d;
		if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
		{
			failer.failDoubleOutside(this, "valueA");
		}
		assertTrue("Expected valueA & valueMin to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	
	// 4th - corner cases
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailComparableMin() {
		double valueA = Double.MIN_VALUE;
		double valueMin = Double.longBitsToDouble(Double.doubleToLongBits(Double.MIN_VALUE) + 0x1);
		double valueMax = Double.MAX_VALUE;
		try
		{
			if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDoubleOutside(this, "valueA");
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
		double valueA = Double.MAX_VALUE;
		double valueMin = Double.MIN_VALUE;
		double valueMax = Double.longBitsToDouble(Double.doubleToLongBits(Double.MAX_VALUE) - 0x1);
		try
		{
			if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDoubleOutside(this, "valueA", "Extra info goes here");
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
	public void testDoubleOutsideFailMinusZeroVsZero() {
		double valueA = Double.longBitsToDouble(Double.doubleToLongBits(0d) + 0x1);
		double valueMin = 0d;
		double valueMax = 0d;
		try
		{
			if(checker.isDoubleOutside(this, valueA, valueMin, valueMax))
			{
				failer.failDoubleOutside(this, "valueA", "Extra info goes here");
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
