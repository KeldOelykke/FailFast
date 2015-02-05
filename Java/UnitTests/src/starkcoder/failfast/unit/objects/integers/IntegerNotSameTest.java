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
package starkcoder.failfast.unit.objects.integers;

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
import starkcoder.failfast.templates.objects.IObjectNotSameTest;

/**
 * Fail-fast unit test of {link:IObjectIntegerNotSameCheck} and {link:IObjectIntegerNotSameFail}.
 * 
 * @author Keld Oelykke
 */
public class IntegerNotSameTest implements IObjectNotSameTest<Integer> {

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
	public void testObjectNotSameCheckerCallerIsNull() {
		Integer referenceA = null;
		Integer referenceB = new Integer(0);
		if(checker.isIntegerNotSame(null, referenceA, referenceB))
		{
			failer.failIntegerNotSame(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNotSameFailerCallerIsNull() {
		Integer referenceA = null;
		Integer referenceB = new Integer(1);
		if(checker.isIntegerNotSame(this, referenceA, referenceB))
		{
			failer.failIntegerNotSame(null, "referenceA", "referenceB");
		}

	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotSameFailerCallerIsWrong() {
		Integer referenceA = null;
		Integer referenceB = new Integer(0);
		if(checker.isIntegerNotSame(new String("Foo"), referenceA, referenceB))
		{
			failer.failIntegerNotSame(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotSameMismatchCheckCheck() {
		Integer referenceA = null;
		Integer referenceB = new Integer(1);
		if(checker.isIntegerNotSame(this, referenceA, referenceB))
		{
			checker.isIntegerNotSame(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotSameMismatchFail() {
		failer.failIntegerNotSame(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectNotSameMismatchWrongCheck() {
		Integer referenceA = null;
		Integer referenceB = null;
		if(checker.isIntegerSame(this, referenceA, referenceB)) // wrong call
		{
			failer.failIntegerNotSame(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectNotSameMismatchWrongFail() {
		Integer referenceA = null;
		Integer referenceB = new Integer(0);
		if(checker.isIntegerNotSame(this, referenceA, referenceB))
		{
			failer.failIntegerSame(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectNotSameFailNoMessage() {
		Integer referenceA = null;
		Integer referenceB = new Integer(1);
		try
		{
			if(checker.isIntegerNotSame(this, referenceA, referenceB))
			{
				failer.failIntegerNotSame(this, "referenceA", "referenceB");
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
	public void testObjectNotSameFailMessage() {
		Integer referenceA = new Integer(0);
		Integer referenceB = new Integer(0);
		try
		{
			if(checker.isIntegerNotSame(this, referenceA, referenceB))
			{
				failer.failIntegerNotSame(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectNotSameNoFail() {
		Integer referenceA = Integer.MAX_VALUE;
		Integer referenceB = referenceA;
		if(checker.isIntegerNotSame(this, referenceA, referenceB))
		{
			failer.failIntegerNotSame(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the not-same check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
