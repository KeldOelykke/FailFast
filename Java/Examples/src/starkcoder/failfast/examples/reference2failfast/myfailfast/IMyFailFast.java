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
package starkcoder.failfast.examples.reference2failfast.myfailfast;

import starkcoder.failfast.IFailFast;

/**
 * Your own fail-fast specification that you can extend with your own fail-fast code.
 * <p>
 * This interface could be referenced from many places in your code.
 * </p>
 * @author Keld Oelykke
 */
public interface IMyFailFast extends IFailFast {

	/**
	 * Retrieves your custom checker instead of IChecker.
	 * <p>
	 * Call methods of this (including your extensions) to check arguments.
	 * </p>
	 * @return custom checker.
	 */
	IMyChecker getMyChecker();

	/**
	 * Retrieves custom failer instead of IFailer.
	 * <p>
	 * Call methods of this (including your extensions) to throw an exception when a check asserts.
	 * </p>
	 * @return custom failer.
	 */
	IMyFailer getMyFailer();
	
}
