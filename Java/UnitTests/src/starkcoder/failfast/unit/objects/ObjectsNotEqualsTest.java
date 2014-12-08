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
 * Fail-fast unit test of {link:IObjectsNotEqualsCheck} and {link:IObjectsNotEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class ObjectsNotEqualsTest {

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
	public void testObjectsArrayNotEqualsCheckerCallerIsNull() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ null };
		if(checker.isObjectsNotEquals(null, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsListNotEqualsCheckerCallerIsNull() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		if(checker.isObjectsNotEquals(null, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsCollectionNotEqualsCheckerCallerIsNull() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		if(checker.isObjectsNotEquals(null, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsArrayNotEqualsFailerCallerIsNull() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ null };
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(null, "referenceA", "referenceB");
		}

	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsListNotEqualsFailerCallerIsNull() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(null, "referenceA", "referenceB");
		}

	}
	@Test(expected=IllegalArgumentException.class)
	public void testObjectsCollectionNotEqualsFailerCallerIsNull() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			failer.failObjectsNotEquals(null, "referenceA", "referenceB");
		}

	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsArrayNotEqualsFailerCallerIsWrong() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ null };
		if(checker.isObjectsNotEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectsNotEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListNotEqualsFailerCallerIsWrong() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		if(checker.isObjectsNotEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectsNotEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionNotEqualsFailerCallerIsWrong() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		if(checker.isObjectsNotEquals(new String("Foo"), referenceA, referenceB))
		{
			failer.failObjectsNotEquals(new String("Bar"), "referenceA", "referenceB");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsArrayNotEqualsMismatchCheckCheck() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ null };
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			checker.isObjectsNotEquals(this, referenceA, referenceB);
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListNotEqualsMismatchCheckCheck() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			checker.isObjectsNotEquals(this, referenceA, referenceB);
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionNotEqualsMismatchCheckCheck() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			checker.isObjectsNotEquals(this, referenceA, referenceB);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsNotEqualsMismatchFail() {
		failer.failObjectsNotEquals(this, "referenceA", "referenceB");
	}

	@Test(expected=IllegalStateException.class)
	public void testObjectsNotEqualsMismatchWrongCheck() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListNotEqualsMismatchWrongCheck() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB");
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionNotEqualsMismatchWrongCheck() {
		Collection<Object> referenceA = null;
		Collection<Object>  referenceB = null;
		if(checker.isObjectsEquals(this, referenceA, referenceB)) // wrong call
		{
			failer.failObjectsNotEquals(this, "referenceA", "referenceB");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testObjectsArrayNotEqualsMismatchWrongFail() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ null };
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsListNotEqualsMismatchWrongFail() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	@Test(expected=IllegalStateException.class)
	public void testObjectsCollectionNotEqualsMismatchWrongFail() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		if(checker.isObjectsNotEquals(this, referenceA, referenceB))
		{
			failer.failObjectsEquals(this, "referenceA", "referenceB"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test
	public void testObjectsArrayNotEqualsNullAndNullNoFail() {
		Object[] referenceA = null;
		Object[] referenceB = null;
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListNotEqualsNullAndNullNoFail() {
		List<Object> referenceA = null;
		List<Object> referenceB = null;
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionNotEqualsNullAndNullNoFail() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = null;
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayNotEqualsEmptyAndEmptyNoFail() {
		Object[] referenceA = new Object[]{};
		Object[] referenceB = new Object[]{};
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListNotEqualsEmptyAndEmptyNoFail() {
		List<Object> referenceA = new ArrayList<Object>();
		List<Object> referenceB = new ArrayList<Object>();
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionNotEqualsEmptyAndEmptyNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		Collection<Object> referenceB = new HashSet<Object>();
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayNotEquals1NullAnd1NullNoFail() {
		Object[] referenceA = new Object[]{ null };
		Object[] referenceB = new Object[]{ null };
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListNotEquals1NullAnd1NullNoFail() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(null);
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(null);
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionNotEquals1NullAnd1NullNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(null);
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(null);
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayNotEquals1SameAnd1SameNoFail() {
		Object o = new Object();
		Object[] referenceA = new Object[]{ o };
		Object[] referenceB = new Object[]{ o };
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListNotEquals1SameAnd1SameNoFail() {
		Object o = new Object();
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(o);
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(o);
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionNotEquals1SameAnd1SameNoFail() {
		Object o = new Object();
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(o);
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(o);
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayNotEqualsWithSameNoFail() {
		Object[] referenceA = new Object[]{ "a", "b" };
		Object[] referenceB = referenceA;
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListNotEqualsWithSameNoFail() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add("a");
		referenceA.add("b");
		List<Object> referenceB = referenceA;
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionNotEqualsWithSameNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add("a");
		referenceA.add("b");
		Collection<Object> referenceB = referenceA;
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test
	public void testObjectsArrayNotEqualsWith2StringsNoFail() {
		String[] referenceA = new String[]{ "a", "b" };
		Object[] referenceB = new String[]{ "a", "b" };
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsListNotEqualsWith2StringsNoFail() {
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
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	@Test
	public void testObjectsCollectionNotEqualsWith2StringsNoFail() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add("a");
		referenceA.add("b");
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add("a");
		referenceB.add("b");
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
			}
		}
		assertTrue("Expected referenceA & referenceB to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectsArrayNotEqualsNullAndEmptyFailNoMessage() {
		Object[] referenceA = null;
		Object[] referenceB = new Object[]{ };
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListNotEqualsNullAndEmptyFailNoMessage() {
		List<Object> referenceA = null;
		List<Object> referenceB = new ArrayList<Object>();
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionNotEqualsNullAndEmptyFailNoMessage() {
		Collection<Object> referenceA = null;
		Collection<Object> referenceB = new HashSet<Object>();
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayNotEqualsEmptyAndNullFailNoMessage() {
		Object[] referenceA = new Object[]{};
		Object[] referenceB = new Object[]{ null};
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListNotEqualsEmptyAndNullFailNoMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(null);
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionNotEqualsEmptyAndNullFailNoMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(null);
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayNotEquals1NullAnd1ObjectFailNoMessage() {
		Object[] referenceA = new Object[]{ null };
		Object[] referenceB = new Object[]{ new Object() };
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsListNotEquals1NullAnd1ObjectFailNoMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(null);
		List<Object> referenceB = new ArrayList<Object>();
		referenceA.add(new Object());
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsCollectionNotEquals1NullAnd1ObjectFailNoMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(null);
		Collection<Object> referenceB = new HashSet<Object>();
		referenceA.add(new Object());
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB");
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
	public void testObjectsArrayNotEquals1ObjectAnd1ObjectFailMessage() {
		Object[] referenceA = new Object[]{ new Object() };
		Object[] referenceB = new Object[]{ new Object() };
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsListNotEquals1ObjectAnd1ObjectFailMessage() {
		List<Object> referenceA = new ArrayList<Object>();
		referenceA.add(new Object());
		List<Object> referenceB = new ArrayList<Object>();
		referenceB.add(new Object());
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
	public void testObjectsCollectionNotEquals1ObjectAnd1ObjectFailMessage() {
		Collection<Object> referenceA = new HashSet<Object>();
		referenceA.add(new Object());
		Collection<Object> referenceB = new HashSet<Object>();
		referenceB.add(new Object());
		try
		{
			if(checker.isObjectsNotEquals(this, referenceA, referenceB))
			{
				failer.failObjectsNotEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
