# Swagger [Petstore](https://petstore.swagger.io/) API tests

## 📜 Summary
- [Stack](#-stack)
- [Automated test cases](#-automated-test-cases)
- [Build](#-build)
- [Jenkins](#-jenkins)
- [Allure report](#-allure-report)
- [Notifications](#-notifications)

  
## 💻 Stack

<div align="center">
  <table>
    <tr>
      <!-- First row -->
      <td align="center" width="110">
        <a href="https://www.java.com" target="_blank">
          <img src="readmemedia/java-original-wordmark.svg" width="48" height="48" alt="Java" />
        </a>
        <br>Java 21
      </td>
      <td align="center" width="110">
        <a href="https://junit.org/junit5/" target="_blank">
          <img src="readmemedia/junit-original.svg" width="48" height="48" alt="JUnit 5" />
        </a>
        <br>JUnit 5
      </td>
      <td align="center" width="110">
        <a href="https://gradle.org/" target="_blank">
          <img src="readmemedia/gradle-original.svg" width="48" height="48" alt="Gradle" />
        </a>
        <br>Gradle
      </td>
      <td align="center" width="110">
        <a href="https://selenide.org/" target="_blank">
          <img src="readmemedia\Selenide.svg" width="48" height="48" alt="Selenide" />
        </a>
        <br>Selenide
      </td>
    </tr>
    <tr>
      <!-- Second row -->
      </td>
      <td align="center" width="110">
        <a href="https://docs.qameta.io/allure/" target="_blank">
          <img src="readmemedia/Allure.svg" width="48" height="48" alt="Allure" />
        </a>
        <br>Allure
      </td>
    <td align="center" width="110">
        <a href="https://rest-assured.io/" target="_blank">
          <img src="readmemedia/restassuredlogo.png" width="48" height="48" alt="Rest Assured" />
        </a>
        <br>Rest Assured
      </td>
      <td align="center" width="110">
        <a href="https://www.jenkins.io/" target="_blank">
          <img src="readmemedia/jenkins-original.svg" width="48" height="48" alt="Jenkins" />
        </a>
        <br>Jenkins
      <td align="center" width="110">
        <a href="https://web.telegram.org/" target="_blank">
          <img src="readmemedia/Telegram.svg" width="48" height="48" alt="Telegram" />
        </a>
        <br>Telegram
      </td>
    </tr>
  </table>
</div>

- Developed using **Java 21**, **JUnit**, and **Selenide**
- Built via **Gradle**
- Run using **Selenoid** containers
- Integrated build with **Jenkins**
- Sending notifications to **Telegram**

## ✅ Automated scenarios
1. Create a new pet
2. Find a pet
3. Update a pet's name
4. Update a pet's status
5. Delete a pet

## ▶️ Build

The command to launch tests:
```bash
gradle clean test
```

## <img src="readmemedia/jenkins-original.svg" width="48" height="48" alt="Jenkins" /> [Jenkins](https://jenkins.autotests.cloud/job/RedFlagMarketingSiteTests/)

The project is integrated with Jenkins, where the parametrized tests can be run remotely within the QA GURU environment.

<img src="" alt="Jenkins Main Page">
//TODO:

## <img src="readmemedia/Allure.svg" width="48" height="48" alt="Allure" /> [Allure report](https://jenkins.autotests.cloud/job/KupsillaWebSite/6/allure/)

### Allure report page
<img src="" alt="Allure Report">
//TODO:

### Each test in the report contains:
- Test steps
//TODO:
  

## <img src="readmemedia/Telegram.svg" width="48" height="48" alt="Telegram" /> Notifications

After each run a notification is sent automatically to **Telegram** with a brief summary of the test results including the number of passed and failed tests, run duration, and report link. This functianlity is implemented using the [allure-notifications](https://github.com/qa-guru/allure-notifications) library which also allows sending notifications to Slack, Discord, and other messengers.

<img src="readmemedia/TgNotification.png" alt="Telegram Notification">
