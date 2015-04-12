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
package starkcoder.failfast.contractors;

import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.IFail;
import starkcoder.failfast.fails.IFailer;

/**
 * Abstract implementation of ICallContractor.
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * @author Keld Oelykke
 *
 */
public abstract class ACallContractor implements ICallContractor
{



	/* (non-Javadoc)
	 * @see starkcoder.failfast.contractors.ICallContractor#pushContractWithCaller(java.lang.Object, starkcoder.failfast.checks.IChecker, java.lang.Class, java.lang.String[])
	 */
	@Override
	public void pushContractWithCaller(Object caller,
			IChecker assertingChecker, Class<? extends ICheck> checkSpecification, Object[] checkArguments, Object[] checkExtraArguments)
	{
		Class<? extends IFail> failSpecificationType = null;
		{
			if(null == caller)
			{
				throw new IllegalArgumentException("caller is null");
			}
		}
		{
			if(null == assertingChecker)
			{
				throw new IllegalArgumentException("assertingChecker is null");
			}
		}
		{
			if(null == checkSpecification)
			{
				throw new IllegalArgumentException("checkerSpecification is null");
			}
			Method[] declaredMethods = checkSpecification.getDeclaredMethods();
			if(declaredMethods.length <= 0)
			{
				throw new IllegalArgumentException("checkerSpecification '" + checkSpecification + "' must have at least 1 check-method - not " + declaredMethods.length);
			}
			for(int index = 0; index < declaredMethods.length; ++index)
			{
				Method declaredMethod = declaredMethods[index];
				NCheck checkAnnotation = declaredMethod.getAnnotation(NCheck.class);
				if(null == checkAnnotation)
				{
					throw new IllegalArgumentException("checkerSpecification '" + checkSpecification + "' must have check-method '" + declaredMethod + "' annotated with " + NCheck.class);
				}
				
				failSpecificationType = checkAnnotation.failSpecificationType();
				if(null == failSpecificationType)
				{
					throw new IllegalArgumentException("checkerSpecification '" + checkSpecification + "' method '" + declaredMethod + "' has failSpecificationType set to null");
				}
			}
		}
		
		synchronized(this.getSynchronizationObject())
		{
			Long currentThreadId = this.getCurrentThreadId();
		    Object callerPushed = this.getThreadId2Caller().get(currentThreadId);
		    Class<? extends IFail> failSpecificationTypePushed = this.getThreadId2FailSpecification().get(currentThreadId);
		    Object[] checkArgumentsPushed = this.getThreadId2CheckArguments().get(currentThreadId);
		    Object[] checkExtraArgumentsPushed = this.getThreadId2CheckExtraArguments().get(currentThreadId);
		    if (null != callerPushed)
		    {
		        throw new IllegalStateException("Cannot push caller " + caller + ", since caller " + callerPushed + " needs to be popped first.");
		    }
			if (null != failSpecificationTypePushed)
			{
			    throw new IllegalStateException("Cannot push fail specification " + failSpecificationType + ", since fail specification " + failSpecificationTypePushed + " needs to be popped first.");
			}
			if (null != checkArgumentsPushed)
			{
			    throw new IllegalStateException("Cannot push fail specification " + failSpecificationType + " with " + checkArguments.length + " user arguments, since fail specification " + failSpecificationTypePushed + " with " + checkArgumentsPushed.length + " arguments needs to be popped first.");
			}
			if (null != checkExtraArgumentsPushed)
			{
			    throw new IllegalStateException("Cannot push fail specification " + failSpecificationType + " with " + checkExtraArguments.length + " extra arguments, since fail specification " + failSpecificationTypePushed + " with " + checkExtraArgumentsPushed.length + " arguments needs to be popped first.");
			}
		
			this.getThreadId2Caller().put(currentThreadId, caller);
			this.getThreadId2FailSpecification().put(currentThreadId, failSpecificationType);
			this.getThreadId2CheckArguments().put(currentThreadId, checkArguments);
			this.getThreadId2CheckExtraArguments().put(currentThreadId, checkExtraArguments);
		}
	}


