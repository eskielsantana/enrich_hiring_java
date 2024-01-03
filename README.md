# Enrich-Hiring-Java

<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg"></a>

## About

This project assesment was done in 2021, and I am now adding it to a public repository and fixing some missed issues.

### Problem

In Europe, someone could rent a truck from one of our customers, and they could conceivably drive that
truck across several countries in one day. Those countries each have their own vehicle registry system,
and they don't talk directly to each other. If you rented a truck in France and drove it through
Switzerland and Germany, and you got into an accident along the way, the police would have a hard
time tracking your vehicle registration to the truck's insurance policy, which is often provided by the
company from which the vehicle is rented.
The European Union came up with a solution to this problem that requires any trucking company to
upload details (vehicle registration number -> insurance policy number) to an EU server that tracks this
information. The police can log into the shared system and easily find the current insurance policy for
this vehicle given its registration number.

### Goals

* Create a cron job to execute veryday at midnight.
* Load data stored in a Mysql database
* Convert this data into a csv file
* Upload the generated csv file to an external HTTP Endpoint

## Project Structure

The project follows a clean architecture guideline. There are two main abstractions.

Domain is the place to keep only business rules. The other places can import classes from the domain, but the domain should be isolated from framework classes and annotations that can create a strong coupling between the business rules and the app structure. This approach makes the process of segregating the business from one specific domain to another. Another advantage of this approach is that it lets us better manage our test strategy. The test domain package executes mainly unit tests to check business rules. We use mocks to simulate the interface's implementation and inputs to do this. Otherwise, the test infrastructure package is responsible for keeping the integration tests.

The Infrastructure package will keep the necessary implementations to integrate the app with external services (repositories, queues, etc.), implement contracts defined at the domain level (abstract classes and interfaces) and set up the app, loading data from environment variables or external services (AWS app config). As this package contains mainly classes from frameworks, the test for this package is focused on integrations

The app was created respecting the **SOLID** principles, using interfaces to define implementation contracts, keeping single responsibility, implementing dependency inversion (infrastructure depends on the domain), etc. This app uses GitHub actions to atomate app build and uses Sonarcloud to check the quality of code and the test coverage.

```
├── .gitignore
├── pom.xml
├── dailyVehicleReport.cvs
├── README.md
├─── src
    ├─── main
    │   ├─── java
    │   │   ├─── domain
    │   │   │   ├─── helper
    │   │   │   │       DateHelper.java
    │   │   │   │
    │   │   │   ├─── report
    │   │   │   │       FileManager.java
    │   │   │   │       ReportService.java
    │   │   │   │
    │   │   │   ├─── scheduled
    │   │   │   │       ScheduleService.java
    │   │   │   │
    │   │   │   ├─── upload
    │   │   │   │       UploadService.java
    │   │   │   │
    │   │   │   └─── vehicle
    │   │   │           Vehicle.java
    │   │   │           VehicleInsurance.java
    │   │   │           VehicleRepository.java
    │   │   │           VehicleService.java
    │   │   │
    │   │   └─── infrastructure
    │   │       │   MainClass.java
    │   │       │
    │   │       ├─── file
    │   │       │       CSVFileManager.java
    │   │       │
    │   │       ├─── insurance
    │   │       │       Insurance.java
    │   │       │
    │   │       └─── vehicle
    │   │               JPAVehicleRepository.java
    │   │               Vehicle.java
    │   │
    │   └─── resources
    │       │   application-test.properties
    │       │   application.properties
    │       │   data
    │       │   log4j.properties
    │       │
    │       └─── META-INF
    └─── test
        └─── java
            │   ReportServiceTest.java
            │
            └─── domain
                ├─── helper
                │       DateHelperTest.java
                │
                └─── report
                        ReportServiceTest.java
```

## Install
#### Download the repository
```sh
$ git clone https://github.com/eskielsantana/enrich_hiring_java.git
```

#### Run tests
```sh
$ cd enrich_hiring_java && mvn clean test
```

#### Run application
```sh
mvn exec:java
```
## Author

* **Ezequiel Santana** - [LinkedIn](https://www.linkedin.com/in/ezequiel-santana/)