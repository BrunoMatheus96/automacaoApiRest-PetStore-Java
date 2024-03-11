package DataFactory;

import pojo.TagPojo;

public class TagDataFactory {
    public static TagPojo envioDeTags() {

        TagPojo tag = new TagPojo();

        tag.setName("Amor da minha vida");
        tag.setId(1);

        return tag;
    }
}
