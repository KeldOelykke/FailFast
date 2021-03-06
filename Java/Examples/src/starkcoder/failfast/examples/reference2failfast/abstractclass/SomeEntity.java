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

package starkcoder.failfast.examples.reference2failfast.abstractclass;

/**
 * Example entity implementation using fail-fast shortcut methods in abstract class.
 * 
 * @author Keld Oelykke
 */
public class SomeEntity extends AEntity
{

  /**
   * Illustrates how fail-fast helper methods in abstract class can be used.
   * 
   * @param foo
   *          some argument
   * @param bar
   *          some other argument
   */
  public void someMethod(Object foo, Object bar)
  {
    // cheap call using existing references
    if (this.getMyChecker().isObjectNull(this, foo))
    {
      // expensive call creating string and exception
      this.getMyFailer().failObjectNull(this, "foo");
    }
    // cheap call using existing references
    if (this.getMyChecker().isObjectNull(this, bar))
    {
      // expensive call creating strings and exception
      this.getMyCallContractor().getContractWithCaller(this)
          .setCustomFailMessagePostfix("Is it really closed?");
      this.getMyFailer().failObjectNull(this, "bar");
    }
  }
}
