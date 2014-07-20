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
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanFalseCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotEqualsCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck;
import starkcoder.failfast.checks.objects.booleans.IObjectBooleanTrueCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanEqualsCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanFalseCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotEqualsCheck;
import starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanTrueCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck;
import starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsCheck;
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
/**
 * @author Keld Oelykke
 *
 */
/**
 * @author Keld Oelykke
 *
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

	// OBJECTS - BOOLEANS - END

	
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

	
	private float floatValueEqualsAlmostDefaultAllowedRelativeDifference = 0.001f;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#getFloatValueEqualsAlmostDefaultAllowedRelativeDifference()
	 */
	@Override
	public float getFloatValueEqualsAlmostDefaultAllowedRelativeDifference()
	{
		return this.floatValueEqualsAlmostDefaultAllowedRelativeDifference;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#setFloatValueEqualsAlmostDefaultAllowedRelativeDifference(float)
	 */
	@Override
	public void setFloatValueEqualsAlmostDefaultAllowedRelativeDifference(
			float defaultAllowedRelativeDifference)
	{
		this.floatValueEqualsAlmostDefaultAllowedRelativeDifference = defaultAllowedRelativeDifference;
	}

	
	private float floatValueEqualsAlmostDefaultMinimumDenominator = 0.00001f;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#getFloatValueEqualsAlmostDefaultMinimumDenominator()
	 */
	@Override
	public float getFloatValueEqualsAlmostDefaultMinimumDenominator()
	{
		return this.floatValueEqualsAlmostDefaultMinimumDenominator;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#setFloatValueEqualsAlmostDefaultMinimumDenominator(float)
	 */
	@Override
	public void setFloatValueEqualsAlmostDefaultMinimumDenominator(
			float defaultMinimumDenominator)
	{
		this.floatValueEqualsAlmostDefaultMinimumDenominator = defaultMinimumDenominator;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float)
	 */
	@Override
	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		float defaultAllowedRelativeDifference = this.getFloatValueEqualsAlmostDefaultAllowedRelativeDifference();
		result = this.isFloatValueEqualsAlmost(caller, valueA, valueB, defaultAllowedRelativeDifference);
				
		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float, float)
	 */
	@Override
	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
			float valueB, float allowedRelativeDifference)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		float defaultMinimumDenomiator = this.getFloatValueEqualsAlmostDefaultMinimumDenominator();
		float relativeDifference = Math.abs(valueB-valueA) / Math.max(Math.max(valueA, valueB), defaultMinimumDenomiator);
		if (relativeDifference <= allowedRelativeDifference)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsAlmostCheck.class);
			result = true;
		}

		return result;
	}

	@Override
	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
			float valueB)
	{
		boolean result = false;

		float defaultAllowedRelativeDifference = this.getFloatValueEqualsAlmostDefaultAllowedRelativeDifference();
		result = this.isFloatValueNotEqualsAlmost(caller, valueA, valueB, defaultAllowedRelativeDifference);
				
		return result;
	}

	@Override
	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
			float valueB, float allowedRelativeDifference)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		float defaultMinimumDenomiator = this.getFloatValueEqualsAlmostDefaultMinimumDenominator();
		float relativeDifference = Math.abs(valueB-valueA) / Math.max(Math.max(valueA, valueB), defaultMinimumDenomiator);
		if (allowedRelativeDifference < relativeDifference)
		{
			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsAlmostCheck.class);
			result = true;
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
