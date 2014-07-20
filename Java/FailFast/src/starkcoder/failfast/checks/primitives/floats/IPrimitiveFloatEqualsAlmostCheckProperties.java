/* The MIT License (MIT)
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
package starkcoder.failfast.checks.primitives.floats;

/**
 * Defines properties used by both {link:IPrimitiveFloatEqualsAlmostCheck} and {link:IPrimitiveFloatNotEqualsAlmostCheck}.
 * 
 * @author Keld Oelykke
 *
 */
public interface IPrimitiveFloatEqualsAlmostCheckProperties
{

	/**
	 * Default relative allowed difference used by isFloatValueEqualsAlmost.
	 * <p>
	 * By default the allowed relative difference is 0.001f (0.1%)
	 * </p>
	 * 
	 * @return default allowed relative difference - default is 0.001f (0.1%)
	 */
	public float getFloatValueEqualsAlmostDefaultAllowedRelativeDifference();

	/**
	 * Changes the default allowed relative difference used by isFloatValueEqualsAlmost.
	 * 
	 * @param defaultRelativeDifference
	 *            new value to set
	 */
	public void setFloatValueEqualsAlmostDefaultAllowedRelativeDifference(
			float defaultAllowedRelativeDifference);

	/**
	 * Default minimum denominator used by isFloatValueEqualsAlmost.
	 * <p>
	 * By default the minimum denominator is 0.00001f
	 * </p>
	 * 
	 * @return default minimum denominator - default is 0.00001f
	 */
	public float getFloatValueEqualsAlmostDefaultMinimumDenominator();

	/**
	 * Changes the default minimum denominator used by isFloatValueEqualsAlmost.
	 * 
	 * @param defaultMinimumDenominator
	 *            new value to set
	 */
	public void setFloatValueEqualsAlmostDefaultMinimumDenominator(
			float defaultMinimumDenominator);

}