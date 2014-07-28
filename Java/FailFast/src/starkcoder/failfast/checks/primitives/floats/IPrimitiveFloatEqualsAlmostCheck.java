/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Keld Ølykke
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
package starkcoder.failfast.checks.primitives.floats;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail;

/**
 * Specifies an equals check for float allowing some relative difference.
 * <p>
 * This is an attempt on a better equals check than the traditional epsilon test
 * (doesn't support both tiny and huge numbers for 1 particular epsilon value).
 * </p>
 * <p>
 * The idea is take the difference between A and B and look how big that is in
 * comparison to values of A and B.
 * </p>
 * <p>
 * A very simple implementation is to calculate rd = abs(B-A) / max(abs(A),
 * abs(B), 1) and return true, if rd is less or equal to a specified
 * allowed relative difference.
 * </p>
 * 
 * @see http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
 * 
 * @author Keld Oelykke
 */
public interface IPrimitiveFloatEqualsAlmostCheck extends IPrimitiveFloatEqualsAlmostCheckProperties, ICheck
{
	/**
	 * Checks if the values are equals (apart from a relative difference).
	 * <p>
	 * The default allowed relative difference is 0.001f (0.1%) {link:IPrimitiveFloatEqualsAlmostCheckProperties}
	 * </p>
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param valueA
	 *            value to equals check against value B
	 * @param valueB
	 *            argument to equals-method of value A
	 * @return true, if values are equals - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IPrimitiveFloatEqualsAlmostFail.class)
	boolean isFloatValueEqualsAlmost(Object caller, float valueA, float valueB);

	/**
	 * Checks if the values are equals (apart from specified allowed relative
	 * difference).
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param valueA
	 *            value to equals check against value B
	 * @param valueB
	 *            argument to equals-method of value A
	 * @param allowedRelativeDifference
	 *            allowed relative difference between A and B
	 * @return true, if values are equals - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IPrimitiveFloatEqualsAlmostFail.class)
	boolean isFloatValueEqualsAlmost(Object caller, float valueA, float valueB,
			float allowedRelativeDifference);
}
