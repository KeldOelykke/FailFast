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
 * Fail-fast unit test of {link:IObjectStringWithoutSubstringCheck} and
 * {link:IObjectStringWithoutSubstringFail}.
 * 
 * @author Keld Oelykke
 */
public class StringWithoutSubstringTest
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
  public void testStringWithoutSubstringCheckerCallerIsNull()
  {
    String referenceA = "okay";
    String substring = "xyz";
    if (checker.isStringWithoutSubstring(null, referenceA, substring))
    {
      failer.failStringWithoutSubstring(this, "referenceA");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStringWithoutSubstringFailerCallerIsNull()
  {
    String referenceA = "okay";
    String substring = "xyz";
    if (checker.isStringWithoutSubstring(this, referenceA, substring))
    {
      failer.failStringWithoutSubstring(null, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringFailerCallerIsWrong()
  {
    String referenceA = "okay";
    String substring = "xyz";
    if (checker.isStringWithoutSubstring(new String("Foo"), referenceA, substring))
    {
      failer.failStringWithoutSubstring(new String("Bar"), "referenceA");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutSubstringMismatchCheckCheck()
  {
    String referenceA = "okay";
    String substring = "xyz";
    if (checker.isStringWithoutSubstring(this, referenceA, substring))
    {
      checker.isStringWithoutSubstring(this, referenceA, substring);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutSubstringMismatchFail()
  {
    failer.failStringWithoutSubstring(this, "referenceA");
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutSubstringMismatchWrongCheck()
  {
    String referenceA = "okay";
    String substring = "ka";
    if (checker.isStringWithSubstring(this, referenceA, substring)) // wrong call
    {
      failer.failStringWithoutSubstring(this, "referenceA");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testStringWithoutSubstringMismatchWrongFail()
  {
    String referenceA = "okay";
    String substring = "xyz";
    if (checker.isStringWithoutSubstring(this, referenceA, substring))
    {
      failer.failStringWithSubstring(this, "referenceA"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testStringNullWithNullSubstringFail()
  {
    String referenceA = null;
    String substring = null;
    try
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
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
  public void testStringEmptyWithNullSubstringFail()
  {
    String referenceA = "";
    String substring = null;
    try
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
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
  public void testStringNonEmptyWithNullSubstringFail()
  {
    String referenceA = "okay";
    String substring = null;
    try
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
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
  public void testStringNullWithEmptySubstringFail()
  {
    String referenceA = null;
    String substring = "";
    try
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
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
  public void testStringEmptyWithEmptySubstringNoFail()
  {
    String referenceA = "";
    String substring = "";
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithEmptySubstringFail()
  {
    String referenceA = "okay";
    String substring = "";
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test(expected = FailFastException.class)
  public void testStringNonEmptyWithNonMatchingSubstringFailMessage()
  {
    String referenceA = "okay";
    String substring = "xyz";
    try
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA", "addition info");
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
    String substring = "ka";
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithMatchingSubstringNoFail()
  {
    String referenceA = "okay";
    String substring = "ka";
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA", "Extra info goes here");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithMatchingPostfixNoFail()
  {
    String referenceA = "okay";
    String substring = "ay";
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA", "Extra info goes here");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testStringNonEmptyWithSelfSubstringNoFail()
  {
    String referenceA = "okay";
    String substring = referenceA;
    {
      if (checker.isStringWithoutSubstring(this, referenceA, substring))
      {
        failer.failStringWithoutSubstring(this, "referenceA", "Extra info goes here");
      }
    }
    assertTrue("Expected referenceA & substring to pass the check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
