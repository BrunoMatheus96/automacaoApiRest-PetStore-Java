package DataFactory;

import org.junit.experimental.categories.Category;
import pojo.CategoryPojo;

public class CategoryDataFactory {
    public static Category createSampleCategory() {
        CategoryPojo category = new CategoryPojo();

        return (Category) category;
    }
}
