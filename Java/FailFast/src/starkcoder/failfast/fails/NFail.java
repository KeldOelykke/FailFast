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
package starkcoder.failfast.fails;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotates fail-methods in failers.
 * 
 * @author Keld Oelykke
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NFail
{
	/**
	 * The Checker specification type with check-methods that must be used with the annotated fail-method.
	 * 
	 * @return Checker specification type that matches this Failer attribute
	 */
	public Class<?> checkerSpecificationType();
	
	/**
	 * Type of exception to throw on failure.
	 * 
	 * @return Type of exception to throw
	 */
	public Class<? extends FailFastException> failExceptionType();
	
	/**
	 * Exception message formatting string for thrown exception.
	 * 
	 * <p>
	 * failMessageArguments is used to produce an Object[] with this.
	 * </p>
	 * <p>
	 * An example could be "%s: %s(%s) and %s(%s) are NOT equals".
	 * </p>
	 * 
	 * @return Exception message formatting string.
	 */
	public String failMessageFormat();
	
	/**
	 * Comma-separated list of ids of checker-call and failer-call arguments supplied by either user or implementation.
	 * 
	 * <p>
	 * This list determines the order and content of an Object[] used with failMessageFormat.
	 * </p>
	 * <p>
	 * Checker-call argument ids are cu0, cu1, cu2, ... e.g. cu0 being caller, cu1 being referenceA
	 * </p>
	 * <p>
	 * Failer-call argument ids are fu0, fu1, fu2, ... e.g. fu0 being caller, fu1 being "referenceA"
	 * </p>
	 * <p>
	 * An example could be "cu0,fu1,cu1,fu2,cu2" to map caller and 2 arguments (both name and value) to failMessageFormat
	 * </p>
	 * <p>
	 * Checker-implementation argument ids are cx0, cx1, cx2, ... e.g. cx0 being default value used in checker
	 * </p>
	 * <p>
	 * Failer-implementation argument ids are fx0, fx1, cx2, ... e.g. fx0 being current time supplied by failer
	 * </p>
	 * 
	 * @return Comma-separated list of ids of checker-call and failer-call arguments supplied by either user or implementation.
	 */
	public String failMessageArguments();
}
