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

import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPostfixCheck;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.NFail;

/**
 * Fail specification throwing an exception when a non-postfix check asserts.
 * 
 * @author Keld Oelykke
 */
public interface IObjectStringWithoutPostfixFail extends IFail
{
  /**
   * Fails specified Strings, since they passed a non-postfix check.
   * 
   * @param caller
   *          object calling checker and then failer (if String check asserted)
   * @param referenceAName
   *          name of reference A to fail
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NFail(failerSpecificationAndMethodId = 
      "IObjectStringWithoutPostfixFail.failStringWithoutPostfix(" 
          + "Object caller, String referenceAName)", 
      checkerSpecificationType = IObjectStringWithoutPostfixCheck.class, 
      failExceptionType = FailFastException.class, 
      failMessageFormat = "%s: String '%s'(%s) does NOT have postfix(%s).", 
      failMessageArguments = "fu0, fu1, cu1, cu2")
  void failStringWithoutPostfix(Object caller, String referenceAName);

  /**
   * Fails specified Strings, since they passed a non-postfix check.
   * 
   * @param caller
   *          object calling checker and then failer (if String check asserted)
   * @param referenceAName
   *          name of reference A to fail
   * @param message
   *          additional information
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NFail(failerSpecificationAndMethodId = 
      "IObjectStringWithoutPostfixFail.failStringWithoutPostfix(" 
          + "Object caller, String referenceAName, String message)", 
      checkerSpecificationType = IObjectStringWithoutPostfixCheck.class, 
      failExceptionType = FailFastException.class, 
      failMessageFormat = "%s: String '%s'(%s) does NOT have postfix(%s). %s.", 
      failMessageArguments = "fu0, fu1, cu1, cu2, fu2")
  void failStringWithoutPostfix(Object caller, String referenceAName, String message);

}
