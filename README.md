[![Build Status](https://travis-ci.org/sojant/sugar-bowl.svg?branch=master)](https://travis-ci.org/sojant/sugar-bowl)

# sugar-bowl
This is a base REST API Project using: Java + Springboot + SpringMVC + Hibernate

## Getting Started
This repo is meant to be used as a boilerplate to speed up REST APIs in Java.
Adding models, daos, should be straightforward, tests are also added for simple CRUD operations.

## Installing

Make a clone of the project, then use the Gradle Wraper (which comes bundled) to build, run or test.

*Nix users, first give the Gradle Wrapper exec Permissons
```
chmod +x ./gradlew
```

## Running the tests

To run the Tests

```
.\gradlew.bat check (Windows)
./gradlew check (Unix)
```

This will execute the tests and leave the results at:
<ProjectDir>\build\reports\tests\test

This project is also setup in Travis for CI
[Sugar Bowl Travis](https://travis-ci.org/sojant/sugar-bowl)

Testing uses an in-memory H2 Database

## Building and Running

To run the project:

Provide appropriate settings for conecting to a reachable database:
application.properties

Run this task to delete/create models:

```
.\gradlew.bat executeCreateModels (Windows)
./gradlew executeCreateModels (Unix)
```

Once models are created in your Database simply run:

```
.\gradlew.bat bootRun (Windows)
./gradlew bootRun (Unix)
```

And test at http://localhost:8080/

## Built With

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java SE
* [Gradle](https://docs.gradle.org/) - Build Tool
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
* [Spring](https://spring.io/) - Architecture
* [Hibernate](http://hibernate.org/) - Persistence

## Author

* **Jose Vazquez** - (https://github.com/sojant)
