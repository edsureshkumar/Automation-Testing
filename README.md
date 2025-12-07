# Automation Framework
# SmartTestHub – Java TestNG Automation Framework

SmartTestHub is a custom automation framework built using **Java, TestNG, Selenium WebDriver, RestAssured, and Maven**.

This project was created to demonstrate real-time automation skills for **UI and API testing**, following good framework practices like Page Object Model and config-driven execution.

---

## 🎯 Objectives

- Show end-to-end automation skills suitable for QA / Automation Engineer roles
- Combine **UI + API testing** in a single framework
- Use a clean, maintainable structure that recruiters can easily understand

---

## 🧱 Tech Stack

- **Language:** Java  
- **Test Framework:** TestNG  
- **UI Automation:** Selenium WebDriver  
- **API Automation:** RestAssured  
- **Build Tool:** Maven  
- **Design Pattern:** Page Object Model (POM)

---

## 📂 Project Structure

```text
src/
├─ main
│ └─ java
│ └─ framework
│ ├─ config # Configuration management (ConfigManager)
│ ├─ core # BasePage, DriverFactory
│ └─ utils # Wait utilities, helpers
└─ test
   ├─ java
   │ └─ tests
   │ ├─ ui # UI test cases + page classes
   │ └─ api # API test cases
   └─ resources
      ├─ config.properties
      └─ testng.xml
