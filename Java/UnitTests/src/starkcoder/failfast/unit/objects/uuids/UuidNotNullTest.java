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
package starkcoder.failfast.unit.objects.uuids;

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
import starkcoder.failfast.templates.objects.IObjectNotNullTest;

/**
 * Fail-fast unit test of {link:IObjectUuidNotNullCheck} and {link:IObjectUuidNotNullFail}.
 * 
 * @author Keld Oelykke
 */
public class UuidNotNullTest implements IObjectNotNullTest<UUID>{

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
	public void testObjectNotNullCheckerCallerIsNull() {
		UUID referenceNotNull = UUID.randomUUID();
		if(checker.isUuidNotNull(null, referenceNotNull))
		{
			failer.failUuidNotNull(this, "referenceNotNull");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNotNullFailerCallerIsNull() {
		UUID referenceNotNull = UUID.randomUUID();
		if(checker.isUuidNotNull(this, referenceNotNull))
		{
			failer.failUuidNotNull(null, "referenceNotNull");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotNullFailerCallerIsWrong() {
		UUID referenceNotNull = UUID.randomUUID();
		if(checker.isUuidNotNull(new String("Foo"), referenceNotNull))
		{
			failer.failUuidNotNull(new String("Bar"), "referenceNotNull");
		}
	}
	

	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotNullMismatchCheckCheck() {
		UUID referenceNotNull = UUID.randomUUID();
		if(checker.isUuidNotNull(this, referenceNotNull))
		{
			checker.isUuidNotNull(this, referenceNotNull);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotNullMismatchFail() {
		failer.failObjectNull(this, "referenceNotNull");
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotNullMismatchWrongCheck() {
		Object referenceNotNull = null;
		if(checker.isObjectNull(this, referenceNotNull)) // wrong call
		{
			failer.failUuidNotNull(this, "referenceNotNull");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotNullMismatchWrongFail() {
		UUID referenceNotNull = UUID.randomUUID();
		if(checker.isUuidNotNull(this, referenceNotNull)) 
		{
			failer.failObjectNull(this, "referenceNotNull"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectNotNullFailNoMessage() {
		UUID referenceNotNull = UUID.randomUUID();
		try
		{
			if(checker.isUuidNotNull(this, referenceNotNull))
			{
				failer.failUuidNotNull(this, "referenceNotNull");
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
	public void testObjectNotNullFailMessage() {
		UUID referenceNotNull = UUID.randomUUID();
		try
		{
			if(checker.isUuidNotNull(this, referenceNotNull))
			{
				failer.failUuidNotNull(this, "referenceNotNull", "Extra info goes here");
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
		UUID referenceNull = null;
		if(checker.isUuidNotNull(this, referenceNull))
		{
			failer.failUuidNotNull(this, "referenceNull");
		}
		assertTrue("Expected referenceNull to pass the not-null check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
