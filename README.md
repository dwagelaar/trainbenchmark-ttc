# The TTC 2015 Train Benchmark Case

[![Build Status](https://travis-ci.com/dwagelaar/trainbenchmark-ttc.svg?branch=master)](https://travis-ci.com/dwagelaar/trainbenchmark-ttc)

**Note.** Although closely related, this repository is not to be confused with the `trainbenchmark` repository which is a benchmark framework for comparing various database management tools, including triplestores, relational databases and graph databases.

## Case description

The `paper/trainbenchmark-ttc.pdf` file contains the [case description](https://github.com/FTSRG/trainbenchmark-ttc/blob/master/paper/trainbenchmark-ttc.pdf), while the [latest compile](https://www.sharelatex.com/github/repos/FTSRG/trainbenchmark-ttc-paper/builds/latest/output.pdf)  is also available.

## Prerequisites

* 64-bit operating system (Ubuntu-based Linux systems are recommended)
* [Oracle JDK 7+](https://github.com/FTSRG/cheat-sheets/wiki/Linux#oracle-jdk-7)
* [Maven 3.0+](https://github.com/FTSRG/cheat-sheets/wiki/Linux#maven-3)

## Platform dependencies

1. Install Maven 3 and make sure it is on your path (check with `mvn --version`).
1. Make sure you have Python 3 installed.

## Using the framework

The `scripts` directory contains the `run.py` script which is used for the following purposes:
* `run.py -b` -- builds the projects
* `run.py -b -s` -- builds the projects without testing
* `run.py -g` -- generates the instance models
* `run.py -m` -- runs the benchmark
* `run.py -v` -- visualizes the results of the latest benchmark

The `config` directory contains the configuration for the scripts:
* `config.json` -- configuration for the model generation and the benchmark
* `reporting.json` -- configuration for the visualization

### Generating instance models

Set the `maxSize` variable to the desired value and run the `run.py -g` script. With enough memory (`-Xmx2G` or more), the models from size `1` to size `512` are generated in about 5 minutes.

### Running the benchmark

The script runs the benchmark for the given number of runs, for the specified tools, queries and sizes.

The benchmark results are stored in a TSV (tab-separated values) file. The header for the TSV file is stored in the `output/header.tsv` file. 

## Reporting and visualization

Make sure you read the `README.md` file in the `reporting` directory and install all the requirements for R.

## Importing to Eclipse

It is recommended to start with an Eclipse distribution tailored to developing EMF-based applications, e.g. Eclipse Modeling.

If you'd like to try the ATL/EMFTVM implementation, it is recommended to use **Eclipse Luna**. There are two ways to resolve the dependencies:

1. **Maven dependencies** (`pom.xml` files). This requires the m2e Eclipse plugin (this is included in Eclipse for Java developers but is not included in Modeling distribution). The m2e plugin can be installed from the the update site of your release (Kepler/Luna).
2. **Plug-in dependencies** (`MANIFEST.MF` files).
  * Use the Orbit update site for your release (<http://download.eclipse.org/tools/orbit/downloads/>) to install the **Apache Commons CLI** and the **Guava: Google Core Libraries for Java Source** plug-ins.
  
  * If you wish to run the ATL/EMFTVM implementation, install ATL/EMFTVM and SimpleGT from <http://marketplace.eclipse.org/content/atlemftvm> and <http://marketplace.eclipse.org/content/simplegt>.

In general, we recommend to stick to your proven build solution, else you may spend a lot of time tinkering with the build. In theory, you can build Eclipse plug-ins with the Tycho Maven plug-in, however, it has a steep learning curve and is tricky to debug. For reference, see <https://github.com/FTSRG/cheat-sheets/wiki/Maven-and-Eclipse>.

## Implementing the benchmark for a new tool

To implement a tool, it is recommended to start from an existing implementation. Please implement your own  [benchmark logic](https://github.com/FTSRG/trainbenchmark-ttc/blob/master/hu.bme.mit.trainbenchmark.ttc.benchmark.java/src/main/java/hu/bme/mit/trainbenchmark/ttc/benchmark/java/JavaBenchmarkLogic.java) and [benchmark case factory](https://github.com/FTSRG/trainbenchmark-ttc/blob/master/hu.bme.mit.trainbenchmark.ttc.benchmark.java/src/main/java/hu/bme/mit/trainbenchmark/ttc/benchmark/java/benchmarkcases/JavaBenchmarkCaseFactory.java) which instantiates the classes for each query defined in the benchmark.

### Matches

In order to make the fewest assumptions on the specific implementations, the pattern matches are stored in the variable `matches` declared as a `Collection<Object>` (see the [AbstractBenchmarkCase](https://github.com/FTSRG/trainbenchmark-ttc/blob/master/hu.bme.mit.trainbenchmark.ttc.benchmark/src/main/java/hu/bme/mit/trainbenchmark/ttc/benchmark/benchmarkcases/AbstractBenchmarkCase.java) class). The framework requires the `matches` collection to be unique.

#### Comparators

To enable a consistent ordering of the matches, the framework requires a comparator class. Section 2.4.2 ("Ordering of the Match Set") in the [case description](#case-description) defines the rules of the ordering.

For implementing a match comparator, we recommend two approaches:
* If the matches are represented in a tuple-like collection, they can be compared by iterating through the collection and comparing each elements in the tuple. Example: the [EMFIncQueryBenchmarkComparator](https://github.com/FTSRG/trainbenchmark-ttc/blob/master/hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery/src/main/java/hu/bme/mit/trainbenchmark/ttc/benchmark/emfincquery/matches/EMFIncQueryBenchmarkComparator.java) class.
* Use [ComparisonChain](http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/ComparisonChain.html) class in Google Guava to compare the model elements in the matches. Example: the [JavaRouteSensorMatchComparator](https://github.com/FTSRG/trainbenchmark-ttc/blob/master/hu.bme.mit.trainbenchmark.ttc.benchmark.java/src/main/java/hu/bme/mit/trainbenchmark/ttc/benchmark/java/matches/JavaRouteSensorMatchComparator.java) class.

### Naming conventions

To avoid confusion between the different implementations, we decided to use the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). This way, the classes in the Java implementation are named `JavaBenchmarkCase`, `JavaPosLength`, `JavaPosLengthMatch`, `JavaPosLengthTransformation`, while the classes in the EMF-IncQuery implementation are named `EMFIncQueryBenchmarkCase`, `EMFIncQueryPosLength`, etc. We found that relying on the package names to differentiate class names like

* `hu.bme.mit.trainbenchmark.ttc.benchmark.java.BenchmarkCase` and 
* `hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.BenchmarkCase`

is error-prone and should be avoided.

## Troubleshooting

* **Problem:** if not running with Oracle JDK7, both the generation and the benchmarking freezes sometimes.
* **Solution:** see [this issue](https://github.com/FTSRG/trainbenchmark-ttc/issues/7) for details.
