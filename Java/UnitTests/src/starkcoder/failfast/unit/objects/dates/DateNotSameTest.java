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

package starkcoder.failfast.unit.objects.dates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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
import starkcoder.failfast.templates.objects.IObjectNotSameTest;

/**
 * Fail-fast unit test of {link:IObjectDateNotSameCheck} and {link:IObjectDateNotSameFail}.
 * 
 * @author Keld Oelykke
 */
public class DateNotSameTest implements IObjectNotSameTest<Date>
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
  public void testObjectNotSameCheckerCallerIsNull()
  {
    Date referenceA = null;
    Date referenceB = new Date(0);
    if (checker.isDateNotSame(null, referenceA, referenceB))
    {
      failer.failDateNotSame(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectNotSameFailerCallerIsNull()
  {
    Date referenceA = null;
    Date referenceB = new Date(1000);
    if (checker.isDateNotSame(this, referenceA, referenceB))
    {
      failer.failDateNotSame(null, "referenceA", "referenceB");
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameFailerCallerIsWrong()
  {
    Date referenceA = null;
    Date referenceB = new Date(0);
    if (checker.isDateNotSame(new String("Foo"), referenceA, referenceB))
    {
      failer.failDateNotSame(new String("Bar"), "referenceA", "referenceB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchCheckCheck()
  {
    Date referenceA = null;
    Date referenceB = new Date(1000);
    if (checker.isDateNotSame(this, referenceA, referenceB))
    {
      checker.isDateNotSame(this, referenceA, referenceB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchFail()
  {
    failer.failDateNotSame(this, "referenceA", "referenceB");
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchWrongCheck()
  {
    Date referenceA = null;
    Date referenceB = null;
    if (checker.isDateSame(this, referenceA, referenceB)) // wrong call
    {
      failer.failDateNotSame(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchWrongFail()
  {
    Date referenceA = null;
    Date referenceB = new Date(0);
    if (checker.isDateNotSame(this, referenceA, referenceB))
    {
      failer.failDateSame(this, "referenceA", "referenceB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testObjectNotSameFailNoMessage()
  {
    Date referenceA = null;
    Date referenceB = new Date(1000);
    try
    {
      if (checker.isDateNotSame(this, referenceA, referenceB))
      {
        failer.failDateNotSame(this, "referenceA", "referenceB");
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
  public void testObjectNotSameFailMessage()
  {
    Date referenceA = new Date(0);
    Date referenceB = new Date(0);
    try
    {
      if (checker.isDateNotSame(this, referenceA, referenceB))
      {
        failer.failDateNotSame(this, "referenceA", "referenceB", "Extra info goes here");
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
  public void testObjectNotSameNoFail()
  {
    Date referenceA = new Date(Long.MAX_VALUE);
    Date referenceB = referenceA;
    if (checker.isDateNotSame(this, referenceA, referenceB))
    {
      failer.failDateNotSame(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the not-same check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
