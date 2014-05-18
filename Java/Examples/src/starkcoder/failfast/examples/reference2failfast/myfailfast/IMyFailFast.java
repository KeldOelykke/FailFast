/**
 * 
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
