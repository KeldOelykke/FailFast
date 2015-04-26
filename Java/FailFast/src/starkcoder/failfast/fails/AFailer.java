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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.Locale;

import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.objects.IObjectArrayEqualsFail;
import starkcoder.failfast.fails.objects.IObjectCollectionEqualsFail;
import starkcoder.failfast.fails.objects.IObjectDefaultFail;
import starkcoder.failfast.fails.objects.IObjectEqualsFail;
import starkcoder.failfast.fails.objects.IObjectListEqualsFail;
import starkcoder.failfast.fails.objects.IObjectNotDefaultFail;
import starkcoder.failfast.fails.objects.IObjectNotEqualsFail;
import starkcoder.failfast.fails.objects.IObjectNotNullFail;
import starkcoder.failfast.fails.objects.IObjectNotSameFail;
import starkcoder.failfast.fails.objects.IObjectNullFail;
import starkcoder.failfast.fails.objects.IObjectSameFail;
import starkcoder.failfast.fails.objects.IObjectsEqualsFail;
import starkcoder.failfast.fails.objects.IObjectsNotEqualsFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanDefaultFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanEqualsFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanFalseFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotDefaultFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotEqualsFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotNullFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotSameFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNullFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanSameFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanTrueFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteDefaultFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteEqualsFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteGreaterFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteInsideFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteLessFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteLessOrEqualsFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteNotDefaultFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteNotEqualsFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteNotNullFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteNotSameFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteNullFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteOutsideFail;
import starkcoder.failfast.fails.objects.bytes.IObjectByteSameFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterDefaultFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterEqualsFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterGreaterFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterInsideFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterLessFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterLessOrEqualsFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterNotDefaultFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterNotEqualsFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterNotNullFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterNotSameFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterNullFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterOutsideFail;
import starkcoder.failfast.fails.objects.characters.IObjectCharacterSameFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateDefaultFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateEqualsFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateGreaterFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateInsideFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateLessFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateLessOrEqualsFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateNotDefaultFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateNotEqualsFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateNotNullFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateNotSameFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateNullFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateOutsideFail;
import starkcoder.failfast.fails.objects.dates.IObjectDateSameFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleDefaultFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleEqualsAlmostFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleEqualsFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleGreaterFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleInsideFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleLessFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleLessOrEqualsFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleNotDefaultFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleNotEqualsAlmostFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleNotEqualsFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleNotNullFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleNotSameFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleNullFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleOutsideFail;
import starkcoder.failfast.fails.objects.doubles.IObjectDoubleSameFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumDefaultFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumGreaterFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumInsideFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumLessFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumLessOrEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotDefaultFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotNullFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotSameFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNullFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumOutsideFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumSameFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatDefaultFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsAlmostFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatGreaterFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatLessFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatLessOrEqualsFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNotDefaultFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsAlmostFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNotNullFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNotSameFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatNullFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatOutsideFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatSameFail;
import starkcoder.failfast.fails.objects.floats.IObjectFloatInsideFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerDefaultFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerEqualsFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerGreaterFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerInsideFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerLessFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerLessOrEqualsFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerNotDefaultFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerNotEqualsFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerNotNullFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerNotSameFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerNullFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerOutsideFail;
import starkcoder.failfast.fails.objects.integers.IObjectIntegerSameFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongDefaultFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongEqualsFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongGreaterFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongInsideFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongLessFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongLessOrEqualsFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongNotDefaultFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongNotEqualsFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongNotNullFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongNotSameFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongNullFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongOutsideFail;
import starkcoder.failfast.fails.objects.longs.IObjectLongSameFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortDefaultFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortEqualsFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortGreaterFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortInsideFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortLessFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortLessOrEqualsFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortNotDefaultFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortNotEqualsFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortNotNullFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortNotSameFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortNullFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortOutsideFail;
import starkcoder.failfast.fails.objects.shorts.IObjectShortSameFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringDefaultFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringEmptyFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringEqualsFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringGreaterFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringLessFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringLessOrEqualsFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringMatchingFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotDefaultFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotEmptyFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotEqualsFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotMatchingFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotNullAndNotEmptyFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotNullFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNotSameFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNullFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNullOrEmptyFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringSameFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithPostfixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithPrefixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithSubstringFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPostfixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPrefixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithoutSubstringFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidDefaultFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidEqualsFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidGreaterFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidGreaterOrEqualsFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidInsideFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidLessFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidLessOrEqualsFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidNotDefaultFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidNotEqualsFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidNotNullFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidNotSameFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidNullFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidOutsideFail;
import starkcoder.failfast.fails.objects.uuids.IObjectUuidSameFail;

/**
 * Abstract implementation of {@link IFailer}.
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * @author Keld Oelykke
 */
public abstract class AFailer implements IFailer
{

