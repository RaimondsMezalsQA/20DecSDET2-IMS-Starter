Coverage: 61.2%
# Inventory Management System (IMS)

The purpose of this project is to create an inventory management system that would be able to manage and store items, customers and orders using a database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
There are 4 main things that need to be installed on your machine to be able to run this project and its tests.
These are:
Appropriate java version (At least java 8)
Apache Maven
Integrated Development Environment (IDE) For example Eclipse
MySQL database

Both apache maven and java can be installed through their own respective files as a zip file. After extracting these files both apache maven and java will need their paths to be set in the environmental variables.
As for the database you could either use Google Cloud Platform or a local instance of MySQL through MySQL Workbench.
```

### Installing

A step by step series of examples that tell you how to get a development env running

To set up a development environment for this project you would need to use an IDE such as Eclipse. The first thing to do would be to import the project within the IDE.
IMMAGE
 
After this the project is ready for development. During this stage you can run both the runner which would run the whole program and the tests for the system.
 
IMMAGE
To run the program itself you would need to right click the runner and run it as a java application.
This is an example of a program being run within the IDE.
IMMAGE
 

## Running the tests

 
IMMAGE
To run the tests, you need to right click the test folder, press coverage as and run as a Junit test.

### Unit Tests 

The purpose of unit tests is to test some of the smaller units of the program. This would include classes, methods, and specific values within the classes. Once unit tests are created, they can be run as many times as you would like, meaning that they are automated.

 
IMMAGE
This is an example of a test which purpose is to check if a certain class in this case the Orders class is being run in the first place.

 
IMMAGE
This is another example of unit test’s being run. In this case they are testing the methods within the OrdersDao class. It tests the functionality of all methods such as create, read, delete, etc.

## Deployment

To use this project outside of an IDE you would need to use a fat jar. One is already made and located in the project root folder and the project target folder of the project. If there is a need to create another fat jar you would need to run “mvn clean package” within the console while being located within the project folder. This would create a new fat jar in the projects target folder.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

* **Raimonds Mezals** - *finished project* - [raimondsmezals](https://github.com/RaimondsMezalsQA)


## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* I would like to say thanks to my trainer Johnson Nicholas for supporting the through the projects and helping me to fix bugs that I struggled the most with.
* I also would like to acknowledge my teammates Cameron, Henry and Sehun which gave me inspiration on fixing some of the strange errors that appeared during this project.

