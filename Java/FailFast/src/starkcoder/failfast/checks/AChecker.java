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

//	// GENERIC OBJECT - START
//
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.objects.IGenericObjectNullCheck#isGenericObjectNull(java.lang.Object, java.lang.Object)
//	 */
//	@Override
//	public <A extends Object> boolean isGenericObjectNull(Object caller, A reference)
//	{
//		boolean result = false;
//		
//		result = this.isGenericObjectNullImplementation(caller, reference, IGenericObjectNullCheck.class);
//		
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.objects.IGenericObjectNotNullCheck#isGenericObjectNotNull(java.lang.Object, java.lang.Object)
//	 */
//	@Override
//	public <A> boolean isGenericObjectNotNull(Object caller, A reference)
//	{
//		boolean result = false;
//		
//		result = this.isGenericObjectNotNullImplementation(caller, reference, IGenericObjectNotNullCheck.class);
//		
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.objects.IGenericObjectSameCheck#isGenericObjectSame(java.lang.Object, java.lang.Object, java.lang.Object)
//	 */
//	@Override
//	public boolean isGenericObjectSame(Object caller, Object referenceA,
//			Object referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IGenericObjectSameCheck.class);
//		
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.objects.IGenericObjectNotSameCheck#isGenericObjectNotSame(java.lang.Object, java.lang.Object, java.lang.Object)
//	 */
//	@Override
//	public <A, B> boolean isGenericObjectNotSame(Object caller, A referenceA,
//			B referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IGenericObjectNotSameCheck.class);
//		
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.objects.IGenericObjectEqualsCheck#isGenericObjectEquals(java.lang.Object, java.lang.Object, java.lang.Object)
//	 */
//	@Override
//	public <A,B> boolean isGenericObjectEquals(Object caller, A referenceA,
//			B referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IGenericObjectEqualsCheck.class);
//		
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.objects.IGenericObjectNotEqualsCheck#isGenericObjectNotEquals(java.lang.Object, java.lang.Object, java.lang.Object)
//	 */
//	@Override
//	public <A,B> boolean isGenericObjectNotEquals(Object caller, A referenceA,
//			B referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IGenericObjectNotEqualsCheck.class);
//		
//		return result;
//	}
//
//	
//	
//	// GENERIC OBJECT - END

	
	
