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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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

/**
 * Fail-fast unit test of {link:IObjectsEqualsCheck} and {link:IObjectsEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class ObjectsEqualsTest {

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
	public void testObjectsArrayEqualsCheckerCallerIsNull() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		if(checker.isObjectsEquals(null, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsListEqualsCheckerCallerIsNull() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		if(checker.isObjectsEquals(null, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsCollectionEqualsCheckerCallerIsNull() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		if(checker.isObjectsEquals(null, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsArrayEqualsFailerCallerIsNull() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(null, "referenceA", "referenceB");
		}

	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsListEqualsFailerCallerIsNull() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(null, "referenceA", "referenceB");
		}

	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsCollectionEqualsFailerCallerIsNull() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(null, "referenceA", "referenceB");
		}

	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsArrayEqualsFailerCallerIsWrong() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		if(checker.isObjectsEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectsEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListEqualsFailerCallerIsWrong() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		if(checker.isObjectsEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectsEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionEqualsFailerCallerIsWrong() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		if(checker.isObjectsEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectsEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsArrayEqualsMismatchCheckCheck() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			checker.isObjectsEquals(this, referenceA, referenceB);
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListEqualsMismatchCheckCheck() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			checker.isObjectsEquals(this, referenceA, referenceB);
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionEqualsMismatchCheckCheck() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			checker.isObjectsEquals(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsEqualsMismatchFail() {
		failer.failObjectsEquals(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectsEqualsMismatchWrongCheck() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ null };
		if(checker.isObjectsNotEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListEqualsMismatchWrongCheck() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(null);
		if(checker.isObjectsNotEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionEqualsMismatchWrongCheck() {
		Collection<Object> referenceA = null;
		Collection<Object>  referenceB = new HashSet<Object>();
		referenceB.add(null);
		if(checker.isObjectsNotEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsArrayEqualsMismatchWrongFail() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListEqualsMismatchWrongFail() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionEqualsMismatchWrongFail() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testObjectsArrayEqualsNullAndNullFailNoMessage() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListEqualsNullAndNullFailNoMessage() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionEqualsNullAndNullFailNoMessage() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayEqualsEmptyAndEmptyFailNoMessage() {
		Object[] referenceA = new Object[]{};
		Object[] referenceB = new Object[]{};
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListEqualsEmptyAndEmptyFailNoMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		List<Object> referenceB = new ArrayList<Object>();
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionEqualsEmptyAndEmptyFailNoMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		Collection<Object> referenceB = new HashSet<Object>();
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayEquals1NullAnd1NullFailNoMessage() {
		Object[] referenceA = new Object[]{ null };
		Object[] referenceB = new Object[]{ null };
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListEquals1NullAnd1NullFailNoMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(null);
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(null);
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionEquals1NullAnd1NullFailNoMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(null);
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(null);
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayEquals1SameAnd1SameFailNoMessage() {
		Object o = new Object();
		Object[] referenceA = new Object[]{ o };
		Object[] referenceB = new Object[]{ o };
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListEquals1SameAnd1SameFailNoMessage() {
		Object o = new Object();
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(o);
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(o);
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionEquals1SameAnd1SameFailNoMessage() {
		Object o = new Object();
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(o);
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(o);
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayEqualsWithSameFailMessage() {
		Object[] referenceA = new Object[]{ "a", "b" };
		Object[] referenceB = referenceA;
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsListEqualsWithSameFailMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add("a");
		referenceA.add("b");
		List<Object> referenceB = referenceA;
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsCollectionEqualsWithSameFailMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add("a");
		referenceA.add("b");
		Collection<Object> referenceB = referenceA;
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsArrayEqualsWith2StringsFailMessage() {
		String[] referenceA = new String[]{ "a", "b" };
		Object[] referenceB = new String[]{ "a", "b" };
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsListEqualsWith2StringsFailMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		for(int a = 0; a < 1000; a++)
		{
			referenceA.add(Integer.toString(a));
		}
		List<Object> referenceB = new ArrayList<Object>();
		for(int b = 0; b < 1000; b++)
		{
			referenceB.add(Integer.toString(b));
		}
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsCollectionEqualsWith2StringsFailMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add("a");
		referenceA.add("b");
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add("a");
		referenceB.add("b");
		try
		{
			if(checker.isObjectsEquals(this, referenceA, referenceB))
			{
				failer.failObjectsEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsArrayEqualsNullAndEmptyNoFail() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ };
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListEqualsNullAndEmptyNoFail() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionEqualsNullAndEmptyNoFail() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayEqualsEmptyAndNullNoFail() {
		Object[] referenceA = new Object[]{};
		Object[] referenceB = new Object[]{ null};
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListEqualsEmptyAndNullNoFail() {
		List<Object> referenceA = new ArrayList<Object>();
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(null);
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionEqualsEmptyAndNullNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(null);
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayEquals1NullAnd1ObjectNoFail() {
		Object[] referenceA = new Object[]{ null };
		Object[] referenceB = new Object[]{ new Object() };
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListEquals1NullAnd1ObjectNoFail() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(null);
		List<Object> referenceB = new ArrayList<Object>();
		referenceA.add(new Object());
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionEquals1NullAnd1ObjectNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(null);
		Collection<Object> referenceB = new HashSet<Object>();
		referenceA.add(new Object());
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayEquals1ObjectAnd1ObjectNoFail() {
		Object[] referenceA = new Object[]{ new Object() };
		Object[] referenceB = new Object[]{ new Object() };
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListEquals1ObjectAnd1ObjectNoFail() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(new Object());
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(new Object());
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionEquals1ObjectAnd1ObjectNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(new Object());
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(new Object());
		if(checker.isObjectsEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB");
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

}
