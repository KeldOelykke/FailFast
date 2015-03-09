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
package starkcoder.failfast.checks.objects;

import starkcoder.failfast.checks.objects.booleans.IObjectBooleanChecker;
import starkcoder.failfast.checks.objects.bytes.IObjectByteChecker;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterChecker;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleChecker;
import starkcoder.failfast.checks.objects.enums.IObjectEnumChecker;
import starkcoder.failfast.checks.objects.floats.IObjectFloatChecker;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerChecker;
import starkcoder.failfast.checks.objects.longs.IObjectLongChecker;
import starkcoder.failfast.checks.objects.shorts.IObjectShortChecker;
import starkcoder.failfast.checks.objects.strings.IObjectStringChecker;

/**
 * Specification grouping all object check specifications.
 * <p>
 * This (or a derivative) should inherit all check methods targeting Object.
 * </p>
 * 
 * @author Keld Oelykke
 */
public interface IObjectChecker extends IObjectNullCheck, IObjectNotNullCheck,
		IObjectDefaultCheck, IObjectNotDefaultCheck,
		IObjectEqualsCheck, IObjectNotEqualsCheck, IObjectSameCheck, IObjectNotSameCheck,
		IObjectArrayChecker, IObjectListChecker, IObjectCollectionChecker,
		IObjectsEqualsCheck, IObjectsNotEqualsCheck,
		IObjectBooleanChecker, IObjectByteChecker, IObjectCharacterChecker, IObjectDoubleChecker, IObjectEnumChecker, IObjectFloatChecker, IObjectShortChecker, IObjectIntegerChecker, IObjectLongChecker, IObjectStringChecker
{

}
