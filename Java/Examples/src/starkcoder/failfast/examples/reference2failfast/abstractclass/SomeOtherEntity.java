/**
 * 
 */
package starkcoder.failfast.examples.reference2failfast.abstractclass;

/**
 * Example entity implementation using fail-fast reference from abstract class.
 * 
 * @author Keld Oelykke
 */
public class SomeOtherEntity extends AEntity
{

	/**
	 * Illustrates how fail-fast getter in abstract class can be used.
	 * <p>
	 * It is easier to use shortcuts to MyChecker and MyFailer as shown in
	 * {link: SomeEntity}.
	 * </p>
	 * 
	 * @param foo
	 *            some argument
	 * @param bar
	 *            some other argument
	 */
	public void someMethod(Object foo, Object bar)
	{
		// cheap call using existing references
		if (this.getMyFailFast().getMyChecker().isObjectNull(this, foo))
		{
			// expensive call creating string and exception
			this.getMyFailFast().getMyFailer().failIsObjectNull(this, "foo");
		}
		// cheap call using existing references
		if (this.getMyFailFast().getMyChecker().isObjectNull(this, bar))
		{
			// expensive call creating strings and exception
			this.getMyFailFast().getMyFailer()
					.failIsObjectNull(this, "bar", "Is it really closed?");
		}
	}
}
