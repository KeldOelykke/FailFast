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
package starkcoder.failfast.unit.objects.enums;

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
 * Fail-fast unit test of {link:IObjectEnumEqualsCheck} and {link:IObjectEnumEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class EnumEqualsTest {

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
	public void testEnumEqualsCheckerCallerIsNull() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_A;
		if(checker.isEnumEquals(null, referenceA, referenceB))
		{
			failer.failEnumEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEnumEqualsFailerCallerIsNull() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_A;
		if(checker.isEnumEquals(this, referenceA, referenceB))
		{
			failer.failEnumEquals(null, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testEnumFailerCallerIsWrong() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_A;
		if(checker.isEnumEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failEnumEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testEnumEqualsMismatchCheckCheck() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_A;
		if(checker.isEnumEquals(this, referenceA, referenceB))
		{
			checker.isEnumEquals(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testEnumEqualsMismatchFail() {
		failer.failEnumEquals(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testEnumEqualsMismatchWrongCheck() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_B;
		if(checker.isEnumNotEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failEnumEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testEnumEqualsMismatchWrongFail() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_A;
		if(checker.isEnumEquals(this, referenceA, referenceB))
		{
			failer.failEnumNotEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testEnumNullEqualsEnumNullFail() {
		EFoo referenceA = null;
		EFoo referenceB = null;
		try
		{
			if(checker.isEnumEquals(this, referenceA, referenceB))
			{
				failer.failEnumEquals(this, "referenceA", "referenceB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testEnumNullEqualsEnumNotNullNoFail() {
		EFoo referenceA = null;
		EFoo referenceB = EFoo.VALUE_A;
		{
			if(checker.isEnumEquals(this, referenceA, referenceB))
			{
				failer.failEnumEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testEnumNotNullEqualsEnumNullNoFail() {
		EFoo referenceA = EFoo.VALUE_B;
		EFoo referenceB = null;
		{
			if(checker.isEnumEquals(this, referenceA, referenceB))
			{
				failer.failEnumEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testEnumEqualsFailNoMessage() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = referenceA;
		try
		{
			if(checker.isEnumEquals(this, referenceA, referenceB))
			{
				failer.failEnumEquals(this, "referenceA", "referenceB");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testEnumEqualsFailMessage() {
		EFoo referenceA = EFoo.VALUE_B;
		EFoo referenceB = EFoo.VALUE_B;
		try
		{
			if(checker.isEnumEquals(this, referenceA, referenceB))
			{
				failer.failEnumEquals(this, "referenceA", "referenceB", "additional info");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			throw failFastException;
		}
	}
	
	@Test
	public void testEnumEqualsNoFail() {
		EFoo referenceA = EFoo.VALUE_A;
		EFoo referenceB = EFoo.VALUE_B;
		if(checker.isEnumEquals(this, referenceA, referenceB))
		{
			failer.failEnumEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testEnumFooEqualsEnumBarNoFail() {
		EFoo referenceA = EFoo.VALUE_A;
		EBar referenceB = EBar.VALUE_A;
		if(checker.isEnumEquals(this, referenceA, referenceB))
		{
			failer.failEnumEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
