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

package starkcoder.failfast.examples.exceptionhandling.singlethreaded;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import starkcoder.failfast.FailFast;
import starkcoder.failfast.IFailFast;
import starkcoder.failfast.SFailFast;
import starkcoder.failfast.checks.Checker;
import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.Failer;
import starkcoder.failfast.fails.IFailFastException;

/**
 * Shows how a reactive fail-fast exception handler can be inserted in the main-loop of an
 * application.
 * <p>
 * This is reactive, since it checks whether a fast-fast exception has occurred.
 * </p>
 * <p>
 * A static reference to fail-fast instance is setup at application startup.
 * </p>
 * <p>
 * The static reference can be accessed from application code via the the static class.
 * </p>
 * <p>
 * The static reference is cleared at application shutdown.
 * </p>
 * 
 * @author Keld Oelykke
 *
 */
public class MainExceptionHandlerReactiveTest
{

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testApplicationMainLoopReactive()
  {

    { // main - this would be in you application startup section
      ICallContractor callContractor = new CallContractor();
      IFailFast failFastOrNull = new FailFast(new Checker(callContractor), new Failer(
          callContractor), callContractor);
      SFailFast.setFailFastOrNull(failFastOrNull);
    }

    { // main - this would be your main controller code e.g. event processor
      boolean shutdown = false;
      while (!shutdown)
      {
        try
        {
          // add processing calls here

          { // reactive code that detects if a fail-fast exception has occurred
            IFailFastException failFastExceptionOrNull = SFailFast.getFailer()
                .getFailFastExceptionOrNull();
            if (null != failFastExceptionOrNull)
            {
              // add logging to warn that a fail-fast exception has been thrown away
              // Nest the fail-fast exception into a new fail-fast exception to end the application
              throw new FailFastException("Looks like a class intercepted a fail-fast exception.",
                  (Exception) failFastExceptionOrNull);
            }
          }

          { // one method on the main thread call-stack may fail-fast
            // and it could catch and discard a fail-fast exception
            try
            {
              String someReference = null;
              if (SFailFast.getChecker().isObjectNull(this, someReference))
              {
                SFailFast.getFailer().failObjectNull(this, "someReference");
              }
            }
            catch (Exception exception)
            {
              // evil - discards all exceptions because developer 'knows' what happens here
            }
          }
        }
        // catch to continue looping for a know non-fatal exception, if needed
        // catch(SomeNonFatalException someNonFatalException)
        // {
        // // a non-fatal exception is ignored - logging goes here
        // // shutdown is false => main-loop continues
        // }
        catch (FailFastException failFastException)
        {
          // developer error - logging goes here
          shutdown = true;
        }
        catch (Exception exception)
        {
          // unexpected error - logging goes here
          shutdown = true;
        }
      }
    }

    { // this would be in you application shutdown section
      SFailFast.setFailFastOrNull(null);
    }
    assertTrue("application shutdown as expected", true);
  }

}
