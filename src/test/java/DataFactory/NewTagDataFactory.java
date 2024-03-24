package DataFactory;

import pojo.TagPojo;

public class NewTagDataFactory {
    public static TagPojo envioDeTagsNovas(String name, Integer id) {

        TagPojo tag = new TagPojo();

        tag.setName(name);
        tag.setId(id);

        return tag;
    }
}
