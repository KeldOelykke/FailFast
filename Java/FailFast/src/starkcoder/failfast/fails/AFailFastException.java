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

package starkcoder.failfast.fails;

/**
 * Abstract implementation of fail-fast exception inheriting RuntimeException.
 * <p>
 * Exception is a checked exception that would require try-catches, but RuntimeException is not.
 * </p>
 * <p>
 * To use checked exceptions would be against one of the purposes of the fail-fast principle (to
 * avoid complex try-catch code).
 * </p>
 * <p>
 * The purpose of this is to ease the burden of concrete implementations.
 * </p>
 * <p>
 * To extend this in a concrete implementation is optional.
 * </p>
 * 
 * @author Keld Oelykke
 */
public abstract class AFailFastException extends RuntimeException implements IFailFastException
{

  /**
   * No idea why this field needs to be in an abstract class, but here is because Eclipse says so.
   * 
   * {@link http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html}
   */
  private static final long serialVersionUID = -255945944091813082L;

  protected AFailFastException(String message)
  {
    super(message);
  }

  protected AFailFastException(String message, Throwable throwable)
  {
    super(message, throwable);
  }

  private Class<?> checkerSpecificationType;

  @Override
  public Class<?> getCheckerSpecificationType()
  {
    return this.checkerSpecificationType;
  }

  @Override
  public void setCheckerSpecificationType(Class<?> checkerSpecificationType)
  {
    this.checkerSpecificationType = checkerSpecificationType;
  }

  private Object[] checkerUserArguments;

  @Override
  public Object[] getCheckerUserArguments()
  {
    return this.checkerUserArguments;
  }

  @Override
  public void setCheckerUserArguments(Object[] checkerUserArguments)
  {
    this.checkerUserArguments = checkerUserArguments;
  }

  private Object[] checkerExtraArguments;

  @Override
  public Object[] getCheckerExtraArguments()
  {
    return this.checkerExtraArguments;
  }

  @Override
  public void setCheckerExtraArguments(Object[] checkerExtraArguments)
  {
    this.checkerExtraArguments = checkerExtraArguments;
  }

  private Class<? extends IFail> failerSpecificationType;

  @Override
  public Class<? extends IFail> getFailerSpecificationType()
  {
    return this.failerSpecificationType;
  }

  @Override
  public void setFailerSpecificationType(Class<? extends IFail> failerSpecificationType)
  {
    this.failerSpecificationType = failerSpecificationType;
  }

  private Object[] failerUserArguments;

  @Override
  public Object[] getFailerUserArguments()
  {
    return this.failerUserArguments;
  }

  @Override
  public void setFailerUserArguments(Object[] failerUserArguments)
  {
    this.failerUserArguments = failerUserArguments;
  }

  private Object[] failerExtraArguments;

  @Override
  public Object[] getFailerExtraArguments()
  {
    return this.failerExtraArguments;
  }

  @Override
  public void setFailerExtraArguments(Object[] failerExtraArguments)
  {
    this.failerExtraArguments = failerExtraArguments;
  }

  private String failMessageFormat;

  @Override
  public String getFailMessageFormat()
  {
    return this.failMessageFormat;
  }

  @Override
  public void setFailMessageFormat(String failMessageFormat)
  {
    this.failMessageFormat = failMessageFormat;
  }

  private String failMessageArguments;

  @Override
  public String getFailMessageArguments()
  {
    return this.failMessageArguments;
  }

  @Override
  public void setFailerMessageArguments(String failMessageArguments)
  {
    this.failMessageArguments = failMessageArguments;
  }

}
