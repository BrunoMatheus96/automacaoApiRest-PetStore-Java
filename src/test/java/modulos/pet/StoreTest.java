package modulos.pet;

import DataFactory.StoreDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static general.basePath.*;
import static io.restassured.RestAssured.*;
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
                get(basePathStoreIventory).
                then().
                statusCode(200).
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
                    request(method, basePathStoreIventory).
                    then().
                    statusCode(405).
                    assertThat().
                    body("apiResponse.type", equalTo("unknown")).
                    and().log().all();
        }
    }

    /*---------------------------------------------GET - PESQUISA POR ID ---------------------------------------------*/
    String Id = "/10";

    @Test
    @DisplayName("Pesquisa por Id 01 - Validar Status 200")
    public void testPesquisaPorId01() {
        given().
                relaxedHTTPSValidation().
                when().
                get(basePathStore + Id).
                then().
                statusCode(200).
                and().log().all();
    }

    @Test
    @DisplayName("Pesquisa por Id 02 - Validar estrutura de resposta com Swagger")
    public void testPesquisaPorId02() {
        given().
                relaxedHTTPSValidation().
                when().
                get(basePathStore + Id).
                then().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                body("id", instanceOf(Integer.class),
                        "petId", instanceOf(Integer.class),
                        "quantity", instanceOf(Integer.class),
                        "status", instanceOf(String.class),
                        "complete", instanceOf(Boolean.class)).
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
                    request(method, basePathStore + Id).
                    then().
                    statusCode(405).
                    assertThat().
                    body("apiResponse.type", equalTo("unknown")).
                    and().log().all();
        }
    }

    @Test
    @DisplayName("Pesquisa por Id 04 - Validar tipagem de dados")
    public void testPesquisaPorId04() {
        given().
                relaxedHTTPSValidation().
                when().
                get(basePathStore + "/Teste").
                then().
                statusCode(404).
                assertThat().
                body("type", equalTo("unknown"),
                        "message", equalTo("java.lang.NumberFormatException: For input string: \"Teste\"")).
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
                post(basePathStore).
                then().
                statusCode(200).
                assertThat().
                body("id", equalTo(9),
                        "petId", equalTo(10),
                        "quantity", equalTo(11),
                        "shipDate", equalTo("2024-03-31T20:07:00.342+0000"),
                        "status", equalTo("Teste Bruno"),
                        "complete", equalTo(true)).
                and().log().all();
    }

    @Test
    @DisplayName("CADASTRO 02 - Validar se a resposta da API está na estrutura esperada JSON")
    public void testCadastro02() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(StoreDataFactory.cadastroStore()).
                when().
                post(basePathStore).
                then().
                assertThat().contentType(ContentType.JSON).
                and().log().all();
    }

    @Test
    @DisplayName("CADASTRO 03 - Verificar se a API retorna o código de status HTTP correto para cada método")
    public void testCadastro03() {
        String[] methods = {"GET", "PUT", "PATCH", "DELETE", "HEAD"};
        for (String method : methods) {
            given().
                    relaxedHTTPSValidation().
                    when().
                    request(method, basePathStore).
                    then().
                    statusCode(405).
                    and().log().all();
        }
    }

    @Test
    @DisplayName("CADASTRO 04 - Validar Status 200 e tipagem do retorno")
    public void testCadastro04() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(StoreDataFactory.cadastroStore()).
                when().
                post(basePathStore).
                then().
                statusCode(200).
                assertThat().
                body("id", instanceOf(Integer.class),
                        "petId", instanceOf(Integer.class),
                        "quantity", instanceOf(Integer.class),
                        "status", instanceOf(String.class),
                        "complete", instanceOf(Boolean.class)).
                and().log().all();
    }

}
