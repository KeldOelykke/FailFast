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
 * Fail-fast unit test of {link:IObjectBooleanNotNullCheck} and {link:IObjectBooleanNotNullFail}.
 * 
 * @author Keld Oelykke
 */
public class BooleanNotNullTest {

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
	public void testBooleanNotNullCheckerCallerIsNull() {
		Boolean referenceNotNull = new Boolean(false);
		if(checker.isBooleanNotNull(null, referenceNotNull))
		{
			failer.failBooleanNotNull(this, "referenceNotNull");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBooleanNotNullFailerCallerIsNull() {
		Boolean referenceNotNull = new Boolean(false);
		if(checker.isBooleanNotNull(this, referenceNotNull))
		{
			failer.failBooleanNotNull(null, "referenceNotNull");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotNullFailerCallerIsWrong() {
		Boolean referenceNotNull = new Boolean(false);
		if(checker.isBooleanNotNull(new String("Foo"), referenceNotNull))
		{
			failer.failBooleanNotNull(new String("Bar"), "referenceNotNull");
		}
	}
	

	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotNullMismatchCheckCheck() {
		Boolean referenceNotNull = new Boolean(false);
		if(checker.isBooleanNotNull(this, referenceNotNull))
		{
			checker.isBooleanNotNull(this, referenceNotNull);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotNullMismatchFail() {
		failer.failObjectNull(this, "referenceNotNull");
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotNullMismatchWrongCheck() {
		Object referenceNotNull = null;
		if(checker.isObjectNull(this, referenceNotNull)) // wrong call
		{
			failer.failBooleanNotNull(this, "referenceNotNull");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanNotNullMismatchWrongFail() {
		Boolean referenceNotNull = new Boolean(false);
		if(checker.isBooleanNotNull(this, referenceNotNull)) 
		{
			failer.failObjectNull(this, "referenceNotNull"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testBooleanNotNullFailNoMessage() {
		Boolean referenceNotNull = new Boolean(false);
		try
		{
			if(checker.isBooleanNotNull(this, referenceNotNull))
			{
				failer.failBooleanNotNull(this, "referenceNotNull");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}

	}
	
	@Test(expected=FailFastException.class)
	public void testBooleanNotNullFailMessage() {
		Boolean referenceNotNull = new Boolean(true);
		try
		{
			if(checker.isBooleanNotNull(this, referenceNotNull))
			{
				failer.failBooleanNotNull(this, "referenceNotNull", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testObjectNullNoFail() {
		Boolean referenceNull = null;
		if(checker.isBooleanNotNull(this, referenceNull))
		{
			failer.failBooleanNotNull(this, "referenceNull");
		}
		assertTrue("Expected referenceNull to pass the not-null check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
