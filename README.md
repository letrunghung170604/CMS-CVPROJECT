**CMS Automation Testing 🧪**

**📌 Overview**

This project is an automated testing framework for the CMS web application, built with Selenium WebDriver, TestNG, and Java. It tests core functionalities of the user-facing e-commerce features, including login, profile management, product retrieval, cart operations, and checkout processes.

The framework supports:

- Parallel execution
- Logging
- Allure reporting
- Screen recording
- Screenshots for failed tests
- Test data management via Excel

**Features 🛠️**

**E-Commerce Module 🛒**

- Login with Customer Account: Validates login scenarios for invalid email, invalid password, and successful login.
- Profile Management: Updates user profile details (e.g., name, phone, password, photo).
- Product Retrieval: Retrieves and saves product details (e.g., name, price, quantity, total price, description) to an Excel sheet.
- Add to Cart: Adds two arbitrary products to the shopping cart.
- Checkout Process: Executes a full checkout flow with the following steps:
    1. My Cart
    2. Shipping Info
    3. Delivery Info
    4. Payment
    5. Confirmation (verifies order status)

**Framework Features 📊**

- Parallel execution with TestNG ⚡
- Data-driven testing using Excel 📈
- Logging with Log4j 📜
- Allure reports for test results 📊
- Screen recording with Monte Screen Recorder 🎥
- Screenshots for failed tests 📸

**Test Execution Flow 🏃‍♂️**

**Setup 🛠️**

- Initialize WebDriver (Chrome/Firefox/Edge) via testng.xml.
- Load configurations from PropertiesFile.properties.
- Read test data from ExcellFile.xlsx 📈.

**Execution ⚡**

- E-Commerce Module 🛒:
  - Login: Authenticate with user credentials (read from Excel).
  - Profile Management: Update user profile details and verify changes.
  - Product Retrieval: Get details of selected products (e.g., iPhone case, newspaper) and save to Excel.
  - Add to Cart: Add two products (e.g., Laptop Gaming, Cosy Anh Tester) to the cart and verify.
  - Checkout: Complete the checkout process (cart, shipping, delivery, payment, confirmation) and verify order status.
- Full Flow: Execute complete flow: Login → Add to Cart → Checkout Success.
- Admin Validation (Note: Partial implementation in provided code):
  - Verify product details added via user page in the admin page.

**Logging & Reporting 📊**

- Log steps using Log4j (Logs/LogPropertiesFile.log) 📜
- Capture screenshots for failed tests (ExportData/Images) 📸
- Record videos of test execution (ExportData/Videos) 🎥
- Generate Allure reports for detailed results 📊

**Teardown 🧹**

- Quit WebDriver and clean up resources.

**View Reports**

- **Allure**: allure serve target/allure-results 📊
- **Screenshots**: ExportData/Images 📸
- **Videos**: ExportData/Videos 🎥
- **Logs**: Logs/LogPropertiesFile.log 📜

**Dependencies 📦**

| **Dependency** | **Version** | **Purpose** |
| --- | --- | --- |
| TestNG | 7.11.0 | Test framework 🧪 |
| WebDriverManager | 6.1.0 | WebDriver binary management 🚀 |
| Apache POI | 5.4.1 | Excel file handling 📈 |
| Log4j | 2.25.1 | Logging 📜 |
| Monte Screen Recorder | 0.7.7.0 | Screen recording 🎥 |
| Allure TestNG | 2.29.1 | Test reporting 📊 |
| DataFaker | 2.4.2 | Test data generation 🎲 |

**Contributors 👤**

- Le Trung Hung
