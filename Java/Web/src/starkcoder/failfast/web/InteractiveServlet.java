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

package starkcoder.failfast.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import starkcoder.failfast.FailFast;
import starkcoder.failfast.IFailFast;
import starkcoder.failfast.checks.Checker;
import starkcoder.failfast.checks.IChecker;
import starkcoder.failfast.contractors.CallContractor;
import starkcoder.failfast.contractors.ICallContractor;
import starkcoder.failfast.fails.Failer;
import starkcoder.failfast.fails.IFailer;

@SuppressWarnings("serial")
public class InteractiveServlet extends HttpServlet
{

  // private static Object synco = new Object();
  private static final String pageHtmlStart = "<!DOCTYPE html><html><meta charset=\"UTF-8\"><body>";
  private static final String pageHtmlEnd = "</body></html>";
  private static final String scriptHtmlStart = "<script>";
  private static final String scriptOnSomethingChanged = 
      "\n  function OnSomethingChanged() {"
      + "\n    var element = document.getElementById('methodNameSelect');" 
      + "\n    var methodName = element.selectedOptions[0].text;" 
      + "\n    var url = \"/interactive?method=\" + methodName;" 
      + "\n    var index = 0;\n    for(index = 0; index < 10; index++)" 
      + "\n    {" 
      + "\n    element = document.getElementById('argumentID' + index);" 
      + "\n    if(element && element.value){" 
      + "\n      url += \"&arg\" + index + \"=\" + element.value;" 
      + "\n    }" 
      + "\n}" 
      + "\n    location.href=url;" 
      + "\n}\n";
  private static final String scriptHtmlEnd = "</script>";
  private static final String selectHtmlStart = 
      "<select id=\"methodNameSelect\" onchange=\"OnSomethingChanged()\">";
  private static final String selectHtmlEnd = "</select>";
  private static final String optionHtml = 
      "<option value=\"XXX_OPTION_KEY\">XXX_OPTION_VALUE</option>";
  private static final String argumentHtml = 
      "<p><i>arg#XXX_ARGUMENT_INDEX</i> (XXX_ARGUMENT_TYPE): <b>XXX_ARGUMENT_VALUE</b></p>";
  private static final String argumentInputHtml = 
      "<input id=\"argumentIDXXX_ARGUMENT_INDEX\" type=\"text\" value=\"XXX_ARGUMENT_INPUT\">" 
          + "</input>";
  private static final String buttonUpdateHtml = 
      "<input type=\"button\" value=\"Update\" onclick=\"OnSomethingChanged()\"></input>";
  private static final String checkerCallReturnValueHtml = 
      "<p><i>Checker</i>: <b>XXX_RETURN_VALUE</b></p>";
  private static final String failerExceptionHtml = 
      "<p><i>Failer</i>: <b>XXX_FAILFAST_EXCEPTION</b></p>";
  private static final String breakHtml = "<br/>";
  private static final String conditionalHtml = 
      "<p>---<p/><p>if(checker.XXX_FAILFAST_CHECKER_METHOD(XXX_FAILFAST_CHECKER_ARGUMENTS)<br/>" 
          + "{<br/>&nbsp;failer.XXX_FAILFAST_FAILER_METHOD(XXX_FAILFAST_FAILER_ARGUMENTS);<br/>}" 
          + "</p><p>---<p/>";
  private static final String linksHtml = "<hr/><a href=\"..\">Home</a>";

