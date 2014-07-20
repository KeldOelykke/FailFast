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
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail;

/**
 * Specifies a not-equals check for float allowing some relative difference.
 * <p>
 * This is an attempt to use a better equals check than the traditional epsilon test
 * - refer to {link:IPrimitiveFloatEqualsAlmostCheck} 
 * </p>
 *  
 * @author Keld Oelykke
 */
public interface IPrimitiveFloatNotEqualsAlmostCheck extends IPrimitiveFloatEqualsAlmostCheckProperties, ICheck
{
	/**
	 * Checks if the values are not-equals (apart from a relative difference).
	 * <p>
	 * By default the allowed relative difference is 0.001 (0.1%) {link:IPrimitiveFloatEqualsAlmostCheckProperties}
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
	@NCheck(failSpecificationType = IPrimitiveFloatNotEqualsAlmostFail.class)
	boolean isFloatValueNotEqualsAlmost(Object caller, float valueA, float valueB);
	
	/**
	 * Checks if the values are not-equals (apart from specified allowed relative difference).
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
	@NCheck(failSpecificationType = IPrimitiveFloatNotEqualsAlmostFail.class)
	boolean isFloatValueNotEqualsAlmost(Object caller, float valueA, float valueB,
			float allowedRelativeDifference);
}
