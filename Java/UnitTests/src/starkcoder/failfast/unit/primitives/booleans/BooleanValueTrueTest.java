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
package starkcoder.failfast.unit.primitives.booleans;

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
 * Fail-fast unit test of {link:IPrimitiveBooleanTrueCheck} and {link:IPrimitiveBooleanTrueFail}.
 * 
 * @author Keld Oelykke
 */
public class BooleanValueTrueTest {

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
	public void testBooleanValueTrueCheckerCallerIsNull() {
		boolean valueA = true;
		if(checker.isBooleanValueTrue(null, valueA))
		{
			failer.failBooleanValueTrue(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBooleanValueTrueFailerCallerIsNull() {
		boolean valueA = true;
		if(checker.isBooleanValueTrue(this, valueA))
		{
			failer.failBooleanValueTrue(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueFailerCallerIsWrong() {
		boolean valueA = true;
		if(checker.isBooleanValueTrue(new String("Foo"), valueA))
		{
			failer.failBooleanValueTrue(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueTrueMismatchCheckCheck() {
		boolean valueA = true;
		if(checker.isBooleanValueTrue(this, valueA))
		{
			checker.isBooleanValueTrue(this, valueA);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueTrueMismatchFail() {
		failer.failBooleanValueTrue(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testBooleanValueTrueMismatchWrongCheck() {
		boolean valueA = false;
		if(checker.isBooleanValueFalse(this, valueA)) // wrong call
		{
			failer.failBooleanValueTrue(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueTrueMismatchWrongFail() {
		boolean valueA = true;
		if(checker.isBooleanValueTrue(this, valueA))
		{
			failer.failBooleanValueFalse(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testBooleanValueTrueFailNoMessage() {
		boolean valueA = true;
		try
		{
			if(checker.isBooleanValueTrue(this, valueA))
			{
				failer.failBooleanValueTrue(this, "valueA");
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
	public void testBooleanValueTrueFailMessage() {
		boolean valueA = true;
		try
		{
			if(checker.isBooleanValueTrue(this, valueA))
			{
				failer.failBooleanValueTrue(this, "valueA", "Extra info goes here");
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
	public void testBooleanValueFalseNoFail() {
		boolean valueA = false;
		if(checker.isBooleanValueTrue(this, valueA))
		{
			failer.failBooleanValueTrue(this, "valueA");
		}
		assertTrue("Expected valueA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
