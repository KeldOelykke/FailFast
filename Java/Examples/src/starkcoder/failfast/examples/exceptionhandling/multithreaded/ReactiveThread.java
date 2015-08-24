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

import starkcoder.failfast.SFailFast;
import starkcoder.failfast.fails.IFailFastException;

/**
 * Reactive thread that terminates itself if a fail-fast exception occurs.
 * 
 * @author Keld Oelykke
 */
public class ReactiveThread extends Thread
{

  @Override
  public void run()
  {
    super.run();

    // thread start code goes here

    { // thread controller code goes here
      boolean shutdown = false;
      while (!shutdown)
      {
        // do processing
        {
          // one method on the thread call-stack may fail-fast
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

        { // reactive code that detects if a fail-fast exception has occurred
          IFailFastException failFastExceptionOrNull = SFailFast.getFailer()
              .getFailFastExceptionOrNull();
          if (null != failFastExceptionOrNull)
          {
            shutdown = true;
          }
        }
      }
    }

    // thread exit code goes here
  }

}
