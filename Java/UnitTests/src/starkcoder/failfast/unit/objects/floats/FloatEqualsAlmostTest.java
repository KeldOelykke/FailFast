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

/**
 * Fail-fast unit test of {link:IObjectFloatEqualsAlmostCheck} and
 * {link:IObjectFloatEqualsAlmostFail}.
 * 
 * @author Keld Oelykke
 */
public class FloatEqualsAlmostTest
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
  public void testFloatEqualsAlmostCheckerCallerIsNull()
  {
    float valueA = 0.123f;
    float valueB = 0.123f;
    if (checker.isFloatEqualsAlmost(null, valueA, valueB))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFloatEqualsAlmostFailerCallerIsNull()
  {
    float valueA = 0.123f;
    float valueB = 0.123f;
    if (checker.isFloatEqualsAlmost(this, valueA, valueB))
    {
      failer.failFloatEqualsAlmost(null, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testFloatFailerCallerIsWrong()
  {
    float valueA = 0.123f;
    float valueB = 0.123f;
    if (checker.isFloatEqualsAlmost(new String("Foo"), valueA, valueB))
    {
      failer.failFloatEqualsAlmost(new String("Bar"), "valueA", "valueB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testFloatEqualsAlmostMismatchCheckCheck()
  {
    float valueA = 0.123f;
    float valueB = 0.123f;
    if (checker.isFloatEqualsAlmost(this, valueA, valueB))
    {
      checker.isFloatEqualsAlmost(this, valueA, valueB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testFloatEqualsAlmostMismatchFail()
  {
    failer.failFloatEqualsAlmost(this, "valueA", "valueB");
  }

  @Test(expected = IllegalStateException.class)
  public void testFloatEqualsAlmostMismatchWrongCheck()
  {
    float valueA = 0.123f;
    float valueB = 0.1241f;
    if (checker.isFloatNotEqualsAlmost(this, valueA, valueB)) // wrong call
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testFloatEqualsAlmostMismatchWrongFail()
  {
    float valueA = 0.123f;
    float valueB = 0.123f;
    if (checker.isFloatEqualsAlmost(this, valueA, valueB))
    {
      failer.failFloatNotEqualsAlmost(this, "valueA", "valueB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testFloatEqualsAlmostFailNoMessage()
  {
    float valueA = 0.123f;
    float valueB = valueA;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
  public void testFloatEqualsAlmostSameReferenceFailNoMessage()
  {
    Float referenceA = 0.123f;
    Float referenceB = referenceA;
    try
    {
      if (checker.isFloatEqualsAlmost(this, referenceA, referenceB))
      {
        failer.failFloatEqualsAlmost(this, "referenceA", "referenceB");
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
  public void testFloatEqualsAlmostFailMessage()
  {
    float valueA = 0.1234f;
    float valueB = valueA;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testFloatEqualsAlmostBBelowARangeNoFail()
  {
    float valueA = 0.1241f;
    float valueB = 0.123f;
    if (checker.isFloatEqualsAlmost(this, valueA, valueB))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testFloatEqualsAlmostBAboveARangeNoFail()
  {
    float valueA = 0.123f;
    float valueB = 0.1241f;
    if (checker.isFloatEqualsAlmost(this, valueA, valueB))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // 4th - method override cases

  @Test(expected = FailFastException.class)
  public void testFloatEqualsAlmostOverrideFailNoMessage()
  {
    float valueA = 0.121f;
    float valueB = 0.12f;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB, 0.0011f))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
  public void testFloatEqualsAlmostOverrideFailMessage()
  {
    float valueA = 0.11f;
    float valueB = 0.1f;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB, 0.011f))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testFloatEqualsAlmostOverrideNoFail()
  {
    float valueA = 0.1234f;
    float valueB = -0.123f;
    if (checker.isFloatEqualsAlmost(this, valueA, valueB, 0.2f))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testFloatEqualsAlmostOverrideNoFailTiny()
  {
    float valueA = (float) Math.pow(1, -10);
    float valueB = (float) (0.9 * Math.pow(1, -10));
    if (checker.isFloatEqualsAlmost(this, valueA, valueB, (float) (0.09 * Math.pow(1, -10))))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testFloatEqualsAlmostOverrideFailTiny()
  {
    float valueA = (float) Math.pow(1, -10);
    float valueB = (float) (0.9 * Math.pow(1, -10));
    if (checker.isFloatEqualsAlmost(this, valueA, valueB, (float) (0.11 * Math.pow(1, -10))))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testFloatEqualsAlmostOverrideNoFailHuge()
  {
    float valueA = (float) Math.pow(1, 10);
    float valueB = (float) (0.9 * Math.pow(1, 10));
    if (checker.isFloatEqualsAlmost(this, valueA, valueB, (float) (0.09 * Math.pow(1, 10))))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testFloatEqualsAlmostOverrideFailHuge()
  {
    float valueA = (float) Math.pow(1, 10);
    float valueB = (float) (0.9 * Math.pow(1, 10));
    if (checker.isFloatEqualsAlmost(this, valueA, valueB, (float) (0.11 * Math.pow(1, 10))))
    {
      failer.failFloatEqualsAlmost(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // 5th - corner cases

  @Test(expected = FailFastException.class)
  public void testFloatEqualsAlmostFailZeroVsZero()
  {
    float valueA = 0f;
    float valueB = valueA;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
  public void testFloatEqualsAlmostFailAlmostZeroVsZero()
  {
    float valueA = 0.00001f;
    float valueB = 0f;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testFloatEqualsAlmostFailMinusAlmostZeroVsZero()
  {
    float valueA = -0.00001f;
    float valueB = 0f;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB", "Extra info goes here");
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
  public void testFloatEqualsAlmostBBelowARangeNoMoreAbsoluteFailNoMessage()
  {
    float defaultAbsoluteEpsilon = 0.01f;
    checker.setFloatEqualsAlmostDefaultAbsoluteEpsilon(defaultAbsoluteEpsilon);
    assertEquals("Expected a custom default value", 
        (Float)defaultAbsoluteEpsilon, checker.getFloatEqualsAlmostDefaultAbsoluteEpsilon());
    float valueA = 0.1241f;
    float valueB = 0.123f;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
  public void testFloatEqualsAlmostBAboveARangeNoMoreAbsoluteFailNoMessage()
  {
    float defaultAbsoluteEpsilon = 0.01f;
    checker.setFloatEqualsAlmostDefaultAbsoluteEpsilon(defaultAbsoluteEpsilon);
    assertEquals("Expected a custom default value", 
        (Float)defaultAbsoluteEpsilon, checker.getFloatEqualsAlmostDefaultAbsoluteEpsilon());
    float valueA = 0.123f;
    float valueB = 0.1241f;
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
  public void testFloatEqualsAlmostBBelowARangeNoMoreRelativeFailNoMessage()
  {
    float defaultRelativeEpsilon = 0.1f;
    checker.setFloatEqualsAlmostDefaultRelativeEpsilon(defaultRelativeEpsilon);
    assertEquals("Expected a custom default value", 
        (Float)defaultRelativeEpsilon, checker.getFloatEqualsAlmostDefaultRelativeEpsilon());
    float valueA = 1f * (float)Math.pow(10f, 10f);
    float valueB = 0.9f * (float)Math.pow(10f, 10f);
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
  public void testFloatEqualsAlmostBAboveARangeNoMoreRelativeFailNoMessage()
  {
    float defaultRelativeEpsilon = 0.1f;
    checker.setFloatEqualsAlmostDefaultRelativeEpsilon(defaultRelativeEpsilon);
    assertEquals("Expected a custom default value", 
        (Float)defaultRelativeEpsilon, checker.getFloatEqualsAlmostDefaultRelativeEpsilon());
    float valueB = 0.9f * (float)Math.pow(10f, 10f);
    float valueA = 1f * (float)Math.pow(10f, 10f);
    try
    {
      if (checker.isFloatEqualsAlmost(this, valueA, valueB))
      {
        failer.failFloatEqualsAlmost(this, "valueA", "valueB");
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
