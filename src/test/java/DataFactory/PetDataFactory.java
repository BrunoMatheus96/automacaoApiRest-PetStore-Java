package DataFactory;

import pojo.CategoryPojo;
import pojo.PetPojo;
import pojo.TagPojo;

import java.util.ArrayList;
import java.util.List;

public class PetDataFactory {
    public static PetPojo envioDeDados(){
        PetPojo informacoes = new PetPojo();

        informacoes.setId(9);
        informacoes.setCategory(CategoryDataFactor.envioDeCategory());
        informacoes.setName("Lua");

        List<String> photo = new ArrayList<>();
        photo.add("Da certo, por favor");
        informacoes.setPhotoUrls(photo);

        List<TagPojo> tag = new ArrayList<>();
        tag.add(TagDataFactory.envioDeTags());
        informacoes.setTags(tag);

        informacoes.setStatus("pending");


        return informacoes;
    }
}
