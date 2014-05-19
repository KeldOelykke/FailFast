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
package starkcoder.failfast.examples.reference2failfast.abstractclass;

import starkcoder.failfast.examples.reference2failfast.myfailfast.IMyChecker;
import starkcoder.failfast.examples.reference2failfast.myfailfast.IMyFailFast;
import starkcoder.failfast.examples.reference2failfast.myfailfast.IMyFailer;
import starkcoder.failfast.examples.reference2failfast.myfailfast.SMyFailFast;

/**
 * Example of an ancestor abstract implementation that includes helper methods to custom fail-fast instances.
 * 
 * @author Keld Oelykke
 *
 */
public class AEntity implements IEntity {
	
	/**
	 * Retrieves custom fail-fast instance from static class.
	 * <p>
	 * In practice it easier to use the shortcuts getMyChecker() & getMyFailer()
	 * </p>
	 * <p>
	 * Check how it used in {link: SomeEntity}.
	 * </p>
	 * @return custom fail-fast instance
	 */
	protected IMyFailFast getMyFailFast()
	{
		return SMyFailFast.getMyFailFast();
	}
	
	/**
	 * Shortcut to your custom checker.
	 * <p>
	 * Check how it used in {link: SomeEntity}.
	 * </p>
	 * @return custom checker.
	 */
	protected IMyChecker getMyChecker()
	{
		return SMyFailFast.getMyChecker();
	}

	/**
	 * Shortcut to your custom failer.
	 * <p>
	 * Check how it used in {link: SomeEntity}.
	 * </p>
	 * @return custom failer.
	 */
	public IMyFailer getMyFailer()
	{
		return SMyFailFast.getMyFailer();
	}
}