//	// GENERIC ARRAY - START
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.arrays.IGenericArrayEqualsCheck#isGenericArrayEquals(java.lang.Object, java.lang.Object[], java.lang.Object[])
//	 */
//	@Override
//	public <A,B> boolean isGenericArrayEquals(Object caller, A[] referenceA,
//			B[] referenceB)
//	{
//		boolean result = false;
//
//		result = this.isGenericArrayEqualsImplementation(caller, referenceA, referenceB, IGenericArrayEqualsCheck.class);
//		
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.arrays.IGenericArrayNotEqualsCheck#isGenericArrayNotEquals(java.lang.Object, java.lang.Object[], java.lang.Object[])
//	 */
//	@Override
//	public <A,B> boolean isGenericArrayNotEquals(Object caller, A[] referenceA,
//			B[] referenceB)
//	{
//		boolean result = false;
//
//		result = this.isGenericArrayNotEqualsImplementation(caller, referenceA, referenceB, IGenericArrayNotEqualsCheck.class);
//		
//		return result;
//	}
//	
//	// GENERIC ARRAY - END
//
//
//	// GENERIC COLLECTION - START
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.collections.IGenericCollectionEqualsCheck#isGenericCollectionEquals(java.lang.Object, java.util.Collection, java.util.Collection)
//	 */
//	@Override
//	public <A,B> boolean isGenericCollectionEquals(Object caller,
//			Collection<A> referenceA, Collection<B> referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericCollectionEqualsImplementation(caller, referenceA, referenceB, IGenericCollectionEqualsCheck.class);
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.collections.IGenericCollectionNotEqualsCheck#isGenericCollectionNotEquals(java.lang.Object, java.util.Collection, java.util.Collection)
//	 */
//	@Override
//	public <A,B> boolean isGenericCollectionNotEquals(Object caller,
//			Collection<A> referenceA, Collection<B> referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericCollectionNotEqualsImplementation(caller, referenceA, referenceB, IGenericCollectionNotEqualsCheck.class);
//
//		return result;
//	}
//	
//	// GENERIC COLLECTION - END
//
//	
//
//	// GENERIC LIST - START
//
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.lists.IGenericListEqualsCheck#isGenericListEquals(java.lang.Object, java.util.List, java.util.List)
//	 */
//	@Override
//	public <A,B> boolean isGenericListEquals(Object caller, List<A> referenceA,
//			List<B> referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericListEqualsImplementation(caller, referenceA, referenceB, IGenericListEqualsCheck.class);
//
//		return result;
//	}
//	
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.generics.lists.IGenericListNotEqualsCheck#isGenericListNotEquals(java.lang.Object, java.util.List, java.util.List)
//	 */
//	@Override
//	public <A,B> boolean isGenericListNotEquals(Object caller, List<A> referenceA,
//			List<B> referenceB)
//	{
//		boolean result = false;
//		
//		result = this.isGenericListNotEqualsImplementation(caller, referenceA, referenceB, IGenericListNotEqualsCheck.class);
//
//		return result;
//
//	}
//	
//	// GENERIC LIST - END

	
	
	// OBJECTS - START
	

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectNullCheck#isObjectNull(java.lang.Object, java.lang.Object)
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
	 * @see
	 * starkcoder.failfast.checks.objects.IObjectNotNullCheck#isObjectNotNull
	 * (java.lang.Object, java.lang.Object)
	 */
	@Override
	public <A extends Object> boolean isObjectNotNull(Object caller, A reference)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, reference, IObjectNotNullCheck.class);

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
	public <A extends Object, B extends Object> boolean isObjectEquals(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectEqualsCheck.class);

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
	public <A extends Object, B extends Object> boolean isObjectNotEquals(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectNotEqualsCheck.class);

		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectSameCheck#isObjectSame(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public <A extends Object, B extends Object> boolean isObjectSame(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectSameCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectNotSameCheck#isObjectNotSame(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public <A extends Object, B extends Object> boolean isObjectNotSame(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectNotSameCheck.class);

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

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getObjectDefault(), IObjectDefaultCheck.class);

		return result;
	}
	@Override
	public <A> boolean isObjectNotDefault(Object caller, A referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getObjectDefault(), IObjectNotDefaultCheck.class);

		return result;
	}


	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectArrayEqualsCheck#isObjectArrayEquals(java.lang.Object, java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public <A extends Object, B extends Object> boolean isObjectArrayEquals(Object caller, A[] referenceA,
			B[] referenceB)
	{
		boolean result = false;

		result = this.isGenericArrayEqualsImplementation(caller, referenceA, referenceB, IObjectArrayEqualsCheck.class);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectListEqualsCheck#isObjectListEquals(java.lang.Object, java.util.List, java.util.List)
	 */
	@Override
	public <A extends Object, B extends Object> boolean isObjectListEquals(Object caller, List<A> referenceA,
			List<B> referenceB)
	{
		boolean result = false;
		
		result = this.isGenericListEqualsImplementation(caller, referenceA, referenceB, IObjectListEqualsCheck.class);

		return result;
	}

	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectCollectionEqualsCheck#isObjectCollectionEquals(java.lang.Object, java.util.Collection, java.util.Collection)
	 */
	@Override
	public <A extends Object, B extends Object> boolean isObjectCollectionEquals(Object caller,
			Collection<A> referenceA, Collection<B> referenceB)
	{
		boolean result = false;
		
		result = this.isGenericCollectionEqualsImplementation(caller, referenceA, referenceB, IObjectCollectionEqualsCheck.class);

		return result;
	}

	// OBJECT - END
	
	
	
	// OBJECTS - BEGIN
	
	@Override
	public boolean isObjectsEquals(Object caller, Object[] referenceA,
			Object[] referenceB)
	{
		boolean result = false;

		result = this.isGenericArrayEqualsImplementation(caller, referenceA, referenceB, IObjectsEqualsCheck.class);
		
		return result;
	}
	@Override
	public boolean isObjectsEquals(Object caller, List<Object> referenceA,
			List<Object> referenceB)
	{
		boolean result = false;
		
		result = this.isGenericListEqualsImplementation(caller, referenceA, referenceB, IObjectsEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isObjectsEquals(Object caller,
			Collection<Object> referenceA, Collection<Object> referenceB)
	{
		boolean result = false;
		
		result = this.isGenericCollectionEqualsImplementation(caller, referenceA, referenceB, IObjectsEqualsCheck.class);

		return result;
	}	
	
	@Override
	public boolean isObjectsNotEquals(Object caller, Object[] referenceA,
			Object[] referenceB)
	{
		boolean result = false;

		result = this.isGenericArrayNotEqualsImplementation(caller, referenceA, referenceB, IObjectsNotEqualsCheck.class);
		
		return result;
	}
	@Override
	public boolean isObjectsNotEquals(Object caller, List<Object> referenceA,
			List<Object> referenceB)
	{
		boolean result = false;
		
		result = this.isGenericListNotEqualsImplementation(caller, referenceA, referenceB, IObjectsNotEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isObjectsNotEquals(Object caller,
			Collection<Object> referenceA, Collection<Object> referenceB)
	{
		boolean result = false;
		
		result = this.isGenericCollectionNotEqualsImplementation(caller, referenceA, referenceB, IObjectsNotEqualsCheck.class);

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

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectBooleanNotSameCheck.class);

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

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectBooleanSameCheck.class);

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

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectBooleanEqualsCheck.class);

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

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectBooleanNotEqualsCheck.class);

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNotNullCheck#isBooleanNotNull(java.lang.Object, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanNotNull(Object caller, Boolean referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectBooleanNotNullCheck.class);

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanNullCheck#isBooleanNull(java.lang.Object, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanNull(Object caller, Boolean referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectBooleanNullCheck.class);

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

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getBooleanDefault(), IObjectBooleanNotDefaultCheck.class);

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.booleans.IObjectBooleanDefaultCheck#isBooleanDefault(java.lang.Object, java.lang.Boolean)
	 */
	@Override
	public boolean isBooleanDefault(Object caller, Boolean referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getBooleanDefault(), IObjectBooleanDefaultCheck.class);

		return result;
	}

	
	// OBJECTS - BOOLEANS - END

	
	

	
	// OBJECTS - BYTES - START

	@Override
	public boolean isByteNotSame(Object caller, Byte referenceA,
			Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectByteNotSameCheck.class);

		return result;
	}
	@Override
	public boolean isByteSame(Object caller, Byte referenceA,
			Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectByteSameCheck.class);

		return result;
	}	
	
	@Override
	public boolean isByteEquals(Object caller, Byte referenceA,
			Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectByteEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isByteNotEquals(Object caller, Byte referenceA,
			Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectByteNotEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isByteNotNull(Object caller, Byte referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectByteNotNullCheck.class);

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

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getByteDefault(), IObjectByteDefaultCheck.class);

		return result;
	}
	@Override
	public boolean isByteNotDefault(Object caller, Byte referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getByteDefault(), IObjectByteNotDefaultCheck.class);

		return result;
	}

	
	@Override
	public boolean isByteLess(Object caller, Byte referenceA, Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectByteLessCheck.class);

		return result;
	}

	@Override
	public boolean isByteLessOrEquals(Object caller, Byte referenceA,
			Byte referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectByteLessOrEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isByteGreater(Object caller, Byte referenceA, Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectByteGreaterCheck.class);

		return result;
	}

	@Override
	public boolean isByteGreaterOrEquals(Object caller, Byte referenceA,
			Byte referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectByteGreaterOrEqualsCheck.class);

		return result;
	}

	@Override
	public boolean isByteOutside(Object caller, Byte referenceA,
			Byte referenceMin, Byte referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectByteOutsideCheck.class);

		return result;
	}

	@Override
	public boolean isByteInside(Object caller, Byte referenceA,
			Byte referenceMin, Byte referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectByteInsideCheck.class);

		return result;
	}
	
	
	// OBJECTS - BYTES - END

		
	
	
	// OBJECTS - ENUMS - START

	@Override
	public <A extends Enum<A>, B extends Enum<B>> boolean isEnumNotSame(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectEnumNotSameCheck.class);

		return result;
	}

	@Override
	public <A extends Enum<A>, B extends Enum<B>> boolean isEnumSame(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectEnumSameCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNotEqualsCheck#isEnumNotEquals(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <A extends Enum<A>, B extends Enum<B>> boolean isEnumNotEquals(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectEnumNotEqualsCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumEqualsCheck#isEnumEquals(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <A extends Enum<A>, B extends Enum<B>> boolean isEnumEquals(Object caller, A referenceA,
			B referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectEnumEqualsCheck.class);

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNotNullCheck#isEnumNotNull(java.lang.Object, java.lang.Enum)
	 */
	@Override
	public <A extends Enum<A>> boolean isEnumNotNull(Object caller, A referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectEnumNotNullCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNullCheck#isEnumNull(java.lang.Object, java.lang.Enum)
	 */
	@Override
	public <A extends Enum<A>> boolean isEnumNull(Object caller, A referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectEnumNullCheck.class);

		return result;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<Class<? extends Enum>, Enum<?>> enumDefaults = new HashMap<Class<? extends Enum>, Enum<?>>();
	@SuppressWarnings("rawtypes")
	protected HashMap<Class<? extends Enum>, Enum<?>> getEnumDefaults()
	{
		return this.enumDefaults;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultProperties#getEnumDefault(java.lang.Class)
	 */
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

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultProperties#setEnumDefault(java.lang.Enum)
	 */
	@Override
	public void setEnumDefault(Enum<?> defaultEnum)
	{
		this.getEnumDefaults().put(defaultEnum.getClass(), defaultEnum);
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumNotDefaultCheck#isEnumNotDefault(java.lang.Object, java.lang.Enum)
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

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getEnumDefault(referenceA.getDeclaringClass()), IObjectEnumNotDefaultCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumDefaultCheck#isEnumDefault(java.lang.Object, java.lang.Enum)
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
//			throw new IllegalArgumentException("referenceA is null");
			result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getEnumDefault(referenceA.getDeclaringClass()), IObjectEnumDefaultCheck.class);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumLessCheck#isEnumLess(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <T extends Enum<T>> boolean isEnumLess(Object caller, T referenceA, T referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectEnumLessCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumLessOrEqualsCheck#isEnumLessOrEquals(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <T extends Enum<T>> boolean isEnumLessOrEquals(Object caller, T referenceA,
			T referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectEnumLessOrEqualsCheck.class);

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumGreaterCheck#isEnumGreater(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <T extends Enum<T>> boolean isEnumGreater(Object caller, T referenceA, T referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectEnumGreaterCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumGreaterOrEqualsCheck#isEnumGreaterOrEquals(java.lang.Object, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <T extends Enum<T>> boolean isEnumGreaterOrEquals(Object caller, T referenceA,
			T referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectEnumGreaterOrEqualsCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumOutsideCheck#isEnumOutside(java.lang.Object, java.lang.Enum, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <T extends Enum<T>> boolean isEnumOutside(Object caller, T referenceA,
			T referenceMin, T referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectEnumOutsideCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.enums.IObjectEnumInsideCheck#isEnumInside(java.lang.Object, java.lang.Enum, java.lang.Enum, java.lang.Enum)
	 */
	@Override
	public <T extends Enum<T>> boolean isEnumInside(Object caller, T referenceA,
			T referenceMin, T referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectEnumInsideCheck.class);

		return result;
	}
	
	// OBJECTS - ENUMS - END

	
	

	// OBJECTS - FLOATS - START

	@Override
	public boolean isFloatNotSame(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectFloatNotSameCheck.class);

		return result;
	}
	@Override
	public boolean isFloatSame(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectFloatSameCheck.class);

		return result;
	}	
	
	@Override
	public boolean isFloatEquals(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectFloatEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isFloatNotEquals(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectFloatNotEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isFloatNotNull(Object caller, Float referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectFloatNotNullCheck.class);

		return result;
	}
	@Override
	public boolean isFloatNull(Object caller, Float referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectFloatNullCheck.class);

		return result;
	}
	
	private Float floatDefault = 0f;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultProperties#getFloatDefault()
	 */
	@Override
	public Float getFloatDefault()
	{
		return this.floatDefault;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultProperties#setFloatDefault(java.lang.Float)
	 */
	@Override
	public void setFloatDefault(Float defaultFloat)
	{
		this.floatDefault = defaultFloat;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatDefaultCheck#isFloatDefault(java.lang.Object, java.lang.Float)
	 */
	@Override
	public boolean isFloatDefault(Object caller, Float referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getFloatDefault(), IObjectFloatDefaultCheck.class);

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatNotDefaultCheck#isFloatNotDefault(java.lang.Object, java.lang.Float)
	 */
	@Override
	public boolean isFloatNotDefault(Object caller, Float referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getFloatDefault(), IObjectFloatNotDefaultCheck.class);

		return result;
	}

	private Float floatEqualsAlmostDefaultAbsoluteEpsilon = 0.00001f;
	@Override
	public Float getFloatEqualsAlmostDefaultAbsoluteEpsilon()
	{
		return this.floatEqualsAlmostDefaultAbsoluteEpsilon;
	}
	@Override
	public void setFloatEqualsAlmostDefaultAbsoluteEpsilon(
			Float defaultAbsoluteEpsilon)
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
	public void setFloatEqualsAlmostDefaultRelativeEpsilon(
			Float defaultRelativeEpsilon)
	{
		this.floatEqualsAlmostDefaultRelativeEpsilon = Math.abs(defaultRelativeEpsilon);
	}	
	
	@Override
	public boolean isFloatEqualsAlmost(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		Float defaultAbsoluteEpsilon = this.getFloatEqualsAlmostDefaultAbsoluteEpsilon();
		Float defaultRelativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatEqualsAlmost(caller, referenceA, referenceB, defaultAbsoluteEpsilon, defaultRelativeEpsilon);
				
		return result;
	}
	@Override
	public boolean isFloatEqualsAlmost(Object caller, Float referenceA,
			Float referenceB, Float absoluteEpsilon)
	{
		boolean result = false;

		Float defaultRelativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon, defaultRelativeEpsilon);
				
		return result;
	}
	@Override
	public boolean isFloatEqualsAlmost(Object caller, Float referenceA,
			Float referenceB, Float absoluteEpsilon, Float relativeEpsilon)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		Float lowB = null;
		Float highB = null;
		if(referenceA == referenceB)
		{
			result = true;
		}
		else 
		{
			lowB = (1f - Math.signum(referenceA) * relativeEpsilon) * referenceA - absoluteEpsilon;
			highB = (1f + Math.signum(referenceA) * relativeEpsilon) * referenceA + absoluteEpsilon;
			result = (lowB <= referenceB && referenceB <= highB);
		}

		if(result)
		{
			this.pushContractWithCaller(caller, IObjectFloatEqualsAlmostCheck.class, new Object[] { caller, referenceA, referenceB }, new Object[] { absoluteEpsilon, relativeEpsilon, lowB, highB });
		}

		return result;
	}

	@Override
	public boolean isFloatNotEqualsAlmost(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		Float absoluteEpsilon = this.getFloatEqualsAlmostDefaultAbsoluteEpsilon();
		Float relativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatNotEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon, relativeEpsilon);
				
		return result;
	}
	@Override
	public boolean isFloatNotEqualsAlmost(Object caller, Float referenceA,
			Float referenceB, Float absoluteEpsilon)
	{
		boolean result = false;

		Float relativeEpsilon = this.getFloatEqualsAlmostDefaultRelativeEpsilon();
		result = this.isFloatNotEqualsAlmost(caller, referenceA, referenceB, absoluteEpsilon, relativeEpsilon);
				
		return result;
	}
	@Override
	public boolean isFloatNotEqualsAlmost(Object caller, Float referenceA,
			Float referenceB, Float absoluteEpsilon, Float relativeEpsilon)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		
		Float lowB = null;
		Float highB = null;
		if(referenceA != referenceB)
		{
			lowB = (1f - Math.signum(referenceA) * relativeEpsilon) * referenceA - absoluteEpsilon;
			highB = (1f + Math.signum(referenceA) * relativeEpsilon) * referenceA + absoluteEpsilon;
			result = (referenceB < lowB ||  highB < referenceB);
		}
		// else false
			
		if (result)
		{
			this.pushContractWithCaller(caller, IObjectFloatNotEqualsAlmostCheck.class, new Object[] { caller, referenceA, referenceB }, new Object[] { absoluteEpsilon, relativeEpsilon, lowB, highB });
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatLessCheck#isFloatLess(java.lang.Object, java.lang.Float, java.lang.Float)
	 */
	@Override
	public boolean isFloatLess(Object caller, Float referenceA, Float referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectFloatLessCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatLessOrEqualsCheck#isFloatLessOrEquals(java.lang.Object, java.lang.Float, java.lang.Float)
	 */
	@Override
	public boolean isFloatLessOrEquals(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectFloatLessOrEqualsCheck.class);

		return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatGreaterCheck#isFloatGreater(java.lang.Object, java.lang.Float, java.lang.Float)
	 */
	@Override
	public boolean isFloatGreater(Object caller, Float referenceA, Float referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectFloatGreaterCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatGreaterOrEqualsCheck#isFloatGreaterOrEquals(java.lang.Object, java.lang.Float, java.lang.Float)
	 */
	@Override
	public boolean isFloatGreaterOrEquals(Object caller, Float referenceA,
			Float referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectFloatGreaterOrEqualsCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatOutsideCheck#isFloatOutside(java.lang.Object, java.lang.Float, java.lang.Float, java.lang.Float)
	 */
	@Override
	public boolean isFloatOutside(Object caller, Float referenceA,
			Float referenceMin, Float referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectFloatOutsideCheck.class);

		return result;
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.floats.IObjectFloatInsideCheck#isFloatInside(java.lang.Object, java.lang.Float, java.lang.Float, java.lang.Float)
	 */
	@Override
	public boolean isFloatInside(Object caller, Float referenceA,
			Float referenceMin, Float referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectFloatInsideCheck.class);

		return result;
	}
	
	
	// OBJECTS - FLOATS - END
	

	
	// OBJECTS - SHORTS - START

	@Override
	public boolean isShortNotSame(Object caller, Short referenceA,
			Short referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectShortNotSameCheck.class);

		return result;
	}
	@Override
	public boolean isShortSame(Object caller, Short referenceA,
			Short referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectShortSameCheck.class);

		return result;
	}	
	
	@Override
	public boolean isShortEquals(Object caller, Short referenceA,
			Short referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectShortEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isShortNotEquals(Object caller, Short referenceA,
			Short referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectShortNotEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isShortNotNull(Object caller, Short referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectShortNotNullCheck.class);

		return result;
	}
	@Override
	public boolean isShortNull(Object caller, Short referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectShortNullCheck.class);

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

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getShortDefault(), IObjectShortDefaultCheck.class);

		return result;
	}
	@Override
	public boolean isShortNotDefault(Object caller, Short referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getShortDefault(), IObjectShortNotDefaultCheck.class);

		return result;
	}

	
	@Override
	public boolean isShortLess(Object caller, Short referenceA, Short referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectShortLessCheck.class);

		return result;
	}

	@Override
	public boolean isShortLessOrEquals(Object caller, Short referenceA,
			Short referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectShortLessOrEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isShortGreater(Object caller, Short referenceA, Short referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectShortGreaterCheck.class);

		return result;
	}

	@Override
	public boolean isShortGreaterOrEquals(Object caller, Short referenceA,
			Short referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectShortGreaterOrEqualsCheck.class);

		return result;
	}

	@Override
	public boolean isShortOutside(Object caller, Short referenceA,
			Short referenceMin, Short referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectShortOutsideCheck.class);

		return result;
	}

	@Override
	public boolean isShortInside(Object caller, Short referenceA,
			Short referenceMin, Short referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectShortInsideCheck.class);

		return result;
	}
	
	
	// OBJECTS - SHORTS - END

	
	
	
	// OBJECTS - INTEGERS - START

	@Override
	public boolean isIntegerNotSame(Object caller, Integer referenceA,
			Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectIntegerNotSameCheck.class);

		return result;
	}
	@Override
	public boolean isIntegerSame(Object caller, Integer referenceA,
			Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectIntegerSameCheck.class);

		return result;
	}	
	
	@Override
	public boolean isIntegerEquals(Object caller, Integer referenceA,
			Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectIntegerEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isIntegerNotEquals(Object caller, Integer referenceA,
			Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectIntegerNotEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isIntegerNotNull(Object caller, Integer referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectIntegerNotNullCheck.class);

		return result;
	}
	@Override
	public boolean isIntegerNull(Object caller, Integer referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectIntegerNullCheck.class);

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

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getIntegerDefault(), IObjectIntegerDefaultCheck.class);

		return result;
	}
	@Override
	public boolean isIntegerNotDefault(Object caller, Integer referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getIntegerDefault(), IObjectIntegerNotDefaultCheck.class);

		return result;
	}

	
	@Override
	public boolean isIntegerLess(Object caller, Integer referenceA, Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectIntegerLessCheck.class);

		return result;
	}

	@Override
	public boolean isIntegerLessOrEquals(Object caller, Integer referenceA,
			Integer referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectIntegerLessOrEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isIntegerGreater(Object caller, Integer referenceA, Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectIntegerGreaterCheck.class);

		return result;
	}

	@Override
	public boolean isIntegerGreaterOrEquals(Object caller, Integer referenceA,
			Integer referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectIntegerGreaterOrEqualsCheck.class);

		return result;
	}

	@Override
	public boolean isIntegerOutside(Object caller, Integer referenceA,
			Integer referenceMin, Integer referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectIntegerOutsideCheck.class);

		return result;
	}

	@Override
	public boolean isIntegerInside(Object caller, Integer referenceA,
			Integer referenceMin, Integer referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectIntegerInsideCheck.class);

		return result;
	}
	
	
	// OBJECTS - INTEGERS - END


	
	// OBJECTS - LONGS - START

	@Override
	public boolean isLongNotSame(Object caller, Long referenceA,
			Long referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectLongNotSameCheck.class);

		return result;
	}
	@Override
	public boolean isLongSame(Object caller, Long referenceA,
			Long referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectLongSameCheck.class);

		return result;
	}	
	
	@Override
	public boolean isLongEquals(Object caller, Long referenceA,
			Long referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectLongEqualsCheck.class);

		return result;
	}
	@Override
	public boolean isLongNotEquals(Object caller, Long referenceA,
			Long referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectLongNotEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isLongNotNull(Object caller, Long referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectLongNotNullCheck.class);

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

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getLongDefault(), IObjectLongDefaultCheck.class);

		return result;
	}
	@Override
	public boolean isLongNotDefault(Object caller, Long referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getLongDefault(), IObjectLongNotDefaultCheck.class);

		return result;
	}

	
	@Override
	public boolean isLongLess(Object caller, Long referenceA, Long referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectLongLessCheck.class);

		return result;
	}

	@Override
	public boolean isLongLessOrEquals(Object caller, Long referenceA,
			Long referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectLongLessOrEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isLongGreater(Object caller, Long referenceA, Long referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectLongGreaterCheck.class);

		return result;
	}

	@Override
	public boolean isLongGreaterOrEquals(Object caller, Long referenceA,
			Long referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectLongGreaterOrEqualsCheck.class);

		return result;
	}

	@Override
	public boolean isLongOutside(Object caller, Long referenceA,
			Long referenceMin, Long referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableOutsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectLongOutsideCheck.class);

		return result;
	}

	@Override
	public boolean isLongInside(Object caller, Long referenceA,
			Long referenceMin, Long referenceMax)
	{
		boolean result = false;

		result = this.isGenericComparableInsideImplementation(caller, referenceA, referenceMin, referenceMax, IObjectLongInsideCheck.class);

		return result;
	}
	
	
	// OBJECTS - LONGS - END
	
	
	
	// OBJECTS - STRINGS - START

	@Override
	public boolean isStringNotSame(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectNotSameImplementation(caller, referenceA, referenceB, IObjectStringNotSameCheck.class);

		return result;
	}

	@Override
	public boolean isStringSame(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		result = this.isGenericObjectSameImplementation(caller, referenceA, referenceB, IObjectStringSameCheck.class);

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

		result = this.isGenericObjectEqualsImplementation(caller, referenceA, referenceB, IObjectStringEqualsCheck.class);

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

		result = this.isGenericObjectNotEqualsImplementation(caller, referenceA, referenceB, IObjectStringNotEqualsCheck.class);

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

		result = this.isGenericObjectDefaultImplementation(caller, referenceA, this.getStringDefault(), IObjectStringDefaultCheck.class);

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotDefaultCheck#isStringNotDefault(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringNotDefault(Object caller, String referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotDefaultImplementation(caller, referenceA, this.getStringDefault(), IObjectStringNotDefaultCheck.class);

		return result;
	}	
	


	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNullCheck#isStringNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringNull(Object caller, String referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNullImplementation(caller, referenceA, IObjectStringNullCheck.class);

		return result;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.strings.IObjectStringNotNullCheck#isStringNotNull(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isStringNotNull(Object caller, String referenceA)
	{
		boolean result = false;

		result = this.isGenericObjectNotNullImplementation(caller, referenceA, IObjectStringNotNullCheck.class);

		return result;
	}
	
	@Override
	public boolean isStringLess(Object caller, String referenceA, String referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableLessImplementation(caller, referenceA, referenceB, IObjectStringLessCheck.class);

		return result;
	}

	@Override
	public boolean isStringLessOrEquals(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;
		
		result = this.isGenericComparableLessOrEqualsImplementation(caller, referenceA, referenceB, IObjectStringLessOrEqualsCheck.class);

		return result;
	}
	
	@Override
	public boolean isStringGreater(Object caller, String referenceA, String referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterImplementation(caller, referenceA, referenceB, IObjectStringGreaterCheck.class);

		return result;
	}

	@Override
	public boolean isStringGreaterOrEquals(Object caller, String referenceA,
			String referenceB)
	{
		boolean result = false;

		result = this.isGenericComparableGreaterOrEqualsImplementation(caller, referenceA, referenceB, IObjectStringGreaterOrEqualsCheck.class);

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

	
	
//	// PRIMITIVES - BOOLEANS - START
//
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanEqualsCheck#isObjectEquals(java.lang.Object, boolean, boolean)
//	 */
//	@Override
//	public boolean isBooleanValueEquals(Object caller, boolean valueA, boolean valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		if (valueA == valueB)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveBooleanEqualsCheck.class, new Object[] { caller, valueA, valueB });
//			result = true;
//		}
//
//		return result;
//	}
//
//
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotEqualsCheck#isObjectNotEquals(java.lang.Object, boolean, boolean)
//	 */
//	@Override
//	public boolean isBooleanValueNotEquals(Object caller, boolean valueA,
//			boolean valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		if (valueA != valueB)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveBooleanNotEqualsCheck.class, new Object[] { caller, valueA, valueB });
//			result = true;
//		}
//
//		return result;
//	}
//	
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanFalseCheck#isBooleanValueFalse(java.lang.Object, boolean)
//	 */
//	@Override
//	public boolean isBooleanValueFalse(Object caller, boolean valueA)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		boolean booleanFalse = false;
//		if (booleanFalse == valueA)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveBooleanFalseCheck.class, new Object[] { caller, valueA }, new Object[] { booleanFalse });
//			result = true;
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanTrueCheck#isBooleanValueTrue(java.lang.Object, boolean)
//	 */
//	@Override
//	public boolean isBooleanValueTrue(Object caller, boolean valueA)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		boolean booleanTrue = true;
//		if (booleanTrue == valueA)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveBooleanTrueCheck.class, new Object[] { caller, valueA }, new Object[] { booleanTrue });
//			result = true;
//		}
//
//		return result;
//	}
//
//	
//	private boolean booleanValueDefault;
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultProperties#getBooleanValueDefault()
//	 */
//	@Override
//	public boolean getBooleanValueDefault()
//	{
//		return this.booleanValueDefault;
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultProperties#setBooleanValueDefault(boolean)
//	 */
//	@Override
//	public void setBooleanValueDefault(boolean defaultBooleanValue)
//	{
//		this.booleanValueDefault = defaultBooleanValue;
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanNotDefaultCheck#isBooleanValueNotDefault(java.lang.Object, boolean)
//	 */
//	@Override
//	public boolean isBooleanValueNotDefault(Object caller, boolean valueA)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		boolean booleanDefault = this.getBooleanValueDefault();
//		if (valueA != booleanDefault)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveBooleanNotDefaultCheck.class, new Object[] { caller, valueA}, new Object[] { booleanDefault });
//			result = true;
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.booleans.IPrimitiveBooleanDefaultCheck#isBooleanValueDefault(java.lang.Object, boolean)
//	 */
//	@Override
//	public boolean isBooleanValueDefault(Object caller, boolean valueA)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		boolean booleanDefault = this.getBooleanValueDefault();
//		if (valueA == booleanDefault)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveBooleanDefaultCheck.class, new Object[] { caller, valueA }, new Object[] { booleanDefault });
//			result = true;
//		}
//
//		return result;
//	}
//
//	
//	// PRIMITIVES - BOOLEANS - END
	
	
	// PRIMITIVES - FLOATS - START
	
	
	
//	// PRIMITIVES - FLOATS - END
//	
//
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsCheck#isFloatValueEquals(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueEquals(Object caller, float valueA, float valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		if (valueA == valueB)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsCheck.class, new Object[] { caller, valueA, valueB });
//			result = true;
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsCheck#isFloatValueNotEquals(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueNotEquals(Object caller, float valueA,
//			float valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//
//		if (valueA != valueB)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsCheck.class, new Object[] { caller, valueA, valueB });
//			result = true;
//		}
//
//		return result;	
//	}
//	
//	
//	private float floatValueDefault = 0f;
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultProperties#getFloatValueDefault()
//	 */
//	@Override
//	public float getFloatValueDefault()
//	{
//		return this.floatValueDefault;
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultProperties#setFloatValueDefault(float)
//	 */
//	@Override
//	public void setFloatValueDefault(float defaultFloatValue)
//	{
//		this.floatValueDefault = defaultFloatValue;
//	}
//	
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatDefaultCheck#isFloatValueDefault(java.lang.Object, float)
//	 */
//	@Override
//	public boolean isFloatValueDefault(Object caller, float valueA)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		float floatValueDefault = this.getFloatValueDefault();
//		if (floatValueDefault == valueA)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatDefaultCheck.class, new Object[] { caller, valueA}, new Object[] { floatValueDefault });
//			result = true;
//		}
//
//		return result;
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotDefaultCheck#isFloatValueNotDefault(java.lang.Object, float)
//	 */
//	@Override
//	public boolean isFloatValueNotDefault(Object caller, float valueA)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		float floatValueDefault = this.getFloatValueDefault();
//		if (floatValueDefault != valueA)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatNotDefaultCheck.class, new Object[] { caller, valueA}, new Object[] { floatValueDefault });
//			result = true;
//		}
//
//		return result;
//	}
//
//
//	
//
//	private float floatValueEqualsAlmostDefaultAbsoluteEpsilon = 0.00001f;
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#getFloatValueEqualsAlmostDefaultAbsoluteEpsilon()
//	 */
//	@Override
//	public float getFloatValueEqualsAlmostDefaultAbsoluteEpsilon()
//	{
//		return this.floatValueEqualsAlmostDefaultAbsoluteEpsilon;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#setFloatValueEqualsAlmostDefaultAbsoluteEpsilon(float)
//	 */
//	@Override
//	public void setFloatValueEqualsAlmostDefaultAbsoluteEpsilon(
//			float defaultAbsoluteEpsilon)
//	{
//		this.floatValueEqualsAlmostDefaultAbsoluteEpsilon = Math.abs(defaultAbsoluteEpsilon);
//	}
//	
//
//	private float floatValueEqualsAlmostDefaultRelativeEpsilon = 0.000001f;
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#getFloatValueEqualsAlmostDefaultRelativeEpsilon()
//	 */
//	@Override
//	public float getFloatValueEqualsAlmostDefaultRelativeEpsilon()
//	{
//		return this.floatValueEqualsAlmostDefaultRelativeEpsilon;
//	}
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheckProperties#setFloatValueEqualsAlmostDefaultRelativeEpsilon(float)
//	 */
//	@Override
//	public void setFloatValueEqualsAlmostDefaultRelativeEpsilon(
//			float defaultRelativeEpsilon)
//	{
//		this.floatValueEqualsAlmostDefaultRelativeEpsilon = Math.abs(defaultRelativeEpsilon);
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
//			float valueB)
//	{
//		boolean result = false;
//
//		float defaultAbsoluteEpsilon = this.getFloatValueEqualsAlmostDefaultAbsoluteEpsilon();
//		float defaultRelativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
//		result = this.isFloatValueEqualsAlmost(caller, valueA, valueB, defaultAbsoluteEpsilon, defaultRelativeEpsilon);
//				
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float, float)
//	 */
//	@Override
//	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
//			float valueB, float absoluteEpsilon)
//	{
//		boolean result = false;
//
//		float defaultRelativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
//		result = this.isFloatValueEqualsAlmost(caller, valueA, valueB, absoluteEpsilon, defaultRelativeEpsilon);
//				
//		return result;
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatEqualsAlmostCheck#isFloatValueEqualsAlmost(java.lang.Object, float, float, float, float)
//	 */
//	@Override
//	public boolean isFloatValueEqualsAlmost(Object caller, float valueA,
//			float valueB, float absoluteEpsilon, float relativeEpsilon)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		float lowB = (1f - Math.signum(valueA) * relativeEpsilon) * valueA - absoluteEpsilon;
//		float highB = (1f + Math.signum(valueA) * relativeEpsilon) * valueA + absoluteEpsilon;
//		if(valueA == valueB)
//		{
//			result = true;
//		}
//		else 
//		{
//			result = (lowB <= valueB && valueB <= highB);
//		}
//
//		if(result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatEqualsAlmostCheck.class, new Object[] { caller, valueA, valueB }, new Object[] { absoluteEpsilon, relativeEpsilon, lowB, highB });
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck#isFloatValueNotEqualsAlmost(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
//			float valueB)
//	{
//		boolean result = false;
//
//		float absoluteEpsilon = this.getFloatValueEqualsAlmostDefaultAbsoluteEpsilon();
//		float relativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
//		result = this.isFloatValueNotEqualsAlmost(caller, valueA, valueB, absoluteEpsilon, relativeEpsilon);
//				
//		return result;
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck#isFloatValueNotEqualsAlmost(java.lang.Object, float, float, float)
//	 */
//	@Override
//	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
//			float valueB, float absoluteEpsilon)
//	{
//		boolean result = false;
//
//		float relativeEpsilon = this.getFloatValueEqualsAlmostDefaultRelativeEpsilon();
//		result = this.isFloatValueNotEqualsAlmost(caller, valueA, valueB, absoluteEpsilon, relativeEpsilon);
//				
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatNotEqualsAlmostCheck#isFloatValueNotEqualsAlmost(java.lang.Object, float, float, float, float)
//	 */
//	@Override
//	public boolean isFloatValueNotEqualsAlmost(Object caller, float valueA,
//			float valueB, float absoluteEpsilon, float relativeEpsilon)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		float lowB = (1f - Math.signum(valueA) * relativeEpsilon) * valueA - absoluteEpsilon;
//		float highB = (1f + Math.signum(valueA) * relativeEpsilon) * valueA + absoluteEpsilon;
//		if(valueA != valueB)
//		{
//			result = (valueB < lowB ||  highB < valueB);
//		}
//		// else false
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatNotEqualsAlmostCheck.class, new Object[] { caller, valueA, valueB }, new Object[] { absoluteEpsilon, relativeEpsilon, lowB, highB });
//		}
//
//		return result;
//	}
//
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatLessCheck#isFloatValueLess(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueLess(Object caller, float valueA, float valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		if(valueA < valueB)
//		{
//			result = true;
//		}
//		// else false
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatLessCheck.class, new Object[] { caller, valueA, valueB });
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatLessEqualsCheck#isFloatValueLessEquals(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueLessEquals(Object caller, float valueA,
//			float valueB)
//	{
//		boolean result = false;
//		
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		if(valueA <= valueB)
//		{
//			result = true;
//		}
//		// else false
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatLessEqualsCheck.class, new Object[] { caller, valueA, valueB });
//		}
//
//		return result;
//	}
//	
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatGreaterCheck#isFloatValueGreater(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueGreater(Object caller, float valueA, float valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		if(valueA > valueB)
//		{
//			result = true;
//		}
//		// else false
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatGreaterCheck.class, new Object[] { caller, valueA, valueB });
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatGreaterEqualsCheck#isFloatValueGreaterEquals(java.lang.Object, float, float)
//	 */
//	@Override
//	public boolean isFloatValueGreaterEquals(Object caller, float valueA,
//			float valueB)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		if(valueA >= valueB)
//		{
//			result = true;
//		}
//		// else false
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatGreaterEqualsCheck.class, new Object[] { caller, valueA, valueB });
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatOutsideCheck#isFloatValueOutside(java.lang.Object, float, float, float)
//	 */
//	@Override
//	public boolean isFloatValueOutside(Object caller, float valueA,
//			float valueMin, float valueMax)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		if(valueMin <= valueMax)
//		{
//			if(valueA < valueMin || valueMax < valueA)
//			{
//				result = true;
//			}
//		}
//		else
//		{ // valueMin > valueMax
//			if(valueA < valueMax || valueMin < valueA)
//			{
//				result = true;
//			}
//		}
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatOutsideCheck.class, new Object[] { caller, valueA, valueMin, valueMax });
//		}
//
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see starkcoder.failfast.checks.primitives.floats.IPrimitiveFloatWithinCheck#isFloatValueWithin(java.lang.Object, float, float, float)
//	 */
//	@Override
//	public boolean isFloatValueWithin(Object caller, float valueA,
//			float valueMin, float valueMax)
//	{
//		boolean result = false;
//
//		if (null == caller)
//		{
//			throw new IllegalArgumentException("caller is null");
//		}
//		
//		if(valueMin <= valueMax)
//		{
//			if(valueMin <= valueA && valueA <= valueMax)
//			{
//				result = true;
//			}
//		}
//		else
//		{ // valueMin > valueMax
//			if(valueMax <= valueA && valueA <= valueMin)
//			{
//				result = true;
//			}
//		}
//			
//		if (result)
//		{
//			this.pushContractWithCaller(caller, IPrimitiveFloatWithinCheck.class, new Object[] { caller, valueA, valueMin, valueMax });
//		}
//
//		return result;
//	}

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
	
	
	protected <A,B> boolean isGenericObjectEqualsImplementation(Object caller, A referenceA,
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
		else if(null != referenceA && null != referenceB
			&& referenceA.equals(referenceB) && referenceB.equals(referenceA)) // agree to be equals
		{
			result = true;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}
	
	protected <A,B> boolean isGenericObjectNotEqualsImplementation(Object caller, A referenceA,
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
		else if(null != referenceA && null != referenceB 
			&& referenceA.equals(referenceB) && referenceB.equals(referenceA)) // not only a mirror check
		{
			equals = true;
		}
		if(!equals)
		{
			result = true;
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}
	
	
	protected <A,B> boolean isGenericObjectSameImplementation(Object caller, A referenceA,
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
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}
	
	protected <A,B> boolean isGenericObjectNotSameImplementation(Object caller, A referenceA,
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
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}
	
	
	protected <A> boolean isGenericObjectDefaultImplementation(Object caller, A referenceA, A referenceDefault, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (referenceDefault.equals(referenceA))
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA }, new Object[] { referenceDefault });
			result = true;
		}

		return result;
	}
	
	protected <A> boolean isGenericObjectNotDefaultImplementation(Object caller, A referenceA, A referenceDefault, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
		if (!referenceDefault.equals(referenceA))
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA }, new Object[] { referenceDefault });
			result = true;
		}

		return result;
	}

	
	protected <A> boolean isGenericObjectNullImplementation(Object caller, A reference, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null == reference)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, reference });
			result = true;
		}

		return result;
	}
	
	protected <A> boolean isGenericObjectNotNullImplementation(Object caller, A reference, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if (null != reference)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, reference });
			result = true;
		}

		return result;
	}
	
	
	protected <T extends Comparable<T>> boolean isGenericComparableLessImplementation(Object caller, T referenceA,
			T referenceB, Class<? extends ICheck> checkerSpecification)
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
		else if(null != referenceA && null != referenceB
			&& referenceA.compareTo(referenceB) < 0)
		{
			result = true;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}	
	
	protected <T extends Comparable<T>> boolean isGenericComparableLessOrEqualsImplementation(Object caller, T referenceA,
			T referenceB, Class<? extends ICheck> checkerSpecification)
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
		else if(null != referenceA && null != referenceB 
			&& referenceA.compareTo(referenceB) <= 0)
		{
			result = true;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}	

	
	protected <T extends Comparable<T>> boolean isGenericComparableGreaterImplementation(Object caller, T referenceA,
			T referenceB, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if(null != referenceA && null != referenceB && 0 < referenceA.compareTo(referenceB))
		{
			result = true;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}		

	protected <T extends Comparable<T>> boolean isGenericComparableGreaterOrEqualsImplementation(Object caller, T referenceA,
			T referenceB, Class<? extends ICheck> checkerSpecification)
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
		else if(null != referenceA && null != referenceB 
			&& 0 <= referenceA.compareTo(referenceB))
		{
			result = true;
		}
		if(result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB });
		}

		return result;
	}
	
	protected <T extends Comparable<T>> boolean isGenericComparableInsideImplementation(Object caller, T referenceA,
		T referenceMin, T referenceMax, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if(null != referenceA && null != referenceMin && null != referenceMax)
		{
			if(referenceMin.compareTo(referenceMax) <= 0)
			{
				if(0 <= referenceA.compareTo(referenceMin) && referenceA.compareTo(referenceMax) <= 0)
				{
					result = true;
				}
			}
			else
			{ // referenceMin > referenceMax
				if(0 <= referenceA.compareTo(referenceMax) && referenceA.compareTo(referenceMin) <= 0)
				{
					result = true;
				}
			}
		}
			
		if (result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceMin, referenceMax });
		}

		return result;
	}	
	
	protected <T extends Comparable<T>> boolean isGenericComparableOutsideImplementation(Object caller, T referenceA,
		T referenceMin, T referenceMax, Class<? extends ICheck> checkerSpecification)
	{
		boolean result = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		if(null != referenceA && null != referenceMin && null != referenceMax)
		{
			if(referenceMin.compareTo(referenceMax) <= 0)
			{
				if(referenceA.compareTo(referenceMin) < 0 || 0 < referenceA.compareTo(referenceMax))
				{
					result = true;
				}
			}
			else
			{ // referenceMin > referenceMax
				if(referenceA.compareTo(referenceMax) < 0 || 0 < referenceA.compareTo(referenceMin))
				{
					result = true;
				}
			}
		}
			
		if (result)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceMin, referenceMax });
		}

		return result;
	}	
	
	protected <A,B> boolean isGenericListEqualsImplementation(Object caller, List<A> referenceA,
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
		else if(null != referenceA && null != referenceA
			&& referenceA.size() == referenceB.size())
		{
			boolean result_ = true;
			for(int index = 0; index < referenceA.size(); ++index)
			{
				Object a = referenceA.get(index);
				Object b = referenceB.get(index);
				if(!((null == a && null == b)
					|| (null != a && null != b && a.equals(b) && b.equals(a)))) // agree to be equals
				{
					result_ = false;
					break;
				}
			}
			result = result_;
		}
		if(result)
		{
			int size = 0;
			String toString = null;
			if(null != referenceA)
			{
				size = referenceA.size();
				String sUp = "[";
				String sDown = "]";
				for(int up = 0, down = referenceA.size()-1; up <= down; ++up, --down)
				{
					Object a = referenceA.get(up);
					Object b = referenceB.get(down);
					if(up == down)
					{
						sUp += (up == 0 ? "" : ", ") + (a == null ? "null" : a.toString());
						break;
					}
					else if(5 < up)
					{
						sUp += ", ...";
						break;
					}
					else
					{
						sUp += (up == 0 ? "" : ", ") + (a == null ? "null" : a.toString());
						sDown = ", " + (b == null ? "null" : b.toString()) + sDown;
					}
				}
				toString = sUp + sDown;
			}
			
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB }, new Object[] { size, toString });
		}

		return result;
	}
	
	protected <A,B> boolean isGenericListNotEqualsImplementation(Object caller, List<A> referenceA,
			List<B> referenceB, Class<? extends ICheck> checkerSpecification)
	{
		boolean isEquals = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		int index = 0;
		Object a = null;
		Object b = null;
		if (null == referenceA && null == referenceB)
		{
			isEquals = true;
		}
		else if(null != referenceA && null != referenceA
			&& referenceA.size() == referenceB.size())
		{
			boolean isEquals_ = true;
			for(index = 0; index < referenceA.size(); ++index)
			{
				a = referenceA.get(index);
				b = referenceB.get(index);
				if(!((null == a && null == b)
					|| (null != a && null != b && a.equals(b) && b.equals(a)))) // agree to be equals
				{
					isEquals_ = false;
					break;
				}
			}
			isEquals = isEquals_;
		}
		if(!isEquals)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB }, new Object[] { index, a, b });
		}

		return !isEquals;
	}
	
	
	
	protected <A,B> boolean isGenericCollectionEqualsImplementation(Object caller,
			Collection<A> referenceA, Collection<B> referenceB, Class<? extends ICheck> checkerSpecification)
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
			Iterator<?> iteratorA = referenceA.iterator();
			Iterator<?> iteratorB = referenceB.iterator();
