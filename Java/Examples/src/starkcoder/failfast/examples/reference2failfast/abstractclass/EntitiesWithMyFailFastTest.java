/**
 * 
 */
package starkcoder.failfast.examples.reference2failfast.abstractclass;

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
 * The static reference can be accessed from application code via helper methods in an abstract class.
 * </p>
 * <p>
 * The static reference is cleared at application shutdown.
 * </p>
 * @author Keld Oelykke
 *
 */
public class EntitiesWithMyFailFastTest {

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
	public void testSomeEntityFooIsNull() {
		SomeEntity someEntity = new SomeEntity();
		Object foo = null;
		Object bar = new Object();
		someEntity.someMethod(foo, bar);
	}
	
	@Test(expected=FailFastException.class)
	public void testSomeEntityBarIsNull() {
		SomeEntity someEntity = new SomeEntity();
		Object foo = new Object();
		Object bar = null;
		someEntity.someMethod(foo, bar);
	}
	
	@Test
	public void testSomeEntityFooBarNotNull() {
		SomeEntity someEntity = new SomeEntity();
		Object foo = new Object();
		Object bar = new Object();
		someEntity.someMethod(foo, bar);
		assertTrue("Expected someEntity to pass the foo & bar null checks", true);
	}

}
