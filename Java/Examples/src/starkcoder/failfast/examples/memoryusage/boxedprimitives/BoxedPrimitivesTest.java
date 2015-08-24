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

package starkcoder.failfast.examples.memoryusage.boxedprimitives;

import java.util.Date;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * These test tries to compare memory usage between methods accepting primitives and methods
 * accepting boxed primitives.
 * <p>
 * The result is that both memory usage and difference is not measurable, indicating that memory is
 * heavily optimized in both cases.
 * </p>
 * 
 * @author Keld Oelykke
 *
 */
public class BoxedPrimitivesTest
{

  private int numberOfPrimitives = 1000000;

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  private boolean methodAcceptingPrimitiveBoolean(boolean primitive)
  {
    boolean result = false;

    if (true == primitive)
    {
      result = true;
    }

    return result;
  }

  private boolean methodAcceptingObjectBoolean(Boolean instance)
  {
    boolean result = false;

    if (Boolean.TRUE.equals(instance))
    {
      result = true;
    }

    return result;
  }

  @Test
  public void testMemoryUsagePrimitiveBoolean()
  {

    boolean[] primitives = new boolean[this.numberOfPrimitives];
    Random random = new Random();
    for (int index = 0; index < primitives.length; ++index)
    { // create test input
      primitives[index] = random.nextBoolean();
    }

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long freeMemoryBefore = runtime.freeMemory();
    long maxMemoryBefore = runtime.maxMemory();
    // long totalMemoryBefore = runtime.totalMemory();
    { // run memory test
      for (int index = 0; index < primitives.length; ++index)
      {
        boolean primitive = primitives[index];
        this.methodAcceptingPrimitiveBoolean(primitive);
      }
    }
    long freeMemoryAfter = runtime.freeMemory();
    long maxMemoryAfter = runtime.maxMemory();
    // long totalMemoryAfter = runtime.totalMemory();
    long usedBefore = maxMemoryBefore - freeMemoryBefore;
    long usedAfter = maxMemoryAfter - freeMemoryAfter;
    long delta = usedAfter - usedBefore;
    double deltaPerCall = ((double) delta) / ((double) this.numberOfPrimitives);

    Date timeBefore = new Date();
    runtime.gc();
    Date timeAfter = new Date();
    long gcCallDurationMs = timeAfter.getTime() - timeBefore.getTime();

    { // output memory usage
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(" - - - \n");
      stringBuilder.append(" Primitive - boolean \n");
      stringBuilder.append(" - - - \n");
      stringBuilder.append("Before: used=");
      stringBuilder.append(usedBefore);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("After: used=");
      stringBuilder.append(usedAfter);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: delta=");
      stringBuilder.append(delta);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: costPerCall=");
      stringBuilder.append(deltaPerCall);
      stringBuilder.append(" [bytes/call]\n");
      stringBuilder.append("GC: callDuration=");
      stringBuilder.append(gcCallDurationMs);
      stringBuilder.append(" [ms]\n");
      stringBuilder.append(" - - - \n");
      System.out.println(stringBuilder.toString());
    }
  }

  @Test
  public void testMemoryUsageBoxedPrimitiveBoolean()
  {

    Boolean[] boxedPrimitives = new Boolean[this.numberOfPrimitives];
    Random random = new Random();
    for (int index = 0; index < boxedPrimitives.length; ++index)
    { // create test input
      boxedPrimitives[index] = random.nextBoolean();
    }

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long freeMemoryBefore = runtime.freeMemory();
    long maxMemoryBefore = runtime.maxMemory();
    // long totalMemoryBefore = runtime.totalMemory();
    { // run memory test
      for (int index = 0; index < boxedPrimitives.length; ++index)
      {
        Boolean boxedPrimitive = boxedPrimitives[index];
        this.methodAcceptingObjectBoolean(boxedPrimitive);
      }
    }
    long freeMemoryAfter = runtime.freeMemory();
    long maxMemoryAfter = runtime.maxMemory();
    // long totalMemoryAfter = runtime.totalMemory();
    long usedBefore = maxMemoryBefore - freeMemoryBefore;
    long usedAfter = maxMemoryAfter - freeMemoryAfter;
    long delta = usedAfter - usedBefore;
    double deltaPerCall = ((double) delta) / ((double) this.numberOfPrimitives);

    Date timeBefore = new Date();
    runtime.gc();
    Date timeAfter = new Date();
    long gcCallDurationMs = timeAfter.getTime() - timeBefore.getTime();

    { // output memory usage
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(" - - - \n");
      stringBuilder.append(" Boxed Primitive - Boolean \n");
      stringBuilder.append(" - - - \n");
      stringBuilder.append("Before: used=");
      stringBuilder.append(usedBefore);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("After: used=");
      stringBuilder.append(usedAfter);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: delta=");
      stringBuilder.append(delta);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: costPerCall=");
      stringBuilder.append(deltaPerCall);
      stringBuilder.append(" [bytes/call]\n");
      stringBuilder.append("GC: callDuration=");
      stringBuilder.append(gcCallDurationMs);
      stringBuilder.append(" [ms]\n");
      stringBuilder.append(" - - - \n");
      System.out.println(stringBuilder.toString());
    }
  }

