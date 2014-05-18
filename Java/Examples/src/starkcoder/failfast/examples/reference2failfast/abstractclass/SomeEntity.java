/**
 * 
 */
package starkcoder.failfast.examples.reference2failfast.abstractclass;

/**
 * Example entity implementation using fail-fast shortcut methods in abstract
 * class.
 * 
 * @author Keld Oelykke
 */
public class SomeEntity extends AEntity {

	/**
	 * Illustrates how fail-fast helper methods in abstract class can be used.
	 * 
	 * @param foo
	 *            some argument
	 * @param bar
	 *            some other argument
	 */
	public void someMethod(Object foo, Object bar) {
		// cheap call using existing references
		if (this.getMyChecker().isObjectNull(this, foo)) 
		{
			// expensive call creating string and exception
			this.getMyFailer().failIsObjectNull(this, "foo"); 
		}
		// cheap call using existing references
		if (this.getMyChecker().isObjectNull(this, bar))
		{
			// expensive call creating strings and exception
			this.getMyFailer().failIsObjectNull(this, "bar",
					"Is it really closed?");
		}
	}
}
