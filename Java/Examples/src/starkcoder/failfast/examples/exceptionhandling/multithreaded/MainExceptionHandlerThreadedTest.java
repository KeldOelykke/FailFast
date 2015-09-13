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

package starkcoder.failfast.examples.exceptionhandling.multithreaded;

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
 * Shows how a reactive fail-fast exception handler can be inserted in the main-loop of a threaded
 * application.
 * <p>
 * The loops of main and each thread are reactive, since they check whether a fast-fast exception
 * has occurred.
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
public class MainExceptionHandlerThreadedTest
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
  public void testApplicationMainLoopThreaded()
  {
    for (int reruns = 100; 0 < reruns; --reruns)
    {
      int noOfThreads = 10;
      ReactiveThread[] threads = new ReactiveThread[noOfThreads];
  
      { // main - this would be in you application startup section
        ICallContractor callContractor = new CallContractor();
        IFailFast failFastOrNull = new FailFast(new Checker(callContractor), new Failer(
            callContractor), callContractor);
        SFailFast.setFailFastOrNull(failFastOrNull);
        for (int i = 0; i < noOfThreads; ++i)
        {
          threads[i] = new ReactiveThread();
        }
      }
  
      { // main - this would be your main controller code e.g. event processor
        for (int i = 0; i < noOfThreads; ++i)
        {
          System.out.println(Thread.currentThread() + ": starting thread " + threads[i]);
          threads[i].start();
          System.out.println(Thread.currentThread() + ": started thread " + threads[i]);
        }
  
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
                // add logging to warn that a fail-fast exception has been registered
                // Nest the fail-fast exception into a new fail-fast exception to end application
                throw new FailFastException("A fail-fast exception has occurred.",
                    (Exception) failFastExceptionOrNull);
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
            System.out.println(Thread.currentThread() + ": Caught failfast exception: " 
                + failFastException);
            shutdown = true;
          }
          catch (Exception exception)
          {
            // unexpected error - logging goes here
            System.out.println(Thread.currentThread() + ": Caught unexpected exception: " 
                + exception);
            shutdown = true;
          }
        }
      }
  
      { // wait for threads
        for (int i = 0; i < noOfThreads; ++i)
        {
          try
          {
            System.out.println(Thread.currentThread() + ": joining thread " + threads[i]);
            threads[i].join();
            System.out.println(Thread.currentThread() + ": joined thread " + threads[i]);
          }
          catch (InterruptedException e)
          {
            // logging goes here
            // maybe do something about it?
          }
        }
      }
  
      { // this would be in you application shutdown section
        SFailFast.setFailFastOrNull(null);
      }
      System.out.println(Thread.currentThread() + ": application shutdown as expected");
      assertTrue("application shutdown as expected", true);
    }
  }

}
