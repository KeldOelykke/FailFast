package starkcoder.failfast.unit.objects.bytes;
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
import starkcoder.failfast.templates.comparables.IComparableInsideTest;

/**
 * Fail-fast unit test of {link:IObjectByteInsideCheck} and {link:IObjectByteInsideFail}.
 * 
 * @author Keld Oelykke
 */
public class ByteInsideTest implements IComparableInsideTest<Byte> {

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
	public void testComparableInsideCheckerCallerIsNull() {
		byte valueA = 123;
		byte valueMin = 123;
		byte valueMax = 123;
		if(checker.isByteInside(null, valueA, valueMin, valueMax))
		{
			failer.failByteInside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableInsideFailerCallerIsNull() {
		byte valueA = 123;
		byte valueMin = 123;
		byte valueMax = 123;
		if(checker.isByteInside(this, valueA, valueMin, valueMax))
		{
			failer.failByteInside(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideFailerCallerIsWrong() {
		byte valueA = 123;
		byte valueMin = 123;
		byte valueMax = 123;
		if(checker.isByteInside(new String("Foo"), valueA, valueMin, valueMax))
		{
			failer.failByteInside(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchCheckCheck() {
		byte valueA = 123;
		byte valueMin = 123;
		byte valueMax = 123;
		if(checker.isByteInside(this, valueA, valueMin, valueMax))
		{
			checker.isByteInside(this, valueA, valueMin, valueMax);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchFail() {
		failer.failByteInside(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchWrongCheck() {
		byte valueA = 123;
		byte valueMin = 125;
		byte valueMax = 124;
		if(checker.isByteOutside(this, valueA, valueMin, valueMax)) // wrong call
		{
			failer.failByteInside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchWrongFail() {
		byte valueA = 123;
		byte valueMin = 123;
		byte valueMax = 123;
		if(checker.isByteInside(this, valueA, valueMin, valueMax))
		{
			failer.failByteOutside(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableInsideFailNoMessage() {
		byte valueA = 123;
		byte valueMin = 122;
		byte valueMax = 124;
		try
		{
			if(checker.isByteInside(this, valueA, valueMin, valueMax))
			{
				failer.failByteInside(this, "valueA");
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
	public void testComparableInsideFailMessage() {
		byte valueA = 123;
		byte valueMin = 124;
		byte valueMax = 122;
		try
		{
			if(checker.isByteInside(this, valueA, valueMin, valueMax))
			{
				failer.failByteInside(this, "valueA", "Extra info goes here");
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
	public void testComparableInsideNoFail() {
		byte valueA = 125;
		byte valueMin = 123;
		byte valueMax = 124;
		if(checker.isByteInside(this, valueA, valueMin, valueMax))
		{
			failer.failByteInside(this, "valueA");
		}
		assertTrue("Expected valueA & valueMin to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	// 4th - corner cases
	
	@Test(expected=FailFastException.class)
	public void testComparableInsideFailComparableMin() {
		byte valueA = Byte.MIN_VALUE;
		byte valueMin = valueA;
		byte valueMax = valueA;
		try
		{
			if(checker.isByteInside(this, valueA, valueMin, valueMax))
			{
				failer.failByteInside(this, "valueA");
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
	public void testComparableInsideFailComparableMax() {
		byte valueA = Byte.MAX_VALUE;
		byte valueMin = valueA;
		byte valueMax = valueA;
		try
		{
			if(checker.isByteInside(this, valueA, valueMin, valueMax))
			{
				failer.failByteInside(this, "valueA", "Extra info goes here");
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
	public void testByteInsideFailMinusZeroVsZero() {
		byte valueA = -0;
		byte valueMin = 0;
		byte valueMax = 0;
		try
		{
			if(checker.isByteInside(this, valueA, valueMin, valueMax))
			{
				failer.failByteInside(this, "valueA", "Extra info goes here");
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
