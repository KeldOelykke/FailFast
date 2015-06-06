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
package starkcoder.failfast.examples.reference2failfast.myfailfast.withcustoms;

import starkcoder.failfast.fails.FailFastException;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.NFail;

/**
 * Example of a custom failer throwing an exception when a FooBar-check asserts.
 * 
 * @author Keld Oelykke
 */
public interface IMyFooBarFail extends IFail
{
	/**
	 * Fails specified Foo, since it asserted a Bar check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (reference check asserted)
	 * @param referenceName
	 *            name of reference to fail
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(failerSpecificationAndMethodID = "IMyFooBarFail.failFooBar(Object caller, String referenceName)",
			checkerSpecificationType = IMyFooBarCheck.class,
			failExceptionType = FailFastException.class, 
			failMessageFormat = "%s: Oh no! Foo '%s' is Bar!",
			failMessageArguments = "fu0, fu1")
	void failFooBar(Object caller, String referenceName);

	/**
	 * Fails specified reference, since it assert a Bar check.
	 * 
	 * @param caller
	 *            object calling checker and then failer (reference check asserted)
	 * @param referenceName
	 *            name of reference to fail
	 * @param message
	 *            additional information
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NFail(failerSpecificationAndMethodID = "IMyFooBarFail.failFooBar(Object caller, String referenceName, String message)",
			checkerSpecificationType = IMyFooBarCheck.class, 
			failExceptionType = FailFastException.class, 
			failMessageFormat = "%s: Oh no! Foo '%s' is Bar! %s.",
			failMessageArguments = "fu0, fu1, fu2")
	void failFooBar(Object caller, String referenceName, String message);

}
