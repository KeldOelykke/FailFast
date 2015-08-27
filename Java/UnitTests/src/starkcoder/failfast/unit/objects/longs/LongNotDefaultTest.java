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
import starkcoder.failfast.templates.objects.IObjectNotDefaultTest;

/**
 * Fail-fast unit test of {link:IObjectLongNotDefaultCheck} and {link:IObjectLongTrueFail}.
 * 
 * @author Keld Oelykke
 */
public class LongNotDefaultTest implements IObjectNotDefaultTest<Long>
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
  public void testObjectNotDefaultCheckerCallerIsNull()
  {
    long valueA = 1;
    if (checker.isLongNotDefault(null, valueA))
    {
      failer.failLongNotDefault(this, "valueA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectNotDefaultFailerCallerIsNull()
  {
    long valueA = 1;
    if (checker.isLongNotDefault(this, valueA))
    {
      failer.failLongNotDefault(null, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectFailerCallerIsWrong()
  {
    long valueA = 1;
    if (checker.isLongNotDefault(new String("Foo"), valueA))
    {
      failer.failLongNotDefault(new String("Bar"), "valueA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testObjectNotDefaultMismatchCheckCheck()
  {
    long valueA = 1;
    if (checker.isLongNotDefault(this, valueA))
    {
      checker.isLongNotDefault(this, valueA);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotDefaultMismatchFail()
  {
    failer.failLongNotDefault(this, "valueA");
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotDefaultMismatchWrongCheck()
  {
    long valueA = 0;
    if (checker.isLongDefault(this, valueA)) // wrong call
    {
      failer.failLongNotDefault(this, "valueA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotDefaultMismatchWrongFail()
  {
    long valueA = 1;
    if (checker.isLongNotDefault(this, valueA))
    {
      failer.failLongDefault(this, "valueA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testObjectNotDefaultFailNoMessage()
  {
    long valueA = 1;
    try
    {
      if (checker.isLongNotDefault(this, valueA))
      {
        failer.failLongNotDefault(this, "valueA");
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
  public void testObjectNotDefaultFailMessage()
  {
    long valueA = 1;
    try
    {
      if (checker.isLongNotDefault(this, valueA))
      {
        failer.failLongNotDefault(this, "valueA", "Extra info goes here");
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
  public void testObjectNullFail()
  {
    Long referenceA = null;
    try
    {
      if (checker.isLongNotDefault(this, referenceA))
      {
        failer.failLongNotDefault(this, "referenceA");
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
  public void testObjectDefaultNoFail()
  {
    long valueA = 0;
    if (checker.isLongNotDefault(this, valueA))
    {
      failer.failLongNotDefault(this, "valueA");
    }
    assertTrue("Expected valueA not to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectDefaultChangedNoFail()
  {
    long valueA = 1;
    checker.setLongDefault(valueA);
    if (checker.isLongNotDefault(this, valueA))
    {
      failer.failLongNotDefault(this, "valueA");
    }
    assertTrue("Expected valueA not to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
