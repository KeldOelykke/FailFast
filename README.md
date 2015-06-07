FailFast
========

A tiny library to help developers build fail-fast systems.


The Principle
=============

The fail-fast principle is a system design principle. 

Such a system is designed to make immediately error response when it is used wrong.

The goal of such a behaviour is to remove magical/complex/fault-tolerant code aka "expensive" code.

If this either makes no sense to you, or you just is not convinced, please visit:

 1) http://en.wikipedia.org/wiki/Fail-fast - an introduction with references
 
 2) http://martinfowler.com/ieeeSoftware/failFast.pdf - this turned stubborn me around

In Practice
=============

As a developer you add throw sections in your code. Some of these are simple e.g. check an argument and throw an exception, if it is illegal. These standard sections are what this library tries to help you with. 

So why do you need help for that?

Your sections might be generalized into 

  if(CONDITIONAL) 
  { 
    throw EXCEPTION(MESSAGE);
  }  

'CONDITIONAL' is code that this library encapsulates. This reduces duplicated code in your application and should make it easier for you to avoid errors.

The 'if(CONDITIONAL)' should always result in a throw part, so when using this library a contract is started. The contract is ended with a 2nd call to the library that encapsulates 'throw EXCEPTION(MESSAGE);'. This also removes duplicated code in your application and increases consistency.

With this library the above section is turned into

  if(checker.isCONDITIONAL(CHECKER_USER_ARGUMENTS)) 
  { 
    // call contract customizations can go here e.g. change EXCEPTION or MESSAGE format
    failer.failCONDITIONAL(FAILER_USER_ARGUMENTS));
  }  

Your code still owns the control point. Sometimes this section is collapsed into a single function call. This is however not an optimal solution, since the call - encapsulates the control point and therefore - requires all information as arguments, even if the CONDITIONAL is false. The result is 1 expensive one-liner without much room for customizations.

So how does this look like? The library offers many checker-failer pairs e.g.

			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull");
			}

The arguments are the dynamic information that changes. 'this' is an Object that becomes the owner of the call contract that is started (if 'referenceNull' is in-fact null). 'this' is therefore needed as an argument to the engaded contract in the failer call. 'referenceNull' is an argument for the checker call and identified as a string argument in the failer call. 

If the checker call returns false, no contract has been started and the failer method will - and should not - be called. This is the cheap outcome, where no new instances are needed.

If the checker call asserts, a contract is started and ended by the expensive failer call that throws an exception with a message format "%s: Object '%s' is null.". With an arguments mapping ("fu0, fu1") the 2 failer arguments supplied by the failer user the result is a thrown FailFastException with the message "ObjectNullTest.testObjectNullFailNoMessage: Object 'referenceNull' is null. " (see https://github.com/KeldOelykke/FailFast/wiki/isObjectNull-&-failObjectNull).

Is this enough? Well, I don't know. Let's figure it out. Any feedback is appreciated.
 

Target Audience
===============

Object-Oriented software developers using Java (... and maybe other languages e.g. C# in the future).


Goal
====

To have a library worth using because of its 

 1) design - simple, extensible & replaceable, and
 
 2) performance - poor on cpu & memory usage.
 
We migrate between projects and we ask ourself what libraries should we pull this time? 
In such moments I try a search for a fail-fast library, but I can't seem to find any.

Clearly, that is no good to anyone, so here is one.

State
========

The 1st major version of this library will only support a construct with a check & fail-pair (see http://starkcoder.blogspot.dk/2013/06/how-could-failfast-library-work.html).

If you think the check & fail-pair construct is odd, you might want to checkout the spot contractor pattern (http://yoawsconsult.blogspot.dk/2012/11/spot-contractor-software-pattern.html). The pattern enforces correctness and performance.

Confidence in the current design is getting high now, though it still needs some tests to prove that
the check & fail-methods can be "managed" from another jar e.g. replaced and/or extended. Inheritance should take care of most, but it is unknown whether the failer-annotations can be modified e.g. to change failer-output formatting.

The scope of the 1st major version is check & fail-pairs that can handle objects wrapping primitives and a few basic object types from the standard library. The 8 primitives are byte, short, int, long, float, double, boolean and char. Implementation is first done via the primitives' wrapping Objects (Byte, Short, etc.). Later on the primitives might be directly implemented to avoid boxing performance penalties. Additional Object types are Object, String, Enum, UUID and Date. 

The scope of the 2nd major version is check & fail-pairs that can handle arrays and collection of above mentioned types. Furthermore, more types and check & fail-pairs might be added.

Examples
========

The wiki has a lot of test examples that hopefully makes sense to you:
 - https://github.com/KeldOelykke/FailFast/wiki

Continuous Integration
======================

Every new commit triggers a build and unit test run on Travis CI: 
 - [![Build Status](https://travis-ci.org/KeldOelykke/FailFast.svg?branch=master)](https://travis-ci.org/KeldOelykke/FailFast)

Releases
===================

Go to http://oelykke-failfast.appspot.com to:
 - download jars
 - view api (javadoc)
 - view unit test results (junit)
 - try the checker-failer pairs online
 
Alternatively, jars can be downloaded from GitHub:
 - https://github.com/KeldOelykke/FailFast/releases
 - https://github.com/KeldOelykke/FailFast/tree/master/Java/Web/war/releases

Dogma
=====

I try to follow these Object-oriented design principles:

A) Importance: Code > Tests > Examples > Documentation.

B) The library may only have 1 static entry, if really needed.

C) Specification Design using Interfaces. Implementation calls Interfaces.

D) Implementation Design using Abstract Classes. Implementation is replaceable/extensible.

D) Strong Encapsulation. Fields are private, implementation only adds protected methods.

E) Fail-fast principle using runtime exceptions.

F) Favor simple runtime features over fancy runtime features. Keeps it straight-forward.  

I change my opinions often. Sometimes because I improve, sometimes because I get a wrong idea.

Please help me change my opinions and this library.


Kind Regards,
Keld Ølykke


