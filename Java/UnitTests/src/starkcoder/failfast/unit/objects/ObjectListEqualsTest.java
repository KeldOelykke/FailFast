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

package starkcoder.failfast.unit.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
 * Fail-fast unit test of {link:IObjectListEqualsCheck} and {link:IObjectListEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class ObjectListEqualsTest
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
  public void testObjectListEqualsCheckerCallerIsNull()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = null;
    if (checker.isObjectListEquals(null, referenceA, referenceB))
    {
      failer.failObjectListEquals(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectListEqualsFailerCallerIsNull()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = null;
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      failer.failObjectListEquals(null, "referenceA", "referenceB");
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testObjectListEqualsFailerCallerIsWrong()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = null;
    if (checker.isObjectListEquals(new String("Foo"), referenceA, referenceB))
    {
      failer.failObjectListEquals(new String("Bar"), "referenceA", "referenceB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testObjectListEqualsMismatchCheckCheck()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = null;
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      checker.isObjectListEquals(this, referenceA, referenceB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectListEqualsMismatchFail()
  {
    failer.failObjectListEquals(this, "referenceA", "referenceB");
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectListEqualsMismatchWrongCheck()
  {
    List<Object> referenceA = null;
    Object[] referenceB = new Object[]
    {
      null
    };
    if (checker.isObjectNotEquals(this, referenceA, referenceB)) // wrong call
    {
      failer.failObjectListEquals(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectListEqualsMismatchWrongFail()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = null;
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      failer.failObjectNotEquals(this, "referenceA", "referenceB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testObjectListEqualsNullAndNullFailNoMessage()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = null;
    try
    {
      if (checker.isObjectListEquals(this, referenceA, referenceB))
      {
        failer.failObjectListEquals(this, "referenceA", "referenceB");
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
  public void testObjectListEqualsEmptyAndEmptyFailNoMessage()
  {
    List<Object> referenceA = new LinkedList<Object>();
    List<Object> referenceB = new ArrayList<Object>();
    try
    {
      if (checker.isObjectListEquals(this, referenceA, referenceB))
      {
        failer.failObjectListEquals(this, "referenceA", "referenceB");
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
  public void testObjectListEquals1NullAnd1NullFailNoMessage()
  {
    List<Object> referenceA = new LinkedList<Object>();
    referenceA.add(null);
    List<Object> referenceB = new ArrayList<Object>();
    referenceB.add(null);
    try
    {
      if (checker.isObjectListEquals(this, referenceA, referenceB))
      {
        failer.failObjectListEquals(this, "referenceA", "referenceB");
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
  public void testObjectListEquals1SameAnd1SameFailNoMessage()
  {
    Object someObject = new Object();
    List<Object> referenceA = new LinkedList<Object>();
    referenceA.add(someObject);
    List<Object> referenceB = new ArrayList<Object>();
    referenceB.add(someObject);
    try
    {
      if (checker.isObjectListEquals(this, referenceA, referenceB))
      {
        failer.failObjectListEquals(this, "referenceA", "referenceB");
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
  public void testObjectListEqualsWithSameFailMessage()
  {
    List<Object> referenceA = new LinkedList<Object>();
    referenceA.add("a");
    referenceA.add("b");
    List<Object> referenceB = referenceA;
    try
    {
      if (checker.isObjectListEquals(this, referenceA, referenceB))
      {
        failer.failObjectListEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
  public void testObjectListEqualsNullAndEmptyNoFail()
  {
    List<Object> referenceA = null;
    List<Object> referenceB = new ArrayList<Object>();
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      failer.failObjectListEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectListEqualsEmptyAndNullNoFail()
  {
    List<Object> referenceA = new ArrayList<Object>();
    List<Object> referenceB = new ArrayList<Object>();
    referenceB.add(null);
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      failer.failObjectListEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectListEquals1NullAnd1ObjectNoFail()
  {
    List<Object> referenceA = new ArrayList<Object>();
    referenceA.add(null);
    List<Object> referenceB = new ArrayList<Object>();
    referenceB.add(new Object());
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      failer.failObjectListEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectListEquals1ObjectAnd1ObjectNoFail()
  {
    List<Object> referenceA = new ArrayList<Object>();
    referenceA.add(new Object());
    List<Object> referenceB = new ArrayList<Object>();
    referenceB.add(new Object());
    if (checker.isObjectListEquals(this, referenceA, referenceB))
    {
      failer.failObjectListEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

}
