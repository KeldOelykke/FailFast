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

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

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
 * Fail-fast unit test of {link:IObjectCollectionEqualsCheck} and
 * {link:IObjectCollectionEqualsFail}.
 * 
 * @author Keld Oelykke
 */
public class ObjectCollectionEqualsTest
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
  public void testObjectCollectionEqualsCheckerCallerIsNull()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = null;
    if (checker.isObjectCollectionEquals(null, referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectCollectionEqualsFailerCallerIsNull()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = null;
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(null, "referenceA", "referenceB");
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testObjectCollectionEqualsFailerCallerIsWrong()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = null;
    if (checker.isObjectCollectionEquals(new String("Foo"), referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(new String("Bar"), "referenceA", "referenceB");
    }
  }

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  public void testObjectCollectionEqualsMismatchCheckCheck()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = null;
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      checker.isObjectCollectionEquals(this, referenceA, referenceB);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectCollectionEqualsMismatchFail()
  {
    failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectCollectionEqualsMismatchWrongCheck()
  {
    Collection<Object> referenceA = null;
    Object[] referenceB = new Object[]
    {
      null
    };
    if (checker.isObjectNotEquals(this, referenceA, referenceB)) // wrong call
    {
      failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testObjectCollectionEqualsMismatchWrongFail()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = null;
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      failer.failObjectNotEquals(this, "referenceA", "referenceB"); // wrong call
    }
  }

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  public void testObjectCollectionEqualsNullAndNullFailNoMessage()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = null;
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
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
  public void testObjectCollectionEqualsEmptyAndEmptyFailNoMessage()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    Collection<Object> referenceB = new TreeSet<Object>();
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
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
  public void testObjectCollectionEquals1NullAnd1NullFailNoMessage()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add(null);
    Collection<Object> referenceB = new HashSet<Object>();
    referenceB.add(null);
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
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
  public void testObjectCollectionEquals1SameAnd1SameFailNoMessage()
  {
    Object someObject = new Integer(0);
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add(someObject);
    Collection<Object> referenceB = new TreeSet<Object>();
    referenceB.add(someObject);
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
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
  public void testObjectCollectionEquals1And1FailNoMessage()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add(new Integer(1));
    Collection<Object> referenceB = new TreeSet<Object>();
    referenceB.add(new Integer(1));
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
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
  public void testObjectCollectionEqualsWithSameFailMessage()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add("a");
    referenceA.add("b");
    Collection<Object> referenceB = referenceA;
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
  public void testObjectCollectionEqualsWithAbcAndCbaFailMessage()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add("A");
    referenceA.add("B");
    referenceA.add("C");
    Collection<Object> referenceB = new TreeSet<Object>();
    referenceB.add("C");
    referenceB.add("B");
    referenceB.add("A");
    try
    {
      if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
      {
        failer.failObjectCollectionEquals(this, "referenceA", "referenceB", "Extra info goes here");
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
  public void testObjectCollectionEqualsNullAndEmptyNoFail()
  {
    Collection<Object> referenceA = null;
    Collection<Object> referenceB = new TreeSet<Object>();
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectCollectionEqualsEmptyAndNullNoFail()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    Collection<Object> referenceB = new HashSet<Object>();
    referenceB.add(null);
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectCollectionEquals1NullAnd1ObjectNoFail()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add(null);
    Collection<Object> referenceB = new TreeSet<Object>();
    referenceB.add(new Integer(0));
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  @Test
  public void testObjectCollectionEquals0And1NoFail()
  {
    Collection<Object> referenceA = new HashSet<Object>();
    referenceA.add(new Integer(0));
    Collection<Object> referenceB = new TreeSet<Object>();
    referenceB.add(new Integer(1));
    if (checker.isObjectCollectionEquals(this, referenceA, referenceB))
    {
      failer.failObjectCollectionEquals(this, "referenceA", "referenceB");
    }
    assertTrue("Expected referenceA & referenceB to pass the equals check", true);
    assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  }

  // @Test
  // public void testObjectCollectionEquals0And1NoFail_() {
  // Collection<Integer> referenceA = new HashSet<Integer>();
  // referenceA.add(new Integer(0));
  // Collection<Object> referenceB = new TreeSet<Object>();
  // referenceB.add(new Integer(1));
  // if(checker.isGenericCollectionEquals(this, referenceA, referenceB))
  // {
  // failer.failGenericCollectionEquals(this, "referenceA", "referenceB");
  // }
  // assertTrue("Expected referenceA & referenceB to pass the equals check", true);
  // assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
  // }

}
