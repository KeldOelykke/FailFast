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

package starkcoder.failfast.examples.reference2failfast.myfailfast.withcustoms;

/**
 * Static class that references a global fail-fast instance.
 * <p>
 * Initialize the static reference at startup and clear it at shutdown.
 * </p>
 * 
 * @author Keld Oelykke
 */
public final class SMyFailFast
{

  private static IMyFailFast myFailFastOrNull;

  /**
   * Retrieves the setup fail-fast instance.
   * <p>
   * If the instance is unavailable e.g. null, an IllegalStateException is thrown.
   * </p>
   * 
   * @return available instance
   */
  public static IMyFailFast getMyFailFast()
  {
    IMyFailFast myFailFast = getMyFailFastOrNull();
    if (null == myFailFast)
    {
      throw new IllegalStateException("static field myFailFast is null.");
    }
    return myFailFast;
  }

  /**
   * Retrieves the setup instance or null, if no instance is available.
   * 
   * @return instance or null
   */
  public static IMyFailFast getMyFailFastOrNull()
  {
    return SMyFailFast.myFailFastOrNull;
  }

  /**
   * Set this at startup and clear it at shutdown.
   * 
   * @param myFailFastOrNull
   *          instance to use or null to prevent usage
   */
  public static void setMyFailFastOrNull(IMyFailFast myFailFastOrNull)
  {
    SMyFailFast.myFailFastOrNull = myFailFastOrNull;
  }

  /**
   * Shortcut to your custom checker.
   *
   * @return custom checker.
   */
  public static IMyChecker getMyChecker()
  {
    return getMyFailFast().getMyChecker();
  }

  /**
   * Retrieves custom failer instead of IFailer.
   * <p>
   * Call methods of this (including your extensions) to throw an exception when a check asserts.
   * </p>
   * 
   * @return custom failer.
   */
  public static IMyFailer getMyFailer()
  {
    return getMyFailFast().getMyFailer();
  }

}
