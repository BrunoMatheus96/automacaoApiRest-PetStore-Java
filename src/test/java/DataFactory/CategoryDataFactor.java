package DataFactory;

import org.junit.experimental.categories.Category;
import pojo.CategoryPojo;

public class CategoryDataFactor {
    public static CategoryPojo envioDeCategory() {
        CategoryPojo category = new CategoryPojo();

        category.setId(0);
        category.setName("Teste");

        return category;
    }
}
