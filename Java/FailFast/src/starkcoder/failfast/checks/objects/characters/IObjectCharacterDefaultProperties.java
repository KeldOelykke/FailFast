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
package starkcoder.failfast.checks.objects.characters;

/**
 * Defines properties used by both {link:IObjectCharacterDefaultCheck} and {link:IObjectCharacterNotDefaultCheck}.
 * 
 * @author Keld Oelykke
 *
 */
public interface IObjectCharacterDefaultProperties
{

	/**
	 * Default float used by isCharacterDefault and isCharacterNotDefault.
	 * <p>
	 * By default a Character has value 0
	 * </p>
	 * 
	 * @return default Character - default is a Character with value 0
	 */
	public Character getCharacterDefault();

	/**
	 * Changes the default value used by isCharacterDefault and isCharacterNotDefault.
	 * 
	 * @param defaultCharacterValue
	 *            new value to set
	 */
	public void setCharacterDefault(
			Character defaultCharacterValue);

}