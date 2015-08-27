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
import starkcoder.failfast.templates.objects.IObjectDefaultTest;

/**
 * Fail-fast unit test of {link:IObjectDoubleDefaultCheck} and {link:IObjectDoubleDefaultFail}.
 * 
 * @author Keld Oelykke
 */
public class DoubleDefaultTest implements IObjectDefaultTest<Double>
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
  public void testObjectDefaultCheckerCallerIsNull()
  {
    double valueA = 0d;
    if (checker.isDoubleDefault(null, valueA))
    {
      failer.failDoubleDefault(this, "valueA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectDefaultFailerCallerIsNull()
  {
    double valueA = 0d;
    if (checker.isDoubleDefault(this, valueA))
    {
      failer.failDoubleDefault(null, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectDefaultFailerCallerIsWrong()
  {
    double valueA = 0d;
    if (checker.isDoubleDefault(new String("Foo"), valueA))
    {
      failer.failDoubleDefault(new String("Bar"), "valueA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testObjectDefaultMismatchCheckCheck()
  {
    double valueA = 0d;
    if (checker.isDoubleDefault(this, valueA))
    {
      checker.isDoubleDefault(this, valueA);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectDefaultMismatchFail()
  {
    failer.failDoubleDefault(this, "valueA");
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectDefaultMismatchWrongCheck()
  {
    double valueA = 1d;
    if (checker.isDoubleNotDefault(this, valueA)) // wrong call
    {
      failer.failDoubleDefault(this, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectDefaultMismatchWrongFail()
  {
    double valueA = 0d;
    if (checker.isDoubleDefault(this, valueA))
    {
      failer.failDoubleNotDefault(this, "valueA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testObjectDefaultFailNoMessage()
  {
    double valueA = 0d;
    try
    {
      if (checker.isDoubleDefault(this, valueA))
      {
        failer.failDoubleDefault(this, "valueA");
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
  public void testObjectDefaultFailMessage()
  {
    double valueA = 0d;
    try
    {
      if (checker.isDoubleDefault(this, valueA))
      {
        failer.failDoubleDefault(this, "valueA", "Extra info goes here");
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
  public void testObjectNullNoFail()
  {
    Double referenceA = null;
    if (checker.isDoubleDefault(this, referenceA))
    {
      failer.failDoubleDefault(this, "referenceA");
    }
    assertTrue("Expected referenceA not to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectNotDefaultNoFail()
  {
    double valueA = 1d;
    if (checker.isDoubleDefault(this, valueA))
    {
      failer.failDoubleDefault(this, "valueA");
    }
    assertTrue("Expected valueA not to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testObjectChangedDefaultFail()
  {
    double valueA = 1d;
    checker.setDoubleDefault(valueA);
    try
    {
      if (checker.isDoubleDefault(this, valueA))
      {
        failer.failDoubleDefault(this, "valueA");
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
