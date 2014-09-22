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
package starkcoder.failfast.unit.primitives.floats;

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
 * Fail-fast unit test of {link:IPrimitiveFloatGreaterEqualsCheck} and {link:IPrimitiveFloatGreaterEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatValueGreaterEqualsTest {

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
	public void testFloatValueGreaterEqualsCheckerCallerIsNull() {
		float valueA = 0.124f;
		float valueB = 0.123f;
		if(checker.isFloatValueGreaterEquals(null, valueA, valueB))
		{
			failer.failFloatValueGreaterEquals(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFloatValueGreaterEqualsFailerCallerIsNull() {
		float valueA = 0.124f;
		float valueB = 0.123f;
		if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
		{
			failer.failFloatValueGreaterEquals(null, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueFailerCallerIsWrong() {
		float valueA = 0.124f;
		float valueB = 0.123f;
		if(checker.isFloatValueGreaterEquals(new String("Foo"), valueA, valueB))
		{
			failer.failFloatValueGreaterEquals(new String("Bar"), "valueA", "valueB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueGreaterEqualsMismatchCheckCheck() {
		float valueA = 0.124f;
		float valueB = 0.123f;
		if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
		{
			checker.isFloatValueGreaterEquals(this, valueA, valueB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueGreaterEqualsMismatchFail() {
		failer.failFloatValueGreaterEquals(this, "valueA", "valueB");
	}

	@Test(expected=IllegalStateException.class)
	public void testFloatValueGreaterEqualsMismatchWrongCheck() {
		float valueA = 0.122f;
		float valueB = 0.123f;
		if(checker.isFloatValueLess(this, valueA, valueB)) // wrong call
		{
			failer.failFloatValueGreaterEquals(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueGreaterEqualsMismatchWrongFail() {
		float valueA = 0.123f;
		float valueB = 0.122f;
		if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
		{
			failer.failFloatValueLess(this, "valueA", "valueB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testFloatValueGreaterFailNoMessage() {
		float valueA = 0.125f;
		float valueB = 0.124f;
		try
		{
			if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
			{
				failer.failFloatValueGreaterEquals(this, "valueA", "valueB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueGreaterFailMessage() {
		float valueA = 0.124f;
		float valueB = -0.124f;
		try
		{
			if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
			{
				failer.failFloatValueGreaterEquals(this, "valueA", "valueB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}

	@Test(expected=FailFastException.class)
	public void testFloatValueEqualsFailNoMessage() {
		float valueA = -0.124f;
		float valueB = -0.124f;
		try
		{
			if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
			{
				failer.failFloatValueGreaterEquals(this, "valueA", "valueB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueEqualsFailMessage() {
		float valueA = 0.125f;
		float valueB = 0.125f;
		try
		{
			if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
			{
				failer.failFloatValueGreaterEquals(this, "valueA", "valueB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testFloatValueGreaterEqualsNoFail() {
		float valueA = 0.122f;
		float valueB = 0.123f;
		if(checker.isFloatValueGreaterEquals(this, valueA, valueB))
		{
			failer.failFloatValueGreaterEquals(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
