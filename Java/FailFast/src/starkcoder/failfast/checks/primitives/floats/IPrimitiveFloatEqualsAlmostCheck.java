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
 * Specifies an equals check for float allowing some difference e.g. due to calculation errors.
 * <p>
 * This is an attempt on a better equals check than the traditional epsilon test
 * (doesn't support both tiny and huge numbers for 1 particular epsilon value).
 * </p>
 * <p>
 * This is also an attempt on a better equals check than the relative error test
 * (doesn't support tiny numbers without an absolute epsilon test).
 * </p>
 * <p>
 * The goal is to get rid of both the absolute and relative epsilon values by using accuracy 
 * of the mantissa aka number of significant digits we include in the check.
 * </p>
 * <p>
 * The idea is to find the greatest exponent X of A and B, 
 * calculate the mantissa difference between A and B (with exponent X) 
 * and look how big that is in comparison to the mantissa of a specified accuracy (with exponent X).
 * </p>
 * 
 * @see http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
 * 
 * @author Keld Oelykke
 */
public interface IPrimitiveFloatEqualsAlmostCheck extends IPrimitiveFloatEqualsAlmostCheckProperties, ICheck
{
	/**
	 * Checks if the values are equals (within a default accuracy).
	 * <p>
	 * The default accuracy is 0.00001f {link:IPrimitiveFloatEqualsAlmostCheckProperties}
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
	 * Checks if the values are equals (within a specified accuracy).
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param valueA
	 *            value to equals check against value B
	 * @param valueB
	 *            argument to equals-method of value A
	 * @param accuracy
	 *            accuracy of A and B
	 * @return true, if values are equals - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IPrimitiveFloatEqualsAlmostFail.class)
	boolean isFloatValueEqualsAlmost(Object caller, float valueA, float valueB,
			float accuracy);
}
