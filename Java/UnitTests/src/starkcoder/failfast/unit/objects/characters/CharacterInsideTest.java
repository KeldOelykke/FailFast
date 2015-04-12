package starkcoder.failfast.unit.objects.characters;
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
import starkcoder.failfast.templates.comparables.IComparableInsideTest;

/**
 * Fail-fast unit test of {link:IObjectCharacterInsideCheck} and {link:IObjectCharacterInsideFail}.
 * 
 * @author Keld Oelykke
 */
public class CharacterInsideTest implements IComparableInsideTest<Character> {

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
	public void testComparableInsideCheckerCallerIsNull() {
		char valueA = '\u2151';
		char valueMin = '\u2151';
		char valueMax = '\u2151';
		if(checker.isCharacterInside(null, valueA, valueMin, valueMax))
		{
			failer.failCharacterInside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testComparableInsideFailerCallerIsNull() {
		char valueA = '\u2151';
		char valueMin = '\u2151';
		char valueMax = '\u2151';
		if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
		{
			failer.failCharacterInside(null, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideFailerCallerIsWrong() {
		char valueA = '\u2151';
		char valueMin = '\u2151';
		char valueMax = '\u2151';
		if(checker.isCharacterInside(new String("Foo"), valueA, valueMin, valueMax))
		{
			failer.failCharacterInside(new String("Bar"), "valueA");
		}
	}
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchCheckCheck() {
		char valueA = '\u2151';
		char valueMin = '\u2151';
		char valueMax = '\u2151';
		if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
		{
			checker.isCharacterInside(this, valueA, valueMin, valueMax);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchFail() {
		failer.failCharacterInside(this, "valueA");
	}

	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchWrongCheck() {
		char valueA = '\u2151';
		char valueMin = '\u2153';
		char valueMax = '\u2152';
		if(checker.isCharacterOutside(this, valueA, valueMin, valueMax)) // wrong call
		{
			failer.failCharacterInside(this, "valueA");
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testComparableInsideMismatchWrongFail() {
		char valueA = '\u2151';
		char valueMin = '\u2151';
		char valueMax = '\u2151';
		if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
		{
			failer.failCharacterOutside(this, "valueA"); // wrong call
		}
	}
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	public void testComparableInsideFailNoMessage() {
		char valueA = '\u2151';
		char valueMin = '\u2150';
		char valueMax = '\u2152';
		try
		{
			if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
			{
				failer.failCharacterInside(this, "valueA");
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
	public void testComparableInsideFailMessage() {
		char valueA = '\u2151';
		char valueMin = '\u2152';
		char valueMax = '\u2150';
		try
		{
			if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
			{
				failer.failCharacterInside(this, "valueA", "Extra info goes here");
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
	public void testComparableInsideNoFail() {
		char valueA = '\u2153';
		char valueMin = '\u2151';
		char valueMax = '\u2152';
		if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
		{
			failer.failCharacterInside(this, "valueA");
		}
		assertTrue("Expected valueA & valueMin to pass the equals check", true);
		assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
	}

	
	// 4th - corner cases
	
	@Test(expected=FailFastException.class)
	public void testComparableInsideFailComparableMin() {
		char valueA = Character.MIN_VALUE;
		char valueMin = valueA;
		char valueMax = valueA;
		try
		{
			if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
			{
				failer.failCharacterInside(this, "valueA");
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
	public void testComparableInsideFailComparableMax() {
		char valueA = Character.MAX_VALUE;
		char valueMin = valueA;
		char valueMax = valueA;
		try
		{
			if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
			{
				failer.failCharacterInside(this, "valueA", "Extra info goes here");
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
	public void testCharacterInsideFailMinusZeroVsZero() {
		char valueA = -0;
		char valueMin = 0;
		char valueMax = 0;
		try
		{
			if(checker.isCharacterInside(this, valueA, valueMin, valueMax))
			{
				failer.failCharacterInside(this, "valueA", "Extra info goes here");
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
