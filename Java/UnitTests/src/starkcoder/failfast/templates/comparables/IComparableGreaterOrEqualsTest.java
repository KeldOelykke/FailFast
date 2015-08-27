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

package starkcoder.failfast.templates.comparables;

import org.junit.Test;

import starkcoder.failfast.fails.FailFastException;

/**
 * Interface for object greater-than-or-equals-to checks.
 * <p>
 * This helps us remember all same checks for each type.
 * </p>
 * 
 * @author Keld Oelykke
 *
 */
public interface IComparableGreaterOrEqualsTest<T extends Comparable<T>> 
{

  // 1st - caller checks

  @Test(expected = IllegalArgumentException.class)
  void testComparableGreaterOrEqualsCheckerCallerIsNull();

  @Test(expected = IllegalArgumentException.class)
  void testComparableGreaterOrEqualsFailerCallerIsNull();

  @Test(expected = IllegalStateException.class)
  void testComparableGreaterOrEqualsFailerCallerIsWrong();

  // 2nd - mismatch calls

  @Test(expected = IllegalStateException.class)
  void testComparableGreaterOrEqualsMismatchCheckCheck();

  @Test(expected = IllegalStateException.class)
  void testComparableGreaterOrEqualsMismatchFail();

  @Test(expected = IllegalStateException.class)
  void testComparableGreaterOrEqualsMismatchWrongCheck();

  @Test(expected = IllegalStateException.class)
  void testComparableGreaterOrEqualsMismatchWrongFail();

  // 3rd - normal cases

  @Test(expected = FailFastException.class)
  void testComparableGreaterFailNoMessage();

  @Test(expected = FailFastException.class)
  void testComparableGreaterFailMessage();

  @Test(expected = FailFastException.class)
  void testComparableEqualsFailNoMessage();

  @Test(expected = FailFastException.class)
  void testComparableEqualsFailMessage();

  @Test
  void testComparableGreaterOrEqualsNoFail();
}
