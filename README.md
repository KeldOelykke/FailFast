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
 

Target Audience
===============

Object-Oriented software developers using Java/C#.


Goal
====

We migrate between projects and we ask ourself what libraries should we pull this time? 
Most times I search for a fail-fast library, but I can't seem to find any.
Last time I didn't even bother to search.

Clearly, that is no good to anyone, so here is a go at a fail-fast library.


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

I change my opinions often. Sometimes because I improve, sometimes because I am wrong.

Please help me change my opinions and this library, if you feel like it.


Kind Regards,
Keld Ølykke