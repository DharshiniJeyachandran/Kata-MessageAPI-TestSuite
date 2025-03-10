1. Message API Test Suite
   ## Overview
   This project is a test automation framework for the **Message API** endpoint of the hotel booking system at [Automation in Testing](https://automationintesting.online/). The tests are implemented using **Cucumber**, **Rest-Assured**, and **Java** to validate both **GET** and **POST** requests, ensuring API compliance and correctness.
   ## Features
    - ✅ Automated API validation for Message API
    - ✅ Schema validation for response structure
    - ✅ Functional and negative test cases
    - ✅ Data-driven testing using Cucumber **Scenario Outlines**
   ---
## Test Scenarios
This test suite validates the Message API with the following:
- ✅ GET Request: Retrieve Messages
- ✅ POST Request: Create a Message (Valid Data)
- ❌ POST Request: Handle Invalid Inputs (Negative Cases)
- ✅ JSON Schema Validation
- ✅ Status Code Validation


## **Setup & Execution**

### **🔹 Prerequisites**

- Java 17 or higher installed
- Maven installed
- IntelliJ IDEA or any Java IDE
- Git installed

### **🔹 How to Run the Tests**

1. Clone the repository:
   ```sh
   git clone https://github.com/DharshiniJeyachandran/Kata-MessageAPI-TestSuite.git
   cd Kata-MessageAPI-TestSuite
   ```
2. Install dependencies:
   mvn clean install
   
3. Run the Cucumber tests:
   
   mvn test
   

## **📌 Technologies Used**

- **Java** – Core programming language
- **Rest-Assured** – API testing library
- **Cucumber** – BDD framework
- **Maven** – Dependency management

