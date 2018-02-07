# mathml [![Build Status](https://travis-ci.org/gaofei88/mathml.svg?branch=master)](http://travis-ci.org/gaofei88/mathml)
## Project Intro
This project is just a java-implementation for [jure/mathtype_to_mathml](https://github.com/jure/mathtype_to_mathml)

jure/mathtype_to_mathml uses [jure/mathtype](https://github.com/jure/mathtype) to transform an ole bin into a MTEF(v5).
This project uses [danielrendall/Metaphor](https://github.com/danielrendall/Metaphor) to do this conversion.
However, some modifications have been done to daniel's code and thus I changed the packagename.
(If this is a copyright issue, please contact me and I'll correct it)

## Build Jar with dependencies
`maven clean assembly:assembly`

## TO-DO
* There are still some conversions not done yet and it remains as it is now.
* Test will be added in future.
* More xslt template needs to be added/modified
