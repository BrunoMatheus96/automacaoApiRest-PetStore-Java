package modulos.pet;

// Importações necessárias para os testes do módulo de produto

import DataFactory.PetDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import pojo.PetPojo;

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
        //basePath = "/pet";
    }

    String[] methods = {"POST", "PUT", "PATCH", "DELETE"};

    //GET - BUSCA DE PET POR STATUS
    @Test
    @DisplayName("Busca por Status 01 - Validar a procura de pets por status available")
    public void testBuscaPorStatus01() {
        // Criação dos objetos PetPojo com diferentes status
        // Chamada do serviço para cada status e validação
        given().
                relaxedHTTPSValidation().
                queryParam("status", "available"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/pet/findByStatus").
                then().
                assertThat().statusCode(200).and().
                assertThat().body("status", hasItem("available")). // Verifica se pelo menos um dos pets retornados tem status "available"
                log().all();
    }

    @Test
    @DisplayName("Busca por Status 02 - Validar a procura de pets por status pending")
    public void testBuscaPorStatus02() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "pending"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/pet/findByStatus").
                then().
                assertThat().statusCode(200).and().
                assertThat().body("status", hasItem("pending")). // Verifica se pelo menos um dos pets retornados tem status "available"
                log().all();
    }

    @Test
    @DisplayName("Busca por Status 03 - Validar a procura de pets por status sold")
    public void testBuscaPorStatus03() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "sold"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/pet/findByStatus").
                then().
                assertThat().statusCode(200).
                assertThat().body("status", hasItem("sold")). // Verifica se pelo menos um dos pets retornados tem status "available"
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

    //GET - BUSCA DE PET POR ID

    String petId = "/pet/9";

    @Test
    @DisplayName("Busca por ID 01 - Validar a procura de pets por ID")
    public void testBuscaPorId01() {
        given().
                relaxedHTTPSValidation().
                when().
                get(petId).
                then().
                assertThat().statusCode(200).and().
                log().all();
    }

    @Test
    @DisplayName("Busca por ID 02 - Validar a procura de pets por ID com erro")
    public void testBuscaPorId02() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/pet/0").
                then().
                statusCode(404).
                assertThat().body("message", equalTo("Pet not found")).
                assertThat().body("type", equalTo("error")). // Verifica se o tipo de erro é "error"
                log().all();
    }

    @Test
    @DisplayName("Busca por ID 03 - Valida se o campo id é um number")
    public void testBuscaPorId04() {
        given().
                relaxedHTTPSValidation().
                when().
                get(petId).
                then().
                assertThat().statusCode(200).
                assertThat().body("id", instanceOf(Number.class)).
                assertThat().body("id", equalTo(9)).
                log().all();
    }

    //POST - Criação de um novo Pet
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
                statusCode(200).log().all();
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
    @DisplayName("Criação de Pet 03- Validar campos presentes no Payload")
    public void testCricaoDePet03() {
        given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(PetDataFactory.envioDeDados()).
                when().
                get("/pet/{id}", 1). // Substitua {id} pelo ID real do pet.
                then().
                assertThat().
                body("id", instanceOf(Number.class)).
                body("category", instanceOf(Object.class)).
                body("name", instanceOf(String.class)).
                body("photoUrls", instanceOf(List.class)).
                body("photoUrls", everyItem(instanceOf(String.class))).
                body("tags", instanceOf(List.class)).
                body("tags", everyItem(instanceOf(Object.class))).
                body("status", instanceOf(String.class)).
                assertThat().statusCode(200).
                log().all(); // Log de todos os detalhes da resposta para debug.
    }

}