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
 * Fail-fast unit test of {link:IPrimitiveFloatOutsideCheck} and {link:IPrimitiveFloatOutsideFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatValueOutsideTest {

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
	public void testFloatValueOutsideCheckerCallerIsNull() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatValueOutside(null, valueA, valueMin, valueMax))
		{
			failer.failFloatValueOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFloatValueOutsideFailerCallerIsNull() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
		{
			failer.failFloatValueOutside(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueFailerCallerIsWrong() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatValueOutside(new String("Foo"), valueA, valueMin, valueMax))
		{
			failer.failFloatValueOutside(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueOutsideMismatchCheckCheck() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
		{
			checker.isFloatValueOutside(this, valueA, valueMin, valueMax);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueOutsideMismatchFail() {
		failer.failFloatValueOutside(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testFloatValueOutsideMismatchWrongCheck() {
		float valueA = 0.124f;
		float valueMin = 0.1241f;
		float valueMax = 0.124f;
		if(checker.isFloatValueWithin(this, valueA, valueMin, valueMax)) // wrong call
		{
			failer.failFloatValueOutside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testFloatValueOutsideMismatchWrongFail() {
		float valueA = 0.122f;
		float valueMin = 0.123f;
		float valueMax = 0.123f;
		if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
		{
			failer.failFloatValueWithin(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailNoMessage() {
		float valueA = 0.121f;
		float valueMin = 0.122f;
		float valueMax = 0.123f;
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailMessage() {
		float valueA = 0.122f;
		float valueMin = 0.124f;
		float valueMax = 0.123f;
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testFloatValueOutsideNoFail() {
		float valueA = 0.123f;
		float valueMin = 0.123f;
		float valueMax = 0.124f;
		if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
		{
			failer.failFloatValueOutside(this, "valueA");
		}
		assertTrue("Expected valueA & valueMin to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailValuesAndNoMessage() {
		float valueA = 0.124f;
		float valueMin = -valueA;
		float valueMax = 0.123f;
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA", valueMin, valueMax);
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailValuesAndMessage() {
		float valueA = -0.124f;
		float valueMin = -0.123f;
		float valueMax = 0.123f;
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA", valueMin, valueMax, "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	// 4th - corner cases
	
	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailFloatMin() {
		float valueA = Float.MIN_VALUE;
		float valueMin = Float.intBitsToFloat(Float.floatToIntBits(Float.MIN_VALUE) + 0x1);
		float valueMax = Float.MAX_VALUE;
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailFloatMax() {
		float valueA = Float.MAX_VALUE;
		float valueMin = Float.MIN_VALUE;
		float valueMax = Float.intBitsToFloat(Float.floatToIntBits(Float.MAX_VALUE) - 0x1);
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testFloatValueOutsideFailMinusZeroVsZero() {
		float valueA = Float.intBitsToFloat(Float.floatToIntBits(0f) + 0x1);
		float valueMin = 0f;
		float valueMax = 0f;
		try
		{
			if(checker.isFloatValueOutside(this, valueA, valueMin, valueMax))
			{
				failer.failFloatValueOutside(this, "valueA", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
}
