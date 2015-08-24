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

package starkcoder.failfast.examples.reference2failfast.myfailfast.withcustoms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.FailFastException;

/**
 * Fail-fast unit test of {link:IMyFooBarCheck} and {link:IMyFooBarFail}.
 * 
 * @author Keld Oelykke
 */
public class MyFooBarTest
{

  private IMyChecker checker;
  private IMyFailer failer;
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
   * Setup fail-fast instances.
   */
  @Before
  public void setUp() throws Exception
  {
    // this would be in you application startup section
    ICallContractor callContractor = new CallContractor();
    IMyFailFast failFastOrNull = new MyFailFast(new MyChecker(callContractor), new MyFailer(
        callContractor), callContractor);
    SMyFailFast.setMyFailFastOrNull(failFastOrNull);
    this.checker = SMyFailFast.getMyChecker();
    this.failer = SMyFailFast.getMyFailer();
  }

  /**
   * Clear fail-fast instances.
   */
  @After
  public void tearDown() throws Exception
  {
    // this would be in you application shutdown section
    SMyFailFast.setMyFailFastOrNull(null);
    this.checker = null;
    this.failer = null;
  }

  // 1st - caller checks

  @Test(expected = IllegalArgumentException.class)
  public void testFooBarCheckerCallerIsNull()
  {
    Foo referenceFooIsBar = new Foo(true);
    if (checker.isFooBar(null, referenceFooIsBar))
    {
      failer.failFooBar(this, "referenceFooIsBar");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFooBarFailerCallerIsNull()
  {
    Foo referenceFooIsBar = new Foo(true);
    if (checker.isFooBar(this, referenceFooIsBar))
    {
      failer.failFooBar(null, "referenceFooIsBar");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testFooBarFailerCallerIsWrong()
  {
    Foo referenceFooIsBar = new Foo(true);
    if (checker.isFooBar(new String("Foo"), referenceFooIsBar))
    {
      failer.failFooBar(new String("Bar"), "referenceFooIsBar");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testFooBarMismatchCheckCheck()
  {
    Foo referenceFooIsBar = new Foo(true);
    if (checker.isFooBar(this, referenceFooIsBar))
    {
      checker.isFooBar(this, referenceFooIsBar);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testFooBarMismatchFail()
  {
    failer.failFooBar(this, "referenceNone");
  }

  // 3rd - normal cases

  @Test
  public void testFooNullNoFail()
  {
    Foo referenceFooIsNull = null;
    if (checker.isFooBar(this, referenceFooIsNull))
    {
      failer.failFooBar(this, "referenceFooIsNull");
    }
    assertTrue("Expected referenceFooIsNull to pass the null check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testFooNotBarNoFail()
  {
    Foo referenceFooNotBar = new Foo(false);
    if (checker.isFooBar(this, referenceFooNotBar))
    {
      failer.failFooBar(this, "referenceFooNotBar");
    }
    assertTrue("Expected referenceFooNotBar to pass the Bar check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testFooBarFailNoMessage()
  {
    Foo referenceFooIsBar = new Foo(true);
    try
    {
      if (checker.isFooBar(this, referenceFooIsBar))
      {
        failer.failFooBar(this, "referenceFooIsBar");
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
  public void testFooBarFailMessage()
  {
    Foo referenceFooIsBar = new Foo(true);
    try
    {
      if (checker.isFooBar(this, referenceFooIsBar))
      {
        failer.failFooBar(this, "referenceFooIsBar", "Is this Bar'ely a problem?");
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
