module FailFast 
{

	/**
	 * Failer reference specification.
	 * <p>
	 * Implement this to manage a reference to a failer.
	 * </p>
	 * @author Keld Oelykke
	 */
    export interface IFailerReference 
    {
		/**
		 * Retrieves failer.
		 * <p>
		 * Call methods of this to throw an exception when a check asserts.
		 * </p>
		 * @return failer.
		 */
		getFailer() : IFailer;

		/**
		 * Sets the failer.
		 * <p>
		 * Call methods of this to throw an exception when a check asserts.
		 * </p>
		 * @param failer
		 *          failer to use.
		 */
		setFailer(failer : IFailer) : void;
	}

}
