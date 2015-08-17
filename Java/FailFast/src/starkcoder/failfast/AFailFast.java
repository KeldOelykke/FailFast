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

package starkcoder.failfast;

import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.IFailer;

/**
 * Abstract implementation of IFailFast.
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * 
 * @author Keld Oelykke
 *
 */
public abstract class AFailFast implements IFailFast
{
  private IChecker checker;

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.ICheckerReference#getChecker()
   */
  @Override
  public IChecker getChecker()
  {
    return this.checker;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.ICheckerReference#setChecker(starkcoder.failfast.checks.IChecker)
   */
  @Override
  public void setChecker(IChecker checker)
  {
    this.checker = checker;
  }

  private IFailer failer;

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.fails.IFailerReference#getFailer()
   */
  @Override
  public IFailer getFailer()
  {
    return this.failer;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.fails.IFailerReference#setFailer(starkcoder.failfast.fails.IFailer)
   */
  @Override
  public void setFailer(IFailer failer)
  {
    this.failer = failer;
  }

  private ICallContractor callContractor;

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.calls.ICallContractorReference#getCallContractor()
   */
  @Override
  public ICallContractor getCallContractor()
  {
    return this.callContractor;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.calls.ICallContractorReference#setCallContractor(starkcoder.failfast.calls
   * .ICallContractor)
   */
  @Override
  public void setCallContractor(ICallContractor callContractor)
  {
    this.callContractor = callContractor;
  }

  /**
   * Default constructor.
   * <p>
   * After using this constructor remember to set Checker, Failer and CallContractor before use.
   * </p>
   */
  protected AFailFast()
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
   * @param checker
   *          checker to use
   * @param failer
   *          failer to use
   * @param callContractor
   *          call contractor to use
   */
  protected AFailFast(IChecker checker, IFailer failer, ICallContractor callContractor)
  {
    super();
    {
      if (null == checker)
      {
        throw new IllegalArgumentException("checker is null");
      }
      this.setChecker(checker);
    }
    {
      if (null == failer)
      {
        throw new IllegalArgumentException("failer is null");
      }
      this.setFailer(failer);
    }
    {
      if (null == callContractor)
      {
        throw new IllegalArgumentException("callContractor is null");
      }
      this.setCallContractor(callContractor);
    }
  }

}
