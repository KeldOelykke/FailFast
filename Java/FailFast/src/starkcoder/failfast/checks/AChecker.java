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

import starkcoder.failfast.checks.objects.IObjectIsNotNullCheck;
import starkcoder.failfast.checks.objects.IObjectIsNullCheck;
import starkcoder.failfast.contractors.ICallContractor;

/**
 * Abstract implementation of {@link IChecker}.
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * @author Keld Oelykke
 */
public class AChecker implements IChecker
{
	private ICallContractor callContractor;
	/* (non-Javadoc)
	 * @see starkcoder.failfast.contractors.ICallContractorReference#getCallContractor()
	 */
	@Override
	public ICallContractor getCallContractor()
	{
		return this.callContractor;
	}
	/* (non-Javadoc)
	 * @see starkcoder.failfast.contractors.ICallContractorReference#setCallContractor(starkcoder.failfast.contractors.ICallContractor)
	 */
	@Override
	public void setCallContractor(ICallContractor callContractor)
	{
		this.callContractor = callContractor;
	}
	
	
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectIsNullChecker#isObjectNull(java.lang.Object, java.lang.Object)
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
            this.pushContractWithCaller(caller, IObjectIsNullCheck.class);
            result = true;
        }

        return result;
	}
	
	/* (non-Javadoc)
	 * @see starkcoder.failfast.checks.objects.IObjectIsNotNullCheck#isObjectNotNull(java.lang.Object, java.lang.Object)
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
            this.pushContractWithCaller(caller, IObjectIsNotNullCheck.class);
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
	 * Recommended constructor receiving required references (manual constructor dependency injection).
	 * <p>
	 * This is ready for use after this call.
	 * </p>
	 * @param callContractor
	 *            used by this
	 */
	protected AChecker(ICallContractor callContractor)
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
	
    protected void pushContractWithCaller(Object caller, Class<? extends ICheck> checkerSpecification)
    {
    	ICallContractor callContractor = this.getCallContractor();
    	if(null == callContractor)
    	{
    		throw new IllegalStateException("CallContractor must be set before using this checker.");
    	}
    	
    	// require a fail call from caller
    	callContractor.pushContractWithCaller(caller, this, checkerSpecification);
    }


}
