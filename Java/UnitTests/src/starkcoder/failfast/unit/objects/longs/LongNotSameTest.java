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
import starkcoder.failfast.templates.objects.IObjectNotSameTest;

/**
 * Fail-fast unit test of {link:IObjectLongNotSameCheck} and {link:IObjectLongNotSameFail}.
 * 
 * @author Keld Oelykke
 */
public class LongNotSameTest implements IObjectNotSameTest<Long>
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
    Long referenceA = null;
    Long referenceB = new Long(0);
    if (checker.isLongNotSame(null, referenceA, referenceB))
    {
      failer.failLongNotSame(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectNotSameFailerCallerIsNull()
  {
    Long referenceA = null;
    Long referenceB = new Long(1);
    if (checker.isLongNotSame(this, referenceA, referenceB))
    {
      failer.failLongNotSame(null, "referenceA", "referenceB");
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameFailerCallerIsWrong()
  {
    Long referenceA = null;
    Long referenceB = new Long(0);
    if (checker.isLongNotSame(new String("Foo"), referenceA, referenceB))
    {
      failer.failLongNotSame(new String("Bar"), "referenceA", "referenceB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchCheckCheck()
  {
    Long referenceA = null;
    Long referenceB = new Long(1);
    if (checker.isLongNotSame(this, referenceA, referenceB))
    {
      checker.isLongNotSame(this, referenceA, referenceB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchFail()
  {
    failer.failLongNotSame(this, "referenceA", "referenceB");
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchWrongCheck()
  {
    Long referenceA = null;
    Long referenceB = null;
    if (checker.isLongSame(this, referenceA, referenceB)) // wrong call
    {
      failer.failLongNotSame(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectNotSameMismatchWrongFail()
  {
    Long referenceA = null;
    Long referenceB = new Long(0);
    if (checker.isLongNotSame(this, referenceA, referenceB))
    {
      failer.failLongSame(this, "referenceA", "referenceB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testObjectNotSameFailNoMessage()
  {
    Long referenceA = null;
    Long referenceB = new Long(1);
    try
    {
      if (checker.isLongNotSame(this, referenceA, referenceB))
      {
        failer.failLongNotSame(this, "referenceA", "referenceB");
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
    Long referenceA = new Long(0);
    Long referenceB = new Long(0);
    try
    {
      if (checker.isLongNotSame(this, referenceA, referenceB))
      {
        failer.failLongNotSame(this, "referenceA", "referenceB", "Extra info goes here");
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
    Long referenceA = Long.MAX_VALUE;
    Long referenceB = referenceA;
    if (checker.isLongNotSame(this, referenceA, referenceB))
    {
      failer.failLongNotSame(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the not-same check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
