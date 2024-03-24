package DataFactory;

import pojo.PetPojo;
import pojo.TagPojo;

import java.util.ArrayList;
import java.util.List;

public class NewPetDataFactory {
    public static PetPojo atualizacaoDeDados(Integer id, String name, String status) {
        PetPojo informacoes = new PetPojo();

        informacoes.setId(id);
        informacoes.setCategory(NewCategoryDataFactor.envioDeCategoryNovas("Teste 02", 1));
        informacoes.setName(name);

        List<TagPojo> tag = new ArrayList<>();
        tag.add(NewTagDataFactory.envioDeTagsNovas("Amor da minha vida 02", 11));
        informacoes.setTags(tag);

        informacoes.setStatus(status);


        return informacoes;
    }
}