	/* (non-Javadoc)
	 * @see starkcoder.failfast.contractors.ICallContractor#popContractWithCaller(java.lang.Object, starkcoder.failfast.fails.IFailer, java.lang.Class)
	 */
	@Override
	public SimpleEntry<Object[], Object[]> popContractWithCaller(Object caller, IFailer throwingFailer,
			Class<? extends IFail> failSpecification)
	{
		SimpleEntry<Object[], Object[]> result = null;
//		Object[] result = null;
		{
			if(null == caller)
			{
				throw new IllegalArgumentException("caller is null");
			}
		}
		{
			if(null == throwingFailer)
			{
				throw new IllegalArgumentException("throwingFailer is null");
			}
		}
		{
			if(null == failSpecification)
			{
				throw new IllegalArgumentException("failSpecification is null");
			}
		}
		
		synchronized(this.getSynchronizationObject())
		{
			Long currentThreadId = this.getCurrentThreadId();
 
		    Object callerPushed = this.getThreadId2Caller().get(currentThreadId);
		    Class<? extends IFail> failSpecificationPushed = this.getThreadId2FailSpecification().get(currentThreadId);
		    Object[] checkArguments = this.getThreadId2CheckArguments().get(currentThreadId);
		    Object[] checkExtraArguments = this.getThreadId2CheckExtraArguments().get(currentThreadId);

            if (null == callerPushed)
            {
                throw new IllegalStateException("Cannot pop caller " + caller + ", since it is not currently pushed.");
            }
            if (null == failSpecificationPushed)
            { // this should never happen
                throw new IllegalStateException("Cannot pop fail specification " + failSpecification + " for caller " + caller + ", since nothing is currently pushed.");
            }
            if (null == checkArguments)
            { // this should never happen
                throw new IllegalStateException("Cannot pop check user arguments " + checkArguments + " for caller " + caller + ", since nothing is currently pushed.");
            }
            if (null == checkExtraArguments)
            { // this should never happen
                throw new IllegalStateException("Cannot pop check extra arguments " + checkExtraArguments + " for caller " + caller + ", since nothing is currently pushed.");
            }
            if (caller != callerPushed)
            {
                throw new IllegalStateException("Cannot pop caller " + caller + ", since caller " + callerPushed + " is currently pushed.");
            }
            if (failSpecification != failSpecificationPushed)
            {
                throw new IllegalStateException("Cannot pop fail specification " + failSpecification + " for caller " + caller + ", since fail specification " + failSpecificationPushed + " is currently pushed.");
            }
            this.getThreadId2Caller().remove(currentThreadId);
            this.getThreadId2FailSpecification().remove(currentThreadId);
            this.getThreadId2CheckArguments().remove(currentThreadId);
            this.getThreadId2CheckExtraArguments().remove(currentThreadId);
            result = new SimpleEntry<>(checkArguments, checkExtraArguments);
 		}
		
		return result;
	}
	
	/**
	 * Default constructor.
	 */
	protected ACallContractor()
	{
		super();
	}
	
	private Object synchronizationObject = new Object();
	protected Object getSynchronizationObject()
	{
		return this.synchronizationObject;
	}
	protected void setSynchronizationObject(Object synchronizationObject)
	{
		this.synchronizationObject = synchronizationObject;
	}
	
	private HashMap<Long, Object> threadId2Caller = new HashMap<Long, Object>();
	protected HashMap<Long, Object> getThreadId2Caller()
	{
		return threadId2Caller;
	}
	protected void setThreadId2Caller(HashMap<Long, Object> threadId2Caller)
	{
		this.threadId2Caller = threadId2Caller;
	}
	
	private HashMap<Long, Class<? extends IFail>> threadId2FailSpecification = new HashMap<Long, Class<? extends IFail>>();
	protected HashMap<Long, Class<? extends IFail>> getThreadId2FailSpecification()
	{
		return threadId2FailSpecification;
	}
	protected void setThreadId2FailSpecification(
			HashMap<Long, Class<? extends IFail>> threadId2FailSpecification)
	{
		this.threadId2FailSpecification = threadId2FailSpecification;
	}
	
	private HashMap<Long, Object[]> threadId2CheckArguments = new HashMap<Long, Object[]>();
	protected HashMap<Long, Object[]> getThreadId2CheckArguments()
	{
		return threadId2CheckArguments;
	}
	protected void setThreadId2CheckArguments(
			HashMap<Long, Object[]> threadId2CheckArguments)
	{
		this.threadId2CheckArguments = threadId2CheckArguments;
	}

	private HashMap<Long, Object[]> threadId2CheckExtraArguments = new HashMap<Long, Object[]>();
	protected HashMap<Long, Object[]> getThreadId2CheckExtraArguments()
	{
		return threadId2CheckExtraArguments;
	}
	protected void setThreadId2CheckExtraArguments(
			HashMap<Long, Object[]> threadId2CheckExtraArguments)
	{
		this.threadId2CheckExtraArguments = threadId2CheckExtraArguments;
	}

	protected Long getCurrentThreadId()
	{
		long id = Thread.currentThread().getId();
		return Long.valueOf(id);
	}
	
	

}
