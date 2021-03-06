package tests.demowebshop;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

class TestDemowebshop extends TestBase {

  @Test
  @Tag("shop")
  @DisplayName("Уведомление по email")
  void notifyByMail() {
    Response response =
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("Nop.customer=dcd17cea-9b97-48ca-a934-8d8a9e9b2c62; __utmc=78382081; __utmz=78382081.1616779538.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ARRAffinity=06e3c6706bb7098b5c9133287f2a8d510a64170f97e4ff5fa919999d67a34a46; __utma=78382081.1806003553.1616779538.1616828183.1617780150.3; nop.CompareProducts=CompareProductIds=72; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=16&RecentlyViewedProductIds=31&RecentlyViewedProductIds=15&RecentlyViewedProductIds=72; __atuvc=7%7C14; __atuvs=606d5e20655f900a006; __utmb=78382081.18.10.1617780150")
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
    Response response =
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("Nop.customer=dcd17cea-9b97-48ca-a934-8d8a9e9b2c62; __utmc=78382081; __utmz=78382081.1616779538.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ARRAffinity=06e3c6706bb7098b5c9133287f2a8d510a64170f97e4ff5fa919999d67a34a46; __utma=78382081.1806003553.1616779538.1616828183.1617780150.3; nop.CompareProducts=CompareProductIds=72; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=16&RecentlyViewedProductIds=31&RecentlyViewedProductIds=15&RecentlyViewedProductIds=72; __atuvc=4%7C14; __atuvs=606d5e20655f900a003; __utmb=78382081.15.10.1617780150")
                    .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=19&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
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
