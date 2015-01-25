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
import starkcoder.failfast.fails.objects.enums.IObjectEnumDefaultFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotDefaultFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotNullFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotSameFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNullFail;
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
import starkcoder.failfast.fails.objects.strings.IObjectStringDefaultFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringEmptyFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringEqualsFail;
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
	
	
	// OBJECTS - ENUM - END ---------------------------------
	

	
	// OBJECTS - FLOAT - START ---------------------------------
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.floats.IObjectFloatNotSameFail#failFloatNotSame(java.lang.Object, java.lang.String, java.lang.String)
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
        String message = this.constructFailMessage(failerSpecificationType, failAnnotation, checkerArguments, checkerExtraArguments, failerArguments, failerExtraArguments);
        FailFastException exception = this.constructFailException(failAnnotation.failExceptionType(), message);
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

    protected FailFastException constructFailException(Class<? extends FailFastException> exceptionType, String message)
    {
    	FailFastException exception = null;
        
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
		}
		catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			throw new IllegalArgumentException("Could not invoke constructor of '" + exceptionType + "'", e);
		}

        return exception;
    }
    
}
