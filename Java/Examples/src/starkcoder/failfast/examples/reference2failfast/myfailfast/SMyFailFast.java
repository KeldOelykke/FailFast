/**
 * 
 */
package starkcoder.failfast.examples.reference2failfast.myfailfast;

/**
 * Static class that references a global fail-fast instance.
 * <p>
 * Initialize the static reference at startup and clear it at shutdown.
 * </p>
 * @author Keld Oelykke
 */
public final class SMyFailFast {

	private static IMyFailFast myFailFastOrNull;

	/**
	 * Retrieves the setup fail-fast instance.
	 * <p>
	 * If the instance is unavailable e.g. null, an IllegalStateException is thrown.
	 * </p>
	 * @return available instance
	 */
	public static IMyFailFast getMyFailFast() {
		IMyFailFast myFailFast = getMyFailFastOrNull();
		if (null == myFailFast) {
			throw new IllegalStateException("static field myFailFast is null.");
		}
		return myFailFast;
	}

	/**
	 * Retrieves the setup instance or null, if no instance is available.
	 * 
	 * @return instance or null
	 */
	public static IMyFailFast getMyFailFastOrNull() {
		return SMyFailFast.myFailFastOrNull;
	}

	/**
	 * Set this at startup and clear it at shutdown.
	 * 
	 * @param myFailFastOrNull
	 *            instance to use or null to prevent usage
	 */
	public static void setMyFailFastOrNull(IMyFailFast myFailFastOrNull) {
		SMyFailFast.myFailFastOrNull = myFailFastOrNull;
	}
	
	
	/**
	 * Shortcut to your custom checker.
	 *
	 * @return custom checker.
	 */
	public static IMyChecker getMyChecker()
	{
		return getMyFailFast().getMyChecker();
	}

	/**
	 * Retrieves custom failer instead of IFailer.
	 * <p>
	 * Call methods of this (including your extensions) to throw an exception when a check asserts.
	 * </p>
	 * @return custom failer.
	 */
	public static IMyFailer getMyFailer()
	{
		return getMyFailFast().getMyFailer();
	}

}
