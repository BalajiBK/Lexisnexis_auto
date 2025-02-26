# LexisNexis

## Overview
This project is built using Java and utilizes several plugins and tools for testing and logging. The primary tools used in this project include:

- **Log4j**: For detailed logging.
- **TestNG**: As the test runner.
- **Cucumber**: For Behavior-Driven Development (BDD).

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- IntelliJ IDEA

## Project Structure
- **src/main/java**: Contains the page object model and selenium code.
- **src/test/java**: Contains test code, including Cucumber feature files and TestNG tests.
- **src/test/resources**: Contains resources required for testing, such as Cucumber feature files.

## Logging
This project uses Log4j for logging. You can configure logging settings in the `log4j2.properties` file located in the `src/test/resources` directory.

## Testing Framework
### TestNG
TestNG is used for running tests. 

### Cucumber
Cucumber is used for writing BDD-style tests. Feature files are in the `src/test/resources` directory.

## Running Tests
You can run the tests in the following ways:

1. **Using Maven Command**:
   Open your terminal and navigate to the project directory. Then run:
   ```bash
   mvn clean test
2. **Using IDE: Right-click on the runner class and select "Run" to execute the tests.

## Reports
## Extent Reports
Extent Reports are generated to provide a detailed overview of the test results. You can find the reports in the **Reports directory after running the tests.

## TestNG Reports
TestNG will generate its own report in the test-output directory.
