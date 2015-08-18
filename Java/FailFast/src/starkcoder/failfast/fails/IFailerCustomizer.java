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
 * This specifies runtime customizations to {link:NFail} attributes of the various failer
 * specifications.
 * <p>
 * The same effect can be achieved in code by inheriting all check-failer specifications and
 * customizing the {link:NFail} attributes in each failer specification (a lot of work).
 * </p>
 * <p>
 * The same effect can also be achieved by customizing every {link:ICallContract} in your
 * application code in each checker-failer section (also alot of work with consistency hard to
 * reach).
 * </p>
 * <p>
 * With these customize methods your application can do the customization once e.g. at startup (less
 * work and with consistency within reach).
 * </p>
 * <p>
 * However, be aware that to reach consistency in your application, you might need to assert all the
 * failer-methods.
 * </p>
 * <p>
 * One use-case for this could be to customize all exception messages with application info.
 * </p>
 * <p>
 * Another use-case for this could be support localization to all exception messages.
 * </p>
 * 
 * @author Keld Oelykke
 */
public interface IFailerCustomizer
{
  /**
   * Retrieves a custom exception class, if set, that the fail-method should throw instead of the
   * default class set in its {link:NFail}.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * 
   * @return a custom exception class of the supplied fail-method should throw, or null
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   */
  Class<? extends RuntimeException> getCustomFailExceptionTypeOrNull(
      String failerSpecificationAndMethodId);

  /**
   * Registers a custom exception class that the supplied failer specification method should throw
   * instead of the default class set in its {link:NFail}.
   * <p>
   * If failExceptionType inherits {link:IFailFastException} the exception will be registered as a
   * fail-fast exception in {link:IFailFast}.
   * </p>
   * <p>
   * If failExceptionType does not inherit {link:IFailFastException} the exception will be treated
   * as a "normal" api exception.
   * </p>
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * @param failExceptionType
   *          a custom exception class that the failer specification method should throw
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if the failerSpecificationAndMethodID already has a registration
   */
  void registerCustomFailExceptionType(String failerSpecificationAndMethodId,
      Class<? extends RuntimeException> failExceptionType);

  /**
   * Unregisters a previously registered custom exception class for the supplied failer
   * specification method.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if the failerSpecificationAndMethodID has no registration to unregister
   */
  void unregisterCustomFailExceptionType(String failerSpecificationAndMethodId);

  /**
   * Retrieves custom message format, if set, that the fail-method should use instead of the default
   * message format set in its {link:NFail}.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * 
   * @return custom message format of the supplied failer specification method, or null
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   */
  String getCustomFailMessageFormatOrNull(String failerSpecificationAndMethodId);

  /**
   * Registers a custom message format that the supplied failer specification method should throw
   * instead of the default class set in its {link:NFail}.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * @param failMessageFormat
   *          custom message format that fail-method should use
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if the failerSpecificationAndMethodID already has a registration
   */
  void registerCustomFailMessageFormat(String failerSpecificationAndMethodId,
      String failMessageFormat);

  /**
   * Unregisters a previously registered custom message format for the supplied failer specification
   * method.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if the failerSpecificationAndMethodID has no registration to unregister
   */
  void unregisterCustomFailMessageFormat(String failerSpecificationAndMethodId);

  /**
   * Retrieves custom message arguments, if set, that the fail-method should use instead of the
   * default message arguments set in its {link:NFail}.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * 
   * @return custom message arguments of the supplied failer specification method, or null
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   */
  String getCustomFailMessageArgumentsOrNull(String failerSpecificationAndMethodId);

  /**
   * Registers custom message arguments that the supplied failer specification method should throw
   * instead of the default class set in its {link:NFail}.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * @param failMessageArguments
   *          custom message arguments that fail-method should use
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if the failerSpecificationAndMethodID already has a registration
   */
  void registerCustomFailMessageArguments(String failerSpecificationAndMethodId,
      String failMessageArguments);

  /**
   * Unregisters a previously registered custom message arguments that the supplied failer
   * specification method should use instead of the default message arguments set in its
   * {link:NFail}.
   * 
   * @param failerSpecificationAndMethodId
   *          unique id of a failer specification method to to affect
   * 
   * @throws IllegalArgumentException
   *           if any of the arguments are null
   * @throws IllegalStateException
   *           if the failerSpecificationAndMethodID has no registration to unregister
   */
  void unregisterCustomFailMessageArguments(String failerSpecificationAndMethodId);

}
