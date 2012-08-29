# topherizer

![screen grab](https://github.com/downloads/cassiel/topherizer/EmacsScreenSnapz001.png)

Topherizer is a Java console application for generating the
`max.java.config.txt` file, which Max's Java users have, for the last
eight years, been painstakingly editing by hand. Topherizer allows the
configuration to be written in Clojure, with a cleaner structure,
conditional "statements" and some shortcut functions.

Main wins:

- Splits sets of JAR files into separate collections, each with its own
  declaration
- Allows different sets of declarations to be included, or excluded, by
  means of conditional tests
- Allows groups of similar declarations to be grouped with a common
  prefix, rather than duplicating large sections of the text
- Supports arbitrary pre-processing on its input: use flags to move
  declarations between the `system` and `dynamic` class loaders, verify
  that JAR files exist, and so on

(It is arguable whether, at first glance, Clojure is an improvement as
an input format. Editing Clojure in a conventional text editor is a
fiddly procedure, but there are editors like Emacs (with Sam Aaron's
[emacs-live][el]) which make the procedure much more efficient.)

## Usage

Download the standalone "uberjar" from the [downloads][dl] page. Run it
on the command line with

```bash
java -jar topherizer-1.0.0-standalone.jar [input] [output]
```

where `input` is the input (Clojure) file and `output` is
`max.java.config.txt`, presumably in the `Cycling '74/java`
directory. (Back up the old one first!)

As a shortcut, if a single argument is provided, as in

```bash
java -jar topherizer-1.0.0-standalone.jar [file]
```

then the output file is named after the input file, but with any
extension removed and replaced by `.txt`.

## Tutorial

For the actual Clojure markup used as input, see the [tutorial][tut].

## Building

Topherizer is a conventional [Leiningen][lein] project; all dependencies
are downloaded and linked in the conventional manner.

## License

Copyright © 2012 Nick Rothwell

Distributed under the Eclipse Public License, the same as Clojure.

[el]: https://github.com/overtone/emacs-live
[dl]: https://github.com/cassiel/topherizer/downloads
[lein]: https://github.com/technomancy/leiningen
[tut]: https://github.com/cassiel/topherizer/blob/master/TUTORIAL.md
