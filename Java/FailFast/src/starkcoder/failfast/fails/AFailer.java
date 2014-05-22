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
import starkcoder.failfast.fails.objects.IObjectNullFail;

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
                        Class<?> argumentType = messageFormatArguments[j].getClass();
                        if (!parameterInfo.isAssignableFrom(argumentType))
                        {
                            allParametersHaveMatchingType = false;
                            break;
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