//			int index = -1;
			while(iteratorA.hasNext() && iteratorB.hasNext())
			{
//				++index;
				Object a = iteratorA.next();
				Object b = iteratorB.next();
				if(!((null == a && null == b)
					|| (null != a && null != b && a.equals(b) && b.equals(a)))) // agree to be equals
				{
					result_ = false;
					break;
				}
			}
			result = result_;
		}
		if(result)
		{
			int size = 0;
			String toString = null;
			if(null != referenceA)
			{
				int index = 0;
				size = referenceA.size();
				String sUp = "[";
				Iterator<?> iteratorA = referenceA.iterator();
				while(iteratorA.hasNext())
				{
					Object a = iteratorA.next();
					if(size <= 10)
					{
						sUp += (index == 0 ? "" : ", ") + (a == null ? "null" : a.toString());
					}
					else
					{
						if(index < 5)
						{
							sUp += (index == 0 ? "" : ", ") + (a == null ? "null" : a.toString());
						}
						else if(index == 5)
						{
							sUp += ", ...";
						}
						else if(size-index < 5)
						{
							sUp += ", " + (a == null ? "null" : a.toString());
						}
					}
					++index;
				}
				toString = sUp + "]";
			}
			
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB }, new Object[] { size, toString });
		}

		return result;
	}	

	protected <A,B> boolean isGenericCollectionNotEqualsImplementation(Object caller,
			Collection<A> referenceA, Collection<B> referenceB, Class<? extends ICheck> checkerSpecification)
	{
		boolean isEquals = false;
	
		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}
	
		int index = 0;
		Object a = null;
		Object b = null;
		if (null == referenceA && null == referenceB)
		{
			isEquals = true;
		}
		else if(null != referenceA && null != referenceA
			&& referenceA.size() == referenceB.size())
		{
			boolean isEquals_ = true;
			Iterator<?> iteratorA = referenceA.iterator();
			Iterator<?> iteratorB = referenceB.iterator();
	//		int index = -1;
			while(iteratorA.hasNext() && iteratorB.hasNext())
			{
				a = iteratorA.next();
				b = iteratorB.next();
				if(!((null == a && null == b)
					|| (null != a && null != b && a.equals(b) && b.equals(a)))) // agree to be equals
				{
					isEquals_ = false;
					break;
				}
				++index;
			}
			isEquals = isEquals_;
		}
		if(!isEquals)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB }, new Object[] { index, a, b });
		}
	
		return !isEquals;
	}
	

	
	protected <A,B> boolean isGenericArrayEqualsImplementation(Object caller, A[] referenceA,
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
		else if(null != referenceA && null != referenceA
			&& referenceA.length == referenceB.length)
		{
			boolean result_ = true;
			for(int index = 0; index < referenceA.length; ++index)
			{
				Object a = referenceA[index];
				Object b = referenceB[index];
				if(!((null == a && null == b)
					|| (null != a && null != b && a.equals(b) && b.equals(a)))) // agree to be equals
				{
					result_ = false;
					break;
				}
			}
			result = result_;
		}
		if(result)
		{
			int size = 0;
			String toString = null;
			if(null != referenceA)
			{
				size = referenceA.length;
				String sUp = "[";
				String sDown = "]";
				for(int up = 0, down = referenceA.length-1; up <= down; ++up, --down)
				{
					Object a = referenceA[up];
					Object b = referenceB[down];
					if(up == down)
					{
						sUp += (up == 0 ? "" : ", ") + (a == null ? "null" : a.toString());
						break;
					}
					else if(5 < up)
					{
						sUp += ", ..., ";
						break;
					}
					else
					{
						sUp += (up == 0 ? "" : ", ") + (a == null ? "null" : a.toString());
						sDown = ", " + (b == null ? "null" : b.toString()) + sDown;
					}
				}
				toString = sUp + sDown;
			}
			
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB }, new Object[] { size, toString });
		}

		return result;
	}
	
	protected <A,B> boolean isGenericArrayNotEqualsImplementation(Object caller, A[] referenceA,
			B[] referenceB, Class<? extends ICheck> checkerSpecification)
	{
		boolean isEquals = false;

		if (null == caller)
		{
			throw new IllegalArgumentException("caller is null");
		}

		int index = 0;
		Object a = null;
		Object b = null;
		if (null == referenceA && null == referenceB)
		{
			isEquals = true;
		}
		else if(null != referenceA && null != referenceA
			&& referenceA.length == referenceB.length)
		{
			boolean isEquals_ = true;
			for(index = 0; index < referenceA.length; ++index)
			{
				a = referenceA[index];
				b = referenceB[index];
				if(!((null == a && null == b)
					|| (null != a && null != b && a.equals(b) && b.equals(a)))) // agree to be equals
				{
					isEquals_ = false;
					break;
				}
			}
			isEquals = isEquals_;
		}
		if(!isEquals)
		{
			this.pushContractWithCaller(caller, checkerSpecification, new Object[] { caller, referenceA, referenceB }, new Object[] { index, a, b });
		}

		return !isEquals;
	}

}
