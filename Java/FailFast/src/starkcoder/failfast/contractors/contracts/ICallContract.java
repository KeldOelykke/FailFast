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
package starkcoder.failfast.contractors.contracts;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.fails.IFail;

/**
 * Specifies contract data between {link:IChecker} and {link:IFailer}.
 * <p>
 * The data is everything needed for the fail-method to throw an exception with an informative well-formatted error message.
 * </p>
 * <p>
 * Please note! ALL properties not named "OrNull" MUST be supplied (cannot be null).
 * </p>
 * <p>
 * Implementations of this should be extensible (not final).
 * </p>
 * 
 * @author Keld Oelykke
 */
public interface ICallContract
{
	/**
	 * The caller that called asserting check-method and therefore must call fail-method.
	 * @return caller that called asserting check-method and therefore must call fail-method
	 */
	Object getCaller();
	/**
	 * The caller that called asserting check-method and therefore must call fail-method.
	 * @param caller caller that called asserting check-method and therefore must call fail-method
	 */
	void setCaller(Object caller);
	
	/**
	 * The checker with asserting check-method.
	 * @return checker with asserting check-method
	 */
	IChecker getAssertingChecker();
	/**
	 * The checker with asserting check-method.
	 * @param assertingChecker checker with asserting check-method
	 */
	void setAssertingChecker(IChecker assertingChecker);
	
	/**
	 * The check specification (interface) with specification of asserting check-method.
	 * <p>
	 * The checkSpecification is annotated with a fail specification that
	 * needs to be called by the caller to end this contract.
	 * </p>
	 * @return check specification (interface) with specification of asserting check-method
	 */
	Class<? extends ICheck> getCheckSpecification();
	/**
	 * The check specification (interface) with specification of asserting check-method.
	 * <p>
	 * The checkSpecification is annotated with a fail specification that
	 * needs to be called by the caller to end this contract.
	 * </p>
	 * @param checkSpecification check specification (interface) with specification of asserting check-method
	 */
	void setCheckSpecification(Class<? extends ICheck> checkSpecification);
	
	/**
	 * The arguments supplied the asserting check-method by the caller.
	 * @return arguments supplied the asserting check-method by the caller.
	 */
	Object[] getCheckArguments();
	/**
	 * The arguments supplied the asserting check-method by the caller.
	 * @param checkArguments arguments supplied the asserting check-method by the caller
	 */
	void setCheckArguments(Object[] checkArguments);
	
	/**
	 * The extra arguments supplied by the asserting check-method implementation.
	 * @return extra arguments supplied by the asserting check-method implementation
	 */
	Object[] getCheckExtraArguments();
	/**
	 * The extra arguments supplied by the asserting check-method implementation.
	 * @param checkExtraArguments extra arguments supplied by the asserting check-method implementation
	 */
	void setCheckExtraArguments(Object[] checkExtraArguments);
	
	/**
	 * Validates the contract data e.g. that no property is missing.
	 * @throws IllegalArgumentException 
	 * 			  if any contract data is missing
	 */
	void validateContractData();
	
	/**
	 * Reflects the fail specification (interface) that should be used to end this contract.
	 * @return the fail specification (interface) that should be used to end this contract
	 */
	Class<? extends IFail> reflectFailSpecificationType();
	
	/**
	 * Retrieves a custom exception class, if set, that fail-method should throw instead of the default class set in attribute of fail-method.
	 * 
	 * @return a custom exception class that fail-method should throw, or null
	 */
	Class<? extends RuntimeException> getCustomFailExceptionTypeOrNull();
	
	/**
	 * Sets a custom exception class that fail-method should throw instead of
	 * the default class set in attribute of fail-method.
	 * <p>
	 * If failExceptionType inherits <link:IFailFastException> the exception
	 * will be registered as a fail-fast exception in <link:IFailFast>.
	 * </p>
	 * <p>
	 * If failExceptionType does not inherit <link:IFailFastException> the
	 * exception will be treated as a "normal" api exception.
	 * </p>
	 * 
	 * @param failExceptionType
	 *            a custom exception class that fail-method should throw
	 */
	void setCustomFailExceptionType(Class<? extends RuntimeException> failExceptionType);
	
	/**
	 * Retrieves custom message format, if set, that fail-method should use instead of the
	 * default format set in attribute of fail-method.
	 * 
	 * @return custom message format that fail-method should use, or null
	 */
	String getCustomFailMessageFormatOrNull();
	
	/**
	 * Sets custom message format that fail-method should use instead of the
	 * default format set in attribute of fail-method.
	 * 
	 * @param failMessageFormat
	 *            custom message format that fail-method should use
	 */
	void setCustomFailMessageFormat(String failMessageFormat);
	
	/**
	 * Retrieves custom message arguments, if set, that fail-method should use instead of the
	 * default arguments set in attribute of fail-method.
	 * 
	 * @return custom message arguments that fail-method should use, or null
	 */
	String getCustomFailMessageArgumentsOrNull();
	
	/**
	 * Sets custom message arguments that fail-method should use instead of the
	 * default arguments set in attribute of fail-method.
	 * 
	 * @param failMessageArguments
	 *            custom message arguments that fail-method should use
	 */
	void setCustomFailMessageArguments(String failMessageArguments);
	
	/**
	 * Retrieves a custom message postfix, if set, that will be added the exception message.
	 * 
	 * @return custom message postfix, if set, that will be added the exception message
	 */
	String getCustomFailMessagePostfixOrNull();
	
	/**
	 * Sets a custom message postfix that will be added the exception message of the exception to throw.
	 */
	void setCustomFailMessagePostfix(String failMessagePostfix);
	
}
