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
 * Specification of a fail-fast exception.
 * <p>
 * The exception has properties that can be used to identify checker, failer, call arguments and
 * formatting rules.
 * </p>
 * 
 * @author Keld Oelykke
 */
public interface IFailFastException
{
  /**
   * The Checker specification type with check-method that asserted.
   * 
   * @return Checker specification type with asserting method.
   */
  Class<?> getCheckerSpecificationType();

  /**
   * Set the Checker specification type with check-method that asserted.
   * 
   * @param checkerSpecificationType
   *          Checker specification type with check-method that asserted.
   */
  void setCheckerSpecificationType(Class<?> checkerSpecificationType);

  /**
   * Arguments supplied at the call of the asserting check-method.
   * 
   * @return Arguments supplied at the call of the asserting check-method.
   */
  Object[] getCheckerUserArguments();

  /**
   * Sets the arguments supplied at the call of the asserting check-method.
   * 
   * @param checkerUserArguments
   *          the arguments supplied at the call of the asserting check-method.
   */
  void setCheckerUserArguments(Object[] checkerUserArguments);

  /**
   * Extra arguments used by the checker implementation.
   * 
   * @return Extra arguments used by the checker implementation.
   */
  Object[] getCheckerExtraArguments();

  /**
   * Set the Extra arguments used by the checker implementation.
   * 
   * @param checkerExtraArguments
   *          Extra arguments used by the checker implementation.
   */
  void setCheckerExtraArguments(Object[] checkerExtraArguments);

  /**
   * The Failer specification type with fail-method that produced this exception.
   * 
   * @return Failer specification type with fail-method that produced this exception.
   */
  Class<? extends IFail> getFailerSpecificationType();

  /**
   * Sets the Failer specification type with fail-method that produced this exception.
   * 
   * @param failerSpecificationType
   *          the Failer specification type with fail-method that produced this exception.
   */
  void setFailerSpecificationType(Class<? extends IFail> failerSpecificationType);

  /**
   * Arguments supplied by at the call of the fail-method.
   * 
   * @return Arguments supplied at the call of the fail-method.
   */
  Object[] getFailerUserArguments();

  /**
   * Sets the arguments supplied at the call of the fail-method.
   * 
   * @param failerUserArguments
   *          the arguments supplied at the call of the fail-method.
   */
  void setFailerUserArguments(Object[] failerUserArguments);

  /**
   * Extra arguments used by the failer implementation.
   * 
   * @return Extra arguments used by the failer implementation.
   */
  Object[] getFailerExtraArguments();

  /**
   * Sets the Extra arguments used by the failer implementation.
   * 
   * @param failerExtraArguments
   *          Extra arguments used by the failer implementation.
   */
  void setFailerExtraArguments(Object[] failerExtraArguments);

  /**
   * Exception message formatting string for thrown exception.
   * 
   * <p>
   * failMessageArguments is used to produce an Object[] with this.
   * </p>
   * <p>
   * An example could be "%s: %s(%s) and %s(%s) are NOT equals".
   * </p>
   * 
   * @return Exception message formatting string.
   */
  String getFailMessageFormat();

  /**
   * Sets the exception message formatting string for thrown exception.
   * 
   * @param failMessageFormat
   *          the exception message formatting string for thrown exception
   */
  void setFailMessageFormat(String failMessageFormat);

  /**
   * Comma-separated list of ids of checker-call and failer-call arguments supplied by either user
   * or implementation.
   * 
   * <p>
   * This list determines the order and content of an Object[] used with failMessageFormat.
   * </p>
   * <p>
   * Checker-call argument ids are cu0, cu1, cu2, ... e.g. cu0 being caller, cu1 being referenceA
   * </p>
   * <p>
   * Failer-call argument ids are fu0, fu1, fu2, ... e.g. fu0 being caller, fu1 being "referenceA"
   * </p>
   * <p>
   * An example could be "cu0,fu1,cu1,fu2,cu2" to map caller and 2 arguments (both name and value)
   * to failMessageFormat
   * </p>
   * <p>
   * Checker-implementation argument ids are cx0, cx1, cx2, ... e.g. cx0 being default value used in
   * checker
   * </p>
   * <p>
   * Failer-implementation argument ids are fx0, fx1, cx2, ... e.g. fx0 being current time supplied
   * by failer
   * </p>
   * 
   * @return Comma-separated list of ids of checker-call and failer-call arguments supplied by
   *         either user or implementation.
   */
  String getFailMessageArguments();

  /**
   * Sets the comma-separated list of ids of checker-call and failer-call arguments supplied by
   * either user or implementation.
   * 
   * @param failMessageArguments
   *          comma-separated list of ids of checker-call and failer-call arguments supplied by
   *          either user or implementation.
   */
  void setFailerMessageArguments(String failMessageArguments);

}
