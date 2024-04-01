package BaseUrl;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BaseUrlTest {
    @BeforeEach //Antes de cada teste faça algo
    public void beforeEach() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}
