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

import java.util.HashMap;

import starkcoder.failfast.contractors.contracts.ICallContract;
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



	@Override
	public void pushContractWithCaller(ICallContract callContract)
	{
		if(null == callContract)
		{
			throw new IllegalArgumentException("callContract is null");
		}
		callContract.validateContractData();
		
		synchronized(this.getSynchronizationObject())
		{
			Long currentThreadId = this.getCurrentThreadId();
			ICallContract callContractPushedOrNull = this.getThreadId2CallContract().get(currentThreadId);
		    if (null != callContractPushedOrNull)
		    {
		        throw new IllegalStateException("Cannot push contract " + callContract + " for caller " + callContract.getCaller() + ", since caller " + callContractPushedOrNull.getCaller() + " first needs to pop contract " + callContractPushedOrNull + ".");
		    }
		    this.getThreadId2CallContract().put(currentThreadId, callContract);
		}
	}


	@Override
	public ICallContract popContractWithCaller(Object caller, IFailer throwingFailer,
			Class<? extends IFail> failSpecification)
	{
		ICallContract result = null;
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

			ICallContract callContractPushedOrNull = this.getThreadId2CallContract().get(currentThreadId);
			
            if (null == callContractPushedOrNull)
            {
                throw new IllegalStateException("Cannot pop a contract for caller " + caller + ", since none is currently pushed.");
            }
            if (caller != callContractPushedOrNull.getCaller())
            {
                throw new IllegalStateException("Cannot pop a contract for caller " + caller + ", since caller " + callContractPushedOrNull.getCaller() + " has pushed a contract.");
            }
            Class<? extends IFail> failSpecificationType = callContractPushedOrNull.reflectFailSpecificationType();
            if (!failSpecification.equals(failSpecificationType))
            {
                throw new IllegalStateException("Cannot pop a contract for fail specification " + failSpecification + " for caller " + caller + ", since a contract for fail specification " + failSpecificationType + " is currently pushed by caller " + callContractPushedOrNull.getCaller() + ".");
            }
            result = callContractPushedOrNull;
 		}
		
		return result;
	}
	
	@Override
	public ICallContract getContractWithCaller(Object caller)
	{
		ICallContract result = null;
		{
			if(null == caller)
			{
				throw new IllegalArgumentException("caller is null");
			}
		}
		
		synchronized(this.getSynchronizationObject())
		{
			Long currentThreadId = this.getCurrentThreadId();

			ICallContract callContractPushedOrNull = this.getThreadId2CallContract().get(currentThreadId);
			
            if (null == callContractPushedOrNull)
            {
                throw new IllegalStateException("Cannot pop a contract for caller " + caller + ", since none is currently pushed.");
            }
            if (caller != callContractPushedOrNull.getCaller())
            {
                throw new IllegalStateException("Cannot pop a contract for caller " + caller + ", since caller " + callContractPushedOrNull.getCaller() + " has pushed a contract.");
            }
            result = callContractPushedOrNull;
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
	
	private HashMap<Long, ICallContract> threadId2CallContract = new HashMap<Long, ICallContract>();
	protected HashMap<Long, ICallContract> getThreadId2CallContract()
	{
		return threadId2CallContract;
	}
	protected void setThreadId2CallContract(HashMap<Long, ICallContract> threadId2CallContract)
	{
		this.threadId2CallContract = threadId2CallContract;
	}


	protected Long getCurrentThreadId()
	{
		long id = Thread.currentThread().getId();
		return Long.valueOf(id);
	}

}
