package modulos.pet;

import DataFactory.StoreDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest dos métodos para o módulo 'Store'") //Título principal
public class StoreTest {
    @BeforeEach //Antes de cada teste faça algo
    public void beforeEach() {
        //Configurando os dados da API Rest da Lojinha
        baseURI = "https://petstore.swagger.io/v2";
        //Caminho inicial da aplicação que se repete em todas as URL
    }

    /*-----------------------------------GET - RETORNA INVENTÁRIO DE PET POR STATUS-----------------------------------*/
    @Test
    @DisplayName("Retorna inventário de Pet por Status 01 - Validar Status 200")
    public void testInventarioPorStatus01() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/store/inventory").
                then().
                assertThat().statusCode(200).
                and().log().all();
    }

    @Test
    @DisplayName("Retorna inventário de Pet por Status 02 - Verificar se a API retorna o código de status HTTP correto para cada método.")
    public void testInventarioPorStatus02() {
        String[] methods = {"POST", "PUT", "PATCH", "DELETE"};
        for (String method : methods) {
            given().
                    relaxedHTTPSValidation().
                    when().
                    request(method, "/store/inventory").
                    then().
                    assertThat().statusCode(405).
                    assertThat().body("apiResponse.type", equalTo("unknown")).
                    and().log().all();
        }
    }

    @Test
    @DisplayName("Retorna inventário de Pet por Status 03 - Validar estrutura de resposta com Swagger")
    public void testInventarioPorStatus03() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/store/inventory").
                then().
                assertThat().statusCode(200).
                assertThat().contentType(ContentType.JSON).
                assertThat().
                body("sold", instanceOf(Integer.class)). // Verifica se 'sold' é um número
                body("commercial", instanceOf(Integer.class)). // Verifica se 'commercial' é um número
                and().log().all();
    }

    /*---------------------------------------------GET - PESQUISA POR ID ---------------------------------------------*/
    @Test
    @DisplayName("Pesquisa por Id 01 - Validar Status 200")
    public void testPesquisaPorId01() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/store/order/10").
                then().
                assertThat().statusCode(200).
                and().log().all();
    }

    @Test
    @DisplayName("Pesquisa por Id 02 - Validar estrutura de resposta com Swagger")
    public void testPesquisaPorId02() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/store/order/10").
                then().
                assertThat().statusCode(200).
                assertThat().contentType(ContentType.JSON).
                assertThat().
                body("id", instanceOf(Integer.class)).
                body("petId", instanceOf(Integer.class)).
                body("quantity", instanceOf(Integer.class)).
                body("status", instanceOf(String.class)).
                body("complete", instanceOf(Boolean.class)).
                and().log().all();
    }

    @Test
    @DisplayName("Pesquisa por Id 03 -  Verificar se a API retorna o código de status HTTP correto para cada método.")
    public void testPesquisaPorId03() {
        String[] methods = {"POST", "PUT", "PATCH"};
        for (String method : methods) {
            given().
                    relaxedHTTPSValidation().
                    when().
                    request(method, "/store/order/10").
                    then().
                    assertThat().statusCode(405).
                    assertThat().body("apiResponse.type", equalTo("unknown")).
                    and().log().all();
        }
    }

    @Test
    @DisplayName("Pesquisa por Id 04 - Validar tipagem de dados")
    public void testPesquisaPorId04() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/store/order/Teste").
                then().
                assertThat().statusCode(404).
                assertThat().body("type", equalTo("unknown")).
                body("message", equalTo("java.lang.NumberFormatException: For input string: \"Teste\"")).
                and().log().all();
    }

    /*------------------------------------------------POST - CADASTRO-------------------------------------------------*/
    @Test
    @DisplayName("CADASTRO 01 - Validar Status 200 e seu retorno")
    public void testCadastro01() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(StoreDataFactory.cadastroStore()).
                when().
                post("/store/order").
                then().
                assertThat().statusCode(200).
                assertThat().body("id", equalTo(9)).
                body("petId", equalTo(10)).
                body("quantity", equalTo(11)).
                body("shipDate", equalTo("2024-03-31T20:07:00.342+0000")).
                body("status", equalTo("Teste Bruno")).
                body("complete", equalTo(true)).
                and().log().all();
    }

}
