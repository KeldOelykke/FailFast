/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Keld Ølykke
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
package starkcoder.failfast.templates.objects;

import org.junit.Test;

import starkcoder.failfast.fails.FailFastException;

/**
 * Interface for object not-equals checks.
 * <p>
 * This helps us remember all same checks for each type.
 * </p>
 * 
 * @author Keld Oelykke
 *
 */
public interface IObjectNotEqualsTest<T extends Object> {

	// 1st - caller checks
	
	@Test(expected=IllegalArgumentException.class)
	void testObjectNotEqualsCheckerCallerIsNull();
	
	@Test(expected=IllegalArgumentException.class)
	void testObjectNotEqualsFailerCallerIsNull();
	
	@Test(expected=IllegalStateException.class)
	void testObjectNotEqualsFailerCallerIsWrong();
	
	
	// 2nd - mismatch calls
	
	@Test(expected=IllegalStateException.class)
	void testObjectNotEqualsMismatchCheckCheck();
	
	@Test(expected=IllegalStateException.class)
	void testObjectNotEqualsMismatchFail();

	@Test(expected=IllegalStateException.class)
	void testObjectNotEqualsMismatchWrongCheck();
	
	@Test(expected=IllegalStateException.class)
	void testObjectNotEqualsMismatchWrongFail();
	
	
	// 3rd - normal cases
	
	@Test(expected=FailFastException.class)
	void testObjectNotEqualsFailNoMessage();
	
	@Test(expected=FailFastException.class)
	void testObjectNotEqualsFailMessage();
	
	@Test
	void testObjectNotEqualsNoFail();
}
