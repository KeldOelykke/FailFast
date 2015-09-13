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

package starkcoder.failfast.unit.objects.doubles;

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

/**
 * Fail-fast unit test of {link:IObjectDoubleEqualsAlmostCheck} and
 * {link:IObjectDoubleEqualsAlmostFail}.
 * 
 * @author Keld Oelykke
 */
public class DoubleEqualsAlmostTest
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
  public void testDoubleEqualsAlmostCheckerCallerIsNull()
  {
    double valueA = 0.123f;
    double valueB = 0.123f;
    if (checker.isDoubleEqualsAlmost(null, valueA, valueB))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoubleEqualsAlmostFailerCallerIsNull()
  {
    double valueA = 0.123f;
    double valueB = 0.123f;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
    {
      failer.failDoubleEqualsAlmost(null, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testDoubleFailerCallerIsWrong()
  {
    double valueA = 0.123f;
    double valueB = 0.123f;
    if (checker.isDoubleEqualsAlmost(new String("Foo"), valueA, valueB))
    {
      failer.failDoubleEqualsAlmost(new String("Bar"), "valueA", "valueB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testDoubleEqualsAlmostMismatchCheckCheck()
  {
    double valueA = 0.123f;
    double valueB = 0.123f;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
    {
      checker.isDoubleEqualsAlmost(this, valueA, valueB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testDoubleEqualsAlmostMismatchFail()
  {
    failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
  }

  @Test(expected = IllegalStateException.class)
  public void testDoubleEqualsAlmostMismatchWrongCheck()
  {
    double valueA = 0.123f;
    double valueB = 0.1241d;
    if (checker.isDoubleNotEqualsAlmost(this, valueA, valueB)) // wrong call
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testDoubleEqualsAlmostMismatchWrongFail()
  {
    double valueA = 0.123f;
    double valueB = 0.123f;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
    {
      failer.failDoubleNotEqualsAlmost(this, "valueA", "valueB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testDoubleEqualsAlmostFailNoMessage()
  {
    double valueA = 0.123f;
    double valueB = valueA;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
  public void testDoubleEqualsAlmostSameReferenceFailNoMessage()
  {
    Double referenceA = 0.123;
    Double referenceB = referenceA;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, referenceA, referenceB))
      {
        failer.failDoubleEqualsAlmost(this, "referenceA", "referenceB");
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
  public void testDoubleEqualsAlmostFailMessage()
  {
    double valueA = 0.1234f;
    double valueB = valueA;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testDoubleEqualsAlmostNoFail()
  {
    double valueA = 0.1241d;
    double valueB = 0.123f;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testDoubleEqualsAlmostBBelowARangeAbsoluteNoFail()
  {
    double valueA = 0.1241d;
    double valueB = 0.123d;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testDoubleEqualsAlmostBAboveARangeAbsoluteNoFail()
  {
    double valueA = 0.123d;
    double valueB = 0.1241d;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // 4th - method override cases

  @Test(expected = FailFastException.class)
  public void testDoubleEqualsAlmostOverrideFailNoMessage()
  {
    double valueA = 0.121d;
    double valueB = 0.12d;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB, 0.0011d))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
  public void testDoubleEqualsAlmostOverrideFailMessage()
  {
    double valueA = 0.11d;
    double valueB = 0.1d;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB, 0.011d))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testDoubleEqualsAlmostOverrideNoFail()
  {
    double valueA = 0.1234f;
    double valueB = -0.123f;
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB, 0.2d))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testDoubleEqualsAlmostOverrideNoFailTiny()
  {
    double valueA = (double) Math.pow(1, -10);
    double valueB = (double) (0.9 * Math.pow(1, -10));
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB, (double) (0.09 * Math.pow(1, -10))))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testDoubleEqualsAlmostOverrideFailTiny()
  {
    double valueA = (double) Math.pow(1, -10);
    double valueB = (double) (0.9 * Math.pow(1, -10));
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB, (double) (0.11 * Math.pow(1, -10))))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testDoubleEqualsAlmostOverrideNoFailHuge()
  {
    double valueA = (double) Math.pow(1, 10);
    double valueB = (double) (0.9 * Math.pow(1, 10));
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB, (double) (0.09 * Math.pow(1, 10))))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testDoubleEqualsAlmostOverrideFailHuge()
  {
    double valueA = (double) Math.pow(1, 10);
    double valueB = (double) (0.9 * Math.pow(1, 10));
    if (checker.isDoubleEqualsAlmost(this, valueA, valueB, (double) (0.11 * Math.pow(1, 10))))
    {
      failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // 5th - corner cases

  @Test(expected = FailFastException.class)
  public void testDoubleEqualsAlmostFailZeroVsZero()
  {
    double valueA = 0f;
    double valueB = valueA;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
  public void testDoubleEqualsAlmostFailAlmostZeroVsZero()
  {
    double valueA = 0.00001d;
    double valueB = 0f;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testDoubleEqualsAlmostFailMinusAlmostZeroVsZero()
  {
    double valueA = -0.00001d;
    double valueB = 0f;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  
  // 6th - override defaults
  
  @Test(expected = FailFastException.class)
  public void testDoubleEqualsAlmostBBelowARangeNoMoreAbsoluteFailNoMessage()
  {
    double defaultAbsoluteEpsilon = 0.01d;
    checker.setDoubleEqualsAlmostDefaultAbsoluteEpsilon(defaultAbsoluteEpsilon);
    assertEquals("Expected a custom default value", 
        (Double)defaultAbsoluteEpsilon, checker.getDoubleEqualsAlmostDefaultAbsoluteEpsilon());
    double valueA = 0.1241d;
    double valueB = 0.123d;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
  public void testDoubleEqualsAlmostBAboveARangeNoMoreAbsoluteFailNoMessage()
  {
    double defaultAbsoluteEpsilon = 0.01d;
    checker.setDoubleEqualsAlmostDefaultAbsoluteEpsilon(defaultAbsoluteEpsilon);
    assertEquals("Expected a custom default value", 
        (Double)defaultAbsoluteEpsilon, checker.getDoubleEqualsAlmostDefaultAbsoluteEpsilon());
    double valueA = 0.123d;
    double valueB = 0.1241d;
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
  public void testDoubleEqualsAlmostBBelowARangeNoMoreRelativeFailNoMessage()
  {
    double defaultRelativeEpsilon = 0.1d;
    checker.setDoubleEqualsAlmostDefaultRelativeEpsilon(defaultRelativeEpsilon);
    assertEquals("Expected a custom default value", 
        (Double)defaultRelativeEpsilon, checker.getDoubleEqualsAlmostDefaultRelativeEpsilon());
    double valueA = 1d * (double)Math.pow(10d, 10d);
    double valueB = 0.9d * (double)Math.pow(10d, 10d);
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
  public void testDoubleEqualsAlmostBAboveARangeNoMoreRelativeFailNoMessage()
  {
    double defaultRelativeEpsilon = 0.1d;
    checker.setDoubleEqualsAlmostDefaultRelativeEpsilon(defaultRelativeEpsilon);
    assertEquals("Expected a custom default value", 
        (Double)defaultRelativeEpsilon, checker.getDoubleEqualsAlmostDefaultRelativeEpsilon());
    double valueB = 0.9d * (double)Math.pow(10d, 10d);
    double valueA = 1d * (double)Math.pow(10d, 10d);
    try
    {
      if (checker.isDoubleEqualsAlmost(this, valueA, valueB))
      {
        failer.failDoubleEqualsAlmost(this, "valueA", "valueB");
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
