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

package starkcoder.failfast.checks;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import starkcoder.failfast.checks.objects.IObjectArrayEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectCollectionEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectDefaultCheck;
import starkcoder.failfast.checks.objects.IObjectEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectListEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectNotDefaultCheck;
import starkcoder.failfast.checks.objects.IObjectNotEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectNotNullCheck;
import starkcoder.failfast.checks.objects.IObjectNotSameCheck;
import starkcoder.failfast.checks.objects.IObjectNullCheck;
import starkcoder.failfast.checks.objects.IObjectSameCheck;
import starkcoder.failfast.checks.objects.IObjectsEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectsNotEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanFalseCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotDefaultCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotSameCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanSameCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanTrueCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteDefaultCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteEqualsCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteGreaterCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteInsideCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteLessCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteNotDefaultCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteNotEqualsCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteNotNullCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteNotSameCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteNullCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteOutsideCheck;
import starkcoder.failfast.checks.objects.bytes.IObjectByteSameCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterDefaultCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterEqualsCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterGreaterCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterInsideCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterLessCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterNotDefaultCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterNotEqualsCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterNotNullCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterNotSameCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterNullCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterOutsideCheck;
import starkcoder.failfast.checks.objects.characters.IObjectCharacterSameCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateDefaultCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateEqualsCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateGreaterCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateInsideCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateLessCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateNotDefaultCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateNotEqualsCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateNotNullCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateNotSameCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateNullCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateOutsideCheck;
import starkcoder.failfast.checks.objects.dates.IObjectDateSameCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleDefaultCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleEqualsAlmostCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleEqualsCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleGreaterCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleInsideCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleLessCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleNotDefaultCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleNotEqualsAlmostCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleNotEqualsCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleNotNullCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleNotSameCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleNullCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleOutsideCheck;
import starkcoder.failfast.checks.objects.doubles.IObjectDoubleSameCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumEqualsCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumGreaterCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumInsideCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumLessCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotDefaultCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotEqualsCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotNullCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotSameCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNullCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumOutsideCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumSameCheck;
import starkcoder.failfast.checks.objects.exceptions.IObjectExceptionCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatEqualsAlmostCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatEqualsCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatGreaterCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatInsideCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatLessCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatNotDefaultCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatNotEqualsAlmostCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatNotEqualsCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatNotNullCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatNotSameCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatNullCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatOutsideCheck;
import starkcoder.failfast.checks.objects.floats.IObjectFloatSameCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerDefaultCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerEqualsCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerGreaterCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerInsideCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerLessCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerNotDefaultCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerNotEqualsCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerNotNullCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerNotSameCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerNullCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerOutsideCheck;
import starkcoder.failfast.checks.objects.integers.IObjectIntegerSameCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongDefaultCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongEqualsCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongGreaterCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongInsideCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongLessCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongNotDefaultCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongNotEqualsCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongNotNullCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongNotSameCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongNullCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongOutsideCheck;
import starkcoder.failfast.checks.objects.longs.IObjectLongSameCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortDefaultCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortEqualsCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortGreaterCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortInsideCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortLessCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortNotDefaultCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortNotEqualsCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortNotNullCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortNotSameCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortNullCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortOutsideCheck;
import starkcoder.failfast.checks.objects.shorts.IObjectShortSameCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringDefaultCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringEqualsCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringGreaterCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringLessCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringMatchingCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotDefaultCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotEqualsCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotMatchingCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotNullAndNotEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotNullCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotSameCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNullCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNullOrEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringSameCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithPostfixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithPrefixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithSubstringCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPostfixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPrefixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutSubstringCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidDefaultCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidEqualsCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidGreaterCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidGreaterOrEqualsCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidInsideCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidLessCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidLessOrEqualsCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidNotDefaultCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidNotEqualsCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidNotNullCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidNotSameCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidNullCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidOutsideCheck;
import starkcoder.failfast.checks.objects.uuids.IObjectUuidSameCheck;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.contractors.contracts.CallContract;
import starkcoder.failfast.contractors.contracts.ICallContract;

/**
 * Abstract implementation of {@link IChecker}.
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * 
 * @author Keld Oelykke
 */
