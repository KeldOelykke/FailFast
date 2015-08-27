/////////////////////////////////////////////////////////////////////////////////////////
//
// The MIT License (MIT)
// 
// Copyright (c) 2014-2015 Keld Oelykke
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
/////////////////////////////////////////////////////////////////////////////////////////

package starkcoder.failfast.unit.objects.longs;

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
import starkcoder.failfast.templates.comparables.IComparableOutsideTest;

/**
 * Fail-fast unit test of {link:IObjectLongOutsideCheck} and {link:IObjectLongOutsideFail}.
 * 
 * @author Keld Oelykke
 */
public class LongOutsideTest implements IComparableOutsideTest<Long>
{

  private IChecker checker;
  private IFailer failer;
  private String toString = null;

  @Override
  public String toString()
  {
    return this.toString;
  }

  @Rule
  public TestWatcher watcher = new TestWatcher()
  {
    protected void starting(Description description)
    {
      toString = description.getTestClass().getSimpleName() + "." + description.getMethodName();
    }
  };

  /**
   * Setup FailFast instances.
   */
  @Before
  public void setUp()
  {
    // this would be in you application startup section
    ICallContractor callContractor = new CallContractor();
    IFailFast failFastOrNull = new FailFast(new Checker(callContractor),
        new Failer(callContractor), callContractor);
    SFailFast.setFailFastOrNull(failFastOrNull);
    this.checker = SFailFast.getChecker();
    this.failer = SFailFast.getFailer();
  }

  /**
   * Clear FailFast instances.
   */
  @After
  public void tearDown()
  {
    // this would be in you application shutdown section
    SFailFast.setFailFastOrNull(null);
    this.checker = null;
    this.failer = null;
  }

  // 1st - caller checks

  @Test(expected = IllegalArgumentException.class)
  public void testComparableOutsideCheckerCallerIsNull()
  {
    long valueA = 122;
    long valueMin = 123;
    long valueMax = 123;
    if (checker.isLongOutside(null, valueA, valueMin, valueMax))
    {
      failer.failLongOutside(this, "valueA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testComparableOutsideFailerCallerIsNull()
  {
    long valueA = 122;
    long valueMin = 123;
    long valueMax = 123;
    if (checker.isLongOutside(this, valueA, valueMin, valueMax))
    {
      failer.failLongOutside(null, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableOutsideFailerCallerIsWrong()
  {
    long valueA = 122;
    long valueMin = 123;
    long valueMax = 123;
    if (checker.isLongOutside(new String("Foo"), valueA, valueMin, valueMax))
    {
      failer.failLongOutside(new String("Bar"), "valueA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testComparableOutsideMismatchCheckCheck()
  {
    long valueA = 122;
    long valueMin = 123;
    long valueMax = 123;
    if (checker.isLongOutside(this, valueA, valueMin, valueMax))
    {
      checker.isLongOutside(this, valueA, valueMin, valueMax);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableOutsideMismatchFail()
  {
    failer.failLongOutside(this, "valueA");
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableOutsideMismatchWrongCheck()
  {
    long valueA = 124;
    long valueMin = 1241;
    long valueMax = 124;
    if (checker.isLongInside(this, valueA, valueMin, valueMax)) // wrong call
    {
      failer.failLongOutside(this, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableOutsideMismatchWrongFail()
  {
    long valueA = 122;
    long valueMin = 123;
    long valueMax = 123;
    if (checker.isLongOutside(this, valueA, valueMin, valueMax))
    {
      failer.failLongInside(this, "valueA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testComparableOutsideFailNoMessage()
  {
    long valueA = 121;
    long valueMin = 122;
    long valueMax = 123;
    try
    {
      if (checker.isLongOutside(this, valueA, valueMin, valueMax))
      {
        failer.failLongOutside(this, "valueA");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      throw failFastException;

    }
  }

  @Test(expected = FailFastException.class)
  public void testComparableOutsideFailMessage()
  {
    long valueA = 122;
    long valueMin = 124;
    long valueMax = 123;
    try
    {
      if (checker.isLongOutside(this, valueA, valueMin, valueMax))
      {
        failer.failLongOutside(this, "valueA", "Extra info goes here");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      throw failFastException;

    }
  }

  @Test
  public void testComparableOutsideNoFail()
  {
    long valueA = 123;
    long valueMin = 123;
    long valueMax = 124;
    if (checker.isLongOutside(this, valueA, valueMin, valueMax))
    {
      failer.failLongOutside(this, "valueA");
    }
    assertTrue("Expected valueA & valueMin to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // 4th - corner cases

  @Test(expected = FailFastException.class)
  public void testComparableOutsideFailComparableMin()
  {
    long valueA = Long.MIN_VALUE;
    long valueMin = (Long.MIN_VALUE + 1);
    long valueMax = Long.MAX_VALUE;
    try
    {
      if (checker.isLongOutside(this, valueA, valueMin, valueMax))
      {
        failer.failLongOutside(this, "valueA");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      throw failFastException;

    }
  }

  @Test(expected = FailFastException.class)
  public void testComparableOutsideFailComparableMax()
  {
    long valueA = Long.MAX_VALUE;
    long valueMin = Long.MIN_VALUE;
    long valueMax = (Long.MAX_VALUE - 1);
    try
    {
      if (checker.isLongOutside(this, valueA, valueMin, valueMax))
      {
        failer.failLongOutside(this, "valueA", "Extra info goes here");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      throw failFastException;

    }
  }

  @Test(expected = FailFastException.class)
  public void testLongOutsideFailMinusZeroVsZero()
  {
    long valueA = (0 + 1);
    long valueMin = 0;
    long valueMax = 0;
    try
    {
      if (checker.isLongOutside(this, valueA, valueMin, valueMax))
      {
        failer.failLongOutside(this, "valueA", "Extra info goes here");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      throw failFastException;

    }
  }
}