  /**
   * Servlet GET method.
   * <p>
   * This is mainly for fun and curiosity. Can it work as an interactive introduction?
   * </p>
   * <p>
   * It might just need to go, since the concept might seem to odd. 
   * </p>
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {

    IFailFast failFastOrNull = null;
    // synchronized(synco)
    {
      // failFastOrNull = SFailFast.getFailFastOrNull();
      // if(null == failFastOrNull)
      {
        ICallContractor callContractor = new CallContractor();
        failFastOrNull = new FailFast(new Checker(callContractor), new Failer(callContractor),
            callContractor);
        // SFailFast.setFailFastOrNull(failFastOrNull);
      }
    }

    String methodInput = request.getParameter("method");
    // String argument1TypeInput = request.getParameter("argType1");
    // String argument2TypeInput = request.getParameter("argType2");
    // String argument3TypeInput = request.getParameter("argType3");
    String argument0ValueInput = request.getParameter("arg0");
    String argument1ValueInput = request.getParameter("arg1");
    String argument2ValueInput = request.getParameter("arg2");
    String argument3ValueInput = request.getParameter("arg3");
    String argument4ValueInput = request.getParameter("arg4");
    String argument5ValueInput = request.getParameter("arg5");

    IChecker checker = failFastOrNull.getChecker();
    Class<?> checkerClass = checker.getClass();
    ArrayList<Method> methods = new ArrayList<Method>();
    {
      Method[] methodCurrent = checkerClass.getMethods();
      for (int index = 0; index < methodCurrent.length; ++index)
      {
        Method method2Insert = methodCurrent[index];
        String methodName = method2Insert.getName();
        if (methodName.startsWith("is"))
        { // insert sorted by alphabet
          int insertIndex = 0;
          while (insertIndex < methods.size())
          {
            Method methodInserted = methods.get(insertIndex);
            if (0 < methodInserted.getName().compareTo(methodName))
            {
              break;
            }
            else
            {
              ++insertIndex;
            }
          }
          methods.add(insertIndex, method2Insert);
        }
      }
    }

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(pageHtmlStart);
    stringBuilder.append(scriptHtmlStart);
    stringBuilder.append(scriptOnSomethingChanged);
    stringBuilder.append(scriptHtmlEnd);
    stringBuilder.append(selectHtmlStart);
    Method method = null;
    for (Method methodCurrent : methods)
    {
      String methodName = methodCurrent.getName();
      {
        if (null == method)
        {
          method = methodCurrent;
        }
        if (methodName.equalsIgnoreCase(methodInput))
        {
          method = methodCurrent;
          stringBuilder.append(optionHtml.replaceFirst("XXX_OPTION_KEY", methodName).replaceFirst(
              ">XXX_OPTION_VALUE", " selected>" + methodName));
        }
        else
        {
          stringBuilder.append(optionHtml.replaceFirst("XXX_OPTION_KEY", methodName).replaceFirst(
              "XXX_OPTION_VALUE", methodName));
        }
      }
    }
    stringBuilder.append(selectHtmlEnd);

    Object[] checkerArguments = null;
    Object[] failerArguments = null;
    if (null != method)
    {
      Class<?>[] checkerArgumentTypes = method.getParameterTypes();
      if (0 < checkerArgumentTypes.length)
      {
        checkerArguments = new Object[checkerArgumentTypes.length];
      }
      failerArguments = new Object[checkerArgumentTypes.length];

      String checkerArgumentsAsString = "";
      String failerArgumentsAsString = "";
      for (int index = 0; index < checkerArgumentTypes.length; ++index)
      {
        Class<?> argumentType = checkerArgumentTypes[index];
        Object checkerArgument = null;
        Object failerArgument = null;
        if (0 == index)
        {
          // if(null == argument0ValueInput)
          // {
          // checkerArgument = this.toString();
          // }
          // else
          {
            checkerArgument = this.parseArgument(argumentType, argumentType.getSimpleName(),
                argument0ValueInput);
          }
          failerArgument = checkerArgument;
          // checkerArgument = this;
          // failerArgument = this;
          checkerArgumentsAsString += checkerArgument;
          failerArgumentsAsString += failerArgument;
        }
        else if (1 == index)
        {
          checkerArgument = this.parseArgument(argumentType, argumentType.getSimpleName(),
              argument1ValueInput);
          failerArgument = "arg1";
          checkerArgumentsAsString += ", " + checkerArgument;
          failerArgumentsAsString += ", " + failerArgument;
        }
        else if (2 == index)
        {
          checkerArgument = this.parseArgument(argumentType, argumentType.getSimpleName(),
              argument2ValueInput);
          failerArgument = "arg2";
          checkerArgumentsAsString += ", " + checkerArgument;
          failerArgumentsAsString += ", " + failerArgument;
        }
        else if (3 == index)
        {
          checkerArgument = this.parseArgument(argumentType, argumentType.getSimpleName(),
              argument3ValueInput);
          failerArgument = "arg3";
          checkerArgumentsAsString += ", " + checkerArgument;
          failerArgumentsAsString += ", " + failerArgument;
        }
        else if (4 == index)
        {
          checkerArgument = this.parseArgument(argumentType, argumentType.getSimpleName(),
              argument4ValueInput);
          failerArgument = "arg4";
          checkerArgumentsAsString += ", " + checkerArgument;
          failerArgumentsAsString += ", " + failerArgument;
        }
        else if (5 == index)
        {
          checkerArgument = this.parseArgument(argumentType, argumentType.getSimpleName(),
              argument5ValueInput);
          failerArgument = "arg5";
          checkerArgumentsAsString += ", " + checkerArgument;
          failerArgumentsAsString += ", " + failerArgument;
        }
        checkerArguments[index] = checkerArgument;
        failerArguments[index] = failerArgument;
        stringBuilder.append(breakHtml);
        String str1 = argumentHtml.replaceFirst("XXX_ARGUMENT_INDEX", "" + index);
        String str2 = str1.replaceFirst("XXX_ARGUMENT_TYPE", argumentType.getSimpleName());
        String str3 = str2.replaceFirst("XXX_ARGUMENT_VALUE", (null == checkerArgument ? "null"
            : checkerArgument.toString()));
        stringBuilder.append(str3);
        String str5 = argumentInputHtml.replaceFirst("XXX_ARGUMENT_INDEX", "" + index);
        String str6 = str5.replaceFirst("XXX_ARGUMENT_INPUT", (null == checkerArgument ? ""
            : checkerArgument.toString()));
        stringBuilder.append(str6);
        stringBuilder.append(buttonUpdateHtml.replaceFirst("XXX_ARGUMENT_INDEX", "" + index));
      }

      String checkerExceptionDescription = null;
      String failerExceptionDescription = null;
      boolean checkerInvoked = false;
      boolean failerInvoked = false;
      boolean failerShouldInvoke = false;
      Boolean resultBoolean = null;
      try
      {
        checkerInvoked = true;
        Object result = method.invoke(checker, checkerArguments);
        resultBoolean = (Boolean) result;
        {
          String failerMethodName = "fail" + method.getName().substring(2);
          IFailer failer = failFastOrNull.getFailer();
          Method[] failerMethods = failer.getClass().getMethods();
          Method failMethod2Call = null;
          for (Method methodCurrent : failerMethods)
          {
            String methodName = methodCurrent.getName();
            if (methodName.equalsIgnoreCase(failerMethodName))
            {
              Class<?>[] failerParameterTypes = methodCurrent.getParameterTypes();
              if (failerArguments.length == failerParameterTypes.length)
              {
                // stringBuilder.append("Calling ");
                // stringBuilder.append(method_.getName());
                // stringBuilder.append("\n");
                failMethod2Call = methodCurrent;
                break;
              }
              else
              {
                if (null == failMethod2Call)
                {
                  failMethod2Call = methodCurrent;
                }
                else if (failerParameterTypes.length < failMethod2Call.getParameterTypes().length)
                {
                  failMethod2Call = methodCurrent;
                }

              }
              // stringBuilder.append(method.getName());
              // stringBuilder.append("\n");
            }
          }

          if (null != failMethod2Call)
          {
            Object[] failerArguments2Use = null;
            Class<?>[] failerParameterTypes = failMethod2Call.getParameterTypes();
            if (failerParameterTypes.length == failerArguments.length)
            {
              failerArguments2Use = failerArguments;
            }
            else
            {
              failerArguments2Use = new Object[failerParameterTypes.length];
              failerArgumentsAsString = "";
              for (int index = 0; index < failerArguments2Use.length
                  && index < failerArguments.length; ++index)
              {
                failerArguments2Use[index] = failerArguments[index];
                if (0 < index)
                {
                  failerArgumentsAsString += ", ";
                }
                failerArgumentsAsString += failerArguments2Use[index];
              }
            }

            if (resultBoolean)
            {
              failerShouldInvoke = true;
              failerInvoked = true;
              failMethod2Call.invoke(failer, failerArguments2Use);
            }
          }
        }
      }
      catch (Exception e)
      {
        if (!failerInvoked)
        {
          checkerExceptionDescription = (null == e.getCause() ? e.toString() : e.getCause()
              .toString());
        }
        else
        {
          failerExceptionDescription = (null == e.getCause() ? e.toString() : e.getCause()
              .toString());
        }
      }

      { // example
        String ex1 = conditionalHtml.replaceFirst("XXX_FAILFAST_CHECKER_METHOD", method.getName());
        String ex2 = ex1.replaceFirst("XXX_FAILFAST_CHECKER_ARGUMENTS", checkerArgumentsAsString);
        String ex3 = ex2.replaceFirst("XXX_FAILFAST_FAILER_METHOD",
            method.getName().replaceFirst("is", "fail"));
        String ex4 = ex3.replaceFirst("XXX_FAILFAST_FAILER_ARGUMENTS", failerArgumentsAsString);
        stringBuilder.append(ex4);
      }

      // stringBuilder.append(breakHtml);
      stringBuilder.append(checkerCallReturnValueHtml.replaceFirst("XXX_RETURN_VALUE",
          (null == resultBoolean ? (null == checkerExceptionDescription ? (checkerInvoked ? "null"
              : "not called") : checkerExceptionDescription) : resultBoolean.toString())));
      // stringBuilder.append(breakHtml);
      stringBuilder
          .append(failerExceptionHtml
              .replaceFirst(
                  "XXX_FAILFAST_EXCEPTION",
                  (null == failerExceptionDescription ? (failerInvoked ? "null"
                      : (failerShouldInvoke 
                          ? "interactive app giving up - please blame the developer - thanks!"
                          : "not called"))
                      : failerExceptionDescription)));

    }

    stringBuilder.append(linksHtml);
    stringBuilder.append(pageHtmlEnd);

    response.setContentType("text/html");
    response.getWriter().println(stringBuilder.toString());
  }

  private Object parseArgument(Class<?> methodArgumentType, String argumentType,
      String argumentValue)
  {
    Object result = null;

    Exception exceptionThrownOrNull = null;
    if (Boolean.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        if (null != argumentValue)
        {
          result = Boolean.parseBoolean(argumentValue);
        }
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Byte.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Byte.parseByte(argumentValue);
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Character.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Character.valueOf(argumentValue.charAt(0));
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Double.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Double.parseDouble(argumentValue);
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Float.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Float.parseFloat(argumentValue);
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Integer.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Integer.parseInt(argumentValue);
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Object.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = argumentValue;
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Long.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Long.parseLong(argumentValue);
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else if (Short.class.getSimpleName().equalsIgnoreCase(argumentType))
    {
      try
      {
        result = Short.parseShort(argumentValue);
      }
      catch (Exception exception)
      {
        exceptionThrownOrNull = exception;
      }
    }
    else
    {
      result = argumentValue;
    }
    
    if (null != exceptionThrownOrNull)
    {
      // TODO: do we want these on web page?
      exceptionThrownOrNull = null;
    }
    
    return result;
  }
}
