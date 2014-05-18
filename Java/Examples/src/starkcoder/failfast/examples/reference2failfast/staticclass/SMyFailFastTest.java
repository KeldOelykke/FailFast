/**
 * 
 */
package starkcoder.failfast.examples.reference2failfast.staticclass;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.examples.reference2failfast.myfailfast.IMyFailFast;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyChecker;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyFailFast;
import starkcoder.failfast.examples.reference2failfast.myfailfast.MyFailer;
import starkcoder.failfast.examples.reference2failfast.myfailfast.SMyFailFast;
import starkcoder.failfast.fails.FailFastException;

/**
 * Shows how a custom fail-fast mechanism can be setup in an application.
 * <p>
 * A static reference to fail-fast instance is setup at application startup.
 * </p>
 * <p>
 * The static reference can be accessed from application code via the the static class.
 * </p>
 * <p>
 * The static reference is cleared at application shutdown.
 * </p>
 * @author Keld Oelykke
 *
 */
public class SMyFailFastTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// this would be in you application startup section
		ICallContractor callContractor = new CallContractor();
		IMyFailFast myFailFastOrNull = new MyFailFast(new MyChecker(callContractor), new MyFailer(callContractor), callContractor);
		SMyFailFast.setMyFailFastOrNull(myFailFastOrNull);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// this would be in you application shutdown section
		SMyFailFast.setMyFailFastOrNull(null);
	}

	@Test(expected=FailFastException.class)
	public void testObjectIsNull() {
		Object referenceNull = null;
		if(SMyFailFast.getMyChecker().isObjectNull(this, referenceNull))
		{
			SMyFailFast.getMyFailer().failIsObjectNull(this, "referenceNull");
		}
	}
	
	@Test
	public void testObjectIsNotNull() {
		Object referenceNotNull = new Object();
		if(SMyFailFast.getMyChecker().isObjectNull(this, referenceNotNull))
		{
			SMyFailFast.getMyFailer().failIsObjectNull(this, "referenceNotNull");
		}
		assertTrue("Expected referenceNotNull to pass the null check", true);
	}

}
