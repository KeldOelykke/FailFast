/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2015 Keld Oelykke
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package starkcoder.failfast.checks.objects.floats;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsAlmostFail;

/**
 * Specifies a not-equals check for Float allowing some difference e.g. due to calculation errors.
 * <p>
 * Please refer to {link:IObjectFloatEqualsAlmostCheck} for more details.
 * </p>
 *  
 * @author Keld Oelykke
 */
public interface IObjectFloatNotEqualsAlmostCheck extends IObjectFloatEqualsAlmostCheckProperties, ICheck
{
	/**
	 * Checks if the referenced values are not-equals  (B is NOT within [L(A);U(A)]).
	 * <p>
	 * The default absolute epsilon is 0.00001f and the default relative epsilon is 0.000001f 
	 * {link:IObjectFloatEqualsAlmostCheckProperties}.
	 * </p>
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param valueA
	 *            value to equals check against value B
	 * @param valueB
	 *            argument to equals-method of value A
	 * @return true, if referenced values are NOT almost equals - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IObjectFloatNotEqualsAlmostFail.class)
	boolean isFloatNotEqualsAlmost(Object caller, Float valueA, Float valueB);
	
	/**
	 * Checks if the referenced values are not-equals (B is NOT within [L(A);U(A)]).
	 * <p>
	 * The default relative epsilon is 0.000001f {link:IObjectFloatEqualsAlmostCheckProperties}.
	 * </p>
	 * @param caller
	 *            end-user instance initiating the check
	 * @param valueA
	 *            value to equals check against value B
	 * @param valueB
	 *            argument to equals-method of value A
	 * @param absoluteEpsilon
	 *            disregarded absolute difference between A and B
	 * @return true, if referenced values are NOT almost equals - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IObjectFloatNotEqualsAlmostFail.class)
	boolean isFloatNotEqualsAlmost(Object caller, Float valueA, Float valueB,
			Float absoluteEpsilon);
	
	/**
	 * Checks if the referenced values are not-equals (B is NOT within [L(A);U(A)]).
	 * @param caller
	 *            end-user instance initiating the check
	 * @param valueA
	 *            value to equals check against value B
	 * @param valueB
	 *            argument to equals-method of value A
	 * @param absoluteEpsilon
	 *            disregarded absolute difference between A and B
	 * @param relativeEpsilon
	 *            disregarded relative difference between A and B
	 * @return true, if referenced values are NOT almost equals - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IObjectFloatNotEqualsAlmostFail.class)
	boolean isFloatNotEqualsAlmost(Object caller, Float valueA, Float valueB,
			Float absoluteEpsilon, Float relativeEpsilon);
}
