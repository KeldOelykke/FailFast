/**
 * 
 */
package starkcoder.failfast;

import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.fails.IFailer;

/**
 * Static class that references a global fail-fast instance.
 * <p>
 * Initialize the static reference at startup and clear it at shutdown.
 * </p>
 * @author Keld Oelykke
 */
public final class SFailFast {

	private static IFailFast failFastOrNull;

	/**
	 * Retrieves the setup fail-fast instance.
	 * <p>
	 * If the instance is unavailable e.g. null, an IllegalStateException is thrown.
	 * </p>
	 * @return available instance
	 */
	public static IFailFast getFailFast() {
		IFailFast failFast = getFailFastOrNull();
		if (null == failFast) {
			throw new IllegalStateException("static field failFast is null.");
		}
		return failFast;
	}

	/**
	 * Retrieves the setup instance or null, if no instance is available.
	 * 
	 * @return instance or null
	 */
	public static IFailFast getFailFastOrNull() {
		return SFailFast.failFastOrNull;
	}

	/**
	 * Set this at startup and clear it at shutdown.
	 * 
	 * @param failFastOrNull
	 *            instance to use or null to prevent usage
	 */
	public static void setFailFastOrNull(IFailFast failFastOrNull) {
		SFailFast.failFastOrNull = failFastOrNull;
	}
	
	
	/**
	 * Shortcut to checker.
	 * <p>
	 * Call methods of this to check references, etc.
	 * </p>
	 * @return fail-fast checker.
	 */
	public static IChecker getChecker()
	{
		return getFailFast().getChecker();
	}

	/**
	 * Shorcut to failer.
	 * <p>
	 * Call methods of this to throw an exception when a check asserts.
	 * </p>
	 * @return fail-fast failer.
	 */
	public static IFailer getFailer()
	{
		return getFailFast().getFailer();
	}

}
