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
 * Fail-fast unit test of {link:IObjectStringNotMatchingCheck} and {link:IObjectStringNotMatchingFail}.
 * 
 * @author Keld Oelykke
 */
public class StringNotMatchingTest {

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
	public void testStringNotMatchingCheckerCallerIsNull() {
		String referenceA = "okay";
		String regex = "[cayo]{4}";
		if(checker.isStringNotMatching(null, referenceA, regex))
		{
			failer.failStringNotMatching(this, "referenceA", regex);
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStringNotMatchingFailerCallerIsNull() {
		String referenceA = "okay";
		String regex = "[cayo]{4}";
		if(checker.isStringNotMatching(this, referenceA, regex))
		{
			failer.failStringNotMatching(null, "referenceA", regex);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringFailerCallerIsWrong() {
		String referenceA = "okay";
		String regex = "[cayo]{4}";
		if(checker.isStringNotMatching(new String("Foo"), referenceA, regex))
		{
			failer.failStringNotMatching(new String("Bar"), "referenceA", regex);
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotMatchingMismatchCheckCheck() {
		String referenceA = "okay";
		String regex = "[cayo]{4}";
		if(checker.isStringNotMatching(this, referenceA, regex))
		{
			checker.isStringNotMatching(this, referenceA, regex);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotMatchingMismatchFail() {
		failer.failStringNotMatching(this, "referenceA", "[cayo]{4}");
	}

	@Test(expected=IllegalStateException.class)
	public void testStringNotMatchingMismatchWrongCheck() {
		String referenceA = "okay";
		String regex = "[kayo]{4}";
		if(checker.isStringMatching(this, referenceA, regex)) // wrong call
		{
			failer.failStringNotMatching(this, "referenceA", regex);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringNotMatchingMismatchWrongFail() {
		String referenceA = "okay";
		String regex = "[cayo]{4}";
		if(checker.isStringNotMatching(this, referenceA, regex))
		{
			failer.failStringMatching(this, "referenceA", regex); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testStringNullWithNullSubstringNoFail() {
		String referenceA = null;
		String regex = null;
		try
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
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
	public void testStringEmptyWithNullSubstringFail() {
		String referenceA = "";
		String regex = null;
		try
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
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
	public void testStringNonEmptyWithNullSubstringFail() {
		String referenceA = "okay";
		String regex = null;
		try
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
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
	public void testStringNullWithEmptySubstringFail() {
		String referenceA = null;
		String regex = "";
		try
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
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
	public void testStringEmptyWithEmptySubstringNoFail() {
		String referenceA = "";
		String regex = "";
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
			}
		}
		assertTrue("Expected referenceA & regex to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithEmptySubstringFail() {
		String referenceA = "okay";
		String regex = "";
		try
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
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
	public void testStringNonEmptyWithNonMatchingSubstringFail() {
		String referenceA = "okay";
		String regex = "[cayo]{4}";
		try
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
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
	public void testStringNonEmptyWithMatchingPrefixFail() {
		String referenceA = "okay";
		String regex = "ok[ya]{2}";
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
			}
		}
		assertTrue("Expected referenceA & regex to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testStringNonEmptyWithMatchingSubstringNoFail() {
		String referenceA = "okay";
		String regex = "[kayo]{1}ka[kayo]{1}";
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex, "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & regex to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	@Test
	public void testStringNonEmptyWithMatchingPostfixNoFail() {
		String referenceA = "okay";
		String regex = "[ko]{2}ay";
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex);
			}
		}
		assertTrue("Expected referenceA & regex to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testStringNonEmptyWithSelfSubstringNoFail() {
		String referenceA = "okay";
		String regex = referenceA;
		{
			if(checker.isStringNotMatching(this, referenceA, regex))
			{
				failer.failStringNotMatching(this, "referenceA", regex, "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & regex to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
}
