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
package starkcoder.failfast.contractors;

import java.util.HashMap;

import starkcoder.failfast.checks.IChecker;
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
	 * @see starkcoder.failfast.contractors.ICallContractor#pushContractWithCaller(java.lang.Object, starkcoder.failfast.checks.IChecker, java.lang.Class)
	 */
	@Override
	public void pushContractWithCaller(Object caller,
			IChecker assertingChecker, Class<?> checkerSpecification)
	{
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
			if(null == checkerSpecification)
			{
				throw new IllegalArgumentException("checkerSpecification is null");
			}
		}
		
		synchronized(this.getSynchronizationObject())
		{
			Long currentThreadId = this.getCurrentThreadId();
		    Object callerPushed = this.getThreadId2Caller().get(currentThreadId);
		    Class<?> checkerSpecificationPushed = this.getThreadId2CheckerSpecification().get(currentThreadId);
		
		    if (null != callerPushed)
		    {
		        throw new IllegalStateException("Cannot push caller " + caller + ", since caller " + callerPushed + " needs to be popped first.");
		    }
			if (null != checkerSpecificationPushed)
			{
			    throw new IllegalStateException("Cannot push checker specification " + checkerSpecification + ", since checker specification " + checkerSpecificationPushed + " needs to be popped first.");
			}
		
			this.getThreadId2Caller().put(currentThreadId, caller);
			this.getThreadId2CheckerSpecification().put(currentThreadId, checkerSpecification);
		}
	}

	/* (non-Javadoc)
	 * @see starkcoder.failfast.contractors.ICallContractor#popContractWithCaller(java.lang.Object, starkcoder.failfast.fails.IFailer, java.lang.Class)
	 */
	@Override
	public void popContractWithCaller(Object caller, IFailer throwingFailer,
			Class<?> checkerSpecification)
	{
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
			if(null == checkerSpecification)
			{
				throw new IllegalArgumentException("checkerSpecification is null");
			}
		}
		
		synchronized(this.getSynchronizationObject())
		{
			Long currentThreadId = this.getCurrentThreadId();
 
		    Object callerPushed = this.getThreadId2Caller().get(currentThreadId);
		    Class<?> checkerSpecificationPushed = this.getThreadId2CheckerSpecification().get(currentThreadId);

            if (null == callerPushed)
            {
                throw new IllegalStateException("Cannot pop caller " + caller + ", since it is not currently pushed.");
            }
            if (caller != callerPushed)
            {
                throw new IllegalStateException("Cannot pop caller " + caller + ", since caller " + callerPushed + " is currently pushed.");
            }
            if (null == checkerSpecificationPushed)
            {
                throw new IllegalStateException("Cannot pop checker specification " + checkerSpecification + ", since it is not currently pushed.");
            }
            if (checkerSpecification != checkerSpecificationPushed)
            {
                throw new IllegalStateException("Cannot pop checker specification " + checkerSpecification + ", since checker specification " + checkerSpecificationPushed + " is currently pushed.");
            }
            this.getThreadId2Caller().remove(currentThreadId);
            this.getThreadId2CheckerSpecification().remove(currentThreadId);
 		}
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
	
	private HashMap<Long, Class<?>> threadId2CheckerSpecification = new HashMap<Long, Class<?>>();
	protected HashMap<Long, Class<?>> getThreadId2CheckerSpecification()
	{
		return threadId2CheckerSpecification;
	}
	protected void setThreadId2CheckerSpecification(
			HashMap<Long, Class<?>> threadId2CheckerSpecification)
	{
		this.threadId2CheckerSpecification = threadId2CheckerSpecification;
	}

	protected Long getCurrentThreadId()
	{
		long id = Thread.currentThread().getId();
		return Long.valueOf(id);
	}
	
	

}
