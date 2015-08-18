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

import starkcoder.failfast.contractors.ICallContractor;

/**
 * Default concrete implementation of {@link IFailer} using abstract implementation {@link AFailer}.
 * <p>
 * This implements all the failers supported.
 * </p>
 * <p>
 * Extend {@link IFailer} and this to support custom failers.
 * </p>
 * 
 * @author Keld Oelykke
 */
public class Failer extends AFailer
{

  /**
   * Default constructor.
   * <p>
   * Remember to set call contractor before usage.
   * </p>
   */
  public Failer()
  {
    super();
  }

  /**
   * Recommended constructor receiving required references (manual constructor dependency
   * injection).
   * <p>
   * This is ready for use after this call.
   * </p>
   * 
   * @param callContractor
   *          used by this
   */
  public Failer(ICallContractor callContractor)
  {
    super(callContractor);
  }

}
