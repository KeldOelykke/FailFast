module FailFast 
{

	/**
	 * Failer specification.
	 * <p>
	 * The Failer is used to throw fail-fast exceptions when a checker asserts.
	 * </p>
	 * A checker that asserts starts a contract that this must end (via the call contractor). </p>
	 * <p>
	 * Threads can poll this to check if a fail-fast exception has been thrown.
	 * </p>
	 * <p>
	 * Implementations of this should be extensible (not final).
	 * </p>
	 * 
	 * @author Keld Oelykke
	 */
    export interface IFailer 
    {
    }

}
