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

Confidence in the current design is getting high now, though it still needs some tests to prove that
 - the check & fail-methods can be "managed" from another jar e.g. replaced and/or extended. Inheritance should take care of most, but it is unknown whether the failer-annotations can be modified e.g. to change failer-output formatting, and
 - support for arrays and collections is possible.

The scope of the 1st major version is check & fail-pairs that can handle primitives and basic objects from the standard library. Since each type seems to require around 10(+/-4) method pairs (see wiki link below), it takes a while to get the implementation complete; 2015 seems reasonable.

Examples
========

https://github.com/KeldOelykke/FailFast/wiki


Builds, Tests & Jar
===================

https://keldoelykke.ci.cloudbees.com/job/FailFast/ (Sorry this is broken, sign up to free program for this project failed in practice. I might have to find another cloud-based build system.)

![Cloudbees is Cool!](http://cloudbees.prod.acquia-sites.com/sites/default/files/styles/large/public/Button-Built-on-CB-1.png)


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


![You know it makes sense!](http://cloudbees.prod.acquia-sites.com/sites/default/files/styles/large/public/Button-Powered-by-CB.png)
