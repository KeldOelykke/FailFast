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

package starkcoder.failfast.fails;

/**
 * This is a specification for managing IFailerObserver's that needs to notified when an exception
 * is about to be thrown.
 * 
 * @author Keld Oelykke
 */
public interface IFailerPublisher
{
  /**
   * Registers an instance to be notified when an exception is about to be thrown.
   * 
   * @param failerObserver
   *          instance to be notified when an exception is about to be thrown
   * 
   * @return registration key that will need to be used to unregister the observer again
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if failerObserver already is registered
   */
  Object registerFailerObserver(IFailerObserver failerObserver);

  /**
   * Unregisters a previously registered instance such that it is no more notified with exceptions
   * about to be thrown.
   * 
   * @param failerObserver
   *          instance to stop being notified when an exception is about to be thrown
   * @param registrationKey
   *          key returned the failer observer when registered
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if either failerObserver or registrationKey is NOT registered
   */
  void unregisterFailerObserver(IFailerObserver failerObserver, Object registrationKey);
}
