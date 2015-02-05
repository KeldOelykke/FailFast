package starkcoder.failfast.unit.objects.strings;
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
import starkcoder.failfast.templates.comparables.IComparableGreaterOrEqualsTest;

/**
 * Fail-fast unit test of {link:IObjectStringGreaterOrEqualsCheck} and {link:IObjectStringGreaterOrEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class StringGreaterOrEqualsTest implements IComparableGreaterOrEqualsTest<String> {

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
	public void testComparableGreaterOrEqualsCheckerCallerIsNull() {
		String referenceA = "a.abd";
		String referenceB = "a.abc";
		if(checker.isStringGreaterOrEquals(null, referenceA, referenceB))
		{
			failer.failStringGreaterOrEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableGreaterOrEqualsFailerCallerIsNull() {
		String referenceA = "a.abd";
		String referenceB = "a.abc";
		if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
		{
			failer.failStringGreaterOrEquals(null, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableGreaterOrEqualsFailerCallerIsWrong() {
		String referenceA = "a.abd";
		String referenceB = "a.abc";
		if(checker.isStringGreaterOrEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failStringGreaterOrEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableGreaterOrEqualsMismatchCheckCheck() {
		String referenceA = "a.abd";
		String referenceB = "a.abc";
		if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
		{
			checker.isStringGreaterOrEquals(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableGreaterOrEqualsMismatchFail() {
		failer.failStringGreaterOrEquals(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableGreaterOrEqualsMismatchWrongCheck() {
		String referenceA = "a.abb";
		String referenceB = "a.abc";
		if(checker.isStringLess(this, referenceA, referenceB)) // wrong call
		{
			failer.failStringGreaterOrEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableGreaterOrEqualsMismatchWrongFail() {
		String referenceA = "a.abc";
		String referenceB = "a.abb";
		if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
		{
			failer.failStringLess(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableGreaterFailNoMessage() {
		String referenceA = "a.abe";
		String referenceB = "a.abd";
		try
		{
			if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
			{
				failer.failStringGreaterOrEquals(this, "referenceA", "referenceB");
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
	public void testComparableGreaterFailMessage() {
		String referenceA = "a.abd";
		String referenceB = "_a.abd";
		try
		{
			if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
			{
				failer.failStringGreaterOrEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testComparableEqualsFailNoMessage() {
		String referenceA = "_a.abd";
		String referenceB = "_a.abd";
		try
		{
			if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
			{
				failer.failStringGreaterOrEquals(this, "referenceA", "referenceB");
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
	public void testComparableEqualsFailMessage() {
		String referenceA = "a.abe";
		String referenceB = "a.abe";
		try
		{
			if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
			{
				failer.failStringGreaterOrEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testComparableGreaterOrEqualsNoFail() {
		String referenceA = "a.abb";
		String referenceB = "a.abc";
		if(checker.isStringGreaterOrEquals(this, referenceA, referenceB))
		{
			failer.failStringGreaterOrEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
