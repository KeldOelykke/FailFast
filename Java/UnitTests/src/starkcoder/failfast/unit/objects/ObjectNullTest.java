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
package starkcoder.failfast.unit.objects;

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
import starkcoder.failfast.templates.objects.IObjectNullTest;

/**
 * Fail-fast unit test of {link:IObjectNullCheck} and {link:IObjectNullFail}.
 * 
 * @author Keld Oelykke
 */
public class ObjectNullTest implements IObjectNullTest<Object> {

	private ICallContractor contractor;
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
		this.contractor = callContractor;
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
	public void testObjectNullCheckerCallerIsNull() {
		Object referenceNull = null;
		if(checker.isObjectNull(null, referenceNull))
		{
			failer.failObjectNull(this, "referenceNull");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNullFailerCallerIsNull() {
		Object referenceNull = null;
		if(checker.isObjectNull(this, referenceNull))
		{
			failer.failObjectNull(null, "referenceNull");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNullFailerCallerIsWrong() {
		Object referenceNull = null;
		if(checker.isObjectNull(new String("Foo"), referenceNull))
		{
			failer.failObjectNull(new String("Bar"), "referenceNull");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNullMismatchCheckCheck() {
		Object referenceNull = null;
		if(checker.isObjectNull(this, referenceNull))
		{
			checker.isObjectNull(this, referenceNull);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNullMismatchFail() {
		failer.failObjectNull(this, "referenceNull");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectNullMismatchWrongCheck() {
		Object referenceNull = new Object();
		if(checker.isObjectNotNull(this, referenceNull)) // wrong call
		{
			failer.failObjectNull(this, "referenceNull");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNullMismatchWrongFail() {
		Object referenceNull = null;
		if(checker.isObjectNull(this, referenceNull)) 
		{
			failer.failObjectNotNull(this, "referenceNull"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectNullFailNoMessage() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull");
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
	public void testObjectNullFailMessage() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull", "Extra info goes here");
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
	public void testObjectNotNullNoFail() {
		Object referenceNotNull = new Object();
		if(checker.isObjectNull(this, referenceNotNull))
		{
			failer.failObjectNull(this, "referenceNotNull");
		}
		assertTrue("Expected referenceNotNull to pass the null check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=NullPointerException.class)
	public void testObjectNullCustomNullPointerExceptionNoMessage() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailExceptionType(NullPointerException.class);
				failer.failObjectNull(this, "referenceNull");
			}
		}
		catch(NullPointerException customException)
		{
			assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
			System.out.println(customException.getMessage());
			throw customException;
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testObjectNullCustomNullPointerExceptionMessage() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailExceptionType(NullPointerException.class);
				failer.failObjectNull(this, "referenceNull", "Extra info goes here");
			}
		}
		catch(NullPointerException customException)
		{
			assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
			System.out.println(customException.getMessage());
			throw customException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectNullCustomFailMessageFormat() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailMessageFormat("%s: I am so tired of Object '%s' being null.");
				failer.failObjectNull(this, "referenceNull", "Extra info goes here");
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
	public void testObjectNullCustomFailMessageFormatAndArguments() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailMessageFormat("Object '%s' is null - reported by Caller '%s'.");
				contractor.getContractWithCaller(this).setCustomFailMessageArguments("fu1, fu0");
				failer.failObjectNull(this, "referenceNull", "Extra info goes here");
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
