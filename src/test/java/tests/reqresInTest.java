package tests;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static utils.UtilsFile.readStringFromFile;

public class reqresInTest {

  @BeforeAll
  static void setup() {
    RestAssured.filters(new AllureRestAssured());
    RestAssured.baseURI = "https://reqres.in/";
  }

  @Test
  public void testUserList() {
    given()
            .when()
            .get("/api/users?page=2")
            .then()
            .statusCode(200)
            .log().body()
            .body("support.text",
                    is("To keep ReqRes free, contributions towards server costs are appreciated!"));
  }

  @Test
  public void testUpdateUser() {
    String data = readStringFromFile("./src/test/resources/update_data.txt");
    given()
            .body(data)
            .when()
            .put("/api/users/2")
            .then()
            .statusCode(200)
            .log().body()
            .body("updatedAt",
                    is(notNullValue()));
  }

  @Test
  public void testDeleteUser() {
    given()
            .when()
            .delete("/api/users/2")
            .then()
            .statusCode(204)
            .log().body();

  }

  @Test
  public void testSuccessLogin() {
    String data = readStringFromFile("./src/test/resources/login_data.txt");
    given()
            .contentType(ContentType.JSON)
            .body(data)
            .when()
            .post("/api/login")
            .then()
            .statusCode(200)
            .log().body()
            .body("token", is(notNullValue()));
  }

  @Test
  public void testUserNotFound() {
given()
        .when()
        .get("/api/users/23")
        .then()
        .statusCode(404)
        .log().body()
        .body(is("{}"));
  }


}
