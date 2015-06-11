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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import starkcoder.failfast.fails.FailFastException;
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
		IFailFast failFastOrNull = new FailFast(this.checker, this.failer, this.contractor);
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
	
	
	
	
	// Contract Customization
	
	@Test(expected=NullPointerException.class)
	public void testObjectNullContractCustomNullPointerExceptionNoMessage() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailExceptionType(NullPointerException.class);
				failer.failObjectNull(this, "referenceNull");
			}
		}
		catch(NullPointerException customException)
		{
			assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
			System.out.println(customException.getMessage());
			throw customException;
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testObjectNullContractCustomNullPointerExceptionMessage() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailExceptionType(NullPointerException.class);
				contractor.getContractWithCaller(this).setCustomFailMessagePostfix("Extra info goes here.");
				failer.failObjectNull(this, "referenceNull");
			}
		}
		catch(NullPointerException customException)
		{
			assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
			System.out.println(customException.getMessage());
			throw customException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectNullContractCustomFailMessageFormat() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailMessageFormat("%s: I am so tired of Object '%s' being null.");
				failer.failObjectNull(this, "referenceNull");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectNullContractCustomFailMessageFormatAndArguments() {
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				contractor.getContractWithCaller(this).setCustomFailMessageFormat("Object '%s' is null - reported by Caller '%s'.");
				contractor.getContractWithCaller(this).setCustomFailMessageArguments("fu1, fu0");
				failer.failObjectNull(this, "referenceNull");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;

		}
	}

	// Failer Customizations
	
	@Test(expected=NullPointerException.class)
	public void testObjectNullFailerCustomNullPointerExceptionNoMessage() {
		
		{ // Failer costumizations could be done at application startup
			String failerSpecificationAndMethodID = "IObjectNullFail.failObjectNull(Object caller, String referenceName)";
			this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodID, NullPointerException.class);
		}
		
		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull");
			}
		}
		catch(NullPointerException customException)
		{
			assertNull("Expected no registered exception in failer", failer.getFailFastExceptionOrNull());
			System.out.println(customException.getMessage());
			throw customException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectNullFailerCustomNullPointerExceptionMessage() {

		{ // Failer costumizations could be done at application startup
			String failerSpecificationAndMethodID = "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
			this.failer.registerCustomFailExceptionType(failerSpecificationAndMethodID, NullPointerException.class);
			this.failer.unregisterCustomFailExceptionType(failerSpecificationAndMethodID); // revert to default
		}

		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			throw failFastException;
		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectNullFailerCustomFailMessageFormat() {

		{ // Failer costumizations could be done at application startup
			String failerSpecificationAndMethodID = "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
			this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodID, "%s: I am so tired of Object '%s' being null. %s");
		}

		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			String s = this.getClass().getSimpleName() + ".testObjectNullFailerCustomFailMessageFormat: I am so tired of Object 'referenceNull' being null. Extra info goes here.";
			assertEquals("Expected exception in failer", s, failFastException.getMessage());
			throw failFastException;

		}
	}
	
	@Test(expected=FailFastException.class)
	public void testObjectNullFailerCustomFailMessageFormatAndArguments() {
		
		{ // Failer costumizations could be done at application startup
			String failerSpecificationAndMethodID = "IObjectNullFail.failObjectNull(Object caller, String referenceName, String message)";
			this.failer.registerCustomFailMessageFormat(failerSpecificationAndMethodID, "Object '%s' is null - reported by Caller '%s'. %s");
			this.failer.registerCustomFailMessageArguments(failerSpecificationAndMethodID, "fu1, fu0, fu2"); // just swapping them
		}

		Object referenceNull = null;
		try
		{
			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull", "Extra info goes here.");
			}
		}
		catch(FailFastException failFastException)
		{
			assertEquals("Expected registered exception in failer", failFastException, failer.getFailFastExceptionOrNull());
			System.out.println(failFastException.getMessage());
			String s = "Object 'referenceNull' is null - reported by Caller '" + this.getClass().getSimpleName() + ".testObjectNullFailerCustomFailMessageFormatAndArguments'. Extra info goes here.";
			assertEquals("Expected exception in failer", s, failFastException.getMessage());
			throw failFastException;
		}
	}		
	

	// Output defaults
	
	@Test
	public void testOutputExceptionDefaults() 
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n --- EXCEPTION DEFAULTS & CUSTOMIZATIONS --- (START)\n");
		stringBuilder.append("\n");
		stringBuilder.append(" Below is a table of defaults for the different failer methods.\n");
		stringBuilder.append(" The exception type, message format and message arguments identification string can be customized as you please.\n");
		stringBuilder.append(" Static customizations can be done via inheritance by overriding which failer specifications to use.\n");
		stringBuilder.append(" Runtime customizations can be done via a call contract (where you call a checker-failer pair) or globally via a failer (e.g. in your startup code).\n");
		stringBuilder.append(" For call contract customizations refer to ICallContract (instance fetched via ICallContractor), the other tests in this file and use below table as inputs.\n");
		stringBuilder.append(" For global runtime customizations refer to IFailerCustomizer (inherited by IFailer), the other tests in this file and use below table as inputs.\n");
		stringBuilder.append("\n");
		
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

		int columnSizeFailerSpecificationAndMethodID = 150;
		int columnSizeFailExceptionType = 30;
		int columnSizeFailMessageFormat = 110;
		int columnSizeFailMessageArguments = 50;
		int columnSizeAll = columnSizeFailerSpecificationAndMethodID 
			+ columnSizeFailExceptionType 
			+ columnSizeFailMessageFormat 
			+ columnSizeFailMessageArguments + 9;
		stringBuilder.append(" /-");
		stringBuilder.append(padWithChars("", '-', columnSizeAll));
		stringBuilder.append("-\\\n");
		{
			stringBuilder.append(" | ");
			stringBuilder.append(padWithChars("failerSpecificationAndMethodID", ' ', columnSizeFailerSpecificationAndMethodID));
			stringBuilder.append(" | ");
			stringBuilder.append(padWithChars("failExceptionType", ' ', columnSizeFailExceptionType));
			stringBuilder.append(" | ");
			stringBuilder.append(padWithChars("failMessageFormat", ' ', columnSizeFailMessageFormat));
			stringBuilder.append(" | ");
			stringBuilder.append(padWithChars("failMessageArguments", ' ', columnSizeFailMessageArguments));
			stringBuilder.append(" |\n");
		}
		stringBuilder.append(" |-");
		stringBuilder.append(padWithChars("", '-', columnSizeAll));
		stringBuilder.append("-|\n");
		for(Class<?> sortedInterface : sortedMap.values())
		{
			Method[] methods = sortedInterface.getDeclaredMethods();
			for(Method method : methods)
			{
				NFail nFail = method.getAnnotation(NFail.class);
				if(null != nFail)
				{
					stringBuilder.append(" | ");
					stringBuilder.append(padWithChars("\"" + nFail.failerSpecificationAndMethodID() + "\"", ' ', columnSizeFailerSpecificationAndMethodID));
					stringBuilder.append(" | ");
					stringBuilder.append(padWithChars(nFail.failExceptionType().getSimpleName() + ".class", ' ', columnSizeFailExceptionType));
					stringBuilder.append(" | ");
					stringBuilder.append(padWithChars("\"" + nFail.failMessageFormat() + "\"", ' ', columnSizeFailMessageFormat));
					stringBuilder.append(" | ");
					stringBuilder.append(padWithChars("\"" + nFail.failMessageArguments() + "\"", ' ', columnSizeFailMessageArguments));
					stringBuilder.append(" |\n");
				}
			}
		}
		stringBuilder.append(" \\-");
		stringBuilder.append(padWithChars("", '-', columnSizeAll));
		stringBuilder.append("-/\n");
		stringBuilder.append("\n");
		stringBuilder.append(" --- EXCEPTION DEFAULTS & CUSTOMIZATIONS --- (END)\n");

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
	
	private String padWithChars(String source, char paddingChar, int totalSize)
	{
		String result = source;
		
		if(source.length() < totalSize)
		{
			int numberSpaces2Append = totalSize - source.length();
			char[] spaces = new char[numberSpaces2Append];
			for(int index = 0; index < spaces.length; ++index)
			{
				spaces[index] = paddingChar;
			}
			result = source + new String(spaces);
		}
		
		return result;
	}
	
	
	
}
