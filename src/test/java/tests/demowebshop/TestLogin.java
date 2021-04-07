package tests.demowebshop;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;


public class TestLogin extends TestBase {

  @Test
  void loginWithUiTest() {
    open("/login");
    $("#Email").val("testovichana@gmail.com");
    $("#Password").val("password").pressEnter();

    $(".account").shouldHave(text("testovichana@gmail.com"));

  }

  @Test
  void loginWithApiTest() {
    Map<String, String> cookies =
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("Email", "testovichana@gmail.com")
                    .formParam("Password", "password")
                    .when()
                    .post("/login")
                    .then()
                    .log().body()
                    .statusCode(302)
//            .body("success", is(true))
                    .extract().cookies();

    open("/Themes/DefaultClean/Content/images/logo.png");
    getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookies.get("Nop.customer")));
    getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookies.get("NOPCOMMERCE.AUTH")));
    getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookies.get("ARRAffinity")));

    open("");
    $(".account").shouldHave(text("testovichana@gmail.com"));
  }
}
