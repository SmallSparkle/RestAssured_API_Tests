package tests.demowebshop.apiHelper;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Auth {

  public Map<String, String> login() {
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
                    .extract().cookies();
    return cookies;
  }
}

