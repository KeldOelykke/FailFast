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

import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.objects.IObjectEqualsFail;
import starkcoder.failfast.fails.objects.IObjectNotEqualsFail;
import starkcoder.failfast.fails.objects.IObjectNotNullFail;
import starkcoder.failfast.fails.objects.IObjectNotSameFail;
import starkcoder.failfast.fails.objects.IObjectNullFail;
import starkcoder.failfast.fails.objects.IObjectSameFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanDefaultFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanEqualsFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanFalseFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotDefaultFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotEqualsFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNotNullFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanNullFail;
import starkcoder.failfast.fails.objects.booleans.IObjectBooleanTrueFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumDefaultFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotDefaultFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotEqualsFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNotNullFail;
import starkcoder.failfast.fails.objects.enums.IObjectEnumNullFail;
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
import starkcoder.failfast.fails.objects.strings.IObjectStringNullFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringNullOrEmptyFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithPostfixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithPrefixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithSubstringFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPostfixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPrefixFail;
import starkcoder.failfast.fails.objects.strings.IObjectStringWithoutSubstringFail;
import starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanDefaultFail;
import starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanEqualsFail;
import starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanFalseFail;
import starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanNotDefaultFail;
import starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanNotEqualsFail;
import starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanTrueFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatDefaultFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatGreaterEqualsFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatGreaterFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatLessEqualsFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatLessFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotDefaultFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatOutsideFail;
import starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatWithinFail;

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
	
	// OBJECTS - END ---------------------------------

	
	// OBJECTS - BOOLEAN - START -------------------------------

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
	
	
	
	// OBJECTS - STRING - START ---------------------------------
	
	
	

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
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPrefixFail#failStringWithPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithPrefix(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringWithPrefixFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPrefixFail#failStringWithPrefix(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithPrefix(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringWithPrefixFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPrefixFail#failStringWithoutPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutPrefix(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringWithoutPrefixFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPrefixFail#failStringWithoutPrefix(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutPrefix(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringWithoutPrefixFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPostfixFail#failStringWithoutPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutPostfix(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringWithoutPostfixFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutPostfixFail#failStringWithoutPostfix(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutPostfix(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringWithoutPostfixFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithSubstringFail#failStringWithSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithSubstring(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringWithSubstringFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithSubstringFail#failStringWithSubstring(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithSubstring(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringWithSubstringFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPostfixFail#failStringWithPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithPostfix(Object caller, String referenceAName,
			String referenceBName)
	{
		this.Throw(caller, IObjectStringWithPostfixFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithPostfixFail#failStringWithPostfix(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithPostfix(Object caller, String referenceAName,
			String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringWithPostfixFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutSubstringFail#failStringWithoutSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutSubstring(Object caller,
			String referenceAName, String referenceBName)
	{
		this.Throw(caller, IObjectStringWithoutSubstringFail.class, new Object[] { caller, referenceAName, referenceBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringWithoutSubstringFail#failStringWithoutSubstring(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringWithoutSubstring(Object caller,
			String referenceAName, String referenceBName, String message)
	{
		this.Throw(caller, IObjectStringWithoutSubstringFail.class, new Object[] { caller, referenceAName, referenceBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringMatchingFail#failStringMatching(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringMatching(Object caller, String referenceAName,
			String regex)
	{
		this.Throw(caller, IObjectStringMatchingFail.class, new Object[] { caller, referenceAName, regex });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringMatchingFail#failStringMatching(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringMatching(Object caller, String referenceAName,
			String regex, String message)
	{
		this.Throw(caller, IObjectStringMatchingFail.class, new Object[] { caller, referenceAName, regex, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotMatchingFail#failStringNotMatching(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringNotMatching(Object caller, String referenceAName,
			String regex)
	{
		this.Throw(caller, IObjectStringNotMatchingFail.class, new Object[] { caller, referenceAName, regex });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.objects.strings.IObjectStringNotMatchingFail#failStringNotMatching(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failStringNotMatching(Object caller, String referenceAName,
			String regex, String message)
	{
		this.Throw(caller, IObjectStringNotMatchingFail.class, new Object[] { caller, referenceAName, regex, message });
	}
	
	
	
	// OBJECTS - STRING - END ---------------------------------
	
	
	
	// PRIMITIVES - BOOLEAN - START -------------------------------

	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanNotEqualsFail#failBooleanValueNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanValueNotEquals(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveBooleanNotEqualsFail.class, new Object[] { caller, valueAName, valueBName });
	}


	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanNotEqualsFail#failBooleanValueNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanValueNotEquals(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveBooleanNotEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanEqualsFail#failBooleanValueEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanValueEquals(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveBooleanEqualsFail.class, new Object[] { caller, valueAName, valueBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanEqualsFail#failBooleanValueEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanValueEquals(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveBooleanEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanFalseFail#failBooleanValueFalse(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failBooleanValueFalse(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveBooleanFalseFail.class, new Object[] { caller, valueAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanFalseFail#failBooleanValueFalse(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanValueFalse(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveBooleanFalseFail.class, new Object[] { caller, valueAName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanTrueFail#failBooleanValueTrue(java.lang.Object, java.lang.String)
	 */
	@Override
	public void failBooleanValueTrue(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveBooleanTrueFail.class, new Object[] { caller, valueAName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.booleans.IPrimitiveBooleanTrueFail#failBooleanValueTrue(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failBooleanValueTrue(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveBooleanTrueFail.class, new Object[] { caller, valueAName, message });
	}
	
	@Override
	public void failBooleanValueDefault(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveBooleanDefaultFail.class, new Object[] { caller, valueAName });
	}
	@Override
	public void failBooleanValueDefault(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveBooleanDefaultFail.class, new Object[] { caller, valueAName, message });
	}
	@Override
	public void failBooleanValueNotDefault(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveBooleanNotDefaultFail.class, new Object[] { caller, valueAName });
	}
	@Override
	public void failBooleanValueNotDefault(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveBooleanNotDefaultFail.class, new Object[] { caller, valueAName, message });
	}
	
	
	// PRIMITIVES - BOOLEAN - END ---------------------------------
	
	
	// PRIMITIVES - FLOAT - START -------------------------------


	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsFail#failFloatValueEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueEquals(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatEqualsFail.class, new Object[] { caller, valueAName, valueBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsFail#failFloatValueEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueEquals(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail#failFloatValueNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueNotEquals(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsFail.class, new Object[] { caller, valueAName, valueBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail#failFloatValueNotEquals(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueNotEquals(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail#failFloatValueNotEquals(java.lang.Object, java.lang.String, float, java.lang.String, float)
	 */
	@Override
	public void failFloatValueNotEquals(Object caller, String valueAName,
			float valueA, String valueBName, float valueB)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsFail.class, new Object[] { caller, valueAName, valueA, valueBName, valueB });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsFail#failFloatValueNotEquals(java.lang.Object, java.lang.String, float, java.lang.String, float, java.lang.String)
	 */
	@Override
	public void failFloatValueNotEquals(Object caller, String valueAName,
			float valueA, String valueBName, float valueB, String message)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsFail.class, new Object[] { caller, valueAName, valueA, valueBName, valueB, message });
	}
	
	
	@Override
	public void failFloatValueDefault(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveFloatDefaultFail.class, new Object[] { caller, valueAName });
	}
	@Override
	public void failFloatValueDefault(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveFloatDefaultFail.class, new Object[] { caller, valueAName, message });
	}
	
	@Override
	public void failFloatValueNotDefault(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveFloatNotDefaultFail.class, new Object[] { caller, valueAName });
	}
	@Override
	public void failFloatValueNotDefault(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveFloatNotDefaultFail.class, new Object[] { caller, valueAName, message });
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail#failFloatValueEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueEqualsAlmost(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail#failFloatValueEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueEqualsAlmost(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail#failFloatValueEqualsAlmost(java.lang.Object, java.lang.String, float, java.lang.String, float)
	 */
	@Override
	public void failFloatValueEqualsAlmost(Object caller, String valueAName,
			float valueA, String valueBName, float valueB)
	{
		this.Throw(caller, IPrimitiveFloatEqualsAlmostFail.class, new Object[] { caller, valueAName, valueA, valueBName, valueB });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatEqualsAlmostFail#failFloatValueEqualsAlmost(java.lang.Object, java.lang.String, float, java.lang.String, float, java.lang.String)
	 */
	@Override
	public void failFloatValueEqualsAlmost(Object caller, String valueAName,
			float valueA, String valueBName, float valueB, String message)
	{
		this.Throw(caller, IPrimitiveFloatEqualsAlmostFail.class, new Object[] { caller, valueAName, valueA, valueBName, valueB, message });
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail#failFloatValueNotEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueNotEqualsAlmost(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail#failFloatValueNotEqualsAlmost(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void failFloatValueNotEqualsAlmost(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsAlmostFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail#failFloatValueNotEqualsAlmost(java.lang.Object, java.lang.String, float, java.lang.String, float)
	 */
	@Override
	public void failFloatValueNotEqualsAlmost(Object caller, String valueAName,
			float valueA, String valueBName, float valueB)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsAlmostFail.class, new Object[] { caller, valueAName, valueA, valueBName, valueB });
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.fails.primitives.floats.IPrimitiveFloatNotEqualsAlmostFail#failFloatValueNotEqualsAlmost(java.lang.Object, java.lang.String, float, java.lang.String, float, java.lang.String)
	 */
	@Override
	public void failFloatValueNotEqualsAlmost(Object caller, String valueAName,
			float valueA, String valueBName, float valueB, String message)
	{
		this.Throw(caller, IPrimitiveFloatNotEqualsAlmostFail.class, new Object[] { caller, valueAName, valueA, valueBName, valueB, message });
	}
	
	
	@Override
	public void failFloatValueGreater(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatGreaterFail.class, new Object[] { caller, valueAName, valueBName });
	}
	@Override
	public void failFloatValueGreater(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatGreaterFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	@Override
	public void failFloatValueGreaterEquals(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatGreaterEqualsFail.class, new Object[] { caller, valueAName, valueBName });
	}
	@Override
	public void failFloatValueGreaterEquals(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatGreaterEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	@Override
	public void failFloatValueLess(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatLessFail.class, new Object[] { caller, valueAName, valueBName });
	}
	@Override
	public void failFloatValueLess(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatLessFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	@Override
	public void failFloatValueLessEquals(Object caller, String valueAName,
			String valueBName)
	{
		this.Throw(caller, IPrimitiveFloatLessEqualsFail.class, new Object[] { caller, valueAName, valueBName });
	}
	@Override
	public void failFloatValueLessEquals(Object caller, String valueAName,
			String valueBName, String message)
	{
		this.Throw(caller, IPrimitiveFloatLessEqualsFail.class, new Object[] { caller, valueAName, valueBName, message });
	}
	
	@Override
	public void failFloatValueOutside(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveFloatOutsideFail.class, new Object[] { caller, valueAName });
	}
	@Override
	public void failFloatValueOutside(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveFloatOutsideFail.class, new Object[] { caller, valueAName, message });
	}
	@Override
	public void failFloatValueOutside(Object caller, String valueAName,
			float valueMin, float valueMax)
	{
		this.Throw(caller, IPrimitiveFloatOutsideFail.class, new Object[] { caller, valueAName, valueMin, valueMax });
	}
	@Override
	public void failFloatValueOutside(Object caller, String valueAName,
			float valueMin, float valueMax, String message)
	{
		this.Throw(caller, IPrimitiveFloatOutsideFail.class, new Object[] { caller, valueAName, valueMin, valueMax, message });
	}
	
	@Override
	public void failFloatValueWithin(Object caller, String valueAName)
	{
		this.Throw(caller, IPrimitiveFloatWithinFail.class, new Object[] { caller, valueAName });
	}
	@Override
	public void failFloatValueWithin(Object caller, String valueAName,
			String message)
	{
		this.Throw(caller, IPrimitiveFloatWithinFail.class, new Object[] { caller, valueAName, message });
	}
	@Override
	public void failFloatValueWithin(Object caller, String valueAName,
			float valueMin, float valueMax)
	{
		this.Throw(caller, IPrimitiveFloatWithinFail.class, new Object[] { caller, valueAName, valueMin, valueMax });
	}
	@Override
	public void failFloatValueWithin(Object caller, String valueAName,
			float valueMin, float valueMax, String message)
	{
		this.Throw(caller, IPrimitiveFloatWithinFail.class, new Object[] { caller, valueAName, valueMin, valueMax, message });
	}
	
	
	// PRIMITIVES - FLOAT - END ---------------------------------

	
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
	
    protected void Throw(Object caller, Class<? extends IFail> failerSpecificationType, Object[] messageFormatArguments)
    {
        if (null == caller)
        {
            throw new IllegalArgumentException("caller is null");
        }

        NFail failAnnotation = this.LookupFailAnnotation(failerSpecificationType, messageFormatArguments);
        String message = String.format(failAnnotation.failMessageFormat(), messageFormatArguments);
        this.popContractWithCaller(caller, failerSpecificationType);
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
    
    protected void popContractWithCaller(Object caller, Class<? extends IFail> failSpecification)
    {
    	ICallContractor callContractor = this.getCallContractor();
    	if(null == callContractor)
    	{
    		throw new IllegalStateException("CallContractor must be set before using this failer.");
    	}
    	// fail call requires a checker call from caller
    	callContractor.popContractWithCaller(caller, this, failSpecification);
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
