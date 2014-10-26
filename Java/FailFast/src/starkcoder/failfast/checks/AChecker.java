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
package starkcoder.failfast.checks;

import starkcoder.failfast.checks.objects.IObjectEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectNotEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectNotNullCheck;
import starkcoder.failfast.checks.objects.IObjectNotSameCheck;
import starkcoder.failfast.checks.objects.IObjectNullCheck;
import starkcoder.failfast.checks.objects.IObjectSameCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanFalseCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotDefaultCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanTrueCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringDefaultCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringEqualsCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringMatchingCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotDefaultCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotEqualsCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotMatchingCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotNullAndNotEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNotNullCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNullCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringNullOrEmptyCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithPostfixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithPrefixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithSubstringCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPostfixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPrefixCheck;
import starkcoder.failfast.checks.objects.strings.IObjectStringWithoutSubstringCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanEqualsCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanFalseCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotDefaultCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotEqualsCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanTrueCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatGreaterCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatGreaterEqualsCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatLessCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatLessEqualsCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotDefaultCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatOutsideCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatWithinCheck;
import starkcoder.failfast.contractors.ICallContractor;

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

	
	// OBJECTS - START
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * starkcoder.failfast.checks.objects.IObjectNullCheck#isObjectNull(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isObjectNull(Object caller, Object reference)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == reference)
		{
			this.pushContractWithCaller(caller, IObjectNullCheck.class);
			result = true;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * starkcoder.failfast.checks.objects.IObjectNotNullCheck#isObjectNotNull
	 * (java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isObjectNotNull(Object caller, Object reference)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != reference)
		{
			this.pushContractWithCaller(caller, IObjectNotNullCheck.class);
			result = true;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * starkcoder.failfast.checks.objects.IObjectEqualsCheck#isObjectEquals(
	 * java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isObjectEquals(Object caller, Object referenceA,
			Object referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if ((null == referenceA && null == referenceB)
				|| (null != referenceA && referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectEqualsCheck.class);
			result = true;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * starkcoder.failfast.checks.objects.IObjectNotEqualsCheck#isObjectNotEquals
	 * (java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isObjectNotEquals(Object caller, Object referenceA,
			Object referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if ((null == referenceA && null != referenceB)
				|| (null != referenceA && !referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectNotEqualsCheck.class);
			result = true;
		}

		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectSameCheck#isObjectSame(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isObjectSame(Object caller, Object referenceA,
			Object referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA == referenceB)
		{
			this.pushContractWithCaller(caller, IObjectSameCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectNotSameCheck#isObjectNotSame(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isObjectNotSame(Object caller, Object referenceA,
			Object referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA != referenceB)
		{
			this.pushContractWithCaller(caller, IObjectNotSameCheck.class);
			result = true;
		}

		return result;
	}

	// OBJECTS - END
	
	
	
	// OBJECTS - BOOLEANS - START
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanEqualsCheck#isBooleanEquals(java.lang.Object, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanEquals(Object caller, Boolean referenceA,
			Boolean referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA == referenceB)
		{
			this.pushContractWithCaller(caller, IObjectBooleanEqualsCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotEqualsCheck#isBooleanNotEquals(java.lang.Object, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanNotEquals(Object caller, Boolean referenceA,
			Boolean referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA != referenceB)
		{
			this.pushContractWithCaller(caller, IObjectBooleanNotEqualsCheck.class);
			result = true;
		}

		return result;
	}
	
	@Override
	public boolean isBooleanNotNull(Object caller, Boolean referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA)
		{
			this.pushContractWithCaller(caller, IObjectBooleanNotNullCheck.class);
			result = true;
		}

		return result;
	}
	@Override
	public boolean isBooleanNull(Object caller, Boolean referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA)
		{
			this.pushContractWithCaller(caller, IObjectBooleanNullCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanFalseCheck#isBooleanFalse(java.lang.Object, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanFalse(Object caller, Boolean referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (null != referenceA)
		{
			if (!referenceA.booleanValue())
			{
				this.pushContractWithCaller(caller, IObjectBooleanFalseCheck.class);
				result = true;
			}
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanTrueCheck#isBooleanTrue(java.lang.Object, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanTrue(Object caller, Boolean referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (null != referenceA)
		{
			if (referenceA.booleanValue())
			{
				this.pushContractWithCaller(caller, IObjectBooleanTrueCheck.class);
				result = true;
			}
		}

		return result;
	}

	private Boolean booleanDefault = Boolean.FALSE;
	@Override
	public Boolean getBooleanDefault()
	{
		return this.booleanDefault;
	}
	@Override
	public void setBooleanDefault(Boolean defaultBoolean)
	{
		this.booleanDefault = defaultBoolean;
	}

	@Override
	public boolean isBooleanNotDefault(Object caller, Boolean referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (this.getBooleanDefault() != referenceA)
		{
			this.pushContractWithCaller(caller, IObjectBooleanNotDefaultCheck.class);
			result = true;
		}

		return result;
	}

	@Override
	public boolean isBooleanDefault(Object caller, Boolean referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (this.getBooleanDefault() == referenceA)
		{
			this.pushContractWithCaller(caller, IObjectBooleanDefaultCheck.class);
			result = true;
		}

		return result;
	}

	
	
	// OBJECTS - BOOLEANS - END

	
	// OBJECTS - STRINGS - START

	private String stringDefault = new String();
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringDefaultProperties#getStringDefault()
	 */
	@Override
	public String getStringDefault()
	{
		return this.stringDefault;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringDefaultProperties#setStringDefault(java.lang.String)
	 */
	@Override
	public void setStringDefault(String defaultString)
	{
		this.stringDefault = defaultString;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringDefaultCheck#isStringDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringDefault(Object caller, String referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (this.getStringDefault().equals(referenceA))
		{
			this.pushContractWithCaller(caller, IObjectStringDefaultCheck.class);
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotDefaultCheck#isStringNotDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringNotDefault(Object caller, String referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (!this.getStringDefault().equals(referenceA))
		{
			this.pushContractWithCaller(caller, IObjectStringNotDefaultCheck.class);
			result = true;
		}

		return result;
	}	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringEqualsCheck#isStringEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringEquals(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA.equals(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringEqualsCheck.class);
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotEqualsCheck#isStringNotEquals(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringNotEquals(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (!referenceA.equals(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringNotEqualsCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNullCheck#isStringNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringNull(Object caller, String referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA)
		{
			this.pushContractWithCaller(caller, IObjectStringNullCheck.class);
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotNullCheck#isStringNotNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringNotNull(Object caller, String referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA)
		{
			this.pushContractWithCaller(caller, IObjectStringNotNullCheck.class);
			result = true;
		}

		return result;
	}

	private final static String stringEmpty = "";
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringEmptyCheck#isStringEmpty(java.lang.Object, java.lang.String)
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
			this.pushContractWithCaller(caller, IObjectStringEmptyCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotEmptyCheck#isStringNotEmpty(java.lang.Object, java.lang.String)
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
			this.pushContractWithCaller(caller, IObjectStringNotEmptyCheck.class);
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
			this.pushContractWithCaller(caller, IObjectStringNullOrEmptyCheck.class);
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
			this.pushContractWithCaller(caller, IObjectStringNotNullAndNotEmptyCheck.class);
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithPostfixCheck#isStringWithPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithPostfix(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != referenceB && referenceA.endsWith(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringWithPostfixCheck.class);
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPostfixCheck#isStringWithoutPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithoutPostfix(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == referenceB || !referenceA.endsWith(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringWithoutPostfixCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithSubstringCheck#isStringWithSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithSubstring(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != referenceB && 0 <= referenceA.indexOf(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringWithSubstringCheck.class);
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutSubstringCheck#isStringWithoutSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithoutSubstring(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == referenceB || referenceA.indexOf(referenceB) < 0)
		{
			this.pushContractWithCaller(caller, IObjectStringWithoutSubstringCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPrefixCheck#isStringWithoutPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithoutPrefix(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == referenceB || !referenceA.startsWith(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringWithoutPrefixCheck.class);
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithPrefixCheck#isStringWithPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithPrefix(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != referenceB && referenceA.startsWith(referenceB))
		{
			this.pushContractWithCaller(caller, IObjectStringWithPrefixCheck.class);
			result = true;
		}

		return result;
	}

	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotMatchingCheck#isStringNotMatching(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringNotMatching(Object caller, String referenceA,
			String regex)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == regex || !referenceA.matches(regex))
		{
			this.pushContractWithCaller(caller, IObjectStringNotMatchingCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringMatchingCheck#isStringMatching(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringMatching(Object caller, String referenceA,
			String regex)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != regex && referenceA.matches(regex))
		{
			this.pushContractWithCaller(caller, IObjectStringMatchingCheck.class);
			result = true;
		}

		return result;
	}

	
	// OBJECTS - STRINGS - END

	
	
	// PRIMITIVES - BOOLEANS - START


	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanEqualsCheck#isObjectEquals(java.lang.Object, boolean, boolean)
	 */
	@Override
	public boolean isBooleanValueEquals(Object caller, boolean valueA, boolean valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA == valueB)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanEqualsCheck.class);
			result = true;
		}

		return result;
	}



	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotEqualsCheck#isObjectNotEquals(java.lang.Object, boolean, boolean)
	 */
	@Override
	public boolean isBooleanValueNotEquals(Object caller, boolean valueA,
			boolean valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA != valueB)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanNotEqualsCheck.class);
			result = true;
		}

		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanFalseCheck#isBooleanValueFalse(java.lang.Object, boolean)
	 */
	@Override
	public boolean isBooleanValueFalse(Object caller, boolean valueA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (!valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanFalseCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanTrueCheck#isBooleanValueTrue(java.lang.Object, boolean)
	 */
	@Override
	public boolean isBooleanValueTrue(Object caller, boolean valueA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanTrueCheck.class);
			result = true;
		}

		return result;
	}

	
	private boolean booleanValueDefault;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultProperties#getBooleanValueDefault()
	 */
	@Override
	public boolean getBooleanValueDefault()
	{
		return this.booleanValueDefault;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultProperties#setBooleanValueDefault(boolean)
	 */
	@Override
	public void setBooleanValueDefault(boolean defaultBooleanValue)
	{
		this.booleanValueDefault = defaultBooleanValue;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotDefaultCheck#isBooleanValueNotDefault(java.lang.Object, boolean)
	 */
	@Override
	public boolean isBooleanValueNotDefault(Object caller, boolean valueA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA != this.getBooleanValueDefault())
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanNotDefaultCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultCheck#isBooleanValueDefault(java.lang.Object, boolean)
	 */
	@Override
	public boolean isBooleanValueDefault(Object caller, boolean valueA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA == this.getBooleanValueDefault())
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanDefaultCheck.class);
			result = true;
		}

		return result;
	}

	
	// PRIMITIVES - BOOLEANS - END
	
	
	// PRIMITIVES - FLOATS - START
	
	
	
	// PRIMITIVES - FLOATS - END
	


	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsCheck#isFloatValueEquals(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueEquals(Object caller, float valueA, float valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA == valueB)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsCheck.class);
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsCheck#isFloatValueNotEquals(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueNotEquals(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (valueA != valueB)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsCheck.class);
			result = true;
		}

		return result;	
	}
	
	
	private float floatValueDefault = 0f;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultProperties#getFloatValueDefault()
	 */
	@Override
	public float getFloatValueDefault()
	{
		return this.floatValueDefault;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultProperties#setFloatValueDefault(float)
	 */
	@Override
	public void setFloatValueDefault(float defaultFloatValue)
	{
		this.floatValueDefault = defaultFloatValue;
	}
	

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultCheck#isFloatValueDefault(java.lang.Object, float)
	 */
	@Override
	public boolean isFloatValueDefault(Object caller, float valueA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (this.getFloatValueDefault() == valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatDefaultCheck.class);
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotDefaultCheck#isFloatValueNotDefault(java.lang.Object, float)
	 */
	@Override
	public boolean isFloatValueNotDefault(Object caller, float valueA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (this.getFloatValueDefault() != valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatNotDefaultCheck.class);
			result = true;
		}

		return result;
	}


	

	private float floatValueEqualsAlmostDefaultAbsoluteEpsilon = 0.00001f;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#getFloatValueEqualsAlmostDefaultAbsoluteEpsilon()
	 */
	@Override
	public float getFloatValueEqualsAlmostDefaultAbsoluteEpsilon()
	{
		return this.floatValueEqualsAlmostDefaultAbsoluteEpsilon;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#setFloatValueEqualsAlmostDefaultAbsoluteEpsilon(float)
	 */
	@Override
	public void setFloatValueEqualsAlmostDefaultAbsoluteEpsilon(
			float defaultAbsoluteEpsilon)
	{
		this.floatValueEqualsAlmostDefaultAbsoluteEpsilon = Math.abs(defaultAbsoluteEpsilon);
	}
	

	private float floatValueEqualsAlmostDefaultRelativeEpsilon = 0.000001f;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#getFloatValueEqualsAlmostDefaultRelativeEpsilon()
	 */
	@Override
	public float getFloatValueEqualsAlmostDefaultRelativeEpsilon()
	{
		return this.floatValueEqualsAlmostDefaultRelativeEpsilon;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#setFloatValueEqualsAlmostDefaultRelativeEpsilon(float)
	 */
	@Override
	public void setFloatValueEqualsAlmostDefaultRelativeEpsilon(
			float defaultRelativeEpsilon)
	{
		this.floatValueEqualsAlmostDefaultRelativeEpsilon = Math.abs(defaultRelativeEpsilon);
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		float defaultAbsoluteEpsilon = this.getFloatValueEqualsAlmostDefaultAbsoluteEpsilon();
		float defaultRelativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatValueEqualsAlmost(caller, valueA, valueB, defaultAbsoluteEpsilon, defaultRelativeEpsilon);
				
		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float, float)
	 */
	@Override
	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
			float valueB, float absoluteEpsilon)
	{
		boolean result = false;

		float defaultRelativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatValueEqualsAlmost(caller, valueA, valueB, absoluteEpsilon, defaultRelativeEpsilon);
				
		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float, float, float)
	 */
	@Override
	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
			float valueB, float absoluteEpsilon, float relativeEpsilon)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueA == valueB)
		{
			result = true;
		}
		else 
		{
			float lowB = (1f - Math.signum(valueA) * relativeEpsilon) * valueA - absoluteEpsilon;
			float highB = (1f + Math.signum(valueA) * relativeEpsilon) * valueA + absoluteEpsilon;
			result = (lowB <= valueB && valueB <= highB);
		}

		if(result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsAlmostCheck.class);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck#isFloatValueNotEqualsAlmost(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		float absoluteEpsilon = this.getFloatValueEqualsAlmostDefaultAbsoluteEpsilon();
		float relativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatValueNotEqualsAlmost(caller, valueA, valueB, absoluteEpsilon, relativeEpsilon);
				
		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck#isFloatValueNotEqualsAlmost(java.lang.Object, float, float, float)
	 */
	@Override
	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
			float valueB, float absoluteEpsilon)
	{
		boolean result = false;

		float relativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatValueNotEqualsAlmost(caller, valueA, valueB, absoluteEpsilon, relativeEpsilon);
				
		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck#isFloatValueNotEqualsAlmost(java.lang.Object, float, float, float, float)
	 */
	@Override
	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
			float valueB, float absoluteEpsilon, float relativeEpsilon)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueA != valueB)
		{
			float lowB = (1f - Math.signum(valueA) * relativeEpsilon) * valueA - absoluteEpsilon;
			float highB = (1f + Math.signum(valueA) * relativeEpsilon) * valueA + absoluteEpsilon;
			result = (valueB < lowB ||  highB < valueB);
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsAlmostCheck.class);
		}

		return result;
	}


	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatLessCheck#isFloatValueLess(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueLess(Object caller, float valueA, float valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueA < valueB)
		{
			result = true;
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatLessCheck.class);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatLessEqualsCheck#isFloatValueLessEquals(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueLessEquals(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueA <= valueB)
		{
			result = true;
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatLessEqualsCheck.class);
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatGreaterCheck#isFloatValueGreater(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueGreater(Object caller, float valueA, float valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueA > valueB)
		{
			result = true;
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatGreaterCheck.class);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatGreaterEqualsCheck#isFloatValueGreaterEquals(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueGreaterEquals(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueA >= valueB)
		{
			result = true;
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatGreaterEqualsCheck.class);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatOutsideCheck#isFloatValueOutside(java.lang.Object, float, float, float)
	 */
	@Override
	public boolean isFloatValueOutside(Object caller, float valueA,
			float valueMin, float valueMax)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueMin <= valueMax)
		{
			if(valueA < valueMin || valueMax < valueA)
			{
				result = true;
			}
		}
		else
		{ // valueMin > valueMax
			if(valueA < valueMax || valueMin < valueA)
			{
				result = true;
			}
		}
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatOutsideCheck.class);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatWithinCheck#isFloatValueWithin(java.lang.Object, float, float, float)
	 */
	@Override
	public boolean isFloatValueWithin(Object caller, float valueA,
			float valueMin, float valueMax)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		if(valueMin <= valueMax)
		{
			if(valueMin <= valueA && valueA <= valueMax)
			{
				result = true;
			}
		}
		else
		{ // valueMin > valueMax
			if(valueMax <= valueA && valueA <= valueMin)
			{
				result = true;
			}
		}
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatWithinCheck.class);
		}

		return result;
	}

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
	 * Recommended constructor receiving required references (manual constructor
	 * dependency injection).
	 * <p>
	 * This is ready for use after this call.
	 * </p>
	 * 
	 * @param callContractor
	 *            used by this
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

	protected void pushContractWithCaller(Object caller,
			Class<? extends ICheck> checkerSpecification)
	{
		ICallContractor callContractor = this.getCallContractor();
		if (null == callContractor)
		{
			throw new IllegalStateException(
					"CallContractor must be set before using this checker.");
		}

		// require a fail call from caller
		callContractor.pushContractWithCaller(caller, this,
				checkerSpecification);
	}
	
}
