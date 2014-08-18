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
package starkcoder.failfast.unit.objects.booleans;

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
 * Fail-fast unit test of {link:IObjectBooleanNotDefaultCheck} and {link:IObjectBooleanNotDefaultFail}.
 * 
 * @author Keld Oelykke
 */
public class BooleanNotDefaultTest {

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
	public void testBooleanNotDefaultCheckerCallerIsNull() {
		Boolean referenceA = Boolean.TRUE;
		if(checker.isBooleanNotDefault(null, referenceA))
		{
			failer.failBooleanNotDefault(this, "referenceA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBooleanNotDefaultFailerCallerIsNull() {
		Boolean referenceA = Boolean.TRUE;
		if(checker.isBooleanNotDefault(this, referenceA))
		{
			failer.failBooleanNotDefault(null, "referenceA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanFailerCallerIsWrong() {
		Boolean referenceA = Boolean.TRUE;
		if(checker.isBooleanNotDefault(new String("Foo"), referenceA))
		{
			failer.failBooleanNotDefault(new String("Bar"), "referenceA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotDefaultMismatchCheckCheck() {
		Boolean referenceA = Boolean.TRUE;
		if(checker.isBooleanNotDefault(this, referenceA))
		{
			checker.isBooleanNotDefault(this, referenceA);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotDefaultMismatchFail() {
		failer.failBooleanNotDefault(this, "referenceA");
	}

	@Test(expected=IllegalStateException.class)
	public void testBooleanNotDefaultMismatchWrongCheck() {
		Boolean referenceA = Boolean.FALSE;
		if(checker.isBooleanDefault(this, referenceA)) // wrong call
		{
			failer.failBooleanNotDefault(this, "referenceA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotDefaultMismatchWrongFail() {
		Boolean referenceA = Boolean.TRUE;
		if(checker.isBooleanNotDefault(this, referenceA))
		{
			failer.failBooleanDefault(this, "referenceA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testBooleanNotDefaultFailNoMessage() {
		Boolean referenceA = Boolean.TRUE;
		try
		{
			if(checker.isBooleanNotDefault(this, referenceA))
			{
				failer.failBooleanNotDefault(this, "referenceA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testBooleanNotDefaultFailMessage() {
		Boolean referenceA = Boolean.TRUE;
		try
		{
			if(checker.isBooleanNotDefault(this, referenceA))
			{
				failer.failBooleanNotDefault(this, "referenceA", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testBooleanNullFail() {
		Boolean referenceA = null;
		try
		{
			if(checker.isBooleanNotDefault(this, referenceA))
			{
				failer.failBooleanNotDefault(this, "referenceA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}

	@Test
	public void testBooleanDefaultNoFail() {
		Boolean referenceA = Boolean.FALSE;
		if(checker.isBooleanNotDefault(this, referenceA))
		{
			failer.failBooleanNotDefault(this, "referenceA");
		}
		assertTrue("Expected referenceA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testBooleanDefaultChangedNoFail() {
		Boolean referenceA = Boolean.TRUE;
		checker.setBooleanDefault(referenceA);
		if(checker.isBooleanNotDefault(this, referenceA))
		{
			failer.failBooleanNotDefault(this, "referenceA");
		}
		assertTrue("Expected valueA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
