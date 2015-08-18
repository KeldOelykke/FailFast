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

package starkcoder.failfast.checks.objects.enums;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.objects.enums.IObjectEnumLessFail;

/**
 * Specifies a less check for Enum.
 * 
 * @author Keld Oelykke
 */
public interface IObjectEnumLessCheck extends ICheck
{
  /**
   * Checks if references are not nulls and A < B.
   * 
   * @param caller
   *          end-user instance initiating the check
   * @param referenceA
   *          reference to check against reference B
   * @param referenceB
   *          argument to check against reference A
   * @return true, if references are not null and A < B, otherwise false
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NCheck(failSpecificationType = IObjectEnumLessFail.class)
  <T extends Enum<T>> boolean isEnumLess(Object caller, T referenceA, T referenceB);

}
