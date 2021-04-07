package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static utils.UtilsFile.readStringFromFile;

public class reqresInTest extends TestBase {

  @Test
  public void testUserList() {
    given()
            .when()
            .get("/api/users?page=2")
            .then()
            .log().body()
            .statusCode(200)
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
            .log().body()
            .statusCode(200)
            .body("updatedAt",
                    is(notNullValue()));
  }

  @Test
  public void testDeleteUser() {
    given()
            .when()
            .delete("/api/users/2")
            .then()
            .log().body()
            .statusCode(204);

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
            .log().body()
            .statusCode(200)
            .body("token", is(notNullValue()));
  }

  @Test
  public void testUserNotFound() {
    given()
            .when()
            .get("/api/users/23")
            .then()
            .log().body()
            .statusCode(404)
            .body(is("{}"));
  }


}
