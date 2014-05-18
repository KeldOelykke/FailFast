/**
 * 
 */
package starkcoder.failfast.examples.reference2failfast.myfailfast;

import starkcoder.failfast.AFailFast;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.IFailer;

/**
 * Custom fail-fast class.
 * 
 * @author Keld Oelykke
 */
public class MyFailFast extends AFailFast implements IMyFailFast {

	@Override
	public IMyChecker getMyChecker() {
		return (IMyChecker)this.getChecker();
	}

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
