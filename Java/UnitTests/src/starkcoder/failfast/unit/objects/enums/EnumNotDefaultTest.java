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
import starkcoder.failfast.templates.objects.IObjectNotDefaultTest;

/**
 * Fail-fast unit test of {link:IObjectEnumNotDefaultCheck} and {link:IObjectEnumNotDefaultFail}.
 * 
 * @author Keld Oelykke
 */
public class EnumNotDefaultTest implements IObjectNotDefaultTest<Enum<?>> {

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
	public void testObjectNotDefaultCheckerCallerIsNull() {
		EFoo referenceA = EFoo.VALUE_B;
		if(checker.isEnumNotDefault(null, referenceA))
		{
			failer.failEnumNotDefault(this, "referenceA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNotDefaultFailerCallerIsNull() {
		EFoo referenceA = EFoo.VALUE_B;
		if(checker.isEnumNotDefault(this, referenceA))
		{
			failer.failEnumNotDefault(null, "referenceA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectFailerCallerIsWrong() {
		EFoo referenceA = EFoo.VALUE_B;
		if(checker.isEnumNotDefault(new String("Foo"), referenceA))
		{
			failer.failEnumNotDefault(new String("Bar"), "referenceA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotDefaultMismatchCheckCheck() {
		EFoo referenceA = EFoo.VALUE_B;
		if(checker.isEnumNotDefault(this, referenceA))
		{
			checker.isEnumNotDefault(this, referenceA);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotDefaultMismatchFail() {
		failer.failEnumNotDefault(this, "referenceA");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectNotDefaultMismatchWrongCheck() {
		EFoo referenceA = EFoo.VALUE_A;
		if(checker.isEnumDefault(this, referenceA)) // wrong call
		{
			failer.failEnumNotDefault(this, "referenceA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotDefaultMismatchWrongFail() {
		EFoo referenceA = EFoo.VALUE_B;
		if(checker.isEnumNotDefault(this, referenceA))
		{
			failer.failEnumDefault(this, "referenceA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectNotDefaultFailNoMessage() {
		EFoo referenceA = EFoo.VALUE_B;
		try
		{
			if(checker.isEnumNotDefault(this, referenceA))
			{
				failer.failEnumNotDefault(this, "referenceA");
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
	public void testObjectNotDefaultFailMessage() {
		EFoo referenceA = EFoo.VALUE_B;
		try
		{
			if(checker.isEnumNotDefault(this, referenceA))
			{
				failer.failEnumNotDefault(this, "referenceA", "Extra info goes here");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNullFail() {
		EFoo referenceA = null;
		try
		{
			if(checker.isEnumNotDefault(this, referenceA))
			{
				failer.failEnumNotDefault(this, "referenceA");
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
	public void testObjectDefaultNoFail() {
		EFoo referenceA = EFoo.VALUE_A;
		if(checker.isEnumNotDefault(this, referenceA))
		{
			failer.failEnumNotDefault(this, "referenceA");
		}
		assertTrue("Expected referenceA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectDefaultChangedNoFail() {
		EFoo referenceA = EFoo.VALUE_B;
		checker.setEnumDefault(referenceA);
		if(checker.isEnumNotDefault(this, referenceA))
		{
			failer.failEnumNotDefault(this, "referenceA");
		}
		assertTrue("Expected valueA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectChanged2DefaultNoFail() {
		EFoo referenceA = EFoo.VALUE_B;
		EBar referenceB = EBar.VALUE_A;
		checker.setEnumDefault(referenceA);
		checker.setEnumDefault(referenceB);
		{
			if(checker.isEnumNotDefault(this, referenceB))
			{
				failer.failEnumNotDefault(this, "referenceB");
			}
		}
		assertTrue("Expected referenceA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	@Test(expected=FailFastException.class)
	public void testObjectChanged2DefaultFail() {
		EFoo referenceA = EFoo.VALUE_A;
		EBar referenceB = EBar.VALUE_A;
		checker.setEnumDefault(referenceA);
		checker.setEnumDefault(EBar.VALUE_B);
		try
		{
			if(checker.isEnumNotDefault(this, referenceB))
			{
				failer.failEnumNotDefault(this, "referenceB");
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
