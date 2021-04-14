package tests.demowebshop;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static utils.UtilsFile.readStringFromFile;

class TestDemowebshop extends TestBase {

  @Test
  @Tag("shop")
  @DisplayName("Уведомление по email")
  void notifyByMail() {
    String cookie = readStringFromFile("./src/test/resources/cookie_demowebshop.txt");

    Response response =
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie(cookie)
                    .when()
                    .post("/productemailafriend/16")
                    .then()
                    .log().body()
                    .statusCode(200)
                    .extract().response();
    Assertions.assertTrue(response.htmlPath()
            .getString("**.findAll{it.@class == 'page-title'}.h1")
            .contains("Email a friend"));
  }

  @Test
  @Tag("shop")
  @DisplayName("Добавить товар в корзину")
  void testAddCart() {
    String cookie = readStringFromFile("./src/test/resources/cookie_dwsh_cart.txt");
    String body = readStringFromFile("./src/test/resources/body_dwsh.txt");

    Response response =
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie(cookie)
                    .body(body)
                    .when()
                    .post("/addproducttocart/details/16/1")
                    .then()
                    .log().body()
                    .statusCode(200)
                    .body("success", is(true))
                    .extract().response();
    System.out.println(response);
  }

}
