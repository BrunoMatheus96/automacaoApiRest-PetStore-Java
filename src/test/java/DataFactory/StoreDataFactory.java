package DataFactory;

import io.restassured.http.ContentType;
import pojo.StorePojo;

import static BaseUrl.BaseUrlStore.basePathStore;
import static io.restassured.RestAssured.given;

public class StoreDataFactory {
    public  static StorePojo cadastroStore(){
        StorePojo storeCadastro = new StorePojo();

        storeCadastro.setId(9);
        storeCadastro.setPetId(10);
        storeCadastro.setQuantity(11);
        storeCadastro.setShipDate("2024-03-31T20:07:00.342Z");
        storeCadastro.setStatus("Teste Bruno");
        storeCadastro.setComplete(true);

        return storeCadastro;
    }

    public static void beforeFindForId() {
        // Realiza o cadastro antes de buscar o ID
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(cadastroStore()).
                when().
                post(basePathStore);
    }
}
