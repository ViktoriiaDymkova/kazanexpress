package tests.api;

import io.restassured.builder.ResponseSpecBuilder;

import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.http.ContentType.JSON;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {
    public static RequestSpecification request;

    static {
        request = with()
                //.filter(withCustomTemplates())
                .contentType(JSON)
                .baseUri("https://api.kazanexpress.ru")
                .basePath("/api")
                .log().all();
    }
    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
}
