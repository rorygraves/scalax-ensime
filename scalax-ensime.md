% Ensime - The other IDE
% Rory Graves
% Scala eXchange December 2014

TODO - Titles at the top
TODO - Commit code on Github
TODO - Add a nice ensime pic
TODO - Basic structure Pic

-------

## Introduction

- What is Ensime
- A little bit of history
- What it does?
- How it does it.
- Our development process.
- The future

----------

## What is ensime?

> * ENhanced Scala Interaction Mode for text Editors

> * Originally designed for Emacs

> * Its really an IDE server

> * which leads to some interesting possibilities



# A little bit of history

Started as a lark by @aemoncannon when, as a seasoned elisper, he had a lightbulb moment with the presentation compiler

##

It started as a hacked version of SLIME.

##

Aemon was in the midst of his MSCS, applied for Google Summer of Code and was accepted

(mentored by Hubert Plociniczak from EPFL).

##

Over GSOC added all of the key elements of a basic IDE (jump to definition, repl support, debug support, etc)

##

*  Time passes - Aemon spoke at some conferences

##

* Time passes - spare time hacking.

##

* Replaced in memory index with lucene for huge speedup.

##

* Redid debugger using jdi

##

* More time passes

##

* Experiment replacing lucene with graph DB - a failure.

##

* Time passes, Aemon has a job, a baby and little time.

## Present

Aemon's words: ' A band of open source heroes appears...'


# Cool features in Ensime

## Features 1: Editing

* Everything you would expect from Emacs
* Semantic highlighting
* Contextual completion
* Type under cursor
* Add an import for the symbol under the cursor.

## Feature 2: Search and Navigation

* Fast classpath search (types and members).
* Jump to source code or documentation.
* Browse packages and type hierarchies.
* Find all references to a symbol.

## Features 3: Refactoring

Uses the scala-refactoring library.
 (also using by Scala-IDE (eclipse))

Supports operations such as:
* Rename
* Organise imports
* Extract variable
* Extract method

## Features 4: Debugging

All of the features you would expect
* Start/Attach
* Add/remove breakpoints
* Step
* Inspect values

## Bonus features

- All of the goodies in Emacs

* Magit
* Darcula theme

# Ensime development

## Structure

PIC OF BASIC STRUCTURE - front to back

## Continuous release

> * Ensime has a rolling release, pushed to the mainline are built and deployed for you.

> * This has a major impact on our review and testing behaviour

## CI - Jenkins

> * We ended up dropping Travis

## Testing and Coverage

> * We measure and care about coverage - current at 64%

> * Next PR will take this over 75%

> * Extending tests and coverage into Emacs


-- PICK of scoverage

# Extending Ensime

- Ensime

## Adding features

## Adding new editors

Still a bit rough right now.
* Neovim


# Ensime as an API

- Ensime as an analysis API
- Cool possibilities

# The Future

- Increase test coverage (Thanks Scoverage!)
- Increase tests
- More tests
- More features
- More editors
- Java support

# Questions?

http://github.com/ensime/ensime-server
@a_dev_musing
