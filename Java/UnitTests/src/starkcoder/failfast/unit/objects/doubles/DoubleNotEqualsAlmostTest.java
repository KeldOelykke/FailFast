package starkcoder.failfast.unit.objects.doubles;
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

/**
 * Fail-fast unit test of {link:IObjectDoubleNotEqualsAlmostCheck} and {link:IObjectDoubleNotEqualsAlmostFail}.
 * 
 * @author Keld Oelykke
 */
public class DoubleNotEqualsAlmostTest {

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
	public void testDoubleNotEqualsAlmostCheckerCallerIsNull() {
		double valueA = 0.123f;
		double valueB = 0.1234f;
		if(checker.isDoubleNotEqualsAlmost(null, valueA, valueB))
		{
			failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDoubleNotEqualsAlmostFailerCallerIsNull() {
		double valueA = 0.123f;
		double valueB = 0.1241d;
		if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB))
		{
			failer.failDoubleNotEqualsAlmost(null, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testDoubleFailerCallerIsWrong() {
		double valueA = 0.123f;
		double valueB = 0.1241d;
		if(checker.isDoubleNotEqualsAlmost(new String("Foo"), valueA, valueB))
		{
			failer.failDoubleNotEqualsAlmost(new String("Bar"), "valueA", "valueB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testDoubleNotEqualsAlmostMismatchCheckCheck() {
		double valueA = 0.123f;
		double valueB = 0.1241d;
		if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB))
		{
			checker.isDoubleNotEqualsAlmost(this, valueA, valueB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testDoubleNotEqualsAlmostMismatchFail() {
		failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
	}

	@Test(expected=IllegalStateException.class)
	public void testDoubleNotEqualsAlmostMismatchWrongCheck() {
		double valueA = 0.123f;
		double valueB = 0.123f;
		if(checker.isDoubleEqualsAlmost(this, valueA, valueB)) // wrong call
		{
			failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testDoubleNotEqualsAlmostMismatchWrongFail() {
		double valueA = 0.123f;
		double valueB = 0.1241d;
		if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB))
		{
			failer.failDoubleEqualsAlmost(this, "valueA", "valueB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testDoubleNotEqualsAlmostFailNoMessage() {
		double valueA = 0.123f;
		double valueB = 0.1241d;
		try
		{
			if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB))
			{
				failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
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
	public void testDoubleNotEqualsAlmostFailMessage() {
		double valueA = 0.1241d;
		double valueB = 0.123f;
		try
		{
			if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB))
			{
				failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
	public void testDoubleNotEqualsAlmostNoFail() {
		double valueA = 0.123001d;
		double valueB = 0.123f;
		if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB))
		{
			failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	


	// 4tf - method override cases
	
	@Test(expected=FailFastException.class)
	public void testDoubleNotEqualsAlmostOverrideFailNoMessage() {
		double valueA = 0.121d;
		double valueB = 0.12d;
		try
		{
			if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB, 0.0001d))
			{
				failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
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
	public void testDoubleNotEqualsAlmostOverrideFailMessage() {
		double valueA = 0.111d;
		double valueB = 0.1d;
		try
		{
			if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB, 0.01d))
			{
				failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
	public void testDoubleNotEqualsAlmostOverrideNoFail() {
		double valueA = 0.1234f;
		double valueB = -0.123f;
		if(checker.isDoubleNotEqualsAlmost(this, valueA, valueB, 2d))
		{
			failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
}
