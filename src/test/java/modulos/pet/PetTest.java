package modulos.pet;

// Importações necessárias para os testes do módulo de produto

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;  // Importa a anotação DisplayName para definir o nome dos testes
import org.junit.jupiter.api.Test;  // Importa a anotação Test para indicar que um método é um teste

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


@DisplayName("Testes de API Rest dos métodos para o módulo 'Pet'") //Título principal
public class PetTest {

    @BeforeEach //Antes de cada teste faça algo
    public void beforeEach() {
        //Configurando os dados da API Rest da Lojinha
        baseURI = "https://petstore.swagger.io/v2";
        //Caminho inicial da aplicação que se repete em todas as URL
        basePath = "/pet";
    }


    //GET DE BUSCA DE PET POR STATUS
    @Test
    @DisplayName("Cenário 01 - Validar a procura de pets por status available")
    public void testValidarAProcuraDePetsPorStatusAvailable() {
        // Criação dos objetos PetPojo com diferentes status
        // Chamada do serviço para cada status e validação
        given().
                relaxedHTTPSValidation().
                queryParam("status", "available"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/findByStatus").
                then().
                assertThat().statusCode(200).and().log().all();
    }

    @Test
    @DisplayName("Cenário 02 - Validar a procura de pets por status pending")
    public void testValidarAProcuraDePetsPorStatusPending() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "pending"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/findByStatus").
                then().
                assertThat().statusCode(200).and().log().all();
    }

    @Test
    @DisplayName("Cenário 03 - Validar a procura de pets por status sold")
    public void testValidarAProcuraDePetsPorStatusSold() {
        given().
                relaxedHTTPSValidation().
                queryParam("status", "sold"). // Adiciona o status "available" como parâmetro de consulta
                when().
                get("/findByStatus").
                then().
                assertThat().statusCode(200).and().log().all();
    }

    @Test
    @DisplayName("Cenário 04 - Validar a procura de pets por ID")
    public void testValidarAProcuraDePetsPorId() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/2837244985").
                then().
                assertThat().statusCode(200).and().log().all();
    }


    //GET DE BUSCA DE PET POR ID
    @Test
    @DisplayName("Cenário 05 - Validar a procura de pets por ID com erro")
    public void testValidarAProcuraDePetsPorIdComErro() {
        given().
                relaxedHTTPSValidation().
                when().
                get("/0").
                then().
                assertThat().
                body("message", equalTo("Pet not found")).
                statusCode(404).and().log().all();
    }
}