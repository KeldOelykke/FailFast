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

package starkcoder.failfast.unit;

import static org.junit.Assert.assertArrayEquals;
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
import starkcoder.failfast.checks.objects.IObjectNullCheck;
import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.Failer;
import starkcoder.failfast.fails.IFailer;
import starkcoder.failfast.fails.objects.IObjectNullFail;

/**
 * Fail-fast unit test of {link:IFailFastException}.
 * 
 * @author Keld Oelykke
 */
public class FailerFastExceptionTest
{

  private IChecker checker;
  private IFailer failer;
  /**
   * Setup FailFast instances.
   */
  @Before
  public void setUp()
  {
    // this trinity would be in you application startup section
    ICallContractor callContractor = new CallContractor();
    IChecker checker = new Checker();
    checker.setCallContractor(callContractor);
    IFailer failer = new Failer();
    failer.setCallContractor(callContractor);
    // easiest if you have access to each of the 3 from your code
    this.checker = checker;
    this.failer = failer;
    // if you want 1 instance grouping the trinity
    IFailFast failFastOrNull = new FailFast();
    failFastOrNull.setChecker(checker);
    failFastOrNull.setFailer(failer);
    failFastOrNull.setCallContractor(callContractor);
    // if you want static access to the trinity
    SFailFast.setFailFastOrNull(failFastOrNull);
  }

  /**
   * Clear FailFast instances.
   */
  @After
  public void tearDown()
  {
    // this would be in you application shutdown section
    IFailFast failFastOrNull = SFailFast.getFailFast();
    SFailFast.setFailFastOrNull(null);
    failFastOrNull.setChecker(null);
    failFastOrNull.setFailer(null);
    failFastOrNull.setCallContractor(null);
    this.checker.setCallContractor(null);
    this.checker = null;
    this.failer.setCallContractor(null);
    this.failer = null;
  }

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

  @Test(expected = FailFastException.class)
  public void testGetCheckerSpecificationType()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals(IObjectNullCheck.class, failFastException.getCheckerSpecificationType());
      System.out.println("failFastException.getCheckerSpecificationType() returned " 
          + failFastException.getCheckerSpecificationType());
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetCheckerUserArguments()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertArrayEquals(new Object[]{ this, referenceNull}, 
          failFastException.getCheckerUserArguments());
      System.out.println("failFastException.getCheckerUserArguments() returned " 
          + arrayToString(failFastException.getCheckerUserArguments()));
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetCheckerExtraArguments()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertArrayEquals(new Object[]{}, failFastException.getCheckerExtraArguments());
      System.out.println("failFastException.getCheckerExtraArguments() returned " 
          + arrayToString(failFastException.getCheckerExtraArguments()));
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetFailerSpecificationType()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals(IObjectNullFail.class, failFastException.getFailerSpecificationType());
      System.out.println("failFastException.getFailerSpecificationType() returned " 
          + failFastException.getFailerSpecificationType());
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetFailerUserArguments()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertArrayEquals(new Object[]{ this, "referenceNull" }, 
          failFastException.getFailerUserArguments());
      System.out.println("failFastException.getFailerUserArguments() returned " 
          + arrayToString(failFastException.getFailerUserArguments()));
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetFailerExtraArguments()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertArrayEquals(new Object[]{}, failFastException.getFailerExtraArguments());
      System.out.println("failFastException.getFailerExtraArguments() returned " 
          + arrayToString(failFastException.getFailerExtraArguments()));
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetFailMessageFormat()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("%s: Object '%s' is null.", failFastException.getFailMessageFormat());
      System.out.println("failFastException.getFailMessageFormat() returned " 
          + failFastException.getFailMessageFormat());
      throw failFastException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testGetFailMessageArguments()
  {
    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("fu0, fu1", failFastException.getFailMessageArguments());
      System.out.println("failFastException.getFailMessageArguments() returned " 
          + failFastException.getFailMessageArguments());
      throw failFastException;
    }
  }

  
  private String arrayToString(Object[] array)
  {
    String result = "null";
    if (null != array)
    {
      String temp = "[";
      for (int index = 0; index < array.length; ++index)
      {
        if (0 < index)
        {
          temp += ", ";
        }
        temp += array[index];
      }
      temp += "]";
      result = temp;
    }
    return result;
  }

}
