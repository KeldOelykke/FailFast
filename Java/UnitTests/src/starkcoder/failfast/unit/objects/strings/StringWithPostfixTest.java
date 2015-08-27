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
 * Fail-fast unit test of {link:IObjectStringWithPostfixCheck} and
 * {link:IObjectStringWithPostfixFail}.
 * 
 * @author Keld Oelykke
 */
public class StringWithPostfixTest
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
  public void testStringWithPostfixCheckerCallerIsNull()
  {
    String referenceA = "okay";
    String postfix = "ay";
    if (checker.isStringWithPostfix(null, referenceA, postfix))
    {
      failer.failStringWithPostfix(this, "referenceA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringWithPostfixFailerCallerIsNull()
  {
    String referenceA = "okay";
    String postfix = "ay";
    if (checker.isStringWithPostfix(this, referenceA, postfix))
    {
      failer.failStringWithPostfix(null, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringFailerCallerIsWrong()
  {
    String referenceA = "okay";
    String postfix = "ay";
    if (checker.isStringWithPostfix(new String("Foo"), referenceA, postfix))
    {
      failer.failStringWithPostfix(new String("Bar"), "referenceA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testStringWithPostfixMismatchCheckCheck()
  {
    String referenceA = "okay";
    String postfix = "ay";
    if (checker.isStringWithPostfix(this, referenceA, postfix))
    {
      checker.isStringWithPostfix(this, referenceA, postfix);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithPostfixMismatchFail()
  {
    failer.failStringWithPostfix(this, "referenceA");
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithPostfixMismatchWrongCheck()
  {
    String referenceA = "okay";
    String postfix = "xyz";
    if (checker.isStringWithoutPostfix(this, referenceA, postfix)) // wrong call
    {
      failer.failStringWithPostfix(this, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithPostfixMismatchWrongFail()
  {
    String referenceA = "okay";
    String postfix = "ay";
    if (checker.isStringWithPostfix(this, referenceA, postfix))
    {
      failer.failStringWithoutPostfix(this, "referenceA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test
  public void testStringNullWithNullPrefixNoFail()
  {
    String referenceA = null;
    String postfix = null;
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & postfix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringEmptyWithNullPrefixNoFail()
  {
    String referenceA = "";
    String postfix = null;
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & postfix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithNullPrefixNoFail()
  {
    String referenceA = "okay";
    String postfix = null;
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & postfix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNullWithEmptyPrefixNoFail()
  {
    String referenceA = null;
    String postfix = "";
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & postfix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringEmptyWithEmptyPrefixFail()
  {
    String referenceA = "";
    String postfix = "";
    try
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
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
  public void testStringNonEmptyWithEmptyPrefixFail()
  {
    String referenceA = "okay";
    String postfix = "";
    try
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
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
  public void testStringNonEmptyWithNonMatchingPrefixNoFail()
  {
    String referenceA = "okay";
    String postfix = "xyz";
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & postfix to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringNonEmptyWithMatchingPrefixFail()
  {
    String referenceA = "okay";
    String postfix = "y";
    try
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA");
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
  public void testStringNonEmptyWithMatchingPrefixFailMessage()
  {
    String referenceA = "okay";
    String postfix = "ay";
    try
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA", "Extra info goes here");
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
  public void testStringNonEmptyWithSelfPrefixFailMessage()
  {
    String referenceA = "okay";
    String postfix = referenceA;
    try
    {
      if (checker.isStringWithPostfix(this, referenceA, postfix))
      {
        failer.failStringWithPostfix(this, "referenceA", "Extra info goes here");
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
