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

package starkcoder.failfast.fails.objects.dates;

import starkcoder.failfast.checks.objects.dates.IObjectDateOutsideCheck;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.NFail;

/**
 * Fail specification throwing an exception when a outside check asserts.
 * 
 * @author Keld Oelykke
 */
public interface IObjectDateOutsideFail extends IFail
{
  /**
   * Fails specified references, since they passed an outside check.
   * 
   * @param caller
   *          object calling checker and then failer (if reference check asserted)
   * @param referenceAName
   *          name of reference A to fail
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NFail(failerSpecificationAndMethodId = 
      "IObjectDateOutsideFail.failDateOutside(Object caller, String referenceAName)", 
      checkerSpecificationType = IObjectDateOutsideCheck.class, 
      failExceptionType = FailFastException.class, 
      failMessageFormat = "%s: Date '%s'(%s) is outside Date range [%s;%s].", 
      failMessageArguments = "fu0, fu1, cu1, cu2, cu3")
  void failDateOutside(Object caller, String referenceAName);

  /**
   * Fails specified references, since they passed a outside check.
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
  @NFail(failerSpecificationAndMethodId = 
      "IObjectDateOutsideFail.failDateOutside(" 
          + "Object caller, String referenceAName, String message)", 
      checkerSpecificationType = IObjectDateOutsideCheck.class, 
      failExceptionType = FailFastException.class, 
      failMessageFormat = "%s: Date '%s'(%s) is outside Date range [%s;%s]. %s.", 
      failMessageArguments = "fu0, fu1, cu1, cu2, cu3, fu2")
  void failDateOutside(Object caller, String referenceAName, String message);

}
