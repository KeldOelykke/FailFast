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
 * Fail-fast unit test of {link:IPrimitiveFloatNotEqualsCheck} and {link:IPrimitiveFloatNotEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatValueNotEqualsTest {

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
	public void testFloatValueNotEqualsCheckerCallerIsNull() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		if(checker.isFloatValueNotEquals(null, valueA, valueB))
		{
			failer.failFloatValueNotEquals(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFloatValueNotEqualsFailerCallerIsNull() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		if(checker.isFloatValueNotEquals(this, valueA, valueB))
		{
			failer.failFloatValueNotEquals(null, "valueA", "valueB");
		}

	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsFailerCallerIsWrong() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		if(checker.isFloatValueNotEquals(new String("Foo"), valueA, valueB))
		{
			failer.failFloatValueNotEquals(new String("Bar"), "valueA", "valueB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsMismatchCheckCheck() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		if(checker.isFloatValueNotEquals(this, valueA, valueB))
		{
			checker.isFloatValueNotEquals(this, valueA, valueB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsMismatchFail() {
		failer.failFloatValueNotEquals(this, "valueA", "valueB");
	}

	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsMismatchWrongCheck() {
		float valueA = 0.123f;
		float valueB = 0.123f;
		if(checker.isFloatValueEquals(this, valueA, valueB)) // wrong call
		{
			failer.failFloatValueNotEquals(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsMismatchWrongFail() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		if(checker.isFloatValueNotEquals(this, valueA, valueB))
		{
			failer.failFloatValueEquals(this, "valueA", "valueB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsFailNoMessage() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		try
		{
			if(checker.isFloatValueNotEquals(this, valueA, valueB))
			{
				failer.failFloatValueNotEquals(this, "valueA", "valueB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsFailMessage() {
		float valueA = 0.1234f;
		float valueB = 0.123f;
		try
		{
			if(checker.isFloatValueNotEquals(this, valueA, valueB))
			{
				failer.failFloatValueNotEquals(this, "valueA", "valueB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testFloatValueNotEqualsNoFail() {
		float valueA = 0.1234f;
		float valueB = valueA;
		if(checker.isFloatValueNotEquals(this, valueA, valueB))
		{
			failer.failFloatValueNotEquals(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the not-equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
