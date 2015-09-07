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
import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.contractors.contracts.ICallContract;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.Failer;
import starkcoder.failfast.fails.IFailer;
import starkcoder.failfast.fails.IFailerObserver;

/**
 * Fail-fast unit test of {link:IFailerObserver}.
 * 
 * @author Keld Oelykke
 */
public class FailerObserverTest implements IFailerObserver
{

  private ICallContractor contractor;
  private IChecker checker;
  private IFailer failer;
  private Object observerRegistrationKey;
  /**
   * Setup FailFast instances.
   */
  @Before
  public void setUp()
  {
    // this trinity would be in you application startup section
    ICallContractor callContractor = new CallContractor();
    IChecker checker = new Checker(callContractor);
    IFailer failer = new Failer(callContractor);
    // easiest if you have access to each of the 3 from your code
    this.contractor = callContractor;
    this.checker = checker;
    this.failer = failer;
    // if you want 1 instance grouping the trinity
    IFailFast failFastOrNull = new FailFast(checker, failer, callContractor);
    // if you want static access to the trinity
    SFailFast.setFailFastOrNull(failFastOrNull);
    // register this as observer
    this.observerRegistrationKey = this.failer.registerFailerObserver(this);
  }

  /**
   * Clear FailFast instances.
   */
  @After
  public void tearDown()
  {
    // this would be in you application shutdown section
    this.failer.unregisterFailerObserver(this, this.observerRegistrationKey);
    this.observerRegistrationKey = null;
    SFailFast.setFailFastOrNull(null);
    this.checker = null;
    this.failer = null;
    this.contractor = null;
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

  // Failer Observer - illegal usages

  @Test(expected = IllegalArgumentException.class)
  public void testRegisterFailerObserverWithNullArgument()
  {
    try
    {
      this.failer.registerFailerObserver(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerObserver is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testRegisterFailerObserverTwice()
  {
    this.failer.unregisterFailerObserver(this, this.observerRegistrationKey); // undo setup
    Object registrationKey = null;
    try
    {
      registrationKey = this.failer.registerFailerObserver(this);
      this.failer.registerFailerObserver(this);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "failerObserver " + this
          + " is already registered.";
      assertEquals(expected, illegalStateException.getMessage());
      this.failer.unregisterFailerObserver(this, registrationKey);
      this.observerRegistrationKey = this.failer.registerFailerObserver(this); // re-do setup
      throw illegalStateException;
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testUnregisterFailerObserverWithNullArgument0()
  {
    Object registrationKey = new Object();
    try
    {
      this.failer.unregisterFailerObserver(null, registrationKey);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerObserver is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnregisterFailerObserverWithNullArgument1()
  {
    try
    {
      this.failer.unregisterFailerObserver(this, null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("registrationKey is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testUnregisterFailerObserverWithoutAnyRegistration()
  {
    this.failer.unregisterFailerObserver(this, this.observerRegistrationKey); // undo setup
    try
    {
      this.failer.unregisterFailerObserver(this, new Object());
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "failerObserver " + this + " is NOT registered.";
      assertEquals(expected, illegalStateException.getMessage());
      this.observerRegistrationKey = this.failer.registerFailerObserver(this); // re-do setup
      throw illegalStateException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testUnregisterFailerObserverWithoutRegistrationKey()
  {
    this.failer.unregisterFailerObserver(this, this.observerRegistrationKey); // undo setup
    Object registrationKey0 = null;
    Object registrationKey1 = null;
    try
    {
      registrationKey0 = this.failer.registerFailerObserver(this);
      registrationKey1 = new Object();
      this.failer.unregisterFailerObserver(this, registrationKey1);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "registrationKey " + registrationKey1
          + " is NOT registered.";
      assertEquals(expected, illegalStateException.getMessage());
      this.failer.unregisterFailerObserver(this, registrationKey0);
      this.observerRegistrationKey = this.failer.registerFailerObserver(this); // re-do setup
      throw illegalStateException;
    }
  }
  

  
  // Failer Observer - legal usages
  
  @Test(expected = FailFastException.class)
  public void testObjectNullFailObserver()
  {
    Object referenceNull = null;
    try
    {
      this.notifyExceptionBeforeThrowCalled = false;
      this.notifyExceptionArgumentFailerExpected = null;
      this.notifyExceptionArgumentContractExpected = null;
      this.notifyExceptionArgumentRuntimeExceptionTypeExpected = null;

      if (checker.isObjectNull(this, referenceNull))
      {
        this.notifyExceptionArgumentFailerExpected = failer;
        this.notifyExceptionArgumentContractExpected = contractor.getContractWithCaller(this);
        this.notifyExceptionArgumentRuntimeExceptionTypeExpected = FailFastException.class;
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected notifyExceptionBeforeThrow to be called", 
          true, this.notifyExceptionBeforeThrowCalled);
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      throw failFastException;

    }
  }

  @Test(expected = NullPointerException.class)
  public void testObjectNullFailObserverCustomExceptionType()
  {
    Object referenceNull = null;
    try
    {
      this.notifyExceptionBeforeThrowCalled = false;
      this.notifyExceptionArgumentFailerExpected = null;
      this.notifyExceptionArgumentContractExpected = null;
      this.notifyExceptionArgumentRuntimeExceptionTypeExpected = null;

      if (checker.isObjectNull(this, referenceNull))
      {
        contractor.getContractWithCaller(this)
            .setCustomFailExceptionType(NullPointerException.class);
        this.notifyExceptionArgumentFailerExpected = failer;
        this.notifyExceptionArgumentContractExpected = contractor.getContractWithCaller(this);
        this.notifyExceptionArgumentRuntimeExceptionTypeExpected = NullPointerException.class;
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (NullPointerException nullPointerException)
    {
      assertEquals("Expected notifyExceptionBeforeThrow to be called", 
          true, this.notifyExceptionBeforeThrowCalled);
      assertEquals("Expected no registered exception in failer", null,
          failer.getFailFastExceptionOrNull());
      System.out.println(nullPointerException.getMessage());
      throw nullPointerException;

    }
  }


  private boolean notifyExceptionBeforeThrowCalled;
  private IFailer notifyExceptionArgumentFailerExpected;
  private ICallContract notifyExceptionArgumentContractExpected;
  private Class<?> notifyExceptionArgumentRuntimeExceptionTypeExpected;
  
  @Override
  public void notifyExceptionBeforeThrow(IFailer failer, ICallContract contract,
      RuntimeException runtimeException)
  {
    this.notifyExceptionBeforeThrowCalled = true;
    if (!failer.equals(this.notifyExceptionArgumentFailerExpected))
    {
      throw new IllegalStateException("Expected failer "
          + this.notifyExceptionArgumentFailerExpected + ", but actual failer was " + failer);
    }
    if (!contract.equals(this.notifyExceptionArgumentContractExpected))
    {
      throw new IllegalStateException("Expected callContract "
          + this.notifyExceptionArgumentContractExpected + ", but actual callContract was "
          + contract);
    }
    if (!runtimeException.getClass().equals(
        this.notifyExceptionArgumentRuntimeExceptionTypeExpected))
    {
      throw new IllegalStateException("Expected expection type "
          + this.notifyExceptionArgumentRuntimeExceptionTypeExpected
          + ", but actual exception type was " + runtimeException.getClass());
    }
    
  }

}
