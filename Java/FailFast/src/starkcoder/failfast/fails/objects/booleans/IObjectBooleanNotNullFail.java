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
package starkcoder.failfast.fails.objects.booleans;

import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck;
import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.NFail;

/**
 * Fail specification throwing an exception when a not-null check asserts.
 * 
 * @author Keld Oelykke
 */
public interface IObjectBooleanNotNullFail extends IFail
{
	/**
	 * Fails specified reference, since it passed a not-null check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (if reference check asserted)
	 * @param referenceAName
	 *            name of reference A to fail
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(checkerSpecificationType = IObjectBooleanNotNullCheck.class, failExceptionType = FailFastException.class, failMessageFormat = "%s: Reference '%s' is NOT null.")
	void failBooleanNotNull(Object caller, String referenceAName);

	/**
	 * Fails specified reference, since it passed a null check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (if reference check asserted)
	 * @param referenceAName
	 *            name of reference A to fail
	 * @param message
	 *            additional information
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(checkerSpecificationType = IObjectBooleanNotNullCheck.class, failExceptionType = FailFastException.class, failMessageFormat = "%s: Reference '%s' is NOT null. %s")
	void failBooleanNotNull(Object caller, String referenceAName, String message);

}