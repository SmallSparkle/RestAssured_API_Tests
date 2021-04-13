package tests.reqresIn;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {

  @BeforeAll
  static void setup() {
    addListener("AllureSelenide", new AllureSelenide());
    if (System.getProperty("remote_driver") != null) {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      Configuration.browserCapabilities = capabilities;
      Configuration.remote = System.getProperty("remote_driver");
    }
    RestAssured.filters(new AllureRestAssured());
    RestAssured.baseURI = "https://reqres.in/";
  }
}
