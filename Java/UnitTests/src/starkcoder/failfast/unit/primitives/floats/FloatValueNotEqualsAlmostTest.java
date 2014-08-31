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
 * Fail-fast unit test of {link:IPrimitiveFloatNotEqualsAlmostCheck} and {link:IPrimitiveFloatNotEqualsAlmostFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatValueNotEqualsAlmostTest {

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
	public void testFloatValueNotEqualsAlmostCheckerCallerIsNull() {
		float valueA = 0.123f;
		float valueB = 0.1234f;
		if(checker.isFloatValueNotEqualsAlmost(null, valueA, valueB))
		{
			failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFloatValueNotEqualsAlmostFailerCallerIsNull() {
		float valueA = 0.123f;
		float valueB = 0.1241f;
		if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB))
		{
			failer.failFloatValueNotEqualsAlmost(null, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueFailerCallerIsWrong() {
		float valueA = 0.123f;
		float valueB = 0.1241f;
		if(checker.isFloatValueNotEqualsAlmost(new String("Foo"), valueA, valueB))
		{
			failer.failFloatValueNotEqualsAlmost(new String("Bar"), "valueA", "valueB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsAlmostMismatchCheckCheck() {
		float valueA = 0.123f;
		float valueB = 0.1241f;
		if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB))
		{
			checker.isFloatValueNotEqualsAlmost(this, valueA, valueB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsAlmostMismatchFail() {
		failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
	}

	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsAlmostMismatchWrongCheck() {
		float valueA = 0.123f;
		float valueB = 0.123f;
		if(checker.isFloatValueEqualsAlmost(this, valueA, valueB)) // wrong call
		{
			failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueNotEqualsAlmostMismatchWrongFail() {
		float valueA = 0.123f;
		float valueB = 0.1241f;
		if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB))
		{
			failer.failFloatValueEqualsAlmost(this, "valueA", "valueB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsAlmostFailNoMessage() {
		float valueA = 0.123f;
		float valueB = 0.1241f;
		try
		{
			if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB))
			{
				failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsAlmostFailMessage() {
		float valueA = 0.1241f;
		float valueB = 0.123f;
		try
		{
			if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB))
			{
				failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testFloatValueNotEqualsAlmostNoFail() {
		float valueA = 0.123001f;
		float valueB = 0.123f;
		if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB))
		{
			failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsAlmostOverrideFailValuesNoMessage() {
		float valueA = 0.121f;
		float valueB = 0.12f;
		try
		{
			if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB, 0.0001f))
			{
				failer.failFloatValueNotEqualsAlmost(this, "valueA", valueA, "valueB", valueB);
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsAlmostOverrideFailValuesMessage() {
		float valueA = 0.111f;
		float valueB = 0.1f;
		try
		{
			if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB, 0.01f))
			{
				failer.failFloatValueNotEqualsAlmost(this, "valueA", valueA, "valueB", valueB, "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}

	// 4tf - method override cases
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsAlmostOverrideFailNoMessage() {
		float valueA = 0.121f;
		float valueB = 0.12f;
		try
		{
			if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB, 0.0001f))
			{
				failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueNotEqualsAlmostOverrideFailMessage() {
		float valueA = 0.111f;
		float valueB = 0.1f;
		try
		{
			if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB, 0.01f))
			{
				failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testFloatValueNotEqualsAlmostOverrideNoFail() {
		float valueA = 0.1234f;
		float valueB = -0.123f;
		if(checker.isFloatValueNotEqualsAlmost(this, valueA, valueB, 2f))
		{
			failer.failFloatValueNotEqualsAlmost(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
}
