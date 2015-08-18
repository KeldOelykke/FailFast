/////////////////////////////////////////////////////////////////////////////////////////
//
// The MIT License (MIT)
// 
// Copyright (c) 2014-2015 Keld Oelykke
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
/////////////////////////////////////////////////////////////////////////////////////////

package starkcoder.failfast.contractors.contracts;

import java.lang.reflect.Method;

import starkcoder.failfast.checks.ICheck;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.checks.NCheck;
import starkcoder.failfast.fails.IFail;

/**
 * Default implementation of {link:ICallContract}.
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * 
 * @author Keld Oelykke
 */
public abstract class ACallContract implements ICallContract
{
  private Object caller;

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#getCaller()
   */
  @Override
  public Object getCaller()
  {
    return this.caller;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCaller(java.lang.Object)
   */
  @Override
  public void setCaller(Object caller)
  {
    this.caller = caller;
  }

  private IChecker assertingChecker;

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#getAssertingChecker()
   */
  @Override
  public IChecker getAssertingChecker()
  {
    return this.assertingChecker;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setAssertingChecker(
   *  starkcoder.failfast.checks.IChecker)
   */
  @Override
  public void setAssertingChecker(IChecker assertingChecker)
  {
    this.assertingChecker = assertingChecker;
  }

  private Class<? extends ICheck> checkSpecification;

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#getCheckSpecification()
   */
  @Override
  public Class<? extends ICheck> getCheckSpecification()
  {
    return this.checkSpecification;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCheckSpecification(
   *  java.lang.Class)
   */
  @Override
  public void setCheckSpecification(Class<? extends ICheck> checkSpecification)
  {
    this.checkSpecification = checkSpecification;
  }

  private Object[] checkArguments;

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#getCheckArguments()
   */
  @Override
  public Object[] getCheckArguments()
  {
    return this.checkArguments;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCheckArguments(
   *  java.lang.Object[])
   */
  @Override
  public void setCheckArguments(Object[] checkArguments)
  {
    this.checkArguments = checkArguments;
  }

  private Object[] checkExtraArguments;

  @Override
  public Object[] getCheckExtraArguments()
  {
    return this.checkExtraArguments;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCheckExtraArguments(
   *  java.lang.Object[])
   */
  @Override
  public void setCheckExtraArguments(Object[] checkExtraArguments)
  {
    this.checkExtraArguments = checkExtraArguments;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#validateContractData()
   */
  @Override
  public void validateContractData()
  {
    Class<? extends IFail> failSpecificationType = null;
    {
      if (null == this.getCaller())
      {
        throw new IllegalArgumentException("Caller is null");
      }
    }
    {
      if (null == this.getAssertingChecker())
      {
        throw new IllegalArgumentException("AssertingChecker is null");
      }
    }
    {
      if (null == this.getCheckSpecification())
      {
        throw new IllegalArgumentException("CheckerSpecification is null");
      }
      Method[] declaredMethods = checkSpecification.getDeclaredMethods();
      if (declaredMethods.length <= 0)
      {
        throw new IllegalArgumentException("CheckerSpecification '" + checkSpecification
            + "' must have at least 1 check-method - not " + declaredMethods.length);
      }
      for (int index = 0; index < declaredMethods.length; ++index)
      {
        Method declaredMethod = declaredMethods[index];
        NCheck checkAnnotation = declaredMethod.getAnnotation(NCheck.class);
        if (null == checkAnnotation)
        {
          throw new IllegalArgumentException("CheckerSpecification '" + checkSpecification
              + "' must have check-method '" + declaredMethod + "' annotated with " + NCheck.class);
        }

        failSpecificationType = checkAnnotation.failSpecificationType();
        if (null == failSpecificationType)
        {
          throw new IllegalArgumentException("CheckerSpecification '" + checkSpecification
              + "' method '" + declaredMethod + "' has failSpecificationType set to null");
        }
      }
    }
    {
      if (null == this.getCheckArguments())
      {
        throw new IllegalArgumentException("CheckArguments is null");
      }
    }
    {
      if (null == this.getCheckExtraArguments())
      {
        throw new IllegalArgumentException("CheckExtraArguments is null");
      }
    }
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#reflectFailSpecificationType()
   */
  @Override
  public Class<? extends IFail> reflectFailSpecificationType()
  {
    Class<? extends IFail> result = null;

    Method[] declaredMethods = this.getCheckSpecification().getDeclaredMethods();
    if (declaredMethods.length <= 0)
    {
      throw new IllegalArgumentException("CheckerSpecification '" + checkSpecification
          + "' must have at least 1 check-method - not " + declaredMethods.length);
    }
    for (int index = 0; index < declaredMethods.length;)
    {
      Method declaredMethod = declaredMethods[index];
      NCheck checkAnnotation = declaredMethod.getAnnotation(NCheck.class);
      if (null == checkAnnotation)
      {
        throw new IllegalArgumentException("CheckerSpecification '" + checkSpecification
            + "' must have check-method '" + declaredMethod + "' annotated with " + NCheck.class);
      }

      result = checkAnnotation.failSpecificationType();
      if (null == result)
      {
        throw new IllegalArgumentException("CheckerSpecification '" + checkSpecification
            + "' method '" + declaredMethod + "' has failSpecificationType set to null");
      }
      break;
    }

    return result;
  }

  private Class<? extends RuntimeException> customFailExceptionTypeOrNull;

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract
   *  #getCustomFailExceptionTypeOrNull()
   */
  @Override
  public Class<? extends RuntimeException> getCustomFailExceptionTypeOrNull()
  {
    return this.customFailExceptionTypeOrNull;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCustomFailExceptionType(
   *  java.lang.Class)
   */
  @Override
  public void setCustomFailExceptionType(Class<? extends RuntimeException> failExceptionType)
  {
    this.customFailExceptionTypeOrNull = failExceptionType;
  }

  private String customFailMessageFormatOrNull;

  @Override
  public String getCustomFailMessageFormatOrNull()
  {
    return this.customFailMessageFormatOrNull;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCustomFailMessageFormat(
   *  java.lang.String)
   */
  @Override
  public void setCustomFailMessageFormat(String failMessageFormat)
  {
    this.customFailMessageFormatOrNull = failMessageFormat;
  }

  private String customFailMessageArguments;

  @Override
  public String getCustomFailMessageArgumentsOrNull()
  {
    return this.customFailMessageArguments;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCustomFailMessageArguments(
   *  java.lang.String)
   */
  @Override
  public void setCustomFailMessageArguments(String failMessageArguments)
  {
    this.customFailMessageArguments = failMessageArguments;
  }

  private String customFailMessagePostfix;

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract
   *  #getCustomFailMessagePostfixOrNull()
   */
  @Override
  public String getCustomFailMessagePostfixOrNull()
  {
    return this.customFailMessagePostfix;
  }

  /* (non-Javadoc)
   * @see starkcoder.failfast.contractors.contracts.ICallContract#setCustomFailMessagePostfix(
   *  java.lang.String)
   */
  @Override
  public void setCustomFailMessagePostfix(String failMessagePostfix)
  {
    this.customFailMessagePostfix = failMessagePostfix;
  }

}
