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
 * Fail-fast unit test of {link:IPrimitiveBooleanFalseCheck} and {link:IPrimitiveBooleanFalseFail}.
 * 
 * @author Keld Oelykke
 */
public class BooleanValueFalseTest {

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
	public void testBooleanValueFalseCheckerCallerIsNull() {
		boolean valueA = false;
		if(checker.isBooleanValueFalse(null, valueA))
		{
			failer.failBooleanValueFalse(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBooleanValueFalseFailerCallerIsNull() {
		boolean valueA = false;
		if(checker.isBooleanValueFalse(this, valueA))
		{
			failer.failBooleanValueFalse(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueFailerCallerIsWrong() {
		boolean valueA = false;
		if(checker.isBooleanValueFalse(new String("Foo"), valueA))
		{
			failer.failBooleanValueFalse(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueFalseMismatchCheckCheck() {
		boolean valueA = false;
		if(checker.isBooleanValueFalse(this, valueA))
		{
			checker.isBooleanValueFalse(this, valueA);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueFalseMismatchFail() {
		failer.failBooleanValueFalse(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testBooleanValueFalseMismatchWrongCheck() {
		boolean valueA = true;
		if(checker.isBooleanValueTrue(this, valueA)) // wrong call
		{
			failer.failBooleanValueFalse(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testBooleanValueFalseMismatchWrongFail() {
		boolean valueA = false;
		if(checker.isBooleanValueFalse(this, valueA))
		{
			failer.failBooleanValueTrue(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testBooleanValueFalseFailNoMessage() {
		boolean valueA = false;
		try
		{
			if(checker.isBooleanValueFalse(this, valueA))
			{
				failer.failBooleanValueFalse(this, "valueA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testBooleanValueFalseFailMessage() {
		boolean valueA = false;
		try
		{
			if(checker.isBooleanValueFalse(this, valueA))
			{
				failer.failBooleanValueFalse(this, "valueA", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testBooleanValueTrueNoFail() {
		boolean valueA = true;
		if(checker.isBooleanValueFalse(this, valueA))
		{
			failer.failBooleanValueFalse(this, "valueA");
		}
		assertTrue("Expected valueA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}