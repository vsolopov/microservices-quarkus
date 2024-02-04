package org.agoncal.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class BookResourceTest {
    @Test
    void testCreateBookEndpoint() {
        String title = "Quarkus Learning";
        String author = "Vlad";
        int year = 2024;
        String genre = "IT";

        given()
                .formParam("title", title)
                .formParam("author", author)
                .formParam("year", year)
                .formParam("genre", genre)
                .when().post("/api/books")
                .then()
                .statusCode(201)
                .body("isbn_13", startsWith("13-"))
                .body("title", is(title))
                .body("author", is(author))
                .body("year_of_publication", is(year))
                .body("genre", is(genre))
                .body("creation_date", startsWith("20"));

    }
}