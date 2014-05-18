/**
 * 
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
