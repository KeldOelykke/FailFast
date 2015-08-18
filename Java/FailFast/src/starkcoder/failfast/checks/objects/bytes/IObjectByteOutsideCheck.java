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

package starkcoder.failfast.checks.objects.bytes;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.objects.bytes.IObjectByteOutsideFail;

/**
 * Specifies a within check for Byte.
 * 
 * @author Keld Oelykke
 */
public interface IObjectByteOutsideCheck extends ICheck
{
  /**
   * Checks if references are not nulls and A is outside [min;max].
   * <p>
   * [min;max] and [max;min] is considered the same number range.
   * </p>
   * 
   * @param caller
   *          end-user instance initiating the check
   * @param referenceA
   *          reference to check against number range
   * @param referenceMin
   *          reference to start of number range
   * @param referenceMax
   *          reference to end of number range
   * @return true, if references are not nulls and A is outside [min;max], otherwise false
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NCheck(failSpecificationType = IObjectByteOutsideFail.class)
  boolean isByteOutside(Object caller, Byte referenceA, Byte referenceMin, Byte referenceMax);

}
