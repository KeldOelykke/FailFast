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
package starkcoder.failfast.contractors;

import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.fails.IFailer;

/**
 * Specification of a call contractor.
 * <p>
 * This contractor is to make a sure a check-call that asserts is followed up by
 * a matching fail-call.
 * </p>
 * <p>
 * Implementations of this should be extensible (not final).
 * </p>
 * @author Keld Oelykke
 */
public interface ICallContractor
{
	/**
	 * Starts a contract between caller and call contractor.
	 * 
	 * @param caller
	 *            instance that called a checker and needs to call a failer
	 * @param assertingChecker
	 *            checker that was called and asserted
	 * @param checkerSpecification
	 *            specification type inherited by IChecker
	 */
	void pushContractWithCaller(Object caller, IChecker assertingChecker,
			Class<?> checkerSpecification);

	/**
	 * Ends a contract between caller and call contractor.
	 * 
	 * @param caller
	 *            instance that called a checker and needs to call a failer
	 * @param throwingFailer
	 *            failer that was called to throw an exception
	 * @param checkerSpecification
	 *            specification type inherited by IChecker
	 */
	void popContractWithCaller(Object caller, IFailer throwingFailer,
			Class<?> checkerSpecification);
}
