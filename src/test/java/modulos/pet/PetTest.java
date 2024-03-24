package modulos.pet;

// Importações necessárias para os testes do módulo de produto

import DataFactory.NewPetDataFactory;
import DataFactory.PetDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@DisplayName("Testes de API Rest dos métodos para o módulo 'Pet'") //Título principal
public class PetTest {

    @BeforeEach //Antes de cada teste faça algo
    public void beforeEach() {
        //Configurando os dados da API Rest da Lojinha
        baseURI = "https://petstore.swagger.io/v2";
        //Caminho inicial da aplicação que se repete em todas as URL
    }

    String[] methods = {"POST", "PUT", "PATCH", "DELETE"};

    /*-------------------------------------GET - BUSCA DE PET POR STATUS----------------------------------------*/
    @Test
    @DisplayName("Busca por Status 01 - Validar a procura de pets por status available e a estrutura da resposta")
    public void testBuscaPorStatus01() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "available"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/pet/findByStatus").
                then().
                assertThat().statusCode(200).and().
                assertThat().body("id", everyItem(instanceOf(Number.class))). //Verfica que o id é número
                assertThat().body("category", everyItem(anyOf(instanceOf(Object.class), nullValue()))). // Verifica se cada item de category é um objeto ou nulo
                assertThat().body("category.id", everyItem(anyOf(instanceOf(Number.class), nullValue()))).
                assertThat().body("category.name", everyItem(anyOf(nullValue(), instanceOf(String.class)))). // Verifica se o campo category.name é do tipo String ou nulo
                assertThat().body("name", everyItem(anyOf(instanceOf(String.class), nullValue()))).
                assertThat().body("tags", everyItem(anyOf(instanceOf(ArrayList.class), nullValue()))). // Verifica se cada item TAGS é uma lista ou nulo
                assertThat().body("tags.id", everyItem(anyOf(instanceOf(Integer.class), instanceOf(List.class)))).
                assertThat().body("tags.name", everyItem(anyOf(instanceOf(String.class), instanceOf(List.class)))).
                assertThat().body("status", hasItem("available")).
                assertThat().contentType(ContentType.JSON).
                log().all();
    }

    @Test
    @DisplayName("Busca por Status 02 - Validar a procura de pets por status pending e a estrutura da resposta")
    public void testBuscaPorStatus02() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "pending"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/pet/findByStatus").
                then().
                assertThat().statusCode(200).and().
                assertThat().body("id", everyItem(instanceOf(Number.class))). //Verfica que o id é número
                assertThat().body("category", everyItem(anyOf(instanceOf(Object.class), nullValue()))). // Verifica se cada item de category é um objeto ou nulo
                assertThat().body("category.id", everyItem(instanceOf(Integer.class))). //Verifica se o campo category.id é do tipo Integer
                assertThat().body("category.name", everyItem(anyOf(nullValue(), instanceOf(String.class)))). // Verifica se o campo category.name é do tipo String ou nulo
                assertThat().body("name", everyItem(anyOf(instanceOf(String.class), nullValue()))).
                assertThat().body("tags", everyItem(anyOf(instanceOf(ArrayList.class), nullValue()))). // Verifica se cada item TAGS é uma lista ou nulo
                assertThat().body("tags.id", everyItem(anyOf(instanceOf(Integer.class), instanceOf(List.class)))).
                assertThat().body("tags.name", everyItem(anyOf(instanceOf(String.class), instanceOf(List.class)))).
                assertThat().body("status", hasItem("pending")).
                assertThat().contentType(ContentType.JSON).
                log().all();
    }

    @Test
    @DisplayName("Busca por Status 03 - Validar a procura de pets por status sold e a estrutura da resposta")
    public void testBuscaPorStatus03() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "sold"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/pet/findByStatus").
                then().
                assertThat().statusCode(200).and().
                assertThat().body("id", everyItem(instanceOf(Number.class))). //Verfica que o id é número
                assertThat().body("category", everyItem(anyOf(instanceOf(Object.class), nullValue()))). // Verifica se cada item de category é um objeto ou nulo
                assertThat().body("category.id", everyItem(instanceOf(Integer.class))). //Verifica se o campo category.id é do tipo Integer
                assertThat().body("category.name", everyItem(anyOf(nullValue(), instanceOf(String.class)))). // Verifica se o campo category.name é do tipo String ou nulo
                assertThat().body("name", everyItem(anyOf(instanceOf(String.class), nullValue()))).
                assertThat().body("tags", everyItem(anyOf(instanceOf(ArrayList.class), nullValue()))). // Verifica se cada item TAGS é uma lista ou nulo
                assertThat().body("tags.id", everyItem(anyOf(instanceOf(Integer.class), instanceOf(List.class)))).
                assertThat().body("tags.name", everyItem(anyOf(instanceOf(String.class), instanceOf(List.class)))).
                assertThat().body("status", hasItem("sold")).
                assertThat().contentType(ContentType.JSON).
                log().all();
    }

    @Test
    @DisplayName("Busca por Status 04 - Validar status 405")
    public void testBuscaPorStatus04() {

        for (String method : methods) {
            given().
                    relaxedHTTPSValidation().
                    queryParam("status", "sold").
                    when().
                    request(method, "/pet/findByStatus").
                    then().
                    assertThat().statusCode(405).and().log().all();
        }
    }

    /*-------------------------------------GET - BUSCA DE PET POR ID--------------------------------------------------*/

    String petId = "/pet/9";

    @Test
    @DisplayName("Busca por ID 01 - Validar a procura de pets por ID e a estrutura da resposta")
    public void testBuscaPorId01() {
        given().
                relaxedHTTPSValidation().
                when().
                get(petId).
                then().
                assertThat().statusCode(200).and().
                assertThat().body("id", instanceOf(Number.class)).
                assertThat().body("id", equalTo(9)).
                assertThat().body("category", (instanceOf(Object.class))). // Verifica se cada item de category é um objeto ou nulo
                assertThat().body("category.id", (instanceOf(Integer.class))). //Verifica se o campo category.id é do tipo Integer
                assertThat().body("category.name", (instanceOf(String.class))). // Verifica se o campo category.name é do tipo String ou nulo
                assertThat().body("name", (instanceOf(String.class))).
                assertThat().body("tags", (instanceOf(ArrayList.class))). // Verifica se cada item TAGS é uma lista ou nulo
                assertThat().body("tags.id", everyItem(anyOf(instanceOf(Integer.class), instanceOf(List.class)))).
                assertThat().body("tags.name", everyItem(anyOf(instanceOf(String.class), instanceOf(List.class)))).
                assertThat().body("status", (instanceOf(String.class))).
                assertThat().contentType(ContentType.JSON).
                log().all();
    }

    @Test
    @DisplayName("Busca por ID 02 - Validar a procura de pets por ID com erro")
    public void testBuscaPorId02() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/pet/10000").
                then().
                statusCode(404).
                assertThat().body("message", equalTo("Pet not found")).
                assertThat().body("type", equalTo("error")). // Verifica se o tipo de erro é "error"
                log().all();
    }

    @Test
    @DisplayName("Busca por ID 03 - Validar Status 405")
    public void testBuscaPorId03() {
        given().
                relaxedHTTPSValidation().
                when().
                put(petId).
                then().
                assertThat().statusCode(405).and().
                log().all();
    }

    /*-----------------------------------POST - Criação de um novo Pet------------------------------------------------*/
    @Test
    @DisplayName("Criação de Pet 01- Criar um Pet novo")
    public void testCricaoDePet01() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(PetDataFactory.envioDeDados()).
                when().
                post("/pet").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    @DisplayName("Criação de Pet 02- Validar status 405")
    public void testCricaoDePet02() {
        String[] methodsReqPost = {"PATCH", "DELETE", "GET"};
        for (String method : methodsReqPost) {
            given().
                    relaxedHTTPSValidation().
                    contentType(ContentType.JSON).
                    body(PetDataFactory.envioDeDados()).
                    when().
                    request(method, "/pet").
                    then().
                    assertThat().statusCode(405).and().log().all();
        }
    }

    @Test
    @DisplayName("Criação de Pet 03- Validar status 400")
    public void testCricaoDePet03() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body("").
                when().
                request("/pet").
                then().
                assertThat().statusCode(400).and().log().all();
    }

    @Test
    @DisplayName("Criação de Pet 04- Validar estrutura de payload e response")
    public void testCricaoDePet04() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(PetDataFactory.envioDeDados()).
                when().
                post("/pet").
                then().
                statusCode(200).
                assertThat().body("id", instanceOf(Number.class)).
                assertThat().body("id", equalTo(9)).
                assertThat().body("category", (instanceOf(Object.class))). // Verifica se cada item de category é um objeto ou nulo
                assertThat().body("category.id", equalTo(0)).
                assertThat().body("category.name", equalTo("Teste")).
                assertThat().body("name", equalTo("Lua")).
                assertThat().body("tags", (instanceOf(ArrayList.class))).
                assertThat().body("tags.id", hasItem(1)).
                assertThat().body("tags.name", everyItem(equalTo("Amor da minha vida"))).
                assertThat().body("status", equalTo("pending")).
                assertThat().contentType(ContentType.JSON). //Valida se a resposta da API está na estrutura esperada (JSON, XML, etc.).
                //Tipagem de dados nas respostas deve estar igual ao Swagger
                assertThat().body("category.id", (instanceOf(Integer.class))).
                assertThat().body("category.name", (instanceOf(String.class))).
                assertThat().body("name", (instanceOf(String.class))).
                assertThat().body("tags", (instanceOf(ArrayList.class))).
                assertThat().body("tags.id", everyItem(anyOf(instanceOf(Integer.class), instanceOf(List.class)))).
                assertThat().body("tags.name", everyItem(anyOf(instanceOf(String.class), instanceOf(List.class)))).
                assertThat().body("status", (instanceOf(String.class))).
                log().all();
    }

    /*-----------------------------------------PUT - Atualizações-----------------------------------------------------*/

    @Test
    @DisplayName("Atualização de Pet 01- Atualização de campos")
    public void testAtualizacaoDePet01() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(NewPetDataFactory.atualizacaoDeDados(10, "Lua 02", "sold")).
                when().
                put("/pet").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    @DisplayName("Atualização de Pet 02 - Validar tipagem de dados nas respostas deve estar igual ao Swagger")
    public void testAtualizacaoDePet02() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(NewPetDataFactory.atualizacaoDeDados(10, "Lua 02", "sold")).
                when().
                put("/pet").
                then().
                statusCode(200).
                assertThat().body("category.id", (instanceOf(Integer.class))).
                assertThat().body("category.name", (instanceOf(String.class))).
                assertThat().body("name", (instanceOf(String.class))).
                assertThat().body("tags", (instanceOf(ArrayList.class))).
                assertThat().body("tags.id", everyItem(anyOf(instanceOf(Integer.class), instanceOf(List.class)))).
                assertThat().body("tags.name", everyItem(anyOf(instanceOf(String.class), instanceOf(List.class)))).
                assertThat().body("status", (instanceOf(String.class))).
                log().all();
    }

    @Test
    @DisplayName("Atualização de Pet 03 - Validar se a resposta da API está na estrutura esperada (JSON, XML, etc.).")
    public void testAtualizacaoDePet03() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(NewPetDataFactory.atualizacaoDeDados(10, "Lua 02", "sold")).
                when().
                put("/pet").
                then().
                statusCode(200).
                assertThat().contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Atualização de Pet 04 - Validar que o response da API deve estar igual ao do Swagger.")
    public void testAtualizacaoDePet04() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(NewPetDataFactory.atualizacaoDeDados(10, "Lua 02", "sold")).
                when().
                put("/pet").
                then().
                statusCode(200).
                assertThat().contentType(ContentType.JSON).
                assertThat().body("id", equalTo(10)).
                assertThat().body("category.id", equalTo(1)).
                assertThat().body("category.name", equalTo("Teste 02")).
                assertThat().body("name", equalTo("Lua 02")).
                assertThat().body("tags.id", hasItem(11)).
                assertThat().body("tags.name", everyItem(equalTo("Amor da minha vida 02"))).
                assertThat().body("status", equalTo("sold")).log().all();
    }

    /*-----------------------------------POST - Update de Pet com a data----------------------------------------------*/


    /*--------------------------------------DELETE - Exclusão de Pet--------------------------------------------------*/

}