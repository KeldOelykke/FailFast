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

package starkcoder.failfast.fails.objects.strings;

import starkcoder.failfast.checks.objects.strings.IObjectStringNotEmptyCheck;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.NFail;

/**
 * Fail specification throwing an exception when a not-empty check asserts.
 * 
 * @author Keld Oelykke
 */
public interface IObjectStringNotEmptyFail extends IFail
{
  /**
   * Fails specified reference, since its referenced value passed a not-empty check.
   * 
   * @param caller
   *          object calling checker and then failer (if reference check asserted)
   * @param referenceAName
   *          name of reference A to fail
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NFail(failerSpecificationAndMethodId = "IObjectStringNotEmptyFail.failStringNotEmpty(" 
      + "Object caller, String referenceAName)", 
      checkerSpecificationType = IObjectStringNotEmptyCheck.class, 
      failExceptionType = FailFastException.class, 
      failMessageFormat = "%s: String '%s'(%s) is NOT empty(%s).", 
      failMessageArguments = "fu0, fu1, cu1, cx0")
  void failStringNotEmpty(Object caller, String referenceAName);

  /**
   * Fails specified reference, since its referenced value passed a not-empty check.
   * 
   * @param caller
   *          object calling checker and then failer (if reference check asserted)
   * @param referenceAName
   *          name of reference A to fail
   * @param message
   *          additional information
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NFail(failerSpecificationAndMethodId = "IObjectStringNotEmptyFail.failStringNotEmpty(" 
      + "Object caller, String referenceAName, String message)", 
      checkerSpecificationType = IObjectStringNotEmptyCheck.class, 
      failExceptionType = FailFastException.class, 
      failMessageFormat = "%s: String '%s'(%s) is NOT empty(%s). %s.", 
      failMessageArguments = "fu0, fu1, cu1, cx0, fu2")
  void failStringNotEmpty(Object caller, String referenceAName, String message);

}
