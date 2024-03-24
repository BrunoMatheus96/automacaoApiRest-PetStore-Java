package DataFactory;

import pojo.CategoryPojo;

public class NewCategoryDataFactor {
    public static CategoryPojo envioDeCategoryNovas(String name, Integer id) {
        CategoryPojo category = new CategoryPojo();

        category.setId(id);
        category.setName(name);

        return category;
    }
}
