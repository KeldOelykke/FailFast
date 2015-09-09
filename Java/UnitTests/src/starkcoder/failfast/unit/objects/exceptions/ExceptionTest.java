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

package starkcoder.failfast.unit.objects.exceptions;

import static org.junit.Assert.assertEquals;

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
 * Fail-fast unit test of {link:IObjectExceptionCheck} and {link:IObjectExceptionFail}.
 * 
 * @author Keld Oelykke
 */
public class ExceptionTest
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


  // 1st - caller and reference checks

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionCheckerCallerIsNull()
  {
    Exception referenceException = new RuntimeException("A test exception!");
    if (checker.isException(null, referenceException))
    {
      failer.failException(this, "referenceException");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionFailerCallerIsNull()
  {
    Exception referenceException = new RuntimeException("A test exception!");
    if (checker.isException(this, referenceException))
    {
      failer.failException(null, "referenceException");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testExceptionFailerCallerIsWrong()
  {
    Exception referenceException = new RuntimeException("A test exception!");
    if (checker.isException(new String("Foo"), referenceException))
    {
      failer.failException(new String("Bar"), "referenceException");
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionReferenceIsNull()
  {
    Exception referenceException = null;
    if (checker.isException(this, referenceException))
    {
      failer.failException(null, "referenceException");
    }
  }
 

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testExceptionMismatchCheckCheck()
  {
    Exception referenceException = new RuntimeException("A test exception!");
    if (checker.isException(this, referenceException))
    {
      checker.isException(this, referenceException);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testExceptionMismatchFail()
  {
    failer.failException(this, "referenceException");
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testExceptionFailNoMessage()
  {
    Exception referenceException = new RuntimeException("A test exception!");
    try
    {
      if (checker.isException(this, referenceException))
      {
        failer.failException(this, "referenceException");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      assertEquals("Expected nested exception to be set", 
          referenceException, failFastException.getCause());
      throw failFastException;

    }
  }

  @Test(expected = FailFastException.class)
  public void testExceptionFailMessage()
  {
    Exception referenceException = new RuntimeException("A test exception!");
    try
    {
      if (checker.isException(this, referenceException))
      {
        failer.failException(this, "referenceException", "Extra info goes here");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      assertEquals("Expected nested exception to be set", 
          referenceException, failFastException.getCause());
      throw failFastException;
    }
  }

}
