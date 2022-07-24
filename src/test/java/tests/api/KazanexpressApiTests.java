package tests.api;

import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableList;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.request.Pagination;
import tests.api.models.request.QueryInput;
import tests.api.models.request.RequesSearch;
import tests.api.models.request.Variables;
import tests.api.models.responce.ResponceSearch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static tests.api.Specs.*;

public class KazanexpressApiTests {

    String authorization = "Basic a2F6YW5leHByZXNzLWN1c3RvbWVyOmN1c3RvbWVyU2VjcmV0S2V5";


    @Tag("api")
    @Test
    @DisplayName("Позитивная проверка авторизации и получения токена")
    public void authTokenTest() {
        given()
                .spec(request)
                .formParam("grant_type", "password")
                .formParam("username", "9312398004")
                .formParam("password", "UZTgf2mW")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("authorization", authorization)
                .when()
                .post("/oauth/token")
                .then()
                .spec(response200)
                .body("token_type", is("bearer"))
                .body("refresh_token", notNullValue())
                .body("access_token", notNullValue());
    }

    @Tag("api")
    @Test
    @DisplayName("Негативная проверка авторизации и получения токена")
    public void authBadTokenTest() {

        Faker faker = new Faker();
        String fakeusername = faker.numerify("##########");

        given()
                .spec(request)
                .formParam("grant_type", "password")
                .formParam("username", fakeusername)
                .formParam("password", "UZTgf2mW")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("authorization", authorization)
                .when()
                .post("/oauth/token")
                .then()
                .spec(response400)
                .body("token_type", is(null));
        //                .body("token_type", is("bearer")); - это ответ в положительной проверке
    }

    @Tag("api")
    @Test
    @DisplayName("Проверка поиска товара по API")
    public void goodsTest() {

        String searchText = "стиральный порошок tide 3 кг",
                queryParam = "query getMakeSearch($queryInput: MakeSearchQueryInput!) " +
                        "{\n  makeSearch(query: $queryInput) {\n    id\n    queryId\n    queryText\n    category " +
                        "{\n      ...CategoryShortFragment\n      __typename\n    }\n    categoryTree " +
                        "{\n      category {\n        ...CategoryFragment\n        __typename\n      }\n      total\n    " +
                        "  __typename\n    }\n    items {\n      catalogCard {\n        __typename\n        ...SkuGroupCardFragment\n      }\n " +
                        "     __typename\n    }\n    facets {\n      ...FacetFragment\n      __typename\n    }\n    total\n    " +
                        "mayHaveAdultContent\n    __typename\n  }\n}\n\nfragment FacetFragment on Facet {\n  filter " +
                        "{\n    id\n    title\n    type\n    measurementUnit\n    description\n    __typename\n  }\n  buckets " +
                        "{\n    filterValue {\n      id\n      description\n      image\n      name\n      __typename\n    }\n    " +
                        "total\n    __typename\n  }\n  range {\n    min\n    max\n    __typename\n  }\n  __typename\n}\n\nfragment " +
                        "CategoryFragment on Category {\n  id\n  icon\n  parent {\n    id\n    __typename\n  }\n  seo {\n    header\n  " +
                        "  metaTag\n    __typename\n  }\n  title\n  adult\n  __typename\n}\n\nfragment CategoryShortFragment on Category " +
                        "{\n  id\n  parent {\n    id\n    title\n    __typename\n  }\n  title\n  __typename\n}\n\nfragment " +
                        "SkuGroupCardFragment on SkuGroupCard {\n  ...DefaultCardFragment\n  photos {\n    key\n    link(trans: PRODUCT_540) " +
                        "{\n      high\n      low\n      __typename\n    }\n    previewLink: link(trans: PRODUCT_240) " +
                        "{\n      high\n      low\n      __typename\n    }\n    __typename\n  }\n  badges {\n    ... on BottomTextBadge " +
                        "{\n      backgroundColor\n      description\n      id\n      link\n      text\n      textColor\n      __typename\n    " +
                        "}\n    __typename\n  }\n  characteristicValues {\n    id\n    value\n    title\n    characteristic {\n      values " +
                        "{\n        id\n        title\n        value\n        __typename\n      }\n      title\n      id\n      __typename\n " +
                        "   }\n    __typename\n  }\n  __typename\n}\n\nfragment DefaultCardFragment on CatalogCard {\n  adult\n  favorite\n " +
                        " feedbackQuantity\n  id\n  minFullPrice\n  minSellPrice\n  offer {\n    due\n    icon\n    text\n    textColor\n   " +
                        " __typename\n  }\n  badges {\n    backgroundColor\n    text\n    textColor\n    __typename\n  }\n  ordersQuantity\n " +
                        " productId\n  rating\n  title\n  __typename\n}";

        RequesSearch requesSearch = new RequesSearch();
        QueryInput queryInput = new QueryInput();
        Variables variables = new Variables();
        Pagination pagination = new Pagination();
        requesSearch.setOperationName("getMakeSearch");
        requesSearch.setQuery(queryParam);
        requesSearch.setVariables(variables);
        variables.setQueryInput(queryInput);
        queryInput.setFilters(ImmutableList.of());
        queryInput.setPagination(pagination);
        queryInput.setSort("BY_RELEVANCE_DESC");
        queryInput.setShowAdultContent("NONE");
        queryInput.setText(searchText);
        pagination.setLimit(0);
        pagination.setOffset(0);

        ResponceSearch responceSearch = given()
                .contentType(ContentType.JSON)
                .body(requesSearch)
                .when().log().all()
                .post("https://dshop.kznexpress.ru/")
                .then().log().all()
                .statusCode(200)
                .extract().as(ResponceSearch.class);
        Assertions.assertEquals(responceSearch.getData().getMakeSearch().getQueryText(), searchText);
        Assertions.assertNotNull(responceSearch.getData().getMakeSearch().getId());
        Assertions.assertNotNull(responceSearch.getData().getMakeSearch().getQueryId());
        Assertions.assertNotNull(responceSearch.getData());
    }
}
