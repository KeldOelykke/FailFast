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
package starkcoder.failfast.contractors;

/**
 * Call contractor reference specification.
 * <p>
 * Implement this to manage a reference to a call contractor.
 * </p>
 * @author Keld Oelykke
 */
public interface ICallContractorReference
{
	/**
	 * Retrieves the call contractor.
	 * 
	 * The call contractor ensures that an IChecker-call that asserts is
	 * followed by a matching IFailer-call.
	 * 
	 * @return call contractor, or null.
	 */
	ICallContractor getCallContractor();

	/**
	 * Sets the call contractor, or null to end an existing contract.
	 * 
	 * The call contractor ensures that an IChecker-call that asserts is
	 * followed by a matching IFailer-call.
	 * 
	 * @param callContractor
	 *            contractor to use, or null, if no contract.
	 */
	void setCallContractor(ICallContractor callContractor);
}
