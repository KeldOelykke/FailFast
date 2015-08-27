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

package starkcoder.failfast.unit.objects.strings;

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
 * Fail-fast unit test of {link:IObjectStringWithoutPrefixCheck} and
 * {link:IObjectStringWithoutPrefixFail}.
 * 
 * @author Keld Oelykke
 */
public class StringWithoutPrefixTest
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
  public void testStringWithoutPrefixCheckerCallerIsNull()
  {
    String referenceA = "okay";
    String prefix = "xyz";
    if (checker.isStringWithoutPrefix(null, referenceA, prefix))
    {
      failer.failStringWithoutPrefix(this, "referenceA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringWithoutPrefixFailerCallerIsNull()
  {
    String referenceA = "okay";
    String prefix = "xyz";
    if (checker.isStringWithoutPrefix(this, referenceA, prefix))
    {
      failer.failStringWithoutPrefix(null, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringFailerCallerIsWrong()
  {
    String referenceA = "okay";
    String prefix = "xyz";
    if (checker.isStringWithoutPrefix(new String("Foo"), referenceA, prefix))
    {
      failer.failStringWithoutPrefix(new String("Bar"), "referenceA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutPrefixMismatchCheckCheck()
  {
    String referenceA = "okay";
    String prefix = "xyz";
    if (checker.isStringWithoutPrefix(this, referenceA, prefix))
    {
      checker.isStringWithoutPrefix(this, referenceA, prefix);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutPrefixMismatchFail()
  {
    failer.failStringWithoutPrefix(this, "referenceA");
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutPrefixMismatchWrongCheck()
  {
    String referenceA = "okay";
    String prefix = "ok";
    if (checker.isStringWithPrefix(this, referenceA, prefix)) // wrong call
    {
      failer.failStringWithoutPrefix(this, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutPrefixMismatchWrongFail()
  {
    String referenceA = "okay";
    String prefix = "xyz";
    if (checker.isStringWithoutPrefix(this, referenceA, prefix))
    {
      failer.failStringWithPrefix(this, "referenceA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testStringNullWithNullPrefixFail()
  {
    String referenceA = null;
    String prefix = null;
    try
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
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
  public void testStringEmptyWithNullPrefixFail()
  {
    String referenceA = "";
    String prefix = null;
    try
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
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
  public void testStringNonEmptyWithNullPrefixFail()
  {
    String referenceA = "okay";
    String prefix = null;
    try
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
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
  public void testStringNullWithEmptyPrefixFail()
  {
    String referenceA = null;
    String prefix = "";
    try
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
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
  public void testStringEmptyWithEmptyPrefixNoFail()
  {
    String referenceA = "";
    String prefix = "";
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & prefix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithEmptyPrefixFail()
  {
    String referenceA = "okay";
    String prefix = "";
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & prefix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringNonEmptyWithNonMatchingPrefixFailMessage()
  {
    String referenceA = "okay";
    String prefix = "xyz";
    try
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA", "addition info");
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
  public void testStringNonEmptyWithMatchingPrefixNoFail()
  {
    String referenceA = "okay";
    String prefix = "o";
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & prefix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithMatchingPrefix2NoFail()
  {
    String referenceA = "okay";
    String prefix = "ok";
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA", "Extra info goes here");
      }
    }
    assertTrue("Expected referenceA & prefix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithSelfPrefixNoFail()
  {
    String referenceA = "okay";
    String prefix = referenceA;
    {
      if (checker.isStringWithoutPrefix(this, referenceA, prefix))
      {
        failer.failStringWithoutPrefix(this, "referenceA", "Extra info goes here");
      }
    }
    assertTrue("Expected referenceA & prefix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
