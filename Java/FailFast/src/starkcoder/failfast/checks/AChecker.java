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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import starkcoder.failfast.checks.objects.IObjectArrayEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectCollectionEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectEqualsCheck;
import starkcoder.failfast.checks.objects.IObjectListEqualsCheck;
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
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotSameCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanSameCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanTrueCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumEqualsCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotDefaultCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotEqualsCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotNullCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNotSameCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumNullCheck;
import starkcoder.failfast.checks.objects.enums.IObjectEnumSameCheck;
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
			this.pushContractWithCaller(caller, IObjectNullCheck.class, new Object[] { caller, reference });
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
			this.pushContractWithCaller(caller, IObjectNotNullCheck.class, new Object[] { caller, reference });
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
			this.pushContractWithCaller(caller, IObjectEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
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
			this.pushContractWithCaller(caller, IObjectNotEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
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
			this.pushContractWithCaller(caller, IObjectSameCheck.class, new Object[] { caller, referenceA, referenceB });
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
			this.pushContractWithCaller(caller, IObjectNotSameCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}

	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectArrayEqualsCheck#isObjectArrayEquals(java.lang.Object, java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public boolean isObjectArrayEquals(Object caller, Object[] referenceA,
			Object[] referenceB)
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
		else if(null != referenceA && null != referenceA
			&& referenceA.length == referenceB.length)
		{
			boolean result_ = true;
			for(int index = 0; index < referenceA.length; ++index)
			{
				Object a = referenceA[index];
				Object b = referenceB[index];
				if(!(null == a && null == b
					|| null != a && a.equals(b)))
				{
					result_ = false;
					break;
				}
			}
			result = result_;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, IObjectArrayEqualsCheck.class, new Object[] { caller, referenceA, referenceB }, new Object[] { (null == referenceA ? null : referenceA.length), (null == referenceB ? null : referenceB.length) });
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectListEqualsCheck#isObjectListEquals(java.lang.Object, java.util.List, java.util.List)
	 */
	@Override
	public boolean isObjectListEquals(Object caller, List<Object> referenceA,
			List<Object> referenceB)
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
		else if(null != referenceA && null != referenceA
			&& referenceA.size() == referenceB.size())
		{
			boolean result_ = true;
			for(int index = 0; index < referenceA.size(); ++index)
			{
				Object a = referenceA.get(index);
				Object b = referenceB.get(index);
				if(!(null == a && null == b
					|| null != a && a.equals(b)))
				{
					result_ = false;
					break;
				}
			}
			result = result_;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, IObjectListEqualsCheck.class, new Object[] { caller, referenceA, referenceB }, new Object[] { (null == referenceA ? null : referenceA.size()), (null == referenceB ? null : referenceB.size()) });
		}

		return result;
	}

	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectCollectionEqualsCheck#isObjectCollectionEquals(java.lang.Object, java.util.Collection, java.util.Collection)
	 */
	@Override
	public boolean isObjectCollectionEquals(Object caller,
			Collection<Object> referenceA, Collection<Object> referenceB)
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
		else if(null != referenceA && null != referenceA
			&& referenceA.size() == referenceB.size())
		{
			boolean result_ = true;
			Iterator<Object> iteratorA = referenceA.iterator();
			Iterator<Object> iteratorB = referenceB.iterator();
//			int index = -1;
			while(iteratorA.hasNext() && iteratorB.hasNext())
			{
//				++index;
				Object a = iteratorA.next();
				Object b = iteratorB.next();
				if(!(null == a && null == b
					|| null != a && a.equals(b)))
				{
					result_ = false;
					break;
				}
			}
			result = result_;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, IObjectCollectionEqualsCheck.class, new Object[] { caller, referenceA, referenceB }, new Object[] { (null == referenceA ? null : referenceA.size()), (null == referenceB ? null : referenceB.size()) });
		}

		return result;
	}

	// OBJECTS - END
	
	
	
	// OBJECTS - BOOLEANS - START
	


	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotSameCheck#isBooleanNotSame(java.lang.Object, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanNotSame(Object caller, Boolean referenceA,
			Boolean referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA != referenceB)
		{
			this.pushContractWithCaller(caller, IObjectBooleanNotSameCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanSameCheck#isBooleanSame(java.lang.Object, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanSame(Object caller, Boolean referenceA,
			Boolean referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA == referenceB)
		{
			this.pushContractWithCaller(caller, IObjectBooleanSameCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}	
	
	
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

		if ((null == referenceA && null == referenceB)
			|| (null != referenceA && referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectBooleanEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
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

		if ((null == referenceA && null != referenceB)
			|| (null != referenceA && !referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectBooleanNotEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck#isBooleanNotNull(java.lang.Object, java.lang.Boolean)
	 */
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
			this.pushContractWithCaller(caller, IObjectBooleanNotNullCheck.class, new Object[] { caller, referenceA });
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck#isBooleanNull(java.lang.Object, java.lang.Boolean)
	 */
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
			this.pushContractWithCaller(caller, IObjectBooleanNullCheck.class, new Object[] { caller, referenceA });
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
			if (referenceA.equals(Boolean.FALSE))
			{
				this.pushContractWithCaller(caller, IObjectBooleanFalseCheck.class, new Object[] { caller, referenceA }, new Object[] { Boolean.FALSE });
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
			if (referenceA.equals(Boolean.TRUE))
			{
				this.pushContractWithCaller(caller, IObjectBooleanTrueCheck.class, new Object[] { caller, referenceA }, new Object[] { Boolean.TRUE });
				result = true;
			}
		}

		return result;
	}

	private Boolean booleanDefault = Boolean.FALSE;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultProperties#getBooleanDefault()
	 */
	@Override
	public Boolean getBooleanDefault()
	{
		return this.booleanDefault;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultProperties#setBooleanDefault(java.lang.Boolean)
	 */
	@Override
	public void setBooleanDefault(Boolean defaultBoolean)
	{
		this.booleanDefault = defaultBoolean;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotDefaultCheck#isBooleanNotDefault(java.lang.Object, java.lang.Boolean)
	 */
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
			this.pushContractWithCaller(caller, IObjectBooleanNotDefaultCheck.class, new Object[] { caller, referenceA }, new Object[] { this.getBooleanDefault() });
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultCheck#isBooleanDefault(java.lang.Object, java.lang.Boolean)
	 */
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
			this.pushContractWithCaller(caller, IObjectBooleanDefaultCheck.class, new Object[] { caller, referenceA }, new Object[] { this.getBooleanDefault() });
			result = true;
		}

		return result;
	}

	
	// OBJECTS - BOOLEANS - END

	
	
	// OBJECTS - ENUMS - START

	@Override
	public boolean isEnumNotSame(Object caller, Enum<?> referenceA,
			Enum<?> referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA != referenceB)
		{
			this.pushContractWithCaller(caller, IObjectEnumNotSameCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}

	@Override
	public boolean isEnumSame(Object caller, Enum<?> referenceA,
			Enum<?> referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA == referenceB)
		{
			this.pushContractWithCaller(caller, IObjectEnumSameCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNotEqualsCheck#isEnumNotEquals(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public boolean isEnumNotEquals(Object caller, Enum<?> referenceA,
			Enum<?> referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if ((null == referenceA && null != referenceB)
				|| (null != referenceA && !referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectEnumNotEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumEqualsCheck#isEnumEquals(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public boolean isEnumEquals(Object caller, Enum<?> referenceA,
			Enum<?> referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if ((null == referenceA && null == referenceB)
				|| (null != referenceA && referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectEnumEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNotNullCheck#isEnumNotNull(java.lang.Object, java.lang.Enum)
	 */
	@Override
	public boolean isEnumNotNull(Object caller, Enum<?> referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA)
		{
			this.pushContractWithCaller(caller, IObjectEnumNotNullCheck.class, new Object[] { caller, referenceA });
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNullCheck#isEnumNull(java.lang.Object, java.lang.Enum)
	 */
	@Override
	public boolean isEnumNull(Object caller, Enum<?> referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA)
		{
			this.pushContractWithCaller(caller, IObjectEnumNullCheck.class, new Object[] { caller, referenceA });
			result = true;
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<Class<? extends Enum>, Enum<?>> enumDefaults = new HashMap<Class<? extends Enum>, Enum<?>>();
	@SuppressWarnings("rawtypes")
	protected HashMap<Class<? extends Enum>, Enum<?>> getEnumDefaults()
	{
		return this.enumDefaults;
	}
	
	@Override
	public Enum<?> getEnumDefault(Class<? extends Enum<?>> enumType)
	{
		Enum<?> result = this.getEnumDefaults().get(enumType);
		if(null == result)
		{ // cache this to avoid repeated array creations
			Enum<?>[] enumConstants = enumType.getEnumConstants();
			if(null != enumConstants && 0 < enumConstants.length)
			{
				result = enumConstants[0];
				this.getEnumDefaults().put(enumType, result);
			}
		}
		return result;
	}

	@Override
	public void setEnumDefault(Enum<?> defaultEnum)
	{
		this.getEnumDefaults().put(defaultEnum.getClass(), defaultEnum);
	}

	@Override
	public boolean isEnumNotDefault(Object caller, Enum<?> referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		Enum<?> enumDefault = null;
		if (null == referenceA)		
		{
			result = true;
		}
		else
		{
			enumDefault = this.getEnumDefault(referenceA.getDeclaringClass());
			if(enumDefault != referenceA)
			{
				result = true;
			}
		}
		if(result)
		{
			this.pushContractWithCaller(caller, IObjectEnumNotDefaultCheck.class, new Object[] { caller, referenceA }, new Object[] { enumDefault });
		}

		return result;
	}

	@Override
	public boolean isEnumDefault(Object caller, Enum<?> referenceA)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA)
		{
			Enum<?> enumDefault = this.getEnumDefault(referenceA.getDeclaringClass());
			if(enumDefault == referenceA)
			{
				this.pushContractWithCaller(caller, IObjectEnumDefaultCheck.class, new Object[] { caller, referenceA }, new Object[] { enumDefault });
				result = true;
			}
		}

		return result;
	}

	
	
	// OBJECTS - ENUMS - END

	
	
	// OBJECTS - STRINGS - START

	@Override
	public boolean isStringNotSame(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA != referenceB)
		{
			this.pushContractWithCaller(caller, IObjectStringNotSameCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}

	@Override
	public boolean isStringSame(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (referenceA == referenceB)
		{
			this.pushContractWithCaller(caller, IObjectStringSameCheck.class, new Object[] { caller, referenceA, referenceB });
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

		if ((null == referenceA && null == referenceB)
				|| (null != referenceA && referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectStringEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
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

		if ((null == referenceA && null != referenceB)
				|| (null != referenceA && !referenceA.equals(referenceB)))
		{
			this.pushContractWithCaller(caller, IObjectStringNotEqualsCheck.class, new Object[] { caller, referenceA, referenceB });
			result = true;
		}

		return result;
	}
	
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
		String stringDefault = this.getStringDefault();
		if (stringDefault.equals(referenceA))
		{
			this.pushContractWithCaller(caller, IObjectStringDefaultCheck.class, new Object[] { caller, referenceA }, new Object[] { stringDefault });
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
		String stringDefault = this.getStringDefault(); 
		if (!stringDefault.equals(referenceA))
		{
			this.pushContractWithCaller(caller, IObjectStringNotDefaultCheck.class, new Object[] { caller, referenceA }, new Object[] { stringDefault });
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
			this.pushContractWithCaller(caller, IObjectStringNullCheck.class, new Object[] { caller, referenceA });
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
			this.pushContractWithCaller(caller, IObjectStringNotNullCheck.class, new Object[] { caller, referenceA });
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
			this.pushContractWithCaller(caller, IObjectStringEmptyCheck.class, new Object[] { caller, referenceA }, new Object[] { stringEmpty });
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
			this.pushContractWithCaller(caller, IObjectStringNotEmptyCheck.class, new Object[] { caller, referenceA }, new Object[] { stringEmpty });
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
			this.pushContractWithCaller(caller, IObjectStringNullOrEmptyCheck.class, new Object[] { caller, referenceA }, new Object[] { stringEmpty });
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
			this.pushContractWithCaller(caller, IObjectStringNotNullAndNotEmptyCheck.class, new Object[] { caller, referenceA }, new Object[] { stringEmpty });
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithPostfixCheck#isStringWithPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithPostfix(Object caller, String referenceA,
			String postfix)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != postfix && referenceA.endsWith(postfix))
		{
			this.pushContractWithCaller(caller, IObjectStringWithPostfixCheck.class, new Object[] { caller, referenceA, postfix });
			result = true;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPostfixCheck#isStringWithoutPostfix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithoutPostfix(Object caller, String referenceA,
			String postfix)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == postfix || !referenceA.endsWith(postfix))
		{
			this.pushContractWithCaller(caller, IObjectStringWithoutPostfixCheck.class, new Object[] { caller, referenceA, postfix });
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithSubstringCheck#isStringWithSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithSubstring(Object caller, String referenceA,
			String substring)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != substring && 0 <= referenceA.indexOf(substring))
		{
			this.pushContractWithCaller(caller, IObjectStringWithSubstringCheck.class, new Object[] { caller, referenceA, substring });
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutSubstringCheck#isStringWithoutSubstring(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithoutSubstring(Object caller, String referenceA,
			String substring)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == substring || referenceA.indexOf(substring) < 0)
		{
			this.pushContractWithCaller(caller, IObjectStringWithoutSubstringCheck.class, new Object[] { caller, referenceA, substring });
			result = true;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithoutPrefixCheck#isStringWithoutPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithoutPrefix(Object caller, String referenceA,
			String prefix)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == referenceA || null == prefix || !referenceA.startsWith(prefix))
		{
			this.pushContractWithCaller(caller, IObjectStringWithoutPrefixCheck.class, new Object[] { caller, referenceA, prefix });
			result = true;
		}

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringWithPrefixCheck#isStringWithPrefix(java.lang.Object, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isStringWithPrefix(Object caller, String referenceA,
			String prefix)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != referenceA && null != prefix && referenceA.startsWith(prefix))
		{
			this.pushContractWithCaller(caller, IObjectStringWithPrefixCheck.class, new Object[] { caller, referenceA, prefix });
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
			this.pushContractWithCaller(caller, IObjectStringNotMatchingCheck.class, new Object[] { caller, referenceA}, new Object[] { regex });
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
			this.pushContractWithCaller(caller, IObjectStringMatchingCheck.class, new Object[] { caller, referenceA}, new Object[] { regex });
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
			this.pushContractWithCaller(caller, IPrimitiveBooleanEqualsCheck.class, new Object[] { caller, valueA, valueB });
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
			this.pushContractWithCaller(caller, IPrimitiveBooleanNotEqualsCheck.class, new Object[] { caller, valueA, valueB });
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

		boolean booleanFalse = false;
		if (booleanFalse == valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanFalseCheck.class, new Object[] { caller, valueA }, new Object[] { booleanFalse });
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

		boolean booleanTrue = true;
		if (booleanTrue == valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanTrueCheck.class, new Object[] { caller, valueA }, new Object[] { booleanTrue });
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
		boolean booleanDefault = this.getBooleanValueDefault();
		if (valueA != booleanDefault)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanNotDefaultCheck.class, new Object[] { caller, valueA}, new Object[] { booleanDefault });
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

		boolean booleanDefault = this.getBooleanValueDefault();
		if (valueA == booleanDefault)
		{
			this.pushContractWithCaller(caller, IPrimitiveBooleanDefaultCheck.class, new Object[] { caller, valueA }, new Object[] { booleanDefault });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsCheck.class, new Object[] { caller, valueA, valueB });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsCheck.class, new Object[] { caller, valueA, valueB });
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
		float floatValueDefault = this.getFloatValueDefault();
		if (floatValueDefault == valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatDefaultCheck.class, new Object[] { caller, valueA}, new Object[] { floatValueDefault });
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
		float floatValueDefault = this.getFloatValueDefault();
		if (floatValueDefault != valueA)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatNotDefaultCheck.class, new Object[] { caller, valueA}, new Object[] { floatValueDefault });
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
		
		float lowB = (1f - Math.signum(valueA) * relativeEpsilon) * valueA - absoluteEpsilon;
		float highB = (1f + Math.signum(valueA) * relativeEpsilon) * valueA + absoluteEpsilon;
		if(valueA == valueB)
		{
			result = true;
		}
		else 
		{
			result = (lowB <= valueB && valueB <= highB);
		}

		if(result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsAlmostCheck.class, new Object[] { caller, valueA, valueB }, new Object[] { absoluteEpsilon, relativeEpsilon, lowB, highB });
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
		
		float lowB = (1f - Math.signum(valueA) * relativeEpsilon) * valueA - absoluteEpsilon;
		float highB = (1f + Math.signum(valueA) * relativeEpsilon) * valueA + absoluteEpsilon;
		if(valueA != valueB)
		{
			result = (valueB < lowB ||  highB < valueB);
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsAlmostCheck.class, new Object[] { caller, valueA, valueB }, new Object[] { absoluteEpsilon, relativeEpsilon, lowB, highB });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatLessCheck.class, new Object[] { caller, valueA, valueB });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatLessEqualsCheck.class, new Object[] { caller, valueA, valueB });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatGreaterCheck.class, new Object[] { caller, valueA, valueB });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatGreaterEqualsCheck.class, new Object[] { caller, valueA, valueB });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatOutsideCheck.class, new Object[] { caller, valueA, valueMin, valueMax });
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
			this.pushContractWithCaller(caller, IPrimitiveFloatWithinCheck.class, new Object[] { caller, valueA, valueMin, valueMax });
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
	
	protected final static Object[] EmptyObjectArray = new Object[]{};

	protected void pushContractWithCaller(Object caller,
			Class<? extends ICheck> checkerSpecification, Object[] checkArguments)
	{
		this.pushContractWithCaller(caller, checkerSpecification, checkArguments, EmptyObjectArray);
	}
	
	protected void pushContractWithCaller(Object caller,
			Class<? extends ICheck> checkerSpecification, Object[] checkArguments, Object[] checkExtraArguments)
	{
		ICallContractor callContractor = this.getCallContractor();
		if (null == callContractor)
		{
			throw new IllegalStateException(
					"CallContractor must be set before using this checker.");
		}

		// require a fail call from caller
		callContractor.pushContractWithCaller(caller, this,
				checkerSpecification, checkArguments, checkExtraArguments);
	}
	
}
