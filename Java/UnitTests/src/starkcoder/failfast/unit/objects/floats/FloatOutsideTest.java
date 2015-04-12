package starkcoder.failfast.unit.objects.floats;
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
 * Fail-fast unit test of {link:IObjectFloatOutsideCheck} and {link:IObjectFloatOutsideFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatOutsideTest implements IComparableOutsideTest<Float> {

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
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatOutside(null, valueA, valueMin, valueMax))
		{
			failer.failFloatOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableOutsideFailerCallerIsNull() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
		{
			failer.failFloatOutside(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideFailerCallerIsWrong() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatOutside(new String("Foo"), valueA, valueMin, valueMax))
		{
			failer.failFloatOutside(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchCheckCheck() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
		{
			checker.isFloatOutside(this, valueA, valueMin, valueMax);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchFail() {
		failer.failFloatOutside(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchWrongCheck() {
		float valueA = 0.124f;
		float valueMin = 0.1241f;
		float valueMax = 0.124f;
		if(checker.isFloatInside(this, valueA, valueMin, valueMax)) // wrong call
		{
			failer.failFloatOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableOutsideMismatchWrongFail() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
		{
			failer.failFloatInside(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailNoMessage() {
		float valueA = 0.121f;
		float valueMin = 0.122f;
		float valueMax = 0.123f;
		try
		{
			if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatOutside(this, "valueA");
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
		float valueA = 0.122f;
		float valueMin = 0.124f;
		float valueMax = 0.123f;
		try
		{
			if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatOutside(this, "valueA", "Extra info goes here");
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
		float valueA = 0.123f;
		float valueMin = 0.123f;
		float valueMax = 0.124f;
		if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
		{
			failer.failFloatOutside(this, "valueA");
		}
		assertTrue("Expected valueA & valueMin to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	
	// 4th - corner cases
	
	@Test(expected=FailFastException.class)
	public void testComparableOutsideFailComparableMin() {
		float valueA = Float.MIN_VALUE;
		float valueMin = Float.intBitsToFloat(Float.floatToIntBits(Float.MIN_VALUE) + 0x1);
		float valueMax = Float.MAX_VALUE;
		try
		{
			if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatOutside(this, "valueA");
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
		float valueA = Float.MAX_VALUE;
		float valueMin = Float.MIN_VALUE;
		float valueMax = Float.intBitsToFloat(Float.floatToIntBits(Float.MAX_VALUE) - 0x1);
		try
		{
			if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatOutside(this, "valueA", "Extra info goes here");
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
	public void testFloatOutsideFailMinusZeroVsZero() {
		float valueA = Float.intBitsToFloat(Float.floatToIntBits(0f) + 0x1);
		float valueMin = 0f;
		float valueMax = 0f;
		try
		{
			if(checker.isFloatOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatOutside(this, "valueA", "Extra info goes here");
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
