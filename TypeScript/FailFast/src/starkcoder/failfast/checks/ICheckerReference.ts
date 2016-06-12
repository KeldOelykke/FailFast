module FailFast 
{

	/**
	 * Checker reference specification.
	 * <p>
	 * Implement this to manage a reference to a checker.
	 * </p>
	 * 
	 * @author Keld Oelykke
	 */
	export interface ICheckerReference 
    {
		/**
		* Retrieves checker.
		* <p>
		* Call methods of this to check arguments.
		* </p>
		* 
		* @return checker.
		*/
		getChecker() : IChecker;
	
		/**
		* Sets the checker.
		* <p>
		* Call methods of this to check arguments.
		* </p>
		* 
		* @param checker
		*          checker to use.
		*/
		setChecker(checker : IChecker) : void;
    }

}
