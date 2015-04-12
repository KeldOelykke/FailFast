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
 * Fail-fast unit test of {link:IObjectStringWithPrefixCheck} and {link:IObjectStringWithPrefixFail}.
 * 
 * @author Keld Oelykke
 */
public class StringWithPrefixTest {

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
	public void testStringWithPrefixCheckerCallerIsNull() {
		String referenceA = "okay";
		String prefix = "ok";
		if(checker.isStringWithPrefix(null, referenceA, prefix))
		{
			failer.failStringWithPrefix(this, "referenceA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStringWithPrefixFailerCallerIsNull() {
		String referenceA = "okay";
		String prefix = "ok";
		if(checker.isStringWithPrefix(this, referenceA, prefix))
		{
			failer.failStringWithPrefix(null, "referenceA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringFailerCallerIsWrong() {
		String referenceA = "okay";
		String prefix = "ok";
		if(checker.isStringWithPrefix(new String("Foo"), referenceA, prefix))
		{
			failer.failStringWithPrefix(new String("Bar"), "referenceA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testStringWithPrefixMismatchCheckCheck() {
		String referenceA = "okay";
		String prefix = "ok";
		if(checker.isStringWithPrefix(this, referenceA, prefix))
		{
			checker.isStringWithPrefix(this, referenceA, prefix);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringWithPrefixMismatchFail() {
		failer.failStringWithPrefix(this, "referenceA");
	}

	@Test(expected=IllegalStateException.class)
	public void testStringWithPrefixMismatchWrongCheck() {
		String referenceA = "okay";
		String prefix = "xyz";
		if(checker.isStringWithoutPrefix(this, referenceA, prefix)) // wrong call
		{
			failer.failStringWithPrefix(this, "referenceA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringWithPrefixMismatchWrongFail() {
		String referenceA = "okay";
		String prefix = "ok";
		if(checker.isStringWithPrefix(this, referenceA, prefix))
		{
			failer.failStringWithoutPrefix(this, "referenceA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test
	public void testStringNullWithNullPrefixNoFail() {
		String referenceA = null;
		String prefix = null;
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
			}
		}
		assertTrue("Expected referenceA & prefix to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testStringEmptyWithNullPrefixNoFail() {
		String referenceA = "";
		String prefix = null;
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
			}
		}
		assertTrue("Expected referenceA & prefix to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testStringNonEmptyWithNullPrefixNoFail() {
		String referenceA = "okay";
		String prefix = null;
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
			}
		}
		assertTrue("Expected referenceA & prefix to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	
	@Test
	public void testStringNullWithEmptyPrefixNoFail() {
		String referenceA = null;
		String prefix = "";
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
			}
		}
		assertTrue("Expected referenceA & prefix to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testStringEmptyWithEmptyPrefixFail() {
		String referenceA = "";
		String prefix = "";
		try
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
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
	public void testStringNonEmptyWithEmptyPrefixFail() {
		String referenceA = "okay";
		String prefix = "";
		try
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
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
	public void testStringNonEmptyWithNonMatchingPrefixNoFail() {
		String referenceA = "okay";
		String prefix = "xyz";
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
			}
		}
		assertTrue("Expected referenceA & prefix to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithMatchingPrefixFail() {
		String referenceA = "okay";
		String prefix = "o";
		try
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA");
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
	public void testStringNonEmptyWithMatchingPrefixFailMessage() {
		String referenceA = "okay";
		String prefix = "ok";
		try
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA", "Extra info goes here");
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
	public void testStringNonEmptyWithSelfPrefixFailMessage() {
		String referenceA = "okay";
		String prefix = referenceA;
		try
		{
			if(checker.isStringWithPrefix(this, referenceA, prefix))
			{
				failer.failStringWithPrefix(this, "referenceA", "Extra info goes here");
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
