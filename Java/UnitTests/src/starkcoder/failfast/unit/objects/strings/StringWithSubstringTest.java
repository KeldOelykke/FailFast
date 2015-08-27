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
 * Fail-fast unit test of {link:IObjectStringWithSubstringCheck} and
 * {link:IObjectStringWithSubstringFail}.
 * 
 * @author Keld Oelykke
 */
public class StringWithSubstringTest
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
  public void testStringWithSubstringCheckerCallerIsNull()
  {
    String referenceA = "okay";
    String substring = "ka";
    if (checker.isStringWithSubstring(null, referenceA, substring))
    {
      failer.failStringWithSubstring(this, "referenceA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringWithSubstringFailerCallerIsNull()
  {
    String referenceA = "okay";
    String substring = "ka";
    if (checker.isStringWithSubstring(this, referenceA, substring))
    {
      failer.failStringWithSubstring(null, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringFailerCallerIsWrong()
  {
    String referenceA = "okay";
    String substring = "ka";
    if (checker.isStringWithSubstring(new String("Foo"), referenceA, substring))
    {
      failer.failStringWithSubstring(new String("Bar"), "referenceA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testStringWithSubstringMismatchCheckCheck()
  {
    String referenceA = "okay";
    String substring = "ka";
    if (checker.isStringWithSubstring(this, referenceA, substring))
    {
      checker.isStringWithSubstring(this, referenceA, substring);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithSubstringMismatchFail()
  {
    failer.failStringWithSubstring(this, "referenceA");
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithSubstringMismatchWrongCheck()
  {
    String referenceA = "okay";
    String substring = "xyz";
    if (checker.isStringWithoutSubstring(this, referenceA, substring)) // wrong call
    {
      failer.failStringWithSubstring(this, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithSubstringMismatchWrongFail()
  {
    String referenceA = "okay";
    String substring = "ka";
    if (checker.isStringWithSubstring(this, referenceA, substring))
    {
      failer.failStringWithoutSubstring(this, "referenceA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test
  public void testStringNullWithNullSubstringNoFail()
  {
    String referenceA = null;
    String substring = null;
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringEmptyWithNullSubstringNoFail()
  {
    String referenceA = "";
    String substring = null;
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithNullSubstringNoFail()
  {
    String referenceA = "okay";
    String substring = null;
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNullWithEmptySubstringNoFail()
  {
    String referenceA = null;
    String substring = "";
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringEmptyWithEmptySubstringFail()
  {
    String referenceA = "";
    String substring = "";
    try
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
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
  public void testStringNonEmptyWithEmptySubstringFail()
  {
    String referenceA = "okay";
    String substring = "";
    try
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
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
  public void testStringNonEmptyWithNonMatchingSubstringNoFail()
  {
    String referenceA = "okay";
    String substring = "xyz";
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringNonEmptyWithMatchingPrefixFail()
  {
    String referenceA = "okay";
    String substring = "ok";
    try
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
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
    String substring = "ka";
    try
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA", "Extra info goes here");
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
    String substring = "ay";
    try
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA");
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
    String substring = referenceA;
    try
    {
      if (checker.isStringWithSubstring(this, referenceA, substring))
      {
        failer.failStringWithSubstring(this, "referenceA", "Extra info goes here");
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
