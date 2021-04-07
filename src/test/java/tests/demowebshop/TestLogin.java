package tests.demowebshop;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


class TestLogin extends TestBase {

  @Test
  void loginWithUiTest() {
    open("/login");
    $("#Email").val("testovichana@gmail.com");
    $("#Password").val("password").pressEnter();

    $(".account").shouldHave(text("testovichana@gmail.com"));

  }

  @Test
  void loginWithApiTest() {
    open("/Themes/DefaultClean/Content/images/logo.png");
    getWebDriver().manage().addCookie(new Cookie("Nop.customer", auth.login().get("Nop.customer")));
    getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", auth.login().get("NOPCOMMERCE.AUTH")));
    getWebDriver().manage().addCookie(new Cookie("ARRAffinity", auth.login().get("ARRAffinity")));

    open("");
    $(".account").shouldHave(text("testovichana@gmail.com"));
  }


}
