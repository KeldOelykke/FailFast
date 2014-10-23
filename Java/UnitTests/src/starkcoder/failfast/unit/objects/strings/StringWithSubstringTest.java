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
import org.junit.Test;

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
 * Fail-fast unit test of {link:IObjectStringWithSubstringCheck} and {link:IObjectStringWithSubstringFail}.
 * 
 * @author Keld Oelykke
 */
public class StringWithSubstringTest {

	private IChecker checker;
	private IFailer failer;
	
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
	public void testStringWithSubstringCheckerCallerIsNull() {
		String referenceA = "okay";
		String referenceB = "ka";
		if(checker.isStringWithSubstring(null, referenceA, referenceB))
		{
			failer.failStringWithSubstring(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStringWithSubstringFailerCallerIsNull() {
		String referenceA = "okay";
		String referenceB = "ka";
		if(checker.isStringWithSubstring(this, referenceA, referenceB))
		{
			failer.failStringWithSubstring(null, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringFailerCallerIsWrong() {
		String referenceA = "okay";
		String referenceB = "ka";
		if(checker.isStringWithSubstring(new String("Foo"), referenceA, referenceB))
		{
			failer.failStringWithSubstring(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testStringWithSubstringMismatchCheckCheck() {
		String referenceA = "okay";
		String referenceB = "ka";
		if(checker.isStringWithSubstring(this, referenceA, referenceB))
		{
			checker.isStringWithSubstring(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringWithSubstringMismatchFail() {
		failer.failStringWithSubstring(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testStringWithSubstringMismatchWrongCheck() {
		String referenceA = "okay";
		String referenceB = "xyz";
		if(checker.isStringWithoutSubstring(this, referenceA, referenceB)) // wrong call
		{
			failer.failStringWithSubstring(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStringWithSubstringMismatchWrongFail() {
		String referenceA = "okay";
		String referenceB = "ka";
		if(checker.isStringWithSubstring(this, referenceA, referenceB))
		{
			failer.failStringWithoutSubstring(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test
	public void testStringNullWithNullSubstringNoFail() {
		String referenceA = null;
		String referenceB = null;
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testStringEmptyWithNullSubstringNoFail() {
		String referenceA = "";
		String referenceB = null;
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testStringNonEmptyWithNullSubstringNoFail() {
		String referenceA = "okay";
		String referenceB = null;
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	
	@Test
	public void testStringNullWithEmptySubstringNoFail() {
		String referenceA = null;
		String referenceB = "";
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testStringEmptyWithEmptySubstringFail() {
		String referenceA = "";
		String referenceB = "";
		try
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithEmptySubstringFail() {
		String referenceA = "okay";
		String referenceB = "";
		try
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testStringNonEmptyWithNonMatchingSubstringNoFail() {
		String referenceA = "okay";
		String referenceB = "xyz";
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithMatchingPrefixFail() {
		String referenceA = "okay";
		String referenceB = "ok";
		try
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithMatchingSubstringFailMessage() {
		String referenceA = "okay";
		String referenceB = "ka";
		try
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}

	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithMatchingPostfixFail() {
		String referenceA = "okay";
		String referenceB = "ay";
		try
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testStringNonEmptyWithSelfSubstringFailMessage() {
		String referenceA = "okay";
		String referenceB = referenceA;
		try
		{
			if(checker.isStringWithSubstring(this, referenceA, referenceB))
			{
				failer.failStringWithSubstring(this, "referenceA", "referenceB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
}
