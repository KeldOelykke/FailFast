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
import static org.junit.Assert.assertNull;

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
 * Fail-fast unit test of {link:IFailerCustomizer}.
 * 
 * @author Keld Oelykke
 *
 */
public class FailerCustomizerTest
{

  private ICallContractor contractor;
  private IChecker checker;
  private IFailer failer;

  /**
   * Setup fail-fast instances.
   */
  @Before
  public void setUp() throws Exception
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
    IFailFast failFastOrNull = new FailFast(this.checker, this.failer, this.contractor);
    // if you want static access to the trinity
    SFailFast.setFailFastOrNull(failFastOrNull);
  }

  /**
   * Clear fail-fast instances.
   */
  @After
  public void tearDown() throws Exception
  {
    // this would be in you application shutdown section
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

  // Failer Customizations - illegal usages
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetCustomFailExceptionTypeOrNullWithNullArgument()
  {
    try
    {
      this.failer.getCustomFailExceptionTypeOrNull(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRegisterCustomFailExceptionTypeWithNullArgument0()
  {
    try
    {
      this.failer.registerCustomFailExceptionType(null,
          NullPointerException.class);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRegisterCustomFailExceptionTypeWithNullArgument1()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodId,
          null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failExceptionType is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testRegisterCustomFailExceptionTypeTwice()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodId,
          NullPointerException.class);
      this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodId,
          NullPointerException.class);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "A custom fail exception '" + NullPointerException.class
          + "' has already been registered for failer specification method '"
          + failerSpecificationAndMethodId + "'";
      assertEquals(expected, illegalStateException.getMessage());
      throw illegalStateException;
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testUnregisterCustomFailExceptionTypeWithNullArgument()
  {
    try
    {
      this.failer.unregisterCustomFailExceptionType(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testUnregisterCustomFailExceptionTypeWithoutAnyRegistration()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.unregisterCustomFailExceptionType(failerSpecificationAndMethodId);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "Can't unregister a custom fail exception for failer specification method '"
          + failerSpecificationAndMethodId + "' since none is registered.";
      assertEquals(expected, illegalStateException.getMessage());
      throw illegalStateException;
    }
  }
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetCustomFailMessageFormatOrNullWithNullArgument()
  {
    try
    {
      this.failer.getCustomFailMessageFormatOrNull(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRegisterCustomFailMessageFormatWithNullArgument0()
  {
    try
    {
      this.failer.registerCustomFailMessageFormat(null, "");
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRegisterCustomFailMessageFormatWithNullArgument1()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodId, null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failMessageFormat is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testRegisterCustomFailMessageFormatTwice()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    String customFailMessageFormat = "some format";
    try
    {
      this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodId, 
          customFailMessageFormat);
      this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodId, 
          customFailMessageFormat);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "A custom fail message format '" + customFailMessageFormat
          + "' has already been registered for failer specification method '"
          + failerSpecificationAndMethodId + "'";
      assertEquals(expected, illegalStateException.getMessage());
      throw illegalStateException;
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnregisterCustomFailMessageFormatWithNullArgument()
  {
    try
    {
      this.failer.unregisterCustomFailMessageFormat(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testUnregisterCustomFailMessageFormatWithoutAnyRegistration()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.unregisterCustomFailMessageFormat(failerSpecificationAndMethodId);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = 
          "Can't unregister a custom fail message format for failer specification method '"
          + failerSpecificationAndMethodId + "' since none is registered.";
      assertEquals(expected, illegalStateException.getMessage());
      throw illegalStateException;
    }
  }

  
  @Test(expected = IllegalArgumentException.class)
  public void testGetCustomFailMessageArgumentsOrNullWithNullArgument()
  {
    try
    {
      this.failer.getCustomFailMessageArgumentsOrNull(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testRegisterCustomFailMessageArgumentsWithNullArgument0()
  {
    try
    {
      this.failer.registerCustomFailMessageArguments(null, "");
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRegisterCustomFailMessageArgumentsWithNullArgument1()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.registerCustomFailMessageArguments(failerSpecificationAndMethodId, null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failMessageArguments is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }


  @Test(expected = IllegalStateException.class)
  public void testRegisterCustomFailMessageArgumentsTwice()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    String customFailMessageArguments = "some arguments";
    try
    {
      this.failer.registerCustomFailMessageArguments(failerSpecificationAndMethodId, 
          customFailMessageArguments);
      this.failer.registerCustomFailMessageArguments(failerSpecificationAndMethodId, 
          customFailMessageArguments);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = "A custom fail message arguments '"
          + customFailMessageArguments
          + "' has already been registered for failer specification method '"
          + failerSpecificationAndMethodId + "'";
      assertEquals(expected, illegalStateException.getMessage());
      throw illegalStateException;
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testUnregisterCustomFailMessageArgumentsWithNullArgument()
  {
    try
    {
      this.failer.unregisterCustomFailMessageArguments(null);
    }
    catch (IllegalArgumentException illegalArgumentException)
    {
      assertEquals("failerSpecificationAndMethodId is null", illegalArgumentException.getMessage());
      throw illegalArgumentException;
    }
  }
  
  @Test(expected = IllegalStateException.class)
  public void testUnregisterCustomFailMessageArgumentsWithoutAnyRegistration()
  {
    String failerSpecificationAndMethodId = 
        "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
    try
    {
      this.failer.unregisterCustomFailMessageArguments(failerSpecificationAndMethodId);
    }
    catch (IllegalStateException illegalStateException)
    {
      String expected = 
          "Can't unregister a custom fail message arguments for failer specification method '"
          + failerSpecificationAndMethodId + "' since none is registered.";
      assertEquals(expected, illegalStateException.getMessage());
      throw illegalStateException;
    }
  }  

  // Failer Customizations - legal usages

  @Test(expected = NullPointerException.class)
  public void testObjectNullFailerRegisterCustomNullPointerExceptionNoMessage()
  {

    { // Failer costumizations could be done at application startup
      String failerSpecificationAndMethodId = 
          "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
      assertNull(this.failer.getCustomFailExceptionTypeOrNull(failerSpecificationAndMethodId));
      this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodId,
          NullPointerException.class);
      assertEquals(NullPointerException.class, 
          this.failer.getCustomFailExceptionTypeOrNull(failerSpecificationAndMethodId));
    }

    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull");
      }
    }
    catch (NullPointerException customException)
    {
      assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
      System.out.println(customException.getMessage());
      throw customException;
    }
  }

  @Test(expected = FailFastException.class)
  public void testObjectNullFailerUnregisterCustomNullPointerExceptionMessage()
  {

    { // Failer costumizations could be done at application startup
      String failerSpecificationAndMethodId = 
          "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
      assertNull(this.failer.getCustomFailExceptionTypeOrNull(failerSpecificationAndMethodId));
      this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodId,
          NullPointerException.class);
      assertEquals(NullPointerException.class, 
          this.failer.getCustomFailExceptionTypeOrNull(failerSpecificationAndMethodId));
      this.failer.unregisterCustomFailExceptionType(failerSpecificationAndMethodId); // revert to
                                                                                     // default
      assertNull(this.failer.getCustomFailExceptionTypeOrNull(failerSpecificationAndMethodId));
    }

    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
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
  public void testObjectNullFailerRegisterCustomFailMessageFormat()
  {

    { // Failer costumizations could be done at application startup
      String failerSpecificationAndMethodId = 
          "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
      String customFailMessageFormat = "%s: I am so tired of Object '%s' being null. %s";
      assertNull(this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
      this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodId,
          customFailMessageFormat);
      assertEquals(customFailMessageFormat, 
          this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
    }

    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      String expectedMessage = this.getClass().getSimpleName()
          + ".testObjectNullFailerRegisterCustomFailMessageFormat: " 
          + "I am so tired of Object 'referenceNull' being null. Extra info goes here.";
      assertEquals("Expected exception in failer", expectedMessage, failFastException.getMessage());
      throw failFastException;

    }
  }
  
  @Test(expected = FailFastException.class)
  public void testObjectNullFailerUnregisterCustomFailMessageFormat()
  {

    { // Failer costumizations could be done at application startup
      String failerSpecificationAndMethodId = 
          "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
      String customFailMessageFormat = "%s: I am so tired of Object '%s' being null. %s";
      assertNull(this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
      this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodId,
          customFailMessageFormat);
      assertEquals(customFailMessageFormat, 
          this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
      this.failer.unregisterCustomFailMessageFormat(failerSpecificationAndMethodId);
      assertNull(this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
    }

    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      String expectedMessage = this.getClass().getSimpleName()
          + ".testObjectNullFailerUnregisterCustomFailMessageFormat: " 
          + "Object 'referenceNull' is null. Extra info goes here.";
      assertEquals("Expected exception in failer", expectedMessage, failFastException.getMessage());
      throw failFastException;

    }
  }

  @Test(expected = FailFastException.class)
  public void testObjectNullFailerRegisterCustomFailMessageArguments()
  {

    { // Failer costumizations could be done at application startup
      String failerSpecificationAndMethodId = 
          "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
      assertNull(this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
      String customFailMessageFormat = "Object '%s' is null - reported by Caller '%s'. %s";
      this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodId,
          customFailMessageFormat);
      assertEquals(customFailMessageFormat, 
          this.failer.getCustomFailMessageFormatOrNull(failerSpecificationAndMethodId));
      
      String customFailMessageFormatArguments = "fu1, fu0, fu2";
      assertNull(this.failer.getCustomFailMessageArgumentsOrNull(failerSpecificationAndMethodId));
      this.failer.registerCustomFailMessageArguments(failerSpecificationAndMethodId,
          customFailMessageFormatArguments); // just swapping them
      assertEquals(customFailMessageFormatArguments, 
          this.failer.getCustomFailMessageArgumentsOrNull(failerSpecificationAndMethodId));
    }

    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      String expectedMessage = "Object 'referenceNull' is null - reported by Caller '"
          + this.getClass().getSimpleName()
          + ".testObjectNullFailerRegisterCustomFailMessageArguments'." 
          + " Extra info goes here.";
      assertEquals("Expected exception in failer", expectedMessage, failFastException.getMessage());
      throw failFastException;
    }
  }

  
  @Test(expected = FailFastException.class)
  public void testObjectNullFailerUnregisterCustomFailMessageArguments()
  {

    { // Failer costumizations could be done at application startup
      String failerSpecificationAndMethodId = 
          "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
      
      String customFailMessageFormatArguments = "fu1, fu0, fu2";
      assertNull(this.failer.getCustomFailMessageArgumentsOrNull(failerSpecificationAndMethodId));
      this.failer.registerCustomFailMessageArguments(failerSpecificationAndMethodId,
          customFailMessageFormatArguments); // just swapping them
      assertEquals(customFailMessageFormatArguments, 
          this.failer.getCustomFailMessageArgumentsOrNull(failerSpecificationAndMethodId));
      this.failer.unregisterCustomFailMessageArguments(failerSpecificationAndMethodId);
      assertNull(this.failer.getCustomFailMessageArgumentsOrNull(failerSpecificationAndMethodId));
    }

    Object referenceNull = null;
    try
    {
      if (checker.isObjectNull(this, referenceNull))
      {
        failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
      }
    }
    catch (FailFastException failFastException)
    {
      assertEquals("Expected registered exception in failer", failFastException,
          failer.getFailFastExceptionOrNull());
      System.out.println(failFastException.getMessage());
      String expectedMessage = this.getClass().getSimpleName()
          + ".testObjectNullFailerUnregisterCustomFailMessageArguments: " 
          + "Object 'referenceNull' is null. Extra info goes here.";
      assertEquals("Expected exception in failer", expectedMessage, failFastException.getMessage());
      throw failFastException;
    }
  }
}