	private ICallContractor callContractor;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * starkcoder.failfast.contractors.ICallContractorReference#getCallContractor
	 * ()
	 */
	@Override
	public ICallContractor getCallContractor()
	{
		return this.callContractor;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * starkcoder.failfast.contractors.ICallContractorReference#setCallContractor
	 * (starkcoder.failfast.contractors.ICallContractor)
	 */
	@Override
	public void setCallContractor(ICallContractor callContractor)
	{
		this.callContractor = callContractor;
	}

	private IFailFastException failFastExceptionOrNull;
	/*
	 * (non-Javadoc)
	 * 
	 * @see starkcoder.failfast.fails.IFailFastExceptionReference#
	 * getFailFastExceptionOrNull()
	 */
	@Override
	public IFailFastException getFailFastExceptionOrNull()
	{
		return this.failFastExceptionOrNull;
	}
	protected void setFailFastExceptionOrNull(
			IFailFastException failFastExceptionOrNull)
	{
		this.failFastExceptionOrNull = failFastExceptionOrNull;
	}

	
//	// GENERIC OBJECT - START -------------------------------
//
//	
//	@Override
//	public void failGenericObjectNull(Object caller, String referenceName)
//	{
//		this.Throw(caller, IGenericObjectNullFail.class, new Object[] { caller, referenceName });
//	}
//	@Override
//	public void failGenericObjectNull(Object caller, String referenceName,
//			String message)
//	{
//		this.Throw(caller, IGenericObjectNullFail.class, new Object[] { caller, referenceName, message });
//	}
//	
//	@Override
//	public void failGenericObjectNotNull(Object caller, String referenceName)
//	{
//		this.Throw(caller, IGenericObjectNotNullFail.class, new Object[] { caller, referenceName });
//	}
//	@Override
//	public void failGenericObjectNotNull(Object caller, String referenceName,
//			String message)
//	{
//		this.Throw(caller, IGenericObjectNotNullFail.class, new Object[] { caller, referenceName, message });
//	}
//	
//	@Override
//	public void failGenericObjectSame(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericObjectSameFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericObjectSame(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericObjectSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	
//	@Override
//	public void failGenericObjectNotSame(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericObjectNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericObjectNotSame(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericObjectNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//
//	
//	@Override
//	public void failGenericObjectEquals(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericObjectEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericObjectEquals(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericObjectEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//
//	@Override
//	public void failGenericObjectNotEquals(Object caller,
//			String referenceAName, String referenceBName)
//	{
//		this.Throw(caller, IGenericObjectNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericObjectNotEquals(Object caller,
//			String referenceAName, String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericObjectNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	
//	// GENERIC OBJECT - END -------------------------------
//
//
//	// GENERIC ARRAY - START -------------------------------
//
//	@Override
//	public void failGenericArrayEquals(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericArrayEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericArrayEquals(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericArrayEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	@Override
//	public void failGenericArrayNotEquals(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericArrayNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericArrayNotEquals(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericArrayNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	
//	// GENERIC ARRAY - END -------------------------------
//
//	
//	// GENERIC COLLECTION - START -------------------------------
//	
//	@Override
//	public void failGenericCollectionEquals(Object caller,
//			String referenceAName, String referenceBName)
//	{
//		this.Throw(caller, IGenericCollectionEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericCollectionEquals(Object caller,
//			String referenceAName, String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericCollectionEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	@Override
//	public void failGenericCollectionNotEquals(Object caller,
//			String referenceAName, String referenceBName)
//	{
//		this.Throw(caller, IGenericCollectionNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericCollectionNotEquals(Object caller,
//			String referenceAName, String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericCollectionNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	
//	// GENERIC COLLECTION - END -------------------------------
//	
//
//	
//	// GENERIC LIST - START -------------------------------
//	
//	@Override
//	public void failGenericListEquals(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericListEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericListEquals(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericListEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	
//	@Override
//	public void failGenericListNotEquals(Object caller, String referenceAName,
//			String referenceBName)
//	{
//		this.Throw(caller, IGenericListNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
//	}
//	@Override
//	public void failGenericListNotEquals(Object caller, String referenceAName,
//			String referenceBName, String message)
//	{
//		this.Throw(caller, IGenericListNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
//	}
//	
//	// GENERIC LIST - END -------------------------------
	
	
	
	// OBJECTS - START -------------------------------
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNullFail#failObjectNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failObjectNull(Object caller, String referenceName)
	{
		this.Throw(caller, IObjectNullFail.class, new Object[] { caller, referenceName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNullFail#failObjectNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectNull(Object caller, String referenceName,
			String message)
	{
		this.Throw(caller, IObjectNullFail.class, new Object[] { caller, referenceName, message });
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNotNullFail#failObjectNotNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failObjectNotNull(Object caller, String referenceName)
	{
		this.Throw(caller, IObjectNotNullFail.class, new Object[] { caller, referenceName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNotNullFail#failObjectNotNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectNotNull(Object caller, String referenceName,
			String message)
	{
		this.Throw(caller, IObjectNotNullFail.class, new Object[] { caller, referenceName, message });
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectEqualsFail#failObjectEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectEqualsFail#failObjectEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNotEqualsFail#failObjectNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNotEqualsFail#failObjectNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}

	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectSameFail#failObjectSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectSameFail#failObjectSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNotSameFail#failObjectNotSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectNotSameFail#failObjectNotSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failObjectDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failObjectDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failObjectNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failObjectNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectArrayEqualsFail#failObjectArrayEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectArrayEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectArrayEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectArrayEqualsFail#failObjectArrayEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectArrayEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectArrayEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectListEqualsFail#failObjectListEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectListEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectListEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectListEqualsFail#failObjectListEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectListEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectListEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectCollectionEqualsFail#failObjectCollectionEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectCollectionEquals(Object caller,
			String referenceAName, String referenceBName)
	{
		this.Throw(caller, IObjectCollectionEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.IObjectCollectionEqualsFail#failObjectCollectionEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failObjectCollectionEquals(Object caller,
			String referenceAName, String referenceBName, String message)
	{
		this.Throw(caller, IObjectCollectionEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	
	// OBJECT - END ---------------------------------
	
	
	// OBJECTS - BEGIN ---------------------------------
	
	@Override
	public void failObjectsEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectsEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failObjectsEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectsEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failObjectsNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectsNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failObjectsNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectsNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	
	// OBJECTS - END ---------------------------------

	
	
	// OBJECTS - BOOLEAN - START -------------------------------


	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotSameFail#failBooleanNotSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectBooleanNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotSameFail#failBooleanNotSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectBooleanNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanSameFail#failBooleanSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectBooleanSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanSameFail#failBooleanSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectBooleanSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotEqualsFail#failBooleanNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectBooleanNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotEqualsFail#failBooleanNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectBooleanNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanEqualsFail#failBooleanEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectBooleanEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanEqualsFail#failBooleanEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectBooleanEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotNullFail#failBooleanNotNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failBooleanNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectBooleanNotNullFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotNullFail#failBooleanNotNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectBooleanNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNullFail#failBooleanNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failBooleanNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectBooleanNullFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanNullFail#failBooleanNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectBooleanNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanFalseFail#failBooleanFalse(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failBooleanFalse(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectBooleanFalseFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.booleans.IObjectBooleanFalseFail#failBooleanFalse(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanFalse(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectBooleanFalseFail.class, new Object[] { caller, referenceAName, message });
	}	

	@Override
	public void failBooleanTrue(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectBooleanTrueFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failBooleanTrue(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectBooleanTrueFail.class, new Object[] { caller, referenceAName, message });
	}	
	
	
	
	@Override
	public void failBooleanDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectBooleanDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failBooleanDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectBooleanDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failBooleanNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectBooleanNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failBooleanNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectBooleanNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	
	// OBJECTS - BOOLEAN - END ---------------------------------
	
	
	// OBJECTS - BYTE - START ---------------------------------
	
	
	@Override
	public void failByteNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failByteSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failByteNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failByteEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failByteNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectByteNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failByteNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectByteNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failByteNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectByteNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failByteNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectByteNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failByteDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectByteDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failByteDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectByteDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failByteNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectByteNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failByteNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectByteNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failByteGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failByteGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failByteLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failByteLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectByteLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failByteLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectByteLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failByteOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectByteOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failByteOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectByteOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failByteInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectByteInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failByteInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectByteInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - BYTE - END ---------------------------------
	
	
	
	// OBJECTS - CHARACTER - START ---------------------------------
	
	
	@Override
	public void failCharacterNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failCharacterSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failCharacterNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failCharacterEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failCharacterNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectCharacterNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failCharacterNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectCharacterNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failCharacterNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectCharacterNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failCharacterNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectCharacterNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failCharacterDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectCharacterDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failCharacterDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectCharacterDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failCharacterNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectCharacterNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failCharacterNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectCharacterNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failCharacterGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failCharacterGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failCharacterLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failCharacterLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectCharacterLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failCharacterLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectCharacterLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failCharacterOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectCharacterOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failCharacterOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectCharacterOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failCharacterInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectCharacterInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failCharacterInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectCharacterInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - CHARACTER - END ---------------------------------
	
	
	// OBJECTS - DATE - START ---------------------------------
	
	
	@Override
	public void failDateNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failDateSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDateNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failDateEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDateNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDateNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDateNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDateNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDateNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDateNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDateNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDateNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDateDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDateDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDateDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDateDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failDateNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDateNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDateNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDateNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDateGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDateGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDateLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDateLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDateLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDateLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDateLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDateOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDateOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDateOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDateOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDateInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDateInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDateInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDateInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - DATE - END ---------------------------------
	

	
	// OBJECTS - DOUBLE - START ---------------------------------
	
	
	@Override
	public void failDoubleNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failDoubleSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failDoubleEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDoubleNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDoubleNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDoubleNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDoubleNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDoubleNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDoubleNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDoubleNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDoubleDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDoubleDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDoubleDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDoubleDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failDoubleNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDoubleNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDoubleNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDoubleNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDoubleEqualsAlmost(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleEqualsAlmost(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleNotEqualsAlmost(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleNotEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleNotEqualsAlmost(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleNotEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}	
	
	@Override
	public void failDoubleGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectDoubleLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failDoubleLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectDoubleLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failDoubleOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDoubleOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDoubleOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDoubleOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failDoubleInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectDoubleInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failDoubleInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectDoubleInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - DOUBLE - END ---------------------------------

	
	
	// OBJECTS - ENUM - START ---------------------------------
	

	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumSameFail#failEnumSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumSameFail#failEnumSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotSameFail#failEnumNotSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotSameFail#failEnumNotSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
		
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumEqualsFail#failEnumEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumEqualsFail#failEnumEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotEqualsFail#failEnumNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotEqualsFail#failEnumNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotNullFail#failEnumNotNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failEnumNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectEnumNotNullFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotNullFail#failEnumNotNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectEnumNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNullFail#failEnumNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failEnumNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectEnumNullFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNullFail#failEnumNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectEnumNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumDefaultFail#failEnumDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failEnumDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectEnumDefaultFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumDefaultFail#failEnumDefault(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectEnumDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotDefaultFail#failEnumNotDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failEnumNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectEnumNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.enums.IObjectEnumNotDefaultFail#failEnumNotDefault(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failEnumNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectEnumNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failEnumGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failEnumGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failEnumGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failEnumGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failEnumLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failEnumLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failEnumLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectEnumLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failEnumLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectEnumLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failEnumOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectEnumOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failEnumOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectEnumOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failEnumInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectEnumInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failEnumInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectEnumInsideFail.class, new Object[] { caller, referenceAName, message });
	}	
	
	// OBJECTS - ENUM - END ---------------------------------
	

	
	// OBJECTS - FLOAT - START ---------------------------------
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectDoubleNotSameFail#failFloatNotSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotSameFail#failFloatNotSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatSameFail#failFloatSame(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatSameFail#failFloatSame(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsFail#failFloatNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsFail#failFloatNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsFail#failFloatEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsFail#failFloatEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotNullFail#failFloatNotNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failFloatNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectFloatNotNullFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotNullFail#failFloatNotNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectFloatNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNullFail#failFloatNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failFloatNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectFloatNullFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNullFail#failFloatNull(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectFloatNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatDefaultFail#failFloatDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failFloatDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectFloatDefaultFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatDefaultFail#failFloatDefault(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectFloatDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotDefaultFail#failFloatNotDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failFloatNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectFloatNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotDefaultFail#failFloatNotDefault(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectFloatNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsAlmostFail#failFloatEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatEqualsAlmost(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatEqualsAlmostFail#failFloatEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatEqualsAlmost(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsAlmostFail#failFloatNotEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotEqualsAlmost(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatNotEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotEqualsAlmostFail#failFloatNotEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatNotEqualsAlmost(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatNotEqualsAlmostFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatGreaterFail#failFloatGreater(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatGreaterFail#failFloatGreater(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatGreaterOrEqualsFail#failFloatGreaterOrEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatGreaterOrEqualsFail#failFloatGreaterOrEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatLessFail#failFloatLess(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatLessFail#failFloatLess(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatLessOrEqualsFail#failFloatLessOrEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectFloatLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatLessOrEqualsFail#failFloatLessOrEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectFloatLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatOutsideFail#failFloatOutside(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failFloatOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectFloatOutsideFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatOutsideFail#failFloatOutside(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectFloatOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatWithinFail#failFloatWithin(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failFloatInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectFloatInsideFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatWithinFail#failFloatWithin(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectFloatInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - FLOAT - END ---------------------------------



	// OBJECTS - SHORT - START ---------------------------------
	
	
	@Override
	public void failShortNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failShortSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failShortNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failShortEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failShortNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectShortNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failShortNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectShortNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failShortNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectShortNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failShortNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectShortNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failShortDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectShortDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failShortDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectShortDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failShortNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectShortNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failShortNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectShortNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failShortGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failShortGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failShortLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failShortLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectShortLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failShortLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectShortLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failShortOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectShortOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failShortOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectShortOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failShortInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectShortInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failShortInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectShortInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - SHORT - END ---------------------------------
	



	// OBJECTS - INTEGER - START ---------------------------------
	
	
	@Override
	public void failIntegerNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failIntegerSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failIntegerNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failIntegerEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failIntegerNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectIntegerNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failIntegerNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectIntegerNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failIntegerNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectIntegerNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failIntegerNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectIntegerNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failIntegerDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectIntegerDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failIntegerDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectIntegerDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failIntegerNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectIntegerNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failIntegerNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectIntegerNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failIntegerGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failIntegerGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failIntegerLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failIntegerLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectIntegerLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failIntegerLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectIntegerLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failIntegerOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectIntegerOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failIntegerOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectIntegerOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failIntegerInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectIntegerInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failIntegerInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectIntegerInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - INTEGER - END ---------------------------------
	

	
	
	// OBJECTS - LONG - START ---------------------------------
	
	@Override
	public void failLongNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failLongSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failLongNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failLongEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failLongNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectLongNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failLongNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectLongNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failLongNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectLongNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failLongNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectLongNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failLongDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectLongDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failLongDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectLongDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failLongNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectLongNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failLongNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectLongNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failLongGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failLongGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failLongLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failLongLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectLongLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failLongLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectLongLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failLongOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectLongOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failLongOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectLongOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failLongInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectLongInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failLongInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectLongInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - LONG - END ---------------------------------
	
	
	
	
	// OBJECTS - STRING - START ---------------------------------
	

	@Override
	public void failStringNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failStringSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failStringEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	
	@Override
	public void failStringNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}	
	
	@Override
	public void failStringNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringNullFail.class, new Object[] { caller, referenceAName });
	}

	@Override
	public void failStringNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	
	@Override
	public void failStringNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failStringNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringNotNullFail.class, new Object[] { caller, referenceAName, message });
	}	
	

	
	@Override
	public void failStringDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failStringDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	

	@Override
	public void failStringNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failStringNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	
	@Override
	public void failStringGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failStringGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failStringLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failStringLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failStringLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}

	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringEmptyFail#failStringEmpty(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringEmpty(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringEmptyFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringEmptyFail#failStringEmpty(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringEmpty(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringEmptyFail.class, new Object[] { caller, referenceAName, message });
	}


	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotEmptyFail#failStringNotEmpty(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringNotEmpty(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringNotEmptyFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotEmptyFail#failStringNotEmpty(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringNotEmpty(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringNotEmptyFail.class, new Object[] { caller, referenceAName, message });
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotNullAndNotEmptyFail#failStringNotNullAndNotEmpty(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringNotNullAndNotEmpty(Object caller,
			String referenceAName)
	{
		this.Throw(caller, IObjectStringNotNullAndNotEmptyFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotNullAndNotEmptyFail#failStringNotNullAndNotEmpty(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringNotNullAndNotEmpty(Object caller,
			String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringNotNullAndNotEmptyFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNullOrEmptyFail#failStringNullOrEmpty(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringNullOrEmpty(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringNullOrEmptyFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNullOrEmptyFail#failStringNullOrEmpty(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringNullOrEmpty(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectStringNullOrEmptyFail.class, new Object[] { caller, referenceAName, message });
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPrefixFail#failStringWithPrefix(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringWithPrefix(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringWithPrefixFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPrefixFail#failStringWithPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithPrefix(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringWithPrefixFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPrefixFail#failStringWithoutPrefix(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringWithoutPrefix(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringWithoutPrefixFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPrefixFail#failStringWithoutPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutPrefix(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringWithoutPrefixFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPostfixFail#failStringWithoutPostfix(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringWithoutPostfix(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringWithoutPostfixFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPostfixFail#failStringWithoutPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutPostfix(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringWithoutPostfixFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithSubstringFail#failStringWithSubstring(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringWithSubstring(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringWithSubstringFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithSubstringFail#failStringWithSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithSubstring(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringWithSubstringFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPostfixFail#failStringWithPostfix(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringWithPostfix(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringWithPostfixFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPostfixFail#failStringWithPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithPostfix(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringWithPostfixFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutSubstringFail#failStringWithoutSubstring(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringWithoutSubstring(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringWithoutSubstringFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutSubstringFail#failStringWithoutSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutSubstring(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringWithoutSubstringFail.class, new Object[] { caller, referenceAName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringMatchingFail#failStringMatching(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringMatching(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringMatchingFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringMatchingFail#failStringMatching(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringMatching(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringMatchingFail.class, new Object[] { caller, referenceAName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotMatchingFail#failStringNotMatching(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failStringNotMatching(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectStringNotMatchingFail.class, new Object[] { caller, referenceAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotMatchingFail#failStringNotMatching(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringNotMatching(Object caller, String referenceAName, String message)
	{
		this.Throw(caller, IObjectStringNotMatchingFail.class, new Object[] { caller, referenceAName, message });
	}
	
	
	// OBJECTS - STRING - END ---------------------------------
	
	


	// OBJECTS - UUID - START ---------------------------------
	
	
	@Override
	public void failUuidNotSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidNotSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidNotSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidNotSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failUuidSame(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidSameFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidSame(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidSameFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failUuidNotEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidNotEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidNotEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	@Override
	public void failUuidEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failUuidNotNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectUuidNotNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failUuidNotNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectUuidNotNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failUuidNull(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectUuidNullFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failUuidNull(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectUuidNullFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failUuidDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectUuidDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failUuidDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectUuidDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	@Override
	public void failUuidNotDefault(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectUuidNotDefaultFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failUuidNotDefault(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectUuidNotDefaultFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failUuidGreater(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidGreaterFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidGreater(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidGreaterFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failUuidGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidGreaterOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidGreaterOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failUuidLess(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidLessFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidLess(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidLessFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failUuidLessOrEquals(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectUuidLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	@Override
	public void failUuidLessOrEquals(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectUuidLessOrEqualsFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	@Override
	public void failUuidOutside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectUuidOutsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failUuidOutside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectUuidOutsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	@Override
	public void failUuidInside(Object caller, String referenceAName)
	{
		this.Throw(caller, IObjectUuidInsideFail.class, new Object[] { caller, referenceAName });
	}
	@Override
	public void failUuidInside(Object caller, String referenceAName,
			String message)
	{
		this.Throw(caller, IObjectUuidInsideFail.class, new Object[] { caller, referenceAName, message });
	}
	
	// OBJECTS - UUID - END ---------------------------------
	

	
	
	
	
//	// PRIMITIVES - BOOLEAN - START -------------------------------
//
//	
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanNotEqualsFail#failBooleanValueNotEquals(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueNotEquals(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveBooleanNotEqualsFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanNotEqualsFail#failBooleanValueNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueNotEquals(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveBooleanNotEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanEqualsFail#failBooleanValueEquals(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueEquals(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveBooleanEqualsFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanEqualsFail#failBooleanValueEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueEquals(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveBooleanEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanFalseFail#failBooleanValueFalse(java.lang.Object, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueFalse(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveBooleanFalseFail.class, new Object[] { caller, valueAName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanFalseFail#failBooleanValueFalse(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueFalse(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveBooleanFalseFail.class, new Object[] { caller, valueAName, message });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanTrueFail#failBooleanValueTrue(java.lang.Object, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueTrue(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveBooleanTrueFail.class, new Object[] { caller, valueAName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanTrueFail#failBooleanValueTrue(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failBooleanValueTrue(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveBooleanTrueFail.class, new Object[] { caller, valueAName, message });
//	}
//	
//	@Override
//	public void failBooleanValueDefault(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveBooleanDefaultFail.class, new Object[] { caller, valueAName });
//	}
//	@Override
//	public void failBooleanValueDefault(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveBooleanDefaultFail.class, new Object[] { caller, valueAName, message });
//	}
//	@Override
//	public void failBooleanValueNotDefault(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveBooleanNotDefaultFail.class, new Object[] { caller, valueAName });
//	}
//	@Override
//	public void failBooleanValueNotDefault(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveBooleanNotDefaultFail.class, new Object[] { caller, valueAName, message });
//	}
//	
//	
//	// PRIMITIVES - BOOLEAN - END ---------------------------------
	
	
//	// PRIMITIVES - FLOAT - START -------------------------------
//
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsFail#failFloatValueEquals(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueEquals(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatEqualsFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsFail#failFloatValueEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueEquals(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail#failFloatValueNotEquals(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueNotEquals(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatNotEqualsFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail#failFloatValueNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueNotEquals(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatNotEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	@Override
//	public void failFloatValueDefault(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveFloatDefaultFail.class, new Object[] { caller, valueAName });
//	}
//	@Override
//	public void failFloatValueDefault(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveFloatDefaultFail.class, new Object[] { caller, valueAName, message });
//	}
//	
//	@Override
//	public void failFloatValueNotDefault(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveFloatNotDefaultFail.class, new Object[] { caller, valueAName });
//	}
//	@Override
//	public void failFloatValueNotDefault(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveFloatNotDefaultFail.class, new Object[] { caller, valueAName, message });
//	}
//	
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail#failFloatValueEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueEqualsAlmost(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail#failFloatValueEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueEqualsAlmost(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail#failFloatValueNotEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueNotEqualsAlmost(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatNotEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail#failFloatValueNotEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public void failFloatValueNotEqualsAlmost(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatNotEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	
//	@Override
//	public void failFloatValueGreater(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatGreaterFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	@Override
//	public void failFloatValueGreater(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatGreaterFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	@Override
//	public void failFloatValueGreaterEquals(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatGreaterEqualsFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	@Override
//	public void failFloatValueGreaterEquals(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatGreaterEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	@Override
//	public void failFloatValueLess(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatLessFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	@Override
//	public void failFloatValueLess(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatLessFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	@Override
//	public void failFloatValueLessEquals(Object caller, String valueAName,
//			String valueBName)
//	{
//		this.Throw(caller, IPrimitiveFloatLessEqualsFail.class, new Object[] { caller, valueAName, valueBName });
//	}
//	@Override
//	public void failFloatValueLessEquals(Object caller, String valueAName,
//			String valueBName, String message)
//	{
//		this.Throw(caller, IPrimitiveFloatLessEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
//	}
//	
//	@Override
//	public void failFloatValueOutside(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveFloatOutsideFail.class, new Object[] { caller, valueAName });
//	}
//	@Override
//	public void failFloatValueOutside(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveFloatOutsideFail.class, new Object[] { caller, valueAName, message });
//	}
//	
//	@Override
//	public void failFloatValueWithin(Object caller, String valueAName)
//	{
//		this.Throw(caller, IPrimitiveFloatWithinFail.class, new Object[] { caller, valueAName });
//	}
//	@Override
//	public void failFloatValueWithin(Object caller, String valueAName,
//			String message)
//	{
//		this.Throw(caller, IPrimitiveFloatWithinFail.class, new Object[] { caller, valueAName, message });
//	}
//	
//	
//	// PRIMITIVES - FLOAT - END ---------------------------------

	
	/**
	 * Default constructor.
	 * <p>
	 * Remember to set call contractor before usage.
	 * </p>
	 */
	protected AFailer()
	{
		super();
	}
	

	/**
	 * Recommended constructor receiving required references (manual constructor dependency injection).
	 * <p>
	 * This is ready for use after this call.
	 * </p>
	 * 
	 * @param callContractor
	 *            used by this
	 */
	public AFailer(ICallContractor callContractor)
	{
		super();
		{
			if(null == callContractor)
			{
				throw new IllegalArgumentException("callContractor is null");
			}
			this.setCallContractor(callContractor);
		}
	}
	
	protected final static Object[] EmptyObjectArray = new Object[]{};
    protected void Throw(Object caller, Class<? extends IFail> failerSpecificationType, Object[] failerArguments)
    {
    	this.Throw(caller, failerSpecificationType, failerArguments, EmptyObjectArray);
    }
    
    protected void Throw(Object caller, Class<? extends IFail> failerSpecificationType, Object[] failerArguments, Object[] failerExtraArguments)
    {
        if (null == caller)
        {
            throw new IllegalArgumentException("caller is null");
        }

        NFail failAnnotation = this.LookupFailAnnotation(failerSpecificationType, failerArguments);
        SimpleEntry<Object[], Object[]> entry = this.popContractWithCaller(caller, failerSpecificationType);
        Object[] checkerArguments = entry.getKey();
        Object[] checkerExtraArguments = entry.getValue();
//        String message = this.formatMessage(failerSpecificationType, failAnnotation, checkerArguments, failerArguments);
//        String message = String.format(failAnnotation.failMessageFormat(), failerArguments);
//        String message = this.constructFailMessage(failerSpecificationType, failAnnotation, checkerArguments, checkerExtraArguments, failerArguments, failerExtraArguments);
        FailFastException exception = this.constructFailException(failerSpecificationType, failAnnotation, checkerArguments, checkerExtraArguments, failerArguments, failerExtraArguments);
        if(null == this.getFailFastExceptionOrNull())
        { // remember first exception
        	this.setFailFastExceptionOrNull(exception);
        }
        throw exception;
    }

	protected NFail LookupFailAnnotation(Class<?> failSpecificationType, Object[] messageFormatArguments)
    {
    	NFail result = null;

    	Method[] methodInfos = failSpecificationType.getDeclaredMethods();
        if (methodInfos.length <= 0)
        {
            throw new IllegalArgumentException(failSpecificationType + " contains no methods");
        }
        
        Method methodInfo = null;
        {
            for (int index = 0; index < methodInfos.length; ++index)
            {
                methodInfo = methodInfos[index];
                Class<?>[] parameterTypes = methodInfo.getParameterTypes();
                // arguments + caller
                if (messageFormatArguments.length == parameterTypes.length)
                {
                    boolean allParametersHaveMatchingType = true;
                    for (int j = 0; j < parameterTypes.length; ++j)
                    {
                        Class<?> parameterInfo = parameterTypes[j];
                        Object messageFormatArgument = messageFormatArguments[j];
                        Class<?> argumentType = (null == messageFormatArgument ? null : messageFormatArgument.getClass());
                        if (null == argumentType || !parameterInfo.isAssignableFrom(argumentType))
                        {
                        	if(parameterInfo.isPrimitive())
                        	{
                        		if(!argumentType.getSimpleName().equalsIgnoreCase(parameterInfo.getSimpleName()))
                        		{
    	                            allParametersHaveMatchingType = false;
    	                            break;
                        		}
                        	}
                        	else if(null == messageFormatArgument)
                        	{ // null matches all Object type parameters
                        		
                        	}
                        	else
                        	{
	                            allParametersHaveMatchingType = false;
	                            break;
                        	}
                        }
                    }
                    if (allParametersHaveMatchingType)
                    {
                        break;
                    }
                }
                else
                {
                    methodInfo = null;
                }
            }
        }

        if (null == methodInfo)
        {
            throw new IllegalArgumentException(failSpecificationType + " contains no method with " + messageFormatArguments.length + " arguments with appropriate types");
        }
        else
        {
            NFail failAnnotation = methodInfo.getAnnotation(NFail.class);
            if (null == failAnnotation)
            {
                throw new IllegalArgumentException(failSpecificationType + "." + methodInfo.getName() + " is not annotated with a NFail annotaiton");
            }
            result = failAnnotation;
        }

        return result;
    }
    
    protected SimpleEntry<Object[], Object[]> popContractWithCaller(Object caller, Class<? extends IFail> failSpecification)
    {
    	SimpleEntry<Object[], Object[]> result = null;
    	
    	ICallContractor callContractor = this.getCallContractor();
    	if(null == callContractor)
    	{
    		throw new IllegalStateException("CallContractor must be set before using this failer.");
    	}
    	// fail call requires a checker call from caller
    	result = callContractor.popContractWithCaller(caller, this, failSpecification);
    	
    	return result;
    }

    protected String constructFailMessage(
			Class<? extends IFail> failerSpecificationType,
			NFail failAnnotation, 
			Object[] checkerUserArguments,
			Object[] checkerExtraArguments,
			Object[] failerUserArguments,
			Object[] failerExtraArguments)
	{
    	String result = null;
    	
    	Object[] messageArgumentObjects = null;
    	{ // build arguments
    		String failMessageArguments = failAnnotation.failMessageArguments();
    		String[] messageArguments = failMessageArguments.split(",");
    		messageArgumentObjects = new Object[messageArguments.length];
    		for(int index = 0; index < messageArguments.length; ++index)
    		{
    			String messageArgument = messageArguments[index].trim();
    			String messageArgumentLower = messageArgument.toLowerCase(Locale.US);
    			if(messageArgumentLower.length() < 3)
    			{
    	            throw new IllegalArgumentException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with illegal entry '" + messageArguments[index] +"' (arg#" + index + ")");
    			}
       			boolean isCheckerUserArgument = false;
       			boolean isCheckerExtraArgument = false;
       			boolean isFailerUserArgument = false;
       			boolean isFailerExtraArgument = false;
    			{
       				char c0 = messageArgumentLower.charAt(0);
       				char c1 = messageArgumentLower.charAt(1);
	    			switch(c0)
	    			{
	      				case 'c':
	    				{
	    					switch(c1)
	    					{
	    	      				case 'u':
	    	    				{
	    	    					isCheckerUserArgument = true;
	    	    				}
	    	    				break;
	    	      				case 'x':
	    	    				{
	    	    					isCheckerExtraArgument = true;
	    	    				}
	    	    				break;
			    				default:
			    				{
			    	   	            throw new IllegalArgumentException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with illegal entry '" + messageArguments[index] +"' (arg#" + index + "). Symbol '" + c1 + "' was unexpected.");
			    	   	       	}
	    					}
	    				}
	    				break;
	      				case 'f':
	    				{
	    					switch(c1)
	    					{
	    	      				case 'u':
	    	    				{
	    	    					isFailerUserArgument = true;
	    	    				}
	    	    				break;
	    	      				case 'x':
	    	    				{
	    	    					isFailerExtraArgument = true;
	    	    				}
	    	    				break;
			    				default:
			    				{
			    	   	            throw new IllegalArgumentException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with illegal entry '" + messageArguments[index] +"' (arg#" + index + "). Symbol '" + c1 + "' was unexpected.");
			    	   	       	}
	    					}
	    				}
	    				break;
	    				default:
	    				{
	    	   	            throw new IllegalArgumentException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with illegal entry '" + messageArguments[index] +"' (arg#" + index + "). Symbol '" + c0 + "' was unexpected.");
	    	   	       	}
	    			}
    			}
    			
				int argumentIndex = -1;
    			{
    				String argumentIndexString = messageArgumentLower.substring(2);
    				try
    				{
        				argumentIndex = Integer.parseInt(argumentIndexString);
    				}
    				catch(NumberFormatException e)
    				{
    	   	            throw new IllegalArgumentException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with illegal entry '" + messageArguments[index] +"' (arg#" + index + "). Postfix '" + argumentIndexString + "' could not be parsed as an integer.");
    				}
    			}
    			
    			Object[] argumentsReference = null;
    			if(isCheckerUserArgument)
    			{
    				argumentsReference = checkerUserArguments;
    			}
    			else if(isCheckerExtraArgument)
    			{
    				argumentsReference = checkerExtraArguments;
    			}
    			else if(isFailerUserArgument)
    			{
    				argumentsReference = failerUserArguments;
    			}
    			else if(isFailerExtraArgument)
    			{
    				argumentsReference = failerExtraArguments;
    			}
    			else
    			{
   	   	            throw new IllegalStateException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with entry '" + messageArguments[index] +"' (arg#" + index + ") causing unexpected problems.");
    			}
    			
    			if(argumentsReference.length <= argumentIndex)
    			{
   	   	            throw new IllegalArgumentException(failerSpecificationType + " has annotation " + failAnnotation + " with failMessageArguments ' with entry '" + messageArguments[index] +"' (arg#" + index + ") that tries to index arguments array with length " + argumentsReference.length +".");
    			}
    			else
    			{
    				Object argument = argumentsReference[argumentIndex];
        			messageArgumentObjects[index] = argument;
    			}
    		}
    	}
    	
    	{ // format arguments into string
    		String format = failAnnotation.failMessageFormat();
    		result = String.format(format, messageArgumentObjects);
    	}
    	
		return result;
	}

    protected FailFastException constructFailException(
		Class<? extends IFail> failerSpecificationType,
		NFail failAnnotation, 
		Object[] checkerUserArguments,
		Object[] checkerExtraArguments,
		Object[] failerUserArguments,
		Object[] failerExtraArguments)
    {
    	FailFastException exception = null;
        
    	Class<? extends FailFastException> exceptionType = failAnnotation.failExceptionType();
    	
        String message = this.constructFailMessage(failerSpecificationType, failAnnotation, checkerUserArguments, checkerExtraArguments, failerUserArguments, failerExtraArguments);
    	
        Constructor<? extends FailFastException> constructor;
		try
		{
			constructor = exceptionType.getConstructor(String.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new IllegalArgumentException("Could not fetch a constructor from '" + exceptionType + "' with a single String argument", e);
		}
		
		
        try
		{
			exception = constructor.newInstance(message);
			exception.setCheckerSpecificationType(failAnnotation.checkerSpecificationType());
			exception.setCheckerUserArguments(checkerUserArguments);
			exception.setCheckerExtraArguments(checkerExtraArguments);
			exception.setFailerSpecificationType(failerSpecificationType);
			exception.setFailerUserArguments(failerUserArguments);
			exception.setFailerExtraArguments(failerExtraArguments);
			exception.setMessageFormat(failAnnotation.failMessageFormat());
			exception.setFailerMessageArguments(failAnnotation.failMessageArguments());
		}
		catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			throw new IllegalArgumentException("Could not invoke constructor of '" + exceptionType + "'", e);
		}

        return exception;
    }
    
}
