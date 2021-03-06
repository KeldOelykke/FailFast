/////////////////////////////////////////////////////////////////////////////////////////
//
// The MIT License (MIT)
// 
// Copyright (c) 2014-2015 Keld Oelykke
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
/////////////////////////////////////////////////////////////////////////////////////////

package starkcoder.failfast.fails.objects;

import starkcoder.failfast.fails.objects.booleans.IObjectBooleanFailer;
import starkcoder.failfast.fails.objects.bytes.IObjectByteFailer;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterFailer;
import starkcoder.failfast.fails.objects.dates.IObjectDateFailer;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleFailer;
import starkcoder.failfast.fails.objects.enums.IObjectEnumFailer;
import starkcoder.failfast.fails.objects.exceptions.IObjectExceptionFailer;
import starkcoder.failfast.fails.objects.floats.IObjectFloatFailer;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerFailer;
import starkcoder.failfast.fails.objects.longs.IObjectLongFailer;
import starkcoder.failfast.fails.objects.shorts.IObjectShortFailer;
import starkcoder.failfast.fails.objects.strings.IObjectStringFailer;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidFailer;

/**
 * Specification grouping all object fail specifications.
 * <p>
 * This (or a derivative) should inherit all fail methods targeting Object.
 * </p>
 * 
 * @author Keld Oelykke
 */
public interface IObjectFailer extends IObjectNullFail, IObjectNotNullFail, IObjectDefaultFail,
    IObjectNotDefaultFail, IObjectEqualsFail, IObjectNotEqualsFail, IObjectSameFail,
    IObjectNotSameFail, IObjectArrayFailer, IObjectListFailer, IObjectCollectionFailer,
    IObjectsEqualsFail, IObjectsNotEqualsFail, IObjectBooleanFailer, IObjectByteFailer,
    IObjectCharacterFailer, IObjectDateFailer, IObjectDoubleFailer, IObjectEnumFailer,
    IObjectExceptionFailer, IObjectFloatFailer, IObjectIntegerFailer, IObjectLongFailer, 
    IObjectShortFailer, IObjectStringFailer, IObjectUuidFailer
{

}
