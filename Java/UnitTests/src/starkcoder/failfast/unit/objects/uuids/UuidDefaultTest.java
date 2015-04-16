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
import starkcoder.failfast.templates.objects.IObjectDefaultTest;

/**
 * Fail-fast unit test of {link:IObjectUuidDefaultCheck} and {link:IObjectUuidDefaultFail}.
 * 
 * @author Keld Oelykke
 */
public class UuidDefaultTest implements IObjectDefaultTest<UUID> {

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
	public void testObjectDefaultCheckerCallerIsNull() {
		UUID valueA = new UUID(0,0);
		if(checker.isUuidDefault(null, valueA))
		{
			failer.failUuidDefault(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectDefaultFailerCallerIsNull() {
		UUID valueA = new UUID(0,0);
		if(checker.isUuidDefault(this, valueA))
		{
			failer.failUuidDefault(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectDefaultFailerCallerIsWrong() {
		UUID valueA = new UUID(0,0);
		if(checker.isUuidDefault(new String("Foo"), valueA))
		{
			failer.failUuidDefault(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectDefaultMismatchCheckCheck() {
		UUID valueA = new UUID(0,0);
		if(checker.isUuidDefault(this, valueA))
		{
			checker.isUuidDefault(this, valueA);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectDefaultMismatchFail() {
		failer.failUuidDefault(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectDefaultMismatchWrongCheck() {
		UUID valueA = UUID.randomUUID();
		if(checker.isUuidNotDefault(this, valueA)) // wrong call
		{
			failer.failUuidDefault(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectDefaultMismatchWrongFail() {
		UUID valueA = new UUID(0,0);
		if(checker.isUuidDefault(this, valueA))
		{
			failer.failUuidNotDefault(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectDefaultFailNoMessage() {
		UUID valueA = new UUID(0,0);
		try
		{
			if(checker.isUuidDefault(this, valueA))
			{
				failer.failUuidDefault(this, "valueA");
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
	public void testObjectDefaultFailMessage() {
		UUID valueA = new UUID(0,0);
		try
		{
			if(checker.isUuidDefault(this, valueA))
			{
				failer.failUuidDefault(this, "valueA", "Extra info goes here");
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
	public void testObjectNullNoFail() {
		UUID referenceA = null;
		if(checker.isUuidDefault(this, referenceA))
		{
			failer.failUuidDefault(this, "referenceA");
		}
		assertTrue("Expected referenceA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	@Test
	public void testObjectNotDefaultNoFail() {
		UUID valueA = UUID.randomUUID();
		if(checker.isUuidDefault(this, valueA))
		{
			failer.failUuidDefault(this, "valueA");
		}
		assertTrue("Expected valueA not to pass the check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectChangedDefaultFail() {
		UUID valueA = UUID.randomUUID();
		checker.setUuidDefault(valueA);
		try
		{
			if(checker.isUuidDefault(this, valueA))
			{
				failer.failUuidDefault(this, "valueA");
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
