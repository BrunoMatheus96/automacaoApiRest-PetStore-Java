package DataFactory;

import pojo.StorePojo;

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
}
