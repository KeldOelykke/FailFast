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
package starkcoder.failfast.checks.objects.enums;

/**
 * Defines properties used by both {link:IObjectEnumDefaultCheck} and {link:IObjectEnumNotDefaultCheck}.
 * 
 * @author Keld Oelykke
 *
 */
public interface IObjectEnumDefaultProperties
{

	/**
	 * Default Enum used by isEnumDefault and isEnumNotDefault.
	 * <p>
	 * By default the first entry of an Enum is the default.
	 * </p>
	 * <p>
	 * Use setEnumDefault() to change the default Enum.
	 * </p>
	 * 
	 * @return default Enum - default is Enum.False
	 */
	Enum<?> getEnumDefault(Class<? extends Enum<?>> enumType);

	/**
	 * Changes the default object used by isEnumDefault and isEnumNotDefault.
	 * 
	 * @param defaultEnum
	 *            new default object to set
	 */
	void setEnumDefault(Enum<?> defaultEnum);

}