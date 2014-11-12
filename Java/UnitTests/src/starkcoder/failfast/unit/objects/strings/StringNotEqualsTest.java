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
package starkcoder.failfast.unit.objects.strings;

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
 * Fail-fast unit test of {link:IObjectStringNotEqualsCheck} and {link:IObjectStringNotEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class StringNotEqualsTest {

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
	public void testStringNotEqualsCheckerCallerIsNull() {
		String referenceA = "";
		String referenceB = "foo";
		if(checker.isStringNotEquals(null, referenceA, referenceB))
		{
			failer.failStringNotEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStringNotEqualsFailerCallerIsNull() {
		String referenceA = "";
		String referenceB = "foo";
		if(checker.isStringNotEquals(this, referenceA, referenceB))
		{
			failer.failStringNotEquals(null, "referenceA", "referenceB");
		}

	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotEqualsFailerCallerIsWrong() {
		String referenceA = "";
		String referenceB = "foo";
		if(checker.isStringNotEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failStringNotEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotEqualsMismatchCheckCheck() {
		String referenceA = "";
		String referenceB = "foo";
		if(checker.isStringNotEquals(this, referenceA, referenceB))
		{
			checker.isStringNotEquals(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotEqualsMismatchFail() {
		failer.failStringNotEquals(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testStringNotEqualsMismatchWrongCheck() {
		String referenceA = "";
		String referenceB = "";
		if(checker.isStringEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failStringNotEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotEqualsMismatchWrongFail() {
		String referenceA = "";
		String referenceB = "foo";
		if(checker.isStringNotEquals(this, referenceA, referenceB))
		{
			failer.failStringEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testStringNotEqualsFailNoMessage() {
		String referenceA = "";
		String referenceB = "foo";
		try
		{
			if(checker.isStringNotEquals(this, referenceA, referenceB))
			{
				failer.failStringNotEquals(this, "referenceA", "referenceB");
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
	public void testStringNotEqualsFailMessage() {
		String referenceA = "foo";
		String referenceB = "";
		try
		{
			if(checker.isStringNotEquals(this, referenceA, referenceB))
			{
				failer.failStringNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testStringNotEqualsFailValuesNoMessage() {
		String referenceA = "";
		String referenceB = "foo";
		try
		{
			if(checker.isStringNotEquals(this, referenceA, referenceB))
			{
				failer.failStringNotEquals(this, "referenceA", referenceA, "referenceB", referenceB);
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
	public void testStringNotEqualsFailValuesAndMessage() {
		String referenceA = "foo";
		String referenceB = "";
		try
		{
			if(checker.isStringNotEquals(this, referenceA, referenceB))
			{
				failer.failStringNotEquals(this, "referenceA", referenceA, "referenceB", referenceB, "Extra info goes here");
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
	public void testStringNotEqualsNoFail() {
		String referenceA = "foo";
		String referenceB = referenceA;
		if(checker.isStringNotEquals(this, referenceA, referenceB))
		{
			failer.failStringNotEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the not-equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
