/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2015 Keld Oelykke
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package starkcoder.failfast.examples.exceptionmessages2customize;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import starkcoder.failfast.FailFast;
import starkcoder.failfast.IFailFast;
import starkcoder.failfast.SFailFast;
import starkcoder.failfast.checks.Checker;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.Failer;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.IFailer;
import starkcoder.failfast.fails.NFail;

/**
 * This gives an overview of the exceptions that can be customized.
 * 
 * @author Keld Oelykke
 *
 */
public class ExceptionMessages2CustomizeTest {

	private ICallContractor contractor;
	private IChecker checker;
	private IFailer failer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// this trinity would be in you application startup section
		ICallContractor callContractor = new CallContractor();
		IChecker checker = new Checker(callContractor);
		IFailer failer = new Failer(callContractor);
		// easiest if you have access to each of the 3 from your code
		this.contractor = callContractor;
		this.checker = checker;
		this.failer = failer;
		// if you want 1 instance grouping the trinity
		IFailFast failFastOrNull = new FailFast(checker, failer, callContractor);
		// if you want static access to the trinity
		SFailFast.setFailFastOrNull(failFastOrNull);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// this would be in you application shutdown section
		SFailFast.setFailFastOrNull(null);
		this.checker = null;
		this.failer = null;
		this.contractor = null;
	}
	
	
	private String toString = null;
	@Override
	public String toString() {
		return this.toString;
	}
	@Rule
	public TestWatcher watcher = new TestWatcher() {
	   protected void starting(Description description) {
		   toString = description.getTestClass().getSimpleName() + "." + description.getMethodName();
	   }
	};
	

	@Test
	public void testOutputDefaults() 
	{
		StringBuilder stringBuilder = new StringBuilder();
		
//		{ // output memory usage
//			stringBuilder.append(" - - - \n");
//			stringBuilder.append(" Primitive - boolean \n");
//			stringBuilder.append(" - - - \n");
//			stringBuilder.append("Before: used=");
//			stringBuilder.append(usedBefore);
//			stringBuilder.append(" [bytes]\n");
//			stringBuilder.append("After: used=");
//			stringBuilder.append(usedAfter);
//			stringBuilder.append(" [bytes]\n");
//			stringBuilder.append("Delta: delta=");
//			stringBuilder.append(delta);
//			stringBuilder.append(" [bytes]\n");
//			stringBuilder.append("Delta: costPerCall=");
//			stringBuilder.append(deltaPerCall);
//			stringBuilder.append(" [bytes/call]\n");
//			stringBuilder.append("GC: callDuration=");
//			stringBuilder.append(gcCallDurationMS);
//			stringBuilder.append(" [ms]\n");
//			stringBuilder.append(" - - - \n");
//		}
		
		HashSet<Class<?>> investigatedClasses = new HashSet<Class<?>>();
		this.populateWithAllSupers(investigatedClasses, this.failer.getClass());
		TreeMap<String, Class<?>> sortedMap = new TreeMap<String, Class<?>>();
		for(Class<?> investigatedClass : investigatedClasses)
		{ // filtering and sorting interfaces by package name
			if(IFail.class.isAssignableFrom(investigatedClass)
				&& investigatedClass.isInterface()
				&& !investigatedClass.equals(IFail.class)
				&& !investigatedClass.equals(IFailer.class))
			{
				sortedMap.put(investigatedClass.getName(), investigatedClass);
			}
		}

		for(Class<?> sortedInterface : sortedMap.values())
		{
			stringBuilder.append("\\\n");
			stringBuilder.append(" | " + sortedInterface.getName() + "\n");
			stringBuilder.append(" | -\n");

			{
				Method[] methods = sortedInterface.getDeclaredMethods();
				for(Method method : methods)
				{
					NFail nFail = method.getAnnotation(NFail.class);
					if(null != nFail)
					{
						stringBuilder.append(" | ");
						stringBuilder.append("\"");
						stringBuilder.append(nFail.failerSpecificationAndMethodID());
						stringBuilder.append("\"");
						stringBuilder.append(", ");
						stringBuilder.append("\"");
						stringBuilder.append(nFail.failExceptionType().getSimpleName());
						stringBuilder.append("\"");
						stringBuilder.append(", ");
						stringBuilder.append("\"");
						stringBuilder.append(nFail.failMessageFormat());
						stringBuilder.append("\"");
						stringBuilder.append(", ");
						stringBuilder.append("\"");
						stringBuilder.append(nFail.failMessageArguments());
						stringBuilder.append("\"\n");
					}
				}
			}
			
			stringBuilder.append("/\n");
		}

//		HashSet<Class<?>> failInterfaces = new HashSet<Class<?>>();
//		
//		Class<? extends IFailer> failerInterface = this.failer.getClass();
//		
//		Class<?>[] interfaces = failerInterface.getInterfaces();
//		Class<?> superClass = failerInterface.getSuperclass();
//		Class<?>[] failerClassInterfaces = failerClass.getClasses();
//		for(Class<?> failerClassInterface : failerClassInterfaces)
//		{
//			if(IFail.class.isAssignableFrom(failerClassInterface))
//			{
//				stringBuilder.append(failerClassInterface.getName() + "\n");
//			}
//		}
//		Method[] methods = .getMethods();
//		for(Method method : methods)
//		{
//			String methodName = method.getName();
//			if(methodName.startsWith("fail"))
//			{
//				
//			}
//		}
		
		System.out.println(stringBuilder.toString());
	}

	private void populateWithAllSupers(HashSet<Class<?>> investigatedClasses,
			Class<?> class2Investigate)
	{
		investigatedClasses.add(class2Investigate);
		Class<?>[] interfaces = class2Investigate.getInterfaces();
		for(Class<?> interface_ : interfaces)
		{
			this.populateWithAllSupers(investigatedClasses, interface_);
		}
		Class<?> superClass = class2Investigate.getSuperclass();
		if(null != superClass)
		{
			this.populateWithAllSupers(investigatedClasses, superClass);
		}
	}
	
	
	
}
