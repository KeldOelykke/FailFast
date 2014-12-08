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
package starkcoder.failfast.checks.objects;

import java.util.Collection;
import java.util.List;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.objects.IObjectsEqualsFail;

/**
 * Specifies an equals check for Objects.
 * 
 * @author Keld Oelykke
 */
public interface IObjectsEqualsCheck extends ICheck
{
	/**
	 * Checks if the references are equals or both nulls.
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param referenceA
	 *            array reference to equals check against reference B
	 * @param referenceB
	 *            arguments to equals-methods of reference A
	 * @return true, if references are equals - including null pairs - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IObjectsEqualsFail.class)
	boolean isObjectsEquals(Object caller, Object[] referenceA, Object[] referenceB);
	
	/**
	 * Checks if the referenced object pairs are equals or both nulls.
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param referenceA
	 *            list reference to equals check against reference B
	 * @param referenceB
	 *            arguments to equals-methods of reference A
	 * @return true, if referenced objects are equals - including null pairs - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IObjectsEqualsFail.class)
	boolean isObjectsEquals(Object caller, List<Object> referenceA, List<Object> referenceB);
	
	/**
	 * Checks if the referenced object pairs are equals or both nulls.
	 * 
	 * @param caller
	 *            end-user instance initiating the check
	 * @param referenceA
	 *            array reference to equals check against reference B
	 * @param referenceB
	 *            arguments to equals-methods of reference A
	 * @return true, if referenced objects are equals - including null pairs - otherwise false
	 * @throws IllegalArgumentException
	 *             if caller is null
	 */
	@NCheck(failSpecificationType = IObjectsEqualsFail.class)
	boolean isObjectsEquals(Object caller, Collection<Object> referenceA, Collection<Object> referenceB);
}
