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
package starkcoder.failfast.examples.reference2failfast.abstractclass;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import starkcoder.failfast.examples.reference2failfast.myfailfast.IMyCallContractor;
import starkcoder.failfast.examples.reference2failfast.myfailfast.IMyFailFast;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyCallContractor;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyChecker;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyFailFast;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyFailer;
import starkcoder.failfast.examples.reference2failfast.myfailfast.SMyFailFast;
import starkcoder.failfast.fails.FailFastException;

/**
 * Shows how a custom fail-fast mechanism can be setup in an application.
 * <p>
 * A static reference to fail-fast instance is setup at application startup.
 * </p>
 * <p>
 * The static reference can be accessed from application code via helper methods in an abstract class.
 * </p>
 * <p>
 * The static reference is cleared at application shutdown.
 * </p>
 * @author Keld Oelykke
 *
 */
public class EntitiesWithMyFailFastTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// this would be in you application startup section
		IMyCallContractor myCallContractor = new MyCallContractor();
		IMyFailFast myFailFastOrNull = new MyFailFast(new MyChecker(myCallContractor), new MyFailer(myCallContractor), myCallContractor);
		SMyFailFast.setMyFailFastOrNull(myFailFastOrNull);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// this would be in you application shutdown section
		SMyFailFast.setMyFailFastOrNull(null);
	}

	@Test(expected=FailFastException.class)
	public void testSomeEntityFooIsNull() {
		SomeEntity someEntity = new SomeEntity();
		Object foo = null;
		Object bar = new Object();
		someEntity.someMethod(foo, bar);
	}
	
	@Test(expected=FailFastException.class)
	public void testSomeEntityBarIsNull() {
		SomeEntity someEntity = new SomeEntity();
		Object foo = new Object();
		Object bar = null;
		someEntity.someMethod(foo, bar);
	}
	
	@Test
	public void testSomeEntityFooBarNotNull() {
		SomeEntity someEntity = new SomeEntity();
		Object foo = new Object();
		Object bar = new Object();
		someEntity.someMethod(foo, bar);
		assertTrue("Expected someEntity to pass the foo & bar null checks", true);
	}

}
