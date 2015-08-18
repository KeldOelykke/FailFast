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

import starkcoder.failfast.contractors.ICallContractorReference;
import starkcoder.failfast.fails.objects.IObjectFailer;

/**
 * Failer specification.
 * <p>
 * The Failer is used to throw fail-fast exceptions when a checker asserts.
 * </p>
 * A checker that asserts starts a contract that this must end (via the call contractor). </p>
 * <p>
 * Threads can poll this to check if a fail-fast exception has been thrown.
 * </p>
 * <p>
 * Implementations of this should be extensible (not final).
 * </p>
 * 
 * @author Keld Oelykke
 */
public interface IFailer extends ICallContractorReference, IFailFastExceptionReference,
    IObjectFailer,
    // IPrimitiveFailer, -- TODO: make this to avoid boxing/unboxing penalties?
    // IGenericsFailer -- TODO: determine whether this is needed at all?
    IFailerPublisher, IFailerCustomizer
{

}
