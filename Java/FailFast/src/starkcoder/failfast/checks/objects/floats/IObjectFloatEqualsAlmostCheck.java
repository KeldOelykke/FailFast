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

package starkcoder.failfast.checks.objects.floats;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsAlmostFail;

/**
 * Specifies an equals check for Float allowing some difference e.g. due to calculation errors.
 * <p>
 * This is an attempt on an equals check that supports both tiny numbers with an absolute epsilon
 * test and large numbers with a relative epsilon test.
 * </p>
 * <p>
 * The idea behind this is to find a lower-function - L(X) - and upper-function - U(X) - that for
 * any X tells in which range Y is considered almost equal to X
 * </p>
 * <p>
 * For an absolute epsilon: L(X) = X - EPSILON_abs, U(x) = X + EPSILON_abs For a relative epsilon:
 * L(X) = (1 - Sign(X) * EPSILON_rel) * X, U(X) = (1 + Sign(X) * EPSILON_rel) * X (see
 * https://github
 * .com/KeldOelykke/FailFast/blob/master/Java/Docs/Images/absAndRelErrorFunctions.png).
 * </p>
 * <p>
 * Above combined gives: L(X) = (1 - Sign(X) * EPSILON_rel) * X - EPSILON_abs, U(x) = (1 + Sign(X) *
 * EPSILON_rel) * X + EPSILON_abs (see
 * https://github.com/KeldOelykke/FailFast/blob/master/Java/Docs/
 * Images/almostEqualZoneForXandY.png).
 * </p>
 * 
 * @see <a
 *      href="http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/">http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/</a>
 * 
 * @author Keld Oelykke
 */
public interface IObjectFloatEqualsAlmostCheck extends IObjectFloatEqualsAlmostCheckProperties,
    ICheck
{
  /**
   * Checks if the referenced values are almost equals (B is within [L(A);U(A)]).
   * <p>
   * The default absolute epsilon is 0.00001f and the default relative epsilon is 0.000001f
   * {link:IObjectFloatEqualsAlmostCheckProperties}.
   * </p>
   * 
   * @param caller
   *          end-user instance initiating the check
   * @param referenceA
   *          reference to equals check against reference B
   * @param referenceB
   *          argument to equals-method of reference A
   * @return true, if referenced values are equals - otherwise false
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NCheck(failSpecificationType = IObjectFloatEqualsAlmostFail.class)
  boolean isFloatEqualsAlmost(Object caller, Float referenceA, Float referenceB);

  /**
   * Checks if the referenced values are almost equals (B is within [L(A);U(A)]).
   * <p>
   * The default relative epsilon is 0.000001f {link:IObjectFloatEqualsAlmostCheckProperties}.
   * </p>
   * 
   * @param caller
   *          end-user instance initiating the check
   * @param referenceA
   *          reference to equals check against reference B
   * @param referenceB
   *          argument to equals-method of reference A
   * @param absoluteEpsilon
   *          disregarded absolute difference between A and B
   * @return true, if referenced values are almost equals - otherwise false
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NCheck(failSpecificationType = IObjectFloatEqualsAlmostFail.class)
  boolean isFloatEqualsAlmost(Object caller, Float referenceA, Float referenceB,
      Float absoluteEpsilon);

  /**
   * Checks if the referenced values are almost equals (B is within [L(A);U(A)]).
   * 
   * @param caller
   *          end-user instance initiating the check
   * @param referenceA
   *          reference to equals check against reference B
   * @param referenceB
   *          argument to equals-method of reference A
   * @param absoluteEpsilon
   *          disregarded absolute difference between A and B
   * @param relativeEpsilon
   *          disregarded relative difference between A and B
   * @return true, if referenced values are almost equals - otherwise false
   * @throws IllegalArgumentException
   *           if caller is null
   */
  @NCheck(failSpecificationType = IObjectFloatEqualsAlmostFail.class)
  boolean isFloatEqualsAlmost(Object caller, Float referenceA, Float referenceB,
      Float absoluteEpsilon, Float relativeEpsilon);
}
