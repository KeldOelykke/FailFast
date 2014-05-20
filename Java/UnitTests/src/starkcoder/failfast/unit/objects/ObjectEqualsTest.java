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
package starkcoder.failfast.unit.objects;

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
 * Fail-fast unit test of {link:IObjectEqualsCheck} and {link:IObjectEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class ObjectEqualsTest {

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
	public void testObjectEqualsCheckerCallerIsNull() {
		Object referenceA = null;
		Object referenceB = null;
		if(checker.isObjectEquals(null, referenceA, referenceB))
		{
			failer.failObjectEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectEqualsFailerCallerIsNull() {
		Object referenceA = null;
		Object referenceB = null;
		if(checker.isObjectEquals(this, referenceA, referenceB))
		{
			failer.failObjectEquals(null, "referenceA", "referenceB");
		}

	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectEqualsFailerCallerIsWrong() {
		Object referenceA = null;
		Object referenceB = null;
		if(checker.isObjectEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectEqualsMismatchCheckCheck() {
		Object referenceA = null;
		Object referenceB = null;
		if(checker.isObjectEquals(this, referenceA, referenceB))
		{
			checker.isObjectEquals(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectEqualsMismatchFail() {
		failer.failObjectEquals(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectEqualsMismatchWrongCheck() {
		Object referenceA = null;
		Object referenceB = new Object();
		if(checker.isObjectNotEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectEqualsMismatchWrongFail() {
		Object referenceA = null;
		Object referenceB = null;
		if(checker.isObjectEquals(this, referenceA, referenceB))
		{
			failer.failObjectNotEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectEqualsFailNoMessage() {
		Object referenceA = null;
		Object referenceB = null;
		if(checker.isObjectEquals(this, referenceA, referenceB))
		{
			failer.failObjectEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectEqualsFailMessage() {
		Object referenceA = new Object();
		Object referenceB = referenceA;
		if(checker.isObjectEquals(this, referenceA, referenceB))
		{
			failer.failObjectEquals(this, "referenceA", "referenceB", "additional info");
		}
	}
	
	@Test
	public void testObjectEqualsNoFail() {
		Object referenceA = new Object();
		Object referenceB = new Object();
		if(checker.isObjectEquals(this, referenceA, referenceB))
		{
			failer.failObjectEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
	}

}
