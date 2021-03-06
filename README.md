FailFast
========

A tiny library to help developers build fail-fast systems.


Target Audience
===============

Object-Oriented software developers using Java (... and maybe other languages e.g. C# in the future).


The Principle
=============

The fail-fast principle is a system design principle. 

Such a system is designed to make immediately error response when it is used wrong.

The goal of such a behaviour is to remove magical/complex/fault-tolerant code aka "expensive" code.

If this either makes no sense to you, or you just is not convinced, please visit:

 1) http://en.wikipedia.org/wiki/Fail-fast - an introduction with references
 
 2) http://martinfowler.com/ieeeSoftware/failFast.pdf - this turned stubborn me around


The Practice
=============

As a developer you add throw sections in your code. Some of these are simple e.g. check an argument and throw an exception, if it is illegal. These standard sections are what this library tries to help you with. 

So why do you need help for that?

Your sections might be generalized into 

			if(CONDITIONAL) 
			{ 
				throw EXCEPTION(MESSAGE);
			}  

'CONDITIONAL' is code that this library encapsulates. This reduces duplicated code in your application and should make it easier for you to avoid errors.

The 'if(CONDITIONAL)' should - when true - result in a throw part, why a 'CONDITIONAL' when using this library starts a contract. The contract is ended with a 2nd call to the library that encapsulates 'throw EXCEPTION(MESSAGE);'. This also removes duplicated code in your application and increases consistency.

With this library the above section is a bit more specific

			if(checker.isCONDITIONAL(CHECKER_USER_ARGUMENTS)) 
			{ 
				// call contract customizations can go here e.g. change EXCEPTION or MESSAGE format
				failer.failCONDITIONAL(FAILER_USER_ARGUMENTS));
			}  

Note! Sometimes this section is collapsed into a single function call. This is NOT an optimal solution, since that single call encapsulates the control point and therefore requires all information as arguments; even if the CONDITIONAL is false. The result is 1 expensive one-liner without much room for customizations.

With this library your code still owns the control point. 

So how does this look like? The library offers many checker-failer pairs e.g.

			if(checker.isObjectNull(this, referenceNull))
			{
				failer.failObjectNull(this, "referenceNull");
			}

The arguments are the dynamic information that changes. 'this' is an Object that is to identify the caller of the checker-failer methods. In the checker-call a contract with the caller is started, if 'referenceNull' is in-fact null, otherwise no contract is started. 'this' is therefore needed again as an argument in the failer-call - to end the engaged contract. 'referenceNull' is an argument for the checker call and as an identification argument in the failer call. 

If the checker-call returns false, no contract has been started and the failer method will - and should not - be called. This is the cheap outcome, where no new allocations are needed e.g. "referenceNull" in above example.

If the checker-call asserts, a contract is started and ended by the expensive failer-call. In above example the failer-call throws a FailFastException with message "ObjectNullTest.testObjectNullFailNoMessage: Object 'referenceNull' is null. " (for more information see https://github.com/KeldOelykke/FailFast/wiki/isObjectNull-&-failObjectNull).
Each checker-failer pair is associated with an exception type, a message format and a message arguments mapping. In above example the checker-failer pair is by default set to exception type FailFastException, message format "%s: Object '%s' is null." and arguments mapping "fu0, fu1" (meaning the 2 failer user arguments).

Is this enough for you? Well, I don't know. Let's figure that one out together. Any feedback is greatly appreciated.

Some of your questions might be answered in the FAQ below, otherwise please create an issue.
 
 
FAQ
===
 - Is the library free? Yes.
 - Is the library open source? Yes.
 - Is the library threadsafe? Yes.
 - How do I get started? Download a jar (see Releases section below), put in the classpath either use the library directly (https://github.com/KeldOelykke/FailFast/wiki/Setup-&-Shutdown) or wrap it yourself (see FAQ "Can I extend and customize it?").
 - Can I extend it? Yes (see  https://github.com/KeldOelykke/FailFast/tree/master/Java/Examples/src/starkcoder/failfast/examples/reference2failfast/myfailfast).
 - Which types are supported? Please refer to the wiki (https://github.com/KeldOelykke/FailFast/wiki).
 - Can I customize exceptions thrown? Yes (Since 1.2) in 3 ways. Dynamically per checker-failer-call by customizing the call contract: See section 'Contract Customization' in (https://github.com/KeldOelykke/FailFast/blob/master/Java/Examples/src/starkcoder/failfast/examples/exceptionmessages2customize/ExceptionMessages2CustomizeTest.java). Dynamically e.g. at application start by customizing the Failer instance: See section 'Failer Customizations' in (https://github.com/KeldOelykke/FailFast/blob/master/Java/Examples/src/starkcoder/failfast/examples/exceptionmessages2customize/ExceptionMessages2CustomizeTest.java). Statically by overriding specifications via inheritance (https://github.com/KeldOelykke/FailFast/tree/master/Java/Examples/src/starkcoder/failfast/examples/reference2failfast/myfailfast/withoverrides). You can also refer to the wiki (https://github.com/KeldOelykke/FailFast/wiki#exception-customizations).
 - Can I check if a failfast exception has been thrown? Yes (https://github.com/KeldOelykke/FailFast/tree/master/Java/Examples/src/starkcoder/failfast/examples/exceptionhandling).


Examples
========

The wiki has a lot of test examples that hopefully makes sense to you:
 - https://github.com/KeldOelykke/FailFast/wiki

The wiki is really a handedited proxy for 2 test packages:
 - https://github.com/KeldOelykke/FailFast/tree/master/Java/Examples/src/starkcoder/failfast/examples
 - https://github.com/KeldOelykke/FailFast/tree/master/Java/UnitTests/src/starkcoder/failfast/unit/objects

Continuous Integration
======================

Every new commit triggers a build and unit test run on Travis CI: 
 - [![Build Status](https://travis-ci.org/KeldOelykke/FailFast.svg?branch=master)](https://travis-ci.org/KeldOelykke/FailFast)

Releases
===================

Go to http://oelykke-failfast.appspot.com to:
 - download jars,
 - view api (javadoc),
 - view unit test results (junit) and coverage (cobertura),
 - view example test results (junit), and
 - try the checker-failer pairs online.
 
Alternatively, jars can be downloaded from GitHub:
 - https://github.com/KeldOelykke/FailFast/releases
 - https://github.com/KeldOelykke/FailFast/tree/master/Java/Web/war/releases

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

The 1st major version of this library is to support basic constructs with a check & fail-pair (see http://starkcoder.blogspot.dk/2013/06/how-could-failfast-library-work.html).

If you think the check & fail-pair construct is odd, you might want to checkout the spot contractor pattern (http://yoawsconsult.blogspot.dk/2012/11/spot-contractor-software-pattern.html). The pattern enforces correctness and performance.

Confidence in the current design is growing. The design allows check & fail-methods to be "managed" from another jar e.g. replaced and/or extended. With inheritance it is possible to override all functionality, but in many cases customization of failer-annotations will do e.g. to change failer-output formatting.

The data type related scope of the 1st major version is check & fail-pairs that can handle objects wrapping primitives and a few basic object types from the standard library. The 8 primitives are byte, short, int, long, float, double, boolean and char. Implementation is first done via the primitives' wrapping Objects (Byte, Short, etc.). Later on the primitives might be directly implemented to avoid any boxing performance penalties (I have found no way of measuring these!?). Additional Object types are Object, String, Enum, UUID and Date. 

The scope of the 2nd major version is check & fail-pairs that can handle arrays and collection of above mentioned types. Furthermore, more types and check & fail-pairs might be added.

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


