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

import starkcoder.failfast.AFailFast;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.IFailer;

/**
 * A custom fail-fast class that can support your custom checker and custom failer.
 * 
 * @author Keld Oelykke
 */
public class MyFailFast extends AFailFast implements IMyFailFast {

	/* (non-Javadoc)
	 * @see starkcoder.failfast.examples.reference2failfast.myfailfast.withcustoms.IMyFailFast#getMyChecker()
	 */
	@Override
	public IMyChecker getMyChecker() {
		return (IMyChecker)this.getChecker();
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.examples.reference2failfast.myfailfast.withcustoms.IMyFailFast#getMyFailer()
	 */
	@Override
	public IMyFailer getMyFailer() {
		return (IMyFailer)this.getFailer();
	}

	/**
	 * Default constructor.
	 * <p>
	 * After using this constructor remember to set Checker, Failer and
	 * CallContractor before use.
	 * </p>
	 */
	public MyFailFast()
	{
		super();
	}

	/**
	 * Recommended constructor receiving required references (manual constructor dependency injection).
	 * <p>
	 * This is ready for use after this call.
	 * </p>
	 * @param checker
	 *            checker to use
	 * @param failer
	 *            failer to use
	 * @param callContractor
	 *            call contractor to use
	 */
	public MyFailFast(IChecker checker, IFailer failer,
			ICallContractor callContractor)
	{
		super(checker, failer, callContractor);
	}
}