public abstract class AChecker implements IChecker
{
  private ICallContractor callContractor;

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.contractors.ICallContractorReference#getCallContractor ()
   */
  @Override
  public ICallContractor getCallContractor()
  {
    return this.callContractor;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.contractors.ICallContractorReference#setCallContractor
   * (starkcoder.failfast.contractors.ICallContractor)
   */
  @Override
  public void setCallContractor(ICallContractor callContractor)
  {
    this.callContractor = callContractor;
  }

  // // GENERIC OBJECT - START
  //
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.objects.IGenericObjectNullCheck#isGenericObjectNull(
  // java.lang.Object, java.lang.Object)
  // */
  // @Override
  // public <A extends Object> boolean isGenericObjectNull(Object caller, A reference)
  // {
  // boolean result = false;
  //
  // result = this.isGenericObjectNullImplementation(caller, reference,
  // IGenericObjectNullCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.objects.IGenericObjectNotNullCheck#isGenericObjectNotNull(
  // java.lang.Object, java.lang.Object)
  // */
  // @Override
  // public <A> boolean isGenericObjectNotNull(Object caller, A reference)
  // {
  // boolean result = false;
  //
  // result = this.isGenericObjectNotNullImplementation(caller, reference,
  // IGenericObjectNotNullCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.objects.IGenericObjectSameCheck#isGenericObjectSame(
  // java.lang.Object, java.lang.Object, java.lang.Object)
  // */
  // @Override
  // public boolean isGenericObjectSame(Object caller, Object referenceA,
  // Object referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
  // IGenericObjectSameCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.objects.IGenericObjectNotSameCheck#isGenericObjectNotSame(
  // java.lang.Object, java.lang.Object, java.lang.Object)
  // */
  // @Override
  // public <A, B> boolean isGenericObjectNotSame(Object caller, A referenceA,
  // B referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
  // IGenericObjectNotSameCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.objects.IGenericObjectEqualsCheck#isGenericObjectEquals(
  // java.lang.Object, java.lang.Object, java.lang.Object)
  // */
  // @Override
  // public <A,B> boolean isGenericObjectEquals(Object caller, A referenceA,
  // B referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
  // IGenericObjectEqualsCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.objects.IGenericObjectNotEqualsCheck
  // #isGenericObjectNotEquals(
  // java.lang.Object, java.lang.Object, java.lang.Object)
  // */
  // @Override
  // public <A,B> boolean isGenericObjectNotEquals(Object caller, A referenceA,
  // B referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
  // IGenericObjectNotEqualsCheck.class);
  //
  // return result;
  // }
  //
  //
  //
  // // GENERIC OBJECT - END

  // // GENERIC ARRAY - START
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.arrays.IGenericArrayEqualsCheck#isGenericArrayEquals(
  // java.lang.Object, java.lang.Object[], java.lang.Object[])
  // */
  // @Override
  // public <A,B> boolean isGenericArrayEquals(Object caller, A[] referenceA,
  // B[] referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericArrayEqualsImplementation(caller, referenceA, referenceB,
  // IGenericArrayEqualsCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.arrays.IGenericArrayNotEqualsCheck#isGenericArrayNotEquals(
  // java.lang.Object, java.lang.Object[], java.lang.Object[])
  // */
  // @Override
  // public <A,B> boolean isGenericArrayNotEquals(Object caller, A[] referenceA,
  // B[] referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericArrayNotEqualsImplementation(caller, referenceA, referenceB,
  // IGenericArrayNotEqualsCheck.class);
  //
  // return result;
  // }
  //
  // // GENERIC ARRAY - END
  //
  //
  // // GENERIC COLLECTION - START
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.collections.IGenericCollectionEqualsCheck
  // #isGenericCollectionEquals(
  // java.lang.Object, java.util.Collection, java.util.Collection)
  // */
  // @Override
  // public <A,B> boolean isGenericCollectionEquals(Object caller,
  // Collection<A> referenceA, Collection<B> referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericCollectionEqualsImplementation(caller, referenceA, referenceB,
  // IGenericCollectionEqualsCheck.class);
  //
  // return result;
  // }
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.collections.IGenericCollectionNotEqualsCheck
  // #isGenericCollectionNotEquals(
  // java.lang.Object, java.util.Collection, java.util.Collection)
  // */
  // @Override
  // public <A,B> boolean isGenericCollectionNotEquals(Object caller,
  // Collection<A> referenceA, Collection<B> referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericCollectionNotEqualsImplementation(caller, referenceA,
  // referenceB,
  // IGenericCollectionNotEqualsCheck.class);
  //
  // return result;
  // }
  //
  // // GENERIC COLLECTION - END
  //
  //
  //
  // // GENERIC LIST - START
  //
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.lists.IGenericListEqualsCheck#isGenericListEquals(
  // java.lang.Object, java.util.List, java.util.List)
  // */
  // @Override
  // public <A,B> boolean isGenericListEquals(Object caller, List<A> referenceA,
  // List<B> referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericListEqualsImplementation(caller, referenceA, referenceB,
  // IGenericListEqualsCheck.class);
  //
  // return result;
  // }
  //
  //
  // /* (non-Javadoc)
  // * @see
  // starkcoder.failfast.checks.generics.lists.IGenericListNotEqualsCheck#isGenericListNotEquals(
  // java.lang.Object, java.util.List, java.util.List)
  // */
  // @Override
  // public <A,B> boolean isGenericListNotEquals(Object caller, List<A> referenceA,
  // List<B> referenceB)
  // {
  // boolean result = false;
  //
  // result = this.isGenericListNotEqualsImplementation(caller, referenceA, referenceB,
  // IGenericListNotEqualsCheck.class);
  //
  // return result;
  //
  // }
  //
  // // GENERIC LIST - END

  // OBJECT - START

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.IObjectNullCheck#isObjectNull(java.lang.Object,
   * java.lang.Object)
   */
  @Override
  public <A extends Object> boolean isObjectNull(Object caller, A reference)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, reference, IObjectNullCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.IObjectNotNullCheck#isObjectNotNull
   * (java.lang.Object, java.lang.Object)
   */
  @Override
  public <A extends Object> boolean isObjectNotNull(Object caller, A reference)
  {
    boolean result = false;

    result = this
        .isGenericObjectNotNullImplementation(caller, reference, IObjectNotNullCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.IObjectEqualsCheck#isObjectEquals(
   * java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectEquals(Object caller, A referenceA,
      B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.IObjectNotEqualsCheck#isObjectNotEquals
   * (java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectNotEquals(Object caller,
      A referenceA, B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectNotEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.IObjectSameCheck#isObjectSame(java.lang.Object,
   * java.lang.Object, java.lang.Object)
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectSame(Object caller, A referenceA,
      B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectSameCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.IObjectNotSameCheck#isObjectNotSame(java.lang.
   * Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectNotSame(Object caller, A referenceA,
      B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectNotSameCheck.class);

    return result;
  }

  private Object objectDefault = new Object();

  @Override
  public Object getObjectDefault()
  {
    return this.objectDefault;
  }

  @Override
  public void setObjectDefault(Object defaultObject)
  {
    this.objectDefault = defaultObject;
  }

  @Override
  public <A> boolean isObjectDefault(Object caller, A referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getObjectDefault(),
        IObjectDefaultCheck.class);

    return result;
  }

  @Override
  public <A> boolean isObjectNotDefault(Object caller, A referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getObjectDefault(), IObjectNotDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.IObjectArrayEqualsCheck#isObjectArrayEquals(java
   * .lang.Object , java.lang.Object[], java.lang.Object[])
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectArrayEquals(Object caller,
      A[] referenceA, B[] referenceB)
  {
    boolean result = false;

    result = this.isGenericArrayEqualsImplementation(caller, referenceA, referenceB,
        IObjectArrayEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.IObjectListEqualsCheck#isObjectListEquals(java
   * .lang.Object, java.util.List, java.util.List)
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectListEquals(Object caller,
      List<A> referenceA, List<B> referenceB)
  {
    boolean result = false;

    result = this.isGenericListEqualsImplementation(caller, referenceA, referenceB,
        IObjectListEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.IObjectCollectionEqualsCheck#isObjectCollectionEquals
   * (java .lang.Object, java.util.Collection, java.util.Collection)
   */
  @Override
  public <A extends Object, B extends Object> boolean isObjectCollectionEquals(Object caller,
      Collection<A> referenceA, Collection<B> referenceB)
  {
    boolean result = false;

    result = this.isGenericCollectionEqualsImplementation(caller, referenceA, referenceB,
        IObjectCollectionEqualsCheck.class);

    return result;
  }

  // OBJECT - END

  // OBJECTS - BEGIN

  @Override
  public boolean isObjectsEquals(Object caller, Object[] referenceA, Object[] referenceB)
  {
    boolean result = false;

    result = this.isGenericArrayEqualsImplementation(caller, referenceA, referenceB,
        IObjectsEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isObjectsEquals(Object caller, List<Object> referenceA, List<Object> referenceB)
  {
    boolean result = false;

    result = this.isGenericListEqualsImplementation(caller, referenceA, referenceB,
        IObjectsEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isObjectsEquals(Object caller, Collection<Object> referenceA,
      Collection<Object> referenceB)
  {
    boolean result = false;

    result = this.isGenericCollectionEqualsImplementation(caller, referenceA, referenceB,
        IObjectsEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isObjectsNotEquals(Object caller, Object[] referenceA, Object[] referenceB)
  {
    boolean result = false;

    result = this.isGenericArrayNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectsNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isObjectsNotEquals(Object caller, List<Object> referenceA, List<Object> referenceB)
  {
    boolean result = false;

    result = this.isGenericListNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectsNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isObjectsNotEquals(Object caller, Collection<Object> referenceA,
      Collection<Object> referenceB)
  {
    boolean result = false;

    result = this.isGenericCollectionNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectsNotEqualsCheck.class);

    return result;
  }

  // OBJECTS - END

  // OBJECTS - BOOLEAN - START

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotSameCheck#isBooleanNotSame
   * (java .lang.Object, java.lang.Boolean, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanNotSame(Object caller, Boolean referenceA, Boolean referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectBooleanNotSameCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanSameCheck#isBooleanSame
   * (java.lang .Object, java.lang.Boolean, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanSame(Object caller, Boolean referenceA, Boolean referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectBooleanSameCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanEqualsCheck#isBooleanEquals
   * (java. lang.Object, java.lang.Boolean, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanEquals(Object caller, Boolean referenceA, Boolean referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectBooleanEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotEqualsCheck#
   * isBooleanNotEquals (java.lang.Object, java.lang.Boolean, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanNotEquals(Object caller, Boolean referenceA, Boolean referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectBooleanNotEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck#isBooleanNotNull
   * (java .lang.Object, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanNotNull(Object caller, Boolean referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectBooleanNotNullCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck#isBooleanNull
   * (java.lang .Object, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanNull(Object caller, Boolean referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA,
        IObjectBooleanNullCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanFalseCheck#isBooleanFalse
   * (java.lang .Object, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanFalse(Object caller, Boolean referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }
    {
      if (Boolean.FALSE.equals(referenceA))
      {
        this.pushContractWithCaller(caller, IObjectBooleanFalseCheck.class, new Object[]
        {
            caller, referenceA
        }, new Object[]
        {
            Boolean.FALSE
        });
        result = true;
      }
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanTrueCheck#isBooleanTrue
   * (java.lang .Object, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanTrue(Object caller, Boolean referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }
    {
      if (Boolean.TRUE.equals(referenceA))
      {
        this.pushContractWithCaller(caller, IObjectBooleanTrueCheck.class, new Object[]
        {
            caller, referenceA
        }, new Object[]
        {
            Boolean.TRUE
        });
        result = true;
      }
    }

    return result;
  }

  private Boolean booleanDefault = Boolean.FALSE;

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultProperties#
   * getBooleanDefault()
   */
  @Override
  public Boolean getBooleanDefault()
  {
    return this.booleanDefault;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultProperties#
   * setBooleanDefault (java.lang.Boolean)
   */
  @Override
  public void setBooleanDefault(Boolean defaultBoolean)
  {
    this.booleanDefault = defaultBoolean;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotDefaultCheck#
   * isBooleanNotDefault (java.lang.Object, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanNotDefault(Object caller, Boolean referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getBooleanDefault(), IObjectBooleanNotDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultCheck#isBooleanDefault
   * (java .lang.Object, java.lang.Boolean)
   */
  @Override
  public boolean isBooleanDefault(Object caller, Boolean referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA,
        this.getBooleanDefault(), IObjectBooleanDefaultCheck.class);

    return result;
  }

  // OBJECTS - BOOLEAN - END

  // OBJECTS - BYTE - START

  @Override
  public boolean isByteNotSame(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectByteNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isByteSame(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectByteSameCheck.class);

    return result;
  }

  @Override
  public boolean isByteEquals(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectByteEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isByteNotEquals(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectByteNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isByteNotNull(Object caller, Byte referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectByteNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isByteNull(Object caller, Byte referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectByteNullCheck.class);

    return result;
  }

  private Byte byteDefault = 0;

  @Override
  public Byte getByteDefault()
  {
    return this.byteDefault;
  }

  @Override
  public void setByteDefault(Byte defaultByte)
  {
    this.byteDefault = defaultByte;
  }

  @Override
  public boolean isByteDefault(Object caller, Byte referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getByteDefault(),
        IObjectByteDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isByteNotDefault(Object caller, Byte referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getByteDefault(), IObjectByteNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isByteLess(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectByteLessCheck.class);

    return result;
  }

  @Override
  public boolean isByteLessOrEquals(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectByteLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isByteGreater(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectByteGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isByteGreaterOrEquals(Object caller, Byte referenceA, Byte referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectByteGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isByteOutside(Object caller, Byte referenceA, Byte referenceMin, Byte referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectByteOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isByteInside(Object caller, Byte referenceA, Byte referenceMin, Byte referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectByteInsideCheck.class);

    return result;
  }

  // OBJECTS - BYTE - END

  // OBJECTS - CHARACTER - START

  @Override
  public boolean isCharacterNotSame(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectCharacterNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterSame(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectCharacterSameCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterEquals(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectCharacterEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterNotEquals(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectCharacterNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterNotNull(Object caller, Character referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectCharacterNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterNull(Object caller, Character referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA,
        IObjectCharacterNullCheck.class);

    return result;
  }

  private Character characterDefault = 0;

  @Override
  public Character getCharacterDefault()
  {
    return this.characterDefault;
  }

  @Override
  public void setCharacterDefault(Character defaultCharacter)
  {
    this.characterDefault = defaultCharacter;
  }

  @Override
  public boolean isCharacterDefault(Object caller, Character referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA,
        this.getCharacterDefault(), IObjectCharacterDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterNotDefault(Object caller, Character referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getCharacterDefault(), IObjectCharacterNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterLess(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectCharacterLessCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterLessOrEquals(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectCharacterLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterGreater(Object caller, Character referenceA, Character referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectCharacterGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterGreaterOrEquals(Object caller, Character referenceA,
      Character referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectCharacterGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterOutside(Object caller, Character referenceA, Character referenceMin,
      Character referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectCharacterOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isCharacterInside(Object caller, Character referenceA, Character referenceMin,
      Character referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectCharacterInsideCheck.class);

    return result;
  }

  // OBJECTS - CHARACTER - END

  // OBJECTS - DATE - START

  @Override
  public boolean isDateNotSame(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectDateNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isDateSame(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectDateSameCheck.class);

    return result;
  }

  @Override
  public boolean isDateEquals(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectDateEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isDateNotEquals(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectDateNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isDateNotNull(Object caller, Date referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectDateNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isDateNull(Object caller, Date referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectDateNullCheck.class);

    return result;
  }

  private Date dateDefault = new Date(0);

  @Override
  public Date getDateDefault()
  {
    return this.dateDefault;
  }

  @Override
  public void setDateDefault(Date defaultDate)
  {
    this.dateDefault = defaultDate;
  }

  @Override
  public boolean isDateDefault(Object caller, Date referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getDateDefault(),
        IObjectDateDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isDateNotDefault(Object caller, Date referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getDateDefault(), IObjectDateNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isDateLess(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectDateLessCheck.class);

    return result;
  }

  @Override
  public boolean isDateLessOrEquals(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectDateLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isDateGreater(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectDateGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isDateGreaterOrEquals(Object caller, Date referenceA, Date referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectDateGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isDateOutside(Object caller, Date referenceA, Date referenceMin, Date referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectDateOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isDateInside(Object caller, Date referenceA, Date referenceMin, Date referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectDateInsideCheck.class);

    return result;
  }

  // OBJECTS - DATE - END

  // OBJECTS - DOUBLE - START

  @Override
  public boolean isDoubleNotSame(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectDoubleNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isDoubleSame(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectDoubleSameCheck.class);

    return result;
  }

  @Override
  public boolean isDoubleEquals(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectDoubleEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isDoubleNotEquals(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectDoubleNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isDoubleNotNull(Object caller, Double referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectDoubleNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isDoubleNull(Object caller, Double referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA,
        IObjectDoubleNullCheck.class);

    return result;
  }

  private Double doubleDefault = 0d;

  @Override
  public Double getDoubleDefault()
  {
    return this.doubleDefault;
  }

  @Override
  public void setDoubleDefault(Double defaultDouble)
  {
    this.doubleDefault = defaultDouble;
  }

  @Override
  public boolean isDoubleDefault(Object caller, Double referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getDoubleDefault(),
        IObjectDoubleDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectDoubleNotDefaultCheck#isDoubleNotDefault
   * (java .lang.Object, java.lang.Double)
   */
  @Override
  public boolean isDoubleNotDefault(Object caller, Double referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getDoubleDefault(), IObjectDoubleNotDefaultCheck.class);

    return result;
  }

  private Double doubleEqualsAlmostDefaultAbsoluteEpsilon = 0.00001d;

  @Override
  public Double getDoubleEqualsAlmostDefaultAbsoluteEpsilon()
  {
    return this.doubleEqualsAlmostDefaultAbsoluteEpsilon;
  }

  @Override
  public void setDoubleEqualsAlmostDefaultAbsoluteEpsilon(Double defaultAbsoluteEpsilon)
  {
    this.doubleEqualsAlmostDefaultAbsoluteEpsilon = Math.abs(defaultAbsoluteEpsilon);
  }

  private Double doubleEqualsAlmostDefaultRelativeEpsilon = 0.000001d;

  @Override
  public Double getDoubleEqualsAlmostDefaultRelativeEpsilon()
  {
    return this.doubleEqualsAlmostDefaultRelativeEpsilon;
  }

  @Override
  public void setDoubleEqualsAlmostDefaultRelativeEpsilon(Double defaultRelativeEpsilon)
  {
    this.doubleEqualsAlmostDefaultRelativeEpsilon = Math.abs(defaultRelativeEpsilon);
  }

  @Override
  public boolean isDoubleEqualsAlmost(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    Double defaultAbsoluteEpsilon = this.getDoubleEqualsAlmostDefaultAbsoluteEpsilon();
    Double defaultRelativeEpsilon = this.getDoubleEqualsAlmostDefaultRelativeEpsilon();
    result = this.isDoubleEqualsAlmost(caller, referenceA, referenceB, defaultAbsoluteEpsilon,
        defaultRelativeEpsilon);

    return result;
  }

  @Override
  public boolean isDoubleEqualsAlmost(Object caller, Double referenceA, Double referenceB,
      Double absoluteEpsilon)
  {
    boolean result = false;

    Double defaultRelativeEpsilon = this.getDoubleEqualsAlmostDefaultRelativeEpsilon();
    result = this.isDoubleEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon,
        defaultRelativeEpsilon);

    return result;
  }

  @Override
  public boolean isDoubleEqualsAlmost(Object caller, Double referenceA, Double referenceB,
      Double absoluteEpsilon, Double relativeEpsilon)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    Double lowB = null;
    Double highB = null;
    if (referenceA == referenceB)
    {
      result = true;
    }
    else
    {
      lowB = (1f - Math.signum(referenceA) * relativeEpsilon) * referenceA - absoluteEpsilon;
      highB = (1f + Math.signum(referenceA) * relativeEpsilon) * referenceA + absoluteEpsilon;
      result = (lowB <= referenceB && referenceB <= highB);
    }

    if (result)
    {
      this.pushContractWithCaller(caller, IObjectDoubleEqualsAlmostCheck.class, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          absoluteEpsilon, relativeEpsilon, lowB, highB
      });
    }

    return result;
  }

  @Override
  public boolean isDoubleNotEqualsAlmost(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    Double absoluteEpsilon = this.getDoubleEqualsAlmostDefaultAbsoluteEpsilon();
    Double relativeEpsilon = this.getDoubleEqualsAlmostDefaultRelativeEpsilon();
    result = this.isDoubleNotEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon,
        relativeEpsilon);

    return result;
  }

  @Override
  public boolean isDoubleNotEqualsAlmost(Object caller, Double referenceA, Double referenceB,
      Double absoluteEpsilon)
  {
    boolean result = false;

    Double relativeEpsilon = this.getDoubleEqualsAlmostDefaultRelativeEpsilon();
    result = this.isDoubleNotEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon,
        relativeEpsilon);

    return result;
  }

  @Override
  public boolean isDoubleNotEqualsAlmost(Object caller, Double referenceA, Double referenceB,
      Double absoluteEpsilon, Double relativeEpsilon)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    Double lowB = null;
    Double highB = null;
    if (referenceA != referenceB)
    {
      lowB = (1f - Math.signum(referenceA) * relativeEpsilon) * referenceA - absoluteEpsilon;
      highB = (1f + Math.signum(referenceA) * relativeEpsilon) * referenceA + absoluteEpsilon;
      result = (referenceB < lowB || highB < referenceB);
    }
    // else false

    if (result)
    {
      this.pushContractWithCaller(caller, IObjectDoubleNotEqualsAlmostCheck.class, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          absoluteEpsilon, relativeEpsilon, lowB, highB
      });
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectDoubleLessCheck#isDoubleLess(java
   * .lang.Object, java.lang.Double, java.lang.Double)
   */
  @Override
  public boolean isDoubleLess(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectDoubleLessCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.floats.IObjectDoubleLessOrEqualsCheck#
   * isDoubleLessOrEquals (java.lang.Object, java.lang.Double, java.lang.Double)
   */
  @Override
  public boolean isDoubleLessOrEquals(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectDoubleLessOrEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectDoubleGreaterCheck#isDoubleGreater
   * (java.lang .Object, java.lang.Double, java.lang.Double)
   */
  @Override
  public boolean isDoubleGreater(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectDoubleGreaterCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.floats.IObjectDoubleGreaterOrEqualsCheck#
   * isDoubleGreaterOrEquals (java.lang.Object, java.lang.Double, java.lang.Double)
   */
  @Override
  public boolean isDoubleGreaterOrEquals(Object caller, Double referenceA, Double referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectDoubleGreaterOrEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectDoubleOutsideCheck#isDoubleOutside
   * (java.lang .Object, java.lang.Double, java.lang.Double, java.lang.Double)
   */
  @Override
  public boolean isDoubleOutside(Object caller, Double referenceA, Double referenceMin,
      Double referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectDoubleOutsideCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectDoubleInsideCheck#isDoubleInside
   * (java.lang .Object, java.lang.Double, java.lang.Double, java.lang.Double)
   */
  @Override
  public boolean isDoubleInside(Object caller, Double referenceA, Double referenceMin,
      Double referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectDoubleInsideCheck.class);

    return result;
  }

  // OBJECTS - DOUBLE - END

  // OBJECTS - ENUM - START

  @Override
  public <A extends Enum<A>, B extends Enum<B>> boolean isEnumNotSame(Object caller, A referenceA,
      B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectEnumNotSameCheck.class);

    return result;
  }

  @Override
  public <A extends Enum<A>, B extends Enum<B>> boolean isEnumSame(Object caller, A referenceA,
      B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectEnumSameCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumNotEqualsCheck#isEnumNotEquals
   * (java.lang .Object, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <A extends Enum<A>, B extends Enum<B>> boolean isEnumNotEquals(Object caller,
      A referenceA, B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectEnumNotEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumEqualsCheck#isEnumEquals(java
   * .lang.Object, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <A extends Enum<A>, B extends Enum<B>> boolean isEnumEquals(Object caller, A referenceA,
      B referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectEnumEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumNotNullCheck#isEnumNotNull(java
   * .lang.Object , java.lang.Enum)
   */
  @Override
  public <A extends Enum<A>> boolean isEnumNotNull(Object caller, A referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectEnumNotNullCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumNullCheck#isEnumNull(java.lang
   * .Object, java.lang.Enum)
   */
  @Override
  public <A extends Enum<A>> boolean isEnumNull(Object caller, A referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectEnumNullCheck.class);

    return result;
  }

  @SuppressWarnings("rawtypes")
  private HashMap<Class<? extends Enum>, Enum<?>> enumDefaults = 
      new HashMap<Class<? extends Enum>, Enum<?>>();

  @SuppressWarnings("rawtypes")
  protected HashMap<Class<? extends Enum>, Enum<?>> getEnumDefaults()
  {
    return this.enumDefaults;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultProperties#getEnumDefault
   * (java.lang .Class)
   */
  @Override
  public Enum<?> getEnumDefault(Class<? extends Enum<?>> enumType)
  {
    Enum<?> result = this.getEnumDefaults().get(enumType);
    if (null == result)
    { // cache this to avoid repeated array creations
      Enum<?>[] enumConstants = enumType.getEnumConstants();
      if (null != enumConstants && 0 < enumConstants.length)
      {
        result = enumConstants[0];
        this.getEnumDefaults().put(enumType, result);
      }
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultProperties#setEnumDefault
   * (java.lang .Enum)
   */
  @Override
  public void setEnumDefault(Enum<?> defaultEnum)
  {
    this.getEnumDefaults().put(defaultEnum.getClass(), defaultEnum);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumNotDefaultCheck#isEnumNotDefault
   * (java.lang .Object, java.lang.Enum)
   */
  @Override
  public <A extends Enum<A>> boolean isEnumNotDefault(Object caller, A referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }
    if (null == referenceA) // can't derive Class from null
    {
      throw new IllegalArgumentException("referenceA is null");
    }

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getEnumDefault(referenceA.getDeclaringClass()), IObjectEnumNotDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultCheck#isEnumDefault(java
   * .lang.Object , java.lang.Enum)
   */
  @Override
  public <A extends Enum<A>> boolean isEnumDefault(Object caller, A referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }
    if (null != referenceA) // can't derive Class from null
    {
      // throw new IllegalArgumentException("referenceA is null");
      result = this.isGenericObjectDefaultImplementation(caller, referenceA,
          this.getEnumDefault(referenceA.getDeclaringClass()), IObjectEnumDefaultCheck.class);
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumLessCheck#isEnumLess(java.lang
   * .Object, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <T extends Enum<T>> boolean isEnumLess(Object caller, T referenceA, T referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectEnumLessCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumLessOrEqualsCheck#isEnumLessOrEquals
   * (java .lang.Object, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <T extends Enum<T>> boolean isEnumLessOrEquals(Object caller, T referenceA, T referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectEnumLessOrEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumGreaterCheck#isEnumGreater(java
   * .lang.Object , java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <T extends Enum<T>> boolean isEnumGreater(Object caller, T referenceA, T referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectEnumGreaterCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.enums.IObjectEnumGreaterOrEqualsCheck#
   * isEnumGreaterOrEquals (java.lang.Object, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <T extends Enum<T>> boolean isEnumGreaterOrEquals(
      Object caller, T referenceA, T referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectEnumGreaterOrEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumOutsideCheck#isEnumOutside(java
   * .lang.Object , java.lang.Enum, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <T extends Enum<T>> boolean isEnumOutside(Object caller, T referenceA, T referenceMin,
      T referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectEnumOutsideCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.enums.IObjectEnumInsideCheck#isEnumInside(java
   * .lang.Object, java.lang.Enum, java.lang.Enum, java.lang.Enum)
   */
  @Override
  public <T extends Enum<T>> boolean isEnumInside(Object caller, T referenceA, T referenceMin,
      T referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectEnumInsideCheck.class);

    return result;
  }

  // OBJECTS - ENUM - END

  
  // EXCEPTION - START
  
  /* (non-Javadoc)
   * @see starkcoder.failfast.checks.exceptions.IExceptionCheck#isException(
   *  java.lang.Object, java.lang.Exception)
   */
  @Override
  public <A extends Exception> boolean isException(Object caller, A exception)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }    
    if (null == exception)
    {
      throw new IllegalArgumentException("exception is null");
    }

    {
      result = true;
    }
    {
      this.pushContractWithCaller(caller, IObjectExceptionCheck.class, new Object[]
      {
          caller, exception
      });
    }

    return result;
  }
  
  // EXCEPTION - END

  
  // OBJECTS - FLOAT - START

  @Override
  public boolean isFloatNotSame(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectFloatNotSameCheck.class);

    return result;
  }


  @Override
  public boolean isFloatSame(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectFloatSameCheck.class);

    return result;
  }

  @Override
  public boolean isFloatEquals(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectFloatEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isFloatNotEquals(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectFloatNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isFloatNotNull(Object caller, Float referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectFloatNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isFloatNull(Object caller, Float referenceA)
  {
    boolean result = false;

    result = this
        .isGenericObjectNullImplementation(caller, referenceA, IObjectFloatNullCheck.class);

    return result;
  }

  private Float floatDefault = 0f;

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultProperties#getFloatDefault
   * ()
   */
  @Override
  public Float getFloatDefault()
  {
    return this.floatDefault;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultProperties#setFloatDefault
   * (java .lang.Float)
   */
  @Override
  public void setFloatDefault(Float defaultFloat)
  {
    this.floatDefault = defaultFloat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultCheck#isFloatDefault
   * (java.lang .Object, java.lang.Float)
   */
  @Override
  public boolean isFloatDefault(Object caller, Float referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getFloatDefault(),
        IObjectFloatDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatNotDefaultCheck#isFloatNotDefault
   * (java .lang.Object, java.lang.Float)
   */
  @Override
  public boolean isFloatNotDefault(Object caller, Float referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getFloatDefault(), IObjectFloatNotDefaultCheck.class);

    return result;
  }

  private Float floatEqualsAlmostDefaultAbsoluteEpsilon = 0.00001f;

  @Override
  public Float getFloatEqualsAlmostDefaultAbsoluteEpsilon()
  {
    return this.floatEqualsAlmostDefaultAbsoluteEpsilon;
  }

  @Override
  public void setFloatEqualsAlmostDefaultAbsoluteEpsilon(Float defaultAbsoluteEpsilon)
  {
    this.floatEqualsAlmostDefaultAbsoluteEpsilon = Math.abs(defaultAbsoluteEpsilon);
  }

  private Float floatEqualsAlmostDefaultRelativeEpsilon = 0.000001f;

  @Override
  public Float getFloatEqualsAlmostDefaultRelativeEpsilon()
  {
    return this.floatEqualsAlmostDefaultRelativeEpsilon;
  }

  @Override
  public void setFloatEqualsAlmostDefaultRelativeEpsilon(Float defaultRelativeEpsilon)
  {
    this.floatEqualsAlmostDefaultRelativeEpsilon = Math.abs(defaultRelativeEpsilon);
  }

  @Override
  public boolean isFloatEqualsAlmost(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    Float defaultAbsoluteEpsilon = this.getFloatEqualsAlmostDefaultAbsoluteEpsilon();
    Float defaultRelativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
    result = this.isFloatEqualsAlmost(caller, referenceA, referenceB, defaultAbsoluteEpsilon,
        defaultRelativeEpsilon);

    return result;
  }

  @Override
  public boolean isFloatEqualsAlmost(Object caller, Float referenceA, Float referenceB,
      Float absoluteEpsilon)
  {
    boolean result = false;

    Float defaultRelativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
    result = this.isFloatEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon,
        defaultRelativeEpsilon);

    return result;
  }

  @Override
  public boolean isFloatEqualsAlmost(Object caller, Float referenceA, Float referenceB,
      Float absoluteEpsilon, Float relativeEpsilon)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    Float lowB = null;
    Float highB = null;
    if (referenceA == referenceB)
    {
      result = true;
    }
    else
    {
      lowB = (1f - Math.signum(referenceA) * relativeEpsilon) * referenceA - absoluteEpsilon;
      highB = (1f + Math.signum(referenceA) * relativeEpsilon) * referenceA + absoluteEpsilon;
      result = (lowB <= referenceB && referenceB <= highB);
    }

    if (result)
    {
      this.pushContractWithCaller(caller, IObjectFloatEqualsAlmostCheck.class, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          absoluteEpsilon, relativeEpsilon, lowB, highB
      });
    }

    return result;
  }

  @Override
  public boolean isFloatNotEqualsAlmost(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    Float absoluteEpsilon = this.getFloatEqualsAlmostDefaultAbsoluteEpsilon();
    Float relativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
    result = this.isFloatNotEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon,
        relativeEpsilon);

    return result;
  }

  @Override
  public boolean isFloatNotEqualsAlmost(Object caller, Float referenceA, Float referenceB,
      Float absoluteEpsilon)
  {
    boolean result = false;

    Float relativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
    result = this.isFloatNotEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon,
        relativeEpsilon);

    return result;
  }

  @Override
  public boolean isFloatNotEqualsAlmost(Object caller, Float referenceA, Float referenceB,
      Float absoluteEpsilon, Float relativeEpsilon)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    Float lowB = null;
    Float highB = null;
    if (referenceA != referenceB)
    {
      lowB = (1f - Math.signum(referenceA) * relativeEpsilon) * referenceA - absoluteEpsilon;
      highB = (1f + Math.signum(referenceA) * relativeEpsilon) * referenceA + absoluteEpsilon;
      result = (referenceB < lowB || highB < referenceB);
    }
    // else false

    if (result)
    {
      this.pushContractWithCaller(caller, IObjectFloatNotEqualsAlmostCheck.class, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          absoluteEpsilon, relativeEpsilon, lowB, highB
      });
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatLessCheck#isFloatLess(java.
   * lang.Object, java.lang.Float, java.lang.Float)
   */
  @Override
  public boolean isFloatLess(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectFloatLessCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.floats.IObjectFloatLessOrEqualsCheck#
   * isFloatLessOrEquals (java.lang.Object, java.lang.Float, java.lang.Float)
   */
  @Override
  public boolean isFloatLessOrEquals(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectFloatLessOrEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatGreaterCheck#isFloatGreater
   * (java.lang .Object, java.lang.Float, java.lang.Float)
   */
  @Override
  public boolean isFloatGreater(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectFloatGreaterCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.floats.IObjectFloatGreaterOrEqualsCheck#
   * isFloatGreaterOrEquals (java.lang.Object, java.lang.Float, java.lang.Float)
   */
  @Override
  public boolean isFloatGreaterOrEquals(Object caller, Float referenceA, Float referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectFloatGreaterOrEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatOutsideCheck#isFloatOutside
   * (java.lang .Object, java.lang.Float, java.lang.Float, java.lang.Float)
   */
  @Override
  public boolean isFloatOutside(Object caller, Float referenceA, Float referenceMin,
      Float referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectFloatOutsideCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.floats.IObjectFloatInsideCheck#isFloatInside(java
   * .lang.Object , java.lang.Float, java.lang.Float, java.lang.Float)
   */
  @Override
  public boolean isFloatInside(Object caller, Float referenceA, Float referenceMin,
      Float referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectFloatInsideCheck.class);

    return result;
  }

  // OBJECTS - FLOAT - END

  // OBJECTS - SHORT - START

  @Override
  public boolean isShortNotSame(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectShortNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isShortSame(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectShortSameCheck.class);

    return result;
  }

  @Override
  public boolean isShortEquals(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectShortEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isShortNotEquals(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectShortNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isShortNotNull(Object caller, Short referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectShortNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isShortNull(Object caller, Short referenceA)
  {
    boolean result = false;

    result = this
        .isGenericObjectNullImplementation(caller, referenceA, IObjectShortNullCheck.class);

    return result;
  }

  private Short shortDefault = 0;

  @Override
  public Short getShortDefault()
  {
    return this.shortDefault;
  }

  @Override
  public void setShortDefault(Short defaultShort)
  {
    this.shortDefault = defaultShort;
  }

  @Override
  public boolean isShortDefault(Object caller, Short referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getShortDefault(),
        IObjectShortDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isShortNotDefault(Object caller, Short referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getShortDefault(), IObjectShortNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isShortLess(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectShortLessCheck.class);

    return result;
  }

  @Override
  public boolean isShortLessOrEquals(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectShortLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isShortGreater(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectShortGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isShortGreaterOrEquals(Object caller, Short referenceA, Short referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectShortGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isShortOutside(Object caller, Short referenceA, Short referenceMin,
      Short referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectShortOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isShortInside(Object caller, Short referenceA, Short referenceMin,
      Short referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectShortInsideCheck.class);

    return result;
  }

  // OBJECTS - SHORT - END

  // OBJECTS - INTEGER - START

  @Override
  public boolean isIntegerNotSame(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectIntegerNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerSame(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectIntegerSameCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerEquals(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectIntegerEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerNotEquals(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectIntegerNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerNotNull(Object caller, Integer referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectIntegerNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerNull(Object caller, Integer referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA,
        IObjectIntegerNullCheck.class);

    return result;
  }

  private Integer integerDefault = 0;

  @Override
  public Integer getIntegerDefault()
  {
    return this.integerDefault;
  }

  @Override
  public void setIntegerDefault(Integer defaultInteger)
  {
    this.integerDefault = defaultInteger;
  }

  @Override
  public boolean isIntegerDefault(Object caller, Integer referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA,
        this.getIntegerDefault(), IObjectIntegerDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerNotDefault(Object caller, Integer referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getIntegerDefault(), IObjectIntegerNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerLess(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectIntegerLessCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerLessOrEquals(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectIntegerLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerGreater(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectIntegerGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerGreaterOrEquals(Object caller, Integer referenceA, Integer referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectIntegerGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerOutside(Object caller, Integer referenceA, Integer referenceMin,
      Integer referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectIntegerOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isIntegerInside(Object caller, Integer referenceA, Integer referenceMin,
      Integer referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectIntegerInsideCheck.class);

    return result;
  }

  // OBJECTS - INTEGER - END

  // OBJECTS - LONG - START

  @Override
  public boolean isLongNotSame(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectLongNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isLongSame(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectLongSameCheck.class);

    return result;
  }

  @Override
  public boolean isLongEquals(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectLongEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isLongNotEquals(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectLongNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isLongNotNull(Object caller, Long referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectLongNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isLongNull(Object caller, Long referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectLongNullCheck.class);

    return result;
  }

  private Long longDefault = new Long(0);

  @Override
  public Long getLongDefault()
  {
    return this.longDefault;
  }

  @Override
  public void setLongDefault(Long defaultLong)
  {
    this.longDefault = defaultLong;
  }

  @Override
  public boolean isLongDefault(Object caller, Long referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getLongDefault(),
        IObjectLongDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isLongNotDefault(Object caller, Long referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getLongDefault(), IObjectLongNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isLongLess(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectLongLessCheck.class);

    return result;
  }

  @Override
  public boolean isLongLessOrEquals(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectLongLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isLongGreater(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectLongGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isLongGreaterOrEquals(Object caller, Long referenceA, Long referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectLongGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isLongOutside(Object caller, Long referenceA, Long referenceMin, Long referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectLongOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isLongInside(Object caller, Long referenceA, Long referenceMin, Long referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectLongInsideCheck.class);

    return result;
  }

  // OBJECTS - LONG - END

  // OBJECTS - STRING - START

  @Override
  public boolean isStringNotSame(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectStringNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isStringSame(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectStringSameCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringEqualsCheck#isStringEquals
   * (java.lang .Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringEquals(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectStringEqualsCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringNotEqualsCheck#isStringNotEquals
   * (java .lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringNotEquals(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectStringNotEqualsCheck.class);

    return result;
  }

  private String stringDefault = new String();

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringDefaultProperties#
   * getStringDefault()
   */
  @Override
  public String getStringDefault()
  {
    return this.stringDefault;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringDefaultProperties#
   * setStringDefault( java.lang.String)
   */
  @Override
  public void setStringDefault(String defaultString)
  {
    this.stringDefault = defaultString;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringDefaultCheck#isStringDefault
   * (java.lang .Object, java.lang.String)
   */
  @Override
  public boolean isStringDefault(Object caller, String referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getStringDefault(),
        IObjectStringDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotDefaultCheck#
   * isStringNotDefault( java.lang.Object, java.lang.String)
   */
  @Override
  public boolean isStringNotDefault(Object caller, String referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getStringDefault(), IObjectStringNotDefaultCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringNullCheck#isStringNull(java
   * .lang.Object , java.lang.String)
   */
  @Override
  public boolean isStringNull(Object caller, String referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA,
        IObjectStringNullCheck.class);

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringNotNullCheck#isStringNotNull
   * (java.lang .Object, java.lang.String)
   */
  @Override
  public boolean isStringNotNull(Object caller, String referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectStringNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isStringLess(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectStringLessCheck.class);

    return result;
  }

  @Override
  public boolean isStringLessOrEquals(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectStringLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isStringGreater(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectStringGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isStringGreaterOrEquals(Object caller, String referenceA, String referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectStringGreaterOrEqualsCheck.class);

    return result;
  }

  private static final String stringEmpty = "";

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringEmptyCheck#isStringEmpty(
   * java.lang. Object, java.lang.String)
   */
  @Override
  public boolean isStringEmpty(Object caller, String referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (stringEmpty.equals(referenceA))
    {
      this.pushContractWithCaller(caller, IObjectStringEmptyCheck.class, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          stringEmpty
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringNotEmptyCheck#isStringNotEmpty
   * (java .lang.Object, java.lang.String)
   */
  @Override
  public boolean isStringNotEmpty(Object caller, String referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (!stringEmpty.equals(referenceA))
    {
      this.pushContractWithCaller(caller, IObjectStringNotEmptyCheck.class, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          stringEmpty
      });
      result = true;
    }

    return result;
  }

  @Override
  public boolean isStringNullOrEmpty(Object caller, String referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA || stringEmpty.equals(referenceA))
    {
      this.pushContractWithCaller(caller, IObjectStringNullOrEmptyCheck.class, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          stringEmpty
      });
      result = true;
    }

    return result;
  }

  @Override
  public boolean isStringNotNullAndNotEmpty(Object caller, String referenceA)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && !stringEmpty.equals(referenceA))
    {
      this.pushContractWithCaller(caller, IObjectStringNotNullAndNotEmptyCheck.class, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          stringEmpty
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithPostfixCheck#
   * isStringWithPostfix (java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringWithPostfix(Object caller, String referenceA, String postfix)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != postfix && referenceA.endsWith(postfix))
    {
      this.pushContractWithCaller(caller, IObjectStringWithPostfixCheck.class, new Object[]
      {
          caller, referenceA, postfix
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPostfixCheck#
   * isStringWithoutPostfix (java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringWithoutPostfix(Object caller, String referenceA, String postfix)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA || null == postfix || !referenceA.endsWith(postfix))
    {
      this.pushContractWithCaller(caller, IObjectStringWithoutPostfixCheck.class, new Object[]
      {
          caller, referenceA, postfix
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithSubstringCheck#
   * isStringWithSubstring (java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringWithSubstring(Object caller, String referenceA, String substring)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != substring && 0 <= referenceA.indexOf(substring))
    {
      this.pushContractWithCaller(caller, IObjectStringWithSubstringCheck.class, new Object[]
      {
          caller, referenceA, substring
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutSubstringCheck#
   * isStringWithoutSubstring(java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringWithoutSubstring(Object caller, String referenceA, String substring)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA || null == substring || referenceA.indexOf(substring) < 0)
    {
      this.pushContractWithCaller(caller, IObjectStringWithoutSubstringCheck.class, new Object[]
      {
          caller, referenceA, substring
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPrefixCheck#
   * isStringWithoutPrefix (java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringWithoutPrefix(Object caller, String referenceA, String prefix)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA || null == prefix || !referenceA.startsWith(prefix))
    {
      this.pushContractWithCaller(caller, IObjectStringWithoutPrefixCheck.class, new Object[]
      {
          caller, referenceA, prefix
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithPrefixCheck#
   * isStringWithPrefix( java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringWithPrefix(Object caller, String referenceA, String prefix)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != prefix && referenceA.startsWith(prefix))
    {
      this.pushContractWithCaller(caller, IObjectStringWithPrefixCheck.class, new Object[]
      {
          caller, referenceA, prefix
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotMatchingCheck#
   * isStringNotMatching (java.lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringNotMatching(Object caller, String referenceA, String regex)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA || null == regex || !referenceA.matches(regex))
    {
      this.pushContractWithCaller(caller, IObjectStringNotMatchingCheck.class, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          regex
      });
      result = true;
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * starkcoder.failfast.checks.objects.strings.IObjectStringMatchingCheck#isStringMatching
   * (java .lang.Object, java.lang.String, java.lang.String)
   */
  @Override
  public boolean isStringMatching(Object caller, String referenceA, String regex)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != regex && referenceA.matches(regex))
    {
      this.pushContractWithCaller(caller, IObjectStringMatchingCheck.class, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          regex
      });
      result = true;
    }

    return result;
  }

  // OBJECTS - STRING - END

  // OBJECTS - UUID - START

  @Override
  public boolean isUuidNotSame(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB,
        IObjectUuidNotSameCheck.class);

    return result;
  }

  @Override
  public boolean isUuidSame(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB,
        IObjectUuidSameCheck.class);

    return result;
  }

  @Override
  public boolean isUuidEquals(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB,
        IObjectUuidEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isUuidNotEquals(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB,
        IObjectUuidNotEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isUuidNotNull(Object caller, UUID referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotNullImplementation(caller, referenceA,
        IObjectUuidNotNullCheck.class);

    return result;
  }

  @Override
  public boolean isUuidNull(Object caller, UUID referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectUuidNullCheck.class);

    return result;
  }

  private UUID uuidDefault = new UUID(0, 0);

  @Override
  public UUID getUuidDefault()
  {
    return this.uuidDefault;
  }

  @Override
  public void setUuidDefault(UUID defaultUuid)
  {
    this.uuidDefault = defaultUuid;
  }

  @Override
  public boolean isUuidDefault(Object caller, UUID referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getUuidDefault(),
        IObjectUuidDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isUuidNotDefault(Object caller, UUID referenceA)
  {
    boolean result = false;

    result = this.isGenericObjectNotDefaultImplementation(caller, referenceA,
        this.getUuidDefault(), IObjectUuidNotDefaultCheck.class);

    return result;
  }

  @Override
  public boolean isUuidLess(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB,
        IObjectUuidLessCheck.class);

    return result;
  }

  @Override
  public boolean isUuidLessOrEquals(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectUuidLessOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isUuidGreater(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB,
        IObjectUuidGreaterCheck.class);

    return result;
  }

  @Override
  public boolean isUuidGreaterOrEquals(Object caller, UUID referenceA, UUID referenceB)
  {
    boolean result = false;

    result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB,
        IObjectUuidGreaterOrEqualsCheck.class);

    return result;
  }

  @Override
  public boolean isUuidOutside(Object caller, UUID referenceA, UUID referenceMin, UUID referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectUuidOutsideCheck.class);

    return result;
  }

  @Override
  public boolean isUuidInside(Object caller, UUID referenceA, UUID referenceMin, UUID referenceMax)
  {
    boolean result = false;

    result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin,
        referenceMax, IObjectUuidInsideCheck.class);

    return result;
  }

  // OBJECTS - UUID - END

  /**
   * Default constructor.
   * <p>
   * Remember to set call contractor before usage.
   * </p>
   */
  protected AChecker()
  {
    super();
  }

  /**
   * Recommended constructor receiving required references (manual constructor dependency
   * injection).
   * <p>
   * This is ready for use after this call.
   * </p>
   * 
   * @param callContractor
   *          used by this
   */
  protected AChecker(ICallContractor callContractor)
  {
    super();
    {
      if (null == callContractor)
      {
        throw new IllegalArgumentException("callContractor is null");
      }
      this.setCallContractor(callContractor);
    }
  }

  protected static final Object[] EmptyObjectArray = new Object[] {};

  /**
   * Call this when a check asserts.
   * <p>
   * This starts a contract that is finished with a fail-call.
   * </p>
   * 
   * @param caller
   *          object calling checker
   * @param checkerSpecification
   *          checker identification (interface)
   * @param checkArguments
   *          array with arguments of called check method
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if CallContractor has not been set, or a previous push (per thread) has not
   *           been popped
   */
  protected void pushContractWithCaller(Object caller,
      Class<? extends ICheck> checkerSpecification, Object[] checkArguments)
  {
    this.pushContractWithCaller(caller, checkerSpecification, checkArguments, EmptyObjectArray);
  }

  /**
   * Call this when a check asserts.
   * <p>
   * This starts a contract that is finished with a fail-call.
   * </p>
   * 
   * @param caller
   *          object calling checker
   * @param checkSpecification
   *          checker identification (interface)
   * @param checkArguments
   *          array with arguments of called check method
   * @param checkExtraArguments
   *          array of extra arguments useful in failure message
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if CallContractor has not been set, or a previous push (per thread) has not
   *           been popped
   */
  protected void pushContractWithCaller(Object caller, Class<? extends ICheck> checkSpecification,
      Object[] checkArguments, Object[] checkExtraArguments)
  {
    ICallContractor callContractor = this.getCallContractor();
    if (null == callContractor)
    {
      throw new IllegalStateException("CallContractor must be set before using this checker.");
    }

    ICallContract callContract = this.contructCallContract(caller, this, checkSpecification,
        checkArguments, checkExtraArguments);
    // require a fail call from caller
    callContractor.pushContractWithCaller(callContract);
  }

  protected ICallContract contructCallContract(Object caller, IChecker assertingChecker,
      Class<? extends ICheck> checkSpecification, Object[] checkArguments,
      Object[] checkExtraArguments)
  {
    ICallContract result = null;

    {
      ICallContract callContract = new CallContract();
      {
        callContract.setCaller(caller);
        callContract.setCheckSpecification(checkSpecification);
        callContract.setCheckArguments(checkArguments);
        callContract.setCheckExtraArguments(checkExtraArguments);
        callContract.setAssertingChecker(assertingChecker);
      }
      result = callContract;
    }

    return result;
  }

  protected <A, B> boolean isGenericObjectEqualsImplementation(Object caller, A referenceA,
      B referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceB && referenceA.equals(referenceB)
        && referenceB.equals(referenceA)) // agree to be equals
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericObjectNotEqualsImplementation(Object caller, A referenceA,
      B referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    boolean equals = false;
    if (null == referenceA && null == referenceB)
    {
      equals = true;
    }
    else if (null != referenceA && null != referenceB && referenceA.equals(referenceB)
        && referenceB.equals(referenceA)) // not only a mirror check
    {
      equals = true;
    }
    if (!equals)
    {
      result = true;
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericObjectSameImplementation(Object caller, A referenceA,
      B referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (referenceA == referenceB)
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericObjectNotSameImplementation(Object caller, A referenceA,
      B referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (referenceA != referenceB)
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <A> boolean isGenericObjectDefaultImplementation(Object caller, A referenceA,
      A referenceDefault, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }
    if (referenceDefault.equals(referenceA))
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          referenceDefault
      });
      result = true;
    }

    return result;
  }

  protected <A> boolean isGenericObjectNotDefaultImplementation(Object caller, A referenceA,
      A referenceDefault, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }
    if (!referenceDefault.equals(referenceA))
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA
      }, new Object[]
      {
          referenceDefault
      });
      result = true;
    }

    return result;
  }

  protected <A> boolean isGenericObjectNullImplementation(Object caller, A reference,
      Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == reference)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, reference
      });
      result = true;
    }

    return result;
  }

  protected <A> boolean isGenericObjectNotNullImplementation(Object caller, A reference,
      Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != reference)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, reference
      });
      result = true;
    }

    return result;
  }

  protected <T extends Comparable<T>> boolean isGenericComparableLessImplementation(Object caller,
      T referenceA, T referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceB && referenceA.compareTo(referenceB) < 0)
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <T extends Comparable<T>> boolean isGenericComparableLessOrEqualsImplementation(
      Object caller, T referenceA, T referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceB && referenceA.compareTo(referenceB) <= 0)
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <T extends Comparable<T>> boolean isGenericComparableGreaterImplementation(
      Object caller, T referenceA, T referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != referenceB && 0 < referenceA.compareTo(referenceB))
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <T extends Comparable<T>> boolean isGenericComparableGreaterOrEqualsImplementation(
      Object caller, T referenceA, T referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceB && 0 <= referenceA.compareTo(referenceB))
    {
      result = true;
    }
    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      });
    }

    return result;
  }

  protected <T extends Comparable<T>> boolean isGenericComparableInsideImplementation(
      Object caller, T referenceA, T referenceMin, T referenceMax,
      Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != referenceMin && null != referenceMax)
    {
      if (referenceMin.compareTo(referenceMax) <= 0)
      {
        if (0 <= referenceA.compareTo(referenceMin) && referenceA.compareTo(referenceMax) <= 0)
        {
          result = true;
        }
      }
      else
      { // referenceMin > referenceMax
        if (0 <= referenceA.compareTo(referenceMax) && referenceA.compareTo(referenceMin) <= 0)
        {
          result = true;
        }
      }
    }

    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceMin, referenceMax
      });
    }

    return result;
  }

  protected <T extends Comparable<T>> boolean isGenericComparableOutsideImplementation(
      Object caller, T referenceA, T referenceMin, T referenceMax,
      Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null != referenceA && null != referenceMin && null != referenceMax)
    {
      if (referenceMin.compareTo(referenceMax) <= 0)
      {
        if (referenceA.compareTo(referenceMin) < 0 || 0 < referenceA.compareTo(referenceMax))
        {
          result = true;
        }
      }
      else
      { // referenceMin > referenceMax
        if (referenceA.compareTo(referenceMax) < 0 || 0 < referenceA.compareTo(referenceMin))
        {
          result = true;
        }
      }
    }

    if (result)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceMin, referenceMax
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericListEqualsImplementation(Object caller, List<A> referenceA,
      List<B> referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceA && referenceA.size() == referenceB.size())
    {
      boolean tempResult = true;
      for (int index = 0; index < referenceA.size(); ++index)
      {
        Object elementA = referenceA.get(index);
        Object elementB = referenceB.get(index);
        if (!((null == elementA && null == elementB) 
            || (null != elementA && null != elementB 
                && elementA.equals(elementB) 
                && elementB.equals(elementA)))) // agree to be equals
        {
          tempResult = false;
          break;
        }
      }
      result = tempResult;
    }
    if (result)
    {
      int size = 0;
      String toString = null;
      if (null != referenceA)
      {
        size = referenceA.size();
        String tempString = "[";
        String tempStringEnd = "]";
        for (int up = 0, down = referenceA.size() - 1; up <= down; ++up, --down)
        {
          Object elementA = referenceA.get(up);
          Object elementB = referenceB.get(down);
          if (up == down)
          {
            tempString += (up == 0 ? "" : ", ") + (elementA == null ? "null" : elementA.toString());
            break;
          }
          else if (5 < up)
          {
            tempString += ", ...";
            break;
          }
          else
          {
            tempString += (up == 0 ? "" : ", ") + (elementA == null ? "null" : elementA.toString());
            tempStringEnd = ", " 
                + (elementB == null ? "null" : elementB.toString()) + tempStringEnd;
          }
        }
        toString = tempString + tempStringEnd;
      }

      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          size, toString
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericListNotEqualsImplementation(Object caller, List<A> referenceA,
      List<B> referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean isEquals = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    int index = 0;
    Object elementA = null;
    Object elementB = null;
    if (null == referenceA && null == referenceB)
    {
      isEquals = true;
    }
    else if (null != referenceA && null != referenceA && referenceA.size() == referenceB.size())
    {
      boolean isEqualsTemp = true;
      for (index = 0; index < referenceA.size(); ++index)
      {
        elementA = referenceA.get(index);
        elementB = referenceB.get(index);
        if (!((null == elementA && null == elementB) 
            || (null != elementA && null != elementB 
                && elementA.equals(elementB) 
                && elementB.equals(elementA)))) // agree to be equals
        {
          isEqualsTemp = false;
          break;
        }
      }
      isEquals = isEqualsTemp;
    }
    if (!isEquals)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          index, elementA, elementB
      });
    }

    return !isEquals;
  }

  protected <A, B> boolean isGenericCollectionEqualsImplementation(Object caller,
      Collection<A> referenceA, Collection<B> referenceB,
      Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceA && referenceA.size() == referenceB.size())
    {
      boolean resultTemp = true;
      Iterator<?> iteratorA = referenceA.iterator();
      Iterator<?> iteratorB = referenceB.iterator();
      // int index = -1;
      while (iteratorA.hasNext() && iteratorB.hasNext())
      {
        // ++index;
        Object elementA = iteratorA.next();
        Object elementB = iteratorB.next();
        if (!((null == elementA && null == elementB) 
            || (null != elementA 
                && null != elementB 
                && elementA.equals(elementB) 
                && elementB.equals(elementA)))) // agree to be equals
        {
          resultTemp = false;
          break;
        }
      }
      result = resultTemp;
    }
    if (result)
    {
      int size = 0;
      String toString = null;
      if (null != referenceA)
      {
        int index = 0;
        size = referenceA.size();
        String tempString = "[";
        Iterator<?> iteratorA = referenceA.iterator();
        while (iteratorA.hasNext())
        {
          Object elementA = iteratorA.next();
          if (size <= 10)
          {
            tempString += (index == 0 ? "" : ", ") 
                + (elementA == null ? "null" : elementA.toString());
          }
          else
          {
            if (index < 5)
            {
              tempString += (index == 0 ? "" : ", ") 
                  + (elementA == null ? "null" : elementA.toString());
            }
            else if (index == 5)
            {
              tempString += ", ...";
            }
            else if (size - index < 5)
            {
              tempString += ", " + (elementA == null ? "null" : elementA.toString());
            }
          }
          ++index;
        }
        toString = tempString + "]";
      }

      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          size, toString
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericCollectionNotEqualsImplementation(Object caller,
      Collection<A> referenceA, Collection<B> referenceB,
      Class<? extends ICheck> checkerSpecification)
  {
    boolean isEquals = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    int index = 0;
    Object elementA = null;
    Object elementB = null;
    if (null == referenceA && null == referenceB)
    {
      isEquals = true;
    }
    else if (null != referenceA && null != referenceA && referenceA.size() == referenceB.size())
    {
      boolean isEqualsTemp = true;
      Iterator<?> iteratorA = referenceA.iterator();
      Iterator<?> iteratorB = referenceB.iterator();
      // int index = -1;
      while (iteratorA.hasNext() && iteratorB.hasNext())
      {
        elementA = iteratorA.next();
        elementB = iteratorB.next();
        if (!((null == elementA && null == elementB) 
            || (null != elementA && null != elementB 
                && elementA.equals(elementB) && elementB.equals(elementA)))) // agree to be equals
        {
          isEqualsTemp = false;
          break;
        }
        ++index;
      }
      isEquals = isEqualsTemp;
    }
    if (!isEquals)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          index, elementA, elementB
      });
    }

    return !isEquals;
  }

  protected <A, B> boolean isGenericArrayEqualsImplementation(Object caller, A[] referenceA,
      B[] referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean result = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    if (null == referenceA && null == referenceB)
    {
      result = true;
    }
    else if (null != referenceA && null != referenceA && referenceA.length == referenceB.length)
    {
      boolean resultTemp = true;
      for (int index = 0; index < referenceA.length; ++index)
      {
        Object elementA = referenceA[index];
        Object elementB = referenceB[index];
        if (!((null == elementA && null == elementB) 
            || (null != elementA && null != elementB 
            && elementA.equals(elementB) && elementB.equals(elementA)))) // agree to be equals
        {
          resultTemp = false;
          break;
        }
      }
      result = resultTemp;
    }
    if (result)
    {
      int size = 0;
      String toString = null;
      if (null != referenceA)
      {
        size = referenceA.length;
        String tempString = "[";
        String tempStringEnd = "]";
        for (int up = 0, down = referenceA.length - 1; up <= down; ++up, --down)
        {
          Object elementA = referenceA[up];
          Object elementB = referenceB[down];
          if (up == down)
          {
            tempString += (up == 0 ? "" : ", ") + (elementA == null ? "null" : elementA.toString());
            break;
          }
          else if (5 < up)
          {
            tempString += ", ..., ";
            break;
          }
          else
          {
            tempString += (up == 0 ? "" : ", ") + (elementA == null ? "null" : elementA.toString());
            tempStringEnd = ", " 
                + (elementB == null ? "null" : elementB.toString()) + tempStringEnd;
          }
        }
        toString = tempString + tempStringEnd;
      }

      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          size, toString
      });
    }

    return result;
  }

  protected <A, B> boolean isGenericArrayNotEqualsImplementation(Object caller, A[] referenceA,
      B[] referenceB, Class<? extends ICheck> checkerSpecification)
  {
    boolean isEquals = false;

    if (null == caller)
    {
      throw new IllegalArgumentException("caller is null");
    }

    int index = 0;
    Object elementA = null;
    Object elementB = null;
    if (null == referenceA && null == referenceB)
    {
      isEquals = true;
    }
    else if (null != referenceA && null != referenceA && referenceA.length == referenceB.length)
    {
      boolean isEqualsTemp = true;
      for (index = 0; index < referenceA.length; ++index)
      {
        elementA = referenceA[index];
        elementB = referenceB[index];
        if (!((null == elementA && null == elementB) 
            || (null != elementA && null != elementB 
                && elementA.equals(elementB) && elementB.equals(elementA)))) // agree to be equals
        {
          isEqualsTemp = false;
          break;
        }
      }
      isEquals = isEqualsTemp;
    }
    if (!isEquals)
    {
      this.pushContractWithCaller(caller, checkerSpecification, new Object[]
      {
          caller, referenceA, referenceB
      }, new Object[]
      {
          index, elementA, elementB
      });
    }

    return !isEquals;
  }

}
