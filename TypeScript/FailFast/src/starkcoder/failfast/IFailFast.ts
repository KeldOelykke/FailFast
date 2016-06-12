module FailFast 
{

	/**
	 * FailFast specification.
	 * <p>
	 * This is to help programmers to follow the FailFast-principle.
	 * </p>
	 * <p>
	 * The FailFast-principle helps you detect and report unexpected errors immediately when they occur.
	 * </p>
	 * <p>
	 * The FailFast-principle favors simple code-fixes over complex runtime error recovery schemes.
	 * </p>
	 * <p>
	 * Read the description and references here: http://en.wikipedia.org/wiki/Fail-fast
	 * </p>
	 * <p>
	 * Implementations of this should be extensible (not final).
	 * </p>
	 * 
	 * @author Keld Oelykke
	 */
    export interface IFailFast extends ICheckerReference, IFailerReference 
    {
    }

}
