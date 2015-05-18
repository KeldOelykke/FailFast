/* The MIT License (MIT)
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
package starkcoder.failfast.checks.objects.uuids;

import java.util.UUID;

/**
 * Defines properties used by both {link:IObjectUuidDefaultCheck} and {link:IObjectUuidNotDefaultCheck}.
 * 
 * @author Keld Oelykke
 *
 */
public interface IObjectUuidDefaultProperties
{

	/**
	 * Default float used by isUuidDefault and isUuidNotDefault.
	 * <p>
	 * By default a UUID has value 0
	 * </p>
	 * 
	 * @return default UUID - default is a UUID with value 0
	 */
	public UUID getUuidDefault();

	/**
	 * Changes the default value used by isUuidDefault and isUuidNotDefault.
	 * 
	 * @param defaultUuidValue
	 *            new value to set
	 */
	public void setUuidDefault(
			UUID defaultUuidValue);

}