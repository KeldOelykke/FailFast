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
package starkcoder.failfast.fails.primitives.floats;

import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatOutsideCheck;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.NFail;

/**
 * Fail specification throwing an exception when a outside check asserts.
 * 
 * @author Keld Oelykke
 */
public interface IPrimitiveFloatOutsideFail extends IFail
{
	/**
	 * Fails specified values, since they passed an outside check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (if value check asserted)
	 * @param valueAName
	 *            name of value A to fail
	 * @param valueBName
	 *            name of value B to fail
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(checkerSpecificationType = IPrimitiveFloatOutsideCheck.class, failExceptionType = FailFastException.class, failMessageFormat = "%s: Value '%s' is outside [min;max].")
	void failFloatValueOutside(Object caller, String valueAName);

	/**
	 * Fails specified values, since they passed a outside check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (if value check asserted)
	 * @param valueAName
	 *            name of value A to fail
	 * @param valueBName
	 *            name of value B to fail
	 * @param message
	 *            additional information
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(checkerSpecificationType = IPrimitiveFloatOutsideCheck.class, failExceptionType = FailFastException.class, failMessageFormat = "%s: Value '%s' is outside [min;max]. %s")
	void failFloatValueOutside(Object caller, String valueAName, String message);

	/**
	 * Fails specified values, since they passed a outside check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (if value check asserted)
	 * @param valueAName
	 *            name of value A to fail
	 * @param valueBName
	 *            name of value B to fail
	 * @param valueMin
	 *            min value to fail
	 * @param valueMax
	 *            max value to fail
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(checkerSpecificationType = IPrimitiveFloatOutsideCheck.class, failExceptionType = FailFastException.class, failMessageFormat = "%s: Value '%s' is outside [%s;%s].")
	void failFloatValueOutside(Object caller, String valueAName, float valueMin, float valueMax);

	/**
	 * Fails specified values, since they passed a outside check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (if value check asserted)
	 * @param valueAName
	 *            name of value A to fail
	 * @param valueBName
	 *            name of value B to fail
	 * @param valueMin
	 *            min value to fail
	 * @param valueMax
	 *            max value to fail
	 * @param message
	 *            additional information
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(checkerSpecificationType = IPrimitiveFloatOutsideCheck.class, failExceptionType = FailFastException.class, failMessageFormat = "%s: Value '%s' is outside [%s;%s]. %s")
	void failFloatValueOutside(Object caller, String valueAName, float valueMin, float valueMax, String message);

}
