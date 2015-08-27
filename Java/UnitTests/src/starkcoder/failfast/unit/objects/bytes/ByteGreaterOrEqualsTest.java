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

package starkcoder.failfast.unit.objects.bytes;

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
import starkcoder.failfast.templates.comparables.IComparableGreaterOrEqualsTest;

/**
 * Fail-fast unit test of {link:IObjectByteGreaterOrEqualsCheck} and
 * {link:IObjectByteGreaterOrEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class ByteGreaterOrEqualsTest implements IComparableGreaterOrEqualsTest<Byte>
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
  public void testComparableGreaterOrEqualsCheckerCallerIsNull()
  {
    byte valueA = 124;
    byte valueB = 123;
    if (checker.isByteGreaterOrEquals(null, valueA, valueB))
    {
      failer.failByteGreaterOrEquals(this, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testComparableGreaterOrEqualsFailerCallerIsNull()
  {
    byte valueA = 124;
    byte valueB = 123;
    if (checker.isByteGreaterOrEquals(this, valueA, valueB))
    {
      failer.failByteGreaterOrEquals(null, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableGreaterOrEqualsFailerCallerIsWrong()
  {
    byte valueA = 124;
    byte valueB = 123;
    if (checker.isByteGreaterOrEquals(new String("Foo"), valueA, valueB))
    {
      failer.failByteGreaterOrEquals(new String("Bar"), "valueA", "valueB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testComparableGreaterOrEqualsMismatchCheckCheck()
  {
    byte valueA = 124;
    byte valueB = 123;
    if (checker.isByteGreaterOrEquals(this, valueA, valueB))
    {
      checker.isByteGreaterOrEquals(this, valueA, valueB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableGreaterOrEqualsMismatchFail()
  {
    failer.failByteGreaterOrEquals(this, "valueA", "valueB");
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableGreaterOrEqualsMismatchWrongCheck()
  {
    byte valueA = 122;
    byte valueB = 123;
    if (checker.isByteLess(this, valueA, valueB)) // wrong call
    {
      failer.failByteGreaterOrEquals(this, "valueA", "valueB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testComparableGreaterOrEqualsMismatchWrongFail()
  {
    byte valueA = 123;
    byte valueB = 122;
    if (checker.isByteGreaterOrEquals(this, valueA, valueB))
    {
      failer.failByteLess(this, "valueA", "valueB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testComparableGreaterFailNoMessage()
  {
    byte valueA = 125;
    byte valueB = 124;
    try
    {
      if (checker.isByteGreaterOrEquals(this, valueA, valueB))
      {
        failer.failByteGreaterOrEquals(this, "valueA", "valueB");
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
  public void testComparableGreaterFailMessage()
  {
    byte valueA = 124;
    byte valueB = -124;
    try
    {
      if (checker.isByteGreaterOrEquals(this, valueA, valueB))
      {
        failer.failByteGreaterOrEquals(this, "valueA", "valueB", "Extra info goes here");
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
  public void testComparableEqualsFailNoMessage()
  {
    byte valueA = -124;
    byte valueB = -124;
    try
    {
      if (checker.isByteGreaterOrEquals(this, valueA, valueB))
      {
        failer.failByteGreaterOrEquals(this, "valueA", "valueB");
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
  public void testComparableEqualsFailMessage()
  {
    byte valueA = 125;
    byte valueB = 125;
    try
    {
      if (checker.isByteGreaterOrEquals(this, valueA, valueB))
      {
        failer.failByteGreaterOrEquals(this, "valueA", "valueB", "Extra info goes here");
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
  public void testComparableGreaterOrEqualsNoFail()
  {
    byte valueA = 122;
    byte valueB = 123;
    if (checker.isByteGreaterOrEquals(this, valueA, valueB))
    {
      failer.failByteGreaterOrEquals(this, "valueA", "valueB");
    }
    assertTrue("Expected valueA & valueB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
