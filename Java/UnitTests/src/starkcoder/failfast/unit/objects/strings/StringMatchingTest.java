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
 * Fail-fast unit test of {link:IObjectStringMatchingCheck} and {link:IObjectStringMatchingFail}.
 * 
 * @author Keld Oelykke
 */
public class StringMatchingTest
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
  public void testStringMatchingCheckerCallerIsNull()
  {
    String referenceA = "okay";
    String regex = "[kayo]{4}";
    if (checker.isStringMatching(null, referenceA, regex))
    {
      failer.failStringMatching(this, "referenceA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringMatchingFailerCallerIsNull()
  {
    String referenceA = "okay";
    String regex = "[kayo]{4}";
    if (checker.isStringMatching(this, referenceA, regex))
    {
      failer.failStringMatching(null, "referenceA", regex);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringFailerCallerIsWrong()
  {
    String referenceA = "okay";
    String regex = "[kayo]{4}";
    if (checker.isStringMatching(new String("Foo"), referenceA, regex))
    {
      failer.failStringMatching(new String("Bar"), "referenceA", regex);
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testStringMatchingMismatchCheckCheck()
  {
    String referenceA = "okay";
    String regex = "[kayo]{4}";
    if (checker.isStringMatching(this, referenceA, regex))
    {
      checker.isStringMatching(this, referenceA, regex);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringMatchingMismatchFail()
  {
    failer.failStringMatching(this, "referenceA", "[kayo]{4}");
  }

  @Test(expected = IllegalStateException.class)
  public void testStringMatchingMismatchWrongCheck()
  {
    String referenceA = "okay";
    String regex = "[cayo]";
    if (checker.isStringNotMatching(this, referenceA, regex)) // wrong call
    {
      failer.failStringMatching(this, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringMatchingMismatchWrongFail()
  {
    String referenceA = "okay";
    String regex = "[kayo]{4}";
    if (checker.isStringMatching(this, referenceA, regex))
    {
      failer.failStringNotMatching(this, "referenceA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test
  public void testStringNullWithNullSubstringNoFail()
  {
    String referenceA = null;
    String regex = null;
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & regex to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringEmptyWithNullSubstringNoFail()
  {
    String referenceA = "";
    String regex = null;
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & regex to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithNullSubstringNoFail()
  {
    String referenceA = "okay";
    String regex = null;
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & regex to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNullWithEmptySubstringNoFail()
  {
    String referenceA = null;
    String regex = "";
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & regex to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringEmptyWithEmptySubstringFail()
  {
    String referenceA = "";
    String regex = "";
    try
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
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
  public void testStringNonEmptyWithEmptySubstringNoFail()
  {
    String referenceA = "okay";
    String regex = "";
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & regex to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithNonMatchingSubstringNoFail()
  {
    String referenceA = "okay";
    String regex = "[cayo]{4}";
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & regex to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringNonEmptyWithMatchingPrefixFail()
  {
    String referenceA = "okay";
    String regex = "ok[ya]{2}";
    try
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
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
  public void testStringNonEmptyWithMatchingSubstringFailMessage()
  {
    String referenceA = "okay";
    String regex = "[kayo]{1}ka[kayo]{1}";
    try
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA", "Extra info goes here");
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
  public void testStringNonEmptyWithMatchingPostfixFail()
  {
    String referenceA = "okay";
    String regex = "[ko]{2}ay";
    try
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA");
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
  public void testStringNonEmptyWithSelfSubstringFailMessage()
  {
    String referenceA = "okay";
    String regex = referenceA;
    try
    {
      if (checker.isStringMatching(this, referenceA, regex))
      {
        failer.failStringMatching(this, "referenceA", "Extra info goes here");
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
