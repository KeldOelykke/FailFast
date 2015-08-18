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

package starkcoder.failfast.checks.objects.strings;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.objects.strings.IObjectStringMatchingFail;

/**
 * Specifies a regex check for String.
 * 
 * @author Keld Oelykke
 */
public interface IObjectStringMatchingCheck extends ICheck
{
  /**
   * Checks if A matches the regular expression.
   * <p>
   * If A is null it can't be matched.
   * </p>
   * <p>
   * If regex is null A can't be matched
   * </p>
   * <p>
   * Empty A is matched by empty regex.
   * </p>
   * 
   * @param caller
   *          end-user instance initiating the check
   * @param referenceA
   *          reference to check for match regex
   * @param regex
   *          the regular expression A is to be matched against
   * @return true, if A matches regex - otherwise false
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NCheck(failSpecificationType = IObjectStringMatchingFail.class)
  boolean isStringMatching(Object caller, String referenceA, String regex);
}
