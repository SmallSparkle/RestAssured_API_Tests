package tests.demowebshop;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;



public class TestBase {

  @BeforeAll
  static void setup() {
    RestAssured.filters(new AllureRestAssured());
    RestAssured.baseURI = "http://demowebshop.tricentis.com";
    Configuration.baseUrl = "http://demowebshop.tricentis.com";
  }
}
