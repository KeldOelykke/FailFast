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

package starkcoder.failfast.unit.objects.floats;

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
 * Fail-fast unit test of {link:IObjectFloatInsideCheck} and {link:IObjectFloatInsideFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatInsideTest implements IComparableInsideTest<Float>
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
  public void testComparableInsideCheckerCallerIsNull()
  {
    float valueA = 0.123f;
    float valueMin = 0.123f;
    float valueMax = 0.123f;
    if (checker.isFloatInside(null, valueA, valueMin, valueMax))
    {
      failer.failFloatInside(this, "valueA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testComparableInsideFailerCallerIsNull()
  {
    float valueA = 0.123f;
    float valueMin = 0.123f;
    float valueMax = 0.123f;
    if (checker.isFloatInside(this, valueA, valueMin, valueMax))
    {
      failer.failFloatInside(null, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableInsideFailerCallerIsWrong()
  {
    float valueA = 0.123f;
    float valueMin = 0.123f;
    float valueMax = 0.123f;
    if (checker.isFloatInside(new String("Foo"), valueA, valueMin, valueMax))
    {
      failer.failFloatInside(new String("Bar"), "valueA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testComparableInsideMismatchCheckCheck()
  {
    float valueA = 0.123f;
    float valueMin = 0.123f;
    float valueMax = 0.123f;
    if (checker.isFloatInside(this, valueA, valueMin, valueMax))
    {
      checker.isFloatInside(this, valueA, valueMin, valueMax);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableInsideMismatchFail()
  {
    failer.failFloatInside(this, "valueA");
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableInsideMismatchWrongCheck()
  {
    float valueA = 0.123f;
    float valueMin = 0.1241f;
    float valueMax = 0.124f;
    if (checker.isFloatOutside(this, valueA, valueMin, valueMax)) // wrong call
    {
      failer.failFloatInside(this, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableInsideMismatchWrongFail()
  {
    float valueA = 0.123f;
    float valueMin = 0.123f;
    float valueMax = 0.123f;
    if (checker.isFloatInside(this, valueA, valueMin, valueMax))
    {
      failer.failFloatOutside(this, "valueA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testComparableInsideFailNoMessage()
  {
    float valueA = 0.123f;
    float valueMin = 0.122f;
    float valueMax = 0.124f;
    try
    {
      if (checker.isFloatInside(this, valueA, valueMin, valueMax))
      {
        failer.failFloatInside(this, "valueA");
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
  public void testComparableInsideFailMessage()
  {
    float valueA = 0.123f;
    float valueMin = 0.124f;
    float valueMax = 0.122f;
    try
    {
      if (checker.isFloatInside(this, valueA, valueMin, valueMax))
      {
        failer.failFloatInside(this, "valueA", "Extra info goes here");
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
  public void testComparableInsideNoFail()
  {
    float valueA = 0.1241f;
    float valueMin = 0.123f;
    float valueMax = 0.124f;
    if (checker.isFloatInside(this, valueA, valueMin, valueMax))
    {
      failer.failFloatInside(this, "valueA");
    }
    assertTrue("Expected valueA & valueMin to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // 4th - corner cases

  @Test(expected = FailFastException.class)
  public void testComparableInsideFailComparableMin()
  {
    float valueA = Float.MIN_VALUE;
    float valueMin = valueA;
    float valueMax = valueA;
    try
    {
      if (checker.isFloatInside(this, valueA, valueMin, valueMax))
      {
        failer.failFloatInside(this, "valueA");
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
  public void testComparableInsideFailComparableMax()
  {
    float valueA = Float.MAX_VALUE;
    float valueMin = valueA;
    float valueMax = valueA;
    try
    {
      if (checker.isFloatInside(this, valueA, valueMin, valueMax))
      {
        failer.failFloatInside(this, "valueA", "Extra info goes here");
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
  public void testComparableInsideNoFailMinusZeroVsZero()
  {
    float valueA = -0f;
    float valueMin = 0f;
    float valueMax = 0f;
    {
      if (checker.isFloatInside(this, valueA, valueMin, valueMax))
      {
        failer.failFloatInside(this, "valueA", "Extra info goes here");
      }
    }
    assertTrue("Expected to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }
}
