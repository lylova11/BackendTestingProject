package hw3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class SpoonacularTest {

    @Test
    void getSearchDietTest () {
        given()
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("diet", "vegetarian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?diet=vegetarian&apiKey=0f0bf970d95c40fb8911036ed284b469")
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchThaiFood () {
        given()
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("query", "Thai")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?query=Thai")
                .then()
                .statusCode(200);
    }

    @Test
    void getExcludeAsianFood() {

        JsonPath response = given()
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("excludeCuisine", "Chinese")
                .queryParam("excludeCuisine", "Korean")
                .queryParam("excludeCuisine", "Thai")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?excludeCuisine=Chinese&excludeCuisine=Korean&excludeCuisine=Thai")
                .body()
                .jsonPath();
        assertThat(response.get("offset"),equalTo(0));
        assertThat(response.get("number"),equalTo(10));
        assertThat(response.get("totalResults"),equalTo(5175));
        assertThat(response.get("results"), notNullValue());
    }

    @Test
    void getEuropeanGlutenFreeFood() {
        given()
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("diet", "Gluten Free")
                .queryParam("cuisine", "European")
                .queryParam("equipment", "pan")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?diet=Gluten Free&cuisine =European&equipment=pan")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void getTomatoEggsAmericanFood() {
        given()
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("includeIngredients", "tomato")
                .queryParam("includeIngredients", "eggs")
                .queryParam("cuisine", "American")
                .queryParam("type", "main course")
                .queryParam("instructionsRequired", "True")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?includeIngredients=tomato&includeIngredients=eggs&cuisine=American&type=main course&instructionsRequired=True")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }



    @Test
    void postClassifyCuisine() {
        given()
            .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
            .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
            .queryParam("title", "Thai pasta")
            .queryParam("ingredientList", "pasta")
            .queryParam("language", "en")
            .when()
            .post("https://api.spoonacular.com/recipes/cuisine?title=Thai pasta&ingredientList=pasta&language=en")
            .then()
            .assertThat()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK")
            .statusLine(containsString("OK"))
            .header("Connection", "keep-alive")
            .contentType(ContentType.JSON)
            .time(lessThan(2000L));
    }


    @Test
    void postClassifyCuisine2() {
        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("title", "Roast chicken")
                .queryParam("ingredientList", "chicken")
                .queryParam("language", "de")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?title=Roast chicken&ingredientList=chicken&language=de")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void postClassifyCuisine3() {
        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("title", "Russian pancakes with caviar")
                .queryParam("ingredientList", "caviar")
                .queryParam("language", "en")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?title=Roast chicken&ingredientList=chicken&language=en")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }

    @Test
    void postClassifyCuisine4() {
        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", "0f0bf970d95c40fb8911036ed284b469")
                .queryParam("title", "Spicy shrimps")
                .queryParam("ingredientList", "shrimps")
                .queryParam("language", "de")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?title=Roast chicken&ingredientList=chicken&language=de")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }
}


