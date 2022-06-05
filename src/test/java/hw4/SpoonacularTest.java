package hw4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class SpoonacularTest {

    @BeforeAll
    static void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void getSearchDietTest() {
        given().spec(requestSpecification)
                .queryParam("diet", "vegetarian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?diet=vegetarian&apiKey=0f0bf970d95c40fb8911036ed284b469")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getSearchThaiFood() {
        given().spec(requestSpecification)
                .queryParam("query", "Thai")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?query=Thai")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void getEuropeanGlutenFreeFood() {
        given().spec(requestSpecification)

                .queryParam("diet", "Gluten Free")
                .queryParam("cuisine", "European")
                .queryParam("equipment", "pan")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?diet=Gluten Free&cuisine =European&equipment=pan")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getTomatoEggsAmericanFood() {
        given().spec(requestSpecification)
                .queryParam("includeIngredients", "tomato")
                .queryParam("includeIngredients", "eggs")
                .queryParam("cuisine", "American")
                .queryParam("type", "main course")
                .queryParam("instructionsRequired", "True")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?includeIngredients=tomato&includeIngredients=eggs&cuisine=American&type=main course&instructionsRequired=True")
                .then().spec(responseSpecification)
                .assertThat()
                .header("Connection", "keep-alive")
                .time(lessThan(2000L));
    }

    @Test
    void postClassifyCuisine() {
        given().spec(requestSpecification)
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("title", "Thai pasta")
                .queryParam("ingredientList", "pasta")
                .queryParam("language", "en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?title=Thai pasta&ingredientList=pasta&language=en")
                .then().spec(responseSpecification)
                .assertThat()
                .header("Connection", "keep-alive")
                .time(lessThan(2000L));
    }
}
