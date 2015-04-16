package starkcoder.failfast.unit.objects.uuids;
/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2015 Keld Oelykke
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

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
import starkcoder.failfast.templates.comparables.IComparableLessOrEqualsTest;

/**
 * Fail-fast unit test of {link:IObjectUuidLessOrEqualsCheck} and {link:IObjectUuidLessOrEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class UuidLessOrEqualsTest implements IComparableLessOrEqualsTest<UUID> {

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
	public void testComparableLessOrEqualsCheckerCallerIsNull() {
		UUID valueA = new UUID(0, 122);
		UUID valueB = new UUID(0, 123);
		if(checker.isUuidLessOrEquals(null, valueA, valueB))
		{
			failer.failUuidLessOrEquals(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableLessOrEqualsFailerCallerIsNull() {
		UUID valueA = new UUID(0, 122);
		UUID valueB = new UUID(0, 123);
		if(checker.isUuidLessOrEquals(this, valueA, valueB))
		{
			failer.failUuidLessOrEquals(null, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableLessOrEqualsFailerCallerIsWrong() {
		UUID valueA = new UUID(0, 122);
		UUID valueB = new UUID(0, 123);
		if(checker.isUuidLessOrEquals(new String("Foo"), valueA, valueB))
		{
			failer.failUuidLessOrEquals(new String("Bar"), "valueA", "valueB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableLessOrEqualsMismatchCheckCheck() {
		UUID valueA = new UUID(0, 122);
		UUID valueB = new UUID(0, 123);
		if(checker.isUuidLessOrEquals(this, valueA, valueB))
		{
			checker.isUuidLessOrEquals(this, valueA, valueB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableLessOrEqualsMismatchFail() {
		failer.failUuidLessOrEquals(this, "valueA", "valueB");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableLessOrEqualsMismatchWrongCheck() {
		UUID valueA = new UUID(0, 124);
		UUID valueB = new UUID(0, 123);
		if(checker.isUuidGreater(this, valueA, valueB)) // wrong call
		{
			failer.failUuidLessOrEquals(this, "valueA", "valueB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableLessOrEqualsMismatchWrongFail() {
		UUID valueA = new UUID(0, 123);
		UUID valueB = new UUID(0, 124);
		if(checker.isUuidLessOrEquals(this, valueA, valueB))
		{
			failer.failUuidGreater(this, "valueA", "valueB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableLessFailNoMessage() {
		UUID valueA = new UUID(0, 123);
		UUID valueB = new UUID(123, 0);
		try
		{
			if(checker.isUuidLessOrEquals(this, valueA, valueB))
			{
				failer.failUuidLessOrEquals(this, "valueA", "valueB");
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
	public void testComparableLessFailMessage() {
		UUID valueA = new UUID(0, 123);
		UUID valueB = new UUID(0, 124);
		try
		{
			if(checker.isUuidLessOrEquals(this, valueA, valueB))
			{
				failer.failUuidLessOrEquals(this, "valueA", "valueB", "Extra info goes here");
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
	public void testComparableEqualsFailNoMessage() {
		UUID valueA = new UUID(0, 124);
		UUID valueB = new UUID(0, 124);
		try
		{
			if(checker.isUuidLessOrEquals(this, valueA, valueB))
			{
				failer.failUuidLessOrEquals(this, "valueA", "valueB");
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
	public void testComparableEqualsFailMessage() {
		UUID valueA = UUID.randomUUID();
		UUID valueB = UUID.fromString(valueA.toString());
		try
		{
			if(checker.isUuidLessOrEquals(this, valueA, valueB))
			{
				failer.failUuidLessOrEquals(this, "valueA", "valueB", "Extra info goes here");
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
	public void testComparableLessOrEqualsNoFail() {
		UUID valueA = new UUID(0, 124);
		UUID valueB = new UUID(0, 123);
		if(checker.isUuidLessOrEquals(this, valueA, valueB))
		{
			failer.failUuidLessOrEquals(this, "valueA", "valueB");
		}
		assertTrue("Expected valueA & valueB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
