gorm-standalone
===============

This project is for using GORM outside a Grails application. The GORM version is 2.0.3 for Hibernate

## Requirements

First you need to setup the classpath, this project uses Gradle, but you can see the dependencies for other build systems like Maven.

Next, if you want to use GORM in your app, you need to use Spring and to define a lot of Spring Beans. In this project, you can see which beans you need to configure.

Please see the following file https://github.com/domix/gorm-standalone/blob/master/src/test/resources/appCtxGorm.xml

Simple like that, take a look into the test cases to see GORM in Action.

Enjoy and please send pull request if you want to improve this.