  private boolean methodAcceptingPrimitiveFloat(float primitive)
  {
    boolean result = false;

    if (0f < primitive)
    {
      result = true;
    }

    return result;
  }

  private static final Float FloatZero = new Float(0f);

  private boolean methodAcceptingObjectFloat(Float instance)
  {
    boolean result = false;

    if (FloatZero < instance)
    {
      result = true;
    }

    return result;
  }

  @Test
  public void testMemoryUsagePrimitiveFloat()
  {

    float[] primitives = new float[this.numberOfPrimitives];
    Random random = new Random();
    for (int index = 0; index < primitives.length; ++index)
    { // create test input
      primitives[index] = random.nextFloat();
    }

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long freeMemoryBefore = runtime.freeMemory();
    long maxMemoryBefore = runtime.maxMemory();
    // long totalMemoryBefore = runtime.totalMemory();
    { // run memory test
      for (int index = 0; index < primitives.length; ++index)
      {
        float primitive = primitives[index];
        this.methodAcceptingPrimitiveFloat(primitive);
      }
    }
    long freeMemoryAfter = runtime.freeMemory();
    long maxMemoryAfter = runtime.maxMemory();
    // long totalMemoryAfter = runtime.totalMemory();
    long usedBefore = maxMemoryBefore - freeMemoryBefore;
    long usedAfter = maxMemoryAfter - freeMemoryAfter;
    long delta = usedAfter - usedBefore;
    double deltaPerCall = ((double) delta) / ((double) this.numberOfPrimitives);

    Date timeBefore = new Date();
    runtime.gc();
    Date timeAfter = new Date();
    long gcCallDurationMs = timeAfter.getTime() - timeBefore.getTime();

    { // output memory usage
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(" - - - \n");
      stringBuilder.append(" Primitive - float \n");
      stringBuilder.append(" - - - \n");
      stringBuilder.append("Before: used=");
      stringBuilder.append(usedBefore);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("After: used=");
      stringBuilder.append(usedAfter);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: delta=");
      stringBuilder.append(delta);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: costPerCall=");
      stringBuilder.append(deltaPerCall);
      stringBuilder.append(" [bytes/call]\n");
      stringBuilder.append("GC: callDuration=");
      stringBuilder.append(gcCallDurationMs);
      stringBuilder.append(" [ms]\n");
      stringBuilder.append(" - - - \n");
      System.out.println(stringBuilder.toString());
    }
  }

  @Test
  public void testMemoryUsageBoxedPrimitiveFloat()
  {

    Float[] boxedPrimitives = new Float[this.numberOfPrimitives];
    Random random = new Random();
    for (int index = 0; index < boxedPrimitives.length; ++index)
    { // create test input
      boxedPrimitives[index] = random.nextFloat();
    }

    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long freeMemoryBefore = runtime.freeMemory();
    long maxMemoryBefore = runtime.maxMemory();
    // long totalMemoryBefore = runtime.totalMemory();
    { // run memory test
      for (int index = 0; index < boxedPrimitives.length; ++index)
      {
        Float boxedPrimitive = boxedPrimitives[index];
        this.methodAcceptingObjectFloat(boxedPrimitive);
      }
    }
    long freeMemoryAfter = runtime.freeMemory();
    long maxMemoryAfter = runtime.maxMemory();
    // long totalMemoryAfter = runtime.totalMemory();
    long usedBefore = maxMemoryBefore - freeMemoryBefore;
    long usedAfter = maxMemoryAfter - freeMemoryAfter;
    long delta = usedAfter - usedBefore;
    double deltaPerCall = ((double) delta) / ((double) this.numberOfPrimitives);

    Date timeBefore = new Date();
    runtime.gc();
    Date timeAfter = new Date();
    long gcCallDurationMs = timeAfter.getTime() - timeBefore.getTime();

    { // output memory usage
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(" - - - \n");
      stringBuilder.append(" Boxed Primitive - Float \n");
      stringBuilder.append(" - - - \n");
      stringBuilder.append("Before: used=");
      stringBuilder.append(usedBefore);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("After: used=");
      stringBuilder.append(usedAfter);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: delta=");
      stringBuilder.append(delta);
      stringBuilder.append(" [bytes]\n");
      stringBuilder.append("Delta: costPerCall=");
      stringBuilder.append(deltaPerCall);
      stringBuilder.append(" [bytes/call]\n");
      stringBuilder.append("GC: callDuration=");
      stringBuilder.append(gcCallDurationMs);
      stringBuilder.append(" [ms]\n");
      stringBuilder.append(" - - - \n");
      System.out.println(stringBuilder.toString());
    }
  }

  @Test
  public void callAllTestAgainAndAgain()
  {
    for (int index = 0; index < 10; index++)
    {
      this.testMemoryUsagePrimitiveBoolean();
      this.testMemoryUsagePrimitiveFloat();
      this.testMemoryUsageBoxedPrimitiveBoolean();
      this.testMemoryUsageBoxedPrimitiveFloat();
    }
  }
}
